package com.wg.Controller;

import java.util.List;
import java.util.Scanner;
import java.util.UUID;

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
		try {
//			System.out.print("Enter course ID: ");
//			String courseId = scanner.nextLine();

			Course course = courseService.getCourse(courseId);
			if (course != null) {
				boolean continueUpdating = true;

				while (continueUpdating) {
					displayUpdateMenu();

					System.out.print("Select an option (1-3): ");
					int choice = scanner.nextInt();
					scanner.nextLine();

					String columnToUpdate = "";

					switch (choice) {
					case 1:
						columnToUpdate = "courseName";
						System.out.print("Enter new Course name: ");
						course.setCourseName(scanner.nextLine());
						break;
					case 2:
						columnToUpdate = "standard";
						System.out.print("Enter new standard: ");
						course.setStandard(scanner.nextInt());
						break;
					case 3:
						continueUpdating = false;
						break;
					default:
						System.out.println("Invalid option. Please try again.");
					}
					if (!continueUpdating) {
						break;
					}
					courseService.updateCourse(course, courseId, columnToUpdate);
					System.out.println("Course updated successfully.");
				}
			} else {
				System.out.println("Course Not found");
			}
		} catch (Exception e) {
			System.out.println("Error updating Course: " + e.getMessage());
		}
	}

	private void displayUpdateMenu() {
		System.out.println("\nWhich field would you like to update?");
		System.out.println("1. Course Name");
		System.out.println("2. Standard");
		System.out.println("3. Done updating");
	}

	public boolean deleteCourse(String courseId) {
		boolean flag = false;
		flag = courseService.deleteCourse(courseId);
		return flag;
	}

	public boolean addCourse(String courseName, int standard) {
		String randomString = UUID.randomUUID().toString();
		int desiredLength = 8;
		if (desiredLength > randomString.length()) {
			desiredLength = randomString.length();
		}
		String courseId = randomString.substring(0, desiredLength);
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
			System.out.println("Course Not Found");
		}
		return course;
	}
}
