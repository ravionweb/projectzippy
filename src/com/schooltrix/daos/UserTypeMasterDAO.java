package com.schooltrix.daos;

import java.io.Serializable;
import java.util.List;

import com.schooltrix.hibernate.UserType;
import com.schooltrix.hibernate.UserType;
/**
 * @author bhanu
 *
 */
public interface UserTypeMasterDAO extends Serializable {
	
	public boolean save(UserType transientInstance) throws Exception;

	public boolean update(UserType transientInstance)throws Exception ;
	
	public boolean delete(UserType persistentInstance)throws Exception ;
	
	public UserType findById(java.lang.Long id)throws Exception ;
	
	public UserType findByProperty(final String filed,final String value)throws Exception ;
	
	public List findByPropertyList(final String filed,final String value)throws Exception ;
	
	public List findAll()throws Exception ;

	
}
