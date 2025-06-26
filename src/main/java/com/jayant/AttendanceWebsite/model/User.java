package com.jayant.AttendanceWebsite.model;

import jakarta.persistence.*;
//This User class is a JPA entity that maps to a users table in your SQL database, storing user login info like username, password, and role.
@Entity
@Table(name = "users") // optional, just for naming
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //Tells Spring to let the database auto-generate the ID.
    private Long id;

    private String username;
    private String password;
    private String role; // "ADMIN", "STUDENT", "TEACHER"

    public User() {
        // Default constructor
    }
    public User(String username, String password, String role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }

    // Getters & setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }
}
