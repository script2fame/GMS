package com.hungteshun.gms.model;

/**
 * �༶ʵ����
 * @author hungteshun
 *
 */
public class Classes {

	//�༶����
	private int classesId;
	
	//�༶����
	private String classesName;
	
	//�Ƿ�ΪҶ��,1:��Ҷ�ӣ�0����Ҷ��
	private int leaf;
	
	//private Classes parent;
	
	//private Set<Classes> chilren;
	
	//��id
	private int pid;

	public int getClassesId() {
		return classesId;
	}

	public void setClassesId(int classesId) {
		this.classesId = classesId;
	}

	public String getClassesName() {
		return classesName;
	}

	public void setClassesName(String classesName) {
		this.classesName = classesName;
	}

	public int getLeaf() {
		return leaf;
	}

	public void setLeaf(int leaf) {
		this.leaf = leaf;
	}

	public int getPid() {
		return pid;
	}

	public void setPid(int pid) {
		this.pid = pid;
	}
	
}
