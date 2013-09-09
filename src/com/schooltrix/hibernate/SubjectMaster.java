package com.schooltrix.hibernate;

/**
 * SubjectMaster entity. @author MyEclipse Persistence Tools
 */

public class SubjectMaster implements java.io.Serializable {

	// Fields

	private Long submId;
	private String bmId;
	private String subCode;
	private String oldSubCode;
	private String subName;
	private String active;

	// Constructors

	/** default constructor */
	public SubjectMaster() {
	}

	/** minimal constructor */
	public SubjectMaster(String subCode, String subName, String active) {
		this.subCode = subCode;
		this.subName = subName;
		this.active = active;
	}

	/** full constructor */
	public SubjectMaster(String bmId, String subCode, String oldSubCode,
			String subName, String active) {
		this.bmId = bmId;
		this.subCode = subCode;
		this.oldSubCode = oldSubCode;
		this.subName = subName;
		this.active = active;
	}

	// Property accessors

	public Long getSubmId() {
		return this.submId;
	}

	public void setSubmId(Long submId) {
		this.submId = submId;
	}

	public String getBmId() {
		return this.bmId;
	}

	public void setBmId(String bmId) {
		this.bmId = bmId;
	}

	public String getSubCode() {
		return this.subCode;
	}

	public void setSubCode(String subCode) {
		this.subCode = subCode;
	}

	public String getOldSubCode() {
		return this.oldSubCode;
	}

	public void setOldSubCode(String oldSubCode) {
		this.oldSubCode = oldSubCode;
	}

	public String getSubName() {
		return this.subName;
	}

	public void setSubName(String subName) {
		this.subName = subName;
	}

	public String getActive() {
		return this.active;
	}

	public void setActive(String active) {
		this.active = active;
	}

}