package com.example.hospitalmanagementsystem.service;

import com.example.hospitalmanagementsystem.dto.AdmissionStateDto;
import com.example.hospitalmanagementsystem.dto.DischargeReasonDto;
import com.example.hospitalmanagementsystem.model.AdmissionState;

public interface AdmissionStateManagementService {
    AdmissionState findById(Long id);

    void setDischargeReason(DischargeReasonDto dischargeReasonDto);

    void saveCause(AdmissionStateDto admissionStateDto);
}
