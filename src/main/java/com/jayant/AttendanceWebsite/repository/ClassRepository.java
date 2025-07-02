package com.jayant.AttendanceWebsite.repository;

import com.jayant.AttendanceWebsite.model.ClassEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClassRepository extends JpaRepository<ClassEntity, Long> {
    boolean existsByName(String name);
    Optional<ClassEntity> findByName(String name);
}
