package com.example.expendituretrackerapi.services.impl;

import com.example.expendituretrackerapi.entities.Budget;
import com.example.expendituretrackerapi.repositories.BudgetRepository;
import com.example.expendituretrackerapi.services.BudgetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service

public class BudgetServiceImpl implements BudgetService {
    @Autowired
    private BudgetRepository budgetRepository;

    @Override
    public Budget createBudget(Budget budget) {
        budget.setBudget(budget.getBudget());
        budget.setCreatedDate(budget.getCreatedDate());
        Budget newBudget = budgetRepository.save(budget);
        return newBudget;
    }
}
