package com.schooltrix.engine;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;


public class UploadDocJobInit extends QuartzJobBean{

	@Override
	protected void executeInternal(JobExecutionContext arg0) throws JobExecutionException {
		// TODO Auto-generated method stub
		String channel 	= 	(String) arg0.getJobDetail().getJobDataMap().get("channel");	
		System.out.println("Compass Pointer Live Sell Calls Started::"+channel);
		
		if(channel.equalsIgnoreCase("email")){
		UploadDocProcess uploadDocProcess = new UploadDocProcess();	
		uploadDocProcess.callingEmailJob();
		
		}else if(channel.equalsIgnoreCase("sms")){
			UploadDocProcess uploadDocProcess = new UploadDocProcess();
			uploadDocProcess.callingSMSJob();
		
		}
		System.out.println("Compass Pointer Live Sell Calls Ended::"+channel);
	}

}
