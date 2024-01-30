package com.psr.repository;


import com.psr.models.Coche;
import com.psr.models.Modelo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface CocheRepository extends JpaRepository<Coche,  Integer> {
    List<Coche> findByOwnerOwnerId(int ownerId);

    @Query("SELECT c FROM Coche c JOIN c.owner o JOIN c.model m JOIN m.marca WHERE o.ownerId = :ownerId")
    List<Coche> findCarsByOwnerId(@Param("ownerId") int ownerId);
    }
