package com.example.hospitalmanagementsystem.service.impl;

import com.example.hospitalmanagementsystem.dto.PatientDto;
import com.example.hospitalmanagementsystem.exception.BadRequestException;
import com.example.hospitalmanagementsystem.exception.NotFoundException;
import com.example.hospitalmanagementsystem.model.Patient;
import com.example.hospitalmanagementsystem.repository.PatientsRepository;
import com.example.hospitalmanagementsystem.service.PatientsManagementService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class PatientsManagementServiceImpl implements PatientsManagementService {

    @Autowired
    private PatientsRepository patientsRepository;

    @Override
    public void savePatient(PatientDto patientDto) {
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
        return toListOfPatientsDto(patientsRepository.filter(patientDto.getFirstName(), patientDto.getLastName()));
    }

    @Override
    @Transactional
    public void deletePatient(PatientDto patientDto) {
        if (patientDto.getFirstName().isEmpty() || patientDto.getLastName().isEmpty()) {
            throw new BadRequestException("Patient's name or lastname is empty!");
        }
        patientsRepository.deleteByFirstNameAndLastName(patientDto.getFirstName(), patientDto.getLastName());
    }

    @Override
    public List<PatientDto> getAllPatients() {
        List<Patient> patientList = patientsRepository.findAll();
        return toListOfPatientsDto(patientList);
    }

    private List<PatientDto> toListOfPatientsDto(List<Patient> patients) {
        List<PatientDto> dtos = new ArrayList<>();
        patients.forEach(p -> dtos.add(new PatientDto(p)));
        return dtos;
    }
}
