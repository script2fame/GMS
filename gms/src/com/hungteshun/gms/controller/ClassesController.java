package com.hungteshun.gms.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import com.hungteshun.gms.manager.ClassesManager;

/**
 * �༶������
 * 
 * @author Administrator
 * 
 */
public class ClassesController {

	private static final String ADD = "1";

	private static final String DEL = "2";

	private static final String MODIFY = "3";

	private static final String QUERY = "4";

	private static final String QUIT = "q";

	private static String state = "";

	public static void main(String[] args) {
		System.out.println("1-��Ӱ༶");
		System.out.println("2-ɾ���༶");
		System.out.println("3-�޸İ༶");
		System.out.println("4-��ѯ�༶");
		System.out.println("q-�˳�");
		BufferedReader br = null;
		try {
			br = new BufferedReader(new InputStreamReader(System.in));
			String s = null;
			while ((s = br.readLine()) != null) {
				if (ADD.equals(s)) {
					state = ADD;
					System.out.println("��������ӵİ༶����ʽΪ��(pid=#,classes_name=#):");
				} else if (DEL.equals(s)) {
					state = DEL;
					System.out.println("������ɾ���İ༶����(classes_id=#):");
				} else if (MODIFY.equals(s)) {
					state = MODIFY;
					System.out.println("�������޸ĵİ༶����ʽΪ��(classes_id=#,classes_name=#):");
				} else if (QUERY.equals(s)) {
					state = QUERY;
					System.out.println("����س���ѯ���еİ༶");
				} else if (QUIT.equalsIgnoreCase(s)) {
					break;
				} else if (ADD.equals(state)) {
					// pid=#,classes_name=#
					int pid = Integer.parseInt(s.substring(s.indexOf("=") + 1,s.indexOf(",")));
					String classesName = s.substring(s.lastIndexOf("=") + 1,s.length());
					ClassesManager.getInstance().addClasses(pid, classesName);
				} else if (DEL.equals(state)) {
					//classes_id=#	
					int classesId = Integer.parseInt(s.split("=")[1]);
					ClassesManager.getInstance().delClasses(classesId);
					System.out.println("ɾ���༶�ɹ�����");
				} else if (MODIFY.equals(state)) {
					//classes_id=#,classes_name=#
					String[] studentArray = s.split(",");
					int classesId = Integer.parseInt(studentArray[0].split("=")[1].trim());
					String classesName = studentArray[1].split("=")[1];
					ClassesManager.getInstance().modifyClasses(classesId, classesName);
					System.out.println("�޸İ༶�ɹ�����");
				} else if (QUERY.equals(state)) {
					ClassesManager.getInstance().outClassesList();
				}
			}
			System.err.println("�����˳�");
		} catch (IOException e) {
			// TODO Auto-generated catch block
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
