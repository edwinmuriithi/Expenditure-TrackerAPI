package com.example.expendituretrackerapi.services.impl;

import com.example.expendituretrackerapi.entities.Expenditure;
import com.example.expendituretrackerapi.exception.ExpenditureNotFoundException;
import com.example.expendituretrackerapi.repositories.ExpenditureRepository;
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
    private final ExpenditureRepository expenditureRepository;

    public ExpenditureServiceImpl(ExpenditureRepository expenditureRepository) {
        this.expenditureRepository = expenditureRepository;
    }

    @Override
    public Expenditure createExpenditure(Expenditure expenditure, Long incomeId) {
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
        existingExpenditure.setTotal(expenditure.getTotal());
        Expenditure newExpenditure = expenditureRepository.save(existingExpenditure);
        log.info("Expenditure updated successfully {}",newExpenditure);
        return newExpenditure;
}
}

