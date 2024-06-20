package com.example.hospitalmanagementsystem.service.stub;

import com.example.hospitalmanagementsystem.dto.AdmissionStateDto;
import com.example.hospitalmanagementsystem.dto.DischargeReasonDto;
import com.example.hospitalmanagementsystem.exception.BadRequestException;
import com.example.hospitalmanagementsystem.exception.NotFoundException;
import com.example.hospitalmanagementsystem.model.AdmissionState;
import com.example.hospitalmanagementsystem.service.AdmissionStateManagementService;

import java.util.ArrayList;
import java.util.List;

public class AdmissionStateManagementServiceStub implements AdmissionStateManagementService {

    private List<AdmissionState> admissionStates = new ArrayList<>();
    private Long nextId = 1L;

    public AdmissionStateManagementServiceStub() {
        // Add admission states for testing
        AdmissionState admissionState1 = new AdmissionState();
        admissionState1.setId(1L);
        admissionState1.setCause("Test Cause");
        admissionState1.setDischarge(false);
        admissionStates.add(admissionState1);
    }

    @Override
    public AdmissionState findById(Long id) {
        if (id == null) {
            throw new BadRequestException("Admission State ID cannot be null");
        }

        return admissionStates.stream()
                .filter(a -> a.getId().equals(id))
                .findFirst()
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

        AdmissionState admissionState = findById(dischargeReasonDto.getId());
        admissionState.setReason(dischargeReasonDto.getReason());
        admissionState.setDischarge(true);
    }

    @Override
    public void saveCause(AdmissionStateDto admissionStateDto) {
        validateAdmissionStateDto(admissionStateDto);

        AdmissionState admissionState;

        if (admissionStateDto.getId() != null) {
            admissionState = findById(admissionStateDto.getId());
        } else {
            admissionState = new AdmissionState();
            admissionState.setId(nextId++);
            admissionStates.add(admissionState);
        }

        admissionState.setCause(admissionStateDto.getCause());
        admissionState.setDischarge(admissionStateDto.isDischarge());
        admissionState.setEnteringDate(admissionStateDto.getEnteringDate());
        admissionState.setExitingDate(admissionStateDto.getExitingDate());
        admissionState.setReason(admissionStateDto.getReason());
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
