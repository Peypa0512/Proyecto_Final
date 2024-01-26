package com.psr.Controller;

import com.psr.models.Propietario;
import com.psr.repository.PropietarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(path="/api")
public class PropietarioControl {

    // accedemos a los datos del repositorio
    @Autowired
    private PropietarioRepository propietarioRepository;


    @GetMapping("/propietario")
    public Iterable<Propietario> dataAll(){
        return propietarioRepository.findAll();
    }

    @GetMapping("/propietario/{id}")
    public Propietario getModelo(@PathVariable int id){
        return propietarioRepository.findById(id).get();
    }

    @PostMapping("/propietario")
    public Propietario addPropietario(@RequestBody Propietario owner){

        return propietarioRepository.save(owner);
    }
    @PutMapping("/propietario/{id}")
    public Propietario updateOwner(@RequestBody Propietario owner, @PathVariable int id){
        Propietario own = propietarioRepository.findById(id).get();
        own.setName(owner.getName());
        own.setDni(owner.getDni());
        own.setCity(owner.getCity());
        own.setPhone(owner.getPhone());
        return propietarioRepository.save(own);
    }
    @DeleteMapping("/propietario/{id}")
    public String deleteModelo(@PathVariable int id) {
        propietarioRepository.deleteById(id);
       return "Cliente borrado";
    }

}























