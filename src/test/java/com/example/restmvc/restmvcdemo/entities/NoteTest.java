package com.example.restmvc.restmvcdemo.entities;

import com.example.restmvc.restmvcdemo.model.Note;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.*;
import java.nio.charset.Charset;
import java.sql.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;

public class NoteTest {

    @Rule
    public TemporaryFolder folder = new TemporaryFolder();

    List<Note> notes = new ArrayList<>();

    @Before
    public void setup() {
        Note n1 = new Note();
        n1.setContent("hh");
        n1.setTitle("Ti");

        Note n2 = new Note();
        n2.setContent("hh");
        n2.setTitle("Bb");
        notes.add(n1);
        notes.add(n2);
    }

    @Test
    public void whenTwoNoteInstancesComparedWithLambdaComparatorThenReturnBoolean() {

        notes.stream().forEach(n -> System.out.print(n.getTitle() + " "));
//        notes.sort((Note n1, Note n2) -> n1.getTitle().compareTo(n2.getTitle()));
        notes.sort(NoteTest::noteCompare);
        System.out.println("");
        notes.stream().forEach(n -> System.out.print(n.getTitle() + " "));

    }

    public static int noteCompare(Note n1, Note n2) {
        if (n1 != null && n2 != null) {
            return n1.getTitle().compareTo(n2.getTitle());
        } else {
            return 0;
        }
    }

}
