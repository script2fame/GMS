package com.hungteshun.gms.model;

import java.util.Date;

/**
 * 学生实体类
 * @author hungteshun
 *
 */
public class Student {

	//学生代码
	private int studentId;
	
	//学生姓名
	private String studentName;
	
	//性别
	private String sex;
	
	//出生日期
	private Date birthday;
	
	//联系电话
	private String contactTel;
	
	//地址
	private String address;
	
	//所属班级
	private Classes classes;

	public int getStudentId() {
		return studentId;
	}

	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}

	public String getStudentName() {
		return studentName;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getContactTel() {
		return contactTel;
	}

	public void setContactTel(String contactTel) {
		this.contactTel = contactTel;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Classes getClasses() {
		return classes;
	}

	public void setClasses(Classes classes) {
		this.classes = classes;
	}
	
}
