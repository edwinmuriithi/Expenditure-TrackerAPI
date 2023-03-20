//package com.example.expendituretrackerapi.repositories;
//
//import com.example.expendituretrackerapi.entities.Income;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import java.time.LocalDate;
//import java.util.Optional;
//
//import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
//import static org.junit.jupiter.api.Assertions.*;
//
//@SpringBootTest
//class IncomeRepositoryTest {
//
//        @Autowired
//        private IncomeRepository incomeRepository;
//
//        @Test
//        @DisplayName("Tests findById Income")
//        public void findByIdIncome() {
//            // Given
//            Income income = new Income(1L, 50000,20000, LocalDate.now());
//            incomeRepository.save(income);
//
//            // When
//            Optional<Income> foundIncome = incomeRepository.findById(income.getId());
//
//            // Then
//            assertThat(foundIncome).isPresent();
//            assertThat(foundIncome.get().getIncome()).isEqualTo(income.getIncome());
//            assertThat(foundIncome.get().getBudget()).isEqualTo(income.getBudget());
//            assertThat(foundIncome.get().getCreatedDate()).isEqualTo(income.getCreatedDate());
//        }
//    }
