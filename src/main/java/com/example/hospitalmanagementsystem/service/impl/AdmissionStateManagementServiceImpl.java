package com.example.hospitalmanagementsystem.service.impl;

import com.example.hospitalmanagementsystem.dto.AdmissionStateDto;
import com.example.hospitalmanagementsystem.dto.DischargeReasonDto;
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
        return admissionStateRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Admission state with id " + id + " not found!"));
    }

    @Override
    public void setDischargeReason(DischargeReasonDto dischargeReasonDto) {
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
}
