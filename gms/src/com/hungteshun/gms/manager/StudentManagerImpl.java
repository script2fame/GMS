package com.hungteshun.gms.manager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import com.hungteshun.gms.model.Student;
import com.hungteshun.gms.util.DbUtil;

public class StudentManagerImpl implements StudentManager {

	public void addStudent(Student student) {
		String sql = "insert into t_student(classes_id, student_name, sex, birthday, contact_tel, address) "
				+ "values(?, ?, ?, ?, ?, ?)";
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = DbUtil.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, student.getClasses().getClassesId());
			pstmt.setString(2, student.getStudentName());
			pstmt.setString(3, student.getSex());
			pstmt.setDate(4, new java.sql.Date(student.getBirthday().getTime()));
			pstmt.setString(5, student.getContactTel());
			pstmt.setString(6, student.getAddress());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DbUtil.close(pstmt);
			DbUtil.close(conn);
		}
	}

	public void delStudent(int studentId) {
		// TODO 删除学生

	}

	public List<Student> findStudentList(int pageNo, int pageSize) {
		// TODO 分页查询
		return null;
	}

	public void modifyStudent(Student student) {
		// TODO 修改学生

	}

}
