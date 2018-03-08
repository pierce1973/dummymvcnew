package com.example.restmvc.restmvcdemo.service;

import com.example.restmvc.restmvcdemo.model.Note;

import java.util.List;

public interface SearchService {

    List<Note> search(String searchType, List<String> keywords);
}
