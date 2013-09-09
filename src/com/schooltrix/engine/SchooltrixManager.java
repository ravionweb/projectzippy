package com.schooltrix.engine;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.sql.CallableStatement;
import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Properties;
import java.util.regex.*;

import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;




public class SchooltrixManager {
	
	private org.apache.log4j.Logger log = Logger.getLogger(SchooltrixManager.class);
	private Connection conn;
	
	public SchooltrixManager() {
	    
	    Properties properties	= getProperties();	    
	    try{
        	    String url 		= properties.getProperty("ST_DB_URL");
        	    String dbName 	= properties.getProperty("ST_DB_NAME");
        	    String driver 	= properties.getProperty("ST_DB_DRIVER");
        	    String userName	= properties.getProperty("ST_DB_USER_NAME");
        	    String password	= properties.getProperty("ST_DB_PASSWORD");     
        	    Class.forName(driver).newInstance();        	
        	    this.conn = DriverManager.getConnection(url+dbName,userName,password);
	    }catch (Exception _ex) {
	    	_ex.printStackTrace();
	    	//log.error("ERROR in Main",_ex);
	    }	
	}
	
	private Properties getProperties() {

	    Properties properties	=	 new Properties();
	    InputStream is		=	null;
	    try{		
		is= getClass().getResourceAsStream ("/com/schooltrix/engine/SMS.PROPERTIES");
		properties.load(is);		
	    }catch(Exception _ex){
		_ex.printStackTrace();
		log.error(" Exception  in reading Properites in Schooltrix "+_ex);			    
	    }finally{
			try {
			    is.close();
			} catch (IOException _ex) {			}
	    }	    
	    return properties;	
	}


	
	public void processSMS(){
		Connection con		= null;	      
		CallableStatement st = null;
		try {
			int flag_records = 0;
			 con		= this.conn;	      
			 st 	= con.prepareCall( "{call SchoolTrix_GET_SMS(?)}");
			//ss.sno, ss.IM_ID, ss.BM_ID, ss.SM_ID, ss.sms_body,ss.no_to_sent ,ss.user_list_file_name, ss.status,ss.req_time,ss.processed_time
			//A:All, P:PROCESSED, U:UN PROCESSED	      	
			st.setString("ACTION", "U");
			
			ResultSet result = st.executeQuery();
			
			while(result.next()){
				System.out.println("---"+result.getBigDecimal(1));
				log.info("---"+result.getString(3).trim());
				result.getInt(1);//sno---------
				result.getString(2).trim();//IM_ID-------
				result.getString(5).trim();//smsbody-------
				result.getString(7).trim();//user_list_file_name--------
				result.getString(8).trim();//status-------------
			
				String ShortName = getInstitutionShortName(result.getString(2).trim());
				
				flag_records++;
				try {
					proceessFiletoSend(ShortName,result.getInt(1),result.getString(2).trim(),result.getString(5).trim(),result.getString(7).trim());
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
			}
			if(flag_records<1){
				System.out.println("Nothing is processed");
			}      
			
		} catch (SQLException e) {
			try {
				con.close();
				st.close();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
		}			
	}
	
	private String getInstitutionShortName(String im_id) {
		// TODO Auto-generated method stub
		String query = "select Short_Name from institution_master where IM_ID = '"+im_id+"' and Active='Y'";
		
		try {
			PreparedStatement st = conn.prepareStatement(query);
			ResultSet rs = st.executeQuery();
				rs.next();
				  System.out.println("----------------  "+rs.getString(1));
				 return rs.getString(1);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
		
	}

	private void proceessFiletoSend(String Shortname,int sno, String IM_ID, String smsBody, String fileName) {
		// TODO Auto-generated method stub
		
		String contents = readFile(Shortname,fileName);
		if(!contents.equalsIgnoreCase("")){		
			
			JSONTokener jsonTokener		= new JSONTokener(contents);
			JSONArray jsonMainArray	= null;
			//JSONObject jsonMainObject	= null;
			
			try{
				jsonMainArray = new JSONArray(jsonTokener);
			}catch(Exception e) {e.printStackTrace();}
			
			StringBuffer mobileNumbers = new StringBuffer("");
			for (int i=0; i<jsonMainArray.length(); i++) {
			    JSONObject item = jsonMainArray.getJSONObject(i);
			    String lastName = item.getString("Last_Name");
			    String firstName = item.getString("First_Name");
			    String mobile	=	item.getString("Mobile");
			    System.out.println("-->"+lastName+"::"+firstName+"::"+mobile);
			    mobileNumbers.append("91");
			    mobileNumbers.append(item.getString("Mobile"));
			    mobileNumbers.append(",");
			    
			}			
			
			System.out.println("mobileNumbers:::"+mobileNumbers);
			//sendSMS(mobileNumbers, smsBody);//from sms body with names here ....further
			Connection con = null;
			CallableStatement st = null;
			try {
				 con		= this.conn;
				 st 	= con.prepareCall( "{call SCHOOLTRIX_SMS_UPDATE(?,?,?)}");			
				
				st.setInt("RECORDID", sno);
				st.setString("ACTION", "S");
				st.setString("FILE_Name", fileName);
				st.executeQuery();
				
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				try {
					con.close();
					st.close();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				e.printStackTrace();
			}
			
	}
		
	}

	private String readFile(String Shortname, String fileName) {
		// TODO Auto-generated method stub
		try{
		StringBuilder contents = new StringBuilder();
		String fileLocation="";		
		try {
			Properties properties	=	 new Properties();
			InputStream is= getClass().getResourceAsStream ("../alerts/AlertsPaths.properties");
			properties.load(is);
			fileLocation	= (String)properties.getProperty("SMSfoldername");
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		BufferedReader input = new BufferedReader(new FileReader(new File(fileLocation+Shortname+"/"+fileName)));

		try {
			String line = null; 
			while (( line = input.readLine()) != null){
				contents.append(line);			    		
			}
			System.out.println("ccc---"+contents);
			 return contents.toString();
		} catch(Exception e){
			e.printStackTrace();
		} finally {
			input.close();
		}
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	return "";
}

	private void sendSMS(StringBuffer mobileNumber,String smsBody) {
		ProcessMSG pmsg = new ProcessMSG();
		//String smsResponse="";
		/*String ACK		= "ERROR";
		boolean statusMsg = false; 	*/
			//smsResponse	= "Your request processed successfully";
		
			pmsg.sendSMS(mobileNumber+"", smsBody);
				
		
	/*	if(ACK.indexOf("MESSAGEACK")!=-1){			
		    statusMsg		= true;
		}
		System.out.println("msg sent::"+statusMsg);
		log.info("msg sent::"+statusMsg);*/
	}
	
//Email Starts HERE.................................................................................................................
	public void processEmail(){
		try {
			int flag_records = 0;
			Connection con		= this.conn;	      
			CallableStatement st 	= con.prepareCall( "{call SchoolTrix_GET_Email(?)}");
			//ss.sno, ss.IM_ID, ss.BM_ID, ss.SM_ID, ss.sms_body,ss.no_to_sent ,ss.user_list_file_name, ss.status,ss.req_time,ss.processed_time
			//A:All, P:PROCESSED, U:UN PROCESSED	      	
			st.setString("ACTION", "U");
			
			ResultSet result = st.executeQuery();
			
			while(result.next()){
				System.out.println("---"+result.getBigDecimal(1));
				//1			2							3					4			5							6									7				8							9									10				11				12
				//se.sno, se.IM_ID, se.BM_ID, se.SM_ID, se.email_from,se.email_subj ,se.email_body, se.no_to_sent,se.user_list_file_name,se.status,se.req_time,se.processed_time FROM sent_email se WHERE se.status=0;
				result.getInt(1);//sno---------
				result.getString(2).trim();//IM_ID-------
				result.getString(5).trim();//-------email_from
				result.getString(6).trim();//-------email_subj
				result.getString(7).trim();//-------email_body
				result.getString(8).trim();//-------no_to_sent
				result.getString(9).trim();//-------user_list_file_name
				String ShortName = getInstitutionShortName(result.getString(2).trim());
				
			System.out.println("sno--"+result.getInt(1));
				flag_records++;
				try {
					proceessEmailFiletoSend(ShortName,result.getInt(1),result.getString(2).trim(),result.getString(5).trim(),result.getString(6).trim(),result.getString(7).trim(),result.getString(9).trim());
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(flag_records<1){
				System.out.println("Nothing is processed");
			}
			con.close();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}			
	}
	
	private void proceessEmailFiletoSend(String shortName,int sno, String IM_ID, String emailFrom, String emailSubj, String emailBody, String fileName) {
		// TODO Auto-generated method stub
		
		String contents = readEmailFile(shortName,fileName);
		if(!contents.equalsIgnoreCase("")){		
			
			JSONTokener jsonTokener		= new JSONTokener(contents);
			JSONArray jsonMainArray	= null;
			//JSONObject jsonMainObject	= null;
			
			try{
				jsonMainArray = new JSONArray(jsonTokener);
			}catch(Exception e) {e.printStackTrace();}
		
			String[] emailIDs = new String[jsonMainArray.length()];
					
			for (int i=0; i<jsonMainArray.length(); i++) {
			    JSONObject item = jsonMainArray.getJSONObject(i);
			    String lastName = item.getString("Last_Name");
			    String firstName = item.getString("First_Name");
			   emailIDs[i]	=	item.getString("Email");
			    System.out.println("-->"+lastName+"::"+firstName+"::"+item.getString("Email"));			 
			    
			    
			}			
			
			sendEmail(emailIDs,emailSubj, emailBody,new String[1],emailFrom);
			//sendEmail(emailIDs,emailSubj, emailBody,new String[1]);//from email body with names here ....further
			
			try {
				Connection con		= this.conn;
				CallableStatement st 	= con.prepareCall( "{call SCHOOLTRIX_Email_UPDATE(?,?,?)}");			
				
				st.setInt("RECORDID", sno);
				st.setString("ACTION", "S");
				st.setString("FILE_Name", fileName);
				st.executeQuery();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
	}
		
	}

		private String readEmailFile(String shortName, String fileName) {
			// TODO Auto-generated method stub
			try{
			StringBuilder contents = new StringBuilder();
			String fileLocation="";		
			try {
				Properties properties	=	 new Properties();
				InputStream is= getClass().getResourceAsStream ("../alerts/AlertsPaths.properties");
				properties.load(is);
				fileLocation	= (String)properties.getProperty("Emailfoldername");
			}catch(Exception e) {
				e.printStackTrace();
			}
			
			BufferedReader input = new BufferedReader(new FileReader(new File(fileLocation+shortName+"/"+fileName)));
	
			try {
				String line = null; 
				while (( line = input.readLine()) != null){
					contents.append(line);			    		
				}
				System.out.println("ccc---"+contents);
				 return contents.toString();
			} catch(Exception e){
				e.printStackTrace();
			} finally {
				input.close();
			}
			}catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		return "";
	}
		//	sendEmail(emailIDs,emailSubj, emailBody,attch[],"Schooltrix.com");
	private void sendEmail(String recipients[],String emailSubj,String emailBody,String[] attachements,String emailFrom) {
		//sendMail(String recipients[], String subject, String message,String attachments[], String from)
		
			try {
				ProcessEmail pmsg = new ProcessEmail();	
				pmsg.sendMail(recipients, emailSubj, emailBody, attachements,emailFrom);
				System.out.println("emailsent::");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	
	
	
	
	public static void main(String[] args) {
		SchooltrixManager stMgr = new SchooltrixManager();		
		//stMgr.processSMS();
		stMgr.processEmail();
	}
	
	
	
}
