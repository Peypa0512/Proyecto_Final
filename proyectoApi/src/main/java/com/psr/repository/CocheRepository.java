package com.psr.repository;


import com.psr.models.Coche;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;


public interface CocheRepository extends JpaRepository<Coche,  Integer> {

    List<Coche> findByModelModelId(int modelId);
}
