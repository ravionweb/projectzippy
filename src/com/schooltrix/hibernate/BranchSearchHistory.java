package com.schooltrix.hibernate;

import java.sql.Timestamp;

/**
 * BranchSearchHistory entity. @author MyEclipse Persistence Tools
 */

public class BranchSearchHistory implements java.io.Serializable {

	// Fields

	private Long bshId;
	private String imId;
	private String userId;
	private String queryString;
	private String searchJson;
	private Timestamp insertedDate;
	private String searchDescription;

	// Constructors

	/** default constructor */
	public BranchSearchHistory() {
	}

	/** minimal constructor */
	public BranchSearchHistory(String userId, String queryString,
			String searchDescription) {
		this.userId = userId;
		this.queryString = queryString;
		this.searchDescription = searchDescription;
	}

	/** full constructor */
	public BranchSearchHistory(String imId, String userId, String queryString,
			String searchJson, Timestamp insertedDate, String searchDescription) {
		this.imId = imId;
		this.userId = userId;
		this.queryString = queryString;
		this.searchJson = searchJson;
		this.insertedDate = insertedDate;
		this.searchDescription = searchDescription;
	}

	// Property accessors

	public Long getBshId() {
		return this.bshId;
	}

	public void setBshId(Long bshId) {
		this.bshId = bshId;
	}

	public String getImId() {
		return this.imId;
	}

	public void setImId(String imId) {
		this.imId = imId;
	}

	public String getUserId() {
		return this.userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getQueryString() {
		return this.queryString;
	}

	public void setQueryString(String queryString) {
		this.queryString = queryString;
	}

	public String getSearchJson() {
		return this.searchJson;
	}

	public void setSearchJson(String searchJson) {
		this.searchJson = searchJson;
	}

	public Timestamp getInsertedDate() {
		return this.insertedDate;
	}

	public void setInsertedDate(Timestamp insertedDate) {
		this.insertedDate = insertedDate;
	}

	public String getSearchDescription() {
		return this.searchDescription;
	}

	public void setSearchDescription(String searchDescription) {
		this.searchDescription = searchDescription;
	}

}