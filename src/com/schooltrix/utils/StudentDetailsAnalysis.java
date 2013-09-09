package com.schooltrix.utils;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.type.StringType;
import org.json.JSONObject;
import org.springframework.beans.BeansException;
import org.springframework.orm.hibernate3.HibernateCallback;

import com.schooltrix.daos.BranchMasterDAO;
import com.schooltrix.daos.ClassMasterDAO;
import com.schooltrix.daos.SectionMasterDAO;
import com.schooltrix.daos.StudentDetailsDAO;
import com.schooltrix.daos.UserMasterDAO;
import com.schooltrix.hibernate.ClassMaster;
import com.schooltrix.hibernate.ParentDetails;
import com.schooltrix.hibernate.ParentStudentMap;
import com.schooltrix.hibernate.SectionClassMap;
import com.schooltrix.hibernate.StudentDetails;
import com.schooltrix.hibernate.StudentSectionMap;
import com.schooltrix.hibernate.UserMaster;
import com.schooltrix.managers.ServiceFinder;

public class StudentDetailsAnalysis {

	StudentDetailsDAO studentDetailsDao= null;
	HttpServletRequest request = null;
	HttpSession session;
	
	public StudentDetailsAnalysis() {
		// TODO Auto-generated constructor stub
	}
	
	public StudentDetailsAnalysis(HttpServletRequest request){
		this.request = request;
	}
	
	
	public StudentDetails getStudentDetails(String pd_id,String bm_id){
		
		ParentDetails parentDetails = null;
		StudentDetails studentDetails = null;
		
		ParentStudentMap parentStudentMap = null;
		
		try {
			studentDetailsDao = (StudentDetailsDAO)ServiceFinder.getContext(request).getBean("StudentDetailsHibernateDao");
			
			parentStudentMap  = studentDetailsDao.getStudentParentMap("pdId", pd_id);
			String stuID = parentStudentMap.getStuId();
			System.out.println(stuID+"*stuID***************pdID*"+pd_id);
			if (stuID != null || stuID != "") {
				
				studentDetails = studentDetailsDao.findById(Long.parseLong(stuID));
				System.out.println(studentDetails.getUmId()+"*&*&*&");
				return studentDetails;
			}
		} catch (BeansException e) {
			e.printStackTrace();
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public StudentDetails getStudentDetailsFromStudentDash(String stu_ID,String bm_id){
		
		StudentDetails studentDetails = null;
		
		try {
			studentDetailsDao = (StudentDetailsDAO)ServiceFinder.getContext(request).getBean("StudentDetailsHibernateDao");
			if (stu_ID != null || stu_ID != "") {
				
				studentDetails = studentDetailsDao.findById(Long.parseLong(stu_ID));
				System.out.println(studentDetails.getUmId()+"*&*&*&");
				return studentDetails;
			}
		} catch (BeansException e) {
			e.printStackTrace();
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	
	public SectionClassMap getCurrentClassID(String stuId, String bM_ID) {
		// TODO Auto-generated method stub
		StudentSectionMap studentSectionMap 	= null;
		SectionMasterDAO sectionMasterDAO 	= null;
		SectionClassMap sectionClassMap 			= null;
		try {
			studentDetailsDao 	= (StudentDetailsDAO)ServiceFinder.getContext(request).getBean("StudentDetailsHibernateDao");
			studentSectionMap 	= studentDetailsDao.getStudentSectionMap("stuId", stuId);
			
			if(studentSectionMap.getScmId() != null){
			sectionMasterDAO 	= (SectionMasterDAO)ServiceFinder.getContext(request).getBean("SectionMasterHibernateDao");
			
			sectionClassMap = sectionMasterDAO.getSectionClassMap("scmId", Long.parseLong(studentSectionMap.getScmId()));	
			return sectionClassMap;			
			}
		} catch (BeansException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public ClassMaster getClassName(String classID) {
		// TODO Auto-generated method stub
		ClassMaster classMaster = null;
		ClassMasterDAO classMasterDAO = null;
		try {
			if(classID != null){
				classMasterDAO 	= (ClassMasterDAO)ServiceFinder.getContext(request).getBean("ClassMasterHibernateDao");
				classMaster = classMasterDAO.findById(Long.parseLong(classID));	
				return classMaster;			
			}
		} catch (BeansException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	public UserMaster getUserMaster(String UMID) {
		// TODO Auto-generated method stub
		UserMaster userMaster = null;
		UserMasterDAO userMasterDAO = null;
		try {		
			if(UMID != null){
				userMasterDAO 	= (UserMasterDAO)ServiceFinder.getContext(request).getBean("UserMasterHibernateDao");				
				userMaster = userMasterDAO.getUserMasterFieldData("umId",UMID,1);	
				return userMaster;			
			}
		} catch (BeansException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public List getStudentRawList(String im_id,String[] schoolNames, String[] branchNames,
			String[] classNames, String[] sectionNames, String fname,
			String lname, String dob, String email, String mobile,
			String landline, String addr1, String addr2, String city,
			String state, String gender, String admissionNumber, String[] classAdmittedIn, String fromDate,
			String toDate, String active,int flag,HttpSession session) {////flag will used for, diff from Search or Download.

		SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
		this.session = session;
		
		StringBuffer insmIds	 	= new StringBuffer();
		StringBuffer inbmIds	 	= new StringBuffer();
		StringBuffer incmIds	 	= new StringBuffer();
		StringBuffer incmAdmIds	 	= new StringBuffer();
		StringBuffer insecIds 		= new StringBuffer();
		
		String smIDs = null;
		String bmIDs = null;
		String cmIDs = null;
		String cmAdmIDs = null;
		String secIDs = null;
		String btCodt = "";

		System.out.println(fname+"**"+lname+"$$"+  dob+"***"+  email+"$$$"+  mobile+"****"+ landline+"$$$$"+  addr1+"**"+  addr2+"$$"+  city+"***"+state+"$$$");  
		System.out.println(btCodt+"^^^"+gender+"****"+  admissionNumber+"$$$$"+"**"+ classAdmittedIn+"***"+  fromDate+"$$$"+ toDate+"&&&"+  active+"IMID"+im_id);
	
		StringBuffer bm_ids = new StringBuffer();
		JSONObject jObject = new JSONObject();
		
		try {
			  if (schoolNames != null) {
				  for (int i = 0; i < schoolNames.length; i++) {
					  insmIds.append(schoolNames[i]);
							if(i<schoolNames.length-1)
								insmIds.append(",");		
					 }
				  
			  }else{
				  insmIds.append( "'%'");
			  }
			  
				jObject.put("sm_ids", insmIds.toString().replaceAll("'", ""));
			  
			  if (branchNames != null) {
				  for (int i = 0; i < branchNames.length; i++) {
					  inbmIds.append(branchNames[i]);
							if(i<branchNames.length-1)
								inbmIds.append(",");		
					 }
				  
			  }else{
				  inbmIds.append( "'%'");
			  }
			  
			  jObject.put("bm_ids", inbmIds.toString().replaceAll("'", ""));
			  
			  if (classNames != null) {
				  for (int i = 0; i < classNames.length; i++) {
					  incmIds.append(classNames[i]);
					  if(i<classNames.length-1)
						  incmIds.append(",");		
				  }
				  
			  }else{
				  incmIds.append( "'%'");
			  }
			  
			  jObject.put("cm_ids", incmIds.toString().replaceAll("'", ""));
			  
			  if (classAdmittedIn != null) {
				  for (int i = 0; i < classAdmittedIn.length; i++) {
					  incmAdmIds.append(classAdmittedIn[i]);
					  if(i<classAdmittedIn.length-1)
						  incmAdmIds.append(",");		
				  }
				  
			  }else{
				  incmAdmIds.append( "'%'");
			  }
			  jObject.put("cam_ids", incmAdmIds.toString().replaceAll("'", ""));
			  
			  if (sectionNames != null) {
				  for (int i = 0; i < sectionNames.length; i++) {
					  insecIds.append(sectionNames[i]);
					  if(i<sectionNames.length-1)
						  insecIds.append(",");		
				  }				  
			  }else{
				  insecIds.append( "'%'");
			  }
			
			  jObject.put("sec_ids", insecIds.toString().replaceAll("'", ""));
		//Part 2---Fields..temp
			if(StringUtils.isBlank(fname)) {
					 jObject.put("fname", fname);
					 fname = "%";
				 } else{
					 jObject.put("fname", fname);
					 fname = "%"+fname+"%";
				 }
			
			if(StringUtils.isBlank(lname)) {
					 jObject.put("lname", lname);
					 lname = "%";
				 } else{
					 jObject.put("lname", lname);
					 lname = "%"+lname+"%";
				 }
			
			if(StringUtils.isBlank(dob)) {
					 jObject.put("dob", dob);
					 dob = "%";
				 } else{
					 jObject.put("dob", dob);
					 dob = "%"+dob+"%";
				 }
			
			if(StringUtils.isBlank(email)) {
					 jObject.put("email", email);
					 email= "%";
				 } else{
					 jObject.put("email", email);
					 email= "%"+email+"%";
				 }
			
			if(StringUtils.isBlank(mobile)) {
						 jObject.put("mobile", mobile);
						 mobile= "%";
					 } else{
						 jObject.put("mobile", mobile);
						 mobile= "%"+mobile+"%";
					 }
		
			if(StringUtils.isBlank(landline)) {
						 jObject.put("landline", landline);
						 landline= "%";
					} else{
						jObject.put("landline", landline);
						landline= "%"+landline+"%";
					}
			
			if(StringUtils.isBlank(addr1)) {
						 jObject.put("addr1", addr1);
						 addr1= "%";
					 } else{
						 jObject.put("addr1", addr1);
						 addr1= "%"+addr1+"%";
					 }
		
			if(StringUtils.isBlank(addr2)) {
						 jObject.put("addr2", addr2);
						 addr2= "%";
					 } else{
						 jObject.put("addr2", addr2);
						 addr2= "%"+addr2+"%";
					 }
			
			if(StringUtils.isBlank(city)) {
						 jObject.put("city", city);
						 city= "%";
					 } else{
						 jObject.put("city", city);
						 city= "%"+city+"%";
					 }
				
				if(StringUtils.isBlank(admissionNumber)) {
						 jObject.put("admissionNumber", admissionNumber);
						 admissionNumber= "%";
					 } else{
						 jObject.put("admissionNumber", admissionNumber);
						 admissionNumber= "%"+admissionNumber+"%";
					 }
			
				 jObject.put("state", state);
				 jObject.put("gender", gender);
				 
				 if (StringUtils.isBlank(state) || state.equalsIgnoreCase("-1")) {
					 state = "%";
					}
				 if (StringUtils.isBlank(gender) ||gender.equalsIgnoreCase("A")) {
					 gender = "%";
					}
			
			 /*  * String fname,			String lname, String dob, String email, String mobile,			String landline, String addr1, String addr2, String city,	
			  * 		String state, String gender, String admissionNumber,			String admissionDate, String classAdmittedIn, String fromDate,			String toDate, String active)* **/
			 if (active != null && (active.equalsIgnoreCase("on") || active.equalsIgnoreCase("y"))) {
				 jObject.put("active", "y");
				 active = "Y";
			 } else{
				 jObject.put("active", "n");
				 active = "N";
			 }
			 
			//Admis Date  Manipulation
			String toDateNew = null;
			String fromDateNew = null;
			try {
					if (StringUtils.isNotBlank(fromDate)) {
						 fromDateNew = sdf2.format(sdf2.parse(fromDate));
						 jObject.put("fromdate", fromDateNew);
					}else{
						fromDateNew = null;
						jObject.put("fromdate", "");
					}
			} catch (Exception e1) {
				fromDateNew = null;
				jObject.put("fromdate", "");
			}	
				try {
					if (StringUtils.isNotBlank(toDate)) {
						 toDateNew = sdf2.format(sdf2.parse(toDate));
						 jObject.put("todate", toDateNew);
					}else{
						toDateNew = null;
						jObject.put("todate", "");
					}
				} catch (Exception e1) {
					toDateNew = null;
					jObject.put("todate", "");
				}	
				
			//order imp
				if (fromDateNew==null && toDateNew==null) {
					btCodt = "'%' and now()";
				} else if(toDateNew == null){
					btCodt = "'"+fromDateNew+"' and '"+fromDateNew+"'";
					//btCodt = "'"+sdf2.format(sdf2.parse(fromDate))+"' and '"+addOneDay(fromDate)+"'";
				} else if(fromDateNew == null){
					btCodt = "'"+toDateNew+"' and '"+toDateNew+"'";
				}else if (fromDateNew!=null && toDateNew!=null) {
					btCodt = "'"+fromDateNew+"' and '"+toDateNew+"'";			
				}
				
				if (flag == 1) {
					 construtQueryStringForHistory(im_id,insmIds+"",inbmIds+"",incmIds+"",insecIds+"",fname,lname,dob,email,mobile,landline,addr1,addr2,city,state,gender,admissionNumber,btCodt,incmAdmIds+"",active);
					 session.setAttribute("jsonForStudentSearch", jObject.toString());
				}
			 
		System.out.println(bm_ids+"<--Major"+insmIds+"***"+inbmIds+"****"+insecIds+"*****"+incmIds+"$$"+incmAdmIds);
		System.out.println(fname+"**"+lname+"$$"+  dob+"***"+  email+"$$$"+  mobile+"****"+ landline+"$$$$"+  addr1+"**"+  addr2+"$$"+  city+"***"+state+"$$$");  
		System.out.println(btCodt+"^^^"+gender+"****"+  admissionNumber+"$$$$"+"**"+ classAdmittedIn+"***"+  fromDate+"$$$"+ toDate+"&&&"+  active+"IMID"+im_id);
			  
			  
		}catch (Exception e) {
				e.printStackTrace();
		}
		StudentDetailsDAO studentDetailsDAO= null;
		try {
			studentDetailsDAO = (StudentDetailsDAO)ServiceFinder.getContext(request).getBean("StudentDetailsHibernateDao");
			List studentDataList = new ArrayList();
			studentDataList =	studentDetailsDAO.getStudentForEdit( im_id,insmIds+"",inbmIds+"",incmIds+"",insecIds+"",
					fname,lname,dob,email,mobile,landline,addr1,addr2,city,state,gender,admissionNumber,btCodt,incmAdmIds+"",active);
		
			System.out.println(studentDataList.size()+"BEFOREin DWR");
			
			 /*  * String fname,			String lname, String dob, String email, String mobile,			String landline, String addr1, String addr2, String city,	
			  * 		String state, String gender, String admissionNumber,			String admissionDate, String classAdmittedIn, String fromDate,			String toDate, String active)* **/
		
			return studentDataList;
		}catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	private void construtQueryStringForHistory( String im_id, String schools,  String branches,
			 String classes,  String sections,  String fname,  String lname,
			 String dob,  String email,  String mobile,  String landline,
			 String addr1,  String addr2,  String city,  String state,
			 String gender,  String admissionNumber,  String dateQString,
			 String classAdmittedIn, String active) {
		

		
		String smIDOpF = null;
		String bmIDOpF = null;
		String cmIDOpF = null;
		String cmAdmIDOpF = null;
		String secIDOpF = null;		
		
		if (schools.replace("'", "").length()>1) {//this logic for identifying %
			smIDOpF = "in";			
		}else {
			smIDOpF = "like";			
		}
		
		if (branches.replace("'", "").length()>1) {//this logic for identifying %
			bmIDOpF = "in";			
		}else {
			bmIDOpF = "like";			
		}
		
		if (classes.replace("'", "").length()>1) {//this logic for identifying %
			cmIDOpF = "in";			
		}else {
			cmIDOpF = "like";			
		}
		if (classAdmittedIn.replace("'", "").length()>1) {//this logic for identifying %
			cmAdmIDOpF = "in";			
		}else {
			cmAdmIDOpF = "like";			
		}
		
		if (sections.replace("'", "").length()>1) {//this logic for identifying %
			secIDOpF = "in";			
		}else {
			secIDOpF = "like";			
		}

		final String smIDOp = smIDOpF;
		final String bmIDOp = bmIDOpF;
		final String cmIDOp = cmIDOpF;
		final String cmAdmIDOp = cmAdmIDOpF;
		final String secIDOp = secIDOpF;
		
		try {
							//14+10=24 fields
							String qu = "select sd.Stu_ID as stuID,sd.First_Name as firstName,sd.Last_Name as lastName,sd.Gender as gender,sd.Address1 as addr1,sd.Address2 as addr2,sd.City as city,sd.State as state,sd.DOB as dob,sd.Photo as photo,sd.Admission_Number as admissionNumber,sd.Admission_Date as admissionDate,sd.Class_Admitted_in as classAdmittedIN,sd.Active as active, " +
									"sm.SeM_ID as secID ,sm.Section_Name as secName,cm.CM_ID as cmID,cm.Class_Name as className,bm.BM_ID as bmID ,bm.Branch_Name as branchName,bm.Short_Name as bShortName,schoolm.SM_ID as smID,schoolm.School_Name as schoolName,	schoolm.Name as sShortName " +
									" from student_details sd inner join student_section_map ssm on sd.Stu_ID=ssm.Stu_ID inner join section_class_map scm on ssm.SCM_ID=scm.SCM_ID " +
									" inner join section_master sm on scm.SeM_ID= sm.SeM_ID inner join class_master cm on scm.CM_ID=cm.CM_ID inner join branch_master bm on ssm.bm_id=bm.bm_id inner join school_master schoolm on ssm.sm_id=schoolm.sm_id "+
									" where scm.SeM_ID "+secIDOp+" ("+sections+") and scm.CM_ID "+cmIDOp+" ("+classes+")  and ssm.BM_ID  "+bmIDOp+" ("+branches+")  and ssm.SM_ID "+smIDOp+" ("+schools+") and ssm.IM_ID in ("+im_id+") and  " +
									" sd.First_Name	 like '"+fname+"' and sd.Last_Name like '"+lname+"' and sd.Gender	 like  '"+gender+"' and sd.Address1	 like  '"+addr1+"' and sd.Address2	 like  '"+addr2+"' " +
									" and sd.City like  '"+city+"' and sd.State	 like  '"+state+"' and sd.Email	 like  '"+email+"' and sd.Mobile	 like  '"+mobile+"' and sd.Landline	 like  '"+landline+"' and sd.DOB	 like  '"+dob+"'  " +
									" and sd.Admission_Number	 like  '"+admissionNumber+"'  and  sd.Admission_Date	between "+dateQString+"  and sd.Class_Admitted_in  "+cmAdmIDOp+" ("+classAdmittedIn+")  and sd.Active like  '"+active+"'  " +
									"and sm.active='y' and cm.active='y'  and bm.active='y'  and schoolm.active='y'   order by ssm.SM_ID desc";							
			
							session.setAttribute("queryForStudentSearch", qu);			
		} catch (Exception e) {
		e.printStackTrace();
		}
		
	}
	
	
}
