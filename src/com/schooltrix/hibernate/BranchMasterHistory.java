package com.schooltrix.hibernate;

import java.sql.Timestamp;

/**
 * BranchMasterHistory entity. @author MyEclipse Persistence Tools
 */

public class BranchMasterHistory implements java.io.Serializable {

	// Fields

	private Long bmhId;
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
	private String action;
	private Timestamp editedDate;

	// Constructors

	/** default constructor */
	public BranchMasterHistory() {
	}

	/** minimal constructor */
	public BranchMasterHistory(Long bmId, String branchName, String address,
			Long stateId, String contactPerson, String emailId, String mobile,
			String active, String action) {
		this.bmId = bmId;
		this.branchName = branchName;
		this.address = address;
		this.stateId = stateId;
		this.contactPerson = contactPerson;
		this.emailId = emailId;
		this.mobile = mobile;
		this.active = active;
		this.action = action;
	}

	/** full constructor */
	public BranchMasterHistory(Long bmId, String imId, Long smId,
			String branchName, String shortName, String address, String city,
			Long stateId, String contactPerson, String emailId, String mobile,
			String landline, String active, String action, Timestamp editedDate) {
		this.bmId = bmId;
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
		this.action = action;
		this.editedDate = editedDate;
	}

	// Property accessors

	public Long getBmhId() {
		return this.bmhId;
	}

	public void setBmhId(Long bmhId) {
		this.bmhId = bmhId;
	}

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