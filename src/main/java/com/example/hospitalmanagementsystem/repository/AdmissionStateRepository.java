package com.example.hospitalmanagementsystem.repository;

import com.example.hospitalmanagementsystem.model.AdmissionState;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdmissionStateRepository extends JpaRepository<AdmissionState, Long> {

}
