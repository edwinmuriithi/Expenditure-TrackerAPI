package com.example.expendituretrackerapi.services.impl;

import com.example.expendituretrackerapi.entities.Expenditure;
import com.example.expendituretrackerapi.entities.Income;
import com.example.expendituretrackerapi.entities.dto.ExpenditureDTO;
import com.example.expendituretrackerapi.entities.dto.IncomeDTO;
import com.example.expendituretrackerapi.entities.dto.UserDTO;
import com.example.expendituretrackerapi.exception.ExpenditureNotFoundException;
import com.example.expendituretrackerapi.exception.UserDetailNotFoundException;
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
        IncomeDTO existingIncome = findUserIncome(userId);

        // Replace existing income with new income data --to be refactored
        if(existingIncome != null){
            Income oldIncome = incomeRepository.findById(existingIncome.getId()).get();
            return initUpdatedIncome(oldIncome, income);
        }

        return initIncome(income, userId);
    }

    private IncomeDTO initIncome(Income income, String userId){
        income.setIncome(income.getIncome());
        income.setBudget(income.getBudget());
        income.setUserId(userId);

        return modelMapper.map(incomeRepository.save(income), IncomeDTO.class);
    }

    private IncomeDTO initUpdatedIncome(Income existingIncome, Income newIncome){
        existingIncome.setIncome(newIncome.getIncome());
        existingIncome.setBudget(newIncome.getBudget());
        return modelMapper.map(incomeRepository.save(existingIncome), IncomeDTO.class);
    }

    @Override
    public ExpenditureDTO createUserExpenditure(Expenditure expenditure, String userId) {
        ExpenditureDTO existingExpenditure = findUserExpenditure(userId);

        // Replace existing expenditure with new expenditure data --To be refactored
        if(existingExpenditure != null){
            Expenditure oldExpenditure = expenditureRepository.findById(existingExpenditure.getId()).get();
            return initUpdatedExpenditure(oldExpenditure, expenditure);
        }

        return initExpenditure(expenditure, userId);
    }

    private ExpenditureDTO initExpenditure(Expenditure expenditure, String userId){
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

    private ExpenditureDTO initUpdatedExpenditure(Expenditure existingExpenditure, Expenditure expenditure){
        existingExpenditure.setRent(expenditure.getRent());
        existingExpenditure.setFood(expenditure.getFood());
        existingExpenditure.setTransport(expenditure.getTransport());
        existingExpenditure.setHealth(expenditure.getHealth());
        existingExpenditure.setSchoolFee(expenditure.getSchoolFee());
        existingExpenditure.setShopping(expenditure.getShopping());
        existingExpenditure.setEntertainment(expenditure.getEntertainment());
        existingExpenditure.setTotal(existingExpenditure.getTotal());

        //Getting percentages
        existingExpenditure.setRentPercentage(existingExpenditure.getRentPercentage());
        existingExpenditure.setFoodPercentage(existingExpenditure.getFoodPercentage());
        existingExpenditure.setTransportPercentage(existingExpenditure.getTransportPercentage());
        existingExpenditure.setHealthPercentage(existingExpenditure.getHealthPercentage());
        existingExpenditure.setSchoolFeePercentage(existingExpenditure.getSchoolFeePercentage());
        existingExpenditure.setShoppingPercentage(existingExpenditure.getShoppingPercentage());
        existingExpenditure.setEntertainmentPercentage(existingExpenditure.getEntertainmentPercentage());
        return modelMapper.map(expenditureRepository.save(existingExpenditure), ExpenditureDTO.class);
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

    @Override
    public IncomeDTO updateUserIncome(Income income, String userId) {
        Income existingIncome = incomeRepository.findUserIncome(userId).orElseThrow(()->
                new UserDetailNotFoundException("User with id"+userId+"not found"));
        existingIncome.setIncome(income.getIncome());
        existingIncome.setBudget(income.getBudget());
        Income newIncome = incomeRepository.save(existingIncome);
        return modelMapper.map(newIncome,IncomeDTO.class);
    }

    @Override
    public ExpenditureDTO updateUserExpenditure(Expenditure expenditure, String userId) {
        Expenditure existingExpenditure = expenditureRepository.findUserExpenditure(userId).orElseThrow(()->
                new ExpenditureNotFoundException("Expenditure with ID "+userId+" not found"));
        existingExpenditure.setRent(expenditure.getRent());
        existingExpenditure.setFood(expenditure.getFood());
        existingExpenditure.setTransport(expenditure.getTransport());
        existingExpenditure.setHealth(expenditure.getHealth());
        existingExpenditure.setSchoolFee(expenditure.getSchoolFee());
        existingExpenditure.setShopping(expenditure.getShopping());
        existingExpenditure.setEntertainment(expenditure.getEntertainment());
        existingExpenditure.setTotal(expenditure.getTotal());
        Expenditure newExpenditure = expenditureRepository.save(existingExpenditure);
        log.info("Expenditure updated successfully {}",newExpenditure);
        return modelMapper.map(newExpenditure,ExpenditureDTO.class);

    }

}
