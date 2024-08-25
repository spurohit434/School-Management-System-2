package com.wg.UI;

import java.util.Scanner;

import com.wg.Controller.UserController;
import com.wg.DAO.UserDAO;

public class StartMenu {
	Scanner scanner = new Scanner(System.in);
	UserDAO userDAO = new UserDAO();
	// UserService userService = new UserService(userDAO);
	// UserService userService = new UserService();
	UserController userController = new UserController();

	public StartMenu(UserController controller) {
		this.userController = controller;
	}

	public StartMenu() {
	}

	public void showStartMenu() {
		System.out.println("School Management System");
		System.out.println("Enter the Role of the User to Login(Admin, Student or Faculty)");
		String role = scanner.next();
		System.out.println("Enter username");
		String username = scanner.next();
		System.out.println("Enter password");
		// String password = SecureInput.getSecureInput("password");
		String password = scanner.next();

		boolean flag = userController.authenticateUser(username, password, role);
		if (flag == true) {
//			UserUI userUI = new UserUI(userController, userService);
			UserUI userUI = new UserUI(userController);

			userUI.displayMenu(role);
		}

	}
}
