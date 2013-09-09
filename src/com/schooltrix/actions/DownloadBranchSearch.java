package com.schooltrix.actions;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.SessionAware;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.BeansException;

import com.opensymphony.xwork2.ActionSupport;
import com.schooltrix.daos.BranchMasterDAO;
import com.schooltrix.managers.ServiceFinder;
import com.schooltrix.utils.BranchMasterSearchData;
import com.schooltrix.utils.SchoolMasterSearchData;

public class DownloadBranchSearch extends ActionSupport implements ServletRequestAware,SessionAware{

	private static final long serialVersionUID = 1L;
	HttpServletRequest request = null;
	Map session;
	private InputStream fileInputStream;
	
	private String fileName;
	
	public String downloadBranchSearch() {
		
		String schoolNames[] 	= request.getParameterValues("schoolNames");
		String branchNames[] 	= request.getParameterValues("branchNames");
		
		String shortName[]		= request.getParameterValues("shortName");
		String state[]					= request.getParameterValues("state");
		String city[]					= request.getParameterValues("city");
		String addr						= request.getParameter("addr");//null ???????????
		String contactPerson		= request.getParameter("contactPerson");
		String mobile					= request.getParameter("mobile");
		String landline				= request.getParameter("landline");
		String email					= request.getParameter("email");
		String active					= request.getParameter("active");
		System.out.println("active-->"+active);
		if (active == null) {
			active = "N";
		}else if (active.equalsIgnoreCase("on")) {
			active = "Y";
		} 
		String IM_ID 		= (String)session.get("IM_ID");
		StringBuffer bm_ids = new StringBuffer();
		StringBuffer insmIds	 	= new StringBuffer();
		StringBuffer inbmIds	 	= new StringBuffer();
		StringBuffer inbnIds	 	= new StringBuffer();
		StringBuffer incityIds 		= new StringBuffer();
		StringBuffer instateIds 		= new StringBuffer();
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		setFileName("BranchSearch-"+sdf.format(new Date())+".csv");//user define File Name......................
		
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
			} else if (inbnIds.toString().contains("%") ){
				bm_ids = inbmIds;
			} else if (inbmIds.toString().contains("%") ){
				bm_ids = inbnIds;
			}else{
				bm_ids = inbmIds;//temperarory...we need to disable any one option ....while selecting other school name or short name..				
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
			  
			System.out.println(addr+"-***-"+contactPerson+"****-"+mobile+"***-"+landline+"*****-"+email+"*Active*"+active);
		
			 if (addr.equalsIgnoreCase("")) {
				addr = "%";
			} else{
				addr = "%"+addr+"%";
			}
			 if (contactPerson.equalsIgnoreCase("")) {
				 contactPerson = "%";
			 } else{
				 contactPerson = "%"+contactPerson+"%";
			 }
				
			 if (mobile.equalsIgnoreCase("")) {
				 mobile = "%";
			 } else{
				 mobile = "%"+mobile+"%";
			 }
			 
			 if (landline.equalsIgnoreCase("")) {
				 landline = "%";
			 } else{
				 landline = "%"+landline+"%";
			 }
			 if (email.equalsIgnoreCase("")) {
				 email = "%";
			 } else{
				 email = "%"+email+"%";
			 }
			 if (active != null && active.equalsIgnoreCase("y")) {
				 active = "Y";
			 } else{
				 active = "N";
			 }
			 
		System.out.println(bm_ids+"<--Major"+insmIds+"***"+inbnIds+"****"+incityIds+"*****"+instateIds);
			  
		}catch (Exception e) {
				e.printStackTrace();
		}
		BranchMasterDAO branchMasterDAO= null;
		try {
			branchMasterDAO = (BranchMasterDAO)ServiceFinder.getContext(request).getBean("BranchMasterHibernateDao");
			List branchDataList = new ArrayList();
			branchDataList =	branchMasterDAO.getBranchesForEdit(insmIds+"",bm_ids+"",incityIds+"",instateIds+"",addr,contactPerson,mobile,landline,email,active,Long.parseLong(IM_ID));
			
			System.out.println(branchDataList.size()+"BEFiiOREin DWR");
			
		//////-----------------------------------------------------------------------------------------------	
			StringBuffer docPreapare = new StringBuffer();
			int sizeMain = branchDataList.size() ;
			 if (sizeMain <=0) {
				 docPreapare.append("No records found");
			 }
			
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

			docPreapare.append(schoolIte);
				
			System.out.println("in scgool-*"+schoolIte);//expecting 2 times
			Set<String> branchSet = new HashSet();
			
			System.out.println(schoolSet.size()+"SET schoolSet-->"+schoolSet);
			String branchIte = "";
		//	if ( branchDataList.size()>0) {//not req
				docPreapare.append("\n\n");
				docPreapare.append("Branch Short Name,Branch Name,Address,City,State,Contact Person,Email Id,Mobile,Landline,Active");			
				docPreapare.append("\n");
			//}
					
			for (Iterator iterator2 = branchDataList.iterator(); iterator2	.hasNext();) {
				try {
					Object row2[] = (Object[]) iterator2.next();
					JSONObject schoolJson= new JSONObject();
		
				if(schoolIte.equalsIgnoreCase((String)row2[2])){//extra chechking for unnessary reputation stooping
				//branch wise					
					if (branchSet.add((String)row2[3])) {
						//		0				1				2				3						4							5			6				7			8					9							10				11		12			13	
						// bm_ID,IM_ID,School_Name,Branch_Name,,Short_Name,,Address,,City,,State_ID,State_name,Contact_Person,Email_ID,Mobile,Landline,Active  						
						docPreapare.append((String)row2[4]+","+(String)row2[3]+","+row2[5].toString().replaceAll(",", " ")+","+(String)row2[6]+","+(String)row2[8]+","+(String)row2[9]+","+(String)row2[10]+","+(String)row2[11]+","+(String)row2[12]+","+(row2[13].toString().equalsIgnoreCase("Y")?"Yes":"No"));
						docPreapare.append("\n");							
						  }//	
					}
				} catch (Exception e) {
							e.printStackTrace();
				}
		
			}//inner1st for loo
			docPreapare.append("\n\n");		
		}//if cond School Wise
			
			} catch (BeansException e) {				e.printStackTrace();				} catch (Exception e) {					e.printStackTrace();				}
		
	}//outer for loop
	//	System.out.println("docPreapare****"+docPreapare);
	 fileInputStream = new ByteArrayInputStream(docPreapare.toString().getBytes());
	 return "success";			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "success";			
	}
	
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	
	public InputStream getInputStream(){  
	      try{	    	  
	             return fileInputStream;  
	      }catch(Exception e){
	    	  System.out.println(e);
	    	  return null;    
	      }  
	   }  

	@Override
	public void setServletRequest(HttpServletRequest arg0) {
		// TODO Auto-generated method stub
		request = arg0;
	}
		
		@Override
		public void setSession(Map<String, Object> session) {
			// TODO Auto-generated method stub
			this.session = session;
		}
	
}
