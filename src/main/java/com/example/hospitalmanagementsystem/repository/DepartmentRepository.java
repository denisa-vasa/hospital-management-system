package com.example.hospitalmanagementsystem.repository;

import com.example.hospitalmanagementsystem.model.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {

    Department findByName(String name);

    @Query("SELECT d FROM Department d WHERE d.name LIKE %?1%")
    public List<Department> filter(String filter);
}
