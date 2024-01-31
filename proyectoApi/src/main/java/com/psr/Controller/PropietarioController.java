package com.psr.Controller;

import com.psr.models.Propietario;
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
public class PropietarioController {

    // accedemos a los datos del repositorio
    @Autowired
    private PropietarioRepository ownerRepository;


    @GetMapping("/propietario")
    public ResponseEntity<List<Propietario>> dataAll(@RequestParam(required = false) String owner){
        List<Propietario> res = new ArrayList<>();
        if(owner == null) {
            ownerRepository.findAll().forEach(res::add);
        }else{
            ownerRepository.findByNameContaining(owner).forEach(res::add);
        }

        if (res.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @GetMapping("/propietario/{id}")
    public ResponseEntity<Propietario> getOwner(@PathVariable("id") int id){
       Propietario owner = ownerRepository.findById(id).orElse(null);

       if (owner == null){
           return new ResponseEntity<>(HttpStatus.NOT_FOUND);
       }
       return new ResponseEntity<>(owner, HttpStatus.OK);

    }
    // me da todos los propietarios con sus coches

    @PostMapping("/propietario")
    public ResponseEntity<Propietario> addOwner(@RequestBody Propietario owner){
        Propietario aux = ownerRepository.save(new Propietario(owner.getName(), owner.getDni(),
                owner.getCity(), owner.getPhone()));
        return new ResponseEntity<>(aux, HttpStatus.CREATED);
    }
    @PutMapping("/propietario/{id}")
    public ResponseEntity<Propietario> updateOwner(@PathVariable("id") int id, @RequestBody Propietario owner){
        Propietario own = ownerRepository.findById(id).orElse(null);
        if (own == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else{
            own.setName(owner.getName());
            own.setDni(owner.getDni());
            own.setCity(owner.getCity());
            own.setPhone(owner.getPhone());
            return new ResponseEntity<>(ownerRepository.save(own), HttpStatus.OK);
        }

    }
    @DeleteMapping("/propietario/{id}")
    public ResponseEntity<HttpStatus> deleteOwner(@PathVariable int id) {
        ownerRepository.deleteById(id);
       return new ResponseEntity<>(HttpStatus.OK);
    }

}























