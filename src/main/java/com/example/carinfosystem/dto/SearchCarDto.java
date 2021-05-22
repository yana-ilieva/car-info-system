package com.example.carinfosystem.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SearchCarDto {
    private Long branch;
    private Long brand;
    private Long model;
    private Long city;
    private String fuelType;
    private String carType;
    private String color;
    private String productionYear;
    private List<Long> extras;
    private String registrationNumber;
}
