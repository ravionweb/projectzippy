package com.schooltrix.actions;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.SessionAware;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import com.opensymphony.xwork2.ActionSupport;
import com.schooltrix.daos.InstitutionMasterDAO;
import com.schooltrix.daos.UploadDocDAO;
import com.schooltrix.daos.UserMasterDAO;
import com.schooltrix.hibernate.InstitutionMaster;
import com.schooltrix.hibernate.UploadDocument;
import com.schooltrix.hibernate.UploadDocumentClassBranchMap;
import com.schooltrix.hibernate.UploadDocuments;
import com.schooltrix.managers.ServiceFinder;

public class UploadDocAction extends ActionSupport implements ServletRequestAware,SessionAware {

	public UploadDocAction() {
		// TODO Auto-generated constructor stub
	}
	
	
	 private org.apache.log4j.Logger log = Logger.getLogger(UploadDocAction.class);
	Map session;
	HttpServletRequest request = null;
	private File fileUP;
	private String fileUPContentType;
	private String fileUPFileName;
	
	public String exeString() {
		return null;
	}
	//new method after..major DB change ..respect to details tables...
	public String uploadDocument() {

	if (fileUPFileName != null) {
		
		if(fileValidation()){
				
		try {
			 String filePath 				= request.getSession().getServletContext().getRealPath("/")+"UploadDoc/";			
			 String im_id 					=	(String)session.get("IM_ID");
			 String institutionName = (String)session.get("IM_SN");

			 String sm_id[] 				=	request.getParameterValues("schoolNames");
			 String bm_id[] 				=	request.getParameterValues("branchNames");
			 String cm_ids[] 			=	request.getParameterValues("selectClass");
			 
			 String cmBMJson 		=	(String)request.getParameter("classBMIds");//hiden field*******************
			 
			// JSONArray jsonMainArrayq = new JSONArray(cmBMJson.toString());
			// System.out.println(jsonMainArrayq+"*********"+jsonMainArrayq.length());
			 System.out.println("cmBMJson::"+cmBMJson);
			 
			 JSONTokener jsonTokener = new JSONTokener(cmBMJson);
			 
		/*	    JSONArray totalObject = new JSONArray(jsonTokener);
		        System.out.println(totalObject);
		        for(int i=0;i<totalObject.length();i++){
		            JSONObject obj = totalObject.getJSONObject(i);
		            System.out.println(obj.getString("A"));
		            System.out.println(obj.getString("B"));
		            JSONArray arr =obj.getJSONArray("C");
		            for(int k=0;k<arr.length();k++){
		                System.out.println(arr.getString(k));
		            }
			 */
			 
			 String selectSubject		=	request.getParameter("selectSubject");
			 
			 String classIds 		=  "";
			 String smIds		 	=  "";
			 String bmIds 			=  "";
			 
			 String selectType 			=	request.getParameter("selectType");
			 String uploadType 		=	request.getParameter("uploadType");
			 String assignmentType =	request.getParameter("assignmentType");
			 String assdesc 					=	request.getParameter("desc");
			 String fileUP 				=	request.getParameter("fileUP");
			 String nty_email 			=	request.getParameter("nty_email");
			
			 System.out.println("selectClass--"+cm_ids.length+"--"+selectType+"--"+uploadType+"-assignmentType-"
			 +assignmentType+"--"+assdesc+"--"+selectSubject+"--"+fileUPFileName+"--"+nty_email);
			 
			 
			 if(assignmentType == null){ 
				 assignmentType = "";
				}
			 //these seeting are usefull for edit upload doc ...time....
			 if(assdesc == null){ 
				 assdesc = "";
			 }
			 if(selectSubject == null){ 
				 selectSubject = "";
			 }
	/*		
		 if(selectSubject == null){ 
				 selectSubject = new String[1];
			 }*/
			 
			 
			 
			 
			 if(nty_email == null || nty_email == "null"){
				 nty_email = "N";
			 }else{
				 nty_email = "Y";
			 }

			 String uniqueFileName ="";
			 
			 File fileToCreate = null;
			 UploadDocument uploadDocData = new UploadDocument();
			 UploadDocDAO uploadDocDao = null;
			 
			 filePath = filePath+"/"+institutionName+"/"+uploadType+"/";//change ?
				
			 uploadDocData.setImId(im_id);
			 uploadDocData.setToWhome(selectType);
			 uploadDocData.setUploadType(uploadType);
			 
			 uniqueFileName = getFileUniqueName();	
			 uploadDocData.setFileName(uniqueFileName);

			 uploadDocData.setAssignType(assignmentType);
			 uploadDocData.setAssgDesc(assdesc);
			 uploadDocData.setSubject(selectSubject);

			 uploadDocData.setNotifyPaEmail(nty_email);
			 uploadDocData.setNotifyPaEmailFlag("0");
			 
			 SimpleDateFormat sdf =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			 uploadDocData.setUploadDate(java.sql.Timestamp.valueOf(sdf.format(new Date())));
				
			 uploadDocData.setProcessedDate(java.sql.Timestamp.valueOf(sdf.format(new Date())));
			 
			 try {
				 uploadDocDao = (UploadDocDAO)ServiceFinder.getContext(request).getBean("UploadDocHibernateDao"); 		
				 uploadDocDao.save(uploadDocData);
				fileToCreate = new File(filePath,uniqueFileName);// unique file name
					FileUtils.copyFile(this.fileUP, fileToCreate);
					
					UploadDocumentClassBranchMap uploClassBranchMap = null;
					
					// System.out.println("jsonTokener:"+jsonTokener);
					 
					 JSONArray jsonMainArray = new JSONArray(jsonTokener);
					 
					// System.out.println("jsonMainArray::"+jsonMainArray.length());
					 for (int i = 0; i < jsonMainArray.length(); i++) {
						 
						 JSONObject jsonObject =  jsonMainArray.getJSONObject(i);						 
						String class_ID = jsonObject.getString("class_id");
						if (isClassIDAccess(class_ID,cm_ids)) {					
							JSONArray bm_IDs = jsonObject.getJSONArray("bms");
				            for(int k=0;k<bm_IDs.length();k++){
				            	uploClassBranchMap = new UploadDocumentClassBranchMap();
				              //  System.out.println(bm_IDs.getString(k)+"**"+class_ID);
				    			uploClassBranchMap.setBmId(bm_IDs.getString(k));
				    			uploClassBranchMap.setCmId(class_ID);
								uploClassBranchMap.setUdId(uploadDocData.getUdId()+"");
								uploadDocDao.saveUploadClassBranchMap(uploClassBranchMap);			                
				            }
						}
					}
					
					session.put("msg","Success");
					
					System.out.println("success");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				session.put("msg","File Not Uploaded");
				return "success";
				
			}
			 
				return "success";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			session.put("msg","File Not Uploaded");
			return "success";
		}
		
		
		}else{
			session.put("msg","Please select File");
			return "success";
	}
	
	
	
	
	}else{
		session.put("msg","Please select File");
		return "success";
	}
	}
	
	/*same as upload doc,
	 *	here main thing is edited doc handling.
	 *1)update ud_id data in upload_document
	 * 2)delete rows  rel to upload_document_class_branch_map
	 * 3)insert updated(edit as action),deleted rows in upload_document_history and upload_document_class_branch_map_history tables..
	 * 4)Insert new records upload_document_class_branch_map..
	 * use multiTransaction..............
	 * */
	public String editedDocumentSave() {
		//System.out.println("in ediiii");
		//req here about file ..bcz he may not upload a file....i.e no change in previous file...
		//if a new file uploaded only check file rel  condtion..otherwise do nothing abt file
	//	System.out.println("fileUPFileName:::>"+fileUPFileName);
		if (fileUPFileName!= null && !fileValidation()) {
				session.put("msg","Please select File");
				return "success";			
		}
		
			//if flow come here ?		
			try {
				String filePath = "";
				 String im_id 					=	(String)session.get("IM_ID");
				 String institutionName = (String)session.get("IM_SN");

				 String sm_id[] 				=	request.getParameterValues("schoolNames");
				 String bm_id[] 				=	request.getParameterValues("branchNames");
				 String cm_ids[] 			=	request.getParameterValues("selectClass");
				 
				 String cmBMJson 		=	(String)request.getParameter("classBMIds");// Hidden field*******************
				 
				 String uploadID 		=	(String)request.getParameter("upld_id");//queryString
				 String fileNameOld 		=	(String)request.getParameter("fileNameOld");//queryString

				 
				 System.out.println("cmBMJson::"+cmBMJson);
				 
				 JSONTokener jsonTokener = new JSONTokener(cmBMJson);
				 
				 String selectSubject		=	request.getParameter("selectSubject");
				 
				 String classIds 		=  "";
				 String smIds		 	=  "";
				 String bmIds 			=  "";
				 
				 String selectType 			=	request.getParameter("selectType");
				 String uploadType 		=	request.getParameter("uploadType");
				 String assignmentType =	request.getParameter("assignmentType");
				 String assdesc 					=	request.getParameter("desc");
				 String fileUP 				=	request.getParameter("fileUP");
				 String nty_email 			=	request.getParameter("nty_email");
				
				 System.out.println("selectClass--"+cm_ids.length+"--"+selectType+"--"+uploadType+"-assignmentType-"
				 +assignmentType+"--"+assdesc+"--"+selectSubject+"--"+fileUPFileName+"--"+nty_email);
				 
				 if(assdesc == null){ 
					 assdesc = "";
				 }
				 if(selectSubject == null){ 
					 selectSubject = "";
				 }
				 
				 if(assignmentType == null){ 
					 assignmentType = "";
					}
				 
				 if(nty_email == null || nty_email == "null"){
					 nty_email = "N";
				 }else{
					 nty_email = "Y";
				 }

				 String uniqueFileName ="";
				 
				 File fileToCreate = null;
				 UploadDocument uploadDocData = new UploadDocument();
				 UploadDocDAO uploadDocDao = null;
				 
					
				 uploadDocData.setImId(im_id);
				 uploadDocData.setToWhome(selectType);
				 uploadDocData.setUploadType(uploadType);
				 
				if (fileUPFileName != null) {
					 filePath 				= request.getSession().getServletContext().getRealPath("/")+"UploadDoc/";	
					 filePath = filePath+"/"+institutionName+"/"+uploadType+"/";//change ?
					uniqueFileName = getFileUniqueName();	
					uploadDocData.setFileName(uniqueFileName);//otherwise omit it..
				}else{
					
					uploadDocData.setFileName(fileNameOld);
				}

				 uploadDocData.setAssignType(assignmentType);
				 uploadDocData.setAssgDesc(assdesc);
				 uploadDocData.setSubject(selectSubject);

				 uploadDocData.setNotifyPaEmail(nty_email);
				 uploadDocData.setNotifyPaEmailFlag("0");
				 
				 SimpleDateFormat sdf =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				 uploadDocData.setUploadDate(java.sql.Timestamp.valueOf(sdf.format(new Date())));
					
				 uploadDocData.setProcessedDate(java.sql.Timestamp.valueOf(sdf.format(new Date())));
				 
				 try {
					 uploadDocDao = (UploadDocDAO)ServiceFinder.getContext(request).getBean("UploadDocHibernateDao"); 		
					 uploadDocData.setUdId(Long.parseLong(uploadID));
					 String resultt = uploadDocDao.updateMultiTransactionCall(uploadDocData,cmBMJson,cm_ids);
					 
					 if (resultt.equalsIgnoreCase("success") && fileUPFileName != null) {
						fileToCreate = new File(filePath,uniqueFileName);// unique file name
						FileUtils.copyFile(this.fileUP, fileToCreate);
						
					}
	
						session.put("msg","Success");
						session.put("resAfterEdit","Success");//2013-07-20...this is for show result ..after edit is success...
						
						System.out.println("success");
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					session.put("msg","File Not Uploaded");
					return "success";
					
				}
				 
					return "success";
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				session.put("msg","File Not Uploaded");
				return "success";
			}
			
		}
		
	
	/*public String uploadDoc() {

	if (fileUPFileName != null) {
		
		if(fileValidation()){
				
		try {
			 String filePath 				= request.getSession().getServletContext().getRealPath("/")+"UploadDoc/";			
			 String im_id 					=	(String)session.get("IM_ID");
			 String institutionName = (String)session.get("IM_SN");

			 String sm_id[] 			=	request.getParameterValues("schoolNames");
			 String bm_id[] 			=	request.getParameterValues("branchNames");
			 String selectClass[] =	request.getParameterValues("selectClass");
			 String selectSubject[] 	=	request.getParameterValues("selectSubject");
			 
			 String classIds 		=  "";
			 String smIds		 	=  "";
			 String bmIds 			=  "";
				StringBuffer inclassIds 	= new StringBuffer();
				StringBuffer insmIds 		= new StringBuffer();
				StringBuffer inbmIds	 	= new StringBuffer();
				StringBuffer inSubjects	 	= new StringBuffer();
				
				
				for (int i = 0; i < sm_id.length; i++) {				
					insmIds.append(sm_id[i]);
					if(i<sm_id.length-1)
						insmIds.append(",");					
				}
				System.out.println("inString**********"+insmIds);
				
			 for (int i = 0; i < bm_id.length; i++) {
				 inbmIds.append(bm_id[i]);
					if(i<bm_id.length-1)
						inbmIds.append(",");		
			 }
			 
			 for (int i = 0; i < selectClass.length; i++) {
				 inclassIds.append(selectClass[i]);
					if(i<selectClass.length-1)
						inclassIds.append(",");	
			}
			 
			 if(selectSubject != null){
			 for (int i = 0; i < selectSubject.length; i++) {
				 inSubjects.append(selectSubject[i]);
				 if(i<selectSubject.length-1)
					 inSubjects.append(",");	
			 }
			 }

			// System.out.println(sm_id.length+"^^"+sm_id[0]+"*sm_id*"+bm_id+"*bm_ids*"+selectClass);
			 
			System.out.println(institutionName+":institutionName:"+insmIds+"**"+inbmIds+"**"+inclassIds+"**"+inSubjects);
			 
			 String selectType 			=	request.getParameter("selectType");
			 String uploadType 		=	request.getParameter("uploadType");
			 String assignmentType =	request.getParameter("assignmentType");
			 String assdesc 					=	request.getParameter("desc");
			 String fileUP 				=	request.getParameter("fileUP");
			 String nty_email 			=	request.getParameter("nty_email");
			
			 System.out.println("selectClass--"+selectClass+"--"+selectType+"--"+uploadType+"-assignmentType-"
			 +assignmentType+"--"+assdesc+"--"+selectSubject+"--"+fileUPFileName+"--"+nty_email);
			 
			 
			 if(assignmentType == null){ 
				 assignmentType = "";
				}
			
		 if(selectSubject == null){ 
				 selectSubject = new String[1];
			 }
			 
			 
			 if(nty_email == null || nty_email == "null"){
				 nty_email = "N";
			 }else{
				 nty_email = "Y";
			 }


			 String uniqueFileName ="";
			 
			 File fileToCreate = null;
			 UploadDocuments uploadDocData = new UploadDocuments();
			 UploadDocDAO uploadDocDao = null;
			 
			 filePath = filePath+"/"+institutionName+"/"+uploadType+"/";//change ?
				
			 uploadDocData.setImId(im_id);
			 uploadDocData.setSmId(insmIds+"");
			 uploadDocData.setBmId(inbmIds+"");

			 uploadDocData.setToWhich(inclassIds+"");
			 uploadDocData.setToWhome(selectType);
			 uploadDocData.setUploadType(uploadType);
			 
			 uniqueFileName = getFileUniqueName();	
			 uploadDocData.setFileName(uniqueFileName);

			 uploadDocData.setAssignType(assignmentType);
			 uploadDocData.setAssgDesc(assdesc);
			 uploadDocData.setSubject(inSubjects+"");

			 uploadDocData.setNotifyPaEmail(nty_email);
			 uploadDocData.setNotifyPaEmailFlag("0");

				
			 SimpleDateFormat sdf =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			 uploadDocData.setUploadDate(java.sql.Timestamp.valueOf(sdf.format(new Date())));
				
			 uploadDocData.setProcessedDate(java.sql.Timestamp.valueOf(sdf.format(new Date())));
			 
			 try {
				 uploadDocDao = (UploadDocDAO)ServiceFinder.getContext(request).getBean("UploadDocHibernateDao"); 		
				 uploadDocDao.save(uploadDocData);			 
				fileToCreate = new File(filePath,uniqueFileName);// unique file name
					FileUtils.copyFile(this.fileUP, fileToCreate);
					
					session.put("msg","Success");
					
					System.out.println("success");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				session.put("msg","File Not Uploaded");
				return "success";
				
			}
			 
				return "success";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			session.put("msg","File Not Uploaded");
			return "success";
		}
		
		
		}else{
			session.put("msg","Please select File");
			return "success";
	}
	
	
	
	
	}else{
		session.put("msg","Please select File");
		return "success";
	}
	}
	*/
/*	private String getinstitutionName(String im_id) {
		try {
			// TODO Auto-generated method stub
			InstitutionMasterDAO imMasterDao = null;
			InstitutionMaster institutionMasterData = new InstitutionMaster();
			imMasterDao = (InstitutionMasterDAO)ServiceFinder.getContext(request).getBean("InstitutionMasterHibernateDao"); 
			institutionMasterData = imMasterDao.findById(Long.parseLong(im_id));
			
			
			return institutionMasterData.getShortName();
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
		return "";
	}*/

	private boolean isClassIDAccess(String class_ID, String[] cm_ids) {
		
		for (int i = 0; i < cm_ids.length; i++) {
			if (class_ID.equalsIgnoreCase(cm_ids[i])) {
				return true;				
			}
		}
		
		return false;
	}
	private boolean fileValidation() {
		// TODO Auto-generated method stub
		
		try {
			String[] allowed = {"pdf", "doc", "docx", "xls", "xlsx", "txt","ppt","pptx","gif","csv","png","jpg","jpeg","dot","dotx","html","odm","ott","odt","rtf","xps"};
				
				int i = fileUPFileName.lastIndexOf('.');
				
				int count = StringUtils.countMatches(fileUPFileName, ".");
				
				System.out.println("iii--"+i+"ooo"+count);
				if (i > 0 && count<2) {
					String extension = fileUPFileName.substring(i+1);
					System.out.println("extension-->"+extension);
				for (int j = 0; j < allowed.length; j++) {
					if(allowed[j].equalsIgnoreCase(extension)){
						return true;
					}
				}
					
					
				}
			
			
			return false;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}

	private String getFileUniqueName() {
		String extension = "";
		if (getFileUPFileName() != null) {		
		
		int i = getFileUPFileName().lastIndexOf('.');
		if (i > 0) {
		    extension = getFileUPFileName().substring(i+1);
		}else{
			extension = "jpg";
		}		
		return String.format("%s.%s", RandomStringUtils.randomAlphanumeric(10), extension);
		}else{
			return  "nothing";
		}
		
	}
	
	@Override
	public void setSession(Map<String, Object> session) {
		// TODO Auto-generated method stub
		System.out.println(session+"------------------------------------");
		this.session = session;
	}

	@Override
	public void setServletRequest(HttpServletRequest request) {
		// TODO Auto-generated method stub
		System.out.println(request+"--------request----------------------------");
		this.request = request;  
	}

	public File getFileUP() {
		return fileUP;
	}

	public void setFileUP(File fileUP) {
		this.fileUP = fileUP;
	}

	public String getFileUPContentType() {
		return fileUPContentType;
	}

	public void setFileUPContentType(String fileUPContentType) {
		this.fileUPContentType = fileUPContentType;
	}

	public String getFileUPFileName() {
		return fileUPFileName;
	}

	public void setFileUPFileName(String fileUPFileName) {
		this.fileUPFileName = fileUPFileName;
	}

	
}
