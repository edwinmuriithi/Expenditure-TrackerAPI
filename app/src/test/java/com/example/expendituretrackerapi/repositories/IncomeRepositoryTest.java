package com.example.expendituretrackerapi.repositories;

import com.example.expendituretrackerapi.entities.Income;
import org.junit.Assert;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class IncomeRepositoryTests {

    @Autowired
    private IncomeRepository incomeRepository;

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