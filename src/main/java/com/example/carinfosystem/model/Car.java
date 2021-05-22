package com.example.carinfosystem.model;

import com.example.carinfosystem.enums.CarType;
import com.example.carinfosystem.enums.Color;
import com.example.carinfosystem.enums.FuelType;
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

    @ManyToOne
    @JoinColumn(name = "model_id")
    private Model model;

    @Enumerated(value = EnumType.STRING)
    private FuelType fuelType;

    @Enumerated(value = EnumType.STRING)
    private CarType carType;

    private Integer productionYear;

    @Enumerated(value = EnumType.STRING)
    private Color color;

    @ManyToMany
    private List<Extra> extras;

    private Boolean deleted;
}
