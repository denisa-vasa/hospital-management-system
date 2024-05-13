package com.example.hospitalmanagementsystem.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "admission_state")
public class AdmissionState {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "entering_date")
    private LocalDateTime enteringDate;

    @Column(name = "exiting_date")
    private LocalDateTime exitingDate;

    @Column(name = "cause")
    private String cause;

    @Column(name = "reason")
    private String reason;

    @Column(name = "discharge")
    private boolean discharge = false;

    @ManyToOne
    private Patient patient;

    @ManyToOne
    private Department department;

    @OneToMany(mappedBy = "admissionState")
    private List<ClinicalData> clinicalDataList;
}

