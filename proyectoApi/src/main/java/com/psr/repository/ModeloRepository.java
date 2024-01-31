package com.psr.repository;

import com.psr.models.Modelo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface ModeloRepository extends JpaRepository<Modelo,  Integer> {

   List<Modelo> findByMarcaBrandId(int id);

   @Query("SELECT m FROM Modelo m JOIN FETCH m.marca ORDER BY m.marca.name ASC")
   List<Modelo> findAllORDERByMarcaName();


}
