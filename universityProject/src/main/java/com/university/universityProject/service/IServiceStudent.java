package com.university.universityProject.service;

import com.university.universityProject.entity.Student;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IServiceStudent {
    //CRUD
    String createStudent(Student student);
    String createStudent(List<Student> students);
    ResponseEntity<Student> getStudentById(Long id);
    ResponseEntity<List<Student>> allStudents();
    String updateStudent(Student newdataStudent, Long id);
    String deleteStudent(Long id);
}
