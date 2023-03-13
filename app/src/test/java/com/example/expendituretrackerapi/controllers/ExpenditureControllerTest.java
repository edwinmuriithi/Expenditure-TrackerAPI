package com.example.expendituretrackerapi.controllers;

import com.example.expendituretrackerapi.entities.Expenditure;
import com.example.expendituretrackerapi.entities.Income;
import com.example.expendituretrackerapi.entities.dto.ExpenditureDTO;
import com.example.expendituretrackerapi.entities.dto.IncomeDTO;
import com.example.expendituretrackerapi.exception.ExpenditureNotFoundException;
import com.example.expendituretrackerapi.repositories.ExpenditureRepository;
import com.example.expendituretrackerapi.services.ExpenditureService;
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

import java.time.LocalDate;
import java.util.Date;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@WebMvcTest(ExpenditureController.class)
class ExpenditureControllerTest {
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
    public void setup() throws Exception{
       Expenditure expenditure = new Expenditure();
       expenditure.setId(1L);
       expenditure.setHealth(50000);
       expenditure.setFood(20000);
       expenditure.setRent(10000);
       expenditure.setEntertainment(2000);
       expenditure.setShopping(30000);
       expenditure.setTransport(1000);
       expenditure.setSchoolFee(60000);
       expenditure.setIncome(new Income());
       expenditure.setCreatedDate(LocalDate.now());
       when(expenditureService.findById(1L)).thenReturn(expenditure);
       when(expenditureService.findById(2L)).thenThrow(new ExpenditureNotFoundException("expenditure not found"));
   }
@Test
    public void createExpenditure(){
    ExpenditureDTO expenditureDTO = new ExpenditureDTO();
    Expenditure expenditure = new Expenditure();
    when(modelMapper.map(expenditureDTO, Expenditure.class)).thenReturn(expenditure);
    //when(expenditureService.createExpenditure(any(Expenditure.class))).thenReturn(expenditure);
    when(modelMapper.map(expenditure, ExpenditureDTO.class)).thenReturn(expenditureDTO);


}

}