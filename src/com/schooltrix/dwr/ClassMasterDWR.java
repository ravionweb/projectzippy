package com.schooltrix.dwr;

import java.io.File;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.Writer;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.directwebremoting.WebContextFactory;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.BeansException;
import com.schooltrix.daos.ClassMasterDAO;
import com.schooltrix.daos.SectionMasterDAO;
import com.schooltrix.hibernate.ClassBranchMap;
import com.schooltrix.hibernate.ClassMaster;
import com.schooltrix.hibernate.SectionMaster;
import com.schooltrix.managers.ServiceFinder;

public class ClassMasterDWR {
	HttpServletResponse response = WebContextFactory.get().getHttpServletResponse();
	HttpServletRequest request = WebContextFactory.get().getHttpServletRequest();
	HttpSession session = WebContextFactory.get().getSession();
	
	 public List<Object[]> getClassMasterList(String SM_ID, String BM_ID) {

			ClassMasterDAO classMasterdao =null;
			List<Object[]> userList= new ArrayList<Object[]>();
			try {
				String IM_ID =(String)session.getAttribute("IM_ID");
				
				System.out.println("in getClassMasterList DWR --"+SM_ID+"---"+BM_ID);
				
		/*		need im_id based on condti...allschools ..and all branches..get list of classs....im_id from session
				SM_ID=0
				BM_ID=0*/
				
				classMasterdao = (ClassMasterDAO)ServiceFinder.getContext(request).getBean("ClassMasterHibernateDao"); 		
				
				if (SM_ID.equalsIgnoreCase("0")) {
					if (BM_ID.equalsIgnoreCase("0")) {
						//all classes under institution
						userList = classMasterdao.getClassMasterList(IM_ID,SM_ID,BM_ID,1);
						
					}else if (Long.parseLong(BM_ID)>=0){
						//here based on branch id only we get classes list....
						userList = classMasterdao.getClassMasterList(IM_ID,SM_ID,BM_ID,2);
					}					
				} else  if (Long.parseLong(SM_ID)>=0){
					//under one school...
					if (BM_ID.equalsIgnoreCase("0")) {
						//all classes under school
						userList = classMasterdao.getClassMasterList(IM_ID,SM_ID,BM_ID,3);
						
					}else if (Long.parseLong(BM_ID)>=0){
						//here based on branch id only we get classes list....
						userList = classMasterdao.getClassMasterList(IM_ID,SM_ID,BM_ID,2);
					}		
				}
				
					
					
					return userList;
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
	 
	 public List<Object[]> getClassMasterList(String BM_ID) {

			ClassMasterDAO classMasterdao =null;
			List<Object[]> userList= new ArrayList<Object[]>();
			try {
							
				System.out.println("in getClassMasterList DWR ");
				classMasterdao = (ClassMasterDAO)ServiceFinder.getContext(request).getBean("ClassMasterHibernateDao"); 		
					userList = classMasterdao.getClassMasterList(BM_ID);					
					return userList;
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
	 
	 
	 public String getMultiClassMasterList(String[] bmIDString) {

			ClassMasterDAO classMasterdao =null;
			List<String[]> classList= new ArrayList<String[]>();
			try {
				
				StringBuffer inString =new StringBuffer();
				for (int i = 0; i < bmIDString.length; i++) {
					inString.append(bmIDString[i]);
					if(i<bmIDString.length-1)
					inString.append(",");
					
				}
				
				String inQuery = inString+"";
				//System.out.println("in getMultiClassMasterList DWR ******"+bmIDString.length);
				classMasterdao = (ClassMasterDAO)ServiceFinder.getContext(request).getBean("ClassMasterHibernateDao"); 		
				classList = classMasterdao.getMultiClassMasterList(inQuery);
					//System.out.println(classList.size()+"************");

					String temp = "";
					JSONArray jsonArr = new JSONArray();
					for (int i = 0; i < classList.size(); i++) {
						JSONObject jsonObject = new JSONObject();
						Object[] row1 = (Object[])classList.get(i);
						String class_id = (String) row1[0];
						String class_name = (String) row1[1];
						JSONArray jsArrayBM = new JSONArray();
						JSONArray jsArrayCBM = new JSONArray();
						//System.out.println(class_id+"***"+class_name);
						
						if (!class_id.equalsIgnoreCase(temp)) {
							temp = class_id;
						}else{
							continue;
						}
					//	for (int p = 0; p <classList.size(); p++) {
							for (int p = i; p <classList.size(); p++) {
							Object[] row = (Object[])classList.get(p);
							if (class_id.equalsIgnoreCase( (String) row[0])) {
								jsArrayBM.put(row[2]);
								jsArrayCBM.put(row[3]);
							}
						}
						
						jsonObject.put("class_name", class_name);
						jsonObject.put("bms", jsArrayBM);
						jsonObject.put("cbm_id", jsArrayCBM);
						jsonObject.put("class_id", class_id);
						
						//System.out.println(jsonObject+"&&&&");
						jsonArr.put(jsonObject);
					}
					System.out.println(jsonArr+"<--jsonObject1");
					return jsonArr.toString();
			} catch (BeansException e) {
			e.printStackTrace();
			return null;
			} catch (Exception e) {
			e.printStackTrace();
			return null;
			}

	 }	
	 
	 public List<Object[]> getSubjectMasterList(String SM_ID, String BM_ID,String CM_ID) {//may only one cm_id req..

		 if (CM_ID != null) {
			ClassMasterDAO classMasterdao =null;
			List<Object[]> subList= new ArrayList<Object[]>();
			try {
				String IM_ID =(String)session.getAttribute("IM_ID");
				System.out.println("in getSubjectMasterList DWR "+CM_ID);
				classMasterdao = (ClassMasterDAO)ServiceFinder.getContext(request).getBean("ClassMasterHibernateDao"); 		
						subList = classMasterdao.getSubjectMasterList(IM_ID,BM_ID,SM_ID,CM_ID);		
				return subList;
			} catch (BeansException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
			} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			}
		 }
		 return null;
	 }	 
	 
	 public List<Object[]> getMultiSubjectMasterList(String SM_IDs[], String[] BM_IDs,String CM_IDs[]) {

		 if (CM_IDs != null || BM_IDs!=null) {
			 
			 	StringBuffer inSM_IDs =new StringBuffer();
				StringBuffer inBM_IDs =new StringBuffer();
				StringBuffer inCM_IDs =new StringBuffer();
				
				
				//in('2','3')//single colon not req..
				for (int i = 0; i < SM_IDs.length; i++) {									
					inSM_IDs.append(SM_IDs[i]);
					if(i<SM_IDs.length-1)
						inSM_IDs.append(",");
				}
				
				
				for (int i = 0; i < BM_IDs.length; i++) {									
					inBM_IDs.append(BM_IDs[i]);
					if(i<BM_IDs.length-1)
						inBM_IDs.append(",");
				}
			 
				for (int i = 0; i < CM_IDs.length; i++) {									
					inCM_IDs.append(CM_IDs[i]);
					if(i<CM_IDs.length-1)
						inCM_IDs.append(",");
				}
			 
				System.out.println(inSM_IDs+"--"+inBM_IDs+"---"+inCM_IDs);
				
			ClassMasterDAO classMasterdao =null;
			List<Object[]> subList= new ArrayList<Object[]>();
			try {
				String IM_ID =(String)session.getAttribute("IM_ID");
				classMasterdao = (ClassMasterDAO)ServiceFinder.getContext(request).getBean("ClassMasterHibernateDao"); 		
						subList = classMasterdao.getMultiSubjectMasterList(IM_ID,inSM_IDs+"",inBM_IDs+"",inCM_IDs+"");		
				return subList;
			} catch (BeansException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
			} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			}
		 }
		 return null;
	 }	 
	 
	 public List<Object[]> getMultiSectionMasterList(String[] BM_IDs,String CM_IDs[]) {

		 if (CM_IDs != null || BM_IDs!=null) {
			 
				StringBuffer inBM_IDs =new StringBuffer();
				StringBuffer inCM_IDs =new StringBuffer();
				
				
				for (int i = 0; i < BM_IDs.length; i++) {									
					inBM_IDs.append(BM_IDs[i]);
					if(i<BM_IDs.length-1)
						inBM_IDs.append(",");
				}
			 
				for (int i = 0; i < CM_IDs.length; i++) {									
					inCM_IDs.append(CM_IDs[i]);
					if(i<CM_IDs.length-1)
						inCM_IDs.append(",");
				}
			 
				System.out.println("--"+inBM_IDs+"---"+inCM_IDs);
				
			SectionMasterDAO sectionMasterDAO=null;
			List<Object[]> subList= new ArrayList<Object[]>();
			try {
				sectionMasterDAO = (SectionMasterDAO)ServiceFinder.getContext(request).getBean("SectionMasterHibernateDao"); 		
						subList = sectionMasterDAO.getSectionClassMapList(inBM_IDs+"",inCM_IDs+"");		
				return subList;
			} catch (BeansException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
			} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			}
		 }
		 return null;
	 }	 

	 public String classNameChange(String schoolID,String branchID,String classID,String newClassName) {
		 ClassMasterDAO classMasterDAO = null;
		 try {
			 classMasterDAO = (ClassMasterDAO)ServiceFinder.getContext(request).getBean("ClassMasterHibernateDao");
			 return classMasterDAO.reNameClassName(classID,newClassName,branchID);//present only on classid ..if req change to based on branch and school id's
		 } catch (Exception e) {
			// TODO: handle exception
		}
		 return "fail";
	}

	 
	 public String saveNewClass(String schoolID,String branchID,String className) {
		 ClassMasterDAO classMasterDAO = null;
		 try {
			 ClassMaster classMaster = new ClassMaster();
			 classMaster.setActive("Y");
			 classMaster.setClassName(className);
			 classMasterDAO = (ClassMasterDAO)ServiceFinder.getContext(request).getBean("ClassMasterHibernateDao");
			 return classMasterDAO.saveClass(classMaster,branchID);//present only on classid ..if req change to based on branch and school id's
		 } catch (Exception e) {
			// TODO: handle exception
		}
		 return null;
	}
	 
	 public String disableCLassMaster(String bmID,String cmID) {
		//if students are there notify..req?
		 ClassMasterDAO classMasterDAO = null;
		 try {
			 classMasterDAO = (ClassMasterDAO)ServiceFinder.getContext(request).getBean("ClassMasterHibernateDao");
			 return classMasterDAO.disableCLass(bmID,cmID);//present only on classid ..if req change to based on branch and school id's
		 } catch (Exception e) {
			// TODO: handle exception
		}
		 return null;
		 
		 
	}
	 
	 //*******				**********Section Start**********			*************
	 public String sectionNameChange(String schoolID,String branchID,String classID,String sectionID,String newSectionName) {
		 SectionMasterDAO sectionMasterDAO = null;
		 try {
			 sectionMasterDAO = (SectionMasterDAO)ServiceFinder.getContext(request).getBean("SectionMasterHibernateDao");
			 return sectionMasterDAO.reNameSectionName(branchID,classID,sectionID,newSectionName);//present only on classid ..if req change to based on branch and school id's
		 } catch (Exception e) {
			 // TODO: handle exception
		 }
		 return "fail";
	 }
	 
		 public String saveNewSection(String schoolID,String branchID,String classID,String sectionName) {
			 SectionMasterDAO sectionMasterDAO = null;
			 try {
				 SectionMaster sectionMaster = new SectionMaster();
				 sectionMaster.setActive("Y");
				 sectionMaster.setSectionName(sectionName);
				 sectionMasterDAO = (SectionMasterDAO)ServiceFinder.getContext(request).getBean("SectionMasterHibernateDao");
				 
				 
				 return sectionMasterDAO.saveSection(sectionMaster,branchID,classID);
			 } catch (Exception e) {
				// TODO: handle exception
			}
			 return null;
		}
		 
		 public String disableSectionMaster(String schoolID,String branchID,String classID,String sectionID,String count) { //count only for student ref
			 
			 SectionMasterDAO sectionMasterDAO = null;
			 try {
				 sectionMasterDAO = (SectionMasterDAO)ServiceFinder.getContext(request).getBean("SectionMasterHibernateDao");
				 return sectionMasterDAO.disableSection(schoolID,branchID,classID,sectionID,count);
			 } catch (Exception e) {
				// TODO: handle exception
			}
			 return null;
		 }
		 
		 // ClassMasterDWR.sectionMove(schoolNames,branchNames,classNames,oldSectionID,newSectionID,function(data){
		 public String sectionMove(String schoolID,String branchID,String classID,String oldSectionID,String newSectionID) {
			 
			 SectionMasterDAO sectionMasterDAO = null;
			 try {
				 sectionMasterDAO = (SectionMasterDAO)ServiceFinder.getContext(request).getBean("SectionMasterHibernateDao");
				 return sectionMasterDAO.moveSection(schoolID,branchID,classID,oldSectionID,newSectionID);
			 } catch (Exception e) {
				// TODO: handle exception
			}
			 return null;
		 }
		 
		 
		 
	 //*******				**********Section End**********			*************
		 
/*	 public List getClassMasterList(String  IM_ID,String SM_ID, String BM_ID) {
		 
		 ClassMasterDAO classMasterdao =null;
		 List<String> userList= new ArrayList<String>();
		 try {
			 System.out.println("in getClassMasterList DWR ");
			 classMasterdao = (ClassMasterDAO)ServiceFinder.getContext(request).getBean("ClassMasterHibernateDao"); 		
			 ClassMaster classInputData = new ClassMaster();
			 userList = classMasterdao.getClassMasterList(IM_ID,BM_ID,SM_ID);					
			 return userList;
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
*/	  
}

