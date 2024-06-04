package com.example.hospitalmanagementsystem.service.impl;

import com.example.hospitalmanagementsystem.exception.NotFoundException;
import com.example.hospitalmanagementsystem.model.AdmissionState;
import com.example.hospitalmanagementsystem.repository.AdmissionStateRepository;
import com.example.hospitalmanagementsystem.service.AdmissionStateManagementService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class AdmissionStateManagementServiceImpl implements AdmissionStateManagementService {

    @Autowired
    private AdmissionStateRepository admissionStateRepository;

    @Override
    public AdmissionState findById(Long id) {
        return admissionStateRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Admission state with id " + id + " not found!"));
    }
}
