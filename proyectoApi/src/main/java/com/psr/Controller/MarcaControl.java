package com.psr.Controller;

import com.psr.models.Marca;
import com.psr.repository.MarcaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping(path="/api")
public class MarcaControl {

    // accedemos a los datos del repositorio
    @Autowired
    private MarcaRepository marcaRepository;

    @GetMapping("/marca")
    public ResponseEntity<List<Marca>> getAll(@RequestParam(required = false) String brand){
        List<Marca> res = new ArrayList<>();
        if (brand == null){
            marcaRepository.findAll().forEach(res::add);
        } else{
            marcaRepository.findByNameContaining(brand).forEach(res::add);
        }
        if (res.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(res, HttpStatus.OK);

    }

    @GetMapping("/marca/{id}")
    public ResponseEntity<Marca> getModel(@PathVariable("id") int id) {
        Marca brand = marcaRepository.findById(id)
                .orElse(null);

        if (brand == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(brand, HttpStatus.OK);
        }
    }

    @PostMapping("/marca")
    public ResponseEntity<Marca> addBrand(@RequestBody Marca brand){
        Marca aux = marcaRepository.save(new Marca(brand.getName()));
        return new ResponseEntity<>(aux, HttpStatus.CREATED);
    }

    @PutMapping("/marca/{id}")
    public ResponseEntity<Marca> updateBrand(@PathVariable("id") int id, @RequestBody Marca brand){

            Marca aux = marcaRepository.findById(id).orElse(null);
            if (aux == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            } else {
                aux.setName(brand.getName());
                return new ResponseEntity<>(marcaRepository.save(aux), HttpStatus.OK);
            }

    }

    @DeleteMapping("/marca/{id}")
    public ResponseEntity<HttpStatus> deleteBrand(@PathVariable("id") int id) {
        marcaRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }


}























