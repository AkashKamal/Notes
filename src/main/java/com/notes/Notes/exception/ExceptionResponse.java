package com.notes.Notes.exception;

import org.json.JSONObject;

import java.util.Date;

public class ExceptionResponse {
    private Date timestamp;
    private String message;
    private int code;

    public ExceptionResponse(Date timestamp, String message,int code) {
        this.timestamp = timestamp;
        this.message = message;
        this.code = code;
    }

    @Override
    public String toString(){
        JSONObject errorJSON = new JSONObject();
        errorJSON.put("timestamp",timestamp);
        errorJSON.put("message",message);
        errorJSON.put("code",code);
        return errorJSON.toString();
    }

}
