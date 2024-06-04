package com.example.hospitalmanagementsystem.service;

import com.example.hospitalmanagementsystem.dto.ClinicalDataDto;
import com.example.hospitalmanagementsystem.dto.LongDto;

import java.util.List;

public interface ClinicalRecordsManagementService {
    void saveClinicalRecord(ClinicalDataDto clinicalDataDto);

    List<ClinicalDataDto> filterClinicalRecord(ClinicalDataDto clinicalDataDto);

    void deleteClinicalRecord(LongDto longDto);
}
