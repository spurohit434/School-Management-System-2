package com.wg.Model;

public class CourseMarks {

	private String userId;
	private String courseId;
	private double marks;

	public CourseMarks(String userId, String courseId, double marks) {
		this.userId = userId;
		this.courseId = courseId;
		this.marks = marks;
	}

	public CourseMarks() {

	}

	// Getters and Setters
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getCourseId() {
		return courseId;
	}

	public void setCourseId(String courseId) {
		this.courseId = courseId;
	}

	public double getMarks() {
		return marks;
	}

	public void setMarks(double marks) {
		this.marks = marks;
	}

	@Override
	public String toString() {
		return "CourseMarks{" + "userId='" + userId + '\'' + ", courseId='" + courseId + '\'' + ", marks=" + marks
				+ '}';
	}
}