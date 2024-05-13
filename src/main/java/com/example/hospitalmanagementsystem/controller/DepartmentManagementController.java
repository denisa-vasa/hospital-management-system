package com.example.hospitalmanagementsystem.controller;

import com.example.hospitalmanagementsystem.dto.DepartmentDto;
import com.example.hospitalmanagementsystem.model.Department;
import com.example.hospitalmanagementsystem.service.impl.DepartmentManagementServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@Slf4j
@RequestMapping("/api")
public class DepartmentManagementController {

    @Autowired
    private DepartmentManagementServiceImpl departmentManagementService;

    @PostMapping("/saveDepartment")
    public Long saveDepartment(@RequestBody DepartmentDto departmentDto) {
        Department department = departmentManagementService.saveDepartment(departmentDto);
        return department.getId();
    }
}
