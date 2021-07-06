package com.notes.Notes.exception;

import org.springframework.http.HttpStatus;

public enum ErrorCode {

//   Error code related to tokens
    TOKEN_EXPIRED(414,"Token has been expired",HttpStatus.FORBIDDEN),
    INVALID_TOKEN(415,"Token is invalid",HttpStatus.PERMANENT_REDIRECT),

//  Not found error code
    NOTE_NOT_FOUND(416,"Notes is not present",HttpStatus.NOT_FOUND),
    LABEL_NOT_FOUND(417,"Label is not present",HttpStatus.NOT_FOUND),

//    Access denied error codes
    ACCESS_DENIED(403,"Access Denied",HttpStatus.FORBIDDEN),

//    Authorization Exception
    INVALID_CREDENTIALS(403,"Email or Password is wrong",HttpStatus.FORBIDDEN),
    USER_EXISTS(403,"This Email ID is already registered",HttpStatus.BAD_REQUEST);



    private final int errorCode;
    private final String message;
    private HttpStatus httpStatus;


    private ErrorCode(int errorCode, String message, HttpStatus httpStatus)
    {
        this.errorCode = errorCode;
        this.message = message;
        this.httpStatus = httpStatus;
    }

    public String getMessage()
    {
        return this.message;
    }

    public int getCode(){
        return this.errorCode;
    }

    public HttpStatus getHttpStatus () { return this.httpStatus;}
}
