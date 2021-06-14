package com.notes.Notes.exception;


public class APIException extends Exception {

    int code;

    public APIException(ErrorCode errorCode)
    {
        super(errorCode.getMessage());
        this.code = errorCode.getCode();
    }

    public int getCode() {
        return code;
    }
}
