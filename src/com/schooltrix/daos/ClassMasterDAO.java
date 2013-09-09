/**
 * 
 */
package com.schooltrix.daos;

import java.io.Serializable;
import java.util.List;

import com.opensymphony.xwork2.inject.Scope.Strategy;
import com.schooltrix.hibernate.ClassBranchMap;
import com.schooltrix.hibernate.ClassMaster;
import com.schooltrix.hibernate.SubjectMaster;

/**
 * @author bhanu
 *
 */
public interface ClassMasterDAO extends Serializable {
	
	public boolean save(ClassMaster transientInstance) throws Exception ;
	
	public boolean update(ClassMaster transientInstance)throws Exception ;
	
	public boolean delete(ClassMaster persistentInstance)throws Exception ;
	
	public ClassMaster findById(java.lang.Long id)throws Exception ;
	
	public ClassMaster findByProperty(final String filed,final String value)throws Exception ;
	
	public List findByPropertyList(final String filed,final String value)throws Exception ;
	
	public List getClassMasterList(final String IM_ID,final String SM_ID,final String BM_ID,final int flag) throws Exception;

	public List getClassMasterList(String BM_ID)throws Exception ;
	
	public List getMultiClassMasterList(final String BM_IDs) throws Exception ;
	
	public List findAll()throws Exception ;
	
	
	public boolean save(SubjectMaster transientInstance) throws Exception ;
	
	public boolean update(SubjectMaster transientInstance)throws Exception ;
	
	public boolean delete(SubjectMaster persistentInstance)throws Exception ;
	
	public SubjectMaster findBySubjectMasterId(java.lang.Long id)throws Exception ;
	
	public SubjectMaster findBySubjectMasterProperty(final String filed,final String value)throws Exception ;
	
	public List findBySubjectMasterPropertyList(final String filed,final String value)throws Exception ;
	
	public List getSubjectMasterList(String IM_ID, String BM_ID, String SM_ID,String CM_ID)throws Exception ;
	
	public List getMultiSubjectMasterList(String IM_ID,final String BM_ID, String SM_ID,final String CM_ID) throws Exception;
	
	public List findAllSubjectMaster()throws Exception ;
	
	public String findAllSubjectMasterJSON() throws Exception;

	public boolean saveClassBranchMap(ClassBranchMap transientInstance) throws Exception;

	public String reNameClassName(String classID, String newClassName,String bmID) throws Exception;

	public String saveClass(ClassMaster classMaster,String bmID) throws Exception;

	public String disableCLass(String bmID, String cmID);
	
	public ClassBranchMap findByIdClassBranchMap(java.lang.Long id) throws Exception;
	
	public boolean isClassByName(final String className,final String bmID);
	
}
