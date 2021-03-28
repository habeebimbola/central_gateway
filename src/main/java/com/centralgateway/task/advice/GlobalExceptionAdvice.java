package com.centralgateway.task.advice;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;

@ControllerAdvice
public class GlobalExceptionAdvice {

    @RequestMapping("/welcome")
    @ExceptionHandler()
    public String handleRuntimeException(RuntimeException runtimeException){
        return "runtimeExceptionForm";
    }
}
