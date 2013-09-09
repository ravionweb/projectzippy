package com.schooltrix.utils;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.type.StringType;
import org.json.JSONObject;
import org.springframework.orm.hibernate3.HibernateCallback;

import com.schooltrix.daos.SchoolMasterDAO;
import com.schooltrix.managers.ServiceFinder;

public class SchoolMasterSearchData {

	HttpSession session;
	public List getSchoolSearchData(String[] schoolNames,	String[] 	shortName, String[] state , String[] city, String addr, 
			String contactPerson , String mobile,	String 	landline ,String 	email,String active,String IM_ID,int jsonFlag,HttpServletRequest request,HttpSession session) {	
		
		this.session = session;
		StringBuffer insmIds	 	= new StringBuffer();
		StringBuffer insnIds	 	= new StringBuffer();
		StringBuffer incityIds 		= new StringBuffer();
		StringBuffer instateIds 		= new StringBuffer();
		
		String smIDs = null;
		String snIDs = null;
		String cityIDs = null;
		String stateIDs = null;
		StringBuffer sm_ids = new StringBuffer();
		
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
			  
			  if (shortName != null) {
				  for (int i = 0; i < shortName.length; i++) {
					  insnIds.append(shortName[i]);
					  if(i<shortName.length-1)
						  insnIds.append(",");		
				  }
				  
			  }else{
				  insnIds.append( "'%'");
			  }
			  
			
			  if (insmIds.toString().contains("%") && insnIds.toString().contains("%") ) {
				sm_ids = sm_ids.append("'%'");
				jObject.put("sm_ids", insmIds.toString().replaceAll("'", ""));
				jObject.put("sn_ids",insnIds.toString().replaceAll("'", ""));
			} else if (insnIds.toString().contains("%") ){
				sm_ids = insmIds;
				jObject.put("sm_ids",insmIds.toString().replaceAll("'", ""));
				jObject.put("sn_ids", insnIds.toString().replaceAll("'", ""));
			} else if (insmIds.toString().contains("%") ){
				sm_ids = insnIds;
				jObject.put("sm_ids", insmIds.toString().replaceAll("'", ""));
				jObject.put("sn_ids", insnIds.toString().replaceAll("'", ""));
			}else{
				sm_ids = insmIds;//temperarory...we need to disable any one option ....while selecting other school name or short name..				
				jObject.put("sm_ids",insmIds.toString().replaceAll("'", ""));
				jObject.put("sn_ids", insnIds.toString().replaceAll("'", ""));
			}
			  
			  if (state != null) {
				  for (int i = 0; i < state.length; i++) {
					  instateIds.append(state[i]);
					  if(i<state.length-1)
						  instateIds.append(",");		
				  }
				  
			  }else{
				  instateIds.append( "'%'");
			  }
				jObject.put("stateIds",instateIds.toString().replaceAll("'", ""));
			  
			  if (city != null) {
				  for (int i = 0; i < city.length; i++) {
					  if(i ==0)
						  incityIds.append("'");					  
					  
					  incityIds.append(city[i]);
					  
					  if(i<city.length-1)
						  incityIds.append("','");	
					  
					  if(i ==(city.length-1))
						  incityIds.append("'");		
				  }
				  
			  }else{
				  incityIds.append( "'%'");
			  }
			  
				jObject.put("cityIds",incityIds.toString().replaceAll("'", ""));
			//	System.out.println(addr+"-***-"+contactPerson+"****-"+mobile+"***-"+landline+"*****-"+email+"*Active*"+active);
			/*	addr***contct****mobile***land*****amil*Active*Y
				0,3,4,5,6,7,8***0,3,4,5,6,7,8****'0','jhjhjhj','jhjhjhjh','HYD','Hyderabad','lklklkl'*****0,1,2
			
			addr+"-***-"+contactPerson+"****-"+mobile+"***-"+landline+"*****-"+email+"*Active*"+active);
				-***-****-***-*****-*Active*null
				0,3,4,5,6,7,8***'%'****'%'*****'%'  */
			 if (addr.equalsIgnoreCase("")) {
				 jObject.put("addr", addr);
				addr = "%";
			} else{
				jObject.put("addr", addr);
				addr = "%"+addr+"%";
			}
			 if (contactPerson.equalsIgnoreCase("")) {
				 jObject.put("contactPerson", contactPerson);
				 contactPerson = "%";
			 } else{
				 jObject.put("contactPerson", contactPerson);
				 contactPerson = "%"+contactPerson+"%";
			 }
				
			 if (mobile.equalsIgnoreCase("")) {
				 jObject.put("mobile", mobile);
				 mobile = "%";
			 } else{
				 jObject.put("mobile", mobile);
				 mobile = "%"+mobile+"%";
			 }
			 
			 if (landline.equalsIgnoreCase("")) {
				 jObject.put("landline", landline);
				 landline = "%";
			 } else{
				 jObject.put("landline", landline);
				 landline = "%"+landline+"%";
			 }
			 if (email.equalsIgnoreCase("")) {
				 jObject.put("email", email);
				 email = "%";
			 } else{
				 jObject.put("email", email);
				 email = "%"+email+"%";
			 }
			 if (active != null && active.equalsIgnoreCase("y")) {
				 jObject.put("active", "y");
			 } else{
				 jObject.put("active", "n");
				 active = "N";
			 }
			 
			 if (jsonFlag == 1) {
				 construtQueryStringForHistory(sm_ids+"",incityIds+"",instateIds+"",addr,contactPerson,mobile,landline,email,active,Long.parseLong(IM_ID));
				 session.setAttribute("jsonForSchoolSearch", jObject.toString());
			}
			 
			//	System.out.println(addr+"-***-"+contactPerson+"****-"+mobile+"***-"+landline+"*****-"+email+"*Active*"+active);
			  
			 // conitune further/.active.................
		
	//	System.out.println(sm_ids+"<--Major"+insmIds+"***"+insnIds+"****"+incityIds+"*****"+instateIds);
			  
		}catch (Exception e) {
				e.printStackTrace();
		}
		SchoolMasterDAO schoolMasterDAO = null;
		try {
			schoolMasterDAO = (SchoolMasterDAO)ServiceFinder.getContext(request).getBean("SchoolMasterHibernateDao");
			List schoolDataList = new ArrayList();
			schoolDataList =	schoolMasterDAO.getSchoolsForEdit(sm_ids+"",incityIds+"",instateIds+"",addr,contactPerson,mobile,landline,email,active,Long.parseLong(IM_ID));
			
			System.out.println(schoolDataList.size()+"BEFOREin DWR");
			return schoolDataList;			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}

	private void construtQueryStringForHistory(String schhols,String citys, String states, String addr, String contactPerson,
			 String mobile, String landline, String email, String active,long im_id) {

		
		try {
			List schoolList=null;
			String smIDOpF = null;
			
			String stIDOpF = null;
			String ctIDOpF = null;
			
//	System.out.println(smIDs.length()+"666"+bmIDs.length()+"777"+cmIDs.length());
			if (schhols.replace("'", "").length()>1) {//this logic for identifying %
				smIDOpF = "in";			
			}else {
				smIDOpF = "like";			
			}
			
			if (citys.replace("'", "").length()>1) {
				ctIDOpF = "in";			
			}else {
				ctIDOpF = "like";			
			}
			if (states.replace("'", "").length()>1) {
				stIDOpF = "in";			
			}else {
				stIDOpF = "like";			
			}
			String smIDOp = smIDOpF;
			String ctIDOp = ctIDOpF;
			String stIDOp = stIDOpF;
									String qu =	"select sm.SM_ID as SM_ID,sm.IM_ID as IM_ID,sm.School_Name as School_Name,sm.Name as Name,sm.Address as Address,sm.City as City,sm.State_ID as State_ID," +
											"sm.Contact_Person as Contact_Person,sm.Email_ID as Email_ID,sm.Mobile as Mobile,sm.Landline as Landline,sm.Active as Active from school_master sm " +
											"where sm.SM_ID "+smIDOp+" ("+schhols+")  and sm.Address like '"+addr+"'  and sm.City "+ctIDOp+" ("+citys+")    and sm.State_ID "+stIDOp+" ("+states+")" +
													"  and sm.Contact_Person like '"+contactPerson+"'  and sm.Email_ID like '"+email+"'   and sm.Mobile like '"+mobile+"'   and sm.Landline like '"+landline+"'   " +
															"and sm.Active ='"+active+"' and sm.IM_ID ="+im_id+ " order by sm_id desc";
					
					//System.out.println("##-->"+qu);
					session.setAttribute("queryForSchoolSearch", qu);
		} catch (Exception e) {
			e.printStackTrace();
			session.setAttribute("queryForSchoolSearch", "");
			session.setAttribute("jsonForSchoolSearch", "");
			e.printStackTrace();
		}	
		
			
	}
	
	
}
