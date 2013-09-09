package com.schooltrix.TestTemp;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

import java.util.List;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import com.schooltrix.daos.BranchMasterDAO;
import com.schooltrix.daos.InstitutionMasterDAO;
import com.schooltrix.daos.SchoolMasterDAO;
import com.schooltrix.daos.SectionMasterDAO;
import com.schooltrix.daos.UserMasterDAO;
import com.schooltrix.hibernate.BranchMaster;
import com.schooltrix.hibernate.InstitutionMaster;
import com.schooltrix.hibernate.SchoolMaster;
import com.schooltrix.hibernate.SectionMaster;
import com.schooltrix.hibernate.UserMaster;
import com.schooltrix.managers.ServiceFinder;

//import com.schooltrix.daos.InstitutionMasterDao;
//import com.schooltrix.daos.SchoolMasterDAO;
//import com.schooltrix.hibernate.InstitutionMaster;
//import com.schooltrix.hibernate.SchoolMaster;

public class HiberDaoSupportTest {

//	ApplicationContext context = new FileSystemXmlApplicationContext("F:/Forceites/SchoolTrixWorkSpace/Schooltrix/WebContent/WEB-INF/applicationContext-hibernate.xml");
	ApplicationContext context = new FileSystemXmlApplicationContext("C:/Program Files/Apache Software Foundation/Tomcat 7.0/webapps/Schooltrix/WEB-INF/applicationContext-hibernate.xml");
	public HiberDaoSupportTest() {
		// TODO Auto-generated constructor stub
	}

	
	public void Test1() {
		
		InstitutionMasterDAO masterDao = (InstitutionMasterDAO)context.getBean("InstitutionMasterHibernateDao"); 
		
		InstitutionMaster im = new InstitutionMaster();
		im.setName("Bharathi Grammer School");
		im.setShortName("BGS");
		im.setAddress("chinthal");
		im.setCity("Hyderabad");
		im.setStateId("1");
		im.setContactPerson("Bharathi");
		im.setEmailId("Bharathi@123.com");
		im.setActive("Y");
		im.setMobile("9493555560");
		im.setLandline("040-23456789");
		
	//1)Save
	  try {
			masterDao.save(im);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		/*2)update
		 * try {
			
		System.out.println(""+im.getShortName());
		im 	= masterDao.findByProperty("shortName", "sfR1fS");
		im.setShortName("RST");
		im.setName("Radha school Tech");
		
		masterDao.update(im);
		System.out.println(""+im.getShortName());
		
		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/

		/*try {
			
			System.out.println(""+im.getShortName());
			
			im 	= masterDao.findByProperty("shortName", "RST");
			
			masterDao.delete(im);
			System.out.println(""+im.getShortName());
			
			
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}*/
		
		
		
		
		
		
				//im	= masterDao.findById(new Long(1));
		
		
	}
	
	
	
	public void Test2() {
		try {
			SchoolMasterDAO schoolDao = (SchoolMasterDAO)context.getBean("SchoolMasterHibernateDao");
			
			SchoolMaster sm = new SchoolMaster();
				sm.setActive("Y");
				sm.setAddress("Nampally");
				sm.setCity("Hyderabad");
				sm.setContactPerson("Sunitha");
				sm.setEmailId("sunitha@schooltrix.com");
				sm.setLandline("040-12034444");
				sm.setMobile("9956574738");
				sm.setName("Sunitha Grammer School");
				sm.setSchoolName("SGSchool");
				sm.setImId(new Long(1));
				sm.setStateId(new Long("1"));
				
				schoolDao.save(sm);
				
		} catch (BeansException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
			
	}
	
	public void Test3() {

		try {
			BranchMasterDAO branchDao = (BranchMasterDAO)context.getBean("BranchMasterHibernateDao");
			
			BranchMaster sm = new BranchMaster();
				sm.setActive("Y");
				sm.setAddress("Kukatpally");
				sm.setSmId(new Long("1"));
				sm.setBranchName("SGSchool Secondary");
				sm.setCity("Hyderabad");
				sm.setShortName("SGSS");
				sm.setContactPerson("rani");
				sm.setLandline("040-712034444");
				sm.setMobile("8056574738");
				sm.setStateId(new Long("1"));
				
				branchDao.save(sm);
				
		} catch (BeansException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
			
	
	}
	
	public void TestStruts() {
		try {
			SectionMasterDAO sctionDao = (SectionMasterDAO)context.getBean("SectionMasterDAO");
			
			SectionMaster sm = new SectionMaster();
				sm.setActive("Y");
				sm.setSectionName("Average");
				sctionDao.save(sm);
				
		} catch (BeansException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
		
		
		
		
	}
	
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//Schooltrix\WebContent\WEB-INF
		//ApplicationContext context = new ClassPathXmlApplicationContext("./Schooltrix/WebContent/WEB-INF/applicationContext-hibernate.xml");
		//ApplicationContext context = new ClassPathXmlApplicationContext("classpath*://WEB-INF//applicationContext-hibernate.xml");
		
		HiberDaoSupportTest hdst = new HiberDaoSupportTest();
		//hdst.Test1();
		//hdst.Test2();
		//hdst.Test3();
		//hdst.TestStruts();
		//hdst.Test2();
		//hdst.Test2();
		//hdst.Test2();
		//String userType,String fName,String lName,String imId
		hdst.getIdPassword("Par","Raresh12","chandra12","3");
		
/*		
		SchoolMaster sm = new SchoolMaster();
			sm.setSchoolName("Sharadha");
		sm.setSmAdrdess("panjagutta");
		sm.setSmCity("hyd");
		sm.setSmContactPerson("puja");
		sm.setSmCountry("india");
		sm.setSmEmailId("puja@schooltris.com");
		sm.setSmIsactive("yes");
		sm.setSmLandline("040-56454454");
		sm.setSmMobile("97878787878");
		sm.setSmState("AP");
		sm.setImId(new Long(2));
		//im = masterDao.findByProperty("imShortName", "RS");
		sm=masterDao.findById(new Long(1));
		sm.setSmName("SRK");
		try {
			masterDao.save(sm);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/
		
		
		
		//update
		//im = masterDao.findByProperty("imShortName", "RS");
		
/*		im = masterDao.findByProperty("imShortName", "RS");
		
		//System.out.println("first"+im.getImAdress());
		masterDao.delete(im);
		System.out.println(im.getImAdress());
		im.setImAdress("baladddnagar");
		masterDao.save(im);
		System.out.println(im.getImAdress());
		
		
		
		*/
		
		
		
		
		
		//List l=masterDao.findByProperty("im_short_name", "DRS");
		//List l = masterDao.findAll();
		//getInstitutionMaster
		//im = masterDao.findById(new Long(6));
		
		
		
//		im = masterDao.getInstitutionMaster("imShortName", "DTR");
	/*	List l = masterDao.findByPropertyList("imAdress", "paradise");
	
		for (int i = 0; i < l.size(); i++) {
			im = (InstitutionMaster)l.get(i);
			System.out.println(im.getImShortName());
		}
		*/
		//System.out.println(im.getImAdress());
		//System.out.println("size::"+l.size());
		
	}


private String getIdPassword(String userType,String fName,String lName,String imId) {
		
		if(userType != null){
			try {
				String userPass = "";
				String userID = null;
				
				StringBuffer uu = new StringBuffer();
				uu.append(userType);
				
				
				if (fName.length()>2) {
					uu.append(fName.substring(0, 3));
				}else{
					uu.append(fName);
				}
				
				if (lName.length()>2) {
					uu.append(lName.substring(0, 3));
				}else{
					uu.append(lName);
				}
				
				userID = uu+"";			
				UserMasterDAO userMasterDao = (UserMasterDAO)context.getBean("UserMasterHibernateDao");
				System.out.println("userID--"+userID+"-imId-"+imId);
				List userlist = userMasterDao.uniqueIDCheck("userId", userID,  "imId", imId);
				
				p:if (userlist.size()>0) {
					for (int k = 1; k < 100; k++) {
						 String modUserId = userID+k;
						int count =0;
						for (int i = 0; i < userlist.size(); i++) {
							UserMaster um = (UserMaster)userlist.get(i);
							if (modUserId.equalsIgnoreCase(um.getUserId())) {
								count++;
							}
						}
					if (count == 0) {
						userID = modUserId;
						break p;
					}
					
					}
				}
				
				//code to shuffle password
				StringBuffer stringBuffer=new StringBuffer();
				List<String> list=new ArrayList<String>();
				list.add("t");
				list.add("r");
				list.add("i");
				list.add("x");
	
				Collections.shuffle(list);  			  	
				Iterator<String> iterator		= list.iterator();	   		
				while(iterator.hasNext()){
					stringBuffer.append(iterator.next());
				}	   		
		  userPass							= stringBuffer.toString()+"1234";
		  System.out.println(userID+"-----"+userPass);
		  return userID+"~"+userPass;
			} catch (BeansException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return null;
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return null;
			}	
		}else{
		return null;
		  }
	}

}
