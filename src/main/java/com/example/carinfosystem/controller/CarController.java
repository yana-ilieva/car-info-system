package com.example.carinfosystem.controller;

import com.example.carinfosystem.dto.SearchCarDto;
import com.example.carinfosystem.enums.CarType;
import com.example.carinfosystem.enums.Color;
import com.example.carinfosystem.enums.FuelType;
import com.example.carinfosystem.model.Car;
import com.example.carinfosystem.repository.CarRepository;
import com.example.carinfosystem.util.ExcelExporter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.example.carinfosystem.repository.CarSearchSpecification.searchCarSpecification;

@RestController
@RequestMapping("/cars")
public class CarController {
    @Autowired
    private CarRepository carRepository;

    @GetMapping("/fuels")
    public List<String> getFuelTypes(){
        List<String> fuels = new ArrayList<>();
        for(FuelType f: FuelType.values()){
            fuels.add(f.name());
        }
        return fuels;
    }

    @GetMapping("/types")
    public List<String> getCarTypes(){
        List<String> types = new ArrayList<>();
        for(CarType c: CarType.values()){
            types.add(c.name());
        }
        return types;
    }

    @GetMapping("/colors")
    public List<String> getColors(){
        List<String> colors = new ArrayList<>();
        for(Color c: Color.values()){
            colors.add(c.name());
        }
        return colors;
    }

    @GetMapping
    public List<Car> getAll(){
        return carRepository.findAll();
    }

    @GetMapping("/{id}")
    public Car findById(@PathVariable Long id){
        return carRepository.findById(id).orElse(null);
    }

    @GetMapping("/export_deleted")
    public void findAllDeleted(HttpServletResponse response) throws IOException {
        response.setContentType("application/octet-stream");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String currentDateTime = LocalDateTime.now().format(formatter);

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=deleted_cars_"+currentDateTime + ".xlsx";
        response.setHeader(headerKey, headerValue);

        ExcelExporter exporter = new ExcelExporter(carRepository.findByDeletedTrue());
        exporter.export(response);
    }

    @PostMapping("/search")
    public List<Car> search(@RequestBody SearchCarDto searchCarDto){
        return carRepository.findAll(searchCarSpecification(searchCarDto));
    }

    @PostMapping
    public Car save(@RequestBody Car car){
        try{
            car.setId(null);
            car.setDeleted(false);
            return carRepository.save(car);
        } catch (Exception e){
            throw new RuntimeException("Could not save car.");
        }
    }

    @PutMapping
    public Car update(@RequestBody Car car){
        if(car.getId() != null){
            try{
                return carRepository.save(car);
            } catch (Exception e){
                throw new RuntimeException("Could not update car.", e);
            }
        }
        throw new RuntimeException("car with id " + car.getId() + " not found.");
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        Optional<Car> carOpt = carRepository.findById(id);
        if(carOpt.isPresent()){
            carOpt.get().setDeleted(true);
            carOpt.get().setDeletedAt(LocalDateTime.now());
            carRepository.save(carOpt.get());
        }
    }
}
