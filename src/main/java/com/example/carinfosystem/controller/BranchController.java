package com.example.carinfosystem.controller;

import com.example.carinfosystem.model.Branch;
import com.example.carinfosystem.repository.BranchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/branches")
public class BranchController {

    @Autowired
    private BranchRepository branchRepository;

    public List<Branch> getAll(){
        return branchRepository.findAll();
    }

    @GetMapping
    public List<Branch> findAll(){
        return branchRepository.findAll();
    }

    @GetMapping("/{id}")
    public Branch findById(@PathVariable Long id){
        return branchRepository.findById(id).orElse(null);
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
