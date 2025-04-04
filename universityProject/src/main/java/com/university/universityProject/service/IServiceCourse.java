package com.university.universityProject.service;

import com.university.universityProject.entity.Course;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IServiceCourse {
    //CRUD
    String createCourse(Course course);
    String createCourse(List<Course> courses);
    ResponseEntity<Course> getCourseById(Long id);
    ResponseEntity<List<Course>> allCourses();
    String updateCourse(Course updCourse, Long id);
    String deleteCourse(Long id);

}
