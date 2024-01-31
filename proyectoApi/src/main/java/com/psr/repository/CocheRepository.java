package com.psr.repository;


import com.psr.models.Coche;
import com.psr.models.Modelo;
import com.psr.models.Pack;
import com.psr.models.Propietario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface CocheRepository extends JpaRepository<Coche,  Integer> {
    List<Coche> findByOwnerOwnerId(int ownerId);

    List<Coche> findByModelModelId(int modelId);

    @Query("SELECT c FROM Coche c JOIN c.owner o JOIN c.model m JOIN m.marca WHERE o.ownerId = :ownerId")
    List<Coche> findCarsByOwnerId(@Param("ownerId") int ownerId);

    @Query(value = "SELECT p.*, c.*, pk.*, md.*, m.* FROM propietario p " +
            "join coche c on p.propietario_id = c.propietario_id " +
            "join pack pk on c.coche_id = pk.coche_id " +
            "join modelo md on c.modelo_id = md.modelo_id " +
            "join marca m on md.marca_id = m.marca_id", nativeQuery = true)

    List<Coche> findAllPropietariosWithCoches();
    }
