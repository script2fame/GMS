package com.hungteshun.gms.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 封装数据库相关操作
 * 
 * @author hungteshun
 * 
 */
public class DbUtil {

	/**
	 * 取得数据库的连接
	 * 
	 * @return 一个数据库连接
	 */
	public static Connection getConnection() {
		Connection conn = null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			String dbUrl = "jdbc:oracle:thin:@127.0.0.1:1521:GMS";
			String username = "hungteshun";
			String password = "hungteshun";
			conn = DriverManager.getConnection(dbUrl, username, password);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}

	public static void close(PreparedStatement pstmt) {
		if (pstmt != null) {
			try {
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public static void close(Connection conn) {
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public static void close(ResultSet rs) {
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public static void setAutoCommit(Connection conn, boolean autoCommit) {
		try {
			if (conn != null) {
				if (conn.getAutoCommit() != autoCommit)
					conn.setAutoCommit(autoCommit);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void commit(Connection conn) {
		try {
			if (conn != null) {
				if (!conn.getAutoCommit()) {
					conn.commit();
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void rollback(Connection conn) {
		try {
			if (conn != null) {
				if (!conn.getAutoCommit()) {
					conn.rollback();
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		Connection conn = DbUtil.getConnection();
		System.out.println(conn);
	}
}
