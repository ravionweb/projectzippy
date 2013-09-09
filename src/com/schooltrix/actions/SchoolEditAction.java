package com.schooltrix.actions;


import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.SessionAware;
import org.json.JSONArray;
import org.json.JSONObject;

import com.opensymphony.xwork2.ActionSupport;
import com.schooltrix.daos.BranchMasterDAO;
import com.schooltrix.daos.SchoolMasterDAO;
import com.schooltrix.daos.StateMasterDAO;
import com.schooltrix.daos.UploadDocDAO;
import com.schooltrix.hibernate.BranchMaster;
import com.schooltrix.hibernate.SchoolMaster;
import com.schooltrix.hibernate.StateMaster;
import com.schooltrix.hibernate.UploadDocument;
import com.schooltrix.hibernate.UploadDocumentClassBranchMap;
import com.schooltrix.hibernate.UploadDocumentClassBranchMapHistory;
import com.schooltrix.managers.ServiceFinder;

public class SchoolEditAction extends ActionSupport implements ServletRequestAware,SessionAware{
	
	Map session;
	HttpServletRequest request;
	
	private org.apache.log4j.Logger log = Logger.getLogger(SchoolEditAction.class);
	
	
	//json creation
	public String fetchSchoolJsonForEdit() {
		String schoolIDForEdit = null;
		
		System.out.println(schoolIDForEdit+"**session.get(schoolIDForEdit)-->"+session.get("schoolIDForEdit"));
		
		if ((session.get("schoolIDForEdit") != null && session.get("schoolIDForEdit")!= "null" ) || schoolIDForEdit != null) {//normal session and refresh also
			if ((session.get("schoolIDForEdit") != null && session.get("schoolIDForEdit")!= "null" )) {
				schoolIDForEdit = (String)session.get("schoolIDForEdit");	
				session.put("schoolIDForEdit", null);//clear the session
			}
		} else {
			return "fail";
		}

		String sd_id = schoolIDForEdit;//*****
		SchoolMasterDAO schoolMasterDAO= null;
		JSONObject jObject = new JSONObject();
		
		try {
			jObject.put("schoolID", sd_id);

			schoolMasterDAO = (SchoolMasterDAO)ServiceFinder.getContext(request).getBean("SchoolMasterHibernateDao");
			SchoolMaster schoolMaster = schoolMasterDAO.findById(Long.parseLong(sd_id));
			jObject.put("active", schoolMaster.getActive());
			jObject.put("addr", schoolMaster.getAddress());
			jObject.put("city", schoolMaster.getCity());
			jObject.put("contactPerson", schoolMaster.getContactPerson());
			jObject.put("email", schoolMaster.getEmailId());
			jObject.put("landline", schoolMaster.getLandline());
			jObject.put("mobile", schoolMaster.getMobile());
			jObject.put("shortName",schoolMaster.getSchoolName());
			jObject.put("schoolName",  schoolMaster.getName());
			jObject.put("state", getStateByID(schoolMaster.getStateId()));//problem here
			jObject.put("stateID", schoolMaster.getStateId());
			
			session.put("forSchoolEdit",jObject.toString());			
			return "success";
		} catch (Exception e1) {
			e1.printStackTrace();
			session.put("forSchoolEdit","");			
		}
		
		return "fail";
	}

	private String getStateByID(Long stateId) {
		StateMaster master = null;
		StateMasterDAO dao = null;
		try {
			dao = (StateMasterDAO)ServiceFinder.getContext(request).getBean("StateMasterHibernateDao");
			master = dao.findById(stateId);
			System.out.println(master.getStateName()+"<-master.getStateName()"+stateId);
			return master.getStateName();
		} catch (Exception e) {
			//e.printStackTrace();//problemmmmmmmmmmmmmmm
		}
		return null;
	}

	@Override
	public void setSession(Map<String, Object> session) {
		// TODO Auto-generated method stub
		System.out.println(session+"------------------------------------");
		this.session = session;
	}

	@Override
	public void setServletRequest(HttpServletRequest request) {
		// TODO Auto-generated method stub
		System.out.println(request+"--------request----------------------------");
		this.request = request;  
	}
}
