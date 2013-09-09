package com.schooltrix.actions;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;
import com.schooltrix.utils.SchoolMasterSearchData;

public class DownloadSchoolSearch extends ActionSupport implements ServletRequestAware,SessionAware{

	private static final long serialVersionUID = 1L;
	HttpServletRequest request = null;
	Map session;
	private InputStream fileInputStream;
	
	private String fileName;
	
	public String downloadSchoolSearch() {
		
		String schoolNames[] 	= request.getParameterValues("schoolNames");
		
		String shortName[]		= request.getParameterValues("shortName");
		String state[]					= request.getParameterValues("state");
		String city[]					= request.getParameterValues("city");
		String addr						= request.getParameter("addr");//null ???????????
		String contactPerson		= request.getParameter("contactPerson");
		String mobile					= request.getParameter("mobile");
		String landline				= request.getParameter("landline");
		String email					= request.getParameter("email");
		String active					= request.getParameter("active");
		if (active == null) {
			active = "N";
		}else if (active.equalsIgnoreCase("on")) {
			active = "Y";
		} 
		
		String UM_ID	 = (String)session.get("UM_ID");
		String pdID 		= (String)session.get("pdID");		
		String IM_ID 		= (String)session.get("IM_ID");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		setFileName("SchoolSearch-"+sdf.format(new Date())+".csv");//user define File Name......................
		
		SchoolMasterSearchData  data = new SchoolMasterSearchData();
		try{
			List resultt = new ArrayList();
			resultt =  data.getSchoolSearchData(schoolNames,shortName, state , city,  addr, contactPerson ,  mobile,landline ,	email, active,IM_ID,0,request,null);
			
			StringBuffer docPreapare = new StringBuffer();	 
			
			
				 if (resultt.size() <=0) {
					 docPreapare.append("No records found");
				 }else{
					 docPreapare.append("	Short Name,School Name	,Address	,City	,Contact Person	,Email Id	,Mobile	,Landline	,Active");
					 
					 docPreapare.append("\n");
					 for (Iterator iterator = resultt.iterator(); iterator.hasNext();) {
						Object[] object = (Object[]) iterator.next();
						docPreapare.append((String)object[2]+","+(String)object[3]+","+object[4].toString().replaceAll(",", " ")+","+(String)object[5]+","+(String)object[7]+","+(String)object[8]+","+(String)object[9]+","+(String)object[10]+","+(object[11].toString().equalsIgnoreCase("Y")?"Yes":"No")+"\n");
					}
				 }
				// System.out.println("docPreapare-->in Download school-->"+docPreapare);
				 fileInputStream = new ByteArrayInputStream(docPreapare.toString().getBytes());
				 
				 return "success";
			} catch (Exception e) {
				e.printStackTrace();
				return "fail";
			}
	}
	
	
	
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	
	public InputStream getInputStream(){  
	      try{	    	  
	             return fileInputStream;  
	      }catch(Exception e){
	    	  System.out.println(e);
	    	  return null;    
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
	
}
