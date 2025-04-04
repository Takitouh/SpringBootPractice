package com.university.universityProject.repository;

import com.university.universityProject.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IRepositoryDepartment extends JpaRepository<Department, Long> {
}
