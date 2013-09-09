package com.schooltrix.daos;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.hibernate.type.StringType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.schooltrix.hibernate.ClassBranchMap;
import com.schooltrix.hibernate.ClassMaster;
import com.schooltrix.hibernate.ClassMasterHistory;
import com.schooltrix.hibernate.SectionClassMap;
import com.schooltrix.hibernate.SectionMaster;
import com.schooltrix.hibernate.SectionMaster;
import com.schooltrix.hibernate.SectionMasterHistory;
import com.schooltrix.hibernate.StudentSectionMap;
import com.schooltrix.hibernate.StudentSectionMapHistory;
import com.schooltrix.managers.ServiceFinder;

public class SectionMasterDAOImpl extends STHibernateDAOSupport implements SectionMasterDAO{
	
	@Override
	public boolean save(SectionMaster transientInstance) throws Exception {
		
			getHibernateTemplate().saveOrUpdate(transientInstance);
			System.out.println("in saveee");
			return true;
		
	}
	@Override
	public boolean update(SectionMaster transientInstance) throws Exception {
		try {
			getHibernateTemplate().update(transientInstance);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	@Override
	public boolean delete(SectionMaster persistentInstance) throws Exception {
		try {
			getHibernateTemplate().delete(persistentInstance);
			return true;
		} catch (Exception re) {
			re.printStackTrace();
			return false;
		}
	}
	@Override
	public SectionMaster findByProperty(final String filed,final String value) throws Exception {
		try {
			return (SectionMaster) getHibernateTemplate().execute(
					new HibernateCallback() {
						public Object doInHibernate(Session session)
								throws HibernateException, SQLException{							
							Criteria crit = session.createCriteria(SectionMaster.class);
							crit.add(Restrictions.eq(filed, value));
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
	public List findByPropertyList(final String filed,final String value) throws Exception {
		List SectionMasterList=null;
		try {
			SectionMasterList = (List) getHibernateTemplate().execute(
					new HibernateCallback() {
						public Object doInHibernate(Session session)throws HibernateException, SQLException {
							Criteria crit = session.createCriteria(SectionMaster.class);
							crit.add(Restrictions.eq(filed, value));
							//crit.addOrder(Order.desc("sno"));
							return crit.list();
						}
					});
		} catch (Exception ex_) {
			ex_.printStackTrace();
			return null;
		}
		return SectionMasterList;
	}
	
	
	
	@Override
	public SectionMaster findById(java.lang.Long id) throws Exception {
		try {
			SectionMaster instance = (SectionMaster) getHibernateTemplate().get("com.schooltrix.hibernate.SectionMaster", id);
			return instance;
		} catch (Exception re) {
			throw re;
		}
	}

	@Override
	public List findAll() throws Exception {
		try {
			String queryString = "from SectionMaster";
			return getHibernateTemplate().find(queryString);
		} catch (Exception re) {
			re.printStackTrace();
			return null;
		}
	}
	
	@Override
	public boolean saveSectionClassMap(SectionClassMap transientInstance) throws Exception {
		
			getHibernateTemplate().saveOrUpdate(transientInstance);
			System.out.println("in saveee---saveSectionClassMap");
			return true;
		
	}
	
	public SectionClassMap getSectionClassMap(final String filed,final Long value) throws Exception {
		

		try {
			return (SectionClassMap) getHibernateTemplate().execute(
					new HibernateCallback() {
						public Object doInHibernate(Session session)
								throws HibernateException, SQLException{							
							Criteria crit = session.createCriteria(SectionClassMap.class);
							crit.add(Restrictions.eq(filed, value));
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
	public SectionClassMap findByProperty3(final String bm_d,final String class_id,final String section_id) throws Exception {
		try {
			return (SectionClassMap) getHibernateTemplate().execute(
					new HibernateCallback() {
						public Object doInHibernate(Session session)
								throws HibernateException, SQLException{							
							Criteria crt = session.createCriteria(SectionClassMap.class);
						
							crt.add(Restrictions.eq("bmId", bm_d));
							crt.add(Restrictions.eq("cmId", class_id));
							crt.add(Restrictions.eq("seMId", section_id));
							crt.add(Restrictions.eq("active", "Y"));
							List list = crt.list();
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
	public List getSectionClassMapList(final String BM_IDs, final String CM_IDs) throws Exception {
		List sectiontMasterList=null;
		try {
			sectiontMasterList = (List) getHibernateTemplate().execute(
					new HibernateCallback() {
						public Object doInHibernate(Session session)throws HibernateException, SQLException {//need more where condtion im_id sm_id
							String qu = "";
							 qu="SELECT sm.SeM_ID as secID, sm.Section_Name as secName FROM section_class_map scm inner join section_master sm on  scm.SeM_ID=sm.SeM_ID where scm.CM_ID in ("+CM_IDs+")" +
							 		"and scm.bm_id in("+BM_IDs+") and sm.Active ='Y' group by sm.Section_Name order by  sm.SeM_ID";
							
							SQLQuery sqlqu = session.createSQLQuery(qu);							
							sqlqu.addScalar("secID", new StringType());
							sqlqu.addScalar("secName", new StringType());
							System.out.println("in SIZE"+sqlqu.list().size());
							return sqlqu.list();
						}
					});
		} catch (Exception ex_) {
			ex_.printStackTrace();
			return null;
		}
		return sectiontMasterList;
	}
	
	@Override
	public String reNameSectionName(String branchID, String classID, String sectionID, String newSectionName){

		try {
			boolean  isSectionThere = isSectionByName(newSectionName,classID, branchID);
			if (isSectionThere) {
				return "already";
			}
			
			SimpleDateFormat sdf 						= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			SectionMaster sectionMaster = findById(Long.parseLong(sectionID));
			SectionMasterHistory sectionMasterHistory = new SectionMasterHistory(sectionMaster.getSeMId(),sectionMaster.getSectionName(), sectionMaster.getActive(),
					"rename",java.sql.Timestamp.valueOf(sdf.format(new Date())));
			getHibernateTemplate().saveOrUpdate(sectionMasterHistory);
			sectionMaster.setSectionName(newSectionName);
			getHibernateTemplate().update(sectionMaster);
			return "success";
		} catch (Exception e) {
			e.printStackTrace();
			return "fail";
		}
		
	}
	
	@Override
		public boolean isSectionByName(final String sectionName, final String cm_id, final String bm_id) {
		  return (boolean)getHibernateTemplate().execute(
					new HibernateCallback() {
						public Object doInHibernate(Session session)throws HibernateException, SQLException {//need more where condtion im_id sm_id
							String qu= "select sm.Section_Name as Section from section_master sm inner join section_class_map scm on sm.SeM_ID=scm.SeM_ID	 " +
									"where sm.Section_Name ='"+sectionName+"' and scm.bm_id='"+bm_id+"' and scm.CM_ID='"+cm_id+"'";
							SQLQuery sqlqu = session.createSQLQuery(qu);							
							sqlqu.addScalar("Section", new StringType());
							System.out.println(qu);
							System.out.println("in SIZE"+sqlqu.list().size());
							if(sqlqu.list().size()>0){
								return true;								
							}else{
								return false;
							}
						}
					});
		}
		
		public String saveSection(SectionMaster sectionMaster,String bmID,String cmID) throws Exception{
			try {
				boolean  isSectionThere = isSectionByName(sectionMaster.getSectionName(),cmID, bmID);
				if (isSectionThere) {
					return "already";
				}
				save(sectionMaster);
				System.out.println(sectionMaster.getSeMId());
				//getHibernateTemplate().saveOrUpdate(sectionMaster);
				System.out.println("ONE DONE");
				SectionClassMap sectionClassMap = new SectionClassMap(sectionMaster.getSeMId()+"",cmID,bmID,"Y");
				System.out.println("Two DONE");			
				//getHibernateTemplate().saveOrUpdate(sectionClassMap);
				System.out.println("DONE");
				saveSectionClassMap(sectionClassMap);//testing transaction--here working fine null and ant data violation exception
				return "success";
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("in exception");
				return "fail";
			}
		}
		
		public String disableSection(String schoolID, String branchID,	String classID, String sectionID, String count) throws Exception{
			SectionMaster smaster= findById(Long.parseLong(sectionID));
			//sectionmaster diable
			//student_section disable--this will later bcz..there any affect in search
			//StudentSectionMap sectionMap = new 
			if (smaster != null) {
			smaster.setActive("N");
			SimpleDateFormat sdf 						= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			SectionMasterHistory history = new SectionMasterHistory(smaster.getSeMId(),smaster.getSectionName(),smaster.getActive(),
					"disable",java.sql.Timestamp.valueOf(sdf.format(new Date())));
			
			getHibernateTemplate().saveOrUpdate(history);
			getHibernateTemplate().update(smaster);
			return "success";
			}else{
				return "notexist";
			}
		}
		@Override
		public String moveSection(String schoolID, final String branchID, String classID,
				String oldSectionID, String newSectionID){
			try {
				//1)major...change in section_student_map
				//2)inert one record in section_master ..action "move"
				SectionMaster smaster= findById(Long.parseLong(oldSectionID));
		
				//first  secton class map
					SectionClassMap scmIDOld = findByProperty3(branchID, classID, oldSectionID);
					SectionClassMap scmIDNew =findByProperty3(branchID, classID, newSectionID);
				// second,we get student section map details
					SimpleDateFormat sdf 						= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					if(scmIDOld != null  && scmIDNew != null){
				
					final long scmOldID = scmIDOld.getScmId();
					final long scmNewID = scmIDNew.getScmId();
					
					SectionMasterHistory history = new SectionMasterHistory(
							smaster.getSeMId(),smaster.getSectionName(),smaster.getActive(),"move",java.sql.Timestamp.valueOf(sdf.format(new Date())));				

							getHibernateTemplate().saveOrUpdate(history);

					return (String)getHibernateTemplate().execute(
								new HibernateCallback() {
									public Object doInHibernate(Session session)throws HibernateException, SQLException {//need more where condtion im_id sm_id
										String qu= "update StudentSectionMap set scmId=:scmNewID where scmId=:scmOldID and bmId=:branchID";
										Query hqlQuery = session.createQuery(qu);							
										hqlQuery.setString("scmNewID", scmNewID+"");
										hqlQuery.setString("scmOldID", scmOldID+"");
										hqlQuery.setString("branchID", branchID);
										System.out.println("in move student-->"+qu);
										int res =  hqlQuery.executeUpdate();
										return "success";
									}
								});
					
					}
				/*		 StudentSectionMapHistory(Long ssmId, String scmId, String stuId,
							String imId, String bmId, String smId, String active,
							String action, Timestamp editedDate) {*/
				
			//	StudentSectionMapHistory history = new StudentSectionMapHistory();
				
			}  catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;
		}
		

}