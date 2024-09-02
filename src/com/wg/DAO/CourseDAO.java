package com.wg.DAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.wg.Constants.CourseConstants;
import com.wg.Model.Course;

public class CourseDAO extends GenericDAO<Course> {

	public CourseDAO() {
	}

	@Override
	protected Course mapResultSetToEntity(ResultSet resultSet) throws SQLException {
		Course course = new Course();
		course.setCourseId(resultSet.getString(CourseConstants.COURSE_ID));
		course.setCourseName(resultSet.getString(CourseConstants.COURSE_NAME));
		course.setStandard(resultSet.getInt(CourseConstants.STANDARD));
		return course;
	}

	public Course getCourse(String courseId) throws SQLException, ClassNotFoundException {
		String selectSQL = "SELECT * FROM Course WHERE courseId = \"" + courseId + "\"";
		return executeGetQuery(selectSQL);
	}

	public List<Course> getAllCourses() throws SQLException, ClassNotFoundException {
		String selectSQL = "SELECT * FROM Course";
		return executeGetAllQuery(selectSQL);
	}

	public boolean deleteCourse(String id) throws SQLException, ClassNotFoundException {
		String selectSQL = "DELETE FROM Course WHERE courseId = \"" + id + "\"";
		return executeQuery(selectSQL);
	}

	public boolean addCourse(Course course) throws SQLException, ClassNotFoundException {
		String sqlQuery = String.format("INSERT INTO Course (courseId, courseName, standard) VALUES ('%s','%s','%s')",
				course.getCourseId(), course.getCourseName(), course.getStandard());
		return executeQuery(sqlQuery);
	}

	public boolean updateCourse(Course course, String columnToUpdate) throws SQLException, ClassNotFoundException {
		Map<String, Object> fieldMap = new HashMap<>();
		fieldMap.put("CourseId", course.getCourseId());
		fieldMap.put("CourseName", course.getCourseName());
		fieldMap.put("Standard", course.getStandard());
		if (fieldMap.containsKey(columnToUpdate)) {
			Object value = fieldMap.get(columnToUpdate);
			String sqlQuery = String.format("UPDATE Course SET %s = '%s' WHERE courseId = '%s'", columnToUpdate, value,
					course.getCourseId());
			System.out.println(sqlQuery);
			return executeQuery(sqlQuery);
		}
		return false;
	}
}

//package com.wg.DAO;
//
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.util.ArrayList;
//import java.util.List;
//
//import com.wg.Constants.CourseConstants;
//import com.wg.Model.Course;
//import com.wg.config.DatabaseConnection;
//
//public class CourseDAO {
//	private Connection connection;
//
//	public CourseDAO() {
//		try {
//			this.connection = DatabaseConnection.getConnection();
//		} catch (ClassNotFoundException e) {
//			e.printStackTrace();
//		}
//	}
//
//	public Course getCourse(String courseId) throws SQLException, ClassNotFoundException {
//		Course course = null;
//		String query = "SELECT * FROM Course WHERE courseId = ?";
//		try (PreparedStatement stmt = connection.prepareStatement(query)) {
//			stmt.setString(1, courseId);
//			ResultSet rs = stmt.executeQuery();
//			if (rs.next()) {
//				course = new Course();
//				course.setCourseId(rs.getString("courseId"));
//				course.setCourseName(rs.getString("coursename"));
//				course.setStandard(rs.getInt("standard"));
//			}
//		}
//		return course;
//	}
//
//	public List<Course> getAllCourses() throws SQLException, ClassNotFoundException {
//		List<Course> list = new ArrayList<>();
//		String query = "SELECT * FROM Course";
//		try (PreparedStatement stmt = connection.prepareStatement(query);) {
//			ResultSet rs = stmt.executeQuery();
//			while (rs.next()) {
//				list.add(new Course(rs.getString("courseId"), rs.getString("courseName"), rs.getInt("standard")));
//			}
//		}
//		return list;
//	}
//
//	public void addCourse(Course course) throws SQLException, ClassNotFoundException {
//		String query = "INSERT INTO Course (courseId, courseName, standard) VALUES (?,?,?)";
//		try (PreparedStatement stmt = connection.prepareStatement(query)) {
//			stmt.setString(1, course.getCourseId());
//			stmt.setString(2, course.getCourseName());
//			stmt.setInt(3, course.getStandard());
//
//			stmt.executeUpdate();
//		}
//	}
//
//	public void deleteCourse(String id) throws SQLException, ClassNotFoundException {
//		String deleteSQL = String.format("DELETE FROM Course WHERE courseId = ?");
//		try (PreparedStatement preparedStatement = connection.prepareStatement(deleteSQL)) {
//			preparedStatement.setString(1, id);
//			preparedStatement.executeUpdate();
//		}
//	}
//
//	public void updateCourse(Course course, String courseId, String columnToUpdate)
//			throws SQLException, ClassNotFoundException {
//		String updateSQL = String.format("UPDATE Course SET %s = ? WHERE courseId = ?", columnToUpdate);
//
//		try (PreparedStatement preparedStatement = connection.prepareStatement(updateSQL)) {
//
//			prepareStatementForUpdate(preparedStatement, course, columnToUpdate);
//			preparedStatement.executeUpdate();
//		}
//	}
//
//	protected void prepareStatementForUpdate(PreparedStatement preparedStatement, Course course, String columnToUpdate)
//			throws SQLException {
//		switch (columnToUpdate) {
//		case CourseConstants.COURSE_NAME:
//			preparedStatement.setString(1, course.getCourseName());
//			preparedStatement.setString(2, course.getCourseId());
//			break;
//		case CourseConstants.STANDARD:
//			preparedStatement.setInt(1, course.getStandard());
//			preparedStatement.setString(2, course.getCourseId());
//			break;
//		default:
//			throw new IllegalArgumentException("Unknown column: " + columnToUpdate);
//		}
//	}
//
//}
