package com.example.restmvc.restmvcdemo.controller;

import com.example.restmvc.restmvcdemo.common.RestErrorUtility;
import com.example.restmvc.restmvcdemo.errorhandling.RestError;
import com.example.restmvc.restmvcdemo.errorhandling.RestErrorMessage;
import com.example.restmvc.restmvcdemo.errorhandling.RestMvcException;
import com.example.restmvc.restmvcdemo.model.Note;
import com.example.restmvc.restmvcdemo.repository.NoteRepository;
import com.example.restmvc.restmvcdemo.service.NoteService;
import com.example.restmvc.restmvcdemo.service.SearchService;
import com.example.restmvc.restmvcdemo.service.UpdateNoteService;
import org.aspectj.weaver.ast.Not;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/api/notes")
public class NoteController {

    @Autowired
    NoteRepository noteRepository;

    @Autowired
    NoteService noteService;

    @Autowired
    UpdateNoteService updateNoteService;

    @Autowired
    private SearchService searchService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ResponseEntity getAllNotes() {
        List<Note> notes = noteRepository.findAll();
        if (notes == null || notes.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(notes);
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ResponseEntity createNote(@Valid @RequestBody Note note) {

        System.out.println("Note:Date: " + note.getCreatedAt().toString());

        try {
            Note response = noteRepository.save(note);
        } catch (Exception e) {
            return ResponseEntity.unprocessableEntity().body(note);
        }
        return ResponseEntity.accepted().body(note);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity getSingleNote(@PathVariable(name = "id") long myId)
            throws RestMvcException {
        Note note = noteRepository.findOne(myId);
        if (note == null) {
            throw new RestMvcException(RestErrorUtility.createRestErrorList("0003",
                    "id", "unable to find the note :" + myId));
        }

        return ResponseEntity.ok().body(note);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity updateNote(@Valid @RequestBody Note noteDetails,
                                     @PathVariable(value = "id") Long id) {
        Note note = noteRepository.findOne(id);
        if (note == null) {
            return ResponseEntity.notFound().build();
        }
        Note updatedNote;

        note.setContent(noteDetails.getContent());
        note.setCreatedAt(noteDetails.getCreatedAt());
        note.setTitle(noteDetails.getTitle());
        updatedNote = noteRepository.save(note);

        return ResponseEntity.accepted().body(updatedNote);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity deleteNote(@PathVariable(value = "id") Long id) {

        Note note = noteRepository.findOne(id);
        if (note == null) {
            return ResponseEntity.notFound().build();
        }
        noteRepository.delete(id);
        return ResponseEntity.ok().build();
    }

    @RequestMapping(value = "/update/{id}", method = RequestMethod.PUT)
    public ResponseEntity updateNoteDate(@PathVariable(value = "id") Long id, Model model)
            throws RestMvcException {

        updateNoteService.processNote(id);
        List<Note> notes = noteService.getAllNotes();
        return ResponseEntity.ok(notes);
    }

    @RequestMapping(value = "/search/{type}", method = RequestMethod.GET)
    public ResponseEntity searchUtil(@PathVariable String type,
                                     @MatrixVariable List<String> keywords){

        List<Note> notes = searchService.search(type, keywords);
        return ResponseEntity.ok().body(notes);

    }

    @RequestMapping(value = "/search/title/{name}", method = RequestMethod.GET)
    public ResponseEntity findByTitle(@PathVariable String title){
        List<Note> list = noteService.getNotesByTitle(title);
        return ResponseEntity.ok().body(list);
    }

    @RequestMapping("/search/mixed/{title}/{id}")
    public ResponseEntity getFromTitlewAndId(@PathVariable(value = "title") String title,
                                             @PathVariable("id") Long id){
        List<Note> notes = noteService.getCombinedIdAndTitleNotes(id, title);
        return ResponseEntity.ok().body(notes);
    }

    /*
    This is a way to send back a JSON eror message as what would be expected for a REST API
    It allows a standard error message to be returned!
     */
    @ExceptionHandler(RestMvcException.class)
    public ResponseEntity exceptionHandlerForNote(HttpServletRequest req, RestMvcException e) {

        String code = "1001";
        String uri = req.getRequestURI();
        RestErrorMessage restErrorMessage = new RestErrorMessage.
                RestErrorMessageBuilder(code, uri).errorList(e.getRestErrorList()).
                build();

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(restErrorMessage);
    }

}
