package com.psr.repository;


import com.psr.models.Coche;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CocheRepository extends JpaRepository<Coche,  Integer> {
}
