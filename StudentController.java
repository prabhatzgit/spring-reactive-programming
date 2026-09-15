package com.learn.webflux.r2dbc.controller;

import com.learn.webflux.r2dbc.model.Student;
import com.learn.webflux.r2dbc.service.StudentService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;

@RestController
public class StudentController {

    private StudentService studentService;

    public StudentController(StudentService studentService){
        this.studentService = studentService;
    }

    @GetMapping("/test")
    public Flux<Integer> getAllIntegers() {
        // Flux is a publisher which streams the data in reactive programming way
        return Flux.just(1, 2, 3, 4, 5);
    }

    /*
    the purpose of produces = MediaType.TEXT_EVENT_STREAM_VALUE is to tell Spring that
    the endpoint will return Server-Sent Events (SSE).

    produces = MediaType.TEXT_EVENT_STREAM_VALUE signals that the endpoint is not a one-time response,
    but a live stream of events over HTTP.
    */
    @GetMapping(value = "/test-delay", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Integer> getAllIntegersWithDealy() {
        // Flux is a publisher which streams the data in reactive programming way
        // return Flux.just(1, 2, 3, 4, 5);

        // stream the data with interval of 1 sec
        return Flux.just(1, 2, 3, 4, 5).delayElements(Duration.ofSeconds(1)).log();
    }

    @GetMapping(value = "/students", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Student> getAllStudents() {

        return Flux.just(new Student(1,"Prabhat", 36),
                            new Student(3,"Abhisek", 32),
                            new Student(7,"Kaushik", 18),
                            new Student(1,"kanha", 30)
                        ).delayElements(Duration.ofSeconds(1))
                        .log();
    }

    @GetMapping(value = "/students-db-call", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Student> getAllStudentsFromDB() {

        // this db call takes few time to fetch data. So, the request here is blocking operation
        // but here the call happens with help of r2dbc driver to achieve asynchronous communication
        // between database and netty server. So, in this time the call is a non-blocking call

        System.out.println("Thread which accepts the request : " + Thread.currentThread().getName());

        Flux<Student> studentFlux = studentService.getAllStudents().log();
        return studentFlux.delayElements(Duration.ofSeconds(1));
    }
    @GetMapping(value = "/students-db-side-delay-fetch-call", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Student> getAllStudentsFromDBDelay() {

        Flux<Student> studentFlux = studentService.getAllStudentsWithDelayedQuery();
        return studentFlux.delayElements(Duration.ofSeconds(1)).log();
    }

    /*

        [ctor-http-nio-3] reactor.Flux.ConcatMapNoPrefetch.1       : onSubscribe(FluxConcatMapNoPrefetch.FluxConcatMapNoPrefetchSubscriber)
        2026-08-23T12:10:45.995+05:30  INFO 2416 --- [spring-webflux-r2dbc] [ctor-http-nio-3] reactor.Flux.ConcatMapNoPrefetch.1       : request(1)
        2026-08-23T12:11:23.797+05:30  INFO 2416 --- [spring-webflux-r2dbc] [     parallel-2] reactor.Flux.ConcatMapNoPrefetch.1       : onNext(com.learn.webflux.r2dbc.model.Student@40783c13)
        2026-08-23T12:11:23.988+05:30  INFO 2416 --- [spring-webflux-r2dbc] [ctor-http-nio-3] reactor.Flux.ConcatMapNoPrefetch.1       : request(31)
        2026-08-23T12:11:24.821+05:30  INFO 2416 --- [spring-webflux-r2dbc] [     parallel-3] reactor.Flux.ConcatMapNoPrefetch.1       : onNext(com.learn.webflux.r2dbc.model.Student@723f3b50)
        2026-08-23T12:11:25.838+05:30  INFO 2416 --- [spring-webflux-r2dbc] [     parallel-4] reactor.Flux.ConcatMapNoPrefetch.1       : onNext(com.learn.webflux.r2dbc.model.Student@2da98f76)
        2026-08-23T12:11:26.851+05:30  INFO 2416 --- [spring-webflux-r2dbc] [     parallel-5] reactor.Flux.ConcatMapNoPrefetch.1       : onNext(com.learn.webflux.r2dbc.model.Student@174b896b)
        2026-08-23T12:11:27.864+05:30  INFO 2416 --- [spring-webflux-r2dbc] [     parallel-6] reactor.Flux.ConcatMapNoPrefetch.1       : onNext(com.learn.webflux.r2dbc.model.Student@45a7416e)
        2026-08-23T12:11:28.873+05:30  INFO 2416 --- [spring-webflux-r2dbc] [     parallel-7] reactor.Flux.ConcatMapNoPrefetch.1       : onNext(com.learn.webflux.r2dbc.model.Student@151ebc55)
        2026-08-23T12:11:28.876+05:30  INFO 2416 --- [spring-webflux-r2dbc] [     parallel-7] reactor.Flux.ConcatMapNoPrefetch.1       : onComplete()

    */

    /*
    Steps to convert a traditional endpoint to functional programming
    1. Write @GetMapping(value = "/students-db-side-delay-fetch-call" in Route
    2. Write this produces = MediaType.TEXT_EVENT_STREAM_VALUE value in return
       ServerResponse.
                    .ok()
                    .contentType(MediaType.TEXT_EVENT_STREAM)
                    .body(allStudents, Student.class);
    3. Method return type should be Mono<ServerResponse>
    4. Now map or route this functional programming of handler method when the request is coming
    5. Create RouterConfig class and create a router studentRouterConfig()
    6. whenever a specific request comes, redirect or route that request using abstract class RouterFunctions
       to handler method i.e. getAllStudentsFromDBByFunctionalApproach()
    */

    public Mono<ServerResponse> getAllStudentsFromDBByFunctionalApproach(ServerRequest serverRequest) {

        Flux<Student> allStudentFlux = studentService.getAllStudentsWithDelayedQuery().log();

        return ServerResponse
                .ok()
                .contentType(MediaType.TEXT_EVENT_STREAM)
                .body(allStudentFlux, Student.class);
    }

    public Mono<ServerResponse> getAllStudentsFromDBByFunctionalApproachById(ServerRequest serverRequest) {

        Flux<Student> fluxStudents = studentService.getAllStudentsWithDelayedQueryById().log();

        return ServerResponse
                .ok()
                .contentType(MediaType.TEXT_EVENT_STREAM)
                .body(fluxStudents, Student.class);
    }

    public Mono<ServerResponse> getAllStudentsWithDelayedQueryByName(ServerRequest serverRequest) {
        String studentName = serverRequest
                                        .queryParam("student_name")
                                        .orElseThrow(() -> new IllegalArgumentException("student_name is required"));
        Flux<Student> studentsFlux = studentService.getAllStudentsWithDelayedQueryByName(studentName);

        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(studentsFlux, Student.class);
    }


}
