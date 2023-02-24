package com.example.expendituretrackerapi.services;

import com.example.expendituretrackerapi.entities.Expenditure;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface ExpenditureService {
    Expenditure createExpenditure(Expenditure expenditure);
    List<Expenditure> viewExpenditure();
}
