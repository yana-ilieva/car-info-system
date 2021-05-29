package com.example.carinfosystem.dto;

import com.example.carinfosystem.model.Branch;
import com.example.carinfosystem.model.Model;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class SaveCarDto {
    private String registrationNumber;

    @NotNull
    private Model model;

    @NotBlank
    private String fuelType;

    @NotBlank
    private String carType;

    @NotBlank
    private String productionYear;

    @NotBlank
    private String color;

    private List<String> extras;

    @NotNull
    private Branch branch;
}
