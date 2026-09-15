package com.learn.webflux.r2dbc.config;

import com.learn.webflux.r2dbc.controller.StudentController;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.*;

@Configuration
public class RouterConfig {

    private final StudentController studentController;

    public RouterConfig(StudentController studentController){
        this.studentController = studentController;
    }

    @Bean
    RouterFunction<ServerResponse> studentRouterConfig(){
        // RequestPredicates is the first parameter
        return RouterFunctions.route(RequestPredicates.GET("/students-functional-programming"),
                studentController::getAllStudentsFromDBByFunctionalApproach);
    }

    @Bean
    RouterFunction<ServerResponse> studentRouterConfigById(){
        // RequestPredicates is the first parameter
        return RouterFunctions.route(RequestPredicates.GET("/students-functional-programming/{studentId}"),
                studentController::getAllStudentsFromDBByFunctionalApproachById);
    }

    @Bean
    RouterFunction<ServerResponse> studentRouterConfigByQueryParam() {
        return RouterFunctions.route(
                RequestPredicates.GET("/students-functional-programming-by-query-param"),
                studentController::getAllStudentsWithDelayedQueryByName
        );
    }

}
