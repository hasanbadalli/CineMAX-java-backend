//package com.example.cinemax.config;
//
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.validation.FieldError;
//import org.springframework.web.bind.MethodArgumentNotValidException;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//import org.springframework.web.bind.annotation.RestControllerAdvice;
//
//import java.util.List;
//import java.util.stream.Collectors;
//
//@RestControllerAdvice
//public class GlobalExceptionHandler {
//
//    @ExceptionHandler(MethodArgumentNotValidException.class)
//    public ResponseEntity<Object> handleValidationExceptions(MethodArgumentNotValidException ex) {
//
//        List<String> errors = ex.getBindingResult().getAllErrors().stream()
//                .map(error -> {
//                    String fieldName = ((FieldError) error).getField();
//                    String errorMessage = error.getDefaultMessage();
//                    return fieldName + ": " + errorMessage;
//                })
//                .collect(Collectors.toList());
//
//        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
//    }
//}
