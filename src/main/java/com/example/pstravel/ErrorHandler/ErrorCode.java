package com.example.pstravel.ErrorHandler;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor @Getter @NoArgsConstructor
public enum ErrorCode {

    NOT_FOUND(404,"COMMON-ERR-404","PAGE NOT FOUND"),
    INTER_SERVER_ERROR(500,"COMMON-ERR-500","INTER SERVER ERROR"),
    NOT_EXIST_MESSAGE(400,"MESSAGE-ERR-400","MESSAGE IS NOT EXIST"),
    ;

    private int status;
    private String errorCode;
    private String message;
}
