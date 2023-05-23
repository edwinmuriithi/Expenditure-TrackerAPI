package com.example.expendituretrackerapi.entities.dto;

import lombok.Data;

@Data
public class UserDTO {
    private String userId;
    private IncomeDTO income;
    private ExpenditureDTO expenditure;
}
