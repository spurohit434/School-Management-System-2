package com.wg.Services;

import java.sql.SQLException;
import java.util.List;

import com.wg.DAO.CourseDAO;
import com.wg.Model.Course;

public class CourseService {
	private CourseDAO courseDAO;

	public CourseService() {

	}

	public CourseService(CourseDAO courseDAO) {
		this.courseDAO = courseDAO;
	}

	public Course getCourse(String courseId) {
		Course course = null;
		try {
			course = courseDAO.getCourse(courseId);
			if (course == null) {
				System.out.println("course null from dao:");
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return course;
	}

	public List<Course> getAllCourses() {
		List<Course> list = null;
		try {
			list = courseDAO.getAllCourses();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	public void addCourse(Course course) {
		try {
			courseDAO.addCourse(course);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

	public boolean deleteCourse(String id) {
		Course course = null;
		try {
			course = courseDAO.getCourse(id);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		if (course != null) {
			try {
				courseDAO.deleteCourse(id);
			} catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			}
			return true;
		} else {
			System.out.println("Course not found Exception");
		}
		return false;
	}

}
