package com.wg.Controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;

import com.wg.Model.Role;
import com.wg.Model.User;
import com.wg.Services.UserService;

public class UserController {

	private UserService userService;
	Scanner scanner = new Scanner(System.in);

	public UserController(UserService userService) {
		this.userService = userService;
	}

	public UserController() {
	}

	public boolean authenticateUser(String username, String password, String role) {
		if (username == null || password == null || role == null) {
			System.out.println("Null values entered");
			return false;
		} else if (!role.equals("Admin") && !role.equals("Student") && !role.equals("Faculty")) {
			System.out.println("Invalid Role entered");
			return false;
		}
		return userService.authenticateUser(username, password, role);
	}

//username, name, age, password, email, input, date, contactNumber,standard, gender, rollNo, mentorOf
	public boolean addUser(String username, String name, int age, String password, String email, String input,
			LocalDate date, String contactNumber, int standard, String gender, String rollNo, int mentorOf) {
		Role role = null;
		try {
			role = Role.valueOf(input);
		} catch (IllegalArgumentException e) {
			System.out.println("Invalid role. Please enter a valid role.");
		}

		String randomString = UUID.randomUUID().toString();
		int desiredLength = 8;
		if (desiredLength > randomString.length()) {
			desiredLength = randomString.length();
		}
		String userId = randomString.substring(0, desiredLength);

		if (userId == null || username == null || age < 0) {
			System.out.println("Enter valid credentials");
			return false;
		}

		User user = new User(userId, username, name, age, password, email, role, date, contactNumber, standard, gender,
				rollNo, mentorOf);
		userService.addUser(user);
		return true;
	}

	public User getUserById(String userId) {
		User user = userService.getUserById(userId);
		return user;
	}

	public List<User> getClassDetails(int standard) {
		List<User> users = userService.getClassDetails(standard);
		return users;
	}

	public boolean deleteUser(String userId) {
		boolean flag = false;
		flag = userService.deleteUser(userId);
		return flag;
	}

	public List<User> getAllUser() {
		List<User> list = userService.getAllUser();
		return list;
	}

	public User getUserByUsername(String username) {
		User user = userService.getUserByUsername(username);
		return user;
	}

//	private String userId;
//	private String name;
//	private LocalDate dob;
//	private String contactNumber;
//	private Role role;
//	private String password;
//	private int standard;
//	private String address;
//	private String username;
//	private int age;
//	private String email;
//	private String gender;
//	private String rollNo;
//	private List<Integer> assignedToStandard;
//	private int mentorOf;
	public void updateUser() {
		try {
			System.out.print("Enter user ID: ");
			String userId = scanner.nextLine();

			User user = userService.getUserById(userId);
			if (user != null) {
				boolean continueUpdating = true;

				while (continueUpdating) {
					displayUpdateMenu();

					System.out.print("Select an option (1-10): ");
					int choice = scanner.nextInt();
					scanner.nextLine();

					String columnToUpdate = "";

					switch (choice) {
					case 1:
						columnToUpdate = "name";
						System.out.print("Enter new name: ");
						user.setName(scanner.nextLine());
						break;
					case 2:
						columnToUpdate = "email";
						System.out.print("Enter new email: ");
						user.setEmail(scanner.nextLine());
						break;
					case 3:
						columnToUpdate = "username";
						System.out.print("Enter new username: ");
						user.setUsername(scanner.nextLine());
						break;
					case 4:
						columnToUpdate = "password";
						System.out.print("Enter new password: ");
						user.setPassword(scanner.nextLine());
						break;
					case 5:
						columnToUpdate = "age";
						System.out.print("Enter new age: ");
						user.setAge(scanner.nextInt());
						scanner.nextLine();
						break;
					case 6:
						columnToUpdate = "gender";
						System.out.print("Enter new gender (M/F): ");
						user.setGender(scanner.nextLine().toUpperCase());
						break;
					case 7:
						columnToUpdate = "contactNumber";
						System.out.print("Enter new contact number: ");
						user.setContactNumber(scanner.nextLine());
						break;
					case 8:
						columnToUpdate = "address";
						System.out.print("Enter new address: ");
						user.setAddress(scanner.nextLine());
						break;
					case 9:
						columnToUpdate = "role";
						System.out.print("Enter new role (Admin, Student, Faculty): ");
						String roleInput = scanner.nextLine().trim(); // Read and trim the input
						try {
							Role newRole = Role.valueOf(roleInput); // Convert input to Role enum
							user.setRole(newRole); // Update the role in User object
							System.out.println("Role updated successfully.");
						} catch (IllegalArgumentException e) {
							System.out.println(
									"Invalid role. Please enter one of the following: Admin, Student, Faculty.");
						}
						break;
					case 10:
						columnToUpdate = "dob";
						System.out.print("Enter new Date of Birth (yyyy-mm-dd): ");
						String dobInput = scanner.nextLine(); // Read user input
						LocalDate dob = null;
						while (dob == null) {
							try {
								dob = LocalDate.parse(dobInput, DateTimeFormatter.ISO_LOCAL_DATE); // Parse the input
							} catch (DateTimeParseException e) {
								System.out.println("Invalid date format. Please enter the date in yyyy-mm-dd format:");
								dobInput = scanner.nextLine(); // Read user input again if format is invalid
							}
						}
						user.setDOB(dob);
						break;
					case 11:
						columnToUpdate = "rollNo";
						System.out.print("Enter new rollNo: ");
						user.setRollNo(scanner.nextLine());
						break;
					case 12:
						columnToUpdate = "mentorOf";
						System.out.print("Enter new mentor of standard: ");
						user.setMentorOf(scanner.nextInt());
						break;
					case 13:
						columnToUpdate = "standard";
						System.out.print("Enter new standard: ");
						user.setStandard(scanner.nextInt());
						break;
					case 14:
						continueUpdating = false;
						break;
					default:
						System.out.println("Invalid option. Please try again.");
					}
					if (!continueUpdating) {
						break;
					}
					userService.updateUser(user, userId, columnToUpdate);
					System.out.println("User updated successfully.");
				}
			} else {
				System.out.println("User not found.");
			}
		} catch (Exception e) {
			System.out.println("Error updating user: " + e.getMessage());
		}
	}

	private void displayUpdateMenu() {
		System.out.println("\nWhich field would you like to update?");
		System.out.println("1. Name");
		System.out.println("2. Email");
		System.out.println("3. Username");
		System.out.println("4. Password");
		System.out.println("5. Age");
		System.out.println("6. Gender");
		System.out.println("7. Contact Number");
		System.out.println("8. Address");
		System.out.println("9. Role");
		System.out.println("10. Date of Birth");
		System.out.println("11. Roll Number");
		System.out.println("12. Mentor of Field");
		System.out.println("13. Standard");
		System.out.println("14. Done");
	}
}
