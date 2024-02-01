package com.psr.repository;

import com.psr.models.Owner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface OwnerRepository extends JpaRepository<Owner,  Integer> {
    List<Owner> findByNameContaining(String name);
    List<Owner> findByCarIdCar(int idCar);

    @Query("SELECT p FROM Owner p JOIN FETCH p.car")
    List<Owner> findAllWithCars();
}
