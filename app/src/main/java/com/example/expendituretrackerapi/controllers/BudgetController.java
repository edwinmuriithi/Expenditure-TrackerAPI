package com.example.expendituretrackerapi.controllers;

import com.example.expendituretrackerapi.entities.dto.BudgetDTO;
import com.example.expendituretrackerapi.repositories.BudgetRepository;
import com.example.expendituretrackerapi.services.BudgetService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/budget")
public class BudgetController {

    @Autowired
    private BudgetService budgetService;
    @Autowired
    private BudgetRepository budgetRepository;
    @Autowired
    private ModelMapper modelMapper;

    public BudgetController(BudgetService budgetService, BudgetRepository budgetRepository) {
        this.budgetService = budgetService;
        this.budgetRepository = budgetRepository;
    }

    @GetMapping
    public ResponseEntity<List<BudgetDTO>> viewAll(){
        return ResponseEntity.ok(budgetService.viewBudget().stream()
                .map(budget -> modelMapper.map(budget, BudgetDTO.class))
                .collect(Collectors.toList()));
    }
}
