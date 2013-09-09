/**
 * 
 */
package com.schooltrix.daos;

import java.io.Serializable;
import java.util.List;

import com.schooltrix.hibernate.CountryMaster;

/**
 * @author bhanu
 *
 */
public interface CountryMasterDAO extends Serializable {


	
	public boolean save(CountryMaster transientInstance) throws Exception ;
	
	public boolean update(CountryMaster transientInstance)throws Exception ;
	
	public boolean delete(CountryMaster persistentInstance)throws Exception ;
	
	public CountryMaster findById(java.lang.Long id)throws Exception ;
	
	public CountryMaster findByProperty(final String filed,final String value)throws Exception ;
	
	public List findByPropertyList(final String filed,final String value)throws Exception ;
	
	public List findAll()throws Exception ;
	

}
