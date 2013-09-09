package com.schooltrix.daos;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.HibernateCallback;

import com.schooltrix.hibernate.BranchMaster;
import com.schooltrix.hibernate.UserType;

public class UserTypeMasterDAOImpl extends STHibernateDAOSupport implements UserTypeMasterDAO{
	
	@Override
	public boolean save(UserType transientInstance) throws Exception {
		
			getHibernateTemplate().saveOrUpdate(transientInstance);
			System.out.println("in saveee");
			return true;
		
	}
	@Override
	public boolean update(UserType transientInstance) {
		try {
			getHibernateTemplate().update(transientInstance);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	@Override
	public boolean delete(UserType persistentInstance) {
		try {
			getHibernateTemplate().delete(persistentInstance);
			return true;
		} catch (Exception re) {
			re.printStackTrace();
			return false;
		}
	}
	@Override
	public UserType findByProperty(final String filed,final String value) {
		try {
			return (UserType) getHibernateTemplate().execute(
					new HibernateCallback() {
						public Object doInHibernate(Session session)
								throws HibernateException, SQLException{							
							Criteria isExpiredCrit = session.createCriteria(UserType.class);
							isExpiredCrit.add(Restrictions.eq(filed, value));
							List list = isExpiredCrit.list();
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
		List UserTypeList=null;
		try {
			UserTypeList = (List) getHibernateTemplate().execute(
					new HibernateCallback() {
						public Object doInHibernate(Session session)throws HibernateException, SQLException {
							Criteria crit = session.createCriteria(UserType.class);
							crit.add(Restrictions.eq(filed, value));
							crit.addOrder(Order.asc("utDesc"));
							return crit.list();
						}
					});
		} catch (Exception ex_) {
			ex_.printStackTrace();
			return null;
		}
		return UserTypeList;
	}
	
	
	
	@Override
	public UserType findById(java.lang.Long id) {
		try {
			UserType instance = (UserType) getHibernateTemplate().get("com.schooltrix.hibernate.UserType", id);
			return instance;
		} catch (Exception re) {
			throw re;
		}
	}

	@Override
	public List findAll() {
		try {
			String queryString = "from UserType";
			return getHibernateTemplate().find(queryString);
		} catch (Exception re) {
			re.printStackTrace();
			return null;
		}
	}
}