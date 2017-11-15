package com.hungteshun.gms.manager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import com.hungteshun.gms.model.Grade;
import com.hungteshun.gms.util.DbUtil;

public class GradeManagerImpl implements GradeManager {

	public void addGrade(int studentId, int courseId, float grade) {
		String sql = "insert into t_grade(student_id, course_id, grade) "
				+ "values(?, ?, ?)";
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = DbUtil.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, studentId);
			pstmt.setInt(2, courseId);
			pstmt.setFloat(3, grade);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DbUtil.close(pstmt);
			DbUtil.close(conn);
		}
	}

	public void delGrade(int studentId, int courseId) {
		String sql = "delete from t_grade where student_id=? and course_id=?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = DbUtil.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, studentId);
			pstmt.setInt(2, courseId);
			pstmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			DbUtil.close(pstmt);
			DbUtil.close(conn);
		}
	}

	public List<Grade> findGradeList(int pageNo, int pageSize) {
		// TODO 完成SQL的写法
		return null;
	}

	public List<Grade> findGradeListByStudentId(int studentId) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Grade> findGradeListTop3() {
		// TODO 完成SQL的写法
		return null;
	}

	public List<Grade> findHigherGradeListOfPerCourse() {
		// TODO 完成SQL的写法
		return null;
	}

	public void modifyGrade(int studentId, int courseId, float grade) {
		String sql = "update t_grade set grade=? where student_id=? and course_id=?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = DbUtil.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setFloat(1, grade);
			pstmt.setInt(2, studentId);
			pstmt.setInt(3, courseId);
			pstmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			DbUtil.close(pstmt);
			DbUtil.close(conn);
		}
	}

}
