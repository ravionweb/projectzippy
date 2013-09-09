package com.schooltrix.utils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.commons.lang.StringUtils;
import org.json.JSONObject;
import com.schooltrix.daos.ParentDetailsDAO;
import com.schooltrix.daos.StudentDetailsDAO;
import com.schooltrix.managers.ServiceFinder;

public class ParentDetailsAnalysis {

	StudentDetailsDAO studentDetailsDao= null;
	HttpServletRequest request = null;
	HttpSession session;
	
	public ParentDetailsAnalysis() {
		// TODO Auto-generated constructor stub
	}
	
	public ParentDetailsAnalysis(HttpServletRequest request){
		this.request = request;
	}

	public List getParentsRawList(String im_id,String[] schoolNames, String[] branchNames,
			String[] classNames, String[] sectionNames, String fname,
			String lname, String dob, String email, String mobile,
			String landline, String addr1, String addr2, String city,
			String state, String gender, String admissionNumber, String[] classAdmittedIn, String fromDate,
			String toDate, String active,int flag,String parentTypeSel,String isDefault,HttpSession session) {////flag will used for, diff from Search or Download.

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
					 construtQueryStringForHistory(im_id,insmIds+"",inbmIds+"",incmIds+"",insecIds+"",fname,lname,dob,email,mobile,landline,addr1,addr2,city,state,gender,
							 admissionNumber,btCodt,incmAdmIds+"",active,parentTypeSel,isDefault);
					 session.setAttribute("jsonForParentSearch", jObject.toString());
				}
			 
		System.out.println(bm_ids+"<--Major"+insmIds+"***"+inbmIds+"****"+insecIds+"*****"+incmIds+"$$"+incmAdmIds);
		System.out.println(fname+"**"+lname+"$$"+  dob+"***"+  email+"$$$"+  mobile+"****"+ landline+"$$$$"+  addr1+"**"+  addr2+"$$"+  city+"***"+state+"$$$");  
		System.out.println(btCodt+"^^^"+gender+"****"+  admissionNumber+"$$$$"+"**"+ classAdmittedIn+"***"+  fromDate+"$$$"+ toDate+"&&&"+  active+"IMID"+im_id);
			  
			  
		}catch (Exception e) {
				e.printStackTrace();
		}
		ParentDetailsDAO parentDetailsDAO= null;
		try {
			parentDetailsDAO = (ParentDetailsDAO)ServiceFinder.getContext(request).getBean("ParentDetailsHibernateDao");
			List parentDataList = new ArrayList();
			parentDataList  =	parentDetailsDAO.getParentForEdit( im_id,insmIds+"",inbmIds+"",incmIds+"",insecIds+"",
					fname,lname,dob,email,mobile,landline,addr1,addr2,city,state,gender,admissionNumber,btCodt,incmAdmIds+"",active,parentTypeSel,isDefault);
			System.out.println(parentDataList .size()+"BEFOREin DWR");
			
			 /*  * String fname,			String lname, String dob, String email, String mobile,			String landline, String addr1, String addr2, String city,	
			  * 		String state, String gender, String admissionNumber,			String admissionDate, String classAdmittedIn, String fromDate,			String toDate, String active)* **/
		
			return parentDataList ;
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
			 String classAdmittedIn, String active,String parentTypeSel,String isDefault) {
		

		
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
			String qu = "select pd.PD_ID as pdID,sd.Stu_ID as stuID,pd.First_Name as firstName,pd.Last_Name as lastName,pd.Gender as gender,pd.Address1 as addr1,pd.Address2 as addr2,pd.City as city,pd.State as state,pd.DOB as dob,pd.Photo as photo,sd.Admission_Number as admissionNumber,sd.Admission_Date as admissionDate,sd.Class_Admitted_in as classAdmittedIN," +
					" pd.Active as active, ptm.Type as parentType,pd.isDefault as isDefault," +
					" sm.SeM_ID as secID ,sm.Section_Name as secName,cm.CM_ID as cmID,cm.Class_Name as className,bm.BM_ID as bmID ,bm.Branch_Name as branchName,bm.Short_Name as bShortName,schoolm.SM_ID as smID,schoolm.School_Name as schoolName,	schoolm.Name as sShortName ,sd.First_Name as sfirstName,sd.Last_Name  as slastName ,pd.Email as email,pd.Mobile as mobile " +
					" from parent_details pd inner join parent_student_map psm on pd.PD_ID = psm.PD_ID inner join student_details sd on psm.Stu_ID = sd.Stu_ID inner join student_section_map ssm on sd.Stu_ID=ssm.Stu_ID inner join section_class_map scm on ssm.SCM_ID=scm.SCM_ID " +
					" inner join section_master sm on scm.SeM_ID= sm.SeM_ID inner join class_master cm on scm.CM_ID=cm.CM_ID inner join branch_master bm on ssm.bm_id=bm.bm_id inner join school_master schoolm on ssm.sm_id=schoolm.sm_id" +
					" inner join parent_type_master ptm on pd.PTM_ID= ptm.PTM_ID "+
					" where scm.SeM_ID "+secIDOp+" ("+sections+") and scm.CM_ID "+cmIDOp+" ("+classes+")  and ssm.BM_ID  "+bmIDOp+" ("+branches+")  and ssm.SM_ID "+smIDOp+" ("+schools+") and ssm.IM_ID in ("+im_id+") and  " +
					" pd.First_Name	 like '"+fname+"' and pd.Last_Name like '"+lname+"' and pd.Gender	 like  '"+gender+"' and pd.Address1	 like  '"+addr1+"' and pd.Address2	 like  '"+addr2+"' " +
					" and pd.City like  '"+city+"' and pd.State	 like  '"+state+"' and pd.Email	 like  '"+email+"' and pd.Mobile	 like  '"+mobile+"' and pd.Landline	 like  '"+landline+"' and pd.DOB	 like  '"+dob+"'  " +
					" and sd.Admission_Number	 like  '"+admissionNumber+"'  and  sd.Admission_Date	between "+dateQString+"  and sd.Class_Admitted_in  "+cmAdmIDOp+" ("+classAdmittedIn+")  and pd.isDefault like '"+isDefault+"' and pd.PTM_ID like '"+parentTypeSel+"' and  pd.Active like  '"+active+"'  " +
					"and sm.active='y' and cm.active='y'  and bm.active='y'  and schoolm.active='y'   order by ssm.SM_ID desc";							
			
							session.setAttribute("queryForParentSearch", qu);			
		} catch (Exception e) {
		e.printStackTrace();
		}
		
	}
	
	
}
