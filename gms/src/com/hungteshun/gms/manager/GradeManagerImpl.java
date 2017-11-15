package com.hungteshun.gms.manager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.hungteshun.gms.model.Classes;
import com.hungteshun.gms.model.Course;
import com.hungteshun.gms.model.Grade;
import com.hungteshun.gms.model.Student;
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
		StringBuffer sbSql = new StringBuffer();
		sbSql.append("select student_id, student_name, classes_name, course_name, grade from ") 
		.append("(")
		.append("select rownum as rn, student_id, student_name, classes_name, course_name, grade from ") 
		.append("(")
		.append("select g.student_id, s.student_name, cls.classes_name, c.course_name, g.grade ")
		.append("from t_grade g join t_student s on g.student_id=s.student_id ")
		.append("join t_classes cls on s.classes_id=cls.classes_id ") 
		.append("join t_course c on g.course_id=c.course_id order by g.student_id,g.course_id ")
		.append(") where rownum <=? ")
		.append(") where rn>? ");	
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Grade> gradeList = null;
		try {
			conn = DbUtil.getConnection();
			pstmt = conn.prepareStatement(sbSql.toString());
			pstmt.setInt(1, pageNo*pageSize);
			pstmt.setInt(2, (pageNo-1)*pageSize);
			rs = pstmt.executeQuery();
			gradeList = makeGradeList(rs);
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DbUtil.close(rs);
			DbUtil.close(pstmt);
			DbUtil.close(conn); //必须关闭
		}
		return gradeList;		
	}

	public List<Grade> findGradeListByStudentId(int studentId) {
//		//sql92
//		select g.student_id, s.student_name, cls.classes_name, c.course_name, g.grade
//		from t_grade g, t_student s, t_classes cls, t_course c
//		where g.student_id=s.student_id and s.classes_id=cls.classes_id and g.course_id=c.course_id and g.student_id=10000

		StringBuffer sbSql = new StringBuffer();
		//sql99
		sbSql.append("select g.student_id, s.student_name, cls.classes_name, c.course_name, g.grade ")
		.append("from t_grade g join t_student s on g.student_id=s.student_id ") 
		.append("join t_classes cls on s.classes_id=cls.classes_id ") 
		.append("join t_course c on g.course_id=c.course_id ")
		.append("where g.student_id=?");		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Grade> gradeList = new ArrayList<Grade>();
		try {
			conn = DbUtil.getConnection();
			pstmt = conn.prepareStatement(sbSql.toString());
			pstmt.setInt(1, studentId);
			rs = pstmt.executeQuery();
			makeGradeList(rs);
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DbUtil.close(rs);
			DbUtil.close(pstmt);
			DbUtil.close(conn); //必须关闭
		}
		return gradeList;
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

	private List<Grade> makeGradeList(ResultSet rs) throws SQLException {
		List<Grade> gradeList = new ArrayList<Grade>();
		while (rs.next()) {
			Grade grade = new Grade();
			
			//学生
			Student student = new Student();
			student.setStudentId(rs.getInt("student_id"));
			student.setStudentName(rs.getString("student_name"));
			
			//班级
			Classes classes = new Classes();
			classes.setClassesName(rs.getString("classes_name"));
			
			//建立Student和Classes的关联
			student.setClasses(classes);
			
			//建立Grade和Student的关联
			grade.setStudent(student);
			
			//课程
			Course course = new Course();
			course.setCourseName(rs.getString("course_name"));
			
			//建立Grade和Course的关联
			grade.setCourse(course);
			
			grade.setGrade(rs.getFloat("grade"));
			
			gradeList.add(grade);
		}	
		return gradeList;
	}
}
