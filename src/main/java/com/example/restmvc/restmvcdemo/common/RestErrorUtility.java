package com.example.restmvc.restmvcdemo.common;

import com.example.restmvc.restmvcdemo.errorhandling.RestError;

import java.util.ArrayList;
import java.util.List;

public class RestErrorUtility {

    public static List<RestError> createRestErrorList(String code, String field, String message){
        RestError restError = new RestError(code, field, message);
        List<RestError> list = new ArrayList<>();
        list.add(restError);
        return list;
    }
}
