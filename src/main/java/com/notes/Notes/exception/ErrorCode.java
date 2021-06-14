package com.notes.Notes.exception;

public enum ErrorCode {

//   Error code related to tokens
    TOKEN_EXPIRED(414,"Token has been expired"),
    INVALID_TOKEN(415,"Token is invalid"),

//  Not found error code
    NOTE_NOT_FOUND(416,"Notes is not present"),
    LABEL_NOT_FOUND(417,"Label is not present"),

//    Access denied error codes
    ACCESS_DENIED(403,"Access Denied"),

//    Authorization Exception
    INVALID_CREDENTIALS(403,"Email or Password is wrong"),
    USER_EXISTS(403,"This Email ID is already registered");



    private final int code;
    private final String message;


    private ErrorCode(int code, String message)
    {
        this.code = code;
        this.message = message;
    }

    public String getMessage()
    {
        return this.message;
    }

    public int getCode(){
        return this.code;
    }
}
