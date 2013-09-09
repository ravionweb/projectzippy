package com.schooltrix.hibernate;

/**
 * StudentxlErrorTemp entity. @author MyEclipse Persistence Tools
 */

public class StudentxlErrorTemp implements java.io.Serializable {

	// Fields

	private Long setId;
	private String imId;
	private String bmId;
	private String smId;
	private String umId;
	private String errorline;
	private String reason;

	// Constructors

	/** default constructor */
	public StudentxlErrorTemp() {
	}

	/** minimal constructor */
	public StudentxlErrorTemp(String umId) {
		this.umId = umId;
	}

	/** full constructor */
	public StudentxlErrorTemp(String imId, String bmId, String smId,
			String umId, String errorline, String reason) {
		this.imId = imId;
		this.bmId = bmId;
		this.smId = smId;
		this.umId = umId;
		this.errorline = errorline;
		this.reason = reason;
	}

	// Property accessors

	public Long getSetId() {
		return this.setId;
	}

	public void setSetId(Long setId) {
		this.setId = setId;
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

	public String getUmId() {
		return this.umId;
	}

	public void setUmId(String umId) {
		this.umId = umId;
	}

	public String getErrorline() {
		return this.errorline;
	}

	public void setErrorline(String errorline) {
		this.errorline = errorline;
	}

	public String getReason() {
		return this.reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

}