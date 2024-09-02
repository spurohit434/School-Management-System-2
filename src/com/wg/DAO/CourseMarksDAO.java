package com.wg.DAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.wg.Model.CourseMarks;

public class CourseMarksDAO extends GenericDAO<CourseMarks> {

	public CourseMarksDAO() {
		super();
	}

	@Override
	protected CourseMarks mapResultSetToEntity(ResultSet resultSet) throws SQLException {
		CourseMarks courseMarks = new CourseMarks();
		courseMarks.setUserId(resultSet.getString("userId")); // Replace with actual column name
		courseMarks.setCourseId(resultSet.getString("courseId")); // Replace with actual column name
		courseMarks.setMarks(resultSet.getDouble("marks")); // Replace with actual column name
		return courseMarks;
	}

	public boolean addMarks(CourseMarks courseMarks) throws ClassNotFoundException, SQLException {
		String sql = String.format("INSERT INTO CourseMarks (UserId, courseId, marks) VALUES ('%s', '%s', '%s')",
				courseMarks.getUserId(), courseMarks.getCourseId(), courseMarks.getMarks());
		// System.out.println(sql);
		return executeQuery(sql);
	}

	public List<CourseMarks> checkMarks(String userId) throws ClassNotFoundException, SQLException {
		List<CourseMarks> courseMarks = new ArrayList<>(); // Initialize as an empty list
		String sql = String.format("SELECT * FROM CourseMarks WHERE UserId = '%s'", userId);
		courseMarks = executeGetAllQuery(sql);
		return courseMarks;
	}

}