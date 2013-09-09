package com.schooltrix.dwr;
import java.io.File;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.Writer;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.RandomStringUtils;
import org.directwebremoting.WebContextFactory;
import org.json.JSONArray;
import com.schooltrix.daos.SentEmailDAO;
import com.schooltrix.hibernate.SentEmail;
import com.schooltrix.managers.ServiceFinder;

public class SentEmailDWR {
	HttpServletResponse response = WebContextFactory.get().getHttpServletResponse();
	HttpServletRequest request = WebContextFactory.get().getHttpServletRequest();
	HttpSession session = WebContextFactory.get().getSession();
	
		public String sendEmailCall(String  fromEmailId, String emailSubject, String emailBodyText,String sentUsersCount,String usersList,  String  IM_ID, String  BM_ID, String  SM_ID) {
			
			SentEmailDAO sentEmailDao =null;
			JSONArray userJson = null;
/*	not req here ...working code	
 * 	try {
				 userJson =new JSONArray(usersList);
				
				for (int i = 0; i < userJson.length(); i++) {
					JSONObject a = userJson.getJSONObject(i);
					System.out.println(a+":::");
					System.out.println("lastname::"+a.getString("Last_Name"));
					System.out.println("First_Name::"+a.getString("First_Name"));
					System.out.println("Mobile::"+a.getString("Mobile"));
				}
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}*/
			
			try {
				userJson =new JSONArray(usersList);
	        		System.out.println(":emailBodyText:"+ emailBodyText+":sentUsersCount:"+ sentUsersCount+":fromEmailId:"+ fromEmailId+":usersList:"+usersList+"--IM_ID"+IM_ID);
			if(userJson.length()>0){
				sentEmailDao = (SentEmailDAO)ServiceFinder.getContext(request).getBean("SentEmailHibernateDao"); 		
				SentEmail emailInputData = new SentEmail();
				emailInputData.setImId(IM_ID);//?
				emailInputData.setSmId(SM_ID);//?
				emailInputData.setBmId(BM_ID);//?
				
				emailInputData.setEmailBody(emailBodyText);
				emailInputData.setEmailFrom(fromEmailId);
				emailInputData.setNoToSent(sentUsersCount);
				emailInputData.setEmailSubj(emailSubject);
		
				emailInputData.setStatus("0");
				
				String fileName = writeIntoFolder(userJson,IM_ID);
				System.out.println("filename---"+fileName);
				if(fileName.equalsIgnoreCase("")){
				//smsInputData.setUserListFileName("temp.txt");
				}else{
					emailInputData.setUserListFileName(fileName);
				}
				SimpleDateFormat sdf =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				
				emailInputData.setReqTime(java.sql.Timestamp.valueOf(sdf.format(new Date())));
				emailInputData.setProcessedTime(java.sql.Timestamp.valueOf(sdf.format(new Date())));
				
				sentEmailDao.save(emailInputData);
								
				return "success";
		
			}else{
				return "No Users";
			}
			}catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
				return "error";
			}
			
		}
		
	   public  String writeIntoFolder(JSONArray finalJSON,String im_id){
			try{
				String fileLocation = "";
				File emaildir = null;
				String fileName = "";
				
				try {
					Properties properties = new Properties();
					InputStream is = getClass().getResourceAsStream("../alerts/AlertsPaths.properties");
					properties.load(is);
					fileLocation = (String) properties.getProperty("Emailfoldername");
					
					 String institutionName = (String)session.getAttribute("IM_SN");
					 	 System.out.println(institutionName+"--institutionName-");
					
					 	 fileLocation = fileLocation+"/"+institutionName+"/";
					System.out.println("fil-------"+fileLocation);
					emaildir = new File(fileLocation);
					properties.clear();
					if (!emaildir.isDirectory()) {
						System.out.println("********** Directory Not exists so create it *************");
						emaildir.mkdirs();
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				Writer jsonEmailTemp = null;
				try {
					if (finalJSON.length() > 0) {
						System.out.println(fileLocation);
						 fileName  =  getFileUniqueName();
						 System.out.println("filename--unique--:"+fileName);
						File file =  new File(""+ fileLocation +fileName);
						if(file.exists()){
							jsonEmailTemp = new BufferedWriter(new FileWriter(file));
							jsonEmailTemp.write(finalJSON.toString().trim());
							jsonEmailTemp.flush();
							jsonEmailTemp.close();
							return fileName;
						}else{
							if(file.createNewFile()){
								jsonEmailTemp = new BufferedWriter(new FileWriter(file));
								jsonEmailTemp.write(finalJSON.toString().trim());
								jsonEmailTemp.flush();
								jsonEmailTemp.close();
								return fileName;
							}
								
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
					return "";
				} finally {
					jsonEmailTemp = null;					
				}
						
				return "";
			}catch (Exception e) {
				e.printStackTrace();
				return "";
			}
		}
		private String getFileUniqueName() {
			String extension = "json";
			return String.format("%s.%s", RandomStringUtils.randomAlphanumeric(10), extension);
		}
}
