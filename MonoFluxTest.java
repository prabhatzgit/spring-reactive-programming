package com.java.techie.demo.springreactivejavatechie;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


public class MonoFluxTest {

    @Test
    public void testMono() {
        // Mono<String> monoString = Mono.just("learning spring reactive");
        // to check whether Mono is following Spring reactive flow or not use log()
        // after adding log(), each of execution print on the console
        Mono<String> monoStringSuccess = Mono.just("learning spring reactive").log();
        monoStringSuccess.subscribe(System.out::println);

        Mono<?> monoStringFailure = Mono.just("learning spring reactive")
                .then(Mono.error(new RuntimeException("exception occured")))
                .log();
        monoStringFailure.subscribe(System.out::println, (e)->System.out.println(e.getMessage()));
    }

    @Test
    public void testFlux() {
        Flux<String> fluxStringSuccess = Flux.just("Spring", "Spring Boot", "Hibernate", "Microservice")
                // Concept: If we want to add something in middle of the operation
                .concatWithValues("AWS")
                .log();
        fluxStringSuccess.subscribe(System.out::println);

        Flux<String> fluxStringFailure = Flux.just("Spring", "Spring Boot", "Hibernate", "Microservice")
                .concatWithValues("AWS")
                // Forcefully want to throw some error
                .concatWith(Flux.error(new RuntimeException("Exception occurred in Flux")))
                // Concept: If we add value to this flux after error, then it will not add the values into the flux
                .concatWithValues("Cloud")
                .log();
        // fluxStringSuccess.subscribe(System.out::println);
        fluxStringFailure.subscribe(System.out::println, (e)-> System.out.println(e.getMessage()));
    }
}


