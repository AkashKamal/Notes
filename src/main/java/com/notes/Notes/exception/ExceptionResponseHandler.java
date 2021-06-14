package com.notes.Notes.exception;

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
public class ExceptionResponseHandler extends ResponseEntityExceptionHandler {


    @ExceptionHandler({APIException.class,AuthorizationException.class})
    public final ResponseEntity<String> handleBadRequestException(APIException ex, WebRequest req) {

            ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), ex.getMessage(), ex.getCode());
            return new ResponseEntity<>(exceptionResponse.toString(), HttpStatus.BAD_REQUEST);
    }
}
