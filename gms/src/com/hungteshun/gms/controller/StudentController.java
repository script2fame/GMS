package com.hungteshun.gms.controller;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import com.hungteshun.gms.manager.StudentManager;
import com.hungteshun.gms.model.Classes;
import com.hungteshun.gms.model.Student;
import com.hungteshun.gms.util.DateUtil;
import com.hungteshun.gms.util.ExamConfigReader;
import com.hungteshun.gms.util.Exportutil;

public class StudentController {

	private static final String ADD = "1";

	private static final String DEL = "2";

	private static final String MODIFY = "3";

	private static final String QUERY = "4";
	
	private static final String EXPORT = "5";
	
	private static final String EXPORTEXCEL = "6";

	private static final String QUIT = "q";

	private static String state = "";

	private static StudentManager studentManager = null;

	static {
		String className = ExamConfigReader.getInstance().getPropertyValue("student-manager-impl");
		try {
			studentManager = (StudentManager) Class.forName(className).newInstance();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		System.out.println("===��������Ҫʹ�õĹ������===");
		System.out.println("=======================");
		System.out.println("1-���ѧ��");
		System.out.println("2-ɾ��ѧ��");
		System.out.println("3-�޸�ѧ��");
		System.out.println("4-��ѯѧ��");
		System.out.println("5-����ѧ����Ϣ");
		System.out.println("6-��������excel�ļ�");
		System.out.println("q-�˳�");
		System.out.println("=======================");
		BufferedReader br = null;
		try {
			br = new BufferedReader(new InputStreamReader(System.in));
			String s = null;
			while ((s = br.readLine()) != null) {
				if (ADD.equals(s)) {
					state = ADD;
					System.out.println("��������ӵ�ѧ��(student_name=#,sex=#,birthday=#,contac_tel=#,address=#,classes_id=#):");
				} else if (DEL.equals(s)) {
					state = DEL;
					System.out.println("������ɾ����ѧ������(student_id=#):");
				} else if (MODIFY.equals(s)) {
					state = MODIFY;
					System.out.println("�������޸ĵ�ѧ����Ϣ(student_id=#,student_name=#,sex=#,birthday=#,contac_tel=#,address=#,classes_id=#):");
				} else if (QUERY.equals(s)) {
					state = QUERY;
					System.out.println("��ҳ��ѯѧ����������(pageNo=#,pageSize=#):");
				} else if (EXPORT.equals(s)) {
					state = EXPORT;
					System.out.println("����س���ѧ����Ϣ�������ļ�");
				} else if(EXPORTEXCEL.equals(s)){
					state = EXPORTEXCEL;
					System.out.println("����س�����ѧ����Ϣ����excel");
				} else if (QUIT.equalsIgnoreCase(s)) {
					state = QUIT;
					System.out.println("�Ƿ�ȷ���˳���Y|N");
				} else if (ADD.equals(state)) {
					addStudent(s);
				} else if (DEL.equals(state)) {
					delStudent(s);
				} else if (MODIFY.equals(state)) {
					modifyStudent(s);
				} else if (EXPORT.equals(state)) {
					exportToTxt();
				} else if(EXPORTEXCEL.equals(state)){
					Exportutil.ExportToExcel();
				} else if (QUERY.equals(state)) {
					outStudent(s);
				} else if (QUIT.equals(state)) {
					if ("Y".equalsIgnoreCase(s)) {
						System.err.println("�ɹ��˳���");
						break;
					} else {
						System.out.println("����ϵͳ�����������:");
						System.out.println("=======================");
						System.out.println("1-���ѧ��");
						System.out.println("2-ɾ��ѧ��");
						System.out.println("3-�޸�ѧ��");
						System.out.println("4-��ѯѧ��");
						System.out.println("5-����ѧ����Ϣ");
						System.out.println("6-��������excel�ļ�");
						System.out.println("q-�˳�");
						System.out.println("=======================");
						continue;
					}
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


	/**
	 * ����Student����
	 * 
	 * @param s
	 * @return
	 */
	private static Student makeStudent(String s) {
		String[] studentArray = s.split(",");
		Student student = new Student();
		for (int i = 0; i < studentArray.length; i++) {
			String[] values = studentArray[i].split("=");
			if ("student_id".equals(values[0])) {
				student.setStudentId(Integer.parseInt(values[1]));
			} else if ("student_name".equals(values[0])) {
				student.setStudentName(values[1]);
			} else if ("sex".equals(values[0])) {
				student.setSex(values[1]);
			} else if ("birthday".equals(values[0])) {
				try {
					student.setBirthday(new SimpleDateFormat("yyyy-MM-dd").parse(values[1]));
				} catch (ParseException e) {
					e.printStackTrace();
				}
			} else if ("contac_tel".equals(values[0])) {
				student.setContactTel(values[1]);
			} else if ("address".equals(values[0])) {
				student.setAddress(values[1]);
			} else if ("classes_id".equals(values[0])) {
				// ����༶����
				Classes classes = new Classes();
				classes.setClassesId(Integer.parseInt(values[1]));
				// ѧ���Ͱ༶��������
				student.setClasses(classes);
			}
		}
		return student;
	}

	private static void addStudent(String s) {
		Student student = makeStudent(s);
		studentManager.addStudent(student);
		System.out.println("���ѧ���ɹ�����");
	}

	private static void outStudent(String s) {
		String[] studentArray = s.split(",");
		int pageNo = Integer.parseInt(studentArray[0].split("=")[1]);
		int pageSize = Integer.parseInt(studentArray[1].split("=")[1]);
		List<Student> studentList = studentManager.findStudentList(pageNo,pageSize);
		for (int i = 0; i < studentList.size(); i++) {
			Student student = studentList.get(i);
			System.out.print("ѧ������:" + student.getStudentId());
			System.out.print(", ѧ������:" + student.getStudentName());
			System.out.print(", �Ա�:" + student.getSex());
			System.out.print(", ��������:"+ new SimpleDateFormat("yyyy/MM/dd").format(student.getBirthday()));
			System.out.print(", ��ϵ�绰:" + student.getContactTel());
			System.out.print(", ��ַ:" + student.getAddress());
			System.out.print(", �༶����:" + student.getClasses().getClassesName());
			// System.out.print(", ����:" + (new Date().getTime() -
			// student.getBirthday().getTime())/(1000*60*60*24)/365);
			// System.out.print(", ����:" + (System.currentTimeMillis() -
			// student.getBirthday().getTime())/(1000*60*60*24)/365);
			long b = 1000L * 60L * 60L * 24L * 365L;
			long a = System.currentTimeMillis()- student.getBirthday().getTime();
			System.out.print(", ����:" + a / b);
			System.out.println("");
		}
	}
	
	private static void modifyStudent(String s) {
		Student student = makeStudent(s);
		studentManager.modifyStudent(student);
		System.out.println("�޸�ѧ���ɹ�����");
	}
	
	private static void delStudent(String s) {
		int studentId = Integer.parseInt(s.split("=")[1]);
		studentManager.delStudent(studentId);
		System.out.println("ɾ��ѧ���ɹ�����");
	}
	
	private static void exportToTxt() {
		File file = new File("c:\\student");
		if (!file.exists()) {
			file.mkdir();
		}
		StringBuffer sbString = new StringBuffer();
		List<Student> studentList = studentManager.findStudentList();
		for (Iterator<Student> iter = studentList.iterator(); iter.hasNext();) {
			Student student = iter.next();
			sbString.append("ѧ������:")
			.append(student.getStudentId())
			.append("ѧ������:")
			.append(student.getStudentName())
			.append("�Ա�:")
			.append(student.getSex())
			.append("��������:")
			.append(DateUtil.formatDate(student.getBirthday()))
			.append("��ϵ�绰:")
			.append(student.getContactTel())
			.append("��ַ:")
			.append(student.getAddress())
			.append("�༶����:")
			.append(student.getClasses().getClassesName()) 
			.append("����:")
			.append(student.getAge())
			.append("\n");
		}
		
		String fileName = new SimpleDateFormat("yyyy��MM��dd��HHʱmm��ss��").format(new Date()) + ".dat";
		BufferedWriter bw = null;
		try {
			bw = new BufferedWriter(
					new FileWriter(
							new File(file, fileName)));
			bw.write(sbString.toString());
			System.out.println("�����ļ��ɹ����ļ�λ�ã�" + file.getPath() + "\\" + fileName);
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			if (bw != null) {
				try {
					bw.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
