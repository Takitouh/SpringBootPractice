package com.university.universityProject.controller;

import com.university.universityProject.DTO.EnrollmentStudentDTO;
import com.university.universityProject.entity.Enrollment;
import com.university.universityProject.entity.EnrollmentId;
import com.university.universityProject.service.IServiceEnrollment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/enrollment")
public class ControllerEnrollment {
    final private IServiceEnrollment serviceEnrollment;

    public ControllerEnrollment(IServiceEnrollment serviceEnrollment) {
        this.serviceEnrollment = serviceEnrollment;
    }
    @GetMapping("/get/{enrollmentId}")
    @ResponseBody
    public ResponseEntity<Enrollment> readEnrollment(@PathVariable EnrollmentId enrollmentId) {
        return serviceEnrollment.getEnrollmentById(enrollmentId);
    }
    @GetMapping("/get")
    @ResponseBody
    public ResponseEntity<List<Enrollment>> readEnrollments() {
        return serviceEnrollment.allEnrollment();
    }

    @PostMapping("/createEnrollment")
    public String createEnrollment(@RequestBody Enrollment enrollment) {
        return serviceEnrollment.createEnrollment(enrollment);
    }

    @PostMapping("/createEnrollments")
    public String createEnrollments(@RequestBody List<Enrollment> enrollments) {
        return serviceEnrollment.createEnrollment(enrollments);
    }
    @DeleteMapping("/delete/{enrollmentId}")
    public String deleteEnrollment(@PathVariable EnrollmentId enrollmentId) {
        return serviceEnrollment.deleteEnrollment(enrollmentId);
    }
    @PutMapping("/update/{idStudent}/{idCourse}")
    public String updateEnrollment(@RequestBody Enrollment updEnrollment, @PathVariable Long idStudent, @PathVariable Long idCourse) {
        return serviceEnrollment.updateEnrollment(updEnrollment, idStudent, idCourse);
    }
    @PatchMapping("/addstudent")
    public String makeenrollmentStudent(@RequestBody EnrollmentStudentDTO request) {
        return serviceEnrollment.addstudentEnrollment(request);
    }
}
