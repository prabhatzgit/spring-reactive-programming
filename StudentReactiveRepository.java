package com.learn.webflux.r2dbc.repository;

import com.learn.webflux.r2dbc.model.Student;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;

// The purpose of using ReactiveCrudRepository is to create async channel between netty server and database
public interface StudentReactiveRepository extends ReactiveCrudRepository<Student, Integer> {

    // The below qeury sleeps 1 sec to fetch each row
    // @Query("SELECT pg_sleep(1), s.student_id, s.student_name, s.student_age FROM sample_db.student s") // 6s to fetch
    @Query("SELECT pg_sleep(6), s.student_id, s.student_name, s.student_age FROM student s;")
    // when we invove this method, whatever method used, we shouldn't wait this method to fetch and gives the
    // response

    // the operation can do other actions as well
    public Flux<Student>  getAllStudentsWithDelayedQuery();

    @Query("SELECT pg_sleep(1), s.student_id, s.student_name, s.student_age FROM student s where s.student_name = 'Alice Johnson';")
    public Flux<Student> getAllStudentsWithDelayedQueryById();

    @Query("SELECT pg_sleep(1), s.student_id, s.student_name, s.student_age FROM student s WHERE s.student_name = :student_name")
    public Flux<Student> getAllStudentsWithDelayedQueryByName(@Param("student_name") String studentName);

    /*@Query("SELECT pg_sleep(1), s.student_id, s.student_name, s.student_age FROM student s WHERE s.student_name = :student_name")
    Flux<Student> getAllStudentsWithDelayedQueryByName(@Param("student_name") String studentName);*/

}
