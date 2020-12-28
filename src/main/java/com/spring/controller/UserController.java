package com.spring.controller;
import java.util.ArrayList;    
import java.util.HashMap;  
import java.util.List;
import java.util.Map;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.spring.exception.ResourceNotFoundException;
import com.spring.model.*;
import com.spring.repository.StudentRepository;
 
@CrossOrigin(origins="http://localhost:4100")

@RestController
public class UserController {   
	
	@Autowired
	private StudentRepository studentRepo;
	
	@PostMapping("/students/loginAdmin")
	public Admin loginAdmin(@RequestBody Admin admin) throws Exception {
		Admin adminOb = new Admin();
		String tempUsername = admin.getUsername();
		String tempPassword = admin.getPassword();
		if(!(adminOb.getUsername().equals(tempUsername) && adminOb.getPassword().equals(tempPassword))) {
			throw new Exception("Invalid credentials");
		}
		return admin;
	}
	
	
	@PostMapping("/students/loginStudent")
	public Student loginStudent(@RequestBody Student student) throws Exception {
		 String tempUsername = student.getUsername();
		 String tempPassword = student.getPassword();
		 Student studentOb = null;
		 if(tempUsername!=null && tempPassword!=null) {
			 studentOb =  studentRepo.getByUsernameAndPassword(tempUsername, tempPassword);
		 }
		 if(studentOb==null) {
			 throw new Exception("Invalid Credentials");
		 }
		 return studentOb;
	}
	
	@PostMapping(value = "/students")
	public Student addStudent(@RequestBody Student student) throws Exception {
		List<Student> studentsList = studentRepo.findAll();
		for(Student ob : studentsList) {
			if(ob.getName().equals(student.getName()) && ob.getMobileNo().equals(student.getMobileNo())) {
				throw new Exception("This student already exists");
			}
		}
		if(student==null) {
			throw new Exception("Enter valid credentials");
		}
		return studentRepo.save(student);
	}
	
	@GetMapping("/students")
	public List<Student>showAllStudents() {
		return studentRepo.findAll();
	}
	
	@GetMapping("/students/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable(value = "id") Long studentId)
        throws ResourceNotFoundException {
        Student student = studentRepo.findById(studentId)
          .orElseThrow(() -> new ResourceNotFoundException("Movie not found for this id :: " + studentId));
        return ResponseEntity.ok().body(student);
    }
	
	@PutMapping("/students/{id}")
    public ResponseEntity<Student> updateStudent(
    		@PathVariable(value = "id") Long studentId, @RequestBody Student studentDetails) throws ResourceNotFoundException {
			Student student = studentRepo.findById(studentId)
			.orElseThrow(()-> new ResourceNotFoundException("Student not found"));
			
			student.setGivenPassword(studentDetails.getPassword());
			Student updatedStudent = studentRepo.save(student);
			return ResponseEntity.ok().body(updatedStudent);
    }
	
	
	@DeleteMapping("/students/{id}")
    public Map<String, Boolean> deleteMovie(@PathVariable(value = "id") Long studentId)
         throws ResourceNotFoundException {
        Student student = studentRepo.findById(studentId)
       .orElseThrow(() -> new ResourceNotFoundException("Movie not found for this id :: " + studentId));

        studentRepo.delete(student);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
	
}
