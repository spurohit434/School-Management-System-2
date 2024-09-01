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

	public void addMarks(CourseMarks courseMarks) {
		courseMarksDAO.addMarks(courseMarks);
	}

	public List<CourseMarks> checkMarks(String userId) {
		return courseMarksDAO.checkMarks(userId);
	}
}