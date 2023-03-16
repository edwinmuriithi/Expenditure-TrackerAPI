package com.example.expendituretrackerapi.controllers;

import com.example.expendituretrackerapi.entities.Expenditure;
import com.example.expendituretrackerapi.entities.Income;
import com.example.expendituretrackerapi.exception.ExpenditureNotFoundException;
import com.example.expendituretrackerapi.repositories.ExpenditureRepository;
import com.example.expendituretrackerapi.services.ExpenditureService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.LocalDate;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ExpenditureController.class)
public class ExpenditureControllerTest extends AbstractTest{
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private ExpenditureRepository expenditureRepository;
    @MockBean
    private ExpenditureService expenditureService;
    @Autowired
    private ObjectMapper objectMapper;
    @InjectMocks
    private ExpenditureController expenditureController;

    @BeforeEach
    public void setUp() {
        Expenditure expenditure = new Expenditure();
        expenditure.setId(1L);
        expenditure.setHealth(20000);
        expenditure.setCreatedDate(LocalDate.now());
        expenditure.setFood(5000);
        expenditure.setEntertainment(2000);
        expenditure.setRent(6500);
        expenditure.setShopping(4000);
        expenditure.setTransport(5000);
        expenditure.setIncome(new Income());
        when(expenditureService.findById(1L)).thenReturn(expenditure);
        when(expenditureService.findById(2L)).thenThrow(new ExpenditureNotFoundException("expenditure with this id is not found"));

    }

    @Test
   public void createExpenditure() throws Exception {
        Expenditure expenditure = new Expenditure(1L,2000,30000,40000,10000,20000,3000,2000,new Income(2L,500000,200000, LocalDate.now()),LocalDate.now());
        mockMvc.perform(MockMvcRequestBuilders.post("/expenditure").contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(expenditure)))
                .andExpect(status().isCreated())
                .andDo(print());
    }
}