package com.example.pstravel.ErrorHandler;

import lombok.Getter;

@Getter
public class MessageDelException extends RuntimeException{

    private ErrorCode errorCode;

    public MessageDelException(String message, ErrorCode errorCode){
        super(message);
        this.errorCode = errorCode;
    }
}
