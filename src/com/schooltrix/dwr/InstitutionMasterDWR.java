package com.schooltrix.dwr;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.directwebremoting.WebContextFactory;
import org.springframework.beans.BeansException;

import com.schooltrix.daos.DomainDAO;
import com.schooltrix.daos.InstitutionMasterDAO;
import com.schooltrix.daos.ParentDetailsDAO;
import com.schooltrix.daos.SchoolMasterDAO;
import com.schooltrix.daos.UserMasterDAO;
import com.schooltrix.hibernate.DomainControl;
import com.schooltrix.hibernate.FranchiseMaster;
import com.schooltrix.hibernate.InstitutionMaster;
import com.schooltrix.hibernate.SchoolMaster;
import com.schooltrix.managers.ServiceFinder;

public class InstitutionMasterDWR {

	HttpServletResponse response = WebContextFactory.get().getHttpServletResponse();
	HttpServletRequest request = WebContextFactory.get().getHttpServletRequest();
	HttpSession session = WebContextFactory.get().getSession();
	
public List getFranchiseMasterDetails() {
    	
		SchoolMasterDAO schoolMasterdao =null;
		List<Object[]> franchiseList= new ArrayList<Object[]>();
		List instList= new ArrayList();
		try {
			// later move this to institutionmasterdao
			schoolMasterdao = (SchoolMasterDAO)ServiceFinder.getContext(request).getBean("SchoolMasterHibernateDao"); 		
			instList = schoolMasterdao.getFranchiseList();//for time being-inert all record with im_id = 0;

			for (int i = 0; i < instList.size(); i++) {
				String[] oioio = new String[2];
				FranchiseMaster ioio = (FranchiseMaster)instList.get(i);
				System.out.println("--90---"+ioio.getFranName());
				oioio[0] = ioio.getId()+"";
				oioio[1] = ioio.getFranName();
				franchiseList.add(oioio);
			}
				return franchiseList;
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

	public String saveInstitution(String  insName,  String insSName,   String  insAddr,  String  insCity,  String insState,  String insContPerson,  String insEmailID,   
			String  insMobile,  String insLandLine,String franchise,   String  insActive) {
	
		System.out.println("ac--"+insActive+"--"+insName+"---"+insAddr+"..."+insCity+"8888"+session);   	
		InstitutionMasterDAO imMasterDao =null;    	
		DomainDAO domainDAO 	=  null;
		try {
				imMasterDao = (InstitutionMasterDAO)ServiceFinder.getContext(request).getBean("InstitutionMasterHibernateDao"); 		
				InstitutionMaster imInputData = new InstitutionMaster();
				imInputData.setActive(insActive);
				imInputData.setAddress(insAddr);
				imInputData.setCity(insCity);
				imInputData.setContactPerson(insContPerson);
				imInputData.setEmailId(insEmailID);
				imInputData.setLandline(insLandLine);
				imInputData.setMobile(insMobile);
				imInputData.setName(insName);
				imInputData.setShortName(insSName);
				imInputData.setStateId(insState);
				imInputData.setFranchiseId(franchise);
				imMasterDao.save(imInputData);
				//session.setAttribute("shortNameTemp", insSName);//for default selection
				
				
				try {
					domainDAO = (DomainDAO)ServiceFinder.getContext(request).getBean("DomainContlHibernateDao"); 		
					DomainControl domainData = new DomainControl();
					domainData.setShortName(insSName);
					//domainData.setUrl(insSName+".schooltrix.com");
					//domainData.setUrl(insSName+".schoolzippy.com");//2013-07-23
					domainData.setUrl(insSName+".schooltrix1.com");
					domainData.setDedicated("Y");
					domainDAO.save(domainData);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				return "saved";
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
	
	public String isIMShortNameCheck(String shortname) {
		
		
		InstitutionMasterDAO institutionMasterDAO = null;
		 ParentDetailsDAO parentDetailsDao = null;
		 List userList = new ArrayList();
		 if(shortname != null ){
			 try {
				 institutionMasterDAO = (InstitutionMasterDAO)ServiceFinder.getContext(request).getBean("InstitutionMasterHibernateDao"); 		
				 userList =  institutionMasterDAO.findByPropertyList("shortName", shortname);
			} catch (BeansException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		 }

		
		if(userList.size()>0){			
			return "true";
		}			
		return "false";
	}
	


}
