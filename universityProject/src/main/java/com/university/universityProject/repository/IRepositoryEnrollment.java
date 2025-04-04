package com.university.universityProject.repository;

import com.university.universityProject.entity.Enrollment;
import com.university.universityProject.entity.EnrollmentId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IRepositoryEnrollment extends JpaRepository<Enrollment, EnrollmentId> {
}
