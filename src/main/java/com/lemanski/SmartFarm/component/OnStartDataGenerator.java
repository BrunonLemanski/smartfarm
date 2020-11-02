package com.lemanski.SmartFarm.component;

import com.lemanski.SmartFarm.model.database.Animal;
import com.lemanski.SmartFarm.model.AnimalType;
import com.lemanski.SmartFarm.model.database.Treatment;
import com.lemanski.SmartFarm.repository.AnimalRepository;
import com.lemanski.SmartFarm.repository.TreatmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class OnStartDataGenerator {

    private AnimalRepository animalRepository;
    private TreatmentRepository treatmentRepository;

    @Autowired
    public OnStartDataGenerator(AnimalRepository animalRepository, TreatmentRepository treatmentRepository) {
        this.animalRepository = animalRepository;
        this.treatmentRepository = treatmentRepository;

        Animal animal = new Animal();
        animal.setName("Krasna");
        animal.setIdPassport("0921312515");
        animal.setOriginCountry("PL");
        animal.setRace("Charolaise");
        animal.setBirthDate(LocalDate.of(2019, 05, 21));
        animal.setGender("XY");
        animal.setPurchaseCost(1400.0);
        animal.setPurchaseDate(LocalDate.of(2019, 07, 01));
        animal.setType(AnimalType.COW);

        Animal animal1 = new Animal();
        animal1.setName("Krasna1");
        animal1.setIdPassport("0921312525");
        animal1.setOriginCountry("PL");
        animal1.setRace("Charolaise");
        animal1.setBirthDate(LocalDate.of(2019, 05, 21));
        animal1.setGender("XY");
        animal1.setPurchaseCost(1400.0);
        animal1.setPurchaseDate(LocalDate.of(2019, 07, 01));
        animal1.setType(AnimalType.COW);

        Animal animal2 = new Animal();
        animal2.setName("Ferdek");
        animal2.setIdPassport("0921312555");
        animal2.setOriginCountry("PL");
        animal2.setRace("Charolaise");
        animal2.setBirthDate(LocalDate.of(2019, 05, 21));
        animal2.setGender("XY");
        animal2.setPurchaseCost(1400.0);
        animal2.setPurchaseDate(LocalDate.of(2019, 07, 01));
        animal2.setType(AnimalType.BULL);

        Animal animal3 = new Animal();
        animal3.setName("Ferdek2");
        animal3.setIdPassport("0921312585");
        animal3.setOriginCountry("PL");
        animal3.setRace("Charolaise");
        animal3.setBirthDate(LocalDate.of(2019, 05, 21));
        animal3.setGender("XY");
        animal3.setPurchaseCost(1400.0);
        animal3.setPurchaseDate(LocalDate.of(2019, 07, 01));
        animal3.setType(AnimalType.CALF);

        Treatment treatment = new Treatment();
        treatment.setMedicineName("NazwaLekarstwa");
        treatment.setComment("Sredni stan, podawac przez 2 tygodnie");
        treatment.setDisease("Zapalenie płuc");
        treatment.setTreatmentDate(LocalDate.of(2020, 01, 05));
        treatment.setAnimal(animal);

        Treatment treatment1 = new Treatment();
        treatment1.setMedicineName("NazwaLekarstwa");
        treatment1.setComment("Sredni stan, podawac przez 2 tygodnie");
        treatment1.setDisease("Zapalenie płuc");
        treatment1.setTreatmentDate(LocalDate.of(2020, 01, 05));
        treatment1.setAnimal(animal);

        Treatment treatment2 = new Treatment();
        treatment2.setMedicineName("NazwaLekarstwa");
        treatment2.setComment("Sredni stan, podawac przez 2 tygodnie");
        treatment2.setDisease("Zapalenie płuc");
        treatment2.setTreatmentDate(LocalDate.of(2020, 01, 05));
        treatment2.setAnimal(animal1);

        Treatment treatment3 = new Treatment();
        treatment3.setMedicineName("NazwaLekarstwa");
        treatment3.setComment("Sredni stan, podawac przez 2 tygodnie");
        treatment3.setDisease("Zapalenie płuc");
        treatment3.setTreatmentDate(LocalDate.of(2020, 01, 05));
        treatment3.setAnimal(animal2);

        //animal.setTreatments(Stream.of(cure -> cure.));

        animalRepository.save(animal);
        animalRepository.save(animal1);
        animalRepository.save(animal2);
        animalRepository.save(animal3);

        treatmentRepository.save(treatment);
        treatmentRepository.save(treatment1);
        treatmentRepository.save(treatment2);
        treatmentRepository.save(treatment3);
    }
}
