/**
 * 
 */
package com.schooltrix.daos;

import java.io.Serializable;
import java.util.List;

import com.schooltrix.hibernate.SectionClassMap;
import com.schooltrix.hibernate.SectionMaster;

/**
 * @author bhanu
 *
 */
public interface SectionMasterDAO extends Serializable {

	public boolean save(SectionMaster transientInstance) throws Exception ;
	
	public boolean update(SectionMaster transientInstance)throws Exception ;
	
	public boolean delete(SectionMaster persistentInstance)throws Exception ;
	
	public SectionMaster findById(java.lang.Long id)throws Exception ;
	
	public SectionMaster findByProperty(final String filed,final String value)throws Exception ;
	
	public List findByPropertyList(final String filed,final String value)throws Exception ;
	
	public List findAll()throws Exception ;

	public boolean saveSectionClassMap(SectionClassMap transientInstance) throws Exception ;
	
	public SectionClassMap getSectionClassMap(final String filed,final Long value) throws Exception ;

	public SectionClassMap findByProperty3(final String bm_d,final String class_id,final String section_id) throws Exception;
	
	public List getSectionClassMapList(final String BM_IDs, final String CM_IDs) throws Exception ;

	public String reNameSectionName(String branchID, String classID, String sectionID, String newSectionName);
	
	public boolean isSectionByName(final String sectionName, final String cm_id, final String bm_id) ;
	
	public String saveSection(SectionMaster sectionMaster,String bmID,String cmID) throws Exception;

	public String disableSection(String schoolID, String branchID,	String classID, String sectionID, String count) throws Exception;

	public String moveSection(String schoolID, String branchID, String classID,
			String oldSectionID, String newSectionID);
	
}
