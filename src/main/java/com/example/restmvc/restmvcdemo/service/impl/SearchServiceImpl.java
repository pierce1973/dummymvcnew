package com.example.restmvc.restmvcdemo.service.impl;

import com.example.restmvc.restmvcdemo.model.Note;
import com.example.restmvc.restmvcdemo.repository.NoteRepository;
import com.example.restmvc.restmvcdemo.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SearchServiceImpl implements SearchService {

    private NoteRepository noteRepository;

    @Autowired
    public SearchServiceImpl(NoteRepository noteRepository) {
        this.noteRepository = noteRepository;
    }

    @Override
    public List<Note> search(final String searchType, final List<String> keywords) {

        //List<String> criterias = keywords.stream().map(key -> "%" + key + "%").collect(Collectors.toList());
        System.out.println("searching for " + searchType + " " + keywords.get(0));
        List<Note> notes = keywords.stream().collect(() -> new ArrayList<Note>(), (list, string) ->
                        noteRepository.findAllOccurancesInField(searchType, string),
                (s, s1) -> s.addAll(s1));

        return notes;
    }
}
