package com.schooltrix.hibernate;

/**
 * ClassBranchMap entity. @author MyEclipse Persistence Tools
 */

public class ClassBranchMap implements java.io.Serializable {

	// Fields

	private Long cbmId;
	private Long bmId;
	private Long cmId;
	private String active;

	// Constructors

	/** default constructor */
	public ClassBranchMap() {
	}

	/** full constructor */
	public ClassBranchMap(Long bmId, Long cmId, String active) {
		this.bmId = bmId;
		this.cmId = cmId;
		this.active = active;
	}

	// Property accessors

	public Long getCbmId() {
		return this.cbmId;
	}

	public void setCbmId(Long cbmId) {
		this.cbmId = cbmId;
	}

	public Long getBmId() {
		return this.bmId;
	}

	public void setBmId(Long bmId) {
		this.bmId = bmId;
	}

	public Long getCmId() {
		return this.cmId;
	}

	public void setCmId(Long cmId) {
		this.cmId = cmId;
	}

	public String getActive() {
		return this.active;
	}

	public void setActive(String active) {
		this.active = active;
	}

}