package com.wg.Controller;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

import com.wg.Model.Attendance;
import com.wg.Model.Status;
import com.wg.Services.AttendanceServices;

public class AttendanceController {
	private AttendanceServices attendanceService;
	Scanner scanner = new Scanner(System.in);

	public AttendanceController(AttendanceServices attendanceService) {
		this.attendanceService = attendanceService;
	}

	public AttendanceController() {
	}

	public boolean addAttendance(String studentId, int standard, LocalDate date, Status status) {
		Attendance attendance = new Attendance(studentId, standard, date, status);
		attendanceService.addAttendance(attendance);
		return true;
	}

	public List<Attendance> viewAttendanceByStandard(int standard) {
		List<Attendance> list = null;
		list = attendanceService.viewAttendanceByStandard(standard);
		return list;
	}

	public List<Attendance> viewAttendanceById(String studentId) {
		List<Attendance> list = null;
		list = attendanceService.viewAttendanceById(studentId);
		return list;
	}
}
