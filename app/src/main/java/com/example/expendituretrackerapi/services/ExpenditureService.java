package com.example.expendituretrackerapi.services;

import com.example.expendituretrackerapi.entities.Expenditure;
import com.example.expendituretrackerapi.exception.ExpenditureNotFoundException;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface ExpenditureService {
    Expenditure createExpenditure(Expenditure expenditure,Long incomeId);

    //int getTotalExpenditure(Expenditure expenditure);

    List<Expenditure> viewExpenditure() throws ExpenditureNotFoundException;
    Integer getTotalExpenditureById(Long expenditureID);

    Expenditure findById(Long expenditureId) throws ExpenditureNotFoundException;
    void deleteExpenditureById(Long expenditureId)throws ExpenditureNotFoundException;
    Expenditure updateExpenditureById(Expenditure expenditure, Long expenditureId)throws ExpenditureNotFoundException;



}
