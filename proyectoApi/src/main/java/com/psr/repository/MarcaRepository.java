package com.psr.repository;


import com.psr.models.Marca;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MarcaRepository extends JpaRepository<Marca,  Integer> {
    List<Marca> findByNameContaining(String name);


}
