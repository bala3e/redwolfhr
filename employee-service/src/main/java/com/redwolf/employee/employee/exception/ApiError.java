package com.redwolf.employee.employee.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApiError {
    private Integer errorCode;
    private String errorDesc;
    private Date date;
}
