package com.example.hospitalmanagementsystem.service.stub;

import com.example.hospitalmanagementsystem.dto.LongDto;
import com.example.hospitalmanagementsystem.dto.PatientDto;
import com.example.hospitalmanagementsystem.exception.BadRequestException;
import com.example.hospitalmanagementsystem.exception.NotFoundException;
import com.example.hospitalmanagementsystem.model.Patient;
import com.example.hospitalmanagementsystem.service.PatientsManagementService;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PatientsManagementServiceStub implements PatientsManagementService {

    private List<Patient> patients = new ArrayList<>();
    private Long nextId = 1L;

    public PatientsManagementServiceStub() {
        // Add patients for testing
        Patient patient1 = new Patient();
        patient1.setId(1L);
        patient1.setFirstName("John");
        patient1.setLastName("Doe");
        patients.add(patient1);
    }

    @Override
    public Patient findById(Long id) {
        if (id == null) {
            throw new BadRequestException("Patient ID cannot be null");
        }

        return patients.stream()
                .filter(p -> p.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new NotFoundException("Patient with id " + id + " not found"));
    }

    @Override
    public void savePatient(PatientDto patientDto) {
        validatePatientDto(patientDto);

        Patient patient;

        if (patientDto.getId() != null) {
            Optional<Patient> existingPatientOpt = patients.stream()
                    .filter(p -> p.getId().equals(patientDto.getId()))
                    .findFirst();
            if (existingPatientOpt.isPresent()) {
                patient = existingPatientOpt.get();
                patient.setFirstName(patientDto.getFirstName());
                patient.setLastName(patientDto.getLastName());
                patient.setBirthDate(patientDto.getBirthDate());
            } else {
                throw new NotFoundException("Patient with id " + patientDto.getId() + " not found");
            }
        } else {
            patient = new Patient();
            patient.setId(nextId++);
            patient.setFirstName(patientDto.getFirstName());
            patient.setLastName(patientDto.getLastName());
            patient.setBirthDate(patientDto.getBirthDate());
            patients.add(patient);
        }
    }

    @Override
    public List<PatientDto> filterPatients(PatientDto patientDto) {
        if (patientDto == null) {
            throw new BadRequestException("Filter criteria cannot be null");
        }
        List<PatientDto> result = new ArrayList<>();
        for (Patient patient : patients) {
            if ((patientDto.getFirstName() == null || patient.getFirstName().equals(patientDto.getFirstName())) &&
                    (patientDto.getLastName() == null || patient.getLastName().equals(patientDto.getLastName()))) {
                result.add(new PatientDto(patient));
            }
        }
        return result;
    }

    @Override
    public void deletePatient(PatientDto patientDto) {
        if (patientDto == null || !StringUtils.hasText(patientDto.getFirstName()) || !StringUtils.hasText(patientDto.getLastName())) {
            throw new BadRequestException("Patient's name or lastname is empty!");
        }
        patients.removeIf(p -> p.getFirstName().equals(patientDto.getFirstName()) && p.getLastName().equals(patientDto.getLastName()));
    }

    @Override
    public List<PatientDto> getAllPatients() {
        List<PatientDto> result = new ArrayList<>();
        for (Patient patient : patients) {
            result.add(new PatientDto(patient));
        }
        return result;
    }

    @Override
    public PatientDto getPatientDtoById(LongDto longDto) {
        if (longDto == null || longDto.getId() == null) {
            throw new BadRequestException("ID cannot be null");
        }

        Patient patient = patients.stream()
                .filter(p -> p.getId().equals(longDto.getId()))
                .findFirst()
                .orElseThrow(() -> new NotFoundException("Patient not found"));

        // For simplicity, assuming no admission state and department handling
        return new PatientDto(
                patient.getId(),
                patient.getFirstName(),
                patient.getLastName(),
                patient.getBirthDate(),
                null,
                null
        );
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
