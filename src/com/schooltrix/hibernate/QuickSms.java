package com.schooltrix.hibernate;

import java.sql.Timestamp;

/**
 * QuickSms entity. @author MyEclipse Persistence Tools
 */

public class QuickSms implements java.io.Serializable {

	// Fields

	private Long sno;
	private String imId;
	private String bmId;
	private String smId;
	private String umId;
	private String pdId;
	private String stuId;
	private String classId;
	private String quickBody;
	private String toWhom;
	private String ipaddr;
	private Timestamp reqTime;

	// Constructors

	/** default constructor */
	public QuickSms() {
	}

	/** minimal constructor */
	public QuickSms(String umId, String pdId, String stuId, String classId,
			Timestamp reqTime) {
		this.umId = umId;
		this.pdId = pdId;
		this.stuId = stuId;
		this.classId = classId;
		this.reqTime = reqTime;
	}

	/** full constructor */
	public QuickSms(String imId, String bmId, String smId, String umId,
			String pdId, String stuId, String classId, String quickBody,
			String toWhom, String ipaddr, Timestamp reqTime) {
		this.imId = imId;
		this.bmId = bmId;
		this.smId = smId;
		this.umId = umId;
		this.pdId = pdId;
		this.stuId = stuId;
		this.classId = classId;
		this.quickBody = quickBody;
		this.toWhom = toWhom;
		this.ipaddr = ipaddr;
		this.reqTime = reqTime;
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

	public String getUmId() {
		return this.umId;
	}

	public void setUmId(String umId) {
		this.umId = umId;
	}

	public String getPdId() {
		return this.pdId;
	}

	public void setPdId(String pdId) {
		this.pdId = pdId;
	}

	public String getStuId() {
		return this.stuId;
	}

	public void setStuId(String stuId) {
		this.stuId = stuId;
	}

	public String getClassId() {
		return this.classId;
	}

	public void setClassId(String classId) {
		this.classId = classId;
	}

	public String getQuickBody() {
		return this.quickBody;
	}

	public void setQuickBody(String quickBody) {
		this.quickBody = quickBody;
	}

	public String getToWhom() {
		return this.toWhom;
	}

	public void setToWhom(String toWhom) {
		this.toWhom = toWhom;
	}

	public String getIpaddr() {
		return this.ipaddr;
	}

	public void setIpaddr(String ipaddr) {
		this.ipaddr = ipaddr;
	}

	public Timestamp getReqTime() {
		return this.reqTime;
	}

	public void setReqTime(Timestamp reqTime) {
		this.reqTime = reqTime;
	}

}