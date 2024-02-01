package com.psr.repository;

import com.psr.models.Model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface ModelRepository extends JpaRepository<Model,  Integer> {

   List<Model> findByBrandIdBrand(int id);

   @Query("SELECT m FROM Model m JOIN FETCH m.brand ORDER BY m.brand.name ASC")
   List<Model> findAllORDERByBrandName();


}
