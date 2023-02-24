package com.example.expendituretrackerapi.services;

import com.example.expendituretrackerapi.entities.Income;
import com.example.expendituretrackerapi.exception.IncomeNotFoundException;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface IncomeService {
    Income createIncome(Income income) throws IncomeNotFoundException;
    List<Income> viewIncome();
    Income findById(Long incomeId) throws IncomeNotFoundException;
}
