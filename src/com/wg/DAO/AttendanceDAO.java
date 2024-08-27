package com.wg.DAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.wg.Model.Attendance;
import com.wg.Model.Status;
import com.wg.config.DatabaseConnection;

public class AttendanceDAO {
	private Connection connection;

	public AttendanceDAO() {
		try {
			this.connection = DatabaseConnection.getConnection();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public void addAttendance(Attendance attendance) throws SQLException, ClassNotFoundException {
		String query = "INSERT INTO Attendance (studentId, standard, date, status) VALUES (?,?,?,?)";
		try (PreparedStatement stmt = connection.prepareStatement(query)) {
			stmt.setString(1, attendance.getStudentId());
			stmt.setInt(2, attendance.getStandard());
			LocalDate localDate = attendance.getDate();
			Date sqlDate = Date.valueOf(localDate);
			stmt.setDate(3, sqlDate);
			stmt.setString(4, attendance.getStatus().name());

			stmt.executeUpdate();
		}

	}

	public List<Attendance> viewAttendanceByStandard(int standard) throws SQLException, ClassNotFoundException {
		List<Attendance> attendanceList = new ArrayList<>();
		String query = "SELECT * FROM Attendance WHERE standard = ?";

		try (PreparedStatement stmt = connection.prepareStatement(query)) {
			stmt.setInt(1, standard);

			try (ResultSet rs = stmt.executeQuery()) {
				while (rs.next()) {
					// Retrieve data from ResultSet
					String studentId = rs.getString("studentId");
					int std = rs.getInt("standard");
					LocalDate date = rs.getDate("date").toLocalDate();
					Status status = Status.valueOf(rs.getString("status"));

					// Create Attendance object and add to list
					Attendance attendance = new Attendance(studentId, std, date, status);
					attendanceList.add(attendance);
				}
			}
		}
		return attendanceList;
	}

	public List<Attendance> viewAttendanceById(String studentId) throws SQLException, ClassNotFoundException {
		List<Attendance> attendanceList = new ArrayList<>();
		String query = "SELECT * FROM Attendance WHERE studentId = ?";

		try (PreparedStatement stmt = connection.prepareStatement(query)) {
			stmt.setString(1, studentId);

			try (ResultSet rs = stmt.executeQuery()) {
				while (rs.next()) {
					// Retrieve data from ResultSet
					int standard = rs.getInt("standard");
					LocalDate date = rs.getDate("date").toLocalDate();
					Status status = Status.valueOf(rs.getString("status"));

					// Create Attendance object and add to list
					Attendance attendance = new Attendance(studentId, standard, date, status);
					attendanceList.add(attendance);
				}
			}
		}
		return attendanceList;
	}

}

//String studentId, int standard, LocalDate date, Status status)
