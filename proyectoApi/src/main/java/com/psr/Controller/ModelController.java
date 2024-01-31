package com.psr.Controller;

import com.psr.models.Brand;
import com.psr.models.Model;
import com.psr.repository.BrandRepository;
import com.psr.repository.ModelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(path="/api")
public class ModelController {

    // accedemos a los datos del repositorio
    @Autowired
    ModelRepository modelRepository;

    @Autowired
    BrandRepository brandRepository;



    @GetMapping("/modelo")
    public ResponseEntity<List<Model>> dataAll() {
        List<Model> res = new ArrayList<>();
        modelRepository.findAll().forEach(res::add);
        if(res.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(res, HttpStatus.OK);
    }



    @GetMapping("/modelo/{id}")
    public ResponseEntity<Model> getModel(@PathVariable("int") int id) {
        Model model = modelRepository.findById(id).orElse(null);
        if (model == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else{
            return new ResponseEntity<>(model, HttpStatus.OK);
        }
    }
    @GetMapping("/marca/modelos")
    public ResponseEntity<List<Model>> getAllBrand(){
        List<Model> res = new ArrayList<>();
        modelRepository.findAllORDERByMarcaName().forEach(res::add);
        if(res.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(res, HttpStatus.OK);

    }
    @GetMapping("/marca/{id}/modelo")
    public ResponseEntity<List<Model>> getAllByCountry(@PathVariable("id") int id) {
        List<Model> res = new ArrayList<>();
        modelRepository.findByMarcaBrandId(id).forEach(res::add);
        if (res.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @PostMapping("/marca/{id}/modelo")
    public ResponseEntity<Model> addModel(@PathVariable("id") int id, @RequestBody Model model) {

        Brand brands = brandRepository.findById(model.getBrand().getBrandId()).orElse(null);
        if (brands == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Model aux = new Model(model.getModel(), brands);
        return new ResponseEntity<>(modelRepository.save(aux), HttpStatus.CREATED);
    }

    @PutMapping("/modelo/{id}")
    public ResponseEntity<Model> updateModel(@PathVariable("id") int id, @RequestBody Model model ) {

        Model aux = modelRepository.findById(id).orElse(null);

        if(aux == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        aux.setModel(model.getModel());
        if(model.getBrand() != null){
            Brand brands = brandRepository.findById(model.getBrand().getBrandId()).orElse(null);
            if(brands != null){
                aux.setBrand(brands);
            }
        }
        return new ResponseEntity<>(modelRepository.save(aux), HttpStatus.OK);
    }

    @DeleteMapping("/modelo/{id}")
    public ResponseEntity<HttpStatus> deleteModel(@PathVariable("int") int id) {
        modelRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}

