package com.schooltrix.hibernate;

/**
 * FranchiseMaster entity. @author MyEclipse Persistence Tools
 */

public class FranchiseMaster implements java.io.Serializable {

	// Fields

	private Long id;
	private String franShortname;
	private String franName;
	private String fname;
	private String lname;
	private String mobile;
	private String email;
	private String addr1;
	private String addr2;
	private String stateId;
	private String active;

	// Constructors

	/** default constructor */
	public FranchiseMaster() {
	}

	/** minimal constructor */
	public FranchiseMaster(String fname, String lname, String mobile,
			String email, String addr1, String stateId) {
		this.fname = fname;
		this.lname = lname;
		this.mobile = mobile;
		this.email = email;
		this.addr1 = addr1;
		this.stateId = stateId;
	}

	/** full constructor */
	public FranchiseMaster(String franShortname, String franName, String fname,
			String lname, String mobile, String email, String addr1,
			String addr2, String stateId, String active) {
		this.franShortname = franShortname;
		this.franName = franName;
		this.fname = fname;
		this.lname = lname;
		this.mobile = mobile;
		this.email = email;
		this.addr1 = addr1;
		this.addr2 = addr2;
		this.stateId = stateId;
		this.active = active;
	}

	// Property accessors

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFranShortname() {
		return this.franShortname;
	}

	public void setFranShortname(String franShortname) {
		this.franShortname = franShortname;
	}

	public String getFranName() {
		return this.franName;
	}

	public void setFranName(String franName) {
		this.franName = franName;
	}

	public String getFname() {
		return this.fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}

	public String getLname() {
		return this.lname;
	}

	public void setLname(String lname) {
		this.lname = lname;
	}

	public String getMobile() {
		return this.mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddr1() {
		return this.addr1;
	}

	public void setAddr1(String addr1) {
		this.addr1 = addr1;
	}

	public String getAddr2() {
		return this.addr2;
	}

	public void setAddr2(String addr2) {
		this.addr2 = addr2;
	}

	public String getStateId() {
		return this.stateId;
	}

	public void setStateId(String stateId) {
		this.stateId = stateId;
	}

	public String getActive() {
		return this.active;
	}

	public void setActive(String active) {
		this.active = active;
	}

}