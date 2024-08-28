package com.wg.Controller;

import java.util.List;
import java.util.Scanner;

import com.wg.Model.CourseMarks;
import com.wg.Services.CourseMarksService;

public class CourseMarksController {
	private CourseMarksService courseMarksService;
	Scanner scanner = new Scanner(System.in);

	public CourseMarksController(CourseMarksService courseMarksService) {
		this.courseMarksService = courseMarksService;
	}

	public CourseMarksController() {
	}

	public void addMarks(String userId, String courseId, double marks) {
		if (marks > 100 || marks < 0) {
			System.out.println("Enter valid marks");
			return;
		}
		courseMarksService.addMarks(userId, courseId, marks);
	}

	public List<CourseMarks> checkMarks(String userId) {
		// TODO Auto-generated method stub
		return courseMarksService.checkMarks(userId);
	}
}