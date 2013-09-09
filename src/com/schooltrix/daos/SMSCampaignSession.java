package com.schooltrix.daos;

import com.schooltrix.dtos.SMSCapmaignDto;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.json.JSONArray;
import org.json.JSONObject;

public class SMSCampaignSession{
		private Session session = null;
		private SessionFactory sessionFactory = null;
		
		public SessionFactory getSessionFactory()
		{
			System.out.println("gettt---fac--" + sessionFactory);
			return sessionFactory;
		}
		public void setSessionFactory(SessionFactory sessionFactory) {
			this.sessionFactory = sessionFactory;
			System.out.println("sess---fac--" + sessionFactory);
		}
		
		public void createSession() {
			if (session == null) {
			System.out.println("session-before--" + session);
			session = sessionFactory.openSession();
			System.out.println("session--after-" + session);
		}
		}
		public void releseSession() {
			try {
			if (session != null) {
			System.out.println("releasing session--" + session);
			
			session.close();
			System.out.println("after release--releasing session--" + session);
			}
			} catch (Exception e) {
			System.out.println(e.getMessage());
			}
		}
		
		public JSONArray getSMSUsersList(SMSCapmaignDto smsdto){
			System.out.println(getSessionFactory() + ":::::");
			String toWhome = null;
			String tablename = null;
			
			String toAll = smsdto.getSelectAll();
			String toClass = smsdto.getSelectClass();
			String quWhere = "";
			String query = "";
			if ((toAll != null) && (toAll != "null")) {
			if ("AllParents".equalsIgnoreCase(toAll)) {
			quWhere = "Par%";
			tablename = "parent_details";
			query = "select First_Name,Last_Name,Mobile from " + tablename + " where um_id in (select um_id from user_master where IM_ID='" + smsdto.getIM_ID() + "' and BM_ID='" + smsdto.getBM_ID() + "' and User_ID like '" + quWhere + "' and Active='Y')and isDefault='Y'  and Active='Y'";
			} else if ("AllTeachingStaff".equalsIgnoreCase(toAll)) {
			quWhere = "TH%";
			tablename = "staff_details";
			query = "select First_Name,Last_Name,Mobile from " + tablename + " where um_id in (select um_id from user_master where IM_ID='" + smsdto.getIM_ID() + "' and BM_ID='" + smsdto.getBM_ID() + "' and User_ID like '" + quWhere + "' and Active='Y') and Active='Y'";
			} else if ("AllNonTeachingStaff".equalsIgnoreCase(toAll)) {
			quWhere = "NTH%";
			tablename = "staff_details";
			query = "select First_Name,Last_Name,Mobile from " + tablename + " where um_id in (select um_id from user_master where IM_ID='" + smsdto.getIM_ID() + "' and BM_ID='" + smsdto.getBM_ID() + "' and User_ID like '" + quWhere + "' and Active='Y') and Active='Y'";
			}
			if (smsdto.getIM_ID() == null)
			{
			smsdto.setIM_ID("1");
			}
			//for mother & father ,we have only one record in user_master table
		//	query = "select First_Name,Last_Name,Mobile from " + tablename + " where um_id in (select um_id from user_master where IM_ID='" + smsdto.getIM_ID() + "' and BM_ID='" + smsdto.getBM_ID() + "' and User_ID like '" + quWhere + "' and Active='Y') and Active='Y'";
			} else if ((toClass != null) && (toClass != "null")) {
			toWhome = toClass;
			System.out.println("in class section");
			
			query = "select First_Name,Last_Name,Mobile from parent_details where  Active='Y' and isDefault='Y' and PD_ID in ( select pd_id from parent_student_map where Active='Y' and Stu_id in" +
					" (select Stu_ID from student_section_map where Active='Y' and IM_ID='" +
			smsdto.getIM_ID() + "' and SM_ID='" + smsdto.getSM_ID() + "' and BM_ID='" + smsdto.getBM_ID() + "' and SCM_ID in " +
			"( SELECT SCM_ID FROM section_class_map WHERE Active='Y' and CM_ID = " + toClass + " and bm_id = " + smsdto.getBM_ID() + ") ))";
			}
			
			try
			{
			SQLQuery sqlQue = session.createSQLQuery(query);
			sqlQue.addScalar("First_Name");
			sqlQue.addScalar("Last_Name");
			sqlQue.addScalar("Mobile");
			System.out.println("query-->" + query);
			List listOfUserData = new ArrayList();
			JSONArray jArray = new JSONArray();
			for (Iterator iterator = sqlQue.list().iterator(); iterator.hasNext();) {
			Object[] object = (Object[])iterator.next();
			JSONObject jsonObj = new JSONObject();
			jsonObj.put("First_Name", (String)object[0]);
			jsonObj.put("Last_Name", (String)object[1]);
			jsonObj.put("Mobile", (String)object[2]);
			jArray.put(jsonObj);
			}
			return jArray;
			}
			catch (HibernateException e) {
			e.printStackTrace(); }
			return null;
			}
} 