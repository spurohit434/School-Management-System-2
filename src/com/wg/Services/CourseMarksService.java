package com.wg.Services;

import com.wg.DAO.CourseMarksDAO;

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
}