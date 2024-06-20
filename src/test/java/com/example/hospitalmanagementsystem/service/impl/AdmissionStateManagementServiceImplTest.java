package com.example.hospitalmanagementsystem.service.impl;

import com.example.hospitalmanagementsystem.exception.BadRequestException;
import com.example.hospitalmanagementsystem.exception.NotFoundException;
import com.example.hospitalmanagementsystem.model.AdmissionState;
import com.example.hospitalmanagementsystem.repository.AdmissionStateRepository;
import com.example.hospitalmanagementsystem.repository.stub.AdmissionStateRepositoryStub;
import com.example.hospitalmanagementsystem.service.DepartmentManagementService;
import com.example.hospitalmanagementsystem.service.PatientsManagementService;
import com.example.hospitalmanagementsystem.service.stub.DepartmentManagementServiceStub;
import com.example.hospitalmanagementsystem.service.stub.PatientsManagementServiceStub;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class AdmissionStateManagementServiceImplTest {
    private AdmissionStateRepository admissionStateRepository;
    private DepartmentManagementService departmentManagementService;
    private PatientsManagementService patientsManagementService;
    private AdmissionStateManagementServiceImpl admissionStateManagementService;

    @BeforeEach
    public void setUp() {
        admissionStateRepository = new AdmissionStateRepositoryStub();
        departmentManagementService = new DepartmentManagementServiceStub();
        patientsManagementService = new PatientsManagementServiceStub();

        admissionStateManagementService = new AdmissionStateManagementServiceImpl(
                admissionStateRepository,
                departmentManagementService,
                patientsManagementService
        );
    }

    @Test
    public void testFindByIdValidId() {
        AdmissionState savedState = new AdmissionState();
        admissionStateRepository.save(savedState); // Automatically assigns ID 1

        AdmissionState admissionState = admissionStateManagementService.findById(1L);
        assertNotNull(admissionState);
        assertEquals(1L, admissionState.getId());
    }

    @Test
    public void testFindByIdInvalidId() {
        assertThrows(NotFoundException.class, () -> {
            admissionStateManagementService.findById(2L);
        });
    }

    @Test
    public void testFindByIdNullId() {
        assertThrows(BadRequestException.class, () -> {
            admissionStateManagementService.findById(null);
        });
    }

}
