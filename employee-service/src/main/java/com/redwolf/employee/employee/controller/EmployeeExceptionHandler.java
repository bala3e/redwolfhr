package com.redwolf.employee.employee.controller;

import com.redwolf.employee.employee.exception.ApiError;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Date;

@RestController
@RestControllerAdvice
public class EmployeeExceptionHandler {
@ExceptionHandler(value = EntityNotFoundException.class)
    public ResponseEntity<ApiError> handeNoRecordException(){

    ApiError apiError = new ApiError(400,"No Emp Found ",new Date());
        return  new ResponseEntity<>(apiError,HttpStatus.BAD_REQUEST);
    }
}
