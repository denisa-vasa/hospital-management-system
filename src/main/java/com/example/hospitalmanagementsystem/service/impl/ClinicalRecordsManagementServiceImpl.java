package com.example.hospitalmanagementsystem.service.impl;

import com.example.hospitalmanagementsystem.dto.ClinicalDataDto;
import com.example.hospitalmanagementsystem.dto.LongDto;
import com.example.hospitalmanagementsystem.dto.PatientDto;
import com.example.hospitalmanagementsystem.exception.BadRequestException;
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
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
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

    public void findById(Long id) {
        if (id == null) {
            throw new BadRequestException("Clinical Record ID cannot be null");
        }
        clinicalDataRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Clinical Record with id " + id + " not found!"));
    }

    @Transactional
    @Override
    public void saveClinicalRecord(ClinicalDataDto clinicalDataDto) {
        validateClinicalDataDto(clinicalDataDto);
        // Fetch patient details including departmentId and admissionStateId
        PatientDto patientDto = patientsManagementService.getPatientDtoById(new LongDto(clinicalDataDto.getPatientId()));

        if (patientDto == null) {
            throw new NotFoundException("Patient with id " + clinicalDataDto.getPatientId() + " not found");
        }

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

    @Override
    public List<ClinicalDataDto> filterClinicalRecord(ClinicalDataDto clinicalDataDto) {
        if (clinicalDataDto == null || !StringUtils.hasText(clinicalDataDto.getClinicalRecord())) {
            throw new BadRequestException("Filter criteria cannot be null or empty");
        }
        return toListOfClinicalDataDto(clinicalDataRepository.filter(clinicalDataDto.getClinicalRecord()));
    }

    @Override
    public void deleteClinicalRecord(LongDto longDto) {
        if (longDto == null || longDto.getId() == null) {
            throw new BadRequestException("ID cannot be null");
        }

        findById(longDto.getId());
        clinicalDataRepository.deleteById(longDto.getId());
    }

    @Override
    public List<ClinicalDataDto> getAllClinicalRecords() {
        List<ClinicalData> clinicalDataList = clinicalDataRepository.findAll();
        return toListOfClinicalDataDto(clinicalDataList);
    }

    @Override
    public List<ClinicalDataDto> getClinicalRecordsByPatientName(PatientDto patientDto) {
        if (patientDto == null || !StringUtils.hasText(patientDto.getFirstName()) || !StringUtils.hasText(patientDto.getLastName())) {
            throw new BadRequestException("Patient's first name or last name cannot be null or empty");
        }
        List<ClinicalData> clinicalDataList = clinicalDataRepository.findByPatientFirstNameAndPatientLastName(patientDto.getFirstName(), patientDto.getLastName());
        return toListOfClinicalDataDto(clinicalDataList);
    }

    public List<ClinicalDataDto> toListOfClinicalDataDto(List<ClinicalData> clinicalDataList) {
        List<ClinicalDataDto> dtos = new ArrayList<>();
        clinicalDataList.forEach(c -> dtos.add(new ClinicalDataDto(c)));
        return dtos;
    }

    private void validateClinicalDataDto(ClinicalDataDto clinicalDataDto) {
        if (clinicalDataDto == null) {
            throw new BadRequestException("ClinicalDataDto cannot be null");
        }

        if (clinicalDataDto.getPatientId() == null) {
            throw new BadRequestException("Patient ID cannot be null");
        }

        if (clinicalDataDto.getDepartmentId() == null) {
            throw new BadRequestException("Department ID cannot be null");
        }

        if (clinicalDataDto.getAdmissionStateId() == null) {
            throw new BadRequestException("Admission State ID cannot be null");
        }

        if (!StringUtils.hasText(clinicalDataDto.getClinicalRecord())) {
            throw new BadRequestException("Clinical record cannot be empty");
        }
    }
}
