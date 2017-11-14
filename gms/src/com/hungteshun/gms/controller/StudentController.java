package com.hungteshun.gms.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import com.hungteshun.gms.manager.StudentManager;
import com.hungteshun.gms.manager.StudentManagerImpl;
import com.hungteshun.gms.model.Classes;
import com.hungteshun.gms.model.Student;

public class StudentController {

	private static final String ADD = "1";
	
	private static final String DEL = "2";
	
	private static final String MODIFY = "3";
	
	private static final String QUERY = "4";
	
	private static final String QUIT = "q";
	
	private static String state = "";
	
	public static void main(String[] args) {
		System.out.println("1-����ѧ��");
		System.out.println("2-ɾ��ѧ��");
		System.out.println("3-�޸�ѧ��");
		System.out.println("4-��ѯѧ��");
		System.out.println("q-�˳�");
		BufferedReader br = null;
		try {
			br = new BufferedReader(new InputStreamReader(System.in));
			String s = null;
			while ((s = br.readLine()) != null) {
				if (ADD.equals(s)) {
					System.out.println("���������ӵ�ѧ��(student_name=#,sex=#,birthday=#,contac_tel=#,address=#,classes_id=#):");
					state = ADD;
				}else if (DEL.equals(s)) {
					state = DEL;
				}else if (MODIFY.equals(s)) {
					state = MODIFY;
				}else if (QUERY.equals(s)) {
					state = QUERY;
				}else if (QUIT.equalsIgnoreCase(s)){
					break;
				}else if (ADD.equals(state)) {
					String[] studentArray = s.split(",");
					Student student = new Student();
					for (int i=0; i<studentArray.length; i++) {
						String[] values = studentArray[i].split("=");
						if ("student_name".equals(values[0])) {
							student.setStudentName(values[1]);
						}else if ("sex".equals(values[0])) {
							student.setSex(values[1]);	
						}else if ("birthday".equals(values[0])) {
							try {
								student.setBirthday(new SimpleDateFormat("yyyy-MM-dd").parse(values[1]));
							} catch (ParseException e) {
								e.printStackTrace();
							}	
						}else if ("contac_tel".equals(values[0])) {
							student.setContactTel(values[1]);
						}else if ("address".equals(values[0])) {
							student.setAddress(values[1]);
						}else if ("classes_id".equals(values[0])) {
							//����༶����
							Classes classes = new Classes();
							classes.setClassesId(Integer.parseInt(values[1]));
							//ѧ���Ͱ༶��������
							student.setClasses(classes);
						}
					}
					if(studentArray.length > 0) {
						StudentManager studentManager = new StudentManagerImpl();
						studentManager.addStudent(student);
					}
					System.out.println("����ѧ���ɹ�����");
				}else if (DEL.equals(state)) {
				}else if (MODIFY.equals(state)) {
				}else if (QUERY.equals(state)) {
				}
			}
			System.err.println("�����˳�");
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

}