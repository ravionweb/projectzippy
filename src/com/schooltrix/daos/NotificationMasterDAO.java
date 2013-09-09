/**
 * 
 */
package com.schooltrix.daos;

import java.io.Serializable;
import java.util.List;
import com.schooltrix.hibernate.NotificationMaster;

/**
 * @author bhanu
 *
 */
public interface NotificationMasterDAO extends Serializable {
	
	public boolean save(NotificationMaster transientInstance) throws Exception ;
	
	public boolean update(NotificationMaster transientInstance)throws Exception ;
	
	public boolean delete(NotificationMaster persistentInstance)throws Exception ;
	
	public NotificationMaster findById(java.lang.Long id)throws Exception ;
	
	public NotificationMaster findByProperty(final String filed,final String value)throws Exception ;
	
	public List findByPropertyList(final String filed,final String value)throws Exception ;
	
	public List findAll()throws Exception ;
	
	public List getNotificationForParent(final String BM_ID,final String classID)  throws Exception;
}
