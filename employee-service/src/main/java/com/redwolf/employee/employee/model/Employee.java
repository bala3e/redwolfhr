package com.redwolf.employee.employee.model;

import lombok.Builder;
import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Builder
public class Employee {
    private String name;
    private int age;
}
