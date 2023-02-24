package com.example.expendituretrackerapi.services;

import com.example.expendituretrackerapi.entities.Income;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface IncomeService {
    Income createIncome(Income income);
    List<Income> viewIncome();
}
