package com.wg.UI;

import java.util.List;
import java.util.Scanner;

import com.wg.Controller.UserController;
import com.wg.Model.User;

public class UserUI {
	private UserController userController;
	Scanner scanner = new Scanner(System.in);
	StartMenu startMenu = new StartMenu();
	// private UserService userService;

//	public UserUI(UserController controller, UserService userService) {
//		this.userController = controller;
//		this.userService = userService;
//	}

//	public UserUI() {
//	}
//
	public UserUI(UserController controller) {
		this.userController = controller;
	}
//
//	public UserUI(UserService userService) {
//		this.userService = userService;
//	}

	public void displayMenu(String role) {
		if (role.equals("Admin")) {
			while (true) {
				System.out.println("1. Add User");
				System.out.println("2. Get User by Id");
				System.out.println("3. Get User by Username");
				System.out.println("4. View Class Details");
				System.out.println("5. Delete User");
				System.out.println("6. Get All Users");
				System.out.println("7. Update User");
				System.out.println("8. Send Notification");
				System.out.println("9. Manage Leaves");
				System.out.println("10. Manage Course");
				System.out.println("11. Manage Marksheets");
				System.out.println("12. Logout");
				System.out.println("13. Exit");
				System.out.println("Enter your choice: ");

				int choice = scanner.nextInt();

				switch (choice) {
				case 1:
					addStudent();
					break;
				case 2:
					getUserById();
					break;
				case 3:
					getUserByUsername();
					break;
				case 4:
					getClassDetails();
					break;
				case 5:
					deleteUser();
					break;
				case 6:
					getAllUser();
					break;
				case 7:
					// updateUser();
					userController.updateUser();
					break;
				case 8:
					// sendNotification();
					break;
				case 9:
					// manageLeaves();
					break;
				case 10:
					// manageCourse();
					break;
				case 11:
					// manageMarksheet();
					break;
				case 12:
					logout();
					break;
				case 13:
					System.out.println("Exiting...");
					return;
				default:
					System.out.println("Invalid choice. Please try again.");
				}
			}
		} else if (role.equals("Student")) {
			while (true) {
				System.out.println("1. Apply Leave");
				System.out.println("2. View Marksheet");
				System.out.println("3. Raise Issue");
				System.out.println("4. Pay fees");
				System.out.println("5. Read Notifications");
				System.out.println("6. Exit");
				System.out.println("Enter your choice: ");

				int choice = scanner.nextInt();
				scanner.nextLine();

				switch (choice) {
				case 1:
					// applyLeave();
					break;
				case 2:
					// viewMarksheet();
					break;
				case 3:
					// raiseIssue();
					break;
				case 4:
					// payFees();
					break;
				case 5:
					// readNotifications();
					break;
				case 6:
					System.out.println("Exiting...");
					return;
				default:
					System.out.println("Invalid choice. Please try again.");
				}
			}
		} else if (role.equals("Faculty")) {
			while (true) {
				System.out.println("1. Add Attendance");
				System.out.println("2. Add Marks");
				System.out.println("3. Apply Leave");
				System.out.println("4. Approve Leave");
				System.out.println("5. Generate Marksheet");
				System.out.println("6. Exit");
				System.out.println("Enter your choice: ");

				int choice = scanner.nextInt();
				scanner.nextLine();

				switch (choice) {
				case 1:
					// addAttendance();
					break;
				case 2:
					// addMarks();
					break;
				case 3:
					// applyLeave();
					break;
				case 4:
					// applyLeave();
					break;
				case 5:
					// generateMarksheet();
					break;
				case 6:
					System.out.println("Exiting...");
					System.exit(0);
					return;
				default:
					System.out.println("Invalid choice. Please try again.");
				}
			}
		} else {
			System.out.println("Enter valid role!");
		}

	}

	private void addStudent() {
		System.out.print("Enter Username: ");
		String username = scanner.next();
		System.out.print("Enter name: ");
		String name = scanner.next();
		System.out.print("Enter age: ");
		int age = scanner.nextInt();
		System.out.print("Enter password: ");
		String password = scanner.next();
		System.out.print("Enter email: ");
		String email = scanner.next();
		System.out.print("Enter role: ");
		String input = scanner.next().trim();

		boolean flag = userController.addUser(username, name, age, password, email, input);
		if (flag == true) {
			System.out.println("User added successfully.");
		} else {
			System.out.println("User not added.");
		}
	}

	private void getUserById() {
		System.out.print("Enter user ID: ");
		String userId = scanner.next();
		User user = userController.getUserById(userId);
		if (user != null) {
			System.out.println(user);
		} else {
			System.out.println("User not found.");
		}
	}

	private void getUserByUsername() {
		System.out.print("Enter user Username: ");
		String username = scanner.next();
		User user = userController.getUserByUsername(username);
		if (user != null) {
			System.out.println(user);
		} else {
			System.out.println("User not found.");
		}
	}

	private void getClassDetails() {
		System.out.println("Enter the standard");
		int standard = scanner.nextInt();
		scanner.nextLine(); // Consume newline

		List<User> users = userController.getClassDetails(standard);
		if (users == null) {
			System.out.println("Users not found!");
		} else {
			for (User user : users) {
				System.out.println(user);
			}
		}
	}

	private void deleteUser() {
		System.out.println("Enter the User Id");
		String UserId = scanner.next();

		boolean flag = userController.deleteUser(UserId);
		if (flag == true) {
			System.out.println("User Deleted Successfully!");
		} else {
			System.out.println("User deletion Unsuccessful!");
		}
	}

	private void getAllUser() {
		List<User> list = userController.getAllUser();
		for (User user : list) {
			System.out.println(user);
		}
	}

	private void logout() {
		startMenu.showStartMenu();
	}

//	private void updateUser() {
//		// User user = null;
//		System.out.print("Enter user ID: ");
//		String userId = scanner.nextLine();
//		boolean continueUpdating = true;
////		if (userService == null) {
////			System.out.println("Error: UserService is not initialized.");
////			return; // Exit early if userService is null
////		}
//
////		try {
////			user = userService.getUserById(userId);
////			if (user != null) {
////				// userService.updateUser(user, columnToUpdate);
////				System.out.println("User updated successfully.");
////			} else {
////				System.out.println("User is null bcuz of the userservvice.");
////			}
////		} catch (Exception e) {
////			System.out.println("Error updating user: " + e.getMessage());
////		}
//
//		while (continueUpdating) {
//			displayUpdateMenu();
//			System.out.print("Select an option (1-10): ");
//			int choice = scanner.nextInt();
//			// scanner.nextLine();
//
//			String columnToUpdate = "";
//			String columnUpdated = "";
//
//			switch (choice) {
//			case 1:
//				columnToUpdate = "name";
//				System.out.print("Enter new name: ");
//				columnUpdated = scanner.nextLine();
//				// user.setName(scanner.nextLine());
//				break;
//			case 2:
//				columnToUpdate = "email";
//				System.out.print("Enter new email: ");
//				// user.setEmail(scanner.nextLine());
//				break;
//			case 3:
//				columnToUpdate = "username";
//				System.out.print("Enter new username: ");
//				// user.setUsername(scanner.nextLine());
//				break;
//			case 4:
//				columnToUpdate = "password";
//				System.out.print("Enter new password: ");
//				// user.setPassword(scanner.nextLine());
//				break;
//			case 5:
//				columnToUpdate = "age";
//				System.out.print("Enter new age: ");
//				// user.setAge(scanner.nextInt());
//				scanner.nextLine(); // Consume newline character
//				break;
//			case 6:
//				columnToUpdate = "gender";
//				System.out.print("Enter new gender (M/F): ");
//				// user.setGender(scanner.nextLine().toUpperCase());
//				break;
//			case 7:
//				columnToUpdate = "contactNo";
//				System.out.print("Enter new phone number: ");
//				// user.setContactNumber(scanner.nextLine());
//				break;
//			case 8:
//				columnToUpdate = "address";
//				System.out.print("Enter new address: ");
//				// user.setAddress(scanner.nextLine());
//				break;
//			case 9:
//				columnToUpdate = "role";
//				System.out.print("Enter new role (ADMIN, STUDENT, FACULTY): ");
//				// user.setRole(Role.valueOf(scanner.nextLine().toUpperCase()));
//				break;
//			case 10:
//				continueUpdating = false;
//				break;
//			default:
//				System.out.println("Invalid option. Please try again.");
//			}
//			if (!continueUpdating) {
//				break;
//			}
//			userController.updateUser(userId, columnToUpdate, columnUpdated);
//			System.out.println("User updated successfully.");
//		}
//	}
//
//	private void displayUpdateMenu() {
//		System.out.println("\nWhich field would you like to update?");
//		System.out.println("1. Name");
//		System.out.println("2. Email");
//		System.out.println("3. Username");
//		System.out.println("4. Password");
//		System.out.println("5. Age");
//		System.out.println("6. Gender");
//		System.out.println("7. Contact Number");
//		System.out.println("8. Address");
//		System.out.println("9. Role");
//		System.out.println("10. Done");
	// }
}

//	private void payFees() {
//
//	}
//
//  private void readNotifications() {
//	
//
//  }
//
//	private void raiseIssue() {
//
//	}
//
//	private void viewMarksheet() {
//
//	}
//
//	private void applyLeave() {
//
//	}
//
//	private void generateMarksheet() {
//
//	}
//
//	private void addMarks() {
//
//	}
//
//	private void addAttendance() {
//
//	}
//}