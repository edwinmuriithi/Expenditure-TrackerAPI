package com.example.expendituretrackerapi.services;

import com.example.expendituretrackerapi.entities.Income;
import com.example.expendituretrackerapi.exception.IncomeNotFoundException;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface IncomeService {
    Income createIncome(Income income) throws IncomeNotFoundException;
    List<Income> viewIncome() throws IncomeNotFoundException;
    Income findById(Long incomeId) throws IncomeNotFoundException;
    void deleteIncomeById(Long incomeId)throws IncomeNotFoundException;
    Income updateIncomeById(Income income,Long incomeId) throws IncomeNotFoundException;
}
