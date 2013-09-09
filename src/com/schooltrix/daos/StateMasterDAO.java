/**
 * 
 */
package com.schooltrix.daos;

import java.io.Serializable;
import java.util.List;

import com.schooltrix.hibernate.SchoolMaster;
import com.schooltrix.hibernate.StateMaster;

/**
 * @author bhanu
 *
 */
public interface StateMasterDAO extends Serializable {
	public boolean save(StateMaster transientInstance) throws Exception ;
	
	public boolean update(StateMaster transientInstance)throws Exception ;
	
	public boolean delete(StateMaster persistentInstance)throws Exception ;
	
	public StateMaster findById(long id)throws Exception ;
	
	public StateMaster findByProperty(final String filed,final String value)throws Exception ;
	
	public List findByPropertyList(final String filed,final String value)throws Exception ;
	
	public List findAll()throws Exception ;
	
}
