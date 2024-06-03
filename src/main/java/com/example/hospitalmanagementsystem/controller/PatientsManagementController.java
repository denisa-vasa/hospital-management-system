package com.example.hospitalmanagementsystem.controller;

import com.example.hospitalmanagementsystem.dto.PatientDto;
import com.example.hospitalmanagementsystem.service.PatientsManagementService;
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
public class PatientsManagementController {

    @Autowired
    private PatientsManagementService patientsManagementService;

    @PostMapping("/savePatient")
    public ResponseEntity<String> savePatient(@RequestBody PatientDto patientDto) {
        patientsManagementService.savePatient(patientDto);
        return ResponseEntity.ok("Patient saved or updated successfully!");
    }

    @PostMapping("/filterPatients")
    public ResponseEntity<List<PatientDto>> filterPatients(@RequestBody PatientDto patientDto) {
        List<PatientDto> list = patientsManagementService.filterPatients(patientDto);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @DeleteMapping("/deletePatient")
    public ResponseEntity<String> deletePatient(@RequestBody PatientDto patientDto) {
        patientsManagementService.deletePatient(patientDto);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON); // Set the content type to JSON
        return ResponseEntity.ok()
                .headers(headers)
                .body("{\"message\": \"Patient deleted successfully!\"}");
    }

    @GetMapping("/getAllPatients")
    public ResponseEntity<List<PatientDto>> getAllPatients() {
        List<PatientDto> list = patientsManagementService.getAllPatients();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }
}
