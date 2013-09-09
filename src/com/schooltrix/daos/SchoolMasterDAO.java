/**
 * 
 */
package com.schooltrix.daos;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Session;

import com.schooltrix.hibernate.SchoolMaster;
import com.schooltrix.hibernate.SchoolMasterHistory;
import com.schooltrix.hibernate.SchoolSearchHistory;

/**
 * @author bhanu
 *
 */
public interface SchoolMasterDAO extends Serializable {

	public boolean save(SchoolMaster transientInstance) throws Exception ;
	
	public boolean update(SchoolMaster transientInstance)throws Exception ;
	
	public boolean delete(SchoolMaster persistentInstance)throws Exception ;
	
	public SchoolMaster findById(java.lang.Long id)throws Exception ;
	
	public SchoolMaster findByProperty(final String filed,final String value)throws Exception ;
	
	public List findByPropertyList(final String filed,final String value)throws Exception ;

	public List getSchoolList(final String filed,final Long value) throws Exception ;
	
	public List findAll()throws Exception ;
	
	public List getFranchiseList()throws Exception ;
	
	public List getSchoolDetailedList(final String filed,final Long value) throws Exception;

	public List getSchoolsForEdit(String string, String string3, String string4, String addr, String contactPerson,
			String mobile, String landline, String email, String active,long im_id) throws Exception;
	
	public String isDescThereDB(final String iM_ID,final String uM_ID,final String searchDesc) throws Exception;
	
	public String saveSchoolSearchHistory(SchoolSearchHistory querySearchHis) throws Exception;
	
	public List searchHistoryLoadDB(final String iM_ID, final  String uM_ID)throws Exception;
	
	public boolean deleteSchoolSerchHistoryDocById(java.lang.Long id) throws Exception ;
	
	public SchoolSearchHistory searchHistorySchoolFindById(java.lang.Long id) throws Exception ;
	
	public boolean deleteSchoolSerchHistoryDoc(SchoolSearchHistory persistentInstance) throws Exception ;
	
	public List searchSchoolHistoryLoadByIDDisplay(final String querySt )throws Exception;

	public String multiTransForSchoolEdit(SchoolMaster sm)throws Exception;
//	String sd_id,String action,String isActive
	public String multiTrancationSchoolDisable(String sd_id,String action,String isActive)throws Exception;
	
	
	
}