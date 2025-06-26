package com.jayant.AttendanceWebsite;

import com.jayant.AttendanceWebsite.model.User;
import com.jayant.AttendanceWebsite.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class AttendanceWebsiteApplication {

	public static void main(String[] args) {
		SpringApplication.run(AttendanceWebsiteApplication.class, args);
	}
//	@Bean
//	CommandLineRunner run(UserRepository userRepo) {
//		return args -> {
//			if (userRepo.findAll().isEmpty()) {
//				userRepo.save(new User("admin", "admin123", "ADMIN"));
//				userRepo.save(new User("teacher", "teach123", "TEACHER"));
//				userRepo.save(new User("student", "stud123", "STUDENT"));
//			}
//		};
//	}
}
