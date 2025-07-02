package com.jayant.AttendanceWebsite.model;

import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;

    private String password;

    private String role; // "ADMIN", "STUDENT", "TEACHER"

    @Column(name = "class_name") // Optional: specify column name
    private String className;    // e.g., "MCA Morning"

    private String status;       // e.g., "PENDING" or "ACTIVE"

    public User() {}

    public User(String email, String password, String role, String className, String status) {
        this.email = email;
        this.password = password;
        this.role = role;
        this.className = className;
        this.status = status;
    }

    // Getters & setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }

    public String getClassName() { return className; }
    public void setClassName(String className) { this.className = className; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}
