package com.example.expendituretrackerapi.controllers;

import com.example.expendituretrackerapi.entities.Expenditure;
import com.example.expendituretrackerapi.entities.dto.ExpenditureDTO;
import com.example.expendituretrackerapi.exception.ExpenditureNotFoundException;
import com.example.expendituretrackerapi.repositories.ExpenditureRepository;
import com.example.expendituretrackerapi.repositories.IncomeRepository;
import com.example.expendituretrackerapi.services.ExpenditureService;
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
@RequestMapping("/expenditure")
@Slf4j
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
        if(expenditure == null){
            log.error("Expenditure not saved");
            throw new ExpenditureNotFoundException("Expenditure not saved");
        }else{
            log.info("Expenditure saved successfully");
        return new ResponseEntity<ExpenditureDTO>(expenditureResponse, HttpStatus.CREATED);
    }
    }

    @GetMapping
    public ResponseEntity<List<ExpenditureDTO>> viewAll(){
        log.info("Fetched expenditure successfully");
        return ResponseEntity.ok(expenditureService.viewExpenditure().stream()
                .map(expenditure -> modelMapper.map(expenditure, ExpenditureDTO.class))
                .collect(Collectors.toList()));
    }

    @GetMapping("{expenditureId}")
    public ResponseEntity<ExpenditureDTO>viewById(@PathVariable Long expenditureId){
        Expenditure expenditure = expenditureService.findById(expenditureId);
        ExpenditureDTO expenditureResponse = modelMapper.map(expenditure, ExpenditureDTO.class);
        log.info("Expenditure has been fetched with ID {}", expenditureId);
        return ResponseEntity.ok().body(expenditureResponse);
    }
}
