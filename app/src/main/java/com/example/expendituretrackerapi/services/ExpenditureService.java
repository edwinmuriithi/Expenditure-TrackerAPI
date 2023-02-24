package com.example.expendituretrackerapi.services;

import com.example.expendituretrackerapi.entities.Expenditure;
import com.example.expendituretrackerapi.exception.ExpenditureNotFoundException;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface ExpenditureService {
    Expenditure createExpenditure(Expenditure expenditure,Long incomeId);
    List<Expenditure> viewExpenditure();
    Expenditure findById(Long expenditureId) throws ExpenditureNotFoundException;
}
