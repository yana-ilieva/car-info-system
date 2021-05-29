package com.example.carinfosystem.repository;

import com.example.carinfosystem.dto.SearchCarDto;
import com.example.carinfosystem.enums.CarType;
import com.example.carinfosystem.enums.Color;
import com.example.carinfosystem.enums.FuelType;
import com.example.carinfosystem.model.Car;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

public final class CarSearchSpecification {

    public static Specification<Car> searchCarSpecification(SearchCarDto searchCarDto){
        return (root, cq, cb) -> {
            List<Predicate> predicates = new ArrayList<>();
            if(searchCarDto.getProductionYear() != null){
                predicates.add(cb.like(root.get("productionYear"), searchCarDto.getProductionYear()));
            }
            if(searchCarDto.getRegistrationNumber() != null){
                predicates.add(cb.like(root.get("registrationNumber"), "%"+searchCarDto.getRegistrationNumber()+"%"));
            }

            if(searchCarDto.getCarType() != null){
                predicates.add(cb.equal(root.get("carType"), CarType.valueOf(searchCarDto.getCarType())));
            }
            if(searchCarDto.getFuelType() != null){
                predicates.add(cb.equal(root.get("fuelType"), FuelType.valueOf(searchCarDto.getFuelType())));
            }
            if(searchCarDto.getColor() != null){
                predicates.add(cb.equal(root.get("color"), Color.valueOf(searchCarDto.getColor())));
            }

            if(searchCarDto.getCity() != null){
                predicates.add(cb.equal(root.get("branch").get("city"), searchCarDto.getCity()));
            }
            if(searchCarDto.getBranch() != null){
                predicates.add(cb.equal(root.get("branch"), searchCarDto.getBranch()));
            }
            if(searchCarDto.getBrand() != null){
                predicates.add(cb.equal(root.get("model").get("brand").get("name"), searchCarDto.getBrand()));
            }
            if(searchCarDto.getModel() != null){
                predicates.add(cb.equal(root.get("model"), searchCarDto.getModel()));
            }
            if(searchCarDto.getExtras() != null && !searchCarDto.getExtras().isEmpty()){
                predicates.add(root.get("extras").in(searchCarDto.getExtras()));
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }
}
