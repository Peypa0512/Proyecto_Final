package com.psr.Controller;

import com.psr.models.Modelo;
import com.psr.repository.ModeloRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(path="/api")
public class ModeloControl {

    // accedemos a los datos del repositorio
    @Autowired
    private ModeloRepository modeloRepository;

    @GetMapping("/modelo")
    public Iterable<Modelo> dataAll() {
        return modeloRepository.findAll();
    }

    @GetMapping("/modelo/{id}")
    public Modelo getModelo(@PathVariable int id) {
        return modeloRepository.findById(id).get();
    }

    @PostMapping("/modelo")
    public Modelo addModelo(@RequestBody Modelo modelo) {

        return modeloRepository.save(modelo);
    }

    @PutMapping("/modelo/{id}")
    public Modelo updateModel(@RequestBody Modelo modelo, @PathVariable int id) {
        try {
            Modelo model = modeloRepository.findById(id).get();
            model.setModel(modelo.getModel());
            System.out.println(model);
            return modeloRepository.save(model);
        } catch (Exception e) {
            return new Modelo("Modelo no encontrado");
        }
    }

    @DeleteMapping("/modelo/{id}")
    public String deleteModelo(@PathVariable int id) {
        modeloRepository.deleteById(id);
        return "Modelo Borrada";
    }
}
