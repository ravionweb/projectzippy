/**
 * 
 */
package com.schooltrix.daos;

import java.io.Serializable;
import java.util.List;

import com.schooltrix.hibernate.BranchSearchHistory;
import com.schooltrix.hibernate.ParentStudentMap;
import com.schooltrix.hibernate.StudentDetails;
import com.schooltrix.hibernate.StudentSearchHistory;
import com.schooltrix.hibernate.StudentSectionMap;
import com.schooltrix.hibernate.StudentxlErrorTemp;

/**
 * @author bhanu
 *
 */
public interface StudentDetailsDAO extends Serializable {
	
	public boolean save(StudentDetails transientInstance) throws Exception ;
	
	public boolean update(StudentDetails transientInstance)throws Exception ;
	
	public boolean delete(StudentDetails persistentInstance)throws Exception ;
	
	public StudentDetails findById(java.lang.Long id)throws Exception ;
	
	public StudentDetails findByProperty(final String filed,final String value)throws Exception ;

	public StudentDetails findByProperty(final String filed,final Long value)throws Exception ;
	
	public List findByPropertyList(final String filed,final String value)throws Exception ;
	
	public List findAll()throws Exception ;

	public boolean saveStudentParentMap(ParentStudentMap transientInstance) throws Exception ;

	public ParentStudentMap getStudentParentMap(final String field,final String pd_id) throws Exception ;//may use both parent and student

	public boolean insertStudentSectionMap(StudentSectionMap ssm) ;

	public StudentSectionMap getStudentSectionMap(final String filed,final String value) throws Exception;

	public boolean insertStudentErrorLog(StudentxlErrorTemp setemp) throws Exception;
	
	public List getStudentErrorLog(final String um_id) throws Exception;
	
	public int deleteStudentErrorLog(final String um_id) throws Exception;

	public List getStudentForEdit(String im_id,String string, String string2,
			String string3, String string4, String fname, String lname,
			String dob, String email, String mobile, String landline,
			String addr1, String addr2, String city, String state,
			String gender, String admissionNumber, String admissionDate,
			String classAdmittedIn, 
			String active);
	
	public String isDescThereDB(final String iM_ID,final String uM_ID,final String searchDesc) throws Exception;
	
	public String saveStudentSearchHistory(StudentSearchHistory querySearchHis) throws Exception;
	
	public List searchHistoryLoadDB(final String iM_ID, final  String uM_ID)throws Exception;
	
	public boolean deleteStudentSerchHistoryDocById(java.lang.Long id) throws Exception ;
	
	public StudentSearchHistory searchHistoryStudentFindById(java.lang.Long id) throws Exception;
	
	public List searchStudentHistoryLoadByIDDisplay(final String querySt )throws Exception;
	
	public boolean deleteStudentSerchHistoryDoc(StudentSearchHistory persistentInstance) throws Exception;

	public String multiTransEditStudentSave(StudentDetails studentData);

	public String multiTrancationStudnetDisable(String stu_ID, String action,
			String isActive);
	
	public List getStudentSectionMapList(final String bmID,final String scmID) throws Exception;

}
