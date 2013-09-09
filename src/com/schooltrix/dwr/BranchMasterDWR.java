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
import org.hibernate.type.StringType;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.BeansException;

import com.schooltrix.daos.BranchMasterDAO;
import com.schooltrix.daos.CountryMasterDAO;
import com.schooltrix.daos.SchoolMasterDAO;
import com.schooltrix.daos.StateMasterDAO;
import com.schooltrix.hibernate.BranchMaster;
import com.schooltrix.hibernate.BranchSearchHistory;
import com.schooltrix.hibernate.CountryMaster;
import com.schooltrix.hibernate.SchoolMaster;
import com.schooltrix.hibernate.SchoolSearchHistory;
import com.schooltrix.hibernate.StateMaster;
import com.schooltrix.managers.ServiceFinder;
import com.schooltrix.utils.BranchMasterSearchData;

public class BranchMasterDWR {

	HttpServletResponse response = WebContextFactory.get().getHttpServletResponse();
	HttpServletRequest request = WebContextFactory.get().getHttpServletRequest();
	HttpSession session = WebContextFactory.get().getSession();
	
    public String saveBranchMaster(String schoolID,String  sname,  String shortName,   String  address,  String  city,  String   country,  String 	br_state,  String br_cperson, 
			
			String bemail,String  br_mobile,  String br_landline,   String  isactive) {
    	
    	BranchMasterDAO bMasterDao = null;
    	
    	try {
    		bMasterDao = (BranchMasterDAO)ServiceFinder.getContext(request).getBean("BranchMasterHibernateDao"); 		
    		BranchMaster br = new BranchMaster();
    		System.out.println(isactive+"--isactive");
				isactive=isactive.equalsIgnoreCase("true")?"Y":"N";
				br.setActive(isactive);
				br.setLandline(br_landline);
				br.setMobile(br_mobile);
				br.setStateId(Long.parseLong(br_state));
				br.setImId((String)session.getAttribute("IM_ID"));
				br.setSmId(Long.parseLong(schoolID));
				br.setBranchName(sname);
				br.setShortName(shortName);
				br.setAddress(address);
				br.setCity(city);
				br.setStateId(new Long(br_state));
				br.setContactPerson(br_cperson);
				br.setEmailId(bemail);
			
				bMasterDao.save(br);
			return "saved";
		} catch (BeansException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "error";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "error";
		}
		
    	
	}
    
    
	 //bhanu
	 public String copyfromSchool(String  school_id) {
	    	
	    	SchoolMasterDAO scMasterDao = null;
	    	SchoolMaster schoolDetails = null;
	    	String country = null;
	    	long state = 0;
	    	try {
	    		scMasterDao = (SchoolMasterDAO)ServiceFinder.getContext(request).getBean("SchoolMasterHibernateDao"); 		
	    		 schoolDetails = (SchoolMaster)scMasterDao.findById(Long.parseLong(school_id));
	    		
	    		 StateMasterDAO sStateMasterDAO = null;
	    		if (schoolDetails.getStateId() >0) {
	    			state = schoolDetails.getStateId();
	    			try {
					/*	sStateMasterDAO = (StateMasterDAO)ServiceFinder.getContext(request).getBean("StateMasterHibernateDao"); 		
						StateMaster stateMaster = sStateMasterDAO.findById((schoolDetails.getStateId()));
							state = stateMaster.getStateId();*/
	    		
					CountryMasterDAO countryMasterDAO = (CountryMasterDAO)ServiceFinder.getContext(request).getBean("CountryMasterHibernateDao"); 
	    			CountryMaster countryMaster = countryMasterDAO.findById(state);
	    					country = countryMaster.getCountryName();
	    			
	    			} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
	    		System.out.println(schoolDetails.getAddress()+"~"+schoolDetails.getCity()+"~"+(country == null ? "India" : country)+"~"+ state+"~"+schoolDetails.getContactPerson()+"~"+schoolDetails.getEmailId()+"~"+schoolDetails.getMobile()+"~"+schoolDetails.getLandline());
	    		return schoolDetails.getAddress()+"~"+schoolDetails.getCity()+"~"+(country == null ? "India" : country)+"~"+ state+"~"+schoolDetails.getContactPerson()+"~"+schoolDetails.getEmailId()+"~"+schoolDetails.getMobile()+"~"+schoolDetails.getLandline();
			} catch (BeansException e) {
				// TODO Auto-generated catch block
//				e.printStackTrace();
				System.out.println("in exceptionnn2");
				return "error";
			} catch (Exception e) {
				// TODO Auto-generated catch block
				System.out.println("in exceptionnn1");
				//e.printStackTrace();
				return "error";
			}
		}
	 
	    //bhanu
	 public List getBranchMasterList(String schoolID) {
		 
			BranchMasterDAO branchMasterDao =null;
			List instList= new ArrayList();
			List branchList = new ArrayList();
			try {
				String im_id =(String)session.getAttribute("IM_ID");
				Long sm_id = Long.parseLong(schoolID);
				branchMasterDao = (BranchMasterDAO)ServiceFinder.getContext(request).getBean("BranchMasterHibernateDao"); 		
				if (sm_id == 0) {
					instList = 	branchMasterDao.findByPropertyList("imId",im_id);
				} else if (sm_id > 0){
					instList = 	branchMasterDao.getBranchList("imId",im_id,"smId",sm_id);
				}
					System.out.println("in dwr ut--"+instList.size());
					for (int i = 0; i < instList.size(); i++) {
						String[] oioio = new String[2];
						BranchMaster ioio = (BranchMaster)instList.get(i);
						System.out.println("--90---"+ioio.getBranchName());
						oioio[0] = ioio.getBmId()+"";
						oioio[1] = ioio.getBranchName();
						branchList.add(oioio);
					}
				return branchList;
			} catch (BeansException e) {
				e.printStackTrace();
				return null;
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}
	}

	    //bhanu
	public List getMultiBranchMasterList(String[] schoolID) {
			 
				BranchMasterDAO branchMasterDao =null;
				List instList= new ArrayList();
				List branchList = new ArrayList();
				try {
					StringBuffer inString =new StringBuffer();
					//in('2','3')
					
					for (int i = 0; i < schoolID.length; i++) {
						if(schoolID[i].equalsIgnoreCase("0")){
							inString = new StringBuffer("0");
							break;
						}						
						inString.append(schoolID[i]);
						if(i<schoolID.length-1)
						inString.append(",");
						
					}
					//System.out.println("inString**********"+inString);
					
					String inQuery = inString+"";
		
					String im_id =(String)session.getAttribute("IM_ID");
					
					Long sm_id = Long.parseLong(schoolID[0]);
					
					branchMasterDao = (BranchMasterDAO)ServiceFinder.getContext(request).getBean("BranchMasterHibernateDao"); 		

					if (inQuery.equalsIgnoreCase("0")) {
						instList = 	branchMasterDao.findByPropertyList("imId",im_id);						
						for (int i = 0; i < instList.size(); i++) {
							String[] oioio = new String[2];
							BranchMaster ioio = (BranchMaster)instList.get(i);
							//System.out.println("--90---"+ioio.getBranchName());
							oioio[0] = ioio.getBmId()+"";
							oioio[1] = ioio.getBranchName();
						//	System.out.println(oioio[0]+"*************"+oioio[1]);
							branchList.add(oioio);
						}
					} else{
						instList = 	branchMasterDao.getMultiBranchList(im_id,inQuery);
						for (int i = 0; i < instList.size(); i++) {
							String[] oioio = new String[2];
							//System.out.println(instList.size()+"^^^^^^^^^^^^^");
						    Object[] uu = (Object[])instList.get(i) ;
						    oioio[0] =(String) uu[0];
							oioio[1] = (String)uu[1];
							//System.out.println(instList.size()+"@@"+oioio[0]+"**"+oioio[1]);
							branchList.add(oioio);							
						}
					}
							//System.out.println("in getInstitutionMasterList"+im_id);
							//	System.out.println("in dwr ut--"+instList.size());
		
						return branchList;
			
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
	
	public String getMultiBranchMasterListDetailedJSON(String[] schoolID) {
		 
			BranchMasterDAO branchMasterDao =null;
			List instList= new ArrayList();
			List branchList = new ArrayList();
			try {
				StringBuffer inString =new StringBuffer();
				//in('2','3')
				if (schoolID != null) {
					
					for (int i = 0; i < schoolID.length; i++) {
						if(schoolID[i].equalsIgnoreCase("0")){
							inString = new StringBuffer("0");
							break;
						}						
						inString.append(schoolID[i]);
						if(i<schoolID.length-1)
						inString.append(",");
						
					}
				//System.out.println("inString**********"+inString);
				}else{
					inString.append("0");
				}
				String inQuery = inString+"";
	
				String im_id =(String)session.getAttribute("IM_ID");
				
				
				branchMasterDao = (BranchMasterDAO)ServiceFinder.getContext(request).getBean("BranchMasterHibernateDao"); 		
	
				JSONObject jObject 					= new JSONObject();
				JSONObject branchNameJson 	= new JSONObject();
				JSONObject branchShortNameJson 	= new JSONObject();
				JSONObject cityJson 					= new JSONObject();
				JSONObject stateJson 				= new JSONObject();
				
				if (inQuery.equalsIgnoreCase("0")) {
					instList = 	branchMasterDao.findByPropertyList("imId",im_id);						
					
					Set set = new HashSet();
	
					for (int i = 0; i < instList.size(); i++) {
						String[] oioio = new String[2];
						BranchMaster bMaster = (BranchMaster)instList.get(i);
						//-----------------------------------------
						
						branchNameJson.put(bMaster.getBmId()+"", bMaster.getBranchName());
						branchShortNameJson.put(bMaster.getBmId()+"", bMaster.getShortName());
							set.add(bMaster.getCity());
						}
						
						for (Iterator iterator = set.iterator(); iterator.hasNext();) {
							String object = (String) iterator.next();
							cityJson.put(object, object);				
						}
						
						
				} else{
				//	BM_ID -"Branch_Name",r("Short_Name", "City",("State_ID"
					instList = 	branchMasterDao.getMultiBranchList(im_id,inQuery);
						Set set = new HashSet();
	
						for (int i = 0; i < instList.size(); i++) {
							String[] oioio = new String[2];
							   Object[] uu = (Object[])instList.get(i) ;
							//-----------------------------------------
							
							branchNameJson.put((String) uu[0], (String) uu[1]);
							branchShortNameJson.put((String) uu[0], (String) uu[2]);
								set.add((String) uu[3]);
							}
							
							for (Iterator iterator = set.iterator(); iterator.hasNext();) {
								String object = (String) iterator.next();
								cityJson.put(object, object);				
							}
				}
				
				//common to above condi
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
				
				jObject.put("branches", branchNameJson);
				jObject.put("branchShortNames", branchShortNameJson);
				jObject.put("citys", cityJson);
				jObject.put("states", stateJson);			
				//System.out.println("School****"+jObject);
				return jObject.toString();
				
		
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
	
	public String getBranchMasterDisplay(String[] schoolNames,String[] 	branchNames,	String[] 	shortName, String[] state , String[] city, String addr, 
			String contactPerson , String mobile,	String 	landline ,String 	email,String active) {
			
			String UM_ID	 = (String)session.getAttribute("UM_ID");
			String pdID 		= (String)session.getAttribute("pdID");		
			String IM_ID 		= (String)session.getAttribute("IM_ID");
			BranchMasterSearchData  data = new BranchMasterSearchData();
			try{
			return data.getBranchSearchData(schoolNames , branchNames,shortName, state , city,  addr, contactPerson ,  mobile,landline ,	email, active,IM_ID,1,request,session);
			
			} catch (Exception e) {
			e.printStackTrace();
			}
			
			return null;
			}
	
	public String saveBranchSearch(String querySearchHis, String searchDesc , String jsonSearchHis) {
		
		if (session.getAttribute("queryForBranchSearch") != null && session.getAttribute("queryForBranchSearch") != "null") {
			querySearchHis = (String)session.getAttribute("queryForBranchSearch");
			jsonSearchHis = (String)session.getAttribute("jsonForBranchSearch");
			session.removeAttribute("queryForBranchSearch");
			session.removeAttribute("jsonForBranchSearch");
		}else{
			return "nosearch";
		}
		
		if(isDescTherecall(searchDesc).equalsIgnoreCase("yes")){
			return "descthere";
		}
		
		
		//System.out.println("jsonSearchHis*****"+jsonSearchHis);
		BranchMasterDAO branchMasterDAO = null;		
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		branchMasterDAO = (BranchMasterDAO)ServiceFinder.getContext(request).getBean("BranchMasterHibernateDao");
		String UM_ID	 = (String)session.getAttribute("UM_ID");
		String IM_ID 		= (String)session.getAttribute("IM_ID");
		
		BranchSearchHistory searchHistory = new BranchSearchHistory();
		searchHistory.setImId(IM_ID);
		searchHistory.setInsertedDate(java.sql.Timestamp.valueOf(sdf.format(new Date())));
		searchHistory.setQueryString(querySearchHis);
		searchHistory.setUserId(UM_ID);
		searchHistory.setSearchDescription(searchDesc);
		searchHistory.setSearchJson(jsonSearchHis);
		
		String result =null;
		try {
			result = branchMasterDAO.saveBranchSearchHistory(searchHistory);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("save history result:"+result);
		return result;
		
	}
	
	public String isDescTherecall(String searchDesc) {
		
		BranchMasterDAO branchMasterDAO	= null;		
		branchMasterDAO = (BranchMasterDAO)ServiceFinder.getContext(request).getBean("BranchMasterHibernateDao");
		String UM_ID	 = (String)session.getAttribute("UM_ID");
		String IM_ID 		= (String)session.getAttribute("IM_ID");
		String result =null;
		try {
			result = branchMasterDAO.isDescThereDB(IM_ID,UM_ID,searchDesc);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("isDescTherecall result:"+result);
		return result;
	}

	public String searchHistoryLoadCall() {
		
		BranchMasterDAO branchMasterDAO = null;			
		branchMasterDAO = (BranchMasterDAO)ServiceFinder.getContext(request).getBean("BranchMasterHibernateDao");
		String UM_ID	 = (String)session.getAttribute("UM_ID");
		String IM_ID 		= (String)session.getAttribute("IM_ID");
		List result =new ArrayList();
		try {
			result = branchMasterDAO.searchHistoryLoadDB(IM_ID,UM_ID);
			JSONArray jsonObjectMain = new JSONArray();
			if (result != null) {
				for (int i = 0; i < result.size(); i++) {
					JSONObject jsonObject = new JSONObject();
					BranchSearchHistory value = (BranchSearchHistory)result.get(i);
					jsonObject.put("id", value.getBshId());
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
	
	public String  deleteBranchSearchHistoryDoc(String searchHisID) {
		BranchMasterDAO branchMasterDAO = null;			
		boolean result = false;
		System.out.println("SearchID**"+searchHisID);
		if (searchHisID != null && !searchHisID.equalsIgnoreCase("-1")) {
			try {
				Long id = Long.parseLong(searchHisID);
			
				branchMasterDAO = (BranchMasterDAO)ServiceFinder.getContext(request).getBean("BranchMasterHibernateDao");
				result = branchMasterDAO.deleteBranchSerchHistoryDocById(id);
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
	public String searchHistoryLoadByIDDisplay(String searchHisQuery) {
		
		BranchMasterDAO	branchMasterDAO = null;
			try {
				branchMasterDAO = (BranchMasterDAO)ServiceFinder.getContext(request).getBean("BranchMasterHibernateDao");
				List branchDataList = new ArrayList();
				branchDataList =	branchMasterDAO.searchBranchHistoryLoadByIDDisplay(searchHisQuery);
				String schoolIte = "";
				JSONArray MainJsonArray		= new JSONArray();		
				Set<String> schoolSet = new HashSet();
						
		for (Iterator iterator1 = branchDataList.iterator(); iterator1	.hasNext();) {
			JSONObject MainJsonObject = new JSONObject();		
			try {
			Object row1[] = (Object[]) iterator1.next();
			//school wise
			JSONArray schoolMainJson= new JSONArray();
			System.out.println(schoolIte+"**schoolIte***"+row1[2]);
			
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
					if(schoolIte.equalsIgnoreCase((String)row2[2])){//extra chechking for unnessary reputation stooping
						if (branchSet.add((String)row2[3])) {
							
							branchIte = (String)row2[3];
							JSONArray brachJsonMain= new JSONArray();
							
							System.out.println("in branch-*"+branchIte);
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
								 
							//	System.out.println("brach main array-->"+schoolJson);//33time
								if (schoolJson.length()>0) {
									schoolMainJson.put(schoolJson);	 //schoolJson ->brach main array
								}
								
							  }//	
							} catch (Exception e) {
								e.printStackTrace();
							}
			
							}//inner1st for loop
				
						}//if cond School Wise
			//System.out.println(schoolMainJson.length()+"schoolMainJson-->"+schoolMainJson);
					if (schoolMainJson.length()>0) {
						MainJsonObject.put((String)row1[2], schoolMainJson);	 
					}
					if (MainJsonObject.length()>0) {
						MainJsonArray.put(MainJsonObject);						
					}
				} catch (BeansException e) {				e.printStackTrace();				} catch (Exception e) {					e.printStackTrace();				}
			}//outer for loop
			
		return MainJsonArray.toString();
				//
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}
	
	 public String editBranchMaster(String branchID ,String schoolID,String  sname,  String shortName,   String  address,  String  city,  String   country,  String 	br_state,  String br_cperson, 
				
				String bemail,String  br_mobile,  String br_landline,   String  isactive) {
	    	
	    	BranchMasterDAO bMasterDao = null;
	    	
	    	try {
	    		bMasterDao = (BranchMasterDAO)ServiceFinder.getContext(request).getBean("BranchMasterHibernateDao"); 		
	    		BranchMaster br = new BranchMaster();
	    		System.out.println(isactive+"--isactive");
					//isactive=isactive.equalsIgnoreCase("true")?"Y":"N";//direct from old value
					br.setActive(isactive);
					br.setLandline(br_landline);
					br.setMobile(br_mobile);
					br.setStateId(Long.parseLong(br_state));
					br.setImId((String)session.getAttribute("IM_ID"));
					br.setSmId(Long.parseLong(schoolID));
					br.setBranchName(sname);
					br.setShortName(shortName);
					br.setAddress(address);
					br.setCity(city);
					br.setStateId(new Long(br_state));
					br.setContactPerson(br_cperson);
					br.setEmailId(bemail);
					br.setBmId(Long.parseLong(branchID));
					String res =	bMasterDao.multiTransForBranchEdit(br);
				return "saved";
			} catch (BeansException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return "error";
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return "error";
			}
			
	    	
		}
	    
	public String disableEnableBranchMaster(String BD_ID,String isActive)  {
			
		BranchMasterDAO bMasterDao = null;
			try{
				bMasterDao = (BranchMasterDAO)ServiceFinder.getContext(request).getBean("BranchMasterHibernateDao");
//String sd_id,String action,String isActive
			String action = isActive.equalsIgnoreCase("Y")?"enable":"diable";
			String result = bMasterDao.multiTrancationBranchDisable(BD_ID,action,isActive);//?---wt r req to disable .
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
