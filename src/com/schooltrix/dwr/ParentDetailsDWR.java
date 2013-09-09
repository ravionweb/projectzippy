package com.schooltrix.dwr;

import java.util.ArrayList;
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

import com.schooltrix.daos.NotificationMasterDAO;
import com.schooltrix.daos.ParentDetailsDAO;
import com.schooltrix.hibernate.ParentDetails;
import com.schooltrix.managers.ServiceFinder;
import com.schooltrix.utils.ParentDetailsAnalysis;
import com.schooltrix.utils.StudentDetailsAnalysis;

public class ParentDetailsDWR {
	HttpServletResponse response = WebContextFactory.get().getHttpServletResponse();
	HttpServletRequest request = WebContextFactory.get().getHttpServletRequest();
	HttpSession session = WebContextFactory.get().getSession();
	
	public List getParentDetails() {
		
		try {
			ParentDetailsDAO parentDetailsDAO = null;
			
			
			String UM_ID = (String)session.getAttribute("UM_ID");
			String pdID = (String)session.getAttribute("pdID");
			
			String IM_ID = (String)session.getAttribute("IM_ID");
			String SM_ID = (String)session.getAttribute("SM_ID");
			String BM_ID = (String)session.getAttribute("BM_ID");
			
					try {
						parentDetailsDAO = (ParentDetailsDAO)ServiceFinder.getContext(request).getBean("ParentDetailsHibernateDao");
						List parentDataList = new ArrayList();
						List parentDataListSplit = new ArrayList();
						parentDataList =	parentDetailsDAO.findByPropertyListLong("umId", Long.parseLong(UM_ID));

						System.out.println("parentDataList.sizz"+parentDataList.size());
						
						for (Iterator iterator = parentDataList.iterator(); iterator.hasNext();) {
							ParentDetails pd = new ParentDetails();
							pd = (ParentDetails) iterator.next();
							List<String> yy = new ArrayList<String>();
							yy.add(pd.getMobile());
							yy.add(pd.getEmail());
							yy.add(pd.getIsDefault());
							yy.add(pd.getPtmId());
							yy.add(pd.getPdId()+"");
							
							System.out.println(pd.getFirstName()+"****"+pd.getPtmId()+"**"+pd.getIsDefault()+"**"+pd.getPtmId()+"**"+pd.getPdId());
							parentDataListSplit.add(yy);
						}
						
						return parentDataListSplit;
						
					} catch (BeansException e) {
						e.printStackTrace();
					} catch (Exception e) {
						e.printStackTrace();
					}
				return null;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	//getParentDetails(fMobile,mMobile,fMail,mMail,checkReq, fpdid, mpdid,
	public  boolean saveParentDetails(String fMobile,String mMobile,String fMail,String mMail,String checkReq,String fpdid,String mpdid) {
		String UM_ID = (String)session.getAttribute("UM_ID");
		String pdID = (String)session.getAttribute("pdID");
		
		String IM_ID = (String)session.getAttribute("IM_ID");
		String SM_ID = (String)session.getAttribute("SM_ID");
		String BM_ID = (String)session.getAttribute("BM_ID");
		ParentDetailsDAO parentDetailsDAO = null;
		
		String radioDefalut1= checkReq.equalsIgnoreCase("1")?"Y":"N";
		String radioDefalut2= checkReq.equalsIgnoreCase("2")?"Y":"N";
		boolean isModified = false;
		
		try {
			parentDetailsDAO = (ParentDetailsDAO)ServiceFinder.getContext(request).getBean("ParentDetailsHibernateDao");
			List parentDataList = new ArrayList();
			List parentDataListSplit = new ArrayList();
			parentDataList =	parentDetailsDAO.findByPropertyListLong("umId", Long.parseLong(UM_ID));

			System.out.println("saveParentDetails.sizz"+parentDataList.size());
			
			for (Iterator iterator = parentDataList.iterator(); iterator.hasNext();) {
				ParentDetails pd = new ParentDetails();
				pd = (ParentDetails) iterator.next();
			int i =0;
				//checkReq--1 father and 2 mother
				System.out.println(pd.getPdId()+"------"+fpdid+"***"+mpdid);
				if (pd.getPdId().toString().equalsIgnoreCase(fpdid)) {
					if (pd.getMobile().equalsIgnoreCase(fMobile)) {							
					}else{
						pd.setMobile(fMobile);
						i++;
					}
					if (pd.getEmail().equalsIgnoreCase(fMail)) {
					}else{
					pd.setEmail(fMail);
					i++;
					}
					if (pd.getIsDefault().equalsIgnoreCase(radioDefalut1)) {
					}else{
							pd.setIsDefault(radioDefalut1);						
						i++;
					}
					
				}
				if (pd.getPdId().toString().equalsIgnoreCase(mpdid)) {
					if (pd.getMobile().equalsIgnoreCase(mMobile)) {							
					}else{
						pd.setMobile(mMobile);
						i++;
					}
					if (pd.getEmail().equalsIgnoreCase(mMail)) {
					}else{
						pd.setEmail(mMail);
						i++;
					}
					if (pd.getIsDefault().equalsIgnoreCase(radioDefalut2)) {
					}else{
						pd.setIsDefault(radioDefalut2);						
						i++;
					}
					
				}
				System.out.println("i******"+i);
				if (i>0) {
					parentDetailsDAO.save(pd);
					isModified = true;
				}
				
				System.out.println(pd.getFirstName()+"****"+pd.getPtmId()+"**"+pd.getIsDefault()+"**"+pd.getPtmId()+"**"+pd.getPdId());
			}
			return isModified;
			
		} catch (BeansException e) {
			e.printStackTrace();
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return isModified;
	}
	
	public String getParentsListForEdit(String schoolNames[],String branchNames[],String classNames[],String sectionNames[],
			String fname,String lname,String dob,String email,String mobile,String landline,String addr1,String addr2,	String city,String state,
			String gender,String admissionNumber,String classAdmittedIn[],String fromDate,String toDate,String active,String parentTypeSel,String isDefault){
		
			ParentDetailsAnalysis analysis = new ParentDetailsAnalysis(request);
				String IM_ID 									= (String)session.getAttribute("IM_ID");
				List parentList								=  analysis.getParentsRawList(IM_ID,schoolNames,branchNames,classNames,sectionNames,fname,lname,dob,email,mobile,landline,addr1,addr2,	
																			city,state,gender,admissionNumber,classAdmittedIn,fromDate,toDate,active,1,parentTypeSel,isDefault,session);

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
				//System.out.println(parentList.size()+"jArray of uploaded DOC list::");
				System.out.println(parentList.size()+"jArray of uploaded DOC list::");
				
				return MainJsonArray.toString();
				
}

	
}
