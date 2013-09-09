package com.schooltrix.hibernate;

/**
 * SectionMaster entity. @author MyEclipse Persistence Tools
 */

public class SectionMaster implements java.io.Serializable {

	// Fields

	private Long seMId;
	private String sectionName;
	private String active;

	// Constructors

	/** default constructor */
	public SectionMaster() {
	}

	/** full constructor */
	public SectionMaster(String sectionName, String active) {
		this.sectionName = sectionName;
		this.active = active;
	}

	// Property accessors

	public Long getSeMId() {
		return this.seMId;
	}

	public void setSeMId(Long seMId) {
		this.seMId = seMId;
	}

	public String getSectionName() {
		return this.sectionName;
	}

	public void setSectionName(String sectionName) {
		this.sectionName = sectionName;
	}

	public String getActive() {
		return this.active;
	}

	public void setActive(String active) {
		this.active = active;
	}

}