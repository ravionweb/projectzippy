package com.schooltrix.hibernate;

/**
 * ClassMaster entity. @author MyEclipse Persistence Tools
 */

public class ClassMaster implements java.io.Serializable {

	// Fields

	private Long cmId;
	private String className;
	private String active;

	// Constructors

	/** default constructor */
	public ClassMaster() {
	}

	/** full constructor */
	public ClassMaster(String className, String active) {
		this.className = className;
		this.active = active;
	}

	// Property accessors

	public Long getCmId() {
		return this.cmId;
	}

	public void setCmId(Long cmId) {
		this.cmId = cmId;
	}

	public String getClassName() {
		return this.className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String getActive() {
		return this.active;
	}

	public void setActive(String active) {
		this.active = active;
	}

}