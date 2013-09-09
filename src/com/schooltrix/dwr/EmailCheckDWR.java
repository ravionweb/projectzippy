package com.schooltrix.dwr;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.directwebremoting.WebContextFactory;
import org.springframework.beans.BeansException;

import com.schooltrix.daos.ParentDetailsDAO;
import com.schooltrix.daos.SMSCreditsDAO;
import com.schooltrix.daos.SentSmsDAO;
import com.schooltrix.daos.StaffDetailsDAO;
import com.schooltrix.hibernate.SmsCredits;
import com.schooltrix.managers.ServiceFinder;

public class EmailCheckDWR {
	HttpServletResponse response = WebContextFactory.get().getHttpServletResponse();
	HttpServletRequest request = WebContextFactory.get().getHttpServletRequest();
	HttpSession session = WebContextFactory.get().getSession();
	
	public String isEmailCheck(String emailID,String userType,String im_id) {
		
		
		 StaffDetailsDAO staffDetailsDao = null;
		 ParentDetailsDAO parentDetailsDao = null;
		 List emailList = new ArrayList();
		 if(userType.equalsIgnoreCase("NTS")||userType.equalsIgnoreCase("TC")||userType.equalsIgnoreCase("admin")||userType.equalsIgnoreCase("PA")){
			 try {
				 
				staffDetailsDao = (StaffDetailsDAO)ServiceFinder.getContext(request).getBean("StaffDetailsHibernateDao"); 		
				// emailList =  staffDetailsDao.findByPropertyList("email", emailID);
				System.out.println("im_id****isEmailCheck****"+im_id);
				 emailList =  staffDetailsDao.emailCheck(emailID,Long.parseLong(im_id));
				 
					if(emailList.size()>0){
						return "true";
					}
				 
				 
				 parentDetailsDao = (ParentDetailsDAO)ServiceFinder.getContext(request).getBean("ParentDetailsHibernateDao"); 		
//				 emailList =  parentDetailsDao.findByPropertyList("email", emailID);
				 emailList =  parentDetailsDao.emailCheck(emailID,Long.parseLong(im_id));
				 
					if(emailList.size()>0){						
						return "true";
					}
					return "false";
			} catch (BeansException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		 }
		return "false";
	}
	
}
