package com.wg.UI;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;
import java.util.stream.Collectors;

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
import com.wg.DAO.AttendanceDAO;
import com.wg.DAO.CourseDAO;
import com.wg.DAO.CourseMarksDAO;
import com.wg.DAO.FeeDAO;
import com.wg.DAO.IssueDAO;
import com.wg.DAO.LeavesDAO;
import com.wg.DAO.NotificationDAO;
import com.wg.DAO.UserDAO;
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
import com.wg.Services.AttendanceServices;
import com.wg.Services.CourseMarksService;
import com.wg.Services.CourseService;
import com.wg.Services.FeeService;
import com.wg.Services.IssueService;
import com.wg.Services.LeavesService;
import com.wg.Services.NotificationService;
import com.wg.Services.UserService;

public class UserUI {
	UserDAO dao = new UserDAO();
	UserService service = new UserService(dao);
	UserController userController = new UserController(service);
	CourseDAO courseDAO = new CourseDAO();
	CourseService courseService = new CourseService(courseDAO);
	CourseController courseController = new CourseController(courseService);
	AttendanceDAO attendanceDAO = new AttendanceDAO();
	AttendanceServices attendanceService = new AttendanceServices(attendanceDAO);
	AttendanceController attendanceController = new AttendanceController(attendanceService);
	FeeDAO feeDAO = new FeeDAO();
	FeeService feeService = new FeeService(feeDAO);
	FeeController feeController = new FeeController(feeService);
	NotificationDAO notificationDAO = new NotificationDAO();
	NotificationService notificationService = new NotificationService(notificationDAO);
	NotificationController notificationController = new NotificationController(notificationService);
	LeavesDAO leavesDAO = new LeavesDAO();
	LeavesService leavesService = new LeavesService(leavesDAO);
	LeavesController leavesController = new LeavesController(leavesService);
	IssueDAO issueDAO = new IssueDAO();
	IssueService issueService = new IssueService(issueDAO);
	IssueController issueController = new IssueController(issueService);
	CourseMarksDAO courseMarksDAO = new CourseMarksDAO();
	CourseMarksService courseMarksService = new CourseMarksService(courseMarksDAO);
	CourseMarksController courseMarksController = new CourseMarksController(courseMarksService);

	Scanner scanner = new Scanner(System.in);

	public void manageFeesStudent(User user) {
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

	public void manageIssueStudent(User user) {
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

	public void manageUser() {
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

	public void manageFees() {
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

	public void manageLeaves(String role) {
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

	public void manageIssues() {
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

	public void manageLeavesStudent(User user) {
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

	public void manageAttendance() {
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

	public User authenticateUser(String username, String password) {
		return userController.authenticateUser(username, password);
	}

	public void viewAttendance(User user) {
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

	public void addUser() {
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
		System.out
				.print("Enter password: (Atleast one UpperCase character, one Special character, one Integer digit): ");
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
		String contactNumber = null;
		boolean validNumber = false;
		while (!validNumber) {
			System.out.println("Enter contact number:");
			contactNumber = scanner.next();
			if (Validator.isValidContactNo(contactNumber)) {
				validNumber = true;
			} else {
				System.out.println("Enter valid Mobile Number");
				validNumber = false;
			}
		}

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

	public void getUserById() {
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

	public void getUserByUsername() {
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

	public void getClassDetails() {
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

	public void deleteUser() {
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

	public void getAllUser() {
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

	public void logout() {
		App.main(null);
	}

	public void updateUser() {
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
		System.out.print("Enter user index: ");
		index = scanner.nextInt();
		userController.updateUser(list.get(index).getUserId());
	}

	public void payFees(User user) {
		String userId = user.getUserId();
		feeController.payFees(userId);
	}

	public void checkFees(User user) {
		String userId = user.getUserId();
		double fees = feeController.checkFees(userId);
		System.out.println("The fees Amount is: " + fees);
	}

	public void addFees() {
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
			} catch (DateTimeParseException | InvalidFeeAmountException e) {
				e.printStackTrace();
				throw new InvalidFeeAmountException("Fee amount must be greater than zero.");
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

	public void calculateFine() {
		List<User> users = userController.getAllUser();
		if (users.isEmpty()) {
			System.out.println("No users Found");
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
		String role = users.get(index).getRole().toString();
		if (role.equals("Admin") || role.equals("Faculty")) {
			System.out.println("Enter Valid Id for Student");
			return;
		}
		double fine = feeController.calculateFine(userId);
		System.out.println("The fine is: " + fine);
	}

	public void checkFine(User user) {
		String userId = user.getUserId();
		double fine = feeController.calculateFine(userId);
		System.out.println("The fine is: " + fine);
	}

	public void getAllCourses() {
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
	}

	public void updateCourse() {
		List<Course> course = courseController.getAllCourses();
		if (course == null) {
			System.out.println("No courses found");
			return;
		}
		System.out.println(StringConstants.BorderLine);
		int index = 0;
		for (Course user : course) {
			System.out
					.println(index + " | " + user.toString().replace(",", " |").replace("User{", "").replace("}", ""));
			index++;
		}
		System.out.println(StringConstants.BorderLine);
		System.out.println("Enter the User Index:");
		index = scanner.nextInt();
		String courseId = course.get(index).getCourseId();
		courseController.updateCourse(courseId);
	}

	public void deleteCourse() {
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

	public void addCourse() {
		System.out.println("Enter Course Name");
		String courseName = scanner.next();
		scanner.nextLine();
		System.out.println("Enter standard");
		int standard = scanner.nextInt();
		courseController.addCourse(courseName, standard);
	}

	public void getCourse() {
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
		System.out.println("Enter the Course Index:");
		index = scanner.nextInt();
		String courseId = list.get(index).getCourseId();
		Course course = courseController.getCourse(courseId);
		System.out.println(course);
	}

	public void addAttendance() {
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
		String role = list.get(index).getRole().toString();
		if (role.equals("Admin") || role.equals("Faculty")) {
			System.out.println("Enter Valid Id for Student");
			return;
		}
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

	public void viewAttendanceByStandard() {
		System.out.println("Enter the standard:");
		int standard = scanner.nextInt();
		List<Attendance> list = attendanceController.viewAttendanceByStandard(standard);
		if (list.isEmpty()) {
			System.out.println("No attendance Record found");
			return;
		}
		System.out.println(StringConstants.BorderLine);
		int index = 0;
		for (Attendance user : list) {
			String studentId = user.getStudentId();
			User userr = userController.getUserById(studentId);
			String name = userr.getName();
			String username = userr.getUsername();
			System.out.println(index + " | " + user.toString().replace(",", " |").replace("User{", "").replace("}", "")
					+ "| Name = " + name + "| Username = " + username);
			index++;
		}
		System.out.println(StringConstants.BorderLine);
	}

	public void viewAttendanceById() {
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

		System.out.println("Enter the Student Index:");
		index = scanner.nextInt();
		String userId = users.get(index).getUserId();
		String role = users.get(index).getRole().toString();
		if (role.equals("Admin") || role.equals("Faculty")) {
			System.out.println("Enter Valid Id for Student");
			return;
		}
		List<Attendance> list = attendanceController.viewAttendanceById(userId);
		if (list.isEmpty()) {
			System.out.println("No attendance records available");
		}
		System.out.println(StringConstants.BorderLine);
		for (Attendance ls : list) {
			System.out.println(ls);
		}
		System.out.println(StringConstants.BorderLine);
	}

	public void approveLeave(String role) {
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
		String Status = leaves.get(index).getStatus().toString();
		if (Status.equals("Approved")) {
			System.out.println("Leave already approved");
			return;
		}
		leavesController.approveLeave(userId);
	}

	public void applyLeave(User user) {
		String userId = user.getUserId();
		System.out.println("Enter Leave Content:");
		String content = scanner.nextLine();
		scanner.nextLine();
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
//		while(!Validator.isValidDate(endDate)) {
//			System.out.println("Enter valid date");
//		}
		Leaves leave = new Leaves(leaveId, userId, content, startDate, endDate, status);
		leavesController.applyLeave(leave);
	}

	public void viewAllLeave() {
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
	}

	public void checkLeaveStatus(User user) {
		String userId = user.getUserId();
		List<Leaves> leaves = leavesController.checkLeaveStatus(userId);
		if (leaves == null) {
			System.out.println("First Apply for Leave");
			return;
		}
		System.out.println(StringConstants.BorderLine);
		for (Leaves leave : leaves) {
			System.out.println(leave);
		}
		System.out.println(StringConstants.BorderLine);

	}

	public void readNotifications(User user) {
		String userId = user.getUserId();
		notificationController.readNotifications(userId);
	}

	public void sendNotification() {
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

	public void raiseIssue(User user) {
		String userId = user.getUserId();
		System.out.println("Enter Issue Content:");
		String message = scanner.nextLine();
		scanner.nextLine();
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
		String Status = issue.get(index).getStatus().toString();
		if (Status.equals("RESOLVED")) {
			System.out.println("Issue already resolved");
			return;
		}
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
		System.out.println(StringConstants.BorderLine);
		int index = 0;
		for (Issue user : issue) {
			System.out
					.println(index + " | " + user.toString().replace(",", " |").replace("User{", "").replace("}", ""));
			index++;
		}
		System.out.println(StringConstants.BorderLine);
	}

	public void checkMarks(User user) {
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

	public void addMarks() {
		boolean validateUser = false;
		String userId = "";
		String courseId = "";
		ArrayList<User> storeUser = new ArrayList<User>();
		User user1 = null;
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
			user1 = users.get(index);
			userId = user1.getUserId();

			if (user1.getRole().toString().equals("Admin") || user1.getRole().toString().equals("Faculty")) {
				System.out.println("Marks can only be added to Student, Enter Student User Index");
				validateUser = false;
			} else {
				storeUser.add(user1);
				validateUser = true;
			}
		}
		validateUser = false;
		while (!validateUser) {

			List<Course> list = courseController.getAllCourses();
			User user2 = storeUser.get(0);
			int standard = user2.getStandard();
			List<Course> filteredCourses = list.stream().filter(course -> course.getStandard() == standard)
					.collect(Collectors.toList());

			System.out.println(StringConstants.BorderLine);
			int index = 0;
			for (Course course : filteredCourses) {
				if (storeUser.isEmpty()) {
					System.out.println("No course added for this standard");
					// manageStudent();
					return;
				} else {
					System.out.println(
							index + " | " + course.toString().replace(",", " |").replace("User{", "").replace("}", ""));
					index++;
				}
			}
			if (index == 0) {
				System.out.println("No courses added for this standard");
				return;
			}
			System.out.println(StringConstants.BorderLine);
			System.out.println("Enter the Course Index:");
			index = scanner.nextInt();
			courseId = filteredCourses.get(index).getCourseId();
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

	public void generateMarksheet() {
		boolean validateUser1 = false;
		String userId1 = "";
		ArrayList<User> storeUser1 = new ArrayList<User>();
		User user11 = null;
		while (!validateUser1) {
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
			user11 = users.get(index);
			userId1 = user11.getUserId();

			if (user11.getRole().toString().equals("Admin") || user11.getRole().toString().equals("Faculty")) {
				System.out.println("Marksheets can only be added to Student, Enter Student UserId");
				validateUser1 = false;
			} else {
				storeUser1.add(user11);
				validateUser1 = true;
			}
		}

		List<CourseMarks> list = courseMarksController.checkMarks(userId1);
		if (list.isEmpty()) {
			System.out.println("Add marks first");
			return;
		}
		double Totalmarks = 0;
		for (CourseMarks course : list) {
			Totalmarks += course.getMarks();
		}
		int noOfCourses = list.size();
		double percentage = Totalmarks / noOfCourses;
		System.out.println("Total marks: " + Totalmarks + " Percentage: " + percentage);

		if (percentage > 45) {
			System.out.println("PASS");
		} else {
			System.out.println("FAIL");
		}
	}

	public void viewMarksheet(User user) {
		String userId = user.getUserId();
		List<CourseMarks> list = courseMarksController.checkMarks(userId);
		if (list.isEmpty()) {
			System.out.println("No marks added");
			return;
		}
		System.out.println(StringConstants.BorderLine);
		for (CourseMarks c : list) {
			Course course = courseController.getCourse(c.getCourseId());
			System.out.println("Course Name: " + course.getCourseName() + "  CourseId: " + c.getCourseId() + "  Marks: "
					+ c.getMarks() + " Standard: " + user.getStandard());
		}
		System.out.println(StringConstants.BorderLine);
		double Totalmarks = 0;
		for (CourseMarks course : list) {
			Totalmarks += course.getMarks();
		}
		int noOfCourses = list.size();
		double percentage = Totalmarks / noOfCourses;
		System.out.println("Total marks: " + Totalmarks + " Percentage: " + percentage);
		if (percentage > 45) {
			System.out.println("Result : PASS");
		} else {
			System.out.println("Result: FAIL");
		}

	}
}