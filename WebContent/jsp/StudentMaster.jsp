<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%
String msg			= (String)session.getAttribute("msg"); 
System.out.println("msg"+msg);
%>

   <link href="css/bootstrap-fileupload.css" rel="stylesheet" media="screen">	
 	<script type="text/javascript" src="js/bootstrap-radio.js"></script>
   <script src="js/bootstrap-fileupload.js" type="text/javascript"></script>
			<script 	src="<%=request.getContextPath()%>/dwr/interface/SchoolMasterDWR.js"></script>
	        <script 	src="<%=request.getContextPath()%>/dwr/interface/BranchMasterDWR.js"></script>
<style type="text/css">
.errors {
	background-color: #ADD8E6;
	border: 1px solid #1E90FF;
	width: 500px;
	margin-bottom: 8px;
}

.errors li {
	list-style: none;
}
</style>
<script type="text/javascript">
		 jQuery(document).ready(function () {
				 resultPopUp();
				  schoolMasterList();
		});

 function resultPopUp(){
	    	var msgg = "<%=msg%>";
		    if(msgg == null  || msgg == "null" ){
		    }else{
		    setError("schoolNames",msgg);
		    jQuery("#schoolNames").focus();
		    }
		      if(msgg != null){
		 		<%session.removeAttribute("msg"); %> ;
		     } 
	    }
	    function okAlertDialog () {
			$("#idAlertDialog").modal ('hide'); 
		};
		function alertDialog (prompt) {
			document.getElementById ("idAlertDialogPrompt").innerHTML = prompt;
			$("#idAlertDialog").modal ("show");
		}
		
		 function schoolMasterList(){	    
		   try{
					//alert("onload school master");
						var listofschools = document.getElementById('schoolNames');					
	
						    SchoolMasterDWR.getSchoolMasterList(function(data){
						                                if (data == null) {													
						                                  alert("error");
						                                 
														} else {				
														//alert(data+":::"+data.length+data[1]);	                                 
						                               for(var i = 0; i < data.length; i++) {
															  var opt = document.createElement("option");
															    var temp = data[i];
															    opt.value = temp[0];
															    opt.innerHTML = temp[1];
															    listofschools.appendChild(opt);
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
	
	function selectSchoolforBranches(val){	   
		removeAllOptions("branchNames");
	   if(val != '-1'){	   
	   var schoolID = jQuery("#schoolNames").val();
		if(val != '-1'){	   
		   try{
				//	alert("based school master getting branch master list--"+schoolID);
						var listofbranchs = document.getElementById('branchNames');					
	
						    BranchMasterDWR.getBranchMasterList(schoolID,function(data){
						                                if (data == null) {													
						                                  alert("error");
						                                 
														} else {				
														//alert(data+":::"+data.length+data[1]);	                                 
						                               for(var i = 0; i < data.length; i++) {
															  var opt = document.createElement("option");
															    var temp = data[i];
															    opt.value = temp[0];
															    opt.innerHTML = temp[1];
															    listofbranchs.appendChild(opt);
															}		                          
														}
						                         }
						      ) ;  
						
		   		 }catch(e){
						 alert("incatch::"+e);
						        jQuery.log.info(e.message);
						        jQuery("#infoError").html("&nbsp;");
		         } 	
	   
				
				
					
				}else{			 	 }
			  }
	 }
		
	function removeAllOptions(whichOption)
		{
	//	var listofSubjects =  document.getElementById("selectSubject");
		var listofSubjects =  document.getElementById(whichOption);
		
			var i;
			for(i=listofSubjects.options.length-1;i>=0;i--)
			{
			listofSubjects.remove(i);
			}
			 var opt = document.createElement("option");
		    opt.value = "-1";
		    opt.innerHTML = "Select";
		    listofSubjects.appendChild(opt);
			
		/* 	 var opt = document.createElement("option");
		    opt.value = "0";
		    opt.innerHTML = "All";
		    listofSubjects.appendChild(opt); */
		}
		function validate(){
		
		var schoolNames = jQuery("#schoolNames").val();
		var branchNames = jQuery("#branchNames").val();
		if(schoolNames=="-1"){
		 setError("schoolNames","Please select valid School");
			jQuery("#schoolNames").focus();
			return false;
		}
		if(branchNames=="-1"){
			 setError("branchNames","Please select  Branch");
			jQuery("#branchNames").focus();
			return false;
		}
			var file1  = jQuery("#fileUP").val();
		
	//	alert(file1);
		if(file1 == "" || file1 == undefined  || file1 == "undefined"){		
			setError("branchNames","Please select a file");
				jQuery("#fileUP").focus();
			return false;
		}
		var extension = file1.split('.').pop().toLowerCase();
		var allowed = ['txt','csv'];
		
		if(allowed.indexOf(extension) === -1) {
		    // Not valid.
			setError("branchNames","Your selected "+ file1+"  extension wrong");
				jQuery("#fileUP").focus();
			return false;
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
		 /*    
		    $form.find('input:radio, input:checkbox')
		         .removeAttr('checked').removeAttr('selected'); */
		}
		
	    </script>



  
 <div style="height:80px;"></div>
	<div class="reg_mainCon">

     <fieldset>
	    <legend><img src="img/list_add_user.PNG" class="img-circle">&nbsp;&nbsp;Student Master</legend>
	  <form  method="post"  action="studentMasterXL.action" enctype="multipart/form-data"  onsubmit="return validate(this)" >
	    <div style="padding:20px;">
	          <label style="color:#000;"></label>
	 
	   <table width="100%" border="0" cellspacing="0" cellpadding="0">
	   <tr><td colspan="4" style="text-align: center;"><span id ="errorspan" style="color:red;font-size: 14px;" ></span>&nbsp;</td></tr>
	   <tr><td>&nbsp;</td></tr>
		           <tr>
                <td><label style="color:#000;"><b>Select School</b></label></td>
                <td><select class="span3"  name="schoolNames" id="schoolNames" onchange="selectSchoolforBranches(this.value)">
                        <option value="-1" selected="selected">Select</option>                       
                       </select>                   
                 </td>
                <td><label style="color:#000;"><b>Select Branch</b></label></td>
                <td><select class="span3" name="branchNames" id="branchNames">
                        <option value="-1" selected="selected">Select</option>                      
                       </select>                   
                 </td>
        </tr>
		 <tr><td>&nbsp;</td></tr>
		   <tr>			  
			  <td width="20%"><label style="color:#000;"><b>Student Master</b></label></td>
	          <td colspan="2">
	          	<div class="fileupload fileupload-new" data-provides="fileupload" >
					  <div class="input-append">
					 <div class="uneditable-input span3"><i class="icon-file fileupload-exists"></i> <span class="fileupload-preview"></span></div><span class="btn btn-file">
					    <span class="fileupload-new">Select file</span><span class="fileupload-exists">Change</span>
					    <input type="file" name="fileUP" id ="fileUP"/></span><a href="#" class="btn fileupload-exists" data-dismiss="fileupload">Remove</a>
					  </div>
				</div>
               </td>
                 <td class="span3" style=" padding-bottom: 9px;">
                        <!-- <button type="button" class="btn"
                            onclick="sendSmS(); return false;">Save</button> -->
                      <button type="submit" class="btn" >Upload</button>
                    </td>
		 </tr> 
	   </table>
	    </div>
	    </form>
	    <div style="float: right;">
		      <form  method="post"    action="download.action">
		   
				   <table width="100%" border="0" cellspacing="0" cellpadding="0">
						 <tr> <td><input type="hidden" name="fileName" id="fileName"   value="student_upload.csv" >
						 <td colspan="2" style="text-align:center;"><button type="submit" class="btn" >Download Template</button></td></tr>
				   </table>
		    </form>
		    </div>
    </fieldset>
</div>
