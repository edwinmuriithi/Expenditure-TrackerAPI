package com.example.expendituretrackerapi.entities.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDate;

@Data
public class IncomeDTO {
    private Long id;
    private Integer income;
    private ExpenditureDTO expenditure;
    private Integer budget;
    @JsonFormat(pattern="yyyy-MM-dd ")
    private LocalDate createdDate;

}
