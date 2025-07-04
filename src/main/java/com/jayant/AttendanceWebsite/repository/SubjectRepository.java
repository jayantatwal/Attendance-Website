package com.jayant.AttendanceWebsite.repository;

import com.jayant.AttendanceWebsite.model.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubjectRepository extends JpaRepository<Subject, Long> {
    boolean existsByName(String name);
}
