package com.example.hospitalmanagementsystem.service;

import com.example.hospitalmanagementsystem.model.AdmissionState;

public interface AdmissionStateManagementService {
    AdmissionState findById(Long id);
}
