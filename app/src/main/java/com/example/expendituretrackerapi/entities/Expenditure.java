package com.example.expendituretrackerapi.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.text.DecimalFormat;
import java.time.LocalDate;

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

    private  Double rentPercentage;
    private  Double foodPercentage;
    private  Double transportPercentage;
    private  Double healthPercentage;
    private  Double schoolFeePercentage;
    private  Double shoppingPercentage;
    private  Double entertainmentPercentage;

    private static final DecimalFormat df = new DecimalFormat("65.87");

    public  Double getRentPercentage() {
        double rentValue = (rent*100)/total;
        return  Math.round(rentValue*100.0)/100.0;
    }

    public  Double getFoodPercentage() {
        double foodP =  Math.round(((food*100)/total)*100.0)/100.0;
        return  foodP;
    }

    public  Double getTransportPercentage() {
        double transportValue = (transport*100)/total;
        return  Math.round(transportValue*100.0)/100.0;
    }

    public  Double getHealthPercentage() {
        double healthP = Math.round(((health*100)/total)*100.0)/100.0;
        return healthP;
    }

    public  Double getSchoolFeePercentage() {
        return  Math.round(((schoolFee*100)/total)*100.0)/100.0;
    }

    public  Double getShoppingPercentage() {
        return  Math.round(((shopping*100)/total)*100.0)/100.0;
    }

    public  Double getEntertainmentPercentage() {
        return Math.round(((entertainment*100)/total)*100.0)/100.0;
    }

    public Integer getTotal(){
        return rent+food+transport+health+schoolFee+shopping+entertainment;
    }

    @PrePersist
    private void onCreate(){
        createdDate = LocalDate.now();
    }

}
