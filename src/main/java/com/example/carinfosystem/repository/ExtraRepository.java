package com.example.carinfosystem.repository;

import com.example.carinfosystem.model.Extra;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ExtraRepository extends JpaRepository<Extra, Long> {

    Extra findByName(String name);
}
