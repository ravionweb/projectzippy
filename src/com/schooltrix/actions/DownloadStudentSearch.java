package com.schooltrix.actions;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.SessionAware;
import org.json.JSONArray;
import org.json.JSONObject;

import com.opensymphony.xwork2.ActionSupport;
import com.schooltrix.utils.ParentDetailsAnalysis;
import com.schooltrix.utils.StudentDetailsAnalysis;

public class DownloadStudentSearch extends ActionSupport implements ServletRequestAware,SessionAware{

	HttpServletRequest request = null;
	Map session;
	private InputStream fileInputStream;
	
	private String fileName;
	
	public String downloadStudentSearch() {
		try{
		String[] schoolNames 		= request.getParameterValues("schoolNames");
		String[] branchNames 		= request.getParameterValues("branchNames");
		String[] classNames			= request.getParameterValues("classNames");
		String[] sectionNames 		= request.getParameterValues("sectionNames");
		
		
		String fname						= request.getParameter("fname");
		String lname						= request.getParameter("lname");
		String dob							= request.getParameter("dob");
		String email						= request.getParameter("email");
		String mobile						= request.getParameter("mobile");
		String landline					= request.getParameter("landline");
		String addr1						= request.getParameter("addr1");
		String addr2						= request.getParameter("addr2");
		String city							= request.getParameter("city");
		String state							= request.getParameter("state");
		String gender						= request.getParameter("gender");
		String admissionNumber	= request.getParameter("admissionNumber");
		String classAdmittedIn[]	= request.getParameterValues("classAdmittedIn");
		String fromDate					= request.getParameter("admissionDateFrom");
		String toDate						= request.getParameter("admissionDateTO");
		String active						= request.getParameter("active");
		
		StudentDetailsAnalysis analysis = new StudentDetailsAnalysis(request);
		String IM_ID = (String)session.get("IM_ID");
		List studentList =  analysis.getStudentRawList(IM_ID,schoolNames,branchNames,classNames,sectionNames,fname,lname,dob,email,mobile,landline,addr1,addr2,	city,state,
														gender,admissionNumber,classAdmittedIn,fromDate,toDate,active,2,null);
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		setFileName("StudentSearch-"+sdf.format(new Date())+".csv");//user define File Name......................
		
		StringBuffer docPreapare = new StringBuffer();
		int sizeMain = studentList.size() ;
		 if (sizeMain <=0) {
			 docPreapare.append("No records found");
		 }
		
		String schoolIte = "";			
		Set<String> schoolSet = new HashSet();
					
		for (Iterator iterator1 = studentList.iterator(); iterator1	.hasNext();) {//for--555
			try {
				Object row1[] = (Object[]) iterator1.next();
				//school wise
				
				if (schoolSet.add((String)row1[22])) {//if--7777		
					schoolIte = (String)row1[22];
					docPreapare.append("School Name : ,"+schoolIte+"\n");
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
					
							if(schoolIte.equalsIgnoreCase((String)row2[22])){//extra chechking for unnessary reputation stooping//ifff------666
							//branch wise
								if (branchSet.add((String)row2[19])) {//if---5555
									
									branchIte = (String)row2[19];
									Set<String> classSet = new HashSet();
									String classIte = "";									
									System.out.println("in branch-*"+branchIte);									
									docPreapare.append("Branch Name : , "+branchIte+"\n");
									for (Iterator iterator3 = studentList.iterator(); iterator3	.hasNext();) {//for each brach it iterate list..//for------3333
										try {
											Object row3[] = (Object[]) iterator3.next();	
											
											if(branchIte.equalsIgnoreCase((String)row3[19])){//if	---44
								//--------------------------
												if (classSet.add((String)row3[17])) {//if	---33												
													classIte = (String)row3[17];
													Set<String> sectionSet = new HashSet();
													String sectionIte = "";
													
													docPreapare.append("Class Name : ,"+classIte+"\n");
													for (Iterator iterator4 = studentList.iterator(); iterator4	.hasNext();) {//for--2
														try {
																Object row4[] = (Object[]) iterator4.next();
														
															if(classIte.equalsIgnoreCase((String)row4[17]) && branchIte.equalsIgnoreCase((String)row4[19])){//if--2222
																
																if (sectionSet.add((String)row4[15])) {//if--1
																	
																	sectionIte = (String)row4[15];
																	System.out.println("insideeee branch-*"+branchIte);
																	//Name, 			DOB,			Gender,  		Addr1,  			Addr2	, 			City,  		AdmNo.	, 			Admi Date,		Class Admitted In,	 Active
																	docPreapare.append("Section Name : ,"+sectionIte+"\n");
																	docPreapare.append("Name,DOB,Gender,Address1,Address2,City,Admission Number, Admission Date, Class Admitted In ,Active"+"\n");		
																	
																	for (Iterator iterator5 = studentList.iterator(); iterator5	.hasNext();) {//for each section it iterate list..//for--1
																		try {
																				Object row5[] = (Object[]) iterator5.next();																			
																				if(sectionIte.equalsIgnoreCase((String)row5[15]) && branchIte.equalsIgnoreCase((String)row5[19])){
																				//if(sectionIte.equalsIgnoreCase((String)row5[15])){
																					//adding student data to object  and after that add to section array...,every section haves it's own array 
//												0			1				2				3			4			5		6		7		8		9			10								11					12																					
//											stuID,firstName,lastName,gender,addr1,addr2,city,state,dob,photo,admissionNumber,admissionDate,classAdmittedIN,
//												13	14			15			16	17			18			19					20				21		22					23																					
	//										active,secID ,secName,cmID,className,bmID ,branchName,bShortName,smID,schoolName,sShortName 													
																					
													//firstName, 	lastName		DOB,			Gender,  		Addr1,  			Addr2	, 			City,  		AdmNo.	, 			Admi Date,		Class Admitted In,	 Active		View Photo	Edit	activeString																					
					docPreapare.append(row5[1]+" "+row5[2]+","+row5[8]+","+(row5[3].toString().equalsIgnoreCase("F")?"Girl":"Boy")+","+row5[4]+","+row5[5]+","+	row5[6]+","+row5[10]+","+row5[11].toString()+","+row5[12]+","+(row5[13].toString().equalsIgnoreCase("Y")?"Yes":"No")+"\n");
																				}// 																
																		 } catch (Exception e) {e.printStackTrace();}	
																	 }//for--1
															
																  }//if--1																	
															
																}//if--2222
															} catch (Exception e) {
																e.printStackTrace();
															}
														}//for--2
													
												   }//if	---33
											  }//if	---44
										}catch (Exception e) {
										e.printStackTrace();
										}	
								
									}//for------3333
								
								}//if---5555
				
							 }//if---6666
						 }catch (Exception e) {
							 e.printStackTrace();
						 }
					  }//for------444
		
				docPreapare.append("\n\n");
				}//if--77777	
			   
			}catch (Exception e) {
				   	e.printStackTrace();
			}	

		}//for-----55555555
		 fileInputStream = new ByteArrayInputStream(docPreapare.toString().getBytes());
		 System.out.println(studentList.size()+"jArray of uploaded DOC list::"+docPreapare);
	
		return "success";					
		}catch (Exception e) {
			e.printStackTrace();
			return "fail";					
		}
	}
	
	public String downloadParentSearch() {
		try{
		String[] schoolNames 		= request.getParameterValues("schoolNames");
		String[] branchNames 		= request.getParameterValues("branchNames");
		String[] classNames			= request.getParameterValues("classNames");
		String[] sectionNames 		= request.getParameterValues("sectionNames");
		
		
		String fname						= request.getParameter("fname");
		String lname						= request.getParameter("lname");
		String dob							= request.getParameter("dob");
		String email						= request.getParameter("email");
		String mobile						= request.getParameter("mobile");
		String landline					= request.getParameter("landline");
		String addr1						= request.getParameter("addr1");
		String addr2						= request.getParameter("addr2");
		String city							= request.getParameter("city");
		String state							= request.getParameter("state");
		String gender						= request.getParameter("gender");
		String admissionNumber	= request.getParameter("admissionNumber");
		String classAdmittedIn[]	= request.getParameterValues("classAdmittedIn");
		String fromDate					= request.getParameter("admissionDateFrom");
		String toDate						= request.getParameter("admissionDateTO");
		String active						= request.getParameter("active");

		String parentTypeSel			= request.getParameter("parentTypeSel");
		String isDefault					= request.getParameter("isDefault");
		
		
		
		ParentDetailsAnalysis analysis = new ParentDetailsAnalysis(request);
		String IM_ID = (String)session.get("IM_ID");
		List parentList =  analysis.getParentsRawList(IM_ID,schoolNames,branchNames,classNames,sectionNames,fname,lname,dob,email,mobile,landline,addr1,addr2,	city,state,
														gender,admissionNumber,classAdmittedIn,fromDate,toDate,active,2,parentTypeSel, isDefault, null);
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		setFileName("ParentSearch-"+sdf.format(new Date())+".csv");//user define File Name......................
		
		StringBuffer docPreapare = new StringBuffer();
		int sizeMain = parentList.size() ;
		 if (sizeMain <=0) {
			 docPreapare.append("No records found");
		 }
		
		String schoolIte = "";			
		Set<String> schoolSet = new HashSet();
					
		for (Iterator iterator1 = parentList.iterator(); iterator1	.hasNext();) {//for--555
		JSONObject MainJsonObject = new JSONObject();		
			try {
			Object row1[] = (Object[]) iterator1.next();
			//school wise
			
				if (schoolSet.add((String)row1[25])) {//if--7777		
				schoolIte = (String)row1[25];
				docPreapare.append("School Name : ,"+schoolIte+"\n");
				Set<String> branchSet = new HashSet();
				String branchIte = "";
				//	0			1				2					3		4			5			6		7		8		9			10							11						12						13
				//stuID,firstName,lastName,gender,addr1,addr2, city,state,dob, photo,admissionNumber,admissionDate,classAdmittedIN,active, 
				//  14		15				16	17				18		19					20				21			22				23
				//secID ,secName,cmID,className,bmID ,branchName,bShortName,smID,schoolName,	sShortName  				
				
				//27=17+10
				//	0			1				2					3		4			5			6		7		8		9			10							11						12						13		
				//pdID, stuID,firstName,lastName,gender,addr1,addr2, city,state,dob, photo,admissionNumber,admissionDate,classAdmittedIN,
				//  14				15				16		17			18		19			20		21			22				23				24				25			26
				//	active, parentType,isDefault,secID ,secName,cmID,className,bmID ,branchName,bShortName,smID,schoolName,	sShortName  
				
				System.out.println(schoolSet.size()+"**SET schoolSet-->"+schoolSet);
					for (Iterator iterator2 = parentList.iterator(); iterator2	.hasNext();) {//for---44444
						try {
						Object row2[] = (Object[]) iterator2.next();
						
							if(schoolIte.equalsIgnoreCase((String)row2[25])){//extra chechking for unnessary reputation stooping//ifff------666
							//branch wise
								if (branchSet.add((String)row2[22])) {//if---5555
								
								branchIte = (String)row2[22];
								Set<String> classSet = new HashSet();
								String classIte = "";
								
								System.out.println("in branch-*"+branchIte);
								docPreapare.append("Branch Name : , "+branchIte+"\n");
								
									for (Iterator iterator3 = parentList.iterator(); iterator3	.hasNext();) {//for each brach it iterate list..//for------3333
									
									try {
									Object row3[] = (Object[]) iterator3.next();	
									
										if(branchIte.equalsIgnoreCase((String)row3[22])){//if	---44
										//--------------------------
											if (classSet.add((String)row3[20])) {//if	---33												
											classIte = (String)row3[20];
											Set<String> sectionSet = new HashSet();
											String sectionIte = "";
											
											docPreapare.append("Class Name : ,"+classIte+"\n");
												for (Iterator iterator4 = parentList.iterator(); iterator4	.hasNext();) {//for--2
												try {
														Object row4[] = (Object[]) iterator4.next();
												
														if(classIte.equalsIgnoreCase((String)row4[20]) && branchIte.equalsIgnoreCase((String)row4[22])){//if--2222
														  
															if (sectionSet.add((String)row4[18])) {//if--1
																
																sectionIte = (String)row4[18];
																System.out.println("insideeee branch-*"+branchIte);
																docPreapare.append("Section Name : ,"+sectionIte+"\n");
																////Parent Name					Student Name		Admission Number	Parent Type  City			Phone Number	Email ID				Default Contact
																docPreapare.append("Name,Student Name,Admission Number, Parent Type, City,Phone Number,Email ID,Default Contact"+"\n");		
																
																for (Iterator iterator5 = parentList.iterator(); iterator5	.hasNext();) {//for each section it iterate list..//for--1
																	try {
																			Object row5[] = (Object[]) iterator5.next();																			
																			if(sectionIte.equalsIgnoreCase((String)row5[18]) && branchIte.equalsIgnoreCase((String)row5[22])&&classIte.equalsIgnoreCase((String)row5[20])){
		//	0			1				2					3		4			5			6		7		8		9			10							11						12						13		
		//pdID, stuID,firstName,lastName,gender,addr1,addr2, city,state,dob, photo,admissionNumber,admissionDate,classAdmittedIN,
		//  14				15				16		17			18		19			20		21			22				23				24				25			26				27						28						29			30
		//	active, parentType,isDefault,secID ,secName,cmID,className,bmID ,branchName,bShortName,smID,schoolName,	sShortName  ,sd.First_Name ,sd.Last_Name  ,pd.Email ,pd.Mobile 
	
												//Parent Name					Student Name		Admission Number	Parent Type  City			Phone Number	Email ID				Default Contact													
docPreapare.append(row5[2]+" "+row5[3]+","+row5[27]+" "+row5[28]+","+row5[11]+","+row5[15]+","+row5[7]+","+	row5[30]+","+row5[29]+","+(row5[16].toString().equalsIgnoreCase("Y")?"Yes":"No")+"\n");								

//old---->Name	DOB	Gender		Address1	Address2  City		Admission Number	 Admission Date	 Class Admitted In parentType,isDefault,	Active																																
																			}
																	 } catch (Exception e) {e.printStackTrace();}	
																 }//for--1
																
															  }//if--1
																
														  }//if--2222
													} catch (Exception e) {
														e.printStackTrace();
													}
												}//for--2
											}//if	---33
										}//if	---44			
									}catch (Exception e) {
									e.printStackTrace();
									}	
									}//for------3333		
								}//if---5555				
							}//if---6666						
						}catch (Exception e) {
					e.printStackTrace();
					}
					}//for------444
				
					docPreapare.append("\n\n");			
				}//if--77777	
			
			}catch (Exception e) {
		e.printStackTrace();
		}	
			
		}//for-----55555555
		 fileInputStream = new ByteArrayInputStream(docPreapare.toString().getBytes());
		 System.out.println(parentList.size()+"jArray of uploaded DOC list::");
	
		return "success";					
		}catch (Exception e) {
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
