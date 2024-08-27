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
			feeDAO.checkFees(userId);
			feeDAO.payFees(userId);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void checkFees(String userId) {
		try {
			feeDAO.checkFees(userId);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void addFees(String userId, double feeAmount, LocalDate deadline, double fine) {
		try {
			feeDAO.addFees(userId, feeAmount, deadline, fine);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void calculateFine(String userId) {
		try {
			feeDAO.calculateFine(userId);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}