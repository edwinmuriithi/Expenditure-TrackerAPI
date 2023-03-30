package com.example.expendituretrackerapi.services.impl;

import com.example.expendituretrackerapi.entities.Expenditure;
import com.example.expendituretrackerapi.entities.Income;

import com.example.expendituretrackerapi.exception.ExpenditureNotFoundException;

import com.example.expendituretrackerapi.exception.IncomeNotFoundException;
import com.example.expendituretrackerapi.repositories.ExpenditureRepository;
import com.example.expendituretrackerapi.repositories.IncomeRepository;
import com.example.expendituretrackerapi.services.ExpenditureService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class ExpenditureServiceImpl implements ExpenditureService {

    @Autowired
    private ExpenditureRepository expenditureRepository;
    @Autowired
    private IncomeRepository incomeRepository;

    public ExpenditureServiceImpl(ExpenditureRepository expenditureRepository,IncomeRepository incomeRepository) {
        this.expenditureRepository = expenditureRepository;
        this.incomeRepository = incomeRepository;
    }

    @Override
    public Expenditure createExpenditure(Expenditure expenditure,Long incomeId) {
        Income income = incomeRepository.findById(incomeId).orElseThrow(()->
                new IncomeNotFoundException("not found income with id :" + incomeId));
        expenditure.setRent(expenditure.getRent());
        expenditure.setFood(expenditure.getFood());
        expenditure.setTransport(expenditure.getTransport());
        expenditure.setHealth(expenditure.getHealth());
        expenditure.setSchoolFee(expenditure.getSchoolFee());
        expenditure.setShopping(expenditure.getShopping());
        expenditure.setEntertainment(expenditure.getEntertainment());
        expenditure.setIncome(income);
        return expenditureRepository.save(expenditure);
    }

    public List<Expenditure> viewExpenditure() throws ExpenditureNotFoundException{
        List<Expenditure> expenditures = expenditureRepository.findAll();
        if (expenditures.isEmpty()){
            log.error("Expenditure is empty");
            throw new ExpenditureNotFoundException("There is no expenditure which has been saved");
        }   else {
            log.info("Expenditure has been retrieved successfully");
            return expenditures;
        }
    }

    @Override
    public Expenditure findById(Long expenditureId) throws ExpenditureNotFoundException {
        Optional<Expenditure> expenditure = expenditureRepository.findById(expenditureId);
        if(expenditure.isPresent()){
            log.info("Fetched Expenditure successfully");
            return expenditure.get();
        }else {
            log.error("Expenditure ID not found");
            throw new ExpenditureNotFoundException("Expenditure not found with ID: " + expenditureId);
        }

    }

    @Override
    public void deleteExpenditureById(Long expenditureId) throws ExpenditureNotFoundException {
        expenditureRepository.findById(expenditureId).orElseThrow(()->new ExpenditureNotFoundException("Expenditure with ID "
        +expenditureId+" not found"));
        log.info("Successfully deleted expenditure");
        expenditureRepository.deleteById(expenditureId);
    }

    @Override
    public Expenditure updateExpenditureById(Expenditure expenditure, Long expenditureId) throws ExpenditureNotFoundException {
        Expenditure existingExpenditure = expenditureRepository.findById(expenditureId).orElseThrow(()->
                new ExpenditureNotFoundException("Expenditure with ID "+expenditureId+" not found"));
        existingExpenditure.setRent(expenditure.getRent());
        existingExpenditure.setFood(expenditure.getFood());
        existingExpenditure.setTransport(expenditure.getTransport());
        existingExpenditure.setHealth(expenditure.getHealth());
        existingExpenditure.setSchoolFee(expenditure.getSchoolFee());
        existingExpenditure.setShopping(expenditure.getShopping());
        existingExpenditure.setEntertainment(expenditure.getEntertainment());
        Expenditure newExpenditure = expenditureRepository.save(existingExpenditure);
        log.info("Expenditure updated successfully {}",newExpenditure);
        return newExpenditure;
}
    @Override
    public Integer getTotalExpenditureById(Long expenditureId){
        return expenditureRepository.getTotalExpenditureById(expenditureId);
    }
}

