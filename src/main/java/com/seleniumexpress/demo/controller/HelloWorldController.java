package com.seleniumexpress.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

@RestController
public class HelloWorldController {

    @GetMapping("/hello")
    public List<String> helloWorldController(){
        List<String> listOfString = List.of("hello","world","hi","everyone");
        return listOfString;
    }

    @GetMapping("/understanding-drawback-of-blocking-call")
    public List<String> understandingDrawbackOfBlockingCall() throws InterruptedException {
        List<String> listOfString = List.of("hello","world","hi","everyone");
        ArrayList<String> returnList = new ArrayList<>();
        // long running task
        for(String str : listOfString){
            // can check whichever thread is able to process the elements
            // reactor-http-nio-3 has processed all these elements and we can check the console
            System.out.println("Element " + str + " processed by thread : - " + Thread.currentThread().getName());
            returnList.add(str);
            // It will block 1 ms while adding each element
            // If we do db operations by multiple users, and it might be provided OutOfMemoryError
            // this is a blocking call
            Thread.sleep(2000);
        }
        return returnList;
    }

    @GetMapping("/benefits-of-asynchronous-call-using-flux")
    public Flux<String> benefitsOfSpringReactiveUsingFlux() throws InterruptedException {

        List<String> listOfString = List.of("hello","world","hi","everyone");
        // Flux is a publisher and helps to create reactive programming
        // Flux publishes zero to many data
        Flux<String> publisher = Flux.fromIterable(listOfString).delayElements(Duration.ofSeconds(2)).log();
        // In the above code, the client means here postman consumes the data from above publisher in
        // each interval of 2 seconds

        // But if we consider above synchronous api or scenario, there it wait the sleep() time period
        // and then it gives data to client

        // the parallel threads which publish the data from stream are managed by netty server
        return publisher;
    }

    @GetMapping("/benefits-of-asynchronous-call-using-mono")
    public Mono<String> benefitsOfSpringReactiveUsingMono() throws InterruptedException {

        // Mono publishes only single element or object from stream
        return Mono.just("Prabhat").log();
    }
}
