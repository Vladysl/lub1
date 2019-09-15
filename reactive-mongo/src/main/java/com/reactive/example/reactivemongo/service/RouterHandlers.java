package com.reactive.example.reactivemongo.service;


import com.reactive.example.reactivemongo.model.Employee;
import com.reactive.example.reactivemongo.model.EmployeeEvent;
import com.reactive.example.reactivemongo.repository.EmployeeRepository;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple2;

import java.time.Duration;
import java.util.Date;
import java.util.stream.Stream;


@Component
public class RouterHandlers {

    private EmployeeRepository repository;

    public RouterHandlers(EmployeeRepository repository) {
        this.repository = repository;
    }

    public Mono<ServerResponse> getAll(ServerRequest serverRequest) {
        return ServerResponse.ok()
                .body(repository.findAll(), Employee.class);
    }

    public Mono<ServerResponse> getByID(ServerRequest serverRequest) {
        String empId = serverRequest.pathVariable("id");

        return ServerResponse.ok()
                .body(repository.findById(empId), Employee.class);
    }

    public Mono<ServerResponse> getByEvents(ServerRequest serverRequest) {
        String empId = serverRequest.pathVariable("id");

        return ServerResponse.ok()
                .contentType(MediaType.TEXT_EVENT_STREAM)
                .body(repository.findById(empId)
                        .flatMapMany(
                                employee -> {
                                    Flux<Long> interval = Flux.interval(Duration.ofSeconds(2));
                                    Flux<EmployeeEvent> employeeEventFlux = Flux.fromStream(Stream.generate(() -> new EmployeeEvent(employee, new Date())));

                                    return Flux.zip(interval, employeeEventFlux)
                                            .map(Tuple2::getT2);
                                }
                        ), EmployeeEvent.class);
    }
}
