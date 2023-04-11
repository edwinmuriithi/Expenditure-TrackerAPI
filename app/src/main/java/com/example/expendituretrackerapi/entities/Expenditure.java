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
    private Integer total;

    @OneToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name = "income_id")
    private Income income;
    @Column(nullable = false)
    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate createdDate;

    private  Integer rentPercentage;
    private Integer foodPercentage;
    private Integer transportPercentage;
    private Integer healthPercentage;
    private Integer schoolFeePercentage;
    private Integer shoppingPercentage;
    private Integer entertainmentPercentage;


    public Integer getRentPercentage() {
        return (rent*100)/total;
    }

    public Integer getFoodPercentage() {
        return (food*100)/total;
    }

    public Integer getTransportPercentage() {
        return (transport*100)/total;
    }

    public Integer getHealthPercentage() {
        return (health*100)/total;
    }

    public Integer getSchoolFeePercentage() {
        return (schoolFee*100)/total;
    }

    public Integer getShoppingPercentage() {
        return (shopping*100)/total;
    }

    public Integer getEntertainmentPercentage() {
        return (entertainment*100)/total;
    }

    public Integer getTotal(){
        return rent+food+transport+health+schoolFee+shopping+entertainment;
    }

    @PrePersist
    private void onCreate(){
        createdDate = LocalDate.now();
    }

}
