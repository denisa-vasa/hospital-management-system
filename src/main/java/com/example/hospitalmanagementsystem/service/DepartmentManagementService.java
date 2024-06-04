package com.example.hospitalmanagementsystem.service;

import com.example.hospitalmanagementsystem.dto.DepartmentDto;
import com.example.hospitalmanagementsystem.dto.FilterDto;
import com.example.hospitalmanagementsystem.dto.StringDto;
import com.example.hospitalmanagementsystem.model.Department;

import java.util.List;

public interface DepartmentManagementService {
    Department findById(Long id);

    void saveDepartment(DepartmentDto departmentDto);

    List<DepartmentDto> filterDepartment(FilterDto filter);

    void deleteDepartment(StringDto stringDto);

    List<DepartmentDto> getAllDepartments();
}
