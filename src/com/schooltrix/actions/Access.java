package com.schooltrix.actions;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;

public class Access extends ActionSupport implements ServletRequestAware,SessionAware{

		private String p1;
		private String p2;
		private String p3;
		Map session;
		HttpServletRequest request;
		
		
		public String execute() throws Exception {
			// TODO Auto-generated method stub
			System.out.println("in acesss.java"+p1+"::"+p2+"***"+p3);
			
			if (p1.equalsIgnoreCase("SA_inst1")) {
				/*session.put("UM_ID", userMaster.getUmId()+"");
				session.put("UT_ID", userMaster.getUtId()+"");
				
				session.put("userType", userTypeString);
				
				//Special to super admin
				session.put("superadmin", "Y");						
					//actually there from db..
				session.put("fName", "Ravi");
				session.put("lName", "Kumar");
				session.put("active", "Y");*/
				
				try {
					System.out.println("in super admin ins creation--"+session.get("superadmin"));
				if(session.get("superadmin") != null){
					
					String superadmin =(String) session.get("superadmin");
					String userType = (String)session.get("userType");
					String UM_ID = (String)session.get("UM_ID");
					String UT_ID = (String)session.get("UT_ID");
					String fName = (String)session.get("fName");
					String lName = (String)session.get("lName");
					String active = (String)session.get("active");
					
					System.out.println("in SA--"+superadmin+"--userType--"+userType+"--UM_ID--"+UM_ID+"--UT_ID--"+UT_ID+"--fName--"+fName);
					if(superadmin.equalsIgnoreCase("Y") && userType.equalsIgnoreCase("SA")){
						System.out.println("in iff of SA");
						return "superInstitutionC";
						
					}
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				return "unauthorised";
				
			}else if (p1.equalsIgnoreCase("AddAdminUser")) {
				try {
					System.out.println("in super admin --AddAdminUser--"+session.get("superadmin"));
					if(session.get("superadmin") != null){
					
					String superadmin =(String) session.get("superadmin");
					String userType = (String)session.get("userType");
					String UM_ID = (String)session.get("UM_ID");
					String UT_ID = (String)session.get("UT_ID");
					String fName = (String)session.get("fName");
					String lName = (String)session.get("lName");
					String active = (String)session.get("active");
					
					System.out.println("AddAdminUser---SA--"+superadmin+"--userType--"+userType+"--UM_ID--"+UM_ID+"--UT_ID--"+UT_ID+"--fName--"+fName);
					if(superadmin.equalsIgnoreCase("Y") && userType.equalsIgnoreCase("SA")){
						System.out.println("AddAdminUser--in iff of SA");
						return "adminAddUser";
						
					}
					}
				} catch (Exception e) {
					e.printStackTrace();
				}		
				return "unauthorised";

			}else if (p1.equalsIgnoreCase("AddNonAdminUser")&&StringUtils.isNotBlank(p3)&&p3.equalsIgnoreCase("forEdit")) {
				
				boolean validAdmin = institutionAdminCheck();
				if(validAdmin){		
					System.out.println(p2+"in user EDit"+p3);
					String p4 = request.getParameter("p4");
					//this is for ravi sir..more links
					if (p2.equalsIgnoreCase("uts")) {
						session.put("studentIDForEdit",p4);
						session.put("pageTO", "uts");
						return "adminNonAddUser_uts_edit";
						
					} else if (p2.equalsIgnoreCase("ffats")) {
						session.put("staffIDForEdit",p4);
						session.put("pageTO", "ffats");
						return "adminNonAddUser_ffats_edit";
						
					} else if (p2.equalsIgnoreCase("rap")) {
						session.put("parentIDForEdit",p4);
						session.put("pageTO", "rap");
						return "adminNonAddUser_rap_edit";	
						
					} else{
						return "unauthorised";
					}
					
					}else{			
						return "unauthorised";		
					}
					
			}
			else if (p1.equalsIgnoreCase("AddNonAdminUser")) {
								
					boolean validAdmin = institutionAdminCheck();
					if(validAdmin){		
						//this is for ravi sir..more links
						if (p2.equalsIgnoreCase("uts")) {
							session.put("pageTO", "uts");
							return "adminNonAddUser_uts";						
						} else if (p2.equalsIgnoreCase("ffats")) {
							session.put("pageTO", "ffats");
							return "adminNonAddUser_ffats";						
						} else if (p2.equalsIgnoreCase("rap")) {
							session.put("pageTO", "rap");
							return "adminNonAddUser_rap";						
						} else{
							return "unauthorised";		
						}
						
					}else{			
						return "unauthorised";		
					}
					
			}else if (p1.equalsIgnoreCase("ParentStartUp")) {
				
				try{
					if(session.get("short_name") != null  && ((String)session.get("userType")).equalsIgnoreCase("PA")&& session.get("superadmin") == null ){
					
					String IM_SN = (String)session.get("IM_SN");
			
					String IM_ID = (String)session.get("IM_ID");
					String SM_ID = (String)session.get("SM_ID");
					String BM_ID = (String)session.get("BM_ID");
					
					String UM_ID = (String)session.get("UM_ID");
					String UT_ID = (String)session.get("UT_ID");
					
					String userType = (String)session.get("userType");
					String fName = (String)session.get("fName");
					String lName = (String)session.get("lName");
					String pdID = (String)session.get("pdID");
					String active = (String)session.get("active");
					
					System.out.println("AddnonAdminUser---AD--"+IM_SN+"--userType--"+userType+"--UM_ID--"+UM_ID+"--UT_ID--"+UT_ID+"--fName--"+fName);
					
					if(IM_SN.equalsIgnoreCase((String)session.get("short_name")) && IM_ID !=null && SM_ID !=null && BM_ID !=null &&  UM_ID !=null){						
						System.out.println("parentView--in iff of  parentView");
						return "parentView";						
					}				
					}
				}catch (Exception e) {
					e.printStackTrace();
				}								
				return "unauthorised";		

			}else if (p1.equalsIgnoreCase("StudentStartUp")) {
				
				try{
					if(session.get("short_name") != null  && ((String)session.get("userType")).equalsIgnoreCase("ST")&& session.get("superadmin") == null ){
					
					String IM_SN = (String)session.get("IM_SN");
			
					String IM_ID = (String)session.get("IM_ID");
					String SM_ID = (String)session.get("SM_ID");
					String BM_ID = (String)session.get("BM_ID");
					
					String UM_ID = (String)session.get("UM_ID");
					String UT_ID = (String)session.get("UT_ID");
					
					String userType = (String)session.get("userType");
					String fName = (String)session.get("fName");
					String lName = (String)session.get("lName");
					String StuID = (String)session.get("StuID");
					String active = (String)session.get("active");
					
					System.out.println("AddnonAdminUser---AD--"+IM_SN+"--userType--"+userType+"--UM_ID--"+UM_ID+"--UT_ID--"+UT_ID+"--fName--"+fName+":StuID:"+StuID);
					
					if(IM_SN.equalsIgnoreCase((String)session.get("short_name")) && IM_ID !=null && SM_ID !=null && BM_ID !=null &&  UM_ID !=null && StuID != null){						
						System.out.println("studentView--in iff of  studentView");
						return "studentView";						
					}				
					}
				}catch (Exception e) {
					e.printStackTrace();
				}								
				return "unauthorised";		

			}else if (p1.equalsIgnoreCase("StaffStartUp")) {
				
				try{//we need move common code to method like ADcheck
					if(session.get("short_name") != null  && ((String)session.get("userType")).equalsIgnoreCase("TC")&& session.get("superadmin") == null ){
					
					String IM_SN = (String)session.get("IM_SN");
			
					String IM_ID = (String)session.get("IM_ID");
					String SM_ID = (String)session.get("SM_ID");
					String BM_ID = (String)session.get("BM_ID");
					
					String UM_ID = (String)session.get("UM_ID");
					String UT_ID = (String)session.get("UT_ID");
					
					String userType = (String)session.get("userType");
					String fName = (String)session.get("fName");
					String lName = (String)session.get("lName");
					String staffID = (String)session.get("staffID");
					String active = (String)session.get("active");
					
					System.out.println("AddnonAdminUser---Staff--"+IM_SN+"--userType--"+userType+"--UM_ID--"+UM_ID+"--UT_ID--"+UT_ID+"--fName--"+fName+":staffID:"+staffID);
					
					if(IM_SN.equalsIgnoreCase((String)session.get("short_name")) && IM_ID !=null && SM_ID !=null && BM_ID !=null &&  UM_ID !=null && staffID != null){						
						System.out.println("studentView--in iff of  studentView");
						return "staffView";						
					}				
					}
				}catch (Exception e) {
					e.printStackTrace();
				}								
				return "unauthorised";		

			}else if (p1.equalsIgnoreCase("AttendanceMark")) {
				
				try{
					if(session.get("short_name") != null  && ((String)session.get("userType")).equalsIgnoreCase("TC")&& session.get("superadmin") == null ){
					
					String IM_SN = (String)session.get("IM_SN");
			
					String IM_ID = (String)session.get("IM_ID");
					String SM_ID = (String)session.get("SM_ID");
					String BM_ID = (String)session.get("BM_ID");
					
					String UM_ID = (String)session.get("UM_ID");
					String UT_ID = (String)session.get("UT_ID");
					
					String userType = (String)session.get("userType");
					String fName = (String)session.get("fName");
					String lName = (String)session.get("lName");
					String staffID = (String)session.get("staffID");
					String active = (String)session.get("active");
					
					System.out.println("AddnonAdminUser---Staff--"+IM_SN+"--userType--"+userType+"--UM_ID--"+UM_ID+"--UT_ID--"+UT_ID+"--fName--"+fName+":staffID:"+staffID);
					
					if(IM_SN.equalsIgnoreCase((String)session.get("short_name")) && IM_ID !=null && SM_ID !=null && BM_ID !=null &&  UM_ID !=null && staffID != null){						
						System.out.println("studentView--in iff of  studentView");
						return "attendanceMark";						
					}				
					}
				}catch (Exception e) {
					e.printStackTrace();
				}								
				return "unauthorised";		

			}else if (p1.equalsIgnoreCase("AttendanceView")) {
				
				try{
					if(session.get("short_name") != null  && ((String)session.get("userType")).equalsIgnoreCase("TC")&& session.get("superadmin") == null ){
					
					String IM_SN = (String)session.get("IM_SN");
			
					String IM_ID = (String)session.get("IM_ID");
					String SM_ID = (String)session.get("SM_ID");
					String BM_ID = (String)session.get("BM_ID");
					
					String UM_ID = (String)session.get("UM_ID");
					String UT_ID = (String)session.get("UT_ID");
					
					String userType = (String)session.get("userType");
					String fName = (String)session.get("fName");
					String lName = (String)session.get("lName");
					String staffID = (String)session.get("staffID");
					String active = (String)session.get("active");
					
					System.out.println("AddnonAdminUser---Staff--"+IM_SN+"--userType--"+userType+"--UM_ID--"+UM_ID+"--UT_ID--"+UT_ID+"--fName--"+fName+":staffID:"+staffID);
					
					if(IM_SN.equalsIgnoreCase((String)session.get("short_name")) && IM_ID !=null && SM_ID !=null && BM_ID !=null &&  UM_ID !=null && staffID != null){						
						System.out.println("studentView--in iff of  studentView");
						return "attendanceView";						
					}				
					}
				}catch (Exception e) {
					e.printStackTrace();
				}								
				return "unauthorised";		

			}else if (p1.equalsIgnoreCase("UserEmail")) {
				boolean validAdmin = institutionAdminCheck();
				if(validAdmin){		
					return "userEmail";						
				}
					return "unauthorised";		

			}else if (p1.equalsIgnoreCase("UserSMS")) {
				boolean validAdmin = institutionAdminCheck();
				if(validAdmin){		
					return "userSMS";						
				}
					return "unauthorised";		

			}else if (p1.equalsIgnoreCase("UploadDocuments") && p2.contains("forEdit")){
				boolean validAdmin = institutionAdminCheck();
				if(validAdmin){
					String p3=request.getParameter("p3");
					System.out.println("p3-->access--"+p3);
					session.put("uploadIDForEdit", p3);
					return "uploadDocumentsEdit";						
				} 		
					return "unauthorised";		

			}else if (p1.equalsIgnoreCase("UploadDocuments")){
				boolean validAdmin = institutionAdminCheck();
				if(validAdmin){		
					return "uploadDocuments";						
				} 		
					return "unauthorised";		

			}else if (p1.equalsIgnoreCase("EditUploadedDocuments")){
				boolean validAdmin = institutionAdminCheck();
				if(validAdmin){		
					return "editPreviousUploads";						
				} 		
					return "unauthorised";		

			}else if (p1.equalsIgnoreCase("schoolMaster") && p2.contains("forEdit")){
				boolean validAdmin = institutionAdminCheck();
				if(validAdmin){
					String p3=request.getParameter("p3");
					System.out.println("p3-->access--"+p3);
					session.put("schoolIDForEdit", p3);
					return "schoolMasterEdit";						
				} 		
					return "unauthorised";		

			}else if (p1.equalsIgnoreCase("schoolMaster")) {
				boolean validAdmin = institutionAdminCheck();
				if(validAdmin){		
					return "schoolMaster";						
				} 	
					return "unauthorised";		
			 
			}else if (p1.equalsIgnoreCase("branchMaster") && p2.contains("forEdit")){
				boolean validAdmin = institutionAdminCheck();
				if(validAdmin){
					String p3=request.getParameter("p3");
					System.out.println("p3-->access--"+p3);
					session.put("branchIDForEdit", p3);
					return "branchMasterEdit";						
				} 		
					return "unauthorised";		

			}else if (p1.equalsIgnoreCase("branchMaster")) {
				boolean validAdmin = institutionAdminCheck();
				if(validAdmin){		
					return "branchMaster";						
				} 
					return "unauthorised";		
			 

			}else if (p1.equalsIgnoreCase("classMaster")) {//xl upload
				boolean validAdmin = institutionAdminCheck();
				if(validAdmin){		
					return "classMaster";						
				} 	
					return "unauthorised";		

			}else if (p1.equalsIgnoreCase("classMasterUI")) {//xl upload
				boolean validAdmin = institutionAdminCheck();
				if(validAdmin){		
					return "classMasterUI";						
				} 	
					return "unauthorised";		

			}else if (p1.equalsIgnoreCase("studentMaster")) {//xl upload
				boolean validAdmin = institutionAdminCheck();
				if(validAdmin){		
					return "studentMaster";						
				} 
					return "unauthorised";		
				
			}else if (p1.equalsIgnoreCase("sectionMaster")) {//xl upload
				boolean validAdmin = institutionAdminCheck();
				if(validAdmin){		
					return "sectionMaster";						
				}		
					return "unauthorised";		

			}else if (p1.equalsIgnoreCase("sectionMasterUI")) {//xl upload
				boolean validAdmin = institutionAdminCheck();
				if(validAdmin){		
					return "sectionMasterUI";						
				}		
					return "unauthorised";		

			}else if (p1.equalsIgnoreCase("sectionMaster")) {//xl upload
				boolean validAdmin = institutionAdminCheck();
				if(validAdmin){		
					return "sectionMaster";						
				}		
					return "unauthorised";		

			}else if (p1.equalsIgnoreCase("comeSoon")) {//xl upload
				boolean validAdmin = institutionAdminCheck();
				if(validAdmin){		
					return "comeSOON";						
				}		
					return "unauthorised";		

			}else if (p1.equalsIgnoreCase("EditSchools")){
				boolean validAdmin = institutionAdminCheck();
				if(validAdmin){		
					return "editPreviousSchools";						
				} 		
					return "unauthorised";		
					
			}else if (p1.equalsIgnoreCase("EditBranches")){
				boolean validAdmin = institutionAdminCheck();
				if(validAdmin){		
					return "editPreviousBranches";						
				} 		
					return "unauthorised";		
					
			}else if (p1.equalsIgnoreCase("EditStudent")){
				boolean validAdmin = institutionAdminCheck();
				if(validAdmin){		
					return "editStudents";						
				} 		
					return "unauthorised";		
					
			}else if (p1.equalsIgnoreCase("EditParent")){
				boolean validAdmin = institutionAdminCheck();
				if(validAdmin){		
					return "editParents";						
				} 		
					return "unauthorised";		
					
			}else if (p1.equalsIgnoreCase("EditStaff")){
				boolean validAdmin = institutionAdminCheck();
				if(validAdmin){		
					return "editStaffs";						
				} 		
				return "unauthorised";		
			
			}
			else if (p1.equalsIgnoreCase("AppStore")){
				boolean validAdmin = institutionAdminCheck();
				if(validAdmin){
					return "appStore";						
				} 		
					return "unauthorised";		
			}
			
			else{
				System.out.println("not correct");
				 return "unauthorised";
			}

			//return "welcome";			
			//return "Schoolmaster";			
		}
		
		private boolean institutionAdminCheck() {

			
			try{
				if(session.get("short_name") != null  && ((String)session.get("userType")).equalsIgnoreCase("AD")&& session.get("superadmin") == null ){
				
				String IM_SN = (String)session.get("IM_SN");
		
				String IM_ID = (String)session.get("IM_ID");
				String SM_ID = (String)session.get("SM_ID");
				String BM_ID = (String)session.get("BM_ID");
				
				String UM_ID = (String)session.get("UM_ID");
				String UT_ID = (String)session.get("UT_ID");
				
				String userType = (String)session.get("userType");
				String fName = (String)session.get("fName");
				String lName = (String)session.get("lName");
				String pdID = (String)session.get("pdID");
				String active = (String)session.get("active");
				
				System.out.println("AddnonAdminUser---AD--"+IM_SN+"--userType--"+userType+"--UM_ID--"+UM_ID+"--UT_ID--"+UT_ID+"--fName--"+fName);
				
				if(IM_SN.equalsIgnoreCase((String)session.get("short_name")) && IM_ID !=null && SM_ID !=null && BM_ID !=null &&  UM_ID !=null){						
					System.out.println("AddAdminUser--in iff of  AddNonAdminUser");
					return true;						
				}				
				}
			}catch (Exception e) {
				e.printStackTrace();
			}								
			return false;		
			
		
		}
		

		
		
		
		
		
		public String getP1() {
			return p1;
		}
		public void setP1(String p1) {
			this.p1 = p1;
		}
		public String getP2() {
			return p2;
		}
		public void setP2(String p2) {
			this.p2 = p2;
		}
		public String getP3() {
			return p3;
		}
		public void setP3(String p3) {
			this.p3 = p3;
		}
		
		@Override
		public void setServletRequest(HttpServletRequest request) {
			// TODO Auto-generated method stub
			this.request = request;
		}
		@Override
		public void setSession(Map<String, Object> session) {
			// TODO Auto-generated method stub
			this.session = session;
		}

	
}
