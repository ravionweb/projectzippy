package com.schooltrix.engine;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Authenticator;
import javax.mail.Part;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.log4j.Logger;

public class ProcessMSG {

	private org.apache.log4j.Logger log = Logger.getLogger(ProcessMSG.class);

	


	private String sms_ACK_folder;



	private SimpleDateFormat df = new SimpleDateFormat("dd MMM yyyy");



	private Properties mailProperties;

	/*
	 Using old API 
	 private String SMS_SERVICE_URL = "http://api.myvaluefirst.com/psms/servlet/psms.Eservice2";

	private String ADDRESS_TAG = "<ADDRESS FROM=\"{0}\" TO=\"{1}\" SEQ=\"{2}\" />";

	private String DATA_TAG = "<?xml version=\"1.0\" encoding=\"ISO-8859-1\"?>"
			+ "<!DOCTYPE MESSAGE SYSTEM \"http://127.0.0.1/psms/dtd/message.dtd\">"
			+ "<MESSAGE><USER USERNAME=\"{0}\" PASSWORD=\"{1}\"/>{2}</MESSAGE>";

	private String SMS_CHUNK = "<SMS UDH=\"0\" CODING=\"1\" TEXT=\"{0}\" PROPERTY=\"\" ID=\"1\">{1}</SMS>";*/

	/**
	 * Constructor
	 * 
	 */
	public ProcessMSG() {


		//this.sms_ACK_folder = mailProperties.getProperty("LiveSellUnSentLog");

	}

	/**
	 * Class to Get PasswordAuthenticator for smtp.
	 * 
	 * @author ramakanth
	 * 
	 */
	public class MyPasswordAuthenticator extends Authenticator {

		public PasswordAuthentication getPasswordAuthentication() {
			return new PasswordAuthentication(username, password);
		}

		private String username;

		private String password;

		public MyPasswordAuthenticator(String s, String s1) {
			username = s;
			password = s1;
		}
	}


	//sending sms using new API........................
	public String sendSMS(String mobileNumber, String smsText) {
		InputStream inputStream =null;
		String retval = "";
		Properties properties  = null;
		String SMS_SERVICE_URL = null;
		String AUTH_USER 	   = null;
		String AUTH_PWD 	   = null;
		String AUTH_SID 	   = null;
		String MSG_TYPE		   = null;
		String DR		  	   = null;
		try {
			 properties = new Properties();
			 inputStream = this.getClass().getResourceAsStream("SMS.properties");
			 properties.load(inputStream);
			 SMS_SERVICE_URL = properties.getProperty("SMS_SERVICE_URL");
			 AUTH_USER 		  = properties.getProperty("SMS_COUNTRY_AUTH_USER");
			 AUTH_PWD 	   = properties.getProperty("SMS_COUNTRY_AUTH_PWD");
			 AUTH_SID 	     = properties.getProperty("SMS_COUNTRY_AUTH_SID");
			 MSG_TYPE	 	= properties.getProperty("SMS_COUNTRY_MSG_TYPE");
			 DR			 	= properties.getProperty("SMS_COUNTRY_DR");
		} catch (Exception ex) {
		    System.out.println("ERROR:While Reading MailProperties.properties @ "+this.getClass().getName()+" of sendSMS()");
		}finally{
			if(inputStream !=null){
				try{
					inputStream.close();
					properties.clear();
				}catch (Exception e) {}
			}
		}
		try{
			StringBuffer postData= new StringBuffer(); ;
			//give all Parameters In String 
			postData.append("User=" + URLEncoder.encode(AUTH_USER,"UTF-8"));
			postData.append("&passwd=" + AUTH_PWD);
			postData.append( "&mobilenumber=" + mobileNumber);
			postData.append("&message=" + URLEncoder.encode(smsText,"UTF-8"));
			postData.append("&sid=" + AUTH_SID);
			postData.append("&mtype=" + MSG_TYPE);
			postData.append("&DR=" + DR);
			URL url = new URL(SMS_SERVICE_URL);
			HttpURLConnection urlconnection = (HttpURLConnection) url.openConnection();
			// If You Are Behind The Proxy Server Set IP And PORT else Comment Below 4 Lines
			//Properties sysProps = System.getProperties();
			//sysProps.put("proxySet", "true");
			//sysProps.put("proxyHost", "Proxy Ip");
			//sysProps.put("proxyPort", "PORT");

			urlconnection.setRequestMethod("POST");
			urlconnection.setRequestProperty("Content-Type","application/x-www-form-urlencoded");
			urlconnection.setDoOutput(true);
			OutputStreamWriter out = new OutputStreamWriter(urlconnection.getOutputStream());
			out.write(postData.toString());
			out.close();
			BufferedReader in = new BufferedReader(	new InputStreamReader(urlconnection.getInputStream()));
			String decodedString;
			while ((decodedString = in.readLine()) != null) {
				retval += decodedString;
			}
			System.out.println(retval+"----------------------");
			in.close();
		}catch (Exception e) {
			e.printStackTrace();
		}
		return retval;
	}
	
	//using old api...................................
	/**
	 * Method to send sms.
	 * 
	 * @param mobileNumber
	 * @param smsText
	 * @return SMS ACK as XML String
	 *//*
	private String sendSMS(String mobileNumber, String smsText, String smsSender) {

		Object[] array = new Object[] { mobileNumber };
		java.util.List toList = Arrays.asList(array);
		StringBuffer returnMsg;

		returnMsg = new StringBuffer("");
		try {

			HttpClient client = new HttpClient();
			PostMethod method = new PostMethod(SMS_SERVICE_URL);
			String dataParameter = getDataParameter(smsSender, toList, smsText);

			//System.out.println(dataParameter);
			method.addParameter("data", dataParameter);
			method.addParameter("action", "send");
			client.executeMethod(method);
			returnMsg.append(method.getResponseBodyAsString());
		} catch (HttpException e) {
			System.out.println("Http Exception in sending SMS."
					+ e.getMessage());
		} catch (IOException e) {
			System.out.println("IO Exception in sending SMS." + e.getMessage());
		}
		
		return returnMsg.toString();

	}*/

	/**
	 * The method format the SMS Message and generate an XML Content with list
	 * of messages and mobile numbers to sent.
	 * 
	 * @param from
	 * @param toList
	 * @param text
	 * @return SMS Text As XML String
	 */
	/*private String getDataParameter(String from, List toList, String text) {

		final String userName = (String) mailProperties
				.getProperty("SMS_AUTH_USER");
		final String password = (String) mailProperties
				.getProperty("SMS_AUTH_PWD");
		
		StringBuffer addressTags = new StringBuffer();

		for (int i = 0; i < toList.size(); i++) {
			String address = (String) toList.get(i);

			addressTags.append(MessageFormat.format(ADDRESS_TAG, new Object[] {
					from, address, new Integer(i + 1) }));
		}
      
		int flag = 0;
		StringBuffer smsChunk = new StringBuffer("");
		String[] st = text.split("&#010;");
		StringBuffer sms = new StringBuffer("");
		String token = "";

		for (int i = 0; i < st.length; i++) {

			token = st[i].toString();
			flag = flag + token.length();

			if (flag > 155) {

				smsChunk.append(MessageFormat.format(SMS_CHUNK, new Object[] {
						sms, addressTags }));

				flag = token.length();
				sms = new StringBuffer("");
			}

			sms.append(encodeHTML(token));
			sms.append("&#010;");

		}

		smsChunk.append(MessageFormat.format(SMS_CHUNK, new Object[] { sms,
				addressTags }));

		return MessageFormat.format(DATA_TAG, new Object[] { userName,
				password, smsChunk });
	}*/

	/**
	 * This method encodes the passed String and returns the encoded String.
	 * 
	 * @param s
	 * @return Token as String
	 */
	private String encodeHTML(String s) {

		StringBuffer out = new StringBuffer();

		for (int i = 0; i < s.length(); i++) {

			char c = s.charAt(i);

			if ((c >= 32 && c <= 46) || (c >= 58 && c <= 64)
					|| (c >= 91 && c <= 96) || (c >= 123 && c <= 126)) {

				out.append("&#" + (int) c + ";");

			} else {
				out.append(c);
			}
		}

		return out.toString();
	}

	
	
	/**
	 * Method to forword SMS from given folder to the the users list.
	 * @param smsFolder
	 * @param mobileNosList
	 */
	public void sendUploadDocSMS(String msg,List mobileNosList) {
		
		StringBuffer mobileNumbers = new StringBuffer("");
		
		boolean isSmsSent = false;
		String responce	  = "";
		String smsText = "";
		for(Iterator itr = mobileNosList.iterator();itr.hasNext();){
		    Object[] user	= (Object[]) itr.next();
		    mobileNumbers.append(user[7].toString());
		    mobileNumbers.append(user[1].toString());
		    mobileNumbers.append(",");
		}
	
			
				//logSMSStatus(fileName[0]);
				//logSMSStatus(getSmsTextFromFile(smsFolder, children[i]));
				//code to send sms using new api......
					InputStream inputStream =null;
					String retval = "";
					Properties properties  = null;
					String SMS_SERVICE_URL = null;
					String AUTH_USER 	   = null;
					String AUTH_PWD 	   = null;
					String AUTH_SID 	   = null;
					String MSG_TYPE		   = null;
					String DR		  	   = null;
					try {
						 properties = new Properties();
						 inputStream = this.getClass().getResourceAsStream("SMS.properties");
						 properties.load(inputStream);
						  SMS_SERVICE_URL = properties.getProperty("SMS_SERVICE_URL");
						  AUTH_USER 	  = properties.getProperty("SMS_COUNTRY_AUTH_USER");
						  AUTH_PWD 	      = properties.getProperty("SMS_COUNTRY_AUTH_PWD");
						  AUTH_SID 	      = properties.getProperty("SMS_COUNTRY_AUTH_SID");
						  MSG_TYPE	 	  = properties.getProperty("SMS_COUNTRY_MSG_TYPE");
						  DR			  = properties.getProperty("SMS_COUNTRY_DR");
					} catch (Exception ex) {
					    System.out.println("ERROR:While Reading MailProperties.properties @ "+this.getClass().getName()+" of sendSMS()");
					}finally{
						if(inputStream !=null){
							try{
								inputStream.close();
								properties.clear();
							}catch (Exception e) {}
						}
					}
					try{
						smsText = msg;
						StringBuffer postData= new StringBuffer(); ;
						//give all Parameters In String 
						postData.append("User=" + URLEncoder.encode(AUTH_USER,"UTF-8"));
						postData.append("&passwd=" + AUTH_PWD);
						postData.append( "&mobilenumber=" + mobileNumbers);
						postData.append("&message=" + URLEncoder.encode(smsText,"UTF-8"));
						postData.append("&sid=" + AUTH_SID);
						postData.append("&mtype=" + MSG_TYPE);
						postData.append("&DR=" + DR);
						URL url = new URL(SMS_SERVICE_URL);
						HttpURLConnection urlconnection = (HttpURLConnection) url.openConnection();
						// If You Are Behind The Proxy Server Set IP And PORT else Comment Below 4 Lines
						//Properties sysProps = System.getProperties();
						//sysProps.put("proxySet", "true");
						//sysProps.put("proxyHost", "Proxy Ip");
						//sysProps.put("proxyPort", "PORT");
						urlconnection.setRequestMethod("POST");
						urlconnection.setRequestProperty("Content-Type","application/x-www-form-urlencoded");
						urlconnection.setDoOutput(true);
						OutputStreamWriter out = new OutputStreamWriter(urlconnection.getOutputStream());
						out.write(postData.toString());
						out.close();
						BufferedReader in = new BufferedReader(	new InputStreamReader(urlconnection.getInputStream()));
						String decodedString;
						while ((decodedString = in.readLine()) != null) {
							responce += decodedString;
						}
						in.close();
					}catch (Exception e) {
						e.printStackTrace();
					}	
				//logSMSStatus(responce);
				isSmsSent = true;
				if (isSmsSent) {}
				log.debug(responce);
		
	}

	/**
	 * Method to read sms text from given file.
	 * 
	 * @param path
	 * @param fileName
	 * @return
	 */
	private String getSmsTextFromFile(String path, String fileName) {
		StringBuilder contents = new StringBuilder();
		BufferedReader input = null;
		try {
			input = new BufferedReader(new FileReader(new File(path + "/"+ fileName)));
			try {
				String line = null;
				while ((line = input.readLine()) != null) {
					contents.append(line);
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {
					input.close();
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		} catch (FileNotFoundException e1) {
			//e1.printStackTrace();
		}
		return contents.toString();
	}
	/**
	 * Method to get Properties file.
	 * 
	 * @return
	 */
	private Properties getMailProperties() {

		Properties properties = new Properties();
		InputStream is = null;
		try {

			is = getClass().getResourceAsStream(
					"../alerts/MailProperties.properties");
			properties.load(is);
		} catch (Exception ex) {
			try {
				is.close();
			} catch (IOException e) {
			}
			ex.printStackTrace();
		}
		return properties;
	}

	/**
	 * Method to make objects elegible for garbage collectron.
	 * 
	 */
	private void releaseobjects() {

		mailProperties = null;
		try {

			Runtime r = Runtime.getRuntime();
			r.gc();
		} catch (Exception e) {
		}
	}

	private void logSMSStatus(String string) {

		FileWriter outputFile = null;
		BufferedWriter writer = null;
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
		try {

			outputFile = new FileWriter(new File(sms_ACK_folder
					+ "\\SMS_ACK.text"), true);
			writer = new BufferedWriter(outputFile);
			writer.write(sdf.format(new Date()) + ":" + string);
			writer.newLine();

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				writer.close();
				outputFile.close();
			} catch (IOException e) {
				e.printStackTrace();
			}

		}

	}

	private void logEMAILStatus(String string) {

		FileWriter outputFile = null;
		BufferedWriter writer = null;
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
		try {
			if (!new File(sms_ACK_folder).isDirectory()) {
				new File(sms_ACK_folder).mkdir();
			}
			if (!new File(sms_ACK_folder + "\\EMAIL_ACK.text").exists()) {
				new File(sms_ACK_folder + "\\EMAIL_ACK.text").createNewFile();
			}

			outputFile = new FileWriter(new File(sms_ACK_folder
					+ "\\EMAIL_ACK.text"), true);
			writer = new BufferedWriter(outputFile);
			writer.write(sdf.format(new Date()) + ":" + string);
			writer.newLine();

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				writer.close();
				outputFile.close();
			} catch (IOException e) {
				e.printStackTrace();
			}

		}

	}

	/**
	 * Main Method
	 * 
	 * @param args
	 */
	public static void main(String[] args) {

		System.out.println("start...");
		ProcessMSG ems = new ProcessMSG();
		/*
		 *  
		 * ems.sendSMSFromFolder("");
		 * ems.releaseobjects();
		 */
//		ems.sendSMS("9493335560", "blah blah");
		System.out.println("end...");
	}

}
