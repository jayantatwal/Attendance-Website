package com.jayant.AttendanceWebsite.controller;

import com.jayant.AttendanceWebsite.model.*;
import com.jayant.AttendanceWebsite.repository.*;
import jakarta.transaction.Transactional;
import org.apache.commons.text.WordUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private ClassRepository classRepository;
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TeacherProfileRepository teacherProfileRepository;

    @Autowired
    private StudentProfileRepository studentProfileRepository;

    @Autowired
    private SubjectRepository subjectRepository;

    // Dashboard
    @GetMapping("/dashboard")
    public String showAdminDashboard() {
        return "admin/dashboard";
    }

    @GetMapping("/add-student")
    public String showAddStudentForm(Model model) {
        model.addAttribute("student", new User()); // assuming you're using User model for student
        model.addAttribute("classes", classRepository.findAll()); // fetch list of classes
        return "admin/add_student";
    }
    @PostMapping("/add-student")
    public String addStudent(@ModelAttribute("user") User user) {
        user.setRole("STUDENT");
        user.setPassword(""); // leave password empty so user can sign up later
        user.setStatus("PENDING"); // optional tracking status

        // Save to users table
        userRepository.save(user);

        // Lookup classId from class name
        Optional<ClassEntity> classEntityOpt = classRepository.findByName(user.getClassName());

        if (classEntityOpt.isPresent()) {
            ClassEntity classEntity = classEntityOpt.get();

            // Save to student_profile table
            StudentProfile studentProfile = new StudentProfile();
            studentProfile.setEmail(user.getEmail());
            studentProfile.setClassId(classEntity.getId());

            studentProfileRepository.save(studentProfile);
        }

        return "redirect:/admin/dashboard";
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
    @PostMapping("/add-subject")
    public String addSubject(@ModelAttribute Subject subject, RedirectAttributes redirectAttributes) {
        String rawName = subject.getName().trim().replaceAll("\\s+", " ");
        String formattedName = WordUtils.capitalizeFully(rawName);
        subject.setName(formattedName);

        try {
            subjectRepository.save(subject);
            redirectAttributes.addFlashAttribute("successMessage", "Subject added successfully.");
        } catch (Exception e) {
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("errorMessage", "Failed to add subject.");
        }

        return "redirect:/admin/dashboard";
    }

    @GetMapping("/delete-student")
    public String showDeleteStudentForm(Model model) {
        return "admin/delete_student"; // assuming this is your HTML page
    }

    @Transactional
    @PostMapping("/delete-student")
    public String deleteStudent(@RequestParam String email, Model model) {
        Optional<User> userOpt = userRepository.findByEmail(email);

        if (userOpt.isPresent() && "STUDENT".equalsIgnoreCase(userOpt.get().getRole())) {
            userRepository.deleteByEmail(email); // delete from users

            // Try deleting from student_profile as well
            studentProfileRepository.findByEmail(email).ifPresent(studentProfile -> {
                studentProfileRepository.deleteByEmail(email);
            });

            model.addAttribute("success", "Student deleted successfully.");
        } else {
            model.addAttribute("error", "No student found with that email.");
        }

        return "admin/delete_student";
    }

    // Teacher
    @GetMapping("/add-teacher")
    public String showAddTeacherForm(Model model) {
        model.addAttribute("user", new User()); // for the email input
        model.addAttribute("subjects", subjectRepository.findAll()); // list of all subjects
        return "admin/add_teacher"; // your Thymeleaf form page
    }


    @PostMapping("/add-teacher")
    public String addTeacher(@ModelAttribute("user") User user,
                             @RequestParam("selectedSubjects") List<Long> subjectIds,
                             RedirectAttributes redirectAttributes) {

        user.setRole("TEACHER");
        user.setPassword(""); // or a default placeholder
        userRepository.save(user);

        TeacherProfile profile = new TeacherProfile();
        profile.setEmail(user.getEmail());

        Set<Subject> subjects = new HashSet<>(subjectRepository.findAllById(subjectIds));
        profile.setSubjects(subjects);

        teacherProfileRepository.save(profile);

        redirectAttributes.addFlashAttribute("successMessage", "Teacher added successfully.");
        return "redirect:/admin/dashboard";
    }

    @GetMapping("/delete-teacher")
    public String showDeleteTeacherForm() {
        return "admin/delete_teacher";
    }

    @PostMapping("/delete-teacher")
    public String deleteTeacher(@RequestParam("email") String email, Model model) {
        Optional<User> userOpt = userRepository.findByEmail(email);

        if (userOpt.isPresent() && "TEACHER".equals(userOpt.get().getRole())) {
            userRepository.delete(userOpt.get());

            // Delete from teacher_profile if exists
            teacherProfileRepository.findByEmail(email).ifPresent(teacherProfileRepository::delete);

            model.addAttribute("success", "Teacher deleted successfully.");
        } else {
            model.addAttribute("error", "Teacher not found with given email.");
        }

        return "admin/delete_teacher";
    }

}
