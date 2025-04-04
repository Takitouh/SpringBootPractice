package com.university.universityProject.service;

import com.university.universityProject.entity.Professor;
import com.university.universityProject.repository.IRepositoryProfessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ServiceProfessor implements IServiceProfessor {
    private static final Logger logger = LoggerFactory.getLogger(ServiceProfessor.class);
    final private IRepositoryProfessor repositoryProfessor;

    public ServiceProfessor(IRepositoryProfessor repositoryProfessor) {
        this.repositoryProfessor = repositoryProfessor;
    }

    @Override
    public String createProfessor(Professor professor) {
        if (professor == null) {
            logger.warn("A professor was attempted to be created, but it was null.");
            return "Professor can't be null";
        }
        repositoryProfessor.save(professor);
        logger.info("Professor {} {} has been created.", professor.getFirstName(), professor.getLastName());
        return "Professor has been created succesfully.";
    }

    @Override
    public String createProfessor(List<Professor> professors) {
        if (professors.isEmpty()) {
            logger.warn("A list of professors was attempted to be created, but it was null.");
            return "The list of professors can't be null.";
        }
        repositoryProfessor.saveAll(professors);
        logger.info("A list of {} professors has been created.", professors.size());
        return "The list of professors was created successfully";
    }

    @Override
    public ResponseEntity<Professor> getProfessorById(Long id) {
        if (id == null || id <= 0 || !repositoryProfessor.existsById(id) ||
                repositoryProfessor.findById(id).isEmpty()) {
            logger.warn("Attempt of retrieving a professor that doesn't exist.");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Professor professor = repositoryProfessor.findById(id).get();
        return new ResponseEntity<>(professor, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<Professor>> allProfessors() {
        if(repositoryProfessor.count() == 0) {
            logger.warn("Attempt of retrieving a list of professors that doesn't exist.");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        List<Professor> professors = repositoryProfessor.findAll();
        return new ResponseEntity<>(professors, HttpStatus.OK);
    }

    @Override
    public String updateProfessor(Professor newdataProfessor, Long id) {
        if (id == null || id <= 0 || !repositoryProfessor.existsById(id) || repositoryProfessor.findById(id).isEmpty()) {
            logger.warn("Attempt of updating a professor that doesn't exist.");
            return "The id of the professor does not exist.";
        }
        Professor professor = repositoryProfessor.findById(id).get();
        professor.setFirstName(newdataProfessor.getFirstName());
        professor.setLastName(newdataProfessor.getLastName());
        professor.setEmail(newdataProfessor.getEmail());
        professor.setSpecialty(newdataProfessor.getSpecialty());
        professor.setDni(newdataProfessor.getDni());
        professor.setDepartment(newdataProfessor.getDepartment());
        repositoryProfessor.save(professor);
        return "Professor has been updated successfully.";
    }

    @Override
    public String deleteProfessor(Long id) {
        if (id == null || id <= 0 || !repositoryProfessor.existsById(id)) {
            logger.warn("Attempt of deleting a professor that doesn't exist.");
            return "The ID doesn't match with any professor in the database.";
        }
        repositoryProfessor.deleteById(id);
        return "Professor has been deleted.";
    }
}

