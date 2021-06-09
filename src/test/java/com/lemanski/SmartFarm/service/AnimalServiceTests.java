package com.lemanski.SmartFarm.service;

import com.lemanski.SmartFarm.exception.CustomExceptionMessage;
import com.lemanski.SmartFarm.model.AnimalType;
import com.lemanski.SmartFarm.model.database.Animal;
import com.lemanski.SmartFarm.repository.AnimalRepository;
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
import java.util.Optional;

import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
public class AnimalServiceTests {

    @TestConfiguration
    static class AnimalServiceImplTestContextConfiguration {

        @Bean
        public AnimalService animalService(){
            return new AnimalService();
        }
    }

    @Autowired
    private AnimalService animalService;

    @MockBean
    private AnimalRepository animalRepository;

    @Before
    public void setUp() {
        Animal animal = new Animal();
        animal.setName("Test");
        animal.setIdPassport("0921312515");
        animal.setOriginCountry("PL");
        animal.setRace("Charolaise");
        animal.setBirthDate(LocalDate.of(2019, 05, 21));
        animal.setGender("XY");
        animal.setPurchaseCost(1400.0);
        animal.setPurchaseDate(LocalDate.of(2019, 07, 01));

        Animal animal1 = new Animal();
        animal1.setName("Test");
        animal1.setIdPassport("0921312515");
        animal1.setOriginCountry("PL");
        animal1.setRace("Charolaise");
        animal1.setBirthDate(LocalDate.of(2019, 05, 21));
        animal1.setGender("XY");
        animal1.setPurchaseCost(1400.0);
        animal1.setPurchaseDate(LocalDate.of(2019, 07, 01));

        when(animalRepository.findById(1L))
                .thenReturn(Optional.of(animal));

        when(animalRepository.findById(2L))
                .thenReturn(Optional.of(animal1));

        animal.setName("New");
        when(animalRepository.save(animal))
                .thenReturn(animal);
    }

    @Test(expected = CustomExceptionMessage.class)
    public void shouldThrownCustomExceptionMessage_whenGetAnimal() {
        animalService.getAnimal(190L);
    }

    @Test
    public void shouldThrownCustomExceptionMessage_whenGetAllAnimals() {
        try{
            animalService.getAllAnimals();
        } catch (CustomExceptionMessage exception) {
            Assert.assertEquals("There are no animals in your base.", exception.getMessage());
        }
    }

    @Test
    public void givenAnimal_whenGetAnimalById() {
        String name  = "Test";
        Optional<Animal> animal = animalService.getAnimal(2L);

        Assert.assertEquals(animal.get().getName(), name);
    }

    @Test
    public void givenAnimal_whenCreateAnimal() {
        Animal animal = new Animal();
        animal.setName("New");
        animal.setIdPassport("0921312515");
        animal.setOriginCountry("PL");
        animal.setType(AnimalType.BULL);
        animal.setRace("Charolaise");
        animal.setBirthDate(LocalDate.of(2019, 05, 21));
        animal.setGender("XY");
        animal.setPurchaseCost(1400.0);
        animal.setPurchaseDate(LocalDate.of(2019, 07, 01));

        animalService.saveAnimal(animal, Optional.of(1L));
        Optional<Animal> createdAnimal = animalRepository.findById(1L);
        Assert.assertEquals(createdAnimal.get().getName(), animal.getName());
    }

    @Test
    public void givenOK_whenDeleteAnimal() {
        Animal animal = new Animal();
        animal.setName("Del");
        animal.setIdPassport("0921312515");
        animal.setOriginCountry("PL");
        animal.setType(AnimalType.BULL);
        animal.setRace("Charolaise");
        animal.setBirthDate(LocalDate.of(2019, 05, 21));
        animal.setGender("XY");
        animal.setPurchaseCost(1400.0);
        animal.setPurchaseDate(LocalDate.of(2019, 07, 01));

        animalRepository.save(animal);
        when(animalRepository.findById(1L)).thenReturn(Optional.of(animal));
        animalService.deleteAnimal(1L);
        verify(animalRepository, times(1)).deleteById(1L);
    }
}
