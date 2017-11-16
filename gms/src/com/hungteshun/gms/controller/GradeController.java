package com.hungteshun.gms.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import com.hungteshun.gms.manager.GradeManager;
import com.hungteshun.gms.model.Grade;
import com.hungteshun.gms.util.ExamConfigReader;

/**
 * �ɼ�������
 * @author hungteshun
 *
 */
public class GradeController {

	private static final String ADD = "1";
	
	private static final String DEL = "2";
	
	private static final String MODIFY = "3";
	
	private static final String QUERY_STUDENT_ID = "4";
	
	private static final String QUERY_TOP3 = "6";
	
	private static final String QUERY = "7";
	
	private static final String QUIT = "q";
	
	private static String state = "";
	
	private static GradeManager gradeManager;
	
	static {
		String className = ExamConfigReader.getInstance().getPropertyValue("grade-manager-impl");
		try {
			Class<?> c = Class.forName(className);
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
		System.out.println("1-��ӳɼ�");
		System.out.println("2-ɾ���ɼ�");
		System.out.println("3-�޸ĳɼ�");
		System.out.println("4-����ѧ�������ѯ�ɼ�");
		System.out.println("5-��ѯÿ����߷�");
		System.out.println("6-��ѯ�ܷ�ǰ����");
		System.out.println("7-��ҳ��ѯ");
		System.out.println("q-�˳�");
		BufferedReader br = null;
		try {
			br = new BufferedReader(new InputStreamReader(System.in));
			String s = null;
			while ((s = br.readLine()) != null) {
				if (ADD.equals(s)) {
					state = ADD;
					System.out.println("��������ӵĳɼ�(student_id=#,course_id=#,grade=#):");
				}else if (DEL.equals(s)) {
					state = DEL;
					System.out.println("������ɾ����ѧ������Ϳγ̴���(student_id=#,course_id=#):");
				}else if (MODIFY.equals(s)) {
					state = MODIFY;
					System.out.println("�������޸ĵĳɼ�(student_id=#,course_id=#,grade=#):");
				}else if (QUERY_STUDENT_ID.equals(s)) {
					state = QUERY_STUDENT_ID;
					System.out.println("����ѧ�������ѯ�ɼ�(student_id=#):");
				}else if (QUERY_TOP3.equals(s)) {
					state = QUERY_TOP3;
					System.out.println("�س���ѯ�ܷ�ǰ����");
				}else if (QUERY.equals(s)) {
					state = QUERY;
					System.out.println("��ҳ��ѯѧ���ɼ�(pageNo=#,pageSize=#):");
				}else if (QUIT.equalsIgnoreCase(s)){
					break;
				}else if (ADD.equals(state)) {
					addGrade(s);
				}else if (DEL.equals(state)) {
					delGrade(s);
				}else if (MODIFY.equals(state)) {
					modifyGrade(s);
				}else if (QUERY_STUDENT_ID.equals(state)) {
					findGradeByStudentById(s);
				}else if (QUERY_TOP3.equals(state)) {
					findGradeListTop3();
				}else if (QUERY.equals(state)) {
					findGradeList(s);
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
	
	private static void addGrade(String s) {
		Map<String, Number> paramMap = parseParam(s);
		int studentId = (Integer)paramMap.get("student_id");
		int courseId = (Integer)paramMap.get("course_id");
		float grade = (Float)paramMap.get("grade");
		gradeManager.addGrade(studentId, courseId, grade);
		System.out.println("��ӳɼ��ɹ�������");
	}
	
	
	private static Map<String, Number> parseParam(String s) {
		Map<String, Number> paramMap = new HashMap<String, Number>();
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
		Map<String, Number> paramMap = parseParam(s);
		int studentId = (Integer)paramMap.get("student_id");
		int courseId = (Integer)paramMap.get("course_id");
		gradeManager.delGrade(studentId, courseId);
		System.out.println("ɾ���ɼ��ɹ�������");
	}
	
	private static void modifyGrade(String s) {
		Map<String, Number> paramMap = parseParam(s);
		int studentId = (Integer)paramMap.get("student_id");
		int courseId = (Integer)paramMap.get("course_id");
		float grade = (Float)paramMap.get("grade");
		gradeManager.modifyGrade(studentId, courseId, grade);
		System.out.println("�޸ĳɼ��ɹ�������");
	}
	
	private static void findGradeByStudentById(String s) {
		Map<String, Number> paramMap = parseParam(s);
		int studentId = (Integer)paramMap.get("student_id");
		List<Grade> gradeList = gradeManager.findGradeListByStudentId(studentId);
		printGradeList(gradeList);
	}
	
	private static void findGradeList(String s) {
		Map<String, Number> paramMap = parseParam(s);
		int pageNo = (Integer)paramMap.get("pageNo");
		int pageSize = (Integer)paramMap.get("pageSize");
		List<Grade> gradeList = gradeManager.findGradeList(pageNo, pageSize);
		printGradeList(gradeList);
	}

	private static void printGradeList(List<?> gradeList) {
		for (Iterator<?> iter = gradeList.iterator(); iter.hasNext();) {
			Grade grade = (Grade) iter.next();
			System.out.print("ѧ������:" + grade.getStudent().getStudentId());
			System.out.print(" ѧ������:" + grade.getStudent().getStudentName());
			System.out.print(" �����༶:"
					+ grade.getStudent().getClasses().getClassesName());
			System.out.print(" �γ�����:" + grade.getCourse().getCourseName());
			System.out.println(" �ɼ�:"
					+ new DecimalFormat("####.00").format(grade.getGrade()));
		}
	}
	
	private static void findGradeListTop3() {
		List<Grade> gradeList = gradeManager.findGradeListTop3();
		for (Iterator<Grade> iter = gradeList.iterator(); iter.hasNext();) {
			Grade grade = (Grade) iter.next();
			System.out.print("ѧ������:" + grade.getStudent().getStudentId());
			System.out.print(" ѧ������:" + grade.getStudent().getStudentName());
			System.out.print(" �����༶:"
					+ grade.getStudent().getClasses().getClassesName());
			System.out.println(" �ɼ�:"
					+ new DecimalFormat("####.00").format(grade.getGrade()));
		}
	}
}
