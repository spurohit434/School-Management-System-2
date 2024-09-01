package com.wg.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import com.wg.config.DatabaseConnection;

public class FeeDAO {

	private Connection connection;

	public FeeDAO() {
		try {
			this.connection = DatabaseConnection.getConnection();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public double checkFees(String studentId) throws SQLException {
		String selectSQL = "SELECT * FROM Fees WHERE studentId = ?";
		double fees = 0;
		try (PreparedStatement preparedStatement = connection.prepareStatement(selectSQL)) {
			preparedStatement.setString(1, studentId);

			try (ResultSet resultSet = preparedStatement.executeQuery()) {
				if (resultSet.next()) {
					fees = resultSet.getDouble("FeeAmount");
					return fees;
				} else {
					System.out.println("No record found for student ID: " + studentId);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new SQLException("Error querying fee record", e);
		}
		return fees;
	}

	public void payFees(String studentId) throws SQLException {
		String updateSQL = "UPDATE Fees SET feeAmount = 0, fine = 0 WHERE studentId = ?";

		try (PreparedStatement preparedStatement = connection.prepareStatement(updateSQL)) {
			preparedStatement.setString(1, studentId);

			int rowsAffected = preparedStatement.executeUpdate();
			if (rowsAffected == 0) {
				System.out.println("No records updated. Check if the student ID exists.");
			} else {
				System.out.println("Fees successfully paid");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new SQLException("Error updating fee record", e);
		}
	}

	public void addFees(String studentId, double feeAmount, LocalDate deadline, double fine) throws SQLException {
		String checkSQL = "SELECT COUNT(*) FROM Fees WHERE studentId = ?";

		try (PreparedStatement checkStmt = connection.prepareStatement(checkSQL)) {
			checkStmt.setString(1, studentId);

			try (ResultSet rs = checkStmt.executeQuery()) {
				rs.next();
				int count = rs.getInt(1);

				if (count > 0) {
					String updateSQL = "UPDATE Fees SET feeAmount = ?, deadline = ?, fine = ? WHERE studentId = ?";
					try (PreparedStatement updateStmt = connection.prepareStatement(updateSQL)) {
						updateStmt.setDouble(1, feeAmount);
						updateStmt.setDate(2, java.sql.Date.valueOf(deadline));
						updateStmt.setDouble(3, fine);
						updateStmt.setString(4, studentId);

						int rowsAffected = updateStmt.executeUpdate();
						if (rowsAffected == 0) {
							System.out.println("No records updated.");
						} else {
							System.out.println("Fees successfully updated");
						}
					}
				} else {
					String addSQL = "INSERT INTO Fees (studentId, feeAmount, deadline, fine) VALUES (?, ?, ?, ?)";
					try (PreparedStatement addStmt = connection.prepareStatement(addSQL)) {
						addStmt.setString(1, studentId);
						addStmt.setDouble(2, feeAmount);
						addStmt.setDate(3, java.sql.Date.valueOf(deadline));
						addStmt.setDouble(4, fine);

						int rowsAffected = addStmt.executeUpdate();
						if (rowsAffected == 0) {
							System.out.println("No records inserted.");
						} else {
							System.out.println("Fees successfully added");
						}
					}
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new SQLException("Error upserting fee record", e);
		}
	}

	public double calculateFine(String studentId) throws SQLException {
		double fine = 0.0;
		String selectSQL = "SELECT feeAmount, deadline FROM Fees WHERE studentId = ?";
		try (PreparedStatement preparedStatement = connection.prepareStatement(selectSQL)) {
			preparedStatement.setString(1, studentId);

			try (ResultSet resultSet = preparedStatement.executeQuery()) {
				if (resultSet.next()) {
					double feeAmount = resultSet.getDouble("feeAmount");
					LocalDate deadline = resultSet.getDate("deadline").toLocalDate();
					System.out.println("The feeAmount is " + feeAmount);
					System.out.println("The deadline is " + deadline);
					LocalDate currentDate = LocalDate.now();
					if (currentDate.isAfter(deadline)) {
						long overdueDays = ChronoUnit.DAYS.between(deadline, currentDate);
						fine = overdueDays * 5.0;
					}
				} else {
					System.out.println("No record found for student ID: " + studentId);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new SQLException("Error calculating fine", e);
		}

		return fine;
	}

}
