package com.schooltrix.hibernate;

/**
 * DomainControl entity. @author MyEclipse Persistence Tools
 */

public class DomainControl implements java.io.Serializable {

	// Fields

	private Long dcId;
	private String url;
	private String shortName;
	private String dedicated;

	// Constructors

	/** default constructor */
	public DomainControl() {
	}

	/** minimal constructor */
	public DomainControl(String url, String shortName) {
		this.url = url;
		this.shortName = shortName;
	}

	/** full constructor */
	public DomainControl(String url, String shortName, String dedicated) {
		this.url = url;
		this.shortName = shortName;
		this.dedicated = dedicated;
	}

	// Property accessors

	public Long getDcId() {
		return this.dcId;
	}

	public void setDcId(Long dcId) {
		this.dcId = dcId;
	}

	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getShortName() {
		return this.shortName;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
	}

	public String getDedicated() {
		return this.dedicated;
	}

	public void setDedicated(String dedicated) {
		this.dedicated = dedicated;
	}

}