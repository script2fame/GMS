package com.hungteshun.gms.model;

/**
 * 课程实体类
 * 
 * @author hungteshun
 *
 */
public class Course {

	// 课程代码
	private int courseId;

	// 课程名称
	private String courseName;

	public int getCourseId() {
		return courseId;
	}

	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

}
