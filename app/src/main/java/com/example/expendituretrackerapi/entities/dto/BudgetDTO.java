package com.example.expendituretrackerapi.entities.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDate;

@Data
public class BudgetDTO {
    private Long id;
    private Integer budget;
    private IncomeDTO incomeDTO;
    @JsonFormat(pattern="yyyy-MM-dd ")
    private LocalDate createdDate;


}
