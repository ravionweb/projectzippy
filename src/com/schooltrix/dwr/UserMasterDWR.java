package com.schooltrix.dwr;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.directwebremoting.WebContextFactory;
import org.springframework.beans.BeansException;

import com.schooltrix.daos.InstitutionMasterDAO;
import com.schooltrix.daos.ParentDetailsDAO;
import com.schooltrix.daos.StaffDetailsDAO;
import com.schooltrix.daos.UserMasterDAO;
import com.schooltrix.daos.UserTypeMasterDAO;
import com.schooltrix.hibernate.InstitutionMaster;
import com.schooltrix.hibernate.StaffDetails;
import com.schooltrix.hibernate.UserMaster;
import com.schooltrix.hibernate.UserType;
import com.schooltrix.managers.ServiceFinder;

public class UserMasterDWR implements Serializable {


	HttpServletResponse response = WebContextFactory.get().getHttpServletResponse();
	HttpServletRequest request = WebContextFactory.get().getHttpServletRequest();
	HttpSession session = WebContextFactory.get().getSession();
	
	
	public String isUserIDCheck(String userID,String im_id) {
		
		
		UserMasterDAO userMasterDao = null;
		 ParentDetailsDAO parentDetailsDao = null;
		 List userList = new ArrayList();
		 if(userID != null && userID !=""){
			 try {
				 System.out.println("im_idddddddd"+im_id);
				 userMasterDao = (UserMasterDAO)ServiceFinder.getContext(request).getBean("UserMasterHibernateDao"); 		
				 //userList =  userMasterDao.findByPropertyList("userId", userID);
				 userList =  userMasterDao.adminUniqueIDCheck("userId", userID, "imId", im_id);
				 
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
	
	
	
	
	
	
	
	
	
	
	
      //19 params
	 public String addUser(String  userID,  String password,   String  fname,  String  lname,  String   userRights,  String 	dob,  String designation,  String email,   
				String  mobile,  String landline,   String  isParent,String  addr1,  String addr2,   String  city,  String  country,  String   state,  String 	fileUP,String active  ) {

		 	System.out.println("ac--"+active+"--"+fileUP+"---"+userRights+"..."+city+"8888"+session);

			
			UserMasterDAO userMasterDao =null;
			StaffDetailsDAO staffDetailsDao = null;
			
			try {
				userMasterDao = (UserMasterDAO)ServiceFinder.getContext(request).getBean("UserMasterHibernateDao"); 		
				UserMaster userInputData = new UserMaster();
					userInputData.setImId(new Long(1));//?
					userInputData.setSmId(new Long(1));//?
					userInputData.setBmId(new Long(1));//?
					userInputData.setUtId(new Long(1));//?
					userInputData.setUserId(userID);
					userInputData.setPassword(password);
					userInputData.setActive(active);
					userMasterDao.save(userInputData);//---------------------ONE
					
					System.out.println("testtttobject-------"+userInputData.getUmId());
					System.out.println("testtttobject-------"+userInputData.getUserId());
						 
					
				staffDetailsDao = (StaffDetailsDAO)ServiceFinder.getContext(request).getBean("StaffDetailsHibernateDao");
				StaffDetails staffData = new StaffDetails();
					staffData.setActive(active);
					staffData.setAddress1(addr1);
					staffData.setAddress2(addr2);
					staffData.setCity(city);
					//staffData.setCountry(country);
					staffData.setDesignation(designation);
					staffData.setDob(dob);
					staffData.setEmail(email);
					staffData.setFirstName(fname);
					staffData.setLandline(landline);
					staffData.setLastName(lname);
					staffData.setMobile(mobile);
					staffData.setPhoto(fileUP);
					staffData.setState(state);
					staffData.setUmId(userInputData.getUmId());//?from above we need usertype ID//-----------------TWO
					
				//	staffDetailsDao.save(staffData);
					
					
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
//BufferedImage
	 public String  addUserTest(String path) {
		 try {
			System.out.println("in test fileupload");
		/*		String storePath="C:/Program Files/Apache Software Foundation/Tomcat 7.0/webapps/Schooltrix/uploaded";
				File outputFile1 = new File(storePath+"/"+path);

				int len1;
				OutputStream out1 = new FileOutputStream(outputFile1);
				byte[] buf1 = new byte[1024];
				while ((len1 = image.read(buf1)) > 0) {
					out1.write(buf1, 0, len1);
				}

				image.close();
				out1.close(); 
				*/
	/*		System.out.println("image---"+image);
				File file = new File("C:/Program Files/Apache Software Foundation/Tomcat 7.0/webapps/Schooltrix/uploaded/file1.png");
				try
				{
				boolean flag=  ImageIO.write(image, "PNG", file);
				System.out.println("flag---"+flag);
				}
				catch (Exception e) {
					e.printStackTrace();
					}
			 */
			
			return "saved";
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "saved33333333";
		}
	}
	 public String getInclude() throws ServletException, IOException
	 {
	     return WebContextFactory.get().forwardToString("/success.jsp");
	 }
	 
	 
	 
}
