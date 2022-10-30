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
public class TeacherController {
    @Autowired
    private TeacherRepository teacherRepo;

    @GetMapping("/teachers/new")
    public String addTeacher(Model model) {
        Teacher teacher = new Teacher();

        model.addAttribute("teacher", teacher);
        model.addAttribute("pageTitle", "Create new Teacher");

        return "StudentAddForm";//need to change with add form 
    }

    @PostMapping("/teachers/save")
    public String createTeacher(Teacher teacher, RedirectAttributes redirectAttributes) {
        try {
            teacherRepo.save(teacher);

            redirectAttributes.addFlashAttribute("message", "Teacher data has been saved successfully!");
        } catch (Exception e) {
            redirectAttributes.addAttribute("message", e.getMessage());
        }

        return "redirect:/teachers";
    }

    @GetMapping("/teachers")
    public String getAllTeachers(Model model) {
        try {
            List<Teacher> teachers = new ArrayList<>();

            teacherRepo.findAll().forEach(teachers::add);
            model.addAttribute("teachers",  teachers);

        } catch (Exception e) {
            model.addAttribute("message", e.getMessage());
        }
        return "StudentListView";//add the name of the table of teacher
    }

    @GetMapping("/teachers/edit/{id}")
    public String editTeacher(@PathVariable("id") Integer id, Model model, RedirectAttributes redirectAttributes) {
        try {
             Teacher teacher = teacherRepo.findById(id).get();

            model.addAttribute("teacher", teacher);
            model.addAttribute("pageTitle", "Edit Teacher Data");

            return "StudentAddForm";//add the anme of teacher update form 
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("message", e.getMessage());

            return "redirect:/teachers";
        }
    }

    @GetMapping("/teachers/delete/{id}")
    public String deleteTeacher(@PathVariable("id") Integer id, Model model, RedirectAttributes redirectAttributes) {
        try {
            teacherRepo.deleteById(id);

            redirectAttributes.addFlashAttribute("message", "The Teacher has been deleted successfully!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("message", e.getMessage());
        }

        return "redirect:/students";
    }
}
