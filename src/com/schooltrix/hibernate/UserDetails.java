package com.schooltrix.hibernate;

import java.util.Date;

/**
 * UserDetails entity. @author MyEclipse Persistence Tools
 */

public class UserDetails implements java.io.Serializable {

	// Fields

	private Long udId;
	private Long umId;
	private String firstName;
	private String lastName;
	private String address1;
	private String address2;
	private String city;
	private Long state;
	private String email;
	private String mobile;
	private String landline;
	private Date dob;
	private String photo;
	private String active;

	// Constructors

	/** default constructor */
	public UserDetails() {
	}

	/** minimal constructor */
	public UserDetails(Long umId, String firstName, String lastName,
			String address1, String city, Long state, String email,
			String mobile, Date dob, String active) {
		this.umId = umId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.address1 = address1;
		this.city = city;
		this.state = state;
		this.email = email;
		this.mobile = mobile;
		this.dob = dob;
		this.active = active;
	}

	/** full constructor */
	public UserDetails(Long umId, String firstName, String lastName,
			String address1, String address2, String city, Long state,
			String email, String mobile, String landline, Date dob,
			String photo, String active) {
		this.umId = umId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.address1 = address1;
		this.address2 = address2;
		this.city = city;
		this.state = state;
		this.email = email;
		this.mobile = mobile;
		this.landline = landline;
		this.dob = dob;
		this.photo = photo;
		this.active = active;
	}

	// Property accessors

	public Long getUdId() {
		return this.udId;
	}

	public void setUdId(Long udId) {
		this.udId = udId;
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

	public Long getState() {
		return this.state;
	}

	public void setState(Long state) {
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

	public Date getDob() {
		return this.dob;
	}

	public void setDob(Date dob) {
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

}