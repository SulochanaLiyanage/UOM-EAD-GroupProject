package com.tournament.springbootcrud.controllers;

import com.tournament.springbootcrud.models.Course;
import com.tournament.springbootcrud.repositories.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.ArrayList;
import java.util.List;

@Controller
public class CourseController {

    @Autowired
    private CourseRepository courseRepository;

    @GetMapping("/course/new")
    public String addCourse(Model model) {

        Course course=new Course();
        model.addAttribute("course", course);
        model.addAttribute("pageTitle", "Create new Course");

        return "CourseAddForm";
    }

    @PostMapping("/course/save")
    public String createCourse(Course course, RedirectAttributes redirectAttributes) {
        try {
            courseRepository.save(course);

            redirectAttributes.addFlashAttribute("message", "Course has been saved successfully!");
        } catch (Exception e) {
            redirectAttributes.addAttribute("message", e.getMessage());
        }

        return "redirect:/course";
    }

    @GetMapping("/course")
    public String getAllCourses(Model model) {
        try {
            List<Course> courses = new ArrayList<>();

            courseRepository.findAll().forEach(courses::add);
            model.addAttribute("courses",  courses);

        } catch (Exception e) {
            model.addAttribute("message", e.getMessage());
        }
        return "CourseListView";
    }

    @GetMapping("/course/edit/{id}")
    public String editCourse(@PathVariable("id") Integer id, Model model, RedirectAttributes redirectAttributes) {
        try {
            Course course=courseRepository.findById(id).get();
            model.addAttribute("course", course);
            model.addAttribute("pageTitle", "Edit Course Data");
            courseRepository.deleteById(id);
            course.setID(id);
            course.setName(course.getName());
            course.setHours(course.getHours());
            return "CourseUpdate";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("message", e.getMessage());

            return "redirect:/course";
        }
    }

    @GetMapping("/course/delete/{id}")
    public String deleteCourse(@PathVariable("id") Integer id, Model model, RedirectAttributes redirectAttributes) {
        try {
            courseRepository.deleteById(id);

            redirectAttributes.addFlashAttribute("message", "The Student has been deleted successfully!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("message", e.getMessage());
        }

        return "redirect:/course";
    }
    @GetMapping("/home")
    public String homePage()
    {
        return "home";
    }
}
