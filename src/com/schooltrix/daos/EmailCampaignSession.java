package com.schooltrix.daos;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.json.JSONArray;
import org.json.JSONObject;

import com.schooltrix.dtos.EmailCapmaignDto;

public class EmailCampaignSession {
	private Session session =null;
	private SessionFactory sessionFactory = null;
	
	public EmailCampaignSession() {
		// TODO Auto-generated constructor stub
	}
	
	public SessionFactory getSessionFactory() {
		System.out.println("gettt---fac--"+sessionFactory);
		return sessionFactory;
	}
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
		System.out.println("sess---fac--"+sessionFactory);
	}

	public void createSession() {
		if(session == null){
			System.out.println("session-before--"+session);
		session = sessionFactory.openSession();
		System.out.println("session--after-"+session);
		}
	}
	public void releseSession(){
		try{
			if(session != null){
				System.out.println("releasing session--"+session);
				//sessionFactory.close();
				session.close();
				System.out.println("after release--releasing session--"+session);
			}
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}
	
	}

	public JSONArray getEmailUsersList(EmailCapmaignDto emailDto) {
		System.out.println(getSessionFactory()+":::::");
		String toWhome = null;
		String tablename= null;
		
		
		String toAll = emailDto.getSelectAll();
		String toClass = emailDto.getSelectClass();
		String quWhere = "";
		String query ="";
		if(toAll != null && toAll !="null"){
			if ("AllParents".equalsIgnoreCase(toAll)) {
				quWhere = "Par%";
				tablename = "parent_details";
				query = "select First_Name,Last_Name,Email from " + tablename + " where um_id in (select um_id from user_master where IM_ID='" + emailDto.getIM_ID() + "' and BM_ID='" + emailDto.getBM_ID() + "' and UT_ID = '2' and Active='Y') and isDefault='Y' and Active='Y'";
			}else if ("AllTeachingStaff".equalsIgnoreCase(toAll)) {
				quWhere = "TH%";				
				tablename = "staff_details";
				query = "select First_Name,Last_Name,Email from " + tablename + " where um_id in (select um_id from user_master where IM_ID='" + emailDto.getIM_ID() + "' and BM_ID='" + emailDto.getBM_ID() + "' and User_ID like '" + quWhere + "' and Active='Y') and Active='Y'";
			}else if ("AllNonTeachingStaff".equalsIgnoreCase(toAll)) {
				quWhere = "NTH%";				
				tablename = "staff_details";
				query = "select First_Name,Last_Name,Email from " + tablename + " where um_id in (select um_id from user_master where IM_ID='" + emailDto.getIM_ID() + "' and BM_ID='" + emailDto.getBM_ID() + "' and User_ID like '" + quWhere + "' and Active='Y') and Active='Y'";
			}
			if(emailDto.getIM_ID() == null)
			{
				//emailDto.setIM_ID("1");
			}
			//for mother & father ,we have only one record in user_master table
		
		} else if ((toClass != null) && (toClass != "null")) {
			toWhome = toClass;
			System.out.println("in class section");
	
			query = "select First_Name,Last_Name,Email from parent_details where Active='Y' and isDefault='Y'  and PD_ID in ( select pd_id from parent_student_map where Active='Y' and Stu_id in (select Stu_ID from student_section_map where Active='Y' and IM_ID='" +
			emailDto.getIM_ID() + "' and SM_ID='" + emailDto.getSM_ID() + "' and BM_ID='" + emailDto.getBM_ID() + "' and SCM_ID in " +
			"( SELECT SCM_ID FROM section_class_map WHERE Active='Y' and CM_ID = " + toClass + " and bm_id = " + emailDto.getBM_ID() + ") ))";
		}
		
		try {			
/*			if(session == null){
				session = sessionFactory.openSession();
				System.out.println("ses--NEWW--"+session);
				System.out.println("sessionFactory--NEWW--"+sessionFactory);
			}*/
			
			SQLQuery sqlQue = session.createSQLQuery(query);
			sqlQue.addScalar("First_Name");
			sqlQue.addScalar("Last_Name");
			sqlQue.addScalar("Email");
			System.out.println("query-->"+query);
			List listOfUserData =new ArrayList<String[]>();
			JSONArray jArray = new JSONArray();
			for (Iterator iterator = sqlQue.list().iterator(); iterator.hasNext();) {
				Object[] object = (Object[]) iterator.next();
				JSONObject jsonObj = new JSONObject();
				jsonObj.put("First_Name", (String)object[0]);
				jsonObj.put("Last_Name", (String)object[1]);
				jsonObj.put("Email", (String)object[2]);
				jArray.put(jsonObj);			
			}
			return jArray;
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
	}
	
}
