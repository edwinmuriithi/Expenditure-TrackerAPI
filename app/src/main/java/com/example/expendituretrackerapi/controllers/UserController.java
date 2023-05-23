package com.example.expendituretrackerapi.controllers;


import com.example.expendituretrackerapi.entities.Expenditure;
import com.example.expendituretrackerapi.entities.Income;
import com.example.expendituretrackerapi.entities.dto.ExpenditureDTO;
import com.example.expendituretrackerapi.entities.dto.IncomeDTO;
import com.example.expendituretrackerapi.entities.dto.UserDTO;
import com.example.expendituretrackerapi.exception.ExpenditureNotFoundException;
import com.example.expendituretrackerapi.exception.IncomeNotFoundException;
import com.example.expendituretrackerapi.exception.UserDetailNotFoundException;
import com.example.expendituretrackerapi.services.UserService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@Slf4j
public class UserController {

    private final UserService userService;
    private final ModelMapper modelMapper;

    @Autowired
    public UserController(UserService userService, ModelMapper modelMapper) {
       this.userService = userService;
       this.modelMapper = modelMapper;
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserDTO> getUserDetails(@PathVariable String userId) throws UserDetailNotFoundException {
        UserDTO user = userService.getUserDetails(userId);

        if (user != null) {
            log.info("Income & Expenditure have been fetched for user with ID {}", userId);
            return ResponseEntity.ok().body(user);
        }else {
            log.error("Unable to fetch income & expenditure for user with ID {}", userId);
            throw new UserDetailNotFoundException("Income & Expenditure for user with ID "+userId+" does not exist");
        }
    }

    @GetMapping("/{userId}/income")
    public ResponseEntity<IncomeDTO> getUserIncome(@PathVariable String userId) throws IncomeNotFoundException {
        IncomeDTO incomeResponse = userService.findUserIncome(userId);
        if (incomeResponse != null) {
            log.info("Income has been fetched for user with ID {}", userId);
            return ResponseEntity.ok().body(incomeResponse);
        }else {
            log.error("Unable to fetch income for user with ID {}", userId);
            throw new IncomeNotFoundException("Income for user with ID "+userId+" does not exist");
        }
    }

    @GetMapping("/{userId}/expenditure")
    public ResponseEntity<ExpenditureDTO> getUserExpenditure(@PathVariable String userId) throws ExpenditureNotFoundException {
        ExpenditureDTO expenditureResponse = userService.findUserExpenditure(userId);
        if (expenditureResponse != null) {
            log.info("Expenditure has been fetched for user with ID {}", userId);
            return ResponseEntity.ok().body(expenditureResponse);
        }else {
            log.error("Unable to fetch expenditure for user with ID {}", userId);
            throw new IncomeNotFoundException("Expenditure for user with ID "+userId+" does not exist");
        }
    }

    @PostMapping("/{userId}/income")
    public ResponseEntity<IncomeDTO>createUserIncome(@PathVariable String userId, @RequestBody IncomeDTO incomeDTO){
        Income incomeRequest = modelMapper.map(incomeDTO, Income.class);

        if(incomeRequest.getIncome() > incomeRequest.getBudget()){
            IncomeDTO income = userService.createUserIncome(incomeRequest, userId);
            log.info("Income has been saved successfully {}",income);
            return new ResponseEntity<>(income, HttpStatus.CREATED);
        } else {
            log.error("Budget has exceeded Income");
            throw new IncomeNotFoundException("budget should not exceed income");
        }
    }

    @PostMapping("/{userId}/expenditure")
    public ResponseEntity<ExpenditureDTO>createUserExpenditure(@PathVariable String userId, @RequestBody ExpenditureDTO expenditureDTO){
        Expenditure expenditureRequest = modelMapper.map(expenditureDTO, Expenditure.class);
        ExpenditureDTO expenditure = userService.createUserExpenditure(expenditureRequest, userId);

        IncomeDTO income = userService.findUserIncome(userId);

        if (expenditure == null) {
            log.error("Expenditure not saved");
            throw new ExpenditureNotFoundException("Expenditure not created");
        } else if(expenditure.getTotal() > income.getBudget()){
            log.error("Budget exceeded total expenditure");
            throw new ExpenditureNotFoundException("Budget should not exceed your total expenditure");
        } else{
            log.info("Expenditure has been saved successfully {}", expenditure);
            return new ResponseEntity<>(expenditure, HttpStatus.CREATED);
        }
    }

    @PutMapping("/{userId}/income")
    public ResponseEntity<IncomeDTO>updateIncome(@PathVariable String userId,@RequestBody IncomeDTO incomeDTO) {
        Income incomeRequest = modelMapper.map(incomeDTO, Income.class);
        IncomeDTO income = userService.updateUserIncome(incomeRequest, userId);
        IncomeDTO incomeResponse = modelMapper.map(income, IncomeDTO.class);
        if (incomeResponse != null) {
            log.info("Income updated Successfully");
            return ResponseEntity.ok().body(incomeResponse);
        } else {
            log.error("user with id " + userId + " not found");
            throw new UserDetailNotFoundException("Update to Income failed");
        }
    }
}
