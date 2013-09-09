package com.schooltrix.hibernate;

import java.sql.Timestamp;

/**
 * UploadDocumentClassBranchMapHistory entity. @author MyEclipse Persistence
 * Tools
 */

public class UploadDocumentClassBranchMapHistory implements
		java.io.Serializable {

	// Fields

	private Long udcbmh;
	private Long sno;
	private String udId;
	private String bmId;
	private String cmId;
	private String action;
	private Timestamp updatedDate;

	// Constructors

	/** default constructor */
	public UploadDocumentClassBranchMapHistory() {
	}

	/** minimal constructor */
	public UploadDocumentClassBranchMapHistory(Long sno, String udId,
			String bmId, String cmId, String action) {
		this.sno = sno;
		this.udId = udId;
		this.bmId = bmId;
		this.cmId = cmId;
		this.action = action;
	}

	/** full constructor */
	public UploadDocumentClassBranchMapHistory(Long sno, String udId,
			String bmId, String cmId, String action, Timestamp updatedDate) {
		this.sno = sno;
		this.udId = udId;
		this.bmId = bmId;
		this.cmId = cmId;
		this.action = action;
		this.updatedDate = updatedDate;
	}

	// Property accessors

	public Long getUdcbmh() {
		return this.udcbmh;
	}

	public void setUdcbmh(Long udcbmh) {
		this.udcbmh = udcbmh;
	}

	public Long getSno() {
		return this.sno;
	}

	public void setSno(Long sno) {
		this.sno = sno;
	}

	public String getUdId() {
		return this.udId;
	}

	public void setUdId(String udId) {
		this.udId = udId;
	}

	public String getBmId() {
		return this.bmId;
	}

	public void setBmId(String bmId) {
		this.bmId = bmId;
	}

	public String getCmId() {
		return this.cmId;
	}

	public void setCmId(String cmId) {
		this.cmId = cmId;
	}

	public String getAction() {
		return this.action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public Timestamp getUpdatedDate() {
		return this.updatedDate;
	}

	public void setUpdatedDate(Timestamp updatedDate) {
		this.updatedDate = updatedDate;
	}

}