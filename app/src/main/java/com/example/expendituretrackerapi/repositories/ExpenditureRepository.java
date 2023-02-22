package com.example.expendituretrackerapi.repositories;

import com.example.expendituretrackerapi.entities.Expenditure;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExpenditureRepository extends JpaRepository<Expenditure, Long> {
}
