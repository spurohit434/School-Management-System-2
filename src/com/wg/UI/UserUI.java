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
import com.wg.Helper.printer.AttendancePrinter;
import com.wg.Helper.printer.CourseMarksPrinter;
import com.wg.Helper.printer.CoursePrinter;
import com.wg.Helper.printer.IssuePrinter;
import com.wg.Helper.printer.LeavesPrinter;
import com.wg.Helper.printer.MarksheetPrinter;
import com.wg.Helper.printer.NotificationPrinter;
import com.wg.Helper.printer.UserPrinter;
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
			System.out.println(StringConstants.ENTER_YOUR_CHOICE);
			int choice = Validator.getUserChoice();
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
				System.out.println(StringConstants.ENTER_VALID_CHOICE);
			}
		}
	}

	public void manageIssueStudent(User user) {
		while (true) {
			System.out.println(" ");
			System.out.println(StringConstants.MANAGE_ISSUES_MENU_STUDENT);
			System.out.println(" ");
			System.out.println(StringConstants.ENTER_YOUR_CHOICE);
			int choice = Validator.getUserChoice();
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
				System.out.println(StringConstants.ENTER_VALID_CHOICE);
			}
		}

	}

	public void manageUser() {
		while (true) {
			System.out.println(" ");
			System.out.println(StringConstants.MANAGE_USER);
			System.out.println(" ");
			System.out.println(StringConstants.ENTER_YOUR_CHOICE);
			int choice = Validator.getUserChoice();
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
				System.out.println(StringConstants.ENTER_VALID_CHOICE);
			}
		}
	}

	public void manageFees() {
		while (true) {
			System.out.println(" ");
			System.out.println(StringConstants.MANAGE_FEES);
			System.out.println(" ");
			System.out.println(StringConstants.ENTER_YOUR_CHOICE);
			int choice = Validator.getUserChoice();
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
				System.out.println(StringConstants.ENTER_VALID_CHOICE);
			}
		}
	}

	public void manageLeaves(String role) {
		while (true) {
			System.out.println(" ");
			System.out.println(StringConstants.MANAGE_LEAVES_MENU);
			System.out.println(" ");
			System.out.println(StringConstants.ENTER_YOUR_CHOICE);
			int choice = Validator.getUserChoice();
			switch (choice) {
			case 1:
				viewAllLeave();
				break;
			case 2:
				approveLeave(role);
				break;
			case 3:
				rejectLeave(role);
				break;
			case 4:
				return;
			default:
				System.out.println(StringConstants.ENTER_VALID_CHOICE);
			}
		}
	}

	public void manageIssues() {
		while (true) {
			System.out.println(" ");
			System.out.println(StringConstants.MANAGE_ISSUES_MENU);
			System.out.println(" ");
			System.out.println(StringConstants.ENTER_YOUR_CHOICE);
			int choice = Validator.getUserChoice();
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
				System.out.println(StringConstants.ENTER_VALID_CHOICE);
			}
		}
	}

	public void manageLeavesStudent(User user) {
		while (true) {
			System.out.println(" ");
			System.out.println(StringConstants.MANAGE_LEAVES_MENU_STUDENT);
			System.out.println(" ");
			System.out.println(StringConstants.ENTER_YOUR_CHOICE);
			int choice = Validator.getUserChoice();
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
				System.out.println(StringConstants.ENTER_VALID_CHOICE);
			}
		}
	}

	public void manageCourse() {
		while (true) {
			System.out.println(StringConstants.MANAGE_COURSES_MENU);
			System.out.println(StringConstants.ENTER_YOUR_CHOICE);
			int choice = Validator.getUserChoice();
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
				System.out.println(StringConstants.ENTER_VALID_CHOICE);
			}
		}
	}

	public void manageAttendance() {
		while (true) {
			System.out.println(StringConstants.MANAGE_ATTENDANCE_MENU);
			System.out.println(StringConstants.ENTER_YOUR_CHOICE);
			int choice = Validator.getUserChoice();
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
				System.out.println(StringConstants.ENTER_VALID_CHOICE);
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
			System.out.println(StringConstants.NO_ATTENDANCE_RECORDS_AVAILABLE);
			return;
		}
		AttendancePrinter.printAttendanceDetails(attendance);
	}

	public void addUser() {
		System.out.print(StringConstants.ENTER_USERNAME_ALPHA_NUMERIC_4_30_CHARACTERS);
		String username = null;
		boolean validUserName = false;
		while (!validUserName) {
			username = scanner.next();
			if (Validator.isValidUsername(username)) {
				validUserName = true;
			} else {
				System.out.println(StringConstants.ENTER_VALID_USERNAME);
				username = scanner.next();
				validUserName = false;
			}
		}

		System.out.print(StringConstants.ENTER_NAME);
		String name = null;
		Boolean validName = false;
		while (!validName) {
			name = scanner.next();
			if (Validator.isValidName(name)) {
				validName = true;
			} else {
				System.out.println(StringConstants.ENTER_VALID_NAME);
				name = scanner.next();
				validName = false;
			}
		}
		int age = 0;
		boolean validInput = false;
		while (!validInput) {
			System.out.print(StringConstants.ENTER_AGE);
			try {
				age = scanner.nextInt();
				if (Validator.isValidAge(age)) {
					validInput = true;
				} else {
					System.out.println(StringConstants.INVALID_AGE_ENTER_A_VALID_AGE);
					validInput = false; // If input is valid, exit loop
				}
			} catch (InputMismatchException e) {
				System.out.println(StringConstants.INVALID_INPUT_PLEASE_ENTER_A_VALID_INTEGER_FOR_AGE);
				scanner.next(); // Clear invalid input
			}
		}

		System.out.print(StringConstants.ENTER_PASSWORD);
		PasswordValidator passwordValidator = new PasswordValidator();
		String password = null;
		boolean isValidPassword = false;
		while (!isValidPassword) {
			password = scanner.next();
			if (passwordValidator.isValidPassword(password)) {
				isValidPassword = true;
			} else {
				System.out.println(StringConstants.ENTER_VALID_PASSWORD);
				isValidPassword = false;
			}
		}
		String hashedPassword = PasswordUtil.hashPassword(password);
		validInput = false;
		String email = null;
		System.out.print(StringConstants.ENTER_EMAIL);
		while (!validInput) {
			try {
				email = scanner.next();
				if (Validator.isValid(email)) {
					validInput = true;
				} else {
					System.out.print(StringConstants.ENTER_VALID_EMAIL);
					validInput = false;
				}
			} catch (InputMismatchException e) {
				System.out.println(StringConstants.INVALID_INPUT_PLEASE_ENTER_A_VALID_EMAIL);
				scanner.next(); // Clear invalid input
			}
		}

		String input = "";
		boolean validInput1 = false;
		while (!validInput1) {
			System.out.print(StringConstants.ENTER_ROLE_STUDENT_FACULTY_CASE_SENSITIVE);
			try {
				input = scanner.next().trim();
				if (input.equals(StringConstants.ADMIN) || input.equals(StringConstants.ADMIN2)
						|| input.equals(StringConstants.A_DMIN)) {
					System.out.println(StringConstants.ROLE_CAN_NOT_BE_ADMIN);
					validInput1 = false;
				} else {
					validInput1 = true;
				}
			} catch (InputMismatchException e) {
				System.out.println(StringConstants.INVALID_INPUT_PLEASE_ENTER_A_VALID_ROLE);
				scanner.next();
			}
		}
		LocalDate date = null;
		while (date == null) {
			System.out.println(StringConstants.ENTER_THE_DATE_OF_BIRTH_YYYY_MM_DD);
			try {
				boolean validDate = false;
				while (!validDate) {
					String input2 = scanner.next();
					date = LocalDate.parse(input2);
					if (date.isBefore(LocalDate.now())) {
						validDate = true;
					} else {
						validDate = false;
						System.out.println(StringConstants.PLEASE_ENTER_VALID_DOB_DATE);
					}
				}
			} catch (DateTimeParseException e) {
				System.out.println(StringConstants.INVALID_DATE_FORMAT);
			}
		}
		String contactNumber = null;
		System.out.println(StringConstants.ENTER_CONTACT_NUMBER);
		boolean validNumber = false;
		while (!validNumber) {
			contactNumber = scanner.next();
			if (Validator.isValidContactNo(contactNumber)) {
				validNumber = true;
			} else {
				System.out.println(StringConstants.ENTER_VALID_MOBILE_NUMBER);
				validNumber = false;
			}
		}

		int standard = 0;
		if (input.equals(StringConstants.STUDENT)) {
			boolean validStandard1 = false;
			while (!validStandard1) {
				System.out.println(StringConstants.ENTER_THE_STANDARD);
				try {
					standard = scanner.nextInt(); // Try to read the integer
					if (standard >= 1 && standard <= 12) {
						validStandard1 = true; // Input is valid
					} else {
						System.out.println(StringConstants.INVALID_INPUT_ENTER_A_NUMBER_BETWEEN_1_AND_12);
					}
				} catch (InputMismatchException e) {
					System.out.println(StringConstants.INVALID_INPUT_PLEASE_ENTER_A_VALID_INTEGER_FOR_STANDARD);
					scanner.next(); // Clear the invalid input
				}
			}
		}

		System.out.println(StringConstants.ENTER_THE_GENDER_M_FOR_MALE_F_FOR_FEMALE);
		String gender = null;
		boolean validGender = false;
		while (!validGender) {
			try {
				gender = scanner.next();
				if (gender.equals("M") || gender.equals("F") || gender.equals("m") || gender.equals("f")) {
					validGender = true;
				} else {
					System.out.println(StringConstants.INVALID_INPUT_PLEASE_ENTER_A_VALID_GENDER_M_F);
					validGender = false;
				}
			} catch (InputMismatchException e) {
				System.out.println(StringConstants.ENTER_VALID_INPUT);
				scanner.next();
			}
		}
		gender = gender.toUpperCase();

		String rollNo = null;
		if (input.equals(StringConstants.STUDENT)) {
			System.out.println(StringConstants.ENTER_THE_ROLL_NUMBER);
			boolean validRollNo = false;
			while (!validRollNo) {
				try {
					rollNo = scanner.next();
					if (Validator.isValidRollNumber(rollNo)) {
						validRollNo = true;
					} else {
						System.out.println(StringConstants.ENTER_VALID_ROLL_NUMBER);
						validRollNo = false;
					}
				} catch (InputMismatchException e) {
					System.out.println(StringConstants.ENTER_VALID_INPUT);
					scanner.next();
				}
			}
		}

		int mentorOf = 0;
		if (input.equals(StringConstants.FACULTY)) {
			boolean validStandard = false;
			System.out.println(StringConstants.ENTER_THE_MENTOR_OF_FIELD_1_12);
			while (!validStandard) {
				mentorOf = scanner.nextInt();
				try {
					if (mentorOf >= 1 && mentorOf <= 12) {
						validStandard = true;
					} else {
						System.out.println(StringConstants.ENTER_VALID_FIELD_1_12);
						validStandard = false;
					}
				} catch (InputMismatchException e) {
					System.out.println(StringConstants.INVALID_INPUT_PLEASE_ENTER_A_VALID_INTEGER_FOR_MENTOR_OF);
					scanner.next();
				}
			}
		}

		boolean flag = userController.addUser(username, name, age, hashedPassword, email, input, date, contactNumber,
				standard, gender, rollNo, mentorOf);
		if (flag) {
			System.out.println(StringConstants.USER_ADDED_SUCCESSFULLY);
		} else {
			System.out.println(StringConstants.USER_NOT_ADDED);
		}
	}

	public void getUserById() {
		System.out.println(StringConstants.LIST_OF_ALL_USERS);
		List<User> list = userController.getAllUser();
		if (list.isEmpty()) {
			System.out.println(StringConstants.NO_USERS_FOUND3);
			return;
		}
		int index = 0;
		UserPrinter.printUsers(list);
		boolean validateIndex = false;
		int limit = list.size();
		System.out.println(StringConstants.ENTER_THE_USER_INDEX);
		while (!validateIndex) {
			try {
				index = scanner.nextInt();
				if (Validator.isValidIndex(index, limit)) {
					validateIndex = true;
				} else {
					System.out.println(StringConstants.ENTER_VALID_USER_INDEX);
					validateIndex = false;
				}
			} catch (InputMismatchException e) {
				System.out.println(StringConstants.ENTER_VALID_INPUT);
				scanner.next();
			}
		}
		String userId = list.get(index - 1).getUserId();
		User user = userController.getUserById(userId);
		if (user == null) {
			System.out.println(StringConstants.USER_NOT_FOUND);
			return;
		}
		List<User> singleUser = new ArrayList<User>();
		singleUser.add(user);
		UserPrinter.printUsers(singleUser);
	}

	public void getUserByUsername() {
		System.out.println(StringConstants.LIST_OF_ALL_USERS);
		List<User> list = userController.getAllUser();
		if (list.isEmpty()) {
			System.out.println(StringConstants.NO_USERS_FOUND4);
			return;
		}
		UserPrinter.printUsers(list);
		System.out.print(StringConstants.ENTER_USER_USERNAME);
		String username = scanner.next();
		User user = userController.getUserByUsername(username);
		if (user == null) {
			System.out.println(StringConstants.USER_NOT_FOUND);
			return;
		}
		List<User> singleUser = new ArrayList<User>();
		singleUser.add(user);
		UserPrinter.printUsers(singleUser);
	}

	public void getClassDetails() {
		boolean validStandard1 = false;
		int standard = 0;
		while (!validStandard1) {
			System.out.println(StringConstants.ENTER_THE_STANDARD);
			try {
				standard = scanner.nextInt(); // Try to read the integer
				if (standard >= 1 && standard <= 12) {
					validStandard1 = true; // Input is valid
				} else {
					System.out.println(StringConstants.INVALID_INPUT_ENTER_A_NUMBER_BETWEEN_1_AND_12);
				}
			} catch (InputMismatchException e) {
				System.out.println(StringConstants.INVALID_INPUT_PLEASE_ENTER_A_VALID_INTEGER_FOR_STANDARD);
				scanner.next(); // Clear the invalid input
			}
		}

		List<User> users = userController.getClassDetails(standard);
		if (users.isEmpty()) {
			System.out.println(StringConstants.USERS_NOT_FOUND4);
			return;
		}
		UserPrinter.printUsers(users);
	}

	public void deleteUser() {
		List<User> users = userController.getAllUser();
		if (users.isEmpty()) {
			System.out.println(StringConstants.NO_USERS_FOUND);
			return;
		}
		System.out.println(StringConstants.LIST_OF_ALL_USERS);
		int index = 0;
		UserPrinter.printUsers(users);
		System.out.println(StringConstants.ENTER_THE_USER_INDEX);
		boolean validateIndex = false;
		int limit = users.size();
		while (!validateIndex) {
			try {
				index = scanner.nextInt();
				if (Validator.isValidIndex(index, limit)) {
					validateIndex = true;
				} else {
					System.out.println(StringConstants.ENTER_VALID_USER_INDEX);
					validateIndex = false;
				}
			} catch (InputMismatchException e) {
				System.out.println(StringConstants.ENTER_VALID_INPUT);
				scanner.next();
			}
		}
		boolean flag = userController.deleteUser(users.get(index - 1).getUserId());
		if (flag == true) {
			System.out.println(StringConstants.USER_DELETED_SUCCESSFULLY);
		} else {
			System.out.println(StringConstants.USER_DELETION_UNSUCCESSFUL);
		}
	}

	public void getAllUser() {
		System.out.println(StringConstants.LIST_OF_ALL_USERS);
		List<User> list = userController.getAllUser();
		if (list.isEmpty()) {
			System.out.println(StringConstants.NO_USERS_FOUND);
			return;
		}
		UserPrinter.printUsers(list);
	}

	public void logout() {
		App.main(null);
	}

	public void updateUser() {
		System.out.println(StringConstants.LIST_OF_ALL_USERS);
		List<User> list = userController.getAllUser();
		int index = 0;
		if (list.isEmpty()) {
			System.out.println(StringConstants.NO_USERS_FOUND);
			return;
		}
		UserPrinter.printUsers(list);
		boolean validateIndex = false;
		int limit = list.size();
		System.out.println(StringConstants.ENTER_THE_USER_INDEX);
		while (!validateIndex) {
			try {
				index = scanner.nextInt();
				if (Validator.isValidIndex(index, limit)) {
					validateIndex = true;
				} else {
					System.out.println(StringConstants.ENTER_VALID_USER_INDEX);
					validateIndex = false;
				}
			} catch (InputMismatchException e) {
				System.out.println(StringConstants.ENTER_VALID_INPUT);
				scanner.next();
			}
		}
		userController.updateUser(list.get(index - 1).getUserId());
	}

	public void payFees(User user) {
		String userId = user.getUserId();
		feeController.payFees(userId);
	}

	public void checkFees(User user) {
		String userId = user.getUserId();
		feeController.checkFees(userId);
	}

	public void addFees() {
		List<User> users = userController.getAllUser();
		if (users.isEmpty()) {
			System.out.println(StringConstants.NO_USERS_FOUND);
			return;
		}
		int index = 0;
		UserPrinter.printUsers(users);
		System.out.println(StringConstants.ENTER_THE_USER_INDEX);
		boolean validateIndex = false;
		int limit = users.size();
		while (!validateIndex) {
			try {
				index = scanner.nextInt();
				if (Validator.isValidIndex(index, limit)) {
					validateIndex = true;
				} else {
					System.out.println(StringConstants.ENTER_VALID_USER_INDEX);
					validateIndex = false;
				}
			} catch (InputMismatchException e) {
				System.out.println(StringConstants.ENTER_VALID_INPUT);
				scanner.next();
			}
		}
		String userId = users.get(index - 1).getUserId();
		User user = userController.getUserById(userId);
		if (!user.getRole().toString().equals(StringConstants.STUDENT)) {
			System.out.println("Enter valid Student UserId");
			return;
		}
		System.out.println(StringConstants.ENTER_THE_FEE_AMOUNT);
		double feeAmount = 0;
		while (feeAmount <= 0) {
			try {
				feeAmount = scanner.nextDouble();
				if (feeAmount <= 0) {
					System.out.println(StringConstants.ENTER_VALID_FEE_AMOUNT);
				}
			} catch (InputMismatchException e) {
				System.out.println(StringConstants.INVALID_INPUT_PLEASE_ENTER_A_NUMERIC_VALUE);
				scanner.next();
			} catch (DateTimeParseException | InvalidFeeAmountException e) {
				e.printStackTrace();
				throw new InvalidFeeAmountException(StringConstants.FEE_AMOUNT_MUST_BE_GREATER_THAN_ZERO);
			}
		}
		LocalDate date = null;
		LocalDate date1 = LocalDate.of(2050, 12, 31);
		while (date == null) {
			System.out.println(StringConstants.ENTER_THE_DEADLINE_YYYY_MM_DD);
			try {
				boolean validDate = false;
				while (!validDate) {
					String input2 = scanner.next();
					date = LocalDate.parse(input2);
					if (date.isAfter(LocalDate.now()) && date.isBefore(date1)) {
						validDate = true;
					} else {
						validDate = false;
						System.out.println(StringConstants.VALID_DEAD_LINE_DATE);
					}
				}
			} catch (DateTimeParseException e) {
				System.out.println(StringConstants.INVALID_DATE_FORMAT);
			}
		}
		double fineAmount = 0;
		feeController.addFees(userId, feeAmount, date, fineAmount);
	}

	public void calculateFine() {
		List<User> users = userController.getAllUser();
		if (users.isEmpty()) {
			System.out.println(StringConstants.NO_USERS_FOUND5);
			return;
		}
		int index = 0;
		UserPrinter.printUsers(users);
		System.out.println(StringConstants.ENTER_THE_USER_INDEX);
		boolean validateIndex = false;
		int limit = users.size();
		while (!validateIndex) {
			try {
				index = scanner.nextInt();
				if (Validator.isValidIndex(index, limit)) {
					validateIndex = true;
				} else {
					System.out.println(StringConstants.ENTER_VALID_USER_INDEX);
					validateIndex = false;
				}
			} catch (InputMismatchException e) {
				System.out.println(StringConstants.ENTER_VALID_INPUT);
				scanner.next();
			}
		}
		String userId = users.get(index - 1).getUserId();
		String role = users.get(index - 1).getRole().toString();
		if (role.equals(StringConstants.ADMIN) || role.equals(StringConstants.FACULTY)) {
			System.out.println(StringConstants.ENTER_VALID_ID);
			return;
		}
		feeController.calculateFine(userId);
	}

	public void checkFine(User user) {
		String userId = user.getUserId();
		feeController.calculateFine(userId);
	}

	public void getAllCourses() {
		List<Course> list = courseController.getAllCourses();
		if (list.isEmpty()) {
			System.out.println(StringConstants.NO_COURSES_FOUND);
			return;
		}
		CoursePrinter.printCourseDetails(list);
	}

	public void updateCourse() {
		List<Course> course = courseController.getAllCourses();
		if (course.isEmpty()) {
			System.out.println(StringConstants.NO_COURSES_FOUND);
			return;
		}
		int index = 0;
		CoursePrinter.printCourseDetails(course);
		System.out.println(StringConstants.ENTER_THE_USER_INDEX);
		boolean validateIndex = false;
		int limit = course.size();
		while (!validateIndex) {
			try {
				index = scanner.nextInt();
				if (Validator.isValidIndex(index, limit)) {
					validateIndex = true;
				} else {
					System.out.println(StringConstants.ENTER_VALID_USER_INDEX);
					validateIndex = false;
				}
			} catch (InputMismatchException e) {
				System.out.println(StringConstants.ENTER_VALID_INPUT);
				scanner.next();
			}
		}
		String courseId = course.get(index - 1).getCourseId();
		courseController.updateCourse(courseId);
	}

	public void deleteCourse() {
		List<Course> list = courseController.getAllCourses();
		if (list.isEmpty()) {
			System.out.println(StringConstants.NO_COURSES_FOUND);
			return;
		}
		int index = 0;
		CoursePrinter.printCourseDetails(list);
		System.out.println(StringConstants.ENTER_THE_USER_INDEX);
		boolean validateIndex = false;
		int limit = list.size();
		while (!validateIndex) {
			try {
				index = scanner.nextInt();
				if (Validator.isValidIndex(index, limit)) {
					validateIndex = true;
				} else {
					System.out.println(StringConstants.ENTER_VALID_USER_INDEX);
					validateIndex = false;
				}
			} catch (InputMismatchException e) {
				System.out.println(StringConstants.ENTER_VALID_INPUT);
				scanner.next();
			}
		}
		String courseId = list.get(index - 1).getCourseId();
		boolean flag = courseController.deleteCourse(courseId);
		if (flag == true) {
			System.out.println(StringConstants.COURSE_DELETED_SUCCESSFULLY);
		} else {
			System.out.println(StringConstants.COURSE_DELETION_UNSUCCESSFUL);
		}
	}

	public void addCourse() {
		System.out.println(StringConstants.ENTER_COURSE_NAME);
		String courseName = "";
		while (true) {
			try {
				courseName = scanner.nextLine();
				// scanner.nextLine();
				courseName.trim();
				if (courseName.matches("^[a-zA-Z\\s]+$") && !courseName.isEmpty()) {
					break;
				} else {
					System.out.println(StringConstants.INVALID_INPUT);
				}
			} catch (InputMismatchException e) {
				System.out.println(StringConstants.INVALID_INPUT);
				scanner.next();
			}
		}
		boolean validStandard = false;
		System.out.println(StringConstants.ENTER_THE_STANDARD_1_12);
		int standard = scanner.nextInt();
		while (!validStandard) {
			try {
				if (standard >= 1 && standard <= 12) {
					validStandard = true;
				} else {
					System.out.println(StringConstants.ENTER_VALID_STANDARD_1_12);
					standard = scanner.nextInt();
					validStandard = false;
				}
			} catch (InputMismatchException e) {
				System.out.println(StringConstants.INVALID_INPUT_PLEASE_ENTER_A_VALID_INTEGER_FOR_STANDARD2);
				scanner.next();
			}
		}
		courseController.addCourse(courseName, standard);
	}

	public void getCourse() {
		List<Course> list = courseController.getAllCourses();
		if (list.isEmpty()) {
			System.out.println(StringConstants.NO_COURSES_FOUND);
			return;
		}
		int index = 0;
		CoursePrinter.printCourseDetails(list);
		System.out.println(StringConstants.ENTER_THE_COURSE_INDEX);
		boolean validateIndex = false;
		int limit = list.size();
		while (!validateIndex) {
			try {
				index = scanner.nextInt();
				if (Validator.isValidIndex(index, limit)) {
					validateIndex = true;
				} else {
					System.out.println(StringConstants.ENTER_VALID_COURSE_INDEX);
					validateIndex = false;
				}
			} catch (InputMismatchException e) {
				System.out.println(StringConstants.ENTER_VALID_INPUT);
				scanner.next();
			}
		}
		String courseId = list.get(index - 1).getCourseId();
		Course course = courseController.getCourse(courseId);
		List<Course> singleCourse = new ArrayList<Course>();
		singleCourse.add(course);
		CoursePrinter.printCourseDetails(singleCourse);
	}

	public void addAttendance() {
		List<User> list = userController.getAllUser();
		if (list.isEmpty()) {
			System.out.println(StringConstants.NO_USERS_FOUND2);
			return;
		}
		int index = 0;
		UserPrinter.printUsers(list);
		System.out.println(StringConstants.ENTER_THE_USER_INDEX);
		boolean validateIndex = false;
		int limit = list.size();
		while (!validateIndex) {
			try {
				index = scanner.nextInt();
				if (Validator.isValidIndex(index, limit)) {
					validateIndex = true;
				} else {
					System.out.println(StringConstants.ENTER_VALID_USER_INDEX);
					validateIndex = false;
				}
			} catch (InputMismatchException e) {
				System.out.println(StringConstants.ENTER_VALID_INPUT);
				scanner.next();
			}
		}
		String studentId = list.get(index - 1).getUserId();
		String role = list.get(index - 1).getRole().toString();
		if (role.equals(StringConstants.ADMIN) || role.equals(StringConstants.FACULTY)) {
			System.out.println(StringConstants.ENTER_VALID_ID);
			return;
		}
		int standard = list.get(index - 1).getStandard();

		LocalDate date = null;
		boolean validateDate = false;
		while (!validateDate) {
			System.out.println(StringConstants.ENTER_THE_DATE_YYYY_MM_DD);
			try {
				String dateString = scanner.next();
				date = LocalDate.parse(dateString);
				if (date.isBefore(LocalDate.now())) {
					validateDate = true;
				} else {
					System.out.println("Enter valid date");
					validateDate = false;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		System.out.println(StringConstants.ENTER_STATUS_P_FOR_PRESENT_A_FOR_ABSENT);
		boolean validateStatus = false;
		String statusInput = null;
		Status status = null;
		while (!validateStatus) {
			statusInput = scanner.next();
			if (statusInput.equals("P") || statusInput.equals("A") || statusInput.equals("p")
					|| statusInput.equals("a")) {
				status = Status.valueOf(statusInput);
				validateStatus = true;
			} else {
				System.out.println("Enter valid Status");
				validateStatus = false;
			}
		}
		boolean flag = attendanceController.addAttendance(studentId, standard, date, status);
		if (flag) {
			System.out.println(StringConstants.ATTENDANCE_ADDED_SUCCESSFULLY);
		} else {
			System.out.println(StringConstants.ATTENDANCE_NOT_ADDED);
		}
	}

	public void viewAttendanceByStandard() {
		boolean validStandard = false;
		int standard = 0;
		while (!validStandard) {
			System.out.println(StringConstants.ENTER_THE_STANDARD);
			try {
				standard = scanner.nextInt(); // Try to read the integer
				if (standard >= 1 && standard <= 12) {
					validStandard = true; // Input is valid
				} else {
					System.out.println(StringConstants.INVALID_INPUT_ENTER_A_NUMBER_BETWEEN_1_AND_12);
				}
			} catch (InputMismatchException e) {
				System.out.println(StringConstants.INVALID_INPUT_PLEASE_ENTER_A_VALID_INTEGER_FOR_STANDARD);
				scanner.next(); // Clear the invalid input
			}
		}
		List<Attendance> list = attendanceController.viewAttendanceByStandard(standard);
		if (list.isEmpty()) {
			System.out.println(StringConstants.NO_ATTENDANCE_RECORD_FOUND);
			return;
		}
		int index = 1;
		AttendancePrinter.extractedHeader();
		for (Attendance user : list) {
			List<Attendance> userList = new ArrayList<Attendance>();
			String studentId = user.getStudentId();
			User userr = userController.getUserById(studentId);
			userList.add(user);
			String name = userr.getName();
			AttendancePrinter.printAttendanceDetails1(userList, name, index);
			index++;
		}
		AttendancePrinter.extractedFooter();
	}

	public void viewAttendanceById() {
		List<User> users = userController.getAllUser();
		if (users.isEmpty()) {
			System.out.println(StringConstants.NO_USERS_FOUND);
			return;
		}
		System.out.println(StringConstants.LIST_OF_ALL_USERS);
		int index = 0;
		UserPrinter.printUsers(users);
		System.out.println(StringConstants.ENTER_THE_USER_INDEX);
		boolean validateIndex = false;
		int limit = users.size();
		while (!validateIndex) {
			try {
				index = scanner.nextInt();
				if (Validator.isValidIndex(index, limit)) {
					validateIndex = true;
				} else {
					System.out.println(StringConstants.ENTER_VALID_USER_INDEX);
					validateIndex = false;
				}
			} catch (InputMismatchException e) {
				System.out.println(StringConstants.ENTER_VALID_INPUT);
				scanner.next();
			}
		}
		String userId = users.get(index - 1).getUserId();
		String role = users.get(index - 1).getRole().toString();
		if (role.equals(StringConstants.ADMIN) || role.equals(StringConstants.FACULTY)) {
			System.out.println(StringConstants.ENTER_VALID_ID);
			return;
		}
		List<Attendance> list = attendanceController.viewAttendanceById(userId);
		if (list.isEmpty()) {
			System.out.println(StringConstants.NO_ATTENDANCE_RECORDS_AVAILABLE);
		}
		AttendancePrinter.printAttendanceDetails(list);
	}

	public void approveLeave(String role) {
		List<Leaves> leaves = leavesController.viewAllLeave();
		if (leaves.isEmpty()) {
			System.out.println(StringConstants.NO_LEAVES_FOUND);
			return;
		}
		int index = 1;

		// LeavesPrinter.printLeaves(leaves);
		LeavesPrinter.extractedHeader();
		for (Leaves leave : leaves) {
			List<Leaves> leaveList = new ArrayList<Leaves>();
			String userId = leave.getUserId();
			User user = userController.getUserById(userId);
			String name = user.getName();
			String username = user.getUsername();
			leaveList.add(leave);
			LeavesPrinter.printLeaves1(leaveList, name, username, index);
			index++;
		}
		LeavesPrinter.extractedFooter();

		System.out.println(StringConstants.ENTER_THE_LEAVE_INDEX);
		boolean validateIndex = false;
		int limit = leaves.size();
		while (!validateIndex) {
			try {
				index = scanner.nextInt();
				if (Validator.isValidIndex(index, limit)) {
					validateIndex = true;
				} else {
					System.out.println(StringConstants.ENTER_VALID_LEAVE_INDEX);
					validateIndex = false;
				}
			} catch (InputMismatchException e) {
				System.out.println(StringConstants.ENTER_VALID_INPUT);
				scanner.next();
			}
		}
		String userId = leaves.get(index - 1).getUserId();
		String Status = leaves.get(index - 1).getStatus().toString();
		if (Status.equals(StringConstants.APPROVED)) {
			System.out.println(StringConstants.LEAVE_ALREADY_APPROVED);
			return;
		}
		leavesController.approveLeave(userId);
	}

	public void rejectLeave(String role) {
		List<Leaves> leaves = leavesController.viewAllLeave();
		if (leaves.isEmpty()) {
			System.out.println(StringConstants.NO_LEAVES_FOUND);
			return;
		}
		int index = 1;
//		LeavesPrinter.printLeaves(leaves);
		LeavesPrinter.extractedHeader();
		for (Leaves leave : leaves) {
			List<Leaves> leaveList = new ArrayList<Leaves>();
			String userId = leave.getUserId();
			User user = userController.getUserById(userId);
			String name = user.getName();
			String username = user.getUsername();
			leaveList.add(leave);
			LeavesPrinter.printLeaves1(leaveList, name, username, index);
			index++;
		}
		LeavesPrinter.extractedFooter();
		System.out.println(StringConstants.ENTER_THE_LEAVE_INDEX);
		boolean validateIndex = false;
		int limit = leaves.size();
		while (!validateIndex) {
			index = scanner.nextInt();
			if (Validator.isValidIndex(index, limit)) {
				validateIndex = true;
			} else {
				System.out.println(StringConstants.ENTER_VALID_LEAVE_INDEX);
				validateIndex = false;
			}
		}
		String userId = leaves.get(index - 1).getUserId();
		String Status = leaves.get(index - 1).getStatus().toString();
		if (Status.equals(StringConstants.REJECTED)) {
			System.out.println(StringConstants.LEAVE_ALREADY_REJECTED);
			return;
		}
		leavesController.rejectLeave(userId);
	}

	public void applyLeave(User user) {
		String userId = user.getUserId();
		System.out.println(StringConstants.ENTER_LEAVE_CONTENT);
		String content = null;
		while (true) {
			try {
				content = scanner.nextLine();
				if (content.matches(".*[a-zA-Z].*")) {
					break;
				} else {
					System.out.println(StringConstants.INVALID_INPUT);
				}
			} catch (InputMismatchException e) {
				System.out.println(StringConstants.INVALID_INPUT + "Exception");
				scanner.next();
			}
		}

		String input = StringConstants.PENDING;
		LeavesStatus status = LeavesStatus.valueOf(input);
		String randomString = UUID.randomUUID().toString();
		int desiredLength = 7;
		if (desiredLength > randomString.length()) {
			desiredLength = randomString.length();
		}
		String leaveId = randomString.substring(0, desiredLength);
		leaveId = 'L' + leaveId;

		LocalDate startDate = null;
		System.out.println(StringConstants.ENTER_THE_LEAVE_START_DATE_YYYY_MM_DD);
		while (startDate == null) {
			try {
				boolean validDate = false;
				while (!validDate) {
					String input2 = scanner.next();
					startDate = LocalDate.parse(input2);
					if (startDate.isAfter(LocalDate.now())) {
						validDate = true;
					} else {
						validDate = false;
						System.out.println(StringConstants.PLEASE_ENTER_VALID_DATE);
					}
				}
			} catch (DateTimeParseException e) {
				System.out.println(StringConstants.INVALID_DATE_FORMAT);
			}
		}

		LocalDate endDate = Validator.ValidateDate(startDate);
		Leaves leave = new Leaves(leaveId, userId, content, startDate, endDate, status);
		leavesController.applyLeave(leave);
	}

	public void viewAllLeave() {
		List<Leaves> leaves = leavesController.viewAllLeave();
		if (leaves.isEmpty()) {
			System.out.println(StringConstants.NO_LEAVES_FOUND);
			return;
		}
		// LeavesPrinter.printLeaves(leaves);
		int index = 1;
		LeavesPrinter.extractedHeader();
		for (Leaves leave : leaves) {
			List<Leaves> leaveList = new ArrayList<Leaves>();
			String userId = leave.getUserId();
			User user = userController.getUserById(userId);
			String name = user.getName();
			String username = user.getUsername();
			leaveList.add(leave);
			LeavesPrinter.printLeaves1(leaveList, name, username, index);
			index++;
		}
		LeavesPrinter.extractedFooter();
	}

	public void checkLeaveStatus(User user) {
		String userId = user.getUserId();
		List<Leaves> leaves = leavesController.checkLeaveStatus(userId);
		if (leaves.isEmpty()) {
			System.out.println(StringConstants.FIRST_APPLY_FOR_LEAVE);
			return;
		}
		// LeavesPrinter.printLeaves(leaves);
		int index = 1;
		LeavesPrinter.extractedHeader();
		for (Leaves leave : leaves) {
			List<Leaves> leaveList = new ArrayList<Leaves>();
			String userId1 = leave.getUserId();
			User user1 = userController.getUserById(userId1);
			String name = user1.getName();
			String username = user1.getUsername();
			leaveList.add(leave);
			LeavesPrinter.printLeaves1(leaveList, name, username, index);
			index++;
		}
		LeavesPrinter.extractedFooter();
	}

	public void readNotifications(User user) {
		String userId = user.getUserId();
		List<Notification> notifications = notificationController.readNotifications(userId);
		if (notifications.isEmpty()) {
			System.out.println(StringConstants.NO_NOTIFICATIONS_FOUND);
			return;
		}
		NotificationPrinter.printNotifications(notifications);
	}

	public void sendNotification() {
		try {
			List<User> users = userController.getAllUser();
			System.out.println(StringConstants.LIST_OF_ALL_USERS);
			int index = 0;
			UserPrinter.printUsers(users);

			System.out.println(StringConstants.ENTER_THE_USER_INDEX2);
			boolean validateIndex = false;
			int limit = users.size();
			while (!validateIndex) {
				index = scanner.nextInt();
				if (Validator.isValidIndex(index, limit)) {
					validateIndex = true;
				} else {
					System.out.println(StringConstants.ENTER_VALID_USER_INDEX);
					validateIndex = false;
				}
			}
			String userId = users.get(index - 1).getUserId();
			System.out.println(StringConstants.ENTER_TITLE);
			String type = null;// scanner.next();
			scanner.nextLine();
			while (true) {
				try {
					type = scanner.nextLine();
					// Check if the title is alphabetic only
					if (type.matches("[a-zA-Z]+")) {
						break; // Exit the loop if valid
					} else {
						System.out.println(StringConstants.INVALID_INPUT);
					}
				} catch (InputMismatchException e) {
					System.out.println(StringConstants.INVALID_INPUT);
					scanner.next();
				}
			}
			System.out.println(StringConstants.ENTER_MESSAGE);
			String description = null;// scanner.nextLine();
			while (true) {
				try {
					description = scanner.nextLine();
					// Check if the title is alphabetic only
					if (description.matches("[a-zA-Z0-9]+")) {
						break; // Exit the loop if valid
					} else {
						System.out.println(StringConstants.INVALID_INPUT);
					}
				} catch (InputMismatchException e) {
					System.out.println(StringConstants.INVALID_INPUT);
					scanner.next();
				}
			}
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
				System.out.println(StringConstants.NOTIFICATION_SENT_SUCCESSFULLY);
			} else {
				System.out.println(StringConstants.NOTIFICATION_NOT_SENT);
			}
		} catch (Exception e) {
			System.out.println("An error occurred: " + e.getMessage());
			// e.printStackTrace();
		}
	}

	public void raiseIssue(User user) {
		String userId = user.getUserId();
		System.out.println(StringConstants.ENTER_ISSUE_CONTENT);
		String message = null;
		while (true) {
			try {
				message = scanner.nextLine();
				if (message.matches(".*[a-zA-Z].*")) {
					break;
				} else {
					System.out.println(StringConstants.INVALID_INPUT);
				}
			} catch (InputMismatchException e) {
				System.out.println(StringConstants.INVALID_INPUT);
				scanner.next();
			}
		}

		String input = StringConstants.PENDING2;
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
		if (issue.isEmpty()) {
			System.out.println(StringConstants.NO_ISSUES_FOUND);
			return;
		}
		int index = 1;
		IssuePrinter.extractedHeader();
		for (Issue i : issue) {
			List<Issue> issueList = new ArrayList<Issue>();
			String userId = i.getUserId();
			User user = userController.getUserById(userId);
			String name = user.getName();
			String username = user.getUsername();
			issueList.add(i);
			IssuePrinter.printIssues1(issueList, name, username, index);
			index++;
		}
		IssuePrinter.extractedFooter();
		System.out.println("Enter the Issue index");
		boolean validateIndex = false;
		int limit = issue.size();
		while (!validateIndex) {
			try {
				index = scanner.nextInt();
				if (Validator.isValidIndex(index, limit)) {
					validateIndex = true;
				} else {
					System.out.println("Enter valid Issue Index:");
					validateIndex = false;
				}
			} catch (InputMismatchException e) {
				System.out.println("Enter valid Issue Index:");
				scanner.next();
			}
		}

		String userId = issue.get(index - 1).getUserId();
		String Status = issue.get(index - 1).getStatus().toString();
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
			System.out.println(StringConstants.NO_ISSUES_FOUND);
			return;
		}
		IssuePrinter.printIssues(issue);
	}

	public void viewAllIssues() {
		List<Issue> issue = issueController.viewAllIssues();
		if (issue.isEmpty()) {
			System.out.println(StringConstants.NO_ISSUES_FOUND);
			return;
		}
		int index = 1;
		IssuePrinter.extractedHeader();
		for (Issue i : issue) {
			List<Issue> issueList = new ArrayList<Issue>();
			String userId = i.getUserId();
			User user = userController.getUserById(userId);
			String name = user.getName();
			String username = user.getUsername();
			issueList.add(i);
			IssuePrinter.printIssues1(issueList, name, username, index);
			index++;
		}
		IssuePrinter.extractedFooter();
	}

	public void checkMarks(User user) {
		String userId = user.getUserId();
		List<CourseMarks> coursesMarks = courseMarksController.checkMarks(userId);
		if (coursesMarks.isEmpty()) {
			System.out.println(StringConstants.NO_MARKS_ADDED);
			return;
		}
		int index = 1;
		CourseMarksPrinter.extractedHeader();
		for (CourseMarks course : coursesMarks) {
			List<CourseMarks> userList = new ArrayList<CourseMarks>();
			Course course1 = courseController.getCourse(course.getCourseId());
			userList.add(course);
			String courseName = course1.getCourseName();
			CourseMarksPrinter.printCourseMarksDetails(userList, courseName, index);
			index++;
		}
		CourseMarksPrinter.extractedFooter();
	}

	public void addMarks() {
		boolean validateUser = false;
		String userId = "";
		String courseId = "";
		ArrayList<User> storeUser = new ArrayList<User>();
		User user1 = null;
		while (!validateUser) {
			List<User> users = userController.getAllUser();
			System.out.println(StringConstants.LIST_OF_ALL_USERS);
			int index = 0;
			UserPrinter.printUsers(users);
			System.out.println(StringConstants.ENTER_THE_USER_INDEX);
			boolean validateIndex = false;
			int limit = users.size();
			while (!validateIndex) {
				index = scanner.nextInt();
				if (Validator.isValidIndex(index, limit)) {
					validateIndex = true;
				} else {
					System.out.println(StringConstants.ENTER_VALID_USER_INDEX);
					validateIndex = false;
				}
			}
			user1 = users.get(index - 1);
			userId = user1.getUserId();

			if (user1.getRole().toString().equals(StringConstants.ADMIN)
					|| user1.getRole().toString().equals(StringConstants.FACULTY)) {
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
			if (filteredCourses.isEmpty()) {
				System.out.println(StringConstants.NO_COURSE);
				return;
			}
			int index = 0;
			if (storeUser.isEmpty()) {
				System.out.println(StringConstants.NO_COURSE);
				return;
			} else {
				CoursePrinter.printCourseDetails(filteredCourses);
			}
			boolean validateIndex = false;
			int limit = filteredCourses.size();
			System.out.println(StringConstants.ENTER_THE_COURSE_INDEX);
			while (!validateIndex) {
				try {
					index = scanner.nextInt();
					if (Validator.isValidIndex(index, limit)) {
						validateIndex = true;
					} else {
						System.out.println(StringConstants.ENTER_VALID_COURSE_INDEX);
						validateIndex = false;
					}
				} catch (InputMismatchException e) {
					System.out.println(StringConstants.ENTER_VALID_COURSE_INDEX);
					scanner.next();
				}
			}
			courseId = filteredCourses.get(index - 1).getCourseId();
			Course course = courseController.getCourse(courseId);
			if (course == null) {
				System.out.println(StringConstants.ENTER_VALID_COURSE_ID);
				validateUser = false;
			} else {
				validateUser = true;
			}
		}
		System.out.println(StringConstants.ENTER_MARKS_0_100);
		boolean validMarks = false;
		double marks = 0;
		while (!validMarks) {
			try {
				marks = scanner.nextDouble();
				if (marks >= 0 && marks <= 100) {
					validMarks = true;
				} else {
					System.out.println(StringConstants.ENTER_VALID_MARKS_0_100);
					validMarks = false;
				}
			} catch (InputMismatchException e) {
				System.out.println(StringConstants.INVALID_INPUT_FOR_MARKS_TYPE);
				scanner.next();
			}
		}
		int standard = user1.getStandard();
		courseMarksController.addMarks(userId, courseId, marks, standard);
	}

	public void generateMarksheet() {
		boolean validateUser1 = false;
		String userId1 = "";
		ArrayList<User> storeUser1 = new ArrayList<User>();
		User user11 = null;
		while (!validateUser1) {
			List<User> users = userController.getAllUser();
			System.out.println(StringConstants.LIST_OF_ALL_USERS);
			int index = 0;
			UserPrinter.printUsers(users);
			boolean validateIndex = false;
			int limit = users.size();
			System.out.println(StringConstants.ENTER_THE_USER_INDEX);
			while (!validateIndex) {
				try {
					index = scanner.nextInt();
					if (Validator.isValidIndex(index, limit)) {
						validateIndex = true;
					} else {
						System.out.println(StringConstants.ENTER_VALID_USER_INDEX);
						validateIndex = false;
					}
				} catch (InputMismatchException e) {
					System.out.println(StringConstants.ENTER_VALID_USER_INDEX);
					scanner.next();
				}
			}
			user11 = users.get(index - 1);
			userId1 = user11.getUserId();
			if (user11.getRole().toString().equals(StringConstants.ADMIN)
					|| user11.getRole().toString().equals(StringConstants.FACULTY)) {
				System.out.println("Marksheets can only be added to Student, Enter Student UserId");
				validateUser1 = false;
			} else {
				storeUser1.add(user11);
				validateUser1 = true;
			}
		}

		List<CourseMarks> list = courseMarksController.checkMarks(userId1);
		if (list.isEmpty()) {
			System.out.println(StringConstants.ADD_MARKS_FIRST_TO_GENERATE_MARKSHEET);
			return;
		}
		double Totalmarks = 0;
		for (CourseMarks course : list) {
			Totalmarks += course.getMarks();
		}
		int noOfCourses = list.size();
		double percentage = Totalmarks / noOfCourses;
		System.out.println(StringConstants.TOTAL_MARKS + Totalmarks);
		System.out.println(StringConstants.PERCENTAGE2 + percentage);
		if (percentage > 45) {
			System.out.println(StringConstants.RESULT_PASS);
		} else {
			System.out.println(StringConstants.RESULT_FAIL);
		}
	}

	public void viewMarksheet(User user) {
		String userId = user.getUserId();
		List<CourseMarks> list = courseMarksController.checkMarks(userId);
		if (list.isEmpty()) {
			System.out.println(StringConstants.NO_MARKS_ADDED);
			return;
		}
		int index = 1;
		final String ROW_FORMAT = "%5d | %-15s | %5s";
		MarksheetPrinter.extractedHeader();
		for (CourseMarks c : list) {
			Course course = courseController.getCourse(c.getCourseId());
			System.out.printf(ROW_FORMAT, index, course.getCourseName(), c.getMarks());
			System.out.println();
			index++;
		}
		MarksheetPrinter.extractedFooter();
		double Totalmarks = 0;
		for (CourseMarks course : list) {
			Totalmarks += course.getMarks();
		}
		int noOfCourses = list.size();
		double percentage = Totalmarks / noOfCourses;
		System.out.println(StringConstants.TOTAL_MARKS + Totalmarks);
		System.out.println(StringConstants.PERCENTAGE2 + percentage);
		if (percentage > 45) {
			System.out.println(StringConstants.RESULT_PASS);
		} else {
			System.out.println(StringConstants.RESULT_FAIL);
		}
	}
}