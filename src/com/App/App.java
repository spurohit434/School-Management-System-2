package com.App;

import java.sql.SQLException;

import com.wg.Controller.AttendanceController;
import com.wg.Controller.CourseController;
import com.wg.Controller.CourseMarksController;
import com.wg.Controller.FeeController;
import com.wg.Controller.IssueController;
import com.wg.Controller.LeavesController;
import com.wg.Controller.NotificationController;
import com.wg.Controller.UserController;
import com.wg.DAO.AttendanceDAO;
import com.wg.DAO.CourseDAO;
import com.wg.DAO.CourseMarksDAO;
import com.wg.DAO.FeeDAO;
import com.wg.DAO.IssueDAO;
import com.wg.DAO.LeavesDAO;
import com.wg.DAO.NotificationDAO;
import com.wg.DAO.UserDAO;
import com.wg.Services.AttendanceServices;
import com.wg.Services.CourseMarksService;
import com.wg.Services.CourseService;
import com.wg.Services.FeeService;
import com.wg.Services.IssueService;
import com.wg.Services.LeavesService;
import com.wg.Services.NotificationService;
import com.wg.Services.UserService;
import com.wg.UI.StartMenu;

public class App {
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		UserDAO dao = new UserDAO();
		UserService service = new UserService(dao);
		UserController controller = new UserController(service);
		CourseDAO courseDAO = new CourseDAO();
		CourseService courseService = new CourseService(courseDAO);
		CourseController courseController = new CourseController(courseService);
		AttendanceDAO attendanceDAO = new AttendanceDAO();
		AttendanceServices attendanceService = new AttendanceServices(attendanceDAO);
		AttendanceController attendanceController = new AttendanceController(attendanceService);
		FeeDAO feeDAO = new FeeDAO();
		FeeService feeService = new FeeService(feeDAO);
		FeeController feeController = new FeeController(feeService);
		NotificationDAO notificationDAO = new NotificationDAO();
		NotificationService notificationService = new NotificationService(notificationDAO);
		NotificationController notificationController = new NotificationController(notificationService);
		LeavesDAO leavesDAO = new LeavesDAO();
		LeavesService leavesService = new LeavesService(leavesDAO);
		LeavesController leavesController = new LeavesController(leavesService);
		IssueDAO issueDAO = new IssueDAO();
		IssueService issueService = new IssueService(issueDAO);
		IssueController issueController = new IssueController(issueService);
		CourseMarksDAO courseMarksDAO = new CourseMarksDAO();
		CourseMarksService courseMarksService = new CourseMarksService(courseMarksDAO);
		CourseMarksController courseMarksController = new CourseMarksController(courseMarksService);

		StartMenu menu = new StartMenu(controller, feeController, courseController, attendanceController,
				notificationController, leavesController, issueController, courseMarksController);
		menu.showStartMenu();
	}
}