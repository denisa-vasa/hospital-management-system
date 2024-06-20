package com.example.hospitalmanagementsystem.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "department")
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "code")
    private String code;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "department")
    private List<AdmissionState> admissionStateList;

    @OneToMany(mappedBy = "department")
    private List<ClinicalData> clinicalDataList;

    public Department(long l, String cardiology, String card) {
        id = l;
        name = cardiology;
        code = card;
    }
}
