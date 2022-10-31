package com.tournament.springbootcrud.controllers;


import com.tournament.springbootcrud.models.Teacher;

import com.tournament.springbootcrud.repositories.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;

@Controller
public class TeacherController {
    @Autowired
    private TeacherRepository teacherRepository;

    @GetMapping("/teacher/new")
    public String addTeacher(Model model) {

        Teacher teacher=new Teacher();
        model.addAttribute("teacher", teacher);
        model.addAttribute("pageTitle", "Add new Teacher Details");

        return "TeacherAdd";
    }

    @PostMapping("/teacher/save")
    public String createTeacher(Teacher teacher, RedirectAttributes redirectAttributes) {
        try {
            teacherRepository.save(teacher);

            redirectAttributes.addFlashAttribute("message", "Teacher has been saved successfully!");
        } catch (Exception e) {
            redirectAttributes.addAttribute("message", e.getMessage());
        }

        return "redirect:/teacher";
    }

    @GetMapping("/teacher")
    public String getAllTeachers(Model model) {
        try {
            List<Teacher> teachers = new ArrayList<>();

            teacherRepository.findAll().forEach(teachers::add);
            model.addAttribute("teachers",  teachers);

        } catch (Exception e) {
            model.addAttribute("message", e.getMessage());
        }
        return "TeacherListView";
    }

    @GetMapping("/teacher/edit/{id}")
    public String editTeacher(@PathVariable("id") Integer id, Model model, RedirectAttributes redirectAttributes) {
        try {
            Teacher teacher=teacherRepository.findById(id).get();

            model.addAttribute("teacher", teacher);
            model.addAttribute("pageTitle", "Edit Teacher Details");
            teacherRepository.deleteById(id);
            teacher.setId(id);
            teacher.setName(teacher.getName());
            teacher.setAge(teacher.getAge());
            teacher.setSubject(teacher.getSubject());
            return "TeacherUpdate";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("message", e.getMessage());

            return "redirect:/teacher";
        }
    }

    @GetMapping("/teacher/delete/{id}")
    public String deleteTeacher(@PathVariable("id") Integer id, Model model, RedirectAttributes redirectAttributes) {
        try {
            teacherRepository.deleteById(id);

            redirectAttributes.addFlashAttribute("message", "The teacher has been deleted successfully!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("message", e.getMessage());
        }

        return "redirect:/teacher";
    }

}
