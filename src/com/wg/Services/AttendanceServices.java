package com.wg.Services;

import java.sql.SQLException;
import java.util.List;

import com.wg.DAO.AttendanceDAO;
import com.wg.Model.Attendance;

public class AttendanceServices {
	private AttendanceDAO attendanceDAO;

	public AttendanceServices() {

	}

	public AttendanceServices(AttendanceDAO attendanceDAO) {
		this.attendanceDAO = attendanceDAO;
	}

	public List<Attendance> viewAttendanceByStandard(int standard) {
		List<Attendance> list = null;
		try {
			list = attendanceDAO.viewAttendanceByStandard(standard);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	public List<Attendance> viewAttendanceById(String studentId) {
		List<Attendance> list = null;
		try {
			list = attendanceDAO.viewAttendanceById(studentId);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	public void addAttendance(Attendance attendance) {
		try {
			attendanceDAO.addAttendance(attendance);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

}
