package com.schooltrix.hibernate;

import java.sql.Timestamp;

/**
 * ParentDetailsHistory entity. @author MyEclipse Persistence Tools
 */

public class ParentDetailsHistory implements java.io.Serializable {

	// Fields

	private Long pdhId;
	private Long pdId;
	private Long umId;
	private String firstName;
	private String lastName;
	private String gender;
	private String address1;
	private String address2;
	private String city;
	private String state;
	private String email;
	private String mobile;
	private String landline;
	private String dob;
	private String photo;
	private String ptmId;
	private String isDefault;
	private String active;
	private String action;
	private Timestamp editedDate;

	// Constructors

	/** default constructor */
	public ParentDetailsHistory() {
	}

	/** minimal constructor */
	public ParentDetailsHistory(Long pdId, String firstName, String lastName,
			String gender, String address1, String city, String state,
			String email, String mobile, String dob, String isDefault,
			String active, String action) {
		this.pdId = pdId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.gender = gender;
		this.address1 = address1;
		this.city = city;
		this.state = state;
		this.email = email;
		this.mobile = mobile;
		this.dob = dob;
		this.isDefault = isDefault;
		this.active = active;
		this.action = action;
	}

	/** full constructor */
	public ParentDetailsHistory(Long pdId, Long umId, String firstName,
			String lastName, String gender, String address1, String address2,
			String city, String state, String email, String mobile,
			String landline, String dob, String photo, String ptmId,
			String isDefault, String active, String action, Timestamp editedDate) {
		this.pdId = pdId;
		this.umId = umId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.gender = gender;
		this.address1 = address1;
		this.address2 = address2;
		this.city = city;
		this.state = state;
		this.email = email;
		this.mobile = mobile;
		this.landline = landline;
		this.dob = dob;
		this.photo = photo;
		this.ptmId = ptmId;
		this.isDefault = isDefault;
		this.active = active;
		this.action = action;
		this.editedDate = editedDate;
	}

	// Property accessors

	public Long getPdhId() {
		return this.pdhId;
	}

	public void setPdhId(Long pdhId) {
		this.pdhId = pdhId;
	}

	public Long getPdId() {
		return this.pdId;
	}

	public void setPdId(Long pdId) {
		this.pdId = pdId;
	}

	public Long getUmId() {
		return this.umId;
	}

	public void setUmId(Long umId) {
		this.umId = umId;
	}

	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getGender() {
		return this.gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getAddress1() {
		return this.address1;
	}

	public void setAddress1(String address1) {
		this.address1 = address1;
	}

	public String getAddress2() {
		return this.address2;
	}

	public void setAddress2(String address2) {
		this.address2 = address2;
	}

	public String getCity() {
		return this.city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return this.state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
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

	public String getDob() {
		return this.dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}

	public String getPhoto() {
		return this.photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public String getPtmId() {
		return this.ptmId;
	}

	public void setPtmId(String ptmId) {
		this.ptmId = ptmId;
	}

	public String getIsDefault() {
		return this.isDefault;
	}

	public void setIsDefault(String isDefault) {
		this.isDefault = isDefault;
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