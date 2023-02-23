package com.example.expendituretrackerapi.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@Table(name = "budget")
@NoArgsConstructor
@AllArgsConstructor
public class Budget {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Integer budget;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "income_id")
    private Income income;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date createdDate;

    @PrePersist
    private void onCreate(){
        createdDate = new Date();
    }


}
