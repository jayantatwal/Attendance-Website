package com.jayant.AttendanceWebsite.repository;

import com.jayant.AttendanceWebsite.model.ClassEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClassRepository extends JpaRepository<ClassEntity, Long> {
    boolean existsByName(String name);
}
