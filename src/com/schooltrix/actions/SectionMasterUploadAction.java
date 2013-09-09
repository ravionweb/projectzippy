package com.schooltrix.actions;
import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.SessionAware;
import org.springframework.beans.BeansException;

import com.opensymphony.xwork2.ActionSupport;
import com.schooltrix.daos.ClassMasterDAO;
import com.schooltrix.daos.SectionMasterDAO;
import com.schooltrix.hibernate.ClassBranchMap;
import com.schooltrix.hibernate.ClassMaster;
import com.schooltrix.hibernate.SectionClassMap;
import com.schooltrix.hibernate.SectionMaster;
import com.schooltrix.managers.ServiceFinder;

public class SectionMasterUploadAction extends ActionSupport implements ServletRequestAware,SessionAware{
	

	Map session;

	private File fileUP;
	private String fileUPContentType;
	private String fileUPFileName;
	public HttpServletRequest request ;
	List<String[]> classList= new ArrayList<String[]>();

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
		String BM_ID = request.getParameter("branchNames");
		String CM_ID = request.getParameter("classNames");
		
		String institutionName = (String)session.get("IM_SN");
		
		try {
			FileReader inpFile = new FileReader(fileUP);
			BufferedReader inpReader = new BufferedReader(inpFile);
			String inpLine = null; 
			int atWhatline=1;
			while ((inpLine = inpReader.readLine()) != null) 
			{
				System.out.println("---"+inpLine+"--------");
				if (inpLine.trim().length() != 0){
				    
				
			  String[] Feilds = inpLine.split(",\\s*");
			  if(Feilds[0]!=null){
				  try {
					if((atWhatline==1) && (Feilds[0].equalsIgnoreCase("Section Name") && Feilds[1].equalsIgnoreCase("Active") )){
						  System.out.println("atWhatline--"+atWhatline);
						  atWhatline++;						  
					  }
					  else if(atWhatline>1){
						  
						  if (CM_ID.equalsIgnoreCase("0")) {//this is for section for All Classes 
							  classMasterList(BM_ID);
						}
						String check =  doInsertIntoDB(Feilds,BM_ID,CM_ID);
						if(check.equalsIgnoreCase("fail")){
							session.put("msg", "Isuue in file");
							return INPUT;						
						}					
								  System.out.println(atWhatline+" --"+Feilds[0]+"-BM_ID-"+BM_ID);
								  atWhatline++;
								  
					  }
					else{
						session.put("msg", "it should contain header");
						  return INPUT;
					  }
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			  }
			}
		  }
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return INPUT;
		}
	//	session.put("msg","Your file name:  " + fileUPFileName+ ",Success !");
		session.put("msg","Saved");
		return SUCCESS;	
	}


	
	private String doInsertIntoDB(String[] inpFeilds,String BM_ID,String CM_ID){
		try {
			SectionMasterDAO smd = null;
			
			 for (int i = 0; i < inpFeilds.length; i++) 
			  {
			     inpFeilds[i] = inpFeilds[i].replace("\"", "");
			    
			  }
			 System.out.println(inpFeilds[0]+"--"+inpFeilds[1]);
			 String isactive="N";
			 if(inpFeilds[1].equalsIgnoreCase("yes") || inpFeilds[1].equalsIgnoreCase("y") ){
				 isactive="Y"; 
			 }
			 //2013-05-04
			 //All-classes ...
			
				smd = (SectionMasterDAO)ServiceFinder.getContext(request).getBean("SectionMasterDAO");
				
				SectionMaster sectionMasterCheck = smd.findByProperty("sectionName", inpFeilds[0]);
				
				long sectionID = 0;
				System.out.println("sectionMasterCheck--"+sectionMasterCheck);
				if (sectionMasterCheck == null) {
								
					SectionMaster sectionData =	new SectionMaster();
					sectionData.setActive(isactive);
					sectionData.setSectionName(inpFeilds[0]);
				smd.save(sectionData);
				sectionID = sectionData.getSeMId();				
				}else{
					sectionID = sectionMasterCheck.getSeMId();
				}
				
				System.out.println(CM_ID+"**CM_ID");
				
			 if (CM_ID.equalsIgnoreCase("0")) {
				 try {
					System.out.println(classList.size()+"***classList.size()");
					 if (classList.size()>0) {
						for (int i = 0; i < classList.size(); i++) {
							Object classMser[] = (Object[])classList.get(i);//classID,ClassName
							//String[] ff = (String[])classMser[0];
							SectionClassMap sbm = new SectionClassMap();
							sbm.setSeMId(sectionID+"");
							sbm.setCmId((String)classMser[0]);
							sbm.setBmId(BM_ID);
							sbm.setActive("Y");
							
							smd.saveSectionClassMap(sbm);
							
						}
						 
						return "success";
						 
					}
				} catch (BeansException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				 
			} else {
				SectionClassMap sbm = new SectionClassMap();
				sbm.setSeMId(sectionID+"");
				sbm.setCmId(CM_ID);
				sbm.setBmId(BM_ID);
				sbm.setActive("Y");
				
				smd.saveSectionClassMap(sbm);
				return "success";
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "fail";
		}
		
		return "fail";
	}
	
	private void classMasterList(String BM_ID) {
		
		try {
			System.out.println(classList.size()+"#######classList.size()");
		if (classList != null && classList.size() ==0) {
			
			ClassMasterDAO 	classMasterdao = (ClassMasterDAO)ServiceFinder.getContext(request).getBean("ClassMasterHibernateDao"); 		
			classList =classMasterdao.getClassMasterList("","",BM_ID,2);
		}	
		
		
		} catch (BeansException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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



