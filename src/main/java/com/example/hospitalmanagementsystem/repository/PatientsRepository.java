package com.example.hospitalmanagementsystem.repository;

import com.example.hospitalmanagementsystem.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PatientsRepository extends JpaRepository<Patient, Long> {
    @Query("SELECT p FROM Patient p WHERE p.firstName LIKE %?1% AND p.lastName LIKE %?2%")
    List<Patient> filter(String firstName, String lastName);

    void deleteByFirstNameAndLastName(String firstName, String lastName);
}
