package com.example.hospitalmanagementsystem.service.impl;

import com.example.hospitalmanagementsystem.dto.DepartmentDto;
import com.example.hospitalmanagementsystem.dto.FilterDto;
import com.example.hospitalmanagementsystem.model.Department;
import com.example.hospitalmanagementsystem.repository.DepartmentRepository;
import com.example.hospitalmanagementsystem.service.DepartmentManagementService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
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
    public void saveDepartment(DepartmentDto departmentDto) {
        Department department;

        if (departmentDto.getId() != null) {
            Optional<Department> existingDepartmentOpt = departmentRepository.findById(departmentDto.getId());
            if (existingDepartmentOpt.isPresent()) {
                department = existingDepartmentOpt.get();
                department.setName(departmentDto.getName());
                department.setCode(departmentDto.getCode());
            } else {
                throw new NoSuchElementException("Department not found with ID: " + departmentDto.getId());
            }
        } else {
            department = new Department();
            department.setName(departmentDto.getName());
            department.setCode(departmentDto.getCode());
        }

        Department savedDepartment = departmentRepository.save(department);

        mapToDto(savedDepartment);
    }

    private void mapToDto(Department department) {
        DepartmentDto departmentDto = new DepartmentDto();
        departmentDto.setId(department.getId());
        departmentDto.setName(department.getName());
        departmentDto.setCode(department.getCode());
    }

    @Override
    public List<DepartmentDto> filterDepartment(FilterDto filter) {
        return toListOfDepartmentDto(departmentRepository.filter(filter.getName()));
    }

    @Override
    @Transactional
    public void deleteDepartment(String name) {
        departmentRepository.deleteByName(name);
    }

    private List<DepartmentDto> toListOfDepartmentDto(List<Department> departmentList) {
       List<DepartmentDto> dtos = new ArrayList<>();
       departmentList.forEach(d -> dtos.add(new DepartmentDto(d)));
       return dtos;
    }
}
