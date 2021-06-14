package com.notes.Notes.exception;

import org.springframework.http.HttpStatus;

public class AuthorizationException extends APIException {

    public AuthorizationException(ErrorCode errorCode, HttpStatus httpStatus)
    {
      super(errorCode,httpStatus);
    }

}
