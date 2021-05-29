package com.example.carinfosystem.repository;

import com.example.carinfosystem.model.Branch;
import com.example.carinfosystem.model.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BranchRepository extends JpaRepository<Branch, Long> {
    List<Branch> findByCity(City city);
}
