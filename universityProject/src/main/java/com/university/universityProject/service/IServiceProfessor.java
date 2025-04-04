package com.university.universityProject.service;

import com.university.universityProject.entity.Professor;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IServiceProfessor {
    //CRUD
    String createProfessor(Professor professor);
    String createProfessor(List<Professor> professors);
    ResponseEntity<Professor> getProfessorById(Long id);
    ResponseEntity<List<Professor>> allProfessors();
    String updateProfessor(Professor professor, Long id);
    String deleteProfessor(Long id);

}
