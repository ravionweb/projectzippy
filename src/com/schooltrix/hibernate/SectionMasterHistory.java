package com.schooltrix.hibernate;

import java.sql.Timestamp;

/**
 * SectionMasterHistory entity. @author MyEclipse Persistence Tools
 */

public class SectionMasterHistory implements java.io.Serializable {

	// Fields

	private Long seMhId;
	private Long seMId;
	private String sectionName;
	private String active;
	private String action;
	private Timestamp editedDate;

	// Constructors

	/** default constructor */
	public SectionMasterHistory() {
	}

	/** minimal constructor */
	public SectionMasterHistory(Long seMId, String sectionName, String active,
			String action) {
		this.seMId = seMId;
		this.sectionName = sectionName;
		this.active = active;
		this.action = action;
	}

	/** full constructor */
	public SectionMasterHistory(Long seMId, String sectionName, String active,
			String action, Timestamp editedDate) {
		this.seMId = seMId;
		this.sectionName = sectionName;
		this.active = active;
		this.action = action;
		this.editedDate = editedDate;
	}

	// Property accessors

	public Long getSeMhId() {
		return this.seMhId;
	}

	public void setSeMhId(Long seMhId) {
		this.seMhId = seMhId;
	}

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

	public String getAction() {
		return this.action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public Timestamp getEditedDate() {
		return this.editedDate;
	}

	public void setEditedDate(Timestamp editedDate) {
		this.editedDate = editedDate;
	}

}