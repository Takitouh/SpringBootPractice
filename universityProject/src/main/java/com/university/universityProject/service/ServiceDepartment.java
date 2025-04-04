package com.university.universityProject.service;

import com.university.universityProject.DTO.DepartmentProfessorDTO;
import com.university.universityProject.entity.Department;
import com.university.universityProject.entity.Professor;
import com.university.universityProject.repository.IRepositoryDepartment;
import com.university.universityProject.repository.IRepositoryProfessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ServiceDepartment implements IServiceDepartment {
    private static final Logger logger = LoggerFactory.getLogger(ServiceDepartment.class);

    IRepositoryDepartment repositoryDepartment;
    IRepositoryProfessor repositoryProfessor;

    public ServiceDepartment(IRepositoryProfessor repositoryProfessor, IRepositoryDepartment repositoryDepartment) {
        this.repositoryProfessor = repositoryProfessor;
        this.repositoryDepartment = repositoryDepartment;
    }

    @Override
    public String createDepartment(Department department) {
        if (department == null) {
            logger.warn("A department was attempted to be created, but it was null.");
            return "Department can't be null";
        }
        repositoryDepartment.save(department);
        logger.info("Department {} has been created.", department.getDepartmentName());
        return "Department has been created succesfully.";    }

    @Override
    public String createDepartment(List<Department> deparments) {
        if (deparments == null) {
            logger.warn("A list of departments was attempted to be created, but it was null.");
            return "The list of departments can't be null.";
        }
        repositoryDepartment.saveAll(deparments);
        logger.info("A list of {} departments has been created.", deparments.size());
        return "The list of departments was created successfully";
    }

    @Override
    public ResponseEntity<Department> getDepartmentById(Long id) {
        if (id == null || id <= 0 || !repositoryDepartment.existsById(id) ||
                repositoryDepartment.findById(id).isEmpty()) {
            logger.warn("Attempt of retrieving a department that doesn't exist.");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Department department = repositoryDepartment.findById(id).get();
        return new ResponseEntity<>(department, HttpStatus.OK);    }

    @Override
    public ResponseEntity<List<Department>> allDepartments() {
        if(repositoryDepartment.count() == 0) {
            logger.warn("Attempt of retrieving a list of departments that doesn't exist.");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        List<Department> departments = repositoryDepartment.findAll();
        return new ResponseEntity<>(departments, HttpStatus.OK);
    }

    @Override
    public String updateDepartment(Department newdataDepartment, Long id) {
        if (id == null || id <= 0 || !repositoryDepartment.existsById(id) || repositoryDepartment.findById(id).isEmpty()) {
            logger.warn("Attempt of updating a department that doesn't exist.");
            return "The id of the department does not exist.";
        }
        Department department = repositoryDepartment.findById(id).get();
        department.setDepartmentName(newdataDepartment.getDepartmentName());
        department.setBuilding(newdataDepartment.getBuilding());
        department.setProfessors(newdataDepartment.getProfessors());
        repositoryDepartment.save(department);
        return "Department has been updated successfully.";
    }

    @Override
    public String deleteDepartment(Long id) {
        if (id == null || id <= 0 || !repositoryDepartment.existsById(id)) {
            logger.warn("Attempt of deleting a department that doesn't exist.");
            return "The ID doesn't match with any department in the database.";
        }
        repositoryDepartment.deleteById(id);
        return "Department has been deleted.";
    }

    @Override
    public String addprofessorDepartment(DepartmentProfessorDTO departmentprofessorRequest) {
        //Check if the values are null
        if (departmentprofessorRequest == null || repositoryDepartment.existsById(departmentprofessorRequest.getDepartmentId())) {
            return "The ID's of the department can't be null.";
        }
         //Save the id's in variables
        List<Long> professorsId = departmentprofessorRequest.getProfessorsId();
        Long departmentId = departmentprofessorRequest.getDepartmentId();
        //Validation
        if (professorsId.isEmpty() || repositoryProfessor.findAllById(professorsId).isEmpty()
                || departmentId == null || repositoryDepartment.findById(departmentId).isEmpty()
        ) {
            return "The ID's of the professor or the department can't be null.";
        }
        //Search and save the objects in variables
        Department department = repositoryDepartment.findById(departmentId).get();
        List<Professor> professors = repositoryProfessor.findAllById(professorsId);
        //Update the atributtes of each one
        for(Professor professor : professors) {
            if(repositoryProfessor.existsById(professor.getIdProfessor())) {
                professor.setDepartment(department);
                repositoryProfessor.save(professor);
                logger.info("Professor {} updated.", professor.getFirstName());
            } else {
                logger.warn("Professor with id {} does not exist.", professor.getIdProfessor());
            }
        }
        department.setProfessors(professors);
        logger.info("Department {} has been updated.", department.getDepartmentName());
        //Save again the objects
        repositoryDepartment.save(department);
        return "The changes have been done in department and professors.";
    }
}
