package com.example.carinfosystem.controller;

import com.example.carinfosystem.model.Brand;
import com.example.carinfosystem.model.Model;
import com.example.carinfosystem.repository.BrandRepository;
import com.example.carinfosystem.repository.ModelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/models")
@CrossOrigin(origins = "http://localhost:8080")
public class ModelController {

    @Autowired
    private ModelRepository modelRepository;

    @Autowired
    private BrandRepository brandRepository;

    @GetMapping("/brand/{brand}")
    public List<Model> getAll(@PathVariable String brand){
        Optional<Brand> brandOpt = brandRepository.findByName(brand);
        return brandOpt.map(value -> modelRepository.findByBrand(value)).orElse(null);
    }

    @GetMapping("/{id}")
    public Model findById(@PathVariable Long id){
        return modelRepository.findById(id).orElse(null);
    }

    @PostMapping
    public Model save(@RequestBody Model model){
        try{
            model.setId(null);
            return modelRepository.save(model);
        } catch (Exception e){
            return null;
        }
    }

    @PutMapping
    public Model update(@RequestBody Model model){
        if(model.getId() != null) {
            try {
                return modelRepository.save(model);
            } catch (Exception e) {
                return null;
            }
        }
        return null;
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        modelRepository.deleteById(id);
    }
}
