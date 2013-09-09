package com.schooltrix.hibernate;

/**
 * UserType entity. @author MyEclipse Persistence Tools
 */

public class UserType implements java.io.Serializable {

	// Fields

	private Long utId;
	private String utCode;
	private String utDesc;
	private String active;

	// Constructors

	/** default constructor */
	public UserType() {
	}

	/** minimal constructor */
	public UserType(String utCode, String active) {
		this.utCode = utCode;
		this.active = active;
	}

	/** full constructor */
	public UserType(String utCode, String utDesc, String active) {
		this.utCode = utCode;
		this.utDesc = utDesc;
		this.active = active;
	}

	// Property accessors

	public Long getUtId() {
		return this.utId;
	}

	public void setUtId(Long utId) {
		this.utId = utId;
	}

	public String getUtCode() {
		return this.utCode;
	}

	public void setUtCode(String utCode) {
		this.utCode = utCode;
	}

	public String getUtDesc() {
		return this.utDesc;
	}

	public void setUtDesc(String utDesc) {
		this.utDesc = utDesc;
	}

	public String getActive() {
		return this.active;
	}

	public void setActive(String active) {
		this.active = active;
	}

}