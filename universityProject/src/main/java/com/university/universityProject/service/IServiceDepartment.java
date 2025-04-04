package com.university.universityProject.service;

import com.university.universityProject.DTO.DepartmentProfessorDTO;
import com.university.universityProject.entity.Department;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IServiceDepartment {
    //CRUD
    String createDepartment(Department department);
    String createDepartment(List<Department> deparments);
    ResponseEntity<Department> getDepartmentById(Long id);
    ResponseEntity<List<Department>> allDepartments();
    String updateDepartment(Department newdataDepartment, Long id);
    String deleteDepartment(Long id);
    String addprofessorDepartment(DepartmentProfessorDTO departmentprofessorRequest);
    //Method for add a professor to a department

}
