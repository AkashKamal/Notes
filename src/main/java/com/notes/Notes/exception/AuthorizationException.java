package com.notes.Notes.exception;

public class AuthorizationException extends APIException {

    public AuthorizationException(ErrorCode errorCode)
    {
      super((errorCode));
    }

}
