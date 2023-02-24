package com.example.expendituretrackerapi.services.impl;

import com.example.expendituretrackerapi.entities.Expenditure;
import com.example.expendituretrackerapi.entities.Income;

import com.example.expendituretrackerapi.exception.ExpenditureNotFoundException;

import com.example.expendituretrackerapi.repositories.ExpenditureRepository;
import com.example.expendituretrackerapi.repositories.IncomeRepository;
import com.example.expendituretrackerapi.services.ExpenditureService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class ExpenditureServiceImpl implements ExpenditureService {

    @Autowired
    private ExpenditureRepository expenditureRepository;
    private IncomeRepository incomeRepository;

    public ExpenditureServiceImpl(ExpenditureRepository expenditureRepository) {
        this.expenditureRepository = expenditureRepository;
    }

    @Override
    public Expenditure createExpenditure(Expenditure expenditure,Long incomeId) {
        Income income = incomeRepository.findById(incomeId).orElseThrow(()->
                new RuntimeException("not found income with id :" + incomeId));
        expenditure.setIncome(income);
        expenditure.setRent(expenditure.getRent());
        expenditure.setFood(expenditure.getFood());
        expenditure.setTransport(expenditure.getTransport());
        expenditure.setHealth(expenditure.getHealth());
        expenditure.setSchoolFee(expenditure.getSchoolFee());
        expenditure.setShopping(expenditure.getShopping());
        expenditure.setEntertainment(expenditure.getEntertainment());
        expenditure.setTotal(expenditure.getTotal());
        Expenditure newExpenditure = expenditureRepository.save(expenditure);
        return newExpenditure;
    }

    @Override
    public List<Expenditure> viewExpenditure() {
        List<Expenditure> expenditures = expenditureRepository.findAll();
        return expenditures;
    }

    @Override
    public Expenditure findById(Long expenditureId) throws ExpenditureNotFoundException {
        Optional<Expenditure> expenditure = expenditureRepository.findById(expenditureId);
        if(expenditure.isPresent()){
            log.info("Fetched Expenditure successfully");
            return expenditure.get();
        }else {
            log.info("Expenditure ID not found");
            throw new ExpenditureNotFoundException("Expenditure not found with ID: " + expenditureId);
        }

    }
}
