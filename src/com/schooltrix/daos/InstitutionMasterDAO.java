/**
 * 
 */
package com.schooltrix.daos;

import java.io.Serializable;
import java.util.List;

import com.schooltrix.hibernate.InstitutionMaster;

/**
 * @author bhanu
 *
 */
public interface InstitutionMasterDAO extends Serializable {
	
	public boolean save(InstitutionMaster transientInstance) throws Exception ;
	
	public boolean update(InstitutionMaster transientInstance)throws Exception ;
	
	public boolean delete(InstitutionMaster persistentInstance)throws Exception ;
	
	public InstitutionMaster findById(java.lang.Long id)throws Exception ;
	
	public InstitutionMaster findByProperty(final String filed,final String value)throws Exception ;
	
	public List findByPropertyList(final String filed,final String value)throws Exception ;
	
	public List findAll()throws Exception ;
}
