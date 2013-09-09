package com.schooltrix.actions;

import java.util.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.SessionAware;
import org.hibernate.Session;
import org.json.JSONArray;
import org.json.JSONObject;

import com.opensymphony.xwork2.ActionSupport;
import com.schooltrix.daos.EmailCampaignSession;
import com.schooltrix.daos.SMSCampaignSession;
import com.schooltrix.daos.StaffDetailsDAO;
import com.schooltrix.dtos.EmailCapmaignDto;
import com.schooltrix.dtos.SMSCapmaignDto;
import com.schooltrix.managers.ServiceFinder;

public class EmailCampaign extends ActionSupport implements ServletRequestAware,SessionAware{
	
	HttpServletRequest request = null;
	Map session = null;
	@Override
	public String execute() throws Exception{
		// TODO Auto-generated method stub
		//afterwords we put all dose values into some dto ...if we want to pass these to some other calss --we pass dto only
		try{
		EmailCapmaignDto emailDto = new EmailCapmaignDto();
		//emailDto.
		emailDto.setAudience(request.getParameter("radio1"));
		
		emailDto.setIM_ID((String)getSession().get("IM_ID"));
		
		emailDto.setSM_ID(request.getParameter("schoolNames"));
		emailDto.setBM_ID(request.getParameter("branchNames"));
	
		emailDto.setFrmmail(request.getParameter("frmmail"));
		emailDto.setEbody(request.getParameter("ebody"));

		emailDto.setSelectAll(request.getParameter("selectAll"));
		emailDto.setSelectClass(request.getParameter("selectClass"));
		emailDto.setSubj(request.getParameter("subj"));
		
	
		
	
		String toWhome= "";
		
			

		EmailCampaignSession emailSession  =null;
		//smsSession.testSessionFac();
		emailSession = (EmailCampaignSession)ServiceFinder.getContext(request).getBean("EmailCampaignSession");
		emailSession.createSession();
		
		/*
		JSONArray jsonPersonData = jsonArray.getJSONArray(1);
		for (int i=0; i<jsonPersonData.length(); i++) {
		    JSONObject item = jsonPersonData.getJSONObject(i);
		    String name = item.getString("name");
		    String surname = item.getString("surname");
		}*/
		
		JSONArray data = emailSession.getEmailUsersList(emailDto);
		
		System.out.println(data.length()+"::::");
		for (int i = 0; i < data.length(); i++) {
			   JSONObject item = data.getJSONObject(i);
			System.out.println("--"+item.getString("First_Name"));
		}
		
		if(data !=null){
		//smsDto.setSMSCount(data.get+"");//it is missing
			emailDto.setEmailCount(data.length()+"");//it is missing
		}
	
		request.setAttribute("usersData", data);
		request.setAttribute("EmailDto", emailDto);
	//	smsSession.releseSession();
		return "previewEmailJsp";
		
	}catch (Exception e) {
		// TODO: handle exception
		return "previewEmailJspFail";
	}
	}

	@Override
	public void setServletRequest(HttpServletRequest arg0) {
		// TODO Auto-generated method stub
		request = arg0;
	}

	@Override
	public void setSession(Map<String, Object> session) {
		// TODO Auto-generated method stub
		this.session = session;
	}
	public Map getSession() {
		
		return session;
	}
	
}
