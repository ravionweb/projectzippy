/**
 * 
 */
package com.schooltrix.daos;

import java.io.Serializable;
import java.util.List;

import com.schooltrix.hibernate.SentSms;

/**
 * @author bhanu
 *
 */
public interface SentSmsDAO extends Serializable {

	public boolean save(SentSms transientInstance) throws Exception ;
	
	public boolean update(SentSms transientInstance)throws Exception ;
	
	public boolean delete(SentSms persistentInstance)throws Exception ;
	
	public SentSms findById(java.lang.Long id)throws Exception ;
	
	public SentSms findByProperty(final String filed,final String value)throws Exception ;
	
	public List findByPropertyList(final String filed,final String value)throws Exception ;
	
	public List findAll()throws Exception ;
}
