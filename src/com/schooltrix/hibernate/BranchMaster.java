package com.schooltrix.hibernate;

/**
 * BranchMaster entity. @author MyEclipse Persistence Tools
 */

public class BranchMaster implements java.io.Serializable {

	// Fields

	private Long bmId;
	private String imId;
	private Long smId;
	private String branchName;
	private String shortName;
	private String address;
	private String city;
	private Long stateId;
	private String contactPerson;
	private String emailId;
	private String mobile;
	private String landline;
	private String active;

	// Constructors

	/** default constructor */
	public BranchMaster() {
	}

	/** minimal constructor */
	public BranchMaster(String branchName, String address, Long stateId,
			String contactPerson, String emailId, String mobile, String active) {
		this.branchName = branchName;
		this.address = address;
		this.stateId = stateId;
		this.contactPerson = contactPerson;
		this.emailId = emailId;
		this.mobile = mobile;
		this.active = active;
	}

	/** full constructor */
	public BranchMaster(String imId, Long smId, String branchName,
			String shortName, String address, String city, Long stateId,
			String contactPerson, String emailId, String mobile,
			String landline, String active) {
		this.imId = imId;
		this.smId = smId;
		this.branchName = branchName;
		this.shortName = shortName;
		this.address = address;
		this.city = city;
		this.stateId = stateId;
		this.contactPerson = contactPerson;
		this.emailId = emailId;
		this.mobile = mobile;
		this.landline = landline;
		this.active = active;
	}

	// Property accessors

	public Long getBmId() {
		return this.bmId;
	}

	public void setBmId(Long bmId) {
		this.bmId = bmId;
	}

	public String getImId() {
		return this.imId;
	}

	public void setImId(String imId) {
		this.imId = imId;
	}

	public Long getSmId() {
		return this.smId;
	}

	public void setSmId(Long smId) {
		this.smId = smId;
	}

	public String getBranchName() {
		return this.branchName;
	}

	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}

	public String getShortName() {
		return this.shortName;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return this.city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public Long getStateId() {
		return this.stateId;
	}

	public void setStateId(Long stateId) {
		this.stateId = stateId;
	}

	public String getContactPerson() {
		return this.contactPerson;
	}

	public void setContactPerson(String contactPerson) {
		this.contactPerson = contactPerson;
	}

	public String getEmailId() {
		return this.emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getMobile() {
		return this.mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getLandline() {
		return this.landline;
	}

	public void setLandline(String landline) {
		this.landline = landline;
	}

	public String getActive() {
		return this.active;
	}

	public void setActive(String active) {
		this.active = active;
	}

}