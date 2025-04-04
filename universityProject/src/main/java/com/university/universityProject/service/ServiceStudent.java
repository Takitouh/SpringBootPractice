package com.university.universityProject.service;

import com.university.universityProject.entity.Professor;
import com.university.universityProject.entity.Student;
import com.university.universityProject.repository.IRepositoryStudent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ServiceStudent implements IServiceStudent {
    private static final Logger logger = LoggerFactory.getLogger(ServiceStudent.class);
    final private IRepositoryStudent repositoryStudent;

    public ServiceStudent(IRepositoryStudent repositoryStudent) {
        this.repositoryStudent = repositoryStudent;
    }

    @Override
    public String createStudent(Student student) {
        if (student == null) {
            logger.warn("A Student was attempted to be created, but it was null.");
            return "Student can't be null";
        }
        repositoryStudent.save(student);
        logger.info("Student {} {} has been created.", student.getFirstName(), student.getLastName());
        return "Student has been created succesfully.";
    }

    @Override
    public String createStudent(List<Student> students) {
        if (students.isEmpty()) {
            logger.warn("A list of students was attempted to be created, but it was null.");
            return "The list of students can't be null.";
        }
        repositoryStudent.saveAll(students);
        logger.info("A list of {} students has been created.", students.size());
        return "The list of students was created successfully";
    }

    @Override
    public ResponseEntity<Student> getStudentById(Long id) {
        if (id == null || id <= 0 || !repositoryStudent.existsById(id) ||
                repositoryStudent.findById(id).isEmpty()) {
            logger.warn("Attempt of retrieving a professor that doesn't exist.");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Student student = repositoryStudent.findById(id).get();
        return new ResponseEntity<>(student, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<Student>> allStudents() {
        if(repositoryStudent.count() == 0) {
            logger.warn("Attempt of retrieving a list of students that doesn't exist.");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        List<Student> students = repositoryStudent.findAll();
        return new ResponseEntity<>(students, HttpStatus.OK);
    }

    @Override
    public String updateStudent(Student newDataStudent, Long id) {
        if (id == null || id <= 0 || !repositoryStudent.existsById(id) || repositoryStudent.findById(id).isEmpty()) {
            logger.warn("Attempt of updating a student that doesn't exist.");
            return "The id of the student does not exist.";
        }
        Student student = repositoryStudent.findById(id).get();
        student.setFirstName(newDataStudent.getFirstName());
        student.setLastName(newDataStudent.getLastName());
        student.setAge(newDataStudent.getAge());
        student.setDni(newDataStudent.getDni());
        student.setBirthDate(newDataStudent.getBirthDate());
        student.setEnrollmentStudent(newDataStudent.getEnrollmentStudent());
        repositoryStudent.save(student);
        return "Student has been updated successfully.";
    }

    @Override
    public String deleteStudent(Long id) {
        if (id == null || id <= 0 || !repositoryStudent.existsById(id)) {
            logger.warn("Attempt of deleting a student that doesn't exist.");
            return "The ID doesn't match with any student in the database.";
        }
        repositoryStudent.deleteById(id);
        return "Student has been deleted.";
    }
}

