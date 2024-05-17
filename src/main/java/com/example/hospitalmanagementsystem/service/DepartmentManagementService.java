package com.example.hospitalmanagementsystem.service;

import com.example.hospitalmanagementsystem.dto.DepartmentDto;
import com.example.hospitalmanagementsystem.dto.FilterDto;
import com.example.hospitalmanagementsystem.model.Department;

import java.util.List;

public interface DepartmentManagementService {

    Department saveDepartment(DepartmentDto departmentDto);

    List<DepartmentDto> filterDepartment(String filter);

    public void deleteDepartment(String name);
}
