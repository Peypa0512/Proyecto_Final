package com.psr.Controller;

import com.psr.models.Coche;
import com.psr.repository.CocheRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(path="/api")
public class CocheControl {

    // accedemos a los datos del repositorio
    @Autowired
    private CocheRepository cocheRepository;

    @GetMapping("/coche")
    public Iterable<Coche> dataAll() {
        return cocheRepository.findAll();
    }

    @GetMapping("/coche/{id}")
    public Coche getById(@PathVariable int id) {
        return cocheRepository.findById(id).get();
    }

    @PostMapping("/coche")
    public Coche addCoche(@RequestBody Coche car) {

        return cocheRepository.save(car);

    }

        @PutMapping("/coche/{id}")
    public Coche updateCar(@RequestBody Coche coche, @PathVariable int id){
        Coche car = cocheRepository.findById(id).get();
        car.setRegistration(coche.getRegistration());
        car.setPvp(coche.getPvp());
        car.setRegDate(coche.getRegDate());
        return cocheRepository.save(car);
    }
    @DeleteMapping("/coche/{id}")
    public void deleteModelo(@PathVariable int id) {

        cocheRepository.deleteById(id);
    }

}






















