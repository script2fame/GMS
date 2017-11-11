package com.hungteshun.gms.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import com.hungteshun.gms.manager.CourseManager;

/**
 * 课程控制器
 * 
 * @author hungteshun
 * 
 */
public class CourseController {

	private static final String ADD = "1";

	private static final String DEL = "2";

	private static final String MODIFY = "3";

	private static final String QUERY = "4";

	private static final String QUIT = "q";

	private static String state = "";

	public static void main(String[] args) {
		System.out.println("1-添加课程");
		System.out.println("2-删除课程");
		System.out.println("3-修改课程");
		System.out.println("4-查询课程");
		System.out.println("q-退出");
		BufferedReader br = null;
		try {
			br = new BufferedReader(new InputStreamReader(System.in));
			String s = null;
			while ((s = br.readLine()) != null) {
				if (ADD.equals(s)) {
					System.out.println("请输入添加的课程(course_name=#):");
					state = ADD;
				} else if (DEL.equals(s)) {
					System.out.println("请输入删除的课程代码(course_id=#):");
					state = DEL;
				} else if (MODIFY.equals(s)) {
					System.out.println("请输入修改的课程(course_id=#,course_name=#):");
					state = MODIFY;
				} else if (QUERY.equals(s)) {
					System.out.println("输入回车查询所有的课程列表");
					state = QUERY;
				} else if (QUIT.equalsIgnoreCase(s)) {
					break;
				} else if (ADD.equals(state)) {
					String[] courseArray = s.split("=");
					// 取得课程名称
					String courseName = courseArray[1];
					CourseManager.getInstance().addCourse(courseName);
					System.out.println("添加课程成功！");
				} else if (DEL.equals(state)) {

				} else if (MODIFY.equals(state)) {

				} else if (QUERY.equals(state)) {

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
