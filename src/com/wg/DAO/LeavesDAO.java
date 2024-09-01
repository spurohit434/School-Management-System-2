package com.wg.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.wg.Model.Leaves;
import com.wg.Model.LeavesStatus;
import com.wg.config.DatabaseConnection;

public class LeavesDAO {
	private Connection connection;

	public LeavesDAO() {
		try {
			this.connection = DatabaseConnection.getConnection();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public void approveLeave(String userId) throws SQLException {
		String updateSQL = "UPDATE Leaves SET status = ? WHERE userId = ?";

		try (PreparedStatement preparedStatement = connection.prepareStatement(updateSQL)) {
			preparedStatement.setString(1, "Approved");
			preparedStatement.setString(2, userId);

//			System.out.println("Executing SQL: " + updateSQL);
//			System.out.println("With parameters: status='Approved', userId=" + userId);
			int rowsAffected = preparedStatement.executeUpdate();

			if (rowsAffected == 0) {
				System.out.println("No records updated. Check if the leave ID exists.");
			} else {
				System.out.println("Leave request successfully approved ");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new SQLException("Error updating leave request status", e);
		}
	}

	public void applyLeave(Leaves leave) throws SQLException {
		String input = "Pending";
		LeavesStatus status = LeavesStatus.valueOf(input);
		String query = "SELECT * FROM Leaves where userId = ? and status = ?";

		try (PreparedStatement preparedStatement1 = connection.prepareStatement(query)) {
			preparedStatement1.setString(1, leave.getUserId());
			preparedStatement1.setString(2, status.name()); // Set the status parameter

			try (ResultSet resultSet = preparedStatement1.executeQuery()) {
				if (resultSet.next()) {
					System.out.println("Leave request already applied. Can not request again");
				} else {
					String insertSQL = "INSERT INTO Leaves (id, userId, content, startDate, endDate, status) VALUES (?, ?, ?, ?, ?, ?)";
					try (PreparedStatement preparedStatement = connection.prepareStatement(insertSQL)) {
						// Set the values in the prepared statement
						preparedStatement.setString(1, leave.getLeaveId());
						preparedStatement.setString(2, leave.getUserId());
						preparedStatement.setString(3, leave.getContent());
						preparedStatement.setDate(4, java.sql.Date.valueOf(leave.getStartDate()));
						preparedStatement.setDate(5, java.sql.Date.valueOf(leave.getEndDate()));
						preparedStatement.setString(6, leave.getStatus().name()); // Convert enum to string

						int rowsAffected = preparedStatement.executeUpdate();
						if (rowsAffected == 0) {
							System.out.println("Leave request was not applied. Check the provided details.");
						} else {
							System.out.println("Leave request successfully applied.");
						}
					} catch (SQLException e) {
						e.printStackTrace();
						throw new SQLException("Error applying leave request", e);
					}
				}
			} catch (SQLException e) {
				e.printStackTrace(); // Log the exception for debugging
			}
		} catch (SQLException e) {
			e.printStackTrace(); // Log the exception for debugging
		}
	}

	public List<Leaves> viewAllLeave() {
		List<Leaves> leavesList = new ArrayList<>();
		String query = "SELECT * FROM Leaves"; // SQL query to select all records from Leaves table
		try (Statement statement = connection.createStatement(); ResultSet resultSet = statement.executeQuery(query)) {

			while (resultSet.next()) {
				Leaves leave = new Leaves();
				leave.setLeaveId(resultSet.getString("id"));
				leave.setUserId(resultSet.getString("userId"));
				leave.setContent(resultSet.getString("content"));
				leave.setStartDate(resultSet.getDate("startDate").toLocalDate());
				leave.setEndDate(resultSet.getDate("endDate").toLocalDate());
				leave.setStatus(LeavesStatus.valueOf(resultSet.getString("status")));
				leavesList.add(leave);
			}
		} catch (SQLException e) {
			e.printStackTrace(); // Log the exception for debugging
		}
		return leavesList;
	}

	public LeavesStatus checkLeaveStatus(String userId) {
		LeavesStatus status = null;
		String query = "SELECT status FROM Leaves WHERE userId = ?";
		try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
			preparedStatement.setString(1, userId);

			try (ResultSet resultSet = preparedStatement.executeQuery()) {
				if (resultSet.next()) {
					status = LeavesStatus.valueOf(resultSet.getString("status"));
				} else {
					System.out.println("No leave request found for user ID: " + userId);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return status;
	}
}
