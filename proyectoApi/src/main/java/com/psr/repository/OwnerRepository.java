package com.psr.repository;

import com.psr.models.Owner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface OwnerRepository extends JpaRepository<Owner,  Integer> {
    List<Owner> findByNameContaining(String name);
    List<Owner> findByCarCarId(int carId);

    @Query("SELECT p FROM Propietario p JOIN FETCH p.car")
    List<Owner> findAllWithCars();
}
