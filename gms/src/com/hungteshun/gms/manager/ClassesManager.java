package com.hungteshun.gms.manager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.hungteshun.gms.util.DbUtil;

/**
 * 班级管理类
 * @author hungteshun
 *
 */
public class ClassesManager {

	private static ClassesManager instance = new ClassesManager();
	
	private ClassesManager() {}
	
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
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DbUtil.close(conn);
		}
	}
	
	/**
	 * 递归读取班级树形结构
	 * @param conn
	 * @param classesId
	 * @param level
	 * @throws SQLException 
	 */
	private void outClassesList(Connection conn, int classesId, int level) throws SQLException {
		String sql = "select * from t_classes where pid=?";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			String s = "";
			for (int i=0; i<level; i++) {
				s+="  ";
			}
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, classesId);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				System.out.println(s + rs.getInt("classes_id") + "【" + rs.getString("classes_name") + "】" );
				//如果是非叶子
				if (rs.getInt("leaf") == 0) {
					//调用自身，递归调用
					outClassesList(conn, rs.getInt("classes_id"), level+1);
				}
			}
		}finally {
			DbUtil.close(rs);
			DbUtil.close(pstmt);
			//不要关闭Connection
			//资源最好遵循在哪打开，在哪关闭的原则
			//DbUtil.close(conn);
		}
	}
	
	public static void main(String[] args) {
		ClassesManager.getInstance().outClassesList();
	}
}
