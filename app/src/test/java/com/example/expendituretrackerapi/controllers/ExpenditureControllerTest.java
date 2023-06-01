package com.example.expendituretrackerapi.controllers;

import com.example.expendituretrackerapi.entities.Expenditure;
import com.example.expendituretrackerapi.entities.Income;
import com.example.expendituretrackerapi.entities.dto.ExpenditureDTO;
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
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
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
    @MockBean
    public IncomeRepository incomeRepository;
    @Mock
    private ModelMapper modelMapper;
    @Autowired
    private ObjectMapper objectMapper;

    @InjectMocks
    private ExpenditureController expenditureController;

    @BeforeEach
    public void setup() throws Exception {
        Expenditure expenditure = new Expenditure();
        expenditure.setId(2L);
        expenditure.setRent(10000);
        expenditure.setFood(5000);
        expenditure.setTransport(3000);
        expenditure.setHealth(3000);
        expenditure.setSchoolFee(20000);
        expenditure.setShopping(2000);
        expenditure.setShopping(2000);
        expenditure.setEntertainment(3000);
        expenditure.setIncome(new Income(1L,200000,100000, LocalDate.now()));
        expenditure.setCreatedDate(LocalDate.now());
        System.out.println("This is " + expenditure.toString());
        when(expenditureService.createExpenditure(expenditure, 1L)).thenReturn(expenditure);
        System.out.println("This is "+ expenditure.toString());
        when(expenditureService.createExpenditure(expenditure,1L)).thenReturn(expenditure);
        when(expenditureService.findById(2L)).thenReturn(expenditure);
        when(expenditureService.findById(3L)).thenThrow(new ExpenditureNotFoundException("Expenditure not found with ID " + 3L));

    }

    @Test
    public void createExpenditure()throws Exception{
//        ExpenditureDTO expenditureDTO = new ExpenditureDTO();
//        Expenditure expenditure = new Expenditure();
//        when(modelMapper.map(expenditureDTO, Expenditure.class)).thenReturn(expenditure);
//        when(expenditureService.createExpenditure(any(Expenditure.class),eq(1L))).thenReturn(expenditure);
//        when(modelMapper.map(expenditure, ExpenditureDTO.class)).thenReturn(expenditureDTO);
//
//        // Act
//        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/expenditure")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(new ObjectMapper().writeValueAsString(expenditureDTO)))
//                .andExpect(MockMvcResultMatchers.status().isCreated())
//                .andReturn();
//
//        // Assert
//        String responseContent = mvcResult.getResponse().getContentAsString();
//        ExpenditureDTO expenditureResponse = new ObjectMapper().readValue(responseContent, ExpenditureDTO.class);
//        assertNotNull(expenditureResponse);

    }



    @Test
    void createExpenditureSuccess() throws Exception{

    }
}
