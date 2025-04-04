package com.university.universityProject.service;

import com.university.universityProject.DTO.EnrollmentStudentDTO;
import com.university.universityProject.entity.Enrollment;
import com.university.universityProject.entity.EnrollmentId;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IServiceEnrollment {
    //CRUD
    String createEnrollment(Enrollment enrollment);
    String createEnrollment(List<Enrollment> enrollments);
    ResponseEntity<Enrollment> getEnrollmentById(EnrollmentId id);
    ResponseEntity<List<Enrollment>> allEnrollment();
    String updateEnrollment(Enrollment enrollment, Long idStudent, Long idCourse);
    String deleteEnrollment(EnrollmentId id);
    String addstudentEnrollment(EnrollmentStudentDTO request);
    //Method for make an enrollment for a student

}
