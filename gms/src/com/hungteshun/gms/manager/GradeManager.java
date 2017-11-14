package com.hungteshun.gms.manager;

import java.util.List;

import com.hungteshun.gms.model.Grade;

/**
 * 成绩接口
 * @author hungteshun
 *
 */
public interface GradeManager {

	/**
	 * 添加成绩
	 * @param studentId
	 * @param courseId
	 * @param grade
	 */
	public void addGrade(int studentId, int courseId, float grade);
	
	/**
	 * 修改成绩
	 * @param studentId
	 * @param courseId
	 * @param grade
	 */
	public void modifyGrade(int studentId, int courseId, float grade);
	
	/**
	 * 根据学生代码和课程代码删除成绩
	 * @param studentId
	 * @param courseId
	 */
	public void delGrade(int studentId, int courseId);
	
	/**
	 * 根据学生代码查询成绩列表
	 * @param studentId
	 * @return
	 */
	public List<Grade> findGradeListByStudentId(int studentId);
	
	/**
	 * 查询每科最高分
	 * @return
	 */
	public List<Grade> findHigherGradeListOfPerCourse();
	
	/**
	 * 查询前三名
	 * @return
	 */
	public List<Grade> findGradeListTop3();
	
	/**
	 * 分页查询
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public List<Grade> findGradeList(int pageNo, int pageSize);
}
