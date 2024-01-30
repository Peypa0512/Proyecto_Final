package com.psr.Controller;

import com.psr.models.Pack;
import com.psr.repository.ModeloRepository;
import com.psr.repository.PackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/api")
public class PackController {

    @Autowired
    PackRepository packRepository;

    @Autowired
    ModeloRepository modelRepository;


    @GetMapping("/pack")
    public ResponseEntity<List<Pack>> getAll(){
        List<Pack> res = new ArrayList<>();
        packRepository.findAll().forEach(res::add);
        if(res.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(res, HttpStatus.OK);
    }




}
