package com.egen.application.billingpaymentservice.handlers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@Slf4j
public class GlobalErrorHandler extends ResponseEntityExceptionHandler {

    private ApiError parseError(Exception ex, WebRequest request) {
        ApiError error = new ApiError();
        error.setDetail(ex.getMessage());
        error.setSource(request.getDescription(false).split("=")[1]);
        return error;
    }

    @ExceptionHandler(Exception.class)
    protected ResponseEntity<ApiError> handleGlobalException(Exception ex, WebRequest request) {
        ApiError error = parseError(ex, request);
        error.setTitle("Server Error");
        log.error(ex.getClass().getSimpleName(), ex.getMessage());
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    protected ResponseEntity<ApiError> handleUnprocessableEntity(IllegalArgumentException ex, WebRequest request) {
        ApiError error = parseError(ex, request);
        error.setTitle("Unable to process entity");
        log.error(ex.getClass().getSimpleName(), ex.getMessage());
        return new ResponseEntity<>(error, HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        ApiError error = parseError(ex, request);
        error.setTitle("Bad request");
        error.setDetail("The request is invalid. Please refer to the documentation for details on how to construct a valid request for " + error.getSource());
        log.error(ex.getClass().getSimpleName(), ex.getMessage());

        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NoSuchMethodException.class)
    protected ResponseEntity<Object> handleNotFoundException(NoSuchMethodException ex, WebRequest request) {
        ApiError error = parseError(ex, request);
        error.setTitle("Not Found");
        error.setDetail("The request can not be processd. Please provide correct details");
        log.error(ex.getClass().getSimpleName(), ex.getMessage());
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }
}
