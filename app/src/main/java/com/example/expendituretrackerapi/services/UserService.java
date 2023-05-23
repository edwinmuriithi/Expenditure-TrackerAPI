package com.example.expendituretrackerapi.services;

import com.example.expendituretrackerapi.entities.Expenditure;
import com.example.expendituretrackerapi.entities.Income;
import com.example.expendituretrackerapi.entities.dto.ExpenditureDTO;
import com.example.expendituretrackerapi.entities.dto.IncomeDTO;
import com.example.expendituretrackerapi.entities.dto.UserDTO;
import com.example.expendituretrackerapi.exception.ExpenditureNotFoundException;
import com.example.expendituretrackerapi.exception.IncomeNotFoundException;
import org.springframework.stereotype.Component;

@Component
public interface UserService {
    UserDTO getUserDetails(String userId);
    IncomeDTO createUserIncome(Income income, String userId);
    ExpenditureDTO createUserExpenditure(Expenditure expenditure, String userId);
    IncomeDTO findUserIncome(String userId) throws IncomeNotFoundException;
    ExpenditureDTO findUserExpenditure(String userId) throws ExpenditureNotFoundException;
    IncomeDTO updateUserIncome( Income income,String userId);
}
