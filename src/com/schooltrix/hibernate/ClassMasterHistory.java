package com.schooltrix.hibernate;

import java.sql.Timestamp;

/**
 * ClassMasterHistory entity. @author MyEclipse Persistence Tools
 */

public class ClassMasterHistory implements java.io.Serializable {

	// Fields

	private Long cmhId;
	private Long cmId;
	private String className;
	private String active;
	private String action;
	private Timestamp editedDate;

	// Constructors

	/** default constructor */
	public ClassMasterHistory() {
	}

	/** minimal constructor */
	public ClassMasterHistory(Long cmId, String className, String active,
			String action) {
		this.cmId = cmId;
		this.className = className;
		this.active = active;
		this.action = action;
	}

	/** full constructor */
	public ClassMasterHistory(Long cmId, String className, String active,
			String action, Timestamp editedDate) {
		this.cmId = cmId;
		this.className = className;
		this.active = active;
		this.action = action;
		this.editedDate = editedDate;
	}

	// Property accessors

	public Long getCmhId() {
		return this.cmhId;
	}

	public void setCmhId(Long cmhId) {
		this.cmhId = cmhId;
	}

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