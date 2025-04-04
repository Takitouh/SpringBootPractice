package com.university.universityProject.repository;

import com.university.universityProject.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IRepositoryCourse extends JpaRepository<Course, Long> {
}
