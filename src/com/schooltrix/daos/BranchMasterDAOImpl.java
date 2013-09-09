package com.schooltrix.daos;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.hibernate.type.StringType;
import org.springframework.orm.hibernate3.HibernateCallback;

import com.schooltrix.hibernate.BranchMaster;
import com.schooltrix.hibernate.BranchMasterHistory;
import com.schooltrix.hibernate.BranchSearchHistory;
import com.schooltrix.hibernate.BranchStaffMap;
import com.schooltrix.hibernate.SchoolMaster;
import com.schooltrix.hibernate.SchoolMasterHistory;
import com.schooltrix.hibernate.SchoolSearchHistory;

public class BranchMasterDAOImpl extends STHibernateDAOSupport implements BranchMasterDAO{
	
	@Override
	public boolean save(BranchMaster transientInstance) throws Exception {
		
			getHibernateTemplate().saveOrUpdate(transientInstance);
			System.out.println("in saveee");
			return true;
		
	}
	@Override
	public boolean update(BranchMaster transientInstance) {
		try {
			getHibernateTemplate().update(transientInstance);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	@Override
	public boolean delete(BranchMaster persistentInstance) {
		try {
			getHibernateTemplate().delete(persistentInstance);
			return true;
		} catch (Exception re) {
			re.printStackTrace();
			return false;
		}
	}
	@Override
	public BranchMaster findByProperty(final String filed,final String value) {
		try {
			return (BranchMaster) getHibernateTemplate().execute(
					new HibernateCallback() {
						public Object doInHibernate(Session session)
								throws HibernateException, SQLException{							
							Criteria isExpiredCrit = session.createCriteria(BranchMaster.class);
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
		List BranchMasterList=null;
		try {
			BranchMasterList = (List) getHibernateTemplate().execute(
					new HibernateCallback() {
						public Object doInHibernate(Session session)throws HibernateException, SQLException {
							Criteria crit = session.createCriteria(BranchMaster.class);
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
		return BranchMasterList;
	}
	
	@Override
	public List getBranchList(final String filed,final String value,final String filed1,final Long value1) {
		List branchMasterList=null;
		try {
			branchMasterList = (List) getHibernateTemplate().execute(
					new HibernateCallback() {
						public Object doInHibernate(Session session)throws HibernateException, SQLException {
							Criteria crit = session.createCriteria(BranchMaster.class);
							crit.add(Restrictions.eq(filed, value));
							crit.add(Restrictions.eq(filed1, value1));
							crit.add(Restrictions.eq("active", "Y"));
							//crit.addOrder(Order.desc("sno"));
							return crit.list();
						}
					});
		} catch (Exception ex_) {
			ex_.printStackTrace();
			return null;
		}
		return branchMasterList;
	}
	
	//i edited this methos ...get 3 more fileds....in EditBranch time required...
	@Override
	public List getMultiBranchList(final String im_id,final String inQuery){
		
		List branchMasterList=null;
		try {
			//System.out.println("in branchMasterList DAOIMPL");
			branchMasterList = (List) getHibernateTemplate().execute(
					new HibernateCallback() {
						public Object doInHibernate(Session session)throws HibernateException, SQLException {
						
							String qu= "select BM_ID,Branch_Name,Short_Name,City,State_ID from branch_master where IM_ID='"+im_id+"' and SM_ID in("+inQuery+") and Active='Y'";
							System.out.println("##"+qu);
							SQLQuery sqlqu = session.createSQLQuery(qu);							
							sqlqu.addScalar("BM_ID", new StringType());
							sqlqu.addScalar("Branch_Name", new StringType());
							sqlqu.addScalar("Short_Name", new StringType());
							sqlqu.addScalar("City", new StringType());
							sqlqu.addScalar("State_ID", new StringType());
							System.out.println("in SIZE"+sqlqu.list().size());
							return sqlqu.list();
						}
					});
		} catch (Exception ex_) {
			ex_.printStackTrace();
			return null;
		}
		return branchMasterList;
	}
	
	
	@Override
	public BranchMaster findById(java.lang.Long id) {
		try {
			BranchMaster instance = (BranchMaster) getHibernateTemplate().get("com.schooltrix.hibernate.BranchMaster", id);
			return instance;
		} catch (Exception re) {
			throw re;
		}
	}

	@Override
	public List findAll() {
		try {
			String queryString = "from BranchMaster";
			return getHibernateTemplate().find(queryString);
		} catch (Exception re) {
			re.printStackTrace();
			return null;
		}
	}
	
	@Override
	public List getBranchesForEdit(final String schhols,final String branches,final String citys,final  String states,final  String addr,final  String contactPerson,
			final  String mobile,final  String landline,final  String email,final  String active,final long im_id) throws Exception{
		
		List branchList=null;
		String smIDOpF = null;
		String bmIDOpF = null;
		
		String stIDOpF = null;
		String ctIDOpF = null;
		
	//	System.out.println(smIDs.length()+"666"+bmIDs.length()+"777"+cmIDs.length());
		if (schhols.replace("'", "").length()>1) {//this logic for identifying %
			smIDOpF = "in";			
		}else {
			smIDOpF = "like";			
		}
		
		if (branches.replace("'", "").length()>1) {//this logic for identifying %
			bmIDOpF = "in";			
		}else {
			bmIDOpF = "like";			
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
		final String bmIDOp = bmIDOpF;
		final String ctIDOp = ctIDOpF;
		final String stIDOp = stIDOpF;
			try {

				return (List) getHibernateTemplate().execute(
						new HibernateCallback() {
							public Object doInHibernate(Session session)throws HibernateException, SQLException {
								String qu =	"select bm.bm_ID as bm_ID,bm.IM_ID as IM_ID,sm.Name as School_Name,bm.Branch_Name as Branch_Name,bm.Short_Name as Short_Name,bm.Address as Address,bm.City as City," +
										"bm.State_ID as State_ID,stm.State_name as State_name,bm.Contact_Person as Contact_Person,bm.Email_ID as Email_ID,bm.Mobile as Mobile,bm.Landline as Landline,bm.Active as Active  " +
										" from branch_master bm inner join school_master sm on bm.sm_id = sm.sm_id  inner join state_master stm on bm.State_id=stm.state_id"+
										" where sm.SM_ID "+smIDOp+" ("+schhols+") and bm.BM_ID "+bmIDOp+" ("+branches+")  and bm.Address like '"+addr+"'  and bm.City "+ctIDOp+" ("+citys+")    and bm.State_ID "+stIDOp+" ("+states+")" +
												"  and bm.Contact_Person like '"+contactPerson+"'  and bm.Email_ID like '"+email+"'   and bm.Mobile like '"+mobile+"'   and bm.Landline like '"+landline+"'   " +
														"and bm.Active ='"+active+"' and bm.IM_ID ="+im_id+ " order by sm.sm_id desc";
				
					
								System.out.println("##-->"+qu);
								
								StringType st = new StringType();
								SQLQuery sqlqu = session.createSQLQuery(qu);	
								
								sqlqu.addScalar("bm_ID", st);
								sqlqu.addScalar("IM_ID", st);
								sqlqu.addScalar("School_Name", st);
								sqlqu.addScalar("Branch_Name", st);
								sqlqu.addScalar("Short_Name", st);
								sqlqu.addScalar("Address",st );
								sqlqu.addScalar("City",st);
								sqlqu.addScalar("State_ID", st);
								sqlqu.addScalar("State_name", st);

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
	
	@Override
	public String isDescThereDB(final String iM_ID,final String uM_ID,final String searchDesc) throws Exception{
		
		try {
			return (String) getHibernateTemplate().execute(
						new HibernateCallback() {
							public Object doInHibernate(Session session)
									throws HibernateException, SQLException{							
								Criteria isCrit = session.createCriteria(BranchSearchHistory.class);
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
	
	@Override
	public String saveBranchSearchHistory(BranchSearchHistory querySearchHis) throws Exception{
		String re = null;
		try {
			final String qust = querySearchHis.getQueryString();
				re =  (String) getHibernateTemplate().execute(
						new HibernateCallback() {
							public Object doInHibernate(Session session)
									throws HibernateException, SQLException{							
								Criteria isCrit = session.createCriteria(BranchSearchHistory.class);
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
			System.out.println(querySearchHis.getBshId()+"**IDDDDD");
			return "success";
		} catch (Exception e) {
			System.out.println("in first Excep*********");
			e.printStackTrace();
		}
		return "error";
	}
	@Override
	public List searchHistoryLoadDB(final String iM_ID, final  String uM_ID)throws Exception{
		try {
			return (List) getHibernateTemplate().execute(
						new HibernateCallback() {
							public Object doInHibernate(Session session)
									throws HibernateException, SQLException{							
								Criteria isCrit = session.createCriteria(BranchSearchHistory.class);
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
	public boolean deleteBranchSerchHistoryDocById(java.lang.Long id) throws Exception {
		try {
			BranchSearchHistory history = 	searchHistoryBranchFindById(id);
			System.out.println(history+"history");
			if (history != null) {
				return deleteBranchSerchHistoryDoc(history);
			}
		} catch (Exception re) {
			re.printStackTrace();
			return false;
		}
		return false;
	}
	
	@Override
	public boolean deleteBranchSerchHistoryDoc(BranchSearchHistory persistentInstance) throws Exception {
		try {
			getHibernateTemplate().delete(persistentInstance);
			return true;
		} catch (Exception re) {
			re.printStackTrace();
			return false;
		}
	}
	
	@Override
	public BranchSearchHistory searchHistoryBranchFindById(java.lang.Long id) throws Exception {
		try {
			BranchSearchHistory instance = (BranchSearchHistory) getHibernateTemplate().get("com.schooltrix.hibernate.BranchSearchHistory", id);
			return instance;
		} catch (Exception re) {
			throw re;
		}
	}
	
	@Override	
	public List searchBranchHistoryLoadByIDDisplay(final String querySt )throws Exception{
		List assignList=null;
		try {
			assignList = (List) getHibernateTemplate().execute(
					new HibernateCallback() {
						public Object doInHibernate(Session session)throws HibernateException, SQLException {				
							//System.out.println("##"+querySt);
							StringType st = new StringType();
							SQLQuery sqlqu = session.createSQLQuery(querySt);	
	// bm_ID, IM_ID, School_Name, Branch_Name, Short_Name, Address, City, State_ID, State_name,Contact_Person, Email_ID, Mobile, Landline, Active  
							
							sqlqu.addScalar("bm_ID", st);
							sqlqu.addScalar("IM_ID", st);
							sqlqu.addScalar("School_Name",st );
							sqlqu.addScalar("Branch_Name",st );
							sqlqu.addScalar("Short_Name", st);
							sqlqu.addScalar("Address",st );
							sqlqu.addScalar("City",st);
							sqlqu.addScalar("State_ID", st);
							sqlqu.addScalar("State_name", st);

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

	public String multiTransForBranchEdit(BranchMaster sm)throws Exception{
		Transaction tx = null;
		Session session = null;
		try {
			session = this.getSession();
			 tx = session.beginTransaction();
			 
			 SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			final long branchID = sm.getBmId();
			BranchMaster master = findById(branchID);
			/**Long smId, Long imId, String schoolName,
			String name, String address, String city, Long stateId,
			String contactPerson, String emailId, String mobile,
			String landline, String active, Timestamp editedDate) 
			**/
					
			BranchMasterHistory history = new BranchMasterHistory(master.getBmId(),master.getImId(),master.getSmId(),master.getBranchName(),master.getShortName(),master.getAddress(),
					master.getCity(),master.getStateId(),master.getContactPerson(),master.getEmailId(),master.getMobile(),master.getLandline(),master.getActive(),"edit",java.sql.Timestamp.valueOf(sdf.format(new Date())));
			history.setBmId(branchID);
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
	
	public String multiTrancationBranchDisable(String bD_ID, String action,String isActive)throws Exception{

		Transaction tx = null;
		Session session = null;
		try {
			session = this.getSession();
			 tx = session.beginTransaction();
			 final long branchID = Long.parseLong(bD_ID);
			 
			 BranchMaster master = findById(branchID);
			 
			 SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		/*	 (Long bmId, String imId, Long smId,
						String branchName, String shortName, String address, String city,
						Long stateId, String contactPerson, String emailId, String mobile,
						String landline, String active, String action, Timestamp editedDate)*/
			 
			 BranchMasterHistory history = new BranchMasterHistory(master.getBmId(),master.getImId(),master.getSmId(),master.getBranchName(),master.getShortName(),
					 master.getAddress(),master.getCity(),master.getStateId(),master.getContactPerson(),master.getEmailId(),master.getMobile(),master.getLandline(),master.getActive(),action,java.sql.Timestamp.valueOf(sdf.format(new Date())));
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
		
	public String branchDisableMultiTransactionBackup(Session session1,String sd_id,String action,String isActive,int txFlag)throws Exception {//txFlag ---1 -->branch level and 0-->above of  branch level like school ,Institution
			Transaction tx = null;
			Session session = null;
			System.out.println("session1 In Branchhhh"+session1);
			if (session1 == null) {
				session = this.getSession();
			}else{
				session = session1;
			}
			try {
				if (txFlag == 1) {
					tx = session.beginTransaction();
				}
				
				System.out.println(action+"**in branch master"+sd_id+"***"+isActive+"****"+txFlag+"&&&&"+tx);
				String hqlQueryString = "update BranchMaster set Active=:active where SM_ID=:sdID";//
				Query hqlQuery = session.createQuery(hqlQueryString);
					hqlQuery.setString("active", isActive);
					hqlQuery.setString("sdID", sd_id);
				int res =  hqlQuery.executeUpdate();
				System.out.println(res+"Branch res");
				
				//branch history and remaing opeartions...
				
				 System.out.println(session+"***SESIIIII Branch***"+session1);
				 if (txFlag == 1) {
						session.flush();
						tx.commit();
					}
				 
				 return "success";
			}catch (Exception e) {
				 if (txFlag == 1) {
						tx.rollback();
					}
				e.printStackTrace();
			}
			return null;
		}
	
	//	Branch Staff Map *********Start***********
	@Override
	public boolean save(BranchStaffMap transientInstance) throws Exception {
		
		getHibernateTemplate().saveOrUpdate(transientInstance);
		System.out.println("in saveee");
		return true;
	
	}
	@Override
	public boolean update(BranchStaffMap transientInstance) throws Exception {
		try {
			getHibernateTemplate().update(transientInstance);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	@Override
	public boolean delete(BranchStaffMap persistentInstance)  throws Exception{
		try {
			getHibernateTemplate().delete(persistentInstance);
			return true;
		} catch (Exception re) {
			re.printStackTrace();
			return false;
		}
	}
	@Override
	public BranchMaster findByPropertyBranchStaffMap(final String filed,final String value)  throws Exception{
		try {
			return (BranchMaster) getHibernateTemplate().execute(
					new HibernateCallback() {
						public Object doInHibernate(Session session)
								throws HibernateException, SQLException{							
							Criteria isExpiredCrit = session.createCriteria(BranchStaffMap.class);
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
	
	//	Branch Staff Map *********END***********
	
	

}