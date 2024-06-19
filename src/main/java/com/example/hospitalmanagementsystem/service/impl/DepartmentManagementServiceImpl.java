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
        return departmentRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Department with id " + id + " not found!"));
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
        return toListOfDepartmentDto(departmentRepository.filter(filter.getName()));
    }

    @Override
    @Transactional
    public void deleteDepartment(StringDto stringDto) {
        if (stringDto.getName().isEmpty()) {
            throw new BadRequestException("Department name is empty!");
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
}
