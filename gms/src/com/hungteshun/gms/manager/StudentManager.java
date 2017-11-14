package com.hungteshun.gms.manager;

import java.util.List;
import com.hungteshun.gms.model.Student;

/**
 * 学生管理接口
 * 
 * @author hungteshun
 */
public interface StudentManager {

	/**
	 * 添加学生
	 * 
	 * @param student
	 */
	public void addStudent(Student student);

	/**
	 * 根据学生代码删除
	 * 
	 * @param studentId
	 */
	public void delStudent(int studentId);

	/**
	 * 修改学生
	 * 
	 * @param student
	 */
	public void modifyStudent(Student student);

	/**
	 * 分页查询
	 * 
	 * @param pageNo
	 *            第几页
	 * @param pageSize
	 *            每页多少条
	 * @return
	 */
	public List<Student> findStudentList(int pageNo, int pageSize);

	/**
	 * 查询所有的学生
	 * @return
	 */
	public List<Student> findStudentList();
}
