package com.lemanski.SmartFarm.controller;

import com.lemanski.SmartFarm.exception.CustomExceptionMessage;
import com.lemanski.SmartFarm.model.database.Animal;
import com.lemanski.SmartFarm.service.AnimalService;
import com.lemanski.SmartFarm.service.ValidationErrorService;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(AnimalController.class)
public class AnimalControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @MockBean
    private AnimalService animalService;

    @MockBean
    private ValidationErrorService errorService; // it's not used by required to run test


    @Before
    public void setUp() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void givenAnimals_whenGetAnimals_thenStatus200andJsonArray() throws Exception{

        Animal animal = createTestingData_Animal("Krasna");
        Animal animal1 = createTestingData_Animal("Krasna1");

        List<Animal> animals = Arrays.asList(animal, animal1);

        given(animalService.getAllAnimals()).willReturn(animals);

        mockMvc.perform(get("/animals")
            .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].name", Matchers.is("Krasna")));
    }

    @Test
    public void givenErrorMessage_whenGetAnimals_thenStatus404() throws Exception{

        when(animalService.getAllAnimals()).thenThrow(new CustomExceptionMessage("There are no animals in your base."));

        mockMvc.perform(get("/animals")
            .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(mvcResult -> Assert.assertTrue(mvcResult.getResolvedException() instanceof CustomExceptionMessage))
                .andExpect(mvcResult -> Assert.assertEquals("There are no animals in your base.", mvcResult.getResolvedException().getMessage()));
    }

    @Test
    public void givenAnimal_whenGetAnimal_thenStatus200andJsonArray() throws Exception {
        Animal animal = createTestingData_Animal("Mucka");

        given(animalService.getAnimal(1L)).willReturn(java.util.Optional.ofNullable(animal));

        mockMvc.perform(get("/animals/1")
            .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.name", Matchers.is("Mucka")));
    }

    @Test
    public void givenErrorMessage_whenGetAnimal_thenStatus404() throws Exception {
        when(animalService.getAnimal(5L)).thenThrow(new CustomExceptionMessage("There is no animal with id: 5."));

        mockMvc.perform(get("/animals/5")
            .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(mvcResult -> Assert.assertTrue(mvcResult.getResolvedException() instanceof CustomExceptionMessage))
                .andExpect(mvcResult -> Assert.assertEquals("There is no animal with id: 5.", mvcResult.getResolvedException().getMessage()));
    }

    private Animal createTestingData_Animal(String name){
        Animal animal = new Animal();
        animal.setName(name);
        animal.setIdPassport("0921312515");
        animal.setOriginCountry("PL");
        animal.setRace("Charolaise");
        animal.setBirthDate(LocalDate.of(2019, 05, 21));
        animal.setGender("XY");
        animal.setPurchaseCost(1400.0);
        animal.setPurchaseDate(LocalDate.of(2019, 07, 01));

        return animal;
    }
}
