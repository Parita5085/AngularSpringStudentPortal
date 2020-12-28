package com.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.ResponseEntity;

import com.spring.model.Student;

public interface StudentRepository extends JpaRepository<Student, Long>{
	public Student getByUsernameAndPassword(String username, String password);
}
