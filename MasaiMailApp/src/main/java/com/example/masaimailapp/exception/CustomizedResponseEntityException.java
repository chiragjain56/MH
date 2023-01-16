package com.example.masaimailapp.exception;

import com.example.masaimailapp.dto.ExceptionResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import java.util.Date;

@ControllerAdvice
@RestController
public class CustomizedResponseEntityException extends ResponseEntityExceptionHandler {

    // Handles all exception
    @ExceptionHandler(Exception.class)
    public final ResponseEntity handleAllException(Exception e, WebRequest webRequest){
        ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(),
                e.getMessage(), webRequest.getDescription(false));
        return new ResponseEntity(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    // Handles if the user has numbers or special characters in his name
    @ExceptionHandler(IncorrectInputException.class)
    public final ResponseEntity handleIncorrectInputException(Exception e, WebRequest webRequest){
        ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(),
                e.getMessage(), webRequest.getDescription(false));
        return new ResponseEntity(exceptionResponse, HttpStatus.BAD_REQUEST);
    }

    // Handles if the mobile number is not 10 digits
    @ExceptionHandler(IncorrectMobileNumberFormat.class)
    public final ResponseEntity handleIncorrectMobileNumberFormat(Exception e, WebRequest webRequest){
        ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(),
                e.getMessage(), webRequest.getDescription(false));
        return new ResponseEntity(exceptionResponse, HttpStatus.BAD_REQUEST);
    }
}
