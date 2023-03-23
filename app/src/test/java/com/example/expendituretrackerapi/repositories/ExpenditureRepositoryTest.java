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
                .income(new Income(1L,2000000,300000, LocalDate.now()))
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
        Expenditure newExpenditure = new Expenditure(2L,2000,3000,4000,20000,1000,3000,1000,new Income(1L,10000,30000,LocalDate.now()),LocalDate.now());
        Expenditure saveExpenditure = expenditureRepository.findById(expenditure.getId()).get();
         saveExpenditure.setRent(newExpenditure.getRent());
         saveExpenditure .setFood(newExpenditure.getFood());
         saveExpenditure.setShopping(newExpenditure.getShopping());
         saveExpenditure.setTransport(newExpenditure.getTransport());
         saveExpenditure.setHealth(newExpenditure.getHealth());
         saveExpenditure.setEntertainment(newExpenditure.getEntertainment());
         saveExpenditure.setSchoolFee(newExpenditure.getSchoolFee());
         expenditureRepository.save(saveExpenditure);

         Expenditure updatedExpenditure = expenditureRepository.findById(expenditure.getId()).get();

         assertThat(updatedExpenditure.getRent()).isEqualTo(newExpenditure.getRent());
         assertThat(updatedExpenditure.getFood()).isEqualTo(newExpenditure.getFood());
         assertThat(updatedExpenditure.getSchoolFee()).isEqualTo(newExpenditure.getSchoolFee());
         assertThat(updatedExpenditure.getShopping()).isEqualTo(newExpenditure.getShopping());
         assertThat(updatedExpenditure.getTransport()).isEqualTo(newExpenditure.getTransport());
         assertThat(updatedExpenditure.getHealth()).isEqualTo(newExpenditure.getHealth());
         assertThat(updatedExpenditure.getEntertainment()).isEqualTo(newExpenditure.getEntertainment());

    }
    @Test
    public void deleteExpenditure(){
        expenditureRepository.save(expenditure);
     expenditureRepository.deleteById(expenditure.getId());
     assertThat(expenditureRepository.findById(1L)).isEmpty();
    }

}