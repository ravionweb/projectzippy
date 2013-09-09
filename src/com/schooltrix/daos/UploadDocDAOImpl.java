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

import com.schooltrix.hibernate.DocumentSearchHistory;
import com.schooltrix.hibernate.UploadDocumentClassBranchMap;
import com.schooltrix.hibernate.UploadDocumentClassBranchMapHistory;
import com.schooltrix.hibernate.UploadDocumentHistory;
import com.schooltrix.hibernate.UploadDocuments;
import com.schooltrix.hibernate.UploadDocument;

public class UploadDocDAOImpl extends STHibernateDAOSupport implements UploadDocDAO{
	
	@Override
	public boolean save(UploadDocument transientInstance) throws Exception {
		
			getHibernateTemplate().saveOrUpdate(transientInstance);
			System.out.println("in saveee");
			return true;
		
	}
	
	@Override
	public boolean update(UploadDocument transientInstance) throws Exception {
		try {
			getHibernateTemplate().update(transientInstance);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	@Override
	public boolean delete(UploadDocument persistentInstance) throws Exception {
		try {
			getHibernateTemplate().delete(persistentInstance);
			return true;
		} catch (Exception re) {
			re.printStackTrace();
			return false;
		}
	}
	
	@Override	
	public UploadDocument findByProperty(final String filed,final String value) throws Exception {
		try {
			return (UploadDocument) getHibernateTemplate().execute(
					new HibernateCallback() {
						public Object doInHibernate(Session session)
								throws HibernateException, SQLException{							
							Criteria isExpiredCrit = session.createCriteria(UploadDocument.class);
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
		List UploadDocumentList=null;
		try {
			UploadDocumentList = (List) getHibernateTemplate().execute(
					new HibernateCallback() {
						public Object doInHibernate(Session session)throws HibernateException, SQLException {
							Criteria crit = session.createCriteria(UploadDocument.class);
							crit.add(Restrictions.eq(filed, value));
							//crit.addOrder(Order.desc("sno"));
							return crit.list();
						}
					});
		} catch (Exception ex_) {
			ex_.printStackTrace();
			return null;
		}
		return UploadDocumentList;
	}
	
	@Override
	public UploadDocument findById(java.lang.Long id) throws Exception {
		try {
			UploadDocument instance = (UploadDocument) getHibernateTemplate().get("com.schooltrix.hibernate.UploadDocument", id);
			return instance;
		} catch (Exception re) {
			throw re;
		}
	}
	
/*	public List getAssignemets(final String bm_id,final String cm_id)throws Exception {

		
		List assignList=null;
		try {
			//System.out.println("in branchMasterList DAOIMPL");
			assignList = (List) getHibernateTemplate().execute(
					new HibernateCallback() {
						public Object doInHibernate(Session session)throws HibernateException, SQLException {
						
							select * from upload_documents where ((to_which like '%,4,%') or
									(to_which like '4,%') or (to_which like '%,4') or (to_which like '4') );
							
							String qu = "select upload_date,assign_type ,subject,assg_desc,file_name  from upload_documents where ((to_which like '%,"+cm_id+",%') or	(to_which like '"+cm_id+",%') " +
									"or (to_which like '%,"+cm_id+"') or (to_which like '"+cm_id+"'))  and ((bm_id like '%,"+bm_id+",%') or	(bm_id like '"+bm_id+",%') " +
									"or (bm_id like '%,"+bm_id+"') or (bm_id like '"+bm_id+"'))  and    to_whome in ('Parents','0') and upload_type='Assignment'  order by upload_date desc";
							
							System.out.println("##"+qu);
							StringType st = new StringType();
							SQLQuery sqlqu = session.createSQLQuery(qu);	
							sqlqu.addScalar("upload_date", st);
							sqlqu.addScalar("assign_type",st );
							sqlqu.addScalar("subject", st);
							sqlqu.addScalar("assg_desc",st);
							sqlqu.addScalar("file_name", st);
							
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
	*/

	public List getAssignemets(final String bm_id,final String cm_id,final int userType)throws Exception {		
		
		List assignList=null;
		try {
			//System.out.println("in branchMasterList DAOIMPL");
			assignList = (List) getHibernateTemplate().execute(
					new HibernateCallback() {
						public Object doInHibernate(Session session)throws HibernateException, SQLException {
							String toWhom = userType == 1? "Parents" : "Students";
/*							String qu = "select upload_date,assign_type ,subject,assg_desc,file_name  from upload_documents where ((to_which like '%,"+cm_id+",%') or	(to_which like '"+cm_id+",%') " +
									"or (to_which like '%,"+cm_id+"') or (to_which like '"+cm_id+"'))  and ((bm_id like '%,"+bm_id+",%') or	(bm_id like '"+bm_id+",%') " +
									"or (bm_id like '%,"+bm_id+"') or (bm_id like '"+bm_id+"'))  and    to_whome in ('Parents','0') and upload_type='Assignment'  order by upload_date desc";
*/							
							String qu ="select ud.upload_date as upload_date,ud.assign_type as assign_type ,ud.subject as subject,ud.assg_desc as assg_desc,ud.file_name as file_name " +
									"from upload_document ud inner join upload_document_class_branch_map udbc on ud.ud_id=udbc.ud_id  " +
									"where  ud.subject in (select subm_id from subject_class_map where cm_id = '"+cm_id+"') " +
									"and udbc.bm_id='"+bm_id+"' and udbc.cm_id='"+cm_id+"' and    ud.to_whome in ('"+toWhom+"','0') and ud.upload_type='Assignment'  order by ud.upload_date desc";
							
							System.out.println("##"+qu);
							StringType st = new StringType();
							SQLQuery sqlqu = session.createSQLQuery(qu);	
							sqlqu.addScalar("upload_date", st);
							sqlqu.addScalar("assign_type",st );
							sqlqu.addScalar("subject", st);
							sqlqu.addScalar("assg_desc",st);
							sqlqu.addScalar("file_name", st);
							
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
	
	public List getAcademics(final String bm_id,final String cm_id,final int userType)throws Exception {		
		
		List assignList=null;
		try {
			//System.out.println("in branchMasterList DAOIMPL");
			assignList = (List) getHibernateTemplate().execute(
					new HibernateCallback() {
						public Object doInHibernate(Session session)throws HibernateException, SQLException {
							String toWhom = userType == 1? "Parents" : "Students";
/*							String qu = "select upload_date,assign_type ,subject,assg_desc,file_name  from upload_documents where ((to_which like '%,"+cm_id+",%') or	(to_which like '"+cm_id+",%') " +
									"or (to_which like '%,"+cm_id+"') or (to_which like '"+cm_id+"'))  and ((bm_id like '%,"+bm_id+",%') or	(bm_id like '"+bm_id+",%') " +
									"or (bm_id like '%,"+bm_id+"') or (bm_id like '"+bm_id+"'))  and    to_whome in ('Parents','0') and upload_type='Assignment'  order by upload_date desc";
*/							
							String qu ="select ud.upload_date as upload_date,ud.subject as subject,ud.assg_desc as assg_desc,ud.file_name as file_name " +
									"from upload_document ud inner join upload_document_class_branch_map udbc on ud.ud_id=udbc.ud_id  " +
									"where  ud.subject in (select subm_id from subject_class_map where cm_id = '"+cm_id+"') " +
									"and udbc.bm_id='"+bm_id+"' and udbc.cm_id='"+cm_id+"' and    ud.to_whome in  ('"+toWhom+"','0')  and ud.upload_type='AcademicMaterial'  order by ud.upload_date desc";
							
							System.out.println("##"+qu);
							StringType st = new StringType();
							SQLQuery sqlqu = session.createSQLQuery(qu);	
							sqlqu.addScalar("upload_date", st);
							sqlqu.addScalar("subject", st);
							sqlqu.addScalar("assg_desc",st);
							sqlqu.addScalar("file_name", st);
							
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
	
	//this is same like getAssignemets....
	public List getUtilities(final String bm_id,final String cm_id,final String uptype,final int userType)throws Exception {

		
		List assignList=null;
		try {
			//System.out.println("in branchMasterList DAOIMPL");
			assignList = (List) getHibernateTemplate().execute(
					new HibernateCallback() {
						public Object doInHibernate(Session session)throws HibernateException, SQLException {
							String toWhom = userType == 1? "Parents" : "Students";
							
						/*	String qu = "select upload_date,upload_type ,file_name  from upload_documents where ((to_which like '%,"+cm_id+",%') or	(to_which like '"+cm_id+",%') " +
									"or (to_which like '%,"+cm_id+"') or (to_which like '"+cm_id+"'))  and ((bm_id like '%,"+bm_id+",%') or	(bm_id like '"+bm_id+",%') " +
									"or (bm_id like '%,"+bm_id+"') or (bm_id like '"+bm_id+"'))  and    to_whome in ('Parents','0') and upload_type='"+uptype+"'  order by upload_date desc limit 1";
						*/	
							String qu ="select ud.upload_date as upload_date,ud.upload_type as upload_type ,ud.file_name as file_name " +
									"from upload_document ud inner join upload_document_class_branch_map udbc on ud.ud_id=udbc.ud_id  " +
									"where  udbc.bm_id='"+bm_id+"' and udbc.cm_id='"+cm_id+"' and    ud.to_whome in  ('"+toWhom+"','0')  and ud.upload_type='"+uptype+"' order by ud.upload_date desc limit 1";
							
							System.out.println(uptype+"getUtilities#"+qu);
							StringType st = new StringType();
							SQLQuery sqlqu = session.createSQLQuery(qu);	
							sqlqu.addScalar("upload_date", st);
							sqlqu.addScalar("upload_type",st );
							sqlqu.addScalar("file_name", st);
							
							System.out.println("getUtilities SIZE"+sqlqu.list().size());
							return sqlqu.list();
						}
					});
		} catch (Exception ex_) {
			ex_.printStackTrace();
			return assignList;
		}
		return assignList;
	}
	
	public boolean saveUploadClassBranchMap(UploadDocumentClassBranchMap transientInstance) throws Exception {
		
		getHibernateTemplate().save(transientInstance);
		//System.out.println("in saveee");
		return true;
	}
	
	public List getUploadDocForEdit(final String inbmIds,final String inclassIds, final String inUserTypeIds,final String uploadType,final String fileType,final String assignmentType,final String selectSubject)throws Exception {	
		
		List assignList=null;
		try {
			//System.out.println("in branchMasterList DAOIMPL");
			assignList = (List) getHibernateTemplate().execute(
					new HibernateCallback() {
						public Object doInHibernate(Session session)throws HibernateException, SQLException {
						String qu =	"";
							
						 if (uploadType.equalsIgnoreCase("Assignment")) {
								qu ="select ud.ud_id as ud_id,	ud.to_whome as to_whome,	 ud.assign_type as assign_type ,  ud.file_name as file_name,  ud.assg_desc as assg_desc,  " +
										"	ud.notify_pa_email as notify_pa_email ,	ud.upload_date as upload_date" +
										" from upload_document ud inner join upload_document_class_branch_map udbc on ud.ud_id=udbc.ud_id  " +
										"where  ud.subject ="+selectSubject+"  and ud.upload_type ='"+uploadType+"'  and udbc.bm_id in ("+inbmIds+") and udbc.cm_id in  ("+inclassIds+") and    ud.to_whome in ("+inUserTypeIds+",'0') and ud.assign_type in ('"+assignmentType+"')  group by ud.ud_id   order by ud.upload_date desc";							
						}else if (uploadType.equalsIgnoreCase("AcademicMaterial")) {
							qu ="select ud.ud_id as ud_id,ud.to_whome as to_whome, ud.assign_type as assign_type ,ud.file_name as file_name,ud.assg_desc as assg_desc,ud.notify_pa_email as notify_pa_email ,ud.upload_date as upload_date" +
									" from upload_document ud inner join upload_document_class_branch_map udbc on ud.ud_id=udbc.ud_id  " +
									"where  ud.subject ="+selectSubject+" and ud.upload_type ='"+uploadType+"' and udbc.bm_id in ("+inbmIds+") and udbc.cm_id in  ("+inclassIds+") and    ud.to_whome in ("+inUserTypeIds+",'0')  group by ud.ud_id    order by ud.upload_date desc";							
						}else{
							qu ="select ud.ud_id as ud_id,ud.to_whome as to_whome, ud.assign_type as assign_type  ,ud.file_name as file_name,ud.assg_desc as assg_desc,ud.notify_pa_email as notify_pa_email ,ud.upload_date as upload_date" +
									" from upload_document ud inner join upload_document_class_branch_map udbc on ud.ud_id=udbc.ud_id  " +
									"where  udbc.bm_id in ("+inbmIds+") and ud.upload_type ='"+uploadType+"' and udbc.cm_id in  ("+inclassIds+") and    ud.to_whome in ("+inUserTypeIds+",'0')  group by ud.ud_id   order by ud.upload_date desc";
						}
							System.out.println("##"+qu);
							StringType st = new StringType();
							SQLQuery sqlqu = session.createSQLQuery(qu);	
							
							sqlqu.addScalar("ud_id", st);
							sqlqu.addScalar("to_whome", st);
							sqlqu.addScalar("assign_type",st );
							sqlqu.addScalar("file_name", st);
							sqlqu.addScalar("assg_desc",st );
							sqlqu.addScalar("notify_pa_email",st);
							sqlqu.addScalar("upload_date", st);
							
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
	public List getUploadDocForEditNew(final String smIDs, final String bmIDs, final String cmIDs,final String userIDs, final String uploadType, final String fileType, final String assignmentType, final String selectSubject,final String dateQString)throws Exception{
		List assignList=null;
		String smIDOpF = null;
		String bmIDOpF = null;
		String cmIDOpF = null;
		
	//	System.out.println(smIDs.length()+"666"+bmIDs.length()+"777"+cmIDs.length());
		if (smIDs.replace("'", "").length()>1) {
			smIDOpF = "in";			
		}else {
			smIDOpF = "like";			
		}
		if (bmIDs.replace("'", "").length()>1) {
			bmIDOpF = "in";			
		}else {
			bmIDOpF = "like";			
		}
		if (cmIDs.replace("'", "").length()>1) {
			cmIDOpF = "in";			
		}else {
			cmIDOpF = "like";			
		}
		final String smIDOp = smIDOpF;
		final String bmIDOp = bmIDOpF;
		final String cmIDOp = cmIDOpF;
		
	//	System.out.println(smIDOp+"bmIDOp"+bmIDOp+"cmIDOp"+cmIDOp+"****"+smIDOpF+""+bmIDOpF+""+cmIDOpF);

		try {
			assignList = (List) getHibernateTemplate().execute(
					new HibernateCallback() {
						public Object doInHibernate(Session session)throws HibernateException, SQLException {
							String qu =	"select ud.ud_id as ud_id,ud.to_whome as to_whome, ud.assign_type as assign_type  ,ud.file_name as file_name,ud.assg_desc as assg_desc,ud.notify_pa_email as notify_pa_email ,ud.upload_date as upload_date," +
									" ud.upload_type as upload_type,  ud.subject as subject, bm.Branch_Name as Branch_Name,sm.Name as Name  from upload_document  ud join  upload_document_class_branch_map udbc  on ud.ud_id=udbc.ud_id  join branch_master bm  on udbc.bm_id =  bm.bm_id join school_master sm on sm.sm_id= bm.sm_id " +
									"where ud.upload_type  like ('"+uploadType+"') and  ud.file_name	 like ('"+fileType+"')	and ud.assign_type like ('"+assignmentType+"')	and ud.subject like ('"+selectSubject+"')  and " +
									"ud.to_whome  in ("+userIDs+",'0') and  bm.sm_id "+smIDOp+" ("+smIDs+")	 and udbc.bm_id "+bmIDOp+" ("+bmIDs+") and udbc.cm_id "+cmIDOp+"("+cmIDs+")" +
										" and upload_date between "+dateQString+" group by ud.ud_id,bm.bm_id order by ud.upload_date desc";
						//for testing set...removed order by
/*							String qu =	"select ud.ud_id as ud_id,ud.to_whome as to_whome, ud.assign_type as assign_type  ,ud.file_name as file_name,ud.assg_desc as assg_desc,ud.notify_pa_email as notify_pa_email ,ud.upload_date as upload_date," +
									" ud.upload_type as upload_type,  ud.subject as subject, bm.Branch_Name as Branch_Name,sm.Name as Name  from upload_document  ud join  upload_document_class_branch_map udbc  on ud.ud_id=udbc.ud_id  join branch_master bm  on udbc.bm_id =  bm.bm_id join school_master sm on sm.sm_id= bm.sm_id " +
									"where ud.upload_type  like ('"+uploadType+"') and  ud.file_name	 like ('"+fileType+"')	and ud.assign_type like ('"+assignmentType+"')	and ud.subject like ('"+selectSubject+"')  and " +
									"ud.to_whome  in ("+userIDs+",'0') and  bm.sm_id "+smIDOp+" ("+smIDs+")	 and udbc.bm_id "+bmIDOp+" ("+bmIDs+") and udbc.cm_id "+cmIDOp+"("+cmIDs+")" +
									" and upload_date between "+dateQString+" group by ud.ud_id,bm.bm_id order by bm.sm_id,bm.bm_id,ud.upload_date desc";
							
*/							
							
							//school wise missing...changed group by
							/*							String qu =	"select ud.ud_id as ud_id,ud.to_whome as to_whome, ud.assign_type as assign_type  ,ud.file_name as file_name,ud.assg_desc as assg_desc,ud.notify_pa_email as notify_pa_email ,ud.upload_date as upload_date" +
									"  from upload_document  ud join  upload_document_class_branch_map udbc  on ud.ud_id=udbc.ud_id  join branch_master bm  on udbc.bm_id =  bm.bm_id " +
									"where ud.upload_type  like ('"+uploadType+"') and  ud.file_name	 like ('"+fileType+"')	and ud.assign_type like ('"+assignmentType+"')	and ud.subject like ('"+selectSubject+"')  and " +
									"ud.to_whome  in ("+userIDs+",'0') and  bm.sm_id "+smIDOp+" ("+smIDs+")	 and udbc.bm_id "+bmIDOp+" ("+bmIDs+") and udbc.cm_id "+cmIDOp+"("+cmIDs+")" +
									" and upload_date between "+dateQString+" group by ud.ud_id   order by ud.upload_date desc";
*/						
							System.out.println("##"+qu);
							StringType st = new StringType();
							SQLQuery sqlqu = session.createSQLQuery(qu);	
							
							sqlqu.addScalar("ud_id", st);
							sqlqu.addScalar("to_whome", st);
							sqlqu.addScalar("assign_type",st );
							sqlqu.addScalar("file_name", st);
							sqlqu.addScalar("assg_desc",st );
							sqlqu.addScalar("notify_pa_email",st);
							sqlqu.addScalar("upload_date", st);

							sqlqu.addScalar("upload_type", st);//7
							sqlqu.addScalar("subject", st);//8
							sqlqu.addScalar("Branch_Name", st);//9
							sqlqu.addScalar("Name", st);//school name 10
							
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
	public List findAll() throws Exception {
		try {
			String queryString = "from UploadDocument";
			return getHibernateTemplate().find(queryString);
		} catch (Exception re) {
			re.printStackTrace();
			return null;
		}
	}
	
	/*
	 * Special method for multi transactions Test...2013-06-08
	 * 
	 * */
	@Override
	public String multiTrancationUploadDelete(final String uD_ID,String Action) {
		Transaction tx = null;
		Session session = null;
		try {
			List uDCBranchMap = null;

			session = this.getSession();
			 tx = session.beginTransaction();
			
			UploadDocument uploadDocument = findById(Long.parseLong(uD_ID));
			//1111//UploadDocument instance = (UploadDocument) getHibernateTemplate().get("com.schooltrix.hibernate.UploadDocument", id);
						System.out.println(uD_ID+"::"+uploadDocument.getFileName()+":::"+uploadDocument.getUdId());
			UploadDocumentHistory uploadDocumentHistory = new UploadDocumentHistory(uploadDocument);
			uploadDocumentHistory.setUdId(uploadDocument.getUdId());
			//getHibernateTemplate().save(uploadDocumentHistory);
			
			session.save(uploadDocumentHistory);
			
			//getHibernateTemplate().delete(uploadDocument);
			session.delete(uploadDocument);
			//delete(uploadDocument);
			
			uDCBranchMap = (List) getHibernateTemplate().execute(
					new HibernateCallback() {
						public Object doInHibernate(Session session)throws HibernateException, SQLException {
							Criteria crit = session.createCriteria(UploadDocumentClassBranchMap.class);
							crit.add(Restrictions.eq("udId", uD_ID));
							//crit.addOrder(Order.desc("sno"));
							return crit.list();
						}
					});
			System.out.println(session+"before coomit"+tx);
			 SimpleDateFormat sdf =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			 
			 UploadDocumentClassBranchMapHistory uploadDocumentClassBranchMapHistory = null;
			 UploadDocumentClassBranchMap uBranchMapInn = null;
			 
			for (Iterator iterator = uDCBranchMap.iterator(); iterator.hasNext();) {
				Object object = (Object) iterator.next();
				 uBranchMapInn = (UploadDocumentClassBranchMap)object;
				 
				//System.out.println(uBranchMapInn.getSno()+"*sno*"+uBranchMapInn.getUdId()+":::"+uBranchMapInn.getBmId()+"::::"+uBranchMapInn.getCmId());
				 uploadDocumentClassBranchMapHistory		= new UploadDocumentClassBranchMapHistory();
				 
				uploadDocumentClassBranchMapHistory.setAction(Action);
				uploadDocumentClassBranchMapHistory.setBmId(uBranchMapInn.getBmId());//testing comment
				uploadDocumentClassBranchMapHistory.setCmId(uBranchMapInn.getCmId());
				uploadDocumentClassBranchMapHistory.setSno(uBranchMapInn.getSno());
				uploadDocumentClassBranchMapHistory.setUdId(uBranchMapInn.getUdId());
				uploadDocumentClassBranchMapHistory.setUpdatedDate(java.sql.Timestamp.valueOf(sdf.format(new Date())));
				
				//getHibernateTemplate().save(uploadDocumentClassBranchMapHistory);
				session.save(uploadDocumentClassBranchMapHistory);
				//System.out.println("in for after save");
				
				//getHibernateTemplate().delete(uBranchMapInn);
				session.delete(uBranchMapInn);
				//System.out.println("in for after delete");
				
			}
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
		return null;
		
	}
	
	/*@return List of  UploadDocumentClassBranchMap object
	 * 
	 * @input Upload Doc ID
	 * */	
	public List getUploadDocumentClassBranchMap(final String field,final String up_id)throws Exception {	
		System.out.println(up_id+"upid");
	return (List) getHibernateTemplate().execute(
			new HibernateCallback() {
				public Object doInHibernate(Session session)throws HibernateException, SQLException {
					Criteria crit = session.createCriteria(UploadDocumentClassBranchMap.class);
					crit.add(Restrictions.eq(field, up_id));
					//crit.addOrder(Order.desc("sno"));
				//	System.out.println(crit.list().size()+"****");
					return crit.list();
				}
			});
	}
	
	public String updateMultiTransactionCall(UploadDocument uploadDocData,String cmBMJson,String cm_ids[])throws Exception{
		Transaction tx = null;
		Session session = null;
		try {
			session = this.getSession();
			 tx = session.beginTransaction();
			 List uDCBranchMap = null;
			 
			final long uploadID = uploadDocData.getUdId();
			 /*same as upload doc,
				 *	here main thing is edited doc handling.
				 *1)update ud_id data in upload_document,	insert updated(edit as action) in upload_document_history
				 * 2)delete rows  rel to upload_document_class_branch_map
				 * 3),deleted rows in upload_document_class_branch_map_history tables..
				 * 4)Insert new records upload_document_class_branch_map..
				 * use multiTransaction..............
				 * */
			UploadDocument uploadDocumentOld = findById(uploadID);
			UploadDocumentHistory uploadDocumentHistory = new UploadDocumentHistory(uploadDocumentOld);
			uploadDocumentHistory.setUdId(uploadID);
			
		session.save(uploadDocumentHistory);
			
			 session.update(uploadDocData);
				
			 
			 //UploadDocumentClassBranchMap
				uDCBranchMap = (List) getHibernateTemplate().execute(
						new HibernateCallback() {
							public Object doInHibernate(Session session)throws HibernateException, SQLException {
								Criteria crit = session.createCriteria(UploadDocumentClassBranchMap.class);
								crit.add(Restrictions.eq("udId", uploadID+""));
								return crit.list();
							}
						});
				System.out.println(session+"before coomit"+tx);
				 SimpleDateFormat sdf =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				 
				 UploadDocumentClassBranchMapHistory uploadDocumentClassBranchMapHistory = null;
				 UploadDocumentClassBranchMap uBranchMapInn = null;
				 
				for (Iterator iterator = uDCBranchMap.iterator(); iterator.hasNext();) {
					Object object = (Object) iterator.next();
					 uBranchMapInn = (UploadDocumentClassBranchMap)object;
					 
					//System.out.println(uBranchMapInn.getSno()+"*sno*"+uBranchMapInn.getUdId()+":::"+uBranchMapInn.getBmId()+"::::"+uBranchMapInn.getCmId());
					 uploadDocumentClassBranchMapHistory		= new UploadDocumentClassBranchMapHistory();
					 
					uploadDocumentClassBranchMapHistory.setAction("Edit");
					uploadDocumentClassBranchMapHistory.setBmId(uBranchMapInn.getBmId());//testing comment
					uploadDocumentClassBranchMapHistory.setCmId(uBranchMapInn.getCmId());
					uploadDocumentClassBranchMapHistory.setSno(uBranchMapInn.getSno());
					uploadDocumentClassBranchMapHistory.setUdId(uBranchMapInn.getUdId());
					uploadDocumentClassBranchMapHistory.setUpdatedDate(java.sql.Timestamp.valueOf(sdf.format(new Date())));
					
					session.save(uploadDocumentClassBranchMapHistory);
					//System.out.println("in for after save");
					
					session.delete(uBranchMapInn);
					//System.out.println("in for after delete");
					
				}
			 
				UploadDocumentClassBranchMap uploClassBranchMap = null;
				
				// System.out.println("jsonTokener:"+jsonTokener);
				JSONTokener jsonTokener = new JSONTokener(cmBMJson);
				 JSONArray jsonMainArray = new JSONArray(jsonTokener);
				 
				// System.out.println("jsonMainArray::"+jsonMainArray.length());
				 for (int i = 0; i < jsonMainArray.length(); i++) {
					 
					 JSONObject jsonObject =  jsonMainArray.getJSONObject(i);						 
					String class_ID = jsonObject.getString("class_id");
					if (isClassIDAccess(class_ID,cm_ids)) {					
						JSONArray bm_IDs = jsonObject.getJSONArray("bms");
			            for(int k=0;k<bm_IDs.length();k++){
			            	uploClassBranchMap = new UploadDocumentClassBranchMap();
			                System.out.println(bm_IDs.getString(k)+"**"+class_ID);
			    			uploClassBranchMap.setBmId(bm_IDs.getString(k));
			    			uploClassBranchMap.setCmId(class_ID);
							uploClassBranchMap.setUdId(uploadDocData.getUdId()+"");
							
							session.save(uploClassBranchMap);			                
			            }
					}
				}
			 
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
	
	private boolean isClassIDAccess(String class_ID, String[] cm_ids) {
		
		for (int i = 0; i < cm_ids.length; i++) {
			if (class_ID.equalsIgnoreCase(cm_ids[i])) {
				return true;				
			}
		}
		
		return false;
	}
	
	public String saveSearchHistory(DocumentSearchHistory querySearchHis) throws Exception{
		String re = null;
		try {
			final String qust = querySearchHis.getQueryString();
				re =  (String) getHibernateTemplate().execute(
						new HibernateCallback() {
							public Object doInHibernate(Session session)
									throws HibernateException, SQLException{							
								Criteria isCrit = session.createCriteria(DocumentSearchHistory.class);
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
			System.out.println(querySearchHis.getDshId()+"**IDDDDD");
			return "success";
		} catch (Exception e) {
			System.out.println("in first Excep*********");
			e.printStackTrace();
		}
		return "error";
	}
	
	public String isDescThereDB(final String iM_ID,final String uM_ID,final String searchDesc) throws Exception{
		
		try {
			return (String) getHibernateTemplate().execute(
						new HibernateCallback() {
							public Object doInHibernate(Session session)
									throws HibernateException, SQLException{							
								Criteria isCrit = session.createCriteria(DocumentSearchHistory.class);
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
	
	public List searchHistoryLoadDB(final String iM_ID, final  String uM_ID)throws Exception{
		try {
			return (List) getHibernateTemplate().execute(
						new HibernateCallback() {
							public Object doInHibernate(Session session)
									throws HibernateException, SQLException{							
								Criteria isCrit = session.createCriteria(DocumentSearchHistory.class);
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
	public List searchHistoryLoadByIDDisplay(final String querySt )throws Exception{
		List assignList=null;
		try {
			assignList = (List) getHibernateTemplate().execute(
					new HibernateCallback() {
						public Object doInHibernate(Session session)throws HibernateException, SQLException {				
							System.out.println("##"+querySt);
							StringType st = new StringType();
							SQLQuery sqlqu = session.createSQLQuery(querySt);	
							
							sqlqu.addScalar("ud_id", st);
							sqlqu.addScalar("to_whome", st);
							sqlqu.addScalar("assign_type",st );
							sqlqu.addScalar("file_name", st);
							sqlqu.addScalar("assg_desc",st );
							sqlqu.addScalar("notify_pa_email",st);
							sqlqu.addScalar("upload_date", st);

							sqlqu.addScalar("upload_type", st);//7
							sqlqu.addScalar("subject", st);//8
							sqlqu.addScalar("Branch_Name", st);//9
							sqlqu.addScalar("Name", st);//school name 10
							
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
	public boolean deleteSerchHistoryDocById(java.lang.Long id) throws Exception {
		try {
			DocumentSearchHistory history = 	serchHistoryDocFindById(id);
			System.out.println(history+"history");
			if (history != null) {
				return deleteSerchHistoryDoc(history);
			}
		} catch (Exception re) {
			re.printStackTrace();
			return false;
		}
		return false;
	}
	
	@Override
	public boolean deleteSerchHistoryDoc(DocumentSearchHistory persistentInstance) throws Exception {
		try {
			getHibernateTemplate().delete(persistentInstance);
			return true;
		} catch (Exception re) {
			re.printStackTrace();
			return false;
		}
	}

	@Override
	public DocumentSearchHistory serchHistoryDocFindById(java.lang.Long id) throws Exception {
		try {
			DocumentSearchHistory instance = (DocumentSearchHistory) getHibernateTemplate().get("com.schooltrix.hibernate.DocumentSearchHistory", id);
			return instance;
		} catch (Exception re) {
			throw re;
		}
	}
	
}