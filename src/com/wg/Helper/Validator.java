package com.wg.Helper;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validator {
	static Scanner scanner = new Scanner(System.in);

	public static boolean isValid(String email) {
		String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\." + "[a-zA-Z0-9_+&*-]+)*@" + "(?:[a-zA-Z0-9-]+\\.)+[a-z"
				+ "A-Z]{2,7}$";

		Pattern pat = Pattern.compile(emailRegex);
		if (email == null) {
			return false;
		}
		return pat.matcher(email).matches();
	}

	public static boolean isValidAge(int age) {
		if (age > 0 && age <= 150) {
			return true;
		}
		return false;
	}

	public static boolean isValidGender(String gender) {
		if (gender == null) {
			return false;
		}
		return gender.equalsIgnoreCase("M") || gender.equalsIgnoreCase("F");
	}

	public static boolean isValidUsername(String username) {
		if (username == null || username.length() < 1) {
			return false;
		}
		return !username.contains(" ");
	}

	public static boolean isValidContactNo(String contactNo) {
		if (contactNo == null) {
			return false;
		}
		Pattern PHONE_NUMBER_PATTERN = Pattern.compile("^\\d{10}$");
		Matcher matcher = PHONE_NUMBER_PATTERN.matcher(contactNo);
		return matcher.matches();
	}

	public static boolean isValidRole(String role) {
		if (role == null) {
			return false;
		}
		return role.equalsIgnoreCase("Admin") || role.equalsIgnoreCase("Student") || role.equalsIgnoreCase("Faculty");
	}

	public static int getUserChoice() {
		while (!scanner.hasNextInt()) {
			System.out.println("Invalid input. Please enter a Valid Input");
			scanner.next();
			System.out.print("Enter your choice: ");
		}
		return scanner.nextInt();
	}

	public static boolean isValidDate(LocalDate endDate) {
		return false;
	}

	public static boolean isValidIndex(int index, int limit) {
		if (index > 0 && index <= limit) {
			return true;
		}
		return false;
	}

	public static LocalDate ValidateDate() {
		LocalDate startDate = LocalDate.now();
		LocalDate endDate = null;
		boolean validDate = false;

		while (!validDate) {
			System.out.println("Enter Leave till date (YYYY-MM-DD):");
			String dateString = scanner.next();
			scanner.nextLine();
			try {
				endDate = LocalDate.parse(dateString);
				if (endDate.isAfter(startDate)) {
					validDate = true;
				} else {
					System.out.println("Error: End date must be after the start date. Please enter a valid date.");
				}
			} catch (DateTimeParseException e) {
				System.out.println("Error: Invalid date format. Please enter the date in YYYY-MM-DD format.");
			}
		}
		return endDate;
	}

}
