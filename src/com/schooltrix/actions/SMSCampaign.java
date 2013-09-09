	package com.schooltrix.actions;
	
	import com.opensymphony.xwork2.ActionSupport;
	import com.schooltrix.daos.SMSCampaignSession;
	import com.schooltrix.dtos.SMSCapmaignDto;
	import com.schooltrix.managers.ServiceFinder;
	import java.io.PrintStream;
	import java.util.Map;
	import javax.servlet.http.HttpServletRequest;
	import org.apache.struts2.interceptor.ServletRequestAware;
	import org.apache.struts2.interceptor.SessionAware;
	import org.json.JSONArray;
	import org.json.JSONObject;
	import org.springframework.context.ApplicationContext;
	
	public class SMSCampaign 	extends ActionSupport 	implements ServletRequestAware, SessionAware	{
	HttpServletRequest request = null;
	Map session = null;
	
	public String execute() throws Exception{
		System.out.println("smscre-->"+request.getParameter("smscredits1"));
		try
		{
		SMSCapmaignDto smsDto = new SMSCapmaignDto();
		
		smsDto.setAudience(request.getParameter("radio1"));

		smsDto.setSms_notif(request.getParameter("sms_notification"));
		
		//notificationSub
		smsDto.setIM_ID((String)getSession().get("IM_ID"));
		
		smsDto.setSM_ID(request.getParameter("schoolNames"));
		smsDto.setBM_ID(request.getParameter("branchNames"));
		
		smsDto.setSmsbody(request.getParameter("smsbody"));
		smsDto.setLimit(request.getParameter("limit"));

		smsDto.setSelectAll(request.getParameter("selectAll"));
		smsDto.setSelectClass(request.getParameter("selectClass"));
		smsDto.setNotiSub(request.getParameter("notificationSub"));
		
		smsDto.setSMSCredit(request.getParameter("smscredits1"));
		
		String toWhome = "";
		
		System.out.println("radio1::" + smsDto.getSelectAll() + "---" + request.getParameter("smscredits1")+"***"+smsDto.getSMSCredit());
		System.out.println("smsbody::" + smsDto.getSmsbody());
		
		SMSCampaignSession smsSession = null;
		
		smsSession = (SMSCampaignSession)ServiceFinder.getContext(request).getBean("SMSCampaignSession");
		smsSession.createSession();
		
		JSONArray data = smsSession.getSMSUsersList(smsDto);
		
		System.out.println(data.length() + "::::");
		for (int i = 0; i < data.length(); i++) {
		JSONObject item = data.getJSONObject(i);
		System.out.println("--" + item.getString("First_Name"));
		}
		
		if (data != null)
		{
		smsDto.setSMSCount(data.length()+"");
		}
		request.setAttribute("usersData", data);
		request.setAttribute("dto", smsDto);
		
		return "previewJsp";
		}
		catch (Exception e) {}
		return "previewJspFail";
	}
	
	public void setServletRequest(HttpServletRequest arg0){
	request = arg0;
	}
	
	public void setSession(Map<String, Object> session){
	this.session = session;
	}
	
	public Map getSession() {
	return session;
	}
	} 