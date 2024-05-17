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
import java.util.Optional;

@Service
@Slf4j
public class DepartmentManagementServiceImpl implements DepartmentManagementService {

    @Autowired
    private DepartmentRepository departmentRepository;

    public DepartmentManagementServiceImpl(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    private Optional<Department> getByName(String name) {
        Department department = departmentRepository.findByName(name);

        return Optional.ofNullable(department);
    }

    @Override
    public Department saveDepartment(DepartmentDto departmentDto) {
        Optional<Department> optionalDepartment = getByName(departmentDto.getName());
        Department existingDepartment = optionalDepartment.orElseGet(Department::new); // create a new department if not found

        // Only set properties if it's a new department
        if (optionalDepartment.isEmpty()) {
            existingDepartment.setName(departmentDto.getName());
            existingDepartment.setCode(departmentDto.getCode());
            return departmentRepository.save(existingDepartment);
        }

        return existingDepartment;
    }

    @Override
    public List<DepartmentDto> filterDepartment(String filter) {
        return toListOfDepartmentDto(departmentRepository.filter(filter));
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
