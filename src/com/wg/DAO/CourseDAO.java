package com.wg.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.wg.Model.Course;
import com.wg.config.DatabaseConnection;

public class CourseDAO {
	private Connection connection;

	public CourseDAO() {
		try {
			this.connection = DatabaseConnection.getConnection();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public Course getCourse(String courseId) throws SQLException, ClassNotFoundException {
		Course course = null;
		String query = "SELECT * FROM Course WHERE courseId = ?";
		try (PreparedStatement stmt = connection.prepareStatement(query)) {
			stmt.setString(1, courseId);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				course = new Course();
				course.setCourseId(rs.getString("courseId"));
				course.setCourseName(rs.getString("coursename"));
				course.setStandard(rs.getInt("standard"));
			}
		}
		return course;
	}

	public List<Course> getAllCourses() throws SQLException, ClassNotFoundException {
		List<Course> list = new ArrayList<>();
		String query = "SELECT * FROM Course";
		try (PreparedStatement stmt = connection.prepareStatement(query);) {
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				list.add(new Course(rs.getString("courseId"), rs.getString("courseName"), rs.getInt("standard")));
			}
		}
		return list;
	}

	public void addCourse(Course course) throws SQLException, ClassNotFoundException {
		String query = "INSERT INTO Course (courseId, courseName, standard) VALUES (?,?,?)";
		try (PreparedStatement stmt = connection.prepareStatement(query)) {
			stmt.setString(1, course.getCourseId());
			stmt.setString(2, course.getCourseName());
			stmt.setInt(3, course.getStandard());

			stmt.executeUpdate();
		}
	}

	public void deleteCourse(String id) throws SQLException, ClassNotFoundException {
		String deleteSQL = String.format("DELETE FROM Course WHERE courseId = ?");
		try (PreparedStatement preparedStatement = connection.prepareStatement(deleteSQL)) {
			preparedStatement.setString(1, id);
			preparedStatement.executeUpdate();
		}
	}

}
