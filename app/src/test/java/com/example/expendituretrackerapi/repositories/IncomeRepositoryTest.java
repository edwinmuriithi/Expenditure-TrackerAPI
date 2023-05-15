package com.example.expendituretrackerapi.repositories;

import com.example.expendituretrackerapi.entities.Income;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
class IncomeRepositoryTest {
    @Autowired
        private IncomeRepository incomeRepository;

        private Income income;
    @Autowired
    private ExpenditureRepository expenditureRepository;

    @BeforeEach
        public void setup(){
            income = Income.builder()
                    .budget(100000)
                    .income(400000)
                    .createdDate(LocalDate.now())
                    .build();
        }

        @Test
        @DisplayName("Save Income")
        public void saveIncome(){
            Income newIncome = incomeRepository.save(income);
            assertThat(newIncome).isNotNull();
            assertThat(newIncome.getId()).isGreaterThan(0);
        }

        @Test
        @DisplayName("Tests findById Income")
        public void findByIdIncome() {
            // Given
            incomeRepository.save(income);
            Optional<Income> income1 = incomeRepository.findById(income.getId());
            assertThat(income1).isNotNull();


        }

    @DisplayName("get All expenditures")
    @Test
    public void getAllIncome(){
            Income income1 = Income.builder()
                    .id(2L)
                    .income(90000)
                    .budget(30000)
                    .createdDate(LocalDate.now())
                    .build();
                    incomeRepository.save(income1);
//                    incomeRepository.save(income);
        List<Income>incomeList = incomeRepository.findAll();
        assertThat(incomeList).isNotNull();
        assertThat(incomeList.size()).isEqualTo(1);
    }
    @Test
    public void updateIncome(){
        incomeRepository.save(income);
        Income savedIncome = incomeRepository.findById(income.getId()).get();
        savedIncome.setIncome(400000);
        savedIncome.setBudget(5000);
        Income updatedIncome = incomeRepository.save(savedIncome);
        assertThat(updatedIncome.getIncome()).isEqualTo(400000);
        assertThat(updatedIncome.getBudget()).isEqualTo(5000);
    }
    @Test
    public void deleteIncome(){
            incomeRepository.save(income);
            incomeRepository.deleteById(income.getId());
            assertThat(incomeRepository.findById(1L)).isEmpty();
    }

    }
