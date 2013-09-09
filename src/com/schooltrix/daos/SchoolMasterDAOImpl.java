package com.schooltrix.daos;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.hibernate.type.StringType;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.schooltrix.hibernate.DocumentSearchHistory;
import com.schooltrix.hibernate.FranchiseMaster;
import com.schooltrix.hibernate.SchoolMaster;
import com.schooltrix.hibernate.SchoolMasterHistory;
import com.schooltrix.hibernate.SchoolSearchHistory;
import com.schooltrix.hibernate.UploadDocument;
import com.schooltrix.hibernate.UploadDocumentHistory;


public class SchoolMasterDAOImpl extends STHibernateDAOSupport implements SchoolMasterDAO{
	
	@Override
	public boolean save(SchoolMaster transientInstance) throws Exception {
		
			getHibernateTemplate().saveOrUpdate(transientInstance);
			System.out.println("in saveee");
			return true;
		
	}
	@Override
	public boolean update(SchoolMaster transientInstance) {
		try {
			getHibernateTemplate().update(transientInstance);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	@Override
	public boolean delete(SchoolMaster persistentInstance) {
		try {
			getHibernateTemplate().delete(persistentInstance);
			return true;
		} catch (Exception re) {
			re.printStackTrace();
			return false;
		}
	}
	@Override
	public SchoolMaster findByProperty(final String filed,final String value) {
		try {
			return (SchoolMaster) getHibernateTemplate().execute(
					new HibernateCallback() {
						public Object doInHibernate(Session session)
								throws HibernateException, SQLException{							
							Criteria isExpiredCrit = session.createCriteria(SchoolMaster.class);
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
		List SchoolMasterList=null;
		try {
			SchoolMasterList = (List) getHibernateTemplate().execute(
					new HibernateCallback() {
						public Object doInHibernate(Session session)throws HibernateException, SQLException {
							Criteria crit = session.createCriteria(SchoolMaster.class);
							crit.add(Restrictions.eq(filed, value));
							//crit.addOrder(Order.desc("sno"));
							return crit.list();
						}
					});
		} catch (Exception ex_) {
			ex_.printStackTrace();
			return null;
		}
		return SchoolMasterList;
	}
	
	@Override
	public List getSchoolList(final String filed,final Long value) {
		List SchoolMasterList=null;
		try {
			SchoolMasterList = (List) getHibernateTemplate().execute(
					new HibernateCallback() {
						public Object doInHibernate(Session session)throws HibernateException, SQLException {
							Criteria crit = session.createCriteria(SchoolMaster.class);
							crit.add(Restrictions.eq(filed, value));
							crit.add(Restrictions.eq("active", "Y"));
							//crit.addOrder(Order.desc("sno"));
							return crit.list();
						}
					});
		} catch (Exception ex_) {
			ex_.printStackTrace();
			return null;
		}
		return SchoolMasterList;
	}
	@Override
	public List getSchoolDetailedList(final String filed,final Long value) {
		List SchoolMasterList=null;
		try {
			SchoolMasterList = (List) getHibernateTemplate().execute(
					new HibernateCallback() {
						public Object doInHibernate(Session session)throws HibernateException, SQLException {
							Criteria crit = session.createCriteria(SchoolMaster.class);
							crit.add(Restrictions.eq(filed, value));
							//crit.addOrder(Order.desc("sno"));
							return crit.list();
						}
					});
		} catch (Exception ex_) {
			ex_.printStackTrace();
			return null;
		}
		return SchoolMasterList;
	}
	
	@Override
	public SchoolMaster findById(java.lang.Long id) {
		try {
			SchoolMaster instance = (SchoolMaster) getHibernateTemplate().get("com.schooltrix.hibernate.SchoolMaster", id);
			return instance;
		} catch (Exception re) {
			throw re;
		}
	}


	@Override
	public List findAll() {
		try {
			String queryString = "from SchoolMaster";
			return getHibernateTemplate().find(queryString);
		} catch (Exception re) {
			re.printStackTrace();
			return null;
		}
	}
	
	@Override
	public List getFranchiseList() {
		List FranchiseMasterList=null;
		try {
			FranchiseMasterList = (List) getHibernateTemplate().execute(
					new HibernateCallback() {
						public Object doInHibernate(Session session)throws HibernateException, SQLException {
							Criteria crit = session.createCriteria(FranchiseMaster.class);
							//crit.add(Restrictions.eq(filed, value));
							crit.add(Restrictions.eq("active", "Y"));
							//crit.addOrder(Order.desc("sno"));
							System.out.println(crit.list().size()+"-");
							return crit.list();
						}
					});
		} catch (Exception ex_) {
			ex_.printStackTrace();
			return null;
		}
		return FranchiseMasterList;
	}

	@Override
	public List getSchoolsForEdit(final String schhols,final String citys,final  String states,final  String addr,final  String contactPerson,
			final  String mobile,final  String landline,final  String email,final  String active,final long im_id) throws Exception{
		
		List schoolList=null;
		String smIDOpF = null;
		
		String stIDOpF = null;
		String ctIDOpF = null;
		
	//	System.out.println(smIDs.length()+"666"+bmIDs.length()+"777"+cmIDs.length());
		if (schhols.replace("'", "").length()>1) {//this logic for identifying %
			smIDOpF = "in";			
		}else {
			smIDOpF = "like";			
		}
		
		if (citys.replace("'", "").length()>1) {
			ctIDOpF = "in";			
		}else {
			ctIDOpF = "like";			
		}
		if (states.replace("'", "").length()>1) {
			stIDOpF = "in";			
		}else {
			stIDOpF = "like";			
		}
		final String smIDOp = smIDOpF;
		final String ctIDOp = ctIDOpF;
		final String stIDOp = stIDOpF;
			try {

				return (List) getHibernateTemplate().execute(
						new HibernateCallback() {
							public Object doInHibernate(Session session)throws HibernateException, SQLException {
								String qu =	"select sm.SM_ID as SM_ID,sm.IM_ID as IM_ID,sm.School_Name as School_Name,sm.Name as Name,sm.Address as Address,sm.City as City,sm.State_ID as State_ID," +
										"sm.Contact_Person as Contact_Person,sm.Email_ID as Email_ID,sm.Mobile as Mobile,sm.Landline as Landline,sm.Active as Active from school_master sm " +
										"where sm.SM_ID "+smIDOp+" ("+schhols+")  and sm.Address like '"+addr+"'  and sm.City "+ctIDOp+" ("+citys+")    and sm.State_ID "+stIDOp+" ("+states+")" +
												"  and sm.Contact_Person like '"+contactPerson+"'  and sm.Email_ID like '"+email+"'   and sm.Mobile like '"+mobile+"'   and sm.Landline like '"+landline+"'   " +
														"and sm.Active ='"+active+"' and sm.IM_ID ="+im_id+ " order by sm_id desc";
				
					
								//System.out.println("##-->"+qu);
								
								StringType st = new StringType();
								SQLQuery sqlqu = session.createSQLQuery(qu);	
								
								sqlqu.addScalar("SM_ID", st);
								sqlqu.addScalar("IM_ID", st);
								sqlqu.addScalar("School_Name",st );
								sqlqu.addScalar("Name", st);
								sqlqu.addScalar("Address",st );
								sqlqu.addScalar("City",st);
								sqlqu.addScalar("State_ID", st);

								sqlqu.addScalar("Contact_Person", st);//
								sqlqu.addScalar("Email_ID", st);//
								sqlqu.addScalar("Mobile", st);//
								sqlqu.addScalar("Landline", st);//
								sqlqu.addScalar("Active", st);// 
								
								System.out.println("in SIZE"+sqlqu.list().size());
								return sqlqu.list();
							}
						});
			} catch (Exception e) {
			e.printStackTrace();
			}
		
		return null;
	}
	
	public String isDescThereDB(final String iM_ID,final String uM_ID,final String searchDesc) throws Exception{
		
		try {
			return (String) getHibernateTemplate().execute(
						new HibernateCallback() {
							public Object doInHibernate(Session session)
									throws HibernateException, SQLException{							
								Criteria isCrit = session.createCriteria(SchoolSearchHistory.class);
								isCrit.add(Restrictions.eq("imId", iM_ID));
								isCrit.add(Restrictions.eq("userId", uM_ID));
								isCrit.add(Restrictions.eq("searchDescription", searchDesc));
								List list = isCrit.list();
								if(list.size()>0)
									return "yes";
								else
									return "no";
							}
						});
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
		
	}
	public String saveSchoolSearchHistory(SchoolSearchHistory querySearchHis) throws Exception{
		String re = null;
		try {
			final String qust = querySearchHis.getQueryString();
				re =  (String) getHibernateTemplate().execute(
						new HibernateCallback() {
							public Object doInHibernate(Session session)
									throws HibernateException, SQLException{							
								Criteria isCrit = session.createCriteria(SchoolSearchHistory.class);
								isCrit.add(Restrictions.eq("queryString", qust));
								List list = isCrit.list();
								if(list.size()>0)
									return "already";
								else
									return "proceed";
							}
						});
		System.out.println(re+"**re");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		if (re.equalsIgnoreCase("already")) {
			return "already";
		}
		
		
		try {
			getHibernateTemplate().save(querySearchHis);
			System.out.println(querySearchHis.getSshId()+"**IDDDDD");
			return "success";
		} catch (Exception e) {
			System.out.println("in first Excep*********");
			e.printStackTrace();
		}
		return "error";
	}
	public List searchHistoryLoadDB(final String iM_ID, final  String uM_ID)throws Exception{
		try {
			return (List) getHibernateTemplate().execute(
						new HibernateCallback() {
							public Object doInHibernate(Session session)
									throws HibernateException, SQLException{							
								Criteria isCrit = session.createCriteria(SchoolSearchHistory.class);
								isCrit.add(Restrictions.eq("imId", iM_ID));
								isCrit.add(Restrictions.eq("userId", uM_ID));
								isCrit.addOrder(Order.desc("insertedDate"));
								List list = isCrit.list();
								if(list.size()>0)
									return list;
								else
									return null;
							}
						});
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}		
	}
	
	@Override
	public boolean deleteSchoolSerchHistoryDocById(java.lang.Long id) throws Exception {
		try {
			SchoolSearchHistory history = 	searchHistorySchoolFindById(id);
			System.out.println(history+"history");
			if (history != null) {
				return deleteSchoolSerchHistoryDoc(history);
			}
		} catch (Exception re) {
			re.printStackTrace();
			return false;
		}
		return false;
	}
	
	@Override
	public boolean deleteSchoolSerchHistoryDoc(SchoolSearchHistory persistentInstance) throws Exception {
		try {
			getHibernateTemplate().delete(persistentInstance);
			return true;
		} catch (Exception re) {
			re.printStackTrace();
			return false;
		}
	}
	
	@Override
	public SchoolSearchHistory searchHistorySchoolFindById(java.lang.Long id) throws Exception {
		try {
			SchoolSearchHistory instance = (SchoolSearchHistory) getHibernateTemplate().get("com.schooltrix.hibernate.SchoolSearchHistory", id);
			return instance;
		} catch (Exception re) {
			throw re;
		}
	}
	
	@Override	
	public List searchSchoolHistoryLoadByIDDisplay(final String querySt )throws Exception{
		List assignList=null;
		try {
			assignList = (List) getHibernateTemplate().execute(
					new HibernateCallback() {
						public Object doInHibernate(Session session)throws HibernateException, SQLException {				
							//System.out.println("##"+querySt);
							StringType st = new StringType();
							SQLQuery sqlqu = session.createSQLQuery(querySt);	
							
							sqlqu.addScalar("SM_ID", st);
							sqlqu.addScalar("IM_ID", st);
							sqlqu.addScalar("School_Name",st );
							sqlqu.addScalar("Name", st);
							sqlqu.addScalar("Address",st );
							sqlqu.addScalar("City",st);
							sqlqu.addScalar("State_ID", st);

							sqlqu.addScalar("Contact_Person", st);//
							sqlqu.addScalar("Email_ID", st);//
							sqlqu.addScalar("Mobile", st);//
							sqlqu.addScalar("Landline", st);//
							sqlqu.addScalar("Active", st);// 
							
							System.out.println("in SIZE"+sqlqu.list().size());
							return sqlqu.list();
						}
					});
		} catch (Exception ex_) {
			ex_.printStackTrace();
			return assignList;
		}
		return assignList;
	}
	
	public String multiTransForSchoolEdit(SchoolMaster sm)throws Exception{
		Transaction tx = null;
		Session session = null;
		try {
			session = this.getSession();
			 tx = session.beginTransaction();
			 
			 SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			final long schoolID = sm.getSmId();
			SchoolMaster master = findById(schoolID);
			/**Long smId, Long imId, String schoolName,
			String name, String address, String city, Long stateId,
			String contactPerson, String emailId, String mobile,
			String landline, String active, Timestamp editedDate) 
			**/
			SchoolMasterHistory history = new SchoolMasterHistory(master.getSmId(),master.getImId(),master.getSchoolName(),master.getName(),master.getAddress(),
					master.getCity(),master.getStateId(),master.getContactPerson(),master.getEmailId(),master.getMobile(),master.getLandline(),master.getActive(),"edit",java.sql.Timestamp.valueOf(sdf.format(new Date())));
			history.setSmId(schoolID);
			session.save(history);
			session.update(sm);
			
			session.flush();
			tx.commit();
			return "success";
		}catch (Exception e) {
			System.out.println("before rollback::"+tx);
			tx.rollback();
			e.printStackTrace();
		}finally{
			System.out.println("realsess:::>"+session);
			try {
				releaseSession(session);
				System.out.println("afetr removeing session::"+session);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return null;
		
	}
	
	public String multiTrancationSchoolDisable( String sd_id,String action,String isActive)throws Exception{
		Transaction tx = null;
		Session session = null;
		try {
			session = this.getSession();
			 tx = session.beginTransaction();
			 final long schoolID = Long.parseLong(sd_id);
			 
			 SchoolMaster master = findById(schoolID);
			 
			 SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

			 SchoolMasterHistory history = new SchoolMasterHistory(master.getSmId(),master.getImId(),master.getSchoolName(),master.getName(),master.getAddress(),
						master.getCity(),master.getStateId(),master.getContactPerson(),master.getEmailId(),master.getMobile(),master.getLandline(),master.getActive(),action,java.sql.Timestamp.valueOf(sdf.format(new Date())));
			//	history.setSmId(schoolID);
				System.out.println(tx+"111->"+session);
				session.save(history);
				
				master.setActive(isActive);
				session.update(master);
		
					session.flush();
					tx.commit();
					session.close();
					System.out.println("commiteeed");
			 
			return "success";
		}catch (Exception e) {
			//System.out.println(tx.wasRolledBack()+"**99y999**->"+tx.wasCommitted()+"******"+session);
					tx.rollback();
					System.out.println(tx.wasRolledBack()+"**EEEEyEE**->"+tx.wasCommitted()+"******"+session);
					session.close();
			e.printStackTrace();
		}
		return null;
	}

	//this is for backup..ravi sir said..why we disable all flow ...only diable school is enough...so let see...where is the effect...........
	public String multiTrancationSchoolDisableBkup(Session session1 , String sd_id,String action,String isActive,int txFlag)throws Exception{
		Transaction tx = null;
		Session session = null;
		if (session1 == null) {
			session = this.getSession();
		}else{
			session = session1;
		}
		try {
			if (txFlag == 1) {
				tx = session.beginTransaction();
			}
			 final long schoolID = Long.parseLong(sd_id);
			 SchoolMaster master = findById(schoolID);
			 
			 SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

			 SchoolMasterHistory history = new SchoolMasterHistory(master.getSmId(),master.getImId(),master.getSchoolName(),master.getName(),master.getAddress(),
						master.getCity(),master.getStateId(),master.getContactPerson(),master.getEmailId(),master.getMobile(),master.getLandline(),master.getActive(),action,java.sql.Timestamp.valueOf(sdf.format(new Date())));
			//	history.setSmId(schoolID);
				System.out.println(tx+"111->"+session);
				session.save(history);
				
				master.setActive("N");
				session.update(master);
				System.out.println(tx.isActive()+"222->"+session);
				String branchRes="";
			//branchRes =  new BranchMasterDAOImpl().branchDisableMultiTransaction(session,sd_id,action,isActive,0);//txFlag -0 bcz after returning form branchDAOImpl ...we need to comited
			/*this's not that much correct solution i think...2013-07-14..calling one daoimpl in another dao impl....further we can implrove ..using any factory or with managers..**/ 
			
		
			 System.out.println(branchRes+"**in SChollDAO**"+sd_id+"&&&"+action+"&&&"+isActive+"&&&&"+txFlag);
			 System.out.println(tx.wasRolledBack()+"333->"+tx.wasCommitted()+"******"+session);
			
				String testRoll = null;
				if (testRoll.equalsIgnoreCase("")) {
					System.out.println("in test roll");
				}
			 
			 if(branchRes == null && txFlag == 1){
					 System.out.println("in roll back-for branch");
						tx.rollback();
						session.close();
						System.out.println("444->"+session);
						return null;
				 }
			 
			 if (txFlag == 1 && branchRes != null) {
					session.flush();
					tx.commit();
					session.close();
					System.out.println("commiteeed");
				}
	
			 
			return "success";
		}catch (RuntimeException e) {
			System.out.println(tx.wasRolledBack()+"**99999**->"+tx.wasCommitted()+"******"+session);
			System.out.println("in exp main");
			 if (txFlag == 1) {
				 System.out.println("555->"+session);
					tx.rollback();
					System.out.println(tx.wasRolledBack()+"**EEEEEE**->"+tx.wasCommitted()+"******"+session);
					session.close();
					System.out.println("666->"+session);
				}
			e.printStackTrace();
		}catch (Exception e) {
			System.out.println(tx.wasRolledBack()+"**99y999**->"+tx.wasCommitted()+"******"+session);
			System.out.println("in expy main");
			 if (txFlag == 1) {
				 System.out.println("55y5->"+session);
					tx.rollback();
					System.out.println(tx.wasRolledBack()+"**EEEEyEE**->"+tx.wasCommitted()+"******"+session);
					session.close();
					System.out.println("6y66->"+session);
				}
			e.printStackTrace();
		}
		return null;
	}	


}