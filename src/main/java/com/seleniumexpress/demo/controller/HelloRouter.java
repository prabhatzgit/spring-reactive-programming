package com.seleniumexpress.demo.controller;

import com.seleniumexpress.demo.handler.HelloHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.concurrent.Delayed;

@Configuration
public class HelloRouter {

    @Autowired
    private HelloHandler helloHandler;

    @Bean
    RouterFunction<ServerResponse> routerConfig(){
        RequestPredicate requestPredicate = RequestPredicates.GET("/hello-webflux-in-functional-programming");

        HandlerFunction<ServerResponse> handlerFunction = new HandlerFunction<ServerResponse>() {
            @Override
            public Mono<ServerResponse> handle(ServerRequest request) {

                Flux<String> dataPublisher = Flux.just("hello","world","learning","spring-reactive").delayElements(Duration.ofSeconds(2)).log();
                Mono<ServerResponse> serverResponse = ServerResponse.ok()
                        .contentType(MediaType.TEXT_EVENT_STREAM)
                        .body(dataPublisher,String.class);
                return serverResponse;
            }
        };

        RouterFunction<ServerResponse> routerFunction = RouterFunctions.route(requestPredicate, handlerFunction);

        return routerFunction;
    }

    // functional programming coding standard
    @Bean
    RouterFunction<ServerResponse> routerConfigModified(){

        /*return RouterFunctions.route(RequestPredicates.GET("/hello-webflux-in-functional-programming-modified"), request -> {
            Mono<ServerResponse> serverResponse = helloHandler.hellohandler().delayElement(Duration.ofSeconds(2)).log();
            return serverResponse;
        });*/
        return RouterFunctions.route(RequestPredicates.GET("/hello-webflux-in-functional-programming-modified"), request -> helloHandler.hellohandler().delayElement(Duration.ofSeconds(2)).log());
    }

    @Bean
    RouterFunction<ServerResponse> routerForPathVariable(){

        // return RouterFunctions.route(RequestPredicates.GET("/hello/{yourName}"), request -> helloHandler.helloHandlerPathVariable(request));
        return RouterFunctions.route(RequestPredicates.GET("/hello/{yourName}"), helloHandler::helloHandlerPathVariable);
    }
}
