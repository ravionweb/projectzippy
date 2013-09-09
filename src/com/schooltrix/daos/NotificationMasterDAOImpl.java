package com.schooltrix.daos;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.LockMode;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.hibernate.type.StringType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.schooltrix.hibernate.NotificationMaster;

public class NotificationMasterDAOImpl extends STHibernateDAOSupport implements NotificationMasterDAO{
	
	@Override
	public boolean save(NotificationMaster transientInstance) throws Exception {
		
			getHibernateTemplate().saveOrUpdate(transientInstance);
			System.out.println("in saveee");
			return true;
		
	}
	
	@Override
	public boolean update(NotificationMaster transientInstance) throws Exception {
		try {
			getHibernateTemplate().update(transientInstance);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	@Override	
	public boolean delete(NotificationMaster persistentInstance) throws Exception {
		try {
			getHibernateTemplate().delete(persistentInstance);
			return true;
		} catch (Exception re) {
			re.printStackTrace();
			return false;
		}
	}
	
	@Override
	public NotificationMaster findByProperty(final String filed,final String value) throws Exception {
		try {
			return (NotificationMaster) getHibernateTemplate().execute(
					new HibernateCallback() {
						public Object doInHibernate(Session session)
								throws HibernateException, SQLException{							
							Criteria isExpiredCrit = session.createCriteria(NotificationMaster.class);
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
		List NotificationMasterList=null;
		try {
			NotificationMasterList = (List) getHibernateTemplate().execute(
					new HibernateCallback() {
						public Object doInHibernate(Session session)throws HibernateException, SQLException {
							Criteria crit = session.createCriteria(NotificationMaster.class);
							crit.add(Restrictions.eq(filed, value));
							//crit.addOrder(Order.desc("sno"));
							System.out.println(crit.list().size()+"in daoImpl find by props");
							return crit.list();
						}
					});
		} catch (Exception ex_) {
			ex_.printStackTrace();
			return null;
		}
		return NotificationMasterList;
	}
	
	@Override
	public NotificationMaster findById(java.lang.Long id) throws Exception {
		try {
			System.out.println("id--"+id);
			NotificationMaster instance = (NotificationMaster) getHibernateTemplate().get("com.schooltrix.hibernate.NotificationMaster", id);
			return instance;
		} catch (Exception re) {
			re.printStackTrace();
			return null;
		}
	}

	@Override
	public List findAll() throws Exception {
		try {
			String queryString = "from NotificationMaster";
			return getHibernateTemplate().find(queryString);
		} catch (Exception re) {
			re.printStackTrace();
			return null;
		}
	}
	@Override
	public List getNotificationForParent(final String BM_ID,final String classID)  throws Exception{

		
		List notificationList=null;
		try {
			//System.out.println("in branchMasterList DAOIMPL");
			notificationList = (List) getHibernateTemplate().execute(
					new HibernateCallback() {
						public Object doInHibernate(Session session)throws HibernateException, SQLException {
						
							String qu= "select notif_subject,notif_body,ondate from notification_master where bm_id='"+BM_ID+"' and (to_class in("+classID+")  or  to_whom ='AllParents') order by ondate desc ";
							System.out.println("##"+qu);
							SQLQuery sqlqu = session.createSQLQuery(qu);	
							sqlqu.addScalar("notif_subject", new StringType());
							sqlqu.addScalar("notif_body", new StringType());
							sqlqu.addScalar("ondate", new StringType());						
							System.out.println("in SIZE"+sqlqu.list().size());
							return sqlqu.list();
						}
					});
		} catch (Exception ex_) {
			ex_.printStackTrace();
			return notificationList;
		}
		return notificationList;
	}
	
	
}