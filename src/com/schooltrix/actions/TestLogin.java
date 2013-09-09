package com.schooltrix.actions;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.springframework.context.ApplicationContext;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.schooltrix.daos.ClassMasterDAO;
import com.schooltrix.hibernate.ClassMaster;
import com.schooltrix.managers.ServiceFinder;
	
//public class TestLogin extends ActionSupport implements ServletRequestAware{
	public class TestLogin extends ActionSupport implements ServletRequestAware{
	
		public HttpServletRequest request = null;
	
		/**
		 * 
		 */
		private final long serialVersionUID = 6044679946406286486L;
	
		/**
		 * @return
		 */
		public String execute(){
			// TODO Auto-generated method stub
			System.out.println("in action support");
			String className 	=  request.getParameter("class_name");
			String active			    =  request.getParameter("active");
			System.out.println(className+":::"+active);
			
	ClassMasterDAO cmd = (ClassMasterDAO)ServiceFinder.getContext(request).getBean("ClassMasterDAO");
			try {
				ClassMaster classData =	new ClassMaster();
				classData.setActive(active);
				classData.setClassName(className);
				
				cmd.save(classData);
				
				
		/*	ClassMaster classData =	cmd.findById(new Long("1"));
			System.out.println(classData.getClassName());
			System.out.println(classData.getActive());*/
			
			} catch (Exception e) {
				System.out.println("in exption");
				e.printStackTrace();
			}
			
			
			
			
			
			return "welcome";
		//	return "successView";
		}
	
		
		public void setServletRequest(HttpServletRequest arg0) {
			// TODO Auto-generated method stub
			this.request  = arg0;
			System.out.println("in set serveltttt"+request);
		}
	
	/*	@Override
		public Object getModel() {
			// TODO Auto-generated method stub
			return null;
		}*/
}