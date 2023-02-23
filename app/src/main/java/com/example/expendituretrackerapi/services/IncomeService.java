package com.example.expendituretrackerapi.services;

import com.example.expendituretrackerapi.entities.Income;

import java.util.List;

public interface IncomeService {
    Income createIncome(Income income);
    List<Income> viewIncome();
}
