package com.example.expendituretrackerapi.repositories;

import com.example.expendituretrackerapi.entities.Income;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IncomeRepository extends JpaRepository<Income, Long>{
    Optional<Income> findById(Long incomeId);
}
