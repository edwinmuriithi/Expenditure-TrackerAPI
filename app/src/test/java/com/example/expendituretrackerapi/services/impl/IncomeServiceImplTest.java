//package com.example.expendituretrackerapi.services.impl;
//
//import com.example.expendituretrackerapi.entities.Income;
//import com.example.expendituretrackerapi.exception.IncomeNotFoundException;
//import com.example.expendituretrackerapi.repositories.IncomeRepository;
//import com.example.expendituretrackerapi.services.IncomeService;
//import org.junit.jupiter.api.Test;
//import org.mockito.Mock;
//import org.springframework.boot.test.context.SpringBootTest;
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.Mockito.*;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.junit.runner.RunWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.MockitoJUnitRunner;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import java.time.LocalDate;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Optional;
//
//import static org.assertj.core.api.Assertions.assertThat;
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.BDDMockito.given;
//import static org.mockito.Mockito.when;
//import static org.springframework.test.web.client.ExpectedCount.times;
//
//@RunWith(SpringRunner.class)
//@SpringBootTest
//class IncomeServiceImplTest {
//    @Mock
//    private IncomeRepository incomeRepository;
//
//    @InjectMocks
//    private IncomeServiceImpl incomeService;
//    private Income income;
//
//    @BeforeEach
//    public void setUp(){
//        income = Income.builder()
//                .id(1L)
//                .income(50000)
//                .budget(2000)
//                .createdDate(LocalDate.now())
//                .build();
//    }
//
//    @Test
//    @DisplayName("Create Income Test")
//    public void testCreateIncome() throws IncomeNotFoundException {
//        Income income = new Income();
//        income.setIncome(5000);
//        income.setBudget(4000);
//        income.setCreatedDate(LocalDate.now());
//
//        when(incomeRepository.save(income)).thenReturn(income);
//
//        Income createdIncome = incomeService.createIncome(income);
//
//        assertNotNull(createdIncome);
//        assertEquals(income.getIncome(), createdIncome.getIncome());
//        assertEquals(income.getBudget(), createdIncome.getBudget());
//        assertEquals(income.getCreatedDate(), createdIncome.getCreatedDate());
//    }
//    @Test
//    @DisplayName("Test when Income is empty")
//    void viewIncome_whenIncomeIsEmpty_throwsIncomeNotFoundException() {
//        // given
//        List<Income> incomeList = new ArrayList<>();
//
//        // when
//        when(incomeRepository.findAll()).thenReturn(incomeList);
//
//        // then
//        IncomeNotFoundException exception = assertThrows(IncomeNotFoundException.class, () -> incomeService.viewIncome());
//        assertEquals("There is no income which has been saved", exception.getMessage());
//    }
//
//    @Test
//    @DisplayName("Test when Income is populated")
//    void viewIncomeTest() throws IncomeNotFoundException {
//        // given
//        List<Income> incomeList = new ArrayList<>();
//        incomeList.add(new Income(1L,50000,2000,LocalDate.now()));
//        Income created = incomeService.createIncome(income);
//        // when
//        when(incomeRepository.findAll()).thenReturn(incomeList);
//
//        // then
//        List<Income> retrievedIncomeList = incomeService.viewIncome();
//        assertEquals(incomeList, retrievedIncomeList);
//    }
//
//    @Test
//    @DisplayName("Find income by ID test")
//    void findIncomeById() throws IncomeNotFoundException{
//        Income created = incomeService.createIncome(income);
//        given(incomeRepository.findById(1L)).willReturn(Optional.of(income));
//        Income savedIncome = incomeService.findById(income.getId());
//        assertThat(savedIncome).isNotNull();
//    }
//    @Test
//    @DisplayName("Update Income By ID Test")
//    public void testUpdateIncomeById() throws IncomeNotFoundException {
//        // create a mock Income object
//        Income income = new Income();
//        income.setId(1L);
//        income.setIncome(50000);
//        income.setBudget(20000);
//        income.setCreatedDate(LocalDate.now());
//
//        // create a mock existing Income object
//        Income existingIncome = new Income();
//        existingIncome.setId(1L);
//        existingIncome.setIncome(40000);
//        existingIncome.setBudget(3000);
//        existingIncome.setCreatedDate(LocalDate.now());
//
//        // mock the behavior of the incomeRepository
//        when(incomeRepository.findById(1L)).thenReturn(Optional.of(existingIncome));
//        when(incomeRepository.save(existingIncome)).thenReturn(existingIncome);
//
//        // call the service method
//        Income updatedIncome = incomeService.updateIncomeById(income, 1L);
//
//
//        // verify that the returned Income object is the same as the existing Income object
//        assertEquals(existingIncome.getId(), updatedIncome.getId());
//        assertEquals(income.getIncome(), updatedIncome.getIncome());
//        assertEquals(income.getBudget(), updatedIncome.getBudget());
//    }
//
//    @Test
//    @DisplayName("Delete Income By ID")
//    public void testDeleteIncomeById() throws IncomeNotFoundException {
//        Long incomeId = 1L;
//
//        // Set up mock behavior
//        when(incomeRepository.findById(incomeId))
//                .thenReturn(Optional.of(new Income(incomeId, 1000,500,LocalDate.now())));
//
//        // Call the service method
//        incomeService.deleteIncomeById(incomeId);
//    }
//}