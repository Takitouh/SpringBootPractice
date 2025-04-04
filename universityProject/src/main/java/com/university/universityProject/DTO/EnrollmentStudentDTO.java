package com.university.universityProject.DTO;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
@Getter @Setter
public class EnrollmentStudentDTO {
//Using a DTO makes the task easier
    private Long idStudent;
    private Long idCourse;
    private Date finalenrollmentDate;
    private Date startenrollmentDate;
    private float finalNote;
}
