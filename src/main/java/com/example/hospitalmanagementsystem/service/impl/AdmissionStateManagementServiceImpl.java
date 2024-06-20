package com.example.hospitalmanagementsystem.service.impl;

import com.example.hospitalmanagementsystem.dto.AdmissionStateDto;
import com.example.hospitalmanagementsystem.dto.DischargeReasonDto;
import com.example.hospitalmanagementsystem.exception.BadRequestException;
import com.example.hospitalmanagementsystem.exception.NotFoundException;
import com.example.hospitalmanagementsystem.model.AdmissionState;
import com.example.hospitalmanagementsystem.repository.AdmissionStateRepository;
import com.example.hospitalmanagementsystem.service.AdmissionStateManagementService;
import com.example.hospitalmanagementsystem.service.DepartmentManagementService;
import com.example.hospitalmanagementsystem.service.PatientsManagementService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class AdmissionStateManagementServiceImpl implements AdmissionStateManagementService {

    @Autowired
    private AdmissionStateRepository admissionStateRepository;

    @Autowired
    private DepartmentManagementService departmentManagementService;

    @Autowired
    private PatientsManagementService patientsManagementService;

    @Override
    public AdmissionState findById(Long id) {
        if (id == null) {
            throw new BadRequestException("Admission State ID cannot be null");
        }
        return admissionStateRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Admission state with id " + id + " not found!"));
    }

    @Override
    public void setDischargeReason(DischargeReasonDto dischargeReasonDto) {
        if (dischargeReasonDto == null) {
            throw new BadRequestException("DischargeReasonDto cannot be null");
        }

        if (dischargeReasonDto.getId() == null) {
            throw new BadRequestException("Admission State ID cannot be null");
        }

        if (dischargeReasonDto.getReason() == null || dischargeReasonDto.getReason().isEmpty()) {
            throw new BadRequestException("Discharge reason cannot be null or empty");
        }

        Optional<AdmissionState> optionalAdmissionState = admissionStateRepository.findById(dischargeReasonDto.getId());
        if (optionalAdmissionState.isPresent()) {
            AdmissionState admissionState = optionalAdmissionState.get();
            admissionState.setReason(dischargeReasonDto.getReason());
            admissionState.setDischarge(true);
            admissionStateRepository.save(admissionState);
        } else {
            throw new NotFoundException("AdmissionState not found with id: " + dischargeReasonDto.getId());
        }
    }

    @Override
    public void saveCause(AdmissionStateDto admissionStateDto) {
        validateAdmissionStateDto(admissionStateDto);
        
        AdmissionState admissionState;

        if (admissionStateDto.getId() != null) {
            Optional<AdmissionState> existingAdmissionState = admissionStateRepository.findById(admissionStateDto.getId());
            if (existingAdmissionState.isPresent()) {
                admissionState = existingAdmissionState.get();
            } else {
                throw new NotFoundException("Admission State with id " + admissionStateDto.getId() + " not found");
            }
        } else {
            admissionState = new AdmissionState();
        }
        admissionState.setCause(admissionStateDto.getCause());
        admissionState.setDischarge(admissionStateDto.isDischarge());
        admissionState.setEnteringDate(admissionStateDto.getEnteringDate());
        admissionState.setExitingDate(admissionStateDto.getExitingDate());
        admissionState.setReason(admissionStateDto.getReason());
        admissionState.setDepartment(departmentManagementService.findById(admissionStateDto.getDepartmentId()));
        admissionState.setPatient(patientsManagementService.findById(admissionStateDto.getPatientId()));

        admissionStateRepository.save(admissionState);
    }

    private void validateAdmissionStateDto(AdmissionStateDto admissionStateDto) {
        if (admissionStateDto == null) {
            throw new BadRequestException("AdmissionStateDto cannot be null");
        }

        if (admissionStateDto.getPatientId() == null) {
            throw new BadRequestException("Patient ID cannot be null");
        }

        if (admissionStateDto.getDepartmentId() == null) {
            throw new BadRequestException("Department ID cannot be null");
        }

        if (admissionStateDto.getEnteringDate() == null) {
            throw new BadRequestException("Entering date cannot be null");
        }

        if (admissionStateDto.getExitingDate() == null) {
            throw new BadRequestException("Exiting date cannot be null");
        }

        if (admissionStateDto.getEnteringDate().isAfter(admissionStateDto.getExitingDate())) {
            throw new BadRequestException("Entering date cannot be after exiting date");
        }

        if (admissionStateDto.isDischarge() && (admissionStateDto.getReason() == null || admissionStateDto.getReason().isEmpty())) {
            throw new BadRequestException("Discharge reason cannot be null or empty when discharge is true");
        }
    }
}
