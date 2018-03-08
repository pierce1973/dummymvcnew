package com.example.restmvc.restmvcdemo.errorhandling;

import java.util.List;

public class RestMvcException extends Exception {

    public List<RestError> getRestErrorList() {
        return restErrorList;
    }

    private List<RestError> restErrorList;

    public RestMvcException(Throwable cause, List<RestError> errorList) {
        super(cause);
        this.restErrorList = errorList;
    }

    public RestMvcException(List<RestError> errorList) {
        this.restErrorList = errorList;
    }
}
