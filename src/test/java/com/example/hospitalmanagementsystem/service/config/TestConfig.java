package com.example.hospitalmanagementsystem.service.config;

import com.example.hospitalmanagementsystem.service.AdmissionStateManagementService;
import com.example.hospitalmanagementsystem.service.PatientsManagementService;
import com.example.hospitalmanagementsystem.service.stub.AdmissionStateManagementServiceStub;
import com.example.hospitalmanagementsystem.service.stub.PatientsManagementServiceStub;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TestConfig {

    @Bean
    public PatientsManagementService patientsManagementService() {
        return new PatientsManagementServiceStub();
    }

    @Bean
    public AdmissionStateManagementService admissionStateManagementService() {
        return new AdmissionStateManagementServiceStub();
    }
}
