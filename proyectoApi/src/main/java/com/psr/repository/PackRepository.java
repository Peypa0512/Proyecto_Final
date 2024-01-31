package com.psr.repository;


import com.psr.models.Pack;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.List;

public interface PackRepository extends JpaRepository<Pack, Integer> {
    List<Pack> findByCarCarId(int id);

}
