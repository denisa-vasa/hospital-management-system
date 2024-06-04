package com.example.hospitalmanagementsystem.repository;

import com.example.hospitalmanagementsystem.model.ClinicalData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClinicalDataRepository extends JpaRepository<ClinicalData, Long> {
}
