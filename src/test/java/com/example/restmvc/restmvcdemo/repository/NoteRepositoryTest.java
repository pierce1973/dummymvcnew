package com.example.restmvc.restmvcdemo.repository;

import com.example.restmvc.restmvcdemo.model.Note;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@DataJpaTest
public class NoteRepositoryTest {

    @Autowired
    private NoteRepository noteRepository;

    @Autowired
    private TestEntityManager testEntityManager;

    @Test
    public void whenTitlePassedThenReturnNoteRecord() {

        Note note = new Note();
        note.setTitle("MyTitle");
        note.setContent("MyContent");

        testEntityManager.persist(note);
        testEntityManager.flush();

        List<Note> foundNote = noteRepository.findByTitle("MyTitle");

        assertEquals(note, foundNote.get(0));

    }

    @Test
    public void whenTitleAnKeywordPassedThenReturnRecords(){

        Note note = new Note();
        note.setContent("MyContent");
        note.setTitle("MyTitle");

        testEntityManager.persist(note);
        testEntityManager.flush();

        List<Note> notes = noteRepository.findAllOccurancesInTitle("MyTitle");

        assertEquals(note.getTitle(), notes.get(0).getTitle());
    }
}