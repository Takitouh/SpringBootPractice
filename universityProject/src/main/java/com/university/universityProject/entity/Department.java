package com.university.universityProject.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data @Entity
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idDepartment;
    private String departmentName;
    private String building;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "department")
    private List<Professor> professors;
}
