package com.university.universityProject.entity;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Objects;
@Getter @Setter
public class EnrollmentId implements Serializable {
    private Long idCourse;
    private Long idStudent;

    public EnrollmentId() {
    }

    public EnrollmentId(Long idCourse, Long idStudent) {
        this.idCourse = idCourse;
        this.idStudent = idStudent;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        EnrollmentId that = (EnrollmentId) o;
        return Objects.equals(idCourse, that.idCourse) && Objects.equals(idStudent, that.idStudent);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idCourse, idStudent);
    }
}
