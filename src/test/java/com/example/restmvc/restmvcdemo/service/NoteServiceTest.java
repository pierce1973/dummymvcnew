package com.example.restmvc.restmvcdemo.service;

import com.example.restmvc.restmvcdemo.model.Note;
import com.example.restmvc.restmvcdemo.repository.NoteRepository;
import com.example.restmvc.restmvcdemo.service.impl.NoteServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class NoteServiceTest {

    /**
     * Define a test Note Service bean which is injected below
     */
    @TestConfiguration
    static class NoteServiceImplTestConfiguration {
        @Bean
        public NoteService noteService() {
            return new NoteServiceImpl();
        }
    }

    @Autowired
    private NoteService noteService;

    @MockBean
    private NoteRepository noteRepository;

    private List<Note> notes;

    @Before
    public void setup() {
        notes = new ArrayList<>();
        Note note = new Note();
        note.setTitle("MyTitle");
        note.setContent("MyContent");
        notes.add(note);

        when(noteRepository.findByTitle("MyTitle")).thenReturn(notes);
        when(noteRepository.findAll()).thenReturn(notes);
    }


    @Test
    public void whenTitlePassedThenReturnNoteList() {
        List<Note> foundNotes = noteService.getNotesByTitle("MyTitle");
        assertThat(foundNotes.get(0).getTitle()).isEqualTo("MyTitle");

    }

    @Test
    public void whenGetAllNotesThenListReturned() {
        List<Note> foundNotes = noteService.getAllNotes();
        assertThat(foundNotes).isEqualTo(notes);
    }
}
