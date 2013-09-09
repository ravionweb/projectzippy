package com.schooltrix.hibernate;

/**
 * UserSchoolMap entity. @author MyEclipse Persistence Tools
 */

public class UserSchoolMap implements java.io.Serializable {

	// Fields

	private Long usmId;
	private Long smId;
	private Long umId;
	private String active;

	// Constructors

	/** default constructor */
	public UserSchoolMap() {
	}

	/** full constructor */
	public UserSchoolMap(Long smId, Long umId, String active) {
		this.smId = smId;
		this.umId = umId;
		this.active = active;
	}

	// Property accessors

	public Long getUsmId() {
		return this.usmId;
	}

	public void setUsmId(Long usmId) {
		this.usmId = usmId;
	}

	public Long getSmId() {
		return this.smId;
	}

	public void setSmId(Long smId) {
		this.smId = smId;
	}

	public Long getUmId() {
		return this.umId;
	}

	public void setUmId(Long umId) {
		this.umId = umId;
	}

	public String getActive() {
		return this.active;
	}

	public void setActive(String active) {
		this.active = active;
	}

}