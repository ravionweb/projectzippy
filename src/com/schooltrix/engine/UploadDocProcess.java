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
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import net.sf.cglib.core.EmitUtils;

import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

public class UploadDocProcess {

	private org.apache.log4j.Logger log = Logger.getLogger(UploadDocProcess.class);
	private Connection conn;
	private String uploadDocPath ;
	
	public UploadDocProcess() {
		// TODO Auto-generated constructor stub
			
		    Properties properties	= getProperties();	    
		    try{
		    	    String url 		= properties.getProperty("ST_DB_URL");
		    	    String dbName 	= properties.getProperty("ST_DB_NAME");
		    	    String driver 	= properties.getProperty("ST_DB_DRIVER");
		    	    String userName	= properties.getProperty("ST_DB_USER_NAME");
		    	    String password	= properties.getProperty("ST_DB_PASSWORD");     
		    	   
		    	    Class.forName(driver).newInstance();        	
		    	    this.conn = DriverManager.getConnection(url+dbName,userName,password);
		    	    
		    	    uploadDocPath  = properties.getProperty("UploadSMS_Emailfoldername");
		    	    System.out.println(uploadDocPath+"---uploadDocPath--"+conn);
		    }catch (Exception _ex) {
		    	_ex.printStackTrace();
		    	//log.error("ERROR in Main",_ex);
		    }	
		}
	
	private Properties getProperties() {
	
	    Properties properties	=	 new Properties();
	    InputStream is		=	null;
	    try{		
		is= getClass().getResourceAsStream ("/com/schooltrix/engine/SMS.PROPERTIES");//DB,sms,email,FolderName  props are there in this prop
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
	
	public void callingSMSJob(){
		Connection con		= null;	      
		CallableStatement st = null;
		try {
			int flag_records = 0;
			 con		= this.conn;	      
			 st 	= con.prepareCall( "{call SchoolTrix_Upload_SMS(?)}");
			//ud.sno, ud.IM_ID, ud.BM_ID, ud.SM_ID, ud.class_id,ud.to_whome ,ud.upload_type, ud.file_name
			//A:All, P:PROCESSED, U:UN PROCESSED	      	
			st.setString("ACTION", "U");
			
			ResultSet result = st.executeQuery();
			
			while(result.next()){
				System.out.println("---"+result.getBigDecimal(1));
				log.info("---"+result.getString(3).trim());
				result.getInt(1);//sno---------
				result.getString(2).trim();//IM_ID-------
				result.getString(3).trim();//SM_ID-------
				result.getString(4).trim();//BM_ID-------
				result.getString(5).trim();//class_id-------class id
				result.getString(6).trim();//to_whome--------
				result.getString(7).trim();//upload_type--------
				result.getString(8).trim();//file_name-------------
			
				String ShortName = getInstitutionShortName(result.getString(2).trim());
				
				if (!result.getString(5).trim().equalsIgnoreCase(null) || !result.getString(5).trim().equalsIgnoreCase("")) {					
				flag_records++;
				//can we use uploaddoc obj for data putting purpose--2013-04-01
				proceessSMSFiletoSend(ShortName,result.getInt(1),result.getString(2).trim(),result.getString(3).trim(),result.getString(4).trim(),result.getString(5).trim(),result.getString(6).trim(),result.getString(7).trim(),result.getString(8).trim());
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
	
	private void proceessSMSFiletoSend(String Shortname,int sno, String im_id, String sm_id, String bm_id, String class_id,String to_whome , String upload_type,  String fileName) {
		// TODO Auto-generated method stub
		//1)get the users list---user Fname ,Lname,mobile number
				//parent details only depends on Class which select(like class-I,Class-II....etc or All classes
				List smsList = getAuthorizedUsersList(im_id,sm_id,bm_id,class_id,"sms");
			
				//these r from properties file late depending on im_id (and sm_id..)
			
				String message = "Dear Parent, New "+upload_type.toUpperCase()+"is Uploaded.";
				
				System.out.println(message+":::message"+sno+":sno:"+fileName);
				
				//ProcessMSG ps = new ProcessMSG();
				//	ps.sendUploadDocSMS(message, smsList);//**********************************Main
				
					Connection con = null;
					CallableStatement st = null;
					try {
						 con		= this.conn;
						 System.out.println("con--"+con);
						 st 	= con.prepareCall( "{call SCHOOLTRIX_Upload_SMS_UPDATE(?,?,?)}");			
						
						st.setInt("RECORDID", sno);
						st.setString("ACTION", "S");
						st.setString("FILEName", fileName);
						st.executeQuery();
						
						System.out.println("----End---");
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

	
	public void callingEmailJob() {
		Connection con		= null;	      
		CallableStatement st = null;
		try {
			int flag_records = 0;
			 con		= this.conn;	      
			 st 	= con.prepareCall( "{call SchoolTrix_Upload_Email(?)}");
			//ud.sno, ud.IM_ID, ud.BM_ID, ud.SM_ID, ud.class_id,ud.to_whome ,ud.upload_type, ud.file_name
			//A:All, P:PROCESSED, U:UN PROCESSED	      	
			st.setString("ACTION", "U");
			
			ResultSet result = st.executeQuery();
			
			while(result.next()){
				System.out.println("---"+result.getBigDecimal(1));
				log.info("---"+result.getString(3).trim());
				result.getInt(1);//sno---------
				result.getString(2).trim();//IM_ID-------
				result.getString(3).trim();//sM_ID-------
				result.getString(4).trim();//bM_ID-------
				result.getString(5).trim();//class_id-------
				result.getString(6).trim();//parent/student--------
				result.getString(7).trim();//upload_type--------
				result.getString(8).trim();//file_name-------------
			
				String ShortName = getInstitutionShortName(result.getString(2).trim());
				if (!result.getString(5).trim().equalsIgnoreCase(null) || !result.getString(5).trim().equalsIgnoreCase("")) {					
				flag_records++;
				//can we use uploaddoc obj for data putting purpose--2013-04-01
				proceessEmailFiletoSend(ShortName,result.getInt(1),result.getString(2).trim(),result.getString(3).trim(),result.getString(4).trim(),result.getString(5).trim(),result.getString(6).trim(),result.getString(7).trim(),result.getString(8).trim());
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
	
	
	private void proceessEmailFiletoSend(String Shortname,int sno, String im_id, String sm_id, String bm_id, String class_id,String to_whome , String upload_type,  String fileName) {
		// TODO Auto-generated method stub
		
		//1)get the users list---user Fname ,Lname,emailID
		//parent details only depends on Class which select(like class-I,Class-II....etc or All classes
		
		List emailList = getAuthorizedUsersList(im_id,sm_id,bm_id,class_id,"email");//************************************88
		
		String[] users = new String[emailList.size()]; 
		for (int i = 0; i < emailList.size(); i++) {
			users[i] =(String) emailList.get(i);
			System.out.println("users[i]--"+users[i]);
		}
		//these r from properties file late depending on im_id (and sm_id..)
		String subject = "New "+upload_type.toUpperCase()+"is Uploaded";
	//	String message = "<html><head >Dear Parents,<br/></head><br/><body style='font-family:verdana;'> New "+upload_type.toUpperCase()+"is Uploaded. Please go through the attachment. </br>\n</br></body></html>\n\n";
		String message ="<div  id=\"yes2\" style=\"width:93%; padding:20px; font-family:Arial; font-size:13px; border:#999999 1px solid; \"> " +
				"<p>Dear Parents,<br/><br/></p><p>New "+upload_type.toUpperCase()+" is Uploaded. Please go through the attachment. </p> <p><br/>Regards,</p><p>Team "+Shortname+"</p></div>";
		
		
		/*		String 	from = "VT Intraday Report<intradayreports@vantagetrade.com>";*/
		String 	from = "Upload From Admin<contactus@schooltrix.com>";
/*		String 	from = "Upload From Admin<uploaddoc@"+Shortname+".com>";
*/		String imagePath = "";
		
		String folderFileName = uploadDocPath+Shortname+"/"+upload_type+"/"+fileName;
		
		System.out.println("fo---"+folderFileName+"-->"+message+"<------>"+subject);
		ProcessEmail pe = new ProcessEmail();
		pe.sendUploadDocMail(users, subject, message, new String[]{folderFileName}, from, imagePath);
		
		
		
			Connection con = null;
			CallableStatement st = null;
			try {
				 con		= this.conn;
				 st 	= con.prepareCall( "{call SCHOOLTRIX_Upload_Email_UPDATE(?,?,?)}");			
				
				st.setInt("RECORDID", sno);
				st.setString("ACTION", "S");
				st.setString("FILEName", fileName);
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

	private List getAuthorizedUsersList(String im_id,String sm_id,String bm_id,String class_id,String smsOrEmail) {
		// TODO Auto-generated method stub
		String query = null;
		List usersList = new ArrayList();
		String channel = "";
		if (smsOrEmail.equalsIgnoreCase("sms")) {
			channel = "Mobile";
		} else if(smsOrEmail.equalsIgnoreCase("email")) {
			channel = "Email";
		}
		
		
		t:if(class_id.equalsIgnoreCase("0")){
			
			if (bm_id.equalsIgnoreCase("0")) {
				
				if (sm_id.equalsIgnoreCase("0")) {
					query = "select "+channel+" from parent_details where um_id in (select um_id from user_master where IM_ID='" + im_id +"' and UT_ID = '2' and Active='Y') and isDefault='Y' and Active='Y'";//AAALL Schoollll
				break t;
				}
				query = "select "+channel+" from parent_details where um_id in (select um_id from user_master where IM_ID='" + im_id+ "' and SM_ID='" +sm_id+ "' and UT_ID = '2' and Active='Y') and isDefault='Y' and Active='Y'";//All Branches
				break t;
			}//bm_id
			
			query = "select "+channel+" from parent_details where um_id in (select um_id from user_master where IM_ID='" +im_id+ "'and SM_ID='" +sm_id+ "' and BM_ID='" +bm_id+ "' and UT_ID = '2' and Active='Y') and isDefault='Y' and Active='Y'";//all classes under one branch
		
/*			query = "select "+channel+" from parent_details where um_id in (select um_id from user_master where IM_ID='"+im_id+"' and User_ID like 'Par%' and Active='Y') and Active='Y'";
*/		}else{
			System.out.println("in class section");
		
			query = "select "+channel+" from parent_details where Active='Y' and isDefault='Y'  and PD_ID in ( select pd_id from parent_student_map where Active='Y' and Stu_id in (select Stu_ID from student_section_map where Active='Y' and IM_ID='" +
					im_id+ "' and SM_ID='" + sm_id+ "' and BM_ID='" + bm_id + "' and SCM_ID in " +
					"( SELECT SCM_ID FROM section_class_map WHERE Active='Y' and CM_ID = " + class_id + " and bm_id = " +bm_id + ") ))";
			
/*			query = "select "+channel+" from parent_details where  Active='Y' and PD_ID in " +
					"( select pd_id from parent_student_map where  Active='Y' and Stu_id in " +
					"(select Stu_ID from student_section_map where  Active='Y' and IM_ID='"+im_id+"' and SM_ID='"+sm_id+"' and  BM_ID='"+bm_id+"' and SCM_ID in  " +
					"( SELECT SCM_ID FROM section_class_map WHERE Active='Y' and CM_ID  = "+class_id+") ))";
*/		}
		
		
		try {
			PreparedStatement st = conn.prepareStatement(query);
			ResultSet rs = st.executeQuery();
			while (rs.next()) {
			//we dnt need parent details here ,i think ...only email/sms id's	
				usersList.add(rs.getString(1));			
			}
			return usersList;
		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		}
	}

	private String readFile(String Shortname, String uploadType,String fileName) {
		// TODO Auto-generated method stub
		try{
		StringBuilder contents = new StringBuilder();
		String fileLocation=uploadDocPath;		
		
		BufferedReader input = new BufferedReader(new FileReader(new File(fileLocation+Shortname+"/"+uploadType+"/"+fileName)));

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
	
	public void TestEmailattchement() {
		
		
		String subject = "New Assignment Uploaded";
		String message = "New Assignment Uploaded-msg";
		String 	from = "VT Intraday Report<intradayreports@vantagetrade.com>";
		String imagePath = "";
		
		String fileName = "KMJ9qKKL9T.pdf";
		
		String folderFileName = uploadDocPath+"RS"+"/"+"Assignments"+"/"+fileName;
		
		
		ProcessEmail pe = new ProcessEmail();
		pe.sendUploadDocMail(new String[]{"bhanuchander33@gmail.com"}, subject, message, new String[]{folderFileName}, from, imagePath);
		
		
	}

	
	public static void main(String[] args) {
		UploadDocProcess udp = new UploadDocProcess();
	//	udp.TestEmailattchement();
		//udp.callingSMSJob();
		udp.callingEmailJob();
	
	
	
	}

}
