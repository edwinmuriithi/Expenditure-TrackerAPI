package com.example.expendituretrackerapi.repositories;

import com.example.expendituretrackerapi.entities.Income;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import static org.junit.jupiter.api.Assertions.*;
import java.time.LocalDate;
import java.util.Optional;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@SpringBootTest
class IncomeRepositoryTest {
    @Autowired
        private IncomeRepository incomeRepository;

        private Income income;
        @BeforeEach
        public void setup(){
            income = Income.builder()
                    .id(1L)
                    .budget(100000)
                    .income(400000)
                    .build();
        }

        @Test
        @DisplayName("Save Income")
        public void saveIncome(){
            Income newIncome = incomeRepository.save(income);
            assertThat(newIncome).isNotNull();
            assertThat(newIncome.getIncome()).isGreaterThan(0);
        }

        @Test
        @DisplayName("Tests findById Income")
        public void findByIdIncome() {
            // Given
            Income income = new Income(1L, 50000,20000, LocalDate.now());
            incomeRepository.save(income);

            // When
            Optional<Income> foundIncome = incomeRepository.findById(income.getId());

            // Then
            assertThat(foundIncome).isPresent();
            assertThat(foundIncome.get().getIncome()).isEqualTo(income.getIncome());
            assertThat(foundIncome.get().getBudget()).isEqualTo(income.getBudget());
            assertThat(foundIncome.get().getCreatedDate()).isEqualTo(income.getCreatedDate());
        }
    }
