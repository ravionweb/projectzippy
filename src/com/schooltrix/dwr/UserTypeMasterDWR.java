package com.schooltrix.dwr;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.directwebremoting.WebContextFactory;
import org.springframework.beans.BeansException;

import com.schooltrix.daos.InstitutionMasterDAO;
import com.schooltrix.daos.UserMasterDAO;
import com.schooltrix.daos.UserTypeMasterDAO;
import com.schooltrix.hibernate.InstitutionMaster;
import com.schooltrix.hibernate.UserMaster;
import com.schooltrix.hibernate.UserType;
import com.schooltrix.managers.ServiceFinder;

public class UserTypeMasterDWR {

	HttpServletResponse response = WebContextFactory.get().getHttpServletResponse();
	HttpServletRequest request = WebContextFactory.get().getHttpServletRequest();
	HttpSession session = WebContextFactory.get().getSession();
	
	public String[] userTypeMaster() {
				UserTypeMasterDAO uTypeMasterDao =null;
				List userTypes =null;
				String[]  userDataTypes = null;
    	try {
    		System.out.println("in UserTypeMasterDWR");
    		uTypeMasterDao = (UserTypeMasterDAO)ServiceFinder.getContext(request).getBean("UserTypeMasterDAO"); 		
				UserType uMasterData = new UserType();//?
				
				userTypes = 	uTypeMasterDao.findByPropertyList("active","Y");
				userDataTypes = new String[userTypes.size()];
				
				for(int i=0;i<userTypes.size();i++){
						userDataTypes[i]=((UserType)userTypes.get(i)).getUtDesc();
				}
				
			return userDataTypes;
			
			
		} catch (BeansException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return userDataTypes;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return userDataTypes;
		}
    	
	}
	
	public List getInstitutionMasterList() {
				InstitutionMasterDAO insMasterDao =null;
				List instList= new ArrayList();
				List uiuiu = new ArrayList();
				try {
							System.out.println("in getInstitutionMasterList");
							insMasterDao = (InstitutionMasterDAO)ServiceFinder.getContext(request).getBean("InstitutionMasterHibernateDao"); 		
								instList = 	insMasterDao.findByPropertyList("active","Y");
								System.out.println("in dwr ut--"+instList.size());

								for (int i = 0; i < instList.size(); i++) {
									String[] oioio = new String[2];
									InstitutionMaster ioio = (InstitutionMaster)instList.get(i);
									System.out.println("--90---"+ioio.getShortName());
									oioio[0] = ioio.getImId()+"";
									oioio[1] = ioio.getShortName();
									uiuiu.add(oioio);
								}
						return uiuiu;
			
				} catch (BeansException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					return null;
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					return null;
				}

	}
}
