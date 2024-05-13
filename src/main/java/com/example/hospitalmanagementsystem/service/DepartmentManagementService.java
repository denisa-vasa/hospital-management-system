package com.example.hospitalmanagementsystem.service;

import com.example.hospitalmanagementsystem.dto.DepartmentDto;
import com.example.hospitalmanagementsystem.model.Department;

public interface DepartmentManagementService {

    Department saveDepartment(DepartmentDto departmentDto);
}
