package com.lemanski.SmartFarm.controller;

import com.lemanski.SmartFarm.model.database.Animal;
import com.lemanski.SmartFarm.service.AnimalService;
import com.lemanski.SmartFarm.service.ValidationErrorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;


@RestController
@RequestMapping("/animals")
public class AnimalController {

    @Autowired
    private AnimalService animalService;

    @Autowired
    private ValidationErrorService errorService;


    @GetMapping("")
    public Iterable getAllAnimals() {
        return animalService.getAllAnimals();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getAnimal(@PathVariable Long id) {
        return animalService.getAnimal(id)
                .map(ResponseEntity::ok)
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("")
    public ResponseEntity<?> createNewAnimals(@Valid @RequestBody Animal animal, BindingResult result) {
        ResponseEntity<?> errorMap = errorService.validation(result);

        if (errorMap != null) {
            return errorMap;
        }
        Animal animal1 = animalService.saveAnimal(animal, null);
        return new ResponseEntity<>(animal1, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateAnimal(@Valid @RequestBody Animal animal, @PathVariable Long id, BindingResult result) {
        ResponseEntity<?> errorMap = errorService.validation(result);

        if (errorMap != null) {
            return errorMap;
        }

        Animal animal1 = animalService.saveAnimal(animal, Optional.ofNullable(id));
        return new ResponseEntity<>(animal1, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteAnimal(@PathVariable Long id) {
        Map<String, String> responseMap = new HashMap<>();
        responseMap.put("message", "Animal with ID " + id.toString() + " was deleted.");

        animalService.deleteAnimal(id);

        return new ResponseEntity<>(responseMap, HttpStatus.OK);
    }
}
