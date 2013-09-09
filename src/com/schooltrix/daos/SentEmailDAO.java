/**
 * 
 */
package com.schooltrix.daos;

import java.io.Serializable;
import java.util.List;

import com.schooltrix.hibernate.SentEmail;


/**
 * @author bhanu
 *
 */
public interface SentEmailDAO extends Serializable {

	public boolean save(SentEmail transientInstance) throws Exception ;
	
	public boolean update(SentEmail transientInstance)throws Exception ;
	
	public boolean delete(SentEmail persistentInstance)throws Exception ;
	
	public SentEmail findById(java.lang.Long id)throws Exception ;
	
	public SentEmail findByProperty(final String filed,final String value)throws Exception ;
	
	public List findByPropertyList(final String filed,final String value)throws Exception ;
	
	public List findAll()throws Exception ;
}
