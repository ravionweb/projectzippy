package com.schooltrix.hibernate;

import java.sql.Timestamp;

/**
 * StaffDetailsHistory entity. @author MyEclipse Persistence Tools
 */

public class StaffDetailsHistory implements java.io.Serializable {

	// Fields

	private Long sdhId;
	private Long sdId;
	private Long umId;
	private String firstName;
	private String lastName;
	private String gender;
	private String address1;
	private String address2;
	private String city;
	private String state;
	private String designation;
	private String email;
	private String mobile;
	private String landline;
	private String dob;
	private String photo;
	private String active;
	private String action;
	private Timestamp editedDate;

	// Constructors

	/** default constructor */
	public StaffDetailsHistory() {
	}

	/** minimal constructor */
	public StaffDetailsHistory(Long sdId, String firstName, String lastName,
			String gender, String address1, String state, String email,
			String mobile, String dob, String active, String action) {
		this.sdId = sdId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.gender = gender;
		this.address1 = address1;
		this.state = state;
		this.email = email;
		this.mobile = mobile;
		this.dob = dob;
		this.active = active;
		this.action = action;
	}

	/** full constructor */
	public StaffDetailsHistory(Long sdId, Long umId, String firstName,
			String lastName, String gender, String address1, String address2,
			String city, String state, String designation, String email,
			String mobile, String landline, String dob, String photo,
			String active, String action, Timestamp editedDate) {
		this.sdId = sdId;
		this.umId = umId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.gender = gender;
		this.address1 = address1;
		this.address2 = address2;
		this.city = city;
		this.state = state;
		this.designation = designation;
		this.email = email;
		this.mobile = mobile;
		this.landline = landline;
		this.dob = dob;
		this.photo = photo;
		this.active = active;
		this.action = action;
		this.editedDate = editedDate;
	}

	// Property accessors

	public Long getSdhId() {
		return this.sdhId;
	}

	public void setSdhId(Long sdhId) {
		this.sdhId = sdhId;
	}

	public Long getSdId() {
		return this.sdId;
	}

	public void setSdId(Long sdId) {
		this.sdId = sdId;
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

	public String getDesignation() {
		return this.designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
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