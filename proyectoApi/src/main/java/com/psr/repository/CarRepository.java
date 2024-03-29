package com.psr.repository;


import com.psr.models.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;


public interface CarRepository extends JpaRepository<Car,  Integer> {

    List<Car> findByModelIdModel(int idModel);
}
