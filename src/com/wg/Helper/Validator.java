package com.wg.Helper;

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
}
