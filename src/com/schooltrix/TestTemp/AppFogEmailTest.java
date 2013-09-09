package com.schooltrix.TestTemp;

import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.StringTokenizer;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.Part;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;



public class AppFogEmailTest {


public String SMTP_HOST_NAME=null;
public String SMTP_AUTH_USER=null;
public String SMTP_AUTH_PWD=null;
public String SMTP_PORT=null;

/**
 * This method sends the mails for recipients list that we pass for this method. 
 * @param recipients
 * @param subject
 * @param message
 * @param attachments
 * @param from
 */
public void sendMail(String recipients[], String subject, String message,String attachments[], String from)
{
	try	{
		Properties props = new Properties();
		InputStream is =  getClass().getResourceAsStream("Email.properties");
		System.out.println("get the mail properties."+is);
		props.load(is);			
		SMTP_HOST_NAME= (String) props.getProperty("SMTP_HOST_NAME");			
		SMTP_AUTH_USER= (String) props.getProperty("SMTP_AUTH_USER");
		SMTP_AUTH_PWD= (String) props.getProperty("SMTP_AUTH_PWD");
		SMTP_PORT=(String) props.getProperty("SMTP_PORT");
		System.out.println("SMTP_HOST_NAME."+SMTP_HOST_NAME);
		System.out.println("SMTP_AUTH_USER."+SMTP_AUTH_USER);
		System.out.println("SMTP_AUTH_PWD."+SMTP_AUTH_PWD);
		System.out.println("SMTP_PORT."+SMTP_PORT);
		//Set the host smtp address
		props.put("mail.smtp.host", SMTP_HOST_NAME);
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.port", SMTP_PORT);
		Authenticator auth = new SMTPAuthenticator();
		// Get session
		Session session = Session.getInstance(props,auth);

		session.setDebug(false);
        MimeMessage messageObj = new MimeMessage(session);
		try{
			if(from!=null&&from.indexOf("$") > 0)
			{
				try
				{
				StringTokenizer stz = new StringTokenizer(from, "$");
				String from1 	 = 	stz.nextToken();
				messageObj.setFrom(new InternetAddress(from1));
				}catch(Exception e){}	 
			}
			else
				{
				messageObj.setFrom(new InternetAddress(from));
				}
		 }catch(Exception _ex){messageObj.setFrom(new InternetAddress(from));}
		// Define message

		InternetAddress[] addressTo = new InternetAddress[recipients.length];
		for (int i = 0; i < recipients.length; i++) {
			addressTo[i] = new InternetAddress(recipients[i]);
		}
		messageObj.setRecipients(Message.RecipientType.TO, addressTo);
		
		messageObj.setSubject(subject);
		
		// create the message part
		MimeBodyPart messageBodyPart = new MimeBodyPart();
		BodyPart mainbody = new  MimeBodyPart();
		String content="";
				
		content=content+"<html><head><meta http-equiv=\"Content-Type\" content=\"text/html; charset=iso-8859-1\" /><title> :Vantagetrade : </title></head><body style=\"margin:0px;\"><table style=\"width:98%;border:#CCCCCC 1px solid;font-family:\"Trebuchet MS\", \"Times New Roman\", Tahoma;font-size:11px;color:#000000;margin:1%;\">" +
		"<tr><td><img src=\"http://www.vantagetrade.com/images/spacer.gif\" alt=\"Buy and Sell Signals\" width=\"99%\" height=\"15\"/></td></tr><tr><td style=\"margin-left:5px;\">" +
		"<img src=\"http://www.vantagetrade.com/images/TermHederLogo.gif\" alt=\"Buy and Sell Signals\" width=\"156\" height=\"47\" /></td></tr><tr><td style=\"padding:10px; border-left:#CCCCCC 1px solid; border-right:#CCCCCC 1px solid;border-bottom:#CCCCCC 1px solid;\">" +
		""+message+"</td></tr></table><body></html>";
		String content_prototype="";		
		mainbody.setContent(content,"text/html");
		mainbody.setDisposition(Part.INLINE);  				

		Multipart multipart = new MimeMultipart();
		//multipart.addBodyPart(messageBodyPart);
		multipart.addBodyPart(mainbody);
		
		//Attachments
		messageBodyPart = new MimeBodyPart();		
		
		/*for (int i = 0; i < attachments.length; i++) {
			DataSource source = new FileDataSource(attachments[i]);
			messageBodyPart.setDataHandler(new DataHandler(source));
			messageBodyPart.setFileName(attachments[i]);
			multipart.addBodyPart(messageBodyPart);
		}		
		*/
		
		// Put parts in message
		messageObj.setContent(multipart);

		// Send the message
		Transport.send(messageObj);		
		
	}
	catch(Exception e)
	{
		e.printStackTrace();
		//log.error(e);
	}
}

/**
 * This method used sends the uploaded Doc mails for recipients list that we pass for this method. 
 * @param recipients
 * @param subject
 * @param message
 * @param attachments
 * @param from
 */
public void sendContactUS_SignUPMail(String recipients[], String subject, String message,String attachments[])
{
	try	{
		Properties props = new Properties();
		InputStream is =  getClass().getResourceAsStream("EmailTest.properties");
		System.out.println("get the mail properties."+is);
		props.load(is);			
		SMTP_HOST_NAME		= "smtp.mailgun.org";//(String) props.getProperty("SMTP_HOST_NAME");			
		SMTP_AUTH_USER		= "postmaster@schoolzippyravionweb.mailgun.org";//(String) props.getProperty("SMTP_AUTH_USER");
		SMTP_AUTH_PWD			= "4z10h-n3zga6";//(String) props.getProperty("SMTP_AUTH_PWD");
		SMTP_PORT						=	"587";//(String) props.getProperty("SMTP_PORT");
		//SMTP_PORT						=	"25";//(String) props.getProperty("SMTP_PORT");
		//String from=(String) props.getProperty("contactUsfrom");
		//String from="bhanu@schoolzippy.com";
		//String from="support@schoolzippyravionweb.mailgun.org";
		String from="support@schoolzippy.com";
		/*System.out.println("SMTP_HOST_NAME."+SMTP_HOST_NAME);
		System.out.println("SMTP_AUTH_USER."+SMTP_AUTH_USER);
		System.out.println("SMTP_AUTH_PWD."+SMTP_AUTH_PWD);
		System.out.println("SMTP_PORT."+SMTP_PORT);*/
		//Set the host smtp address
		props.put("mail.smtp.host", SMTP_HOST_NAME);
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.port", SMTP_PORT);
		Authenticator auth = new SMTPAuthenticator();
		// Get session
		Session session = Session.getInstance(props,auth);

		session.setDebug(true);
        MimeMessage messageObj = new MimeMessage(session);
		try{
			if(from!=null&&from.indexOf("$") > 0)
			{
				try
				{
				StringTokenizer stz = new StringTokenizer(from, "$");
				String from1 	 = 	stz.nextToken();
				messageObj.setFrom(new InternetAddress(from1));
				}catch(Exception e){}	 
			}
			else
				{
				messageObj.setFrom(new InternetAddress(from));
				}
		 }catch(Exception _ex){messageObj.setFrom(new InternetAddress(from));}
		// Define message

		InternetAddress[] addressTo = new InternetAddress[recipients.length];
		for (int i = 0; i < recipients.length; i++) {
			addressTo[i] = new InternetAddress(recipients[i]);
		}
		//System.out.println("addressTo.............."+addressTo[0]);
		messageObj.setRecipients(Message.RecipientType.TO, addressTo);
		//messageObj.setRecipients(Message.RecipientType.CC, addressTo);
		messageObj.setSubject(subject);
		
		// create the message part
		MimeBodyPart messageBodyPart 	= 	new MimeBodyPart();
		BodyPart mainbody 				= 	new  MimeBodyPart();
		String content					=	"";
		
/*		content=content+"<html><head><meta http-equiv=\"Content-Type\" content=\"text/html; charset=iso-8859-1\" /><title> :SchoolZippy : </title></head><body style=\"margin:0px;\"><table style=\"width:98%;font-family:\"Trebuchet MS\", \"Times New Roman\", Tahoma;font-size:11px;color:#000000;margin:1%;\">" +
		"<tr><td style=\"padding:10px;color:red;border:1 solid\">" +
		""+message+"</td></tr></table><body></html>";*/
		
		

		content=content+"<html><head><meta http-equiv=\"Content-Type\" content=\"text/html; charset=iso-8859-1\" /><title> :SchoolZippy : </title></head><body style=\"margin:0px;\">" +
				"<div style=\"font-size: 28px; color: Blue; text-align: center;\">Welcome To New API AppFog </div><div style=\"font-size: 18px; color: #3995DF; text-align: center;\">Basic Mail Fired Successfully.</div><body></html>";		
		
		String content_prototype="";		
		//mainbody.setHeader("HI,","text/html");
		System.out.println(message);
		//mainbody.setContent(message,"text/html");
		mainbody.setDisposition(Part.INLINE);  				

		Multipart multipart = new MimeMultipart();
		//multipart.addBodyPart(messageBodyPart);
		
		
		//Attachments
				
	/*	
		for (int i = 0; i < attachments.length; i++) {
			System.out.println(".........................."+attachments[i]);
			messageBodyPart = new MimeBodyPart();
			DataSource source = new FileDataSource(attachments[i]);
			messageBodyPart.setDataHandler(new DataHandler(source));
			messageBodyPart.setFileName(attachments[i]);
			
			multipart.addBodyPart(messageBodyPart);		
			// Put parts in message	
			// Send the message			
		}*/
		System.out.println(content);
		mainbody.setContent(content,"text/html");
		multipart.addBodyPart(mainbody);
		messageObj.setHeader("<b style=\"width:98%;border:#CCCCCC 1px solid;font-family:\"Italic\", \"Italic\", Tahoma;font-size:11px;color:#000000;margin:1%;\">" 
	+"This is an auto generated email. Please do not reply</b>", "text/html");
		messageObj.setContent(multipart);
		
		Transport.send(messageObj);		
	}
	catch(Exception e)
	{
		e.printStackTrace();
	//	log.error(e);
	}
}



/**
 * SimpleAuthenticator is used to do simple authentication when the SMTP
 * server requires it.
 */
private class SMTPAuthenticator extends javax.mail.Authenticator {

	public PasswordAuthentication getPasswordAuthentication() 
            {
		try
		{
			String username = SMTP_AUTH_USER;
			String password = SMTP_AUTH_PWD;
		return new PasswordAuthentication(username, password);
	}
		catch(Exception e)
		{
			//e.printStackTrace();
			//log.error(e);
			return null;
		}
}
}

public static void main(String args[]){

	AppFogEmailTest smb = new AppFogEmailTest();

	System.out.println("---");
	//(String recipients[], String subject, String message,String attachments[])
	smb.sendContactUS_SignUPMail(new String[]{"bhanuchander33@gmail.com","bhanu@schoolzippy.com","ravionweb@gmail.com"}, "APPFogMailAPI-TestMode", "tsttt", null);
	}

}

