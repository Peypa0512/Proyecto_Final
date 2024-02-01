package com.psr.Controller;

import com.psr.models.Owner;
import com.psr.repository.OwnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class OwnerController {

    // accedemos a los datos del repositorio
    @Autowired
    private OwnerRepository ownerRepository;


    @GetMapping("/propietario")
    public ResponseEntity<List<Owner>> dataAll(@RequestParam(required = false) String owner){
        List<Owner> res = new ArrayList<>();
        if(owner == null) {
            ownerRepository.findAll().forEach(res::add);
        }else{
            ownerRepository.findByNameContaining(owner).forEach(res::add);
        }
        if (res.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);        }
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @GetMapping("/propietario/{id}")
    public ResponseEntity<Owner> getOwner(@PathVariable("id") int id){
       Owner owner = ownerRepository.findById(id).orElse(null);

       if (owner == null){
           return new ResponseEntity<>(HttpStatus.NOT_FOUND);
       }
       return new ResponseEntity<>(owner, HttpStatus.OK);

    }
    // me da todos los propietarios con sus coches
    @GetMapping("/propietario/coches")
    public List<Owner> carsOwners() {
        return ownerRepository.findAllWithCars();
    }

    @GetMapping("/propietario/coches/{id}")
    public ResponseEntity<List<Owner>> getCochesByPropietario(@PathVariable("id") int ownerId) {
        List<Owner> owner = ownerRepository.findByCarIdCar(ownerId);

        if (owner.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(owner, HttpStatus.OK);
    }
    @PostMapping("/propietario")
    public ResponseEntity<Owner> addOwner(@RequestBody Owner owner){
        Owner aux = ownerRepository.save(new Owner(owner.getName(), owner.getDni(),
                owner.getCity(), owner.getPhone()));
        return new ResponseEntity<>(aux, HttpStatus.CREATED);
    }
    @PutMapping("/propietario/{id}")
    public ResponseEntity<Owner> updateOwner(@PathVariable("id") int id, @RequestBody Owner owner){
        Owner own = ownerRepository.findById(id).orElse(null);
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























