package com.example.hospitalmanagementsystem.controller;

import com.example.hospitalmanagementsystem.dto.AdmissionStateDto;
import com.example.hospitalmanagementsystem.dto.DischargeReasonDto;
import com.example.hospitalmanagementsystem.service.AdmissionStateManagementService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@Slf4j
@RequestMapping("/api")
public class AdmissionsManagementController {

    @Autowired
    private AdmissionStateManagementService admissionStateManagementService;

    @PostMapping("/dischargeReason")
    public ResponseEntity<String> setDischargeReason(@RequestBody DischargeReasonDto dischargeReasonDto) {
        admissionStateManagementService.setDischargeReason(dischargeReasonDto);
        return ResponseEntity.ok("Discharged!");
    }

    @PostMapping("/saveCause")
    public ResponseEntity<String> saveCause(@RequestBody AdmissionStateDto admissionStateDto) {
        admissionStateManagementService.saveCause(admissionStateDto);
        return ResponseEntity.ok("Admitted or Transferred!");
    }
}
