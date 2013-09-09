package com.schooltrix.actions;


import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javassist.ClassClassPath;

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
import com.schooltrix.daos.StudentDetailsDAO;
import com.schooltrix.daos.UploadDocDAO;
import com.schooltrix.hibernate.BranchMaster;
import com.schooltrix.hibernate.ClassMaster;
import com.schooltrix.hibernate.SchoolMaster;
import com.schooltrix.hibernate.StateMaster;
import com.schooltrix.hibernate.StudentDetails;
import com.schooltrix.hibernate.UploadDocument;
import com.schooltrix.hibernate.UploadDocumentClassBranchMap;
import com.schooltrix.hibernate.UploadDocumentClassBranchMapHistory;
import com.schooltrix.managers.ServiceFinder;
import com.schooltrix.utils.StudentDetailsAnalysis;

public class StudentEditAction extends ActionSupport implements ServletRequestAware,SessionAware{
	
	Map session;
	HttpServletRequest request;
	
	private org.apache.log4j.Logger log = Logger.getLogger(StudentEditAction.class);
	
	
	//json creation
	public String fetchStudentJsonForEdit() {
		String studentIDForEdit = null;
		
		System.out.println("**session.get(studentIDForEdit)-->"+session.get("studentIDForEdit"));
		
		if ((session.get("studentIDForEdit") != null && session.get("studentIDForEdit")!= "null" ) || studentIDForEdit != null) {//normal session and refresh also
			if ((session.get("studentIDForEdit") != null && session.get("studentIDForEdit")!= "null" )) {
				studentIDForEdit = (String)session.get("studentIDForEdit");	
				session.put("studentIDForEdit", null);//clear the session
			}
		} else {
			return "fail";
		}

		String sd_id = studentIDForEdit;//*****
		StudentDetailsDAO studentDetailsDAO= null;
		JSONObject jObject = new JSONObject();
		
		try {
			jObject.put("schoolID", sd_id);

			studentDetailsDAO = (StudentDetailsDAO)ServiceFinder.getContext(request).getBean("StudentDetailsHibernateDao");
			StudentDetails schoolMaster = studentDetailsDAO.findById(Long.parseLong(sd_id));
			jObject.put("active", schoolMaster.getActive());
			jObject.put("addr1", schoolMaster.getAddress1());
			jObject.put("addr2", schoolMaster.getAddress2());
			jObject.put("admissionDate", schoolMaster.getAdmissionDate());
			jObject.put("admissionNumber", schoolMaster.getAdmissionNumber());
			jObject.put("city", schoolMaster.getCity());
			jObject.put("classAdmittedIN", schoolMaster.getClassAdmittedIn());//neeeddd logics ..for edit purpose
			jObject.put("classAdmittedINName",getClassNameFromUtil(schoolMaster.getClassAdmittedIn()));//neeeddd logics 
			jObject.put("dob", schoolMaster.getDob());
			jObject.put("email", schoolMaster.getEmail());
			jObject.put("fname", schoolMaster.getFirstName());
			jObject.put("gender", schoolMaster.getGender());
			jObject.put("landline", schoolMaster.getLandline());
			jObject.put("lname", schoolMaster.getLastName());
			jObject.put("mobile", schoolMaster.getMobile());
			jObject.put("photo", schoolMaster.getPhoto());
			try{
			jObject.put("state", getStateByID(Long.parseLong(schoolMaster.getState())));
			}catch (Exception e) {
				jObject.put("state", "AP");
			}
			jObject.put("umID", schoolMaster.getUmId());
			jObject.put("StID", schoolMaster.getStuId());
			System.out.println("STUDENT-->"+jObject);
			session.put("forStudentEdit",jObject.toString());
			session.put("pageTO", "uts");//for usertype purpose
			return "success";
		} catch (Exception e1) {
			e1.printStackTrace();
			session.put("forStudentEdit","");			
		}
		
		return "fail";
	}

	private String  getClassNameFromUtil(String classAdmittedIn) {
		ClassMaster classMaster = null;
		try {
			StudentDetailsAnalysis analysis = new StudentDetailsAnalysis(request);
			classMaster = analysis.getClassName(classAdmittedIn);
			return classMaster.getClassName();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
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
