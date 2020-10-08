package com.lemanski.SmartFarm.service;

import com.lemanski.SmartFarm.exception.CustomExceptionMessage;
import com.lemanski.SmartFarm.model.database.Animal;
import com.lemanski.SmartFarm.model.AnimalType;
import com.lemanski.SmartFarm.repository.AnimalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
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
}
