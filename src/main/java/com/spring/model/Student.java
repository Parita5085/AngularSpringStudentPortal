package com.spring.model;

import java.util.Random;

import javax.persistence.Column;
import javax.persistence.Entity;  
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Student {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	@Column(nullable=false)
	private String name;
	@Column(nullable=false)
	private String course;
	private double fees;
	@Column(nullable=false)
	private String mobileNo;
	@Column(nullable=false)
	private String username;
	@Column(nullable=false)
	private String password;
		
	public Student() {
		
		//Random password
		String choices = "abcdefghijklmn0123456789";
		String randomPassword = "";
		int length = 6;
		
		Random r = new Random();
		char[] randomChar = new char[length];
		
		for(int i=0; i<length; i++) {
			randomChar[i] = choices.charAt(r.nextInt(choices.length()));
		}
		
		for(int i=0; i<randomChar.length; i++) {
			randomPassword = randomPassword + randomChar[i];
		}
		this.password = randomPassword;
		
		//Random username
		String choiceForUsername = "123456789";
		String randomNumber = "";
		int lengthUsername = 2;
		char[] randomUsername = new char[lengthUsername];
		
		for(int i=0; i<lengthUsername; i++) {
			randomUsername[i] = choiceForUsername.charAt(r.nextInt(choiceForUsername.length()));
		}
		
		for(int i=0; i<randomUsername.length; i++) {
			randomNumber += randomUsername[i];
		}
		this.username = "Univ_" + randomNumber;
	}

	public Student(String name, String course, double fees, String mobileNo) {
		this.name = name;
		this.course = course;
		this.fees = fees;
		this.mobileNo = mobileNo;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCourse() {
		return course;
	}

	public void setCourse(String course) {
		this.course = course;
	}

	public double getFees() {
		return fees;
	}

	public void setFees(double fees) {
		this.fees = fees;
	}

	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setGivenPassword(String password) {
		this.password = password;
	}
	
	public void setPassword(String password) {
	this.password = password;
	}


	@Override
	public String toString() {
		return "Student [id=" + id + ", name=" + name + ", course=" + course + ", fees=" + fees + ", mobileNo="
				+ mobileNo + ", username=" + username + ", password=" + password + "]";
	}

	
	
}
