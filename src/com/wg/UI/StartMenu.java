package com.wg.UI;

import java.sql.SQLException;
import java.util.Scanner;

import com.App.App;
import com.wg.Controller.AttendanceController;
import com.wg.Controller.CourseController;
import com.wg.Controller.CourseMarksController;
import com.wg.Controller.FeeController;
import com.wg.Controller.IssueController;
import com.wg.Controller.LeavesController;
import com.wg.Controller.NotificationController;
import com.wg.Controller.UserController;
import com.wg.Model.User;

public class StartMenu {
	Scanner scanner = new Scanner(System.in);
	FeeController feeController = new FeeController();
	UserController userController = new UserController();
	CourseController courseController = new CourseController();
	AttendanceController attendanceController = new AttendanceController();
	NotificationController notificationController = new NotificationController();
	LeavesController leavesController = new LeavesController();
	IssueController issueController = new IssueController();
	CourseMarksController courseMarksController = new CourseMarksController();

	public StartMenu(UserController controller, FeeController feeController, CourseController courseController,
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

	public StartMenu() {
	}

	public void showStartMenu() {
		System.out.println(" ");
		System.out.println("======  Welcome to School Management System  ======");
		System.out.println("1. Login to system");
		System.out.println("2. Exit");
		System.out.println(" ");

		System.out.println("Enter your choice: ");

		int choice = scanner.nextInt();
		switch (choice) {
		case 1:
			login();
			break;
		case 2:
			System.exit(0);
			break;
		default:
			System.out.println("Enter Valid Choice");
			return;
		}
	}

	public void login() {
		System.out.println("Enter username: ");
		String username = scanner.next();
		System.out.println("Enter password: ");
		// String password = SecureInput.getSecureInput("password");
		String password = scanner.next();

		User user = userController.authenticateUser(username, password);
		if (user == null) {
			System.out.println("Enter valid Credentials!");
			try {
				App.main(null);
			} catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			}
			return;
		} else {
			UserUI userUI = new UserUI(userController, feeController, courseController, attendanceController,
					notificationController, leavesController, issueController, courseMarksController);
			userUI.displayMenu(user);
		}
	}
}