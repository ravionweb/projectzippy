package com.schooltrix.hibernate;

/**
 * BranchStaffMap entity. @author MyEclipse Persistence Tools
 */

public class BranchStaffMap implements java.io.Serializable {

	// Fields

	private Long bsmId;
	private String bmId;
	private String umId;
	private String active;

	// Constructors

	/** default constructor */
	public BranchStaffMap() {
	}

	/** full constructor */
	public BranchStaffMap(String bmId, String umId, String active) {
		this.bmId = bmId;
		this.umId = umId;
		this.active = active;
	}

	// Property accessors

	public Long getBsmId() {
		return this.bsmId;
	}

	public void setBsmId(Long bsmId) {
		this.bsmId = bsmId;
	}

	public String getBmId() {
		return this.bmId;
	}

	public void setBmId(String bmId) {
		this.bmId = bmId;
	}

	public String getUmId() {
		return this.umId;
	}

	public void setUmId(String umId) {
		this.umId = umId;
	}

	public String getActive() {
		return this.active;
	}

	public void setActive(String active) {
		this.active = active;
	}

}