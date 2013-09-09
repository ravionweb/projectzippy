package com.schooltrix.hibernate;

/**
 * ParentStudentMap entity. @author MyEclipse Persistence Tools
 */

public class ParentStudentMap implements java.io.Serializable {

	// Fields

	private Long psmId;
	private String pdId;
	private String stuId;
	private String active;

	// Constructors

	/** default constructor */
	public ParentStudentMap() {
	}

	/** full constructor */
	public ParentStudentMap(String pdId, String stuId, String active) {
		this.pdId = pdId;
		this.stuId = stuId;
		this.active = active;
	}

	// Property accessors

	public Long getPsmId() {
		return this.psmId;
	}

	public void setPsmId(Long psmId) {
		this.psmId = psmId;
	}

	public String getPdId() {
		return this.pdId;
	}

	public void setPdId(String pdId) {
		this.pdId = pdId;
	}

	public String getStuId() {
		return this.stuId;
	}

	public void setStuId(String stuId) {
		this.stuId = stuId;
	}

	public String getActive() {
		return this.active;
	}

	public void setActive(String active) {
		this.active = active;
	}

}