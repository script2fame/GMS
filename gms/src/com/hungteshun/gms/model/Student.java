package com.hungteshun.gms.model;

import java.util.Date;

/**
 * ѧ��ʵ����
 * @author hungteshun
 *
 */
public class Student {

	//ѧ������
	private int studentId;
	
	//ѧ������
	private String studentName;
	
	//�Ա�
	private String sex;
	
	//��������
	private Date birthday;
	
	//��ϵ�绰
	private String contactTel;
	
	//��ַ
	private String address;
	
	//�����༶
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
