package com.example.carinfosystem.controller;

import com.example.carinfosystem.model.Brand;
import com.example.carinfosystem.repository.BrandRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/brands")
public class BrandController {
    @Autowired
    private BrandRepository brandRepository;

    @GetMapping
    public List<Brand> getAll(){
        return brandRepository.findAll();
    }

    @GetMapping("/{id}")
    public Brand findById(@PathVariable Long id){
        return brandRepository.findById(id).orElse(null);
    }

    @PostMapping
    public Brand save(@RequestBody Brand brand){
        try{
            brand.setId(null);
            return brandRepository.save(brand);
        } catch (Exception e){
            return null;
        }
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        brandRepository.deleteById(id);
    }
}
