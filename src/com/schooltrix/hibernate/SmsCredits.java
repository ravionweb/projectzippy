package com.schooltrix.hibernate;

/**
 * SmsCredits entity. @author MyEclipse Persistence Tools
 */

public class SmsCredits implements java.io.Serializable {

	// Fields

	private Long sno;
	private String imId;
	private String bmId;
	private String smId;
	private String smsCredits;
	private String active;

	// Constructors

	/** default constructor */
	public SmsCredits() {
	}

	/** minimal constructor */
	public SmsCredits(String smsCredits, String active) {
		this.smsCredits = smsCredits;
		this.active = active;
	}

	/** full constructor */
	public SmsCredits(String imId, String bmId, String smId, String smsCredits,
			String active) {
		this.imId = imId;
		this.bmId = bmId;
		this.smId = smId;
		this.smsCredits = smsCredits;
		this.active = active;
	}

	// Property accessors

	public Long getSno() {
		return this.sno;
	}

	public void setSno(Long sno) {
		this.sno = sno;
	}

	public String getImId() {
		return this.imId;
	}

	public void setImId(String imId) {
		this.imId = imId;
	}

	public String getBmId() {
		return this.bmId;
	}

	public void setBmId(String bmId) {
		this.bmId = bmId;
	}

	public String getSmId() {
		return this.smId;
	}

	public void setSmId(String smId) {
		this.smId = smId;
	}

	public String getSmsCredits() {
		return this.smsCredits;
	}

	public void setSmsCredits(String smsCredits) {
		this.smsCredits = smsCredits;
	}

	public String getActive() {
		return this.active;
	}

	public void setActive(String active) {
		this.active = active;
	}

}