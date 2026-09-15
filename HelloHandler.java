package com.seleniumexpress.demo.handler;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;

@Component
public class HelloHandler {

    public Mono<ServerResponse> hellohandler() {

        Flux<String> dataPublisher = Flux.just("hello","world","learning","spring-reactive");
        Mono<ServerResponse> serverResponse = ServerResponse
                .ok()
                .body(dataPublisher,String.class);
        return serverResponse;
    }

    // functional style of coding
    public Mono<ServerResponse> helloHandlerPathVariable(ServerRequest serverRequest) {

        String pathVariableName = serverRequest.pathVariable("yourName");
        String serverResponse = "Your name is : " + pathVariableName;
        Mono<String> publisher = Mono.just(serverResponse);
        Mono<ServerResponse> monoResponse = ServerResponse.ok().body(publisher, String.class);
        return monoResponse;
    }
}
