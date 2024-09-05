package com.wg.DAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

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

	public Fee checkFees(String studentId) throws SQLException, ClassNotFoundException {
		String selectSQL = String.format("SELECT * FROM Fees WHERE studentId = '%s'", studentId);
		Fee fee = executeGetQuery(selectSQL);
		return fee;
	}

	public boolean payFees(String studentId) throws SQLException, ClassNotFoundException {
		String updateSQL = String.format("UPDATE Fees SET feeAmount = 0, fine = 0 WHERE studentId = '%s'", studentId);
		return executeQuery(updateSQL);
	}

	public boolean insertFees(String studentId, double feeAmount, LocalDate deadline, double fine)
			throws ClassNotFoundException, SQLException {
		String addSQL = String.format(
				"INSERT INTO Fees (studentId, feeAmount, deadline, fine) VALUES ('%s', '%s', '%s', '%s')", studentId,
				feeAmount, deadline, fine);
		boolean flag = executeQuery(addSQL);
		return flag;
	}

	public boolean updateFees(String studentId, double feeAmount, LocalDate deadline, double fine)
			throws ClassNotFoundException, SQLException {
		String updateSQL = String.format(
				"UPDATE Fees SET feeAmount = '%s', deadline = '%s', fine = '%s' WHERE studentId = '%s'", feeAmount,
				deadline, fine, studentId);
		boolean flag = executeQuery(updateSQL);
		return flag;
	}

	public void addFees(String studentId, double feeAmount, LocalDate deadline, double fine)
			throws SQLException, ClassNotFoundException {
		Fee fee = null;
		String checkSQL = String.format("SELECT * FROM Fees WHERE studentId = '%s'", studentId);
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

	public Fee calculateFine(String studentId) throws SQLException, ClassNotFoundException {
		String selectSQL = String.format("SELECT * FROM Fees WHERE studentId = '%s'", studentId);
		Fee fee = executeGetQuery(selectSQL);
		return fee;
	}

}