package com.lemanski.SmartFarm.controller;

import com.lemanski.SmartFarm.service.AnimalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/animals")
public class AnimalController {

    @Autowired
    private AnimalService animalService;


    @GetMapping("")
    public Iterable getAllAnimals() {
        return animalService.getAllAnimals();
    }


}
