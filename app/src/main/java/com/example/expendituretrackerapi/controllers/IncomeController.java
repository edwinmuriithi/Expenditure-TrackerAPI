package com.example.expendituretrackerapi.controllers;

import com.example.expendituretrackerapi.entities.Income;
import com.example.expendituretrackerapi.entities.dto.IncomeDTO;
import com.example.expendituretrackerapi.repositories.IncomeRepository;
import com.example.expendituretrackerapi.services.IncomeService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/income")
public class IncomeController {
    @Autowired
    private IncomeService incomeService;
    @Autowired
    private IncomeRepository incomeRepository;
    @Autowired
    private ModelMapper modelMapper;
    private IncomeController(IncomeService incomeService,IncomeRepository incomeRepository){
        this.incomeService = incomeService;
        this.incomeRepository = incomeRepository;
    }
    @PostMapping
    public ResponseEntity<IncomeDTO>createIncome(@RequestBody IncomeDTO incomeDTO){
        Income incomeRequest = modelMapper.map(incomeDTO, Income.class);
        Income income = incomeService.createIncome(incomeRequest);
        IncomeDTO incomeResponse = modelMapper.map(income,IncomeDTO.class);
        return new ResponseEntity<IncomeDTO>(incomeResponse, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<IncomeDTO>> viewAll(){
        return ResponseEntity.ok(incomeService.viewIncome().stream()
                .map(income -> modelMapper.map(income, IncomeDTO.class))
                .collect(Collectors.toList()));
    }
}
