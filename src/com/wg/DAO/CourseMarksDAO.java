package com.wg.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

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

//	public void addMarks(String userId, String courseId, double marks) {
//
//	}

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
}
