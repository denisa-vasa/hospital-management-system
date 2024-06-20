package com.example.hospitalmanagementsystem.service.impl;

import com.example.hospitalmanagementsystem.dto.DepartmentDto;
import com.example.hospitalmanagementsystem.dto.FilterDto;
import com.example.hospitalmanagementsystem.dto.StringDto;
import com.example.hospitalmanagementsystem.exception.BadRequestException;
import com.example.hospitalmanagementsystem.exception.NotFoundException;
import com.example.hospitalmanagementsystem.model.Department;
import com.example.hospitalmanagementsystem.repository.DepartmentRepository;
import com.example.hospitalmanagementsystem.repository.stub.DepartmentRepositoryStub;
import org.junit.jupiter.api.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class DepartmentManagementServiceImplTest {
    private DepartmentRepository departmentRepository;
    private DepartmentManagementServiceImpl departmentService;

    @BeforeEach
    void setUp() {
        departmentRepository = new DepartmentRepositoryStub();
        departmentService = new DepartmentManagementServiceImpl(departmentRepository);
    }

    @Test
    @Order(1)
    void findById_ShouldReturnDepartment_WhenIdExists() {
        Long departmentId = 1L;
        Department mockDepartment = new Department(1L, "Emergency Care", "EC01");
        departmentRepository.save(mockDepartment);

        Department found = departmentService.findById(departmentId);

        assertNotNull(found);
        assertEquals("Emergency Care", found.getName());
        assertEquals("EC01", found.getCode());
    }

    @Test
    @Order(2)
    void findById_ShouldThrowNotFoundException_WhenIdDoesNotExist() {
        NotFoundException exception = assertThrows(NotFoundException.class, () -> departmentService.findById(99L));

        assertEquals("Department with id 99 not found!", exception.getMessage());
    }

    @Test
    @Order(3)
    void findById_ShouldThrowBadRequestException_WhenIdIsNull() {
        BadRequestException exception = assertThrows(BadRequestException.class, () -> departmentService.findById(null));

        assertEquals("Department ID cannot be null", exception.getMessage());
    }

    @Test
    @Order(4)
    void saveDepartment_ShouldSaveDepartment_WhenDtoIsValid() {
        DepartmentDto departmentDto = new DepartmentDto();
        departmentDto.setId(1L);
        departmentDto.setName("Updated Emergency Care");
        departmentDto.setCode("ECO01");

        departmentService.saveDepartment(departmentDto);

        Department saved = departmentRepository.findById(1L).orElseThrow();
        assertEquals(departmentDto.getId(), saved.getId());
        assertEquals(departmentDto.getName(), saved.getName());
        assertEquals(departmentDto.getCode(), saved.getCode());
    }

    @Test
    @Order(5)
    void saveDepartment_ShouldThrowNotFoundException_WhenIdDoesNotExist() {
        DepartmentDto departmentDto = new DepartmentDto();
        departmentDto.setId(99L);
        departmentDto.setName("Non-Existent");
        departmentDto.setCode("NONE");

        NotFoundException exception = assertThrows(NotFoundException.class, () -> departmentService.saveDepartment(departmentDto));

        assertEquals("Department with ID: 99 not found!", exception.getMessage());
    }

    @Test
    @Order(6)
    void saveDepartment_ShouldThrowBadRequestException_WhenDtoIsNull() {
        BadRequestException exception = assertThrows(BadRequestException.class, () -> departmentService.saveDepartment(null));

        assertEquals("DepartmentDto cannot be null", exception.getMessage());
    }

    @Test
    @Order(7)
    void filterDepartment_ShouldReturnFilteredDepartments_WhenFilterIsValid() {
        FilterDto filter = new FilterDto();
        filter.setName("Emergency Care");

        List<DepartmentDto> filteredDepartments = departmentService.filterDepartment(filter);

        assertNotNull(filteredDepartments);
        assertEquals(1, filteredDepartments.size());
        assertEquals("Emergency Care", filteredDepartments.get(0).getName());
    }

    @Test
    @Order(8)
    void filterDepartment_ShouldThrowBadRequestException_WhenFilterIsNull() {
        BadRequestException exception = assertThrows(BadRequestException.class, () -> departmentService.filterDepartment(null));

        assertEquals("Filter criteria cannot be null or empty", exception.getMessage());
    }

    @Test
    @Order(9)
    void deleteDepartment_ShouldDeleteDepartment_WhenNameIsValid() {
        StringDto stringDto = new StringDto();
        stringDto.setName("Emergency Care");

        departmentService.deleteDepartment(stringDto);

        assertTrue(departmentRepository.findByName("Emergency Care").isEmpty());
    }

    @Test
    @Order(10)
    void deleteDepartment_ShouldThrowBadRequestException_WhenNameIsNull() {
        BadRequestException exception = assertThrows(BadRequestException.class, () -> departmentService.deleteDepartment(null));

        assertEquals("Department name cannot be null or empty!", exception.getMessage());
    }

    @Test
    @Order(11)
    void getAllDepartments_ShouldReturnAllDepartments() {
        List<DepartmentDto> departments = departmentService.getAllDepartments();

        assertNotNull(departments);
        assertEquals(1, departments.size());
        assertEquals("Emergency Care", departments.get(0).getName());
    }
}
