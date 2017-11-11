package com.hungteshun.gms.manager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.hungteshun.gms.model.Course;
import com.hungteshun.gms.util.DbUtil;

/**
 * 课程管理类
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
	 * 添加课程
	 * 
	 * @param courseName
	 *            课程名称
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
			DbUtil.close(conn); // 必须关闭
		}
	}

	// 查询所有课程
	public List<Course> findCourseList() {
		String sql = "select * from t_course order by course_id";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Course> courseList = new ArrayList<Course>();
		try {
			conn = DbUtil.getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				int courseId = rs.getInt("course_id");
				String courseName = rs.getString("course_name");
				// 每个记录对应一个对象
				Course course = new Course();
				course.setCourseId(courseId);
				course.setCourseName(courseName);
				courseList.add(course);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DbUtil.close(pstmt);
			DbUtil.close(conn); // 必须关闭
		}
		return courseList;
	}

	/**
	 * 根据课程代码删除课程
	 * 
	 * @param courseId
	 */
	public void delCourse(int courseId) {
		String sql = "delete from t_course where course_id=?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = DbUtil.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, courseId);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DbUtil.close(pstmt);
			DbUtil.close(conn);
		}
	}

	/**
	 * 修改课程
	 * 
	 * @param courseId
	 * @param courseName
	 */
	public void modifyCourse(int courseId, String courseName) {
		String sql = "update t_course set course_name=? where course_id=?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = DbUtil.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, courseName);
			pstmt.setInt(2, courseId);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DbUtil.close(pstmt);
			DbUtil.close(conn);
		}
	}
}
