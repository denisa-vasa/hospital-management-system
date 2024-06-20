package com.example.hospitalmanagementsystem.service.impl;

import com.example.hospitalmanagementsystem.dto.*;
import com.example.hospitalmanagementsystem.exception.NotFoundException;
import com.example.hospitalmanagementsystem.model.ClinicalData;
import com.example.hospitalmanagementsystem.repository.ClinicalDataRepository;
import com.example.hospitalmanagementsystem.repository.stub.ClinicalDataRepositoryStub;
import com.example.hospitalmanagementsystem.service.AdmissionStateManagementService;
import com.example.hospitalmanagementsystem.service.ClinicalRecordsManagementService;
import com.example.hospitalmanagementsystem.service.DepartmentManagementService;
import com.example.hospitalmanagementsystem.service.PatientsManagementService;
import com.example.hospitalmanagementsystem.service.stub.AdmissionStateManagementServiceStub;
import com.example.hospitalmanagementsystem.service.stub.DepartmentManagementServiceStub;
import com.example.hospitalmanagementsystem.service.stub.PatientsManagementServiceStub;
import org.junit.jupiter.api.*;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ClinicalRecordsManagementServiceImplTest {
    private ClinicalDataRepository clinicalDataRepository;
    private ClinicalRecordsManagementService clinicalRecordsManagementService;
    private PatientsManagementService patientsManagementService;
    private DepartmentManagementService departmentManagementService;
    private AdmissionStateManagementService admissionStateManagementService;

    @BeforeEach
    public void setUp() {
        clinicalDataRepository = new ClinicalDataRepositoryStub();
        patientsManagementService = new PatientsManagementServiceStub();
        departmentManagementService = new DepartmentManagementServiceStub();
        admissionStateManagementService = new AdmissionStateManagementServiceStub();

        clinicalRecordsManagementService = new ClinicalRecordsManagementServiceImpl(clinicalDataRepository,
                patientsManagementService,
                departmentManagementService,
                admissionStateManagementService);
    }

    @Test
    @Order(1)
    public void testSaveClinicalRecord_NewRecord() {
        // Arrange
        ClinicalDataDto clinicalDataDto = new ClinicalDataDto();
        clinicalDataDto.setPatientId(1L);
        clinicalDataDto.setDepartmentId(1L);
        clinicalDataDto.setAdmissionStateId(1L);
        clinicalDataDto.setClinicalRecord("Test Clinical Record");

        clinicalRecordsManagementService.saveClinicalRecord(clinicalDataDto);

        ClinicalData saved = clinicalDataRepository.findById(1L).orElseThrow();
        assertEquals(clinicalDataDto.getId(), saved.getId());
        assertEquals(clinicalDataDto.getClinicalRecord(), saved.getClinicalRecord());
        assertEquals(clinicalDataDto.getPatientId(), saved.getPatient().getId());
        assertEquals(clinicalDataDto.getDepartmentId(), saved.getDepartment().getId());
        assertEquals(clinicalDataDto.getAdmissionStateId(), saved.getAdmissionState().getId());
    }

    @Test
    @Order(2)
    public void testSaveClinicalRecord_PatientNotFound() {
        // Arrange
        ClinicalDataDto clinicalDataDto = new ClinicalDataDto();
        clinicalDataDto.setPatientId(999L); // Non-existent patient ID

        // Act + Assert
        assertThrows(NotFoundException.class, () -> clinicalRecordsManagementService.saveClinicalRecord(clinicalDataDto));
    }

    @Test
    @Order(3)
    public void testFilterClinicalRecord() {
        // Arrange
        ClinicalDataDto clinicalDataDto = new ClinicalDataDto();
        clinicalDataDto.setClinicalRecord("FilterText");

        // Save data directly to the repository stub
        clinicalDataRepository.save(new ClinicalData(1L, "FilterText"));
        clinicalDataRepository.save(new ClinicalData(2L, "Another Clinical Record"));

        // Act
        List<ClinicalDataDto> result = clinicalRecordsManagementService.filterClinicalRecord(clinicalDataDto);

        // Assert
        assertFalse(result.isEmpty());
        assertEquals("FilterText", result.get(0).getClinicalRecord());
    }

    @Test
    @Order(4)
    public void testDeleteClinicalRecord() {
        // Arrange
        ClinicalDataRepositoryStub repositoryStub = (ClinicalDataRepositoryStub) clinicalDataRepository;
        repositoryStub.save(new ClinicalData(1L, 1L, 1L, "Clinical Record to Delete"));

        LongDto longDto = new LongDto(1L);

        // Act
        assertDoesNotThrow(() -> clinicalRecordsManagementService.deleteClinicalRecord(longDto));

        // Assert
        assertTrue(repositoryStub.getAllClinicalData().isEmpty());
    }

}
