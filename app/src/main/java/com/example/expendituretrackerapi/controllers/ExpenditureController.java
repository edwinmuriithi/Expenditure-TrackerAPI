package com.example.expendituretrackerapi.controllers;

import com.example.expendituretrackerapi.entities.Expenditure;
import com.example.expendituretrackerapi.entities.dto.ExpenditureDTO;
import com.example.expendituretrackerapi.repositories.ExpenditureRepository;
import com.example.expendituretrackerapi.repositories.IncomeRepository;
import com.example.expendituretrackerapi.services.ExpenditureService;
import com.example.expendituretrackerapi.services.IncomeService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/expenditure")
public class ExpenditureController {
    @Autowired
    private ExpenditureService expenditureService;
    @Autowired
    private ExpenditureRepository expenditureRepository;
    @Autowired
    private ModelMapper modelMapper;

    public ExpenditureController(ExpenditureService expenditureService, ExpenditureRepository expenditureRepository) {
        this.expenditureService = expenditureService;
        this.expenditureRepository = expenditureRepository;
    }

    @PostMapping
    public ResponseEntity<ExpenditureDTO>createExpenditure(@RequestBody ExpenditureDTO expenditureDTO){
        Expenditure expenditureRequest = modelMapper.map(expenditureDTO,Expenditure.class);
        Expenditure expenditure = expenditureService.createExpenditure(expenditureRequest);
        ExpenditureDTO expenditureResponse = modelMapper.map(expenditure, ExpenditureDTO.class);
        return new ResponseEntity<ExpenditureDTO>(expenditureResponse, HttpStatus.CREATED);
    }
}
