package com.hungteshun.gms.manager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.hungteshun.gms.model.Classes;
import com.hungteshun.gms.util.DbUtil;

/**
 * �༶������
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
	 * ����༶�б�
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
	 * �ݹ��ȡ�༶���νṹ
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
				System.out.println(s + rs.getInt("classes_id") + "��"
						+ rs.getString("classes_name") + "��");
				// ����Ƿ�Ҷ��
				if (rs.getInt("leaf") == 0) {
					// ���������ݹ����
					outClassesList(conn, rs.getInt("classes_id"), level + 1);
				}
			}
		} finally {
			DbUtil.close(rs);
			DbUtil.close(pstmt);
			// ��Ҫ�ر�Connection
			// ��Դ�����ѭ���Ĵ򿪣����Ĺرյ�ԭ��
			// DbUtil.close(conn);
		}
	}

	/**
	 * ��Ӱ༶
	 * 
	 * @param pid
	 *            ��id
	 * @param classesName
	 *            �༶����
	 */
	public void addClasses(int pid, String classesName) {
		String sql = "insert into t_classes(pid, classes_name) values(?, ?)";
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = DbUtil.getConnection();

			// �����Զ��ύ�����ֶ���������
			DbUtil.setAutoCommit(conn, false);
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, pid);
			pstmt.setString(2, classesName);
			pstmt.executeUpdate();

			// ȡ�õ�ǰ�ڵ�ĸ��ڵ�
			Classes parentClasses = findClassesById(pid);

			// ���ΪҶ��
			if (parentClasses.getLeaf() == 1) {
				// �޸�Ϊ��Ҷ��
				modifyLeaf(conn, parentClasses.getClassesId(), 0);
			}
			// �ύ����
			DbUtil.commit(conn);
		} catch (Exception e) {
			e.printStackTrace();
			// �ع�����
			DbUtil.rollback(conn);
		} finally {
			DbUtil.close(pstmt);
			// �ָ����ӵ����״̬Ϊ�Զ��ύ
			DbUtil.setAutoCommit(conn, true);
			DbUtil.close(conn);
		}
	}

	/**
	 * �޸�leaf�ֶ�
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
			// }catch(SQLException e) { //����д���޷��ع�������Ϊ����쳣�Ե��ˣ��ϱߵķ�����֪�������˴���
			// e.printStackTrace();
		} finally {
			DbUtil.close(pstmt);
		}
	}

	/**
	 * ����id��ѯ�༶
	 * 
	 * @param classesId
	 * @return ������ڷ���Classes���󣬷��򷵻�null
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

	/**
	 * �޸İ༶
	 * 
	 * @param classesId
	 * @param classesName
	 */
	public void modifyClasses(int classesId, String classesName) {
		String sql = "update t_classes set classes_name=? where classes_id=?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = DbUtil.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, classesName);
			pstmt.setInt(2, classesId);
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DbUtil.close(pstmt);
			DbUtil.close(conn);
		}

	}

	/**
	 * ���ݰ༶����ɾ���༶
	 * 
	 * @param classesId
	 */
	public void delClasses(int classesId) {
		Connection conn = null;
		try {
			conn = DbUtil.getConnection();

			// �ֶ���������
			DbUtil.setAutoCommit(conn, false);

			// ����classesIdȡ�ð༶�༶������ҪΪ���޸���leaf״̬ΪҶ�ӣ�
			Classes classes = findClassesById(classesId);

			// �ݹ�ɾ���༶
			delClasses(conn, classesId);

			// �����ӽڵ����
			int count = getChildren(conn, classes.getPid());
			// ����������ӽڵ�
			if (count == 0) {
				// �޸�leafΪҶ��
				modifyLeaf(conn, classes.getPid(), 1);
			}
			// �ύ����
			DbUtil.commit(conn);
		} catch (Exception e) {
			e.printStackTrace();
			DbUtil.rollback(conn);
		} finally {
			DbUtil.close(conn);
		}
	}

	/**
	 * �ݹ�ɾ���༶
	 * 
	 * @param conn
	 * @param classesId
	 */
	private void delClasses(Connection conn, int classesId) throws Exception {
		String sql = "select * from t_classes where pid=?";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, classesId);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				// ��Ҷ��
				if (rs.getInt("leaf") == 0) {
					delClasses(conn, rs.getInt("classes_id"));
				} else {
					// ɾ��
					delClassesById(conn, rs.getInt("classes_id"));
				}
			}
			// ɾ������
			delClassesById(conn, classesId);
			// }catch(SQLException e) {
		} catch (Exception e) {
			e.printStackTrace();
			// throw new SQLException(e.toString());
			throw e;
		} finally {
			DbUtil.close(rs);
			DbUtil.close(pstmt);
		}
	}

	/**
	 * ɾ������İ༶
	 * 
	 * @param conn
	 * @param classesId
	 */
	private void delClassesById(Connection conn, int classesId)
			throws Exception {
		String sql = "delete from t_classes where classes_id=?";
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, classesId);
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			DbUtil.close(pstmt);
		}
	}

	/**
	 * ȡ�õ�ǰ�ڵ���ӽڵ����
	 * 
	 * @param conn
	 * @param classsesId
	 * @return
	 * @throws SQLException
	 */
	private int getChildren(Connection conn, int classsesId)
			throws SQLException {
		String sql = "select count(*) from t_classes where pid=?";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int count = 0;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, classsesId);
			rs = pstmt.executeQuery();
			rs.next();
			// rs.getInt("COUNT(*)")
			count = rs.getInt(1);
		} finally {
			DbUtil.close(rs);
			DbUtil.close(pstmt);
		}
		return count;
	}

	public static void main(String[] args) {
		ClassesManager.getInstance().outClassesList();
	}
}
