package com.lemanski.SmartFarm.controller;

import com.lemanski.SmartFarm.model.database.Treatment;
import com.lemanski.SmartFarm.service.TreatmentService;
import com.lemanski.SmartFarm.service.ValidationErrorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
public class TreatmentController {

    @Autowired
    private TreatmentService treatmentService;

    @Autowired
    private ValidationErrorService errorService;

    @GetMapping("/treatments")
    public ResponseEntity<?> getAllTreatments() {
        return new ResponseEntity<>(treatmentService.getAllTreatments(), HttpStatus.OK);
    }

    @GetMapping("/animals/treatment")
    public ResponseEntity<?> getTreatmentForAnimal(@RequestParam Long animalId, @RequestParam Long treatmentId) {
        return new ResponseEntity<>(treatmentService.getTreatment(animalId, treatmentId), HttpStatus.OK);
    }

    @PostMapping("/animals/treatment")
    public ResponseEntity<?> addNewTreatment(@RequestParam String animalId, @RequestBody Treatment treatment, BindingResult result) {
        ResponseEntity<?> errorMap = errorService.validation(result);

        if(errorMap != null) {
            return errorMap;
        }

        Treatment treatment1 = treatmentService.saveTreatment(Long.parseLong(animalId), treatment);
        return new ResponseEntity<>(treatment1, HttpStatus.CREATED);
    }
}
