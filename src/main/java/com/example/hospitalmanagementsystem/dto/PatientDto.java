package com.example.hospitalmanagementsystem.dto;

import com.example.hospitalmanagementsystem.model.Patient;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PatientDto {
    private Long id;
    private String firstName;
    private String lastName;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate birthDate;
    private Long departmentId;
    private Long admissionStateId;

    public PatientDto(Patient p) {
        id = p.getId();
        firstName = p.getFirstName();
        lastName = p.getLastName();
        birthDate = p.getBirthDate();
    }

    public PatientDto(Long patientId, String alice, String smith, LocalDate of) {
        id = patientId;
        firstName = alice;
        lastName = smith;
        birthDate = of;
    }
}
