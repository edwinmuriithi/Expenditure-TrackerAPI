package com.example.expendituretrackerapi.controllers;

import com.example.expendituretrackerapi.entities.Expenditure;
import com.example.expendituretrackerapi.exception.ExpenditureNotFoundException;
import com.example.expendituretrackerapi.repositories.ExpenditureRepository;
import com.example.expendituretrackerapi.repositories.IncomeRepository;
import com.example.expendituretrackerapi.services.ExpenditureService;
import com.example.expendituretrackerapi.services.IncomeService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@WebMvcTest(ExpenditureController.class)
class ExpenditureControllerTest extends AbstractTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private ExpenditureService expenditureService;
    @MockBean
    private ExpenditureRepository expenditureRepository;
    @Mock
    private ModelMapper modelMapper;
    @Autowired
    private ObjectMapper objectMapper;

    @InjectMocks
    private ExpenditureController expenditureController;

    @BeforeEach
    public void setup() throws Exception {
        Expenditure expenditure = new Expenditure();
        expenditure.setRent(10000);
        expenditure.setFood(5000);
        expenditure.setTransport(3000);
        expenditure.setHealth(3000);
        expenditure.setSchoolFee(20000);
        expenditure.setShopping(2000);
        expenditure.setShopping(2000);
        expenditure.setEntertainment(3000);
        System.out.println("This is " + expenditure.toString());
        when(expenditureService.createExpenditure(expenditure, 1L)).thenReturn(expenditure);
        when(expenditureService.findById(2L)).thenReturn(expenditure);
        when(expenditureService.findById(3L)).thenThrow(new ExpenditureNotFoundException("Expenditure not found with ID " + 3L));

    }

}