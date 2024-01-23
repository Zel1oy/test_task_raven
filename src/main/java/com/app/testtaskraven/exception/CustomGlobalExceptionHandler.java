package com.app.testtaskraven.exception;

import com.app.testtaskraven.dto.ArgumentNotValidResponseDto;
import jakarta.persistence.EntityNotFoundException;
import java.time.LocalDateTime;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class CustomGlobalExceptionHandler extends ResponseEntityExceptionHandler {
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex,
            HttpHeaders headers,
            HttpStatusCode status,
            WebRequest request
    ) {
        String[] errors = ex.getBindingResult().getAllErrors()
                .stream()
                .map(this::getErrorMessage)
                .toArray(String[]::new);
        return createResponseEntityFromExceptionErrors(errors, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    protected ResponseEntity<Object> handleEntityNotFoundException(
            EntityNotFoundException e
    ) {
        return createResponseEntityFromExceptionErrors(
                new String[]{e.getMessage()}, HttpStatus.NOT_FOUND);
    }

    private ResponseEntity<Object> createResponseEntityFromExceptionErrors(
            String[] errors, HttpStatus status) {
        ArgumentNotValidResponseDto notValidResponse = new ArgumentNotValidResponseDto();
        notValidResponse.setTimestamp(LocalDateTime.now());
        notValidResponse.setStatus(status);
        notValidResponse.setErrors(errors);
        return new ResponseEntity<>(notValidResponse, HttpStatusCode.valueOf(status.value()));
    }

    private String getErrorMessage(ObjectError e) {
        if (e instanceof FieldError) {
            String field = ((FieldError) e).getField();
            String message = e.getDefaultMessage();
            return field + " " + message;
        }
        return e.getDefaultMessage();
    }
}
