package com.jayant.AttendanceWebsite.controller;

import com.jayant.AttendanceWebsite.model.ClassEntity;
import com.jayant.AttendanceWebsite.model.Subject;
import com.jayant.AttendanceWebsite.repository.ClassRepository;
import org.apache.commons.text.WordUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private ClassRepository classRepository;

    // Dashboard
    @GetMapping("/dashboard")
    public String showAdminDashboard() {
        return "admin/dashboard";
    }

    // Student
    @GetMapping("/add-student")
    public String showAddStudentForm(Model model) {
        return "admin/add_student";
    }

    // Teacher
    @GetMapping("/add-teacher")
    public String showAddTeacherForm() {
        return "admin/add_teacher";
    }

    // Timetable
    @GetMapping("/add-timetable")
    public String showAddTimetableForm() {
        return "admin/add_timetable";
    }

    // Class - GET
    @GetMapping("/add-class")
    public String showAddClassForm(Model model) {
        model.addAttribute("classEntity", new ClassEntity());
        return "admin/add_class";
    }

    // Class - POST
    @PostMapping("/add-class")
    public String saveClass(@ModelAttribute ClassEntity classEntity, RedirectAttributes redirectAttributes) {
        String rawInput = classEntity.getName().trim().replaceAll("\\s+", " ");
        String formattedName = WordUtils.capitalizeFully(rawInput);
        classEntity.setName(formattedName);

        try {
            classRepository.save(classEntity);
            redirectAttributes.addFlashAttribute("successMessage", "Class saved successfully.");
        } catch (Exception e) {
            e.printStackTrace(); // optional: use a logger in production
            redirectAttributes.addFlashAttribute("errorMessage", "Class already exists or failed to save.");
        }

        return "redirect:/admin/dashboard";
    }

    // Subject
    @GetMapping("/add-subject")
    public String showAddSubjectForm(Model model) {
        model.addAttribute("subject", new Subject());
        return "admin/add_subject";
    }
}
