package com.geekbrains.ru.gb_rest_angular.core.exception;

import com.geekbrains.ru.gb_rest_angular.api.AppError;
import com.geekbrains.ru.gb_rest_angular.api.ResourceNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<AppError> catchResourceNotFoundException(ResourceNotFoundException e){
        log.error(e.getMessage(),e);
        return new ResponseEntity<>(new AppError(HttpStatus.NOT_FOUND.value(),e.getMessage()),HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<FieldsValidationError> catchValidationException(ValidationException e){
        log.error(e.getMessage(),e);
        return new ResponseEntity<>(new FieldsValidationError(e.getErrorFieldsMessages()),HttpStatus.BAD_REQUEST);
    }
}
