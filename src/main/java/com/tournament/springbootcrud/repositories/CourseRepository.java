package com.tournament.springbootcrud.repositories;

import com.tournament.springbootcrud.models.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Integer> {
}
