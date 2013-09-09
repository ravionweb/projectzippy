package com.schooltrix.daos;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Session;

import com.schooltrix.hibernate.BranchMaster;
import com.schooltrix.hibernate.BranchSearchHistory;
import com.schooltrix.hibernate.BranchStaffMap;
import com.schooltrix.hibernate.SchoolSearchHistory;
/**
 * @author bhanu
 *
 */
public interface BranchMasterDAO extends Serializable {
	
	public boolean save(BranchMaster transientInstance) throws Exception ;
	
	public boolean update(BranchMaster transientInstance)throws Exception ;
	
	public boolean delete(BranchMaster persistentInstance)throws Exception ;
	
	public BranchMaster findById(java.lang.Long id)throws Exception ;
	
	public BranchMaster findByProperty(final String filed,final String value)throws Exception ;
	
	public List findByPropertyList(final String filed,final String value)throws Exception ;
	
	public List getBranchList(final String filed,final String value,final String filed1,final Long value1)throws Exception ; 
	
	public List getMultiBranchList(final String im_id,final String inQuery)throws Exception ; 
	
	public List findAll()throws Exception ;
	
	public List getBranchesForEdit(final String schhols,final String branches,final String citys,final  String states,final  String addr,
			final  String contactPerson,	final  String mobile,final  String landline,final  String email,final  String active,final long im_id) throws Exception;
	
	public String isDescThereDB(final String iM_ID,final String uM_ID,final String searchDesc) throws Exception;
	
	public String saveBranchSearchHistory(BranchSearchHistory querySearchHis) throws Exception;
	
	public List searchHistoryLoadDB(final String iM_ID, final  String uM_ID)throws Exception;
	
	public boolean deleteBranchSerchHistoryDocById(java.lang.Long id) throws Exception;
	
	public boolean deleteBranchSerchHistoryDoc(BranchSearchHistory persistentInstance) throws Exception;
	
	public BranchSearchHistory searchHistoryBranchFindById(java.lang.Long id) throws Exception;
	
	public List searchBranchHistoryLoadByIDDisplay(final String querySt )throws Exception;

	public String multiTransForBranchEdit(BranchMaster br)throws Exception;

	public String multiTrancationBranchDisable(String bD_ID, String action,String isActive)throws Exception;
	
	//	Branch Staff Map *********Start***********
	public boolean save(BranchStaffMap transientInstance) throws Exception ;
	public boolean update(BranchStaffMap transientInstance) throws Exception ;
	public boolean delete(BranchStaffMap persistentInstance)  throws Exception;
	public BranchMaster findByPropertyBranchStaffMap(final String filed,final String value)  throws Exception;
	//	Branch Staff Map *********END***********
}
