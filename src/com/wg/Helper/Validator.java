package com.wg.Helper;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validator {
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
}
