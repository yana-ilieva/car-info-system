package com.example.carinfosystem.model;

import com.example.carinfosystem.enums.CarType;
import com.example.carinfosystem.enums.Color;
import com.example.carinfosystem.enums.FuelType;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "cars")
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String registrationNumber;

    @ManyToOne
    @JoinColumn(name = "model_id")
    private Model model;

    @Enumerated(value = EnumType.STRING)
    private FuelType fuelType;

    @Enumerated(value = EnumType.STRING)
    private CarType carType;

    private String productionYear;

    @Enumerated(value = EnumType.STRING)
    private Color color;

    @ManyToMany
    private List<Extra> extras;

    @ManyToOne
    @JoinColumn(name = "branch_id")
    private Branch branch;

    @JsonManagedReference
    @OneToMany(mappedBy = "car", cascade = CascadeType.REMOVE)
    private List<Note> notes;

    private Boolean deleted;
}
