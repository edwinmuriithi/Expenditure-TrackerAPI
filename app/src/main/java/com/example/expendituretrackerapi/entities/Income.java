package com.example.expendituretrackerapi.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;


@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "income")
public class Income {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull(message = "should not be blank")
    private Integer income;
    @NotNull(message = "Budget should not be blank")
    private Integer budget;
    @Column(nullable = false)

    
    private LocalDate createdDate;

    @PrePersist
    private void onCreate(){
        createdDate =  LocalDate.now();
    }



}
