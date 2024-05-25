package com.example.hospitalmanagementsystem;

import com.example.hospitalmanagementsystem.dto.DepartmentDto;
import com.example.hospitalmanagementsystem.model.Department;
import com.example.hospitalmanagementsystem.repository.DepartmentRepository;
import com.example.hospitalmanagementsystem.service.DepartmentManagementService;
import com.example.hospitalmanagementsystem.service.impl.DepartmentManagementServiceImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
class DepartmentsUnitTest {

    @Autowired
    private DepartmentManagementService departmentManagementService;

    @Autowired
    private DepartmentRepository departmentRepository;

    @BeforeEach
    void setUp() {
        departmentRepository = mock(DepartmentRepository.class);
        departmentManagementService = new DepartmentManagementServiceImpl(departmentRepository);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    @Order(1)
    void saveDepartment_NewDepartment_ShouldSaveSuccessfully() {
        DepartmentDto departmentDto = new DepartmentDto();
        departmentDto.setName("Test Department");
        departmentDto.setCode("TEST");

        when(departmentRepository.findByName("Test Department")).thenReturn(null);

        when(departmentRepository.save(any(Department.class))).thenAnswer(invocationOnMock -> {
            Department departmentArgument = invocationOnMock.getArgument(0);
            departmentArgument.setId(1L);
            return departmentArgument;
        });

        //Department savedDepartment = departmentManagementService.saveDepartment(departmentDto);

        verify(departmentRepository, times(1)).findByName("Test Department");
        verify(departmentRepository, times(1)).save(any(Department.class));
        //assertEquals(departmentDto.getName(), savedDepartment.getName());
        //assertEquals(departmentDto.getCode(), savedDepartment.getCode());
    }

    @Test
    @Order(2)
    void saveDepartment_ExistingDepartment_ShouldReturnExisting() {
        DepartmentDto departmentDto = new DepartmentDto();
        departmentDto.setName("Test Department");
        departmentDto.setCode("TEST");

        Department existingDepartment = new Department();
        existingDepartment.setName("Test Department");
        existingDepartment.setCode("TEST");

        when(departmentRepository.findByName("Test Department")).thenReturn(existingDepartment);

       // Department savedDepartment = departmentManagementService.saveDepartment(departmentDto);

        verify(departmentRepository, times(1)).findByName("Test Department");
        verify(departmentRepository, never()).save(any(Department.class));

       // assertEquals(existingDepartment, savedDepartment);
    }

}