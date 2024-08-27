package com.wg.Controller;

import java.util.List;

import com.wg.Model.Leaves;
import com.wg.Model.LeavesStatus;
import com.wg.Services.LeavesService;

public class LeavesController {
	private LeavesService leavesService;
	// private Scanner scanner = new Scanner(System.in);

	public LeavesController() {
	}

	public LeavesController(LeavesService leavesService) {
		this.leavesService = leavesService;
	}

	public void approveLeave(String userId) {
		leavesService.approveLeave(userId);
	}

	public void applyLeave(Leaves leave) {
//		String leaves = leavesService.applyLeave(leave);
//		return leaves;
		leavesService.applyLeave(leave);
	}

	public List<Leaves> viewAllLeave() {
		List<Leaves> leaves = leavesService.viewAllLeave();
		return leaves;
	}

	public LeavesStatus checkLeaveStatus(String userId) {
		return leavesService.checkLeaveStatus(userId);
	}

}
