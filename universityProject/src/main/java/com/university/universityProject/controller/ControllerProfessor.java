package com.university.universityProject.controller;

import com.university.universityProject.entity.Professor;
import com.university.universityProject.service.IServiceProfessor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/professor")
public class ControllerProfessor {
    final private IServiceProfessor serviceProfessor;

    public ControllerProfessor(IServiceProfessor serviceProfessor) {
        this.serviceProfessor = serviceProfessor;
    }

    @GetMapping("/get/{id}")
    @ResponseBody
    public ResponseEntity<Professor> readProfessor(@PathVariable Long id) {
        return serviceProfessor.getProfessorById(id);
    }
    @GetMapping("/get")
    @ResponseBody
    public ResponseEntity<List<Professor>> readProfessors() {
        return serviceProfessor.allProfessors();
    }

    @PostMapping("/createProfessor")
    public String createProfessor(@RequestBody Professor professor) {
        return serviceProfessor.createProfessor(professor);
    }

    @PostMapping("/createProfessors")
    public String createProfessors(@RequestBody List<Professor> professors) {
        return serviceProfessor.createProfessor(professors);
    }
    @DeleteMapping("/delete/{id}")
    public String deleteProfessor(@PathVariable Long id) {
        return serviceProfessor.deleteProfessor(id);
    }
    @PutMapping("/update/{id}")
    public String updateProfessor(@RequestBody Professor updProfessor, @PathVariable Long id) {
        return serviceProfessor.updateProfessor(updProfessor, id);
    }
}
