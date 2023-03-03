package com.example.expendituretrackerapi.controllers;

import com.example.expendituretrackerapi.entities.Income;
import com.example.expendituretrackerapi.exception.IncomeNotFoundException;
import com.example.expendituretrackerapi.services.IncomeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(IncomeController.class)
class IncomeControllerTest extends AbstractTest{
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private IncomeService incomeService;

    @BeforeEach
    public void setup() throws Exception {
        Income income = new Income();
        income.setIncome(10000);
        income.setBudget(2000);
        income.setCreatedDate(LocalDate.now());
        income.setId(1L);
        System.out.println("This is " + income.toString());
        when(incomeService.findById(1L)).thenReturn(income);
        when(incomeService.findById(2L)).thenThrow(new IncomeNotFoundException("Campaign not found with ID " + 2L));

    }
        @Test
        @DisplayName("View Income by ID success")
        void viewIncomeByIdSuccessTest() throws Exception{
            MockHttpServletRequestBuilder requestBuilder = get("/income/1")
                    .contentType(MediaType.APPLICATION_JSON);
            ResultActions resultActions = mockMvc.perform(requestBuilder).andDo(print()).andExpect(status().is2xxSuccessful());
            MvcResult response =
                    resultActions.andExpect(content().contentType(MediaType.APPLICATION_JSON)).andReturn();

            MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/income/1")
                    .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        }

    }

