package com.example.hospitalmanagementsystem.controller;

import com.example.hospitalmanagementsystem.dto.DepartmentDto;
import com.example.hospitalmanagementsystem.dto.FilterDto;
import com.example.hospitalmanagementsystem.dto.StringDto;
import com.example.hospitalmanagementsystem.service.DepartmentManagementService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<String> saveDepartment(@RequestBody DepartmentDto departmentDto) {
        departmentManagementService.saveDepartment(departmentDto);
        return ResponseEntity.ok("Department saved or updated successfully!");
    }

    @PostMapping("/filterDepartment")
    public ResponseEntity<List<DepartmentDto>> filterDepartment(@RequestBody FilterDto filter) {
        List<DepartmentDto> list = departmentManagementService.filterDepartment(filter);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @DeleteMapping("/deleteDepartment")

    public ResponseEntity<String> deleteDepartment(@RequestBody StringDto stringDto) {
        departmentManagementService.deleteDepartment(stringDto);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON); // Set the content type to JSON
        return ResponseEntity.ok()
                .headers(headers)
                .body("{\"message\": \"Department deleted successfully!\"}");
    }

    @GetMapping("/getAllDepartments")
    public ResponseEntity<List<DepartmentDto>> getAllDepartments() {
        List<DepartmentDto> list = departmentManagementService.getAllDepartments();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }
}
