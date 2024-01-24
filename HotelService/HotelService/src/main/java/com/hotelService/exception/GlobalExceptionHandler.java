package com.hotelService.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Map<String,Object>> handlerResourceNotFoundException(ResourceNotFoundException ex){
        Map <String,Object>map=new HashMap<>();
        map.put("message",ex.getMessage());
        map.put("success",false);
        map.put("status",HttpStatus.NOT_FOUND);
        String message=ex.getMessage();
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(map);
    }
}
