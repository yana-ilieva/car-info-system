package com.example.carinfosystem.controller;

import com.example.carinfosystem.dto.SaveCarDto;
import com.example.carinfosystem.dto.SearchCarDto;
import com.example.carinfosystem.enums.CarType;
import com.example.carinfosystem.enums.Color;
import com.example.carinfosystem.enums.FuelType;
import com.example.carinfosystem.model.*;
import com.example.carinfosystem.repository.CarRepository;
import com.example.carinfosystem.repository.ExtraRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
@WebMvcTest(CarController.class)
public class CarControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private CarRepository carRepository;

    @MockBean
    private ExtraRepository extraRepository;

    @Test
    public void getFuelTypes() throws Exception {
        List<String> fuels = new ArrayList<>();
        fuels.add("GAS");
        fuels.add("DIESEL");
        fuels.add("ETHANOL_MIXTURE");
        fuels.add("ELECTRICITY");
        String string = objectMapper.writeValueAsString(fuels);
        mvc.perform(get("/cars/fuels"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(content().json(string, true));
    }

    @Test
    public void getCarTypes() throws Exception {
        List<String> carTypes= new ArrayList<>();
        carTypes.add("MANUAL");
        carTypes.add("AUTOMATIC");
        String string = objectMapper.writeValueAsString(carTypes);
        mvc.perform(get("/cars/types"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(content().json(string, true));
    }

    @Test
    public void getColors() throws Exception {
        List<String> colors = new ArrayList<>();
        for (Color value : Color.values()) {
            colors.add(value.name());
        }
        String string = objectMapper.writeValueAsString(colors);
        mvc.perform(get("/cars/colors"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(content().json(string, true));
    }

    @Test
    public void search() throws Exception {
        Brand brand = new Brand();
        brand.setId(1L);
        brand.setName("Fiat");
        Model model = new Model();
        model.setId(1L);
        model.setName("Tipo");
        model.setBrand(brand);
        City city = new City();
        city.setId(1L);
        city.setName("Varna");
        Branch branch = new Branch();
        branch.setId(1L);
        branch.setName("Levski");
        branch.setCity(city);
        Car car = Car.builder()
                .id(1L)
                .color(Color.BLACK)
                .carType(CarType.MANUAL)
                .fuelType(FuelType.GAS)
                .productionYear("1994")
                .model(model)
                .branch(branch)
                .build();
        Brand brand1 = new Brand();
        brand1.setId(1L);
        brand1.setName("Fiat");
        Model model1 = new Model();
        model1.setId(1L);
        model1.setName("Stilo");
        model1.setBrand(brand);
        City city1 = new City();
        city1.setId(1L);
        city1.setName("Varna");
        Branch branch1 = new Branch();
        branch1.setId(1L);
        branch1.setName("Levski");
        branch1.setCity(city);
        Car car1 = Car.builder()
                .id(1L)
                .color(Color.BLACK)
                .carType(CarType.MANUAL)
                .fuelType(FuelType.DIESEL)
                .productionYear("2003")
                .model(model1)
                .branch(branch1)
                .build();
        List<Car> cars = new ArrayList<>();
        cars.add(car);
        cars.add(car1);

        when(carRepository.findAll(any(Specification.class))).thenReturn(cars);

        SearchCarDto dto = new SearchCarDto();
        dto.setBranch(1L);

        String request = objectMapper.writeValueAsString(dto);
        String response = objectMapper.writeValueAsString(cars);

        mvc.perform(post("/cars/search").contentType(MediaType.APPLICATION_JSON).content(request))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(content().json(response,true));

    }

    @Test
    public void save() throws Exception {
        Brand brand = new Brand();
        brand.setId(1L);
        brand.setName("Fiat");
        Model model = new Model();
        model.setId(1L);
        model.setName("Tipo");
        model.setBrand(brand);
        City city = new City();
        city.setId(1L);
        city.setName("Varna");
        Branch branch = new Branch();
        branch.setId(1L);
        branch.setName("Levski");
        branch.setCity(city);
        Car car = Car.builder()
                .id(1L)
                .color(Color.BLACK)
                .carType(CarType.MANUAL)
                .fuelType(FuelType.GAS)
                .productionYear("1994")
                .model(model)
                .branch(branch)
                .deleted(true)
                .deletedAt(LocalDateTime.now())
                .build();

        SaveCarDto saveCarDto = new SaveCarDto();
        saveCarDto.setCarType(car.getCarType().name());
        saveCarDto.setFuelType(car.getFuelType().name());
        saveCarDto.setColor(car.getColor().name());
        saveCarDto.setBranch(car.getBranch());
        saveCarDto.setModel(car.getModel());
        saveCarDto.setProductionYear(car.getProductionYear());

        String request = objectMapper.writeValueAsString(saveCarDto);
        String response = objectMapper.writeValueAsString(car);

        when(carRepository.save(any(Car.class))).thenReturn(car);

        mvc.perform(post("/cars").contentType(MediaType.APPLICATION_JSON).content(request))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(content().json(response,true));
    }
}