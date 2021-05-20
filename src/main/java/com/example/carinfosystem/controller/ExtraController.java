package com.example.carinfosystem.controller;

import com.example.carinfosystem.model.Extra;
import com.example.carinfosystem.repository.ExtraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/extras")
public class ExtraController {

    @Autowired
    private ExtraRepository extraRepository;

    @GetMapping
    public List<Extra> getAll(){
        return extraRepository.findAll();
    }

    @GetMapping("/{id}")
    public Extra findById(@PathVariable Long id){
        return extraRepository.findById(id).orElse(null);
    }

    @PostMapping
    public Extra save(@RequestBody Extra extra){
        try{
            extra.setId(null);
            return extraRepository.save(extra);
        } catch (Exception e){
            return null;
        }
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        extraRepository.deleteById(id);
    }
}
