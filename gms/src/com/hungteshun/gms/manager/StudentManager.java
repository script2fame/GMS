package com.hungteshun.gms.manager;

import java.util.List;
import com.hungteshun.gms.model.Student;

/**
 * ѧ������ӿ�
 * 
 * @author hungteshun
 */
public interface StudentManager {

	/**
	 * ���ѧ��
	 * 
	 * @param student
	 */
	public void addStudent(Student student);

	/**
	 * ����ѧ������ɾ��
	 * 
	 * @param studentId
	 */
	public void delStudent(int studentId);

	/**
	 * �޸�ѧ��
	 * 
	 * @param student
	 */
	public void modifyStudent(Student student);

	/**
	 * ��ҳ��ѯ
	 * 
	 * @param pageNo
	 *            �ڼ�ҳ
	 * @param pageSize
	 *            ÿҳ������
	 * @return
	 */
	public List<Student> findStudentList(int pageNo, int pageSize);

	/**
	 * ��ѯ���е�ѧ��
	 * @return
	 */
	public List<Student> findStudentList();
}
