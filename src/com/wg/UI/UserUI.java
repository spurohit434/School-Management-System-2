package com.wg.UI;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;

import com.App.App;
import com.wg.Controller.AttendanceController;
import com.wg.Controller.CourseController;
import com.wg.Controller.FeeController;
import com.wg.Controller.LeavesController;
import com.wg.Controller.NotificationController;
import com.wg.Controller.UserController;
import com.wg.Model.Attendance;
import com.wg.Model.Course;
import com.wg.Model.Leaves;
import com.wg.Model.LeavesStatus;
import com.wg.Model.Notification;
import com.wg.Model.Status;
import com.wg.Model.User;

public class UserUI {
	private UserController userController;
	private FeeController feeController;
	private CourseController courseController;
	private AttendanceController attendanceController;
	private NotificationController notificationController;
	private LeavesController leavesController;
	Scanner scanner = new Scanner(System.in);
	StartMenu startMenu = new StartMenu();

	public UserUI(UserController controller, FeeController feeController, CourseController courseController,
			AttendanceController attendanceController, NotificationController notificationController,
			LeavesController leavesController) {
		this.userController = controller;
		this.feeController = feeController;
		this.courseController = courseController;
		this.attendanceController = attendanceController;
		this.notificationController = notificationController;
		this.leavesController = leavesController;
	}

	public void displayMenu(String role, User user) {
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
				System.out.println("9. Delete Notification");
				System.out.println("10. Add Fees");
				System.out.println("11. Calculate Fine");
				System.out.println("12. View All Leave Requests");
				System.out.println("13. Approve Leave");
				System.out.println("14. Manage Course");
				System.out.println("15. Manage Marksheets");
				System.out.println("16. Manage Attendance");
				System.out.println("17. Logout");
				System.out.println("18. Exit");
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
					updateUser();
					break;
				case 8:
					sendNotification();
					break;
				case 9:
					deleteNotification();
					break;
				case 10:
					addFees();
					break;
				case 11:
					calculateFine();
					break;
				case 12:
					viewAllLeave();
					break;
				case 13:
					approveLeave(role);
					break;
				case 14:
					manageCourse();
					break;
				case 15:
					// manageMarksheet();
					break;
				case 16:
					manageAttendance();
					break;
				case 17:
					logout();
					break;
				case 18:
					System.out.println("Exiting...");
					System.exit(0);
					return;
				default:
					System.out.println("Invalid choice. Please try again.");
				}
			}
		} else if (role.equals("Student")) {
			while (true) {
				System.out.println("1. Apply Leave");
				System.out.println("2. Check Leave Status");
				System.out.println("3. View Marksheet");
				System.out.println("4. Check fees");
				System.out.println("5. Pay fees");
				System.out.println("6. Read Notifications");
				System.out.println("7. Logout");
				System.out.println("8. Exit");
				System.out.println("Enter your choice: ");

				int choice = scanner.nextInt();
				scanner.nextLine();

				switch (choice) {
				case 1:
					applyLeave(user);
					break;
				case 2:
					checkLeaveStatus(user);
					break;
				case 3:
					// viewMarksheet();
					break;
				case 4:
					checkFees(user);
					break;
				case 5:
					payFees(user);
					break;
				case 6:
					readNotifications(user);
					break;
				case 7:
					logout();
					break;
				case 8:
					System.out.println("Exiting...");
					System.exit(0);
					return;
				default:
					System.out.println("Invalid choice. Please try again.");
				}
			}
		} else if (role.equals("Faculty")) {
			while (true) {
				System.out.println("1. Manage Attendance");
				System.out.println("2. View All Leave requests");
				System.out.println("3. Apply Leave");
				System.out.println("4. Approve Leave");
				System.out.println("5. Check Leave Status");
				System.out.println("6. Generate Marksheet");
				System.out.println("7. Read Notifications");
				System.out.println("8. Logout");
				System.out.println("9. Exit");
				System.out.println("Enter your choice: ");

				int choice = scanner.nextInt();
				scanner.nextLine();

				switch (choice) {
				case 1:
					manageAttendance();
					break;
//				case 2:
//					// addMarks();
//					break;
				case 2:
					viewAllLeave();
					break;
				case 3:
					applyLeave(user);
					break;
				case 4:
					approveLeave(role);
					break;
				case 5:
					checkLeaveStatus(user);
					break;
				case 6:
					// generateMarksheet();
					break;
				case 7:
					readNotifications(user);
					break;
				case 8:
					logout();
					break;
				case 9:
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
		int age = 0;
		boolean validInput = false;
		while (!validInput) {
			System.out.print("Enter age: ");
			try {
				age = scanner.nextInt();
				validInput = true; // If input is valid, exit loop
			} catch (InputMismatchException e) {
				System.out.println("Invalid input. Please enter a valid integer for age.");
				scanner.next(); // Clear invalid input
			}
		}
		System.out.print("Enter password: ");
		String password = scanner.next();
		System.out.print("Enter email: ");
		String email = scanner.next();
		System.out.print("Enter role: ");
		String input = scanner.next().trim();
		LocalDate date = null;
		while (date == null) {
			System.out.println("Enter the date of birth (yyyy-mm-dd):");
			try {
				String input1 = scanner.next().trim();
				date = LocalDate.parse(input1);
			} catch (DateTimeParseException e) {
				System.out.println("Invalid date format. Please enter the date in yyyy-mm-dd format:");
			}
		}
		System.out.println("Enter contact number:");
		String contactNumber = scanner.next();
		int standard = 0;
		while (true) {
			System.out.println("Enter the standard (student only):");
			try {
				standard = scanner.nextInt();
				break; // If input is valid, exit loop
			} catch (InputMismatchException e) {
				System.out.println("Invalid input. Please enter a valid integer for standard.");
				scanner.next(); // Clear invalid input
			}
		}
		System.out.println("Enter the gender:");
		String gender = scanner.next();
		System.out.println("Enter the roll number (student only):");
		String rollNo = scanner.next();
		int mentorOf = 0;
		while (true) {
			System.out.println("Enter the mentor of field (faculty only):");
			try {
				mentorOf = scanner.nextInt();
				break; // If input is valid, exit loop
			} catch (InputMismatchException e) {
				System.out.println("Invalid input. Please enter a valid integer for mentorOf.");
				scanner.next(); // Clear invalid input
			}
		}
		boolean flag = userController.addUser(username, name, age, password, email, input, date, contactNumber,
				standard, gender, rollNo, mentorOf);
		if (flag) {
			System.out.println("User added successfully.");
		} else {
			System.out.println("User not added.");
		}
	}

	private void getUserById() {
		System.out.print("Enter user ID: ");
		String userId = scanner.next();
		User user = userController.getUserById(userId);
//		if (user != null) {
//			System.out.println(user);
//		} else {
//			System.out.println("User not found.");
//		}
//		
//		
//		User user = new User("u1", "John Doe", "1990-01-01", "1234567890", Role.STUDENT, "password", Standard.TENTH, "123 Main St", "johndoe", 25, "johndoe@example.com", "Male", "R1", true, "Mentor1");

		System.out.println(
				"+---------------+----------+------------+---------------+--------+----------+---------+---------+------+---------+---------+---------------+---------------+");
		System.out.println(
				"|   User ID    |  Name   | Date of Birth | Contact Number |  Role  | Password | Standard | Address | Username | Age | Email | Gender | Roll No | Assigned To | Mentor Of |");
		System.out.println(
				"+---------------+----------+------------+---------------+--------+----------+---------+---------+------+---------+---------+---------------+---------------+");
		System.out.println(user.toString().replace(",", " |").replace("User{", "").replace("}", ""));
		System.out.println(
				"+---------------+----------+------------+---------------+--------+----------+---------+---------+------+---------+---------+---------------+---------------+");
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
		try {
			App.main(null);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void updateUser() {
		userController.updateUser();
	}

	private void payFees(User user) {
		String userId = user.getUserId();
		feeController.payFees(userId);
	}

	private void checkFees(User user) {
		String userId = user.getUserId();
		feeController.checkFees(userId);
	}

	private void addFees() {
		System.out.println("Enter the UserId:");
		String userId = scanner.next();
		System.out.println("Enter the Fee Amount:");
		double feeAmount = scanner.nextDouble();
		System.out.println("Enter the deadline (yyyy-mm-dd):");
		LocalDate date = null;
		while (date == null) {
			try {
				String input = scanner.next().trim();
				date = LocalDate.parse(input);
			} catch (DateTimeParseException e) {
				System.out.println("Invalid date format. Please enter the date in yyyy-mm-dd format:");
			}
		}
		System.out.println("Enter the fine:");
		double fine = scanner.nextDouble();

		feeController.addFees(userId, feeAmount, date, fine);
	}

	private void calculateFine() {
		System.out.println("Enter the UserId:");
		String userId = scanner.next();
		feeController.calculateFine(userId);
	}

	private void manageCourse() {
		while (true) {
			System.out.println("Enter the Choice:");
			System.out.println("1. Add Course");
			System.out.println("2. Get Course");
			System.out.println("3. Delete Course");
			System.out.println("4. Update Course");
			System.out.println("5. Get All Courses");
			System.out.println("6. Logout and go back to Main Menu");

			int choice = scanner.nextInt();
			scanner.nextLine();

			switch (choice) {
			case 1:
				addCourse();
				break;
			case 2:
				getCourse();
				break;
			case 3:
				deleteCourse();
				break;
			case 4:
				updateCourse();
				break;
			case 5:
				getAllCourses();
				break;
			case 6:
				startMenu.showStartMenu();
				break;
			default:
				System.out.println("Enter valid choice!");
			}
		}
	}

	private void getAllCourses() {
		List<Course> list = courseController.getAllCourses();
		for (Course course : list) {
			System.out.println(course);
		}
	}

	private void updateCourse() {
		System.out.println("Enter Course Id");
		String courseId = scanner.next();
		courseController.updateCourse(courseId);
	}

	private void deleteCourse() {
		System.out.println("Enter the Course Id");
		String courseId = scanner.next();
		boolean flag = courseController.deleteCourse(courseId);
		if (flag == true) {
			System.out.println("Course Deleted Successfully!");
		} else {
			System.out.println("Course deletion Unsuccessful!");
		}
	}

	private void addCourse() {
		System.out.println("Enter Course Id");
		String courseId = scanner.next();
		System.out.println("Enter Course Name");
		String courseName = scanner.next();
		System.out.println("Enter standard");
		int standard = scanner.nextInt();
		boolean flag = courseController.addCourse(courseId, courseName, standard);
		if (flag == true) {
			System.out.println("Course added successfully");
		} else {
			System.out.println("Course not added.");
		}
	}

	private void getCourse() {
		System.out.println("Enter Course Id");
		String courseId = scanner.next();
		Course course = courseController.getCourse(courseId);
		System.out.println(course);
	}

	private void manageAttendance() {
		while (true) {
			System.out.println("Enter the Choice:");
			System.out.println("1. Add Attendance");
			System.out.println("2. View Attendance by Standard");
			System.out.println("3. View Attendance by Student Id");
			System.out.println("4. Logout and go back to Main Menu");

			int choice = scanner.nextInt();
			scanner.nextLine();

			switch (choice) {
			case 1:
				addAttendance();
				break;
			case 2:
				viewAttendanceByStandard();
				break;
			case 3:
				viewAttendanceById();
				break;
			case 4:
				logout();
				break;
			default:
				System.out.println("Enter valid choice!");
			}
		}
	}

	private void addAttendance() {
		System.out.println("Enter Student Id:");
		String studentId = scanner.nextLine();
		System.out.println("Enter standard:");
		int standard = scanner.nextInt();
		System.out.println("Enter the date (YYYY-MM-DD):");
		String dateString = scanner.next();
		LocalDate date = LocalDate.parse(dateString);
		System.out.println("Enter status (P for Present, A for Absent):");
		String statusInput = scanner.next();
		Status status = Status.valueOf(statusInput);

		boolean flag = attendanceController.addAttendance(studentId, standard, date, status);
		if (flag) {
			System.out.println("Attendance added successfully");
		} else {
			System.out.println("Attendance not added.");
		}
	}

	private void viewAttendanceByStandard() {
		System.out.println("Enter the standard:");
		int standard = scanner.nextInt();

		List<Attendance> list = attendanceController.viewAttendanceByStandard(standard);
		for (Attendance ls : list) {
			System.out.println(ls);
		}
	}

	private void viewAttendanceById() {
		System.out.println("Enter the Student Id:");
		String studentId = scanner.nextLine();

		List<Attendance> list = attendanceController.viewAttendanceById(studentId);
		for (Attendance ls : list) {
			System.out.println(ls);
		}
	}

	private void approveLeave(String role) {
		String userId = "";
		if (role.equals("Admin")) {
			System.out.println("Enter the User Id to Approve Leave");
			userId = scanner.next();
		} else {
			System.out.println("Enter the Student User Id");
			userId = scanner.next();
		}
		leavesController.approveLeave(userId);
	}

	private void applyLeave(User user) {
		String userId = user.getUserId();
		System.out.println("Enter Leave Content:");
		String content = scanner.nextLine();
		String input = "Pending";
		LeavesStatus status = LeavesStatus.valueOf(input);
		String randomString = UUID.randomUUID().toString();
		int desiredLength = 7;
		if (desiredLength > randomString.length()) {
			desiredLength = randomString.length();
		}
		String leaveId = randomString.substring(0, desiredLength);
		leaveId = 'N' + leaveId;
		LocalDate startDate = LocalDate.now();
		System.out.println("Enter Leave till date (YYYY-MM-DD):");
		String dateString = scanner.next();
		LocalDate endDate = LocalDate.parse(dateString);
		Leaves leave = new Leaves(leaveId, userId, content, startDate, endDate, status);
//		String leaveID = leavesController.applyLeave(leave);
//		System.out.println("Your leave Id is: " + leaveID);
		leavesController.applyLeave(leave);
	}

	private void viewAllLeave() {
		List<Leaves> leaves = leavesController.viewAllLeave();
		for (Leaves leave : leaves) {
			System.out.println(leave);
		}
	}

	private void checkLeaveStatus(User user) {
		String userId = user.getUserId();
		LeavesStatus leaves = leavesController.checkLeaveStatus(userId);
		System.out.println("Your leaves Status :" + leaves);
	}

	private void readNotifications(User user) {
		String userId = user.getUserId();
		notificationController.readNotifications(userId);
	}

	private void deleteNotification() {

	}

	private void sendNotification() {
		try {
			System.out.println("Enter user id to send notification: ");
			String userId = scanner.next();
			System.out.println("Enter title: ");
			String type = scanner.next();
			System.out.println("Enter message: ");
			String description = scanner.next();
			String randomString = UUID.randomUUID().toString();
			int desiredLength = 7;
			if (desiredLength > randomString.length()) {
				desiredLength = randomString.length();
			}
			String notificationId = randomString.substring(0, desiredLength);
			LocalDate todayDate = LocalDate.now();
			notificationId = 'N' + notificationId;
			Notification notification = new Notification(notificationId, userId, type, description, todayDate);
			boolean sendStatus = notificationController.sendNotification(notification);
			if (sendStatus) {
				System.out.println("Notification sent successfully");
			} else {
				System.out.println("Notification not sent");
			}
		} catch (Exception e) {
			System.out.println("An error occurred: " + e.getMessage());
			e.printStackTrace(); // For debugging, logs stack trace
		}
	}
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
}