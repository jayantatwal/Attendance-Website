package com.jayant.AttendanceWebsite.model;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "teacher_profile")
public class TeacherProfile {

    @ManyToMany
    @JoinTable(
            name = "teacher_subjects",
            joinColumns = @JoinColumn(name = "teacher_id"),
            inverseJoinColumns = @JoinColumn(name = "subject_id")
    )
    private Set<Subject> subjects = new HashSet<>();

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "full_name", length = 100)
    private String fullName;

    @Column(length = 50)
    private String designation;

    @Column(length = 100)
    private String department;

    @Column(unique = true, nullable = false)
    private String email;

    // Constructors
    public TeacherProfile() {}

    public TeacherProfile(String fullName, String designation, String department, String email) {
        this.fullName = fullName;
        this.designation = designation;
        this.department = department;
        this.email = email;
    }

    // Getters & Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getFullName() { return fullName; }
    public void setFullName(String fullName) { this.fullName = fullName; }

    public String getDesignation() { return designation; }
    public void setDesignation(String designation) { this.designation = designation; }

    public String getDepartment() { return department; }
    public void setDepartment(String department) { this.department = department; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public Set<Subject> getSubjects() {return subjects;}
    public void setSubjects(Set<Subject> subjects) {this.subjects = subjects;}
}
