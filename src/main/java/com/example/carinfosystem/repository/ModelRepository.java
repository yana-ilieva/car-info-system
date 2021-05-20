package com.example.carinfosystem.repository;

import com.example.carinfosystem.model.Brand;
import com.example.carinfosystem.model.Model;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ModelRepository extends JpaRepository<Model, Long> {
    List<Model> findByBrand(Brand brand);
}
