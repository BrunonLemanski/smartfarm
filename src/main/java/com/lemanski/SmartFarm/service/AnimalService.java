package com.lemanski.SmartFarm.service;

import com.lemanski.SmartFarm.exception.CustomExceptionMessage;
import com.lemanski.SmartFarm.model.database.Animal;
import com.lemanski.SmartFarm.model.AnimalType;
import com.lemanski.SmartFarm.repository.AnimalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AnimalService {

    @Autowired
    private AnimalRepository animalRepository;

    public List getAllAnimals() {
        List<Animal> animals = animalRepository.findAll();

        if (animals.isEmpty()) {
            throw new CustomExceptionMessage("There are no animals in your base.");
        } else {
            Map<AnimalType, Long> animalTypeCounter = animals.stream().collect(Collectors.groupingBy(Animal::getType, Collectors.counting()));

            return Arrays.asList(animals, animalTypeCounter);
        }
    }

    public Optional<Animal> getAnimal(Long id) {
        Optional<Animal> animal = animalRepository.findById(id);

        if (animal.isPresent()) {
            return animal;
        } else {
            throw new CustomExceptionMessage("There is no animal with id: " + id + ".");
        }
    }

    public Animal saveAnimal(Animal animal, Optional<Long> animalId) {
        try {
            if(animal.getType().equals(AnimalType.BULL)
                    || animal.getType().equals(AnimalType.COW)
                    || animal.getType().equals(AnimalType.CALF)){

                animalId.ifPresent(animal::setId);

                if(animalId.isPresent()) {
                    LocalDateTime createdOn = animalRepository.findById(animalId.get()).get().getCreatedOn();
                    animal.setCreatedOn(createdOn);
                }

                return animalRepository.save(animal);
            } else {
                throw new CustomExceptionMessage("Invalid animal type on input. Please use only COW, CALF or BULL.");
            }
        } catch (Exception e) {
            throw new CustomExceptionMessage("Animal with this ID already exist in your base.");
        }
    }

    public void deleteAnimal(Long id) {
        try{
            animalRepository.deleteById(id);
        } catch (Exception e) {
            throw new CustomExceptionMessage("Animal does not exist in database.");
        }
    }
}
