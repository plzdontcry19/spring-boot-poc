package com.demo.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.demo.backend.entity.Course;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
}
