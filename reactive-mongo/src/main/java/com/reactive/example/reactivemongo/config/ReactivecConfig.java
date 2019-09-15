package com.reactive.example.reactivemongo.config;

import com.reactive.example.reactivemongo.service.RouterHandlers;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;

@Configuration
public class ReactivecConfig {

    @Bean
    RouterFunction<?> routerFunction(RouterHandlers routerHandlers) {
        return RouterFunctions.route(RequestPredicates.GET("/route/all"), routerHandlers::getAll)
                .andRoute(RequestPredicates.GET("/route/{id}"), routerHandlers::getByID)
                .andRoute(RequestPredicates.GET("/route/{id}/events"), routerHandlers::getByEvents);
    }
}
