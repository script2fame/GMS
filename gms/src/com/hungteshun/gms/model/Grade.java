package com.hungteshun.gms.model;

/**
 * 成绩实体类
 * 
 * @author hungteshun
 *
 */
public class Grade {

	// 学生
	private Student student;

	// 课程
	private Course course;

	// 成绩
	private float grade;

	public Course getCourse() {
		return course;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public float getGrade() {
		return grade;
	}

	public void setGrade(float grade) {
		this.grade = grade;
	}

	public void setCourse(Course course) {
		this.course = course;
	}
}
