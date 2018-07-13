package com.sabrehrtrial.kata19.service;

/**
 * a structure for service error output
 */
public class ErrorStruct {

    private String code;
    private String message;
    
    public ErrorStruct(String code, String message) {
        this.code = code;
        this.message = message;
    }
    
    public String getCode() {
        return code;
    }
    
    public String getMessage() {
        return message;
    }
    
}
