package com.seleniumexpresss.consumerapp.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.time.Duration;

@RestController
public class ConsumerController {

    WebClient webClient = WebClient.builder().baseUrl("http://localhost:8081").build();

    @GetMapping("/consume")
    public Mono<String> consumeData() {
        System.out.println("No of cores : " + Runtime.getRuntime().availableProcessors());
    return Mono.just("here is my data");
    }

    @GetMapping("/consume/delay")
    public Mono<String> consumeDataWithDealy() {
        //System.out.println("No of cores : " + Runtime.getRuntime().availableProcessors());
        return Mono.just("here is my data").delayElement(Duration.ofSeconds(10));
        // In console, we see Handler is being applied: org.springframework.http.server.reactive.ReactorHttpHandlerAdapter@eb81c11
        // 1
        // this is the entry point where spring framework kicks in
        // Channel creation, request receiving done by netty server
        // 2
        // Now the netty server gives request to spring framework
        // So, in between netty and spring f/w, there is a bridge and that is ReactorHttpHandlerAdapter
        // So, the request sent by netty server receive by ReactorHttpHandlerAdapter and gives to Spring framework
        // o.s.w.s.adapter.HttpWebHandlerAdapter    : [57a41093-1] HTTP GET "/consume" on top of dispatcherhandler this HttpWebHandlerAdapter
        // consumes request
        // In spring reactive, dispatcherhandler
        // Now dispatcherhandler route this request to RequestMappingHandlerMapping to find the specific request of controller
        // HandlerAdapter helps to invoke and execute the method
        // ResponseBodyResultHandler helps DispatcherHandler to sends a response back in html format and other format like json
        // HttpLogging prints the data
        // HttpWebHandlerAdapter gives status code response
        // HttpServerOperations     : [57a41093-1, L:/[0:0:0:0:0:0:0:1]:8082 - R:/[0:0:0:0:0:0:0:1]:60405] Decreasing pending responses count: 0
        // because the response has already been served
        // r.n.http.server.HttpServerOperations     : [57a41093-1, L:/[0:0:0:0:0:0:0:1]:8082 - R:/[0:0:0:0:0:0:0:1]:60405] Last HTTP packet was sent, terminating the channel
        // Once we open the channel or TCP connection: where request and response process
        // ctor-http-nio-2 : handles the request
    }

    @GetMapping("/consume/baseurl")
    public Mono<String> consumeEndpoint() {
        System.out.println("executing consume data method");
        Mono<String> response = webClient.get().uri("/hello").retrieve().bodyToMono(String.class);
        return response;
    }
}
