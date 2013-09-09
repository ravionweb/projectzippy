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

import com.schooltrix.hibernate.UserMaster;
import com.schooltrix.hibernate.UserMaster;

public class UserMasterDAOImpl extends STHibernateDAOSupport implements UserMasterDAO {
	
	@Override
	public boolean save(UserMaster transientInstance) throws Exception {
		
			getHibernateTemplate().saveOrUpdate(transientInstance);
			System.out.println("in saveee");
			return true;
		
	}
	@Override
	public boolean update(UserMaster transientInstance) throws Exception {
		try {
			getHibernateTemplate().update(transientInstance);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	@Override
	public boolean delete(UserMaster persistentInstance) throws Exception {
		try {
			getHibernateTemplate().delete(persistentInstance);
			return true;
		} catch (Exception re) {
			re.printStackTrace();
			return false;
		}
	}
	@Override
	public UserMaster findByProperty(final String filed1,final String value1,final String filed2,final String value2) throws Exception {
		try {
			return (UserMaster) getHibernateTemplate().execute(
					new HibernateCallback() {
						public Object doInHibernate(Session session)
								throws HibernateException, SQLException{							
							Criteria usermasterCrit = session.createCriteria(UserMaster.class);
							usermasterCrit.add(Restrictions.eq(filed1, value1));
							usermasterCrit.add(Restrictions.eq(filed2, value2));
							usermasterCrit.add(Restrictions.eq("active", "Y"));
							List list = usermasterCrit.list();
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
		List UserMasterList=null;
		try {
			UserMasterList = (List) getHibernateTemplate().execute(
					new HibernateCallback() {
						public Object doInHibernate(Session session)throws HibernateException, SQLException {
							Criteria crit = session.createCriteria(UserMaster.class);
							crit.add(Restrictions.eq(filed, value));
							//crit.addOrder(Order.desc("sno"));
							return crit.list();
						}
					});
		} catch (Exception ex_) {
			ex_.printStackTrace();
			return null;
		}
		return UserMasterList;
	}
	
	//Used in --parentDashBoard--StudentDeatilsAnalysis class
	@Override
	public UserMaster getUserMasterFieldData(final String filed,final String value ,final int flag) throws Exception {
		try {
			return (UserMaster) getHibernateTemplate().execute(
					new HibernateCallback() {
						public Object doInHibernate(Session session)throws HibernateException, SQLException {
							Criteria crit = session.createCriteria(UserMaster.class);
							if (flag == 1) {
								crit.add(Restrictions.eq(filed, Long.parseLong(value)));
							} else {
								crit.add(Restrictions.eq(filed, value));

							}
							//crit.addOrder(Order.desc("sno"));
							List list = crit.list();
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
	public List findByPropertyList2(final String filed,final String value,final String filed1,final String value1) throws Exception {
		List UserMasterList=null;
		try {
			//System.out.println(filed+"--"+value+"--imId-"+filed1+"--"+value1);
			UserMasterList = (List) getHibernateTemplate().execute(
					new HibernateCallback() {
						public Object doInHibernate(Session session)throws HibernateException, SQLException {
							Criteria crit = session.createCriteria(UserMaster.class);
							crit.add(Restrictions.eq(filed, value));
							crit.add(Restrictions.eq(filed1, new Long(value1)));
							//crit.addOrder(Order.desc("sno"));
							return crit.list();
						}
					});
		} catch (Exception ex_) {
			ex_.printStackTrace();
			return null;
		}
		return UserMasterList;
	}
	//for non admin usersss...
	@Override
	public List uniqueIDCheck(final String filed,final String value,final String filed1,final String value1) throws Exception {
		List UserMasterList=null;
		try {
			//System.out.println(filed+"--"+value+"--imId-"+filed1+"--"+value1);
			UserMasterList = (List) getHibernateTemplate().execute(
					new HibernateCallback() {
						public Object doInHibernate(Session session)throws HibernateException, SQLException {
							Criteria crit = session.createCriteria(UserMaster.class);
							crit.add(Restrictions.like(filed, value+"%"));
							crit.add(Restrictions.eq(filed1, new Long(value1)));
							//crit.addOrder(Order.desc("sno"));
							return crit.list();
						}
					});
		} catch (Exception ex_) {
			ex_.printStackTrace();
			return null;
		}
		return UserMasterList;
	}
	@Override
	public List adminUniqueIDCheck(final String filed,final String value,final String filed1,final String value1) throws Exception {
		List UserMasterList=null;
		try {
			//System.out.println(filed+"--"+value+"--imId-"+filed1+"--"+value1);
			UserMasterList = (List) getHibernateTemplate().execute(
					new HibernateCallback() {
						public Object doInHibernate(Session session)throws HibernateException, SQLException {
							Criteria crit = session.createCriteria(UserMaster.class);
							crit.add(Restrictions.eq(filed, value));
							crit.add(Restrictions.eq(filed1, new Long(value1)));
							//crit.addOrder(Order.desc("sno"));
							return crit.list();
						}
					});
		} catch (Exception ex_) {
			ex_.printStackTrace();
			return null;
		}
		return UserMasterList;
	}
	
	@Override
	public UserMaster findById(java.lang.Long id) throws Exception {
		try {
			UserMaster instance = (UserMaster) getHibernateTemplate().get("com.schooltrix.hibernate.UserMaster", id);
			return instance;
		} catch (Exception re) {
			throw re;
		}
	}

	@Override
	public List findAll() throws Exception {
		try {
			String queryString = "from UserMaster";
			return getHibernateTemplate().find(queryString);
		} catch (Exception re) {
			re.printStackTrace();
			return null;
		}
	}
	@Override
	public UserMaster findByProperty3(final String filed1,final String value1,
			final	String filed2,final String value2, final String filed3, final Long value3)
			throws Exception {
		try {
			return (UserMaster) getHibernateTemplate().execute(
					new HibernateCallback() {
						public Object doInHibernate(Session session)
								throws HibernateException, SQLException{							
							Criteria usermasterCrit = session.createCriteria(UserMaster.class);
							usermasterCrit.add(Restrictions.eq(filed1, value1));
							usermasterCrit.add(Restrictions.eq(filed2, value2));
							usermasterCrit.add(Restrictions.eq(filed3, value3));
							usermasterCrit.add(Restrictions.eq("active", "Y"));
							List list = usermasterCrit.list();
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
}