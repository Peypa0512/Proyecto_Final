package com.accenture.controller;

import com.accenture.models.Marca;
import com.accenture.repository.MarcaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path="/api")
public class MarcaController {

    @Autowired
    MarcaRepository marcaRepository;

    @GetMapping("/index")
    public String getAll(Model model){
        model.addAttribute("marcas", marcaRepository.findAll());
        return "marca";
    }

    // boton añadir
    @GetMapping("/add")
    public String addBrand(Marca brand){
        return("add");
    }

    @PostMapping("/addbrand")
    public String addBrand(@Validated Marca marca, BindingResult result, Model model){
        if(result.hasErrors()){
            return "add";
        }
        System.out.println(result);
        marcaRepository.save(marca);
        return "redirect:/api/index";
    }

    @GetMapping("/edit/{id}")
    public String editBrand(@PathVariable("id") int id, Model model){
        Marca brand = marcaRepository.findById(id).get();
        return "updateMarca";
    }



}
