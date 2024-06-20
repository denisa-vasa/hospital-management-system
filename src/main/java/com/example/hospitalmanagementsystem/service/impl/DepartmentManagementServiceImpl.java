package com.example.hospitalmanagementsystem.service.impl;

import com.example.hospitalmanagementsystem.dto.DepartmentDto;
import com.example.hospitalmanagementsystem.dto.FilterDto;
import com.example.hospitalmanagementsystem.dto.StringDto;
import com.example.hospitalmanagementsystem.exception.BadRequestException;
import com.example.hospitalmanagementsystem.exception.NotFoundException;
import com.example.hospitalmanagementsystem.model.Department;
import com.example.hospitalmanagementsystem.repository.DepartmentRepository;
import com.example.hospitalmanagementsystem.service.DepartmentManagementService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class DepartmentManagementServiceImpl implements DepartmentManagementService {

    @Autowired
    private DepartmentRepository departmentRepository;

    public DepartmentManagementServiceImpl(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    @Override
    public Department findById(Long id) {
        if (id == null) {
            throw new BadRequestException("Department ID cannot be null");
        }
        return departmentRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Department with id " + id + " not found!"));
    }

    @Override
    public void saveDepartment(DepartmentDto departmentDto) {
        validateDepartmentDto(departmentDto);

        Department department;

        if (departmentDto.getId() != null) {
            Optional<Department> existingDepartmentOpt = departmentRepository.findById(departmentDto.getId());
            if (existingDepartmentOpt.isPresent()) {
                department = existingDepartmentOpt.get();
                department.setName(departmentDto.getName());
                department.setCode(departmentDto.getCode());
            } else {
                throw new NotFoundException("Department with ID: " + departmentDto.getId() + " not found!");
            }
        } else {
            department = new Department();
            department.setName(departmentDto.getName());
            department.setCode(departmentDto.getCode());
        }

        departmentRepository.save(department);
    }

    private void mapToDto(Department department) {
        DepartmentDto departmentDto = new DepartmentDto();
        departmentDto.setId(department.getId());
        departmentDto.setName(department.getName());
        departmentDto.setCode(department.getCode());
    }

    @Override
    public List<DepartmentDto> filterDepartment(FilterDto filter) {
        if (filter == null || !StringUtils.hasText(filter.getName())) {
            throw new BadRequestException("Filter criteria cannot be null or empty");
        }
        return toListOfDepartmentDto(departmentRepository.filter(filter.getName()));
    }

    @Override
    @Transactional
    public void deleteDepartment(StringDto stringDto) {
        if (stringDto == null || !StringUtils.hasText(stringDto.getName())) {
            throw new BadRequestException("Department name cannot be null or empty!");
        }
        departmentRepository.deleteByName(stringDto.getName());
    }

    @Override
    public List<DepartmentDto> getAllDepartments() {
        List<Department> departmentList = departmentRepository.findAll();
        return toListOfDepartmentDto(departmentList);
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
