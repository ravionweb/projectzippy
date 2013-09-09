package com.schooltrix.daos;

import java.io.Serializable;
import java.util.List;

import com.schooltrix.hibernate.QuickSms;
import com.schooltrix.hibernate.SmsCredits;
/**
 * @author bhanu
 *
 */
public interface SMSCreditsDAO extends Serializable {
	
	public boolean save(SmsCredits transientInstance) throws Exception ;
	
	public boolean update(SmsCredits transientInstance)throws Exception ;
	
	public boolean delete(SmsCredits persistentInstance)throws Exception ;
	
	public SmsCredits findById(java.lang.Long id)throws Exception ;
	
	public SmsCredits findByProperty(final String filed,final String value)throws Exception ;
	
	public List findByPropertyList(final String filed,final String value)throws Exception ;
	
	public List findAll()throws Exception ;
	
	public boolean saveQuickMSG(QuickSms transientInstance) throws Exception ;
}
