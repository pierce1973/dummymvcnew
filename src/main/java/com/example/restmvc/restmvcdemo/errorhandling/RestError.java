package com.example.restmvc.restmvcdemo.errorhandling;

import java.util.List;

public class RestError {

    public String getCode() {
        return code;
    }

    public String getField() {
        return field;
    }

    public String getMesssage() {
        return messsage;
    }

    public String getCause() {
        return cause;
    }

    private String code;
    private String field;
    private String messsage;
    private String cause;

    public RestError(String code, String field, String messsage) {
        this(code, field, messsage, "");
    }

    public RestError(String code, String field, String messsage, String cause) {
        this.code = code;
        this.field = field;
        this.messsage = messsage;
        this.cause = cause;
    }
}
