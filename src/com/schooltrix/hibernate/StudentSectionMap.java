package com.schooltrix.hibernate;

/**
 * StudentSectionMap entity. @author MyEclipse Persistence Tools
 */

public class StudentSectionMap implements java.io.Serializable {

	// Fields

	private Long ssmId;
	private String scmId;
	private String stuId;
	private String imId;
	private String bmId;
	private String smId;
	private String active;

	// Constructors

	/** default constructor */
	public StudentSectionMap() {
	}

	/** full constructor */
	public StudentSectionMap(String scmId, String stuId, String imId,
			String bmId, String smId, String active) {
		this.scmId = scmId;
		this.stuId = stuId;
		this.imId = imId;
		this.bmId = bmId;
		this.smId = smId;
		this.active = active;
	}

	// Property accessors

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

}