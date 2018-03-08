package com.example.restmvc.restmvcdemo.service.impl;

import com.example.restmvc.restmvcdemo.model.Note;
import com.example.restmvc.restmvcdemo.repository.NoteRepository;
import com.example.restmvc.restmvcdemo.service.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class NoteServiceImpl implements NoteService{

    @Autowired
    private NoteRepository noteRepository;

    @Override
    public List<Note> getAllNotes() {

        List<Note> notes = noteRepository.findAll();
        return notes;
    }

    @Override
    public List<Note> getNotesByTitle(String title) {
        List<Note> notes = noteRepository.findByTitle(title);
        return notes;
    }

    @Override
    @Transactional
    public List<Note> getCombinedIdAndTitleNotes(Long id, String title) {

        List<Note> listFromId = noteRepository.findByIdGreaterThan(id);
        List<Note> listFromTitle = noteRepository.findByTitleLike(title);
        listFromId.addAll(listFromTitle);

        return listFromId;

    }


}
