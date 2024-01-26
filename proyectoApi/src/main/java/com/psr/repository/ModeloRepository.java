package com.psr.repository;

import com.psr.models.Modelo;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface ModeloRepository extends JpaRepository<Modelo,  Integer> {

   List<Modelo> findByMarcaBrandId(int id);


}
