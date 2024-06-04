package com.example.hospitalmanagementsystem.service;

import com.example.hospitalmanagementsystem.dto.PatientDto;
import com.example.hospitalmanagementsystem.model.Patient;

import java.util.List;

public interface PatientsManagementService {
    Patient findById(Long id);

    void savePatient(PatientDto patientDto);

    List<PatientDto> filterPatients(PatientDto patientDto);

    void deletePatient(PatientDto patientDto);

    List<PatientDto> getAllPatients();
}
