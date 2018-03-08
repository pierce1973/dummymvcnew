package com.example.restmvc.restmvcdemo.repository;

import com.example.restmvc.restmvcdemo.model.Note;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NoteRepository extends JpaRepository<Note, Long> {

    @Query("select n from Note n where ?1 like %?2%")
    List<Note> findAllOccurancesInField(String field, String criteria);

    @Query("select n from Note n where n.title like %?1")
    List<Note> findAllOccurancesInTitle(String criteria);

    List<Note> findByTitle(String title);

    List<Note> findByIdGreaterThan(long value);

    List<Note> findByTitleLike(String keyword);
}
