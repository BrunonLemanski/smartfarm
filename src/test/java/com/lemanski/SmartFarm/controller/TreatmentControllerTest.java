package com.lemanski.SmartFarm.controller;

import com.lemanski.SmartFarm.model.AnimalType;
import com.lemanski.SmartFarm.model.database.Animal;
import com.lemanski.SmartFarm.model.database.Treatment;
import com.lemanski.SmartFarm.service.TreatmentService;
import com.lemanski.SmartFarm.service.ValidationErrorService;
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

    private Treatment createTreatment(String medicineName) {
        Animal animal = new Animal();
        animal.setName("Hufky");
        animal.setType(AnimalType.COW);
        animal.setIdPassport("0921312515");
        animal.setOriginCountry("PL");
        animal.setRace("Charolaise");
        animal.setBirthDate(LocalDateTime.of(2019, 05, 21, 11, 56));
        animal.setGender("XY");
        animal.setPurchaseCost(1400.0);
        animal.setPurchaseDate(LocalDateTime.of(2019, 07, 01, 11, 23));


        Treatment treatment = new Treatment();
        treatment.setMedicineName(medicineName);
        treatment.setComment("Comment Testing TreatmentController");
        treatment.setDisease("Example Disease");
        treatment.setTreatmentDate(LocalDate.now());
        treatment.setAnimal(animal);

        return treatment;
    }
}