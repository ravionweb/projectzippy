package com.schooltrix.dwr;


import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.directwebremoting.WebContextFactory;
import org.springframework.beans.BeansException;
import com.schooltrix.daos.SMSCreditsDAO;
import com.schooltrix.hibernate.QuickSms;
import com.schooltrix.hibernate.SmsCredits;
import com.schooltrix.managers.ServiceFinder;

public class SMSCreditsDWR implements Serializable {


	HttpServletResponse response = WebContextFactory.get().getHttpServletResponse();
	HttpServletRequest request = WebContextFactory.get().getHttpServletRequest();
	HttpSession session = WebContextFactory.get().getSession();
	
    
	 public String smsCreditsBalance(String SM_ID,String BM_ID) {

			SMSCreditsDAO smsCreditsDao =null;	
			String smscredits = "";
			try {
				String IM_ID = (String)session.getAttribute("IM_ID");
				smsCreditsDao = (SMSCreditsDAO)ServiceFinder.getContext(request).getBean("SMSCreditsHibernateDao"); 		
				SmsCredits smsInputData = new SmsCredits();
				smsInputData = smsCreditsDao.findByProperty("bmId", BM_ID);
				if (smsInputData != null) {
					 smscredits=smsInputData.getSmsCredits();
				}
					
					System.out.println("smsInputData-------"+smscredits);
					
			return smscredits;
			} catch (BeansException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "error";
			} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "error";
			}

	 }
	 
	 public String sentQuickSMS(String toWhome,String Msg,String ipAddr) {
			
			String IM_ID = (String)session.getAttribute("IM_ID");
			String SM_ID = (String)session.getAttribute("SM_ID");
			String BM_ID = (String)session.getAttribute("BM_ID");

			String UM_ID = (String)session.getAttribute("UM_ID");
			String pdID ="";
			try {
				pdID = (String)session.getAttribute("pdID");
			} catch (Exception e1) {
				pdID = "";
			}

			String classID 		= (String)session.getAttribute("ClassID");
			String studentID 	= (String)session.getAttribute("StuID");
		 
			SMSCreditsDAO smsCreditsDao =null;	
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			if (IM_ID !=null && SM_ID != null && BM_ID !=null && UM_ID != null && Msg != null && Msg != "" && toWhome != null && toWhome != "") {
				try {
					smsCreditsDao = (SMSCreditsDAO)ServiceFinder.getContext(request).getBean("SMSCreditsHibernateDao"); 		
					QuickSms quickSms = new QuickSms();
					quickSms.setBmId(BM_ID);
					quickSms.setClassId(classID);
					quickSms.setImId(IM_ID);
					quickSms.setIpaddr(ipAddr);
					quickSms.setPdId(pdID);//for time being from parent dash no isuue,,,but from student dash .it will.null
					quickSms.setQuickBody(Msg);
					quickSms.setReqTime(Timestamp.valueOf(sdf.format(new Date())));
					quickSms.setSmId(SM_ID);
					quickSms.setToWhom(toWhome);
					quickSms.setStuId(studentID);
					quickSms.setUmId(UM_ID);
					
					boolean result = smsCreditsDao.saveQuickMSG(quickSms);
					System.out.println("result"+result);
					if (result) {
						return "Success";
					} else {
						return "Failed";
					}
					
				} catch (BeansException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			return "Failed";			
	}
	 
}
