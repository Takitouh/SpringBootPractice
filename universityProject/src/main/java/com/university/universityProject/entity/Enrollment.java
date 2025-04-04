package com.university.universityProject.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data @Entity
@IdClass(EnrollmentId.class)
public class Enrollment {
    @Id
    private Long idStudent;
    @Id
    private Long idCourse;
    private Date finaldateEnrollment;
    private Date startdateEnrollment;
    private float finalNote;
    @ManyToOne
            @JoinColumn(name = "idStudent", nullable = false, insertable = false, updatable = false)
    private Student student;

    @ManyToOne
            @JoinColumn(name = "idCourse", nullable = false, insertable = false, updatable = false)
    private Course course;

    @Override
    public String toString() {
        return "Enrollment{" +
                "idStudent=" + idStudent +
                ", idCourse=" + idCourse +
                ", finaldateEnrollment=" + finaldateEnrollment +
                ", startdateEnrollment=" + startdateEnrollment +
                ", finalNote=" + finalNote +
                ", student=" + student.getFirstName() +
                ", course=" + course.getCourseName() +
                '}';
    }

    //#NOTE: I override the toString method because i think it might produce a stackOverflow for the
    //birectional relation between course and student, if only we call the name it shouldn't throw any exception

}
