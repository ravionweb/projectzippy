package com.schooltrix.hibernate;

/**
 * AbstractInstitutionMaster entity provides the base persistence definition of
 * the InstitutionMaster entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractInstitutionMaster implements java.io.Serializable {

	// Fields

	private Long imId;
	private String imName;
	private String imShortName;
	private String imAdress;
	private String imCity;
	private String imState;
	private String imCountry;
	private String imContactPerson;
	private String imEmailId;
	private String imMobile;
	private String imLandline;
	private String isActive;

	// Constructors

	/** default constructor */
	public AbstractInstitutionMaster() {
	}

	/** minimal constructor */
	public AbstractInstitutionMaster(String imShortName,
			String imContactPerson, String imEmailId, String imMobile,
			String isActive) {
		this.imShortName = imShortName;
		this.imContactPerson = imContactPerson;
		this.imEmailId = imEmailId;
		this.imMobile = imMobile;
		this.isActive = isActive;
	}

	/** full constructor */
	public AbstractInstitutionMaster(String imName, String imShortName,
			String imAdress, String imCity, String imState, String imCountry,
			String imContactPerson, String imEmailId, String imMobile,
			String imLandline, String isActive) {
		this.imName = imName;
		this.imShortName = imShortName;
		this.imAdress = imAdress;
		this.imCity = imCity;
		this.imState = imState;
		this.imCountry = imCountry;
		this.imContactPerson = imContactPerson;
		this.imEmailId = imEmailId;
		this.imMobile = imMobile;
		this.imLandline = imLandline;
		this.isActive = isActive;
	}

	// Property accessors

	public Long getImId() {
		return this.imId;
	}

	public void setImId(Long imId) {
		this.imId = imId;
	}

	public String getImName() {
		return this.imName;
	}

	public void setImName(String imName) {
		this.imName = imName;
	}

	public String getImShortName() {
		return this.imShortName;
	}

	public void setImShortName(String imShortName) {
		this.imShortName = imShortName;
	}

	public String getImAdress() {
		return this.imAdress;
	}

	public void setImAdress(String imAdress) {
		this.imAdress = imAdress;
	}

	public String getImCity() {
		return this.imCity;
	}

	public void setImCity(String imCity) {
		this.imCity = imCity;
	}

	public String getImState() {
		return this.imState;
	}

	public void setImState(String imState) {
		this.imState = imState;
	}

	public String getImCountry() {
		return this.imCountry;
	}

	public void setImCountry(String imCountry) {
		this.imCountry = imCountry;
	}

	public String getImContactPerson() {
		return this.imContactPerson;
	}

	public void setImContactPerson(String imContactPerson) {
		this.imContactPerson = imContactPerson;
	}

	public String getImEmailId() {
		return this.imEmailId;
	}

	public void setImEmailId(String imEmailId) {
		this.imEmailId = imEmailId;
	}

	public String getImMobile() {
		return this.imMobile;
	}

	public void setImMobile(String imMobile) {
		this.imMobile = imMobile;
	}

	public String getImLandline() {
		return this.imLandline;
	}

	public void setImLandline(String imLandline) {
		this.imLandline = imLandline;
	}

	public String getIsActive() {
		return this.isActive;
	}

	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}

}