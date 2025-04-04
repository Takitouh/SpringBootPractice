package com.university.universityProject.service;

import com.university.universityProject.entity.Course;
import com.university.universityProject.repository.IRepositoryCourse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ServiceCourse implements IServiceCourse {
    private static final Logger logger = LoggerFactory.getLogger(ServiceCourse.class);

    //I will use the constructor method for DI because I read it's a better practice
    IRepositoryCourse repositoryCourse;

    public ServiceCourse(IRepositoryCourse repositoryCourse) {
        this.repositoryCourse = repositoryCourse;
    }

    @Override
    public String createCourse(Course course) {
        if (course == null) {
            logger.warn("A course was attempted to be created, but it was null.");
            return "Course can't be null";
        }
        repositoryCourse.save(course);
        logger.info("Course {} has been created.", course.getCourseName());
        return "Course has been created succesfully.";
    }

    @Override
    public String createCourse(List<Course> courses) {
        if (courses.isEmpty()) {
            logger.warn("A list of courses was attempted to be created, but it was null.");
            return "The list of courses can't be null.";
        }
        repositoryCourse.saveAll(courses);
        logger.info("A list of {} courses has been created.", courses.size());
        return "The list of courses was created successfully";
    }

    @Override
    public ResponseEntity<Course> getCourseById(Long id) {
        if (id == null || id <= 0 || !repositoryCourse.existsById(id) ||
                repositoryCourse.findById(id).isEmpty()) {
            logger.warn("Attempt of retrieving a course that doesn't exist.");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Course course = repositoryCourse.findById(id).get();
        return new ResponseEntity<>(course, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<Course>> allCourses() {
        if(repositoryCourse.count() == 0) {
            logger.warn("Attempt of retrieving a list of courses that doesn't exist.");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        List<Course> courses = repositoryCourse.findAll();
        return new ResponseEntity<>(courses, HttpStatus.OK);
    }

    @Override
    public String updateCourse(Course updCourse, Long id) {
        if (updCourse == null || id == null || !repositoryCourse.existsById(id) ||
                repositoryCourse.findById(id).isEmpty()) {
            logger.warn("A course was attempted to be updated, but it was null.");
            return "Course can't be null";
        }
        Course course = repositoryCourse.findById(id).get();
        course.setCourseName(updCourse.getCourseName());
        course.setCredits(updCourse.getCredits());
        course.setDescription(updCourse.getDescription());
        course.setEnrollmentCourse(updCourse.getEnrollmentCourse());
        repositoryCourse.save(course);
        return "Course has been updated.";
    }

    @Override
    public String deleteCourse(Long id) {
        if (id == null || id <= 0 || !repositoryCourse.existsById(id)) {
            logger.warn("Attempt of deleting a course that doesn't exist.");
            return "The ID doesn't match with any course in the database.";
        }
        repositoryCourse.deleteById(id);
        return "Course has been deleted.";
    }
}
//#NOTE: We need to modify the type return of the READ of the others Services, we need use ResponseEntity