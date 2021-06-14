package com.notes.Notes.exception;


import org.springframework.http.HttpStatus;

public class APIException extends Exception {

    int code;
    HttpStatus httpStatus;
    public APIException(ErrorCode errorCode, HttpStatus httpStatus)
    {
        super(errorCode.getMessage());
        this.code = errorCode.getCode();
        this.httpStatus = httpStatus;
    }

    public int getCode() {
        return code;
    }
}
