package com.example.hospitalmanagementsystem.repository;

import com.example.hospitalmanagementsystem.model.ClinicalData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClinicalDataRepository extends JpaRepository<ClinicalData, Long> {
    @Query("SELECT c FROM ClinicalData c WHERE c.clinicalRecord LIKE %?1%")
    List<ClinicalData> filter(String clinicalRecord);
}
