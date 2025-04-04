package com.university.universityProject.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.util.Date;
import java.util.List;

@Data @Entity
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idStudent;
    private String dni;
    private String firstName;
    private String lastName;
    private int age;
    private Date birthDate;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "student")
    private List<Enrollment> enrollmentStudent;

}
