package com.schooltrix.hibernate;

import java.util.Date;

/**
 * StudentAdmissionDetail entity. @author MyEclipse Persistence Tools
 */

public class StudentAdmissionDetail implements java.io.Serializable {

	// Fields

	private Long sadId;
	private Long umId;
	private String admissionNumber;
	private Date admissionDate;
	private Long classAdmittedIn;

	// Constructors

	/** default constructor */
	public StudentAdmissionDetail() {
	}

	/** full constructor */
	public StudentAdmissionDetail(Long umId, String admissionNumber,
			Date admissionDate, Long classAdmittedIn) {
		this.umId = umId;
		this.admissionNumber = admissionNumber;
		this.admissionDate = admissionDate;
		this.classAdmittedIn = classAdmittedIn;
	}

	// Property accessors

	public Long getSadId() {
		return this.sadId;
	}

	public void setSadId(Long sadId) {
		this.sadId = sadId;
	}

	public Long getUmId() {
		return this.umId;
	}

	public void setUmId(Long umId) {
		this.umId = umId;
	}

	public String getAdmissionNumber() {
		return this.admissionNumber;
	}

	public void setAdmissionNumber(String admissionNumber) {
		this.admissionNumber = admissionNumber;
	}

	public Date getAdmissionDate() {
		return this.admissionDate;
	}

	public void setAdmissionDate(Date admissionDate) {
		this.admissionDate = admissionDate;
	}

	public Long getClassAdmittedIn() {
		return this.classAdmittedIn;
	}

	public void setClassAdmittedIn(Long classAdmittedIn) {
		this.classAdmittedIn = classAdmittedIn;
	}

}