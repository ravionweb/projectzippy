package com.schooltrix.hibernate;

import java.sql.Timestamp;

/**
 * SchoolMasterHistory entity. @author MyEclipse Persistence Tools
 */

public class SchoolMasterHistory implements java.io.Serializable {

	// Fields

	private Long smhId;
	private Long smId;
	private Long imId;
	private String schoolName;
	private String name;
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
	public SchoolMasterHistory() {
	}

	/** minimal constructor */
	public SchoolMasterHistory(Long smId, Long imId, String schoolName,
			String name, Long stateId, String action) {
		this.smId = smId;
		this.imId = imId;
		this.schoolName = schoolName;
		this.name = name;
		this.stateId = stateId;
		this.action = action;
	}

	/** full constructor */
	public SchoolMasterHistory(Long smId, Long imId, String schoolName,
			String name, String address, String city, Long stateId,
			String contactPerson, String emailId, String mobile,
			String landline, String active, String action, Timestamp editedDate) {
		this.smId = smId;
		this.imId = imId;
		this.schoolName = schoolName;
		this.name = name;
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

	public Long getSmhId() {
		return this.smhId;
	}

	public void setSmhId(Long smhId) {
		this.smhId = smhId;
	}

	public Long getSmId() {
		return this.smId;
	}

	public void setSmId(Long smId) {
		this.smId = smId;
	}

	public Long getImId() {
		return this.imId;
	}

	public void setImId(Long imId) {
		this.imId = imId;
	}

	public String getSchoolName() {
		return this.schoolName;
	}

	public void setSchoolName(String schoolName) {
		this.schoolName = schoolName;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
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