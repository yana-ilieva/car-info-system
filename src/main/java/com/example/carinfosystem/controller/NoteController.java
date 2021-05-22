package com.example.carinfosystem.controller;

import com.example.carinfosystem.model.Note;
import com.example.carinfosystem.repository.NoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/notes")
public class NoteController {

    @Autowired
    private NoteRepository noteRepository;

    @PostMapping
    public Note save(@RequestBody Note note){
        try{
            note.setId(null);
            note.setCreatedAt(LocalDateTime.now());
            return noteRepository.save(note);
        } catch (Exception e){
            throw new RuntimeException("Cannot save note.");
        }
    }
}
