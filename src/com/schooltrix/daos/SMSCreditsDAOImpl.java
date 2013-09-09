package com.schooltrix.daos;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.HibernateCallback;

import com.schooltrix.hibernate.QuickSms;
import com.schooltrix.hibernate.SmsCredits;

public class SMSCreditsDAOImpl extends STHibernateDAOSupport implements SMSCreditsDAO{
	
	@Override
	public boolean save(SmsCredits transientInstance) throws Exception {
		
			getHibernateTemplate().saveOrUpdate(transientInstance);
			System.out.println("in saveee");
			return true;
		
	}
	@Override
	public boolean update(SmsCredits transientInstance) {
		try {
			getHibernateTemplate().update(transientInstance);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	@Override
	public boolean delete(SmsCredits persistentInstance) {
		try {
			getHibernateTemplate().delete(persistentInstance);
			return true;
		} catch (Exception re) {
			re.printStackTrace();
			return false;
		}
	}
	@Override
	public SmsCredits findByProperty(final String filed,final String value) {
		try {
			return (SmsCredits) getHibernateTemplate().execute(
					new HibernateCallback() {
						public Object doInHibernate(Session session)
								throws HibernateException, SQLException{							
							Criteria smsCrit = session.createCriteria(SmsCredits.class);
							smsCrit.add(Restrictions.eq(filed, value));
							smsCrit.add(Restrictions.eq("active", "Y"));
							List list = smsCrit.list();
							if(list.size()>0)
								return list.get(0);
							else
								return null;
						}
					});
		} catch (Exception ex_) {
			ex_.printStackTrace();
			return null;
		}
		
	}
		
	
	@Override
	public List findByPropertyList(final String filed,final String value) {
		List SmsCreditsList=null;
		try {
			SmsCreditsList = (List) getHibernateTemplate().execute(
					new HibernateCallback() {
						public Object doInHibernate(Session session)throws HibernateException, SQLException {
							Criteria crit = session.createCriteria(SmsCredits.class);
							crit.add(Restrictions.eq(filed, value));
							//crit.addOrder(Order.desc("sno"));
							return crit.list();
						}
					});
		} catch (Exception ex_) {
			ex_.printStackTrace();
			return null;
		}
		return SmsCreditsList;
	}
	
	
	
	@Override
	public SmsCredits findById(java.lang.Long id) {
		try {
			SmsCredits instance = (SmsCredits) getHibernateTemplate().get("com.schooltrix.hibernate.SmsCredits", id);
			return instance;
		} catch (Exception re) {
			throw re;
		}
	}

	@Override
	public List findAll() {
		try {
			String queryString = "from SmsCredits";
			return getHibernateTemplate().find(queryString);
		} catch (Exception re) {
			re.printStackTrace();
			return null;
		}
	}
	//-------------------------------------------------------------------------------------
	@Override
	public boolean saveQuickMSG(QuickSms transientInstance) throws Exception {
			getHibernateTemplate().saveOrUpdate(transientInstance);
			return true;
		
	}
}