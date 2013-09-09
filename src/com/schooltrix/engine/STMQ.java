package com.schooltrix.engine;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class STMQ { 

   //private  static org.apache.log4j.Logger log = Logger.getLogger(VTMQ.class);

    /**     *
     * @param args
     * this cons..for excuting from command prompt..tray-icon
     */
	public STMQ() {
		System.out.println("in STMQ");
		ApplicationContext apx;
		try {
			apx = new ClassPathXmlApplicationContext("./com/schooltrix/engine/Schooltrix-Jobs.xml");
			System.out.println(apx.getDisplayName());
		
		} catch (BeansException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
   public static void main(String[] args) {
	
	   new STMQ();
	   
	   
	//PropertyConfigurator.configure("./log4j.properties");
	//log.info("---------------------VTMQ---------------------------------");
/*
	ApplicationContext apx 	= 	new ClassPathXmlApplicationContext("./com/waterlife/process/Water-Life-Jobs.xml"); //MOBILE JOB Started
	
	System.out.println(apx.getDisplayName());*/
		
    }

}
