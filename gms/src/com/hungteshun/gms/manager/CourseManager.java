package com.hungteshun.gms.manager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.hungteshun.gms.util.DbUtil;

/**
 * �γ̹�����
 * 
 * @author hungteshun
 *
 */
public class CourseManager {

	private static CourseManager instance = new CourseManager();

	private CourseManager() {
	}

	public static CourseManager getInstance() {
		return instance;
	}

	/**
	 * ��ӿγ�
	 * 
	 * @param courseName
	 *            �γ�����
	 */
	public void addCourse(String courseName) {
		String sql = "insert into t_course(course_name) values(?)";
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = DbUtil.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, courseName);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DbUtil.close(pstmt);
			DbUtil.close(conn); // ����ر�
		}
	}
}
