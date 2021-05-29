package com.example.carinfosystem.controller;

import com.example.carinfosystem.model.Note;
import com.example.carinfosystem.repository.NoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/notes")
@CrossOrigin(origins = "http://localhost:8080")
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
