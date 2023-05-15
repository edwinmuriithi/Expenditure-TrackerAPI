package com.example.expendituretrackerapi.services.impl;

import com.example.expendituretrackerapi.entities.Expenditure;
import com.example.expendituretrackerapi.entities.Income;
import com.example.expendituretrackerapi.entities.dto.ExpenditureDTO;
import com.example.expendituretrackerapi.entities.dto.IncomeDTO;
import com.example.expendituretrackerapi.entities.dto.UserDTO;
import com.example.expendituretrackerapi.repositories.ExpenditureRepository;
import com.example.expendituretrackerapi.repositories.IncomeRepository;
import com.example.expendituretrackerapi.services.UserService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class UserServiceImpl implements UserService {
    private final IncomeRepository incomeRepository;
    private final ExpenditureRepository expenditureRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public UserServiceImpl(IncomeRepository incomeRepository, ExpenditureRepository expenditureRepository, ModelMapper modelMapper){
        this.incomeRepository = incomeRepository;
        this.expenditureRepository = expenditureRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public UserDTO getUserDetails(String userId) {
        UserDTO user = new UserDTO();
        user.setUserId(userId);
        user.setIncome(findUserIncome(userId));
        user.setExpenditure(findUserExpenditure(userId));
        return user;
    }

    @Override
    public IncomeDTO createUserIncome(Income income, String userId) {
        income.setIncome(income.getIncome());
        income.setBudget(income.getBudget());
        income.setUserId(userId);
        income.setCreatedDate(income.getCreatedDate());

        return modelMapper.map(incomeRepository.save(income), IncomeDTO.class);
    }

    @Override
    public ExpenditureDTO createUserExpenditure(Expenditure expenditure, String userId) {
        expenditure.setUserId(userId);
        expenditure.setRent(expenditure.getRent());
        expenditure.setFood(expenditure.getFood());
        expenditure.setTransport(expenditure.getTransport());
        expenditure.setHealth(expenditure.getHealth());
        expenditure.setSchoolFee(expenditure.getSchoolFee());
        expenditure.setShopping(expenditure.getShopping());
        expenditure.setEntertainment(expenditure.getEntertainment());
        expenditure.setTotal(expenditure.getTotal());

        //Getting percentages
        expenditure.setRentPercentage(expenditure.getRentPercentage());
        expenditure.setFoodPercentage(expenditure.getFoodPercentage());
        expenditure.setTransportPercentage(expenditure.getTransportPercentage());
        expenditure.setHealthPercentage(expenditure.getHealthPercentage());
        expenditure.setSchoolFeePercentage(expenditure.getSchoolFeePercentage());
        expenditure.setShoppingPercentage(expenditure.getShoppingPercentage());
        expenditure.setEntertainmentPercentage(expenditure.getEntertainmentPercentage());
        return modelMapper.map(expenditureRepository.save(expenditure), ExpenditureDTO.class);
    }

    @Override
    public IncomeDTO findUserIncome(String userId) {
        Optional<Income> income = incomeRepository.findUserIncome(userId);
        return modelMapper.map(income, IncomeDTO.class);
    }

    @Override
    public ExpenditureDTO findUserExpenditure(String userId) {
        Optional<Expenditure> expenditure = expenditureRepository.findUserExpenditure(userId);
        return modelMapper.map(expenditure, ExpenditureDTO.class);
    }
}
