package com.psr.Controller;

import com.psr.models.Car;
import com.psr.models.Model;
import com.psr.models.Owner;
import com.psr.repository.CarRepository;
import com.psr.repository.ModelRepository;
import com.psr.repository.OwnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(path="/api")
public class CarController {

    // accedemos a los datos del repositorio
    @Autowired
    private CarRepository carRepository;

    @Autowired
    private OwnerRepository ownerRepository;

    @Autowired
    private ModelRepository modelRepository;

    @GetMapping("/coche")
    public ResponseEntity<List<Car>> dataAll() {
        List<Car> res = new ArrayList<>();
        carRepository.findAll().forEach(res::add);
        if (res.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @GetMapping("/coche/modelo")
    public ResponseEntity<List<Car>> getModelCoche() {
        List<Car> res = new ArrayList<>();
        carRepository.findAll().forEach(res::add);
        if (res.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @GetMapping("/coche/{id}")
    public ResponseEntity<Car> getById(@PathVariable("id") int id) {
        Car car = carRepository.findById(id).orElse(null);

        if (car == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else{
            return new ResponseEntity<>(car, HttpStatus.OK);
        }
    }

    @GetMapping("/coche/modelo/{id}")
    public ResponseEntity<List<Car>> getModelCarById(@PathVariable("id") int id) {
        List<Car>car = carRepository.findByModelIdModel(id);

        if (car.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(car, HttpStatus.OK);

    }


    @PostMapping("/propietario/{id}/coche")
    public ResponseEntity<Car> addCoche(@PathVariable("id") int id, @RequestBody Car car) {
        Owner newOwn = ownerRepository.findById(id).orElse(null);
        if (newOwn == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        Car newCar = new Car(car.getRegistration(), car.getRegdate(), car.getPvp(), newOwn);
        return new ResponseEntity<>(carRepository.save(newCar), HttpStatus.CREATED);
    }

    @PostMapping("/coche/{idcar}/modelo/{idmodel}")
    public ResponseEntity<Car> addModelCar(@PathVariable("idcar") int idcar,
                                           @PathVariable("idmodel") int idmodel) {

        Car car = carRepository.findById(idcar).orElse(null);
        Model model = modelRepository.findById(idmodel).orElse(null);
        if (car == null || model == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        car.getModel().add(model);


        return new ResponseEntity<>(carRepository.save(car), HttpStatus.CREATED);
    }


    @PutMapping("/coche/{id}")
    public ResponseEntity<Car> updateCar(@PathVariable("id") int id, @RequestBody Car car){
        Car aux = carRepository.findById(id).orElse(null);
        if (aux == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        aux.setOwner(car.getOwner());
        aux.setRegistration(car.getRegistration());
        aux.setRegdate(car.getRegdate());
        aux.setPvp(car.getPvp());
        if(car.getRegistration() != null) {
            Owner owners = ownerRepository.findById(car.getOwner().getIdOwner()).orElse(null);
            if (owners != null) {
                aux.setOwner(owners);
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
    public ResponseEntity<Car> deleteCarModel(@PathVariable("idcar") int idcar,
                                              @PathVariable("idmodel") int idmodel) {
        Car car = carRepository.findById(idcar).orElse(null);
        Model model = modelRepository.findById(idmodel).orElse(null);
        if (car == null || model == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        car.getModel().remove(model);

        return new ResponseEntity<>(carRepository.save(car), HttpStatus.OK);
    }

    @DeleteMapping("/coche/{idcar}/propietario/{idown}")
    public ResponseEntity<Car> ownerCar(@PathVariable("idcar") int idcar,
                                        @PathVariable("idown") int idown) {
        Car car = carRepository.findById(idcar).orElse(null);
        Owner own = ownerRepository.findById(idown).orElse(null);
        if (car == null || own == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        car.getModel().remove(own);

        return new ResponseEntity<>(carRepository.save(car), HttpStatus.OK);
    }


}

