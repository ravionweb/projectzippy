package com.schooltrix.hibernate;

/**
 * StateMaster entity. @author MyEclipse Persistence Tools
 */

public class StateMaster implements java.io.Serializable {

	// Fields

	private Integer stateId;
	private String stateName;
	private String active;
	private String countryId;
	private String numericCode;

	// Constructors

	/** default constructor */
	public StateMaster() {
	}

	/** full constructor */
	public StateMaster(String stateName, String active, String countryId,
			String numericCode) {
		this.stateName = stateName;
		this.active = active;
		this.countryId = countryId;
		this.numericCode = numericCode;
	}

	// Property accessors

	public Integer getStateId() {
		return this.stateId;
	}

	public void setStateId(Integer stateId) {
		this.stateId = stateId;
	}

	public String getStateName() {
		return this.stateName;
	}

	public void setStateName(String stateName) {
		this.stateName = stateName;
	}

	public String getActive() {
		return this.active;
	}

	public void setActive(String active) {
		this.active = active;
	}

	public String getCountryId() {
		return this.countryId;
	}

	public void setCountryId(String countryId) {
		this.countryId = countryId;
	}

	public String getNumericCode() {
		return this.numericCode;
	}

	public void setNumericCode(String numericCode) {
		this.numericCode = numericCode;
	}

}