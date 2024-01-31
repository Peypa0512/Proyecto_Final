package com.psr.Controller;

import com.psr.models.Coche;
import com.psr.models.Pack;
import com.psr.repository.CocheRepository;
import com.psr.repository.PackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/api")
public class PackController {

    @Autowired
    PackRepository packRepository;

    @Autowired
   CocheRepository carRepository;


    @GetMapping("/pack")
    public ResponseEntity<List<Pack>> getAll(){
        List<Pack> res = new ArrayList<>();
        packRepository.findAll().forEach(res::add);
        if(res.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(res, HttpStatus.OK);
    }


    @GetMapping("/pack/{id}")
    public ResponseEntity<Pack> getById(@PathVariable("id") int id){
        Pack pack = packRepository.findById(id).orElse(null);

        if(pack == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(pack, HttpStatus.OK);
    }

    @GetMapping("/coche/{id}/pack")
    public ResponseEntity<List<Pack>> getAllByModel(@PathVariable("id") int id) {

        List<Pack> res = new ArrayList<>();

        packRepository.findByCarCarId(id).forEach(res::add);

        if(res.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @PostMapping("/coche/{id}/pack")
    public ResponseEntity<Pack> addPack(@PathVariable("id") int id, @RequestBody Pack pack){
        Coche car = carRepository.findById(id).orElse(null);
        if(car == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Pack temp = new Pack(pack.getColor(), pack.getCarFinish(), pack.getEngine(), pack.getCv(),
                pack.getCarFinish(), car);
        return new ResponseEntity<>(packRepository.save(temp), HttpStatus.OK);
    }
 // preguntar
    @PutMapping("pack/{id}")
    public ResponseEntity<Pack> updatePack(@PathVariable("id") int id, @RequestBody Pack pack){
        Pack aux = packRepository.findById(id).orElse(null);
        System.out.println(aux);
        System.out.println(pack);
        System.out.println(pack.getCar());
        if(aux == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        aux.setColor(pack.getColor());
        aux.setFinish(pack.getFinish());
        aux.setEngine(pack.getEngine());
        aux.setCv(pack.getCv());
        aux.setCarFinish(pack.getCarFinish());

        if (pack.getCarFinish() != null && pack.getCar() != null){
            Coche car = carRepository.findById(pack.getCar().getCarId()).orElse(null);
            if (car == null){
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }
        return new ResponseEntity<>(packRepository.save(aux), HttpStatus.OK);
    }

    @DeleteMapping("/coche/{id}/pack")
    public ResponseEntity<HttpStatus> deletePack(@PathVariable("id") int id){
        packRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
