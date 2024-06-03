package com.example.hospitalmanagementsystem.service;

import com.example.hospitalmanagementsystem.dto.PatientDto;

import java.util.List;

public interface PatientsManagementService {
    void savePatient(PatientDto patientDto);

    List<PatientDto> filterPatients(PatientDto patientDto);

    void deletePatient(PatientDto patientDto);

    List<PatientDto> getAllPatients();
}
