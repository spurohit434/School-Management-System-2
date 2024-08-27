package com.wg.Controller;

import java.time.LocalDate;
import java.util.Scanner;

import com.wg.Services.FeeService;

public class FeeController {
	private FeeService feeService;
	Scanner scanner = new Scanner(System.in);

	public FeeController(FeeService feeService) {
		this.feeService = feeService;
	}

	public FeeController() {
	}

	public void payFees(String userId) {
		feeService.payFees(userId);
	}

	public void checkFees(String userId) {
		feeService.checkFees(userId);
	}

	public void addFees(String userId, double feeAmount, LocalDate deadline, double fine) {
		feeService.addFees(userId, feeAmount, deadline, fine);
	}

	public void calculateFine(String userId) {
		feeService.calculateFine(userId);
	}

//	FeeDAO feeDAO = new FeeDAO();
//
//	public void payFees(String userId) {
//		try {
//			feeDAO.checkFees(userId);
//			feeDAO.payFees(userId);
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//	}
//
//	public void checkFees(String userId) {
//		try {
//			feeDAO.checkFees(userId);
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//	}
//
//	public void setFees(String userId, double feeAmount, LocalDate deadline, double fine) {
//		try {
//			feeDAO.setFees(userId, feeAmount, deadline, fine);
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//	}
//
//	public void calculateFine(String userId) {
//		try {
//			feeDAO.calculateFine(userId);
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//	}
}
