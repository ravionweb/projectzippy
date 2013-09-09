package com.schooltrix.hibernate;

/**
 * CountryMaster entity. @author MyEclipse Persistence Tools
 */

public class CountryMaster implements java.io.Serializable {

	// Fields

	private Long countryId;
	private String countryName;
	private String active;
	private String defaultCountry;
	private String countryIsd;
	private String smsActive;
	private String currencyCode;
	private String numericCode;

	// Constructors

	/** default constructor */
	public CountryMaster() {
	}

	/** full constructor */
	public CountryMaster(String countryName, String active,
			String defaultCountry, String countryIsd, String smsActive,
			String currencyCode, String numericCode) {
		this.countryName = countryName;
		this.active = active;
		this.defaultCountry = defaultCountry;
		this.countryIsd = countryIsd;
		this.smsActive = smsActive;
		this.currencyCode = currencyCode;
		this.numericCode = numericCode;
	}

	// Property accessors

	public Long getCountryId() {
		return this.countryId;
	}

	public void setCountryId(Long countryId) {
		this.countryId = countryId;
	}

	public String getCountryName() {
		return this.countryName;
	}

	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}

	public String getActive() {
		return this.active;
	}

	public void setActive(String active) {
		this.active = active;
	}

	public String getDefaultCountry() {
		return this.defaultCountry;
	}

	public void setDefaultCountry(String defaultCountry) {
		this.defaultCountry = defaultCountry;
	}

	public String getCountryIsd() {
		return this.countryIsd;
	}

	public void setCountryIsd(String countryIsd) {
		this.countryIsd = countryIsd;
	}

	public String getSmsActive() {
		return this.smsActive;
	}

	public void setSmsActive(String smsActive) {
		this.smsActive = smsActive;
	}

	public String getCurrencyCode() {
		return this.currencyCode;
	}

	public void setCurrencyCode(String currencyCode) {
		this.currencyCode = currencyCode;
	}

	public String getNumericCode() {
		return this.numericCode;
	}

	public void setNumericCode(String numericCode) {
		this.numericCode = numericCode;
	}

}