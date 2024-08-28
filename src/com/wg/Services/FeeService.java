package com.wg.Services;

import java.sql.SQLException;
import java.time.LocalDate;

import com.wg.DAO.FeeDAO;

public class FeeService {
	private FeeDAO feeDAO;

	public FeeService() {
	}

	public FeeService(FeeDAO feeDAO) {
		this.feeDAO = feeDAO;
	}

	public void payFees(String userId) {
		try {
			double fees = feeDAO.checkFees(userId);
			double fine = feeDAO.calculateFine(userId);
			System.out.println("The fine is " + fine);
			double totalFees = fees + fine;
			System.out.println("Total Payalbe amount is: " + totalFees);
			feeDAO.payFees(userId);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public double checkFees(String userId) {
		try {
			return feeDAO.checkFees(userId);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	public void addFees(String userId, double feeAmount, LocalDate deadline, double fine) {
		try {
			feeDAO.addFees(userId, feeAmount, deadline, fine);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public double calculateFine(String userId) {
		try {
			return feeDAO.calculateFine(userId);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}
}