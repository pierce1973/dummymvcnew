package com.example.restmvc.restmvcdemo.errorhandling;

import org.springframework.context.annotation.Bean;

import java.util.List;

public class RestErrorMessage {

    private final String code;
    private final String uri;

    private final List<RestError> errorList;

    private RestErrorMessage(RestErrorMessageBuilder builder) {
        this.code = builder.code;
        this.uri = builder.uri;
        this.errorList = builder.errorList;
    }

    public String getCode() {
        return code;
    }

    public String getUri() {
        return uri;
    }

    public List<RestError> getErrorList() {
        return errorList;
    }

    public static class RestErrorMessageBuilder{

        private final String code;
        private final String uri;
        private List<RestError> errorList;

        public RestErrorMessageBuilder(String code, String uri) {
            this.code = code;
            this.uri = uri;
        }

        public RestErrorMessageBuilder errorList(List<RestError> errorList){
            this.errorList = errorList;
            return this;
        }

        public RestErrorMessage build(){
            return new RestErrorMessage(this);
        }
    }
}
