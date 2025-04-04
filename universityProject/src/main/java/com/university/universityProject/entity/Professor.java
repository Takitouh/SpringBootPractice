package com.university.universityProject.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Professor {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idProfessor;
    private String dni;
    private String firstName;
    private String lastName;
    private String email;
    private String specialty;
    @ManyToOne
    @JoinColumn(name = "deparment")
    private Department department;
}
