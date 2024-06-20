package com.example.hospitalmanagementsystem.service.stub;

import com.example.hospitalmanagementsystem.dto.DepartmentDto;
import com.example.hospitalmanagementsystem.dto.FilterDto;
import com.example.hospitalmanagementsystem.dto.StringDto;
import com.example.hospitalmanagementsystem.exception.BadRequestException;
import com.example.hospitalmanagementsystem.exception.NotFoundException;
import com.example.hospitalmanagementsystem.model.Department;
import com.example.hospitalmanagementsystem.service.DepartmentManagementService;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DepartmentManagementServiceStub implements DepartmentManagementService {

    private final List<Department> departments = new ArrayList<>();
    private long nextId = 1L;

    @Override
    public Department findById(Long id) {
        return departments.stream()
                .filter(d -> d.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new NotFoundException("Department with id " + id + " not found"));
    }

    @Override
    public void saveDepartment(DepartmentDto departmentDto) {
        validateDepartmentDto(departmentDto);

        Department department;

        if (departmentDto.getId() != null) {
            Optional<Department> existingDepartmentOpt = departments.stream()
                    .filter(d -> d.getId().equals(departmentDto.getId()))
                    .findFirst();

            if (existingDepartmentOpt.isPresent()) {
                department = existingDepartmentOpt.get();
                department.setName(departmentDto.getName());
                department.setCode(departmentDto.getCode());
            } else {
                throw new NotFoundException("Department with ID: " + departmentDto.getId() + " not found!");
            }
        } else {
            department = new Department();
            department.setId(nextId++);
            department.setName(departmentDto.getName());
            department.setCode(departmentDto.getCode());
            departments.add(department);
        }

        // Simulating save operation (in a real scenario, this might be a no-op for stub)
        System.out.println("Saved department: " + department);
    }

    @Override
    public List<DepartmentDto> filterDepartment(FilterDto filter) {
        if (filter == null || !StringUtils.hasText(filter.getName())) {
            throw new BadRequestException("Filter criteria cannot be null or empty");
        }

        // Simulating filtering logic (in a real scenario, use mocked data)
        List<Department> filteredDepartments = departments.stream()
                .filter(d -> d.getName().contains(filter.getName()))
                .toList();

        return toListOfDepartmentDto(filteredDepartments);
    }

    @Override
    public void deleteDepartment(StringDto stringDto) {
        if (stringDto == null || !StringUtils.hasText(stringDto.getName())) {
            throw new BadRequestException("Department name cannot be null or empty!");
        }

        // Simulating delete operation (in a real scenario, modify list or mock repository)
        departments.removeIf(d -> d.getName().equals(stringDto.getName()));
    }

    @Override
    public List<DepartmentDto> getAllDepartments() {
        return toListOfDepartmentDto(departments);
    }

    private List<DepartmentDto> toListOfDepartmentDto(List<Department> departmentList) {
        List<DepartmentDto> dtos = new ArrayList<>();
        departmentList.forEach(d -> dtos.add(new DepartmentDto(d)));
        return dtos;
    }

    private void validateDepartmentDto(DepartmentDto departmentDto) {
        if (departmentDto == null) {
            throw new BadRequestException("DepartmentDto cannot be null");
        }

        if (!StringUtils.hasText(departmentDto.getName())) {
            throw new BadRequestException("Department name cannot be empty");
        }

        if (!StringUtils.hasText(departmentDto.getCode())) {
            throw new BadRequestException("Department code cannot be empty");
        }
    }
}
