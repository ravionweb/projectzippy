package com.schooltrix.daos;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
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
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.schooltrix.hibernate.BranchMaster;
import com.schooltrix.hibernate.BranchMasterHistory;
import com.schooltrix.hibernate.BranchSearchHistory;
import com.schooltrix.hibernate.ParentDetails;
import com.schooltrix.hibernate.ParentDetailsHistory;
import com.schooltrix.hibernate.ParentStudentMap;
import com.schooltrix.hibernate.StudentDetails;
import com.schooltrix.hibernate.StudentDetails;
import com.schooltrix.hibernate.StudentDetails;
import com.schooltrix.hibernate.StudentDetailsHistory;
import com.schooltrix.hibernate.StudentSearchHistory;
import com.schooltrix.hibernate.StudentSectionMap;
import com.schooltrix.hibernate.StudentxlErrorTemp;
import com.schooltrix.hibernate.UploadDocument;
import com.schooltrix.hibernate.UploadDocumentClassBranchMap;
import com.schooltrix.hibernate.UploadDocumentClassBranchMapHistory;
import com.schooltrix.hibernate.UploadDocumentHistory;

public class StudentDetailsDAOImpl extends STHibernateDAOSupport implements StudentDetailsDAO{
	
	@Override
	public boolean save(StudentDetails transientInstance) throws Exception {
		
			getHibernateTemplate().saveOrUpdate(transientInstance);
			System.out.println("in saveee");
			return true;
		
	}
	@Override
	public boolean update(StudentDetails transientInstance) throws Exception {
		try {
			getHibernateTemplate().update(transientInstance);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	@Override
	public boolean delete(StudentDetails persistentInstance) throws Exception {
		try {
			getHibernateTemplate().delete(persistentInstance);
			return true;
		} catch (Exception re) {
			re.printStackTrace();
			return false;
		}
	}
	@Override
	public StudentDetails findByProperty(final String filed,final String value) throws Exception {
		try {
			return (StudentDetails) getHibernateTemplate().execute(
					new HibernateCallback() {
						public Object doInHibernate(Session session)
								throws HibernateException, SQLException{							
							Criteria isExpiredCrit = session.createCriteria(StudentDetails.class);
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
	public StudentDetails findByProperty(final String filed,final Long value) throws Exception {
		try {
			return (StudentDetails) getHibernateTemplate().execute(
					new HibernateCallback() {
						public Object doInHibernate(Session session)
								throws HibernateException, SQLException{							
							Criteria isExpiredCrit = session.createCriteria(StudentDetails.class);
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
		List StudentDetailsList=null;
		try {
			StudentDetailsList = (List) getHibernateTemplate().execute(
					new HibernateCallback() {
						public Object doInHibernate(Session session)throws HibernateException, SQLException {
							Criteria crit = session.createCriteria(StudentDetails.class);
							crit.add(Restrictions.eq(filed, value));
							//crit.addOrder(Order.desc("sno"));
							return crit.list();
						}
					});
		} catch (Exception ex_) {
			ex_.printStackTrace();
			return null;
		}
		return StudentDetailsList;
	}
	
	
	
	@Override
	public StudentDetails findById(java.lang.Long id) throws Exception {
		try {
			StudentDetails instance = (StudentDetails) getHibernateTemplate().get("com.schooltrix.hibernate.StudentDetails", id);
			return instance;
		} catch (Exception re) {
			throw re;
		}
	}

	@Override
	public List findAll() throws Exception {
		try {
			String queryString = "from StudentDetails";
			return getHibernateTemplate().find(queryString);
		} catch (Exception re) {
			re.printStackTrace();
			return null;
		}
	}
	@Override
	public boolean saveStudentParentMap(ParentStudentMap transientInstance)
			throws Exception {
		// TODO Auto-generated method stub

		getHibernateTemplate().save(transientInstance);
		System.out.println("in saveee");
		return false;
	}
	
	public ParentStudentMap getStudentParentMap(final String field,final String pd_id) throws Exception {

		try {
			return (ParentStudentMap) getHibernateTemplate().execute(
					new HibernateCallback() {
						public Object doInHibernate(Session session)
								throws HibernateException, SQLException{							
							Criteria isExpiredCrit = session.createCriteria(ParentStudentMap.class);
							isExpiredCrit.add(Restrictions.eq(field, pd_id));
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
	public boolean insertStudentSectionMap(StudentSectionMap ssm) {
		// TODO Auto-generated method stub
		getHibernateTemplate().save(ssm);
		System.out.println("in saveee");
		return true;
	}
	@Override
	public StudentSectionMap getStudentSectionMap(final String field,final String value) throws Exception{

		try {
			return (StudentSectionMap) getHibernateTemplate().execute(
					new HibernateCallback() {
						public Object doInHibernate(Session session)
								throws HibernateException, SQLException{							
							Criteria Crit = session.createCriteria(StudentSectionMap.class);
							Crit.add(Restrictions.eq(field, value));
							List list = Crit.list();
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
	public List getStudentSectionMapList(final String bmID,final String scmID) throws Exception{
		try {
			return (List) getHibernateTemplate().execute(
					new HibernateCallback() {
						public Object doInHibernate(Session session)
								throws HibernateException, SQLException{							
							Criteria Crit = session.createCriteria(StudentSectionMap.class);
							Crit.add(Restrictions.eq("bmId", bmID));
							Crit.add(Restrictions.eq("scmId", scmID));
							return Crit.list();
						}
					});
		} catch (Exception ex_) {
			ex_.printStackTrace();
			return null;
		}
	}
	
	@Override
	public boolean insertStudentErrorLog(StudentxlErrorTemp setemp) throws Exception{
		// TODO Auto-generated method stub
		getHibernateTemplate().saveOrUpdate(setemp);
		return true;
	}

	@Override
	public int deleteStudentErrorLog(final String um_id) throws Exception {

		
		Integer deletedData = (Integer)getHibernateTemplate().execute(new HibernateCallback () {
		    public Object doInHibernate(Session session) throws HibernateException, SQLException {
		        // delete the data
		    	Query hqlQuery = session.createQuery("DELETE FROM StudentxlErrorTemp WHERE umId=?");
		    	hqlQuery.setString(0, um_id);
		        int updated = hqlQuery.executeUpdate();
		        return updated;
		    }
		});
		
				return 0;
	}
	
	@Override
	public List getStudentErrorLog(final String um_id) throws Exception{
		// TODO Auto-generated method stub
	
		List StudentDetailsList=null;
		try {
			System.out.println("in getStudentErrorLog-----"+um_id);
			StudentDetailsList = (List) getHibernateTemplate().execute(
					new HibernateCallback() {
						public Object doInHibernate(Session session)throws HibernateException, SQLException {
							Criteria crit = session.createCriteria(StudentxlErrorTemp.class);
							crit.add(Restrictions.eq("umId", um_id));
							//crit.addOrder(Order.desc("sno"));
							System.out.println(crit.list().size()+"^^^^^^^^");
							return crit.list();
						}
					});
		} catch (Exception ex_) {
			ex_.printStackTrace();
			return null;
		}
		return StudentDetailsList;
	
	}
	@Override
	public List getStudentForEdit(final String im_id,final String schools,final  String branches,
			final String classes, final String sections, final String fname, final String lname,
			final String dob, final String email, final String mobile, final String landline,
			final String addr1, final String addr2, final String city, final String state,
			final String gender, final String admissionNumber, final String dateQString,
			final String classAdmittedIn,final String active){
		
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
							String qu = "select sd.Stu_ID as stuID,sd.First_Name as firstName,sd.Last_Name as lastName,sd.Gender as gender,sd.Address1 as addr1,sd.Address2 as addr2,sd.City as city,sd.State as state,sd.DOB as dob,sd.Photo as photo,sd.Admission_Number as admissionNumber,sd.Admission_Date as admissionDate,sd.Class_Admitted_in as classAdmittedIN,sd.Active as active, " +
									"sm.SeM_ID as secID ,sm.Section_Name as secName,cm.CM_ID as cmID,cm.Class_Name as className,bm.BM_ID as bmID ,bm.Branch_Name as branchName,bm.Short_Name as bShortName,schoolm.SM_ID as smID,schoolm.School_Name as schoolName,	schoolm.Name as sShortName " +
									" from student_details sd inner join student_section_map ssm on sd.Stu_ID=ssm.Stu_ID inner join section_class_map scm on ssm.SCM_ID=scm.SCM_ID " +
									" inner join section_master sm on scm.SeM_ID= sm.SeM_ID inner join class_master cm on scm.CM_ID=cm.CM_ID inner join branch_master bm on ssm.bm_id=bm.bm_id inner join school_master schoolm on ssm.sm_id=schoolm.sm_id "+
									" where scm.SeM_ID "+secIDOp+" ("+sections+") and scm.CM_ID "+cmIDOp+" ("+classes+")  and ssm.BM_ID  "+bmIDOp+" ("+branches+")  and ssm.SM_ID "+smIDOp+" ("+schools+") and ssm.IM_ID in ("+im_id+") and  " +
									" sd.First_Name	 like '"+fname+"' and sd.Last_Name like '"+lname+"' and sd.Gender	 like  '"+gender+"' and sd.Address1	 like  '"+addr1+"' and sd.Address2	 like  '"+addr2+"' " +
									" and sd.City like  '"+city+"' and sd.State	 like  '"+state+"' and sd.Email	 like  '"+email+"' and sd.Mobile	 like  '"+mobile+"' and sd.Landline	 like  '"+landline+"' and sd.DOB	 like  '"+dob+"'  " +
									" and sd.Admission_Number	 like  '"+admissionNumber+"'  and  sd.Admission_Date	between "+dateQString+"  and sd.Class_Admitted_in  "+cmAdmIDOp+" ("+classAdmittedIn+")  and sd.Active like  '"+active+"'  " +
									"and sm.active='y' and cm.active='y'  and bm.active='y'  and schoolm.active='y'   order by ssm.SM_ID desc";							
			
							System.out.println("##-->"+qu);
							
							StringType st = new StringType();
							SQLQuery sqlqu = session.createSQLQuery(qu);	
							
							sqlqu.addScalar("stuID", st);
							sqlqu.addScalar("firstName", st);
							sqlqu.addScalar("lastName", st);
							sqlqu.addScalar("gender", st);
							sqlqu.addScalar("addr1", st);
							sqlqu.addScalar("addr2",st );
							sqlqu.addScalar("city",st);
							sqlqu.addScalar("state", st);
							sqlqu.addScalar("dob", st);
							//	0			1				2					3		4			5			6		7		8		9			10							11						12						13
							//stuID,firstName,lastName,gender,addr1,addr2, city,state,dob, photo,admissionNumber,admissionDate,classAdmittedIN,active, 
							//  14		15				16	17				18		19					20				21			22				23
							//secID ,secName,cmID,className,bmID ,branchName,bShortName,smID,schoolName,	sShortName  
							sqlqu.addScalar("photo", st);//
							sqlqu.addScalar("admissionNumber", st);//
							sqlqu.addScalar("admissionDate", st);//
							sqlqu.addScalar("classAdmittedIN", st);//
							sqlqu.addScalar("active", st);// 
							sqlqu.addScalar("secID", st);// 
							sqlqu.addScalar("secName", st);// 
							sqlqu.addScalar("cmID", st);// 
							sqlqu.addScalar("className", st);// 
							sqlqu.addScalar("bmID", st);// 
							sqlqu.addScalar("branchName", st);// 
							sqlqu.addScalar("bShortName", st);// 
							sqlqu.addScalar("smID", st);// 
							sqlqu.addScalar("schoolName", st);// 
							sqlqu.addScalar("sShortName", st);// 
							//sqlqu.addScalar("active", st);// -------------------------------?2013-08-11
							
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
								Criteria isCrit = session.createCriteria(StudentSearchHistory.class);
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
	public String saveStudentSearchHistory(StudentSearchHistory querySearchHis) throws Exception{
		String re = null;
		try {
			final String qust = querySearchHis.getQueryString();
				re =  (String) getHibernateTemplate().execute(
						new HibernateCallback() {
							public Object doInHibernate(Session session)
									throws HibernateException, SQLException{							
								Criteria isCrit = session.createCriteria(StudentSearchHistory.class);
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
	
	@Override
	public List searchHistoryLoadDB(final String iM_ID, final  String uM_ID)throws Exception{
		try {
			return (List) getHibernateTemplate().execute(
						new HibernateCallback() {
							public Object doInHibernate(Session session)
									throws HibernateException, SQLException{							
								Criteria isCrit = session.createCriteria(StudentSearchHistory.class);
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
	public boolean deleteStudentSerchHistoryDocById(java.lang.Long id) throws Exception {
		try {
			StudentSearchHistory history = 	searchHistoryStudentFindById(id);
			System.out.println(history+"history");
			if (history != null) {
				return deleteStudentSerchHistoryDoc(history);
			}
		} catch (Exception re) {
			re.printStackTrace();
			return false;
		}
		return false;
	}
	
	@Override
	public boolean deleteStudentSerchHistoryDoc(StudentSearchHistory persistentInstance) throws Exception {
		try {
			getHibernateTemplate().delete(persistentInstance);
			return true;
		} catch (Exception re) {
			re.printStackTrace();
			return false;
		}
	}
	
	@Override
	public StudentSearchHistory searchHistoryStudentFindById(java.lang.Long id) throws Exception {
		try {
			StudentSearchHistory instance = (StudentSearchHistory) getHibernateTemplate().get("com.schooltrix.hibernate.StudentSearchHistory", id);
			return instance;
		} catch (Exception re) {
			throw re;
		}
	}
	
	@Override	
	public List searchStudentHistoryLoadByIDDisplay(final String querySt )throws Exception{
		List assignList=null;
		try {
			assignList = (List) getHibernateTemplate().execute(
					new HibernateCallback() {
						public Object doInHibernate(Session session)throws HibernateException, SQLException {				
							//System.out.println("##"+querySt);
							StringType st = new StringType();
							SQLQuery sqlqu = session.createSQLQuery(querySt);	
	// bm_ID, IM_ID, School_Name, Branch_Name, Short_Name, Address, City, State_ID, State_name,Contact_Person, Email_ID, Mobile, Landline, Active  
							
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
							sqlqu.addScalar("secID", st);// 
							sqlqu.addScalar("secName", st);// 
							sqlqu.addScalar("cmID", st);// 
							sqlqu.addScalar("className", st);// 
							sqlqu.addScalar("bmID", st);// 
							sqlqu.addScalar("branchName", st);// 
							sqlqu.addScalar("bShortName", st);// 
							sqlqu.addScalar("smID", st);// 
							sqlqu.addScalar("schoolName", st);// 
							sqlqu.addScalar("sShortName", st);// 
							sqlqu.addScalar("active", st);// 
							
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
	public String multiTransEditStudentSave(StudentDetails studentData){

		Transaction tx = null;
		Session session = null;
		try {
			session = this.getSession();
			 tx = session.beginTransaction();
			 List uDCBranchMap = null;
			 SimpleDateFormat sdf =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			final long studID = studentData.getStuId();

			StudentDetails studentDetailsOLD = findById(studID);
	
			StudentDetailsHistory studentDetailsHistory= new StudentDetailsHistory(studentDetailsOLD.getStuId(),
					studentDetailsOLD.getUmId(),studentDetailsOLD.getFirstName(),studentDetailsOLD.getLastName(),studentDetailsOLD.getGender(),
					studentDetailsOLD.getAddress1() ,studentDetailsOLD.getAddress2() ,studentDetailsOLD.getCity(),studentDetailsOLD.getState() ,studentDetailsOLD.getEmail() ,
					studentDetailsOLD.getMobile() ,studentDetailsOLD.getLandline() ,studentDetailsOLD.getDob() ,studentDetailsOLD.getPhoto() ,studentDetailsOLD.getAdmissionNumber() ,
					studentDetailsOLD.getAdmissionDate() ,studentDetailsOLD.getClassAdmittedIn() ,studentDetailsOLD.getActive() ,"edit" ,java.sql.Timestamp.valueOf(sdf.format(new Date())));
			
			studentDetailsHistory.setStuHId(studID);
			
			session.save(studentDetailsHistory);
			studentData.setUmId(studentDetailsOLD.getUmId());
			studentData.setActive(studentDetailsOLD.getActive());//no change for edit
			session.update(studentData);
				

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
	
	public String multiTrancationStudnetDisable(String stu_ID, String action,String isActive){

		Transaction tx 	= null;
		Session session = null;
		try {
				session 	= this.getSession();
				System.out.println(session.getTransaction()+"--trans already there--->"+session.getTransaction().isActive());
				//session.connection().setAutoCommit(false);
				tx 			= session.beginTransaction();
				System.out.println(session.getTransaction()+"--trans new there--->"+session.getTransaction().isActive());
				System.out.println(session.getFlushMode()+"***********8");
				//try this...
		final long stuID 								= Long.parseLong(stu_ID);
		StudentDetails studentDetailsOLD = findById(stuID);
        SimpleDateFormat sdf 						= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			 
        StudentDetailsHistory studentDetailsHistory= new StudentDetailsHistory(studentDetailsOLD.getStuId(),
						studentDetailsOLD.getUmId(),studentDetailsOLD.getFirstName(),studentDetailsOLD.getLastName(),studentDetailsOLD.getGender(),
						studentDetailsOLD.getAddress1() ,studentDetailsOLD.getAddress2() ,studentDetailsOLD.getCity(),studentDetailsOLD.getState() ,studentDetailsOLD.getEmail() ,
						studentDetailsOLD.getMobile() ,studentDetailsOLD.getLandline() ,studentDetailsOLD.getDob() ,studentDetailsOLD.getPhoto() ,studentDetailsOLD.getAdmissionNumber() ,
						studentDetailsOLD.getAdmissionDate() ,studentDetailsOLD.getClassAdmittedIn() ,studentDetailsOLD.getActive() ,action ,java.sql.Timestamp.valueOf(sdf.format(new Date())));
				
				System.out.println(tx+"111->"+session);
				session.save(studentDetailsHistory);
				
				studentDetailsOLD.setActive(isActive);
				session.update(studentDetailsOLD);
				System.out.println("22222222->"+session);
				//need parent diable.....
				String queryP = "from ParentStudentMap where stuId=? ";
				Query hqlQuery = session.createQuery(queryP);
				hqlQuery.setString(0, stu_ID);
				
				List parentsList = hqlQuery.list();
				ParentStudentMap parentStudentMap = new ParentStudentMap();
				if (parentsList != null) {
					int sizeOfParents = parentsList.size();
					System.out.println("sizeOfParents***"+sizeOfParents);
							int j = 0;
							for (int i = 0; i < sizeOfParents; i++) {
								parentStudentMap = (ParentStudentMap)parentsList.get(i);

								System.out.println("AFTERRRR"+parentStudentMap.getPdId());
								
								String queryPD = "from ParentDetails where pdId=? ";
								Query hqlQueryPD = session.createQuery(queryPD);
									hqlQueryPD.setString(0, parentStudentMap.getPdId());
									ParentDetails details = null;
									List parentsPDList = hqlQueryPD.list();
									
									if (parentsPDList != null && parentsPDList.size()>0) {
									 details =(ParentDetails)parentsPDList.get(0);
									}
							//	test multi transation once again...	
							System.out.println("*Parent ID**"+details.getPdId());
							ParentDetailsHistory detailsHistory = new ParentDetailsHistory(details.getPdId(),details.getUmId(),details.getFirstName(),details.getLastName(),details.getGender(),
									details.getAddress1(),details.getAddress2(),details.getCity(),details.getState(),details.getEmail(),details.getMobile(),details.getLandline(),details.getDob(),
									details.getPhoto(),details.getPtmId(),details.getIsDefault(),details.getActive(),action,java.sql.Timestamp.valueOf(sdf.format(new Date())));
							
							session.save(detailsHistory);								
							details.setActive(isActive);
							session.update(details);
							
							}
				}
				
				
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
	
	
	
}