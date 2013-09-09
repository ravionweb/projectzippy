/**
 * 
 */
package com.schooltrix.daos;

import java.io.Serializable;
import java.util.List;

import com.schooltrix.hibernate.DocumentSearchHistory;
import com.schooltrix.hibernate.UploadDocument;
import com.schooltrix.hibernate.UploadDocumentClassBranchMap;
import com.schooltrix.hibernate.UploadDocuments;

/**
 * @author bhanu
 *
 */
public interface UploadDocDAO extends Serializable {
	
	public boolean save(UploadDocument transientInstance) throws Exception ;
	
	public boolean update(UploadDocument transientInstance)throws Exception ;
	
	public boolean delete(UploadDocument persistentInstance)throws Exception ;
	
	public UploadDocument findById(java.lang.Long id)throws Exception ;
	
	public UploadDocument findByProperty(final String filed,final String value)throws Exception ;
	
	public List findByPropertyList(final String filed,final String value)throws Exception ;
	
	public List getAssignemets(final String bm_id,final String cm_id,final int userType)throws Exception ;

	public List getAcademics(final String bm_id,final String cm_id,final int userType)throws Exception ;
	
	public List getUtilities(final String bm_id,final String cm_id,final String uptype,final int userType)throws Exception ;

	public boolean saveUploadClassBranchMap(UploadDocumentClassBranchMap transientInstance) throws Exception;

	public List getUploadDocForEdit(final String inbmIds,final String inclassIds, final String inUserTypeIds,final String uploadType,final String fileType,final String assignmentType,final String selectSubject)throws Exception ;
	
	public List findAll()throws Exception ;

	public String multiTrancationUploadDelete(String uD_ID,String Action);
	
	public List getUploadDocumentClassBranchMap(final String field,final String up_id)throws Exception;

	public String updateMultiTransactionCall(UploadDocument uploadDocData,String cmBMJson,String cm_ids[])throws Exception;

	public List getUploadDocForEditNew(String smIDs, String bmIDs, String cmIDs, String userIDs, String uploadType, String fileType, String assignmentType, String selectSubject,String dateQString)throws Exception;

	public String saveSearchHistory(DocumentSearchHistory querySearchHis) throws Exception;

	public String isDescThereDB(final String iM_ID,final String uM_ID,final String searchDesc) throws Exception;

	public List searchHistoryLoadDB(final String iM_ID, final String uM_ID)throws Exception;
	
	public List searchHistoryLoadByIDDisplay(final String querySt )throws Exception;
	
	public boolean deleteSerchHistoryDocById(java.lang.Long id) throws Exception;
	
	public boolean deleteSerchHistoryDoc(DocumentSearchHistory persistentInstance) throws Exception ;
	
	public DocumentSearchHistory serchHistoryDocFindById(java.lang.Long id) throws Exception;
	
}
