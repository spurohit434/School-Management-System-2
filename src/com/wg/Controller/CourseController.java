package com.wg.Controller;

import java.util.List;
import java.util.Scanner;

import com.wg.Model.Course;
import com.wg.Services.CourseService;

public class CourseController {

	private CourseService courseService;
	Scanner scanner = new Scanner(System.in);

	public CourseController(CourseService courseService) {
		this.courseService = courseService;
	}

	public CourseController() {
	}

	public List<Course> getAllCourses() {
		List<Course> course = null;
		course = courseService.getAllCourses();
		return course;
	}

	public void updateCourse(String courseId) {

	}

	public boolean deleteCourse(String courseId) {
		boolean flag = false;
		flag = courseService.deleteCourse(courseId);
		return flag;
	}

	public boolean addCourse(String courseId, String courseName, int standard) {
		if (courseId == null || courseName == null || standard > 12) {
			System.out.println("Enter valid credentials");
			return false;
		}

		Course course = new Course(courseId, courseName, standard);
		courseService.addCourse(course);
		return true;
	}

	public Course getCourse(String courseId) {
		Course course = null;
		course = courseService.getCourse(courseId);
		if (course == null) {
			System.out.println("Course null from dao:");
		}
		return course;
	}
}
