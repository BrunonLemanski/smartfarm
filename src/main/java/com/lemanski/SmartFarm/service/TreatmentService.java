package com.lemanski.SmartFarm.service;

import com.lemanski.SmartFarm.exception.CustomExceptionMessage;
import com.lemanski.SmartFarm.exception.NotFoundAnimalException;
import com.lemanski.SmartFarm.model.database.Animal;
import com.lemanski.SmartFarm.model.database.Treatment;
import com.lemanski.SmartFarm.repository.AnimalRepository;
import com.lemanski.SmartFarm.repository.TreatmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TreatmentService {

    @Autowired
    private TreatmentRepository treatmentRepository;

    @Autowired
    private AnimalRepository animalRepository;

    public List getAllTreatments() {
        List<Treatment> treatments = treatmentRepository.findAll();

        if (treatments.isEmpty()) {
            throw new CustomExceptionMessage("There is no treatments in your base.");
        } else {
            return treatmentRepository.findAll();
        }
    }

    public Treatment getTreatment(Long animalId, Long treatmentId) {
        Optional<Animal> animal = animalRepository.findById(animalId);

        if (animal.isEmpty()) {
            throw new NotFoundAnimalException("Animal not found in base.");
        } else { //Optional<Treatment> treatments = animal.get().getTreatments().stream().filter(t -> t.getId().equals(treatmentId)).findFirst();
            try {
              return animal.get().getTreatments().stream().filter(t -> t.getId().equals(treatmentId)).findFirst().get();

            } catch (Exception e) {
                throw new NotFoundAnimalException("Treatment not found.");
            }
        }
    }

    public Treatment saveTreatment(Long animalId, Treatment treatment) {
        try {
            Optional<Animal> animal = animalRepository.findById(animalId);

            if (animal.isEmpty()) {
                throw new NotFoundAnimalException("Animal with ID: " + animalId.toString() + " not found in your base.");
            }

            treatment.setAnimal(animal.get());
            return treatmentRepository.save(treatment);
        } catch (Exception e) {
            throw new CustomExceptionMessage("Treatment with this id already exist in base.");
        }
    }
}
