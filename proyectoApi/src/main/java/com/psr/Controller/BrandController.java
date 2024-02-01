package com.psr.Controller;


import com.psr.models.Brand;
import com.psr.repository.BrandRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping(path="/api")
public class BrandController {

    // accedemos a los datos del repositorio
    @Autowired
    private BrandRepository brandRepository;

    @GetMapping("/marca")
    public ResponseEntity<List<Brand>> getAll(@RequestParam(required = false) String brand){
        List<Brand> res = new ArrayList<>();
        if (brand == null){
            brandRepository.findAll().forEach(res::add);
        } else{
            brandRepository.findByNameContaining(brand).forEach(res::add);
        }
        if (res.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(res, HttpStatus.OK);

    }

    @GetMapping("/marca/{id}")
    public ResponseEntity<Brand> getModel(@PathVariable("id") int id) {
        Brand brand = brandRepository.findById(id)
                .orElse(null);

        if (brand == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(brand, HttpStatus.OK);
        }
    }

    @PostMapping("/marca")
    public ResponseEntity<Brand> addBrand(@RequestBody Brand brand){
        Brand aux = brandRepository.save(new Brand(brand.getName()));
        return new ResponseEntity<>(aux, HttpStatus.CREATED);
    }

    @PutMapping("/marca/{id}")
    public ResponseEntity<Brand> updateBrand(@PathVariable("id") int id, @RequestBody Brand brand){

        Brand aux = brandRepository.findById(id).orElse(null);
        if (aux == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            aux.setName(brand.getName());
            return new ResponseEntity<>(brandRepository.save(aux), HttpStatus.OK);
        }

    }

    @DeleteMapping("/marca/{id}")
    public ResponseEntity<HttpStatus> deleteBrand(@PathVariable("id") int id) {
        brandRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }


}























