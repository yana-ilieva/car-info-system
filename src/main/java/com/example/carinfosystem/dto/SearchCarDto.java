package com.example.carinfosystem.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SearchCarDto {
    private String branch;
    private String brand;
    private String model;
    private String fuelType;
    private String carType;
    private String color;
    private String productionYear;
    private List<String> extras;
}
