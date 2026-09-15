package com.learn.webflux.r2dbc;

import com.learn.webflux.r2dbc.repository.StudentReactiveRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringWebfluxR2dbcApplication {

    @Autowired
    StudentReactiveRepository studentRepository;

    public static void main(String[] args) {
        SpringApplication.run(SpringWebfluxR2dbcApplication.class, args);
    }

    // use implements CommandLineRunner fo test
    /*@Override
    public void run(String... args) throws Exception {
            Flux<Student> studentFlux = studentRepository.findAll();
            studentFlux.subscribe(System.out::println);
    }*/
}
