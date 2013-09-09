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
	        <script 	src="<%=request.getContextPath()%>/dwr/interface/ClassMasterDWR.js"></script>
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
                margin-top: 25px;
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
				margin-top: 25px;
				}
	
	//
	 #idConfirmDialogReName {
				width: 450px;
				height: 165px;
				background-color: whitesmoke;
				}
			 #idConfirmDialogReName .btn {
				width: 70px;
				margin-bottom: 8px;
				margin-right: 15px;
				margin-top: 80px;
				float: right;
				}
			 #idConfirmDialogReName h3 {
				margin-left: 60px;
				margin-top:15px;
				}
			 #idConfirmDialogReName img {
				float: left;
				margin-left: 15px;
				margin-top: 25px;
				}
	
</style>
<script type="text/javascript">

		function okAlertDialog () {
			$("#idAlertDialog").modal ('hide'); 
			};
		function alertDialog (prompt) {
			document.getElementById ("idAlertDialogPrompt").innerHTML = prompt;
			$("#idAlertDialog").modal ("show");
			}
        
        function closeConfirmDialog () {
			$("#idConfirmDialog").modal ('hide'); 
			};
		function okConfirmDialog () {
			$("#idConfirmDialog").modal ('hide'); 
			disableClassAfterConfirm();
			};
		function confirmDialog (prompt, callback) {
			document.getElementById ("idConfirmDialogPrompt").innerHTML = prompt;
			confirmDialogCallback = callback;
			$("#idConfirmDialog").modal ("show");
			}
        
        function closeConfirmDialogReName () {
			$("#idConfirmDialogReName").modal ('hide'); 
			};
		function okConfirmDialogReName () {
			$("#idConfirmDialogReName").modal ('hide'); 
			reNameClassAfterConfirm();
			};
		function confirmDialogReName (prompt, callback) {
			document.getElementById ("idConfirmDialogPromptReName").innerHTML = prompt;
			//confirmDialogCallback = callback;
			$("#idConfirmDialogReName").modal ("show");
			}
        



		 jQuery(document).ready(function () {
				// resultPopUp();
				  schoolMasterList();
				  
		});


 function resultPopUp(){
	    	var msg = "<%=msg%>";
		    if(msg == null  || msg == "null" ){
		    }else{
		   setError("schoolNames",msg);
		    jQuery("#schoolNames").focus();
		    }
		      if(msg != null){
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
	 
	 }
	 
	 
	 function classMasterLoadDisplay(){
		   var branchID = jQuery("#branchNames").val();
		if(branchID != '-1'){
		   var schoolID = jQuery("#schoolNames").val();
			if (branchID != "-1" || schoolID !="-1") {
			   try{
					 ClassMasterDWR.getClassMasterList(schoolID,branchID,function(data){
						                                if (data == null) {													
						                                  alert("error");
														} else {	
								var innerHtmlClass = '<table class="table table-bordered" width="100%" style="margin-bottom:0px;">';
									if(data.length>0){
								innerHtmlClass += '<tr><th style="text-align: center;">Class Name</th><th style="text-align: center;">Rename</th><th style="text-align: center;">Disable</th></tr>';
								                 for(var i = 0; i < data.length; i++) {
													    var temp = data[i];
																	//classAreaID
														innerHtmlClass += '<tr  >';
														innerHtmlClass += '<td style="text-align: center;">'+temp[1]+'</td>';
														innerHtmlClass += '<td style="text-align: center;">';
														innerHtmlClass += '<a href="#" onclick="reNameClass(\''+temp[0]+'\',\''+temp[1]+'\')" >';
														innerHtmlClass += '<i class="icon-edit"></i></a></td>';
														innerHtmlClass += '<td style="text-align: center;"><a href="#" onclick="disableClass(\''+temp[0]+'\',\''+temp[1]+'\')" ><i class="icon-trash"></i> </a></td>';
														innerHtmlClass += '</tr>';
												}
										}else{
														innerHtmlClass += '<tr  >';
														innerHtmlClass += '<td colspan="3" style="color:red;font-size:16px;font-weight:bold;text-align: center;background-color:silver ">No Classes Found</td>';													
														innerHtmlClass += '</tr>';
										}		
												
								innerHtmlClass += '</table>';
							jQuery('#classAreaID').html(innerHtmlClass);
							}
						}) ;  
		   		 }catch(e){
						 alert("incatch::"+e);
						        jQuery.log.info(e.message);
						        jQuery("#infoError").html("&nbsp;");
		         }	   
			}
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

		
		
	function validate(){//*************************************************************8
		
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
	    
	    function addNewClass() {
			var className = jQuery('#newClassID').val();			
			var schoolNames = jQuery('#schoolNames').val();
			var branchNames = jQuery('#branchNames').val();
			
			if(schoolNames=="-1"){
		 setError("schoolNames","Please Select School");
			jQuery("#schoolNames").focus();
			return false;
			}
			
			if(branchNames=="-1"){
				 setError("branchNames","Please Select  Branch");
				jQuery("#branchNames").focus();
				return false;
			}
			if(className.length<=0 || className.length>=20){
			 setError("newClassID","Invalid Class Name");
				jQuery("#newClassID").focus();
				return false;
			
			}
			
			ClassMasterDWR.saveNewClass(schoolNames,branchNames,className,function(data){
					if(data == "success"){
						setError('schoolNames', "Success");
						classMasterLoadDisplay();
						 jQuery('#newClassID').val('');		
					}else if(data == "already"){
						setError('schoolNames', "Class Name  Already Exist");
					}else{
						setError('schoolNames', "Fail");
					}					
			});			
		}
	    
	    
	    
	    function disableClass(idForReName,nameForReName) {
			confirmDialog ('<div style="font-size:18px">Are you sure, you want to disable this Class?</div>', alert);
			jQuery('#hiddenClassID').val(idForReName);
		}
		
		function disableClassAfterConfirm(){
			var oldClassID =jQuery('#hiddenClassID').val(); 
			var schoolNames = jQuery("#schoolNames").val();
			var branchNames = jQuery("#branchNames").val();//feature purpose
			ClassMasterDWR.disableCLassMaster(branchNames,oldClassID,function(data) {
						if(data == "success"){
			 				setError("schoolNames","Success");
			 				classMasterLoadDisplay();
			 			}else 	if(data == "notexist"){
			 				setError("schoolNames","Class Name  Not Exist");
			 			}else{
						setError('schoolNames', "Fail");
					}		
			});
			
		}
		
	    function reNameClass(idForReName,nameForReName) {
			confirmDialogReName ('<div style="font-size:18px;">Are you sure, you want to Rename this Class?</div>', alert);
			//req hideen input for old class id?
			jQuery('#newClassName').val("");
			jQuery('#oldClassName').val(nameForReName);
			jQuery('#hiddenClassID').val(idForReName);
		}
	    
		function reNameClassAfterConfirm() {
			var oldClassID =jQuery('#hiddenClassID').val(); 
			var newClassName = jQuery('#newClassName').val();
			var oldClassName = jQuery('#oldClassName').val();
			
			if(newClassName.length<1 || newClassName.length>=20 || newClassName==oldClassName){
					setError("schoolNames","Invalid Class Name");
					return false;
			}
			
				var schoolNames = jQuery("#schoolNames").val();
				var branchNames = jQuery("#branchNames").val();//feature purpose
			 ClassMasterDWR.classNameChange(schoolNames,branchNames,oldClassID,newClassName,function(data){
			 			if(data == "success"){
			 				setError("schoolNames","Success");
			 				classMasterLoadDisplay();
			 			}else 	if(data == "already"){
			 				setError("schoolNames","Class Name  Already Exist");
			 			}else{
						setError('schoolNames', "Fail");
					}			 
			 });
		}
		
	    </script>

 <div style="height:25px;text-align: center;"><span id ="errorspan" style="color:red;font-size: 14px;font-weight: bold;" >&nbsp;</span></div>
	<div class="reg_mainCon">

     <fieldset>
	    <legend><img src="img/list_add_user.PNG" class="img-circle">&nbsp;&nbsp;Class Master</legend>
	    <div style="padding:20px;">
	   <table width="100%" border="0" cellspacing="0" cellpadding="0">
		  <tr>
                <td><label style="color:#000;"><b>Select School</b></label></td>
                <td><select class="span3"  name="schoolNames" id="schoolNames" onchange="selectSchoolforBranches(this.value);">
                        <option value="-1" selected="selected">Select</option>                       
                       </select>                   
                 </td>
                <td><label style="color:#000;"><b>Select Branch</b></label></td>
                <td><select class="span3" name="branchNames" id="branchNames" onchange="classMasterLoadDisplay();">
                        <option value="-1" selected="selected">Select</option>                      
                       </select>                   
                 </td>
        </tr>
		 <tr><td colspan="4"><label style="color:#000; text-align:center; font-size:18px; line-height:28px;"><b>Create New Class</b></label></td></tr>
 		<tr>
          <td colspan="4" style="text-align: center;"><div style="display: inline-block;"><label style="color:#000;"><b>Class Name</b></label></div>
          <div align="left" style="display: inline-block;">
              <input class="span3" type="text" placeholder="" id='newClassID' name='newClassID'>
			  <div style='display:inline-block;'><i onclick='addNewClass();' style='margin-top: -4px;'  class="icon-plus"></i></div>
             <!--  <button type="button" class="btn">Add</button> -->
            </div></td>
        </tr>
      </table>
	  </div>
	  
	<div id="classAreaID" style="line-height:50px; text-align:center; font-size:14px; color:#666666;">
	</div>
    
  <div style="padding-bottom: 11px;"></div>
    </fieldset>    
</div>


 		 <div id="idAlertDialog"    class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="windowTitleLabel" aria-hidden="true">
            <img src="img/alert.gif" width="34" height="28" alt="alert icon">
            <h3 id="idAlertDialogPrompt"></h3>
            <a href="#" class="btn btn-primary" onclick="okAlertDialog ();">OK</a>
            </div>
            
          <div id="idConfirmDialog" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="windowTitleLabel" aria-hidden="true">
			<img src="img/alert.gif" width="34" height="28" alt="confirm icon">
			<h3 id="idConfirmDialogPrompt"></h3>
			<a href="#" class="btn btn-primary" style="margin-top: 0px;" onclick="okConfirmDialog ();">Yes</a>
			<a href="#" class="btn" style="margin-top: 0px;"onclick="closeConfirmDialog ();">No</a>
			</div>
			
		 <div id="idConfirmDialogReName" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="windowTitleLabel" aria-hidden="true">
			<img src="img/alert.gif"  width="34" height="28" alt="confirm icon" /><h3 id="idConfirmDialogPromptReName"></h3>
				<div style="text-align: center;">
					<label style="color:#000;">Old:</label><input disabled="disabled" type="text" class="span2" id='oldClassName' />
					<label style="color:#000;">New:</label><input type="text" class="span2" id='newClassName' />
				</div>
				<br/>
				<div >
					<a href="#" class="btn btn-primary" style="margin-top: 0px;" onclick="okConfirmDialogReName ();">Yes</a>
					<a href="#" class="btn" style="margin-top: 0px;"onclick="closeConfirmDialogReName ();">No</a>
			</div>
			</div>
			
			<input type="hidden" id='hiddenClassID'>
			
