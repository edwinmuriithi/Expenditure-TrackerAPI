package com.example.expendituretrackerapi.controllers;

import com.example.expendituretrackerapi.entities.Income;
import com.example.expendituretrackerapi.entities.dto.IncomeDTO;
import com.example.expendituretrackerapi.exception.IncomeNotFoundException;
import com.example.expendituretrackerapi.repositories.IncomeRepository;
import com.example.expendituretrackerapi.services.IncomeService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(IncomeController.class)
class IncomeControllerTest extends AbstractTest{
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private IncomeService incomeService;
    @MockBean
    private IncomeRepository incomeRepositoryS;
    @Mock
    private ModelMapper modelMapper;
    @Autowired
    private ObjectMapper objectMapper;

    @InjectMocks
    private IncomeController incomeController;

    @BeforeEach
    public void setup() throws Exception {
        Income income = new Income();
        income.setIncome(10000);
        income.setBudget(2000);
        income.setCreatedDate(LocalDate.now());
        income.setId(1L);
        System.out.println("This is " + income.toString());
        when(incomeService.createIncome(income)).thenReturn(income);
        when(incomeService.findById(1L)).thenReturn(income);
        when(incomeService.findById(2L)).thenThrow(new IncomeNotFoundException("Campaign not found with ID " + 2L));

    }

        @Test
        @DisplayName("Create Income test")
        public void createIncomeTest() throws Exception{
            // Arrange
            IncomeDTO incomeDTO = new IncomeDTO();
            Income income = new Income();
            when(modelMapper.map(incomeDTO, Income.class)).thenReturn(income);
            when(incomeService.createIncome(any(Income.class))).thenReturn(income);
            when(modelMapper.map(income, IncomeDTO.class)).thenReturn(incomeDTO);

            // Act
            MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/income")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(new ObjectMapper().writeValueAsString(incomeDTO)))
                    .andExpect(MockMvcResultMatchers.status().isCreated())
                    .andReturn();

            // Assert
            String responseContent = mvcResult.getResponse().getContentAsString();
            IncomeDTO incomeResponse = new ObjectMapper().readValue(responseContent, IncomeDTO.class);
            assertNotNull(incomeResponse);
        }

//    @Test
//    @DisplayName("Should return a List of Incomes")
//    public void viewAllIncomeTest() throws Exception {
//        // Arrange
//        List<Income> incomes = new ArrayList<>();
//        incomes.add(new Income());
//        when(incomeService.viewIncome()).thenReturn(incomes);
//        List<IncomeDTO> incomeDTOs = new ArrayList<>();
//        incomeDTOs.add(new IncomeDTO());
//        incomeDTOs.add(new IncomeDTO());
//        when(modelMapper.map(any(), any())).thenReturn(new IncomeDTO());
//        when(modelMapper.map(incomes.get(0), IncomeDTO.class)).thenReturn(incomeDTOs.get(0));
//        when(modelMapper.map(incomes.get(1), IncomeDTO.class)).thenReturn(incomeDTOs.get(1));
//
//        // Act
//        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/income"))
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
//                .andReturn();
//
//        // Assert
//        String responseContent = mvcResult.getResponse().getContentAsString();
//        List<IncomeDTO> actualIncomeDTOs = new ObjectMapper().readValue(responseContent, new TypeReference<List<IncomeDTO>>() {
//        });
//        assertNotNull(actualIncomeDTOs);
//        assertEquals(incomeDTOs.size(), actualIncomeDTOs.size());
//    }
        @Test
        @DisplayName("View Income by ID success")
        void viewIncomeByIdTest() throws Exception{
            MockHttpServletRequestBuilder requestBuilder = get("/income/1")
                    .contentType(MediaType.APPLICATION_JSON);
            ResultActions resultActions = mockMvc.perform(requestBuilder).andDo(print()).andExpect(status().is2xxSuccessful());
            MvcResult response =
                    resultActions.andExpect(content().contentType(MediaType.APPLICATION_JSON)).andReturn();

            MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/income/1")
                    .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        }
    @Test
    @DisplayName("Update Income by ID")
    public void shouldUpdateIncomeById() throws Exception {
        // Arrange
        Long incomeId = 1L;
        IncomeDTO incomeDTO = new IncomeDTO();
        incomeDTO.setIncome(10000);
        incomeDTO.setBudget(5000);

        Income income = new Income();
        income.setId(incomeId);
        income.setIncome(15000);
        income.setBudget(7000);

        Mockito.when(incomeService.updateIncomeById(Mockito.any(Income.class), Mockito.anyLong()))
                .thenReturn((income));

        mockMvc.perform(MockMvcRequestBuilders.put("/income/1")
                        .content(asJsonString(incomeDTO))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1L))
                .andExpect(MockMvcResultMatchers.jsonPath("$.income").value(15000))
                .andExpect(MockMvcResultMatchers.jsonPath("$.budget").value(7000));
    }

    private static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @Test
    @DisplayName("ID not found when Updating by ID Test")
    public void shouldThrowIncomeNotFoundExceptionWhenUpdatingNonExistingIncome() throws Exception {
        // Arrange
        Long nonExistingIncomeId = 100L;
        IncomeDTO incomeDTO = new IncomeDTO();
        incomeDTO.setIncome(10000);
        incomeDTO.setBudget(5000);
        incomeDTO.setCreatedDate(LocalDate.now());

        given(incomeService.updateIncomeById(any(Income.class), eq(nonExistingIncomeId)))
                .willThrow(new IncomeNotFoundException("Income not found"));

        // Act
        MvcResult mvcResult = mockMvc.perform(
                        put("/incomes/{incomeId}", nonExistingIncomeId)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(incomeDTO)))
                .andExpect(status().isNotFound())
                .andReturn();

    }
        @Test
    @DisplayName("Delete Income by ID test")
    void deleteIncomeById() throws Exception {
        // given
        doNothing().when(incomeService).deleteIncomeById(1L);

        // when & then
        mockMvc.perform(delete("/income/" + 1L))
                .andExpect(status().isOk());

        verify(incomeService).deleteIncomeById(1L);
    }

    }

