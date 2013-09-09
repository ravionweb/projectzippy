package com.schooltrix.daos;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.LockMode;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.schooltrix.hibernate.StateMaster;
import com.schooltrix.hibernate.StateMaster;


public class StateMasterDAOImpl extends STHibernateDAOSupport implements StateMasterDAO {
	
	@Override
	public boolean save(StateMaster transientInstance) throws Exception {
		
			getHibernateTemplate().saveOrUpdate(transientInstance);
			System.out.println("in saveee");
			return true;
		
	}
	@Override
	public boolean update(StateMaster transientInstance) throws Exception {
		try {
			getHibernateTemplate().update(transientInstance);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	@Override
	public boolean delete(StateMaster persistentInstance) throws Exception {
		try {
			getHibernateTemplate().delete(persistentInstance);
			return true;
		} catch (Exception re) {
			re.printStackTrace();
			return false;
		}
	}
	@Override
	public StateMaster findByProperty(final String filed,final String value) throws Exception {
		try {
			return (StateMaster) getHibernateTemplate().execute(
					new HibernateCallback() {
						public Object doInHibernate(Session session)
								throws HibernateException, SQLException{							
							Criteria isExpiredCrit = session.createCriteria(StateMaster.class);
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
	public List findByPropertyList(final String filed,final String value) throws Exception {
		List StateMasterList=null;
		try {
			StateMasterList = (List) getHibernateTemplate().execute(
					new HibernateCallback() {
						public Object doInHibernate(Session session)throws HibernateException, SQLException {
							Criteria crit = session.createCriteria(StateMaster.class);
							crit.add(Restrictions.eq(filed, value));
							//crit.addOrder(Order.desc("sno"));
							return crit.list();
						}
					});
		} catch (Exception ex_) {
			ex_.printStackTrace();
			return null;
		}
		return StateMasterList;
	}
	
	
	
	@Override
	public StateMaster findById(long id) throws Exception {
		try {
			System.out.println("-id"+id);
	
			StateMaster instance = (StateMaster) getHibernateTemplate().get("com.schooltrix.hibernate.StateMaster", id);
			System.out.println("insss"+instance);
			return instance;
		} catch (Exception re) {
			//re.printStackTrace();
			return null;
		}
	}

	@Override
	public List findAll() throws Exception {
		try {
			String queryString = "from StateMaster";
			return getHibernateTemplate().find(queryString);
		} catch (Exception re) {
			re.printStackTrace();
			return null;
		}
	}
}