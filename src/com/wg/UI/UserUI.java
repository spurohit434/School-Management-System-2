package com.wg.UI;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;

import com.App.App;
import com.wg.Constants.StringConstants;
import com.wg.Controller.AttendanceController;
import com.wg.Controller.CourseController;
import com.wg.Controller.CourseMarksController;
import com.wg.Controller.FeeController;
import com.wg.Controller.IssueController;
import com.wg.Controller.LeavesController;
import com.wg.Controller.NotificationController;
import com.wg.Controller.UserController;
import com.wg.Helper.InvalidFeeAmountException;
import com.wg.Helper.PasswordUtil;
import com.wg.Helper.PasswordValidator;
import com.wg.Helper.Validator;
import com.wg.Model.Attendance;
import com.wg.Model.Course;
import com.wg.Model.CourseMarks;
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
				System.out.println(StringConstants.adminMenu);
				System.out.println("Enter your choice: ");
				int choice = Validator.getUserChoice(scanner);

				switch (choice) {
				case 1:
					manageUser();
					break;
				case 2:
					getClassDetails();
					break;
				case 3:
					sendNotification();
					break;
				case 4:
					manageFees();
					break;
				case 5:
					manageLeaves(role);
					break;
				case 6:
					manageIssues();
					break;
				case 7:
					manageCourse();
					break;
				case 8:
					manageAttendance();
					break;
				case 9:
					logout();
					break;
				case 10:
					System.out.println("Exiting...");
					System.exit(0);
					return;
				default:
					System.out.println("Invalid choice. Please try again.");
				}
			}
		} else if (role.equals("Student")) {
			while (true) {
				System.out.println(StringConstants.STUDENT_MENU);
				System.out.println("Enter your choice: ");
				int choice = scanner.nextInt();
				scanner.nextLine();
				switch (choice) {
				case 1:
					manageLeavesStudent(user);
					break;
				case 2:
					manageIssueStudent(user);
					break;
				case 3:
					manageFeesStudent(user);
					break;
				case 4:
					checkMarks(user);
					break;
				case 5:
					readNotifications(user);
					break;
				case 6:
					viewAttendance(user);
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
				System.out.println(StringConstants.FACULTY_MENU);
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
					addMarks();
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
		} else {
			System.out.println("Enter valid role!");
		}
	}

	private void manageFeesStudent(User user) {

		while (true) {
			System.out.println(" ");
			System.out.println(StringConstants.MANAGE_FEES_STUDENT);
			System.out.println(" ");
			System.out.println("Enter your choice: ");
			int choice = Validator.getUserChoice(scanner);
			scanner.nextLine();

			switch (choice) {
			case 1:
				checkFees(user);
				break;
			case 2:
				checkFine(user);
				break;
			case 3:
				payFees(user);
				break;
			case 4:
				return;
			default:
				System.out.println("Enter valid choice!");
			}
		}

	}

	private void manageIssueStudent(User user) {

		while (true) {
			System.out.println(" ");
			System.out.println(StringConstants.MANAGE_ISSUES_MENU_STUDENT);
			System.out.println(" ");
			System.out.println("Enter your choice: ");
			int choice = Validator.getUserChoice(scanner);
			scanner.nextLine();

			switch (choice) {
			case 1:
				raiseIssue(user);
				break;
			case 2:
				checkIssueStatus(user);
				break;
			case 3:
				return;
			default:
				System.out.println("Enter valid choice!");
			}
		}

	}

	private void manageUser() {

		while (true) {
			System.out.println(" ");
			System.out.println(StringConstants.MANAGE_USER);
			System.out.println(" ");
			System.out.println("Enter your choice: ");
			int choice = Validator.getUserChoice(scanner);
			scanner.nextLine();

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
				getAllUser();
				break;
			case 5:
				deleteUser();
				break;
			case 6:
				updateUser();
				break;
			case 7:
				return;
			default:
				System.out.println("Enter valid choice!");
			}
		}
	}

	private void manageFees() {

		while (true) {
			System.out.println(" ");
			System.out.println(StringConstants.MANAGE_FEES);
			System.out.println(" ");
			System.out.println("Enter your choice: ");
			int choice = Validator.getUserChoice(scanner);
			scanner.nextLine();

			switch (choice) {
			case 1:
				addFees();
				break;
			case 2:
				calculateFine();
				break;
			case 3:
				return;
			default:
				System.out.println("Enter valid choice!");
			}
		}
	}

	private void manageLeaves(String role) {

		while (true) {
			System.out.println(" ");
			System.out.println(StringConstants.MANAGE_LEAVES_MENU);
			System.out.println(" ");
			System.out.println("Enter your choice: ");
			int choice = Validator.getUserChoice(scanner);
			scanner.nextLine();

			switch (choice) {
			case 1:
				viewAllLeave();
				break;
			case 2:
				approveLeave(role);
				break;
			case 3:
				return;
			default:
				System.out.println("Enter valid choice!");
			}
		}
	}

	private void manageIssues() {

		while (true) {
			System.out.println(" ");
			System.out.println(StringConstants.MANAGE_ISSUES_MENU);
			System.out.println(" ");
			System.out.println("Enter your choice: ");
			int choice = Validator.getUserChoice(scanner);
			scanner.nextLine();

			switch (choice) {
			case 1:
				viewAllIssues();
				break;
			case 2:
				resolveIssue();
				break;
			case 3:
				return;
			default:
				System.out.println("Enter valid choice!");
			}
		}
	}

	private void manageLeavesStudent(User user) {

		while (true) {
			System.out.println(" ");
			System.out.println(StringConstants.MANAGE_LEAVES_MENU_STUDENT);
			System.out.println(" ");
			System.out.println("Enter your choice: ");
			int choice = Validator.getUserChoice(scanner);
			scanner.nextLine();
			switch (choice) {
			case 1:
				applyLeave(user);
				break;
			case 2:
				checkLeaveStatus(user);
				break;
			case 3:
				return;
			default:
				System.out.println("Enter valid choice!");
			}
		}
	}

	public void manageCourse() {

		while (true) {
			System.out.println(StringConstants.MANAGE_COURSES_MENU);
			System.out.println("Enter your choice: ");
			int choice = Validator.getUserChoice(scanner);
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

	private void manageAttendance() {

		while (true) {
			System.out.println(StringConstants.MANAGE_ATTENDANCE_MENU);
			System.out.println("Enter your choice: ");
			int choice = Validator.getUserChoice(scanner);
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

	private void viewAttendance(User user) {
		String userId = user.getUserId();
		List<Attendance> attendance = attendanceController.viewAttendanceById(userId);
		if (attendance.isEmpty()) {
			System.out.println("No attendance records available");
			return;
		}
		for (Attendance attendee : attendance) {
			System.out.println(attendee);
		}
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
		System.out.println("List of all Users");
		List<User> list = userController.getAllUser();
		System.out.println(StringConstants.BorderLine);
		int index = 0;
		for (User user : list) {
			System.out
					.println(index + " | " + user.toString().replace(",", " |").replace("User{", "").replace("}", ""));
			index++;
		}
		System.out.println(StringConstants.BorderLine);
		System.out.println("Enter the User Index:");
		index = scanner.nextInt();
		String userId = list.get(index).getUserId();
		User user = userController.getUserById(userId);
		if (user != null) {
			System.out.println(StringConstants.BorderLine);
			System.out.println(user.toString().replace(",", " |").replace("User{", "").replace("}", ""));
			System.out.println(StringConstants.BorderLine);

		} else {
			System.out.println("User not found.");
		}
	}

	private void getUserByUsername() {
		System.out.print("Enter user Username: ");
		String username = scanner.next();
		User user = userController.getUserByUsername(username);
		if (user != null) {
			System.out.println(StringConstants.BorderLine);
			System.out.println(user.toString().replace(",", " |").replace("User{", "").replace("}", ""));
			System.out.println(StringConstants.BorderLine);

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
			System.out.println(StringConstants.BorderLine);
			for (User user : users) {
				System.out.println(user.toString().replace(",", " |").replace("User{", "").replace("}", ""));
			}
			System.out.println(StringConstants.BorderLine);
		}
	}

	private void deleteUser() {
		List<User> users = userController.getAllUser();
		if (users == null) {
			System.out.println("No users found");
			return;
		}
		System.out.println("List of all Users");
		System.out.println(StringConstants.BorderLine);
		int index = 0;
		for (User user : users) {
			System.out
					.println(index + " | " + user.toString().replace(",", " |").replace("User{", "").replace("}", ""));
			index++;
		}
		System.out.println(StringConstants.BorderLine);
		System.out.println("Enter the User Index:");
		index = scanner.nextInt();
		boolean flag = userController.deleteUser(users.get(index).getUserId());
		if (flag == true) {
			System.out.println("User Deleted Successfully!");
		} else {
			System.out.println("User deletion Unsuccessful!");
		}
	}

	private void getAllUser() {
		System.out.println("List of all Users");
		List<User> list = userController.getAllUser();
		System.out.println(StringConstants.BorderLine);
		int index = 0;
		if (list == null) {
			System.out.println("No users found");
			return;
		}
		for (User user : list) {
			System.out
					.println(index + " | " + user.toString().replace(",", " |").replace("User{", "").replace("}", ""));
			index++;
		}
		System.out.println(StringConstants.BorderLine);
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
		double fees = feeController.checkFees(userId);
		System.out.println("The fees Amount is: " + fees);
	}

	private void addFees() {
		List<User> users = userController.getAllUser();
		if (users == null) {
			System.out.println("No users found");
			return;
		}
		System.out.println(StringConstants.BorderLine);
		int index = 0;
		for (User user : users) {
			System.out
					.println(index + " | " + user.toString().replace(",", " |").replace("User{", "").replace("}", ""));
			index++;
		}
		System.out.println(StringConstants.BorderLine);
		System.out.println("Enter the User Index:");
		index = scanner.nextInt();
		String userId = users.get(index).getUserId();
		User user = userController.getUserById(userId);
		boolean validateUser = false;
		while (!validateUser) {
			if (!user.getRole().toString().equals("Student")) {
				System.out.println("Enter valid Student UserId");
				validateUser = false;
			} else {
				validateUser = true;
			}
		}

		double feeAmount = 0;
		while (feeAmount <= 0) {
			System.out.println("Enter the Fee Amount:");
			try {
				feeAmount = scanner.nextDouble();
				throw new InvalidFeeAmountException("Fee amount must be greater than zero.");
			} catch (DateTimeParseException | InvalidFeeAmountException e) {
				e.printStackTrace();
			}
		}
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
		List<User> users = userController.getAllUser();
		System.out.println(StringConstants.BorderLine);
		int index = 0;
		for (User user : users) {
			System.out
					.println(index + " | " + user.toString().replace(",", " |").replace("User{", "").replace("}", ""));
			index++;
		}
		System.out.println(StringConstants.BorderLine);
		System.out.println("Enter the User Index:");
		index = scanner.nextInt();
		String userId = users.get(index).getUserId();
		double fine = feeController.calculateFine(userId);
		System.out.println("The fine is: " + fine);
	}

	private void checkFine(User user) {
		String userId = user.getUserId();
		double fine = feeController.calculateFine(userId);
		System.out.println("The fine is: " + fine);
	}

	private void getAllCourses() {
		List<Course> list = courseController.getAllCourses();
		if (list == null) {
			System.out.println("No courses found");
			return;
		}
		for (Course course : list) {
			System.out.println(course);
		}
	}

	private void updateCourse() {
		courseController.updateCourse();
	}

	private void deleteCourse() {
		List<Course> list = courseController.getAllCourses();
		if (list == null) {
			System.out.println("No courses found");
			return;
		}
		System.out.println(StringConstants.BorderLine);
		int index = 0;
		for (Course user : list) {
			System.out
					.println(index + " | " + user.toString().replace(",", " |").replace("User{", "").replace("}", ""));
			index++;
		}
		System.out.println(StringConstants.BorderLine);
		System.out.println("Enter the User Index:");
		index = scanner.nextInt();
		String courseId = list.get(index).getCourseId();
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
		List<Course> list = courseController.getAllCourses();
		if (list == null) {
			System.out.println("No courses found");
			return;
		}
		System.out.println(StringConstants.BorderLine);
		int index = 0;
		for (Course user : list) {
			System.out
					.println(index + " | " + user.toString().replace(",", " |").replace("User{", "").replace("}", ""));
			index++;
		}
		System.out.println(StringConstants.BorderLine);
		System.out.println("Enter the User Index to Calculate Fine");
		index = scanner.nextInt();
		String courseId = list.get(index).getCourseId();
		Course course = courseController.getCourse(courseId);
		System.out.println(course);
	}

	private void addAttendance() {
		List<User> list = userController.getAllUser();
		if (list == null) {
			System.out.println("No Users found");
			return;
		}
		System.out.println(StringConstants.BorderLine);
		int index = 0;
		for (User user : list) {
			System.out
					.println(index + " | " + user.toString().replace(",", " |").replace("User{", "").replace("}", ""));
			index++;
		}
		System.out.println(StringConstants.BorderLine);
		System.out.println("Enter the User Index:");
		index = scanner.nextInt();
		String studentId = list.get(index).getUserId();
		System.out.println("Enter standard:");
		int standard = scanner.nextInt();

		LocalDate date = null;
		boolean validateDate = false;
		while (!validateDate) {
			System.out.println("Enter the date (YYYY-MM-DD):");
			try {
				String dateString = scanner.next();
				date = LocalDate.parse(dateString);
				validateDate = true;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

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
		List<User> users = userController.getAllUser();
		System.out.println("List of all Users");
		System.out.println(StringConstants.BorderLine);
		int index = 0;
		for (User user : users) {
			System.out
					.println(index + " | " + user.toString().replace(",", " |").replace("User{", "").replace("}", ""));
			index++;
		}
		System.out.println(StringConstants.BorderLine);

		System.out.println("Enter the User Index to delete");
		index = scanner.nextInt();
		String userId = users.get(index).getUserId();
		List<Attendance> list = attendanceController.viewAttendanceById(userId);
		System.out.println(StringConstants.BorderLine);
		for (Attendance ls : list) {
			System.out.println(ls);
		}
		System.out.println(StringConstants.BorderLine);
	}

	private void approveLeave(String role) {
		List<Leaves> leaves = leavesController.viewAllLeave();
		if (leaves.isEmpty()) {
			System.out.println("No Leaves found");
			return;
		}
		System.out.println(StringConstants.BorderLine);
		int index = 0;
		for (Leaves user : leaves) {
			System.out
					.println(index + " | " + user.toString().replace(",", " |").replace("User{", "").replace("}", ""));
			index++;
		}
		System.out.println(StringConstants.BorderLine);
		System.out.println("Enter the Leave Index");
		index = scanner.nextInt();
		String userId = leaves.get(index).getUserId();
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
		if (leaves.isEmpty()) {
			System.out.println("No Leaves found");
			return;
		}
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
			List<User> users = userController.getAllUser();
			System.out.println("List of all Users");
			System.out.println(StringConstants.BorderLine);
			int index = 0;
			for (User user : users) {
				System.out.println(
						index + " | " + user.toString().replace(",", " |").replace("User{", "").replace("}", ""));
				index++;
			}
			System.out.println(StringConstants.BorderLine);

			System.out.println("Enter the User Index");
			index = scanner.nextInt();
			String userId = users.get(index).getUserId();
			System.out.println("Enter title: ");
			String type = scanner.next();
			scanner.nextLine();
			System.out.println("Enter message: ");
			String description = scanner.nextLine();
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
		List<Issue> issue = issueController.viewAllIssues();
		System.out.println("List of all Issues");
		System.out.println(StringConstants.BorderLine);
		int index = 0;
		for (Issue user : issue) {
			System.out
					.println(index + " | " + user.toString().replace(",", " |").replace("User{", "").replace("}", ""));
			index++;
		}
		System.out.println(StringConstants.BorderLine);

		System.out.println("Enter the Issue index");
		index = scanner.nextInt();
		String userId = issue.get(index).getUserId();
		issueController.resolveIssue(userId);
	}

	public void checkIssueStatus(User user) {
		String userId = user.getUserId();
		List<Issue> issue = issueController.checkIssueStatus(userId);
		if (issue.isEmpty()) {
			System.out.println("No Issues found");
			return;
		}
		for (Issue issues : issue) {
			System.out.println(issues);
		}
	}

	public void viewAllIssues() {
		List<Issue> issue = issueController.viewAllIssues();
		if (issue.isEmpty()) {
			System.out.println("No Issues found");
			return;
		}
		for (Issue issues : issue) {
			System.out.println(issues);
		}
	}

	private void checkMarks(User user) {
		String userId = user.getUserId();
		List<CourseMarks> coursesMarks = courseMarksController.checkMarks(userId);
		if (coursesMarks.isEmpty()) {
			System.out.println("No marks added");
			return;
		}
		for (CourseMarks c : coursesMarks) {
			Course course = courseController.getCourse(c.getCourseId());
			System.out.println("Course Name: " + course.getCourseName() + "  CourseId: " + c.getCourseId() + "  Marks: "
					+ c.getMarks());
		}
	}

	private void addMarks() {
		boolean validateUser = false;
		String userId = "";
		String courseId = "";
		while (!validateUser) {
			List<User> users = userController.getAllUser();
			System.out.println("List of all Users");
			System.out.println(StringConstants.BorderLine);
			int index = 0;
			for (User user : users) {
				System.out.println(
						index + " | " + user.toString().replace(",", " |").replace("User{", "").replace("}", ""));
				index++;
			}
			System.out.println(StringConstants.BorderLine);

			System.out.println("Enter the User Index:");
			index = scanner.nextInt();
			userId = users.get(index).getUserId();
			User user = userController.getUserById(userId);
			if (user == null) {
				System.out.println("Enter valid Index");
				validateUser = false;
			} else if (user.getRole().toString().equals("Admin") || user.getRole().toString().equals("Faculty")) {
				System.out.println("Marks can only be added to Student, Enter Student UserId");
				validateUser = false;
			} else {
				validateUser = true;
			}
		}
		validateUser = false;
		while (!validateUser) {
			List<Course> list = courseController.getAllCourses();
			if (list == null) {
				System.out.println("No courses found");
				return;
			}
			System.out.println(StringConstants.BorderLine);
			int index = 0;
			for (Course user : list) {
				System.out.println(
						index + " | " + user.toString().replace(",", " |").replace("User{", "").replace("}", ""));
				index++;
			}
			System.out.println(StringConstants.BorderLine);
			System.out.println("Enter the User Index to Calculate Fine");
			index = scanner.nextInt();
			courseId = list.get(index).getCourseId();
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
}