package com.schooltrix.hibernate;

import java.sql.Timestamp;

/**
 * NotificationMaster entity. @author MyEclipse Persistence Tools
 */

public class NotificationMaster implements java.io.Serializable {

	// Fields

	private Long nfId;
	private String imId;
	private String smId;
	private String bmId;
	private String umId;
	private String toWhom;
	private String toClass;
	private String notifSubject;
	private String notifBody;
	private String ipAddr;
	private Timestamp ondate;

	// Constructors

	/** default constructor */
	public NotificationMaster() {
	}

	/** minimal constructor */
	public NotificationMaster(String imId, String smId, String notifSubject,
			Timestamp ondate) {
		this.imId = imId;
		this.smId = smId;
		this.notifSubject = notifSubject;
		this.ondate = ondate;
	}

	/** full constructor */
	public NotificationMaster(String imId, String smId, String bmId,
			String umId, String toWhom, String toClass, String notifSubject,
			String notifBody, String ipAddr, Timestamp ondate) {
		this.imId = imId;
		this.smId = smId;
		this.bmId = bmId;
		this.umId = umId;
		this.toWhom = toWhom;
		this.toClass = toClass;
		this.notifSubject = notifSubject;
		this.notifBody = notifBody;
		this.ipAddr = ipAddr;
		this.ondate = ondate;
	}

	// Property accessors

	public Long getNfId() {
		return this.nfId;
	}

	public void setNfId(Long nfId) {
		this.nfId = nfId;
	}

	public String getImId() {
		return this.imId;
	}

	public void setImId(String imId) {
		this.imId = imId;
	}

	public String getSmId() {
		return this.smId;
	}

	public void setSmId(String smId) {
		this.smId = smId;
	}

	public String getBmId() {
		return this.bmId;
	}

	public void setBmId(String bmId) {
		this.bmId = bmId;
	}

	public String getUmId() {
		return this.umId;
	}

	public void setUmId(String umId) {
		this.umId = umId;
	}

	public String getToWhom() {
		return this.toWhom;
	}

	public void setToWhom(String toWhom) {
		this.toWhom = toWhom;
	}

	public String getToClass() {
		return this.toClass;
	}

	public void setToClass(String toClass) {
		this.toClass = toClass;
	}

	public String getNotifSubject() {
		return this.notifSubject;
	}

	public void setNotifSubject(String notifSubject) {
		this.notifSubject = notifSubject;
	}

	public String getNotifBody() {
		return this.notifBody;
	}

	public void setNotifBody(String notifBody) {
		this.notifBody = notifBody;
	}

	public String getIpAddr() {
		return this.ipAddr;
	}

	public void setIpAddr(String ipAddr) {
		this.ipAddr = ipAddr;
	}

	public Timestamp getOndate() {
		return this.ondate;
	}

	public void setOndate(Timestamp ondate) {
		this.ondate = ondate;
	}

}