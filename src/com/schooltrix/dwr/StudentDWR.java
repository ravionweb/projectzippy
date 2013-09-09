package com.schooltrix.dwr;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.directwebremoting.WebContextFactory;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.BeansException;

import com.schooltrix.daos.BranchMasterDAO;
import com.schooltrix.daos.ParentDetailsDAO;
import com.schooltrix.daos.StaffDetailsDAO;
import com.schooltrix.daos.StudentDetailsDAO;
import com.schooltrix.daos.StudentDetailsDAOImpl;
import com.schooltrix.engine.SchooltrixManager;
import com.schooltrix.hibernate.BranchSearchHistory;
import com.schooltrix.hibernate.ClassMaster;
import com.schooltrix.hibernate.ParentSearchHistory;
import com.schooltrix.hibernate.StaffDetailsHistory;
import com.schooltrix.hibernate.StaffSearchHistory;
import com.schooltrix.hibernate.StudentDetails;
import com.schooltrix.hibernate.StudentSearchHistory;
import com.schooltrix.hibernate.StudentxlErrorTemp;
import com.schooltrix.hibernate.UserMaster;
import com.schooltrix.managers.ServiceFinder;
import com.schooltrix.utils.StudentDetailsAnalysis;
import com.schooltrix.utils.UploadDocUtil;

public class StudentDWR {

	HttpServletResponse response = WebContextFactory.get().getHttpServletResponse();
	HttpServletRequest request = WebContextFactory.get().getHttpServletRequest();
	HttpSession session = WebContextFactory.get().getSession();
	
	public List getErrorMsg() {
		
		 StudentDetailsDAO studentDetailsDao = (StudentDetailsDAO)ServiceFinder.getContext(request).getBean("StudentDetailsDAO");
		 String um_id = (String)session.getAttribute("UM_ID");
		 System.out.println("um_id---"+um_id);
		 List errorLog  = null;
		 List<Object[]> errorList= new ArrayList<Object[]>();
		 try {
			errorLog = studentDetailsDao.getStudentErrorLog(um_id);	
		 
			System.out.println("** size"+errorLog.size());
					for (int i = 0; i < errorLog.size(); i++) {
						String[] oioio = new String[2];
						StudentxlErrorTemp ioio = (StudentxlErrorTemp)errorLog.get(i);
						System.out.println("--error line---"+ioio.getErrorline());
						oioio[0] = ioio.getErrorline();
						oioio[1] = ioio.getReason();
						errorList.add(oioio);
					}
		return errorList;
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return null;
			}
	}
	
	public String getStudentData() {
		
		try {
			StudentDetails studentDetails = null;
			String classID = null;
			String studentID = null;
			SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
			SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
			
			JSONObject jsonObject = new JSONObject();
			
			String UM_ID = (String)session.getAttribute("UM_ID");
			String pdID = (String)session.getAttribute("pdID");
			
			String IM_ID = (String)session.getAttribute("IM_ID");
			String SM_ID = (String)session.getAttribute("SM_ID");
			String BM_ID = (String)session.getAttribute("BM_ID");
			String shortName =(String) session.getAttribute("IM_SN");

			classID 		= (String)session.getAttribute("ClassID");
			studentID 	= (String)session.getAttribute("StuID");//these two value set by LoginAction class....
			
			//can we made this to usefull for parentdashboard as well as student dash board
			//1)case this from parent so we have pdID
			StudentDetailsAnalysis studentDetailsAnalysis = new StudentDetailsAnalysis(request);
			
			//studentDetails = studentDetailsAnalysis.getStudentDetails( pdID, BM_ID);//2013-06-04-not req already we have stuID from LoginAction

			//2)case this from student so we have stuID
			studentDetails = studentDetailsAnalysis.getStudentDetailsFromStudentDash( studentID, BM_ID);
			
			if (studentDetails != null) {
				UserMaster userMaster =studentDetailsAnalysis.getUserMaster(studentDetails.getUmId());
				jsonObject.put("StuUserID", userMaster == null ? "" : userMaster.getUserId());
				jsonObject.put("StuName", studentDetails.getFirstName()+studentDetails.getLastName());
				jsonObject.put("DOB", sdf.format(sdf1.parse(studentDetails.getDob())));
				
				System.out.println(studentDetails.getDob()+"****"+sdf.parse(studentDetails.getDob())+"****"+sdf.format(sdf1.parse(studentDetails.getDob())));
				
				ClassMaster classMaster = studentDetailsAnalysis.getClassName(classID);
				jsonObject.put("className", classMaster == null ? "" : classMaster.getClassName() );
				jsonObject.put("joinDate",  sdf.format(sdf1.parse(studentDetails.getAdmissionDate())));
				//	String path			= request.getSession().getServletContext().getRealPath("/")+"UploadDoc/"+shortName+"/"+assignType;
				//return new FileInputStream(new File(path+"/"+assignFileName));
				System.out.println("Photo Loc-->"+session.getServletContext().getRealPath("/")+"uploaded/"+shortName+"/ST/"+studentDetails.getPhoto());
				jsonObject.put("photoID", "/uploaded/"+shortName+"/ST/"+studentDetails.getPhoto());		
				System.out.println("json-->"+jsonObject);
			}
			return jsonObject.toString();
		} catch (NullPointerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
//20
	public String getStudentListForEdit(String schoolNames[],String branchNames[],String classNames[],String sectionNames[],
																		String fname,String lname,String dob,String email,String mobile,String landline,String addr1,String addr2,	String city,String state,
																			String gender,String admissionNumber,String classAdmittedIn[],String fromDate,String toDate,String active){
		StudentDetailsAnalysis analysis = new StudentDetailsAnalysis(request);
		String IM_ID = (String)session.getAttribute("IM_ID");
		List studentList =  analysis.getStudentRawList(IM_ID,schoolNames,branchNames,classNames,sectionNames,fname,lname,dob,email,mobile,landline,addr1,addr2,	city,state,
														gender,admissionNumber,classAdmittedIn,fromDate,toDate,active,1,session);
		
		
			JSONArray MainJsonArray		= new JSONArray();		
			String schoolIte = "";			
			Set<String> schoolSet = new HashSet();
						
			for (Iterator iterator1 = studentList.iterator(); iterator1	.hasNext();) {//for--555
				JSONObject MainJsonObject = new JSONObject();		
				try {
					Object row1[] = (Object[]) iterator1.next();
					//school wise
					JSONArray schoolMainJson= new JSONArray();
					
					if (schoolSet.add((String)row1[22])) {//if--7777		
						schoolIte = (String)row1[22];
						Set<String> branchSet = new HashSet();
						String branchIte = "";
						//24=14+10
						//	0			1				2					3		4			5			6		7		8		9			10							11						12						13
						//stuID,firstName,lastName,gender,addr1,addr2, city,state,dob, photo,admissionNumber,admissionDate,classAdmittedIN,active, 
						//  14		15				16	17				18		19					20				21			22				23
						//secID ,secName,cmID,className,bmID ,branchName,bShortName,smID,schoolName,	sShortName  
						System.out.println(schoolSet.size()+"SET schoolSet-->"+schoolSet);
						for (Iterator iterator2 = studentList.iterator(); iterator2	.hasNext();) {//for---44444
							try {
								Object row2[] = (Object[]) iterator2.next();
								JSONObject schoolJson= new JSONObject();
						
								if(schoolIte.equalsIgnoreCase((String)row2[22])){//extra chechking for unnessary reputation stooping//ifff------666
								//branch wise
									if (branchSet.add((String)row2[19])) {//if---5555
										
										branchIte = (String)row2[19];
										Set<String> classSet = new HashSet();
										String classIte = "";
										
										JSONArray brachJsonMain= new JSONArray();
										System.out.println("in branch-*"+branchIte);
										
										for (Iterator iterator3 = studentList.iterator(); iterator3	.hasNext();) {//for each brach it iterate list..//for------3333
											
											JSONObject brachJson= new JSONObject();
											try {
												Object row3[] = (Object[]) iterator3.next();	
												
												if(branchIte.equalsIgnoreCase((String)row3[19])){//if	---44
									//--------------------------
													if (classSet.add((String)row3[17])) {//if	---33												
														classIte = (String)row3[17];
														Set<String> sectionSet = new HashSet();
														String sectionIte = "";
														
														JSONArray classJsonMain= new JSONArray();
														
														for (Iterator iterator4 = studentList.iterator(); iterator4	.hasNext();) {//for--2
															try {
																	Object row4[] = (Object[]) iterator4.next();
																	JSONObject classJson= new JSONObject();
															
																if(classIte.equalsIgnoreCase((String)row4[17]) && branchIte.equalsIgnoreCase((String)row4[19])){//if--2222
																  
																	if (sectionSet.add((String)row4[15])) {//if--1
																		
																		sectionIte = (String)row4[15];
																		JSONArray sectionJsonMain= new JSONArray();
																		System.out.println("insideeee branch-*"+branchIte);
																
																		for (Iterator iterator5 = studentList.iterator(); iterator5	.hasNext();) {//for each section it iterate list..//for--1
																			JSONObject sectionJson= new JSONObject();
																			try {
																					Object row5[] = (Object[]) iterator5.next();																			
																					if(sectionIte.equalsIgnoreCase((String)row5[15]) && branchIte.equalsIgnoreCase((String)row5[19])&&classIte.equalsIgnoreCase((String)row5[17])){
																					//if(sectionIte.equalsIgnoreCase((String)row5[15])){
																						//adding student data to object  and after that add to section array...,every section haves it's own array 
																						int i=0;
																						sectionJson.put("stuID", (String)row5[i]);      i++;
																						sectionJson.put("firstName", (String)row5[i]);      i++;
																						sectionJson.put("lastName", (String)row5[i]);      i++;
																						sectionJson.put("gender", (String)row5[i]);      i++;
																						sectionJson.put("addr1", (String)row5[i]);      i++;
																						sectionJson.put("addr2", (String)row5[i]);      i++;
																						sectionJson.put("city", (String)row5[i]);      i++;
																						sectionJson.put("state", (String)row5[i]);      i++;
																						sectionJson.put("dob", (String)row5[i]);      i++;
																						sectionJson.put("photo", (String)row5[i]);      i++;
																						sectionJson.put("admissionNumber", (String)row5[i]);      i++;
																						sectionJson.put("admissionDate", (String)row5[i]);      i++;
																						sectionJson.put("classAdmittedIN", (String)row5[i]);      i++;
																						sectionJson.put("active", (String)row5[i]);      i++;
																						sectionJson.put("secID", (String)row5[i]);      i++;
																						sectionJson.put("secName", (String)row5[i]);      i++;
																						sectionJson.put("cmID", (String)row5[i]);      i++;
																						sectionJson.put("className", (String)row5[i]);      i++;
																						sectionJson.put("bmID", (String)row5[i]);      i++;
																						sectionJson.put("branchName", (String)row5[i]);      i++;
																						sectionJson.put("bShortName", (String)row5[i]);      i++;
																						sectionJson.put("smID", (String)row5[i]);      i++;
																						sectionJson.put("schoolName", (String)row5[i]);      i++;
																						sectionJson.put("sShortName", (String)row5[i]);      i++;
																						sectionJsonMain.put(sectionJson);
																					}// 																
																			 } catch (Exception e) {e.printStackTrace();}	
																		 }//for--1
																		 if (sectionJsonMain.length()>0) {
																			 classJson.put( (String)row4[15], sectionJsonMain);	//15 -className
																			 //System.out.println("classJson-->"+classJson);
																		 }
																	  }//if--1
																		
																		if (classJson.length()>0) {
																			classJsonMain.put(classJson);
																		}
																		
																		//System.out.println("class main array-->"+classJsonMain);//33time
																				
																	}//if--2222
																} catch (Exception e) {
																	e.printStackTrace();
																}
															}//for--2
														
															 if (classJsonMain.length()>0) {
																 brachJson.put( (String)row3[17], classJsonMain);	
																// System.out.println("brachJson-->"+brachJson);
															 }
														
													   }//if	---33
												  }//if	---44			
											}catch (Exception e) {
											e.printStackTrace();
											}	
											
											if (brachJson.length()>0) {
												brachJsonMain.put(brachJson);
												//System.out.println("brachJsonMain-->"+brachJsonMain);
											}
											
										}//for------3333		
										
										 if (brachJsonMain.length()>0) {
											 schoolJson.put( (String)row2[19], brachJsonMain);	
											// System.out.println("schoolJson-->"+schoolJson);
										 }
										
										
									}//if---5555				
									
									if (schoolJson.length()>0) {
										schoolMainJson.put(schoolJson);
										//System.out.println("schoolMainJson-->"+schoolMainJson);
									}									
									
								 }//if---6666						
							 }catch (Exception e) {
								 e.printStackTrace();
							 }
						  }//for------444
						
						 if (schoolMainJson.length()>0) {
							 MainJsonObject.put( (String)row1[22], schoolMainJson);	
						//	 System.out.println("MainJsonObject-->"+MainJsonObject);
						 }				
					}//if--77777	
				   
				}catch (Exception e) {
					   	e.printStackTrace();
				}	
				
				if (MainJsonObject.length()>0) {
					MainJsonArray.put(MainJsonObject);
				//	System.out.println("MainJsonArray-->"+MainJsonArray);
				}		
			}//for-----55555555		
								//------------------------------------
			System.out.println(studentList.size()+"jArray of uploaded DOC list::");
		//System.out.println(studentList.size()+"jArray of uploaded DOC list::"+MainJsonArray);
		
		return MainJsonArray.toString();
		
	}

	public String saveUserSearch(String querySearchHis, String searchDesc , String jsonSearchHis,String userType) {
		
		if (session.getAttribute("queryForStudentSearch") != null && session.getAttribute("queryForStudentSearch") != "null" && userType.equalsIgnoreCase("ST")) {
			querySearchHis = (String)session.getAttribute("queryForStudentSearch");
			jsonSearchHis = (String)session.getAttribute("jsonForStudentSearch");
			session.removeAttribute("queryForStudentSearch");
			session.removeAttribute("jsonForStudentSearch");
		}else	if (session.getAttribute("queryForParentSearch") != null && session.getAttribute("queryForParentSearch") != "null" && userType.equalsIgnoreCase("PA")) {
			querySearchHis = (String)session.getAttribute("queryForParentSearch");
			jsonSearchHis = (String)session.getAttribute("jsonForParentSearch");
			session.removeAttribute("queryForParentSearch");
			session.removeAttribute("jsonForParentSearch");
		}else	if (session.getAttribute("queryForStaffSearch") != null && session.getAttribute("queryForStaffSearch") != "null" && userType.equalsIgnoreCase("TC")) {
			querySearchHis = (String)session.getAttribute("queryForStaffSearch");
			jsonSearchHis = (String)session.getAttribute("jsonForStaffSearch");
			session.removeAttribute("queryForStaffSearch");
			session.removeAttribute("jsonForStaffSearch");
		}
		else{
			return "nosearch";
		}
		
		if(isDescTherecall(searchDesc,userType).equalsIgnoreCase("yes")){
			return "descthere";
		}
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String UM_ID	 = (String)session.getAttribute("UM_ID");
		String IM_ID 		= (String)session.getAttribute("IM_ID");
		String result =null;
		if (userType.equalsIgnoreCase("ST")) {
			StudentDetailsDAO studentDetailsDAO = null;		
			studentDetailsDAO = (StudentDetailsDAO)ServiceFinder.getContext(request).getBean("StudentDetailsHibernateDao");
			StudentSearchHistory searchHistory = new StudentSearchHistory();
			searchHistory.setImId(IM_ID);
			searchHistory.setInsertedDate(java.sql.Timestamp.valueOf(sdf.format(new Date())));
			searchHistory.setQueryString(querySearchHis);
			searchHistory.setUserId(UM_ID);
			searchHistory.setSearchDescription(searchDesc);
			searchHistory.setSearchJson(jsonSearchHis);
			
			
			try {
				result = studentDetailsDAO.saveStudentSearchHistory(searchHistory);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (userType.equalsIgnoreCase("PA")) {
			
			ParentDetailsDAO parentDetailsDAO= null;		
			parentDetailsDAO = (ParentDetailsDAO)ServiceFinder.getContext(request).getBean("ParentDetailsHibernateDao");
			ParentSearchHistory searchHistory = new ParentSearchHistory();
			searchHistory.setImId(IM_ID);
			searchHistory.setInsertedDate(java.sql.Timestamp.valueOf(sdf.format(new Date())));
			searchHistory.setQueryString(querySearchHis);
			searchHistory.setUserId(UM_ID);
			searchHistory.setSearchDescription(searchDesc);
			searchHistory.setSearchJson(jsonSearchHis);
			try {
				result = parentDetailsDAO.saveParentSearchHistory(searchHistory);
			} catch (Exception e) {
				e.printStackTrace();
			}

		} else if (userType.equalsIgnoreCase("TC")) {
			
			StaffDetailsDAO staffDetailsDAO= null;		
			staffDetailsDAO = (StaffDetailsDAO)ServiceFinder.getContext(request).getBean("StaffDetailsHibernateDao");
			StaffSearchHistory searchHistory = new StaffSearchHistory();
			searchHistory.setImId(IM_ID);
			searchHistory.setInsertedDate(java.sql.Timestamp.valueOf(sdf.format(new Date())));
			searchHistory.setQueryString(querySearchHis);
			searchHistory.setUserId(UM_ID);
			searchHistory.setSearchDescription(searchDesc);
			searchHistory.setSearchJson(jsonSearchHis);
			try {
				result = staffDetailsDAO.saveStaffSearchHistory(searchHistory);
			} catch (Exception e) {
				e.printStackTrace();
			}

		}

		System.out.println(userType+"save history result:"+result);
		return result;
		
	}
	
	public String isDescTherecall(String searchDesc,String userType) {
		String result =null;
		if (userType.equalsIgnoreCase("ST")) {
			StudentDetailsDAO studentDetailsDAO	= null;		
			studentDetailsDAO = (StudentDetailsDAO)ServiceFinder.getContext(request).getBean("StudentDetailsHibernateDao");
			String UM_ID	 = (String)session.getAttribute("UM_ID");
			String IM_ID 		= (String)session.getAttribute("IM_ID");
			try {
				result = studentDetailsDAO.isDescThereDB(IM_ID,UM_ID,searchDesc);
			} catch (Exception e) {
				e.printStackTrace();
			}
	 }else if (userType.equalsIgnoreCase("PA")) {
				ParentDetailsDAO parentDetailsDAO= null;		
				parentDetailsDAO = (ParentDetailsDAO)ServiceFinder.getContext(request).getBean("ParentDetailsHibernateDao");
				String UM_ID	 = (String)session.getAttribute("UM_ID");
				String IM_ID 		= (String)session.getAttribute("IM_ID");
				try {
					result = parentDetailsDAO.isDescThereDB(IM_ID,UM_ID,searchDesc);
				} catch (Exception e) {
					e.printStackTrace();
				}
		 }else if (userType.equalsIgnoreCase("TC")) {
				StaffDetailsDAO staffDetailsDAO= null;		
				staffDetailsDAO = (StaffDetailsDAO)ServiceFinder.getContext(request).getBean("StaffDetailsHibernateDao");
				String UM_ID	 = (String)session.getAttribute("UM_ID");
				String IM_ID 		= (String)session.getAttribute("IM_ID");
				try {
					result = staffDetailsDAO.isDescThereDB(IM_ID,UM_ID,searchDesc);
				} catch (Exception e) {
					e.printStackTrace();
				}
		 }
		
		System.out.println("isDescTherecall result:"+result);
		return result;
	}

	public String searchHistoryLoadCall(String userType) {
		
		if (userType.equalsIgnoreCase("ST")) {
			StudentDetailsDAO studentDetailsDAO = null;			
			studentDetailsDAO = (StudentDetailsDAO)ServiceFinder.getContext(request).getBean("StudentDetailsHibernateDao");
			String UM_ID	 = (String)session.getAttribute("UM_ID");
			String IM_ID 		= (String)session.getAttribute("IM_ID");
			List result =new ArrayList();
			try {
				result = studentDetailsDAO.searchHistoryLoadDB(IM_ID,UM_ID);
				JSONArray jsonObjectMain = new JSONArray();
				if (result != null) {
					for (int i = 0; i < result.size(); i++) {
						JSONObject jsonObject = new JSONObject();
						StudentSearchHistory value = (StudentSearchHistory)result.get(i);
						jsonObject.put("id", value.getSshId());
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
		} else 	if (userType.equalsIgnoreCase("PA")) {

			ParentDetailsDAO parentDetailsDAO = null;			
			parentDetailsDAO = (ParentDetailsDAO)ServiceFinder.getContext(request).getBean("ParentDetailsHibernateDao");
			String UM_ID	 = (String)session.getAttribute("UM_ID");
			String IM_ID 		= (String)session.getAttribute("IM_ID");
			List result =new ArrayList();
			try {
				result = parentDetailsDAO.searchHistoryLoadDB(IM_ID,UM_ID);
				JSONArray jsonObjectMain = new JSONArray();
				if (result != null) {
					for (int i = 0; i < result.size(); i++) {
						JSONObject jsonObject = new JSONObject();
						ParentSearchHistory value = (ParentSearchHistory)result.get(i);
						jsonObject.put("id", value.getPshId());
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
		
		} else 	if (userType.equalsIgnoreCase("TC")) {

			StaffDetailsDAO staffDetailsDAO= null;			
			staffDetailsDAO = (StaffDetailsDAO)ServiceFinder.getContext(request).getBean("StaffDetailsHibernateDao");
			String UM_ID	 = (String)session.getAttribute("UM_ID");
			String IM_ID 		= (String)session.getAttribute("IM_ID");
			List result =new ArrayList();
			try {
				result = staffDetailsDAO.searchHistoryLoadDB(IM_ID,UM_ID);
				JSONArray jsonObjectMain = new JSONArray();
				if (result != null) {
					for (int i = 0; i < result.size(); i++) {
						JSONObject jsonObject = new JSONObject();
						StaffSearchHistory value = (StaffSearchHistory)result.get(i);
						jsonObject.put("id", value.getStahId());
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
		
		}
		
		
		return null;
	}
	
	public String  deleteSearchHistoryDoc(String searchHisID,String userType) {
		boolean result = false;
		System.out.println("SearchID**"+searchHisID);
		if (searchHisID != null && !searchHisID.equalsIgnoreCase("-1")) {
			try {
				Long id = Long.parseLong(searchHisID);
			
				
				if (userType.equalsIgnoreCase("ST")) {
					StudentDetailsDAO studentDetailsDAO	= null;		
					studentDetailsDAO = (StudentDetailsDAO)ServiceFinder.getContext(request).getBean("StudentDetailsHibernateDao");
					result = studentDetailsDAO.deleteStudentSerchHistoryDocById(id);
				} else 	if (userType.equalsIgnoreCase("PA")) {
					ParentDetailsDAO parentDetailsDAO = null;
					parentDetailsDAO = (ParentDetailsDAO)ServiceFinder.getContext(request).getBean("ParentDetailsHibernateDao");
					result = parentDetailsDAO.deleteParentSerchHistoryById(id);					
				} else 	if (userType.equalsIgnoreCase("TC")) {
					StaffDetailsDAO staffDetailsDAO = null;
					staffDetailsDAO = (StaffDetailsDAO)ServiceFinder.getContext(request).getBean("StaffDetailsHibernateDao");
					result = staffDetailsDAO.deleteStaffSerchHistoryById(id);					
				}
				
				
				
				
				
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

	/**
	 * the difference is here we have direct query
	 * */
	public String searchHistoryLoadByIDDisplay(String searchHisQuery,String userType) {
		
		if (userType.equalsIgnoreCase("ST")) {
		return	getSearchHistoryResultStudent(searchHisQuery);
		} else 	if (userType.equalsIgnoreCase("PA")) {
			return	getSearchHistoryResultParent(searchHisQuery);
		}else 	if (userType.equalsIgnoreCase("TC")) {
			return	getSearchHistoryResultStaff(searchHisQuery);
		}
		
	
		return null;
	}
	
	private String getSearchHistoryResultParent(String searchHisQuery) {

		try {
			
			ParentDetailsDAO parentDetailsDAO = null;			
			parentDetailsDAO = (ParentDetailsDAO)ServiceFinder.getContext(request).getBean("ParentDetailsHibernateDao");
			List parentList = new ArrayList();
			parentList =	parentDetailsDAO.searchParentHistoryLoadByIDDisplay(searchHisQuery);
			JSONArray MainJsonArray		= new JSONArray();		
			String schoolIte = "";
			Set<String> schoolSet = new HashSet();
						
			for (Iterator iterator1 = parentList.iterator(); iterator1	.hasNext();) {//for--555
				JSONObject MainJsonObject = new JSONObject();		
				try {
				Object row1[] = (Object[]) iterator1.next();
				//school wise
				JSONArray schoolMainJson= new JSONArray();
				
					if (schoolSet.add((String)row1[25])) {//if--7777		
					schoolIte = (String)row1[25];
					Set<String> branchSet = new HashSet();
					String branchIte = "";
						
					//31=17+14
					//	0			1				2					3		4			5			6		7		8		9			10							11						12						13		
					//pdID, stuID,firstName,lastName,gender,addr1,addr2, city,state,dob, photo,admissionNumber,admissionDate,classAdmittedIN,
					//  14				15				16		17			18		19			20		21			22				23				24				25			26				27						28						29			30
					//	active, parentType,isDefault,secID ,secName,cmID,className,bmID ,branchName,bShortName,smID,schoolName,	sShortName  ,sd.First_Name ,sd.Last_Name  ,pd.Email ,pd.Mobile 
					
					System.out.println(schoolSet.size()+"**SET schoolSet-->"+schoolSet);
						for (Iterator iterator2 = parentList.iterator(); iterator2	.hasNext();) {//for---44444
							try {
							Object row2[] = (Object[]) iterator2.next();
							JSONObject schoolJson= new JSONObject();
							
								if(schoolIte.equalsIgnoreCase((String)row2[25])){//extra chechking for unnessary reputation stooping//ifff------666
								//branch wise
									if (branchSet.add((String)row2[22])) {//if---5555
									
									branchIte = (String)row2[22];
									Set<String> classSet = new HashSet();
									String classIte = "";
									
									JSONArray brachJsonMain= new JSONArray();
									System.out.println("in branch-*"+branchIte);
									
										for (Iterator iterator3 = parentList.iterator(); iterator3	.hasNext();) {//for each brach it iterate list..//for------3333
										
										JSONObject brachJson= new JSONObject();
										try {
										Object row3[] = (Object[]) iterator3.next();	
										
											if(branchIte.equalsIgnoreCase((String)row3[22])){//if	---44
											//--------------------------
												if (classSet.add((String)row3[20])) {//if	---33												
												classIte = (String)row3[20];
												Set<String> sectionSet = new HashSet();
												String sectionIte = "";
												
												JSONArray classJsonMain= new JSONArray();
												
													for (Iterator iterator4 = parentList.iterator(); iterator4	.hasNext();) {//for--2
													try {
															Object row4[] = (Object[]) iterator4.next();
															JSONObject classJson= new JSONObject();
													
															if(classIte.equalsIgnoreCase((String)row4[20]) && branchIte.equalsIgnoreCase((String)row4[22])){//if--2222
															  
																if (sectionSet.add((String)row4[18])) {//if--1
																	
																	sectionIte = (String)row4[18];
																	JSONArray sectionJsonMain= new JSONArray();
																	System.out.println("insideeee branch-*"+branchIte);
															
																	for (Iterator iterator5 = parentList.iterator(); iterator5	.hasNext();) {//for each section it iterate list..//for--1
																		JSONObject sectionJson= new JSONObject();
																		try {
																				Object row5[] = (Object[]) iterator5.next();																			
																				if(sectionIte.equalsIgnoreCase((String)row5[18]) && branchIte.equalsIgnoreCase((String)row5[22])&&classIte.equalsIgnoreCase((String)row5[20])){
																				//if(sectionIte.equalsIgnoreCase((String)row5[18])){
																					//adding student data to object  and after that add to section array...,every section haves it's own array 
																					int i=0;
																					sectionJson.put("pdID", (String)row5[i]);      i++;
																					sectionJson.put("stuID", (String)row5[i]);      i++;
																					sectionJson.put("firstName", (String)row5[i]);      i++;
																					sectionJson.put("lastName", (String)row5[i]);      i++;
																					sectionJson.put("gender", (String)row5[i]);      i++;
																					sectionJson.put("addr1", (String)row5[i]);      i++;
																					sectionJson.put("addr2", (String)row5[i]);      i++;
																					sectionJson.put("city", (String)row5[i]);      i++;
																					sectionJson.put("state", (String)row5[i]);      i++;
																					sectionJson.put("dob", (String)row5[i]);      i++;
																					sectionJson.put("photo", (String)row5[i]);      i++;
																					sectionJson.put("admissionNumber", (String)row5[i]);      i++;
																					sectionJson.put("admissionDate", (String)row5[i]);      i++;
																					sectionJson.put("classAdmittedIN", (String)row5[i]);      i++;
																																							
																					sectionJson.put("active", (String)row5[i]);      i++;
																					sectionJson.put("parentType", (String)row5[i]);      i++;
																					sectionJson.put("isDefault", (String)row5[i]);      i++;

																					sectionJson.put("secID", (String)row5[i]);      i++;
																					sectionJson.put("secName", (String)row5[i]);      i++;
																					sectionJson.put("cmID", (String)row5[i]);      i++;
																					sectionJson.put("className", (String)row5[i]);      i++;
																					sectionJson.put("bmID", (String)row5[i]);      i++;
																					sectionJson.put("branchName", (String)row5[i]);      i++;
																					sectionJson.put("bShortName", (String)row5[i]);      i++;
																					sectionJson.put("smID", (String)row5[i]);      i++;
																					sectionJson.put("schoolName", (String)row5[i]);      i++;
																					sectionJson.put("sShortName", (String)row5[i]);      i++;
																					sectionJson.put("sfirstName", (String)row5[i]);      i++;
																					sectionJson.put("slastName", (String)row5[i]);      i++;
																					sectionJson.put("email", (String)row5[i]);      i++;
																					sectionJson.put("mobile", (String)row5[i]);      i++;
																					sectionJsonMain.put(sectionJson);
																				}// 																
																		 } catch (Exception e) {e.printStackTrace();}	
																	 }//for--1
																	 if (sectionJsonMain.length()>0) {
																		 classJson.put( (String)row4[18], sectionJsonMain);	//18 -className
																		 //System.out.println("classJson-->"+classJson);
																	 }
																  }//if--1
																	
																	if (classJson.length()>0) {
																		classJsonMain.put(classJson);
																	}
																	
																	//System.out.println("class main array-->"+classJsonMain);//33time
																			
																}//if--2222
														} catch (Exception e) {
															e.printStackTrace();
														}
													}//for--2
												
												 if (classJsonMain.length()>0) {
													 brachJson.put( (String)row3[20], classJsonMain);	
													// System.out.println("brachJson-->"+brachJson);
												 }
												
												}//if	---33
											}//if	---44			
										}catch (Exception e) {
										e.printStackTrace();
										}	
										
										if (brachJson.length()>0) {
										brachJsonMain.put(brachJson);
										//System.out.println("brachJsonMain-->"+brachJsonMain);
										}
										
										}//for------3333		
									
									if (brachJsonMain.length()>0) {
									schoolJson.put( (String)row2[22], brachJsonMain);	
									// System.out.println("schoolJson-->"+schoolJson);
									}
									
									
									}//if---5555				
								
								if (schoolJson.length()>0) {
								schoolMainJson.put(schoolJson);
								//System.out.println("schoolMainJson-->"+schoolMainJson);
								}									
								
								}//if---6666						
							}catch (Exception e) {
						e.printStackTrace();
						}
						}//for------444
					
					if (schoolMainJson.length()>0) {
					MainJsonObject.put( (String)row1[25], schoolMainJson);	
					//	 System.out.println("MainJsonObject-->"+MainJsonObject);
					}				
					}//if--77777	
				
				}catch (Exception e) {
			e.printStackTrace();
			}	
			
			if (MainJsonObject.length()>0) {
			MainJsonArray.put(MainJsonObject);
			//	System.out.println("MainJsonArray-->"+MainJsonArray);
			}		
			}//for-----55555555		
								//------------------------------------
			return MainJsonArray.toString();
			//
		
		
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		
		
		return null;
	}

	private String getSearchHistoryResultStudent(String searchHisQuery) {
	
	try {
	
		StudentDetailsDAO studentDetailsDAO = null;			
		studentDetailsDAO = (StudentDetailsDAO)ServiceFinder.getContext(request).getBean("StudentDetailsHibernateDao");
		List studentList = new ArrayList();
		studentList =	studentDetailsDAO.searchStudentHistoryLoadByIDDisplay(searchHisQuery);
		JSONArray MainJsonArray		= new JSONArray();		
		String schoolIte = "";
		Set<String> schoolSet = new HashSet();
					
		for (Iterator iterator1 = studentList.iterator(); iterator1	.hasNext();) {//for--555
			JSONObject MainJsonObject = new JSONObject();		
			try {
				Object row1[] = (Object[]) iterator1.next();
				//school wise
				JSONArray schoolMainJson= new JSONArray();
				
				if (schoolSet.add((String)row1[22])) {//if--7777		
					schoolIte = (String)row1[22];
					Set<String> branchSet = new HashSet();
					String branchIte = "";
					//24=14+10
					//	0			1				2					3		4			5			6		7		8		9			10							11						12						13
					//stuID,firstName,lastName,gender,addr1,addr2, city,state,dob, photo,admissionNumber,admissionDate,classAdmittedIN,active, 
					//  14		15				16	17				18		19					20				21			22				23
					//secID ,secName,cmID,className,bmID ,branchName,bShortName,smID,schoolName,	sShortName  
					System.out.println(schoolSet.size()+"SET schoolSet-->"+schoolSet);
					for (Iterator iterator2 = studentList.iterator(); iterator2	.hasNext();) {//for---44444
						try {
							Object row2[] = (Object[]) iterator2.next();
							JSONObject schoolJson= new JSONObject();
					
							if(schoolIte.equalsIgnoreCase((String)row2[22])){//extra chechking for unnessary reputation stooping//ifff------666
							//branch wise
								if (branchSet.add((String)row2[19])) {//if---5555
									
									branchIte = (String)row2[19];
									Set<String> classSet = new HashSet();
									String classIte = "";
									
									JSONArray brachJsonMain= new JSONArray();
									System.out.println("in branch-*"+branchIte);
									
									for (Iterator iterator3 = studentList.iterator(); iterator3	.hasNext();) {//for each brach it iterate list..//for------3333
										
										JSONObject brachJson= new JSONObject();
										try {
											Object row3[] = (Object[]) iterator3.next();	
											
											if(branchIte.equalsIgnoreCase((String)row3[19])){//if	---44
								//--------------------------
												if (classSet.add((String)row3[17])) {//if	---33												
													classIte = (String)row3[17];
													Set<String> sectionSet = new HashSet();
													String sectionIte = "";
													
													JSONArray classJsonMain= new JSONArray();
													
													for (Iterator iterator4 = studentList.iterator(); iterator4	.hasNext();) {//for--2
														try {
																Object row4[] = (Object[]) iterator4.next();
																JSONObject classJson= new JSONObject();
														
															if(classIte.equalsIgnoreCase((String)row4[17]) && branchIte.equalsIgnoreCase((String)row4[19])){//if--2222
															  
																if (sectionSet.add((String)row4[15])) {//if--1
																	
																	sectionIte = (String)row4[15];
																	JSONArray sectionJsonMain= new JSONArray();
																	System.out.println("insideeee branch-*"+branchIte);
															
																	for (Iterator iterator5 = studentList.iterator(); iterator5	.hasNext();) {//for each section it iterate list..//for--1
																		JSONObject sectionJson= new JSONObject();
																		try {
																				Object row5[] = (Object[]) iterator5.next();																			
																				if(sectionIte.equalsIgnoreCase((String)row5[15]) && branchIte.equalsIgnoreCase((String)row5[19])){
																				//if(sectionIte.equalsIgnoreCase((String)row5[15])){
																					//adding student data to object  and after that add to section array...,every section haves it's own array 
																					int i=0;
																					sectionJson.put("stuID", (String)row5[i]);      i++;
																					sectionJson.put("firstName", (String)row5[i]);      i++;
																					sectionJson.put("lastName", (String)row5[i]);      i++;
																					sectionJson.put("gender", (String)row5[i]);      i++;
																					sectionJson.put("addr1", (String)row5[i]);      i++;
																					sectionJson.put("addr2", (String)row5[i]);      i++;
																					sectionJson.put("city", (String)row5[i]);      i++;
																					sectionJson.put("state", (String)row5[i]);      i++;
																					sectionJson.put("dob", (String)row5[i]);      i++;
																					sectionJson.put("photo", (String)row5[i]);      i++;
																					sectionJson.put("admissionNumber", (String)row5[i]);      i++;
																					sectionJson.put("admissionDate", (String)row5[i]);      i++;
																					sectionJson.put("classAdmittedIN", (String)row5[i]);      i++;
																					sectionJson.put("active", (String)row5[i]);      i++;
																					sectionJson.put("secID", (String)row5[i]);      i++;
																					sectionJson.put("secName", (String)row5[i]);      i++;
																					sectionJson.put("cmID", (String)row5[i]);      i++;
																					sectionJson.put("className", (String)row5[i]);      i++;
																					sectionJson.put("bmID", (String)row5[i]);      i++;
																					sectionJson.put("branchName", (String)row5[i]);      i++;
																					sectionJson.put("bShortName", (String)row5[i]);      i++;
																					sectionJson.put("smID", (String)row5[i]);      i++;
																					sectionJson.put("schoolName", (String)row5[i]);      i++;
																					sectionJson.put("sShortName", (String)row5[i]);      i++;
																					sectionJsonMain.put(sectionJson);
																				}// 																
																		 } catch (Exception e) {e.printStackTrace();}	
																	 }//for--1
																	 if (sectionJsonMain.length()>0) {
																		 classJson.put( (String)row4[15], sectionJsonMain);	//15 -className
																		// System.out.println("classJson-->"+classJson);
																	 }
																  }//if--1
																	
																	if (classJson.length()>0) {
																		classJsonMain.put(classJson);
																	}
																	
																//	System.out.println("class main array-->"+classJsonMain);//33time
																			
																}//if--2222
															} catch (Exception e) {
																e.printStackTrace();
															}
														}//for--2
													
														 if (classJsonMain.length()>0) {
															 brachJson.put( (String)row3[17], classJsonMain);	
														//	 System.out.println("brachJson-->"+brachJson);
														 }
													
												   }//if	---33
											  }//if	---44			
										}catch (Exception e) {
										e.printStackTrace();
										}	
										
										if (brachJson.length()>0) {
											brachJsonMain.put(brachJson);
										//	System.out.println("brachJsonMain-->"+brachJsonMain);
										}
										
									}//for------3333		
									
									 if (brachJsonMain.length()>0) {
										 schoolJson.put( (String)row2[19], brachJsonMain);	
									//	 System.out.println("schoolJson-->"+schoolJson);
									 }
									
									
								}//if---5555				
								
								if (schoolJson.length()>0) {
									schoolMainJson.put(schoolJson);
								//	System.out.println("schoolMainJson-->"+schoolMainJson);
								}									
								
							 }//if---6666						
						 }catch (Exception e) {
							 e.printStackTrace();
						 }
					  }//for------444
					
					 if (schoolMainJson.length()>0) {
						 MainJsonObject.put( (String)row1[22], schoolMainJson);	
					//	 System.out.println("MainJsonObject-->"+MainJsonObject);
					 }				
				}//if--77777	
			   
			}catch (Exception e) {
				   	e.printStackTrace();
			}	
			
			if (MainJsonObject.length()>0) {
				MainJsonArray.put(MainJsonObject);
			//	System.out.println("MainJsonArray-->"+MainJsonArray);
			}		
		}//for-----55555555		
							//------------------------------------
		return MainJsonArray.toString();
		//
	
	} catch (Exception e) {
		e.printStackTrace();
	}
		return null;
	}

	
	private String getSearchHistoryResultStaff(String searchHisQuery) {

		try {
			
			StaffDetailsDAO staffDetailsDAO = null;			
			staffDetailsDAO = (StaffDetailsDAO)ServiceFinder.getContext(request).getBean("StaffDetailsHibernateDao");
			List staffList = new ArrayList();
			staffList =	staffDetailsDAO.searchStaffHistoryLoadByIDDisplay(searchHisQuery);

			JSONArray MainJsonArray		= new JSONArray();		
			
			String schoolIte = "";			
			Set<String> schoolSet = new HashSet();
			
				for (Iterator iterator1 = staffList.iterator(); iterator1	.hasNext();) {//for--555
				JSONObject MainJsonObject = new JSONObject();		
					try {
					Object row1[] = (Object[]) iterator1.next();
					//school wise
					JSONArray schoolMainJson= new JSONArray();
					
						if (schoolSet.add((String)row1[2])) {//if--7777		
						schoolIte = (String)row1[2];
						Set<String> branchSet = new HashSet();
						String branchIte = "";
							
						//31=17+14
						//	0			1				2					3		4			5			6		7		8		9			10							11						12						13		
						//pdID, stuID,firstName,lastName,gender,addr1,addr2, city,state,dob, photo,admissionNumber,admissionDate,classAdmittedIN,
						//  14				15				16		17			18		19			20		21			22				23				24				25			26				27						28						29			30
						//	active, parentType,isDefault,secID ,secName,cmID,className,bmID ,branchName,bShortName,smID,schoolName,	sShortName  ,sd.First_Name ,sd.Last_Name  ,pd.Email ,pd.Mobile 

				//					0			1				2					3				4							5				6					7						8						9				10			11			12				13		
				//		 bm.bm_ID ,IM_ID ,Name ,Branch_Name ,Short_Name ,sd.SD_ID , sd.UM_ID ,First_Name ,Last_Name ,Designation ,sd.city ,sd. Mobile ,sd.Email ,sd.Photo
						
						System.out.println(schoolSet.size()+"**SET schoolSet-->"+schoolSet);
						
									for (Iterator iterator2 = staffList.iterator(); iterator2	.hasNext();) {//for---44444
										try {
										Object row2[] = (Object[]) iterator2.next();
										JSONObject schoolJson= new JSONObject();
										
											if(schoolIte.equalsIgnoreCase((String)row2[2])){//extra chechking for unnessary reputation stooping//ifff------666
											//branch wise
												if (branchSet.add((String)row2[3])) {//if---5555
												
												branchIte = (String)row2[3];
												Set<String> classSet = new HashSet();
												String classIte = "";
												
												JSONArray brachJsonMain= new JSONArray();
												System.out.println("in branch-*"+branchIte);
												
												for (Iterator iterator3 = staffList.iterator(); iterator3	.hasNext();) {//for each brach it iterate list..//for------3333
													
													JSONObject brachJson= new JSONObject();
													try {
													Object row3[] = (Object[]) iterator3.next();	
													
														if(branchIte.equalsIgnoreCase((String)row3[3])){//if	---44-----------------------------------------------MY reeeeqq
														//--------------------------
																try {
																				System.out.println("insideeee branch-*"+branchIte);
																								int i=0;
					//					0			1				2					3				4							5				6					7						8						9				10			11			12				13		
					//		 bm.bm_ID ,IM_ID ,Name ,Branch_Name ,Short_Name ,sd.SD_ID , sd.UM_ID ,First_Name ,Last_Name ,Designation ,sd.city ,sd. Mobile ,sd.Email ,sd.Photo
																								brachJson.put("bm_ID", (String)row3[i]); 			i++;
																								brachJson.put("IM_ID", (String)row3[i]); 			i++;
																								brachJson.put("School_Name", (String)row3[i]);			i++;
																								brachJson.put("Branch_Name", (String)row3[i]);			i++;
																								brachJson.put("Short_Name", (String)row3[i]);			i++;
																								brachJson.put("SD_ID", (String)row3[i]);			i++;
																								brachJson.put("UM_ID", (String)row3[i]);			i++;
																								brachJson.put("firstName", (String)row3[i]);      i++;
																								brachJson.put("lastName", (String)row3[i]);      i++;
																								brachJson.put("designation", (String)row3[i]);      i++;
																								
																								brachJson.put("city", (String)row3[i]);			i++;
																								brachJson.put("mobile", (String)row3[i]);      i++;
																								brachJson.put("email", (String)row3[i]);      i++;
																								brachJson.put("photo", (String)row3[i]);      i++;																							
																								brachJsonMain.put(brachJson);
																						
																	} catch (Exception e) {
																		e.printStackTrace();
																	}
															 
														}//if	---44			//..................................................................end reqqqqqqqqqqqqqqqqqqqqqq
													}catch (Exception e) {
													e.printStackTrace();
													}	
													
													}//for------3333		
												
												if (brachJsonMain.length()>0) {
												schoolJson.put( (String)row2[3], brachJsonMain);	
												// System.out.println("schoolJson-->"+schoolJson);
												}
												
												
												}//if---5555				
											
											if (schoolJson.length()>0) {
											schoolMainJson.put(schoolJson);
											//System.out.println("schoolMainJson-->"+schoolMainJson);
											}									
											
											}//if---6666						
										}catch (Exception e) {
									e.printStackTrace();
									}
									}//for------444
						
							if (schoolMainJson.length()>0) {
							MainJsonObject.put( (String)row1[2], schoolMainJson);	
							//	 System.out.println("MainJsonObject-->"+MainJsonObject);
							}				
							}//if--77777	
						
						}catch (Exception e) {
					e.printStackTrace();
					}	
					
					if (MainJsonObject.length()>0) {
					MainJsonArray.put(MainJsonObject);
						System.out.println("MainJsonArray-->"+MainJsonArray);
					}		
				}//for-----55555555		
			//------------------------------------
			//System.out.println(staffList.size()+"jArray of uploaded DOC list::");
			System.out.println(staffList.size()+"jArray of uploaded DOC list::");
			
			return MainJsonArray.toString();

	    
		
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}

	
	public String disableEnableStudentMaster(String Stu_ID,String isActive)  {
		
		StudentDetailsDAO stuDao= null;
			try{
				stuDao = (StudentDetailsDAO)ServiceFinder.getContext(request).getBean("StudentDetailsHibernateDao");
			//	stuDao = new StudentDetailsDAOImpl();//not work session is null
//String sd_id,String action,String isActive
			String action = isActive.equalsIgnoreCase("Y")?"enable":"diable";
			String result = stuDao.multiTrancationStudnetDisable(Stu_ID,action,isActive);//?---wt r req to disable .
		
			System.out.println("delete student result:"+result);
			return result;
			
			}catch (Exception e) {
				e.printStackTrace();
			}
			return null;
		}
	
	public String getStudentListForDelete(String schoolNames,String branchNames,String classNames,String sectionNames){
		
	try {
		return getStudentListForEdit( new String[]{schoolNames}, new String[]{branchNames}, new String[]{classNames},new String[]{sectionNames},
					 null, null, null, null, null, null, null, null,	 null, null,
					 null, null, null, null, null, "Y");
	} catch (Exception e) {e.printStackTrace();}
	return null;
	}
	
}
