package com.example.restmvc.restmvcdemo.service.impl;

import com.example.restmvc.restmvcdemo.errorhandling.RestError;
import com.example.restmvc.restmvcdemo.errorhandling.RestMvcException;
import com.example.restmvc.restmvcdemo.model.Note;
import com.example.restmvc.restmvcdemo.repository.NoteRepository;
import com.example.restmvc.restmvcdemo.service.UpdateNoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class UpdateNoteServiceImpl implements UpdateNoteService {

    @Autowired
    private NoteRepository noteRepository;

    @Override
    @Transactional
    public void processNote(long id) throws RestMvcException {

        Note note = noteRepository.findOne(id);
        if(note == null){
            RestError restError = new RestError("0001", "id",
                    "Note with id: " + id + "was not found");
            List<RestError> list = new ArrayList<>();
            list.add(restError);
            throw new RestMvcException(list);
        }

        // Create a new comment and date
        note.setContent(note.getContent() + " +");
        note.setCreatedAt(new Date());

        try {
            Note updatedNote = noteRepository.save(note);
        } catch (Exception e) {
            RestError restError = new RestError("0002", "id",
                    "Note: " + id + "could not be updted", e.getMessage());
            List<RestError> list = new ArrayList<>();
            list.add(restError);
            throw new RestMvcException(e, list);
        }
    }
}
