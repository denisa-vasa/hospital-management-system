package com.example.hospitalmanagementsystem.model;

import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "clinical_data")
public class ClinicalData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "clinical_record")
    private String clinicalRecord;

    @ManyToOne
    private Patient patient;

    @ManyToOne
    private Department department;

    @ManyToOne
    private AdmissionState admissionState;

}

