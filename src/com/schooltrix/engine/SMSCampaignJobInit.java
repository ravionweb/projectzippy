package com.schooltrix.engine;

import java.sql.SQLException;
import java.util.Date;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

public class SMSCampaignJobInit extends QuartzJobBean{

    @Override
    protected void executeInternal(JobExecutionContext arg0)throws JobExecutionException {
		try{
		    
		    test();
		    System.out.println("--------------------------------------------------------------------------");
		}catch(Exception _ex){
		    _ex.printStackTrace();	    
		}
	    
    }

    private static synchronized void test() throws SQLException {
	
	System.out.println("SMS JOB Started::"+new Date());

	new SchooltrixManager().processSMS();
	
	System.out.println("SMS JOB End...."+new Date());
    
    }



}
