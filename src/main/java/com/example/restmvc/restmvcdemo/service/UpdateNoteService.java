package com.example.restmvc.restmvcdemo.service;

import com.example.restmvc.restmvcdemo.errorhandling.RestMvcException;

public interface UpdateNoteService {

    void processNote(long id) throws RestMvcException;
}
