package com.hungteshun.gms.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

import com.hungteshun.gms.manager.GradeManager;
import com.hungteshun.gms.util.ExamConfigReader;

/**
 * 成绩控制器
 * @author hungteshun
 *
 */
public class GradeController {

	private static final String ADD = "1";
	
	private static final String DEL = "2";
	
	private static final String MODIFY = "3";
	
	private static final String QUERY = "4";
	
	private static final String QUIT = "q";
	
	private static String state = "";
	
	private static GradeManager gradeManager;
	
	static {
		String className = ExamConfigReader.getInstance().getPropertyValue("grade-manager-impl");
		try {
			Class c = Class.forName(className);
			gradeManager = (GradeManager)c.newInstance();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		System.out.println("1-添加成绩");
		System.out.println("2-删除成绩");
		System.out.println("3-修改成绩");
		System.out.println("4-根据学生代码查询成绩");
		System.out.println("5-查询每科最高分");
		System.out.println("6-查询总分前三名");
		System.out.println("7-分页查询");
		System.out.println("q-退出");
		BufferedReader br = null;
		try {
			br = new BufferedReader(new InputStreamReader(System.in));
			String s = null;
			while ((s = br.readLine()) != null) {
				if (ADD.equals(s)) {
					state = ADD;
					System.out.println("请输入添加的成绩(student_id=#,course_id=#,grade=#):");
				}else if (DEL.equals(s)) {
					state = DEL;
					delGrade(s);
				}else if (MODIFY.equals(s)) {
					state = MODIFY;
				}else if (QUERY.equals(s)) {
					state = QUERY;
				}else if (QUIT.equalsIgnoreCase(s)){
					break;
				}else if (ADD.equals(state)) {
					addGrade(s);
				}else if (DEL.equals(state)) {
				}else if (MODIFY.equals(state)) {
				}else if (QUERY.equals(state)) {
				}
			}
			System.err.println("正常退出");
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
	
	private static void addGrade(String s) {
		Map paramMap = parseParam(s);
		int studentId = (Integer)paramMap.get("student_id");
		int courseId = (Integer)paramMap.get("course_id");
		float grade = (Float)paramMap.get("grade");
		gradeManager.addGrade(studentId, courseId, grade);
		System.out.println("添加成绩成功！！！");
	}
	
	
	private static Map parseParam(String s) {
		Map paramMap = new HashMap();
		//student_id=#,course_id=#,grade=#
		StringTokenizer st1 = new StringTokenizer(s, ",");
		while (st1.hasMoreTokens()) {
			//student_id=#
			String t1 = st1.nextToken();
			StringTokenizer st2 = new StringTokenizer(t1, "=");
			if(st2.hasMoreTokens()) {
				String leftStr = st2.nextToken();
				if ("student_id".equals(leftStr)) {
					if (st2.hasMoreTokens()) {
						paramMap.put("student_id", Integer.parseInt(st2.nextToken()));
					}
				}else if ("course_id".equals(leftStr)) {
					if (st2.hasMoreTokens()) {
						paramMap.put("course_id",Integer.parseInt(st2.nextToken()));	
					}
				}else if ("grade".equals(leftStr)) {
					if (st2.hasMoreTokens()) {
						paramMap.put("grade", Float.parseFloat(st2.nextToken()));	
					}
				} 
			}
		}
		return paramMap;
	}	
	
	private static void delGrade(String s) {
		Map paramMap = parseParam(s);
		int studentId = (Integer)paramMap.get("student_id");
		int courseId = (Integer)paramMap.get("course_id");
		gradeManager.delGrade(studentId, courseId);
		System.out.println("删除成绩成功！！！");
	}
}
