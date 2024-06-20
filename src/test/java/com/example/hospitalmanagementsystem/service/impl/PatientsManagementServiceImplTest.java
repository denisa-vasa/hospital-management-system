package com.example.hospitalmanagementsystem.service.impl;

import com.example.hospitalmanagementsystem.dto.PatientDto;
import com.example.hospitalmanagementsystem.exception.NotFoundException;
import com.example.hospitalmanagementsystem.model.Patient;
import com.example.hospitalmanagementsystem.repository.PatientsRepository;
import com.example.hospitalmanagementsystem.repository.stub.PatientRepositoryStub;
import org.junit.jupiter.api.*;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class PatientsManagementServiceImplTest {

    private PatientsRepository patientsRepository; // Manual mock implementation

    private PatientsManagementServiceImpl patientsManagementService;

    @BeforeEach
    public void setup() {
        patientsRepository = new PatientRepositoryStub();
        patientsManagementService = new PatientsManagementServiceImpl(patientsRepository);
    }

    @Test
    @Order(1)
    public void testFindById_existingPatient() {
        // Given
        Long patientId = 1L;
        Patient mockPatient = new Patient(patientId, "Patient6", "Patient6", LocalDate.of(1991, 10, 1));
        patientsRepository.save(mockPatient);

        // When
        Patient found = patientsManagementService.findById(patientId);

        // Then
        assertNotNull(found);
        assertEquals("Patient6", found.getFirstName());
        assertEquals("Patient6", found.getLastName());
        assertEquals(LocalDate.of(1991, 10, 1), found.getBirthDate());
    }

    @Test
    @Order(2)
    public void testFindById_patientNotFound() {
        // Given
        Long patientId = 100L;

        // When / Then
        assertThrows(NotFoundException.class, () -> patientsManagementService.findById(patientId));
    }

    @Test
    @Order(3)
    public void testSavePatient_newPatient_WhenDtoIsValid() {
        PatientDto patientDto = new PatientDto();
        patientDto.setId(1L);
        patientDto.setFirstName("Updated Patient6");
        patientDto.setLastName("Updated Patient6");
        patientDto.setBirthDate(LocalDate.of(2001, 1, 1));

        patientsManagementService.savePatient(patientDto);

        Patient saved = patientsRepository.findById(1L).orElseThrow();
        assertEquals(patientDto.getId(), saved.getId());
        assertEquals(patientDto.getFirstName(), saved.getFirstName());
        assertEquals(patientDto.getLastName(), saved.getLastName());
        assertEquals(patientDto.getBirthDate(), saved.getBirthDate());
    }
}



