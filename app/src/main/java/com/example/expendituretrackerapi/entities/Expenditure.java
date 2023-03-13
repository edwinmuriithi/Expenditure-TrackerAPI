package com.example.expendituretrackerapi.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "expenditure")
@AllArgsConstructor
@NoArgsConstructor
public class Expenditure {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private int rent;
    private int food;
    private int transport;
    private int health;
    private int schoolFee;
    private int shopping;
    private int entertainment;

    @OneToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name = "income_id")
    private Income income;
    @Column(nullable = false)
    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate createdDate;

    public int getTotal(){
        return rent+food+transport+health+schoolFee+shopping+entertainment;
    }
    @PrePersist
    private void onCreate(){
        createdDate = LocalDate.now();
    }
}
