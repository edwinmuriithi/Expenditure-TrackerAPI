package com.example.expendituretrackerapi.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "income")
public class Income {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Integer income;
    private Integer budget;
    @Temporal(TemporalType.TIMESTAMP)
    @OneToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY,mappedBy = "income")
    @JoinColumn(name = "expenditure_id")
    private Expenditure expenditure;

    @Column(nullable = false)
    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate createdDate;

    @PrePersist
    private void onCreate(){
        createdDate =  LocalDate.now();
    }



}
