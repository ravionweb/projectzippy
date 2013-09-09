package com.schooltrix.hibernate;

import java.sql.Timestamp;

/**
 * UploadDocumentHistory entity. @author MyEclipse Persistence Tools
 */

public class UploadDocumentHistory implements java.io.Serializable {

	// Fields

	private Long udh;
	private Long udId;
	private String imId;
	private String toWhome;
	private String uploadType;
	private String assignType;
	private String assgDesc;
	private String subject;
	private String fileName;
	private String notifyPaEmail;
	private String notifyPaEmailFlag;
	private Timestamp uploadDate;
	private Timestamp processedDate;

	// Constructors

	/** default constructor */
	public UploadDocumentHistory() {
	}

	/** minimal constructor */
	public UploadDocumentHistory(Long udId, String toWhome, String uploadType,
			String fileName, String notifyPaEmail) {
		this.udId = udId;
		this.toWhome = toWhome;
		this.uploadType = uploadType;
		this.fileName = fileName;
		this.notifyPaEmail = notifyPaEmail;
	}

	/** full constructor */
	public UploadDocumentHistory(Long udId, String imId, String toWhome,
			String uploadType, String assignType, String assgDesc,
			String subject, String fileName, String notifyPaEmail,
			String notifyPaEmailFlag, Timestamp uploadDate,
			Timestamp processedDate) {
		this.udId = udId;
		this.imId = imId;
		this.toWhome = toWhome;
		this.uploadType = uploadType;
		this.assignType = assignType;
		this.assgDesc = assgDesc;
		this.subject = subject;
		this.fileName = fileName;
		this.notifyPaEmail = notifyPaEmail;
		this.notifyPaEmailFlag = notifyPaEmailFlag;
		this.uploadDate = uploadDate;
		this.processedDate = processedDate;
	}
	/** Manual constructor */
	public UploadDocumentHistory(UploadDocument uploaddoc) {
		this.udId = uploaddoc.getUdId();
		this.imId = uploaddoc.getImId();
		this.toWhome = uploaddoc.getToWhome();
		this.uploadType = uploaddoc.getUploadType();
		this.assignType = uploaddoc.getAssignType();
		this.assgDesc = uploaddoc.getAssgDesc();
		this.subject = uploaddoc.getSubject();
		this.fileName = uploaddoc.getFileName();
		this.notifyPaEmail = uploaddoc.getNotifyPaEmail();
		this.notifyPaEmailFlag = uploaddoc.getNotifyPaEmailFlag();
		this.uploadDate = uploaddoc.getUploadDate();
		this.processedDate = uploaddoc.getProcessedDate();
	}
	// Property accessors

	public Long getUdh() {
		return this.udh;
	}

	public void setUdh(Long udh) {
		this.udh = udh;
	}

	public Long getUdId() {
		return this.udId;
	}

	public void setUdId(Long udId) {
		this.udId = udId;
	}

	public String getImId() {
		return this.imId;
	}

	public void setImId(String imId) {
		this.imId = imId;
	}

	public String getToWhome() {
		return this.toWhome;
	}

	public void setToWhome(String toWhome) {
		this.toWhome = toWhome;
	}

	public String getUploadType() {
		return this.uploadType;
	}

	public void setUploadType(String uploadType) {
		this.uploadType = uploadType;
	}

	public String getAssignType() {
		return this.assignType;
	}

	public void setAssignType(String assignType) {
		this.assignType = assignType;
	}

	public String getAssgDesc() {
		return this.assgDesc;
	}

	public void setAssgDesc(String assgDesc) {
		this.assgDesc = assgDesc;
	}

	public String getSubject() {
		return this.subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getFileName() {
		return this.fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getNotifyPaEmail() {
		return this.notifyPaEmail;
	}

	public void setNotifyPaEmail(String notifyPaEmail) {
		this.notifyPaEmail = notifyPaEmail;
	}

	public String getNotifyPaEmailFlag() {
		return this.notifyPaEmailFlag;
	}

	public void setNotifyPaEmailFlag(String notifyPaEmailFlag) {
		this.notifyPaEmailFlag = notifyPaEmailFlag;
	}

	public Timestamp getUploadDate() {
		return this.uploadDate;
	}

	public void setUploadDate(Timestamp uploadDate) {
		this.uploadDate = uploadDate;
	}

	public Timestamp getProcessedDate() {
		return this.processedDate;
	}

	public void setProcessedDate(Timestamp processedDate) {
		this.processedDate = processedDate;
	}

}