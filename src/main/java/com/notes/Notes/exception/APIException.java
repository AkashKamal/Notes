package com.notes.Notes.exception;


import org.springframework.http.HttpStatus;

public class APIException extends Exception {

    int code;
    HttpStatus httpStatus;
    public APIException(ErrorCode errorCode)
    {
        super(errorCode.getMessage());
        this.code = errorCode.getCode();
        this.httpStatus = errorCode.getHttpStatus();
    }

    public int getCode() {
        return code;
    }

    public HttpStatus getHttpStatus(){
        return this.httpStatus;
    }
}
