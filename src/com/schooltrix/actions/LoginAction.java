package com.schooltrix.actions;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.SessionAware;
import org.hibernate.Session;
import org.springframework.beans.BeansException;

import com.opensymphony.xwork2.ActionSupport;
import com.schooltrix.daos.InstitutionMasterDAO;
import com.schooltrix.daos.ParentDetailsDAO;
import com.schooltrix.daos.StaffDetailsDAO;
import com.schooltrix.daos.StudentDetailsDAO;
import com.schooltrix.daos.UserMasterDAO;
import com.schooltrix.daos.UserTypeMasterDAO;
import com.schooltrix.hibernate.InstitutionMaster;
import com.schooltrix.hibernate.ParentDetails;
import com.schooltrix.hibernate.SectionClassMap;
import com.schooltrix.hibernate.StaffDetails;
import com.schooltrix.hibernate.StudentDetails;
import com.schooltrix.hibernate.UserMaster;
import com.schooltrix.hibernate.UserType;
import com.schooltrix.managers.ServiceFinder;
import com.schooltrix.utils.StudentDetailsAnalysis;


public class LoginAction extends ActionSupport implements SessionAware,ServletRequestAware{
	 private org.apache.log4j.Logger log = Logger.getLogger(LoginAction.class);
	private String username,password;
	Map<String,String> session;
	private String userType;
	InstitutionMaster institutionMasterData = null;
	HttpServletRequest request = null;
	
	public String execute()  throws Exception {
		// TODO Auto-generated method stub
		System.out.println("user--"+username+"pass--::"+password);
		System.out.println("superadminHome--"+session.get("superadminHome")+"--short_name--"+session.get("short_name"));
		
		long userTypeID = 0; 
		String userTypeString = "";
		String UserID = "";
		String hibernateDaoType = "";
		
	
		
		//UserMasterDAO userMasterDao = null;
		if(username != null && password != null && username != "" && password !="" && username != "null" && password != "null"
			&& (session.get("short_name") != null || session.get("superadminHome") !=null)
			&& (session.get("short_name") != "null" || session.get("superadminHome") !="null")){
			
			try {
				//get user details
				UserMasterDAO userMasterDao = null;
				UserTypeMasterDAO userTypeMasterDao = null;
				UserMaster userMaster = null;
				UserType userType = null;
				ParentDetailsDAO parentDetailsDao= null;
				StaffDetailsDAO staffDetailsDao= null;
				StudentDetailsDAO studentDetailsDao= null;
				
				ParentDetails parentDetails = null;
				StaffDetails staffDetails = null;
				StudentDetails studentDetails = null;
				
				if (session.get("short_name") != "null" && session.get("short_name") != null) {
				//USER Mater Details based on userName and Password
				
					getinstitutionMasterDetails(session.get("short_name"));
				
				System.out.println("ins--ImId()--"+institutionMasterData.getImId()+"---"+session.get("short_name"));
				
				userMasterDao = (UserMasterDAO)ServiceFinder.getContext(request).getBean("UserMasterHibernateDao");
				userMaster = userMasterDao.findByProperty3("userId", username, "password", password,"imId",institutionMasterData.getImId());
				
				
				}else if(session.get("superadminHome") !="null" && session.get("superadminHome") !=null){
				userMasterDao = (UserMasterDAO)ServiceFinder.getContext(request).getBean("UserMasterHibernateDao");
				userMaster = userMasterDao.findByProperty("userId", username, "password", password);
				
				//getinstitutionMasterDetails not requ..here
				}
				
				System.out.println("userrrrr master in--non super admin--"+userMaster);
				if (userMaster != null) {
					
					try {
						userTypeID = userMaster.getUtId();		
						
						if(userTypeID > 0){
							//UserTypeMaster details based on utID in UserMaster..so we get userTypeString(like PA,TC,NTS,ST..)		
							userTypeMasterDao = (UserTypeMasterDAO)ServiceFinder.getContext(request).getBean("UserTypeMasterHibernateDao");
							userType = userTypeMasterDao.findById(userTypeID);
							userTypeString = userType.getUtCode();
						}
						
						if (userTypeString.equalsIgnoreCase("SA") && session.get("superadminHome").equalsIgnoreCase("Y")) {
								
							session.put("UM_ID", userMaster.getUmId()+"");
							session.put("UT_ID", userMaster.getUtId()+"");
							
							session.put("userType", userTypeString);
							
							//Special to super admin
							session.put("superadmin", "Y");						
								//actually there from db..
							session.put("fName", "Ravi");
							session.put("lName", "Kumar");
							session.put("active", "Y");
							return	"SA_createInst";		
						}
						
						
						//getting Details based on userID from UserMaster..
						if(userTypeString.equalsIgnoreCase("PA")){
							
							hibernateDaoType = "ParentDetailsHibernateDao";
							parentDetailsDao = (ParentDetailsDAO)ServiceFinder.getContext(request).getBean("ParentDetailsHibernateDao");
							parentDetails = parentDetailsDao.findByProperty("umId", userMaster.getUmId());
							
							session.put("IM_ID", userMaster.getImId()+"");
							session.put("SM_ID", userMaster.getSmId()+"");
							session.put("BM_ID", userMaster.getBmId()+"");
							session.put("UM_ID", userMaster.getUmId()+"");
							session.put("UT_ID", userMaster.getUtId()+"");
							
							
							session.put("userType", userTypeString);
							

							// getinstitutionMasterDetails(userMaster.getImId());
							 
							 
							if(institutionMasterData != null){
							session.put("name", institutionMasterData.getName());
							 session.put("IM_SN", institutionMasterData.getShortName());
							}else{
								session.put("IM_SN", session.get("short_name"));						
							}
							session.put("fName", parentDetails.getFirstName());
							session.put("lName", parentDetails.getLastName());
							session.put("active", parentDetails.getActive());
							session.put("email", parentDetails.getEmail());
							session.put("pdID", parentDetails.getPdId()+"");
						
							session.put("city", parentDetails.getCity());
							session.put("stateID", parentDetails.getState());
							/*Start
							 * this code added after some prob dwr...setting session in NotificationDWR....2013-05-19*/
							StudentDetails studentDetails1 = null;
							//String classID = null;
							SectionClassMap sectionClassMap = null;
							
							
							StudentDetailsAnalysis studentDetailsAnalysis = new StudentDetailsAnalysis(request);
							studentDetails1 = studentDetailsAnalysis.getStudentDetails(parentDetails.getPdId()+"", userMaster.getBmId()+"");
						
							if (studentDetails1 != null) {
								//classID = studentDetails.getClassAdmittedIn();
								sectionClassMap  =  studentDetailsAnalysis.getCurrentClassID(studentDetails1.getStuId()+"",userMaster.getBmId()+"");//return may null
								
								/*these session usefull total parent dashboard .... so for time being .. ...
								 * these session removed after logout only...
								 * Assignmenet ..and remain things use these session to get thier respected values... 
								 * */
								System.out.println(sectionClassMap.getCmId()+"*classID*"+studentDetails1.getStuId());
								
								session.put("StuID", studentDetails1.getStuId()+"");
								session.put("ClassID", sectionClassMap.getCmId());
								
							/*END*/
							}
							
							
							//return "";	yet to decided 
							return "parentViewTemp";
						}else if((userTypeString.equalsIgnoreCase("AD"))){
							//i think here seperation is req for AD and other TC,NTS(but present nothing is there for TC,NTS)
											
							
							hibernateDaoType = "StaffDetailsHibernateDao";
							staffDetailsDao = (StaffDetailsDAO)ServiceFinder.getContext(request).getBean("StaffDetailsHibernateDao");
							System.out.println("userMaster-------------"+ userMaster.getUmId());
							staffDetails = staffDetailsDao.findByProperty("umId", userMaster.getUmId());
						
							
							session.put("IM_ID", userMaster.getImId()+"");
							session.put("SM_ID", userMaster.getSmId()+"");
							session.put("BM_ID", userMaster.getBmId()+"");
							session.put("UM_ID", userMaster.getUmId()+"");
							session.put("UT_ID", userMaster.getUtId()+"");

							session.put("userType", userTypeString);
							
							System.out.println("in AD_______"+institutionMasterData.getName());
							//getinstitutionMasterDetails(userMaster.getImId());
								if(institutionMasterData != null){
									
									session.put("name", institutionMasterData.getName());
								 session.put("IM_SN", institutionMasterData.getShortName());
								}else{
									session.put("IM_SN", "");						
								}
							
							session.put("fName", staffDetails.getFirstName());
							session.put("lName", staffDetails.getLastName());
							session.put("active", staffDetails.getActive());
							session.put("email", staffDetails.getEmail());
							session.put("pdID", staffDetails.getSdId()+"");
							
							session.put("city", staffDetails.getCity());
							session.put("stateID", staffDetails.getState());
							
							
							
							//return "";	yet to decided
							return "AddSchool";
						}else if((userTypeString.equalsIgnoreCase("TC")) || (userTypeString.equalsIgnoreCase("NTS"))){
							//i think here seperation is req for AD and other TC,NTS(but present nothing is there for TC,NTS)
											
							
							hibernateDaoType = "StaffDetailsHibernateDao";
							staffDetailsDao = (StaffDetailsDAO)ServiceFinder.getContext(request).getBean("StaffDetailsHibernateDao");
							System.out.println("userMaster-------------"+ userMaster.getUmId());
							staffDetails = staffDetailsDao.findByProperty("umId", userMaster.getUmId());
						
							
							session.put("IM_ID", userMaster.getImId()+"");
							session.put("SM_ID", userMaster.getSmId()+"");
							session.put("BM_ID", userMaster.getBmId()+"");
							session.put("UM_ID", userMaster.getUmId()+"");
							session.put("UT_ID", userMaster.getUtId()+"");

							session.put("userType", userTypeString);
							
							System.out.println("in AD_______"+institutionMasterData.getName());
							//getinstitutionMasterDetails(userMaster.getImId());
								if(institutionMasterData != null){
									
									session.put("name", institutionMasterData.getName());
								 session.put("IM_SN", institutionMasterData.getShortName());
								}else{
									session.put("IM_SN", "");						
								}
							
							session.put("fName", staffDetails.getFirstName());
							session.put("lName", staffDetails.getLastName());
							session.put("active", staffDetails.getActive());
							session.put("email", staffDetails.getEmail());
							session.put("staffID", staffDetails.getSdId()+"");
							
							session.put("city", staffDetails.getCity());
							session.put("stateID", staffDetails.getState());
							
							
							
							//return "";	yet to decided
							return "StaffViewTemp";
						}else if(userTypeString.equalsIgnoreCase("ST")){
							
							hibernateDaoType = "StudentDetailsHibernateDao";
							studentDetailsDao = (StudentDetailsDAO)ServiceFinder.getContext(request).getBean("StudentDetailsHibernateDao");
							studentDetails = studentDetailsDao.findByProperty("umId", userMaster.getUmId()+"");
							
							session.put("IM_ID", userMaster.getImId()+"");
							session.put("SM_ID", userMaster.getSmId()+"");
							session.put("BM_ID", userMaster.getBmId()+"");
							session.put("UM_ID", userMaster.getUmId()+"");
							session.put("UT_ID", userMaster.getUtId()+"");

							session.put("userType", userTypeString);
							
							 //getinstitutionMasterDetails(userMaster.getImId());
								if(institutionMasterData != null){
									session.put("name", institutionMasterData.getName());
								 session.put("IM_SN", institutionMasterData.getShortName());
								}else{
									session.put("IM_SN", "");						
								}
							
							session.put("fName", studentDetails.getFirstName());
							session.put("lName", studentDetails.getLastName());
							session.put("active", studentDetails.getActive());
							session.put("email", studentDetails.getEmail());
							session.put("StuID", studentDetails.getStuId()+"");
							SectionClassMap sectionClassMap = null;
							StudentDetailsAnalysis studentDetailsAnalysis = new StudentDetailsAnalysis(request);
							
							sectionClassMap  =  studentDetailsAnalysis.getCurrentClassID(studentDetails.getStuId()+"",userMaster.getBmId()+"");//return may null
							session.put("ClassID", sectionClassMap.getCmId());
							
							
							session.put("city", studentDetails.getCity());
							session.put("stateID", studentDetails.getState());
							//return "";	yet to decided 
							return "studentViewTemp";
						}
					} catch (Exception e) {
						//session.put("whylogin","Login failed. Try again");
						session.clear();
						session.put("whylogin","Login failed. You are not belongs to this URL, please check once before you try again.");
						System.out.println("in WR username pass-fffffffffffff--Login Failed.try Again fail");
						e.printStackTrace();
						return "fails";					
					}
					
				
				
				
				}//userMaster != null
				else{
					session.put("whylogin","Login failed. Try again");
					System.out.println("in WR username pass---Login Failed.try Again fail");
					return "fails";
				}
				
			} catch (Exception e) {//major try catch...
				System.out.println("EXptionnnnnnnnnn");
				e.printStackTrace();
			}
			
		}else{//username & password != null;
			System.out.println("in pass & user are  emptyyy  wrong fail");
			session.put("whylogin","Login failed. Try again");
			return "fails";
			
			
		}
	
		session.put("whylogin","Login failed. Try again");
		System.out.println("in pass emtyyyyy---& user wrong fail");
		return "fails";

	}
	
	
	private InstitutionMaster getinstitutionMasterDetails(String shortName) {
		try {
			// TODO Auto-generated method stub
			InstitutionMasterDAO imMasterDao = null;
			institutionMasterData = new InstitutionMaster();
			imMasterDao = (InstitutionMasterDAO)ServiceFinder.getContext(request).getBean("InstitutionMasterHibernateDao"); 
			institutionMasterData = imMasterDao.findByProperty("shortName", shortName);
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
	
	
	public void setSession(Map session) {
		// TODO Auto-generated method stub
		 this.session=session;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}


	@Override
	public void setServletRequest(HttpServletRequest request) {
		// TODO Auto-generated method stub
		this.request=  request;
	}




}