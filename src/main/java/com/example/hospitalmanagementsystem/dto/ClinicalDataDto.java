package com.example.hospitalmanagementsystem.dto;

import com.example.hospitalmanagementsystem.model.ClinicalData;
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

    public ClinicalDataDto(ClinicalData c) {
        id = c.getId();
        clinicalRecord = c.getClinicalRecord();
        patientId = c.getPatient().getId();
        departmentId = c.getDepartment().getId();
        admissionStateId = c.getAdmissionState().getId();
    }
}
