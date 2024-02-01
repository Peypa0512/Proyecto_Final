package com.accenture.controller;

import com.accenture.models.Brand;
import com.accenture.repository.BrandRepository;
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
public class BrandController {

    @Autowired
    BrandRepository brandRepository;

    @GetMapping("/index")
    public String getAll(Model model){
        model.addAttribute("marcas", brandRepository.findAll());
        return "marca";
    }

//    // boton añadir
//    @GetMapping("/add")
//    public String addBrand(Brand brand){
//        return("add");
//    }
//
//    @PostMapping("/addbrand")
//    public String addBrand(@Validated Brand marca, BindingResult result, Model model){
//        if(result.hasErrors()){
//            return "add";
//        }
//        System.out.println(result);
//        brandRepository.save(marca);
//        return "redirect:/api/index";
//    }
//
//    @GetMapping("/edit/{id}")
//    public String editBrand(@PathVariable("id") int id, Model model){
//        Brand brand = brandRepository.findById(id).get();
//        return "updateMarca";
//    }
//


}
