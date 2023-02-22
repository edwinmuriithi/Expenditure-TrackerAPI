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
    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate createdDate;

}
