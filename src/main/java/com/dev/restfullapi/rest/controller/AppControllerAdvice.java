package com.dev.restfullapi.rest.controller;

import com.dev.restfullapi.exception.InvalidPasswordException;
import com.dev.restfullapi.exception.NotFoundException;
import com.dev.restfullapi.exception.handle.HandleException;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.stream.Collectors;

@RestControllerAdvice
public class AppControllerAdvice {

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public HandleException handleNotFoundException(NotFoundException exception) {
        return new HandleException(exception.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public HandleException handleMethodArgumentNotValidException(MethodArgumentNotValidException exception) {
        final var errors = exception
                .getBindingResult()
                .getAllErrors()
                .stream()
                .map(error -> error.getDefaultMessage())
                .collect(Collectors.toList());
        return new HandleException(errors);
    }

    @ExceptionHandler(InvalidPasswordException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public HandleException handleInvalidPasswordException(InvalidPasswordException exception) {
        return new HandleException(exception.getMessage());
    }

    @ExceptionHandler(UsernameNotFoundException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public HandleException handleUsernameNotFoundException(UsernameNotFoundException exception) {
        return new HandleException(exception.getMessage());
    }
}
