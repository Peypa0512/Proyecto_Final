package com.psr.repository;


import com.psr.models.Brand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BrandRepository extends JpaRepository<Brand,  Integer> {
    List<Brand> findByNameContaining(String name);


}
