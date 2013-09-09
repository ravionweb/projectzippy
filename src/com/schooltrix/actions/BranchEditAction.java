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
import com.schooltrix.daos.StateMasterDAO;
import com.schooltrix.daos.UploadDocDAO;
import com.schooltrix.hibernate.BranchMaster;
import com.schooltrix.hibernate.SchoolMaster;
import com.schooltrix.hibernate.StateMaster;
import com.schooltrix.hibernate.UploadDocument;
import com.schooltrix.hibernate.UploadDocumentClassBranchMap;
import com.schooltrix.hibernate.UploadDocumentClassBranchMapHistory;
import com.schooltrix.managers.ServiceFinder;

public class BranchEditAction extends ActionSupport implements ServletRequestAware,SessionAware{
	
	Map session;
	HttpServletRequest request;
	
	private org.apache.log4j.Logger log = Logger.getLogger(BranchEditAction.class);
	
	
	//json creation
	public String fetchBranchJsonForEdit() {
		String branchIDForEdit = null;
		
		System.out.println(branchIDForEdit+"**session.get(branchIDForEdit)-->"+session.get("branchIDForEdit"));
		
		if ((session.get("branchIDForEdit") != null && session.get("branchIDForEdit")!= "null" ) || branchIDForEdit != null) {//normal session and refresh also
			if ((session.get("branchIDForEdit") != null && session.get("branchIDForEdit")!= "null" )) {
				branchIDForEdit = (String)session.get("branchIDForEdit");	
				session.put("branchIDForEdit", null);//clear the session
			}
		} else {
			return "fail";
		}

		String sd_id = branchIDForEdit;//*****
		BranchMasterDAO branchMasterDAO= null;
		JSONObject jObject = new JSONObject();
		
		try {
			jObject.put("branchID", sd_id);

			branchMasterDAO = (BranchMasterDAO)ServiceFinder.getContext(request).getBean("BranchMasterHibernateDao");
			BranchMaster branchMaster	= branchMasterDAO.findById(Long.parseLong(sd_id));
			jObject.put("schoolID", branchMaster.getSmId());
			jObject.put("active", branchMaster.getActive());
			jObject.put("addr", branchMaster.getAddress());
			jObject.put("city", branchMaster.getCity());
			jObject.put("contactPerson", branchMaster.getContactPerson());
			jObject.put("email", branchMaster.getEmailId());
			jObject.put("landline", branchMaster.getLandline());
			jObject.put("mobile", branchMaster.getMobile());
			jObject.put("shortName",branchMaster.getShortName());
			jObject.put("branchName",  branchMaster.getBranchName());
			jObject.put("state", getStateByID(branchMaster.getStateId()));//problem here
			jObject.put("stateID", branchMaster.getStateId());
			
			session.put("forBranchEdit",jObject.toString());			
			return "success";
		} catch (Exception e1) {
			e1.printStackTrace();
			session.put("forBranchEdit","");			
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
