	<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	    pageEncoding="ISO-8859-1"%>
    	
    	<%
    	//String tempShortname =(String) session.getAttribute("shortNameTemp");//this is for auto select of institute from InstitutionAdding
    	String par1= "";
    	   	System.out.println("******tt**"+ request.getAttribute("p2"));
    	   	if( request.getAttribute("p2") != null){
    	   	
    	   	par1 = (String) request.getAttribute("p2");
    	   	System.out.println("********"+par1);
    	   	
    	   	}
    	 %>
    	
    	
    	
    	
    	<!-- css -->
	    <link href="css/bootstrap-fileupload.css" rel="stylesheet" media="screen">	   
    	<link href="css/datepicker.css"  rel="stylesheet" type="text/css" media="screen"  >
    	<style type="text/css">
		span.mandatory {
		    color: #FF0000;
		    display: inline;
		}

			
.divDemoBody  {
                width: 60%;
                margin-left: auto;
                margin-right: auto;
                margin-top: 100px;
                }
            .divDemoBody p {
                font-size: 18px;
                line-height: 140%;
                padding-top: 12px;
                }
            .divDialogElements input {
                font-size: 18px;
                padding: 3px;
                height: 32px;
                width: 500px;
                }
            .divButton {
                padding-top: 12px;
                }
            .btn {
                /*width: 80px;*/
                margin-left: 5px;
                }
             #idAlertDialog {
                width: 450px;
                height: 225px;
                background-color: whitesmoke;
                }
             #idAlertDialog .btn {
                width: 70px;
                margin-bottom: 8px;
                margin-right: 155px;
                margin-top: 45px;
                float: right;
                }
             #idAlertDialog h3 {
            
                margin-top:15px;
                }
             #idAlertDialog img {
                float: left;
                margin-left: 15px;
                margin-top: 15px;
                }
                #idConfirmDialog {
				width: 450px;
				height: 165px;
				background-color: whitesmoke;
				}
			 #idConfirmDialog .btn {
				width: 70px;
				margin-bottom: 8px;
				margin-right: 15px;
				margin-top: 80px;
				float: right;
				}
			 #idConfirmDialog h3 {
				margin-left: 60px;
				margin-top:15px;
				}
			 #idConfirmDialog img {
				float: left;
				margin-left: 15px;
				margin-top: 15px;
				}
</style>
    	
    	
    <!-- 	 <script type="text/javascript"
     src="http://cdnjs.cloudflare.com/ajax/libs/jquery/1.8.3/jquery.min.js">
    </script>
    <script type="text/javascript"
     src="http://netdna.bootstrapcdn.com/twitter-bootstrap/2.2.2/js/bootstrap.min.js">
    </script> -->
   
  
 	  <!-- 
      <script type="text/javascript"
     src="http://netdna.bootstrapcdn.com/twitter-bootstrap/2.2.2/js/bootstrap.min.js">
     </script>
         --><!--  <script type="text/javascript"
     src="http://tarruda.github.com/bootstrap-datetimepicker/assets/js/bootstrap-datetimepicker.min.js">
    </script> -->
    
	    <!-- javascripts -->
	    <script src="js/bootstrap-datetimepicker.min.js"> </script>
    	<script  src="js/bootstrap-datetimepicker.pt-BR.js"> </script>
    	
	    <script src="js/bootstrap-fileupload.js" type="text/javascript"></script>
    	
    	<!-- DWR calls -->
	    <script 	src="<%=request.getContextPath()%>/dwr/interface/UserTypeMasterDWR.js"></script>
	    <script 	src="<%=request.getContextPath()%>/dwr/interface/UserMasterDWR.js"></script>
	    <script 	src="<%=request.getContextPath()%>/dwr/interface/EmailDomainDWR.js"></script>
		<script type="text/javascript">
		
		
/*       			
      			function changeCountry(value){
					document.getElementById("displaySubjecti").style.display="none";
					document.getElementById("displaySubjects").style.display="none";
						if(value == "India"){
							document.getElementById("displaySubjects").style.display="block";
							}
						else{
							document.getElementById("displaySubjecti").style.display="block";
							
						}
				} */
      			
    	</script>
	    <script>
	    var userTypes;
   
	   	function uniqueUserIDCheck(){
			var im_id_list = jQuery("#im_id_list").val();
			var userID = jQuery("#userID").val();
			var patUser			= /^[a	-zA-Z0-9-. ]{2,25}$/;			
			
				if(im_id_list=="-1"){
			  setError("im_id_list","Please select Institution Name");
               jQuery("#im_id_list").focus();
				return false;
				}
				
				if(!userID.match(patUser)){
				  setError("userID","Please enter valid UserId");
               jQuery("#userID").focus();
				return false;
				}
			if (userID != "" && userID != null) {//some more validations req this filed
			UserMasterDWR.isUserIDCheck(
													"AD"+userID,
													im_id_list,
													function(data){								
					                                   if (data =="false") {
					                                  // alert("not found"+data);											
														} else {			
														  setError("userID","User Id Already Exist");
											               jQuery("#userID").focus();
														}
					                        	 }
					      ); 
				}
		}	
	   
	   function uniqueEmailCheck(){
	   
			var emailID = jQuery("#email").val();
	   		var im_id_list = jQuery("#im_id_list").val();
			var userType = jQuery("#userRights").val();
	   			var patEmail		= /^\w+([-+.']\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/;
			
				if(im_id_list=="-1"){
			  setError("im_id_list","Please select Institution Name");
               jQuery("#im_id_list").focus();
				return false;
				}
				
				if(!emailID.match(patEmail)){
				  setError("email","Please Enter valid e-mail");
               jQuery("#email").focus();
				return false;
				}
				
				
			EmailDomainDWR.isEmailCheck(
													emailID,
													userType,
													im_id_list,
													function(data){								
					                                   if (data =="false") {
					                                  // alert("not found"+data);											
														} else {			
														 setError("email","Email Id Already Exist");
											               jQuery("#email").focus();
														}
					                        	 }
					      ); 
					
		}	
	   
	    function isParentCheck() {
      	
			if(document.getElementById("isParent").checked == true) {
				document.getElementById("parentTypeSel").disabled=false;		
				document.getElementById("parentTypeSel").style.display="block";
			}else {
				document.getElementById("parentTypeSel").style.display="block";						
				document.getElementById("parentTypeSel").disabled=true;	
			}
		}
		

				    
	
	    function addUserFormSubmit(){
	   // alert(document.getElementById("city").value);
	   // document.getElementById("addform").submit();
	    form = document.forms[addform]; //assuming only form.
		form.submit();
	    }
	    
	    function validateAdminUser(addAdminUser){
	    
	  	   // var patEmail	= /^([\w\-\.]+)@((\[([0-9]{1,3}\.){3}[0-9]{1,3}\])|(([\w\-]+\.)+)([a-zA-Z]{2,4}))$/; previous					
		//var patName		=/^[a-zA-Z-. ]{2,25}$/; previous
		var patEmail		= /^\w+([-+.']\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/;
		var patName 		= /^[a-zA-Z]([a-zA-Z]+\s)*[a-zA-Z]+$/;
		var patUser			= /^[a	-zA-Z0-9-. ]{2,25}$/;
		var patAddress		= /^[a-zA-Z_0-9@\!#\$\^%&*()+=\-[]\\\';,\.\/\{\}\|\":<>\? ]{2,25}$/;
		var patNum			= /^[0-9-+ ]{8,15}$/;
	//	var patBody	 		= /^[A-Za-z0-9 ].+$/;//previous
		var patBody			= /[a-z-A-Z][a-zA-Z0-9\s,'-.$#@:;]*$/;
		var patcity  		= /^[a-zA-Z][a-zA-Z ]*['.]?[a-zA-Z ]+[a-zA-Z ]+$/;
		var patDob			= /^[0-9- ]{10}$/;
		var patPassword		= /^[a-zA-Z0-9]{4,15}$/;
  	
  
                var im_id_list   		=   jQuery("#im_id_list").val();
                var userID				=	$.trim(jQuery("#userID").val()).match(patUser);	
				var  fname 				= 	$.trim(jQuery("#fname").val()).match(patName);
				var password			=   jQuery("#password").val().match(patPassword);
				var passwordValue 		=   jQuery("#password").val();
				var cpassword			=   jQuery("#cPassword").val().match(patPassword);
				var cpasswordValue		=   jQuery("#cPassword").val();
				var  lname 				= 	$.trim(jQuery("#lname").val()).match(patName);
				var  dob 				= 	$.trim(jQuery("#dob").val()).match(patDob);
				var  userRights 		= 	jQuery("#userRights").val();
				var  email 				= 	$.trim(jQuery("#email").val()).match(patEmail);
				var  mobile 			= 	$.trim(jQuery("#mobile").val()).match(patNum);
				var  landline 			= 	$.trim(jQuery("#landline").val()).match(/^\d{3,5}(\d{6,8})?$/);
				var  addr1 				= 	$.trim((jQuery("#addr1").val())).match(patBody);
				var  addr2 				= 	$.trim(jQuery("#addr2").val()).match(patBody);				
				var  city 				= 	$.trim(jQuery("#city").val()).match(patName);
				var  designation 		= 	$.trim(jQuery("#designation").val()).match(patName);
				var state				= 	jQuery("#state").val();
				var country=jQuery("#country").val();
				
			//	var parentTypeSel		=	jQuery("#parentTypeSel").val();
			/* 	var admissionNumber		=   jQuery("#admissionNumber").val().match(patNum);
				var admissionDate		=   jQuery("#admissionDate1").val().match(patDob);
				var classAdmittedIn		=   jQuery("#classAdmittedIn").val().match(patBody); */
				//var  isParent 				=	jQuery("#isParent").prop('checked') ? 'Y':'N';
				var  active 				=	jQuery("#active").prop('checked') ? 'Y':'N';
				
				
				if(im_id_list=="-1"){
			  setError("im_id_list","Please select Institution Short Name");
               jQuery("#im_id_list").focus();
				return false;
				}
				if(!userID){
				  setError("userID","Please enter valid UserId");
               jQuery("#userID").focus();
				return false;
				}
				if(!password){
				  setError("password","Password should be minimum 4 charcters and should contain only Alphanumeric characters");
               jQuery("#password").focus();
				return false;
				}
			if(cpasswordValue != passwordValue){
				  setError("cpasswordValue","Confirm Password and Password must match");
               jQuery("#cpasswordValue").focus();
				return false;
				}
				if(!fname){
				  setError("fname","please enter valid First Name");
               jQuery("#fname").focus();
				return false;
				}
				if(!lname){
				  setError("lname","please enter valid Last Name");
               jQuery("#lname").focus();
				return false;
				}
				if(userRights=="Select"){
				  setError("userRights","please select User Rights ");
               jQuery("#userRights").focus();
				return false;
				}
				 if(!dob){
				   setError("dob","please enter valid DOB");
               jQuery("#dob").focus();
				return false;
				}
				if(!designation){
				  setError("designation","please enter valid Designation");
               jQuery("#designation").focus();
				return false;
				} 
				
				if(!email){
			  setError("email","Enter valid e-mail");
               jQuery("#email").focus();
				return false;
				}
				if(!mobile){
				  setError("mobile","please enter valid Mobile ");
               jQuery("#mobile").focus();
				return false;
				}
				/*	if(!landline){
				  setError("landline","please enter valid Landline ");
               jQuery("#landline").focus();
				return false;
				} */
				/*	if(isParent=="Y"  && parentTypeSel=="Select"){
				  setError("parentTypeSel","please select Parent Type");
               jQuery("#parentTypeSel").focus();
				return false;
				} */
			
				if(!addr1){
				  setError("addr1","please enter valid Address 1");
               jQuery("#addr1").focus();
				return false;
				}
				if(!addr2){
				  setError("addr2","please enter valid Address 2");
               jQuery("#addr2").focus();
				return false;
				}
				if(!city){
				  setError("city","'please enter valid City");
               jQuery("#city").focus();
				return false;
				}
				if(country=="Select"){
				  setError("country","please select Country");
               jQuery("#country").focus();
				return false;
				}
				if(state=="Select"){
				  setError("state","please select State");
               jQuery("#state").focus();
				return false;
				}
				if (!validateForm()) {
					return false;
				}
				//return false;
			 if(active=="N"){
              //alert("Please select check box");
              setError("active","Please select check box");
              jQuery("#active").focus();
              return false;
           }
				
				
				    
	    }
	       
	    function validateForm() {
			var file1  = jQuery("#fileUP").val();
			if(file1 == ""){
			  setError("fileUP","Please select a photo");
               jQuery("#fileUP").focus();
				return false;
			}
			var extension = file1.split('.').pop().toLowerCase();
			var allowed = ['gif','jpeg','pjpeg','png','jpg'];
			if(allowed.indexOf(extension) === -1) {
				//alertDialog("Your selected "+ file1+"  extension wrong"); previous
				  setError("fileUP","Only gif / jpeg / pjpeg / png formats are allowed");
               jQuery("#fileUP").focus();
				return false;
			}else{
    			return true;
			}
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
		       jQuery("#state").val('Andhra Pradesh');
		    
		    $form.find('input:radio, input:checkbox')
		         .removeAttr('checked').removeAttr('selected');
		}
	    
	    </script>
	    
<div class="reg_mainCon">
 <form action="addadminuser.action" method="post" enctype="multipart/form-data"  name="addAdminUser"  onsubmit="return validateAdminUser(this)"> 
    <fieldset>
    <legend><img src="img/list_add_user.PNG" class="img-circle">&nbsp;&nbsp;Add ADMIN</legend>
    <div style="padding:20px;">
      <table width="100%" border="0" cellspacing="0" cellpadding="0">
            <tr><td colspan="5" style="text-align: center;"><span id ="errorspan" style="color:red" ></span>&nbsp;</td></tr>
          <tr>
	          <td style="text-align:center" colspan="2">
	          <label style="color:#000;margin-left: 100px;"><b>Institution Short Name</b></label>                 
	          </td>
			<td></td>
			<td colspan="2"><select id="im_id_list" name="im_id_list" class="span3"><!--  onchange="changeCountry(this.value)" -->
               <option selected="selected" value="-1">Select</option>
              </select> </td>
		</tr>
         
        <tr>
          <td width="22%"><label style="color:#000;"><b>User ID</b></label></td>
          <td width="26%">
          <div class="input-prepend" align="left">
				<span class="add-on">AD</span>
				<input class="span3" style="width: 230px;" id="userID" name="userID"  type="text" placeholder="User ID" onblur="uniqueUserIDCheck();">
			</div>
      <!--     <div align="left">
            <input class="span3" type="text" placeholder="" name="userID"  id="userID" onblur="uniqueUserIDCheck();">
          </div></td> -->
          
          
          <td >&nbsp;</td>
         <td width="18%"><label style="color:#000;"><b>User Rights</b></label></td>
          <td width="30%"><select class="span3"  name="userRights"  id="userRights">
              <option>Select</option>
              <option value="admin" selected="selected" >Admin</option>
                </select></td>
        </tr>  
        <tr>
          <td ><label style="color:#000;"><b>Password</b></label></td>
          <td ><div align="left">
            <input class="span3" type="password" placeholder="" name="password"  id="password">
          </div></td>
          <td width="4%">&nbsp;</td>
          <td ><label style="color:#000;"><b>Confirm Password</b></label></td>
          <td ><input class="span3" type="password" placeholder="" name="cPassword"  id="cPassword"></td>
        </tr>
        <tr>
          <td ><label style="color:#000;"><b>First Name</b></label></td>
          <td ><input class="span3" type="text" placeholder="" name="fname"  id="fname"></td>
          <td >&nbsp;</td>
          <td ><label style="color:#000;"><b>Last Name</b></label></td>
          <td ><input class="span3" type="text" placeholder="" name="lname"  id="lname"></td>
        </tr><tr>
          <td ><label style="color:#000;"><b>Date of Birth</b></label></td>
          <td ><div align="left">
          
            <div id="datetimepicker" class="input-append date">
			      <input class="span3" type="text" data-format="yyyy-MM-dd"  name="dob"  id="dob"></input>
			      <span class="add-on">
			        <i data-time-icon="icon-time" data-date-icon="icon-calendar"></i>
			      </span>
		    </div>
          </div></td>
          
          <td >&nbsp;</td>
          <td ><label style="color:#000;"><b>Designation</b></label></td>
          <td ><input class="span3" type="text" placeholder="" name="designation"  id="designation"></td>
        </tr><tr>
          <td ><label style="color:#000;"><b>Email</b></label></td>
          <td ><div align="left">
            <input class="span3" type="text" placeholder="" name="email"  id="email" onblur="uniqueEmailCheck();">
          </div></td>
          <td >&nbsp;</td>
          <td ><label style="color:#000;"><b>Mobile</b></label></td>
          <td ><input class="span3" type="text" placeholder="" name="mobile"  id="mobile"></td>
        </tr><tr>
          <td ><label style="color:#000;"><b>Landline</b></label></td>
          <td ><div align="left">
            <input class="span3" type="text" placeholder="" name="landline"  id="landline">
          </div></td>
          <td >&nbsp;</td>
          <td ><label style="color:#000;"><b>Gender</b></label></td>
          <td >            
             <select  class="span3"   name="gender"  id="gender" ><!--  onchange="changeCountry(this.value)" -->
                  <option value="M">Male</option>
                  <option value="F">Female</option>
       		</select>
       	</td> 
         <!--  <td ><label style="color:#000;"><b>Is Parent</b></label></td>
          <td ><input type="checkbox" style="margin-top:-6px;" name="isParent"  id="isParent"  onclick="isParentCheck();"></td> -->
        </tr><tr>
          <td ><label style="color:#000;"><b>Address 1</b></label></td>
          <td ><div align="left">
            <input class="span3" type="text" placeholder="" name="addr1"  id="addr1">
          </div></td>
          <td >&nbsp;</td>
          <td ><label style="color:#000;"><b>Address 2</b></label></td>
          <td ><input class="span3" type="text" placeholder="" name="addr2"  id="addr2"></td>
        </tr><tr>
          <td ><label style="color:#000;"><b>City</b></label></td>
          <td ><div align="left">
            <input class="span3" type="text" placeholder="" name="city"  id="city">
          </div></td>
          <td >&nbsp;</td>
          <td ><label style="color:#000;"><b>Country</b></label></td>
          <td >            
             <select class="span3" name="country"  id="country" ><!--  onchange="changeCountry(this.value)" -->
               <option value="India"  selected="selected">India</option><option value="Indonesia">Indonesia</option>
                    </select> 
         </td>
        </tr><tr>
  		  <td ><label style="color:#000;"><b>State</b></label></td>
			 <td ><div align="left">	
				<div id="displaySubjects" style="display:show;">
					<!--  <select   class="span3"  id="states" name="states"> -->
					 <select   class="span3"  id="state" name="state">
				   <option value="1">Andhra Pradesh</option>
                   <option value="2">Madya pradesh</option>
				   <option value="3">Bihar</option>
				   </select>
				   </div>
             </div>
          </td>
          <td >&nbsp;</td>
<!-- 	      <td ><label style="color:#000;"><b>Parent Type</b></label></td>         
	        <td > 
					<select   class="span3"  id="parentTypeSel"   name="parentTypeSel" disabled="disabled">
					     <option selected="selected">Select</option>
				   <option value="1">Father</option>
                   <option value="2">Mother</option>
				   <option value="3">Other</option>
				   </select>				   
          </td>   -->
        </tr>
        <tr>
          <td width="20%" ><label style="color:#000;"><b>Photo</b></label></td>
          <td>
          	<div class="fileupload fileupload-new" data-provides="fileupload">
				  <div class="input-append">
				    <div class="uneditable-input span2"><i class="icon-file fileupload-exists"></i> <span class="fileupload-preview"></span></div><span class="btn btn-file">
				    <span class="fileupload-new">Select file</span><span class="fileupload-exists">Change</span>
				    <input type="file" name="fileUP" id ="fileUP"/></span><a href="#" class="btn fileupload-exists" data-dismiss="fileupload">Remove</a>
				  </div>
</div>
          </td>
          <td></td>
          <td></td>
          <td></td>
          </tr>
          <tr>
          <td ><label style="color:#000;"><b>Active</b></label></td>
          <td ><div align="left">
            <input type="checkbox" name="active" id="active"  checked="checked">
          </div></td>
          <td >&nbsp;</td>
          <td >&nbsp;</td>
          <td >&nbsp;</td>
        </tr>
		<tr>
          <td colspan="5" >&nbsp;</td>          
        </tr>
              
        <tr>         
          	<td >&nbsp;</td>   
            <td colspan="4">       	
      <!--   <button type="button" class="btn" onclick="addUser();">Save changes</button> -->
   <!--      <button type="button" class="btn" onclick="addUserFormSubmit();">Save changes</button> -->
        <button type="submit" class="btn btn-warning" >Save changes</button>
            <button type="reset" class="btn btn-warning">Cancel</button></td>
        </tr>
      </table>
    </div>
    </fieldset>
  </form>
</div>
<div style="height:20px;"></div>

<!-- /container -->
   <div id="idAlertDialog"    class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="windowTitleLabel" aria-hidden="true">
            <img src="img/alert.gif" width="34" height="28" alt="alert icon">
            <h3 id="idAlertDialogPrompt"></h3>
            <a href="#" class="btn btn-primary" onclick="okAlertDialog ();">OK</a>
            </div>
<script type="text/javascript">
 
    $('#datetimepicker').datetimepicker({
      		  pickTime: false
      			});
      			
	    function getInstitutionMaster(){
				//Process DWR/AJAX request here
				try{
				//alert("in alrttt--getInstitutionMaster");
					var listofInsit = document.getElementById('im_id_list');
					    UserTypeMasterDWR.getInstitutionMasterList(function(data){
					                                if (data == null) {														
					                                  alert("error");
					                                 
													} else {				
												//	alert(data+":::"+data.length);	                                 
					                               for(var i = 0; i < data.length; i++) {
														  var opt = document.createElement("option");
														    var temp = data[i];
														    opt.value = temp[0];
														    opt.innerHTML = temp[1];
														    listofInsit.appendChild(opt);
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
      	
      	    			
      	    function getUserTypeMaster(){
		//Process DWR/AJAX request here
		try{
		//alert("in alrttt--getUserTypeMaster");
			var dd = document.getElementById('userRights');
		     /*    UserTypeMasterDWR.userTypeMaster(function(data){
			                                   if (data ==null) {														
			                                   alert("exception::"+data);
											} else {
				                                 userTypes=data;
				                                   alert("save::"+data);
				                                   
												for(var i = 0; i < data.length; i++) {
												    var opt = document.createElement('option');
												    opt.innerHTML = data[i];
												    opt.value = data[i];
												    dd.appendChild(opt);
												}
											}
			                         }
			      ) ;     */
			 }catch(e){
			 alert("incatch::"+e);
			        jQuery.log.info(e.message);
			        jQuery("#infoError").html("&nbsp;");
       	     } 		
		 										
		
		}
      			
      			
  	   function resultPopUp(){
		    var userID = "<%=session.getAttribute("userIDD")%>";
		    var password =  "<%=session.getAttribute("passwordd")%>";
		    var msg =  "<%=session.getAttribute("msg")%>";
		    if(userID == null || password == null || userID == "null" || password == "null"){
			    if(msg != null && msg != "null"){
			     alertDialog("<div style='padding-left:50px; text-align:center; margin-top: 15px;'> "+msg+".</div>");
			     <%session.removeAttribute("msg"); %> ;
			    }
		    }else{
		    alertDialog("<div style='padding-left:50px; text-align:center; margin-top: 15px;'> "+msg+".<br/> Admin ID : "+userID+"<br/>Password : "+password+"</div>");
		        resetForm($('#addAdminUser')); // by id, recommended
		    }
		
		    if(userID != null){
		 		<%session.removeAttribute("userIDD"); %> ;
		 		<%session.removeAttribute("passwordd"); %> ;
		 		 <%session.removeAttribute("msg"); %> ;
		    }
		    	var shortNameSel ='<%=par1%>';
		    	var listofInsit = document.getElementById('im_id_list');
		    		for (var i = 0; i < listofInsit.options.length; i++) {
				        if (listofInsit.options[i].text== shortNameSel) {
				            listofInsit.options[i].selected = true;
				            return;
				      	}
    				}
		    
		  
	    }		
	    
	     onloadmethods();
	     
	    	    		function okAlertDialog () {
			$("#idAlertDialog").modal ('hide'); 
		};
		function alertDialog (prompt) {
			document.getElementById ("idAlertDialogPrompt").innerHTML = prompt;
			$("#idAlertDialog").modal ("show");
			}
			
 	 function onloadmethods(){
	 //   alert("in onload");
	 	getInstitutionMaster();
		getUserTypeMaster();
		resultPopUp();
	    
	    }
				
	    
</script>



