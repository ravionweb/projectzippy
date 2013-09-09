package com.schooltrix.hibernate;

/**
 * SectionClassMap entity. @author MyEclipse Persistence Tools
 */

public class SectionClassMap implements java.io.Serializable {

	// Fields

	private Long scmId;
	private String seMId;
	private String cmId;
	private String bmId;
	private String active;

	// Constructors

	/** default constructor */
	public SectionClassMap() {
	}

	/** minimal constructor */
	public SectionClassMap(String seMId, String cmId, String bmId) {
		this.seMId = seMId;
		this.cmId = cmId;
		this.bmId = bmId;
	}

	/** full constructor */
	public SectionClassMap(String seMId, String cmId, String bmId, String active) {
		this.seMId = seMId;
		this.cmId = cmId;
		this.bmId = bmId;
		this.active = active;
	}

	// Property accessors

	public Long getScmId() {
		return this.scmId;
	}

	public void setScmId(Long scmId) {
		this.scmId = scmId;
	}

	public String getSeMId() {
		return this.seMId;
	}

	public void setSeMId(String seMId) {
		this.seMId = seMId;
	}

	public String getCmId() {
		return this.cmId;
	}

	public void setCmId(String cmId) {
		this.cmId = cmId;
	}

	public String getBmId() {
		return this.bmId;
	}

	public void setBmId(String bmId) {
		this.bmId = bmId;
	}

	public String getActive() {
		return this.active;
	}

	public void setActive(String active) {
		this.active = active;
	}

}