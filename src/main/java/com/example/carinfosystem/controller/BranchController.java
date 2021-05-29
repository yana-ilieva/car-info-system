package com.example.carinfosystem.controller;

import com.example.carinfosystem.model.Branch;
import com.example.carinfosystem.model.City;
import com.example.carinfosystem.repository.BranchRepository;
import com.example.carinfosystem.repository.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/branches")
@CrossOrigin(origins = "http://localhost:8080")
public class BranchController {

    @Autowired
    private BranchRepository branchRepository;

    @Autowired
    private CityRepository cityRepository;

    @GetMapping
    public List<Branch> findAll(){
        return branchRepository.findAll();
    }

    @GetMapping("/{id}")
    public Branch findById(@PathVariable Long id){
        return branchRepository.findById(id).orElse(null);
    }

    @GetMapping("/city/{id}")
    public List<Branch> findByCity(@PathVariable("id") Long cityId){
        Optional<City> cityOpt = cityRepository.findById(cityId);
        return cityOpt.map(city -> branchRepository.findByCity(city)).orElse(null);
    }

    @PostMapping
    public Branch save(@RequestBody Branch branch){
        try{
            branch.setId(null);
            return branchRepository.save(branch);
        } catch (Exception e){
            return null;
        }
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        branchRepository.deleteById(id);
    }
}
