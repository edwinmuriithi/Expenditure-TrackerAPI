package com.example.expendituretrackerapi.repositories;

import com.example.expendituretrackerapi.entities.Expenditure;
import com.example.expendituretrackerapi.entities.Income;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class ExpenditureRepositoryTest {
    @Autowired
    private ExpenditureRepository expenditureRepository;
    private Expenditure expenditure;
    @BeforeEach
    public void setup(){
        expenditure= Expenditure.builder()
                .rent(6000)
                .food(7000)
                .shopping(5000)
                .transport(3000)
                .health(90000)
                .entertainment(1000)
                .createdDate(LocalDate.now())
                .schoolFee(40000)
                .build();
    }
    @Test
    public void saveExpenditure(){
        Expenditure expenditure1 = expenditureRepository.save(expenditure);
        assertThat(expenditure1).isNotNull();
        assertThat(expenditure1.getId()).isGreaterThan(0);
    }
    @Test
    public void getAllExpenditures(){
        expenditureRepository.save(expenditure);
        List<Expenditure> expenditure1 = expenditureRepository.findAll();
        assertThat(expenditure1).isNotNull();
        assertThat(expenditure1.size()).isEqualTo(1);

    }
    @Test
    public void getExpenditureById(){
        expenditureRepository.save(expenditure);
        Optional<Expenditure> expenditure1 = expenditureRepository.findById(expenditure.getId());
        assertThat(expenditure1).isNotNull();
    }
    @Test
    public void updateExpenditure(){
        expenditureRepository.save(expenditure);
       // Expenditure newExpenditure = new Expenditure(2L,2000,3000,4000,20000,1000,3000,1000,new Income(1L,10000,30000,LocalDate.now()),LocalDate.now());
        Expenditure saveExpenditure = expenditureRepository.findById(expenditure.getId()).get();
         saveExpenditure.setRent(5000);
         saveExpenditure .setFood(5000);
         saveExpenditure.setShopping(5000);
         saveExpenditure.setTransport(5000);
         saveExpenditure.setHealth(3000);
         saveExpenditure.setEntertainment(5000);
         saveExpenditure.setSchoolFee(70000);
        Expenditure updatedExpenditure = expenditureRepository.save(saveExpenditure);

         assertThat(updatedExpenditure.getRent()).isEqualTo(5000);
         assertThat(updatedExpenditure.getFood()).isEqualTo(5000);
         assertThat(updatedExpenditure.getSchoolFee()).isEqualTo(70000);
         assertThat(updatedExpenditure.getShopping()).isEqualTo(5000);
         assertThat(updatedExpenditure.getTransport()).isEqualTo(5000);
         assertThat(updatedExpenditure.getHealth()).isEqualTo(3000);
         assertThat(updatedExpenditure.getEntertainment()).isEqualTo(5000);

    }
    @Test
    public void deleteExpenditure(){
        expenditureRepository.save(expenditure);
     expenditureRepository.deleteById(expenditure.getId());
     assertThat(expenditureRepository.findById(1L)).isEmpty();
    }

}