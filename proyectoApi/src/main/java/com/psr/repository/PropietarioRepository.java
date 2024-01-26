package com.psr.repository;

import com.psr.models.Propietario;
import org.springframework.data.jpa.repository.JpaRepository;


public interface PropietarioRepository extends JpaRepository<Propietario,  Integer> {
}
