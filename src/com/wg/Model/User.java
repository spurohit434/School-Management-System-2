package com.wg.Model;

import java.sql.Connection;
import java.util.List;
import java.util.Scanner;

public class User {
	private String userId;
	private String name;
	private String dateOfBirth;
	private String contactNumber;
	private Role role;
	private String password;
	private int standard;
	private String address;
	private String username;
	private int age;
	private String email;
	private String gender;
	private String rollNo;
	private List<Integer> assignedToStandard;
	private int mentorOf;

	private Connection connection;
	private Scanner scanner;

	public User(String userId, String name, String dateOfBirth, String contactNumber, Role role, String password,
			int standard, String address, int age, String email, String gender, String rollNo,
			List<Integer> assignedToStandard, int mentorOf, Connection connection, Scanner scanner) {
		super();
		this.userId = userId;
		this.name = name;
		this.dateOfBirth = dateOfBirth;
		this.contactNumber = contactNumber;
		this.role = role;
		this.password = password;
		this.standard = standard;
		this.address = address;
		this.age = age;
		this.email = email;
		this.gender = gender;
		this.rollNo = rollNo;
		this.assignedToStandard = assignedToStandard;
		this.mentorOf = mentorOf;
		this.connection = connection;
		this.scanner = scanner;
	}

	public User(Connection connection, Scanner scanner) {
		this.connection = connection;
		this.scanner = scanner;
	}

	// Constructors
	public User() {
	}

	public User(String userId, String name, String email) {
		this.userId = userId;
		this.name = name;
		this.email = email;
	}

	public User(String userId, String name, String email, String password) {
		this.userId = userId;
		this.name = name;
		this.email = email;
		this.password = password;
	}

	public User(String userId, String name, int age, String password, String email, Role role) {
		this.userId = userId;
		this.name = name;
		this.email = email;
		this.age = age;
		this.password = password;
		this.role = role;
	}

	public User(String userId, String name, String email, int age, int standard) {
		this.userId = userId;
		this.name = name;
		this.email = email;
		this.age = age;
		this.standard = standard;
	}

	public User(String userId, String username, String name, int age, String password, String email, Role role) {
		// TODO Auto-generated constructor stub
		this.userId = userId;
		this.username = username;
		this.name = name;
		this.email = email;
		this.age = age;
		this.password = password;
		this.role = role;
	}

	// getter setters
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getStandard() {
		return standard;
	}

	public void setStandard(int standard) {
		this.standard = standard;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getRollNo() {
		return rollNo;
	}

	public void setRollNo(String rollNo) {
		this.rollNo = rollNo;
	}

	public List<Integer> getAssignedTOStandard() {
		return assignedToStandard;
	}

	public void setAssignedTOStandard(List<Integer> assignedTOStandard) {
		this.assignedToStandard = assignedToStandard;
	}

	public int getMentorOf() {
		return mentorOf;
	}

	public void setMentorOf(int mentorOf) {
		this.mentorOf = mentorOf;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	@Override
	public String toString() {
		return "User{" + "userId=" + userId + ", name='" + name + '\'' + ", email='" + email + '\'' + ", password='"
				+ password + '\'' + '}';
	}
}
