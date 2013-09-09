package com.schooltrix.daos;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.hibernate.type.StringType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.schooltrix.hibernate.ParentDetails;
import com.schooltrix.hibernate.ParentDetailsHistory;
import com.schooltrix.hibernate.ParentSearchHistory;
import com.schooltrix.hibernate.ParentStudentMap;
import com.schooltrix.hibernate.StaffDetails;
import com.schooltrix.hibernate.StaffDetails;
import com.schooltrix.hibernate.StaffDetailsHistory;
import com.schooltrix.hibernate.StaffSearchHistory;
import com.schooltrix.hibernate.StudentDetails;
import com.schooltrix.hibernate.StudentDetailsHistory;

public class StaffDetailsDAOImpl extends STHibernateDAOSupport implements StaffDetailsDAO{
	
	@Override
	public boolean save(StaffDetails transientInstance) throws Exception {
		
			getHibernateTemplate().saveOrUpdate(transientInstance);
			System.out.println("in saveee");
			return true;
		
	}
	@Override
	public boolean update(StaffDetails transientInstance) throws Exception {
		try {
			getHibernateTemplate().update(transientInstance);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	@Override
	public boolean delete(StaffDetails persistentInstance) throws Exception {
		try {
			getHibernateTemplate().delete(persistentInstance);
			return true;
		} catch (Exception re) {
			re.printStackTrace();
			return false;
		}
	}
	@Override
	public StaffDetails findByProperty(final String filed,final Long value) throws Exception {
		try {
			System.out.println("value-------"+value);
			return (StaffDetails) getHibernateTemplate().execute(
					new HibernateCallback() {
						public Object doInHibernate(Session session)
								throws HibernateException, SQLException{							
							Criteria isExpiredCrit = session.createCriteria(StaffDetails.class);
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
	public StaffDetails findByProperty(final String filed,final String value) throws Exception {
		try {
			System.out.println("value-------"+value);
			return (StaffDetails) getHibernateTemplate().execute(
					new HibernateCallback() {
						public Object doInHibernate(Session session)
								throws HibernateException, SQLException{							
							Criteria isExpiredCrit = session.createCriteria(StaffDetails.class);
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
		List StaffDetailsList=null;
		try {
			StaffDetailsList = (List) getHibernateTemplate().execute(
					new HibernateCallback() {
						public Object doInHibernate(Session session)throws HibernateException, SQLException {
							Criteria crit = session.createCriteria(StaffDetails.class);
							crit.add(Restrictions.eq(filed, value));
							//crit.addOrder(Order.desc("sno"));
							return crit.list();
						}
					});
		} catch (Exception ex_) {
			ex_.printStackTrace();
			return null;
		}
		return StaffDetailsList;
	}
	
	public List emailCheck(String email,Long im_id) throws Exception {
		try {
			String queryString = "from UserMaster um where um.umId in (select sd.umId from StaffDetails sd where sd.email ='"+email+"' ) and um.imId="+im_id;
			System.out.println("in emailcheck of StaffDetailsDAOIMPl");
			return getHibernateTemplate().find(queryString);
		} catch (Exception re) {
			re.printStackTrace();
			return null;
		}
	}
	
	
	@Override
	public StaffDetails findById(java.lang.Long id) throws Exception {
		try {
			StaffDetails instance = (StaffDetails) getHibernateTemplate().get("com.schooltrix.hibernate.StaffDetails", id);
			return instance;
		} catch (Exception re) {
			throw re;
		}
	}

	@Override
	public List findAll() throws Exception {
		try {
			String queryString = "from StaffDetails";
			return getHibernateTemplate().find(queryString);
		} catch (Exception re) {
			re.printStackTrace();
			return null;
		}
	}
	
	public List getStaffForEdit(final String im_id,final String schools,final  String branches,
			final String fname, final String lname,
			final String dob, final String email, final String mobile, final String landline,
			final String addr1, final String addr2, final String city, final String state,
			final String gender,final String desig, final String admissionNumber, final String dateQString,
			final String isParentCheck,final String parentTypeSel,final String active,final String isDefault){
	
		String smIDOpF = null;
		String bmIDOpF = null;
		String cmIDOpF = null;
		String cmAdmIDOpF = null;
		String secIDOpF = null;		
		
		if (schools.replace("'", "").length()>1) {//this logic for identifying %
			smIDOpF = "in";			
		}else {
			smIDOpF = "like";			
		}
		
		if (branches.replace("'", "").length()>1) {//this logic for identifying %
			bmIDOpF = "in";			
		}else {
			bmIDOpF = "like";			
		}
		

		final String smIDOp = smIDOpF;
		final String bmIDOp = bmIDOpF;
		
		try {

			return (List) getHibernateTemplate().execute(
					new HibernateCallback() {
						public Object doInHibernate(Session session)throws HibernateException, SQLException {
							//14+10=24 fields
							SQLQuery sqlqu = null;
							StringType st = new StringType();
							String qu = null;
							
							if (isParentCheck.equalsIgnoreCase("Y")) {
								qu = "select pd.PD_ID as pdID,sd.Stu_ID as stuID,pd.First_Name as firstName,pd.Last_Name as lastName,pd.Gender as gender,pd.Address1 as addr1,pd.Address2 as addr2,pd.City as city,pd.State as state,pd.DOB as dob,pd.Photo as photo,sd.Admission_Number as admissionNumber,sd.Admission_Date as admissionDate,sd.Class_Admitted_in as classAdmittedIN," +
									" pd.Active as active, ptm.Type as parentType,pd.isDefault as isDefault," +
									" sm.SeM_ID as secID ,sm.Section_Name as secName,cm.CM_ID as cmID,cm.Class_Name as className,bm.BM_ID as bmID ,bm.Branch_Name as branchName,bm.Short_Name as bShortName,schoolm.SM_ID as smID,schoolm.School_Name as schoolName,	schoolm.Name as sShortName,sd.First_Name as sfirstName,sd.Last_Name  as slastName,pd.Email as email,pd.Mobile as mobile ," +
									" staffD.Designation as desig ,staffD.SD_ID as staffID " +
									" from parent_details pd  inner join staff_details staffD on pd.UM_ID = staffD.UM_ID " +
									"inner join parent_student_map psm on pd.PD_ID = psm.PD_ID inner join student_details sd on psm.Stu_ID = sd.Stu_ID inner join student_section_map ssm on sd.Stu_ID=ssm.Stu_ID inner join section_class_map scm on ssm.SCM_ID=scm.SCM_ID " +
									" inner join section_master sm on scm.SeM_ID= sm.SeM_ID inner join class_master cm on scm.CM_ID=cm.CM_ID inner join branch_master bm on ssm.bm_id=bm.bm_id inner join school_master schoolm on ssm.sm_id=schoolm.sm_id" +
									" inner join parent_type_master ptm on pd.PTM_ID= ptm.PTM_ID "+
									" where ssm.BM_ID  "+bmIDOp+" ("+branches+")  and ssm.SM_ID "+smIDOp+" ("+schools+") and ssm.IM_ID in ("+im_id+") and  " +
									" pd.First_Name	 like '"+fname+"' and pd.Last_Name like '"+lname+"' and pd.Gender	 like  '"+gender+"' and pd.Address1	 like  '"+addr1+"' and pd.Address2	 like  '"+addr2+"' " +
									" and pd.City like  '"+city+"' and pd.State	 like  '"+state+"' and pd.Email	 like  '"+email+"' and pd.Mobile	 like  '"+mobile+"' and pd.Landline	 like  '"+landline+"' and pd.DOB	 like  '"+dob+"'  " +
									" and sd.Admission_Number	 like  '"+admissionNumber+"'  and  sd.Admission_Date	between "+dateQString+"  and staffD.Designation like '"+desig+"'  and pd.isDefault like '"+isDefault+"' and pd.PTM_ID like '"+parentTypeSel+"' and  pd.Active like  '"+active+"'  " +
									"and sm.active='y' and cm.active='y'  and bm.active='y'  and schoolm.active='y'   order by ssm.SM_ID desc";							
							
							System.out.println("##-->"+qu);
							sqlqu = session.createSQLQuery(qu);	
							
							
							sqlqu.addScalar("pdID", st);
							sqlqu.addScalar("stuID", st);
							sqlqu.addScalar("firstName", st);
							sqlqu.addScalar("lastName", st);
							sqlqu.addScalar("gender", st);
							sqlqu.addScalar("addr1", st);
							sqlqu.addScalar("addr2",st );
							sqlqu.addScalar("city",st);
							sqlqu.addScalar("state", st);
							sqlqu.addScalar("dob", st);

							sqlqu.addScalar("photo", st);//
							sqlqu.addScalar("admissionNumber", st);//
							sqlqu.addScalar("admissionDate", st);//
							sqlqu.addScalar("classAdmittedIN", st);//
							sqlqu.addScalar("active", st);// 
							
							sqlqu.addScalar("parentType", st);// 
							sqlqu.addScalar("isDefault", st);// 
							
							sqlqu.addScalar("secID", st);// 
							sqlqu.addScalar("secName", st);// 
							sqlqu.addScalar("cmID", st);// 
							sqlqu.addScalar("className", st);// 
							sqlqu.addScalar("bmID", st);// 
							sqlqu.addScalar("branchName", st);// 
							sqlqu.addScalar("bShortName", st);// 
							sqlqu.addScalar("smID", st);// 
							sqlqu.addScalar("schoolName", st);// 
							sqlqu.addScalar("sShortName", st);// active here re

							sqlqu.addScalar("sfirstName", st);// 
							sqlqu.addScalar("slastName", st);// 
							sqlqu.addScalar("email", st);// 
							sqlqu.addScalar("mobile", st);// 	
							sqlqu.addScalar("desig", st);//new for staff
							sqlqu.addScalar("staffID", st);//new for staff
							
							System.out.println("in Staff IFF----"+sqlqu.list().size());
							}else if (isParentCheck.equalsIgnoreCase("N")) {
								qu = "select bm.bm_ID as bm_ID,bm.IM_ID as IM_ID,sm.School_Name as School_Name,bm.Branch_Name as Branch_Name,bm.Short_Name as Short_Name," +
										"sd.SD_ID as sdID, sd.UM_ID as umID,sd.First_Name as fName,sd.Last_Name as lName,sd.Designation as desig,sd.city as city,sd. Mobile as mobile ,sd.Email as email ,sd.Photo as photo " +
										"from staff_details sd inner join user_master um on sd.UM_ID=um.UM_ID inner join branch_master bm on um.bm_id = bm.bm_id  inner join  school_master sm on bm.SM_ID = sm.sm_id  " +
										"where um.BM_ID  "+bmIDOp+" ("+branches+")  and um.SM_ID "+smIDOp+" ("+schools+") and um.IM_ID in ("+im_id+") and  " +
										"sd.First_Name like '"+fname+"' and sd.Last_Name like '"+lname+"' and sd.Gender like '"+gender+"' and  sd.Address1 like '"+addr1+"'" +
										"and sd.Address2 like '"+addr2+"'  and sd.city like '"+city+"' and sd.state like '"+state+"' and sd.Designation like '"+desig+"' and sd.Email like '"+email+"' " +
												"and sd.Mobile like '"+mobile+"' and  sd.Landline like '"+landline+"' and sd.DOB like '"+dob+"' and sd.Active like '"+active+"' ";
								System.out.println("##-->"+qu);
								sqlqu = session.createSQLQuery(qu);	
								sqlqu.addScalar("bm_ID", st);
								sqlqu.addScalar("IM_ID", st);
								sqlqu.addScalar("School_Name", st);
								sqlqu.addScalar("Branch_Name", st);
								sqlqu.addScalar("Short_Name", st);

								sqlqu.addScalar("sdID", st);
								sqlqu.addScalar("umID", st);
								sqlqu.addScalar("fName", st);
								sqlqu.addScalar("lName", st);
								sqlqu.addScalar("desig", st);
								sqlqu.addScalar("city", st);
								sqlqu.addScalar("mobile", st);
								sqlqu.addScalar("email", st);
								sqlqu.addScalar("photo", st);
								System.out.println("in Staff ELse ---"+sqlqu.list().size());

							}
							
							
							
							
							return sqlqu.list();
						}
					});
		} catch (Exception e) {
		e.printStackTrace();
		}
	
		return null;
	
	}
	
	
	@Override
	public String saveStaffSearchHistory(StaffSearchHistory querySearchHis) throws Exception{
		String re = null;
		try {
			final String qust = querySearchHis.getQueryString();
				re =  (String) getHibernateTemplate().execute(
						new HibernateCallback() {
							public Object doInHibernate(Session session)
									throws HibernateException, SQLException{							
								Criteria isCrit = session.createCriteria(StaffSearchHistory.class);
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
			System.out.println(querySearchHis.getStahId()+"**IDDDDD");
			return "success";
		} catch (Exception e) {
			System.out.println("in first Excep*********");
			e.printStackTrace();
		}
		return "error";
	}
	
	@Override
	public String isDescThereDB(final String iM_ID,final String uM_ID,final String searchDesc) throws Exception{
		
		try {
			return (String) getHibernateTemplate().execute(
						new HibernateCallback() {
							public Object doInHibernate(Session session)
									throws HibernateException, SQLException{							
								Criteria isCrit = session.createCriteria(StaffSearchHistory.class);
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
	public List searchStaffHistoryLoadByIDDisplay(final String querySt )throws Exception{
		List assignList=null;
		try {
			assignList = (List) getHibernateTemplate().execute(
					new HibernateCallback() {
						public Object doInHibernate(Session session)throws HibernateException, SQLException {				
							//System.out.println("##"+querySt);
							StringType st = new StringType();
							SQLQuery sqlqu = session.createSQLQuery(querySt);	
							sqlqu.addScalar("bm_ID", st);
							sqlqu.addScalar("IM_ID", st);
							sqlqu.addScalar("School_Name", st);
							sqlqu.addScalar("Branch_Name", st);
							sqlqu.addScalar("Short_Name", st);

							sqlqu.addScalar("sdID", st);
							sqlqu.addScalar("umID", st);
							sqlqu.addScalar("fName", st);
							sqlqu.addScalar("lName", st);
							sqlqu.addScalar("desig", st);
							sqlqu.addScalar("city", st);
							sqlqu.addScalar("mobile", st);
							sqlqu.addScalar("email", st);
							sqlqu.addScalar("photo", st);
							
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
	
	@Override
	public List searchHistoryLoadDB(final String iM_ID, final  String uM_ID)throws Exception{
		try {
			return (List) getHibernateTemplate().execute(
						new HibernateCallback() {
							public Object doInHibernate(Session session)
									throws HibernateException, SQLException{							
								Criteria isCrit = session.createCriteria(StaffSearchHistory.class);
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
	public boolean deleteStaffSerchHistoryById(java.lang.Long id) throws Exception {
		try {
			StaffSearchHistory history = 	searchHistoryStaffFindById(id);
			System.out.println(history+"history");
			if (history != null) {
				return deleteStaffSerchHistory(history);
			}
		} catch (Exception re) {
			re.printStackTrace();
			return false;
		}
		return false;
	}
	
	@Override
	public StaffSearchHistory searchHistoryStaffFindById(java.lang.Long id) throws Exception {
		try {
			StaffSearchHistory instance = (StaffSearchHistory) getHibernateTemplate().get("com.schooltrix.hibernate.StaffSearchHistory", id);
			return instance;
		} catch (Exception re) {
			throw re;
		}
	}
	
	@Override
	public boolean deleteStaffSerchHistory(StaffSearchHistory persistentInstance) throws Exception {
		try {
			getHibernateTemplate().delete(persistentInstance);
			return true;
		} catch (Exception re) {
			re.printStackTrace();
			return false;
		}
	}
	
	public String multiTrancationStaffDisable(String sta_ID, String action,String isActive){

		Transaction tx 	= null;
		Session session = null;
		try {
				session 	= this.getSession();
				System.out.println(session.getTransaction()+"--trans already there--multiTrancationStaffDisable->"+session.getTransaction().isActive());
				//session.connection().setAutoCommit(false);
				tx 			= session.beginTransaction();
				System.out.println(session.getTransaction()+"--trans new there--multiTrancationStaffDisable->"+session.getTransaction().isActive());
				System.out.println(session.getFlushMode()+"*****multiTrancationStaffDisable******8");
				//try this...
		final long staID 								= Long.parseLong(sta_ID);
		StaffDetails staffDetailsOLD = findById(staID);
        SimpleDateFormat sdf 						= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        
        StaffDetailsHistory staffDetailsHistory= new StaffDetailsHistory(staffDetailsOLD.getSdId(),
						staffDetailsOLD.getUmId(),staffDetailsOLD.getFirstName(),staffDetailsOLD.getLastName(),staffDetailsOLD.getGender(),
						staffDetailsOLD.getAddress1() ,staffDetailsOLD.getAddress2() ,staffDetailsOLD.getCity(),staffDetailsOLD.getState() ,staffDetailsOLD.getDesignation(),
						staffDetailsOLD.getEmail() ,staffDetailsOLD.getMobile() ,staffDetailsOLD.getLandline() ,staffDetailsOLD.getDob() ,staffDetailsOLD.getPhoto() ,
						staffDetailsOLD.getActive() ,action ,java.sql.Timestamp.valueOf(sdf.format(new Date())));
				
				System.out.println(tx+"1staffDetailsHistory11->"+session);
				session.save(staffDetailsHistory);
				
				staffDetailsOLD.setActive(isActive);
				session.update(staffDetailsOLD);
				System.out.println("2222staffDetailsHistory2222->"+session);
				
			//	session.flush();
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
	
	
	@Override
	public String multiTransEditStaffSave(StaffDetails staffDetails){

		Transaction tx = null;
		Session session = null;
		try {
			session = this.getSession();
			 tx = session.beginTransaction();
			 List uDCBranchMap = null;
			 SimpleDateFormat sdf =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			final long sdID = staffDetails.getSdId();

			StaffDetails staffDetailsOLD = findById(sdID);
			
			StaffDetailsHistory staffDetailsHistory= new StaffDetailsHistory(staffDetailsOLD.getSdId(),
					staffDetailsOLD.getUmId(),staffDetailsOLD.getFirstName(),staffDetailsOLD.getLastName(),staffDetailsOLD.getGender(),
					staffDetailsOLD.getAddress1() ,staffDetailsOLD.getAddress2() ,staffDetailsOLD.getCity(),staffDetailsOLD.getState() ,staffDetailsOLD.getDesignation(),
					staffDetailsOLD.getEmail() ,staffDetailsOLD.getMobile() ,staffDetailsOLD.getLandline() ,staffDetailsOLD.getDob() ,staffDetailsOLD.getPhoto() ,
					staffDetailsOLD.getActive() ,"edit" ,java.sql.Timestamp.valueOf(sdf.format(new Date())));
			
			//studentDetailsHistory.setStuHId(pdID);
			
			session.save(staffDetailsHistory);
			staffDetails.setUmId(staffDetailsOLD.getUmId());
			staffDetails.setActive(staffDetailsOLD.getActive());//no change for eidt
			
			session.update(staffDetails);

				session.flush();
				tx.commit();
				return "success";
			} catch (Exception e) {
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
		return "fail";
	}
	
	
	
	
}