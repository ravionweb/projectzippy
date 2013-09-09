package com.schooltrix.dwr;

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

import com.schooltrix.daos.StaffDetailsDAO;
import com.schooltrix.daos.StudentDetailsDAO;
import com.schooltrix.managers.ServiceFinder;
import com.schooltrix.utils.ParentDetailsAnalysis;
import com.schooltrix.utils.StaffDetailsAnalysis;

public class StaffDWR {

	HttpServletRequest request 			= WebContextFactory.get().getHttpServletRequest();
	HttpServletResponse response 	= WebContextFactory.get().getHttpServletResponse();
    HttpSession session 						= WebContextFactory.get().getSession();
    
    public String getStaffListForEdit(String schoolNames[],String branchNames[],String classNames[],String sectionNames[],
			String fname,String lname,String dob,String email,String mobile,String landline,String addr1,String addr2,	String city,String state,
			String gender,String admissionNumber,String classAdmittedIn[],String fromDate,String toDate,String active,String parentTypeSel,String isDefault,String designation,String isParentCheck){
		
			StaffDetailsAnalysis analysis = new StaffDetailsAnalysis(request);
				String IM_ID 									= (String)session.getAttribute("IM_ID");
				List staffList								=  analysis.getStaffRawList(IM_ID,schoolNames,branchNames,classNames,sectionNames,fname,lname,dob,email,mobile,landline,addr1,addr2,	
																			city,state,gender,admissionNumber,classAdmittedIn,fromDate,toDate,active,1,parentTypeSel,isDefault,designation,isParentCheck,session);
				if (isParentCheck.equalsIgnoreCase("Y")) {
					return getStaffListParentCheck(staffList);
				}else if (isParentCheck.equalsIgnoreCase("N")) {
					
					return getStaffListParentCheckNO(staffList);
				}
				return null;
}

    //do after 
    private String getStaffListParentCheckNO(List staffList) {


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

    }


	private String getStaffListParentCheck(List staffList) {

		JSONArray MainJsonArray		= new JSONArray();		
		
		String schoolIte = "";			
		Set<String> schoolSet = new HashSet();
		
			for (Iterator iterator1 = staffList.iterator(); iterator1	.hasNext();) {//for--555
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
					
								for (Iterator iterator2 = staffList.iterator(); iterator2	.hasNext();) {//for---44444
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
											
												for (Iterator iterator3 = staffList.iterator(); iterator3	.hasNext();) {//for each brach it iterate list..//for------3333
												
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
														
															for (Iterator iterator4 = staffList.iterator(); iterator4	.hasNext();) {//for--2
															try {
																	Object row4[] = (Object[]) iterator4.next();
																	JSONObject classJson= new JSONObject();
															
																	if(classIte.equalsIgnoreCase((String)row4[20]) && branchIte.equalsIgnoreCase((String)row4[22])){//if--2222
																	  
																		if (sectionSet.add((String)row4[18])) {//if--1
																			
																			sectionIte = (String)row4[18];
																			JSONArray sectionJsonMain= new JSONArray();
																			System.out.println("insideeee branch-*"+branchIte);
																	
																			for (Iterator iterator5 = staffList.iterator(); iterator5	.hasNext();) {//for each section it iterate list..//for--1
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
																							sectionJson.put("desig", (String)row5[i]);      i++;
																							sectionJson.put("staffID", (String)row5[i]);      i++;
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
		//System.out.println(staffList.size()+"jArray of uploaded DOC list::");
		System.out.println(staffList.size()+"jArray of uploaded DOC list::");
		
		return MainJsonArray.toString();
    	
	}
    
	public String disableEnableStaffMaster(String Sta_ID,String isActive)  {
		
		StaffDetailsDAO staffDetailsDAO= null;
			try{
				staffDetailsDAO = (StaffDetailsDAO)ServiceFinder.getContext(request).getBean("StaffDetailsHibernateDao");
			//	stuDao = new StudentDetailsDAOImpl();//not work session is null
			//String sd_id,String action,String isActive
			String action = isActive.equalsIgnoreCase("Y")?"enable":"diable";
			String result = staffDetailsDAO.multiTrancationStaffDisable(Sta_ID,action,isActive);
		
			System.out.println("delete student result:"+result);
			return result;
			
			}catch (Exception e) {
				e.printStackTrace();
			}
			return null;
		}
    

}

