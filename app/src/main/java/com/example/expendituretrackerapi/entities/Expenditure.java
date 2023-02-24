package com.example.expendituretrackerapi.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

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
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "income_id")
    private Income income;
    @Column(nullable = false)
    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate createdDate;

    @PrePersist
    private void onCreate(){
        createdDate = LocalDate.now();
    }
}
