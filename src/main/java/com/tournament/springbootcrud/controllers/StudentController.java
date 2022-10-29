package com.tournament.springbootcrud.controllers;

import com.tournament.springbootcrud.models.Student;
import com.tournament.springbootcrud.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class StudentController {
    @Autowired
    private StudentRepository studentRepo;

    @GetMapping("/students/new")
    public String addStudent(Model model) {
        Student student = new Student();

        model.addAttribute("student", student);
        model.addAttribute("pageTitle", "Create new Student");

        return "StudentAddForm";
    }

    @PostMapping("/students/save")
    public String createStudent(Student student, RedirectAttributes redirectAttributes) {
        try {
            studentRepo.save(student);

            redirectAttributes.addFlashAttribute("message", "Student data has been saved successfully!");
        } catch (Exception e) {
            redirectAttributes.addAttribute("message", e.getMessage());
        }

        return "redirect:/students";
    }

    @GetMapping("/students")
    public String getAllStudents(Model model) {
        try {
            List<Student> students = new ArrayList<>();

            studentRepo.findAll().forEach(students::add);
            model.addAttribute("students",  students);

        } catch (Exception e) {
            model.addAttribute("message", e.getMessage());
        }
        return "StudentListView";
    }

    @GetMapping("/students/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable("id") int id) {
        Optional<Student> studentData = studentRepo.findById(id);

        if (studentData.isPresent()) {
            return new ResponseEntity<>(studentData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/students/{id}")
    public ResponseEntity<Student> updateStudent(@PathVariable("id") int id, @RequestBody Student student) {
        Optional<Student> studentData = studentRepo.findById(id);

        if (studentData.isPresent()) {
            Student _student = studentData.get();
            _student.setName(student.getName());
            _student.setAge(student.getAge());
            _student.setGrade(student.getGrade());
            return new ResponseEntity<>(studentRepo.save(_student), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/students/{id}")
    public ResponseEntity<HttpStatus> deleteStudent(@PathVariable("id") int id) {
        try {
            studentRepo.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
