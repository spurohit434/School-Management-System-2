package com.wg.Services;

import java.sql.SQLException;
import java.util.List;
import java.util.logging.Logger;

import com.wg.DAO.AttendanceDAO;
import com.wg.Helper.LoggingUtil;
import com.wg.Model.Attendance;

public class AttendanceServices {
	private AttendanceDAO attendanceDAO;
	Logger logger = LoggingUtil.getLogger(AttendanceServices.class);

	public AttendanceServices() {

	}

	public AttendanceServices(AttendanceDAO attendanceDAO) {
		this.attendanceDAO = attendanceDAO;
	}

	public List<Attendance> viewAttendanceByStandard(int standard) {
		List<Attendance> list = null;
		try {
			try {
				list = attendanceDAO.viewAttendanceByStandard(standard);
			} catch (ClassNotFoundException e) {
				logger.severe(e.getMessage());
				e.printStackTrace();
			}
		} catch (SQLException e) {
			logger.severe(e.getMessage());
			e.printStackTrace();
		}
		return list;
	}

	public List<Attendance> viewAttendanceById(String studentId) {
		List<Attendance> list = null;
		try {
			list = attendanceDAO.viewAttendanceById(studentId);
		} catch (ClassNotFoundException | SQLException e) {
			logger.severe(e.getMessage());
			e.printStackTrace();
		}
		return list;
	}

	public boolean addAttendance(Attendance attendance) {
		boolean flag = false;
		try {
			flag = attendanceDAO.addAttendance(attendance);
			return flag;
		} catch (ClassNotFoundException | SQLException e) {
			logger.severe(e.getMessage());
			e.printStackTrace();
		}
		return flag;
	}

}
