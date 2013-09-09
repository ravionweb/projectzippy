package com.schooltrix.daos;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.LockMode;
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
import com.schooltrix.hibernate.ParentDetails;
import com.schooltrix.hibernate.ParentDetails;
import com.schooltrix.hibernate.ParentDetailsHistory;
import com.schooltrix.hibernate.ParentSearchHistory;
import com.schooltrix.hibernate.StudentDetails;
import com.schooltrix.hibernate.StudentDetailsHistory;
import com.schooltrix.hibernate.StudentSearchHistory;

public class ParentDetailsDAOImpl extends STHibernateDAOSupport implements ParentDetailsDAO{
	
	@Override
	public boolean save(ParentDetails transientInstance) throws Exception {
		
			getHibernateTemplate().saveOrUpdate(transientInstance);
			System.out.println("in saveee");
			return true;
		
	}
	@Override
	public boolean update(ParentDetails transientInstance) throws Exception {
		try {
			getHibernateTemplate().update(transientInstance);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	@Override
	public boolean delete(ParentDetails persistentInstance) throws Exception {
		try {
			getHibernateTemplate().delete(persistentInstance);
			return true;
		} catch (Exception re) {
			re.printStackTrace();
			return false;
		}
	}
	@Override
	public ParentDetails findByProperty(final String filed,final String value) throws Exception {
		try {
			return (ParentDetails) getHibernateTemplate().execute(
					new HibernateCallback() {
						public Object doInHibernate(Session session)
								throws HibernateException, SQLException{							
							Criteria parentCrit = session.createCriteria(ParentDetails.class);
							parentCrit.add(Restrictions.eq(filed, value));
							List list = parentCrit.list();
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
	public ParentDetails findByProperty(final String filed,final Long value) throws Exception {
		try {
			return (ParentDetails) getHibernateTemplate().execute(
					new HibernateCallback() {
						public Object doInHibernate(Session session)
								throws HibernateException, SQLException{							
							Criteria parentCrit = session.createCriteria(ParentDetails.class);
							parentCrit.add(Restrictions.eq(filed, value));
							List list = parentCrit.list();
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
		List ParentDetailsList=null;
		try {
			ParentDetailsList = (List) getHibernateTemplate().execute(
					new HibernateCallback() {
						public Object doInHibernate(Session session)throws HibernateException, SQLException {
							Criteria crit = session.createCriteria(ParentDetails.class);
							crit.add(Restrictions.eq(filed, value));
							//crit.addOrder(Order.desc("sno"));
							return crit.list();
						}
					});
		} catch (Exception ex_) {
			ex_.printStackTrace();
			return null;
		}
		return ParentDetailsList;
	}
	@Override
	public List findByPropertyListLong(final String filed,final Long value) throws Exception {
		List ParentDetailsList=null;
		try {
			ParentDetailsList = (List) getHibernateTemplate().execute(
					new HibernateCallback() {
						public Object doInHibernate(Session session)throws HibernateException, SQLException {
							Criteria crit = session.createCriteria(ParentDetails.class);
							crit.add(Restrictions.eq(filed, value));
							crit.addOrder(Order.asc("ptmId"));
							//System.out.println(crit.list().size()+"in findByPropertyListLong");
							return crit.list();
						}
					});
		} catch (Exception ex_) {
			ex_.printStackTrace();
			return null;
		}
		return ParentDetailsList;
	}
	
	//from AUTOS a where a.model in (select m.model from MODELS m)
	
	
	public List emailCheck(String email,Long im_id) throws Exception {
		try {
			String queryString = "from UserMaster um where um.umId in (select pd.umId from ParentDetails pd where pd.email ='"+email+"' ) and um.imId="+im_id;
			System.out.println("in emailcheck of ParentDetailsDAOIMPl");
			return getHibernateTemplate().find(queryString);
		} catch (Exception re) {
			re.printStackTrace();
			return null;
		}
	}
	
	
	@Override
	public ParentDetails findById(java.lang.Long id) throws Exception {
		try {
			ParentDetails instance = (ParentDetails) getHibernateTemplate().get("com.schooltrix.hibernate.ParentDetails", id);
			return instance;
		} catch (Exception re) {
			throw re;
		}
	}

	@Override
	public List findAll() throws Exception {
		try {
			String queryString = "from ParentDetails";
			return getHibernateTemplate().find(queryString);
		} catch (Exception re) {
			re.printStackTrace();
			return null;
		}
	}
	
	//2013-08-11
	@Override
	public List getParentForEdit(final String im_id,final String schools,final  String branches,
			final String classes, final String sections, final String fname, final String lname,
			final String dob, final String email, final String mobile, final String landline,
			final String addr1, final String addr2, final String city, final String state,
			final String gender, final String admissionNumber, final String dateQString,
			final String classAdmittedIn,final String active,final String parentTypeSel,final String isDefault){
		
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
		
		if (classes.replace("'", "").length()>1) {//this logic for identifying %
			cmIDOpF = "in";			
		}else {
			cmIDOpF = "like";			
		}
		if (classAdmittedIn.replace("'", "").length()>1) {//this logic for identifying %
			cmAdmIDOpF = "in";			
		}else {
			cmAdmIDOpF = "like";			
		}
		
		if (sections.replace("'", "").length()>1) {//this logic for identifying %
			secIDOpF = "in";			
		}else {
			secIDOpF = "like";			
		}

		final String smIDOp = smIDOpF;
		final String bmIDOp = bmIDOpF;
		final String cmIDOp = cmIDOpF;
		final String cmAdmIDOp = cmAdmIDOpF;
		final String secIDOp = secIDOpF;
		
		try {

			return (List) getHibernateTemplate().execute(
					new HibernateCallback() {
						public Object doInHibernate(Session session)throws HibernateException, SQLException {
							//14+10=24 fields
							String qu = "select pd.PD_ID as pdID,sd.Stu_ID as stuID,pd.First_Name as firstName,pd.Last_Name as lastName,pd.Gender as gender,pd.Address1 as addr1,pd.Address2 as addr2,pd.City as city,pd.State as state,pd.DOB as dob,pd.Photo as photo,sd.Admission_Number as admissionNumber,sd.Admission_Date as admissionDate,sd.Class_Admitted_in as classAdmittedIN," +
									" pd.Active as active, ptm.Type as parentType,pd.isDefault as isDefault," +
									" sm.SeM_ID as secID ,sm.Section_Name as secName,cm.CM_ID as cmID,cm.Class_Name as className,bm.BM_ID as bmID ,bm.Branch_Name as branchName,bm.Short_Name as bShortName,schoolm.SM_ID as smID,schoolm.School_Name as schoolName,	schoolm.Name as sShortName,sd.First_Name as sfirstName,sd.Last_Name  as slastName,pd.Email as email,pd.Mobile as mobile " +
									" from parent_details pd inner join parent_student_map psm on pd.PD_ID = psm.PD_ID inner join student_details sd on psm.Stu_ID = sd.Stu_ID inner join student_section_map ssm on sd.Stu_ID=ssm.Stu_ID inner join section_class_map scm on ssm.SCM_ID=scm.SCM_ID " +
									" inner join section_master sm on scm.SeM_ID= sm.SeM_ID inner join class_master cm on scm.CM_ID=cm.CM_ID inner join branch_master bm on ssm.bm_id=bm.bm_id inner join school_master schoolm on ssm.sm_id=schoolm.sm_id" +
									" inner join parent_type_master ptm on pd.PTM_ID= ptm.PTM_ID "+
									" where scm.SeM_ID "+secIDOp+" ("+sections+") and scm.CM_ID "+cmIDOp+" ("+classes+")  and ssm.BM_ID  "+bmIDOp+" ("+branches+")  and ssm.SM_ID "+smIDOp+" ("+schools+") and ssm.IM_ID in ("+im_id+") and  " +
									" pd.First_Name	 like '"+fname+"' and pd.Last_Name like '"+lname+"' and pd.Gender	 like  '"+gender+"' and pd.Address1	 like  '"+addr1+"' and pd.Address2	 like  '"+addr2+"' " +
									" and pd.City like  '"+city+"' and pd.State	 like  '"+state+"' and pd.Email	 like  '"+email+"' and pd.Mobile	 like  '"+mobile+"' and pd.Landline	 like  '"+landline+"' and pd.DOB	 like  '"+dob+"'  " +
									" and sd.Admission_Number	 like  '"+admissionNumber+"'  and  sd.Admission_Date	between "+dateQString+"  and sd.Class_Admitted_in  "+cmAdmIDOp+" ("+classAdmittedIn+")  and pd.isDefault like '"+isDefault+"' and pd.PTM_ID like '"+parentTypeSel+"' and  pd.Active like  '"+active+"'  " +
									"and sm.active='y' and cm.active='y'  and bm.active='y'  and schoolm.active='y'   order by ssm.SM_ID desc";							
							
							//System.out.println("##-->"+qu);
							
							StringType st = new StringType();
							SQLQuery sqlqu = session.createSQLQuery(qu);	
							
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
								Criteria isCrit = session.createCriteria(ParentSearchHistory.class);
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
	public String saveParentSearchHistory(ParentSearchHistory querySearchHis) throws Exception{
		String re = null;
		try {
			final String qust = querySearchHis.getQueryString();
				re =  (String) getHibernateTemplate().execute(
						new HibernateCallback() {
							public Object doInHibernate(Session session)
									throws HibernateException, SQLException{							
								Criteria isCrit = session.createCriteria(ParentSearchHistory.class);
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
			System.out.println(querySearchHis.getPshId()+"**IDDDDD");
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
								Criteria isCrit = session.createCriteria(ParentSearchHistory.class);
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
	public String multiTransEditParentSave(ParentDetails parentDetails){

		Transaction tx = null;
		Session session = null;
		try {
			session = this.getSession();
			 tx = session.beginTransaction();
			 List uDCBranchMap = null;
			 SimpleDateFormat sdf =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			final long pdID = parentDetails.getPdId();

			ParentDetails parentDetailsOLD = findById(pdID);
			
			ParentDetailsHistory parentDetailsHistory= new ParentDetailsHistory(parentDetailsOLD.getPdId(),
					parentDetailsOLD.getUmId(),parentDetailsOLD.getFirstName(),parentDetailsOLD.getLastName(),parentDetailsOLD.getGender(),
					parentDetailsOLD.getAddress1() ,parentDetailsOLD.getAddress2() ,parentDetailsOLD.getCity(),parentDetailsOLD.getState() ,parentDetailsOLD.getEmail() ,
					parentDetailsOLD.getMobile() ,parentDetailsOLD.getLandline() ,parentDetailsOLD.getDob() ,parentDetailsOLD.getPhoto() ,parentDetailsOLD.getPtmId() ,
					parentDetailsOLD.getIsDefault(),parentDetailsOLD.getActive() ,"edit" ,java.sql.Timestamp.valueOf(sdf.format(new Date())));
			
			//studentDetailsHistory.setStuHId(pdID);
			
			session.save(parentDetailsHistory);
			parentDetails.setUmId(parentDetailsOLD.getUmId());
			parentDetails.setActive(parentDetailsOLD.getActive());//no change for eidt
			
			session.update(parentDetails);
				

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
	
	@Override
	public boolean deleteParentSerchHistoryById(java.lang.Long id) throws Exception {
		try {
			ParentSearchHistory history = 	searchHistoryStudentFindById(id);
			System.out.println(history+"history");
			if (history != null) {
				return deleteParentSerchHistory(history);
			}
		} catch (Exception re) {
			re.printStackTrace();
			return false;
		}
		return false;
	}
	@Override
	public ParentSearchHistory searchHistoryStudentFindById(java.lang.Long id) throws Exception {
		try {
			ParentSearchHistory instance = (ParentSearchHistory) getHibernateTemplate().get("com.schooltrix.hibernate.ParentSearchHistory", id);
			return instance;
		} catch (Exception re) {
			throw re;
		}
	}
	
	@Override
	public boolean deleteParentSerchHistory(ParentSearchHistory persistentInstance) throws Exception {
		try {
			getHibernateTemplate().delete(persistentInstance);
			return true;
		} catch (Exception re) {
			re.printStackTrace();
			return false;
		}
	}
	
	
	@Override	
	public List searchParentHistoryLoadByIDDisplay(final String querySt )throws Exception{
		List assignList=null;
		try {
			assignList = (List) getHibernateTemplate().execute(
					new HibernateCallback() {
						public Object doInHibernate(Session session)throws HibernateException, SQLException {				
							//System.out.println("##"+querySt);
							StringType st = new StringType();
							SQLQuery sqlqu = session.createSQLQuery(querySt);	
							
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
	
	
	
}