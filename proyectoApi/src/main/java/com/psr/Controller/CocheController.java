package com.psr.Controller;

import com.psr.models.Coche;
import com.psr.models.Modelo;
import com.psr.models.Pack;
import com.psr.models.Propietario;
import com.psr.repository.CocheRepository;
import com.psr.repository.ModeloRepository;
import com.psr.repository.PropietarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/api")
public class CocheController {

    // accedemos a los datos del repositorio
    @Autowired
    private CocheRepository carRepository;

    @Autowired
    private PropietarioRepository ownerRepository;

    @Autowired
    private ModeloRepository modelRepository;

    @GetMapping("/coche")
    public ResponseEntity<List<Coche>> dataAll() {
        List<Coche> res = new ArrayList<>();
        carRepository.findAll().forEach(res::add);
        if (res.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @GetMapping("/coche/modelo")
    public ResponseEntity<List<Coche>> getModelCoche() {
        List<Coche> res = new ArrayList<>();
        carRepository.findAll().forEach(res::add);
        if (res.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @GetMapping("/coche/{id}")
    public ResponseEntity<Coche> getById(@PathVariable("id") int id) {
        Coche car = carRepository.findById(id).orElse(null);

        if (car == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else{
            return new ResponseEntity<>(car, HttpStatus.OK);
        }
    }

    @GetMapping("/coche/modelo/{id}")
    public ResponseEntity<List<Coche>> getModelCarById(@PathVariable("id") int id) {
        List<Coche>car = carRepository.findByModelModelId(id);

        if (car.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
            return new ResponseEntity<>(car, HttpStatus.OK);

    }


    @PostMapping("/Propietario/{id}/coche")
    public ResponseEntity<Coche> addCoche(@PathVariable("id") int id, @RequestBody Coche car) {
        Propietario newOwner = ownerRepository.findById(id).orElse(null);
        if (newOwner == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        Coche newCar = new Coche(car.getRegistration(), car.getRegDate(), car.getPvp(), newOwner);
        return new ResponseEntity<>(newCar, HttpStatus.CREATED);
    }

    @PostMapping("/coche/{idcar}/modelo/{idmodel}")
    public ResponseEntity<Coche> addModelCar(@PathVariable("idcar") int idcar,
                                                @PathVariable("idmodel") int idmodel) {

        Coche car = carRepository.findById(idcar).orElse(null);
        Modelo model = modelRepository.findById(idmodel).orElse(null);
        if (car == null || model == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        car.getModel().add(model);

        return new ResponseEntity<>(carRepository.save(car), HttpStatus.CREATED);
    }


        @PutMapping("/coche/{id}")
        public ResponseEntity<Coche> updateCar(@PathVariable("id") int id, @RequestBody Coche car){
        Coche aux = carRepository.findById(id).orElse(null);
        if (aux == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        aux.setOwner(car.getOwner());
        aux.setRegistration(car.getRegistration());
        aux.setRegDate(car.getRegDate());
        aux.setPvp(car.getPvp());
        if(car.getRegistration() != null) {
            Propietario owner = ownerRepository.findById(car.getOwner().getOwnerId()).orElse(null);
            if (owner != null) {
                aux.setOwner(owner);
            }

        }
        return new ResponseEntity<>(aux, HttpStatus.OK);
    }
    @DeleteMapping("/coche/{id}")
    public ResponseEntity<HttpStatus> deleteCar(@PathVariable("id") int id) {

        carRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/coche/{idcar}/modelo/{idmodel}")
    public ResponseEntity<Coche> deleteCarModel(@PathVariable("idcar") int idcar,
                                                   @PathVariable("idmodel") int idmodel) {
        Coche car = carRepository.findById(idcar).orElse(null);
        Modelo model = modelRepository.findById(idmodel).orElse(null);
        if (car == null || model == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        car.getModel().remove(model);

        return new ResponseEntity<>(carRepository.save(car), HttpStatus.OK);
    }

    @DeleteMapping("/coche/{idcar}/propietario/{idown}")
    public ResponseEntity<Coche> ownerCar(@PathVariable("idcar") int idcar,
                                                @PathVariable("idown") int idown) {
        Coche car = carRepository.findById(idcar).orElse(null);
        Propietario own = ownerRepository.findById(idown).orElse(null);
        if (car == null || own == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        car.getModel().remove(own);

        return new ResponseEntity<>(carRepository.save(car), HttpStatus.OK);
    }


}






















