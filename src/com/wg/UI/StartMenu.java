package com.wg.UI;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

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
		System.out.println(" ");
		System.out.println(StringConstants.Welcome);
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

	public void login1() {
		// Create a JFrame to contain the login dialog
		JFrame frame = new JFrame("Login");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(300, 150);
		frame.setLocationRelativeTo(null);

		// Create a JPanel to hold the input components
		JPanel panel = new JPanel(new GridLayout(3, 2));

		// Add Username label and field
		JLabel userLabel = new JLabel("Username:");
		JTextField userField = new JTextField();
		panel.add(userLabel);
		panel.add(userField);

		// Add Password label and field
		JLabel passLabel = new JLabel("Password:");
		JPasswordField passField = new JPasswordField();
		panel.add(passLabel);
		panel.add(passField);

		// Add Login button
		JButton loginButton = new JButton("Login");
		panel.add(loginButton);

		// Set up the action listener for the login button
		loginButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String username = userField.getText();
				String password = new String(passField.getPassword());

				User user = userController.authenticateUser(username, password);
				if (user == null) {
					JOptionPane.showMessageDialog(frame, "Enter valid credentials!", "Login Error",
							JOptionPane.ERROR_MESSAGE);
				} else {
					frame.dispose(); // Close the login frame
					UserUI userUI = new UserUI(userController, feeController, courseController, attendanceController,
							notificationController, leavesController, issueController, courseMarksController);
					userUI.displayMenu(user);
				}
			}
		});

		frame.add(panel);
		frame.setVisible(true);
	}
}