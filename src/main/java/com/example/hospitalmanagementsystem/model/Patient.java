package com.example.hospitalmanagementsystem.model;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "patient")
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "birth_date")
    private LocalDate birthDate;

    @OneToMany(mappedBy = "patient")
    private List<AdmissionState> admissionStateList;

    @OneToMany(mappedBy = "patient")
    private List<ClinicalData> clinicalDataList;

    public Patient(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Patient(Long patientId, String existing, String patient, LocalDate of) {
        id = patientId;
        firstName = existing;
        lastName = patient;
        birthDate = of;
    }

    public Patient(long l, String patient6, String patient61) {
        id = l;
        firstName = patient6;
        lastName = patient61;
    }
}

