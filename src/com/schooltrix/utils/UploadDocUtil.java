package com.schooltrix.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.json.JSONObject;
import org.json.JSONTokener;

public class UploadDocUtil {

	public String[] prepareParamsForUploadDoc(String[] schoolNames, String[] branchNames,
			String[] selectClass, String[] selectType, String uploadType, String fileType,
			String assignmentType, String selectSubject, String fromDate,
			String toDate) {
		// TODO Auto-generated method stub
		
		String[] resultStrings = new String[9];

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss a");
		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
		
		
		StringBuffer insmIds	 	= new StringBuffer();
		StringBuffer inbmIds	 	= new StringBuffer();
		StringBuffer inclassIds 		= new StringBuffer();
		StringBuffer inUserTypeIds 		= new StringBuffer();
		
		
		String smIDs = null;
		String bmIDs = null;
		String cmIDs = null;
		String userIDs = null;

		String fromDateNew = null;
		
		String fileTypeOrig = fileType;
		
		System.out.println(branchNames+"**branchNames.");
	
			try {
			  if (schoolNames != null) {
				  for (int i = 0; i < schoolNames.length; i++) {
					  insmIds.append(schoolNames[i]);
							if(i<schoolNames.length-1)
								insmIds.append(",");		
					 }
				  
			  }else{
				  insmIds.append( "'%'");
			  }
				
			  if (branchNames != null) {
				  for (int i = 0; i < branchNames.length; i++) {
					  inbmIds.append(branchNames[i]);
					  if(i<branchNames.length-1)
						  inbmIds.append(",");		
				  }
				  
			  }else{
				  inbmIds.append( "'%'");
			  }
			  
				System.out.println(" branchNames::"+ inbmIds);
			 if (selectClass != null) {	
				 for (int i = 0; i < selectClass.length; i++) {
					 inclassIds.append(selectClass[i]);
						if(i<selectClass.length-1)
							inclassIds.append(",");	
				}
				 
			 }else{
				 inclassIds.append( "'%'");
			  }
			 
			if (selectType != null) {
				 for (int i = 0; i < selectType.length; i++) {
					 if(i ==0)
						 inUserTypeIds.append("'");
					 
					 inUserTypeIds.append(selectType[i]);
					 
					 if(i<selectType.length-1)
						 inUserTypeIds.append("','");	

					 if(i ==(selectType.length-1))
						 inUserTypeIds.append("'");	
				 }
			}else{
				//inUserTypeIds.append( "0");...some issue...'0' and 0 while saving empty form search..already we have 0 in DB..
				inUserTypeIds.append( "%");
			  }
			
		
			
			 smIDs = insmIds+"";
			 bmIDs = inbmIds+"";
			 cmIDs = inclassIds+"";
			 userIDs = inUserTypeIds+"";
		
		
				if (uploadType.equalsIgnoreCase("0")) {
					uploadType = "%";
				}if (fileType.equalsIgnoreCase("All")) {
					fileType = "%";
				}else if (!fileType.equalsIgnoreCase("All")) {
					fileType = "%."+fileType;
				}
				System.out.println("assignmentType__________________"+assignmentType);
				System.out.println("selectSubject__________________"+selectSubject);
				if (assignmentType.equalsIgnoreCase("0")) {
					assignmentType = "%";
				}if (selectSubject.equalsIgnoreCase("0")) {
					selectSubject = "%";
				}
				
		
				String toDateNew = null;
				try {
					if (!fromDate.isEmpty()) {
						 fromDateNew = sdf1.format(sdf.parse(fromDate));
						//System.out.println(fromDate+"**"+fromDateNew);
						
					}
				} catch (Exception e1) {
					fromDateNew = null;
					e1.printStackTrace();
				}	
				try {
				//	if (toDate != "" || toDate != null) {
					if (!toDate.isEmpty()) {
						 toDateNew = sdf1.format(sdf.parse(toDate));
					//	System.out.println(toDate+"**"+toDateNew);
					}
				} catch (Exception e1) {
					toDateNew = null;
					e1.printStackTrace();
				}	
				String btCodt = "";
				//upload_date between '%' and now()--both are null
				//formation of between condition here
				//we maintain the order so...2nd con valid one...
				if (fromDateNew==null && toDateNew==null) {
					btCodt = "'%' and now()";
				} else if(toDateNew == null){
					//btCodt = "'"+fromDateNew+"' and '"+fromDateNew+"'";
					btCodt = "'"+sdf2.format(sdf.parse(fromDate))+"' and '"+addOneDay(fromDate)+"'";
				} else if(fromDateNew == null){
				//	btCodt = "'"+toDateNew+"' and '"+toDateNew+"'";
					btCodt = "'"+sdf2.format(sdf.parse(toDate))+"' and '"+addOneDay(toDate)+"'";
				}else if (fromDateNew!=null && toDateNew!=null) {
					btCodt = "'"+fromDateNew+"' and '"+toDateNew+"'";			
				}
		
		//smIDs,bmIDs,cmIDs,userIDs,uploadType,fileType,assignmentType,selectSubject,btCodt
				resultStrings[0] = smIDs;
				resultStrings[1] = bmIDs;
				resultStrings[2] = cmIDs;
				resultStrings[3] = userIDs;
				resultStrings[4] = uploadType;
				resultStrings[5] = fileType;
				resultStrings[6] = assignmentType;
				resultStrings[7] = selectSubject;
				resultStrings[8] = btCodt;
				
				return resultStrings;
		
			}catch (Exception e) {

			e.printStackTrace();
			}
		return null;
	}
			
			private String addOneDay(String fromDate) {
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss a");
				SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");	
				try {
					
					///sdf2.format(sdf.parse(fromDate))
				/*	String dt = "2008-01-01";  // Start date
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
					Calendar c = Calendar.getInstance();
					c.setTime(sdf.parse(dt));
					c.add(Calendar.DATE, 1);  // number of days to add
					dt = sdf.format(c.getTime());  // dt is now the new date
			*/		
					String newdate = sdf2.format(sdf.parse(fromDate));
					System.out.println("newdate Before***"+newdate);
					Calendar c = Calendar.getInstance();
					c.setTime(sdf2.parse(newdate));
					c.add(Calendar.DATE, 1);  // number of days to add
					newdate = sdf2.format(c.getTime());  // dt is now the new date
					System.out.println("newdate After***"+newdate);
					return newdate;
			
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					return fromDate;
				}
			}
			
			
			public String getSubjectName(String subjectJson, String subID) {
				try {
					JSONTokener jsonTokener = new JSONTokener(subjectJson);
					JSONObject jsonObject = new JSONObject(jsonTokener);
					
					return jsonObject.get(subID).toString();		
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return null;
			}
			
			

}
