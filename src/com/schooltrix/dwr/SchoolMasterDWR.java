package com.schooltrix.dwr;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.directwebremoting.WebContextFactory;
import org.hibernate.Session;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.BeansException;

import com.schooltrix.daos.BranchMasterDAO;
import com.schooltrix.daos.ClassMasterDAO;
import com.schooltrix.daos.InstitutionMasterDAO;
import com.schooltrix.daos.SchoolMasterDAO;
import com.schooltrix.daos.StateMasterDAO;
import com.schooltrix.daos.UploadDocDAO;
import com.schooltrix.hibernate.DocumentSearchHistory;
import com.schooltrix.hibernate.InstitutionMaster;
import com.schooltrix.hibernate.SchoolMaster;
import com.schooltrix.hibernate.SchoolSearchHistory;
import com.schooltrix.hibernate.StateMaster;
import com.schooltrix.hibernate.SubjectMaster;
import com.schooltrix.managers.ServiceFinder;
import com.schooltrix.utils.SchoolMasterSearchData;
import com.schooltrix.utils.UploadDocUtil;

public class SchoolMasterDWR {

	HttpServletResponse response = WebContextFactory.get().getHttpServletResponse();
	HttpServletRequest request = WebContextFactory.get().getHttpServletRequest();
	HttpSession session = WebContextFactory.get().getSession();
	
    public String saveSchoolMaster(String  sName,  String ssName,   String  sAddress,  String  sCity,  Long   sState,  String 	sCountry,  String sContPerson, 
    								
    								String sEmail,String  sMobile,  String sLandline,   String  sIsActive) {
    	
    	SchoolMasterDAO sMasterDao = null;
    	System.out.println("i am here SchoolMasterDWR");
    	
    	try {
    		String im_id =(String) session.getAttribute("IM_ID");
    		//min validation..
    		if (im_id != null && im_id != "null" && ((String) session.getAttribute("userType")).equalsIgnoreCase("AD")) {
				
			
    		sMasterDao = (SchoolMasterDAO)ServiceFinder.getContext(request).getBean("SchoolMasterHibernateDao"); 		
				SchoolMaster sm = new SchoolMaster();
				sIsActive=sIsActive.equalsIgnoreCase("Y")?"Y":"N";
				sm.setActive(sIsActive);
				sm.setAddress(sAddress);
				sm.setCity(sCity);
				sm.setContactPerson(sContPerson);
				sm.setEmailId(sEmail);
				sm.setLandline(sLandline);
				sm.setMobile(sMobile);
				sm.setName(sName);
				sm.setImId(Long.parseLong(im_id));
				sm.setStateId(sState);//modify
				sm.setSchoolName(ssName);
			//	sm.setCountry(sCountry);
				sMasterDao.save(sm);
			return "saved";
    		}
    		
		} catch (BeansException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "error";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return "error";
		
    	
	}
    
    //using in uploadDoc,branchadd...
    public List getSchoolMasterList() {
    	
		SchoolMasterDAO schoolMasterdao =null;
		List<Object[]> schoolList= new ArrayList<Object[]>();
		List instList= new ArrayList();
		try {
			String IM_ID = (String)session.getAttribute("IM_ID");
			schoolMasterdao = (SchoolMasterDAO)ServiceFinder.getContext(request).getBean("SchoolMasterHibernateDao"); 		
			instList = schoolMasterdao.getSchoolList("imId",Long.parseLong(IM_ID));			
			
			System.out.println(instList.size()+"***size in getSchoolMasterList DWR --IM_ID  "+IM_ID);

			for (int i = 0; i < instList.size(); i++) {
				String[] oioio = new String[2];
				SchoolMaster ioio = (SchoolMaster)instList.get(i);
				oioio[0] = ioio.getSmId()+"";
				oioio[1] = ioio.getName();
				schoolList.add(oioio);
			}
			
				return schoolList;
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

    public String getSchoolMasterDetailedListJson() {
    	
		SchoolMasterDAO schoolMasterdao =null;
		List instList= new ArrayList();
		try {
			String IM_ID = (String)session.getAttribute("IM_ID");
			schoolMasterdao = (SchoolMasterDAO)ServiceFinder.getContext(request).getBean("SchoolMasterHibernateDao"); 		
			instList = schoolMasterdao.getSchoolDetailedList("imId",Long.parseLong(IM_ID));			
			
			System.out.println(instList.size()+"***size in getSchoolMasterList DWR --IM_ID  "+IM_ID);
			JSONObject jObject 					= new JSONObject();
			JSONObject schoolNameJson 	= new JSONObject();
			JSONObject shortNameJson 	= new JSONObject();
			JSONObject cityJson 					= new JSONObject();
			JSONObject stateJson 				= new JSONObject();
			
			Set set = new HashSet();
			
			for (int i = 0; i < instList.size(); i++) {
				SchoolMaster sMaster = (SchoolMaster)instList.get(i);				
				schoolNameJson.put(sMaster.getSmId()+"", sMaster.getName());
				shortNameJson.put(sMaster.getSmId()+"", sMaster.getSchoolName());
				set.add(sMaster.getCity());
			}
			
			for (Iterator iterator = set.iterator(); iterator.hasNext();) {
				String object = (String) iterator.next();
				cityJson.put(object, object);				
			}
			
			try {
				List stateList = new ArrayList();
				StateMasterDAO stateMasterDAO = (StateMasterDAO)ServiceFinder.getContext(request).getBean("StateMasterHibernateDao");
				stateList = stateMasterDAO.findByPropertyList("active","Y");
				for (int i = 0; i < stateList.size(); i++) {
					StateMaster stateMaster = (StateMaster)stateList.get(i);
					stateJson.put(stateMaster.getStateId()+"",stateMaster.getStateName());					
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			jObject.put("schools", schoolNameJson);
			jObject.put("shortNames", shortNameJson);
			jObject.put("citys", cityJson);
			jObject.put("states", stateJson);			
			//System.out.println("School****"+jObject);
			return jObject.toString();
			
		} catch (BeansException e) {
			e.printStackTrace();
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
 }
/*    getSchoolMasterDisplay(
			String schoolNames,	String 	shortName, String state , String city, String addr, String contactPerson , String mobile,	String 	landline ,String 	email,String active,	function(data){*/
				
				
	public List getSchoolMasterDisplay(String[] schoolNames,	String[] 	shortName, String[] state , String[] city, String addr, 
																		String contactPerson , String mobile,	String 	landline ,String 	email,String active) {
		
		String UM_ID	 = (String)session.getAttribute("UM_ID");
		String pdID 		= (String)session.getAttribute("pdID");		
		String IM_ID 		= (String)session.getAttribute("IM_ID");
		SchoolMasterSearchData  data = new SchoolMasterSearchData();
	try{
			return data.getSchoolSearchData(schoolNames,shortName, state , city,  addr, contactPerson ,  mobile,landline ,	email, active,IM_ID,1,request,session);
	
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	public String saveSchoolSearch(String querySearchHis, String searchDesc , String jsonSearchHis) {
		
		if (session.getAttribute("queryForSchoolSearch") != null && session.getAttribute("queryForSchoolSearch") != "null") {
			querySearchHis = (String)session.getAttribute("queryForSchoolSearch");
			jsonSearchHis = (String)session.getAttribute("jsonForSchoolSearch");
			session.removeAttribute("queryForSchoolSearch");
			session.removeAttribute("jsonForSchoolSearch");
		}else{
			return "nosearch";
		}
		
		if(isDescTherecall(searchDesc).equalsIgnoreCase("yes")){
			return "descthere";
		}
		
		
		//System.out.println("jsonSearchHis*****"+jsonSearchHis);
		SchoolMasterDAO schoolMasterDAO = null;		
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		schoolMasterDAO = (SchoolMasterDAO)ServiceFinder.getContext(request).getBean("SchoolMasterHibernateDao");
		String UM_ID	 = (String)session.getAttribute("UM_ID");
		String IM_ID 		= (String)session.getAttribute("IM_ID");
		
		SchoolSearchHistory searchHistory = new SchoolSearchHistory();
		searchHistory.setImId(IM_ID);
		searchHistory.setInsertedDate(java.sql.Timestamp.valueOf(sdf.format(new Date())));
		searchHistory.setQueryString(querySearchHis);
		searchHistory.setUserId(UM_ID);
		searchHistory.setSearchDescription(searchDesc);
		searchHistory.setSearchJson(jsonSearchHis);
		
		String result =null;
		try {
			result = schoolMasterDAO.saveSchoolSearchHistory(searchHistory);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("save history result:"+result);
		return result;
		
	}
	
	public String isDescTherecall(String searchDesc) {
		
		SchoolMasterDAO schoolMasterDAO = null;		
		schoolMasterDAO = (SchoolMasterDAO)ServiceFinder.getContext(request).getBean("SchoolMasterHibernateDao");
		String UM_ID	 = (String)session.getAttribute("UM_ID");
		String IM_ID 		= (String)session.getAttribute("IM_ID");
		String result =null;
		try {
			result = schoolMasterDAO.isDescThereDB(IM_ID,UM_ID,searchDesc);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("isDescTherecall result:"+result);
		return result;
	}
	
	public String searchHistoryLoadCall() {
		
		SchoolMasterDAO schoolMasterDAO = null;			
		schoolMasterDAO = (SchoolMasterDAO)ServiceFinder.getContext(request).getBean("SchoolMasterHibernateDao");
		String UM_ID	 = (String)session.getAttribute("UM_ID");
		String IM_ID 		= (String)session.getAttribute("IM_ID");
		List result =new ArrayList();
		try {
			result = schoolMasterDAO.searchHistoryLoadDB(IM_ID,UM_ID);
			JSONArray jsonObjectMain = new JSONArray();
			if (result != null) {
				for (int i = 0; i < result.size(); i++) {
					JSONObject jsonObject = new JSONObject();
					SchoolSearchHistory value = (SchoolSearchHistory)result.get(i);
					jsonObject.put("id", value.getSshId());
					jsonObject.put("queryString", value.getQueryString());
					jsonObject.put("jsonString", value.getSearchJson());
					jsonObject.put("descS", value.getSearchDescription());
					jsonObjectMain.put(jsonObject);
				}
				//System.out.println("innnnnn->"+jsonObjectMain);
				return jsonObjectMain.toString();
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public String  deleteSchoolSearchHistoryDoc(String searchHisID) {
		SchoolMasterDAO schoolMasterDAO = null;			
		boolean result = false;
		System.out.println("SearchID**"+searchHisID);
		if (searchHisID != null && !searchHisID.equalsIgnoreCase("-1")) {
			try {
				Long id = Long.parseLong(searchHisID);
			
				schoolMasterDAO = (SchoolMasterDAO)ServiceFinder.getContext(request).getBean("SchoolMasterHibernateDao");
				result = schoolMasterDAO.deleteSchoolSerchHistoryDocById(id);
				System.out.println("result**"+result);
				if (result == true) {
					return "success";
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}		
		return null;
	}

	/**
	 * Same like getUploadedDocForEditNew()
	 * the difference is here we have direct query
	 * */
	public List searchHistoryLoadByIDDisplay(String searchHisOnloadJson) {
		
		SchoolMasterDAO	schoolMasterDAO = null;
			try {
				schoolMasterDAO = (SchoolMasterDAO)ServiceFinder.getContext(request).getBean("SchoolMasterHibernateDao");
				List assignDataList = new ArrayList();
				assignDataList =	schoolMasterDAO.searchSchoolHistoryLoadByIDDisplay(searchHisOnloadJson);
			return assignDataList;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}
	
	 public String editSchoolMaster(String schoolID, String  sName,  String ssName,   String  sAddress,  String  sCity,  Long   sState,  String 	sCountry,  String sContPerson,				
				String sEmail,String  sMobile,  String sLandline,   String  sIsActive) {
			
			SchoolMasterDAO sMasterDao = null;
			System.out.println("i am here for Edit  SchoolMasterDWR");
			
			try {
					String im_id =(String) session.getAttribute("IM_ID");
					//min validation..
					if (im_id != null && im_id != "null" && ((String) session.getAttribute("userType")).equalsIgnoreCase("AD")) {				
					
							sMasterDao = (SchoolMasterDAO)ServiceFinder.getContext(request).getBean("SchoolMasterHibernateDao"); 		
							SchoolMaster sm = new SchoolMaster();
							//sIsActive=sIsActive.equalsIgnoreCase("Y")?"Y":"N";
							sm.setActive(sIsActive);
							sm.setAddress(sAddress);
							sm.setCity(sCity);
							sm.setContactPerson(sContPerson);
							sm.setEmailId(sEmail);
							sm.setLandline(sLandline);
							sm.setMobile(sMobile);
							sm.setName(sName);
							sm.setImId(Long.parseLong(im_id));
							sm.setStateId(sState);//modify
							sm.setSchoolName(ssName);
							sm.setSmId(Long.parseLong(schoolID));
							//	sm.setCountry(sCountry);
						String res = sMasterDao.multiTransForSchoolEdit(sm);
						if (res.equalsIgnoreCase("success")) 
							return "saved";
					}
			
			} catch (BeansException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "error";
			} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			}
			return "error";
	 }

		public String disableEnableSchoolMaster(String SD_ID,String isActive)  {
			
			SchoolMasterDAO schoolMasterDAO= null;
			try{
			schoolMasterDAO = (SchoolMasterDAO)ServiceFinder.getContext(request).getBean("SchoolMasterHibernateDao");
//String sd_id,String action,String isActive
			String action = isActive.equalsIgnoreCase("Y")?"enable":"diable";
			String result = schoolMasterDAO.multiTrancationSchoolDisable(SD_ID,action,isActive);//?---wt r req to disable .
			/**
			 * School disable ..................School_Master
			 * 1)Branches under school-------Branch_Master
			 * 2)a)classes under branch------------Class_Master
			 * 2)b)staff under branch-------------Staff_Details
			 * 
			 * 3)section under class----------Section_Master
			 * 4)students under section--------Student_Details
			 * 
			 	*Class_Branch_Map
				*Section_Class_Map
				*User_Master
				*Staff_Details
				*Parent_Details
				*
				*Student_Section_Map
			 * 
			 * options..2
			 * 1)all modifications manually done
			 * 2)maintain  a table for disabled schools..and every where use this table for validation (inner join school id)
			 * 
			 * */
			System.out.println("delete result:"+result);
			return result;
			
			}catch (Exception e) {
				// TODO: handle exception
			}
			return null;
		}
	 
	 
}
