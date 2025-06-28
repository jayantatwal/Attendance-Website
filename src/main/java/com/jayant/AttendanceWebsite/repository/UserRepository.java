package com.jayant.AttendanceWebsite.repository;

import com.jayant.AttendanceWebsite.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
//It allows you to interact with the users table in MySQL using Java methods, like finding users by their username

// Automatically gives you built-in methods like save(), findById(), delete() AND
//Adds a custom method findByUsername(String username) to fetch a user by their username from the database.
public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String username);
}
