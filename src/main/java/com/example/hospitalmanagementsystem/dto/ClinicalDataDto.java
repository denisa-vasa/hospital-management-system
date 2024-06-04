package com.example.hospitalmanagementsystem.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClinicalDataDto {
    private Long id;
    private String clinicalRecord;
    private Long patientId;
    private Long departmentId;
    private Long admissionStateId;
}
