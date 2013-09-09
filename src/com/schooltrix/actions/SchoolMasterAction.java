/**
 * 
 */
package com.schooltrix.actions;

import com.opensymphony.xwork2.ActionSupport;

/**
 * @author bhanu
 *
 */
public class SchoolMasterAction extends ActionSupport {
	
	
		 public String editUser(
							String fname,
							String lname, 
							String country,
							String postalCode,
							String countryCode,
							String city,
							String phoneNumber,
							String newEmail,
							String oldpwd,
							String newpwd,
							String action
						) 
			{/*
			
			final String MOBILE_PATTERN = "^[0-9]{10,12}$";
			final String EMAIL_PATTERN  = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,4})$";
			
			
			String data	= "fail";
			
				  
					try{
							HttpSession session = request.getSession();
							String email=(String)session.getAttribute("userid");
							
							Pattern emailpattern;
							Matcher emailmatcher;
							Pattern mobilepattern;
							Matcher mobilematcher;
							
							emailpattern = Pattern.compile(EMAIL_PATTERN);   
							emailmatcher = emailpattern.matcher(email);
							boolean emailmatch=  emailmatcher.matches();
							
							mobilepattern = Pattern.compile(MOBILE_PATTERN);
							mobilematcher = mobilepattern.matcher(phoneNumber);
							boolean mobilematch=  mobilematcher.matches(); 
							
								if((StringUtils.isEmpty(email)||!emailmatch)&& action.equalsIgnoreCase("UE")){
									return "13";
								}
								if((StringUtils.isEmpty(phoneNumber)||!mobilematch)&& action.equalsIgnoreCase("UP")){
									return "14";
								}
								if(countryCode.equalsIgnoreCase("")){
									  countryCode    = "91";    
								}
										  
							
							Registrationdto registDto = new Registrationdto();
							registDto.setEmail(email);
							
							registDto.setFname(fname);
							registDto.setLname(lname);
							registDto.setCountry(country);		
							registDto.setPostalcode(postalCode);	
							registDto.setCountryCode(Integer.parseInt(countryCode));
							registDto.setCity(city);
							registDto.setPhoneNumber(phoneNumber);
							registDto.setNewemail(newEmail);
							registDto.setPwd(oldpwd);
							registDto.setNewpwd(newpwd);
							
							if("I".equalsIgnoreCase(action)){
							 registDto.setEmail(newEmail);
							 
							}
							
							RegistrationDAO registrationDAO = (RegistrationDAO) ServiceFinder.getContext(request).getBean("registrationHibernateDao");
							registrationDAO.createSession();
							data = registrationDAO.editUser(registDto, action);
							// System.out.println("data-->"+data);
							if(data != null){
								if(data.equals("success")||data=="success")
								{
									String countryisd = registrationDAO.getCountryISD(email);	
									session.setAttribute("countryisd", countryisd);				
								}else if(data.equalsIgnoreCase("5")){
									//on successfull email update sql return status code 5
									session.setAttribute("userid", newEmail);	
									changeEmail(email,newEmail);
								}else if(data.equalsIgnoreCase("7")){
									//on successfull pwd update sql return status code 7
									changePassword(newpwd,oldpwd);
									
								}
							 
							}
							registrationDAO.releseSession();
					}catch(Exception e){
							e.printStackTrace();
							}  
							return data;
							
			*/
			 return "data";
			} 
	
}
