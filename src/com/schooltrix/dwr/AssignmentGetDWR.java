package com.schooltrix.dwr;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.directwebremoting.WebContextFactory;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.springframework.beans.BeansException;
import org.springframework.orm.hibernate3.HibernateCallback;

import com.schooltrix.daos.ClassMasterDAO;
import com.schooltrix.daos.UploadDocDAO;
import com.schooltrix.hibernate.DocumentSearchHistory;
import com.schooltrix.hibernate.StudentDetails;
import com.schooltrix.hibernate.SubjectMaster;
import com.schooltrix.managers.ServiceFinder;
import com.schooltrix.utils.StudentDetailsAnalysis;
import com.schooltrix.utils.UploadDocUtil;

public class AssignmentGetDWR {

	HttpServletResponse response = WebContextFactory.get().getHttpServletResponse();
	HttpServletRequest request = WebContextFactory.get().getHttpServletRequest();
	HttpSession session = WebContextFactory.get().getSession();		
	
	public List getAssignmenet(String userType) {
			
			try {
				UploadDocDAO uploadDocDAO = null;
				
				StudentDetails studentDetails = null;
				String classID = null;
				String studentID = null;
				
				String UM_ID = (String)session.getAttribute("UM_ID");
				String pdID = (String)session.getAttribute("pdID");
				
				String IM_ID = (String)session.getAttribute("IM_ID");
				String SM_ID = (String)session.getAttribute("SM_ID");
				String BM_ID = (String)session.getAttribute("BM_ID");
	
				classID 		= (String)session.getAttribute("ClassID");
				studentID 	= (String)session.getAttribute("StuID");//these two value set by NotificationDWr class....		
						
						try {
							uploadDocDAO = (UploadDocDAO)ServiceFinder.getContext(request).getBean("UploadDocHibernateDao");
							List assignDataList = new ArrayList();
							if (userType.equalsIgnoreCase("parent")) {
								assignDataList =	uploadDocDAO.getAssignemets(BM_ID, classID,1);
							} else if (userType.equalsIgnoreCase("student")) {
								assignDataList =	uploadDocDAO.getAssignemets(BM_ID, classID,2);
	
							}
							
							System.out.println("assignDataList.sizz"+assignDataList.size());
							return assignDataList;
							
						} catch (BeansException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						return null;
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;
	}
		
	public List getAcademics(String userType) {
		
		try {
			UploadDocDAO uploadDocDAO = null;
			
			StudentDetails studentDetails = null;
			String classID = null;
			String studentID = null;
			
			String UM_ID = (String)session.getAttribute("UM_ID");
			String pdID = (String)session.getAttribute("pdID");
			
			String IM_ID = (String)session.getAttribute("IM_ID");
			String SM_ID = (String)session.getAttribute("SM_ID");
			String BM_ID = (String)session.getAttribute("BM_ID");
	
			classID 		= (String)session.getAttribute("ClassID");
			studentID 	= (String)session.getAttribute("StuID");//these two value set by NotificationDWr class....		
					
					try {
						uploadDocDAO = (UploadDocDAO)ServiceFinder.getContext(request).getBean("UploadDocHibernateDao");
						List assignDataList = new ArrayList();
						if (userType.equalsIgnoreCase("parent")) {
							assignDataList =	uploadDocDAO.getAcademics(BM_ID, classID,1);
						} else if (userType.equalsIgnoreCase("student")) {
							assignDataList =	uploadDocDAO.getAcademics(BM_ID, classID,2);
						}
						
						System.out.println("assignDataList.sizz"+assignDataList.size());
						return assignDataList;
						
					} catch (BeansException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					return null;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public List getUtilites(String userType) {
		
		List utilitiesDataListMain = new ArrayList();
		try {
			UploadDocDAO uploadDocDAO = null;
			
			StudentDetails studentDetails = null;
			String classID = null;
			String studentID = null;
			
			String UM_ID = (String)session.getAttribute("UM_ID");
			String pdID = (String)session.getAttribute("pdID");
			
			String IM_ID = (String)session.getAttribute("IM_ID");
			String SM_ID = (String)session.getAttribute("SM_ID");
			String BM_ID = (String)session.getAttribute("BM_ID");
	
			classID 		= (String)session.getAttribute("ClassID");
			studentID 	= (String)session.getAttribute("StuID");//these two value set by NotificationDWr class....
			//'Calendar','ExamSchedule','Canteen menu','Time table')
			String[] uploadTypes = new String[]{"ExamSchedule","Calendar","Time table","Canteen menu"};		
					try {
						uploadDocDAO = (UploadDocDAO)ServiceFinder.getContext(request).getBean("UploadDocHibernateDao");
						
						for (int i = 0; i < uploadTypes.length; i++) {
							if (userType.equalsIgnoreCase("parent")) {
								utilitiesDataListMain.add(uploadDocDAO.getUtilities(BM_ID, classID,uploadTypes[i],1));
							} else if (userType.equalsIgnoreCase("student")) {
								utilitiesDataListMain.add(uploadDocDAO.getUtilities(BM_ID, classID,uploadTypes[i],2));
							}
						}
					System.out.println(utilitiesDataListMain.size()+"-->Main sizee");
					//testing
						for (Iterator iterator = utilitiesDataListMain.iterator(); iterator	.hasNext();) {
							ArrayList eee = (ArrayList) iterator.next();
							if (eee.size()>0) {
							//	System.out.println("***"+eee.get(0));
								Object[] dd =(Object[]) eee.get(0);							
								//System.out.println("@@@@"+dd[0]);								
							}
							//System.out.println("outside***");
						}
						
						return utilitiesDataListMain;
						
					} catch (BeansException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					return utilitiesDataListMain;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return utilitiesDataListMain;
	}
	//not usingggggggggggggggggggggggggggggggggggggggggggggggggNOTUSING
	public String getUploadedDocForEdit(String branchNames[],String selectClass[],String selectType[],String uploadType,String fileType,String assignmentType,String selectSubject){
		//branchNames,selectClass,selectType,uploadType,fileType,assignmentType,selectSubject,--7
		
		try {
			UploadDocDAO uploadDocDAO = null;
			ClassMasterDAO classMasterdao = null;
			String UM_ID	 = (String)session.getAttribute("UM_ID");
			String pdID 		= (String)session.getAttribute("pdID");		
			String IM_ID 		= (String)session.getAttribute("IM_ID");
			
			StringBuffer inbmIds	 	= new StringBuffer();
			StringBuffer inclassIds 		= new StringBuffer();
			StringBuffer inUserTypeIds 		= new StringBuffer();
			
			for (int i = 0; i < branchNames.length; i++) {
				 inbmIds.append(branchNames[i]);
					if(i<branchNames.length-1)
						inbmIds.append(",");		
			 }		
			 for (int i = 0; i < selectClass.length; i++) {
				 inclassIds.append(selectClass[i]);
					if(i<selectClass.length-1)
						inclassIds.append(",");	
			}
			 for (int i = 0; i < selectType.length; i++) {//
				 if(i ==0)
					 inUserTypeIds.append("'");
				 
				 inUserTypeIds.append(selectType[i]);
				 
				 if(i<selectType.length-1)
					 inUserTypeIds.append("','");	
	
				 if(i ==(selectType.length-1))
					 inUserTypeIds.append("'");	
			 }
			 JSONArray jArray = new JSONArray();
					try {
						uploadDocDAO = (UploadDocDAO)ServiceFinder.getContext(request).getBean("UploadDocHibernateDao");
						List assignDataList = new ArrayList();
							assignDataList =	uploadDocDAO.getUploadDocForEdit(inbmIds+"", inclassIds+"",inUserTypeIds+"",uploadType,fileType,assignmentType,selectSubject);
						//	ud_id,	to_whome, 	assign_type ,	file_name,	assg_desc(4),	notify_pa_email ,	upload_date
							System.out.println("SIze of Uploaeded::::"+assignDataList.size());
							String subjectName = "";
						
							 if (assignDataList.size() >0 && (uploadType.equalsIgnoreCase("Assignment") || uploadType.equalsIgnoreCase("AcademicMaterial"))) {
								//if above condition satisfies only get Subject master data
								 SubjectMaster subjectMaster = new SubjectMaster();
								 classMasterdao = (ClassMasterDAO)ServiceFinder.getContext(request).getBean("ClassMasterHibernateDao"); 	
								 subjectMaster =  classMasterdao.findBySubjectMasterId(Long.parseLong(selectSubject));
								 subjectName = subjectMaster.getSubName();
							}
							
							for (Iterator iterator = assignDataList.iterator(); iterator	.hasNext();) {
								Object row[] = (Object[]) iterator.next();
								JSONObject jsonObject = new JSONObject();
								
								 if (uploadType.equalsIgnoreCase("Assignment")) {
									 try {
									if (fileType.trim().equalsIgnoreCase(getFileExtn((String)row[3]))) {
										
										jsonObject.put("ud_id", (String)row[0]);//total 9
										 jsonObject.put("uploadType", uploadType);
										 jsonObject.put("to_whome", (String)row[1]);
										 jsonObject.put("assign_type", (String)row[2]);
										 jsonObject.put("fileName", (String)row[3]);
										 jsonObject.put("assg_desc", (String)row[4]);
										 jsonObject.put("subject", subjectName);
										 jsonObject.put("isEmail", row[5].toString().equalsIgnoreCase("Y")?"Yes":"No");
										 jsonObject.put("upload_date", (String)row[6]);
								
									}else if (fileType.trim().equalsIgnoreCase("All")) {
											
										jsonObject.put("ud_id", (String)row[0]);//total 9
										 jsonObject.put("uploadType", uploadType);
										 jsonObject.put("to_whome", (String)row[1]);
										 jsonObject.put("assign_type", (String)row[2]);
										 jsonObject.put("fileName", (String)row[3]);
										 jsonObject.put("assg_desc", (String)row[4]);
										 jsonObject.put("subject", subjectName);
										 jsonObject.put("isEmail", row[5].toString().equalsIgnoreCase("Y")?"Yes":"No");
										 jsonObject.put("upload_date", (String)row[6]);
									
										}
									 } catch (Exception e) {
										e.printStackTrace();									
									}
									 
								}else if (uploadType.equalsIgnoreCase("AcademicMaterial")) {
	
									 try {
									if (fileType.trim().equalsIgnoreCase(getFileExtn((String)row[3]))) {
										
										jsonObject.put("ud_id", (String)row[0]);//total 9
										 jsonObject.put("uploadType", uploadType);
										 jsonObject.put("to_whome", (String)row[1]);
										 jsonObject.put("assign_type", "-");
										 jsonObject.put("fileName", (String)row[3]);
										 jsonObject.put("assg_desc", (String)row[4]);
										 jsonObject.put("subject", subjectName);
										 jsonObject.put("isEmail", row[5].toString().equalsIgnoreCase("Y")?"Yes":"No");
										 jsonObject.put("upload_date", (String)row[6]);
								
									}else if (fileType.trim().equalsIgnoreCase("All")) {
											
										jsonObject.put("ud_id", (String)row[0]);//total 9
										 jsonObject.put("uploadType", uploadType);
										 jsonObject.put("to_whome", (String)row[1]);
										 jsonObject.put("assign_type", "-");
										 jsonObject.put("fileName", (String)row[3]);
										 jsonObject.put("assg_desc", (String)row[4]);
										 jsonObject.put("subject", subjectName);
										 jsonObject.put("isEmail", row[5].toString().equalsIgnoreCase("Y")?"Yes":"No");
										 jsonObject.put("upload_date", (String)row[6]);
									
										}
									
									 } catch (Exception e) {
										e.printStackTrace();									
									}
									 
								}else{
	
									 try {
									if (fileType.trim().equalsIgnoreCase(getFileExtn((String)row[3]))) {
										
										jsonObject.put("ud_id", (String)row[0]);//total 9
										 jsonObject.put("uploadType", uploadType);
										 jsonObject.put("to_whome", (String)row[1]);
										 jsonObject.put("assign_type","-");
										 jsonObject.put("fileName", (String)row[3]);
										 jsonObject.put("assg_desc", "-");
										 jsonObject.put("subject", "-");
										 jsonObject.put("isEmail", row[5].toString().equalsIgnoreCase("Y")?"Yes":"No");
										 jsonObject.put("upload_date", (String)row[6]);
								
									}else if (fileType.trim().equalsIgnoreCase("All")) {
											
										jsonObject.put("ud_id", (String)row[0]);//total 9
										 jsonObject.put("uploadType", uploadType);
										 jsonObject.put("to_whome", (String)row[1]);
										 jsonObject.put("assign_type","-");
										 jsonObject.put("fileName", (String)row[3]);
										 jsonObject.put("assg_desc", "-");
										 jsonObject.put("subject", "-");
										 jsonObject.put("isEmail", row[5].toString().equalsIgnoreCase("Y")?"Yes":"No");
										 jsonObject.put("upload_date", (String)row[6]);
										}
									
									 } catch (Exception e) {
										e.printStackTrace();
										System.out.println("jArray-Exception"+jArray);										
									}								
								}
								 //System.out.println("jsonObject"+jsonObject+"jsonObject.length()::"+jsonObject.length());
								 
								 if (jsonObject.length()>0) {
									 jArray.put(jsonObject);	 
								}
								 
						}//for loop
							System.out.println(assignDataList.size()+"jArray of uploaded DOC list::"+jArray);
						return jArray.toString();
					} catch (BeansException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					return null;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public String getUploadedDocForEditNew(String schoolNames[],String branchNames[],String selectClass[],String selectType[],
																				String uploadType,String fileType,String assignmentType,String selectSubject,String fromDate,String toDate){
		try {
			//Start-----------2013=07-06 ---common code moved to Another class ..purpose is for download...
			
			UploadDocUtil docUtil = new UploadDocUtil();
			String[] resultStrings = docUtil.prepareParamsForUploadDoc(schoolNames,branchNames,selectClass,selectType,uploadType,fileType,assignmentType,selectSubject,fromDate,toDate);
			
			if (resultStrings == null) {
				return null;
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
			
				String UM_ID	 = (String)session.getAttribute("UM_ID");
				String pdID 		= (String)session.getAttribute("pdID");		
				String IM_ID 		= (String)session.getAttribute("IM_ID");
					
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
						jObject.put("userIDs", userIDs.replaceAll("'", ""));//if none selcted %
						
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
						
						
				//System.out.println(smIDs+"****"+bmIDs+"******"+cmIDs+"@@@"+userIDs+"LENTH>"+smIDs.length()+"**"+bmIDs.length());	
				//0,3,4,5,6,7,8****%******%@@@%
				/*if length>1 means multi selection So USE 	sql		in ()
				 * if length=1 means single(0 or None or any single value(this also possible) ) selection So USE 	sql		like ()
				 * */
	
				//2nd Part---all valuse lenth is 1 only .....here am using like ...bcz 0 menas %
				//System.out.println(uploadType+"**"+fileType+"**asign*"+assignmentType+"****"+selectSubject+"***END");
				//System.out.println(uploadType+"**"+fileType+"**aisgn*"+assignmentType+"****"+selectSubject+"#After***END");
					//Constructing query ...purpose of this is search History saving...
					try {
						constructQueryForHistory(smIDs,bmIDs,cmIDs,userIDs,uploadType,fileType,assignmentType,selectSubject,btCodt);
						session.setAttribute("jsonForSearch", jObject.toString());
					} catch (Exception e) {
					}
					//END Search History
					
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
							
		for (Iterator iterator1 = assignDataList.iterator(); iterator1	.hasNext();) {
			JSONObject MainJsonObject = new JSONObject();		
			try {
				Object row1[] = (Object[]) iterator1.next();
				//school wise
				JSONArray schoolMainJson= new JSONArray();
				//System.out.println(schoolIte+"**schoolIte***"+row1[10]);//33 times
				
				/**
				 * without using size conditon ..
				 * can i use set....for not ietaring duplicate rows...Success---
				 * i.e add value to set ...use if contion ...if already presint in that set or not?
				 * 
				 * (backup taken befor this in Desktop)
				 * */
				
				//set1.add((String)row1[10]);
				
	//			if (!schoolIte.equalsIgnoreCase((String)row1[10])) {										
					if (schoolSet.add((String)row1[10])) {					
					
					schoolIte = (String)row1[10];
				//	System.out.println("in scgool-*"+schoolIte);//expecting 2 times
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
					//		System.out.println(branchIte+"*branchIte**"+(String)row2[9]);//33times--but school wise--64
							
	//						if (!branchIte.equalsIgnoreCase((String)row2[9])) {
							if (branchSet.add((String)row2[9])) {	
					//			System.out.println("in branchIte");//2 times
								
								branchIte = (String)row2[9];
								JSONArray brachJsonMain= new JSONArray();
								System.out.println("in branch-*"+branchIte);
								
								for (Iterator iterator = assignDataList.iterator(); iterator	.hasNext();) {//for each brach it iterate list..
									
									JSONObject brachJson= new JSONObject();
									try {
										Object row[] = (Object[]) iterator.next();
									String uploadTypeDB = (String)row[7];
									
									if(branchIte.equalsIgnoreCase((String)row[9])){
									
							//	System.out.println(uploadTypeDB+"uploadTypeDB"+fileType);
									 if (uploadTypeDB.equalsIgnoreCase("Assignment")) {
										 try {
										if (fileTypeOrig.trim().equalsIgnoreCase(getFileExtn((String)row[3]))) {
											
											brachJson.put("ud_id", (String)row[0]);// 
											 brachJson.put("uploadType", uploadTypeDB);
											 brachJson.put("to_whome", (String)row[1]);
											 brachJson.put("assign_type", (String)row[2]);
											 brachJson.put("fileName", (String)row[3]);
											 brachJson.put("assg_desc", (String)row[4]);
											 brachJson.put("subject", new UploadDocUtil().getSubjectName(SubjectJson,(String)row[8]));
											 brachJson.put("isEmail", row[5].toString().equalsIgnoreCase("Y")?"Yes":"No");
											 brachJson.put("upload_date", (String)row[6]);
									
										}else if (fileTypeOrig.trim().equalsIgnoreCase("All")) {
												
											brachJson.put("ud_id", (String)row[0]);//total 9
											 brachJson.put("uploadType", uploadTypeDB);
											 brachJson.put("to_whome", (String)row[1]);
											 brachJson.put("assign_type", (String)row[2]);
											 brachJson.put("fileName", (String)row[3]);
											 brachJson.put("assg_desc", (String)row[4]);
											 brachJson.put("subject", new UploadDocUtil().getSubjectName(SubjectJson,(String)row[8]));
											 brachJson.put("isEmail", row[5].toString().equalsIgnoreCase("Y")?"Yes":"No");
											 brachJson.put("upload_date", (String)row[6]);
										
											}
										 } catch (Exception e) {
											e.printStackTrace();									
										}
										 
									}else if (uploadTypeDB.equalsIgnoreCase("AcademicMaterial")) {
	
										 try {
										if (fileTypeOrig.trim().equalsIgnoreCase(getFileExtn((String)row[3]))) {
											
											brachJson.put("ud_id", (String)row[0]);//total 9
											 brachJson.put("uploadType", uploadTypeDB);
											 brachJson.put("to_whome", (String)row[1]);
											 brachJson.put("assign_type", "-");
											 brachJson.put("fileName", (String)row[3]);
											 brachJson.put("assg_desc", (String)row[4]);
											 brachJson.put("subject", new UploadDocUtil().getSubjectName(SubjectJson,(String)row[8]));
											 brachJson.put("isEmail", row[5].toString().equalsIgnoreCase("Y")?"Yes":"No");
											 brachJson.put("upload_date", (String)row[6]);
									
										}else if (fileTypeOrig.trim().equalsIgnoreCase("All")) {
												
											brachJson.put("ud_id", (String)row[0]);//total 9
											 brachJson.put("uploadType", uploadTypeDB);
											 brachJson.put("to_whome", (String)row[1]);
											 brachJson.put("assign_type", "-");
											 brachJson.put("fileName", (String)row[3]);
											 brachJson.put("assg_desc", (String)row[4]);
											 brachJson.put("subject",new UploadDocUtil(). getSubjectName(SubjectJson,(String)row[8]));
											 brachJson.put("isEmail", row[5].toString().equalsIgnoreCase("Y")?"Yes":"No");
											 brachJson.put("upload_date", (String)row[6]);
										
											}
										
										 } catch (Exception e) {
											e.printStackTrace();									
										}
										 
									}else{
	
										 try {
										if (fileTypeOrig.trim().equalsIgnoreCase(getFileExtn((String)row[3]))) {
											
											brachJson.put("ud_id", (String)row[0]);//total 9
											 brachJson.put("uploadType", uploadTypeDB);
											 brachJson.put("to_whome", (String)row[1]);
											 brachJson.put("assign_type","-");
											 brachJson.put("fileName", (String)row[3]);
											 brachJson.put("assg_desc", "-");
											 brachJson.put("subject", "-");
											 brachJson.put("isEmail", row[5].toString().equalsIgnoreCase("Y")?"Yes":"No");
											 brachJson.put("upload_date", (String)row[6]);
									
										}else if (fileTypeOrig.trim().equalsIgnoreCase("All")) {
												
											brachJson.put("ud_id", (String)row[0]);//total 9
											 brachJson.put("uploadType", uploadTypeDB);
											 brachJson.put("to_whome", (String)row[1]);
											 brachJson.put("assign_type","-");
											 brachJson.put("fileName", (String)row[3]);
											 brachJson.put("assg_desc", "-");
											 brachJson.put("subject", "-");
											 brachJson.put("isEmail", row[5].toString().equalsIgnoreCase("Y")?"Yes":"No");
											 brachJson.put("upload_date", (String)row[6]);
											}
										
										 } catch (Exception e) {
											e.printStackTrace();
										}								
									}
									 
									}// 
									 //System.out.println("brachJson"+brachJson+"brachJson.length()::"+brachJson.length());
								//	 System.out.println("brachJson-->"+brachJson);
											 if (brachJson.length()>0) {
												 brachJsonMain.put(brachJson);	 
											}
										
										} catch (Exception e) {								e.printStackTrace();											}
									 
	   								  }//inner1st for loop
									// System.out.println("brachJsonMain-->"+brachJsonMain);
									if (brachJsonMain.length()>0) {
										 schoolJson.put( (String)row2[9], brachJsonMain);	 
									}
								
								   }//branch wise IF condition
									 
									//System.out.println("brach main array-->"+schoolJson);//33time
									if (schoolJson.length()>0) {
										schoolMainJson.put(schoolJson);	 //schoolJson ->brach main array
									}
									
								  }//	
								} catch (Exception e) {
									e.printStackTrace();
								}
			 
								}//inner1st for loop
					
							}//if cond School Wise
				//System.out.println(schoolMainJson.length()+"schoolMainJson-->"+schoolMainJson);
						if (schoolMainJson.length()>0) {
							MainJsonObject.put((String)row1[10], schoolMainJson);	 
						}
						if (MainJsonObject.length()>0) {
							MainJsonArray.put(MainJsonObject);
							
						}
					
					} catch (BeansException e) {				e.printStackTrace();				} catch (Exception e) {					e.printStackTrace();				}
				}//outer for loop
		
			//System.out.println(assignDataList.size()+"jArray of uploaded DOC list::"+MainJsonArray);
					
			return MainJsonArray.toString();
			
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	


	private String getFileExtn(String filename) {
	
		try {
			int i = filename.lastIndexOf('.');
			if (i > 0) {
				return filename.substring(i+1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "txt";
	}
	
	
	public String deleteUploadedDoc(String UD_ID) {
		
		UploadDocDAO uploadDocDAO = null;
		
		uploadDocDAO = (UploadDocDAO)ServiceFinder.getContext(request).getBean("UploadDocHibernateDao");
		String result = uploadDocDAO.multiTrancationUploadDelete(UD_ID,"delete");
		System.out.println("delete result:"+result);
		return result;
	}


	private void constructQueryForHistory( String smIDs,  String bmIDs,  String cmIDs, String userIDs,  String uploadType,  String fileType,  
			String assignmentType,  String selectSubject, String dateQString) {
			try {
				String smIDOpF = null;
				String bmIDOpF = null;
				String cmIDOpF = null;
				
				//System.out.println(smIDs.length()+"666"+bmIDs.length()+"777"+cmIDs.length());
				if (smIDs.replace("'", "").length()>1) {
					smIDOpF = "in";			
				}else {
					smIDOpF = "like";			
				}
				if (bmIDs.replace("'", "").length()>1) {
					bmIDOpF = "in";			
				}else {
					bmIDOpF = "like";			
				}
				if (cmIDs.replace("'", "").length()>1) {
					cmIDOpF = "in";			
				}else {
					cmIDOpF = "like";			
				}
				String smIDOp = smIDOpF;
				String bmIDOp = bmIDOpF;
				String cmIDOp = cmIDOpF;
				
				String qu =	"select ud.ud_id as ud_id,ud.to_whome as to_whome, ud.assign_type as assign_type  ,ud.file_name as file_name,ud.assg_desc as assg_desc,ud.notify_pa_email as notify_pa_email ,ud.upload_date as upload_date," +
				" ud.upload_type as upload_type,  ud.subject as subject, bm.Branch_Name as Branch_Name,sm.Name as Name  from upload_document  ud join  upload_document_class_branch_map udbc  on ud.ud_id=udbc.ud_id  join branch_master bm  on udbc.bm_id =  bm.bm_id join school_master sm on sm.sm_id= bm.sm_id " +
				"where ud.upload_type  like ('"+uploadType+"') and  ud.file_name	 like ('"+fileType+"')	and ud.assign_type like ('"+assignmentType+"')	and ud.subject like ('"+selectSubject+"')  and " +
				"ud.to_whome  in ("+userIDs+",'0') and  bm.sm_id "+smIDOp+" ("+smIDs+")	 and udbc.bm_id "+bmIDOp+" ("+bmIDs+") and udbc.cm_id "+cmIDOp+"("+cmIDs+")" +
				" and upload_date between "+dateQString+" group by ud.ud_id,bm.bm_id order by ud.upload_date desc";
				session.setAttribute("queryForSearch", qu);
				
			} catch (Exception e) {
				session.setAttribute("queryForSearch", "");
				session.setAttribute("jsonForSearch", "");
				e.printStackTrace();
		}	
	}
	
	public String saveSearch(String querySearchHis, String searchDesc , String jsonSearchHis) {
		
		if (session.getAttribute("queryForSearch") != null && session.getAttribute("queryForSearch") != "null") {
			querySearchHis = (String)session.getAttribute("queryForSearch");
			jsonSearchHis = (String)session.getAttribute("jsonForSearch");
			session.removeAttribute("queryForSearch");
			session.removeAttribute("jsonForSearch");
		}else{
			return "nosearch";
		}
		
		if(isDescTherecall(searchDesc).equalsIgnoreCase("yes")){
			return "descthere";
		}
		
		
		System.out.println("jsonSearchHis*****"+jsonSearchHis);
		UploadDocDAO uploadDocDAO = null;
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		uploadDocDAO = (UploadDocDAO)ServiceFinder.getContext(request).getBean("UploadDocHibernateDao");
		String UM_ID	 = (String)session.getAttribute("UM_ID");
		String IM_ID 		= (String)session.getAttribute("IM_ID");
		
		DocumentSearchHistory searchHistory = new DocumentSearchHistory();
		searchHistory.setImId(IM_ID);
		searchHistory.setInsertedDate(java.sql.Timestamp.valueOf(sdf.format(new Date())));
		searchHistory.setQueryString(querySearchHis);
		searchHistory.setUserId(UM_ID);
		searchHistory.setSearchDescription(searchDesc);
		searchHistory.setSearchJson(jsonSearchHis);
		
		String result =null;
		try {
			result = uploadDocDAO.saveSearchHistory(searchHistory);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("save history result:"+result);
		return result;
		
	}
	
	public String isDescTherecall(String searchDesc) {
		
		UploadDocDAO uploadDocDAO = null;		
		uploadDocDAO = (UploadDocDAO)ServiceFinder.getContext(request).getBean("UploadDocHibernateDao");
		String UM_ID	 = (String)session.getAttribute("UM_ID");
		String IM_ID 		= (String)session.getAttribute("IM_ID");
		String result =null;
		try {
			result = uploadDocDAO.isDescThereDB(IM_ID,UM_ID,searchDesc);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("isDescTherecall result:"+result);
		return result;
	}
	
	public String searchHistoryLoadCall() {
		
		UploadDocDAO uploadDocDAO = null;		
		uploadDocDAO = (UploadDocDAO)ServiceFinder.getContext(request).getBean("UploadDocHibernateDao");
		String UM_ID	 = (String)session.getAttribute("UM_ID");
		String IM_ID 		= (String)session.getAttribute("IM_ID");
		List result =new ArrayList();
		try {
			result = uploadDocDAO.searchHistoryLoadDB(IM_ID,UM_ID);
			JSONArray jsonObjectMain = new JSONArray();
			if (result != null) {
				for (int i = 0; i < result.size(); i++) {
					JSONObject jsonObject = new JSONObject();
					DocumentSearchHistory value = (DocumentSearchHistory)result.get(i);
					jsonObject.put("id", value.getDshId());
					jsonObject.put("queryString", value.getQueryString());
					jsonObject.put("jsonString", value.getSearchJson());
					jsonObject.put("descS", value.getSearchDescription());
					jsonObjectMain.put(jsonObject);
				}
				//System.out.println("innnnnn->"+jsonObjectMain);
				return jsonObjectMain.toString();
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * Same like getUploadedDocForEditNew()
	 * the difference is here we have direct query
	 * */
	public String searchHistoryLoadByIDDisplay(String searchHisOnloadJson) {
		
		UploadDocDAO	uploadDocDAO = null;
		ClassMasterDAO classMasterdao = null;
			
			try {
				uploadDocDAO = (UploadDocDAO)ServiceFinder.getContext(request).getBean("UploadDocHibernateDao");
				List assignDataList = new ArrayList();
				assignDataList =	uploadDocDAO.searchHistoryLoadByIDDisplay(searchHisOnloadJson);
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
					
for (Iterator iterator1 = assignDataList.iterator(); iterator1	.hasNext();) {
	JSONObject MainJsonObject = new JSONObject();		
	try {
		Object row1[] = (Object[]) iterator1.next();
		//school wise
		JSONArray schoolMainJson= new JSONArray();
		System.out.println(schoolIte+"**schoolIte***"+row1[10]);//33 times
		
		
		//set1.add((String)row1[10]);
		
//			if (!schoolIte.equalsIgnoreCase((String)row1[10])) {										
			if (schoolSet.add((String)row1[10])) {					
			
			schoolIte = (String)row1[10];
		//	System.out.println("in scgool-*"+schoolIte);//expecting 2 times
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
			//		System.out.println(branchIte+"*branchIte**"+(String)row2[9]);//33times--but school wise--64
					
//						if (!branchIte.equalsIgnoreCase((String)row2[9])) {
					if (branchSet.add((String)row2[9])) {	
			//			System.out.println("in branchIte");//2 times
						
						branchIte = (String)row2[9];
						JSONArray brachJsonMain= new JSONArray();
						System.out.println("in branch-*"+branchIte);
						
						for (Iterator iterator = assignDataList.iterator(); iterator	.hasNext();) {//for each brach it iterate list..
							
							JSONObject brachJson= new JSONObject();
							try {
								Object row[] = (Object[]) iterator.next();
							String uploadTypeDB = (String)row[7];
							
							if(branchIte.equalsIgnoreCase((String)row[9])){
							
					//	Changed here ..bcz i assume that file type is alredy in query 
							 if (uploadTypeDB.equalsIgnoreCase("Assignment")) {
								 try {
									brachJson.put("ud_id", (String)row[0]);//total 9
									 brachJson.put("uploadType", uploadTypeDB);
									 brachJson.put("to_whome", (String)row[1]);
									 brachJson.put("assign_type", (String)row[2]);
									 brachJson.put("fileName", (String)row[3]);
									 brachJson.put("assg_desc", (String)row[4]);
									 brachJson.put("subject", new UploadDocUtil().getSubjectName(SubjectJson,(String)row[8]));
									 brachJson.put("isEmail", row[5].toString().equalsIgnoreCase("Y")?"Yes":"No");
									 brachJson.put("upload_date", (String)row[6]);
								 } catch (Exception e) {
									e.printStackTrace();									
								}
							}else if (uploadTypeDB.equalsIgnoreCase("AcademicMaterial")) {
								 try {
									brachJson.put("ud_id", (String)row[0]);//total 9
									 brachJson.put("uploadType", uploadTypeDB);
									 brachJson.put("to_whome", (String)row[1]);
									 brachJson.put("assign_type", "-");
									 brachJson.put("fileName", (String)row[3]);
									 brachJson.put("assg_desc", (String)row[4]);
									 brachJson.put("subject", new UploadDocUtil().getSubjectName(SubjectJson,(String)row[8]));
									 brachJson.put("isEmail", row[5].toString().equalsIgnoreCase("Y")?"Yes":"No");
									 brachJson.put("upload_date", (String)row[6]);
								 } catch (Exception e) {
									e.printStackTrace();									
								}
							}else{
								 try {
									brachJson.put("ud_id", (String)row[0]);//total 9
									 brachJson.put("uploadType", uploadTypeDB);
									 brachJson.put("to_whome", (String)row[1]);
									 brachJson.put("assign_type","-");
									 brachJson.put("fileName", (String)row[3]);
									 brachJson.put("assg_desc", "-");
									 brachJson.put("subject", "-");
									 brachJson.put("isEmail", row[5].toString().equalsIgnoreCase("Y")?"Yes":"No");
									 brachJson.put("upload_date", (String)row[6]);
								 } catch (Exception e) {
									e.printStackTrace();
								}								
							}
							 
							}// 
							 //System.out.println("brachJson"+brachJson+"brachJson.length()::"+brachJson.length());
						//	 System.out.println("brachJson-->"+brachJson);
									 if (brachJson.length()>0) {
										 brachJsonMain.put(brachJson);	 
									}
								
								} catch (Exception e) {								e.printStackTrace();											}
							 
								  }//inner1st for loop
							// System.out.println("brachJsonMain-->"+brachJsonMain);
							if (brachJsonMain.length()>0) {
								 schoolJson.put( (String)row2[9], brachJsonMain);	 
							}
						
						   }//branch wise IF condition
							 
							//System.out.println("brach main array-->"+schoolJson);//33time
							if (schoolJson.length()>0) {
								schoolMainJson.put(schoolJson);	 //schoolJson ->brach main array
							}
							
						  }//	
						} catch (Exception e) {
							e.printStackTrace();
						}
	 
						}//inner1st for loop
			
					}//if cond School Wise
		//System.out.println(schoolMainJson.length()+"schoolMainJson-->"+schoolMainJson);
				if (schoolMainJson.length()>0) {
					MainJsonObject.put((String)row1[10], schoolMainJson);	 
				}
				if (MainJsonObject.length()>0) {
					MainJsonArray.put(MainJsonObject);
					
				}
			
			} catch (BeansException e) {				e.printStackTrace();				} catch (Exception e) {					e.printStackTrace();				}
		}//outer for loop

	System.out.println(assignDataList.size()+"jArray of uploaded DOC list::"+MainJsonArray);
			
		return MainJsonArray.toString();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}
	
	public String  deleteSearchHistoryDoc(String searchHisID) {
		UploadDocDAO docDAO = null;
		boolean result = false;
		System.out.println("SearchID**"+searchHisID);
		if (searchHisID != null && !searchHisID.equalsIgnoreCase("-1")) {
			try {
				Long id = Long.parseLong(searchHisID);
				docDAO = (UploadDocDAO)ServiceFinder.getContext(request).getBean("UploadDocHibernateDao");
				result = docDAO.deleteSerchHistoryDocById(id);
				System.out.println("result**"+result);
				if (result == true) {
					return "success";
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}		
		return null;
	}
	
}
