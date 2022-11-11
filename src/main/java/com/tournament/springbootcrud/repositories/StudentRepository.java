package com.tournament.springbootcrud.repositories;

import com.tournament.springbootcrud.models.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student,Integer> {
    @Query(value="SELECT * FROM students WHERE Name=?1", nativeQuery = true)
    List<Student> getStudentByName(String studentName);

    @Query(value="SELECT * FROM students WHERE age=?1", nativeQuery = true)
    List<Student> getStudentAge(String studentAge);

}
