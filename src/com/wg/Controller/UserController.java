package com.wg.Controller;

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
		// TODO Auto-generated constructor stub
	}

	public boolean authenticateUser(String username, String password, String role) {
		if (username == null || password == null || role == null) {
			System.out.println("Null values entered");
			return false;
		} else if (!role.equals("Admin") && !role.equals("Admin") && !role.equals("Admin")) {
			System.out.println("Invalid Role entered");
			return false;
		}
		return userService.authenticateUser(username, password, role);
	}

	public boolean addUser(String username, String name, int age, String password, String email, String input) {
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

		User user = new User(userId, username, name, age, password, email, role);
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

//	public void updateUser(String userId, String columnToUpdate, String columnUpdated) {
//		try {
//			User user1 = userService.getUserById(userId);
//			if (user1 != null) {
//				User user = null;
//				user.setName(columnUpdated);
//				userService.updateUser(user, columnToUpdate);
//				System.out.println("User updated successfully.");
//			} else {
//				System.out.println("User not found.");
//			}
//		} catch (Exception e) {
//			System.out.println("Error updating user: " + e.getMessage());
//		}
//	}

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
						scanner.nextLine(); // Consume newline character
						break;
					case 6:
						columnToUpdate = "gender";
						System.out.print("Enter new gender (M/F): ");
						user.setGender(scanner.nextLine().toUpperCase());
						break;
					case 7:
						columnToUpdate = "phoneNo";
						System.out.print("Enter new phone number: ");
						user.setContactNumber(scanner.nextLine());
						break;
					case 8:
						columnToUpdate = "address";
						System.out.print("Enter new address: ");
						user.setAddress(scanner.nextLine());
						break;
					case 9:
						columnToUpdate = "role";
						System.out.print("Enter new role (CUSTOMER, BRANCH_MANAGER, ADMIN): ");
						// user.setRole(User.Role.valueOf(scanner.nextLine().toUpperCase()));
						break;
					case 10:
						continueUpdating = false;
						break;
					default:
						System.out.println("Invalid option. Please try again.");
					}
					if (!continueUpdating) {
						break;
					}
					// user.setUpdatedAt(new Date());
					userService.updateUser(user, columnToUpdate);
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
		System.out.println("10. Done");
	}
}
