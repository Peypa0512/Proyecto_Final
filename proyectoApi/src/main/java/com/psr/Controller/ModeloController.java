package com.psr.Controller;

import com.psr.models.Marca;
import com.psr.models.Modelo;
import com.psr.repository.CocheRepository;
import com.psr.repository.MarcaRepository;
import com.psr.repository.ModeloRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(path="/api")
public class ModeloController {

    // accedemos a los datos del repositorio
    @Autowired
    ModeloRepository modeloRepository;

    @Autowired
    MarcaRepository marcaRepository;



    @GetMapping("/modelo")
    public ResponseEntity<List<Modelo>> dataAll() {
        List<Modelo> res = new ArrayList<>();
        modeloRepository.findAll().forEach(res::add);
        if(res.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(res, HttpStatus.OK);
    }



    @GetMapping("/modelo/{id}")
    public ResponseEntity<Modelo> getModel(@PathVariable("int") int id) {
        Modelo model = modeloRepository.findById(id).orElse(null);
        if (model == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else{
            return new ResponseEntity<>(model, HttpStatus.OK);
        }
    }
    //Hasta aqui-> falta probarlo
    @PostMapping("/marca/{id}/modelo")
    public ResponseEntity<Modelo> addModel(@PathVariable("id") int id, @RequestBody Modelo modelo) {

        Marca brand = marcaRepository.findById(id).orElse(null);
        if (brand == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Modelo aux = new Modelo(modelo.getModel(), brand);
        return new ResponseEntity<>(modeloRepository.save(aux), HttpStatus.CREATED);
    }

    @PutMapping("/modelo/{id}")
    public ResponseEntity<Modelo> updateModel(@PathVariable("id") int id, @RequestBody Modelo modelo ) {

            Modelo aux = modeloRepository.findById(id).orElse(null);

            if(aux == null) {
               return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            aux.setModel(modelo.getModel());
            if(modelo.getMarca() != null){
                Marca brand = marcaRepository.findById(modelo.getMarca().getBrandId()).orElse(null);
                if(brand != null){
                    aux.setMarca(brand);
                }
            }
            return new ResponseEntity<>(modeloRepository.save(aux), HttpStatus.OK);

    }

    @DeleteMapping("/modelo/{id}")
    public ResponseEntity<HttpStatus> deleteModel(@PathVariable("int") int id) {
        modeloRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
