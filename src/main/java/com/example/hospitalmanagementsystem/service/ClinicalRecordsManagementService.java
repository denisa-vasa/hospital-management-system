package com.example.hospitalmanagementsystem.service;

import com.example.hospitalmanagementsystem.dto.ClinicalDataDto;

import java.util.List;

public interface ClinicalRecordsManagementService {
    void saveClinicalRecord(ClinicalDataDto clinicalDataDto);

    List<ClinicalDataDto> filterClinicalRecord(ClinicalDataDto clinicalDataDto);
}
