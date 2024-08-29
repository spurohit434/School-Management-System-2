package com.wg.UI;

import java.sql.SQLException;
import java.util.Scanner;

import javax.swing.JOptionPane;
import javax.swing.JPasswordField;

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
		while (true) {
			System.out.println(StringConstants.Welcome);
			System.out.println("Enter username: ");
			String username = scanner.next();

			JPasswordField passwordField = new JPasswordField(20);
			JOptionPane.showConfirmDialog(null, passwordField, "Enter your password", JOptionPane.OK_CANCEL_OPTION,
					JOptionPane.PLAIN_MESSAGE);
			char[] passwordChars = passwordField.getPassword();
			String password = new String(passwordChars);

			User user = userController.authenticateUser(username, password);
			if (user == null) {
				System.out.println("Invalid credentials");
			} else {
				System.out.println("User Authenticated Successfully");
				UserUI userUI = new UserUI(userController, feeController, courseController, attendanceController,
						notificationController, leavesController, issueController, courseMarksController);
				userUI.displayMenu(user);
			}
		}
	}

	public void login() {
		System.out.println("Enter username: ");
		String username = scanner.next();
		System.out.println("Enter password: ");
		String password = scanner.next();

		User user = userController.authenticateUser(username, password);
		if (user == null) {
			System.out.println("Enter valid Credentials!");
			try {
				App.main(null);
				return;
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

	public void login1() {
		System.out.print("Enter username: ");
		String username = scanner.next();

		JPasswordField passwordField = new JPasswordField(20);
		JOptionPane.showConfirmDialog(null, passwordField, "Enter your password", JOptionPane.OK_CANCEL_OPTION,
				JOptionPane.PLAIN_MESSAGE);
		char[] passwordChars = passwordField.getPassword();
		String password = new String(passwordChars);

		User user = userController.authenticateUser(username, password);
		if (user != null) {
			System.out.println("Login successful.");
			UserUI userUI = new UserUI(userController, feeController, courseController, attendanceController,
					notificationController, leavesController, issueController, courseMarksController);
			userUI.displayMenu(user);
		} else {
			System.out.println("Invalid username or password.");
			return;
		}
	}
}