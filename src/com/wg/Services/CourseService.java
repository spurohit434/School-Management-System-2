package com.wg.Services;

import java.sql.SQLException;
import java.util.List;
import java.util.logging.Logger;

import com.wg.DAO.CourseDAO;
import com.wg.Helper.LoggingUtil;
import com.wg.Model.Course;

public class CourseService {
	private CourseDAO courseDAO;

	Logger logger = LoggingUtil.getLogger(CourseService.class);
//	logger.severe(e.getMessage());

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
				System.out.println("No course found");
			}
			return course;
		} catch (ClassNotFoundException | SQLException e) {
			logger.severe(e.getMessage());
			e.printStackTrace();
		}
		return course;
	}

	public List<Course> getAllCourses() {
		List<Course> list = null;
		try {
			list = courseDAO.getAllCourses();
		} catch (ClassNotFoundException | SQLException e) {
			logger.severe(e.getMessage());
			e.printStackTrace();
		}
		return list;
	}

	public void addCourse(Course course) {
		boolean flag = false;
		try {
			flag = courseDAO.addCourse(course);
			if (flag == true) {
				System.out.println("Course Added successfully");
			} else {
				System.out.println("Course not added");
			}
		} catch (ClassNotFoundException | SQLException e) {
			logger.severe(e.getMessage());
			e.printStackTrace();
		}
	}

	public boolean deleteCourse(String id) {
		Course course = null;
		try {
			course = courseDAO.getCourse(id);
		} catch (ClassNotFoundException | SQLException e) {
			logger.severe(e.getMessage());
			e.printStackTrace();
		}
		if (course != null) {
			try {
				courseDAO.deleteCourse(id);
			} catch (ClassNotFoundException | SQLException e) {
				logger.severe(e.getMessage());
				e.printStackTrace();
			}
			return true;
		} else {
			System.out.println("Course not found");
		}
		return false;
	}

	public boolean updateCourse(Course course, String columnToUpdate) {
		boolean flag = false;
		try {
			flag = courseDAO.updateCourse(course, columnToUpdate);
			return flag;
		} catch (ClassNotFoundException | SQLException e) {
			logger.severe(e.getMessage());
			e.printStackTrace();
		}
		return flag;
	}

}
