package com.example.hospitalmanagementsystem.service.impl;

import com.example.hospitalmanagementsystem.dto.ClinicalDataDto;
import com.example.hospitalmanagementsystem.exception.NotFoundException;
import com.example.hospitalmanagementsystem.model.AdmissionState;
import com.example.hospitalmanagementsystem.model.ClinicalData;
import com.example.hospitalmanagementsystem.repository.AdmissionStateRepository;
import com.example.hospitalmanagementsystem.repository.ClinicalDataRepository;
import com.example.hospitalmanagementsystem.repository.DepartmentRepository;
import com.example.hospitalmanagementsystem.repository.PatientsRepository;
import com.example.hospitalmanagementsystem.service.ClinicalRecordsManagementService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class ClinicalRecordsManagementServiceImpl implements ClinicalRecordsManagementService {

    @Autowired
    private ClinicalDataRepository clinicalDataRepository;

    @Autowired
    private PatientsRepository patientsRepository;

    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private AdmissionStateRepository admissionStateRepository;

    @Override
    public void saveClinicalRecord(ClinicalDataDto clinicalDataDto) {
        ClinicalData clinicalData;

        if (clinicalDataDto.getId() != null) {
            Optional<ClinicalData> existingClinicalDataOpt = clinicalDataRepository.findById(clinicalDataDto.getId());
            if (existingClinicalDataOpt.isPresent()) {
                clinicalData = existingClinicalDataOpt.get();
                clinicalData.setClinicalRecord(clinicalDataDto.getClinicalRecord());
            } else {
                throw new NotFoundException("Clinical Record with id " + clinicalDataDto.getId() + " not found!");
            }
        } else {
            clinicalData = new ClinicalData();
            clinicalData.setClinicalRecord(clinicalDataDto.getClinicalRecord());
        }

        clinicalData.setPatient(patientsRepository.findById());

        clinicalDataRepository.save(clinicalData);
    }
}
