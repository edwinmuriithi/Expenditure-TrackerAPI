package com.example.expendituretrackerapi.repositories;

import com.example.expendituretrackerapi.entities.Expenditure;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ExpenditureRepository extends JpaRepository<Expenditure, Long> {

    Optional<Expenditure> findById(Long expenditureId);
   @Query("SELECT SUM(expenditure.food+expenditure.rent+expenditure.transport+expenditure.schoolFee+expenditure.shopping+expenditure.entertainment+expenditure.health)FROM Expenditure expenditure")
    Integer getTotalExpenditureById(Long expenditureId);


}
