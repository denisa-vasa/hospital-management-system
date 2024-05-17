package com.example.hospitalmanagementsystem.controller;

import com.example.hospitalmanagementsystem.dto.DepartmentDto;
import com.example.hospitalmanagementsystem.dto.FilterDto;
import com.example.hospitalmanagementsystem.model.Department;
import com.example.hospitalmanagementsystem.service.DepartmentManagementService;
import com.example.hospitalmanagementsystem.service.impl.DepartmentManagementServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@Slf4j
@RequestMapping("/api")
public class DepartmentManagementController {

    @Autowired
    private DepartmentManagementService departmentManagementService;

    @PostMapping("/saveDepartment")
    public void saveDepartment(@RequestBody DepartmentDto departmentDto) {
        departmentManagementService.saveDepartment(departmentDto);
    }

    @GetMapping("/filterDepartment")
    public List<DepartmentDto> filterDepartment(@RequestBody FilterDto filter) {
        return departmentManagementService.filterDepartment(filter.getName());
    }
}
