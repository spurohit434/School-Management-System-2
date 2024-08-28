package com.wg.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.wg.Model.CourseMarks;
import com.wg.config.DatabaseConnection;

public class CourseMarksDAO {
	private Connection connection;

	public CourseMarksDAO() {
		try {
			this.connection = DatabaseConnection.getConnection();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public void addMarks(String userId, String courseId, double marks) {
		String sql = "INSERT INTO CourseMarks (UserId, courseId, marks) VALUES (?, ?, ?)";
		try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
			// Set the parameters
			preparedStatement.setString(1, userId);
			preparedStatement.setString(2, courseId);
			preparedStatement.setDouble(3, marks);
			// Execute the statement
			preparedStatement.executeUpdate();
			System.out.println("Marks added successfully.");
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Failed to add marks.");
		}
	}

	public List<CourseMarks> checkMarks(String userId) {
//		List<CourseMarks> courseMarks = null;
//		CourseMarks course = null;
//		String sql = "SELECT * FROM CourseMarks WHERE UserId = ?";
//		try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
//			preparedStatement.setString(1, userId);
//			// Execute the query
//			try (ResultSet resultSet = preparedStatement.executeQuery()) {
//				while (resultSet.next()) {
//					course = new CourseMarks();
//					course.setUserId(resultSet.getString("userId"));
//					course.setMarks(resultSet.getDouble("marks"));
//					course.setCourseId(resultSet.getString("courseId"));
//					courseMarks.add(course);
//				}
//			}
//			return courseMarks;
//		} catch (SQLException e) {
//			e.printStackTrace();
//			System.out.println("Failed to retrieve marks.");
//		}
//		return courseMarks;

		List<CourseMarks> courseMarks = new ArrayList<>(); // Initialize as an empty list
		String sql = "SELECT * FROM CourseMarks WHERE UserId = ?";

		try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
			preparedStatement.setString(1, userId);

			// Execute the query
			try (ResultSet resultSet = preparedStatement.executeQuery()) {
				while (resultSet.next()) {
					CourseMarks course = new CourseMarks();
					course.setUserId(resultSet.getString("UserId"));
					course.setCourseId(resultSet.getString("courseId"));
					course.setMarks(resultSet.getDouble("marks"));
					courseMarks.add(course);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Failed to retrieve marks.");
		}

		return courseMarks;
	}
}
