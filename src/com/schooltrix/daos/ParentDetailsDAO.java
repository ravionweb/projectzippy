/**
 * 
 */
package com.schooltrix.daos;

import java.io.Serializable;
import java.util.List;

import com.schooltrix.hibernate.ParentDetails;
import com.schooltrix.hibernate.ParentSearchHistory;

/**
 * @author bhanu
 *
 */
public interface ParentDetailsDAO extends Serializable {
	
	public boolean save(ParentDetails transientInstance) throws Exception ;
	
	public boolean update(ParentDetails transientInstance)throws Exception ;
	
	public boolean delete(ParentDetails persistentInstance)throws Exception ;
	
	public ParentDetails findById(java.lang.Long id)throws Exception ;
	
	public ParentDetails findByProperty(final String filed,final String value)throws Exception ;

	public ParentDetails findByProperty(final String filed,final Long value)throws Exception ;
	
	public List findByPropertyList(final String filed,final String value)throws Exception ;
	
	public List findByPropertyListLong(final String filed,final Long value)throws Exception ;
	
	public List emailCheck(String email,Long im_id) throws Exception;
	
	public List findAll()throws Exception ;
	
	public List getParentForEdit(final String im_id,final String schools,final  String branches,
			final String classes, final String sections, final String fname, final String lname,
			final String dob, final String email, final String mobile, final String landline,
			final String addr1, final String addr2, final String city, final String state,
			final String gender, final String admissionNumber, final String dateQString,
			final String classAdmittedIn,final String active,final String parentTypeSel,final String isDefault);
	
	public String isDescThereDB(final String iM_ID,final String uM_ID,final String searchDesc) throws Exception;
	
	public String saveParentSearchHistory(ParentSearchHistory querySearchHis) throws Exception;
	
	public List searchHistoryLoadDB(final String iM_ID, final  String uM_ID)throws Exception;
	
	public String multiTransEditParentSave(ParentDetails parentDetails);
	
	public ParentSearchHistory searchHistoryStudentFindById(java.lang.Long id) throws Exception;
	
	public boolean deleteParentSerchHistoryById(java.lang.Long id) throws Exception ;
	
	public boolean deleteParentSerchHistory(ParentSearchHistory persistentInstance) throws Exception ;
	
	public List searchParentHistoryLoadByIDDisplay(final String querySt )throws Exception;
	
	
}
