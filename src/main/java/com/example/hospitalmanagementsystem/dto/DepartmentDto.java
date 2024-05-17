package com.example.hospitalmanagementsystem.dto;

import com.example.hospitalmanagementsystem.model.Department;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DepartmentDto {

    private String name;

    private String code;

    public DepartmentDto(Department d) {
        name = d.getName();
        code = d.getCode();
    }
}
