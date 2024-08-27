package com.wg.Services;

import java.sql.SQLException;
import java.util.List;

import com.wg.DAO.LeavesDAO;
import com.wg.Model.Leaves;
import com.wg.Model.LeavesStatus;

public class LeavesService {
	private LeavesDAO leavesDAO;

	public LeavesService() {
	}

	public LeavesService(LeavesDAO leavesDAO) {
		this.leavesDAO = leavesDAO;
	}

	public void approveLeave(String userId) {
		try {
			leavesDAO.approveLeave(userId);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void applyLeave(Leaves leave) {
		try {
			// String leaves = leavesDAO.applyLeave(leave);
			// return leaves;
			leavesDAO.applyLeave(leave);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		// return "null";
	}

	public List<Leaves> viewAllLeave() {
		List<Leaves> leaves = leavesDAO.viewAllLeave();
		return leaves;
	}

	public LeavesStatus checkLeaveStatus(String userId) {
		LeavesStatus status = leavesDAO.checkLeaveStatus(userId);
		return status;
	}

}
