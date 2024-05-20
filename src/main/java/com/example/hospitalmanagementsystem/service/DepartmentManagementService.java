package com.example.hospitalmanagementsystem.service;

import com.example.hospitalmanagementsystem.dto.DepartmentDto;
import com.example.hospitalmanagementsystem.dto.FilterDto;

import java.util.List;

public interface DepartmentManagementService {

    public void saveDepartment(DepartmentDto departmentDto);

    List<DepartmentDto> filterDepartment(FilterDto filter);

    public void deleteDepartment(String name);

    List<DepartmentDto> getAllDepartments();
}
