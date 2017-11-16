package com.hungteshun.gms.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.List;

import com.hungteshun.gms.manager.CourseManager;
import com.hungteshun.gms.model.Course;

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
		System.out.println("===请输入您要使用的功能序号===");
		System.out.println("=======================");
		System.out.println("1-添加课程");
		System.out.println("2-删除课程");
		System.out.println("3-修改课程");
		System.out.println("4-查询课程");
		System.out.println("q-退出");
		System.out.println("=======================");
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
					state = QUIT;
					System.out.println("是否确定退出？Y|N");
				} else if (ADD.equals(state)) {
					String[] courseArray = s.split("=");
					// 取得课程名称
					String courseName = courseArray[1];
					CourseManager.getInstance().addCourse(courseName);
					System.out.println("添加课程成功！");
				} else if (DEL.equals(state)) {
					// 输入的课程id形如：course_id=#
					String[] courseArray = s.split("=");
					CourseManager.getInstance().delCourse(Integer.parseInt(courseArray[1]));
					System.out.println("删除课程成功！！");
				} else if (MODIFY.equals(state)) {
					// course_id=#,course_name=#
					// course_name=#,course_id=#
					String[] courseArray = s.split(",");
					int courseId = 0;
					String courseName = "";
					for (int i = 0; i < courseArray.length; i++) {
						String[] values = courseArray[i].split("=");
						if ("course_id".equals(values[0])) {
							courseId = Integer.parseInt(values[1]);
						} else if ("course_name".equals(values[0])) {
							courseName = values[1];
						}
					}
					if (courseArray.length > 0) {
						CourseManager.getInstance().modifyCourse(courseId, courseName);
						System.out.println("修改课程成功！！");
					}
				} else if (QUERY.equals(state)) {
					List<Course> courseList = CourseManager.getInstance().findCourseList();
					System.out.println("=========课程列表========");
					for (Iterator<Course> iter = courseList.iterator(); iter.hasNext();) {
						Course course = iter.next();
						System.out.println(course.getCourseId() + ", " + course.getCourseName());
					}
				}else if (QUIT.equals(state)) {
					if ("Y".equalsIgnoreCase(s)) {
						System.err.println("成功退出！");
						break;
					} else {
						System.out.println("返回系统，请继续操作:");
						System.out.println("=======================");
						System.out.println("1-添加课程");
						System.out.println("2-删除课程");
						System.out.println("3-修改课程");
						System.out.println("4-查询课程");
						System.out.println("q-退出");
						System.out.println("=======================");
						continue;
					}
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
