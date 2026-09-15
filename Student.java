package com.learn.webflux.r2dbc.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@AllArgsConstructor
@Getter
@Setter
@Table("student")
public class Student {
    @Id
    private Integer studentId;
    private String studentName;
    private Integer studentAge;
}
