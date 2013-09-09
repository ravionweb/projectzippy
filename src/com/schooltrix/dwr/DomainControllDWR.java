package com.schooltrix.dwr;

import java.awt.Window;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.directwebremoting.WebContextFactory;
import org.springframework.beans.BeansException;

import com.schooltrix.daos.DomainDAO;
import com.schooltrix.hibernate.DomainControl;
import com.schooltrix.managers.ServiceFinder;

public class DomainControllDWR {
	
	HttpServletResponse response = WebContextFactory.get().getHttpServletResponse();
	HttpServletRequest request = WebContextFactory.get().getHttpServletRequest();
	HttpSession session = WebContextFactory.get().getSession();
	
	public String domainControllerCheck() {
		
		String flag = "";
		try {
			String path								=  request.getContextPath();
			String scheme						=  request.getScheme();
			String domainName				=  request.getServerName();
			DomainDAO domainDAO 	=  null;
			DomainControl domainData = null;
			String subDomain[] = null;
			
			
			System.out.println("domainName-->"+domainName);
			
			if(domainName != null && domainName != "null" &&domainName != ""){
			
				//if (domainName.toLowerCase().equalsIgnoreCase("superadmin.schoolzippy.com")) {
					if (domainName.toLowerCase().equalsIgnoreCase("superadmin.schooltrix1.com")) {
					System.out.println("in super admin home----");
					session.setAttribute("url", domainName);
					session.setAttribute("superadminHome", "Y");			
					return "success";
				}			
				//1) this is based on sub domain
			//if(domainName.toLowerCase().contains("schoolzippy.com".toLowerCase()) || domainName.toLowerCase().endsWith("schoolzippy.com".toLowerCase())){
			if(domainName.toLowerCase().contains("schooltrix1.com".toLowerCase()) || domainName.toLowerCase().endsWith("schooltrix1.com".toLowerCase())){
					//sub domain is k
					 /*
					  IMPORTANT : Some special characters need to be escaped while providing them as
					  delimiters like "." and "|".-----so use    "\\."  for "."
					  */
					subDomain = domainName.split("\\.");
					System.out.println("subDomain.lenth--"+subDomain.length);
					
					if (subDomain.length>2) {
					
					 try {
						domainDAO = (DomainDAO)ServiceFinder.getContext(request).getBean("DomainContlHibernateDao"); 		
						 domainData=  domainDAO.findByProperty("shortName", subDomain[0]);
						 if (domainData != null) {
							 System.out.println("in domaindata != null    ...."+domainData.getUrl()+"-- subDomain[0]---"+ subDomain[0]);
							 session.setAttribute("short_name", domainData.getShortName());
							 session.setAttribute("url", domainData.getUrl());					 
						 }else{
							 return "fail";
						 }
							
					} catch (BeansException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						return "fail";
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						return  "fail";
					}				
					 return "success";
				 }
			  }else if(domainName.length()>5){
					// URL based checking
				  String domainNameTemp = domainName.toLowerCase();
				  System.out.println("domainName---URL--"+domainNameTemp);
				  try {
						domainDAO = (DomainDAO)ServiceFinder.getContext(request).getBean("DomainContlHibernateDao"); 		
						
						domainData=  domainDAO.findByProperty("url", domainNameTemp);//return may null also
						
						if(domainData == null && domainNameTemp.startsWith("www.")){
							System.out.println("www.--adding");
							
							int i = domainNameTemp.indexOf("www.");
							System.out.println(i+"=====");
							if(i != -1) {							
							
							System.out.println("--->"+domainNameTemp.substring(i+4));
							domainData=  domainDAO.findByProperty("url", domainNameTemp.substring(i+4));//return may null also
						}
						}else if(domainData == null){
							domainData=  domainDAO.findByProperty("url", "www."+domainNameTemp);//return may null also
							
						}
						
						 System.out.println("domainData---in URL---"+domainData);
						 
						 if (domainData != null) {
							 System.out.println("in domaindata-URL Based    ...."+domainData.getUrl()+"-- subDomain---"+ domainData.getShortName());
							
				
							if(domainData.getShortName().equalsIgnoreCase("SAAA")){
								//app fog purpose req...changed
								session.setAttribute("url", domainData.getUrl());			
								session.setAttribute("superadminHome", "Y");		
							}else{
								session.setAttribute("url", domainData.getUrl());			
								 session.setAttribute("short_name", domainData.getShortName());
								
							}
							 
						 }else{
							 return "fail";
						 }
							
					} catch (BeansException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						return "fail";
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						return  "fail";
					}				
					 return "success";
					
		}
				
				
	  }// main if
			
			return "fail";
	} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "fail";
	}
		
		/*if (flag.equalsIgnoreCase("fail")) {
			try {
				System.out.println("in faillll if---"+response);
				RequestDispatcher dis = request.getRequestDispatcher("/accessdenied.action");
				dis.forward(request, response);
				//response.sendRedirect("/accessdenied.action");
				String urr	= request.getRealPath(""); 
				System.out.println("in iff afterrrr-----------<>"+urr);
						response.sendRedirect(urr+"/accessdenied.action");
			}catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}*/
		
   }
}
