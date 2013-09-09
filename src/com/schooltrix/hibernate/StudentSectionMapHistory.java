package com.schooltrix.hibernate;

import java.sql.Timestamp;

/**
 * StudentSectionMapHistory entity. @author MyEclipse Persistence Tools
 */

public class StudentSectionMapHistory implements java.io.Serializable {

	// Fields

	private Long ssmhId;
	private Long ssmId;
	private String scmId;
	private String stuId;
	private String imId;
	private String bmId;
	private String smId;
	private String active;
	private String action;
	private Timestamp editedDate;

	// Constructors

	/** default constructor */
	public StudentSectionMapHistory() {
	}

	/** minimal constructor */
	public StudentSectionMapHistory(Long ssmId, String scmId, String stuId,
			String imId, String bmId, String smId, String active, String action) {
		this.ssmId = ssmId;
		this.scmId = scmId;
		this.stuId = stuId;
		this.imId = imId;
		this.bmId = bmId;
		this.smId = smId;
		this.active = active;
		this.action = action;
	}

	/** full constructor */
	public StudentSectionMapHistory(Long ssmId, String scmId, String stuId,
			String imId, String bmId, String smId, String active,
			String action, Timestamp editedDate) {
		this.ssmId = ssmId;
		this.scmId = scmId;
		this.stuId = stuId;
		this.imId = imId;
		this.bmId = bmId;
		this.smId = smId;
		this.active = active;
		this.action = action;
		this.editedDate = editedDate;
	}

	// Property accessors

	public Long getSsmhId() {
		return this.ssmhId;
	}

	public void setSsmhId(Long ssmhId) {
		this.ssmhId = ssmhId;
	}

	public Long getSsmId() {
		return this.ssmId;
	}

	public void setSsmId(Long ssmId) {
		this.ssmId = ssmId;
	}

	public String getScmId() {
		return this.scmId;
	}

	public void setScmId(String scmId) {
		this.scmId = scmId;
	}

	public String getStuId() {
		return this.stuId;
	}

	public void setStuId(String stuId) {
		this.stuId = stuId;
	}

	public String getImId() {
		return this.imId;
	}

	public void setImId(String imId) {
		this.imId = imId;
	}

	public String getBmId() {
		return this.bmId;
	}

	public void setBmId(String bmId) {
		this.bmId = bmId;
	}

	public String getSmId() {
		return this.smId;
	}

	public void setSmId(String smId) {
		this.smId = smId;
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