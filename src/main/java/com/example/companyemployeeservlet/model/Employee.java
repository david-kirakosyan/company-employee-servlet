package com.example.companyemployeeservlet.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Employee {

    private int id;
    private String name;
    private String surname;
    private String email;
    private String picName;
    private Company company;
}
