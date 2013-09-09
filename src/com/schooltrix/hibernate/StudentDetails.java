package com.schooltrix.hibernate;

/**
 * StudentDetails entity. @author MyEclipse Persistence Tools
 */

public class StudentDetails implements java.io.Serializable {

	// Fields

	private Long stuId;
	private String umId;
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
	private String admissionNumber;
	private String admissionDate;
	private String classAdmittedIn;
	private String active;

	// Constructors

	/** default constructor */
	public StudentDetails() {
	}

	/** minimal constructor */
	public StudentDetails(String umId, String firstName, String lastName,
			String gender, String address1, String city, String state,
			String admissionNumber, String admissionDate,
			String classAdmittedIn, String active) {
		this.umId = umId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.gender = gender;
		this.address1 = address1;
		this.city = city;
		this.state = state;
		this.admissionNumber = admissionNumber;
		this.admissionDate = admissionDate;
		this.classAdmittedIn = classAdmittedIn;
		this.active = active;
	}

	/** full constructor */
	public StudentDetails(String umId, String firstName, String lastName,
			String gender, String address1, String address2, String city,
			String state, String email, String mobile, String landline,
			String dob, String photo, String admissionNumber,
			String admissionDate, String classAdmittedIn, String active) {
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
		this.admissionNumber = admissionNumber;
		this.admissionDate = admissionDate;
		this.classAdmittedIn = classAdmittedIn;
		this.active = active;
	}

	// Property accessors

	public Long getStuId() {
		return this.stuId;
	}

	public void setStuId(Long stuId) {
		this.stuId = stuId;
	}

	public String getUmId() {
		return this.umId;
	}

	public void setUmId(String umId) {
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

	public String getAdmissionNumber() {
		return this.admissionNumber;
	}

	public void setAdmissionNumber(String admissionNumber) {
		this.admissionNumber = admissionNumber;
	}

	public String getAdmissionDate() {
		return this.admissionDate;
	}

	public void setAdmissionDate(String admissionDate) {
		this.admissionDate = admissionDate;
	}

	public String getClassAdmittedIn() {
		return this.classAdmittedIn;
	}

	public void setClassAdmittedIn(String classAdmittedIn) {
		this.classAdmittedIn = classAdmittedIn;
	}

	public String getActive() {
		return this.active;
	}

	public void setActive(String active) {
		this.active = active;
	}

}