package com.schooltrix.actions;

import java.io.File;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.SessionAware;
import org.springframework.beans.BeansException;

import com.opensymphony.xwork2.ActionSupport;
import com.schooltrix.daos.BranchMasterDAO;
import com.schooltrix.daos.InstitutionMasterDAO;
import com.schooltrix.daos.ParentDetailsDAO;
import com.schooltrix.daos.SectionMasterDAO;
import com.schooltrix.daos.StaffDetailsDAO;
import com.schooltrix.daos.StudentDetailsDAO;
import com.schooltrix.daos.UserMasterDAO;
import com.schooltrix.hibernate.BranchStaffMap;
import com.schooltrix.hibernate.InstitutionMaster;
import com.schooltrix.hibernate.ParentDetails;
import com.schooltrix.hibernate.ParentStudentMap;
import com.schooltrix.hibernate.SectionClassMap;
import com.schooltrix.hibernate.StaffDetails;
import com.schooltrix.hibernate.StudentDetails;
import com.schooltrix.hibernate.StudentSectionMap;
import com.schooltrix.hibernate.UserMaster;
import com.schooltrix.managers.ServiceFinder;

public class AddUserAction extends ActionSupport  implements SessionAware,ServletRequestAware{

	 private org.apache.log4j.Logger log = Logger.getLogger(AddUserAction.class);
	private String username,password;
	Map session;
	private String userType;
	HttpServletRequest request = null;
	private File fileUP;
	private String fileUPContentType;
	private String fileUPFileName;
	
	public String execute() throws Exception {
		return "";
	}
	
	//this method prasently return "success" in all cases but ,diff is if success full tranx only have some values in session
	public String addNonAdminUser() throws Exception {
		// TODO Auto-generated method stub
		 System.out.println(""+session.get("IM_SN")+"--"+session.get("IM_ID")+"---"+(String)session.get("userType"));
		if(session.get("IM_SN") != null && session.get("IM_ID")!=null && ((String)session.get("userType")).equalsIgnoreCase("AD") ){
			
	
		
		String autoUserID			 =null;
		 String autoPassword 		=	null;
		
		 String filePath = request.getSession().getServletContext().getRealPath("/")+"uploaded/";
		 
		/* 
		 session.put("IM_ID", userMaster.getImId()+"");
					session.put("SM_ID", userMaster.getSmId()+"");
					session.put("BM_ID", userMaster.getBmId()+"");
					session.put("UM_ID", userMaster.getUmId()+"");
					session.put("UT_ID", userMaster.getUtId()+"");
		 *
		 */
		String IM_SN =	(String)session.get("IM_SN");

		String im_id =	(String)session.get("IM_ID");
/*		 String sm_id =	(String)session.get("SM_ID");
		 String bm_id =	(String)session.get("BM_ID");*/
		 
		String sm_id  = request.getParameter("schoolNames");
		String bm_id = request.getParameter("branchNames");
		String cm_id = request.getParameter("classNames");
		String secm_id = request.getParameter("sectionNames");
		 
		 String fname =	request.getParameter("fname");
		 String lname =	request.getParameter("lname");
		
		 String  userRights =	request.getParameter("userRights");//IMP---------------------------
		 
		 String userType = "";
		
		 String dob =	request.getParameter("dob");
		 String designation =	request.getParameter("designation");
		 String email =	request.getParameter("email");
		 String mobile =	request.getParameter("mobile");
		 String landline =	request.getParameter("landline");
		 
		 String isParent =	request.getParameter("isParent");
		 String parentType =	request.getParameter("parentTypeSel");
		 
		 String addr1 =	request.getParameter("addr1");
		 String addr2 =	request.getParameter("addr2");
		 String city =	request.getParameter("city");
		// String country =	request.getParameter("country");
		 String state =	request.getParameter("state");
		 String fileUP =	request.getParameter("fileUP");
		 String active =	request.getParameter("active");
		
		 String admissionNumber =	request.getParameter("admissionNumber");
		 String admissionDate =	request.getParameter("admissionDate");
		 String classAdmittedIn =	request.getParameter("classAdmittedIn");
		 String gender =	request.getParameter("gender");
		 
		 String isDefault  =	request.getParameter("isDefault");
		 
		 //NEW req..Parent details while adding student 2013-08-28 
		 String fFName 				=	request.getParameter("fFName");
		 String fLName 				=	request.getParameter("fLName");
		 String fEmail 				=	request.getParameter("fEmail");
		 String fPhone 				=	request.getParameter("fPhone");
		 String isDefaultPar	 	=	request.getParameter("isDefaultPar");
		 String mFName 			=	request.getParameter("mFName");
		 String mLName 			=	request.getParameter("mLName");
		 String mEmail 				=	request.getParameter("mEmail");
		 String mPhone 				=	request.getParameter("mPhone");
		 String autoUserIDPar			 =null;
		 String autoPasswordPar 		=	null;		 
		 boolean isdddE = false;
		 UserMaster userInputDataF =null;//based on null ....we get data or not checking
		 
		 
		 if(admissionNumber == null){ 
			admissionNumber = "";
		}
		if(admissionDate == null){ 
			admissionDate= "0000-00-00";
		}
		if(classAdmittedIn == null){
			classAdmittedIn= "";
		}
		 
		 
		 if(active == null || active == "null"){
			 active = "N";
		 }else{
			 active = "Y";
		 }

		 if(isParent == null || isParent == "null"){
			 isParent = "N";
		 }else{
			 isParent = "Y";
		 }
		 
		 if(isDefault == null || isDefault == "null"){
			 isDefault = "N";
		 }else{
			 isDefault = "Y";
		 }
	
		 
		 
		 
		 System.out.println("state::"+state+":fileUP:"+fileUP+"::userRights:"+userRights+"::isParent::"+isParent+"*isDefault*"+isDefault);
		 //userRights.equalsIgnoreCase("AD")
		 
		 //generate ID and Password
		 
		 
/*		 i.	For Staff
		 1.	Prefix SF to the Unique ID
		 ii.	For Teacher 
		 1.	Prefix TH to the Unique ID
		 iii.	For Non Teacher
		 1.	Prefix NTH to the Unique ID
		 iv.	For Students
		 1.	Prefix Stu to the Unique ID
		 v.	For Parents
		 1.	Prefix Par to the Unique ID*/

			 if(userRights.equalsIgnoreCase("PA")){
				String[] idPassword =  getIdPassword("Par",fname,lname,im_id).split("~");
				autoUserID 	= idPassword[0];
				autoPassword = idPassword[1];
				userType = "2";//presently assigned manually,but later from db;
			}else if(userRights.equalsIgnoreCase("TC")){
				String[] idPassword =  getIdPassword("TH",fname,lname,im_id).split("~");
				autoUserID 	= idPassword[0];
				autoPassword = idPassword[1];
				userType = "3";//presently assigned manually,but later from db;
			}else if(userRights.equalsIgnoreCase("NTS")){
				String[] idPassword =  getIdPassword("NTH",fname,lname,im_id).split("~");
				autoUserID 	= idPassword[0];
				autoPassword = idPassword[1];						
				userType = "4";//presently assigned manually,but later from db;
			}else if(userRights.equalsIgnoreCase("ST")){
				String[] idPassword =  getIdPassword("Stu",fname,lname,im_id).split("~");
				autoUserID 	= idPassword[0];
				autoPassword = idPassword[1];						
				userType = "5";//presently assigned manually,but later from db;
			}				 
		 
		 String uniqueFileName ="";
		 String returnFlag = "";
		 
		 UserMasterDAO userMasterDao =null;
		 StaffDetailsDAO staffDetailsDao = null;
		 ParentDetailsDAO parentDetailsDao = null;
		 StudentDetailsDAO studentDetailsDao = null;
		 ParentDetails parentData = null;
		 StaffDetails staffData =null;
		 StudentDetails studentData =null;
		 File fileToCreate = null;
		 
		 filePath = filePath+"/"+IM_SN+"/"+userRights+"/";
		 
		 UserMaster userInputData = new UserMaster();
			userInputData.setImId(Long.parseLong(im_id));//?
			userInputData.setSmId(Long.parseLong(sm_id));//?
			userInputData.setBmId(Long.parseLong(bm_id));//?
			userInputData.setUtId(Long.parseLong(userType));//?
			userInputData.setUserId(autoUserID);
			userInputData.setPassword(autoPassword);
			userInputData.setActive(active);
			
			 if(userRights.equalsIgnoreCase("NTS")||userRights.equalsIgnoreCase("TC")){
				 
				 System.out.println("in NTS or TC data");
				 staffData = new StaffDetails();
					staffData.setActive(active);
					staffData.setAddress1(addr1);
					staffData.setAddress2(addr2);
					staffData.setCity(city);
					//staffData.setCountry(country);
					staffData.setDesignation(designation);
					staffData.setDob(dob);
					staffData.setEmail(email);
					staffData.setFirstName(fname);
					staffData.setLandline(landline);
					staffData.setLastName(lname);
					staffData.setGender(gender);
					staffData.setMobile(mobile);
					//staffData.setPhoto(fileUP);
					staffData.setState(state);//state ID
					returnFlag = "successSaff";
			 }
			if(userRights.equalsIgnoreCase("PA" ) ||  isParent.equalsIgnoreCase("Y")){//a parent may also TS or NTS
				System.out.println("in PA or Y data");
				 parentData = new ParentDetails();//15
				parentData.setActive(active);
				parentData.setAddress1(addr1);
				parentData.setAddress2(addr2);
				parentData.setCity(city);
				parentData.setDob(dob);
				parentData.setEmail(email);
				parentData.setFirstName(fname);
				parentData.setLandline(landline);
				parentData.setLastName(lname);
				parentData.setGender(gender);
				parentData.setMobile(mobile);
				//parentData.setPhoto(fileUP);
				parentData.setState(state);//state ID
				parentData.setIsDefault(isDefault);
				if (parentType == null) {
					parentData.setPtmId("1");					
				} else {
					parentData.setPtmId(parentType);
				}
				
				
			}
			
			ParentDetails detailsF = null;
			ParentDetails detailsM = null;
			
			if(userRights.equalsIgnoreCase("ST")){
				System.out.println("in student data");
				studentData = new StudentDetails();
				studentData.setActive(active);
				studentData.setAddress1(addr1);
				studentData.setAddress2(addr2);
				studentData.setAdmissionDate(admissionDate);
				studentData.setAdmissionNumber(admissionNumber);
				studentData.setCity(city);
				studentData.setClassAdmittedIn(classAdmittedIn);
				studentData.setDob(dob);
				studentData.setEmail(email);
				studentData.setFirstName(fname);
				studentData.setGender(gender);
				studentData.setLandline(landline);
				studentData.setLastName(lname);
				studentData.setMobile(mobile);
				//studentData.setPhoto(fileUP);
				studentData.setState(state);
				
				
				boolean parFlag = false;
				if ((isDefaultPar.equalsIgnoreCase("father") && StringUtils.isNotBlank(fFName)&&StringUtils.isNotBlank(fLName) 
						&&StringUtils.isNotBlank(fEmail) &&StringUtils.isNotBlank(fPhone) )
						||(!isDefaultPar.equalsIgnoreCase("father") && (StringUtils.isNotBlank(fFName)||StringUtils.isNotBlank(fLName) 
						&&StringUtils.isNotBlank(fEmail) &&StringUtils.isNotBlank(fPhone) ))){
					detailsF = new ParentDetails();//15
					detailsF.setFirstName(StringUtils.isNotBlank(fFName) == true?fFName:"NA");
					detailsF.setLastName(StringUtils.isNotBlank(fLName) == true?fLName:"NA");
					detailsF.setEmail(StringUtils.isNotBlank(fEmail) == true?fEmail:"NA");
					detailsF.setMobile(StringUtils.isNotBlank(fPhone) == true?fPhone:"NA");
					
					detailsF.setActive("NA");
					detailsF.setAddress1("NA");
					detailsF.setAddress2("NA");
					detailsF.setCity("NA");
					detailsF.setDob("NA");
					detailsF.setLandline("NA");
					detailsF.setGender("M");
					detailsF.setState(state);//state ID
					detailsF.setPtmId("1");					
					detailsF.setPhoto("noImage.jpg");
					detailsF.setIsDefault("N");
					detailsF.setActive("Y");
					parFlag = true;
					System.out.println("IN Father");
					if (isDefaultPar.equalsIgnoreCase("father")) {
						detailsF.setIsDefault("Y");
						isdddE = true;
						String[] idPassword =  getIdPassword("Par",fFName,fLName,im_id).split("~");
						autoUserIDPar 		= idPassword[0];
						autoPasswordPar 	= idPassword[1];
					}
				}
				
			System.out.println(fFName +"**"+fLName +"&&"+fEmail +"(("+fPhone);
			System.out.println(mFName +"**"+mLName +"&&"+mEmail +"(("+mPhone);
			
			if ((isDefaultPar.equalsIgnoreCase("mother") && StringUtils.isNotBlank(mFName) &&StringUtils.isNotBlank(mLName) 
					&&StringUtils.isNotBlank(mEmail)&&StringUtils.isNotBlank(mPhone) )
					|| (!isDefaultPar.equalsIgnoreCase("mother") && (StringUtils.isNotBlank(mFName)||StringUtils.isNotBlank(mLName) ||StringUtils.isNotBlank(mEmail)||StringUtils.isNotBlank(mPhone)))){
				
					detailsM = new ParentDetails();//15
					detailsM.setFirstName(StringUtils.isNotBlank(mFName) == true?mFName:"NA");
					detailsM.setLastName(StringUtils.isNotBlank(mLName) == true?mLName:"NA");
					detailsM.setEmail(StringUtils.isNotBlank(mEmail) == true?mEmail:"NA");
					detailsM.setMobile(StringUtils.isNotBlank(mPhone) == true?mPhone:"NA");
					//problem empty records inserted
					detailsM.setActive("NA");
					detailsM.setAddress1("NA");
					detailsM.setAddress2("NA");
					detailsM.setCity("NA");
					detailsM.setDob("NA");
					detailsM.setLandline("NA");
					detailsM.setGender("F");
					detailsM.setState(state);//state ID
					detailsM.setPtmId("2");	
					detailsM.setPhoto("noImage.jpg");
					detailsM.setIsDefault("N");
					detailsM.setActive("Y");
					System.out.println("IN MOTHER");
					parFlag = true;
					if (isDefaultPar.equalsIgnoreCase("mother")  || isdddE == false) {
						detailsM.setIsDefault("Y");
						isdddE = true;
						String[] idPassword =  getIdPassword("Par",mFName,mLName,im_id).split("~");
						autoUserIDPar 		= idPassword[0];
						autoPasswordPar 	= idPassword[1];
					}

				}
				if (parFlag == true) {
			
				userInputDataF = new UserMaster();
				userInputDataF.setImId(Long.parseLong(im_id));
				userInputDataF.setSmId(Long.parseLong(sm_id));
				userInputDataF.setBmId(Long.parseLong(bm_id));
				userInputDataF.setUtId(2L);//?
				userInputDataF.setUserId(autoUserIDPar);
				userInputDataF.setPassword(autoPasswordPar);
				userInputDataF.setActive("Y");
				
				}
				
				returnFlag = "successStu";
			}
		//-------------------------------------------------------------------------------------------------------------------------------------------
			//studentData.setPhoto(fileUP);
			//staffData.setPhoto(fileUP);
			//parentData.setPhoto(fileUP);
		 
		 //Condition Starts here
			 if((userRights.equalsIgnoreCase("NTS")||userRights.equalsIgnoreCase("TC")) && isParent.equalsIgnoreCase("Y")){
			 
				 System.out.println("in NTS or TC and  Y Insertion");
				 try {
					userMasterDao = (UserMasterDAO)ServiceFinder.getContext(request).getBean("UserMasterHibernateDao"); 		
						userMasterDao.save(userInputData);//---------------------ONE
						uniqueFileName = getFileUniqueName();
						parentData.setPhoto(uniqueFileName);
						parentData.setUmId(userInputData.getUmId());
					parentDetailsDao = (ParentDetailsDAO)ServiceFinder.getContext(request).getBean("ParentDetailsHibernateDao");
						parentDetailsDao.save(parentData);
						/**branch-Staff Map -2013-08-18----before we thing these type of mapping all are in seperate form...but ,
						 * here may not required ,bcz branch iD and UM_id i have so i can save mapping here...see if any thing wrong in later  */ 
						BranchStaffMap branchStaffMap = new BranchStaffMap();
						branchStaffMap.setActive("Y");
						branchStaffMap.setBmId(bm_id);
						branchStaffMap.setUmId(userInputData.getUmId()+"");
						BranchMasterDAO branchMasterDAO =  (BranchMasterDAO)ServiceFinder.getContext(request).getBean("BranchMasterHibernateDao");
						branchMasterDAO.save(branchStaffMap);
						
						staffData.setPhoto(uniqueFileName);
						staffData.setUmId(userInputData.getUmId());//?from above we need usertype ID//-----------------TWO
					staffDetailsDao = (StaffDetailsDAO)ServiceFinder.getContext(request).getBean("StaffDetailsHibernateDao");
						staffDetailsDao.save(staffData);
						
						//   File fileToCreate = new File(filePath, this.fileUPFileName);
						if (!uniqueFileName.equalsIgnoreCase("noImage.jpg")) {							
							fileToCreate = new File(filePath,uniqueFileName);// unique file name
							FileUtils.copyFile(this.fileUP, fileToCreate);
						}else{
							System.out.println("no image");
						}
						
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					session.put("nonadminMsg", "Failed");
					return returnFlag;
				}
			 
		 }else  if(userRights.equalsIgnoreCase("TC")||userRights.equalsIgnoreCase("NTS")){
			 //Staff details------------and userMaster
			 System.out.println("in NTS or TC Insertion");
				try {
					userMasterDao = (UserMasterDAO)ServiceFinder.getContext(request).getBean("UserMasterHibernateDao"); 		
						userMasterDao.save(userInputData);//---------------------ONE
						
						/**branch-Staff Map -2013-08-18----before we thing these type of mapping all are in seperate form...but ,
						 * here may not required ,bcz branch iD and UM_id i have so i can save mapping here...see if any thing wrong in later  */ 
						BranchStaffMap branchStaffMap = new BranchStaffMap();//2013-08-28
						branchStaffMap.setActive("Y");
						branchStaffMap.setBmId(bm_id);
						branchStaffMap.setUmId(userInputData.getUmId()+"");
						BranchMasterDAO branchMasterDAO =  (BranchMasterDAO)ServiceFinder.getContext(request).getBean("BranchMasterHibernateDao");
						branchMasterDAO.save(branchStaffMap);
						
						
						uniqueFileName = getFileUniqueName();
						staffData.setPhoto(uniqueFileName);
						staffData.setUmId(userInputData.getUmId());//?from above we need usertype ID//-----------------TWO
					staffDetailsDao = (StaffDetailsDAO)ServiceFinder.getContext(request).getBean("StaffDetailsHibernateDao");
						staffDetailsDao.save(staffData);
						
						//   File fileToCreate = new File(filePath, this.fileUPFileName);
						if (!uniqueFileName.equalsIgnoreCase("noImage.jpg")) {							
							fileToCreate = new File(filePath,uniqueFileName);// unique file name
							FileUtils.copyFile(this.fileUP, fileToCreate);
						}else{
							System.out.println("no image");
						}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					session.put("nonadminMsg", "Failed");
					return returnFlag;
				}
			 
		 }else if(userRights.equalsIgnoreCase("PA")){
			 System.out.println("in PA Insertion");
			 
				try {
					returnFlag = "successPA";
					
					userMasterDao = (UserMasterDAO)ServiceFinder.getContext(request).getBean("UserMasterHibernateDao"); 		
						userMasterDao.save(userInputData);//---------------------ONE
						
						uniqueFileName = getFileUniqueName(); 
						parentData.setPhoto(uniqueFileName);
						parentData.setUmId(userInputData.getUmId());
					parentDetailsDao = (ParentDetailsDAO)ServiceFinder.getContext(request).getBean("ParentDetailsHibernateDao");
						parentDetailsDao.save(parentData);
				
						//   File fileToCreate = new File(filePath, this.fileUPFileName);
						//String name = String.format("%s.%s", RandomStringUtils.randomAlphanumeric(10), "jpg");
						if (!uniqueFileName.equalsIgnoreCase("noImage.jpg")) {							
							fileToCreate = new File(filePath,uniqueFileName);// unique file name
							FileUtils.copyFile(this.fileUP, fileToCreate);
						}else{
							System.out.println("no image");
						}
						
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					session.put("nonadminMsg", "Failed");
					return returnFlag;
				}
			 
		 }else if (userRights.equalsIgnoreCase("ST")) {

			 System.out.println("in Stu Insertion");
			 
				try {
					userMasterDao = (UserMasterDAO)ServiceFinder.getContext(request).getBean("UserMasterHibernateDao"); 		
						userMasterDao.save(userInputData);//---------------------ONE
						
						System.out.println(userInputData.getUmId()+"::::"+studentData.getAdmissionDate());
						uniqueFileName = getFileUniqueName();
						studentData.setPhoto(uniqueFileName);
						studentData.setUmId(userInputData.getUmId()+"");
					
					studentDetailsDao = (StudentDetailsDAO)ServiceFinder.getContext(request).getBean("StudentDetailsHibernateDao");
					studentDetailsDao.save(studentData);

					//student section map*** START
					StudentSectionMap map = new StudentSectionMap(getSctionClassMapID(bm_id, cm_id, secm_id),studentData.getStuId()+"",im_id,bm_id,sm_id,"Y");
					studentDetailsDao.insertStudentSectionMap(map);
					//student section map*** END
					
					   System.out.println("Server path:" + filePath+":::fileUPFileName"+fileUPFileName+"::fileUP:"+fileUP);
						 
			            System.out.println("source file name:" + getFileUPFileName()+":::fileUPFileName"+getFileUP()+"::fileUP:"+getFileUPContentType());
					
						if (!uniqueFileName.equalsIgnoreCase("noImage.jpg")) {							
							fileToCreate = new File(filePath,uniqueFileName);// unique file name
							FileUtils.copyFile(this.fileUP, fileToCreate);
						}else{
							System.out.println("no image");
						}
						
						try{
							if (userInputDataF != null) {
								userMasterDao.save(userInputDataF);
								
									parentDetailsDao = (ParentDetailsDAO)ServiceFinder.getContext(request).getBean("ParentDetailsHibernateDao");
									if (detailsF != null) {
										detailsF.setUmId(userInputDataF.getUmId());
										parentDetailsDao.save(detailsF);
									ParentStudentMap map2 = new ParentStudentMap();
										map2.setActive("Y");
										map2.setPdId(detailsF.getPdId()+"");
										map2.setStuId(studentData.getStuId()+"");
										studentDetailsDao.saveStudentParentMap(map2);
									}
									if (detailsM != null) {
										detailsM.setUmId(userInputDataF.getUmId());
										parentDetailsDao.save(detailsM);
										
										ParentStudentMap map2 = new ParentStudentMap();
										map2.setActive("Y");
										map2.setPdId(detailsM.getPdId()+"");
										map2.setStuId(studentData.getStuId()+"");
										studentDetailsDao.saveStudentParentMap(map2);
										
									}
						  }	
						}catch (Exception e) {
							e.printStackTrace();
						}
						
					
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					session.put("nonadminMsg", "Failed");
					return returnFlag;
				}
			 
		}
			 session.put("nonadminMsg", "Success");
			 session.put("userIDD", autoUserID);
			 session.put("passwordd", autoPassword);
		 return returnFlag;
		 
		}else{
			System.out.println("in main else of AddUseraction--");
			return "success";
		}
		 
	}
	
	public String getSctionClassMapID(String bM_ID, String curClassID,
			String sectionID2) {
		SectionMasterDAO smd = (SectionMasterDAO)ServiceFinder.getContext(request).getBean("SectionMasterDAO");
		try {
			SectionClassMap sectionMasterCheck = smd.findByProperty3(bM_ID, curClassID, sectionID2);
			return sectionMasterCheck.getScmId()+"";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	
	
	public String addAdminUser() throws Exception {
		// TODO Auto-generated method stub

		String filePath = request.getSession().getServletContext().getRealPath("/")+"uploaded/";
		 
		/* String im_id =	request.getParameter("fname");
		 String sm_id =	request.getParameter("fname");
		 String bm_id =	request.getParameter("fname");*/
		
		 String im_id =	request.getParameter("im_id_list");
		 String sm_id =	"0";
		 String bm_id =	"0";
		
		 if (Long.parseLong(im_id)>0) {
			
		
		 
		 String fname =	request.getParameter("fname");
		 String lname =	request.getParameter("lname");
		 
		 String userID =	request.getParameter("userID");	
		 String password =	request.getParameter("password");
		
		 String  userRights =	request.getParameter("userRights");//IMP---------------------------
		 String userType = "";
		
		 String dob =	request.getParameter("dob");
		 String designation =	request.getParameter("designation");
		 String email =	request.getParameter("email");
		 String mobile =	request.getParameter("mobile");
		 String landline =	request.getParameter("landline");
		 String gender =	request.getParameter("gender");
		 if(StringUtils.isBlank(gender) && gender.equalsIgnoreCase("F")){
			 gender = "F";			 
		 }else{
			 gender = "M";
		 }
		 
		 //String isParent =	request.getParameter("isParent");
		// String parentType =	request.getParameter("parentTypeSel");
		 
		 String addr1 =	request.getParameter("addr1");
		 String addr2 =	request.getParameter("addr2");
		 String city =	request.getParameter("city");
		// String country =	request.getParameter("country");
		 String state =	request.getParameter("state");
		 String fileUP =	request.getParameter("fileUP");
		 String active =	request.getParameter("active");
	

		 if(active == null || active == "null"){
			 active = "N";
		 }else{
			 active = "Y";
		 }

	/*	 if(isParent == null || isParent == "null"){
			 isParent = "N";
		 }else{
			 isParent = "Y";
		 }*/
	
		 System.out.println("state::"+state+":fileUP:"+fileUP+"::userRights:"+userRights);
		 //userRights.equalsIgnoreCase("AD")
		 
		 //generate ID and Password
		 
		 
		 String uniqueFileName ="";
		 
		 
		 UserMasterDAO userMasterDao =null;
		 StaffDetailsDAO staffDetailsDao = null;
		 ParentDetailsDAO parentDetailsDao = null;
		
		 ParentDetails parentData = null;
		 StaffDetails staffData =null;		 
		 File fileToCreate = null;
		 
		 try {
			 InstitutionMaster getinstitutionMasterDetails = getinstitutionMasterDetails(Long.parseLong(im_id));
			  filePath = filePath+"/"+getinstitutionMasterDetails.getShortName()+"/"+userRights+"/";
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			filePath = filePath+"/"+im_id+"/"+userRights+"/";
		}
		 
		 userType = "1";//presently assigned manually,but later from db;
		 
		 UserMaster userInputData = new UserMaster();
			userInputData.setImId(Long.parseLong(im_id));//?
			userInputData.setSmId(new Long(sm_id));//?
			userInputData.setBmId(new Long(bm_id));//?
			userInputData.setUtId(Long.parseLong(userType));//?
			userInputData.setUserId("AD"+userID);
			userInputData.setPassword(password);
			userInputData.setActive(active);
			
			 if(userRights.equalsIgnoreCase("admin")){
				 
				 System.out.println("in admin data");
				 staffData = new StaffDetails();
					staffData.setActive(active);
					staffData.setAddress1(addr1);
					staffData.setAddress2(addr2);
					staffData.setCity(city);
					//staffData.setCountry(country);
					staffData.setDesignation(designation);
					staffData.setDob(dob);
					staffData.setEmail(email);
					staffData.setFirstName(fname);
					staffData.setLandline(landline);
					staffData.setGender(gender);
					staffData.setLastName(lname);
					staffData.setMobile(mobile);
					staffData.setState(state);//state ID
			 }
			
		//-------------------------------------------------------------------------------------------------------------------------------------------
		 //Condition Starts here
		if(userRights.equalsIgnoreCase("admin")){
			 //Staff details------------and userMaster
			 System.out.println("in Admin Insertion");
				try {
					userMasterDao = (UserMasterDAO)ServiceFinder.getContext(request).getBean("UserMasterHibernateDao"); 		
						userMasterDao.save(userInputData);//---------------------ONE
						
						uniqueFileName = getFileUniqueName();
						staffData.setPhoto(uniqueFileName);
						staffData.setUmId(userInputData.getUmId());//?from above we need usertype ID//-----------------TWO
					staffDetailsDao = (StaffDetailsDAO)ServiceFinder.getContext(request).getBean("StaffDetailsHibernateDao");
						staffDetailsDao.save(staffData);
						
						//   File fileToCreate = new File(filePath, this.fileUPFileName);
						if (!uniqueFileName.equalsIgnoreCase("noImage.jpg")) {							
							fileToCreate = new File(filePath,uniqueFileName);// unique file name
							FileUtils.copyFile(this.fileUP, fileToCreate);
						}else{
							System.out.println("no image");
						}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					session.put("msg", "Failed");
					return "success";
				}
			 
		 }
			 session.put("userIDD", "AD"+userID);
			 session.put("passwordd", password);
			 session.put("msg", "Success");
			 
			/* request.setAttribute("userIDD", userID);
			 request.setAttribute("passwordd", password);*/
		 return "success";
		 }else{
			 session.put("msg", "Failed");
			 return "success";
		 }
	}
	
	private String getFileUniqueName() {
		String extension = "";
		if (getFileUPFileName() != null) {		
		
		int i = getFileUPFileName().lastIndexOf('.');
		if (i > 0) {
		    extension = getFileUPFileName().substring(i+1);
		}else{
			extension = "jpg";
		}		
		return String.format("%s.%s", RandomStringUtils.randomAlphanumeric(10), extension);
		}else{
			return  "noImage.jpg";
		}
		
	}
	
	private String getIdPassword(String userType,String fName,String lName,String imId) {
		
		if(userType != null){
			try {
				String userPass = "";
				String userID = null;
				
				StringBuffer uu = new StringBuffer();
				uu.append(userType);
				
				
				if (fName.length()>2) {
					uu.append(fName.substring(0, 3));
				}else{
					uu.append(fName);
				}
				
				if (lName.length()>2) {
					uu.append(lName.substring(0, 3));
				}else{
					uu.append(lName);
				}
				
				userID = uu+"";			
				
				UserMasterDAO userMasterDao = (UserMasterDAO)ServiceFinder.getContext(request).getBean("UserMasterHibernateDao"); 	
				System.out.println("userID--"+userID+"-imId-"+imId);
				List userlist = userMasterDao.findByPropertyList2("userId", userID,  "imId", imId);
				
				p:if (userlist.size()>0) {
					for (int k = 1; k < 100; k++) {
						 String modUserId = userID+k;
						int count =0;
						for (int i = 0; i < userlist.size(); i++) {
							UserMaster um = (UserMaster)userlist.get(i);
							if (modUserId.equalsIgnoreCase(um.getUserId())) {
								count++;
							}
						}
					if (count == 0) {
						userID = modUserId;
						break p;
					}
					
					}
				}
				
				//code to shuffle password
				StringBuffer stringBuffer=new StringBuffer();
				List<String> list=new ArrayList<String>();
				list.add("z");
				list.add("i");
				list.add("p");
				list.add("y");

				Collections.shuffle(list);  			  	
				Iterator<String> iterator		= list.iterator();	   		
				while(iterator.hasNext()){
					stringBuffer.append(iterator.next());
				}	   		
		  userPass							= stringBuffer.toString()+"1234";
		  return userID+"~"+userPass;
			} catch (BeansException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return null;
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return null;
			}	
		}else{
		return null;
  	  }
	}
	
	
	public String editNonAdminUserSave() throws Exception{
		// TODO Auto-generated method stub
		 System.out.println(session+"*****"+session.get("IM_SN")+"--"+session.get("IM_ID")+"---"+(String)session.get("userType")+"**isDefault*"+request.getParameter("isDefault"));
		if(session.get("IM_SN") != null && session.get("IM_ID")!=null && ((String)session.get("userType")).equalsIgnoreCase("AD") ){
			
	
	/*		if (fileUPFileName!= null) {
				session.put("msg","Please select File");
				return "success";			
		}*/
		
		 String filePath = request.getSession().getServletContext().getRealPath("/")+"uploaded/";
		 
		 String IM_SN =	(String)session.get("IM_SN");
		 String im_id =	(String)session.get("IM_ID");

		 
		 String userID 		=	(String)request.getParameter("userIDForEditAction");//queryString
		 String fileNameOld 		=	(String)request.getParameter("photoOLDEditAction");//queryString---queryString not working in appfog
		// String activeEdit 		=	(String)request.getParameter("activeEdit");//queryString

		 
		 String fname =	request.getParameter("fname");
		 String lname =	request.getParameter("lname");
		
		 String  userRights =	request.getParameter("userRights");//IMP---------------------------
		 
		 String userType = "";
		
		 String dob =	request.getParameter("dob");
		 String designation =	request.getParameter("designation");
		 String email =	request.getParameter("email");
		 String mobile =	request.getParameter("mobile");
		 String landline =	request.getParameter("landline");
		 
		 String isParent =	request.getParameter("isParent");
		 String parentType =	request.getParameter("parentTypeSel");
		 
		 String addr1 =	request.getParameter("addr1");
		 String addr2 =	request.getParameter("addr2");
		 String city =	request.getParameter("city");
		// String country =	request.getParameter("country");
		 String state =	request.getParameter("state");
		 String fileUP =	request.getParameter("fileUP");
		 String active =	request.getParameter("active");

		 String isDefault =	request.getParameter("isDefault") ;//new check..2013-08-16
		 if(isDefault == null || isDefault == "null"){
			 isDefault = "N";
		 }else{
			 isDefault = "Y";
		 }
		 
		 String admissionNumber =	request.getParameter("admissionNumber");
		 String admissionDate =	request.getParameter("admissionDate");
		 String classAdmittedIn =	request.getParameter("classAdmittedIn");
		 String gender =	request.getParameter("gender");
		if(admissionNumber == null){ 
			admissionNumber = "";
		}
		if(admissionDate == null){ 
			admissionDate= "0000-00-00";
		}
		if(classAdmittedIn == null){
			classAdmittedIn= "";
		}
		 
		 
		 if(active == null || active == "null"){
			 active = "N";
		 }else{
			 active = "Y";
		 }

		 if(isParent == null || isParent == "null"){
			 isParent = "N";
		 }else{
			 isParent = "Y";
		 }
	
		
		 System.out.println("fileNameOld--"+fileNameOld+"state::"+state+":fileUP:"+fileUP+"::userRights:"+userRights+"::isParent::"+isParent);
		 //userRights.equalsIgnoreCase("AD")
		 
		 //generate ID and Password
		 
		 
/*		 i.	For Staff
		 1.	Prefix SF to the Unique ID
		 ii.	For Teacher 
		 1.	Prefix TH to the Unique ID
		 iii.	For Non Teacher
		 1.	Prefix NTH to the Unique ID
		 iv.	For Students
		 1.	Prefix Stu to the Unique ID
		 v.	For Parents
		 1.	Prefix Par to the Unique ID*/

			 if(userRights.equalsIgnoreCase("PA")){
				userType = "2";//presently assigned manually,but later from db;
			}else if(userRights.equalsIgnoreCase("TC")){
				userType = "3";//presently assigned manually,but later from db;
			}else if(userRights.equalsIgnoreCase("NTS")){
				userType = "4";//presently assigned manually,but later from db;
			}else if(userRights.equalsIgnoreCase("ST")){
				userType = "5";//presently assigned manually,but later from db;
			}				 
		 
		 String uniqueFileName ="";
		 String returnFlag = "";
		 
		 UserMasterDAO userMasterDao =null;
		 StaffDetailsDAO staffDetailsDao = null;
		 ParentDetailsDAO parentDetailsDao = null;
		 StudentDetailsDAO studentDetailsDao = null;
		 ParentDetails parentData = null;
		 StaffDetails staffData =null;
		 StudentDetails studentData =null;
		 File fileToCreate = null;
		 
		 filePath = filePath+"/"+IM_SN+"/"+userRights+"/";
		 
			
			 if(userRights.equalsIgnoreCase("NTS")||userRights.equalsIgnoreCase("TC")){
				 
				 System.out.println("in NTS or TC data");
				 staffData = new StaffDetails();
				//	staffData.setActive(activeEdit);//no change for edit
				 	staffData.setSdId(Long.parseLong(userID));
					staffData.setAddress1(addr1);
					staffData.setAddress2(addr2);
					staffData.setCity(city);
					//staffData.setCountry(country);
					staffData.setDesignation(designation);
					staffData.setDob(dob);
					staffData.setEmail(email);
					staffData.setFirstName(fname);
					staffData.setLandline(landline);
					staffData.setLastName(lname);
					staffData.setGender(gender);
					staffData.setMobile(mobile);
					//staffData.setPhoto(fileUP);
					staffData.setState(state);//state ID
					returnFlag = "successSaff";
			 }
			if(userRights.equalsIgnoreCase("PA" ) ||  isParent.equalsIgnoreCase("Y")){//a parent may also TS or NTS
				System.out.println("in PA or Y data");
				 parentData = new ParentDetails();//15
				//staffData.setActive(activeEdit);//no change for edit
				parentData.setPdId(Long.parseLong(userID));
				parentData.setAddress1(addr1);
				parentData.setAddress2(addr2);
				parentData.setCity(city);
				parentData.setDob(dob);
				parentData.setEmail(email);
				parentData.setFirstName(fname);
				parentData.setLandline(landline);
				parentData.setLastName(lname);
				parentData.setGender(gender);
				parentData.setMobile(mobile);
				//parentData.setPhoto(fileUP);
				parentData.setState(state);//state ID
				parentData.setIsDefault(isDefault);
				if (parentType == null) {
					parentData.setPtmId("1");					
				} else {
					parentData.setPtmId(parentType);
				}
				
				returnFlag = "successPar";
			}
			if(userRights.equalsIgnoreCase("ST")){//2013-08-08...only this only taken...as considaration............so nothing for TC NTC
				System.out.println("in student data");
				studentData = new StudentDetails();
			//	staffData.setActive(activeEdit);//no change for edit
				studentData.setStuId(Long.parseLong(userID));
				studentData.setAddress1(addr1);
				studentData.setAddress2(addr2);
				studentData.setAdmissionDate(admissionDate);
				studentData.setAdmissionNumber(admissionNumber);
				studentData.setCity(city);
				studentData.setClassAdmittedIn(classAdmittedIn);
				studentData.setDob(dob);
				studentData.setEmail(email);
				studentData.setFirstName(fname);
				studentData.setGender(gender);
				studentData.setLandline(landline);
				studentData.setLastName(lname);
				studentData.setMobile(mobile);
				//studentData.setPhoto(fileUP);
				studentData.setState(state);
				returnFlag = "successStu";
			}
		//-------------------------------------------------------------------------------------------------------------------------------------------
			//studentData.setPhoto(fileUP);
			//staffData.setPhoto(fileUP);
			//parentData.setPhoto(fileUP);
		 
		 //Condition Starts here
			 if((userRights.equalsIgnoreCase("NTS")||userRights.equalsIgnoreCase("TC")) && isParent.equalsIgnoreCase("Y")){/*
			 
				 System.out.println("in NTS or TC and  Y Insertion");
				 try {
					userMasterDao = (UserMasterDAO)ServiceFinder.getContext(request).getBean("UserMasterHibernateDao"); 		
						userMasterDao.save(userInputData);//---------------------ONE
						uniqueFileName = getFileUniqueName();
						parentData.setPhoto(uniqueFileName);
						parentData.setUmId(userInputData.getUmId());
					parentDetailsDao = (ParentDetailsDAO)ServiceFinder.getContext(request).getBean("ParentDetailsHibernateDao");
						parentDetailsDao.save(parentData);
						
						staffData.setPhoto(uniqueFileName);
						staffData.setUmId(userInputData.getUmId());//?from above we need usertype ID//-----------------TWO
					staffDetailsDao = (StaffDetailsDAO)ServiceFinder.getContext(request).getBean("StaffDetailsHibernateDao");
						staffDetailsDao.save(staffData);
						
						//   File fileToCreate = new File(filePath, this.fileUPFileName);
						if (!uniqueFileName.equalsIgnoreCase("noImage.jpg")) {							
							fileToCreate = new File(filePath,uniqueFileName);// unique file name
							FileUtils.copyFile(this.fileUP, fileToCreate);
						}else{
							System.out.println("no image");
						}
						
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					session.put("nonadminMsg", "Failed");
					return returnFlag;
				}
			 
		 */}else  if(userRights.equalsIgnoreCase("TC")||userRights.equalsIgnoreCase("NTS")){

			 
			 System.out.println("in Staff Insertion");
			 
				try {
					if (fileUPFileName != null) {
						uniqueFileName = getFileUniqueName();
						staffData.setPhoto(uniqueFileName);
					}else{
						staffData.setPhoto(fileNameOld);
					}
					
					staffDetailsDao = (StaffDetailsDAO)ServiceFinder.getContext(request).getBean("StaffDetailsHibernateDao");
					String resultt = staffDetailsDao.multiTransEditStaffSave(staffData);
					
					   System.out.println("Server path:" + filePath+":ggggg::fileUPFileName"+fileUPFileName+"::fileUP:"+fileUP+"source file name:" + getFileUPFileName()+":::fileUPFileName"+getFileUP()+"::fileUP:"+getFileUPContentType());						 
					
						 if (resultt.equalsIgnoreCase("success") && fileUPFileName != null && !uniqueFileName.equalsIgnoreCase("noImage.jpg")) {
								fileToCreate = new File(filePath,uniqueFileName);// unique file name
								FileUtils.copyFile(this.fileUP, fileToCreate);								
						}
						 //here i am not using adding related session..only one session for edit success..
						 session.put("resStaffAfterEdit","Success");//2013-07-20...this is for show result ..after edit is success...		 
					   
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					session.put("resStaffAfterEdit", "Failed");
					return returnFlag;
				}
			 
		
		 }else if(userRights.equalsIgnoreCase("PA")){
			 
			 System.out.println("in Parent Insertion");
			 
				try {
					if (fileUPFileName != null) {
						uniqueFileName = getFileUniqueName();
						parentData.setPhoto(uniqueFileName);
					}else{
						parentData.setPhoto(fileNameOld);
					}
					
					parentDetailsDao = (ParentDetailsDAO)ServiceFinder.getContext(request).getBean("ParentDetailsHibernateDao");
					String resultt = parentDetailsDao.multiTransEditParentSave(parentData);
					
					   System.out.println("Server path:" + filePath+":ggggg::fileUPFileName"+fileUPFileName+"::fileUP:"+fileUP+"source file name:" + getFileUPFileName()+":::fileUPFileName"+getFileUP()+"::fileUP:"+getFileUPContentType());						 
					
						 if (resultt.equalsIgnoreCase("success") && fileUPFileName != null && !uniqueFileName.equalsIgnoreCase("noImage.jpg")) {
								fileToCreate = new File(filePath,uniqueFileName);// unique file name
								FileUtils.copyFile(this.fileUP, fileToCreate);								
						}
						 //here i am not using adding related session..only one session for edit success..
						 session.put("resParAfterEdit","Success");//2013-07-20...this is for show result ..after edit is success...		 
					   
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					session.put("resParAfterEdit", "Failed");
					return returnFlag;
				}
			 
		}else if (userRights.equalsIgnoreCase("ST")) {

			 System.out.println("in Stu Insertion");
			 
				try {
					if (fileUPFileName != null) {
						uniqueFileName = getFileUniqueName();
						studentData.setPhoto(uniqueFileName);
					}else{
						studentData.setPhoto(fileNameOld);
					}
					
					studentDetailsDao = (StudentDetailsDAO)ServiceFinder.getContext(request).getBean("StudentDetailsHibernateDao");
					String resultt = studentDetailsDao.multiTransEditStudentSave(studentData);
					
					   System.out.println("Server path:" + filePath+":::fileUPFileName"+fileUPFileName+"::fileUP:"+fileUP+"source file name:" + getFileUPFileName()+":::fileUPFileName"+getFileUP()+"::fileUP:"+getFileUPContentType());						 
					
						 if (resultt.equalsIgnoreCase("success") && fileUPFileName != null && !uniqueFileName.equalsIgnoreCase("noImage.jpg")) {
								fileToCreate = new File(filePath,uniqueFileName);// unique file name
								FileUtils.copyFile(this.fileUP, fileToCreate);								
						}
						 //here i am not using adding related session..only one session for edit success..
						 session.put("resStuAfterEdit","Success");//2013-07-20...this is for show result ..after edit is success...		 
					   
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					session.put("resStuAfterEdit", "Failed");
					return returnFlag;
				}
			 
		}
			
		 return returnFlag;
		 
		}else{
			System.out.println("in main else of AddUseraction--");
			return "success";
		}
		 
	}
	
	@Override
	public void setServletRequest(HttpServletRequest arg0) {
		// TODO Auto-generated method stub
		request = arg0;
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


	public void setSession(Map session1) {
		// TODO Auto-generated method stub
		System.out.println(session1+"session in add user edit--->"+session);
		session = session1;
		
	}
	private InstitutionMaster getinstitutionMasterDetails(Long im_id) {
		try {
			// TODO Auto-generated method stub
			InstitutionMasterDAO imMasterDao = null;
			InstitutionMaster institutionMasterData = new InstitutionMaster();
			imMasterDao = (InstitutionMasterDAO)ServiceFinder.getContext(request).getBean("InstitutionMasterHibernateDao"); 
			institutionMasterData = imMasterDao.findById(im_id);
			return institutionMasterData;
		} catch (BeansException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	
}
