package com.example.hospitalmanagementsystem.controller;

import com.example.hospitalmanagementsystem.dto.ClinicalDataDto;
import com.example.hospitalmanagementsystem.dto.LongDto;
import com.example.hospitalmanagementsystem.dto.PatientDto;
import com.example.hospitalmanagementsystem.service.ClinicalRecordsManagementService;
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
public class ClinicalRecordsManagementController {

    @Autowired
    private ClinicalRecordsManagementService clinicalRecordsManagementService;

    @PostMapping("/saveClinicalRecord")
    public ResponseEntity<String> saveClinicalRecord(@RequestBody ClinicalDataDto clinicalDataDto) {
        clinicalRecordsManagementService.saveClinicalRecord(clinicalDataDto);
        return ResponseEntity.ok("Clinical Record saved or updated successfully!");
    }

    @PostMapping("/filterClinicalRecord")
    public ResponseEntity<List<ClinicalDataDto>> filterClinicalRecord(@RequestBody ClinicalDataDto clinicalDataDto) {
        List<ClinicalDataDto> list = clinicalRecordsManagementService.filterClinicalRecord(clinicalDataDto);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @DeleteMapping("/deleteClinicalRecord")
    public ResponseEntity<String> deleteClinicalRecord(@RequestBody LongDto longDto) {
        clinicalRecordsManagementService.deleteClinicalRecord(longDto);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON); // Set the content type to JSON
        return ResponseEntity.ok()
                .headers(headers)
                .body("{\"message\": \"Clinical Record deleted successfully!\"}");
    }

    @GetMapping("/getAllClinicalRecords")
    public ResponseEntity<List<ClinicalDataDto>> getAllClinicalRecords() {
        List<ClinicalDataDto> list = clinicalRecordsManagementService.getAllClinicalRecords();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/getClinicalRecordsByPatientName")
    public ResponseEntity<List<ClinicalDataDto>> getClinicalRecordsByPatientName(@RequestBody PatientDto patientDto) {
        List<ClinicalDataDto> list = clinicalRecordsManagementService.getClinicalRecordsByPatientName(patientDto);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }
}
