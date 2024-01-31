package com.psr.repository;

import com.psr.models.Coche;
import com.psr.models.Marca;
import com.psr.models.Modelo;
import com.psr.models.Propietario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface PropietarioRepository extends JpaRepository<Propietario,  Integer> {
    List<Propietario> findByNameContaining(String name);
    List<Propietario> findByCarCarId(int carId);

    @Query("SELECT p FROM Propietario p JOIN FETCH p.car")
    List<Propietario> findAllWithCars();
}
