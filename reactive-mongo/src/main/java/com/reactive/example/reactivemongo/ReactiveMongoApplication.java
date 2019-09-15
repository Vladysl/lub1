package com.reactive.example.reactivemongo;

import com.reactive.example.reactivemongo.model.Employee;
import com.reactive.example.reactivemongo.repository.EmployeeRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.UUID;
import java.util.stream.Stream;

@SpringBootApplication
public class ReactiveMongoApplication {

    public static void main(String[] args) {
        SpringApplication.run(ReactiveMongoApplication.class, args);
    }

    @Bean
    CommandLineRunner employee(EmployeeRepository employeeRepository) {
        return strings -> {
            employeeRepository
                    .deleteAll()
                    .subscribe(null, null, () -> {
                        Stream.of(new Employee(UUID.randomUUID().toString(), "John", 2000L),
                                new Employee(UUID.randomUUID().toString(), "Ivan", 3000L),
                                new Employee(UUID.randomUUID().toString(), "Peter", 4000L))
                                .forEach(employee ->
                                        employeeRepository.save(employee)
                                                .subscribe(System.out::println));
                    });

        };
    }

}
