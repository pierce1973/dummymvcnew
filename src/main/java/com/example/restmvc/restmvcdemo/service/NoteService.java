package com.example.restmvc.restmvcdemo.service;

import com.example.restmvc.restmvcdemo.model.Note;

import java.util.List;

public interface NoteService {

    List<Note> getAllNotes();

    List<Note> getNotesByTitle(String title);

    List<Note> getCombinedIdAndTitleNotes(Long id, String title);
}
