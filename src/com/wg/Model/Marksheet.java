package com.wg.Model;

import java.util.List;

public class Marksheet {
	String userId;
	Result result; // enumvals
	double percentage;
	double marks;
	int standard;
	List<Course> courseName;

	public Marksheet(String userId, Result result, double percentage, double marks, int standard,
			List<Course> courseName) {
		super();
		this.userId = userId;
		this.result = result;
		this.percentage = percentage;
		this.marks = marks;
		this.standard = standard;
		this.courseName = courseName;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Result getResult() {
		return result;
	}

	public void setResult(Result result) {
		this.result = result;
	}

	public double getPercentage() {
		return percentage;
	}

	public void setPercentage(double percentage) {
		this.percentage = percentage;
	}

	public double getMarks() {
		return marks;
	}

	public void setMarks(double marks) {
		this.marks = marks;
	}

	public int getStandard() {
		return standard;
	}

	public void setStandard(int standard) {
		this.standard = standard;
	}

	public List<Course> getCourseName() {
		return courseName;
	}

	public void setCourseName(List<Course> courseName) {
		this.courseName = courseName;
	}

	@Override
	public String toString() {
		return "User{" + "userId='" + userId + '\'' + ", result=" + result + ", percentage=" + percentage + ", marks="
				+ marks + ", standard=" + standard + ", courseName=" + courseName + '}';
	}

}
