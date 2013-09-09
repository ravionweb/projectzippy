/**
 * 
 */
package com.schooltrix.daos;

import java.io.Serializable;
import java.util.List;

import com.schooltrix.hibernate.DomainControl;

/**
 * @author bhanu
 *
 */
public interface DomainDAO extends Serializable {
	
	public boolean save(DomainControl transientInstance) throws Exception ;
	
	public boolean update(DomainControl transientInstance)throws Exception ;
	
	public boolean delete(DomainControl persistentInstance)throws Exception ;
	
	public DomainControl findById(java.lang.Long id)throws Exception ;
	
	public DomainControl findByProperty(final String filed,final String value)throws Exception ;
	
	public List findByPropertyList(final String filed,final String value)throws Exception ;
	
	public List findAll()throws Exception ;
}
