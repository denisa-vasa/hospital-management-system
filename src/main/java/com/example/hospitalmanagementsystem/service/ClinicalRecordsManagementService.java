package com.example.hospitalmanagementsystem.service;

import com.example.hospitalmanagementsystem.dto.ClinicalDataDto;

public interface ClinicalRecordsManagementService {
    void saveClinicalRecord(ClinicalDataDto clinicalDataDto);
}
