package com.example.expendituretrackerapi.services.impl;

import com.example.expendituretrackerapi.entities.Income;
import com.example.expendituretrackerapi.exception.IncomeNotFoundException;
import com.example.expendituretrackerapi.repositories.IncomeRepository;
import com.example.expendituretrackerapi.services.IncomeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class IncomeServiceImpl implements IncomeService {
    @Autowired
    private IncomeRepository incomeRepository;

    public IncomeServiceImpl(IncomeRepository incomeRepository){
        this.incomeRepository = incomeRepository;
    }

    @Override
    public Income createIncome(Income income) throws IncomeNotFoundException {
        income.setIncome(income.getIncome());
        income.setBudget(income.getBudget());
        income.setCreatedDate(income.getCreatedDate());
        if (income.getIncome() > income.getBudget()) {
            Income newIncome = incomeRepository.save(income);
            return newIncome;
        } else {
            log.error("Budget has exceeded Income");
            throw new IncomeNotFoundException("budget should not exceed income");
        }
    }

        @Override
        public List<Income> viewIncome () throws IncomeNotFoundException {
            List<Income> income = incomeRepository.findAll();
            if (income.isEmpty()) {
                log.error("Income is empty");
                throw new IncomeNotFoundException("There is no income which has been saved");
            } else {
                log.info("Income is retrieved successfully");
                return income;
            }
        }

        @Override
        public Income findById (Long incomeId) throws IncomeNotFoundException {
            Optional<Income> income = incomeRepository.findById(incomeId);
            if (income.isPresent()) {
                log.info("Fetched Income by ID successfully {}", incomeId);
                return income.get();
            } else {
                log.error("Income with ID " + incomeId + " not found");
                throw new IncomeNotFoundException("Income not found with id: " + incomeId);
            }
        }

        @Override
        public void deleteIncomeById (Long incomeId) throws IncomeNotFoundException {
            incomeRepository.findById(incomeId).orElseThrow(() -> new IncomeNotFoundException("Income with ID " + incomeId + " not found"));
            log.info("Income deleted successfully");
            incomeRepository.deleteById(incomeId);
        }

        @Override
        public Income updateIncomeById (Income income, Long incomeId) throws IncomeNotFoundException {
            Income existingIncome = incomeRepository.findById(incomeId).orElseThrow(() ->
                    new IncomeNotFoundException("Income with ID " + incomeId + " not found"));

            existingIncome.setIncome(income.getIncome());
            existingIncome.setBudget(income.getBudget());
            Income newIncome = incomeRepository.save(existingIncome);
            log.info("Income updated successfully {}", newIncome);
            return newIncome;

        }
}
