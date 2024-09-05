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
}
