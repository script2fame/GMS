package com.hungteshun.gms.model;

/**
 * 班级实体类
 * @author hungteshun
 *
 */
public class Classes {

	//班级代码
	private int classesId;
	
	//班级名称
	private String classesName;
	
	//是否为叶子,1:是叶子，0：非叶子
	private int leaf;
	
	//private Classes parent;
	
	//private Set<Classes> chilren;
	
	//父id
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
