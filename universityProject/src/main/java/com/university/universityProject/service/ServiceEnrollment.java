package com.university.universityProject.service;

import com.university.universityProject.DTO.EnrollmentStudentDTO;
import com.university.universityProject.entity.*;
import com.university.universityProject.repository.IRepositoryCourse;
import com.university.universityProject.repository.IRepositoryEnrollment;
import com.university.universityProject.repository.IRepositoryStudent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ServiceEnrollment implements IServiceEnrollment{

    private final IRepositoryEnrollment repositoryEnrollment;
    private final IRepositoryStudent repositoryStudent;
    private final IRepositoryCourse repositoryCourse;
    private static final Logger logger = LoggerFactory.getLogger(ServiceEnrollment.class);

    public ServiceEnrollment(IRepositoryEnrollment repositoryEnrollment, IRepositoryStudent repositoryStudent, IRepositoryCourse repositoryCourse) {
        this.repositoryEnrollment = repositoryEnrollment;
        this.repositoryStudent = repositoryStudent;
        this.repositoryCourse = repositoryCourse;
    }

    @Override
    public String createEnrollment(Enrollment enrollment) {
        if (enrollment == null) {
            logger.warn("A enrollment was attempted to be created, but it was null.");
            return "Enrollment can't be null";
        }
        repositoryEnrollment.save(enrollment);
        logger.info("Enrollment idCourse: {} idStudent {} has been created.", enrollment.getIdCourse(), enrollment.getIdStudent());
        return "Enrollment has been created succesfully.";
    }

    @Override
    public String createEnrollment(List<Enrollment> enrollments) {
        if (enrollments.isEmpty()) {
            logger.info("A list of enrollments was attempted to be created, but it was null.");
            return "The list of enrollments can't be null.";
        }
        repositoryEnrollment.saveAll(enrollments);
        logger.info("A list of {} enrollments has been created.", enrollments.size());
        return "The list of enrollments was created successfully";    }

    @Override
    public ResponseEntity<Enrollment> getEnrollmentById(EnrollmentId id) {
        if (id == null || id.getIdCourse() <= 0 || id.getIdStudent() <= 0
                || repositoryEnrollment.findById(id).isEmpty()) {
            logger.info("Attempt of retrieving a course that doesn't exist.");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Enrollment enrollment = repositoryEnrollment.findById(id).get();
        return new ResponseEntity<>(enrollment, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<Enrollment>> allEnrollment() {
        if(repositoryEnrollment.count() == 0) {
            logger.info("Attempt of retrieving a list of enrollments that doesn't exist.");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        List<Enrollment> enrollments = repositoryEnrollment.findAll();
        return new ResponseEntity<>(enrollments, HttpStatus.OK);
    }

    @Override
    public String updateEnrollment(Enrollment newDataEnrollment, Long idStudent, Long idCourse) {
        EnrollmentId id = new EnrollmentId(idStudent, idCourse);
        if (id.getIdStudent() <= 0 || id.getIdCourse() <= 0 || !repositoryEnrollment.existsById(id) || repositoryEnrollment.findById(id).isEmpty()) {
            logger.warn("Attempt of updating an enrollment that doesn't exist.");
            return "The id of the enrollment does not exist.";
        }
        Enrollment enrollment = repositoryEnrollment.findById(id).get();
        enrollment.setStartdateEnrollment(newDataEnrollment.getStartdateEnrollment());
        enrollment.setFinaldateEnrollment(newDataEnrollment.getFinaldateEnrollment());
        enrollment.setIdCourse(newDataEnrollment.getIdCourse());
        enrollment.setIdStudent(newDataEnrollment.getIdStudent());
        enrollment.setFinalNote(newDataEnrollment.getFinalNote());
        enrollment.setStudent(newDataEnrollment.getStudent());
        enrollment.setCourse(newDataEnrollment.getCourse());

        repositoryEnrollment.save(enrollment);
        return "Enrollment has been updated successfully.";
    }

    @Override
    public String deleteEnrollment(EnrollmentId id) {
        if (id == null || id.getIdStudent() <= 0 || id.getIdCourse() <= 0 ||
                !repositoryEnrollment.existsById(id)) {
            logger.info("Attempt of deleting a enrollment that doesn't exist.");
            return "The ID doesn't match with any enrollment in the database.";
        }
        repositoryEnrollment.deleteById(id);
        return "Enrrolment has been deleted.";
    }

    @Override
    public String addstudentEnrollment(EnrollmentStudentDTO request) {
        //Validate the request and ID's and the end of the enrollment which is PeriodEnrollment
        // may be after the enrollmentDate
        if (request == null || request.getIdCourse() <= 0 || request.getIdStudent() <= 0
                || request.getFinalenrollmentDate() == null ||
                request.getFinalenrollmentDate().after(request.getStartenrollmentDate())) {
            logger.info("Invalid arguments");
            return "The request is invalid, please retry.";
        }
        Long idStudent = request.getIdStudent();
        Long idCourse = request.getIdCourse();
        Date finaldateEnrollment = request.getFinalenrollmentDate();
        Date startdateEnrollment = request.getStartenrollmentDate();

        EnrollmentId idEnrollment = new EnrollmentId();

        idEnrollment.setIdStudent(idStudent);
        idEnrollment.setIdCourse(idCourse);

        if (repositoryEnrollment.existsById(idEnrollment) ||
                repositoryEnrollment.findById(idEnrollment).isPresent()) {
            logger.info("Enrollment with courseId {}, studentId {} and final date {}, ALREADY exists", idCourse, idStudent, finaldateEnrollment);
            return "The enrollment already exist.";
        }
        if (!repositoryStudent.existsById(idStudent) || !repositoryCourse.existsById(idCourse)
        || repositoryStudent.findById(idStudent).isPresent() || repositoryCourse.findById(idCourse).isPresent()) {
            logger.info("The student with id {} and the course with de id {} doesn't exist.", idStudent, idCourse);
            return "The student or the course doesn't exist.";
        }
        Enrollment enrollment = repositoryEnrollment.findById(idEnrollment).get();
        Course course = repositoryCourse.findById(idCourse).get();
        Student student = repositoryStudent.findById(idStudent).get();

        enrollment.setIdStudent(idStudent);
        enrollment.setIdCourse(idCourse);
        enrollment.setFinaldateEnrollment(finaldateEnrollment);
        enrollment.setStartdateEnrollment(startdateEnrollment);
        enrollment.setStudent(student);
        enrollment.setCourse(course);
        logger.info("New enrollment Student: {} and Course: {}", enrollment.getIdStudent(), enrollment.getIdCourse());
        return "The student was added to the enrollment with the course requested";
    }

    //Maybe we mistake at the time of assign the Id finalDate for enrollment
}
