/**
 * 
 */
package com.schooltrix.daos;

import java.io.Serializable;
import java.util.List;

import com.schooltrix.hibernate.UserMaster;

/**
 * @author bhanu
 *
 */
public interface UserMasterDAO extends Serializable {

	public boolean save(UserMaster transientInstance) throws Exception ;
	
	public boolean update(UserMaster transientInstance)throws Exception ;
	
	public boolean delete(UserMaster persistentInstance)throws Exception ;
	
	public UserMaster findById(java.lang.Long id)throws Exception ;
	
	public UserMaster findByProperty3(final String filed1,final String value1,final String filed2,final String value2,final String filed3,final Long value3)throws Exception ;
	
	public UserMaster findByProperty(final String filed1,final String value1,final String filed2,final String value2)throws Exception ;

	public List findByPropertyList(final String filed,final String value)throws Exception ;
	
	public UserMaster getUserMasterFieldData(final String filed,final String value ,final int flag) throws Exception;

	public List findByPropertyList2(final String filed,final String value,final String filed1,final String value1)throws Exception ;
	
	public List uniqueIDCheck(final String filed,final String value,final String filed1,final String value1) throws Exception;
	
	public List adminUniqueIDCheck(final String filed,final String value,final String filed1,final String value1) throws Exception ;
	
	public List findAll()throws Exception ;
}
