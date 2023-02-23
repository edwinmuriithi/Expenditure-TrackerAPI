package com.example.expendituretrackerapi.services.impl;

import com.example.expendituretrackerapi.entities.Income;
import com.example.expendituretrackerapi.repositories.IncomeRepository;
import com.example.expendituretrackerapi.services.IncomeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class IncomeServiceImpl implements IncomeService {
    @Autowired
    private IncomeRepository incomeRepository;

    public IncomeServiceImpl(IncomeRepository incomeRepository){
        this.incomeRepository = incomeRepository;
    }

    @Override
    public Income createIncome(Income income) {
        income.setIncome(income.getIncome());
        income.setBudget(income.getBudget());
        income.setExpenditure(income.getExpenditure());
        income.setCreatedDate(income.getCreatedDate());
        Income newIncome = incomeRepository.save(income);
        return newIncome;
    }

    @Override
    public List<Income> viewIncome() {
        List<Income> income = incomeRepository.findAll();
        return income;
    }
}
