<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	    pageEncoding="ISO-8859-1"%>
	 
	 <%
	 
	 System.out.print("successssssssssssssssss--*"+session.getAttribute("smsmsg"));
	  %>
	 
	 
	    <link href="css/bootstrap.css" rel="stylesheet">
	    <style type="text/css">
	    .styledRadio {
			display: inline-block;
		}
		</style>

	    <script type="text/javascript" src="js/bootstrap-radio.js"></script>
	    
	      <script 	src="<%=request.getContextPath()%>/dwr/interface/SMSCreditsDWR.js"></script>
	      <script 	src="<%=request.getContextPath()%>/dwr/interface/SchoolMasterDWR.js"></script>
	      <script 	src="<%=request.getContextPath()%>/dwr/interface/BranchMasterDWR.js"></script>
	      <script 	src="<%=request.getContextPath()%>/dwr/interface/ClassMasterDWR.js"></script>
	    
	    <script type="text/javascript">
	    var smsCrGlobal = "";//the form wil not submit if no sms credits
	    
	    $(function(){		
			$('input:radio').screwDefaultButtons({
				image: 'url("img/radioSmall.jpg")',
				width: 20,
				height: 20
			});
			
		});
				
				
	    function disableOrEnable(val){
			if(val=='all'){
				document.getElementById('selectClass').disabled = true;
				document.getElementById('selectAll').disabled = false;
			}else{
					document.getElementById('selectAll').disabled = true;
					document.getElementById('selectClass').disabled = false;
			}
		}
	 function selectValue(value){	
		if(value=="-1"){
			alert('plz select value');
			}
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
	   if(val != '-1'){
	   var schoolID = jQuery("#schoolNames").val();

		removeAllOptions("branchNames");
		
		   try{
		   	var im_id = "<%=session.getAttribute("IM_ID")%>";	
		   	
					//alert("based school master getting branch master list--"+schoolID);
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
				
			  }
	 
	   
	   
	 }
		 
	function selectBranchesforClasses(){
	   
	 	   
	   var branchID = jQuery("#branchNames").val();
	   var schoolID = jQuery("#schoolNames").val();

		removeAllOptions("selectClass");
					  
		if(branchID != '-1'){
		   try{
		   	var im_id = "<%=session.getAttribute("IM_ID")%>";	
		   	
				//	alert("based school master getting branch master list--"+branchID+"--"+schoolID);
						var listofclasses = document.getElementById('selectClass');					
	
						    ClassMasterDWR.getClassMasterList(schoolID,branchID,function(data){
						                                if (data == null) {													
						                                  alert("error");
						                                 
														} else {				
														//alert(data+":::"+data.length+data[1]);	                                 
						                               for(var i = 0; i < data.length; i++) {
															  var opt = document.createElement("option");
															    var temp = data[i];
															    opt.value = temp[0];
															    opt.innerHTML = temp[1];
															    listofclasses.appendChild(opt);
															}		                          
														}
						                         }
						      ) ;  
						
		   		 }catch(e){
						 alert("incatch::"+e);
						        jQuery.log.info(e.message);
						        jQuery("#infoError").html("&nbsp;");
		         } 	
	   
				
				
					
				} else{			 
				 setError("branchNames","Please select  branch");
				jQuery("#branchNames").focus();
				return false;
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
	 	function getClassesList(){
	
			if (!document.getElementById("classRadio").checked) {
			//alert("checkkk");			
			selectBranchesforClasses();

			}
		}

	 function resultPopUp(){
	    	var smsmsg = "<%=session.getAttribute("smsmsg")%>";
		    if(smsmsg == null  || smsmsg == "null" ){
		    }else{
		   setError("schoolNames",smsmsg);
		    jQuery("#schoolNames").focus();
		    }
		      if(smsmsg != null){
		 		<%session.removeAttribute("smsmsg"); %> ;
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
	    
		


	 function onloadmethods(){
	resultPopUp();
	 schoolMasterList();

	// classMaster();
	 }
	function classMaster(){
			//Process DWR/AJAX request here
				try{
				//alert("in alrttt--SMSCredits");
					
					var im_id = "<%=session.getAttribute("IM_ID")%>";					
					var sm_id = "<%=session.getAttribute("SM_ID")%>";				
					var bm_id = "<%=session.getAttribute("BM_ID")%>";						
					
					var listofClasses = document.getElementById('selectClass');
					
					alert(listofClasses+"::::::");
					
				     ClassMasterDWR.getClassMasterList(im_id,sm_id,bm_id,function(data){
					                                if (data == null) {														
					                                  alert("error");
					                                 
													} else {				
												//	alert(data+":::")		                                 
					                               for(var i = 0; i < data.length; i++) {
														  var opt = document.createElement("option");
														    var temp = data[i];
														    opt.value = temp[0];
														    opt.innerHTML = temp[1];
														    listofClasses.appendChild(opt);
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
	
	function getSMSCredits(){
	
	
	   var branchID = jQuery("#branchNames").val();
	   var schoolID = jQuery("#schoolNames").val();
	   
	   if(branchID != -1 && schoolID != -1){
	   smscredits(schoolID,branchID);
	   }
	
	}
	
	 function smscredits(schoolID,branchID){
	 				//Process DWR/AJAX request here
				try{
					//alert(branchID+"--")
				     SMSCreditsDWR.smsCreditsBalance(schoolID,branchID,function(data){
					                                if (data != "error" && data != null && data != "") {
					                                   //alert("::"+data);
					                                   smsCrGlobal = data;
					                                   jQuery("#smscredits1").val(data);
					                                document.getElementById("smscredits").innerHTML=data;
					                                
													} else {
													 setError("schoolNames","You Dont have SMS credits. Form will not submitted");
													//alertDialog('You Dont have SMS credits');	              
													smsCrGlobal = "0";                   
					                                document.getElementById("smscredits").innerHTML="0";
					                                   jQuery("#smscredits1").val("0");				                          
													}
					                         }
					      ) ;   
					 }catch(e){
					 alert("incatch::"+e);
					        jQuery.log.info(e.message);
					        jQuery("#infoError").html("&nbsp;");
	        	     } 
	 
	 
	 }
	 

	function limiter(){
		var tex = document.smsForm.smsbody.value;
		var len = tex.length;
		document.getElementById("limit").innerHTML=len;
		document.smsForm.limit.value = len;
	}
	function previewSmS(){
	alert('preview');
	}
	function previewSMSValidate(){
			var body =/^[A-Za-z0-9].+$/;
			var smsbody			=  jQuery("#smsbody").val();
			var selectAll			=  jQuery("#selectAll").val();
			var selectClass			=  jQuery("#selectClass").val();
			var radios = document.getElementsByName("radio1");
			var sms_notification = document.getElementsByName("sms_notification");
		    var formValid = false;
		     var i = 0;
		     var j = 0;
		   	var schoolNames = jQuery("#schoolNames").val();
			var branchNames = jQuery("#branchNames").val();	
		
		
			if(schoolNames=="-1"){
				 setError("schoolNames","Please select  school");
				jQuery("#schoolNames").focus();
				return false;
			}
			
			if(branchNames=="-1"){
			 	setError("branchNames","Please select branch");
				jQuery("#branchNames").focus();
				return false;
			}    
		     
		    while (!formValid && i < radios.length) {
		        if (radios[i].checked) formValid = true;
		        i++;     		          
		    }
		    if (!formValid){ 
		    setError("selectAll","Must check send to or select parents of .");
				jQuery("#selectAll").focus();
			    return false;
		    }
		    if(i==1 && selectAll=="-1"){
		    setError("selectAll","please select send to option");
				jQuery("#selectAll").focus();
				return false;
			}else if(i==2 && selectClass=="-1"){
			   setError("selectClass","please select parent of select option");
				jQuery("#selectClass").focus();
				return false;
			}
			var msgTypeRadio = false;
			while (!msgTypeRadio && j < sms_notification.length) {
		        if (sms_notification[j].checked) msgTypeRadio = true;
		        j++;     		          
		    }
		if (!msgTypeRadio){ 
		    setError("sms_notification","Please select any message type.");
				jQuery("#sms_notification").focus();
			    return false;
		    }
		    var tt = jQuery("#sms_notification").val();
		    //smsRadio,notifictionRadio,smsnotificationRadio
		    
		      if(j != 2){
		    	if (smsCrGlobal <= 0) {
					 setError("schoolNames","No SMS Credits.");
					jQuery("#schoolNames").focus();
					return false;
				}
			}
		    
		    
		    if(j != 1){
			    if(jQuery("#notificationSub").val() == "" || jQuery("#notificationSub").val() == null ||jQuery("#notificationSub").val().length < 4){
			    setError("notificationSub","Please enter notififcation subject");
					jQuery("#notificationSub").focus();
				    return false;
			    }
		    }
		    
	
		if(smsbody.length<=0){
		   setError("smsbody","Please enter some message");
				jQuery("#smsbody").focus();
			return false;
		}
		//alertDialog('All validations cleared');

	}
	
		
	function showNotfSubj(){
	  var checkReq = $('input[name=sms_notification]:checked').val();
	
	if (checkReq != "smsRadio") {
		document.getElementById("noti").style.display="block";
	} else {
		document.getElementById("noti").style.display='none';
	}
	}



	
	function okAlertDialog () {
		$("#idAlertDialog").modal ('hide'); 
	};
	function alertDialog (prompt) {
		document.getElementById ("idAlertDialogPrompt").innerHTML = prompt;
		$("#idAlertDialog").modal ("show");
	}
	    function test(){
	    var yy =   jQuery("#smscredits1").val();
	    alert("hjhjhj"+yy);
	    }
	   </script>
	<div style="height:50px;"></div>
<div class="reg_mainCon">
<form action="previewSMS.action"	method="post"  name="smsForm"  onsubmit="return previewSMSValidate(this)">
    <fieldset>
    <legend><img src="img/list_add_user.PNG" class="img-circle">&nbsp;&nbsp;Send SMS/Notification</legend>
    <div style="padding:20px;">
      <table width="100%" border="0" cellspacing="0" cellpadding="10">
       <tr><td colspan="4" style="text-align: center;padding: 0px;"><span id ="errorspan" style="color:red;font-size: 14px;" ></span>&nbsp;</td></tr>
      	<tr>
          <td colspan="2" style="border-bottom:1px #dcdcdc solid; padding:0px; margin-bottom:10px;"><div style="color:#000; font-size:20px; font-weight:bold; padding-bottom:5px;float:left;"><b>Select Audience</b></div>
          <div style="float:right; color:#333333;">SMS Balance: <span id="smscredits" >0</span>
          <input type="hidden"  id="smscredits1"  name="smscredits1">
          </div><div style="width:100%;height:1px; clear:both;">&nbsp;</div>
          </td>
        </tr>
        <tr>
          <td colspan="2" style="padding-top:20px;"><div style="float:left; margin-right:20px;"><span class="help-inline">
              <label style="color:#000;"><b>School</b></label></span>&nbsp;&nbsp;
              <select class="span3"  name="schoolNames" id="schoolNames" onchange="selectSchoolforBranches(this.value);">
                <option value="-1" selected="selected">Select</option>
	                       <!--  <option value="0">ALL</option> -->
	          </select> </div><div  style="float:left;"><span class="help-inline">
              <label style="color:#000;"><b>Branch</b></label> </span>&nbsp;&nbsp;
              <select class="span3" name="branchNames" id="branchNames" onchange="getSMSCredits();"><!-- selectBranchesforClasses(this.value) -->
	       			<option value="-1" selected="selected">Select</option>
	                      <!--   <option value="0">All</option> -->
	         </select></div>
	       </td>
        </tr>
        <tr>
          <td colspan="2"><table width="100%" border="0" cellspacing="0" cellpadding="0">
              <tr>
                <td><input type="radio" name="radio1" id="inlineCheckbox5" value="" onchange="disableOrEnable('all')"	 checked></td>
                <td><b>Send to</b></td>
                <td><select class="span2"  name="selectAll" id="selectAll" onchange="selectValue(this.value);">
	                   		<option value="-1"  selected="selected">Select</option>
							<option value="AllParents">All parents</option>
	                        <option value="AllTeachingStaff">All Teaching Staff</option>
	                        <option value="AllNonTeachingStaff">All Non Teaching Staff</option>
                  </select>
                </td>
                <td><input type="radio" name="radio1"  id="classRadio" onchange="disableOrEnable('class');" onclick="getClassesList();return false;"></td>
                <td><b>Select Parents of</b></td>
                <td><select class="span2" name="selectClass"   id="selectClass" onchange="selectValue(this.value)" disabled="disabled">
        				   <option value="-1"  selected="selected">Select</option>
                  </select></td>
              </tr>
            </table></td>
        </tr>
        <tr>
          <td colspan="2"><label style="color:#000; font-size:20px; font-weight:bold; padding-bottom:5px; border-bottom:1px #dcdcdc solid;"><b>Select Message Type</b></label> </td>
        </tr>
        <tr>
          <td colspan="2" style="border-bottom:1px solid #DCDCDC; padding-bottom:20px;">
	          <table width="100%" border="0" cellspacing="0" cellpadding="0">
	              <tr>
	              <td style="width:20%; height:20px; overflow:hidden;">&nbsp;</td>
	                <td style="width:5%; height:20px; overflow:hidden;">
	                <input type="radio" name="sms_notification" id="smsRadio" value="smsRadio" checked="checked" onchange="showNotfSubj();"></td>
	                <td style="width:10%;padding-left:10px;"><b>SMS</b></td>
	                <td style="width:5%; height:20px; overflow:hidden;">
	                <input type="radio" name="sms_notification" id="notifictionRadio" value="notifictionRadio" onchange="showNotfSubj();"></td>
	                <td style="width:15%;padding-left:10px;"><b>Notification</b></td>
	                <td style="width:5%; height:20px; overflow:hidden;">
	                <input type="radio" name="sms_notification" id="smsnotificationRadio" value="smsnotificationRadio" onchange="showNotfSubj();"></td>
	                <td style="width:40%;padding-left:10px;">SMS &amp; Notification</td>
	              </tr>
	            </table></td>
        </tr>           
        <tr>
          <td colspan="2">
          	<div style="color:#000; float:left;"><b>Message</b></div> 
            <div id="noti" style="float:right; width:302px;display: none;">
            	<div style="float:left;">
            		<label style="color:#000;"><b>Notification Subject</b></label>
                </div> 
                <div style="float:right;" >               
                <input class="input-medium" type="text" placeholder="" name="notificationSub"  id="notificationSub">
                </div>
                <div style="width:100%;height:1px; clear:both;">&nbsp;</div>  
                </div> 
              <div style="width:100%;height:1px; clear:both;">&nbsp;</div> 
            <textarea style="width:100%;" rows="3"  name="smsbody" id="smsbody" onkeyup="limiter()" ></textarea>
          </td>
        </tr>
        <tr>
            <td colspan="1" style="float: left;padding-top: 0px;">
              <div style="float:left; color:#333333;"><span id='limit'>0</span>
              <input type="hidden" name='limit' id='limit'>
              </div>
             <!-- <script type="text/javascript">
				document.write("<input type=text style=width:55px; name='limit' id='limit' size=4 readonly value='0'>");
			</script> -->
                </td>
        </tr>
        <tr>
          <td><button type="submit" class="btn">Preview</button>
  
            <!--<button type="button" class="btn">Cancel</button>--></td>        
          <td>&nbsp;</td>
        </tr>
      </table>
    </div>
    </fieldset>
  </form>
</div>
<div style="height:20px;"></div>



 <div id="idAlertDialog" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="windowTitleLabel" aria-hidden="true">
            <img src="img/alert.gif" width="34" height="28" alt="alert icon">
            <h3 id="idAlertDialogPrompt"></h3>
            <a href="#" class="btn btn-primary" onclick="okAlertDialog ();">OK</a>
            </div>
<script type="text/javascript">
onloadmethods();
</script>

