package com.hungteshun.gms.manager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.hungteshun.gms.model.Classes;
import com.hungteshun.gms.util.DbUtil;

/**
 * 班级管理类
 * 
 * @author hungteshun
 * 
 */
public class ClassesManager {

	private static ClassesManager instance = new ClassesManager();

	private ClassesManager() {
	}

	public static ClassesManager getInstance() {
		return instance;
	}

	/**
	 * 输出班级列表
	 */
	public void outClassesList() {
		Connection conn = null;
		try {
			conn = DbUtil.getConnection();
			outClassesList(conn, 0, 0);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DbUtil.close(conn);
		}
	}

	/**
	 * 递归读取班级树形结构
	 * 
	 * @param conn
	 * @param classesId
	 * @param level
	 * @throws SQLException
	 */
	private void outClassesList(Connection conn, int classesId, int level)
			throws SQLException {
		String sql = "select * from t_classes where pid=?";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			String s = "";
			for (int i = 0; i < level; i++) {
				s += "  ";
			}
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, classesId);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				System.out.println(s + rs.getInt("classes_id") + "【"
						+ rs.getString("classes_name") + "】");
				// 如果是非叶子
				if (rs.getInt("leaf") == 0) {
					// 调用自身，递归调用
					outClassesList(conn, rs.getInt("classes_id"), level + 1);
				}
			}
		} finally {
			DbUtil.close(rs);
			DbUtil.close(pstmt);
			// 不要关闭Connection
			// 资源最好遵循在哪打开，在哪关闭的原则
			// DbUtil.close(conn);
		}
	}

	/**
	 * 添加班级
	 * 
	 * @param pid
	 *            父id
	 * @param classesName
	 *            班级名称
	 */
	public void addClasses(int pid, String classesName) {
		String sql = "insert into t_classes(pid, classes_name) values(?, ?)";
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = DbUtil.getConnection();

			// 不再自动提交事务，手动控制事务
			DbUtil.setAutoCommit(conn, false);
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, pid);
			pstmt.setString(2, classesName);
			pstmt.executeUpdate();

			// 取得当前节点的父节点
			Classes parentClasses = findClassesById(pid);

			// 如果为叶子
			if (parentClasses.getLeaf() == 1) {
				// 修改为非叶子
				modifyLeaf(conn, parentClasses.getClassesId(), 0);
			}
			// 提交事务
			DbUtil.commit(conn);
		} catch (Exception e) {
			e.printStackTrace();
			// 回滚事务
			DbUtil.rollback(conn);
		} finally {
			DbUtil.close(pstmt);
			// 恢复连接的最初状态为自动提交
			DbUtil.setAutoCommit(conn, true);
			DbUtil.close(conn);
		}
	}

	/**
	 * 修改leaf字段
	 * 
	 * @param conn
	 * @param classesId
	 * @param leaf
	 *            1/0
	 */
	private void modifyLeaf(Connection conn, int classesId, int leaf) // {
			throws SQLException {
		String sql = "update t_classes set leaf=? where classes_id=?";
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, leaf);
			pstmt.setInt(2, classesId);
			pstmt.executeUpdate();
			// }catch(SQLException e) { //此种写法无法回滚事务，因为你把异常吃掉了，上边的方法不知道发生了错误
			// e.printStackTrace();
		} finally {
			DbUtil.close(pstmt);
		}
	}

	/**
	 * 根据id查询班级
	 * 
	 * @param classesId
	 * @return 如果存在返回Classes对象，否则返回null
	 */
	public Classes findClassesById(int classesId) {
		String sql = "select * from t_classes where classes_id=?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Classes classes = null;
		try {
			conn = DbUtil.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, classesId);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				classes = new Classes();
				classes.setClassesId(classesId);
				classes.setClassesName(rs.getString("classes_name"));
				classes.setPid(rs.getInt("pid"));
				classes.setLeaf(rs.getInt("leaf"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DbUtil.close(rs);
			DbUtil.close(pstmt);
			DbUtil.close(conn);
		}
		return classes;
	}

	public static void main(String[] args) {
		ClassesManager.getInstance().outClassesList();
	}
}
