package com.example.hospitalmanagementsystem.service.impl;

import com.example.hospitalmanagementsystem.dto.LongDto;
import com.example.hospitalmanagementsystem.dto.PatientDto;
import com.example.hospitalmanagementsystem.exception.BadRequestException;
import com.example.hospitalmanagementsystem.exception.NotFoundException;
import com.example.hospitalmanagementsystem.model.AdmissionState;
import com.example.hospitalmanagementsystem.model.Department;
import com.example.hospitalmanagementsystem.model.Patient;
import com.example.hospitalmanagementsystem.repository.PatientsRepository;
import com.example.hospitalmanagementsystem.service.PatientsManagementService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class PatientsManagementServiceImpl implements PatientsManagementService {

    @Autowired
    private PatientsRepository patientsRepository;


    @Override
    public Patient findById(Long id) {
        if (id == null) {
            throw new BadRequestException("Patient ID cannot be null");
        }

        return patientsRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Patient with id " + id + "not found"));
    }

    @Override
    public void savePatient(PatientDto patientDto) {
        validatePatientDto(patientDto);

        Patient patient;

        if (patientDto.getId() != null) {
            Optional<Patient> existingPatientOpt = patientsRepository.findById(patientDto.getId());
            if (existingPatientOpt.isPresent()) {
                patient = existingPatientOpt.get();
                patient.setFirstName(patientDto.getFirstName());
                patient.setLastName(patientDto.getLastName());
                patient.setBirthDate(patientDto.getBirthDate());
            } else {
                throw new NotFoundException("Patient with id " + patientDto.getId() + "not found");
            }
        } else {
            patient = new Patient();
            patient.setFirstName(patientDto.getFirstName());
            patient.setLastName(patientDto.getLastName());
            patient.setBirthDate(patientDto.getBirthDate());
        }

        patientsRepository.save(patient);
    }

    @Override
    public List<PatientDto> filterPatients(PatientDto patientDto) {
        if (patientDto == null) {
            throw new BadRequestException("Filter criteria cannot be null");
        }
        return toListOfPatientsDto(patientsRepository.filter(patientDto.getFirstName(), patientDto.getLastName()));
    }

    @Override
    @Transactional
    public void deletePatient(PatientDto patientDto) {
        if (patientDto == null || !StringUtils.hasText(patientDto.getFirstName()) || !StringUtils.hasText(patientDto.getLastName())) {
            throw new BadRequestException("Patient's name or lastname is empty!");
        }
        patientsRepository.deleteByFirstNameAndLastName(patientDto.getFirstName(), patientDto.getLastName());
    }

    @Override
    public List<PatientDto> getAllPatients() {
        List<Patient> patientList = patientsRepository.findAll();
        return toListOfPatientsDto(patientList);
    }

    @Override
    public PatientDto getPatientDtoById(LongDto longDto) {
        if (longDto == null || longDto.getId() == null) {
            throw new BadRequestException("ID cannot be null");
        }

        Patient patient = patientsRepository.findById(longDto.getId())
                .orElseThrow(() -> new NotFoundException("Patient not found"));

        AdmissionState latestAdmissionState = patient.getAdmissionStateList().stream()
                .max(Comparator.comparing(AdmissionState::getEnteringDate))
                .orElse(null);

        Long departmentId = null;
        Long admissionStateId = null;

        if (latestAdmissionState != null) {
            admissionStateId = latestAdmissionState.getId();
            // Assuming AdmissionState has a reference to Department
            Department department = latestAdmissionState.getDepartment();
            if (department != null) {
                departmentId = department.getId();
            }
        }

        return new PatientDto(
                patient.getId(),
                patient.getFirstName(),
                patient.getLastName(),
                patient.getBirthDate(),
                departmentId,
                admissionStateId
        );
    }

    private List<PatientDto> toListOfPatientsDto(List<Patient> patients) {
        List<PatientDto> dtos = new ArrayList<>();
        patients.forEach(p -> dtos.add(new PatientDto(p)));
        return dtos;
    }

    private void validatePatientDto(PatientDto patientDto) {
        if (patientDto == null) {
            throw new BadRequestException("PatientDto cannot be null");
        }

        if (!StringUtils.hasText(patientDto.getFirstName())) {
            throw new BadRequestException("Patient's first name cannot be empty");
        }

        if (!StringUtils.hasText(patientDto.getLastName())) {
            throw new BadRequestException("Patient's last name cannot be empty");
        }

        if (patientDto.getBirthDate() == null) {
            throw new BadRequestException("Patient's birth date cannot be null");
        }
    }
}
