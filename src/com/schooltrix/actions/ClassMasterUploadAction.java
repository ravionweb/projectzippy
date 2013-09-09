/**
 * 
 */
package com.schooltrix.actions;
import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;
import com.schooltrix.daos.ClassMasterDAO;
import com.schooltrix.hibernate.ClassBranchMap;
import com.schooltrix.hibernate.ClassMaster;
import com.schooltrix.managers.ServiceFinder;

public class ClassMasterUploadAction extends ActionSupport implements ServletRequestAware,SessionAware{
	

	Map session;

	private File fileUP;
	private String fileUPContentType;
	private String fileUPFileName;
	public HttpServletRequest request ;
	

	public String execute() {		

		if(fileUPFileName==null){
			session.put("msg", "please upload csv/txt files");
			return INPUT;
		}
		else if(!fileUPFileName.substring(Math.max(0, fileUPFileName.length() - 3)).equalsIgnoreCase("txt") && !fileUPFileName.substring(Math.max(0, fileUPFileName.length() - 3)).equalsIgnoreCase("csv")){
			session.put("msg", "please upload only csv file or txt files");
			return INPUT;			
		}
		
		String SM_ID = request.getParameter("schoolNames");
		long BM_ID = Long.parseLong(request.getParameter("branchNames"));
		
		String institutionName = (String)session.get("IM_SN");
		StringBuffer returnMsg = new StringBuffer();//2013-09-01
		
		int atWhatline=1;
		int successCount=0;
		try {
			FileReader inpFile = new FileReader(fileUP);
			BufferedReader inpReader = new BufferedReader(inpFile);
			String inpLine = null; 
			while ((inpLine = inpReader.readLine()) != null) 
			{
				if (inpLine.trim().length() != 0){
					
			  try {
				String[] Feilds = inpLine.split(",\\s*");
				  if(Feilds[0]!=null){
					  if((atWhatline==1) && (Feilds[0].equalsIgnoreCase("Class Name") && Feilds[1].equalsIgnoreCase("Active") )){
						  System.out.println("atWhatline--"+atWhatline);
						  atWhatline++;
						  
					  }
					  else if(atWhatline>1){
						String check =  doInsertIntoDB(Feilds,BM_ID);
						if (check.equalsIgnoreCase("already")) {
							returnMsg.append(Feilds[0]+"-exist,");
						}else	if(check.equalsIgnoreCase("fail")){
							returnMsg.append(Feilds[0]+"-not valid,");
							//session.put("msg", "Isuue in file");
							//return INPUT;						
						}else if(check.equalsIgnoreCase("success")){
							returnMsg.append(Feilds[0]+"-success,");
							successCount++;
						}
						
						/*if(check.equalsIgnoreCase("fail")){
							session.put("msg", "Isuue in file");
							return INPUT;						
						}*/
						//  writeIntoFolder();
								  System.out.println(atWhatline+" --"+Feilds[0]+"-BM_ID-"+BM_ID);
								  atWhatline++;
					  }
					else{
						session.put("msg", "it should contain header");
						  return INPUT;
					  }
					}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			 }
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return INPUT;
		}
		//session.put("msg","Saved");
		StringBuffer retMSG = new StringBuffer();
		if (atWhatline>2) {
			//some records other than header
			retMSG.append((atWhatline-2)+"-record(s) Uploaded.  ");
			if (successCount>0) {
				retMSG.append((successCount)+"-record(s) Successfully Saved.Details as Bleow:<br>");
			}else{
				retMSG.append("No Success records. Details as Bleow:<br>");
			}
		}else{
			returnMsg.append("No Rows Found,");
		}
		String newRes = retMSG+ returnMsg.substring(0, returnMsg.length()-1);
		System.out.println(retMSG+"**"+returnMsg+"**"+newRes);
		session.put("msg",newRes);
		return SUCCESS;	
	}


	
	private String doInsertIntoDB(String[] inpFeilds,long BM_ID){
		 for (int i = 0; i < inpFeilds.length; i++) 
		  {
		     inpFeilds[i] = inpFeilds[i].replace("\"", "");
		    
		  }
		 System.out.println(inpFeilds[0]+"--"+inpFeilds[1]);
		 String isactive="N";
		 if(inpFeilds[1].equalsIgnoreCase("yes") || inpFeilds[1].equalsIgnoreCase("y") ){
			 isactive="Y"; 
		 }
			try {
				ClassMasterDAO cmd = (ClassMasterDAO)ServiceFinder.getContext(request).getBean("ClassMasterDAO");

				/*//here we need to check whether the class name already exist or not?
				ClassMaster classMasterCheck = cmd.findByProperty("className", inpFeilds[0]);*/

				boolean classMasterCheck = cmd.isClassByName(inpFeilds[0],BM_ID+"");
				
				long classID = 0;
				System.out.println("classMasterCheck--"+classMasterCheck);
				if (classMasterCheck == false) {
								
				ClassMaster classData =	new ClassMaster();
				classData.setActive(isactive);
				classData.setClassName(inpFeilds[0]);
				cmd.save(classData);
				classID = classData.getCmId();				
				
				ClassBranchMap cbm = new ClassBranchMap();
				cbm.setCmId(classID);
				cbm.setBmId(BM_ID);
				cbm.setActive("Y");
				
				cmd.saveClassBranchMap(cbm);
				
				
				return "success";
			
			}else{
				 return "already";
			}
				
			} catch (Exception e) {
				System.out.println("in exption");
				e.printStackTrace();
				return "fail";
			}
	}
	
 

	@Override
	public void setSession(Map session) {
		// TODO Auto-generated method stub
		System.out.println(session+"------------------------------------");
		this.session = session;
	}

	@Override
	public void setServletRequest(HttpServletRequest request) {
		// TODO Auto-generated method stub
		System.out.println(request+"--------request----------------------------");
		this.request = request;  
	}

	public File getFileUP() {
		return fileUP;
	}

	public void setFileUP(File fileUP) {
		this.fileUP = fileUP;
	}

	public String getFileUPContentType() {
		return fileUPContentType;
	}

	public void setFileUPContentType(String fileUPContentType) {
		this.fileUPContentType = fileUPContentType;
	}

	public String getFileUPFileName() {
		return fileUPFileName;
	}

	public void setFileUPFileName(String fileUPFileName) {
		this.fileUPFileName = fileUPFileName;
	}

	
}