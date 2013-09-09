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
import com.schooltrix.daos.UploadDocDAO;
import com.schooltrix.hibernate.BranchMaster;
import com.schooltrix.hibernate.UploadDocument;
import com.schooltrix.hibernate.UploadDocumentClassBranchMap;
import com.schooltrix.hibernate.UploadDocumentClassBranchMapHistory;
import com.schooltrix.managers.ServiceFinder;

public class PreviousUploadEditAction extends ActionSupport implements ServletRequestAware,SessionAware{
	
	Map session;
	HttpServletRequest request;
	String jsonObjectMain ;
	
	String uploadIDFOREdit ;
	
	private org.apache.log4j.Logger log = Logger.getLogger(PreviousUploadEditAction.class);
	
	
	//json creation
	public String fetchUploadJsonForEdit() {
		
		//String ud_id = request.getParameter("upload_ID");
		//String p1 = request.getParameter("p1");
		
		System.out.println(uploadIDFOREdit+"**session.get(uploadIDForEdit)-->"+session.get("uploadIDForEdit"));
		
		if ((session.get("uploadIDForEdit") != null && session.get("uploadIDForEdit")!= "null" ) || uploadIDFOREdit != null) {//normal session and refresh also
			if ((session.get("uploadIDForEdit") != null && session.get("uploadIDForEdit")!= "null" )) {
				uploadIDFOREdit = (String)session.get("uploadIDForEdit");	
				session.put("uploadIDForEdit", null);//clear the session
			}
		} else {
			return "fail";
		}
		 
		//String ud_id = "18";//testing
		String ud_id = uploadIDFOREdit;
		
		UploadDocDAO uploadDocDAO = null;
		List uDCBranchMap = new ArrayList();
		UploadDocumentClassBranchMap uBranchMapInn = null;
		BranchMasterDAO bMasterDao = null;
		
		String im_id =(String)session.get("IM_ID");
		
		uploadDocDAO = (UploadDocDAO)ServiceFinder.getContext(request).getBean("UploadDocHibernateDao");
		//System.out.println("before:::::result var:::");
		
		JSONArray jArray = new JSONArray();
		JSONArray jArray_BM = new JSONArray();
		JSONArray jArray_CM = new JSONArray();
		JSONArray jArray_SM = new JSONArray();
		JSONObject jObject = new JSONObject();
		try {
			jObject.put("uploadID", ud_id);
			//11111111111111111111111111111111
			UploadDocument uploadDocument = uploadDocDAO.findById(Long.parseLong(ud_id));
			jObject.put("userType", uploadDocument.getToWhome());
			jObject.put("uploadType", uploadDocument.getUploadType());
			jObject.put("assignType", uploadDocument.getAssignType());
			jObject.put("assignDesc", uploadDocument.getAssgDesc());
			jObject.put("sub", uploadDocument.getSubject());
			jObject.put("fileName", uploadDocument.getFileName());
			jObject.put("email", uploadDocument.getNotifyPaEmail());
		
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		//222222222222222222222222222222
		try {
			uDCBranchMap = uploadDocDAO.getUploadDocumentClassBranchMap("udId",ud_id);
			
			//33333333333333333333333--getSchoolList
			//from branchmaster where im_id
	    	System.out.println(uDCBranchMap.size());
			
	    	bMasterDao = (BranchMasterDAO)ServiceFinder.getContext(request).getBean("BranchMasterHibernateDao");
			List schollBranchList =  bMasterDao.findByPropertyList("imId", im_id);
			
			for (Iterator iterator = uDCBranchMap.iterator(); iterator.hasNext();) {
				Object object = (Object) iterator.next();
				 uBranchMapInn = (UploadDocumentClassBranchMap)object;				 
				//System.out.println(uBranchMapInn.getSno()+"*sno*"+uBranchMapInn.getUdId()+":::"+uBranchMapInn.getBmId()+"::::"+uBranchMapInn.getCmId());
				 
				jArray_BM.put(uBranchMapInn.getBmId());//duplicate var remove at js..http://jsfiddle.net/sushanth009/JWsZh/
				jArray_CM.put(uBranchMapInn.getCmId());
				jArray_SM.put(getSchoolID(schollBranchList,uBranchMapInn.getBmId()));
			}
			
			jObject.put("classList", jArray_CM);
			jObject.put("branchList", jArray_BM);
			jObject.put("schoolList", jArray_SM);
			System.out.println("jObject:::"+jObject);
			jsonObjectMain = jObject.toString();
			session.put("forEdit",jsonObjectMain);
			
			return "success";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "fail";
	}
	
	
	private String getSchoolID(List schollBranchList,String bm_id) {
		
		for (Iterator iterator = schollBranchList.iterator(); iterator
				.hasNext();) {
			Object object = (Object) iterator.next();
			BranchMaster uBranchInn = (BranchMaster)object;
			String bm_idd =uBranchInn.getBmId()+"";
			if (bm_id.equalsIgnoreCase(bm_idd)) {
					return uBranchInn.getSmId()+"";				
			}
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
