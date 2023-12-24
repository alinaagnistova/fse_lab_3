package ru.cosmosway.web04.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
class SessionUserNotFoundAdvice {
    @ResponseBody
    @ExceptionHandler(SessionUserNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String attemptNotFoundHandler(SessionUserNotFoundException ex) {
        return ex.getMessage();
    }
}
