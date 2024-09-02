package com.wg.Services;

import java.sql.SQLException;
import java.util.List;

import com.wg.DAO.LeavesDAO;
import com.wg.Model.Leaves;

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
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public void applyLeave(Leaves leave) {
		try {
			leavesDAO.applyLeave(leave);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public List<Leaves> viewAllLeave() {
		List<Leaves> leaves = null;
		try {
			leaves = leavesDAO.viewAllLeave();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return leaves;
	}

	public List<Leaves> checkLeaveStatus(String userId) {
		List<Leaves> status = null;
		try {
			status = leavesDAO.checkLeaveStatus(userId);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return status;
	}

}
