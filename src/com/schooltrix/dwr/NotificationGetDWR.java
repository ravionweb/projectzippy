package com.schooltrix.dwr;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.directwebremoting.WebContextFactory;
import org.springframework.beans.BeansException;

import com.schooltrix.daos.NotificationMasterDAO;
import com.schooltrix.hibernate.NotificationMaster;
import com.schooltrix.hibernate.StudentDetails;
import com.schooltrix.managers.ServiceFinder;
import com.schooltrix.utils.StudentDetailsAnalysis;

public class NotificationGetDWR {

	HttpServletResponse response = WebContextFactory.get().getHttpServletResponse();
	HttpServletRequest request = WebContextFactory.get().getHttpServletRequest();
	HttpSession session = WebContextFactory.get().getSession();		
	
	
	public List getNotifications() {
		
		try {
			NotificationMasterDAO notificationMasterDAO = null;
			
			
			/*StudentDetails studentDetails = null;//Session classID and stuID removed here puted loginAction
			String classID = null;*/
			
			String UM_ID = (String)session.getAttribute("UM_ID");
			String pdID = (String)session.getAttribute("pdID");
			
			String IM_ID = (String)session.getAttribute("IM_ID");
			String SM_ID = (String)session.getAttribute("SM_ID");
			String BM_ID = (String)session.getAttribute("BM_ID");
			String classID = (String)session.getAttribute("ClassID");
			
					try {
						System.out.println(classID+"****classID in NotificationDWR**");
						notificationMasterDAO = (NotificationMasterDAO)ServiceFinder.getContext(request).getBean("NotificationMasterHibernateDao");
						List notiDataList = new ArrayList();
						notiDataList =	notificationMasterDAO.getNotificationForParent(BM_ID, classID);
						System.out.println("notiDataList.sizz"+notiDataList.size());
						return notiDataList;
						
					} catch (BeansException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				return null;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	
	
	
	
}
