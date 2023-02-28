package com.example.expendituretrackerapi.controllers;

import com.example.expendituretrackerapi.entities.Income;
import com.example.expendituretrackerapi.entities.dto.IncomeDTO;
import com.example.expendituretrackerapi.exception.IncomeNotFoundException;
import com.example.expendituretrackerapi.repositories.IncomeRepository;
import com.example.expendituretrackerapi.services.IncomeService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/income")
@Slf4j
public class IncomeController {
    @Autowired
    private IncomeService incomeService;
    @Autowired
    private IncomeRepository incomeRepository;
    @Autowired
    private ModelMapper modelMapper;
    public IncomeController(IncomeService incomeService,IncomeRepository incomeRepository){
        this.incomeService = incomeService;
        this.incomeRepository = incomeRepository;
    }
    @PostMapping
    public ResponseEntity<IncomeDTO>createIncome(@RequestBody IncomeDTO incomeDTO){
        Income incomeRequest = modelMapper.map(incomeDTO, Income.class);
        Income income = incomeService.createIncome(incomeRequest);
        IncomeDTO incomeResponse = modelMapper.map(income,IncomeDTO.class);
        if (income == null) {
            log.error("Income not saved");
            throw new IncomeNotFoundException("Income not created");
        }else{
            log.info("Income has been saved successfully {}",incomeResponse);
        return new ResponseEntity<IncomeDTO>(incomeResponse, HttpStatus.CREATED);
    }}

    @GetMapping
    public ResponseEntity<List<IncomeDTO>> viewAll() throws IncomeNotFoundException{
        log.info("Fetched Income Successfully");
        return ResponseEntity.ok(incomeService.viewIncome().stream()
                .map(income -> modelMapper.map(income, IncomeDTO.class))
                .collect(Collectors.toList()));
    }

    @GetMapping("/{incomeId}")
    public ResponseEntity<IncomeDTO>viewIncomeBYId(@PathVariable Long incomeId) throws IncomeNotFoundException {
        Income income = incomeService.findById(incomeId);
        IncomeDTO incomeResponse = modelMapper.map(income, IncomeDTO.class);
        if (incomeResponse !=null) {
            log.info("Income has been fetched with ID {}", incomeId);
            return ResponseEntity.ok().body(incomeResponse);
        }else {
            log.info("Unable to fetch income with ID {}", incomeId);
            throw new IncomeNotFoundException("Income ID "+incomeId+" does not exist");
        }
    }

    @DeleteMapping("/{incomeId}")
    public void deleteIncome(@PathVariable Long incomeId)throws IncomeNotFoundException{
        log.info("Income deleted successfully");
        incomeService.deleteIncomeById(incomeId);
    }

}
