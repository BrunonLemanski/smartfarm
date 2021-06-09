package com.lemanski.SmartFarm.service;

import com.lemanski.SmartFarm.exception.CustomExceptionMessage;
import com.lemanski.SmartFarm.exception.NotFoundAnimalException;
import com.lemanski.SmartFarm.model.database.Animal;
import com.lemanski.SmartFarm.model.database.Treatment;
import com.lemanski.SmartFarm.repository.AnimalRepository;
import com.lemanski.SmartFarm.repository.TreatmentRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Optional;

import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class TreatmentServiceTests {

    @TestConfiguration
    static class TreatmentServiceImplTestContextConfiguration {

        @Bean
        public TreatmentService treatmentService(){
            return new TreatmentService();
        }
    }

    @Autowired
    private TreatmentService treatmentService;

    @MockBean
    private TreatmentRepository treatmentRepository;

    @MockBean
    private AnimalRepository animalRepository;

    @Before
    public void setUp() {
        Animal animal = new Animal();
        animal.setId(1L);
        animal.setName("Test");
        animal.setIdPassport("0921312515");
        animal.setOriginCountry("PL");
        animal.setRace("Charolaise");
        animal.setBirthDate(LocalDate.of(2019, 05, 21));
        animal.setGender("XY");
        animal.setPurchaseCost(1400.0);
        animal.setPurchaseDate(LocalDate.of(2019, 07, 01));

        Treatment treatment = new Treatment();
        treatment.setId(1L);
        treatment.setMedicineName("NazwaLekarstwa");
        treatment.setComment("Sredni stan, podawac przez 2 tygodnie");
        treatment.setDisease("Zapalenie płuc");
        treatment.setTreatmentDate(LocalDate.of(2020, 01, 05));
        treatment.setAnimal(animal);

        animal.setTreatments(Arrays.asList(treatment));

        when(animalRepository.findById(1L))
                .thenReturn(Optional.of(animal));

        when(treatmentRepository.findById(1L))
                .thenReturn(Optional.of(treatment));
    }

    @Test(expected = CustomExceptionMessage.class)
    public void shouldThrownCustomExceptionMessage_whenGetAllTreatment() {
        treatmentService.getAllTreatments();
    }

    @Test(expected = NotFoundAnimalException.class)
    public void shouldThrownNotFoundAnimalException_whenGetTreatment() {
        treatmentService.getTreatment(2L, 2L);
    }

    @Test
    public void givenTreatment_whenGetTreatmentById() {
        String disease = "Zapalenie płuc";
        Treatment treatment = treatmentService.getTreatment(1L, 1L);

        Assert.assertEquals(treatment.getDisease(), disease);
    }

    @Test
    public void givenTreatment_whenCreateTreatment() {
        Animal animal = new Animal();
        animal.setId(1L);
        animal.setName("Test");
        animal.setIdPassport("0921312515");
        animal.setOriginCountry("PL");
        animal.setRace("Charolaise");
        animal.setBirthDate(LocalDate.of(2019, 05, 21));
        animal.setGender("XY");
        animal.setPurchaseCost(1400.0);
        animal.setPurchaseDate(LocalDate.of(2019, 07, 01));

        Treatment treatment = new Treatment();
        treatment.setId(1L);
        treatment.setMedicineName("NazwaLekarstwa");
        treatment.setComment("Sredni stan, podawac przez 2 tygodnie");
        treatment.setDisease("Zapalenie płuc");
        treatment.setTreatmentDate(LocalDate.of(2020, 01, 05));
        treatment.setAnimal(animal);

        animal.setTreatments(Arrays.asList(treatment));

        treatmentService.saveTreatment(1L, treatment);
        Treatment treatment1 = treatmentService.getTreatment(1L, 1L);
        Assert.assertEquals(treatment1.getDisease(), treatment.getDisease());
    }
}
