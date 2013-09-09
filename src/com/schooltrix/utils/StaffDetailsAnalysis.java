package com.schooltrix.utils;

import java.sql.SQLException;
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
import org.springframework.orm.hibernate3.HibernateCallback;

import com.schooltrix.daos.ParentDetailsDAO;
import com.schooltrix.daos.StaffDetailsDAO;
import com.schooltrix.managers.ServiceFinder;

public class StaffDetailsAnalysis {

	HttpServletRequest request = null;
	HttpSession session;
	
	public StaffDetailsAnalysis(HttpServletRequest request){
		this.request = request;
	}
	
	public List getStaffRawList(String im_id,String[] schoolNames, String[] branchNames,
			String[] classNames, String[] sectionNames, String fname,
			String lname, String dob, String email, String mobile,
			String landline, String addr1, String addr2, String city,
			String state, String gender, String admissionNumber, String[] classAdmittedIn, String fromDate,
			String toDate, String active,int flag,String parentTypeSel,String isDefault,String desig,String isParentCheck,HttpSession session) {////flag will used for, diff from Search or Download.

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

		System.out.println(desig+"^^"+fname+"**"+lname+"$$"+  dob+"***"+  email+"$$$"+  mobile+"****"+ landline+"$$$$"+  addr1+"**"+  addr2+"$$"+  city+"***"+state+"$$$");  
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
			
			if(StringUtils.isBlank(desig)) {
				jObject.put("desig", desig);
				desig= "%";
			} else{
				jObject.put("desig", desig);
				desig= "%"+desig+"%";
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
				 
				 if (state.equalsIgnoreCase("-1")) {
					 state = "%";
					}
				 if (gender.equalsIgnoreCase("A")) {
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
			 if (isDefault != null && (isDefault.equalsIgnoreCase("on") || isDefault.equalsIgnoreCase("y"))) {
				 jObject.put("isDefault", "y");
				 isDefault = "Y";
			 } else{
				 jObject.put("isDefault", "n");
				 isDefault = "N";
			 }
			 if (isParentCheck != null && (isParentCheck.equalsIgnoreCase("on") || isParentCheck.equalsIgnoreCase("y"))) {
				 jObject.put("isParentCheck", "y");
				 isParentCheck = "Y";
			 } else{
				 jObject.put("isParentCheck", "n");
				 isParentCheck = "N";
			 }
			 
			 if (parentTypeSel.equalsIgnoreCase("-1")) {
				 jObject.put("parentTypeSel", parentTypeSel);//if req cross checking ptmid will do later ....bcz through firebug (or someing)pass other values also posible..
				 parentTypeSel= "%";
			} else {
				 jObject.put("parentTypeSel", parentTypeSel);
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
					 construtQueryStringForHistory(im_id,insmIds+"",inbmIds+"",
								fname,lname,dob,email,mobile,landline,addr1,addr2,city,state,gender,desig,admissionNumber,btCodt,isParentCheck,parentTypeSel,active,isDefault);
					 session.setAttribute("jsonForStaffSearch", jObject.toString());
				}
			 
		System.out.println(bm_ids+"<--Major"+insmIds+"***"+inbmIds+"****"+insecIds+"*****"+incmIds+"$$"+incmAdmIds);
		System.out.println(fname+"**"+lname+"$$"+  dob+"***"+  email+"$$$"+  mobile+"****"+ landline+"$$$$"+  addr1+"**"+  addr2+"$$"+  city+"***"+state+"$$$");  
		System.out.println(btCodt+"^^^"+gender+"****"+  admissionNumber+"$$$$"+"**"+ classAdmittedIn+"***"+  fromDate+"$$$"+ toDate+"&&&"+  active+"IMID"+im_id);
			  
			  
		}catch (Exception e) {
				e.printStackTrace();
		}
		
		//need parent chechk...can i show student data..how
		
		StaffDetailsDAO staffDetailsDAO= null;
		try {
			staffDetailsDAO = (StaffDetailsDAO)ServiceFinder.getContext(request).getBean("StaffDetailsHibernateDao");
			List parentDataList = new ArrayList();
			parentDataList  =	staffDetailsDAO.getStaffForEdit( im_id,insmIds+"",inbmIds+"",
					fname,lname,dob,email,mobile,landline,addr1,addr2,city,state,gender,desig,admissionNumber,btCodt,isParentCheck,parentTypeSel,active,isDefault);
			System.out.println(parentDataList .size()+"BEFOREin DWR");
			
			 /*  * String fname,			String lname, String dob, String email, String mobile,			String landline, String addr1, String addr2, String city,	
			  * 		String state, String gender, String admissionNumber,			String admissionDate, String classAdmittedIn, String fromDate,			String toDate, String active)* **/
		
			return parentDataList ;
		}catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	private void construtQueryStringForHistory(String im_id,String schools, String branches,
			String fname, String lname,
			String dob, String email, String mobile, String landline,
			String addr1, String addr2, String city, String state,
			String gender,String desig, String admissionNumber, String dateQString,
			String isParentCheck,String parentTypeSel,String active,String isDefault) {

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
		

		String smIDOp = smIDOpF;
		String bmIDOp = bmIDOpF;
		//removed if for isParentCHeck....
		String qu = null;
			qu = "select bm.bm_ID as bm_ID,bm.IM_ID as IM_ID,sm.Name as School_Name,bm.Branch_Name as Branch_Name,bm.Short_Name as Short_Name," +
					"sd.SD_ID as sdID, sd.UM_ID as umID,sd.First_Name as fName,sd.Last_Name as lName,sd.Designation as desig,sd.city as city,sd. Mobile as mobile ,sd.Email as email ,sd.Photo as photo " +
					"from staff_details sd inner join user_master um on sd.UM_ID=um.UM_ID inner join branch_master bm on um.bm_id = bm.bm_id  inner join  school_master sm on bm.SM_ID = sm.sm_id  " +
					"where um.BM_ID  "+bmIDOp+" ("+branches+")  and um.SM_ID "+smIDOp+" ("+schools+") and um.IM_ID in ("+im_id+") and  " +
					"sd.First_Name like '"+fname+"' and sd.Last_Name like '"+lname+"' and sd.Gender like '"+gender+"' and  sd.Address1 like '"+addr1+"'" +
					"and sd.Address2 like '"+addr2+"'  and sd.city like '"+city+"' and sd.state like '"+state+"' and sd.Designation like '"+desig+"' and sd.Email like '"+email+"' " +
							"and sd.Mobile like '"+mobile+"' and  sd.Landline like '"+landline+"' and sd.DOB like '"+dob+"' and sd.Active like '"+active+"' ";
			System.out.println("##-->"+qu);
			session.setAttribute("queryForStaffSearch", qu);			
		
	}

	
}
