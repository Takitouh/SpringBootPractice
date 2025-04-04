package com.university.universityProject.controller;

import com.university.universityProject.entity.Student;
import com.university.universityProject.service.IServiceStudent;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/student")
public class ControllerStudent {
    final private IServiceStudent serviceStudent;

    public ControllerStudent(IServiceStudent serviceStudent) {
        this.serviceStudent = serviceStudent;
    }
    @GetMapping("/get/{id}")
    @ResponseBody
    public ResponseEntity<Student> readStudent(@PathVariable Long id) {
     return serviceStudent.getStudentById(id);
    }
    @GetMapping("/get")
    @ResponseBody
    public ResponseEntity<List<Student>> readStudents() {
        return serviceStudent.allStudents();
    }

    @PostMapping("/createStudent")
    public String createStudent(@RequestBody Student student) {
        return serviceStudent.createStudent(student);
    }

    @PostMapping("/createStudents")
    public String createStudents(@RequestBody List<Student> students) {
        return serviceStudent.createStudent(students);
    }
    @DeleteMapping("/delete/{id}")
    public String deleteStudent(@PathVariable Long id) {
        return serviceStudent.deleteStudent(id);
    }
    @PutMapping("/update/{id}")
    public String updateStudent(@RequestBody Student updStudent, @PathVariable Long id) {
        return serviceStudent.updateStudent(updStudent, id);
    }
}
//Remember to change the type return of the GetMappings of the others controller because with void it
//didn't return anything in the JSON
