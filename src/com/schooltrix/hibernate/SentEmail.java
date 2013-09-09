package com.schooltrix.hibernate;

import java.sql.Timestamp;

/**
 * SentEmail entity. @author MyEclipse Persistence Tools
 */

public class SentEmail implements java.io.Serializable {

	// Fields

	private Long sno;
	private String imId;
	private String bmId;
	private String smId;
	private String emailFrom;
	private String emailSubj;
	private String emailBody;
	private String noToSent;
	private String userListFileName;
	private String status;
	private Timestamp reqTime;
	private Timestamp processedTime;

	// Constructors

	/** default constructor */
	public SentEmail() {
	}

	/** minimal constructor */
	public SentEmail(String status, Timestamp reqTime, Timestamp processedTime) {
		this.status = status;
		this.reqTime = reqTime;
		this.processedTime = processedTime;
	}

	/** full constructor */
	public SentEmail(String imId, String bmId, String smId, String emailFrom,
			String emailSubj, String emailBody, String noToSent,
			String userListFileName, String status, Timestamp reqTime,
			Timestamp processedTime) {
		this.imId = imId;
		this.bmId = bmId;
		this.smId = smId;
		this.emailFrom = emailFrom;
		this.emailSubj = emailSubj;
		this.emailBody = emailBody;
		this.noToSent = noToSent;
		this.userListFileName = userListFileName;
		this.status = status;
		this.reqTime = reqTime;
		this.processedTime = processedTime;
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

	public String getEmailFrom() {
		return this.emailFrom;
	}

	public void setEmailFrom(String emailFrom) {
		this.emailFrom = emailFrom;
	}

	public String getEmailSubj() {
		return this.emailSubj;
	}

	public void setEmailSubj(String emailSubj) {
		this.emailSubj = emailSubj;
	}

	public String getEmailBody() {
		return this.emailBody;
	}

	public void setEmailBody(String emailBody) {
		this.emailBody = emailBody;
	}

	public String getNoToSent() {
		return this.noToSent;
	}

	public void setNoToSent(String noToSent) {
		this.noToSent = noToSent;
	}

	public String getUserListFileName() {
		return this.userListFileName;
	}

	public void setUserListFileName(String userListFileName) {
		this.userListFileName = userListFileName;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Timestamp getReqTime() {
		return this.reqTime;
	}

	public void setReqTime(Timestamp reqTime) {
		this.reqTime = reqTime;
	}

	public Timestamp getProcessedTime() {
		return this.processedTime;
	}

	public void setProcessedTime(Timestamp processedTime) {
		this.processedTime = processedTime;
	}

}