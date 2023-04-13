package com.example.expendituretrackerapi.controllers;

import com.example.expendituretrackerapi.entities.Expenditure;
import com.example.expendituretrackerapi.entities.dto.ExpenditureDTO;
import com.example.expendituretrackerapi.exception.ExpenditureNotFoundException;
import com.example.expendituretrackerapi.repositories.ExpenditureRepository;
import com.example.expendituretrackerapi.repositories.IncomeRepository;
import com.example.expendituretrackerapi.services.ExpenditureService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/expenditure")
@Slf4j
//@DependsOn("incomeRepository")
public class ExpenditureController {
    @Autowired
    private IncomeRepository incomeRepository;
    @Autowired
    private ExpenditureService expenditureService;
    @Autowired
    private ExpenditureRepository expenditureRepository;
    @Autowired
    private ModelMapper modelMapper;


    public ExpenditureController(ExpenditureService expenditureService, ExpenditureRepository expenditureRepository,IncomeRepository incomeRepository) {
        this.expenditureService = expenditureService;
        this.expenditureRepository = expenditureRepository;
        this.incomeRepository = incomeRepository;
    }

    public ExpenditureController() {

    }

    @PostMapping("/{incomeId}")
    public ResponseEntity<ExpenditureDTO>createExpenditure(@RequestBody ExpenditureDTO expenditureDTO,@PathVariable(value = "incomeId") Long incomeId){
        Expenditure expenditureRequest = modelMapper.map(expenditureDTO,Expenditure.class);
        Expenditure expenditure = expenditureService.createExpenditure(expenditureRequest,incomeId);
        ExpenditureDTO expenditureResponse = modelMapper.map(expenditure, ExpenditureDTO.class);
//        if(expenditure != null){
//            log.info("Expenditure saved successfully");
            return new ResponseEntity<ExpenditureDTO>(expenditureResponse, HttpStatus.CREATED);
//        }else{
//            log.error("Expenditure not saved");
//            throw new ExpenditureNotFoundException("Expenditure not saved");
//    }
    }

    @GetMapping
    public ResponseEntity<List<ExpenditureDTO>> viewAll(){
        log.info("Fetched expenditure successfully");
        return ResponseEntity.ok(expenditureService.viewExpenditure().stream()
                .map(expenditure -> modelMapper.map(expenditure, ExpenditureDTO.class))
                .collect(Collectors.toList()));
    }
//    @GetMapping("/totalExpenditure/{expenditureId}")
//   public ResponseEntity<Integer>getTotalExpenditureById(@PathVariable Long expenditureId){
//        Integer total = expenditureService.getTotalExpenditureById(expenditureId);
//        return ResponseEntity.ok(total);
//    }
    @GetMapping("/getPercentages")
    public ResponseEntity<Map<Long,Double>> getExpenditurePercentage(){
        Map<Long,Double>percentages = expenditureService.calculatePercentage();
       return ResponseEntity.ok(percentages);
    }

    @GetMapping("/{expenditureId}")
    public ResponseEntity<ExpenditureDTO>viewById(@PathVariable Long expenditureId){
        Expenditure expenditure = expenditureService.findById(expenditureId);
        ExpenditureDTO expenditureResponse = modelMapper.map(expenditure, ExpenditureDTO.class);
        log.info("Expenditure has been fetched with ID {}", expenditureId);
        return ResponseEntity.ok().body(expenditureResponse);
    }

    @PutMapping("/{expenditureId}")
    public ResponseEntity<ExpenditureDTO> updateExpenditureById(@RequestBody ExpenditureDTO expenditureDTO, @PathVariable Long expenditureId)
            throws ExpenditureNotFoundException {
        Expenditure expenditureRequest = modelMapper.map(expenditureDTO, Expenditure.class);
        Expenditure expenditure = expenditureService.updateExpenditureById(expenditureRequest, expenditureId);
        ExpenditureDTO expenditureResponse = modelMapper.map(expenditure,ExpenditureDTO.class);
        if (expenditureResponse !=null){
            log.info("Expenditure updated Successfully");
            return ResponseEntity.ok().body(expenditureResponse);
        }else{
            log.error("Expenditure ID "+expenditureId+" not found");
            throw new ExpenditureNotFoundException("Update to Expenditure failed");
        }
    }

    @DeleteMapping("/{expenditureId}")
    public void deleteExpenditureById(@PathVariable Long expenditureId)throws ExpenditureNotFoundException{
        expenditureService.deleteExpenditureById(expenditureId);
        log.info("Expenditure has been deleted successfully");
    }
}
