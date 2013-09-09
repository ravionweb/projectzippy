package com.schooltrix.actions;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;

public class PhotoDownloadAction extends ActionSupport implements ServletRequestAware,SessionAware{

	HttpServletRequest request = null;
	Map session;
	String userType = null;
	String userFileName = null;
	String admissionNumber = null;
	String shortName = null;
	String fileName = null;
	
	   public String getFileName() {
		   System.out.println(fileName+"**getFile");
		return fileName;
	}
	   
	public void setFileName(String fileName1) {
		int i = fileName1.lastIndexOf('.');
		userType 				= request.getParameter("type");
		try{
			admissionNumber 	= request.getParameter("admNumber");//fileName purpose.. only for student purpose...parent time.. may use name or something...
		}catch (Exception e) {
		}
		
		int count = StringUtils.countMatches(fileName1, ".");
		String extension = "";
		if (i > 0 && count<2) 
			extension = fileName1.substring(i+1);
		if (StringUtils.isNotBlank(admissionNumber)) {
			fileName = admissionNumber+"."+extension;
		}else{
		fileName = userType+"."+extension;
		}
	}
	
	
	public InputStream getInputStream() {
			shortName =(String) session.get("IM_SN");
		  try {
			String path			= request.getSession().getServletContext().getRealPath("/")+"uploaded/"+shortName+"/"+userType;
			if (userFileName.equalsIgnoreCase("noImage.jpg")) {
				path					=request.getSession().getServletContext().getRealPath("/");
			}
			
			return new FileInputStream(new File(path+"/"+userFileName));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}  
	}
 
	public String execute() throws Exception {
	
		///Schooltrix/uploaded/NTR/ST
		userType 				= request.getParameter("type");
		userFileName 	= request.getParameter("fileName");
		try{
		admissionNumber 	= request.getParameter("admNumber");//fileName purpose.. only for student purpose...parent time.. may use name or something...
		}catch (Exception e) {
		}
		System.out.println("in Image ---download *"+userFileName+"***"+userType+"*admissionNumber*"+admissionNumber);
		
		String IM_ID = (String)session.get("IM_ID");
		String SM_ID = (String)session.get("SM_ID");
		String BM_ID = (String)session.get("BM_ID");
		
		String UM_ID = (String)session.get("UM_ID");
		
		if (IM_ID == null || SM_ID == null || BM_ID ==null || UM_ID==null ) {
			return "fail";
		}
		
	    return "success";
	}

	@Override
	public void setServletRequest(HttpServletRequest request) {
		// TODO Auto-generated method stub
		this.request = request;  
	}
	
	@Override
	public void setSession(Map<String, Object> session) {
		// TODO Auto-generated method stub
	//	System.out.println(session+"------------------------------------");
		this.session = session;
	}
}
