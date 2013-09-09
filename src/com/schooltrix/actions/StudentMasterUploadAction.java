package com.schooltrix.actions;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.swing.text.StyledEditorKit.BoldAction;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.RandomStringUtils;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.SessionAware;
import org.springframework.beans.BeansException;

import com.opensymphony.xwork2.ActionSupport;
import com.schooltrix.daos.ClassMasterDAO;
import com.schooltrix.daos.ParentDetailsDAO;
import com.schooltrix.daos.SectionMasterDAO;
import com.schooltrix.daos.StateMasterDAO;
import com.schooltrix.daos.StudentDetailsDAO;
import com.schooltrix.daos.UserMasterDAO;
import com.schooltrix.hibernate.ClassMaster;
import com.schooltrix.hibernate.ParentDetails;
import com.schooltrix.hibernate.ParentStudentMap;
import com.schooltrix.hibernate.SectionClassMap;
import com.schooltrix.hibernate.SectionMaster;
import com.schooltrix.hibernate.StateMaster;
import com.schooltrix.hibernate.StudentDetails;
import com.schooltrix.hibernate.StudentSectionMap;
import com.schooltrix.hibernate.StudentxlErrorTemp;
import com.schooltrix.hibernate.UserMaster;
import com.schooltrix.managers.ServiceFinder;

public class StudentMasterUploadAction extends ActionSupport implements ServletRequestAware,SessionAware{
	

	Map session;
	private File fileUP;
	private String fileUPContentType;
	private String fileUPFileName;
	public HttpServletRequest request ;
	private String stateID ;
	private String sectionID ;
	private String sectionClassMapID ;
	private String classCurID ;
	private String classAdmitID ;
	
	public String execute() {		

		if(fileUPFileName==null){
			session.put("msg", "please upload csv/txt files");
			return INPUT;
		}
		else if(!fileUPFileName.substring(Math.max(0, fileUPFileName.length() - 3)).equalsIgnoreCase("txt") && !fileUPFileName.substring(Math.max(0, fileUPFileName.length() - 3)).equalsIgnoreCase("csv")){
			session.put("msg", "please upload only csv file or txt files");
			return INPUT;			
		}
		
		String SM_ID = request.getParameter("schoolNames");
		String BM_ID = request.getParameter("branchNames");
		
		String institutionName = (String)session.get("IM_SN");
		String UM_ID = (String)session.get("UM_ID");
		String IM_ID = (String)session.get("IM_ID");
		
		System.out.println(SM_ID+"-BM_ID-"+BM_ID+"--IM_ID-"+IM_ID+"-institutionName-"+institutionName);
		
		try {
			FileReader inpFile = new FileReader(fileUP);
			BufferedReader inpReader = new BufferedReader(inpFile);
			String inpLine = null; 
			int atWhatline=1;
			
			int errorCount = 0;
			int ignoredCount = 0;
			int insertedCount = 0;
			int totalRecords = 0;
			
			//delete old error log before new errorlog create...may or maynot create a log every time..bcz..create log only in error
			deleteStudentError(UM_ID);
			
		while ((inpLine = inpReader.readLine()) != null) {
			if ((inpLine == null || inpLine == "" ) && atWhatline == 1) {
				System.out.println("--File is empty1");
				session.put("msg", "File is empty");
				return INPUT;	
			}
			classAdmitID = null;
			classCurID	=	null;
			sectionID	=	null;
			stateID = null;
		if (inpLine.trim().length() != 0){
			//for next record global values to set to null****************
			//*********************************
			
			totalRecords++;//for total records uploaded-result
				System.out.println("innnnnnnnnnnn");
			  M:{
					System.out.println("MMMMMMMMMMMMMMMMMMM");
				  try {
					  System.out.println("----------innnnnnnnnnnn");
				String[] fields = inpLine.split(",\\s*");
				  
				  if(fields[0]!=null){
					 
						//for printing juSTTT	
					  for (int j = 0; j < fields.length; j++) {
								System.out.print(fields[j]+"--");
							}
					  System.out.println("88888888888");			 
					//22-Student_First_Name,Student_Last_Name,DOB,Admission_Number,Admission_Date,Class_Admitted_In,Current_Class,Current_Section,Gender,
					//Father_First_Name,Father_Last_Name,DOB,Mother_First_Name,Mother_Last_Name,DOB,Primary_Email,Primary_Mobile,Landline,Address1,Address2,City,State		 
							 
					String[] format = {"Student_First_Name","Student_Last_Name","DOB","Admission_Number","Admission_Date","Class_Admitted_in","Current_Class","Current_Section","Gender",
							"Father_First_Name","Father_Last_Name","DOB","Mother_First_Name","Mother_Last_Name","DOB","Primary_Email","Primary_Mobile","Landline","Address1","Address2","City","State"};
				
					System.out.println(fields.length+"**********"+format.length+"-------"+atWhatline);
					
					if ((atWhatline==1)) {
						 for (int j = 0; j < fields.length; j++) {//bcz upload txt may have more colums
							if (fields[j].equalsIgnoreCase(format[j])) {
								System.out.print(j+"--");
								atWhatline++;
							}else{
								session.put("msg", "Header Failed");
								//atWhatline = 1;
								return INPUT;
							}
						}
					}else if(atWhatline>1){
						//required every fields(column) validation
						//2nd line onwards here-------------------22 fields
						
						if (fields.length != format.length) {
						//I --reason to fail-error
							//insert into DB--Error
							saveStudentErrorLog(IM_ID,SM_ID,BM_ID,UM_ID,fields,"column count does't match");
							errorCount++;
							break M;
						}else{
							//II -- reason to fail---if validLine-false ---here count is OK but each field will validate against wt type it's.
							boolean validLine = isValidLine(fields,BM_ID);//main is field validation						
							
							
							if (validLine) {
								//check for admission number already exist or not ---this is for ignored records
							 	if (!isAdmissionNumberExist(IM_ID,fields[3])) {
							 		System.out.println("in admission false--");
							 		boolean check =  doInsertIntoDB(fields,IM_ID,SM_ID,BM_ID);//main operations*********************************************************
							 	
							 		//		 		testing purpose~~~~~~~~~~~~~~~~~~~~~~~~~~~~
							 		//boolean check =  true;//doInsertIntoDB(fields,IM_ID,SM_ID,BM_ID);//main operations*********************************************************
							 		if (!check) {
							 			//insert into DB--Error
							 			saveStudentErrorLog(IM_ID,SM_ID,BM_ID,UM_ID,fields,"column validation failed *");
							 			errorCount++;
										break M;
									}else{
										insertedCount++;
										break M;
									}
							 	}else{
									//insert into DB-------Ignored records
							 		ignoredCount++;
									break M;
								}				 					
								
								//we need a specil method in daoimpl,for save all at once or roll back. 
															
							} else {
								//insert into DB----error
								saveStudentErrorLog(IM_ID,SM_ID,BM_ID,UM_ID,fields,"column validation failed");
								errorCount++;
								break M;
							}
						
						}
					 	
					}
					 
				}else if (atWhatline > 1) {
					//some line is empty
					System.out.println("line is empty222");
					//session.put("msg", "File is empty");
				//insert into DB----error
					saveStudentErrorLog(IM_ID,SM_ID,BM_ID,UM_ID,fields,"empty row");
					errorCount++;
					break M;	  
				}
			
			 } catch (Exception e) {
				// TODO Auto-generated catch block
				 //inpLine
				 saveStudentErrorLog(IM_ID,SM_ID,BM_ID,UM_ID,new String[]{"some"},"some other");
				e.printStackTrace();
				//insert into DB----error
				errorCount++;
			}
		}
	  }//empty line after header validation...
	 }//while############################################3
		
		session.put("msg", "Student File Upload Success");
		session.put("result", errorCount+"~"+ignoredCount+"~"+(insertedCount)+"~"+(totalRecords-1));
		System.out.println("errorCount**"+errorCount);
		System.out.println("ignoredCount**"+ignoredCount);
		System.out.println("insertedCount**"+insertedCount);
		
	  } catch (Exception e) {
			// TODO Auto-generated catch block
		  session.put("msg", "Invalid File");
			e.printStackTrace();
			return INPUT;
	 }
		return SUCCESS;	
  }

	private boolean isAdmissionNumberExist(String iM_ID, String admissionN) {
		// TODO Auto-generated method stub
		 StudentDetailsDAO studentDetailsDao = (StudentDetailsDAO)ServiceFinder.getContext(request).getBean("StudentDetailsDAO");
		try {
			if (studentDetailsDao.findByProperty("admissionNumber", admissionN) == null) {
				return false;
			}			
		} catch (Exception e) {e.printStackTrace();	}
		return	true;	
	}

	private boolean isValidLine(String[] fields,String bm_id) {
		// TODO Auto-generated method stub---27 fields
		/*String[] format = {"Student_First_Name","Student_Last_Name","DOB","Admission_Number","Admission_Date","Class_Admitted_in","Current_Class","Current_Section","Gender",
				"Father_First_Name","Father_Last_Name","DOB","Mother_First_Name","Mother_Last_Name","DOB","Primary_Email","Primary_Mobile","Landline","Address1","Address2","City","State"};
*/	
		for (int i = 0; i < fields.length; i++) {
			if(!Validate(fields[i], i,bm_id)){
				return false;
			}		
		}		
		return true;
	}
	
	//************Validate methods*************
	
	   private boolean Validate(String value,int index,String bm_id){
		   System.out.println("in validate()-------"+value+"----"+index);
			  if (value!= null) {
				  value= value.trim();
				}else{
					return false;
				}
	  
		   String regx = "";									//	0						1						2						3									4							5												6						7
		   /*String[] format = {"Student_First_Name","Student_Last_Name","DOB","Admission_Number","Admission_Date","Class_Admitted_in","Current_Class","Current_Section","Gender",
			"Father_First_Name","Father_Last_Name","DOB","Mother_First_Name","Mother_Last_Name","DOB","Primary_Email","Primary_Mobile","Landline","Address1","Address2","City","State"};
*/	
		if (index == 0) {
			regx = "^[a-zA-Z-. ]{2,25}$";//name
		} else if (index == 1) {
			regx = "^[a-zA-Z-. ]{2,25}$";//lname
		} else if (index == 2) {
			boolean hjhj= dateValidation(value);
			System.out.println(hjhj+"from date validation result");
			if(!dateValidation(value)){
				return false;
			}else{
				return true;
			}
		} else if (index == 3) {
			regx = "^[0-9-+ ]{8,15}$";
		} else if (index == 4) {
			if(!dateValidation(value)){
				return false;
			}else{
				return true;
			}
		} else if (index == 5) {
			//validate from DB-class name
			classAdmitID = getClassID(value);//prasently it will look all classes in class master but not specific to branch
			if (classAdmitID == null || classAdmitID == "null" ) {
				return false;
			} else{
				return true;
			}
		} else if (index == 6) {
			//validate from DB --Current_Class name is dr in DB or not
			classCurID = getClassID(value);
			if (classCurID == null || classCurID == "null" ) {
				return false;
			} else{
				return true;
			}
		} else if (index == 7) {
			//validate from DB --section name is dr in BD or not
			sectionID 					= getSectionID(value);//here  section id is req for getting sectionId..it's further give to getsectionclassMapId(),,
		//	sectionClassMapID	= 	getSctionClassMapID(bm_id,classAdmitID,sectionID);
			sectionClassMapID	= 	getSctionClassMapID(bm_id,classCurID,sectionID);//based on current class only  v get section class map id....
		/*	if (sectionID == null || sectionID == "null" ) {
				return false;
			}else*/
			if (sectionClassMapID == null || sectionClassMapID == "null" ) {
				return false;
			} else{
				return true;
			}
			
		} else if (index == 8) {
			if (value.equalsIgnoreCase("F") || value.equalsIgnoreCase("M")) {		
				return true;			
			} else {
				return false;
			}
		} else if (index == 9) {
			//9										10							11		12									13								14			15						16							17						18		19			20		21
			//"Father_First_Name","Father_Last_Name","DOB","Mother_First_Name","Mother_Last_Name","DOB","Primary_Email","Primary_Mobile","Landline","Address1","Address2","City","State"};
			regx = "^[a-zA-Z-. ]{2,25}$";//Father_First_Name		
		} else if (index == 10) {
			regx = "^[a-zA-Z-. ]{2,25}$";//Father_Last_Name			
		} else if (index == 11) {
			if(!dateValidation(value)){
				return false;
			}else{
				return true;
			}//Father DOB
		} else if (index == 12) {
			regx = "^[a-zA-Z-. ]{2,25}$";//Mother_First_Name			
		} else if (index == 13) {
			regx = "^[a-zA-Z-. ]{2,25}$";//Mother_last_Name			
		} else if (index == 14) {
			if(!dateValidation(value)){
				return false;
			}else{
				return true;
			}//mother DOB			
		} else if (index == 15) {
			regx	= "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";//email		
		} else if (index == 16) {
			regx	="^(\\+91-|\\+91|0)?\\d{10}$";//mobile
		} else if (index == 17) {
			regx	= "^\\d{3,5}(\\d{6,8})?$";//land line
		} else if (index == 18) {
			regx="^[a-z-A-Z][a-zA-Z0-9\\s,'-.]*$";
		} else if (index == 19) {
			regx="^[a-z-A-Z][a-zA-Z0-9\\s,'-.]*$";			
		} else if (index == 20) {
			regx="^[a-zA-Z][a-zA-Z ]*['.]?[a-zA-Z ]+[a-zA-Z ]+$";			
		}else if (index == 21) {
		//validate from db---state id/state short name(not dr in DB)/state name
			stateID = getStateID(value);
			if (stateID == null || stateID == "null" ) {
				return false;
			}else{
				return true;
			}
		}
	   
		   Pattern p = Pattern.compile(regx);//
		   Matcher m = p.matcher(value);
		  		   
		   return m.matches();  //matches will search the whole string		   
	   }
	

	   public boolean dateValidation(String date) {
		   String[] formatStrings = {"dd-MM-yyyy","dd/MM/yyyy","dd.MM.yyyy","dd MM yyyy"};			
		   System.out.println(date+"--------------------dateValidation()");
		    for (String formatString : formatStrings)		    {
		        try
		        {
		        	SimpleDateFormat sdf = new SimpleDateFormat(formatString);
		        	 sdf.parse(date);		   
		        	 System.out.println("true after validationnnnnnnnnnnnnnnnnn");
		            return true;
		        }
		        catch (ParseException e) {
		        	System.out.println("false"+formatString);
		        	}
		    }
		    
		   return false;
	}
	   

	   public String getValidDate(String date) {
		   String[] formatStrings = {"dd-MM-yyyy","dd/MM/yyyy","dd.MM.yyyy","dd MM yyyy"};			
		    for (String formatString : formatStrings){
		        try
		        {
		        	SimpleDateFormat sdf = new SimpleDateFormat(formatString);
		        	SimpleDateFormat myFormat = new SimpleDateFormat("yyyy-MM-dd");
		        	Date drr = sdf.parse(date);
		        	String Org = myFormat.format(drr);		        	
		            return Org;
		        }
		        catch (ParseException e) {
		        	//System.out.println("false"+formatString);
		        	}
		    }
		 return date;
	}
	
	    	
	
	   
	private boolean doInsertIntoDB(String[] fields,String IM_ID, String SM_ID, String BM_ID){
		 for (int i = 0; i < fields.length; i++) 
		  {System.out.print("%%--"+fields[i]);
			 fields[i] = fields[i].replace("\"", "");	
			 System.out.print("&&--"+fields[i]);
		  }		 
		 
		 
		 StudentDetailsDAO studentDetailsDao = (StudentDetailsDAO)ServiceFinder.getContext(request).getBean("StudentDetailsDAO");
		 UserMasterDAO userMasterDao = (UserMasterDAO)ServiceFinder.getContext(request).getBean("UserMasterHibernateDao");
		 ParentDetailsDAO parentDetailsDao = (ParentDetailsDAO)ServiceFinder.getContext(request).getBean("ParentDetailsHibernateDao");
		 //two usermaster entries ,1 student details and 2 parent details(father,mother) ,2 Parent_Student_Map, 1  student_section entry
			
	 
			try {
				 
				 String[] idPasswordStu =  getIdPassword("Stu",fields[0],fields[1],IM_ID).split("~");
				 String[] idPasswordPar =  getIdPassword("Par",fields[9],fields[10],IM_ID).split("~");
				
				 
				 String autoUserIDStu 					= 	idPasswordStu[0];
				 String autoPasswordStu 					= 	idPasswordStu[1];
				
				 String autoUserIDPar					= 	idPasswordPar[0];
				 String autoPasswordPar 					= 	idPasswordPar[1];
				 System.out.println("");
				 System.out.println(autoUserIDStu+"--stu--"+autoPasswordStu+"--par-"+autoUserIDPar+"--"+autoPasswordPar);
				 
				 UserMaster umaster = new UserMaster();
							umaster.setImId(Long.parseLong(IM_ID));
							umaster.setSmId(Long.parseLong(SM_ID));
							umaster.setBmId(Long.parseLong(BM_ID));
							umaster.setUtId(new Long(5));//5-student
							umaster.setUserId(autoUserIDStu);
							umaster.setPassword(autoPasswordStu);
							umaster.setActive("Y");
						
				
							//Student_First_Name,Student_Last_Name,DOB,Admission_Number,Admission_Date,Class_Admitted_In,Current_Class,Current_Section,Gender,---9
							//Father_First_Name,Father_Last_Name,DOB,Mother_First_Name,Mother_Last_Name,DOB,Primary_Email,Primary_Mobile,Landline,Address1,Address2,City,State		 
							
				StudentDetails studentData =new StudentDetails();
				
				
				int i = 0;
				
				//0									1
				//Student_First_Name,Student_Last_Name,DOB,Email,Mobile,Landline,Address1,Address2,City,State,Admission_Number,Admission_Date,Class_Admitted_in,Gender,Active,
				
				//Student_First_Name,Student_Last_Name,DOB,Admission_Number,Admission_Date,Class_Admitted_In,Current_Class,Current_Section,Gender,---9
				studentData.setFirstName(fields[i++]);
				studentData.setLastName(fields[i++]);
				studentData.setDob(getValidDate((fields[i++])));
				studentData.setAdmissionNumber(fields[i++]);
				studentData.setAdmissionDate(getValidDate((fields[i++])));
				String classNameO = fields[i++];
				//String classID = getClassID(classNameO);
				studentData.setClassAdmittedIn(classAdmitID == null?classNameO:classAdmitID);//------Class id required,so based on className get the classID...dis is one type validation also
				
				String Current_Class = fields[i++];//section_student_map Table
				String Current_Section = fields[i++];			
				
				studentData.setGender(fields[i++]);
//Father_First_Name (9),Father_Last_Name,DOB,Mother_First_Name(12),Mother_Last_Name,DOB,Primary_Email(15),Primary_Mobile,Landline,Address1(18),Address2,City,State
				studentData.setEmail("NA");
				studentData.setMobile("NA");
				studentData.setLandline("NA");
				studentData.setAddress1(fields[18]);
				studentData.setAddress2(fields[19]);
				studentData.setCity(fields[20]);
				//
				//String stateID = getStateID(fields[21]);
				studentData.setState(stateID);
				studentData.setActive("Y");
				
				studentData.setPhoto("noImage.jpg");
				
				
				userMasterDao.save(umaster);
				studentData.setUmId(umaster.getUmId()+"");			
				studentDetailsDao.save(studentData);
				
			//	System.out.println("Student FNmae"+fields[0]+"DOB"+fields[10]+"Parent FNmae"+fields[15]+"DOB"+fields[20]);
				System.out.println(umaster.getUmId()+"--umasterID-");
				
				//father
				 UserMaster umasterPar = new UserMaster();
				 umasterPar.setImId(Long.parseLong(IM_ID));
				 umasterPar.setSmId(Long.parseLong(SM_ID));
				 umasterPar.setBmId(Long.parseLong(BM_ID));
				 umasterPar.setUtId(new Long(2));//2-Parent
				 umasterPar.setUserId(autoUserIDPar);
				 umasterPar.setPassword(autoPasswordPar);
				 umasterPar.setActive("Y");
				 
				 //mother---not needed in usermaster
			/*	 UserMaster umasterMother = new UserMaster();
				 umasterMother.setImId(Long.parseLong(IM_ID));
				 umasterMother.setSmId(Long.parseLong(SM_ID));
				 umasterMother.setBmId(Long.parseLong(BM_ID));
				 umasterMother.setUtId(new Long(2));//2-Parent
				 umasterMother.setUserId(autoUserIDPar);
				 umasterMother.setPassword(autoPasswordPar);
				 umasterMother.setActive("Y");*/
				
				
				ParentDetails parentData =new ParentDetails();

				ParentDetails motherData =new ParentDetails();
				
				//i start here 9
				parentData.setFirstName(fields[i++]);
				parentData.setLastName(fields[i++]);
				parentData.setDob(getValidDate(fields[i++]));
				
				motherData.setFirstName(fields[i++]);
				motherData.setLastName(fields[i++]);				
				motherData.setDob(getValidDate(fields[i++]));
				
				parentData.setEmail(fields[i]);
				motherData.setEmail(fields[i]);i++;
				parentData.setMobile(fields[i]);
				motherData.setMobile(fields[i]);i++;
				parentData.setLandline(fields[i]);
				motherData.setLandline(fields[i]);i++;
				//Father_First_Name (9),Father_Last_Name,DOB,Mother_First_Name(12),Mother_Last_Name,DOB,Primary_Email(15),Primary_Mobile,Landline,Address1(18),Address2,City,State
				parentData.setAddress1(fields[i]);
				motherData.setAddress1(fields[i]);i++;
				parentData.setAddress2(fields[i]);
				motherData.setAddress2(fields[i]);i++;
				parentData.setCity(fields[i]);
				motherData.setCity(fields[i]);i++;
				parentData.setState(stateID);	
				motherData.setState(stateID);	
				
				parentData.setPtmId("1");
				 parentData.setActive("Y");				
				parentData.setIsDefault("Y");
				parentData.setPhoto("noImage.jpg");
				
				motherData.setPtmId("2");
				motherData.setActive("Y");				
				motherData.setIsDefault("N");
				motherData.setPhoto("noImage.jpg");
				
				userMasterDao.save(umasterPar);
				parentData.setUmId(umasterPar.getUmId());			
				parentDetailsDao.save(parentData);
				//mother
				motherData.setUmId(umasterPar.getUmId());			
				parentDetailsDao.save(motherData);
				
				System.out.println(umasterPar.getUmId()+"-umasterParID()"+"$$same as father$$$"+motherData.getUmId()+"-umasterMotherID");
				
				ParentStudentMap psm = new ParentStudentMap();
				psm.setActive("Y");
				psm.setPdId(parentData.getPdId()+"");
				psm.setStuId(studentData.getStuId()+"");
				
				//mother
				ParentStudentMap psmM = new ParentStudentMap();
				psmM.setActive("Y");
				psmM.setPdId(motherData.getPdId()+"");
				psmM.setStuId(studentData.getStuId()+"");
				
				
				System.out.println(parentData.getPdId()+"--both--"+studentData.getStuId());
				//Parent_Student Map
				studentDetailsDao.saveStudentParentMap(psm);
				studentDetailsDao.saveStudentParentMap(psmM);
				
				
				//getClassID(Current_Class);
				//String secID = getSectionID(Current_Section);
				
				//4/29/2013 12:23am--******* required...
				/*1)section_id
				 * 2)class_id
				 * 3)bm_id
				 * 4)using above 3 get Section_Class_Map id(SCM_ID).
				 * 5)insert dt id to Student_Section_Map 
				 * */
				
				//Student_Section_Map
				StudentSectionMap ssm = new StudentSectionMap();
				ssm.setActive("Y");
				ssm.setSmId(SM_ID);
				ssm.setImId(IM_ID);
				ssm.setBmId(BM_ID);
				ssm.setScmId(sectionClassMapID);
				ssm.setStuId(studentData.getStuId()+"");
			
				studentDetailsDao.insertStudentSectionMap(ssm);
				
				
			} catch (Exception e) {
				System.out.println("in exption");
				e.printStackTrace();
				return false;
			}
			return true;
	}
	
	

	private String getSctionClassMapID(String bM_ID, String curClassID,
			String sectionID2) {
		SectionMasterDAO smd = (SectionMasterDAO)ServiceFinder.getContext(request).getBean("SectionMasterDAO");
		try {
			SectionClassMap sectionMasterCheck = smd.findByProperty3(bM_ID, curClassID, sectionID2);
			return sectionMasterCheck.getScmId()+"";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}

	private String getClassID(String className) {
		try {
			// TODO Auto-generated method stub
			System.out.println(className+"---------------className");
			ClassMasterDAO classMasterdao = (ClassMasterDAO)ServiceFinder.getContext(request).getBean("ClassMasterHibernateDao"); 	
			ClassMaster classMasterData = new ClassMaster();
			
			classMasterData = 	classMasterdao.findByProperty("className", className.trim());
		
		return classMasterData.getCmId()+"";
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
	
	private String getSectionID(String sectionName) {
		try {
			// TODO Auto-generated method stub
			System.out.println("sectionName---"+sectionName);
			SectionMasterDAO sectionMasterdao = (SectionMasterDAO)ServiceFinder.getContext(request).getBean("SectionMasterHibernateDao"); 	
			SectionMaster sectionMasterData = new SectionMaster();
			
			sectionMasterData = 	sectionMasterdao.findByProperty("sectionName", sectionName.trim());
		
		return sectionMasterData.getSeMId()+"";
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
	
	public String getStateID(String StateName) {

		try {
			// TODO Auto-generated method stub
			System.out.println("StateName---------"+StateName);
			StateMasterDAO stateMasterdao = (StateMasterDAO)ServiceFinder.getContext(request).getBean("StateMasterHibernateDao"); 	
			StateMaster stateMasterData = new StateMaster();
			
			stateMasterData  = 	stateMasterdao.findByProperty("stateName", StateName.trim());
		
		return stateMasterData.getStateId()+"";
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
	
	public void saveStudentErrorLog(String iM_ID, String sM_ID, String bM_ID, String uM_ID, String[] fields, String reason) {
		System.out.println("reasonnnnnnn---"+reason);
		 StudentDetailsDAO studentDetailsDao = (StudentDetailsDAO)ServiceFinder.getContext(request).getBean("StudentDetailsDAO");
		 //delete old error log for same um_id
		 
		 StudentxlErrorTemp setemp = new StudentxlErrorTemp();
		 setemp.setBmId(bM_ID);
		 setemp.setImId(iM_ID);
		 setemp.setReason(reason);
		 setemp.setSmId(sM_ID);
		 setemp.setUmId(uM_ID);
		 
		 
		 StringBuffer sb = new StringBuffer();
		 for (int i = 0; i < fields.length; i++) {
			sb.append(fields[i]);
			if (i<fields.length-1) {
				sb.append(",");
			}
		}
		 String line = sb.toString();
		 System.out.println(iM_ID+"--"+sM_ID+"--"+bM_ID+"--"+uM_ID+"--"+fields+"--"+reason+"--"+line);

		 setemp.setErrorline(line);
		 
		 try {
			studentDetailsDao.insertStudentErrorLog(setemp);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
	}


	public void deleteStudentError(String uM_ID) {
		System.out.println("in deleet logggg----------");
		 StudentDetailsDAO studentDetailsDao = (StudentDetailsDAO)ServiceFinder.getContext(request).getBean("StudentDetailsDAO");
		 try {
			int  u = studentDetailsDao.deleteStudentErrorLog(uM_ID);
			System.out.println(u+"**^^^^^^^^*");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	public void setSession(Map session) {
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
				
				UserMasterDAO userMasterDao = (UserMasterDAO)ServiceFinder.getContext(request).getBean("UserMasterHibernateDao"); 	
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
		  System.out.println(userID+"~~~~~~~~~~~~"+userPass);
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
		return  "noImage.jpg";
	}
	
	}


	   

}



