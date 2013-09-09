/**
 * 
 */
package com.schooltrix.daos;

import java.io.Serializable;
import java.util.List;

import com.schooltrix.hibernate.StaffDetails;
import com.schooltrix.hibernate.StaffSearchHistory;

/**
 * @author bhanu
 *
 */
public interface StaffDetailsDAO extends Serializable {
	
	public boolean save(StaffDetails transientInstance) throws Exception ;
	
	public boolean update(StaffDetails transientInstance)throws Exception ;
	
	public boolean delete(StaffDetails persistentInstance)throws Exception ;
	
	public StaffDetails findById(java.lang.Long id)throws Exception ;
	
	public StaffDetails findByProperty(final String filed,final String value)throws Exception ;
	
	public StaffDetails findByProperty(final String filed,final Long value)throws Exception ;

	public List findByPropertyList(final String filed,final String value)throws Exception ;
	
	public List emailCheck(String email,Long im_id) throws Exception;
	
	public List findAll()throws Exception ;

	public List getStaffForEdit(String im_id, String insmIds, String inbmIds,
			String fname, String lname, String dob, String email,
			String mobile, String landline, String addr1, String addr2,
			String city, String state, String gender, String desig,
			String admissionNumber, String btCodt, String isParentCheck,
			String parentTypeSel, String active, String isDefault);
	
	public String saveStaffSearchHistory(StaffSearchHistory querySearchHis) throws Exception;
	
	public String isDescThereDB(final String iM_ID,final String uM_ID,final String searchDesc) throws Exception;
	
	public List searchStaffHistoryLoadByIDDisplay(final String querySt )throws Exception;
	
	public List searchHistoryLoadDB(final String iM_ID, final  String uM_ID)throws Exception;
	
	public boolean deleteStaffSerchHistoryById(java.lang.Long id) throws Exception ;
	
	public StaffSearchHistory searchHistoryStaffFindById(java.lang.Long id) throws Exception ;
	
	public boolean deleteStaffSerchHistory(StaffSearchHistory persistentInstance) throws Exception ;
	
	public String multiTrancationStaffDisable(String sta_ID, String action,String isActive);
	
	public String multiTransEditStaffSave(StaffDetails staffDetails);
	
	
	
	
	
	
	
	
}
