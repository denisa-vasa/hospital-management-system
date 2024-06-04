package com.example.hospitalmanagementsystem.service.impl;

import com.example.hospitalmanagementsystem.dto.ClinicalDataDto;
import com.example.hospitalmanagementsystem.exception.NotFoundException;
import com.example.hospitalmanagementsystem.model.ClinicalData;
import com.example.hospitalmanagementsystem.repository.ClinicalDataRepository;
import com.example.hospitalmanagementsystem.service.AdmissionStateManagementService;
import com.example.hospitalmanagementsystem.service.ClinicalRecordsManagementService;
import com.example.hospitalmanagementsystem.service.DepartmentManagementService;
import com.example.hospitalmanagementsystem.service.PatientsManagementService;
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
    private PatientsManagementService patientsManagementService;

    @Autowired
    private DepartmentManagementService departmentManagementService;

    @Autowired
    private AdmissionStateManagementService admissionStateManagementService;

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

        clinicalData.setPatient(patientsManagementService.findById(clinicalDataDto.getPatientId()));
        clinicalData.setDepartment(departmentManagementService.findById(clinicalDataDto.getDepartmentId()));
        clinicalData.setAdmissionState(admissionStateManagementService.findById(clinicalDataDto.getAdmissionStateId()));

        clinicalDataRepository.save(clinicalData);
    }
}
