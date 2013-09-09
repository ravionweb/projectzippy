package com.schooltrix.TestTemp;

import java.text.BreakIterator;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.RandomStringUtils;


import com.sun.org.apache.bcel.internal.generic.FNEG;

public class TestRandom {

	/**
	 * @param args
	 */
/*	public static void main(String[] args) {
		// TODO Auto-generated method stub
		for (int i = 0; i < 1000; i++) {
		
			String name = String.format("%s.%s", RandomStringUtils.randomAlphanumeric(10), "jpg");
			System.out.println("name--"+name);
			
		}
	}*/


	    public  String generateUniqueId() {      
	        UUID idOne = UUID.randomUUID();
	        System.out.println("id---"+idOne);
	        String str=""+idOne;        
	        String[] components = str.split("-");
	        if (components.length>4) {
	        	System.out.println("555---------"+components[4]);
				
			} else {

				System.out.println("1111----"+components[0]);
			}
			
	        
	        
	        
	        int uid=str.hashCode();
	        String filterStr="TH"+uid;
	        str=filterStr.replaceAll("-", "");//chances are dr for coming start -number with "-"
	       // System.out.println("--"+filterStr);
	       // return Integer.parseInt(str);
	         return (str);
	    }

	    // XXX: replace with java.util.UUID
	    
	    public void testtt() {
	    	String fname = "ab";
	    	String lname = "efgh";
	    	
	    	System.out.println(fname.length());
	    	System.out.println(lname.length());
	    	String gg = null;
	    	if (fname.length()>2) {
	    		 gg = fname.substring(0, 3);
			}else{
				gg = fname;
			}
			
			String ttt = lname.substring(0, 2);
	    	
			System.out.println(gg+"--------"+ttt);
			
		}
	    
	    public void ytyty() {
	    	//code to shuffle password
			List<String> list=new ArrayList<String>();
   		   	list.add("t");
  		   	list.add("r");
  		   	list.add("i");
  		   	list.add("x");
for (int i = 0; i < 10; i++) {
	StringBuffer stringBuffer=new StringBuffer();
	

  		   	Collections.shuffle(list);
  	
  		   	java.util.Iterator<String> iterator		= list.iterator();
	   		
  		   	while(iterator.hasNext()){
	   			stringBuffer.append(iterator.next());
	   		}
	   		
  		   	String pass	= stringBuffer.toString();
  		   	System.out.println(pass+"1234");
}
		}

	    public void yttttttt() {
			
	    	String domainName = "www.rs.schooltrix.com";
	    	
	    	if(domainName.toLowerCase().startsWith("www.")){
				System.out.println("www.--adding");
				
				int i = domainName.toLowerCase().indexOf("www.");
				System.out.println(i+"=====");
				if(i != -1) {								
				System.out.println("--->"+domainName.substring(i+4));
				
			}
		}
	    }
	    
	    public void Ifcond() {
			int i =0;
			if(i++ == 1 || i++ ==1){
				System.out.println("in if"+i);
			}else{
				System.out.println("in else"+i);
			}
	    	
	    	
	    	
		}
	    
	    public void emailValidation() {
	    	String rex  = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
	    			+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";	
	    	 Pattern pattern =Pattern.compile(rex);
	    	  Matcher	matcher = pattern.matcher("bhanu.fdfdf@gmail.com");
			System.out.println(matcher.matches());
	    	
	    	
		}
	    public void someValidation() {
	    	
	    			String rex  = "^\\d{3,5}([\\-]\\d{6,8})?$";	
	    	    	 Pattern pattern =Pattern.compile(rex);
	    	    	  Matcher	matcher = pattern.matcher("040-43434344");
	    			System.out.println(matcher.matches());
		}
	    public void ifBreakCheck() {
			
	    	int i = 5;
	    	
	    	y:if (i<9) {
				if (i<6) {
					if (i>1) {						
						System.out.println("in 1111111111");
						//break y;
					}
					System.out.println("in 666666");
					break y;
				}
				System.out.println("in 999999999999999");
				break y;
	    		
			} else {

				System.out.println("in ELSSSSSSSSSSSSS");
			}
	    	
	    	
		}
	    
	 
	    //test fo finnaly and return      
	     public String goT() {  
	            try {  
	               String u = "yuyuy";
	               long uuuu = Long.parseLong(u);
	               return "ok in try";
	            }  
	            catch (Exception ex) {  
	                System.out.println("Entered catch");  
	                return "ok";  
	            }finally {
	                System.out.println("Entered finally");  
	                return "finnalyOK";  
	            } 
	         //   return "Endok";  
	        }
	    
	    
	    public static void main(String[] args) {
	    	
	    	TestRandom tr = new TestRandom();
//	    	tr.testtt();
	    //	tr.ytyty();
	    	//	tr.yttttttt();
	    	//tr.Ifcond();
	    	
	    	//System.out.println(tr.goT());
	    	tr.trimtest();
	    	
	    	//tr.ifBreakCheck();
	   /*     for (int i = 0; i <102; i++) {
	            System.out.println(tr.generateUniqueId());
	            //generateUniqueId();
	        }
	   */     
	    }
		
	    private void trimtest() {
		    String hh = null;
		    if (hh == null) {
			System.out.println("one");	
			} 		
		    if (hh.equalsIgnoreCase("null") ){
				System.out.println("two");	
				} 		
		    if (hh .equals(null) ){
				System.out.println("thre");	
				} 		
		    if (hh!= null) {
				System.out.println("four");	
				} 		
			
		}

		private void dateTest() {
			// TODO Auto-generated method stub
					  // Pattern p = Pattern.compile("^[0-1][1-2][-]?(0[1-9]|[12][0-9]|3[01])[- /]?(18|19|20|21)\\d{2}$");
				/*	   Pattern p1 = Pattern.compile("^(((0[1-9]|[1]\\d|3[01])\\/(0[13578]|1[02])\\/((19|[2-9]\\d)\\d{2}))|((0[1-9]|[12]\\d|30)\\/(0[13456789]|1[012])\\/((19|[2-9]\\d)\\d{2}))|((0[1-9]|1\\d|2[0-8])\\/02\\/((19|[2-9]\\d)\\d{2}))|(29\\/02/((1[6-9]|[2-9]\\d)(0[48]|[2468][048]|[13579][26])|((16|[2468][048]|[3579][26])00))))$");
					   Matcher m = p1.matcher(date);
					   System.out.println(m.find());*/
					  // String[] formatStrings = {"M/y", "M/d/y", "M-d-y"};
					   //String[] formatStrings = {"M/y", "M/d/y", "M-d-y"};
					   //String[] formatStrings = {"dd.M.yyyy", "M/dd/yyyy", "dd-M-yyyy","M-dd-yyyy","yyyy-dd-MM","yyyy-MM-dd"};
					// ...
		//	String date = "26-04-2013";
		//	String date = "26/04/2013";
		//	String date = "6-4-13";
			String date = "26-04-2013";
					 //  String[] formatStrings = {"dd-MM-yyyy","dd-MM-yy","dd/MM/yyyy","dd.MM.yyyy","dd MM yyyy"};
					String[] formatStrings = {"dd-MM-yyyy"};
			
					    for (String formatString : formatStrings)
					    {
					        try
					        {
					        	
					        	SimpleDateFormat ty = new SimpleDateFormat(formatString);
					        	SimpleDateFormat Ori = new SimpleDateFormat("yyyy-MM-dd");
					        	Date ff = ty.parse(date);
					        	
					        	System.out.println(ff);					        	
					            System.out.println("truee--"+formatString+"------>"+Ori.format(ff));
					        }
					        catch (Exception e) {//System.out.println("false"+formatString);
					        	}
					        }
					    }

	}
	

