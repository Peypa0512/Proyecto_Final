package com.psr.repository;

import com.psr.models.Model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface ModelRepository extends JpaRepository<Model,  Integer> {

   List<Model> findByMarcaBrandId(int id);

   @Query("SELECT m FROM Modelo m JOIN FETCH m.marca ORDER BY m.marca.name ASC")
   List<Model> findAllORDERByMarcaName();


}
