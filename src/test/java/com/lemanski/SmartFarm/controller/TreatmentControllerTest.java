package com.lemanski.SmartFarm.controller;

import com.lemanski.SmartFarm.exception.CustomExceptionMessage;
import com.lemanski.SmartFarm.model.AnimalType;
import com.lemanski.SmartFarm.model.database.Animal;
import com.lemanski.SmartFarm.model.database.Treatment;
import com.lemanski.SmartFarm.service.TreatmentService;
import com.lemanski.SmartFarm.service.ValidationErrorService;
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
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.given;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(TreatmentController.class)
public class TreatmentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @MockBean
    private TreatmentService treatmentService;

    @MockBean
    private ValidationErrorService errorService;

    @Before
    public void setUp() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void givenTreatments_whenGetAllTreatments_thenStatus200andJsonArray() throws Exception {
        Treatment treatment = createTreatment("Penicylina");
        Treatment treatment1 = createTreatment("Lek");

        List<Treatment> treatments = Arrays.asList(treatment, treatment1);

        given(treatmentService.getAllTreatments()).willReturn(treatments);

        mockMvc.perform(get("/treatments")
            .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].medicineName", is("Penicylina")));
    }

    @Test
    public void givenErrorMessage_whenGetAllTreatments_thenStatus404() throws Exception {
        when(treatmentService.getAllTreatments()).thenThrow(new CustomExceptionMessage("There is no treatments in your base."));

        mockMvc.perform(get("/treatments")
            .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(mvcResult -> Assert.assertTrue(mvcResult.getResolvedException() instanceof CustomExceptionMessage))
                .andExpect(mvcResult -> Assert.assertEquals("There is no treatments in your base.", mvcResult.getResolvedException().getMessage()));
    }

    @Test
    public void givenTreatment_whenGetTreatmentForAnimal_thenStatus200andObject() throws Exception {
        Treatment treatment = createTreatment("Lek");

        given(treatmentService.getTreatment(1L, 1L)).willReturn(treatment);

        mockMvc.perform(get("/animals/treatment?animalId=1&treatmentId=1")
            .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.medicineName", is("Lek")));
    }

    private Treatment createTreatment(String medicineName) {
        Animal animal = new Animal();
        animal.setId(1L);
        animal.setName("Hufky");
        animal.setType(AnimalType.COW);
        animal.setIdPassport("0921312515");
        animal.setOriginCountry("PL");
        animal.setRace("Charolaise");
        animal.setBirthDate(LocalDate.of(2019, 05, 21));
        animal.setGender("XY");
        animal.setPurchaseCost(1400.0);
        animal.setPurchaseDate(LocalDate.of(2019, 07, 01));

        Treatment treatment = new Treatment();
        treatment.setId(1L);
        treatment.setMedicineName(medicineName);
        treatment.setComment("Comment Testing TreatmentController");
        treatment.setDisease("Example Disease");
        treatment.setTreatmentDate(LocalDate.now());
        treatment.setAnimal(animal);

        return treatment;
    }
}