package com.wg.DAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import com.wg.Model.Fee;

public class FeeDAO extends GenericDAO<Fee> {

	public FeeDAO() {
		super();
	}

	@Override
	protected Fee mapResultSetToEntity(ResultSet resultSet) throws SQLException {
		Fee feeRecord = new Fee();
		feeRecord.setStudentId(resultSet.getString("studentId")); // Replace with actual column name
		feeRecord.setFeeAmount(resultSet.getDouble("feeAmount")); // Replace with actual column name
		feeRecord.setDeadline(resultSet.getDate("deadline").toLocalDate());
		feeRecord.setFine(resultSet.getDouble("fine"));
		return feeRecord;
	}

	public double checkFees(String studentId) throws SQLException, ClassNotFoundException {
		String selectSQL = String.format("SELECT * FROM Fees WHERE studentId = '%s'", studentId);
		double fees = 0;
		Fee fee = executeGetQuery(selectSQL);
		fees = fee.getFeeAmount();
		return fees;
	}

	public boolean payFees(String studentId) throws SQLException, ClassNotFoundException {
		String updateSQL = String.format("UPDATE Fees SET feeAmount = 0, fine = 0 WHERE studentId = '%s'", studentId);
		// System.out.println(updateSQL);
		return executeQuery(updateSQL);
	}

	public void addFees(String studentId, double feeAmount, LocalDate deadline, double fine)
			throws SQLException, ClassNotFoundException {
		Fee fee = null;
		String checkSQL = String.format("SELECT * FROM Fees WHERE studentId = '%s'", studentId);
		// System.out.println(checkSQL);
		fee = executeGetQuery(checkSQL);
		if (fee == null) {
			String addSQL = String.format(
					"INSERT INTO Fees (studentId, feeAmount, deadline, fine) VALUES ('%s', '%s', '%s', '%s')",
					studentId, feeAmount, deadline, fine);
			boolean flag = executeQuery(addSQL);
			if (flag == true) {
				System.out.println("Fees successfully added");
			} else {
				System.out.println("No records inserted.");
			}

		} else {
			String updateSQL = String.format(
					"UPDATE Fees SET feeAmount = '%s', deadline = '%s', fine = '%s' WHERE studentId = '%s'", feeAmount,
					deadline, fine, studentId);
			boolean flag = executeQuery(updateSQL);
			if (flag == true) {
				System.out.println("Fees successfully updated");
			} else {
				System.out.println("No records updated.");
			}
		}
	}

	public double calculateFine(String studentId) throws SQLException, ClassNotFoundException {
		double fine = 0.0;
		String selectSQL = String.format("SELECT * FROM Fees WHERE studentId = '%s'", studentId);
		// System.out.println(selectSQL);
		Fee fee = executeGetQuery(selectSQL);
		if (fee == null) {
			System.out.println("No records found");
			return fine;
		}
		double feeAmount = fee.getFeeAmount();
		LocalDate deadLine = fee.getDeadline();
		System.out.println("The feeAmount is " + feeAmount);
		System.out.println("The deadline is " + deadLine);

		LocalDate currentDate = LocalDate.now();
		if (currentDate.isAfter(deadLine)) {
			long overdueDays = ChronoUnit.DAYS.between(deadLine, currentDate);
			fine = overdueDays * 5.0;
		}
		return fine;
	}

}