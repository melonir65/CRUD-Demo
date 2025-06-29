package com.rafaelmeloni.crud_demo.controllers.handlers;

import com.rafaelmeloni.crud_demo.dto.CustomError;
import com.rafaelmeloni.crud_demo.dto.ValidationError;
import com.rafaelmeloni.crud_demo.services.exceptions.DatabaseException;
import com.rafaelmeloni.crud_demo.services.exceptions.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.Instant;

@ControllerAdvice
public class ControllerExceptionHandler {


    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<CustomError>resourceNotFound(ResourceNotFoundException e, HttpServletRequest request){
        HttpStatus status = HttpStatus.NOT_FOUND;
        CustomError customError = new CustomError(Instant.now(),status.value(),e.getMessage(),request.getRequestURI());
        return ResponseEntity.status(status).body(customError);
    }

    @ExceptionHandler(DatabaseException.class)
    public ResponseEntity<CustomError>database(DatabaseException e, HttpServletRequest request){
        HttpStatus status = HttpStatus.BAD_REQUEST;
        CustomError customError = new CustomError(Instant.now(),status.value(),e.getMessage(),request.getRequestURI());
        return ResponseEntity.status(status).body(customError);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<CustomError>methodArgumentValidation(MethodArgumentNotValidException e, HttpServletRequest request){
        HttpStatus status = HttpStatus.UNPROCESSABLE_ENTITY;
        ValidationError validationError = new ValidationError(Instant.now(),status.value(),"Invalid Data",request.getRequestURI());
        for(FieldError fieldError : e.getBindingResult().getFieldErrors()){
           validationError.addError(fieldError.getField(),fieldError.getDefaultMessage());

        }
        return ResponseEntity.status(status).body(validationError);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<CustomError>database(EntityNotFoundException e, HttpServletRequest request){
        HttpStatus status = HttpStatus.BAD_REQUEST;
        CustomError customError = new CustomError(Instant.now(),status.value(),"Invalid id",request.getRequestURI());
        return ResponseEntity.status(status).body(customError);
    }




}
