package com.hungteshun.gms.model;

/**
 * �ɼ�ʵ����
 * 
 * @author hungteshun
 *
 */
public class Grade {

	// ѧ��
	private Student student;

	// �γ�
	private Course course;

	// �ɼ�
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
