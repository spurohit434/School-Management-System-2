package com.wg.Services;

import java.sql.SQLException;
import java.util.List;
import java.util.logging.Logger;

import com.wg.DAO.LeavesDAO;
import com.wg.Helper.LoggingUtil;
import com.wg.Model.Leaves;

public class LeavesService {
	private LeavesDAO leavesDAO;
	Logger logger = LoggingUtil.getLogger(LeavesService.class);
	// logger.severe(e.getMessage());

	public LeavesService() {
	}

	public LeavesService(LeavesDAO leavesDAO) {
		this.leavesDAO = leavesDAO;
	}

	public void approveLeave(String userId) {
		boolean flag = false;
		try {
			flag = leavesDAO.approveLeave(userId);
			if (flag == true) {
				System.out.println("Leave Approved Successfully");
			} else {
				System.out.println("Leave Not approved");
			}
		} catch (SQLException e) {
			logger.severe(e.getMessage());
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			logger.severe(e.getMessage());
			e.printStackTrace();
		}
	}

	public void rejectLeave(String userId) {
		boolean flag = false;
		try {
			flag = leavesDAO.rejectLeave(userId);
			if (flag == true) {
				System.out.println("Leave Rejected Successfully");
			} else {
				System.out.println("Leave Not rejected");
			}
		} catch (SQLException e) {
			logger.severe(e.getMessage());
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			logger.severe(e.getMessage());
			e.printStackTrace();
		}
	}

	public void applyLeave(Leaves leave) {
		try {
			leavesDAO.applyLeave(leave);
		} catch (SQLException e) {
			logger.severe(e.getMessage());
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			logger.severe(e.getMessage());
			e.printStackTrace();
		}
	}

	public List<Leaves> viewAllLeave() {
		List<Leaves> leaves = null;
		try {
			leaves = leavesDAO.viewAllLeave();
		} catch (ClassNotFoundException | SQLException e) {
			logger.severe(e.getMessage());
			e.printStackTrace();
		}
		return leaves;
	}

	public List<Leaves> checkLeaveStatus(String userId) {
		List<Leaves> status = null;
		try {
			status = leavesDAO.checkLeaveStatus(userId);
		} catch (ClassNotFoundException | SQLException e) {
			logger.severe(e.getMessage());
			e.printStackTrace();
		}
		return status;
	}

}
