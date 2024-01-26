package com.psr.repository;

import com.psr.models.Modelo;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ModeloRepository extends JpaRepository<Modelo,  Integer> {
}
