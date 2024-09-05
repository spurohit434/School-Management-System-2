package com.wg.Services;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.logging.Logger;

import com.wg.DAO.FeeDAO;
import com.wg.Helper.LoggingUtil;
import com.wg.Model.Fee;

public class FeeService {
	private FeeDAO feeDAO;
	Logger logger = LoggingUtil.getLogger(FeeService.class);

	public FeeService() {
	}

	public FeeService(FeeDAO feeDAO) {
		this.feeDAO = feeDAO;
	}

	public void payFees(String userId) {
		try {
			Fee fees = null;
			try {
				fees = feeDAO.checkFees(userId);
				if (fees == null) {
					System.out.println("Fees not added yet");
					return;
				}
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			double fine = 0;
			double feesAmount = fees.getFeeAmount();
			LocalDate deadLine = fees.getDeadline();
			LocalDate currentDate = LocalDate.now();

			if (currentDate.isAfter(deadLine)) {
				long overdueDays = ChronoUnit.DAYS.between(deadLine, currentDate);
				fine = overdueDays * 5.0;
			}
			System.out.println("The fine is " + fine);
			double totalFees = feesAmount + fine;
			System.out.println("Total Payalbe amount is: " + totalFees);
			if (feesAmount == 0 && fine == 0) {
				System.out.println("No fee amount to pay");
				return;
			}
			try {
				boolean flag = feeDAO.payFees(userId);
				if (flag == true) {
					System.out.println("Fees paid successfully");
					logger.info("Fees paid successfully!!");

				} else {
					System.out.println("Fees not paid");
					logger.info("Fees payment unsuccessful!!");
				}
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void checkFees(String userId) {
		try {
			Fee fees = null;
			fees = feeDAO.checkFees(userId);
			if (fees == null) {
				System.out.println("Fees not added yet");
				return;
			} else {
				System.out.println("The fees Amount is: " + fees.getFeeAmount());
				return;
			}
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

//	public void addFees(String userId, double feeAmount, LocalDate deadline, double fine) {
//		try {
//			feeDAO.addFees(userId, feeAmount, deadline, fine);
//		} catch (SQLException | ClassNotFoundException e) {
//			e.printStackTrace();
//		}
//	}

	public void addFees(String userId, double feeAmount, LocalDate deadline, double fine) {
		try {
			Fee fees = null;
			fees = feeDAO.checkFees(userId);
			if (fees == null) {
				boolean flag = feeDAO.insertFees(userId, feeAmount, deadline, fine);
				if (flag == true) {
					System.out.println("Fees successfully added");
				} else {
					System.out.println("No records inserted.");
				}
			} else {
				boolean flag = feeDAO.updateFees(userId, feeAmount, deadline, fine);
				if (flag == true) {
					System.out.println("Fees successfully updated");
				} else {
					System.out.println("No records inserted.");
				}
			}
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public void calculateFine(String userId) {
		try {
			Fee fee = null;
			fee = feeDAO.calculateFine(userId);
			if (fee == null) {
				System.out.println("No fine applicable as fees not added yet");
				return;
			}
			double fine = 0;
			double feeAmount = fee.getFeeAmount();
			LocalDate deadLine = fee.getDeadline();
			LocalDate currentDate = LocalDate.now();

			if (currentDate.isAfter(deadLine)) {
				long overdueDays = ChronoUnit.DAYS.between(deadLine, currentDate);
				fine = overdueDays * 5.0;
			}

			System.out.println("The feeAmount is " + feeAmount);
			System.out.println("The deadline is " + deadLine);
			System.out.println("The fine is " + fine);

		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
}