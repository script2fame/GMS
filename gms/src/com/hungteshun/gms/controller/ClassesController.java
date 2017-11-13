package com.hungteshun.gms.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import com.hungteshun.gms.manager.ClassesManager;

/**
 * 班级控制器
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
		System.out.println("1-添加班级");
		System.out.println("2-删除班级");
		System.out.println("3-修改班级");
		System.out.println("4-查询班级");
		System.out.println("q-退出");
		BufferedReader br = null;
		try {
			br = new BufferedReader(new InputStreamReader(System.in));
			String s = null;
			while ((s = br.readLine()) != null) {
				if (ADD.equals(s)) {
					state = ADD;
					System.out.println("请输入添加的班级，格式为：(pid=#,classes_name=#):");
				} else if (DEL.equals(s)) {
					state = DEL;
					System.out.println("请输入删除的班级代码(classes_id=#):");
				} else if (MODIFY.equals(s)) {
					state = MODIFY;
					System.out.println("请输入修改的班级，格式为：(classes_id=#,classes_name=#):");
				} else if (QUERY.equals(s)) {
					state = QUERY;
					System.out.println("输入回车查询所有的班级");
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
					System.out.println("删除班级成功！！");
				} else if (MODIFY.equals(state)) {
					//classes_id=#,classes_name=#
					String[] studentArray = s.split(",");
					int classesId = Integer.parseInt(studentArray[0].split("=")[1].trim());
					String classesName = studentArray[1].split("=")[1];
					ClassesManager.getInstance().modifyClasses(classesId, classesName);
					System.out.println("修改班级成功！！");
				} else if (QUERY.equals(state)) {
					ClassesManager.getInstance().outClassesList();
				}
			}
			System.err.println("正常退出");
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
