package com.schooltrix.dwr;

import com.schooltrix.daos.NotificationMasterDAO;
import com.schooltrix.daos.SMSCreditsDAO;
import com.schooltrix.daos.SentSmsDAO;
import com.schooltrix.hibernate.NotificationMaster;
import com.schooltrix.hibernate.SentSms;
import com.schooltrix.hibernate.SmsCredits;
import com.schooltrix.managers.ServiceFinder;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintStream;
import java.io.Writer;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.commons.lang.RandomStringUtils;
import org.directwebremoting.WebContext;
import org.directwebremoting.WebContextFactory;
import org.json.JSONArray;
import org.springframework.context.ApplicationContext;

public class SentSMSDWR
{
		HttpServletResponse response = WebContextFactory.get().getHttpServletResponse();
		HttpServletRequest request = WebContextFactory.get().getHttpServletRequest();
		HttpSession session = WebContextFactory.get().getSession();		
	
		public String sendSMSNotificationCall(String smsBodyText, String sentUsersCount, String smsBalcredits, String usersList,String notiSub,String toWhome,String toClass,  String BM_ID, String SM_ID,String ipaddr)
		{
	
		try
		{
			JSONArray userJson = null;
			String UM_ID = (String)session.getAttribute("UM_ID");			
			userJson = new JSONArray(usersList);
		
		if (userJson.length() > 0 && UM_ID != null && UM_ID != "null" || UM_ID != "") {
				
			String sendSMS = sendSMSCall( smsBodyText,  sentUsersCount,  smsBalcredits,  usersList,   BM_ID,  SM_ID, ipaddr);
			String sendNoti = sendNotififcation( SM_ID, BM_ID,  smsBodyText, notiSub, toWhome, toClass,   usersList,   ipaddr);
				
				if (sendNoti.equalsIgnoreCase("success") && sendSMS.equalsIgnoreCase("success")) {
					session.setAttribute("smsmsg", "success");
					return "success";
				}	
		
		}		
		session.setAttribute("smsmsg", "No Recipients");
		return "No Users";
		}
		catch (Exception e)
		{
		e.printStackTrace(); }
		session.setAttribute("smsmsg", "error");
		return "error";
	}
	
		public String sendNotififcation(String SM_ID,String BM_ID, String notiBodyText,String notiSub,String toWhome,String toClass,  String usersList,  String ipaddr)
		{
		NotificationMasterDAO sentNFDao = null;
		JSONArray userJson = null;
		try
		{
			String IM_ID = (String)session.getAttribute("IM_ID");
			String UM_ID = (String)session.getAttribute("UM_ID");
			
			userJson = new JSONArray(usersList);//this is for cross checking purpose only here..
			System.out.println(UM_ID+ "--IM_ID" + IM_ID);
			
			if (userJson.length() > 0) {
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				sentNFDao = (NotificationMasterDAO)ServiceFinder.getContext(request).getBean("NotificationMasterHibernateDao");
				NotificationMaster notificationData = new NotificationMaster();
				notificationData.setBmId(BM_ID);
				notificationData.setImId(IM_ID);
				notificationData.setSmId(SM_ID);
				notificationData.setUmId(UM_ID);
	
				notificationData.setNotifBody(notiBodyText);
				notificationData.setNotifSubject(notiSub);
				notificationData.setOndate(Timestamp.valueOf(sdf.format(new Date())));
				
				notificationData.setToClass(toClass);
				notificationData.setToWhom(toWhome);
				notificationData.setIpAddr(ipaddr);
			
				sentNFDao.save(notificationData);
				session.setAttribute("smsmsg", "success");
				return "success";
			}
		}
		catch (Exception e) {
		e.printStackTrace();
		session.setAttribute("smsmsg", "error");
		return "error";
		}
		
		session.setAttribute("smsmsg", "No Recipients");
		return "No Users";
	}
		
		public String sendSMSCall(String smsBodyText, String sentUsersCount, String smsBalcredits, String usersList,  String BM_ID, String SM_ID,String ipaddr)
			{
			SentSmsDAO sentSmsDao = null;
			JSONArray userJson = null;
			try
			{
				String IM_ID = (String)session.getAttribute("IM_ID");
				String UM_ID = (String)session.getAttribute("UM_ID");
				
			userJson = new JSONArray(usersList);
			System.out.println(UM_ID+":smsBodyText:" + smsBodyText + ":sentUsersCount:" + sentUsersCount + ":smsBalcredits:" + smsBalcredits + ":usersList:" + usersList + "--IM_ID" + IM_ID);
			if (userJson.length() > 0) {
			sentSmsDao = (SentSmsDAO)ServiceFinder.getContext(request).getBean("SentSmsHibernateDao");
			SentSms smsInputData = new SentSms();
			smsInputData.setImId(IM_ID);
			smsInputData.setSmId(SM_ID);
			smsInputData.setBmId(BM_ID);
			smsInputData.setUmId(UM_ID);
			
			smsInputData.setNoToSent(sentUsersCount);
			smsInputData.setSmsBalanceCredits(smsBalcredits);
			smsInputData.setSmsBody(smsBodyText);
			smsInputData.setStatus("0");
			smsInputData.setIpaddr(ipaddr);
			
			String fileName = writeIntoFolder(userJson, IM_ID);
			if (!fileName.equalsIgnoreCase(""))
			{
			smsInputData.setUserListFileName(fileName);
			}
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			
			smsInputData.setReqTime(Timestamp.valueOf(sdf.format(new Date())));
			smsInputData.setProcessedTime(Timestamp.valueOf(sdf.format(new Date())));
			
			SMSCreditsDAO smsCreditsDao = null;
			try {
			smsCreditsDao = (SMSCreditsDAO)ServiceFinder.getContext(request).getBean("SMSCreditsHibernateDao");
			SmsCredits smsCreditsInputData = new SmsCredits();
			smsCreditsInputData = smsCreditsDao.findByProperty("imId", IM_ID);
			smsCreditsInputData.setSmsCredits(smsInputData.getSmsBalanceCredits());
			
			sentSmsDao.save(smsInputData);
			smsCreditsDao.update(smsCreditsInputData);
			}
			catch (Exception e) {
			e.printStackTrace();
			session.setAttribute("smsmsg", "error");
			return "error";
			}
			
			session.setAttribute("smsmsg", "success");
			return "success";
			}
			
			session.setAttribute("smsmsg", "No Recipients");
			return "No Users";
			}
			catch (Exception e)
			{
			e.printStackTrace(); }
			session.setAttribute("smsmsg", "error");
			return "error";
		}
		
		public String writeIntoFolder(JSONArray finalJSON, String im_id){
			try
				{
				String fileLocation = "";
				File smsdir = null;
				String fileName = "";
				try
					{
					Properties properties = new Properties();
					java.io.InputStream is = getClass().getResourceAsStream("../alerts/AlertsPaths.properties");
					properties.load(is);
					fileLocation = properties.getProperty("SMSfoldername");
					
					String institutionName = (String)session.getAttribute("IM_SN");
					System.out.println(institutionName + "--institutionName-");
					
					fileLocation = fileLocation + "/" + institutionName + "/";
					smsdir = new File(fileLocation);
					properties.clear();
						if (!smsdir.isDirectory()) {
							
						System.out.println("********** Directory Not exists so create it *************");
						smsdir.mkdirs();
						}
					} catch (Exception e) {
				e.printStackTrace();
				}
				Writer jsonSMSTemp = null;
					try {
					if (finalJSON.length() > 0) {
					System.out.println(fileLocation);
					fileName = getFileUniqueName();
					
					System.out.println(fileName + ":::");
					File file = new File(fileLocation + fileName);
					String str1; if (file.exists()) {
					jsonSMSTemp = new BufferedWriter(new FileWriter(file));
					jsonSMSTemp.write(finalJSON.toString().trim());
					jsonSMSTemp.flush();
					jsonSMSTemp.close();
					return fileName;
					}
					if (file.createNewFile()) {
					jsonSMSTemp = new BufferedWriter(new FileWriter(file));
					jsonSMSTemp.write(finalJSON.toString().trim());
					jsonSMSTemp.flush();
					jsonSMSTemp.close();
					return fileName;
					}
					}
					}
				catch (Exception e)
				{
				e.printStackTrace();
				return "";
					} finally {
					jsonSMSTemp = null; } jsonSMSTemp = null;
				
				return "";
				} catch (Exception e) {
			e.printStackTrace(); }
			return "";
		}
		
		private String getFileUniqueName() {
			String extension = "json";
			return String.format("%s.%s", new Object[] { RandomStringUtils.randomAlphanumeric(10), extension });
		}
} 