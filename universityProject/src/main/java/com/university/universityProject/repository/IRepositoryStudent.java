package com.university.universityProject.repository;

import com.university.universityProject.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IRepositoryStudent extends JpaRepository<Student, Long> {
}
