package com.example.expendituretrackerapi.entities.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDate;

@Data
public class ExpenditureDTO {
    private Long id;
    private Integer rent;
    private Integer food;
    private Integer transport;
    private Integer health;
    private Integer schoolFee;
    private Integer shopping;
    private Integer entertainment;
    private Integer total;

    private  Integer rentPercentage;
    private Integer foodPercentage;
    private Integer transportPercentage;
    private Integer healthPercentage;
    private Integer schoolFeePercentage;
    private Integer shoppingPercentage;
    private Integer entertainmentPercentage;
    private IncomeDTO income;
    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate createdDate;

}
