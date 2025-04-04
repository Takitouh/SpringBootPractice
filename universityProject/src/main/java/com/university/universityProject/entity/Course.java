package com.university.universityProject.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idCourse;
    private String courseName;
    private int credits;
    private String description;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "course", cascade = CascadeType.PERSIST)
    private List<Enrollment> enrollmentCourse;

}
