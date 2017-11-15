package com.hungteshun.gms.manager;

import java.util.List;

import com.hungteshun.gms.model.Grade;

/**
 * �ɼ��ӿ�
 * @author hungteshun
 *
 */
public interface GradeManager {

	/**
	 * ��ӳɼ�
	 * @param studentId
	 * @param courseId
	 * @param grade
	 */
	public void addGrade(int studentId, int courseId, float grade);
	
	/**
	 * �޸ĳɼ�
	 * @param studentId
	 * @param courseId
	 * @param grade
	 */
	public void modifyGrade(int studentId, int courseId, float grade);
	
	/**
	 * ����ѧ������Ϳγ̴���ɾ���ɼ�
	 * @param studentId
	 * @param courseId
	 */
	public void delGrade(int studentId, int courseId);
	
	/**
	 * ����ѧ�������ѯ�ɼ��б�
	 * @param studentId
	 * @return
	 */
	public List<Grade> findGradeListByStudentId(int studentId);
	
	/**
	 * ��ѯÿ����߷�
	 * @return
	 */
	public List<Grade> findHigherGradeListOfPerCourse();
	
	/**
	 * ��ѯǰ����
	 * @return
	 */
	public List<Grade> findGradeListTop3();
	
	/**
	 * ��ҳ��ѯ
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public List<Grade> findGradeList(int pageNo, int pageSize);
}
