package com.example.expendituretrackerapi.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

@Builder
@Data
@Entity
@Table(name = "expenditure")
@AllArgsConstructor
@NoArgsConstructor
public class Expenditure {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Integer rent;
    private Integer food;
    private Integer transport;
    private Integer health;
    private Integer schoolFee;
    private Integer shopping;
    private Integer entertainment;

    @OneToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name = "income_id")
    private Income income;
    @Column(nullable = false)
    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate createdDate;

    public Integer getTotal(){
        return rent+food+transport+health+schoolFee+shopping+entertainment;
    }
    @PrePersist
    private void onCreate(){
        createdDate = LocalDate.now();
    }

}
