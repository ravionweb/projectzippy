package com.schooltrix.hibernate;

/**
 * UploadDocumentClassBranchMap entity. @author MyEclipse Persistence Tools
 */

public class UploadDocumentClassBranchMap implements java.io.Serializable {

	// Fields

	private Long sno;
	private String udId;
	private String bmId;
	private String cmId;

	// Constructors

	/** default constructor */
	public UploadDocumentClassBranchMap() {
	}

	/** full constructor */
	public UploadDocumentClassBranchMap(String udId, String bmId, String cmId) {
		this.udId = udId;
		this.bmId = bmId;
		this.cmId = cmId;
	}

	// Property accessors

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

}