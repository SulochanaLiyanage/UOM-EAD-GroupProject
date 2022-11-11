package com.tournament.springbootcrud.controllers;

import com.tournament.springbootcrud.models.Student;
import com.tournament.springbootcrud.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;

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

    @GetMapping("/students/edit/{id}")
    public String editStudent(@PathVariable("id") Integer id, Model model, RedirectAttributes redirectAttributes) {
        try {
            Student student = studentRepo.findById(id).get();

            model.addAttribute("student", student);
            model.addAttribute("pageTitle", "Edit Student Data");

            return "StudentAddForm";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("message", e.getMessage());

            return "redirect:/students";
        }
    }

    @GetMapping("/students/delete/{id}")
    public String deleteStudent(@PathVariable("id") Integer id, Model model, RedirectAttributes redirectAttributes) {
        try {
            studentRepo.deleteById(id);

            redirectAttributes.addFlashAttribute("message", "The Student has been deleted successfully!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("message", e.getMessage());
        }

        return "redirect:/students";
    }

    @GetMapping("students/findStudentByName")
    public String homePage(Model model)
    {
        model.addAttribute("something","randika 46");
        return "FoundStudents";
    }


    @GetMapping("/students/findStudentByName/{studentName}")
    public String getUserByName(@PathVariable String studentName, Model model){
        List<Student> students = new ArrayList<>();

        studentRepo.getStudentByName(studentName).forEach(students::add);
        model.addAttribute("data",  students);



//        Student st= studentRepo.getStudentByName(studentName);
//        model.addAttribute("data",st);
        return "FoundStudents";
    }

    @GetMapping("/students/findStudentByName/age/{studentAge}")
    public String getStudentByAge(@PathVariable String studentAge, Model model){
        List<Student> students = new ArrayList<>();

        studentRepo.getStudentAge(studentAge).forEach(students::add);
        model.addAttribute("data",  students);
//        Student st= studentRepo.getStudentAge(studentAge);
//        model.addAttribute("data",st);
        return "FoundStudents";
    }

}
