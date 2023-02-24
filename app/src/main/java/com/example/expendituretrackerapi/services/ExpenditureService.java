package com.example.expendituretrackerapi.services;

import com.example.expendituretrackerapi.entities.Expenditure;

import java.util.List;

public interface ExpenditureService {
    Expenditure createExpenditure(Expenditure expenditure,Long incomeId);
    List<Expenditure> viewExpenditure();
}
