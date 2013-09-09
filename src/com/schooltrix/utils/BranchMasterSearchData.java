package com.schooltrix.utils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.BeansException;

import com.schooltrix.daos.BranchMasterDAO;
import com.schooltrix.daos.SchoolMasterDAO;
import com.schooltrix.managers.ServiceFinder;

public class BranchMasterSearchData {

	HttpSession session;
	public String getBranchSearchData(String[] schoolNames,String[] branchNames,	String[] 	shortName, String[] state , String[] city, String addr, 
			String contactPerson , String mobile,	String 	landline ,String 	email,String active,String IM_ID,int jsonFlag,HttpServletRequest request,HttpSession session) {
		
		this.session = session;
		StringBuffer insmIds	 	= new StringBuffer();
		StringBuffer inbmIds	 	= new StringBuffer();
		StringBuffer inbnIds	 	= new StringBuffer();
		StringBuffer incityIds 		= new StringBuffer();
		StringBuffer instateIds 		= new StringBuffer();
		
		String smIDs = null;
		String bmIDs = null;
		String snIDs = null;
		String cityIDs = null;
		String stateIDs = null;
		StringBuffer bm_ids = new StringBuffer();
		
		JSONObject jObject = new JSONObject();
		
		try {
			  if (schoolNames != null) {
				  for (int i = 0; i < schoolNames.length; i++) {
					  insmIds.append(schoolNames[i]);
							if(i<schoolNames.length-1)
								insmIds.append(",");		
					 }
				  
			  }else{
				  insmIds.append( "'%'");
			  }
			  
				jObject.put("sm_ids", insmIds.toString().replaceAll("'", ""));
			  
			  if (branchNames != null) {
				  for (int i = 0; i < branchNames.length; i++) {
					  inbmIds.append(branchNames[i]);
							if(i<branchNames.length-1)
								inbmIds.append(",");		
					 }
				  
			  }else{
				  inbmIds.append( "'%'");
			  }
			  
			  if (shortName != null) {
				  for (int i = 0; i < shortName.length; i++) {
					  inbnIds.append(shortName[i]);
					  if(i<shortName.length-1)
						  inbnIds.append(",");		
				  }
				  
			  }else{
				  inbnIds.append( "'%'");
			  }
			
			  if (inbmIds.toString().contains("%") && inbnIds.toString().contains("%") ) {
				  bm_ids = bm_ids.append("'%'");
				jObject.put("bm_ids", inbmIds.toString().replaceAll("'", ""));
				jObject.put("bn_ids",inbnIds.toString().replaceAll("'", ""));
			} else if (inbnIds.toString().contains("%") ){
				bm_ids = inbmIds;
				jObject.put("bm_ids",inbmIds.toString().replaceAll("'", ""));
				jObject.put("bn_ids", inbnIds.toString().replaceAll("'", ""));
			} else if (inbmIds.toString().contains("%") ){
				bm_ids = inbnIds;
				jObject.put("bm_ids", inbmIds.toString().replaceAll("'", ""));
				jObject.put("bn_ids", inbnIds.toString().replaceAll("'", ""));
			}else{
				bm_ids = inbmIds;//temperarory...we need to disable any one option ....while selecting other school name or short name..				
				jObject.put("bm_ids",inbmIds.toString().replaceAll("'", ""));
				jObject.put("bn_ids", inbnIds.toString().replaceAll("'", ""));
			}
			  
			  if (state != null) {
				  for (int i = 0; i < state.length; i++) {
					  instateIds.append(state[i]);
					  if(i<state.length-1)
						  instateIds.append(",");		
				  }
				  
			  }else{
				  instateIds.append( "'%'");
			  }
				jObject.put("stateIds",instateIds.toString().replaceAll("'", ""));
			  
			  if (city != null) {
				  for (int i = 0; i < city.length; i++) {
					  if(i ==0)
						  incityIds.append("'");					  
					  
					  incityIds.append(city[i]);
					  
					  if(i<city.length-1)
						  incityIds.append("','");	
					  
					  if(i ==(city.length-1))
						  incityIds.append("'");		
				  }
				  
			  }else{
				  incityIds.append( "'%'");
			  }
			  
				jObject.put("cityIds",incityIds.toString().replaceAll("'", ""));
			System.out.println(addr+"-***-"+contactPerson+"****-"+mobile+"***-"+landline+"*****-"+email+"*Active*"+active);
		
			 if (addr.equalsIgnoreCase("")) {
				 jObject.put("addr", addr);
				addr = "%";
			} else{
				jObject.put("addr", addr);
				addr = "%"+addr+"%";
			}
			 if (contactPerson.equalsIgnoreCase("")) {
				 jObject.put("contactPerson", contactPerson);
				 contactPerson = "%";
			 } else{
				 jObject.put("contactPerson", contactPerson);
				 contactPerson = "%"+contactPerson+"%";
			 }
				
			 if (mobile.equalsIgnoreCase("")) {
				 jObject.put("mobile", mobile);
				 mobile = "%";
			 } else{
				 jObject.put("mobile", mobile);
				 mobile = "%"+mobile+"%";
			 }
			 
			 if (landline.equalsIgnoreCase("")) {
				 jObject.put("landline", landline);
				 landline = "%";
			 } else{
				 jObject.put("landline", landline);
				 landline = "%"+landline+"%";
			 }
			 if (email.equalsIgnoreCase("")) {
				 jObject.put("email", email);
				 email = "%";
			 } else{
				 jObject.put("email", email);
				 email = "%"+email+"%";
			 }
			 if (active != null && active.equalsIgnoreCase("y")) {
				 jObject.put("active", "y");
			 } else{
				 jObject.put("active", "n");
				 active = "N";
			 }
			 
			 if (jsonFlag == 1) {
				 construtQueryStringForHistory(insmIds+"",bm_ids+"",incityIds+"",instateIds+"",addr,contactPerson,mobile,landline,email,active,Long.parseLong(IM_ID));
				 session.setAttribute("jsonForBranchSearch", jObject.toString());
			}
			 
			//	System.out.println(addr+"-***-"+contactPerson+"****-"+mobile+"***-"+landline+"*****-"+email+"*Active*"+active);
			  
			 // conitune further/.active.................
		
		System.out.println(bm_ids+"<--Major"+insmIds+"***"+inbnIds+"****"+incityIds+"*****"+instateIds);
			  
		}catch (Exception e) {
				e.printStackTrace();
		}
		BranchMasterDAO branchMasterDAO= null;
		try {
			branchMasterDAO = (BranchMasterDAO)ServiceFinder.getContext(request).getBean("BranchMasterHibernateDao");
			List branchDataList = new ArrayList();
			branchDataList =	branchMasterDAO.getBranchesForEdit(insmIds+"",bm_ids+"",incityIds+"",instateIds+"",addr,contactPerson,mobile,landline,email,active,Long.parseLong(IM_ID));
			
			System.out.println(branchDataList.size()+"BEFOREin DWR");
			
		//////-----------------------------------------------------------------------------------------------	
			
			
			String schoolIte = "";
			
			JSONArray MainJsonArray		= new JSONArray();		
			
			Set<String> schoolSet = new HashSet();
					
	for (Iterator iterator1 = branchDataList.iterator(); iterator1	.hasNext();) {
		JSONObject MainJsonObject = new JSONObject();		
		try {
		Object row1[] = (Object[]) iterator1.next();
		//school wise
		JSONArray schoolMainJson= new JSONArray();
		System.out.println(schoolIte+"**schoolIte***"+row1[2]);//33 times
		//		0				1				2				3						4							5			6				7			8					9							10				11		12			13	
		// bm_ID,IM_ID,School_Name,Branch_Name,,Short_Name,,Address,,City,,State_ID,State_name,Contact_Person,Email_ID,Mobile,Landline,Active  
			if (schoolSet.add((String)row1[2])) {			
			
			schoolIte = (String)row1[2];
			System.out.println("in scgool-*"+schoolIte);//expecting 2 times
			Set<String> branchSet = new HashSet();
			
			System.out.println(schoolSet.size()+"SET schoolSet-->"+schoolSet);
			String branchIte = "";
			for (Iterator iterator2 = branchDataList.iterator(); iterator2	.hasNext();) {
				try {
					Object row2[] = (Object[]) iterator2.next();
					JSONObject schoolJson= new JSONObject();
			//		System.out.println(schoolIte+"*inner schoolIte*"+(String)row2[10]);//33times---64 Total
		
				//	System.out.println(branchSet.size()+"SET branch-->"+branchSet);
				if(schoolIte.equalsIgnoreCase((String)row2[2])){//extra chechking for unnessary reputation stooping
				//branch wise
			//		System.out.println(branchIte+"*branchIte**"+(String)row2[9]);//33times--but school wise--64
					
		//						if (!branchIte.equalsIgnoreCase((String)row2[9])) {
					if (branchSet.add((String)row2[3])) {//88888888888888888888888888888888888888888888888888888
			//			System.out.println("in branchIte");//2 times
						
						branchIte = (String)row2[3];
						JSONArray brachJsonMain= new JSONArray();//i think here onwords we can write our code((((((((((((((((((((((((((((((((((((((99
						
						System.out.println("in branch-*"+branchIte);
						//		0				1				2				3						4							5			6				7			8					9							10				11		12			13	
						// bm_ID,IM_ID,School_Name,Branch_Name,,Short_Name,,Address,,City,,State_ID,State_name,Contact_Person,Email_ID,Mobile,Landline,Active  
						int i = 0;
						JSONObject brachJson= new JSONObject();
						
						brachJson.put("bm_ID", (String)row2[i]); 			i++;
						brachJson.put("IM_ID", (String)row2[i]); 			i++;
						brachJson.put("School_Name", (String)row2[i]);			i++;
						brachJson.put("Branch_Name", (String)row2[i]);			i++;
						brachJson.put("Short_Name", (String)row2[i]);			i++;
						brachJson.put("Address", (String)row2[i]);			i++;
						brachJson.put("City", (String)row2[i]);			i++;
						brachJson.put("State_ID", (String)row2[i]);			i++;
						brachJson.put("State_name", (String)row2[i]);			i++;
						brachJson.put("Contact_Person", (String)row2[i]);			i++;
						brachJson.put("Email_ID", (String)row2[i]);			i++;
						brachJson.put("Mobile", (String)row2[i]);			i++;
						brachJson.put("Landline", (String)row2[i]);			i++;
						brachJson.put("Active", (String)row2[i]);
						 if (brachJson.length()>0) {
							 brachJsonMain.put(brachJson);	 
						}
						//branchName : branchArray(brachJsonMain)
						 					//branchArray->objects(brachJson)
							if (brachJsonMain.length()>0) {
								 schoolJson.put( (String)row2[3], brachJsonMain);	 
							}
						
						   }//branch wise IF condition
							 
							System.out.println("brach main array-->"+schoolJson);//33time
							if (schoolJson.length()>0) {
								schoolMainJson.put(schoolJson);	 //schoolJson ->brach main array
							}
							
						  }//	
						} catch (Exception e) {
							e.printStackTrace();
						}
		
						}//inner1st for loop
			
					}//if cond School Wise
		System.out.println(schoolMainJson.length()+"schoolMainJson-->"+schoolMainJson);
				if (schoolMainJson.length()>0) {
					MainJsonObject.put((String)row1[2], schoolMainJson);	 
				}
				if (MainJsonObject.length()>0) {
					MainJsonArray.put(MainJsonObject);
					
				}
			
			} catch (BeansException e) {				e.printStackTrace();				} catch (Exception e) {					e.printStackTrace();				}
		}//outer for loop
		
			
		return MainJsonArray.toString();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	private void construtQueryStringForHistory(String schhols,String branches,String citys, String states, String addr, String contactPerson,
			 String mobile, String landline, String email, String active,long im_id) {

		
		try {
			List branchList=null;
			String smIDOpF = null;
			String bmIDOpF = null;
			
			String stIDOpF = null;
			String ctIDOpF = null;
			
		//	System.out.println(smIDs.length()+"666"+bmIDs.length()+"777"+cmIDs.length());
			if (schhols.replace("'", "").length()>1) {//this logic for identifying %
				smIDOpF = "in";			
			}else {
				smIDOpF = "like";			
			}
			
			if (branches.replace("'", "").length()>1) {//this logic for identifying %
				bmIDOpF = "in";			
			}else {
				bmIDOpF = "like";			
			}
			
			if (citys.replace("'", "").length()>1) {
				ctIDOpF = "in";			
			}else {
				ctIDOpF = "like";			
			}
			if (states.replace("'", "").length()>1) {
				stIDOpF = "in";			
			}else {
				stIDOpF = "like";			
			}
			final String smIDOp = smIDOpF;
			final String bmIDOp = bmIDOpF;
			final String ctIDOp = ctIDOpF;
			final String stIDOp = stIDOpF;
			String qu =	"select bm.bm_ID as bm_ID,bm.IM_ID as IM_ID,sm.Name as School_Name,bm.Branch_Name as Branch_Name,bm.Short_Name as Short_Name,bm.Address as Address,bm.City as City," +
					"bm.State_ID as State_ID,stm.State_name as State_name,bm.Contact_Person as Contact_Person,bm.Email_ID as Email_ID,bm.Mobile as Mobile,bm.Landline as Landline,bm.Active as Active  " +
					" from branch_master bm inner join school_master sm on bm.sm_id = sm.sm_id  inner join state_master stm on bm.State_id=stm.state_id"+
					" where sm.SM_ID "+smIDOp+" ("+schhols+") and bm.BM_ID "+bmIDOp+" ("+branches+")  and bm.Address like '"+addr+"'  and bm.City "+ctIDOp+" ("+citys+")    and bm.State_ID "+stIDOp+" ("+states+")" +
							"  and bm.Contact_Person like '"+contactPerson+"'  and bm.Email_ID like '"+email+"'   and bm.Mobile like '"+mobile+"'   and bm.Landline like '"+landline+"'   " +
									"and bm.Active ='"+active+"' and bm.IM_ID ="+im_id+ " order by sm.sm_id desc";
		
					//System.out.println("##-->"+qu);
					session.setAttribute("queryForBranchSearch", qu);
		} catch (Exception e) {
			e.printStackTrace();
			session.setAttribute("queryForBranchSearch", "");
			session.setAttribute("jsonForBranchSearch", "");
			e.printStackTrace();
		}	
		
			
	}
	

}
