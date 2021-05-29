package com.example.carinfosystem.controller;

import com.example.carinfosystem.model.City;
import com.example.carinfosystem.repository.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cities")
@CrossOrigin(origins = "http://localhost:8080")
public class CityController {

    @Autowired
    private CityRepository cityRepository;

    @GetMapping
    public List<City> getAll(){
        return cityRepository.findAll();
    }

    @GetMapping("/{id}")
    public City findById(@PathVariable Long id){
        return cityRepository.findById(id).orElse(null);
    }

    @PostMapping
    public City save(@RequestBody City city){
        try{
           city.setId(null);
           return cityRepository.save(city);
        } catch (Exception e){
            return null;
        }
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        cityRepository.deleteById(id);
    }
}
