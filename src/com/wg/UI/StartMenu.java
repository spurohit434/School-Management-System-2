package com.wg.UI;

import java.util.Scanner;

import javax.swing.JOptionPane;
import javax.swing.JPasswordField;

import com.wg.Constants.StringConstants;
import com.wg.Helper.Validator;
import com.wg.Model.User;

public class StartMenu {
	private final Scanner scanner = new Scanner(System.in);
	UserUI userUI = new UserUI();

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

			User user = userUI.authenticateUser(username, password);
			if (user == null) {
				System.out.println("Invalid credentials");
				System.out.println(" ");
			} else {
				displayMenu(user);
			}
		}
	}

	public void login() {
		while (true) {
			System.out.println(StringConstants.Welcome);
			System.out.println("Enter username: ");
			String username = scanner.next();
			System.out.println("Enter password: ");
			String password = scanner.next();

			User user = userUI.authenticateUser(username, password);
			if (user == null) {
				System.out.println("Invalid credentials");
				System.out.println(" ");
			} else {
				displayMenu(user);
			}
		}
	}

	public void displayMenu(User user) {
		String role = user.getRole().toString();
		if (role.equals("Admin")) {
			while (true) {
				System.out.println(StringConstants.ADMIN_MENU);
				System.out.println("Enter your choice: ");
				int choice = Validator.getUserChoice();
				// scanner.nextLine();
				switch (choice) {
				case 1:
					userUI.manageUser();
					break;
				case 2:
					userUI.getClassDetails();
					break;
				case 3:
					userUI.sendNotification();
					break;
				case 4:
					userUI.manageFees();
					break;
				case 5:
					userUI.manageLeaves(role);
					break;
				case 6:
					userUI.manageIssues();
					break;
				case 7:
					userUI.manageCourse();
					break;
				case 8:
					userUI.manageAttendance();
					break;
				case 9:
					userUI.logout();
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
				// int choice = scanner.nextInt();
				int choice = Validator.getUserChoice();
				// scanner.nextLine();
				switch (choice) {
				case 1:
					userUI.manageLeavesStudent(user);
					break;
				case 2:
					userUI.manageIssueStudent(user);
					break;
				case 3:
					userUI.manageFeesStudent(user);
					break;
				case 4:
					userUI.checkMarks(user);
					break;
				case 5:
					userUI.readNotifications(user);
					break;
				case 6:
					userUI.viewAttendance(user);
					break;
				case 7:
					userUI.viewMarksheet(user);
					break;
				case 8:
					userUI.logout();
					break;
				case 9:
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
				int choice = Validator.getUserChoice();
				// scanner.nextLine();
				switch (choice) {
				case 1:
					userUI.manageAttendance();
					break;
				case 2:
					userUI.viewAllLeave();
					break;
				case 3:
					userUI.applyLeave(user);
					break;
				case 4:
					userUI.approveLeave(role);
					break;
				case 5:
					userUI.checkLeaveStatus(user);
					break;
				case 6:
					userUI.raiseIssue(user);
					break;
				case 7:
					userUI.checkIssueStatus(user);
					break;
				case 8:
					userUI.addMarks();
					break;
				case 9:
					userUI.generateMarksheet();
					break;
				case 10:
					userUI.readNotifications(user);
					break;
				case 11:
					userUI.logout();
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

}