package com.learn.webflux.r2dbc.service;

import com.learn.webflux.r2dbc.model.Student;
import com.learn.webflux.r2dbc.repository.StudentReactiveRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
public class StudentService {


    private final StudentReactiveRepository studentReactiveRepository;

    public StudentService(StudentReactiveRepository studentReactiveRepository){
        this.studentReactiveRepository = studentReactiveRepository;
    }
    public Flux<Student> getAllStudents(){
        return studentReactiveRepository.findAll();
    }

    public Flux<Student> getAllStudentsWithDelayedQuery(){
        return studentReactiveRepository.getAllStudentsWithDelayedQuery();
    }

    public Flux<Student> getAllStudentsWithDelayedQueryById(){
        return studentReactiveRepository.getAllStudentsWithDelayedQueryById();
    }

    public Flux<Student> getAllStudentsWithDelayedQueryByName(String studentName){
        return studentReactiveRepository.getAllStudentsWithDelayedQueryByName(studentName);
    }
}
