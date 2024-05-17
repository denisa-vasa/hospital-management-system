package com.example.hospitalmanagementsystem.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FilterDto {

    private String name;

    public FilterDto() {
        name = getName();
    }
}
