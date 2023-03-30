package com.example.expendituretrackerapi.controllers;

import com.example.expendituretrackerapi.entities.Expenditure;
import com.example.expendituretrackerapi.entities.Income;
import com.example.expendituretrackerapi.entities.dto.ExpenditureDTO;
import com.example.expendituretrackerapi.exception.ExpenditureNotFoundException;
import com.example.expendituretrackerapi.repositories.ExpenditureRepository;
import com.example.expendituretrackerapi.repositories.IncomeRepository;
import com.example.expendituretrackerapi.services.ExpenditureService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDate;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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
    @MockBean
    private ModelMapper modelMapper;
    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
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
        when(expenditureService.findById(2L)).thenReturn(expenditure);
        when(expenditureService.findById(3L)).thenThrow(new ExpenditureNotFoundException("Expenditure not found with ID " + 3L));

    }
    @Test
    public void createExpenditure()throws Exception{
//        ExpenditureDTO expenditureDTO = new ExpenditureDTO();
//        Long incomeId = 1L;
//        Expenditure expenditure = new Expenditure();
//        expenditure.setId(2L);
//        expenditure.setHealth(1000);
//        expenditure.setFood(3000);
//        expenditure.setCreatedDate(LocalDate.now());
//        expenditure.setShopping(4000);
//        expenditure.setRent(5000);
//        expenditure.setEntertainment(1000);
//        expenditure.setSchoolFee(60000);
//        expenditure.setTransport(3000);
//        expenditure.setIncome(new Income(1L,300000,500000,LocalDate.now()));
//        when(expenditureService.createExpenditure(any(Expenditure.class),eq(incomeId))).thenReturn(expenditure);
//        //act
//        ResponseEntity<ExpenditureDTO>response = expenditureController.createExpenditure(expenditureDTO,incomeId);
//        //Assert
////        Mockito.verify(expenditureService,times(2)).createExpenditure(Mockito.any(Expenditure.class),eq(incomeId));
//        verify(expenditureService,times(1)).createExpenditure((Expenditure) Matchers.any(Expenditure.class),eq(incomeId));
//        assertEquals(HttpStatus.CREATED,response.getStatusCode());
//        assertEquals(expenditure.getId(), Objects.requireNonNull(response.getBody()).getId());
//        assertEquals(expenditure.getHealth(),response.getBody().getHealth());
//        assertEquals(expenditure.getFood(),response.getBody().getFood());
//        assertEquals(expenditure.getEntertainment(),response.getBody().getEntertainment());
//        assertEquals(expenditure.getRent(),response.getBody().getRent());
//        assertEquals(expenditure.getShopping(),response.getBody().getShopping());
//        assertEquals(expenditure.getTransport(),response.getBody().getTransport());
//        assertEquals(expenditure.getCreatedDate(),response.getBody().getCreatedDate());
//        assertEquals(expenditure.getSchoolFee(),response.getBody().getSchoolFee());
//        assertEquals(expenditure.getIncome(),response.getBody().getIncome());

    }
    @Test
    void createExpenditure_withInvalidInput_shouldThrowException() {
//        // Arrange
//        ExpenditureDTO expenditureDTO = new ExpenditureDTO();
//        Long incomeId = 1L;
//        when(expenditureService.createExpenditure(any(Expenditure.class), eq(incomeId))).thenReturn(null);
//
//        // Act & Assert
//        assertThrows(ExpenditureNotFoundException.class, () -> {
//            expenditureController.createExpenditure(expenditureDTO, incomeId);
//        });
    }

    @Test
    public void getAllExpenditure()throws Exception{

    }
    @Test
    public void getExpenditureById() throws Exception{
//        MockHttpServletRequestBuilder requestBuilder = get("/expenditure/2")
//                .contentType(MediaType.APPLICATION_JSON);
//        ResultActions resultActions = mockMvc.perform(requestBuilder).andDo(print()).andExpect(status().is2xxSuccessful());
//        MvcResult response =
//                resultActions.andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON)).andReturn();
//
//        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/expenditure/2")
//                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

    }
    @Test
    public void deleteExpenditureById() throws Exception{
        // given
        doNothing().when(expenditureService).deleteExpenditureById(2L);

        // when & then
        mockMvc.perform(delete("/expenditure/" + 2L))
                .andExpect(status().isOk());

        verify(expenditureService).deleteExpenditureById(2L);
    }

}

