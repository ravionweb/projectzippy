package com.schooltrix.daos;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.LockMode;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.hibernate.type.StringType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.schooltrix.hibernate.InstitutionMaster;

public class InstitutionMasterDAOImpl extends STHibernateDAOSupport implements InstitutionMasterDAO{
	
	@Override
	public boolean save(InstitutionMaster transientInstance) throws Exception {
		
			getHibernateTemplate().saveOrUpdate(transientInstance);
			System.out.println("in saveee");
			return true;
		
	}
	@Override
	public boolean update(InstitutionMaster transientInstance) throws Exception {
		try {
			getHibernateTemplate().update(transientInstance);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	@Override
	public boolean delete(InstitutionMaster persistentInstance) throws Exception {
		try {
			getHibernateTemplate().delete(persistentInstance);
			return true;
		} catch (Exception re) {
			re.printStackTrace();
			return false;
		}
	}
	@Override
	public InstitutionMaster findByProperty(final String filed,final String value) throws Exception {
		try {
			return (InstitutionMaster) getHibernateTemplate().execute(
					new HibernateCallback() {
						public Object doInHibernate(Session session)
								throws HibernateException, SQLException{							
							Criteria isExpiredCrit = session.createCriteria(InstitutionMaster.class);
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
		List InstitutionMasterList=null;
		try {
			InstitutionMasterList = (List) getHibernateTemplate().execute(
					new HibernateCallback() {
						public Object doInHibernate(Session session)throws HibernateException, SQLException {
							Criteria crit = session.createCriteria(InstitutionMaster.class);
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
		return InstitutionMasterList;
	}
	
	public List getClassMasterList(String IM_ID,final String BM_ID, String SM_ID) throws Exception {
		List ClassMasterList=null;
		try {
			System.out.println("in getClassMasterList DAOIMPL");
			ClassMasterList = (List) getHibernateTemplate().execute(
					new HibernateCallback() {
						public Object doInHibernate(Session session)throws HibernateException, SQLException {//need more where condtion im_id sm_id
							String qu= "select cm_id,class_name from class_master where cm_id in(select cm_id  from class_branch_map where Active='Y' and bm_id='"+BM_ID+"')";
							SQLQuery sqlqu = session.createSQLQuery(qu);							
							sqlqu.addScalar("cm_id", new StringType());
							sqlqu.addScalar("class_name", new StringType());
							System.out.println("in SIZE"+sqlqu.list().size());
							return sqlqu.list();
						}
					});
		} catch (Exception ex_) {
			ex_.printStackTrace();
			return null;
		}
		return ClassMasterList;
	}
	
	
	@Override
	public InstitutionMaster findById(java.lang.Long id) throws Exception {
		try {
			System.out.println("id--"+id);
			InstitutionMaster instance = (InstitutionMaster) getHibernateTemplate().get("com.schooltrix.hibernate.InstitutionMaster", id);
			return instance;
		} catch (Exception re) {
			re.printStackTrace();
			return null;
		}
	}

	@Override
	public List findAll() throws Exception {
		try {
			String queryString = "from InstitutionMaster";
			return getHibernateTemplate().find(queryString);
		} catch (Exception re) {
			re.printStackTrace();
			return null;
		}
	}
}