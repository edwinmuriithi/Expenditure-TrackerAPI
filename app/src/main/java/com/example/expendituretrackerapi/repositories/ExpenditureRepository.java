package com.example.expendituretrackerapi.repositories;

import com.example.expendituretrackerapi.entities.Expenditure;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ExpenditureRepository extends JpaRepository<Expenditure, Long> {

    Optional<Expenditure> findById(Long expenditureId);

}
