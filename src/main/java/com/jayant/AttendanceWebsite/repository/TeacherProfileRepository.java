package com.jayant.AttendanceWebsite.repository;

import com.jayant.AttendanceWebsite.model.TeacherProfile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TeacherProfileRepository extends JpaRepository<TeacherProfile, Long> {
    Optional<TeacherProfile> findByEmail(String email);
    void deleteByEmail(String email);
}
