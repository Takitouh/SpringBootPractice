package com.university.universityProject.controller;

import com.university.universityProject.entity.Course;
import com.university.universityProject.service.ServiceCourse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/course")
public class ControllerCourse {
    final private ServiceCourse serviceCourse;

    public ControllerCourse(ServiceCourse serviceCourse) {
        this.serviceCourse = serviceCourse;
    }
    @GetMapping("/get/{id}")
    @ResponseBody
    public ResponseEntity<Course> readCourse(@PathVariable Long id) {
        return serviceCourse.getCourseById(id);
    }
    @GetMapping("/get")
    @ResponseBody
    public ResponseEntity<List<Course>> readCourses() {
        return serviceCourse.allCourses();
    }

    @PostMapping("/createCourse")
    public String createCourse(@RequestBody Course course) {
        return serviceCourse.createCourse(course);
    }

    @PostMapping("/createCourses")
    public String createCourses(@RequestBody List<Course> courses) {
        return serviceCourse.createCourse(courses);
    }
    @DeleteMapping("/delete/{id}")
    public String deleteCourse(@PathVariable Long id) {
        return serviceCourse.deleteCourse(id);
    }
    @PutMapping("/update/{id}")
    public String updateCourse(@RequestBody Course updCourse, @PathVariable Long id ) {
        return serviceCourse.updateCourse(updCourse, id);
    }
}

