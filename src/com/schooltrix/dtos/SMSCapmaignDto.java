package com.schooltrix.dtos;

public class SMSCapmaignDto {

	
	private String audience =null;
	private String sms_notif=null;
	private String smsbody =null;
	private String selectClass =null;
	private String selectAll =null;
	private String limit =null;
	private String notiSub =null;
//	String smscredit =request.getParameter("smscredit");
	private String IM_ID  =null;
	private String BM_ID =null;
	private String SM_ID =null;
	private String SMSCredit = null;
	private String SMSCount = null;
	
	public String getAudience() {
		return audience;
	}
	public void setAudience(String audience) {
		this.audience = audience;
	}
	public String getSmsbody() {
		return smsbody;
	}
	public void setSmsbody(String smsbody) {
		this.smsbody = smsbody;
	}
	public String getSelectClass() {
		return selectClass;
	}
	public void setSelectClass(String selectClass) {
		this.selectClass = selectClass;
	}
	public String getSelectAll() {
		return selectAll;
	}
	public void setSelectAll(String selectAll) {
		this.selectAll = selectAll;
	}
	public String getLimit() {
		return limit;
	}
	public void setLimit(String limit) {
		this.limit = limit;
	}
	public String getSMSCredit() {
		return SMSCredit;
	}
	public void setSMSCredit(String sMSCredit) {
		SMSCredit = sMSCredit;
	}
	public String getSMSCount() {
		return SMSCount;
	}
	public void setSMSCount(String sMSCount) {
		SMSCount = sMSCount;
	}
	public String getIM_ID() {
		return IM_ID;
	}
	public void setIM_ID(String iM_ID) {
		IM_ID = iM_ID;
	}
	public String getBM_ID() {
		return BM_ID;
	}
	public void setBM_ID(String bM_ID) {
		BM_ID = bM_ID;
	}
	public String getSM_ID() {
		return SM_ID;
	}
	public void setSM_ID(String sM_ID) {
		SM_ID = sM_ID;
	}
	public String getSms_notif() {
		return sms_notif;
	}
	public void setSms_notif(String sms_notif) {
		this.sms_notif = sms_notif;
	}
	public String getNotiSub() {
		return notiSub;
	}
	public void setNotiSub(String notiSub) {
		this.notiSub = notiSub;
	}
	
}
