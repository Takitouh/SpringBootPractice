package com.university.universityProject.controller;

import com.university.universityProject.DTO.DepartmentProfessorDTO;
import com.university.universityProject.entity.Department;
import com.university.universityProject.service.IServiceDepartment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/department")
public class ControllerDepartment {

    final private IServiceDepartment serviceDepartment;

    public ControllerDepartment(IServiceDepartment serviceDepartment) {
        this.serviceDepartment = serviceDepartment;
    }

    @GetMapping("/get/{id}")
    @ResponseBody
    public ResponseEntity<Department> readDepartment(@PathVariable Long id) {
        return serviceDepartment.getDepartmentById(id);
    }
    @GetMapping("/get")
    @ResponseBody
    public ResponseEntity<List<Department>> readDepartments() {
        return serviceDepartment.allDepartments();
    }

    @PostMapping("/createDepartment")
    public String  createDepartment(@RequestBody Department department) {
        return serviceDepartment.createDepartment(department);
    }

    @PostMapping("/createDepartments")
    public String createDepartments(@RequestBody List<Department> departments) {
        return serviceDepartment.createDepartment(departments);
    }
    @DeleteMapping("/delete/{id}")
    public String deleteDepartment(@PathVariable Long id) {
        return serviceDepartment.deleteDepartment(id);
    }

    @PatchMapping("/addprof")
    public String addProfessor(@RequestBody DepartmentProfessorDTO requestaddProf){
        return serviceDepartment.addprofessorDepartment(requestaddProf);
    }
    @PutMapping("/update/{id}")
    public String updateDepartment(@RequestBody Department updDepartment, @PathVariable Long id) {
        return serviceDepartment.updateDepartment(updDepartment, id);
    }
}
