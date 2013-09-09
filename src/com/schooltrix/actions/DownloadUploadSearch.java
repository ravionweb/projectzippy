package com.schooltrix.actions;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.SessionAware;
import org.directwebremoting.export.Data;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.BeansException;

import com.opensymphony.xwork2.ActionSupport;
import com.schooltrix.daos.ClassMasterDAO;
import com.schooltrix.daos.UploadDocDAO;
import com.schooltrix.hibernate.SubjectMaster;
import com.schooltrix.managers.ServiceFinder;
import com.schooltrix.utils.UploadDocUtil;

public class DownloadUploadSearch extends ActionSupport implements ServletRequestAware,SessionAware{


	private static final long serialVersionUID = 1L;
	HttpServletRequest request = null;
	Map session;
	private InputStream fileInputStream;
	
	   private String fileName;
	   public String getFileName() {
		return fileName;
	}
		public void setFileName(String fileName) {
			this.fileName = fileName;
		}
	/**duplicate code ......actually same like getUploadedDocForEditNew() in AssignmentGetDwr....
	 *difference is here search history rel code not there....
	 *i will remove later this common code......
	 *already created a new class for common code UploadDocUtil....but not all done here... 
	 */
	public String downloadUploadDocSearch() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat sdf2 = new SimpleDateFormat("dd-MMM-yyyy");
		
		setFileName("UploadSearch-"+sdf.format(new Date())+".csv");//user define File Name......................
		
		System.out.println("Entereddddddddddd");
	/*	schoolNames,branchNames,selectClass,selectType,uploadType,
			fileType,assignmentType,selectSubject,fromDate,toDate,*/
			String schoolNames[] 	= request.getParameterValues("schoolNames");
			
			String branchNames[]	= request.getParameterValues("branchNames");
			String selectClass[]		= request.getParameterValues("selectClass");
			String selectType[]		= request.getParameterValues("selectType");
			String uploadType			= request.getParameter("uploadType");//null ???????????
			String fileType				= request.getParameter("fileType");
			String assignmentType	= request.getParameter("assignmentType");
			String selectSubject		= request.getParameter("selectSubject");
			String fromDate				= request.getParameter("fromDate");
			String toDate					= request.getParameter("toDate");
		
			if (assignmentType == null) {//This condition not req in Dwr ,,,bcz..there we r sending jQuery selected value....but coming to java action dt filed disabled means ..taking as NULL
				assignmentType = "0";
			} 			
			if (selectSubject == null) {//This condition not req in Dwr ,,,bcz..there we r sending jQuery selected value....but coming to java action dt filed disabled means ..taking as NULL
				selectSubject = "0";
			} 			
			
			System.out.println(uploadType+"hhhhhh-->"+assignmentType);

		try {
			//Start-----------2013=07-06 ---common code moved to Another class ..purpose is for download...
			
			UploadDocUtil docUtil = new UploadDocUtil();
			String[] resultStrings = docUtil.prepareParamsForUploadDoc(schoolNames,branchNames,selectClass,selectType,uploadType,fileType,assignmentType,selectSubject,fromDate,toDate);
			
			if (resultStrings == null) {
				//return null;
			} 
			
			//smIDs,bmIDs,cmIDs,userIDs,uploadType,fileType,assignmentType,selectSubject,btCodt
			
			String smIDs = resultStrings[0];
			String bmIDs = resultStrings[1];
			String cmIDs = resultStrings[2];
			String userIDs = resultStrings[3];
			
			//END
			
			String fileTypeOrig = fileType;
			
			UploadDocDAO uploadDocDAO = null;
			ClassMasterDAO classMasterdao = null;
			
			System.out.println("in dwrrr getUploadedDocForEditNew**"+fromDate+"***"+toDate);
			
			
			JSONObject jObject = new JSONObject();
			
				String UM_ID	 = (String)session.get("UM_ID");
				String pdID 		= (String)session.get("pdID");		
				String IM_ID 		= (String)session.get("IM_ID");
					
					try {//before setting reqqqqqq....
						jObject.put("uploadType", uploadType);
						jObject.put("fileType", fileType);
						jObject.put("assignmentType", assignmentType);
						jObject.put("selectSubject", selectSubject);
						jObject.put("dateQStringFROM", fromDate);
						jObject.put("dateQStringTO", toDate);
					
					
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			
					//smIDs,bmIDs,cmIDs,userIDs,
					//4-uploadType,fileType,assignmentType,selectSubject,btCodt
					
					uploadType 			= resultStrings[4];
					fileType					= resultStrings[5];
					assignmentType		= resultStrings[6];
					selectSubject			= resultStrings[7];
					String btCodt			= resultStrings[8];
					
						jObject.put("smIDs", smIDs.replaceAll("'", ""));
						jObject.put("bmIDs", bmIDs.replaceAll("'", ""));
						jObject.put("cmIDs", cmIDs.replaceAll("'", ""));
						jObject.put("userIDs", userIDs.replaceAll("'", ""));
						
						if (smIDs.equalsIgnoreCase("0")) {
							smIDs = "'%'";
						}
						if (bmIDs.equalsIgnoreCase("0")) {
							bmIDs = "'%'";
						}		
						if (cmIDs.equalsIgnoreCase("0")) {
							cmIDs = "'%'";
						}
						
						
						//User ID's--2013-7-06
						StringBuffer usrIDs = new StringBuffer();
						if (userIDs.contains("%")) {
							usrIDs.append("'0','Parents','Students'");//using this..i have some reason..-->ud.to_whome  in ('0','Parents','Students','0')							
							userIDs = usrIDs.toString();
						}
						
				System.out.println(smIDs+"****"+bmIDs+"******"+cmIDs+"@@@"+userIDs+"LENTH>"+smIDs.length()+"**"+bmIDs.length());	
					System.out.println("btCodt:::"+btCodt);
				
					try {
						uploadDocDAO = (UploadDocDAO)ServiceFinder.getContext(request).getBean("UploadDocHibernateDao");
						List assignDataList = new ArrayList();
							assignDataList =	uploadDocDAO.getUploadDocForEditNew(smIDs,bmIDs,cmIDs,userIDs,uploadType,fileType,assignmentType,selectSubject,btCodt);
						//	ud_id,	to_whome, 	assign_type ,	file_name,	assg_desc(4),	notify_pa_email ,	upload_date
							System.out.println("SIze of Uploaeded::::"+assignDataList.size());
							String subjectName = "";
							JSONObject jsonObject = null;
							String SubjectJson = null;
							int sizeMain = assignDataList.size() ;
						 if (sizeMain >0) {
								//if above condition satisfies only get Subject master data
								 SubjectMaster subjectMaster = new SubjectMaster();
								 classMasterdao = (ClassMasterDAO)ServiceFinder.getContext(request).getBean("ClassMasterHibernateDao"); 	
								 SubjectJson =  classMasterdao.findAllSubjectMasterJSON();
							}
							System.out.println(subjectName+"--->subjectName");
							
							//Here Major Assumption is query result in school ,after branch ,and then after upload date.......
							//result in upload date desc format
							String schoolIte = "";
							
							JSONArray MainJsonArray		= new JSONArray();		
							
							Set<String> schoolSet = new HashSet();
							
							StringBuffer docPreapare = new StringBuffer();
							
							 if (sizeMain <=0) {
								 docPreapare.append("No records found");
							 }
							
		for (Iterator iterator1 = assignDataList.iterator(); iterator1	.hasNext();) {
			JSONObject MainJsonObject = new JSONObject();		
			try {
				Object row1[] = (Object[]) iterator1.next();
				//school wise
				JSONArray schoolMainJson= new JSONArray();
				System.out.println(schoolIte+"**schoolIte***"+row1[10]);//33 times
	
				
					if (schoolSet.add((String)row1[10])) {
					//? can i use here school..
					schoolIte = (String)row1[10];
					docPreapare.append(schoolIte);//-----------------------------------------------------111111111111111111
					Set<String> branchSet = new HashSet();
					
					System.out.println(schoolSet.size()+"SET schoolSet-->"+schoolSet);
					String branchIte = "";
					for (Iterator iterator2 = assignDataList.iterator(); iterator2	.hasNext();) {
						try {
							Object row2[] = (Object[]) iterator2.next();
							JSONObject schoolJson= new JSONObject();
					//		System.out.println(schoolIte+"*inner schoolIte*"+(String)row2[10]);//33times---64 Total
	
						//	System.out.println(branchSet.size()+"SET branch-->"+branchSet);
						if(schoolIte.equalsIgnoreCase((String)row2[10])){//extra chechking for unnessary reputation stooping
						//branch wise
							if (branchSet.add((String)row2[9])) {
					//			System.out.println("in branchIte");//2 times
								
								branchIte = (String)row2[9];
								docPreapare.append("\n");
								docPreapare.append(branchIte);//----------------------------------------------------222222222222
								docPreapare.append("\n");
								JSONArray brachJsonMain= new JSONArray();
								System.out.println("in branch-*"+branchIte);
								
								//headers here.............................
								docPreapare.append("Upload Type, 	Audience, 	Assignment Type, 	Description,	Subject,	Email,	Date"+"\n");
								for (Iterator iterator = assignDataList.iterator(); iterator	.hasNext();) {//for each brach it iterate list..
									
									JSONObject brachJson= new JSONObject();
									try {
										Object row[] = (Object[]) iterator.next();
									String uploadTypeDB = (String)row[7];
									
									if(branchIte.equalsIgnoreCase((String)row[9])){
									
							//	System.out.println(uploadTypeDB+"uploadTypeDB"+fileType);
									 if (uploadTypeDB.equalsIgnoreCase("Assignment")) {
										 try {
											 //("Upload Type, 	Audience, 	Assignment Type, 	Description,	Subject,	Email	Date
											 docPreapare.append(uploadTypeDB+","+((row[1]+"").equalsIgnoreCase("0")?"Parents-Students":row[1]+"")+","+(String)row[2]+","+(String)row[4]+","+new UploadDocUtil(). getSubjectName(SubjectJson,(String)row[8])+","+(row[5].toString().equalsIgnoreCase("Y")?"Yes":"No")+","+sdf2.format(sdf1.parse((String)row[6])));
										 } catch (Exception e) {
											e.printStackTrace();									
										}
										 
									}else if (uploadTypeDB.equalsIgnoreCase("AcademicMaterial")) {
										 try {
											 //("Upload Type, 	Audience, 	Assignment Type, 	Description,	Subject,	Email	Date
											 docPreapare.append(uploadTypeDB+","+((row[1]+"").equalsIgnoreCase("0")?"Parents-Students":row[1]+"")+","+"-"+","+(String)row[4]+","+new UploadDocUtil(). getSubjectName(SubjectJson,(String)row[8])+","+(row[5].toString().equalsIgnoreCase("Y")?"Yes":"No")+","+sdf2.format(sdf1.parse((String)row[6])));
										 } catch (Exception e) {
											e.printStackTrace();									
										}
										 
									}else{
										 try {
											 //("Upload Type, 	Audience, 	Assignment Type, 	Description,	Subject,	Email	Date
											 docPreapare.append(uploadTypeDB+","+((row[1]+"").equalsIgnoreCase("0")?"Parents-Students":row[1]+"")+","+"-"+","+"-"+","+"-"+","+(row[5].toString().equalsIgnoreCase("Y")?"Yes":"No")+","+sdf2.format(sdf1.parse((String)row[6])));
										 } catch (Exception e) {
											e.printStackTrace();
										}								
									}
									 docPreapare.append("\n");
									 
									}// 
								
									} catch (Exception e) {								e.printStackTrace();											}
									 
	   								  }//inner1st for loop
									// System.out.println("brachJsonMain-->"+brachJsonMain);
							
								   }//branch wise IF condition
						
								  }//	
								} catch (Exception e) {
									e.printStackTrace();
								}
			 
								}//inner1st for loop
					 docPreapare.append("\n\n");
							}//if cond School Wise
			
					
					} catch (BeansException e) {				e.printStackTrace();				} catch (Exception e) {					e.printStackTrace();				}
				}//outer for loop
		//System.out.println("docPreapare"+docPreapare);
		 fileInputStream = new ByteArrayInputStream(docPreapare.toString().getBytes());
		 
         	return "success";
			
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "fail";
	
		
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
