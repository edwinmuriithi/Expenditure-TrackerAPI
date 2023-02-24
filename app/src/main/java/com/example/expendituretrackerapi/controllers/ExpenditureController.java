package com.example.expendituretrackerapi.controllers;

import com.example.expendituretrackerapi.entities.Expenditure;
import com.example.expendituretrackerapi.entities.Income;
import com.example.expendituretrackerapi.entities.dto.ExpenditureDTO;
import com.example.expendituretrackerapi.repositories.ExpenditureRepository;
import com.example.expendituretrackerapi.repositories.IncomeRepository;
import com.example.expendituretrackerapi.services.ExpenditureService;
import com.example.expendituretrackerapi.services.IncomeService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/expenditure")
public class ExpenditureController {
    @Autowired
    private ExpenditureService expenditureService;
    @Autowired
    private ExpenditureRepository expenditureRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private IncomeRepository incomeRepository;

    public ExpenditureController(ExpenditureService expenditureService, ExpenditureRepository expenditureRepository) {
        this.expenditureService = expenditureService;
        this.expenditureRepository = expenditureRepository;
    }

    @PostMapping("/{incomeId}")
    public ResponseEntity<ExpenditureDTO>createExpenditure(@RequestBody ExpenditureDTO expenditureDTO,@PathVariable(value = "incomeId") Long incomeId){
        Expenditure expenditureRequest = modelMapper.map(expenditureDTO,Expenditure.class);
        Expenditure expenditure = expenditureService.createExpenditure(expenditureRequest,incomeId);
        ExpenditureDTO expenditureResponse = modelMapper.map(expenditure, ExpenditureDTO.class);
        return new ResponseEntity<ExpenditureDTO>(expenditureResponse, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<ExpenditureDTO>> viewAll(){
        return ResponseEntity.ok(expenditureService.viewExpenditure().stream()
                .map(expenditure -> modelMapper.map(expenditure, ExpenditureDTO.class))
                .collect(Collectors.toList()));
    }
}
