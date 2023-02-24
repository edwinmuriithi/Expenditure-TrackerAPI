package com.example.expendituretrackerapi.services.impl;

import com.example.expendituretrackerapi.entities.Budget;
import com.example.expendituretrackerapi.repositories.BudgetRepository;
import com.example.expendituretrackerapi.services.BudgetService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class BudgetServiceImpl implements BudgetService {
    @Autowired
    private BudgetRepository budgetRepository;

    public BudgetServiceImpl(BudgetRepository budgetRepository) {
        this.budgetRepository = budgetRepository;
    }

    @Override
    public List<Budget> viewBudget() {
        List<Budget> budget = budgetRepository.findAll();
        return budget;
    }
}
