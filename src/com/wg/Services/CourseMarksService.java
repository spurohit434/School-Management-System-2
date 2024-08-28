package com.wg.Services;

import java.util.List;

import com.wg.DAO.CourseMarksDAO;
import com.wg.Model.CourseMarks;

public class CourseMarksService {
	private CourseMarksDAO courseMarksDAO;

	public CourseMarksService() {

	}

	public CourseMarksService(CourseMarksDAO courseMarksDAO) {
		this.courseMarksDAO = courseMarksDAO;
	}

	public void addMarks(String userId, String courseId, double marks) {
		courseMarksDAO.addMarks(userId, courseId, marks);
	}

	public List<CourseMarks> checkMarks(String userId) {
		return courseMarksDAO.checkMarks(userId);
	}
}