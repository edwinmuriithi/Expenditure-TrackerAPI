package com.example.expendituretrackerapi.repositories;

import com.example.expendituretrackerapi.entities.Budget;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BudgetRepository extends JpaRepository<Budget,Long> {
}
