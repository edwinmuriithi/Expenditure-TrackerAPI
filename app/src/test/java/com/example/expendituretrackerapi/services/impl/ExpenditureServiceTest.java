package com.example.expendituretrackerapi.services.impl;

import com.example.expendituretrackerapi.entities.Expenditure;
import com.example.expendituretrackerapi.entities.Income;
import com.example.expendituretrackerapi.repositories.ExpenditureRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;

@RunWith(JUnit4.class)
@ExtendWith(MockitoExtension.class)
class ExpenditureServiceTest {
    @Mock
    private ExpenditureRepository expenditureRepository;
    @InjectMocks
    private ExpenditureServiceImpl expenditureService;
    private Expenditure expenditure;
    private Income income;

    @BeforeEach
    void setUp() {
        expenditure = Expenditure.builder()
                .id(2L)
                .food(2000)
                .rent(3000)
                .health(5000)
                .entertainment(300)
                .schoolFee(5000)
                .transport(3000)
                .createdDate(LocalDate.now())
                .shopping(5000)
                .income(new Income())
                .build();
        income = Income.builder()
                .id(1L)
                .income(20000000)
                .budget(400000)
                .build();
    }
    @Test
    void createExpenditure(){
//        when(expenditureRepository.save(Mockito.any(Expenditure.class))).thenReturn(expenditure);
//        Expenditure savedExpenditures = expenditureService.createExpenditure(expenditure,1L);
//        assertThat(savedExpenditures).isNotNull();

    }
    @Test
    void viewAllExpenditures(){
        Expenditure expenditure1 = Expenditure.builder()
                .id(3L)
                .food(2000)
                .rent(3000)
                .health(5000)
                .entertainment(300)
                .schoolFee(5000)
                .transport(3000)
                .createdDate(LocalDate.now())
                .shopping(5000)
                .income(new Income())
                .build();

        given(expenditureRepository.findAll()).willReturn(List.of(expenditure,expenditure1));
        List<Expenditure>expenditureList = expenditureService.viewExpenditure();
        assertThat(expenditureList).isNotNull();
        assertThat(expenditureList.size()).isEqualTo(2);
    }
    @Test
    void getExpenditureById(){
        given(expenditureRepository.findById(2L)).willReturn(Optional.of(expenditure));
        Expenditure foundExpenditure = expenditureService.findById(expenditure.getId());
        assertThat(foundExpenditure).isNotNull();
    }
}