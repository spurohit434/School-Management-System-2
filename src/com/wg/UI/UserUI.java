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
import com.wg.Controller.CourseMarksController;
import com.wg.Controller.FeeController;
import com.wg.Controller.IssueController;
import com.wg.Controller.LeavesController;
import com.wg.Controller.NotificationController;
import com.wg.Controller.UserController;
import com.wg.Helper.PasswordUtil;
import com.wg.Helper.PasswordValidator;
import com.wg.Helper.Validator;
import com.wg.Model.Attendance;
import com.wg.Model.Course;
import com.wg.Model.Issue;
import com.wg.Model.IssuesStatus;
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
	private IssueController issueController;
	private CourseMarksController courseMarksController;
	Scanner scanner = new Scanner(System.in);
	StartMenu startMenu = new StartMenu();

	public UserUI(UserController controller, FeeController feeController, CourseController courseController,
			AttendanceController attendanceController, NotificationController notificationController,
			LeavesController leavesController, IssueController issueController,
			CourseMarksController courseMarksController) {
		this.userController = controller;
		this.feeController = feeController;
		this.courseController = courseController;
		this.attendanceController = attendanceController;
		this.notificationController = notificationController;
		this.leavesController = leavesController;
		this.issueController = issueController;
		this.courseMarksController = courseMarksController;
	}

	public void displayMenu(User user) {
		String role = user.getRole().toString();
		if (role.equals("Admin")) {
			while (true) {
				System.out.println("----- ADMIN MENU -----");
				System.out.println("1. Add User");
				System.out.println("2. Get User by Id");
				System.out.println("3. Get User by Username");
				System.out.println("4. View Class Details");
				System.out.println("5. Delete User");
				System.out.println("6. Get All Users");
				System.out.println("7. Update User");
				System.out.println("8. Send Notification");
				System.out.println("9. Add Fees");
				System.out.println("10. Calculate Fine");
				System.out.println("11. View All Leave Requests");
				System.out.println("12. Approve Leave");
				System.out.println("13. View All Issues");
				System.out.println("14. Resolve Issue");
				System.out.println("15. Manage Course");
				System.out.println("16. Manage Attendance");
				System.out.println("17. Manage Marksheets");
				System.out.println("18. Logout");
				System.out.println("19. Exit");
				System.out.println("Enter your choice: ");

				int choice = scanner.nextInt();

				switch (choice) {
				case 1:
					addUser();
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
					addFees();
					break;
				case 10:
					calculateFine();
					break;
				case 11:
					viewAllLeave();
					break;
				case 12:
					approveLeave(role);
					break;
				case 13:
					viewAllIssues();
					break;
				case 14:
					resolveIssue();
					break;
				case 15:
					manageCourse();
					break;
				case 16:
					manageAttendance();
					break;
				case 17:
					// manageMarksheet();
					break;
				case 18:
					logout();
					break;
				case 19:
					System.out.println("Exiting...");
					System.exit(0);
					return;
				default:
					System.out.println("Invalid choice. Please try again.");
				}
			}
		} else if (role.equals("Student")) {
			while (true) {
				System.out.println("----- STUDENT MENU -----");
				System.out.println("1. Apply Leave");
				System.out.println("2. Check Leave Status");
				System.out.println("3. Raise Issue");
				System.out.println("4. Check Issue Status");
				System.out.println("5. View Marksheet");
				System.out.println("6. Check fees");
				System.out.println("7. Pay fees");
				System.out.println("8. Check Marks");
				System.out.println("9. Read Notifications");
				System.out.println("10. Logout");
				System.out.println("11. Exit");
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
					raiseIssue(user);
					break;
				case 4:
					checkIssueStatus(user);
					break;
				case 5:
					System.out.println("Functionality Not added yet [ Under Construction ]");
					// viewMarksheet();
					break;
				case 6:
					checkFees(user);
					break;
				case 7:
					payFees(user);
					break;
				case 8:
					System.out.println("Functionality Not added yet [ Under Construction ]");
					// checkMarks(user);
					break;
				case 9:
					readNotifications(user);
					break;
				case 10:
					logout();
					break;
				case 11:
					System.out.println("Exiting...");
					System.exit(0);
					return;
				default:
					System.out.println("Invalid choice. Please try again.");
				}
			}
		} else if (role.equals("Faculty")) {
			while (true) {
				System.out.println("----- FACULTY MENU -----");
				System.out.println("1. Manage Attendance");
				System.out.println("2. View All Leave requests");
				System.out.println("3. Apply Leave");
				System.out.println("4. Approve Leave");
				System.out.println("5. Check Leave Status");
				System.out.println("6. Raise Issue");
				System.out.println("7. Check Issue Status");
				System.out.println("8. Add Marks");
				System.out.println("9. Generate Marksheet");
				System.out.println("10. Read Notifications");
				System.out.println("11. Logout");
				System.out.println("12. Exit");
				System.out.println("Enter your choice: ");

				int choice = scanner.nextInt();
				scanner.nextLine();

				switch (choice) {
				case 1:
					manageAttendance();
					break;
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
					raiseIssue(user);
					break;
				case 7:
					checkIssueStatus(user);
					break;
				case 8:
					// System.out.println("Functionality Not added yet [ Under Construction ]");
					addMarks();
					break;
				case 9:
					System.out.println("Functionality Not added yet [ Under Construction ]");
					// generateMarksheet();
					break;
				case 10:
					readNotifications(user);
					break;
				case 11:
					logout();
					break;
				case 12:
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

	private void checkMarks(User user) {

	}

	private void addMarks() {
		boolean validateUser = false;
		String userId = "";
		String courseId = "";
		while (!validateUser) {
			System.out.println("Enter user Id");
			userId = scanner.next();
			User user = userController.getUserById(userId);
			if (user == null) {
				System.out.println("Enter valid userId");
				validateUser = false;
			} else {
				validateUser = true;
			}
		}
		validateUser = false;
		while (!validateUser) {
			System.out.println("Enter course Id");
			courseId = scanner.next();
			Course course = courseController.getCourse(courseId);
			if (course == null) {
				System.out.println("Enter valid courseId");
				validateUser = false;
			} else {
				validateUser = true;
			}
		}
		System.out.println("Enter marks");
		double marks = scanner.nextDouble();
		courseMarksController.addMarks(userId, courseId, marks);
	}

	private void addUser() {
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
				if (Validator.isValidAge(age)) {
					validInput = true;
				} else {
					validInput = false; // If input is valid, exit loop
				}
			} catch (InputMismatchException e) {
				System.out.println("Invalid input. Please enter a valid integer for age.");
				scanner.next(); // Clear invalid input
			}
		}

		System.out.print("Enter password: (Atleast one UpperCase character, one Special character, one Integer digit)");
		String password = scanner.next();
		PasswordValidator passwordValidator = new PasswordValidator();
		boolean isValidPassword = passwordValidator.isValidPassword(password);
		if (!isValidPassword) {
			System.out.println("Enter valid password");
			return;
		}
		String hashedPassword = PasswordUtil.hashPassword(password);

		validInput = false;
		String email = null;
		while (!validInput) {
			System.out.print("Enter email: ");
			try {
				email = scanner.next();
				if (Validator.isValid(email)) {
					validInput = true;
				} else {
					validInput = false;
				}
			} catch (InputMismatchException e) {
				System.out.println("Invalid input. Please enter a valid Email");
				scanner.next(); // Clear invalid input
			}
		}

		String input = "";
		boolean validInput1 = false;
		while (!validInput1) {
			System.out.print("Enter role: ");
			try {
				input = scanner.next().trim();
				if (input.equals("Admin")) {
					System.out.println("Role can not be Admin");
					validInput1 = false;
				} else {
					validInput1 = true;
				}
			} catch (InputMismatchException e) {
				System.out.println("Invalid input. Please enter a valid Role.");
				scanner.next();
			}
		}
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
		if (input.equals("Student")) {
			while (true) {
				System.out.println("Enter the standard:");
				try {
					standard = scanner.nextInt();
					break; // If input is valid, exit loop
				} catch (InputMismatchException e) {
					System.out.println("Invalid input. Please enter a valid integer for standard.");
					scanner.next(); // Clear invalid input
				}
			}
		}
		System.out.println("Enter the gender:");
		String gender = scanner.next();

		String rollNo = null;
		if (input.equals("Student")) {
			System.out.println("Enter the roll number:");
			rollNo = scanner.next();
		}

		int mentorOf = 0;
		if (input.equals("Faculty")) {
			while (true) {
				System.out.println("Enter the mentor of field:");
				try {
					mentorOf = scanner.nextInt();
					break; // If input is valid, exit loop
				} catch (InputMismatchException e) {
					System.out.println("Invalid input. Please enter a valid integer for mentorOf.");
					scanner.next(); // Clear invalid input
				}
			}
		}

		boolean flag = userController.addUser(username, name, age, hashedPassword, email, input, date, contactNumber,
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
		if (user != null) {
			System.out.println(
					"+---------------+----------+------------+---------------+--------+----------+---------+---------+------+---------+---------+---------------+---------------+---------------+----------+------------+---------------+--------+----------+---------+---------+------+---------+---------+");
			System.out.println(user.toString().replace(",", " |").replace("User{", "").replace("}", ""));
			System.out.println(
					"+---------------+----------+------------+---------------+--------+----------+---------+---------+------+---------+---------+---------------+---------------+---------------+----------+------------+---------------+--------+----------+---------+---------+------+---------+---------+");

		} else {
			System.out.println("User not found.");
		}
	}

	private void getUserByUsername() {
		System.out.print("Enter user Username: ");
		String username = scanner.next();
		User user = userController.getUserByUsername(username);
		if (user != null) {
			System.out.println(
					"+---------------+----------+------------+---------------+--------+----------+---------+---------+------+---------+---------+---------------+---------------+---------------+----------+------------+---------------+--------+----------+---------+---------+------+---------+---------+");
			System.out.println(user.toString().replace(",", " |").replace("User{", "").replace("}", ""));
			System.out.println(
					"+---------------+----------+------------+---------------+--------+----------+---------+---------+------+---------+---------+---------------+---------------+---------------+----------+------------+---------------+--------+----------+---------+---------+------+---------+---------+");

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
				System.out.println(
						"+---------------+----------+------------+---------------+--------+----------+---------+---------+------+---------+---------+---------------+---------------+---------------+----------+------------+---------------+--------+----------+---------+---------+------+---------+---------+");
				System.out.println(user.toString().replace(",", " |").replace("User{", "").replace("}", ""));
				System.out.println(
						"+---------------+----------+------------+---------------+--------+----------+---------+---------+------+---------+---------+---------------+---------------+---------------+----------+------------+---------------+--------+----------+---------+---------+------+---------+---------+");
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
		System.out.println(
				"+---------------+----------+------------+---------------+--------+----------+---------+---------+------+---------+---------+---------------+---------------+---------------+----------+------------+---------------+--------+----------+---------+---------+------+---------+---------+");

		for (User user : list) {
			System.out.println(user.toString().replace(",", " |").replace("User{", "").replace("}", ""));
		}
		System.out.println(
				"+---------------+----------+------------+---------------+--------+----------+---------+---------+------+---------+---------+---------------+---------------+---------------+----------+------------+---------------+--------+----------+---------+---------+------+---------+---------+");

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

	public void manageCourse() {
		while (true) {
			System.out.println("Manage Courses");
			System.out.println("1. Add Course");
			System.out.println("2. Get Course");
			System.out.println("3. Delete Course");
			System.out.println("4. Update Course");
			System.out.println("5. Get All Courses");
			System.out.println("6. Logout and go back to Main Menu");
			System.out.println("Enter the Choice:");

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
				return;
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
		courseController.updateCourse();
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
			System.out.println("Manage Attendance");
			System.out.println("1. Add Attendance");
			System.out.println("2. View Attendance by Standard");
			System.out.println("3. View Attendance by Student Id");
			System.out.println("4. Logout and go back to Main Menu");
			System.out.println("Enter the Choice:");

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
				return;
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
		leaveId = 'L' + leaveId;
		LocalDate startDate = LocalDate.now();
		System.out.println("Enter Leave till date (YYYY-MM-DD):");
		String dateString = scanner.next();
		LocalDate endDate = LocalDate.parse(dateString);
		Leaves leave = new Leaves(leaveId, userId, content, startDate, endDate, status);
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

	private void raiseIssue(User user) {
		String userId = user.getUserId();
		System.out.println("Enter Issue Content:");
		String message = scanner.nextLine();
		String input = "PENDING";
		IssuesStatus status = IssuesStatus.valueOf(input);
		String randomString = UUID.randomUUID().toString();
		int desiredLength = 7;
		if (desiredLength > randomString.length()) {
			desiredLength = randomString.length();
		}
		String issueID = randomString.substring(0, desiredLength);
		issueID = 'I' + issueID;
		Issue issue = new Issue(issueID, message, userId, status);
		issueController.raiseIssue(issue);
	}

	public void resolveIssue() {
		System.out.println("Enter the User Id to Resolve Issue");
		String userId = scanner.next();
		issueController.resolveIssue(userId);
	}

	public void checkIssueStatus(User user) {
		String userId = user.getUserId();
		List<Issue> issue = issueController.checkIssueStatus(userId);
		for (Issue issues : issue) {
			System.out.println(issues);
		}
	}

	public void viewAllIssues() {
		List<Issue> issue = issueController.viewAllIssues();
		for (Issue issues : issue) {
			System.out.println(issues);
		}
	}

//
//	private void viewMarksheet() {
//
//	}
//	private void generateMarksheet() {
//
//	}
//
//	private void addMarks() {
//
//	}

}