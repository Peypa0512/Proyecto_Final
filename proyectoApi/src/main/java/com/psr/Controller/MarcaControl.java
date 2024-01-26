package com.psr.Controller;

import com.psr.models.Marca;
import com.psr.repository.MarcaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping(path="/api")
public class MarcaControl {

    // accedemos a los datos del repositorio
    @Autowired
    private MarcaRepository marcaRepository;

    @GetMapping("/marca")
    public Iterable<Marca> dataAll(){
        return marcaRepository.findAll();
    }

    @GetMapping("/marca/{id}")
    public Marca getMarca(@PathVariable int id){
        return marcaRepository.findById(id).get();
    }

    @PostMapping("/marca")
    public Marca addMarca(@RequestBody Marca marcas){
        return marcaRepository.save(marcas);
    }

    @PutMapping("/marca/{id}")
    public Marca updataMarca(@RequestBody Marca marca, @PathVariable int id){
        try{
            Marca brand = marcaRepository.findById(id).get();
            brand.setName(marca.getName());
            return marcaRepository.save(brand);
        }catch (Exception ex){
            return new Marca("Marca de Vehiculo no encontrada");
        }
    }

    @DeleteMapping("/marca/{id}")
    public   String deleteMarca(@PathVariable int id) {
        marcaRepository.deleteById(id);
        return "Marca Borrada";
    }


}























