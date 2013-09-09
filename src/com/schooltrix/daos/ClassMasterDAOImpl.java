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
import org.hibernate.criterion.Restrictions;
import org.hibernate.type.StringType;
import org.json.JSONObject;

import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.HibernateCallback;

import com.schooltrix.hibernate.ClassBranchMap;
import com.schooltrix.hibernate.ClassMaster;
import com.schooltrix.hibernate.ClassMasterHistory;
import com.schooltrix.hibernate.SubjectMaster;
import com.sun.istack.internal.FinalArrayList;
import com.sun.org.apache.xpath.internal.operations.Bool;


public class ClassMasterDAOImpl extends STHibernateDAOSupport implements ClassMasterDAO{
	
	
	
	
	
	@Override
	public boolean save(ClassMaster transientInstance) throws Exception {
		System.out.println("in ClassMasterDAOImpl");
			getHibernateTemplate().saveOrUpdate(transientInstance);
			System.out.println("in saveee");
			return true;
		
	}
	@Override
	public boolean update(ClassMaster transientInstance) throws Exception {
		try {
			getHibernateTemplate().update(transientInstance);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	@Override
	public boolean delete(ClassMaster persistentInstance) throws Exception {
		try {
			getHibernateTemplate().delete(persistentInstance);
			return true;
		} catch (Exception re) {
			re.printStackTrace();
			return false;
		}
	}
	@Override
	public ClassMaster findByProperty(final String filed,final String value) throws Exception {
		try {
			return (ClassMaster) getHibernateTemplate().execute(
					new HibernateCallback() {
						public Object doInHibernate(Session session)
								throws HibernateException, SQLException{							
							Criteria isExpiredCrit = session.createCriteria(ClassMaster.class);
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
		List ClassMasterList=null;
		try {
			ClassMasterList = (List) getHibernateTemplate().execute(
					new HibernateCallback() {
						public Object doInHibernate(Session session)throws HibernateException, SQLException {
							Criteria crit = session.createCriteria(ClassMaster.class);
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
		return ClassMasterList;
	}
	

		@Override
		public List getClassMasterList(final String IM_ID,final String SM_ID,final String BM_ID,final int flag) throws Exception {
			List ClassMasterList=null;
			try {
				System.out.println("in daoimpl--"+flag+"--"+IM_ID+"--"+SM_ID+"--"+BM_ID+"--");
				ClassMasterList = (List) getHibernateTemplate().execute(
						new HibernateCallback() {
							public Object doInHibernate(Session session)throws HibernateException, SQLException {//need more where condtion im_id sm_id
							//	String qu= "select cm_id,class_name from class_master where cm_id in(select cm_id  from class_branch_map where Active='Y' and bm_id='"+BM_ID+"')";
								String  qu=null;
								if (flag == 1) {
									//all classes under institution
									qu= "select cm_id,class_name from class_master where cm_id in(select cm_id  from class_branch_map where Active='Y'" +
											" and bm_id in (select bm_id  from branch_master where Active='Y' and im_id='"+IM_ID+"'))";
									
								}else if (flag == 2) {
									//here based on branch id only we get classes list....
									qu= "select cm_id,class_name from class_master where cm_id in(select cm_id  from class_branch_map where Active='Y'" +
											" and bm_id ='"+BM_ID+"')";
									
								}else if (flag == 3) {
									//under one school...
									//all classes under school
									qu= "select cm_id,class_name from class_master where cm_id in(select cm_id  from class_branch_map where Active='Y'" +
											" and bm_id in (select bm_id  from branch_master where Active='Y' and sm_id='"+SM_ID+"'))";
									
								}
								
								
								SQLQuery sqlqu = session.createSQLQuery(qu);							
								sqlqu.addScalar("cm_id", new StringType());
								sqlqu.addScalar("class_name", new StringType());
								System.out.println(qu);
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
		public List getClassMasterList(final String BM_ID) throws Exception {
			List ClassMasterList=null;
			try {
				System.out.println("in getClassMasterList DAOIMPL");
				ClassMasterList = (List) getHibernateTemplate().execute(
						new HibernateCallback() {
							public Object doInHibernate(Session session)throws HibernateException, SQLException {//need more where condtion im_id sm_id
							//	String qu= "select cm_id,class_name from class_master where cm_id in(select cm_id  from class_branch_map where Active='Y' and bm_id='"+BM_ID+"')";
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
		public List<String> getMultiClassMasterList(final String BM_IDs) throws Exception {
			List<String> ClassMasterList=null;
			try {
				ClassMasterList = (List<String>) getHibernateTemplate().execute(
						new HibernateCallback() {
							public Object doInHibernate(Session session)throws HibernateException, SQLException {
								String qu = "select cm.cm_id as cm_id,cm.class_name as class_name,cbm.bm_id as bm_id,cbm.cbm_id as cbm_id  from class_master cm inner join class_branch_map cbm on " +
										"cm.cm_id=cbm.cm_id where cbm.Active='Y' and cbm.bm_id in  ("+BM_IDs+") order by cm.cm_id";
							//	String qu= "select cm_id,class_name from class_master where cm_id in(select cm_id  from class_branch_map where Active='Y' and bm_id in ("+BM_IDs+"))";
								System.out.println("in getMultiClassMasterList DAOIMPL*&"+qu);
								SQLQuery sqlqu = session.createSQLQuery(qu);							
								sqlqu.addScalar("cm_id", new StringType());
								sqlqu.addScalar("class_name", new StringType());
								sqlqu.addScalar("bm_id", new StringType());
								sqlqu.addScalar("cbm_id", new StringType());
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
	public ClassMaster findById(java.lang.Long id) throws Exception {
		try {
			System.out.println("in classmaster impl findbyid");
			ClassMaster instance = (ClassMaster) getHibernateTemplate().get("com.schooltrix.hibernate.ClassMaster", id);
			return instance;
		} catch (Exception re) {
			throw re;
		}
	}

	@Override
	public List findAll() throws Exception {
		try {
			String queryString = "from ClassMaster";
			return getHibernateTemplate().find(queryString);
		} catch (Exception re) {
			re.printStackTrace();
			return null;
		}
	}
	//-------------------------------------------------------------------
	@Override
	public boolean save(SubjectMaster transientInstance) throws Exception {
		System.out.println("in SubjectMasterDAOImpl");
			getHibernateTemplate().saveOrUpdate(transientInstance);
			System.out.println("in saveee");
			return true;
		
	}
	@Override
	public boolean update(SubjectMaster transientInstance) throws Exception {
		try {
			getHibernateTemplate().update(transientInstance);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	@Override
	public boolean delete(SubjectMaster persistentInstance) throws Exception {
		try {
			getHibernateTemplate().delete(persistentInstance);
			return true;
		} catch (Exception re) {
			re.printStackTrace();
			return false;
		}
	}
	@Override
	public SubjectMaster findBySubjectMasterProperty(final String filed,final String value) throws Exception {
		try {
			return (SubjectMaster) getHibernateTemplate().execute(
					new HibernateCallback() {
						public Object doInHibernate(Session session)
								throws HibernateException, SQLException{							
							Criteria isExpiredCrit = session.createCriteria(SubjectMaster.class);
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
	public List findBySubjectMasterPropertyList(final String filed,final String value) throws Exception {
		List SubjectMasterList=null;
		try {
			SubjectMasterList = (List) getHibernateTemplate().execute(
					new HibernateCallback() {
						public Object doInHibernate(Session session)throws HibernateException, SQLException {
							Criteria crit = session.createCriteria(SubjectMaster.class);
							crit.add(Restrictions.eq(filed, value));
							//crit.addOrder(Order.desc("sno"));
							return crit.list();
						}
					});
		} catch (Exception ex_) {
			ex_.printStackTrace();
			return null;
		}
		return SubjectMasterList;
	}
	
	@Override
	public SubjectMaster findBySubjectMasterId(java.lang.Long id) throws Exception {
		try {
			System.out.println("in SubjectMaster impl findbyid");
			SubjectMaster instance = (SubjectMaster) getHibernateTemplate().get("com.schooltrix.hibernate.SubjectMaster", id);
			return instance;
		} catch (Exception re) {
			throw re;
		}
	}

	@Override
	public List findAllSubjectMaster() throws Exception {
		try {
			String queryString = "from SubjectMaster";
			return getHibernateTemplate().find(queryString);
		} catch (Exception re) {
			re.printStackTrace();
			return null;
		}
	}
	
	//EDITUpload DOC
	public String findAllSubjectMasterJSON() throws Exception {
		
		List SubjectMasterList=null;
		try {
			SubjectMasterList = (List) getHibernateTemplate().execute(
					new HibernateCallback() {
						public Object doInHibernate(Session session)throws HibernateException, SQLException {//need more where condtion im_id sm_id
							//String qu= "select class_name from subject_master where cm_id in(select cm_id  from class_branch_map where Active='Y' and bm_id='"+BM_ID+"')";
							String qu ="SELECT subm_id,sub_name FROM subject_master where Active='Y' ";									
							
							SQLQuery sqlqu = session.createSQLQuery(qu);							
							sqlqu.addScalar("subm_id", new StringType());
							sqlqu.addScalar("sub_name", new StringType());
							System.out.println("in SIZE"+sqlqu.list().size());
							return sqlqu.list();
						}
					});
			JSONObject details = new JSONObject();  
			for (Iterator iterator = SubjectMasterList.iterator(); iterator.hasNext();) {
				Object[] object = (Object[]) iterator.next();
				details.put(object[0].toString(), object[1].toString());
			}
			return details.toString();
			
		} catch (Exception ex_) {
			ex_.printStackTrace();
			return null;
		}
	}
	
	
//requirement based methodsssssssssssssssssssssss
		@Override
		public List getSubjectMasterList(String IM_ID,final String BM_ID, String SM_ID,final String CM_ID) throws Exception {
			List SubjectMasterList=null;
			try {
				System.out.println("in getSubjectMasterList DAOIMPL---"+BM_ID+"---"+CM_ID);
				SubjectMasterList = (List) getHibernateTemplate().execute(
						new HibernateCallback() {
							public Object doInHibernate(Session session)throws HibernateException, SQLException {//need more where condtion im_id sm_id
								//String qu= "select class_name from subject_master where cm_id in(select cm_id  from class_branch_map where Active='Y' and bm_id='"+BM_ID+"')";
								String qu = "";
								if(CM_ID.equalsIgnoreCase("0")){
									qu="SELECT subm_id,sub_name FROM subject_master where subm_id in " +
											"(SELECT subm_id FROM subject_class_map where cm_id in " +
											"(select cm_id  from class_branch_map where Active='Y' and bm_id='"+BM_ID+"'))";									
								}else{								
								 qu="SELECT subm_id,sub_name FROM subject_master where subm_id in (SELECT subm_id FROM subject_class_map where cm_id='"+CM_ID+"')";
								}
								
								SQLQuery sqlqu = session.createSQLQuery(qu);							
								sqlqu.addScalar("subm_id", new StringType());
								sqlqu.addScalar("sub_name", new StringType());
								System.out.println("in SIZE"+sqlqu.list().size());
								return sqlqu.list();
							}
						});
			} catch (Exception ex_) {
				ex_.printStackTrace();
				return null;
			}
			return SubjectMasterList;
		}
	
		@Override
		public List getMultiSubjectMasterList(String IM_ID,String SM_ID,final String BM_IDs, final String CM_IDs) throws Exception {
			List SubjectMasterList=null;
			try {
				//System.out.println("in getSubjectMasterList DAOIMPL---"+BM_IDs+"---"+CM_IDs);
				SubjectMasterList = (List) getHibernateTemplate().execute(
						new HibernateCallback() {
							public Object doInHibernate(Session session)throws HibernateException, SQLException {//need more where condtion im_id sm_id
								//String qu= "select class_name from subject_master where cm_id in(select cm_id  from class_branch_map where Active='Y' and bm_id='"+BM_ID+"')";
								String qu = "";
								 qu="SELECT sub_name, subm_id FROM subject_master where subm_id in (SELECT subm_id FROM subject_class_map where cm_id in ("+CM_IDs+"))" +
								 		"and bm_id in("+BM_IDs+") group by sub_name ";
								
								SQLQuery sqlqu = session.createSQLQuery(qu);							
								sqlqu.addScalar("sub_name", new StringType());
								sqlqu.addScalar("subm_id", new StringType());
								System.out.println("in SIZE"+sqlqu.list().size());
								return sqlqu.list();
							}
						});
			} catch (Exception ex_) {
				ex_.printStackTrace();
				return null;
			}
			return SubjectMasterList;
		}
	
		@Override
		public boolean saveClassBranchMap(ClassBranchMap transientInstance) throws Exception {
			System.out.println("in ClassMasterDAOImpl---ClassBranchMap");
				getHibernateTemplate().saveOrUpdate(transientInstance);
				System.out.println("in saveee");
				return true;
			
		}

		@Override
		public ClassBranchMap findByIdClassBranchMap(java.lang.Long id) throws Exception {
			try {
				System.out.println("in classmaster impl findbyid");
				ClassBranchMap instance = (ClassBranchMap) getHibernateTemplate().get("com.schooltrix.hibernate.ClassBranchMap", id);
				return instance;
			} catch (Exception re) {
				throw re;
			}
		}
		
		@Override		
		public String reNameClassName(String classID, String newClassName,String bmID) throws Exception{
				try {
	/*				ClassMaster classMaster2 = findByProperty("className", newClassName);
					if (classMaster2 != null && isClassExist(classMaster2.getCmId() ,Long.parseLong(bmID))  ) {
						return "already";
					}*/
					boolean  isClassThere = isClassByName(newClassName, bmID);
					if (isClassThere) {
						return "already";
					}
					
					SimpleDateFormat sdf 						= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					ClassMaster classMaster = findById(Long.parseLong(classID));
	/*				Long cmId, String className, String active,
					String action, Timestamp editedDate) {*/						
					ClassMasterHistory classMasterHistory = new ClassMasterHistory(classMaster.getCmId(),classMaster.getClassName(),classMaster.getActive(),"rename",java.sql.Timestamp.valueOf(sdf.format(new Date())));
					getHibernateTemplate().saveOrUpdate(classMasterHistory);
					classMaster.setClassName(newClassName);
					getHibernateTemplate().update(classMaster);
					return "success";
				} catch (Exception e) {
					e.printStackTrace();
					return "fail";
				}
		}
	
		//private boolean isClassExist(final long cm_id,final long bm_id) {	
		@Override
		public boolean isClassByName(final String className,final String bm_id) {			
		  return (boolean)getHibernateTemplate().execute(
					new HibernateCallback() {
						public Object doInHibernate(Session session)throws HibernateException, SQLException {//need more where condtion im_id sm_id
						//	String qu= "select cm_id,class_name from class_master where cm_id in(select cm_id  from class_branch_map where Active='Y' and bm_id='"+BM_ID+"')";
							String qu= "select cm.Class_Name as Class_Name from class_master cm inner join class_branch_map cbm on cm.cm_id=cbm.cm_id		where cm.Class_Name ='"+className+"' and cbm.BM_ID='"+bm_id+"' ";
							SQLQuery sqlqu = session.createSQLQuery(qu);							
							sqlqu.addScalar("Class_Name", new StringType());
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
		
/*		@Override
		 boolean isClassByName(String className,String bmID) {
			try{
				//ClassMaster classMaster2 = findByProperty("className", className);
				if (classMaster2 != null && isClassExist(classMaster2.getCmId(),Long.parseLong(bmID))  ) {
					return true;
				}
				return false;
			}catch (Exception e) {
				e.printStackTrace();
				return true;	
			}
		}*/
		
		
		public String saveClass(ClassMaster classMaster,String bmID) throws Exception{
			try {
				boolean  isClassThere = isClassByName(classMaster.getClassName(), bmID);
				if (isClassThere) {
					return "already";
				}
				
				save(classMaster);
				ClassBranchMap branchMap = new ClassBranchMap(Long.parseLong(bmID),classMaster.getCmId(),"Y");
				saveClassBranchMap(branchMap);
				return "success";
			} catch (Exception e) {
				e.printStackTrace();
				return "fail";
			}
		}
		@Override
		public String disableCLass(final String bmID,final String cmID){
			
			   try {
				return (String)getHibernateTemplate().execute(
							new HibernateCallback() {
								public Object doInHibernate(Session session)throws HibernateException, SQLException {//need more where condtion im_id sm_id
								//	String qu= "select cm_id,class_name from class_master where cm_id in(select cm_id  from class_branch_map where Active='Y' and bm_id='"+BM_ID+"')";
									String qu= "select cm.cm_id as cm_id,cm.class_name as class_name,cm.active as active from class_master cm inner join class_branch_map cbm on cm.cm_id=cbm.cm_id		where cbm.CM_ID ='"+cmID+"' and cbm.BM_ID='"+bmID+"' ";
									SQLQuery sqlqu = session.createSQLQuery(qu);							
									sqlqu.addScalar("cm_id", new StringType());
									sqlqu.addScalar("class_name", new StringType());
									sqlqu.addScalar("active", new StringType());
									System.out.println(qu);
									System.out.println("in SIZE"+sqlqu.list().size());
									if(sqlqu.list().size()>0){
										Object[] row = (Object[])sqlqu.list().get(0);
										System.out.println("in sideiii");
										SimpleDateFormat sdf 						= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
										ClassMasterHistory classMasterHistory = new ClassMasterHistory(Long.parseLong(row[0]+""),row[1]+"",row[2]+"","disable",java.sql.Timestamp.valueOf(sdf.format(new Date())));
										getHibernateTemplate().saveOrUpdate(classMasterHistory);
										
										String innerQU = "update ClassMaster set active='N' where cmId='"+cmID+"'";
										Query query = session.createQuery(innerQU);
										query.executeUpdate();
										
										String innerQU1 = "update ClassBranchMap set active='N' where cmId='"+cmID+"' and bmId='"+bmID+"'";
										Query query1 = session.createQuery(innerQU1);
										query1.executeUpdate();
										System.out.println("inside disableCLass");
										return "success";								
									}else{
										return "notexist";
									}
								}
							});
			} catch (DataAccessException e) {
				e.printStackTrace();
				return "fail";
			}
			
		}
}