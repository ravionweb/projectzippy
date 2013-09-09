package com.schooltrix.actions;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.RequestAware;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;

public class Logout extends ActionSupport implements ServletRequestAware,SessionAware{

	Map session = null;
	HttpServletRequest request = null;
	
	
	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		
		if( (session.get("short_name") != "null" && session.get("short_name") != null) || (session.get("superadminHome") !="null" && session.get("superadminHome") !=null)){
		
			if(session.get("IM_ID") !=null  && session.get("UM_ID") !=null && session.get("UT_ID") !=null && session.get("UT_ID") !=null){
				session = null;
				
				
				
			}
		
		
		
		}
		
		
		
		
		
		
		
		
		
		return super.execute();
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	@Override
	public void setSession(Map<String, Object> session) {
		// TODO Auto-generated method stub
		this.session = session;
	}



	@Override
	public void setServletRequest(HttpServletRequest request) {
		// TODO Auto-generated method stub
		this.request = request;
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
