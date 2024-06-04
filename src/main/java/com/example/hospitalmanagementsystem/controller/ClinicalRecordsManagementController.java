package com.example.hospitalmanagementsystem.controller;

import com.example.hospitalmanagementsystem.dto.ClinicalDataDto;
import com.example.hospitalmanagementsystem.service.ClinicalRecordsManagementService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@Slf4j
@RequestMapping("/api")
public class ClinicalRecordsManagementController {

    @Autowired
    private ClinicalRecordsManagementService clinicalRecordsManagementService;

    @PostMapping("/saveClinicalRecord")
    public ResponseEntity<String> saveClinicalRecord(@RequestBody ClinicalDataDto clinicalDataDto) {
        clinicalRecordsManagementService.saveClinicalRecord(clinicalDataDto);
        return ResponseEntity.ok("Clinical Record saved or updated successfully!");
    }
}
