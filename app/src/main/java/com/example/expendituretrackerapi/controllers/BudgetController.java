package com.example.expendituretrackerapi.controllers;

import com.example.expendituretrackerapi.entities.Budget;
import com.example.expendituretrackerapi.entities.dto.BudgetDTO;
import com.example.expendituretrackerapi.repositories.BudgetRepository;
import com.example.expendituretrackerapi.services.BudgetService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/budget")
public class BudgetController {
    @Autowired
    private BudgetService budgetService;
    @Autowired
    private BudgetRepository budgetRepository;
    @Autowired
    private ModelMapper modelMapper;
    public BudgetController(BudgetService budgetService,BudgetRepository budgetRepository){
        this.budgetRepository = budgetRepository;
        this.budgetService = budgetService;

    }
    @PostMapping
    private ResponseEntity<BudgetDTO>createBudget(@RequestBody BudgetDTO budgetDTO){
        //converting DTO to entity
        Budget budgetRequest = modelMapper.map(budgetDTO, Budget.class);
        Budget budget = budgetService.createBudget(budgetRequest);
        //convert entity to DTO
        BudgetDTO budgetResponse = modelMapper.map(budget, BudgetDTO.class);
        return new ResponseEntity<BudgetDTO>(budgetResponse, HttpStatus.CREATED);
    }
}
