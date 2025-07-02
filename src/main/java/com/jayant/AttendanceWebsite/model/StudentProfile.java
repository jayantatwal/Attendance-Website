package com.jayant.AttendanceWebsite.model;

import jakarta.persistence.*;

@Entity
@Table(name = "student_profile")
public class StudentProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "roll_number")
    private String rollNumber;

    private String phone;

    @Column(name = "full_name")
    private String fullName;

    @Column(name = "class_id")
    private Long classId;

    @Column(nullable = false, unique = true)
    private String email;

    // Constructors
    public StudentProfile() {}

    public StudentProfile(String rollNumber, String phone, String fullName, Long classId, String email) {
        this.rollNumber = rollNumber;
        this.phone = phone;
        this.fullName = fullName;
        this.classId = classId;
        this.email = email;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRollNumber() {
        return rollNumber;
    }

    public void setRollNumber(String rollNumber) {
        this.rollNumber = rollNumber;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Long getClassId() {
        return classId;
    }

    public void setClassId(Long classId) {
        this.classId = classId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
