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
	        <script 	src="<%=request.getContextPath()%>/dwr/interface/StudentDWR.js"></script>
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
             #idConfirmDialogDisable {
				/* width: 450px;
				height: 165px; */
				min-width:450px;
				min-height:165px;
				background-color: whitesmoke;
				}
			 #idConfirmDialogDisable .btn {
				width: 70px;
				margin-bottom: 8px;
				margin-right: 15px;
				margin-top: 80px;
				float: right;
				}
			 #idConfirmDialogDisable h3 {
				margin-left: 60px;
				margin-top:15px;
				}
			 #idConfirmDialogDisable img {
				float: left;
				margin-left: 15px;
				margin-top: 25px;
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
	
	
			 #idConfirmDialogMove .btn {
				width: 70px;
				margin-bottom: 8px;
				margin-right: 15px;
				margin-top: 80px;
				float: right;
				}
			 #idConfirmDialogMove h3 {
				margin-left: 60px;
				margin-top:15px;
				}
			 #idConfirmDialogMove img {
				float: left;
				margin-left: 15px;
				margin-top: 25px;
				}
	
	
				.TogglerHaderALL .open .forChrome {
   		 /* 	background-color: #F5F5F5; */
   		 	background-image: url("img/icon_minus.gif");
    		background-position: 3px 4px;
    		background-repeat: no-repeat;
    		/* border: 1px solid #CCCCCC; */
    		color: #000000;
    		cursor: pointer;
    		display: block;
    		line-height: 22px;
    		padding-left: 20px;
    		text-align: left;
    		text-decoration: none;
    		width: 500px;
    	    font-size: 21px;
    		font-weight: bold;
	}


		.TogglerHaderALL .close .forChrome {
		/* 	background-color: #F5F5F5; */
			background-image: url("img/icon_plus.gif");
			background-position: 3px 4px;
			background-repeat: no-repeat;
		/* 	border: 1px solid #CCCCCC; */
			color: #000000;
			cursor: pointer;
			display: block;
			line-height: 22px;
			padding-left: 20px;
			text-align: left;
			text-decoration: none;
			width: 500px;
			font-size: 21px;
   			 font-weight: bold;
		}
		
	
</style>
<script type="text/javascript">


		var sectionListData;

		function okAlertDialog () {
			$("#idAlertDialog").modal ('hide'); 
			};
		function alertDialog (prompt) {
			document.getElementById ("idAlertDialogPrompt").innerHTML = prompt;
			$("#idAlertDialog").modal ("show");
			}
        
        function closeConfirmDialog () {
			$("#idConfirmDialogDisable").modal ('hide'); 
			};
		function okConfirmDialog () {
			$("#idConfirmDialogDisable").modal ('hide'); 
			disableSectionAfterConfirm();
			};
		function confirmDialog (prompt, callback) {
			document.getElementById ("idConfirmDialogPromptDisable").innerHTML = prompt;
			confirmDialogCallback = callback;
			$("#idConfirmDialogDisable").modal ("show");
			}
        
        function closeConfirmDialogReName () {
			$("#idConfirmDialogReName").modal ('hide'); 
			};
		function okConfirmDialogReName () {
			$("#idConfirmDialogReName").modal ('hide'); 
			reNameSectionAfterConfirm();
			};
		function confirmDialogReName (prompt, callback) {
			document.getElementById ("idConfirmDialogPromptReName").innerHTML = prompt;
			//confirmDialogCallback = callback;
			$("#idConfirmDialogReName").modal ("show");
			}
        
     	function closeConfirmDialogMove() {
			$("#idConfirmDialogMove").modal ('hide'); 
			};
		function okConfirmDialogMove () {
			$("#idConfirmDialogMove").modal ('hide'); 
			moveSectionAfterConfirm();
			};
		function confirmDialogMove (prompt, callback) {
			document.getElementById ("idConfirmDialogPromptMove").innerHTML = prompt;
			//confirmDialogCallback = callback;
			$("#idConfirmDialogMove").modal ("show");
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
		removeAllOptions("classNames");
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
	 	
		function selectBranchesforClasses(){
		   var branchID = jQuery("#branchNames").val();		
		   var schoolID = jQuery("#schoolNames").val();
		   
			if(schoolID == null || schoolID == ""){
				setError("schoolNames","Please select School");
				 jQuery("#schoolNames").focus();
				return false;
			}			
			if(branchID == null || branchID == ""){
				setError("branchNames","Please select Branch");
				 jQuery("#branchNames").focus();
				return false;
			}
			removeAllOptions("classNames");
		   try{
						var listofclasses2 = document.getElementById('classNames');					
						    ClassMasterDWR.getClassMasterList(schoolID,branchID,function(data){
						                                if (data == null) {													
						                                  alert("error");
														} else {				
														 for(var i = 0; i < data.length; i++) {
															  var opt = document.createElement("option");
															    var temp = data[i];
															    opt.value = temp[0];
															    opt.innerHTML = temp[1];
															    listofclasses2.appendChild(opt);
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
		
		function selectSectionForClasses(){
		   	var schoolID =  new Array();
		   		schoolID[0] = jQuery("#schoolNames").val();
			var branchID = new Array();
				branchID[0] = jQuery("#branchNames").val();
			var classID =  new Array();
				classID[0] = jQuery("#classNames").val();
			
			if(schoolID == null || schoolID == ""){
				setError("schoolNames","Please select School");
				 jQuery("#schoolNames").focus();
				return false;
			}			
			if(branchID == null || branchID == ""){
				setError("branchNames","Please select Branch");
				 jQuery("#branchNames").focus();
				return false;
			}
			if(classID == null || classID == ""){
				setError("classID","Please select Class");
				 jQuery("#classID").focus();
				return false;
			}
			try{
				 ClassMasterDWR.getMultiSectionMasterList(branchID,classID,function(data){
					                               if (data == null) {
						                                  alert("error");
														} else {	
														sectionListData = data;//move purpose
								var innerHtmlClass = '<table class="table table-bordered" width="100%" style="margin-bottom:0px;">';
									if(data.length>0){
										innerHtmlClass += '<tr><th style="text-align: center;">Section Name</th><th style="text-align: center;">Rename</th><th style="text-align: center;">Move</th><th style="text-align: center;">Disable</th></tr>';
								                 for(var i = 0; i < data.length; i++) {
													    var temp = data[i];
																	//sectionAreaID
														innerHtmlClass += '<tr  >';
														innerHtmlClass += '<td style="text-align: center;">'+temp[1]+'</td>';
														innerHtmlClass += '<td style="text-align: center;">';
														innerHtmlClass += '<a href="#" onclick="reNameSection(\''+temp[0]+'\',\''+temp[1]+'\')" >';
														innerHtmlClass += '<i class="icon-edit"></i></a></td>';
														innerHtmlClass += '<td style="text-align: center;">';
														innerHtmlClass += '<a href="#" onclick="moveSection(\''+temp[0]+'\',\''+temp[1]+'\')" >';
														innerHtmlClass += '<i class="icon-move"></i></a></td>';
														innerHtmlClass += '<td style="text-align: center;"><a href="#" onclick="disableSection(\''+temp[0]+'\',\''+temp[1]+'\')" ><i class="icon-trash"></i> </a></td>';
														innerHtmlClass += '</tr>';
												}
										}else{
														innerHtmlClass += '<tr  >';
														innerHtmlClass += '<td colspan="3" style="color:red;font-size:16px;font-weight:bold;text-align: center;background-color:silver ">No Sections Found</td>';													
														innerHtmlClass += '</tr>';
										}		
												
								innerHtmlClass += '</table>';
							jQuery('#sectionAreaID').html(innerHtmlClass);
							}
					      }) ;	
			   		 }catch(e){
							 alert("incatch::"+e);
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
	    
	    function addNewSection() {
			var newSectionName = jQuery('#newSectionID').val();			
			var classNames = jQuery('#classNames').val();			
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
			if(classNames=="-1"){
				 setError("classNames","Please Select  Class");
				jQuery("#classNames").focus();
				return false;
			}
			if(newSectionName.length<=0 || newSectionName.length>=20){
			 setError("newSectionID","Invalid Section Name");
				jQuery("#newSectionID").focus();
				return false;
			
			}
			
 			ClassMasterDWR.saveNewSection(schoolNames,branchNames,classNames,newSectionName,function(data){
					if(data == "success"){
						setError('schoolNames', "Success");
						selectSectionForClasses();
						 jQuery('#newSectionID').val('');		
					}else if(data == "already"){
						setError('schoolNames', "Section Name  Already Exist");
					}else{
						setError('schoolNames', "Fail");
					}					
			});		
		}
	    
	    
	    var studentedDocJson ;
	    function disableSection(idForReName,nameForReName) {
	    //get student data/list here ..and count 
	    //secID,cmID,bmID,smID
	    	var schoolNames 	= jQuery("#schoolNames").val();
			var branchNames 	= jQuery("#branchNames").val();
			var classNames 	= jQuery("#classNames").val();
			//String schoolNames,String branchNames,String classNames,String sectionNames){
			StudentDWR.getStudentListForDelete(schoolNames,branchNames,classNames,idForReName,function(data) {
		 																						if (data == null) {
																									alert("Error");	
																								} else {
																									studentedDocJson = jQuery.parseJSON(data);
																									//alert("First*"+studentedDocJson);
																									if(studentedDocJson != null && studentedDocJson.length > 0){
																						 			//alert("Second"+studentedDocJson.length);
																									}																							  
	 																								//not this one -->getStudentListDisp(data);
																								}
				});																				
			jQuery('#hiddenSectionID').val(idForReName);
			setTimeout(disableSectionBeforeConfirm,200);
		}
		
		function disableSectionBeforeConfirm(){
			if(studentedDocJson != null && studentedDocJson.length > 0){
			jQuery('#idCloseDisable').hide();
			confirmDialog ('<div style="font-size:14px">There are students mapped to this section. <br/>You cannot disable a section till there are students mapped to it. <br/><a href="#myModal_1" data-toggle="modal"  onclick="getStudentListDisp()" style="color: #FF3300">Click here</a> to View Student Details. <br/>Close this window and Move students to a different section first.', alert);
// 			confirmDialog ('<div style="\"font-size:18px\"">There are students mapped to this section.<a href="#myModal_1" data-toggle="modal"  onclick="getStudentListDisp()">View Details</a></div>', alert);
			}else{		
			confirmDialog ('<div style="font-size:16px">Do you want to disable this section permanently?</div>', alert);
			}
		}
		
		function disableSectionAfterConfirm(){
			var oldSectionID =jQuery('#hiddenSectionID').val(); 
			var schoolNames = jQuery("#schoolNames").val();
			var branchNames = jQuery("#branchNames").val();//feature purpose
			var classNames = jQuery("#classNames").val();
				var countForRef = 0;
				if(studentedDocJson != null && studentedDocJson.length > 0){
	 			countForRef = studentedDocJson.length;			
			}else{
				 	ClassMasterDWR.disableSectionMaster(schoolNames,branchNames,classNames,oldSectionID,countForRef,function(data) {
								if(data == "success"){
					 				setError("schoolNames","Success");
					 				selectSectionForClasses();
					 			}else 	if(data == "notexist"){
					 				setError("schoolNames","Section Name  Not Exist");
					 			}else{
								setError('schoolNames', "Fail");
							}		
					}); 
			
			}
		}
		
	    function reNameSection(idForReName,nameForReName) {
			confirmDialogReName ('<div style="font-size:18px;">Are you sure, you want to rename this Section?</div>', alert);
			//req hideen input for old class id?
			jQuery('#newSectionName').val("");
			jQuery('#oldSectionName').val(nameForReName);
			jQuery('#hiddenSectionID').val(idForReName);
		}
	    
		function reNameSectionAfterConfirm() {
			var oldSectionID =jQuery('#hiddenSectionID').val(); 
			var newSectionName = jQuery('#newSectionName').val();
			var oldSectionName = jQuery('#oldSectionName').val();
			
				if(newSectionName.length<1 || newSectionName.length>20 || newSectionName==oldSectionName){
					setError("schoolNames","Invalid Section Name");
					return false;
			}
			
				var schoolNames = jQuery("#schoolNames").val();
				var branchNames = jQuery("#branchNames").val();//feature purpose
				var classNames = jQuery("#classNames").val();//feature purpose
			 ClassMasterDWR.sectionNameChange(schoolNames,branchNames,classNames,oldSectionID,newSectionName,function(data){
			 			if(data == "success"){
			 				setError("schoolNames","Success");
			 				selectSectionForClasses();
			 			}else 	if(data == "already"){
			 				setError("schoolNames","Section Name  Already Exist");
			 			}else{
						setError('schoolNames', "Fail");
					}			 
			 });
		}
		
		function moveSection(idForMove,nameForMove) {
		removeAllOptions("newSectionNameMove");
			confirmDialogMove ('<div style="font-size:18px;">Are you sure, you want to move this Section?</div>', alert);
			//req hideen input for old class id?
			jQuery('#newSectionNameMove').val("");
			jQuery('#oldSectionNameMove').val(nameForMove);
			jQuery('#hiddenSectionID').val(idForMove);
			var listOfsec = document.getElementById('newSectionNameMove');		
			if(sectionListData.length>0){
					for(var i = 0; i < sectionListData.length; i++) {
															  var opt = document.createElement("option");
															    var temp = sectionListData[i];
															    if(temp[0] != idForMove){
															    opt.value = temp[0];
															    opt.innerHTML = temp[1];
															    
															    listOfsec.appendChild(opt);
															    }
															}
			}											
			//sectionListData--newSectionNameMove			
		}
		
		function moveSectionAfterConfirm() {
				var oldSectionID =jQuery('#hiddenSectionID').val(); 
			var oldSectionName = jQuery('#oldSectionNameMove').val();
			var newSectionID = jQuery('#newSectionNameMove').val();
			//var newSectionID = $("#newSectionNameMove option:selected").attr("id");
			
				if(newSectionID<=0 || oldSectionID==newSectionID){
					setError("schoolNames","Invalid Section Selected");
					return false;
			}
			
				var schoolNames = jQuery("#schoolNames").val();
				var branchNames = jQuery("#branchNames").val();//feature purpose
				var classNames = jQuery("#classNames").val();//feature purpose
			  ClassMasterDWR.sectionMove(schoolNames,branchNames,classNames,oldSectionID,newSectionID,function(data){
			 			if(data == "success"){
			 				setError("schoolNames","Success");
			 				//selectSectionForClasses();
			 			}else{
						setError('schoolNames', "Fail");
					}			 
			 }); 
		}
		
		
	var	 userTypeGlobal = "ST";
		var totaljson		= [];
		var indexforTotalJ = 0;
		var NewScrubber = [];
		var PageCount	= 5;
		//var OpenItemCount	= 15;
		var OpenItemCount	= 5;
		
		function getStudentListDisp1(){
		win2 =	window.open(getStudentListDisp(), "_parent", "height=580,width=870,scrollbars=1,location=no,menubar=no,resizable=0,status=no,toolbar=no,screenX=100,screenY=100");
		 
		}
		
	 function getStudentListDisp(){	 
	 
		var studentGridId  	 = jQuery('#studentDynamicGrid');
				studentGridId.html("");
	
			//var studentedDocJson = studentedDocJson;
			if(studentedDocJson != null && studentedDocJson.length > 0){
 			var mainJSONOBJ = '';
 			var globalIncr = 0;
 /*33*/	   for(var i = 0; i < studentedDocJson.length; i++) {//school Obj's
		                                   var studentID 			= i+"studentID";
	  
		      	var schoolWise = studentedDocJson[i];
				var one = 10;
				var OneMain ='';
				  
				  for(var subKKey in studentedDocJson[i]){//ABODE Kids International, value: [object Object]
				 		var studentStringCreate = '';
				 		var tempSchoolID = 'school'+subKKey+"-"+globalIncr;
						var tempSchoolIDA = 'Aschool'+subKKey+"-"+globalIncr;
							studentStringCreate += '<div style="   background-color: #FFFFFF; border: 1px solid #DDDDDD; border-radius: 6px 6px 6px 6px; color: #9DA0A4; font-size: 12px; font-weight: bold; left: -1px; margin: 0 auto; max-width: 98%; padding: 3px 3px;">';
							studentStringCreate += ' <div style="padding:10px 20px 20px 20px;"><fieldset>';
							studentStringCreate += '<div class="TogglerHaderALL" id=\''+tempSchoolIDA+'\' ><div  style="float:left;width:100%;" class="open"  >';
							studentStringCreate += ' <legend style="margin-bottom:15px">';
							studentStringCreate += '<a href="\''+tempSchoolIDA+'\'"  class="forChrome"  onclick="showTogglerBodyAll(\''+tempSchoolID+'\'); return false;" >School : '+subKKey+'</a></legend></div></div><div style="display: inline-block;padding-top: 6px;	float: left;width:100%;" id=\''+tempSchoolID+'\'>';

				   /*11*/		for(var subKKey2 in studentedDocJson[i][subKKey]){//0, value: [object Object]
				   			for(var subKKey3 in studentedDocJson[i][subKKey][subKKey2]){//AKI Compass, value: [object Object],[obje
		
									var tempBranchID = 'branch'+subKKey2+"-"+globalIncr;
									var tempBranchIDA = 'Abranch'+subKKey2+"-"+globalIncr;
									
									studentStringCreate += '<div style="   background-color: #FFFFFF; border: 1px solid #DDDDDD; border-radius: 6px 6px 6px 6px; color: #9DA0A4; font-size: 12px; font-weight: bold; left: -1px; margin: 0 auto; max-width: 98%; padding: 3px 3px;">';
									studentStringCreate += ' <div style="padding:10px 20px 20px 20px;">';
									studentStringCreate += ' <fieldset>';
									studentStringCreate += '<div class="TogglerHaderALL" id=\''+tempBranchIDA+'\' ><div  style="float:left;width:100%;" class="open"  >';
									studentStringCreate += ' <legend style="margin-bottom:15px">';
									studentStringCreate += '<a href="\''+tempBranchIDA+'\'"  class="forChrome" onclick="showTogglerBodyAll(\''+tempBranchID+'\'); return false;" >Branch : '+subKKey3+'</a></legend></div></div><div style="display: inline-block;padding-top: 6px;width:100%;	float: left;" id=\''+tempBranchID+'\'>';
										
								/*	studentStringCreate += '<div id="branch'+subKKey2+'"  width="100%" style="margin-bottom:0px;color:black;"> Branch : '+subKKey3+'</div></legend>'; */
									
								for(var subKKey4 in studentedDocJson[i][subKKey][subKKey2][subKKey3]){
						/*222*/		      for(var subKKey5 in studentedDocJson[i][subKKey][subKKey2][subKKey3][subKKey4]){
									var tempClassID = 'class'+subKKey4+"-"+globalIncr;
									var tempClassIDA = 'Aclass'+subKKey4+"-"+globalIncr;
									
									studentStringCreate += '<div style="   background-color: #FFFFFF; border: 1px solid #DDDDDD; border-radius: 6px 6px 6px 6px; color: #9DA0A4; font-size: 12px; font-weight: bold; left: -1px; margin: 0 auto; max-width: 98%; padding: 3px 3px;">';
									studentStringCreate += ' <div style="padding:10px 20px 20px 20px;" >';
									studentStringCreate += ' <fieldset>';
									studentStringCreate += '<div class="TogglerHaderALL" id=\''+tempClassIDA+'\' ><div  style="float:left;width: 100%;" class="open"  >';
									studentStringCreate += '<legend style="margin-bottom:15px"><a  href="\''+tempClassIDA+'\'"  class="forChrome"  onclick="showTogglerBodyAll(\''+tempClassID+'\'); return false;" > Class :  '+subKKey5+'</a></legend>';
									studentStringCreate += '</div></div><div style="display: inline-block;padding-top: 6px;	float: left;width:100%;" id=\''+tempClassID+'\'>';
/* 									studentStringCreate += '<a  onclick="showTogglerBodyAll(\'fromClass\',\''+tempClassID+'\'); return false;" > Class :  '+subKKey5+'</a></legend><div style="display: inline-block;padding-top: 6px;	float: right;" id=\''+tempClassID+'\'>'; */
									
									for(var subKKey6 in studentedDocJson[i][subKKey][subKKey2][subKKey3][subKKey4][subKKey5]){
										for(var subKKey7 in studentedDocJson[i][subKKey][subKKey2][subKKey3][subKKey4][subKKey5][subKKey6]){
											var tempSectionID = 'section'+subKKey6+"-"+globalIncr;
											var tempSectionIDA = 'Asection'+subKKey6+"-"+globalIncr;
								
											studentStringCreate += '<div style="   background-color: #FFFFFF; border: 1px solid #DDDDDD; border-radius: 6px 6px 6px 6px; color: #9DA0A4; font-size: 12px; font-weight: bold; left: -1px; margin: 0 auto; max-width: 98%; padding: 3px 3px;">';
											studentStringCreate += ' <div style="padding:10px 20px 20px 20px;"><fieldset>';
											studentStringCreate += '<div class="TogglerHaderALL" id=\''+tempSectionIDA+'\' ><div  style="float:left;width:100%;" class="open"  >';
											studentStringCreate += ' <legend style="margin-bottom:15px">';
											studentStringCreate += '<a href="\''+tempSectionIDA+'\'"  class="forChrome" onclick="showTogglerBodyAll(\''+tempSectionID+'\'); return false;" >Section : '+subKKey7+'</a></legend></div></div><div style="display: inline-block;padding-top: 6px;	float: left;width:100%;" id=\''+tempSectionID+'\'>';

/* 									studentStringCreate += ' <div  width="100%" style="margin-bottom:0px;color:black;">Section : '+subKKey7+'</div></legend>'; */
										   			if (OneMain !== '') {
															//studentStringCreate +='<div><hr style="border-style: none;height: 10px;"/></div>';
													}
										   			OneMain = one+""+i;
										 			one++;
														studentStringCreate += ' <div id="data'+OneMain+'"></div><div><hr style="border-style: none;margin: 4px;"/></div><div id="pag'+OneMain+'"></div>';

						   		 	  	studentStringCreate += '</div></fieldset></div></div><div><hr style="border-style: none;height: 0;margin:10px;"/></div>';     //section level
										 			globalIncr++;//2013-08-10
									}
								  }
						   		  studentStringCreate += '</div></fieldset></div></div><div><hr style="border-style: none;height: 0;margin:10px;"/></div>';     //class level
					/*222*/	   		}
						   	 }
						   	 studentStringCreate += '</div></fieldset></div></div><div><hr style="border-style: none;height: 0;margin:10px;"/></div>';     //branch level
						}
	/*11*/		}
						studentStringCreate += '</div></fieldset></div></div><div><hr style="border-style: none;height: 0;margin:15px;"/></div>';     //school level
				}
			//}
				 mainJSONOBJ += studentStringCreate;
/*33*/	}//main for loop
		 //		studentGridId.html(mainJSONOBJ);
		 		
		for(var k = 0; k < studentedDocJson.length; k++) {//school Obj's
			var schoolWise 	=  studentedDocJson[k];
			var oneD 				= 10;
			var OneMainD 		= '';

		     for(var subKey in studentedDocJson[k]){//7
		     
			        for(var subKey2 in studentedDocJson[k][subKey]){
			        
				         for(var subKey3 in studentedDocJson[k][subKey][subKey2]){
				         
				         	for(var subKey4 in studentedDocJson[k][subKey][subKey2][subKey3]){//4
				         	
							      for(var subKey5 in studentedDocJson[k][subKey][subKey2][subKey3][subKey4]){
							      
									for(var subKey6 in studentedDocJson[k][subKey][subKey2][subKey3][subKey4][subKey5]){
									
										for(var subKey7 in studentedDocJson[k][subKey][subKey2][subKey3][subKey4][subKey5][subKey6]){//1
								
								 	 		  totaljson[indexforTotalJ]=studentedDocJson[k][subKey][subKey2][subKey3][subKey4][subKey5][subKey6][subKey7];
												if(totaljson[indexforTotalJ].length>0){
												 	 	OneMainD = oneD+""+k;
									 					oneD++;
														NewScrubber[indexforTotalJ]		= new jQuery.DataScrubber(totaljson[indexforTotalJ], PageCount, OpenItemCount);
														renderPagination(NewScrubber[indexforTotalJ].init(),OneMainD,indexforTotalJ);			
														indexforTotalJ++;			
														}
														
								  }//1
								}
							  }
							}	//4	       
						}////key4: AKI Compass, value: [object Object],[object Object]---END						       
					}////key3: 0, value: [object Object]					        
				 }//7			//key2: ABODE Kids International, value: [object Object]--END
		      }//for loop
		     
			}/* else{
			 studentGridId.html('<table class="table table-bordered" width="100%" style="margin-bottom:0px;"><tr><td style="color:red;font-size:16px;font-weight:bold;text-align: center;background-color:silver ">No records found.</td></tr></table>');
			}//if */
	}
	
	function renderPagination(cc,idf,indexforTotalJT){
			var paginationStr = "";
				paginationStr += " <div class=\"displaydetailspagination\" style=\"float:left;display:inline-block;\">";
			if (Math.ceil(totaljson[indexforTotalJT].length / OpenItemCount) > 1) {
				paginationStr += " <input type='button' class='btn btn-mini prev' value='&lt;' onclick=\"renderPagination(NewScrubber["+indexforTotalJT+"].scrubLeft(),"+idf+","+indexforTotalJT+"); return false;\">";
				var tmpStr = "";
					for (var i = cc.paginationStart; i <= cc.paginationEnd; i+=1) {
						if (i == (cc.currentPageIdx + cc.paginationStart)) {
							tmpStr += "<input type='button' class='btn btn-warning btn-mini' value='"+i+"'>";
						} else {
							tmpStr += "<input type='button' class='btn btn-mini' onclick=\"renderPagination(NewScrubber["+indexforTotalJT+"].doScrub(" + i + "),"+idf+","+indexforTotalJT+"); return false;\" value="+i+" \">";
						}
					 }
				paginationStr += tmpStr;
				paginationStr += " <input type='button' class='btn btn-mini next' value='&gt;' onclick=\"renderPagination(NewScrubber["+indexforTotalJT+"].scrubRight(),"+idf+","+indexforTotalJT+"); return false;\">";
			}
				paginationStr += " </div>";
				paginationStr += " <div class=\"delivery_gotoPageTxt\" style=\"float:right;display:inline-block;\"> Total " + totaljson[indexforTotalJT].length + " Document(s).</div>";
				var tempID = "#pag"+idf;
				jQuery(tempID).html(paginationStr);
				renderData(cc,idf);
	}
	
	function renderData(pickObj,idff){
	
			pickObj=pickObj.records;
			var tempIDc = '#data'+""+idff;
			var newCallsGrid  	 = jQuery(tempIDc);
			var stringForm = '';
			
			 var  activeString;
					 if ($('#active').is(":checked")){
						activeString='Disable';
					} else{
					 	activeString='Enable';
				    }
		//based on user display content -->userTypeGlobal
		
				stringForm += '<table class="table table-bordered" width="100%" style="margin-bottom:0px;">';
				stringForm += '<tr style="color:#000">';
				  if (userTypeGlobal == "ST") {
				stringForm += '<th width="14%" style="text-align:center;">Name</th><th width="11%" style="text-align:center;">DOB</th><th width="11%">Gender</th>';
			
				stringForm += '<th width="11%" style="text-align:center;">Addr1</th><th width="11%" style="text-align:center;">Addr2</th><th width="11%">City</th>';
				stringForm += '<th width="11%" style="text-align:center;">Admission Number</th><th width="11%" style="text-align:center;">Admission Date</th><th width="20%" style="text-align:center;">Class Admitted</th>';
				stringForm += '<th width="9%" style="text-align:center;">Active</th>';
				}
			//	stringForm += '<th width="8%" style="text-align:center;">View Photo</th>';
			//	stringForm += '<th width="5%" style="text-align:center;">Edit</th><th width="5%" style="text-align:center;">'+activeString+'</th> </tr>';
				
			
			
			if(pickObj.length > 0){
               for(var subKey4 in pickObj){
				  var studentID 			= subKey4+"studentID"+idff;
					stringForm += '<tr>';
					  if (userTypeGlobal == "ST") {					                              	
			        stringForm += '<td  width="14%" style="text-align:center;">'+pickObj[subKey4].firstName+" "+pickObj[subKey4].lastName+'</td><td  width="13%" style="text-align:center;">'+pickObj[subKey4].dob+'</td>';
			        stringForm += ' <td  width="11%" style="text-align:center;">'+pickObj[subKey4].gender+' </td> <td  width="11%" style="text-align:center;">'+pickObj[subKey4].addr1+' </td>';					                             	
			        stringForm += '<td  width="20%" style="text-align:center;">'+pickObj[subKey4].addr2+'</td><td width="9%"  style="text-align:center;">'+pickObj[subKey4].city+'</td>';
			        stringForm += '<td  width="5%" style="text-align:center;">'+pickObj[subKey4].admissionNumber+'</td><td  width="10%"  style="text-align:center;">'+pickObj[subKey4].admissionDate+'</td>';
			        stringForm += '<td  width="20%" style="text-align:center;">'+pickObj[subKey4].className+'</td>';
			         stringForm += '<td width="9%"  style="text-align:center;">'+(pickObj[subKey4].active=="Y"?"Yes":"No")+'</td>';
            		//stringForm += '<td  width="8%" style="text-align:center;"><a href="downloadImage.action?type='+userTypeGlobal+'&fileName='+pickObj[subKey4].photo+'&admNumber='+pickObj[subKey4].admissionNumber+'" id="'+pickObj[subKey4].smID+'" ><i class="icon-eye-open"></i> </a></td>	<td  width="5%" style="text-align:center;"><a href="javascript:void(0)" id="'+studentID+'"  value="'+pickObj[subKey4].stuID+'" onclick="editUserCall(id,\''+pickObj[subKey4].stuID+'\');"><i class="icon-pencil"></i></a> </td>		<td  width="5%"  style="text-align:center;"><a href="#"  onclick="dEnableUserCall(\''+pickObj[subKey4].stuID+'\');" ><i class="icon-trash"></i> </a></td></tr>';
            	//	stringForm += '<td  width="8%" style="text-align:center;"><img style="height: 35px; width: 35px;" src="downloadImage.action?type='+userTypeGlobal+'&fileName='+pickObj[subKey4].photo+'&admNumber='+pickObj[subKey4].email+'" id="'+pickObj[subKey4].smID+'" onclick="openImageViewer(\''+pickObj[subKey4].photo+'\',\''+pickObj[subKey4].admissionNumber+'\');"  /></td>	<td  width="5%" style="text-align:center;"><a href="javascript:void(0)" id="'+studentID+'"  value="'+pickObj[subKey4].stuID+'" onclick="editUserCall(id,\''+pickObj[subKey4].stuID+'\');"><i class="icon-pencil"></i></a> </td>		<td  width="5%"  style="text-align:center;"><a href="#"  onclick="dEnableUserCall(\''+pickObj[subKey4].stuID+'\');" ><i class="icon-trash"></i> </a></td></tr>';
			       
			       }
/*             		stringForm += '<td  width="8%" style="text-align:center;"><a href="downloadImage.action?type=ST&fileName='+pickObj[subKey4].photo+'&admNumber='+pickObj[subKey4].admissionNumber+'" id="'+pickObj[subKey4].smID+'" ><i class="icon-eye-open"></i> </a></td>	<td  width="5%" style="text-align:center;"><a href="javascript:void(0)" id="'+studentID+'"  value="'+pickObj[subKey4].stuID+'" onclick="editUserCall(id,\''+pickObj[subKey4].stuID+'\');"><i class="icon-pencil"></i></a> </td>		<td  width="5%"  style="text-align:center;"><a href="#" id="'+studentID+'"  value="'+pickObj[subKey4].stuID+'" onclick="dEnableUserCall(id);" ><i class="icon-trash"></i> </a></td></tr>'; */
			  
			   }///DATA end
              	stringForm +='</table>';
		}else{
          		stringForm += '<table class="table table-bordered" width="100%" style="margin-bottom:0px;"><tr><td>No records found.</td></tr></table>';
         }//if
		// newCallsGrid.html(stringForm);
		 	$("#myModalLabel11").text("Test"); 
			$("#bodyyID").html(stringForm);
	}		
		
	    </script>

 <div style="height:25px;text-align: center;"><span id ="errorspan" style="color:red;font-size: 14px;font-weight: bold;" >&nbsp;</span></div>
	<div class="reg_mainCon">

     <fieldset>
	    <legend><img src="img/list_add_user.PNG" class="img-circle">&nbsp;&nbsp;Section Master</legend>
	    <div style="padding:20px;">
	   <table width="100%" border="0" cellspacing="0" cellpadding="0">
		  <tr>
                <td><label style="color:#000;"><b>Select School</b></label></td>
                <td><select class="span3"  name="schoolNames" id="schoolNames" onchange="selectSchoolforBranches(this.value);">
                        <option value="-1" selected="selected">Select</option>                       
                       </select>                   
                 </td>
                <td><label style="color:#000;"><b>Select Branch</b></label></td>
                <td><select class="span3" name="branchNames" id="branchNames" onchange="selectBranchesforClasses();">
                        <option value="-1" selected="selected">Select</option>                      
                       </select>                   
                 </td>
        </tr>
        	  <tr>
                <td><label style="color:#000;"><b>Select Class</b></label></td>
                <td><select class="span3"  name="classNames" id="classNames" onchange="selectSectionForClasses();">
                        <option value="-1" selected="selected">Select</option>                       
                       </select>                   
                 </td>
        </tr>
		 <tr><td colspan="4"><label style="color:#000; text-align:center; font-size:18px; line-height:28px;"><b>Create New Section</b></label></td></tr>
 		<tr>
          <td colspan="4" style="text-align: center;"><div style="display: inline-block;"><label style="color:#000;"><b>Section Name</b></label></div>
          <div align="left" style="display: inline-block;">
              <input class="span3" type="text" placeholder="" id='newSectionID' name='newSectionID'>
			  <div style='display:inline-block;'><i onclick='addNewSection();' style='margin-top: -4px;'  class="icon-plus"></i></div>
             <!--  <button type="button" class="btn">Add</button> -->
            </div></td>
        </tr>
      </table>
	  </div>
	  
	<div id="sectionAreaID" style="line-height:50px; text-align:center; font-size:14px; color:#666666;">
	</div>
    
  <div style="padding-bottom: 11px;"></div>
    </fieldset>    
</div>


 		 <div id="idAlertDialog"    class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="windowTitleLabel" aria-hidden="true">
            <img src="img/alert.gif" width="34" height="28" alt="alert icon">
            <h3 id="idAlertDialogPrompt"></h3>
            <a href="#" class="btn btn-primary" onclick="okAlertDialog ();">OK</a>
            </div>
            
          <div id="idConfirmDialogDisable" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="windowTitleLabel" aria-hidden="true">
			<img src="img/alert.gif" width="34" height="28" alt="confirm icon">
			<h3 id="idConfirmDialogPromptDisable"></h3>
			<a href="#" class="btn btn-primary" style="margin-top: 0px;" onclick="okConfirmDialog ();">OK</a>
			<a href="#" id='idCloseDisable' class="btn" style="margin-top: 0px;"onclick="closeConfirmDialog ();">Cancel</a>
			</div>
			
		 <div id="idConfirmDialogReName" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="windowTitleLabel" aria-hidden="true">
			<img src="img/alert.gif"  width="34" height="28" alt="confirm icon" /><h3 id="idConfirmDialogPromptReName"></h3>
				<div style="text-align: center;">
					<label style="color:#000;">Old:</label><input disabled="disabled" type="text" class="span2" id='oldSectionName' />
					<label style="color:#000;">New:</label><input type="text" class="span2" id='newSectionName' />
				</div>
				<br/>
				<div >
					<a href="#" class="btn btn-primary" style="margin-top: 0px;" onclick="okConfirmDialogReName ();">Yes</a>
					<a href="#" class="btn" style="margin-top: 0px;"onclick="closeConfirmDialogReName ();">No</a>
			</div>
			</div>
		 <div id="idConfirmDialogMove" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="windowTitleLabel" aria-hidden="true">
			<img src="img/alert.gif"  width="34" height="28" alt="confirm icon" /><h3 id="idConfirmDialogPromptMove"></h3>
				<div style="text-align: center;">
					<label style="color:#000;">From :</label><input disabled="disabled" type="text" class="span2" id='oldSectionNameMove' />
					<label style="color:#000;">Move to :</label>
					<select class="span2"  name="newSectionNameMove" id="newSectionNameMove" >
                       </select>     
					<!-- <input type="text" class="span2" id='newSectionNameMove' /> -->
				</div>
				<br/>
				<div >
					<a href="#" class="btn btn-primary" style="margin-top: 0px;" onclick="okConfirmDialogMove ();">Yes</a>
					<a href="#" class="btn" style="margin-top: 0px;"onclick="closeConfirmDialogMove ();">No</a>
			</div>
			</div>
			
			<input type="hidden" id='hiddenSectionID'>
			
			<div id="studentDynamicGrid" ></div>
			
	<div id="myModal_1" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	  <div class="modal-header">
	    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
	    <h3 id="myModalLabel11"></h3>
	  </div>
	  <div class="modal-body">
	    <p id="bodyyID"></p>
	  </div>
	  <div class="modal-footer">
	    <button class="btn" data-dismiss="modal" aria-hidden="true">Close</button>
	  </div>
	</div>
	
	
 <script  src="js/paginationjQuery.js"> </script> 