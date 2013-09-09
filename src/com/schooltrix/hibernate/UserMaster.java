package com.schooltrix.hibernate;

/**
 * UserMaster entity. @author MyEclipse Persistence Tools
 */

public class UserMaster implements java.io.Serializable {

	// Fields

	private Long umId;
	private Long imId;
	private Long smId;
	private Long bmId;
	private Long utId;
	private String userId;
	private String password;
	private String active;

	// Constructors

	/** default constructor */
	public UserMaster() {
	}

	/** full constructor */
	public UserMaster(Long imId, Long smId, Long bmId, Long utId,
			String userId, String password, String active) {
		this.imId = imId;
		this.smId = smId;
		this.bmId = bmId;
		this.utId = utId;
		this.userId = userId;
		this.password = password;
		this.active = active;
	}

	// Property accessors

	public Long getUmId() {
		return this.umId;
	}

	public void setUmId(Long umId) {
		this.umId = umId;
	}

	public Long getImId() {
		return this.imId;
	}

	public void setImId(Long imId) {
		this.imId = imId;
	}

	public Long getSmId() {
		return this.smId;
	}

	public void setSmId(Long smId) {
		this.smId = smId;
	}

	public Long getBmId() {
		return this.bmId;
	}

	public void setBmId(Long bmId) {
		this.bmId = bmId;
	}

	public Long getUtId() {
		return this.utId;
	}

	public void setUtId(Long utId) {
		this.utId = utId;
	}

	public String getUserId() {
		return this.userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getActive() {
		return this.active;
	}

	public void setActive(String active) {
		this.active = active;
	}

}