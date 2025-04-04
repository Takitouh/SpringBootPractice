package com.university.universityProject.repository;

import com.university.universityProject.entity.Professor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IRepositoryProfessor extends JpaRepository<Professor, Long> {
}
