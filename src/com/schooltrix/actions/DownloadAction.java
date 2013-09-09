package com.schooltrix.actions;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.interceptor.RequestAware;
import org.apache.struts2.interceptor.ServletRequestAware;

import com.opensymphony.xwork2.ActionSupport;


public class DownloadAction extends ActionSupport implements ServletRequestAware{  
	
	   private String fileName;
	   HttpServletRequest request = null;
	   public String getFileName() {
		return fileName;
	}
	   
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public InputStream getInputStream(){  
	      try{  
	    	  String path= request.getSession().getServletContext().getRealPath("/")+"csvfiles/";
	             return new FileInputStream(new File(path+fileName));  
	      }catch(Exception e){
	    	  System.out.println(e);
	    	  return null;    
	      }  
	   }  
	   public String execute(){ 
		   System.out.println("fileName"+fileName);
	      return SUCCESS;  
	    }

		@Override
		public void setServletRequest(HttpServletRequest arg0) {
			// TODO Auto-generated method stub
			request = arg0;
		}
	   
	   
	  

/*	@Action(value = "downloadFile", results = { @Result(name = "success", type = "stream", params = { "contentType", "application/octet-stream", "inputName", "fileInputStream", "contentDisposition", "attachment; filename=\"${fileName}.txt\"", "bufferSize", "1024" }) })
	public String downloadFile() throws Exception {
	fileName = "license.txt";
	fileInputStream = new FileInputStream(new File("C:\\", fileName));
	return SUCCESS;
	}
	private InputStream       fileInputStream;
	private String      fileName;
	public InputStream getFileInputStream() {
	return fileInputStream;
	}	*/


}

