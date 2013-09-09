	<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	    pageEncoding="ISO-8859-1"%>
	
<script 	src="<%=request.getContextPath()%>/dwr/interface/InstitutionMasterDWR.js"></script>
<%-- <script 	src="<%=request.getContextPath()%>/dwr/interface/InstitutionMasterDWR.js"></script> --%>
<%-- <script 	src="<%=request.getContextPath()%>/dwr/interface/HomePageDWR.js"></script> --%>

<%
	if(session.getAttribute("superadmin") != null){
			try{
					String superadmin =(String) session.getAttribute("superadmin");
					 String userType = (String)session.getAttribute("userType");
					 
				/*	String UM_ID = (String)session.get("UM_ID");
					String UT_ID = (String)session.get("UT_ID");
					String fName = (String)session.get("fName");
					String lName = (String)session.get("lName");
					String active = (String)session.get("active"); */
					
					if(superadmin.equalsIgnoreCase("Y") && userType.equalsIgnoreCase("SA")){
					}else{
					response.sendRedirect("BeforeLogin.action");	
					///jsp/HomePage.jsp				
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				
		}else{
					response.sendRedirect("BeforeLogin.action");					
		
		}

 %>

	
	<script type="text/javascript">
	function getFranchiseMaster(){
			   try{
						var listoffranchise = document.getElementById('franchise');					
	
						    InstitutionMasterDWR.getFranchiseMasterDetails(function(data){
						                                if (data == null) {													
						                                  alert("error");
						                                 
														} else {				
														//alert(data+":::"+data.length);	                                 
						                               for(var i = 0; i < data.length; i++) {
															  var opt = document.createElement("option");
															    var temp = data[i];
															    opt.value = temp[0];
															    opt.innerHTML = temp[1];
															    listoffranchise.appendChild(opt);
															}		                          
														}
						                         }
						      ) ;  
						
		   		 }catch(e){
						 alert("incatch::"+e);
						        jQuery.log.info(e.message);
						        jQuery("#infoError").html("&nbsp;");
		         }
	}
	
	function uniqueIMShortNameCheck(){
			var shortName = jQuery("#insSName").val();
			
			InstitutionMasterDWR.isIMShortNameCheck(
													shortName,
													function(data){								
					                                   if (data =="false") {
					                                  // alert("not found"+data);											
														} else {
						                                     setError("insSName","Short Name Already Exist");
						                                   		jQuery("#insSName").focus();			                              		                                   													
														}
					                        	 }
					      ); 
					
		}	
	
	 $(document).ready(function () {
			getFranchiseMaster();

		});
	
		function validateInstitutionMaster(){
	
	//var patEmail	= /^([\w\-\.]+)@((\[([0-9]{1,3}\.){3}[0-9]{1,3}\])|(([\w\-]+\.)+)([a-zA-Z]{2,4}))$/;
			//to validate only lower chars  var reg = /^[_a-z0-9-]+(\.[_a-z0-9-]+)*@[a-z0-9-]+(\.[a-z0-9-]+)*(\.[a-z]{2,4})$/;
		    var patEmail	=/^\w+([-+.']\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/;
		    var  patName 	= /^[a-zA-Z \']{3,25}$/;
			var  patZipCode	= /^[0-9]{3,6}$/;
			var  insName 	= jQuery("#insName").val();
			var  insSName 	= jQuery("#insSName").val();
			var  insAddr 	= jQuery("#insAddr").val();
			var  insCity 	= jQuery("#insCity").val();
			var  insCountry 	= jQuery("#insCountry").val();
			var  insStates 	= jQuery("#insStates").val();
			var  insContPerson 	= jQuery("#insContPerson").val();
			var  insEmailID 	= jQuery("#insEmailID").val();
			var  insMobile 		= jQuery("#insMobile").val();
			var  insLandLine 	= jQuery("#insLandLine").val();
			var  franchise 	= jQuery("#franchise").val();
			//var  insActive 	= jQuery("#insActive").val();
		//	var  insActive 	=jQuery('#insActive').prop('checked') ? 'Y':'N';
			var  insActive;
			 if ($('#insActive').is(":checked"))
						{insActive='Y';
						  // it is checked
			} else{insActive='N';}


			
		//validate	
		    insName = $.trim(insName);
		   // alert("sS a.a".search(/^[a-zA-Z][a-zA-Z '.]+[a-zA-Z]$/));//if (insName.search(/^[a-zA-Z][a-zA-Z ]*['.]?[a-zA-Z ]+[a-zA-Z ]+$/) == -1 || insName =="") {
			if (insName.search(/^([a-zA-Z]+\s)*[a-zA-Z]*['. ]?[ ]?([a-zA-Z]+[.]?\s?)*[a-zA-Z]+$/) == -1 || insName =="") {
			  // alert("Enter valid school Name");
			   setError("insName","Enter valid school Name");
               jQuery("#insName").focus();
	         return false;
            }
            
            insSName = $.trim(insSName);
			if (insSName.search(/[^a-zA-Z]+/) != -1 || insSName =="") {
			  // alert("Short Name should contain only characters");
			   setError("insSName","Short Name should contain only characters");
                jQuery("#insSName").focus();
	         return false;
            }
            //if (insAddr.search(/^[a-zA-Z0-9\s,'-]*$/) != -1 || insAddr =="") {
            if (insAddr.search(/[a-z-A-Z][a-zA-Z0-9\s,'-.#@$;:]*$/)== -1 || insAddr =="") {
			   //alert("Enter address in valid format");
			   setError("insAddr","Enter address in valid format");
                jQuery("#insAddr").focus();
	         return false;
            }
            insCity = $.trim(insCity);
            if (insCity.search(/^[a-zA-Z][a-zA-Z ]*['.]?[a-zA-Z ]+[a-zA-Z ]+$/) == -1 || insCity =="") {
			 //  alert("Enter valid City Name");
			   setError("insCity","Enter valid City Name");
               jQuery("#insCity").focus();
	         return false;
            }
            
          if(insCountry=="select"){
             // alert("Please select country");
              setError("insCountry","Please select country");
              jQuery("#insCountry").focus();
              return false;
           }  
           if(insStates=="select"){
             // alert("Please select country");
              setError("insStates","Please select State");
              jQuery("#insStates").focus();
              return false;
           }
            
            insContPerson = $.trim(insContPerson);
			if (insContPerson.search(/^[a-zA-Z]([a-zA-Z]+\s)*[a-zA-Z]+$/) == -1 || insContPerson =="") {
			 //  alert("Please Enter valid Contact person Name");
			    setError("insContPerson","Please Enter valid Contact person Name");
               jQuery("#insContPerson").focus();
	         return false;
            }
           insEmailID = $.trim(insEmailID); 
           if (insEmailID.search(patEmail) == -1 || insEmailID =="") {
			   //alert("Enter valid e-mail");
			   setError("insEmailID","Enter valid e-mail");
               jQuery("#insEmailID").focus();
	         return false;
            }
            insMobile  =$.trim(insMobile);
            if (insMobile.search(/^[0]?[789]\d{9}$/) == -1 || insMobile =="") {
			   //alert("Enter valid Mobile No.");
			   setError("insMobile","Enter valid Mobile No.");
               jQuery("#insMobile").focus();
	         return false;
            }
            
             insLandLine  =$.trim(insLandLine);
     /*        if (insLandLine.search(/^\d{3,5}(\d{6,8})?$/) == -1 || insLandLine =="") {
			   //alert("Enter valid Landline No.");
			   setError("insLandLine","Enter valid Landline No.");
               jQuery("#insLandLine").focus();
	         return false;
            } */
            
  
           if(franchise== "-1"){
             // alert("Please select country");
              setError("franchise","Please select franchise");
              jQuery("#franchise").focus();
              return false;
           }  
           
           if(insActive=="N"){
              //alert("Please select check box");
              setError("insActive","Please select check box");
              jQuery("#insActive").focus();
              return false;
           }
  
		}
		
		function okAlertDialog () {
			jQuery("#idAlertDialog").modal ('hide'); 
		};
		function alertDialog (prompt) {
			document.getElementById ("idAlertDialogPrompt").innerHTML = prompt;
			jQuery("#idAlertDialog").modal ("show");
		}
		
			var errorDisp=null;
       function setError(errElement, msg){
			if(errorDisp != null){
				errorDisp.stop(true, true).animate({opacity: 1}, 0);
				errorDisp.hide();
			}

			var feild 		= jQuery("#"+errElement);
				errorDisp 	= jQuery("#errorspan");
			
			errorDisp.show();											
			jQuery("#errorspan").html(msg);

			errorDisp.position({
				of:feild,
				my:"left" 	+ " " + "top",
				at:"right" 	+ " " + "top",
				offset:'0 0',
				collision : 'none none'
				
				
			});
			errorDisp.animate({opacity: 0}, 6000);
	  }
	
	    function resetForm($form) {
		    $form.find('input:text, input:password, input:file, textarea').val('');
		       jQuery("#country").val('India');
		       jQuery("#franchise").val('Select Franchise');
		       jQuery("#insStates").val('Andhra Pradesh');
		    
		    $form.find('input:radio, input:checkbox')
		         .removeAttr('checked').removeAttr('selected');
		}
		
		function saveInstitutionMasterAndGo(flag){
			var retrnvalue=validateInstitutionMaster();
			
			if(retrnvalue == "false" || retrnvalue == false)
			{
			return false; 
			}
		
			var  insName 	= jQuery("#insName").val();
			var  insSName 	= jQuery("#insSName").val();
			var  insAddr 	= jQuery("#insAddr").val();
			var  insCity 	= jQuery("#insCity").val();
			var  insStates 	= jQuery("#insStates").val();			
			var  insContPerson 	= jQuery("#insContPerson").val();
			var  insEmailID 	= jQuery("#insEmailID").val();
			var  insMobile 		= jQuery("#insMobile").val();
			var  insLandLine 	= jQuery("#insLandLine").val();
			var  franchise 	= jQuery("#franchise").val();
		//	var  insActive 	=jQuery('#insActive').prop('checked') ? 'Y':'N';
			var  insActive;
			 if ($('#insActive').is(":checked"))
						{insActive='Y';
						  // it is checked
			} else{insActive='N';}
	

			//Process DWR/AJAX request here
			
			try{
			//alert("in alrttt--saveIns-----"+insStates+"-----"+insLandLine+"--flag--"+flag);
			        InstitutionMasterDWR.saveInstitution(
				                                   insName,
				                                   insSName,
				                                   insAddr,
				                                   insCity,
				                                 	insStates,
				                                   insContPerson,
				                                   insEmailID,
				                                   insMobile,
				                                   insLandLine,
				                                   franchise,
				                                   insActive,
				                                   function(data){
				                                   if (data =='saved') {
				                                   //alertDialog("save");
													if(flag == 2){
				                                       document.location ="Access.action?p1=AddAdminUser&p2="+insSName;//for by defult selection
				                                        resetForm($('#form1')); // by id, recommended
				                                       }else if(flag ==1){
				                                       alertDialog("Success.<br/>Instituiton Name:<br/>"+insName);
				                                      // alert("wr to go");
				                                        resetForm($('#form1')); // by id, recommended
				                                     }
				                                       
													}else {
					                                   alert("exception::"+data);	
													}
					       						}
				                              ) ;    
				 }catch(e){
				 alert("incatch::"+e);
				        jQuery.log.info(e.message);
				        jQuery("#infoError").html("&nbsp;");
        	     } 		
		}
			
				function changeCountry(value){
					document.getElementById("displaySubjecti").style.display="none";
					document.getElementById("displaySubjects").style.display="none";
						if(value == "India"){
							document.getElementById("displaySubjects").style.display="block";
							}
						else{
							document.getElementById("displaySubjecti").style.display="block";
							
						}
				}

</script>
	
<div style="height:40px;"></div>
<div class="reg_mainCon">
  <form  id="form1">
    <fieldset>
    <legend><img src="img/list_add_user.PNG" class="img-circle">&nbsp;&nbsp;Create Institution</legend>
    <div style="padding:20px;">
       <table width="100%" border="0" cellspacing="0" cellpadding="0">
            <tr><td colspan="2" style="padding-left: 260px;"><span id ="errorspan" style="color:red" ></span>&nbsp;</td></tr>
             <tr>
                <td>
                    <label style="color:#000;"><b>Institution Name</b></label>
                  <input class="span3" type="text" placeholder=""  name="insName" id="insName"></td>
          <td>
                    <label style="color:#000;"><b>Institution Short Name</b></label>
                    <input class="span3" type="text" placeholder=""  name="insSName" id="insSName" onblur="uniqueIMShortNameCheck();">              </td>
        </tr>
            <tr>
                <td colspan="2">
                <label style="color:#000;"><b>Address</b></label>     
                <textarea style="width:82%;" rows="3"  name="insAddr" id="insAddr"></textarea>
                </td>
            </tr>
            <tr>
                <td>
                    <label style="color:#000;"><b>City</b></label>
                    <input class="span3" type="text" placeholder=""  name="insCity" id="insCity">                </td>
           <!--    
                <td>
                    <label style="color:#000;"><b>Country</b></label>
                    <input class="span3" type="text" placeholder=""  name="insCountry" id="insCountry">                </td>
            </tr>
            <tr>
               <td>
                    <label style="color:#000;"><b>State</b></label>
                    <input class="span3" type="text" placeholder=""  name="insState" id="insState">                </td>
        -->
            <td ><label style="color:#000;"><b>Country</b></label>            
             <select  class="span3"  name="select" name="insCountry"  id="insCountry"><!--  onchange="changeCountry(this.value)"  -->
				   <option value="India"  selected="selected">India</option><option value="Indonesia">Indonesia</option>
             </select> 
           </td>
        </tr><tr>
      <!--     <td ><label style="color:#000;"><b>State</b></label></td>
       
          <td ><div align="left">
            <input class="span3" type="text" placeholder="" name="state"  id="state">
          </div>
           -->
          		  <td ><label style="color:#000;"><b>State</b></label>			    
			    <div id="displaySubjecti" style="display:none;">
					    <input  class="span3" type="text"  id="insStatei" name="insStatei"  />
				</div>
				<div id="displaySubjects" style="display:show;">
					 <select   class="span3" id="insStates" name="insStates">
				   <option value="1">Andhra Pradesh</option>
                   <option value="2">Madya pradesh</option>
				   <option value="3">Bihar</option>
				   </select>				
             </div>
          </td>
                <td>
                    <label style="color:#000;"><b>Contact Person</b></label>
                    <input class="span3" type="text" placeholder=""  name="insContPerson" id="insContPerson"></td>
            </tr>
            <tr>
                <td>
                    <label style="color:#000;"><b>Email ID</b></label>
                    <input class="span3" type="text" placeholder=""  name="insEmailID" id="insEmailID"></td>
                <td>
                    <label style="color:#000;"><b>Mobile</b></label>
                    <input class="span3" type="text" placeholder=""  name="insMobile" id="insMobile"></td>
            </tr>
            <tr>
                <td>
                    <label style="color:#000;"><b>Landline</b></label>
                    <input class="span3" type="text" placeholder=""  name="insLandLine" id="insLandLine"></td>
             
             <td> <label style="color:#000;"><b>Franchise</b></label>            
             <select  class="span3"  name="select" name="franchise"  id="franchise"><!--  onchange="changeCountry(this.value)"  -->
				  <option value="-1" >Select Franchise</option>
				 <!--  <option value="1"  selected="selected">Schooltrix</option> -->
             </select>  </td></tr>
                <tr ><td rowspan="1">
                    <label style="color:#000;"><b>Active</b></label> <input type="checkbox"  name="insActive" id="insActive" checked="checked">
                    </td>
            </tr>
            <tr><td>&nbsp;</td></tr>
            <tr>
             <td style="border:none;">
                <button type="button" class="btn btn-warning" onclick="saveInstitutionMasterAndGo(1);">Save</button> 
                <button type="button" class="btn btn-warning"  onclick="saveInstitutionMasterAndGo(2);">Save and Add User</button></td>
            <td style="border:none;">&nbsp;</td>
        </tr>
        </table>
    </div>
    </fieldset>
  </form>
</div>
<div style="height:20px;"></div>

<!-- /container -->
 <div id="idAlertDialog" class="modal hide fade" style="height: 200px;" tabindex="-1" role="dialog" aria-labelledby="windowTitleLabel" aria-hidden="true">
            <img src="img/alert.gif" width="34" height="28" alt="alert icon">
            <h3 id="idAlertDialogPrompt"></h3>
            <a href="#" class="btn btn-primary" style="margin-top: 0px;" onclick="okAlertDialog ();">OK</a>
            </div>

