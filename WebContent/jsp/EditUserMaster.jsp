<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	    pageEncoding="ISO-8859-1"%>
    	
    	<%     	
    	
    		if(session.getAttribute("short_name") != null  && ((String)session.getAttribute("userType")).equalsIgnoreCase("AD")&& session.getAttribute("superadmin") == null ){
    			if(session.getAttribute("IM_ID")!= null &&session.getAttribute("SM_ID")!= null &&session.getAttribute("BM_ID")!= null &&session.getAttribute("UM_ID")!= null && session.getAttribute("pageTO")!=null){
    			}else{response.sendRedirect("BeforeLogin.action");	
    			}
    		}else{
    		response.sendRedirect("BeforeLogin.action");	
    		}
    		
    		String userTypeEdit = request.getParameter("p1");
    	
    	%>
    	
    	
    <%
        String searchHistoryQu =null;
    	String searchHistoryJSON =null;
    		if (userTypeEdit == "EditStudent") {
    		     	searchHistoryQu =(String) session.getAttribute("queryForStudentSearch");
     				searchHistoryJSON =(String) session.getAttribute("jsonForStudentSearch");
     				    session.removeAttribute("queryForStudentSearch");
     				    session.removeAttribute("jsonForStudentSearch");
			} else if (userTypeEdit == "EditStaff") {
			}else if (userTypeEdit == "EditParent") {
				    searchHistoryQu =(String) session.getAttribute("queryForParentSearch");
     				searchHistoryJSON =(String) session.getAttribute("jsonForParentSearch");
     					  	session.removeAttribute("queryForParentSearch");
     				    	session.removeAttribute("jsonForParentSearch");
			}
    
   
    System.out.println("searchHistoryQu**"+searchHistoryQu);
    System.out.println("searchHistoryJSON**"+searchHistoryJSON);
/*     if(searchHistoryQu != "" && searchHistoryQu != null && searchHistoryQu != "null" ){
    session.removeAttribute("queryForStudentSearch");    
    }
    if(searchHistoryJSON != "" && searchHistoryJSON != null && searchHistoryJSON != "null" ){
    session.removeAttribute("jsonForStudentSearch");    
    } */
    
     %>
    	
    	<!-- css -->
    	<link href="css/datepicker.css"  rel="stylesheet" type="text/css" media="screen"  >
    		<style type="text/css">
		span.mandatory {
		    color: #FF0000;
		    display: inline;
		}
	.span3{
		width: 258px;
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
				
					.TogglerHader .open a {
   		 	background-color: #F5F5F5;
   		 	background-image: url("img/icon_minus.gif");
    		background-position: 3px 4px;
    		background-repeat: no-repeat;
    		border: 1px solid #CCCCCC;
    		color: #000000;
    		cursor: pointer;
    		display: block;
    		line-height: 22px;
    		padding-left: 20px;
    		text-align: left;
    		text-decoration: none;
    		width: 77px;
    	    font-size: 12px;
    		font-weight: normal;
	}


		.TogglerHader .close a {
			background-color: #F5F5F5;
			background-image: url("img/icon_plus.gif");
			background-position: 3px 4px;
			background-repeat: no-repeat;
			border: 1px solid #CCCCCC;
			color: #000000;
			cursor: pointer;
			display: block;
			line-height: 22px;
			padding-left: 20px;
			text-align: left;
			text-decoration: none;
			width: 77px;
			font-size: 12px;
   			 font-weight: normal;
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
    	
    	<link href="css/bootstrap-lightbox.css" rel="stylesheet" media="screen">
    	<script src="js/bootstrap-lightbox.js"></script>
    	
    	
	    <!-- javascripts -->
	    <script src="js/bootstrap-multiselect.js" type="text/javascript"></script>
	    <script src="js/bootstrap-datetimepicker.min.js"> </script>
    	<script  src="js/bootstrap-datetimepicker.pt-BR.js"> </script>
    	
    	<!-- DWR calls -->
	    <script 	src="<%=request.getContextPath()%>/dwr/interface/UserTypeMasterDWR.js"></script>
	    <script 	src="<%=request.getContextPath()%>/dwr/interface/UserMasterDWR.js"></script>
	    <script 	src="<%=request.getContextPath()%>/dwr/interface/SchoolMasterDWR.js"></script>
	    <script 	src="<%=request.getContextPath()%>/dwr/interface/BranchMasterDWR.js"></script>
	     <script 	src="<%=request.getContextPath()%>/dwr/interface/ClassMasterDWR.js"></script>
	     <script 	src="<%=request.getContextPath()%>/dwr/interface/StudentDWR.js"></script>
	     <script 	src="<%=request.getContextPath()%>/dwr/interface/ParentDetailsDWR.js"></script>
	     <script 	src="<%=request.getContextPath()%>/dwr/interface/StaffDWR.js"></script>
		<script type="text/javascript">
		
		   var searchQueryForHis = "<%=searchHistoryQu%>";
        var searchJSONForHis = '<%=searchHistoryJSON%>';        
        var searchHisOnloadJson = '';        
        
        //identifies user type
        var userTypeGlobal = '';
        
        // code for alert box
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
			disableUserAfterConfirm();
			};
		function confirmDialog (prompt, callback) {
			document.getElementById ("idConfirmDialogPrompt").innerHTML = prompt;
			confirmDialogCallback = callback;
			$("#idConfirmDialog").modal ("show");
			}
        
        function closeConfirmDialogSearch () {
			$("#idConfirmDialogSearch").modal ('hide'); 
			};
		function okConfirmDialogSearch () {
			$("#idConfirmDialogSearch").modal ('hide'); 
			deleteSearchHistoryAfterConfirm();
			};
		function confirmDialogSearch (prompt, callback) {
			document.getElementById ("idConfirmDialogPromptSearch").innerHTML = prompt;
		//	confirmDialogCallback = callback;
			$("#idConfirmDialogSearch").modal ("show");
			}	
        
		
		 $(document).ready(function() {
        		
     				$('#schoolNames').multiselect({
							buttonText: function(options, select) {
								if (options.length == 0) {
									return 'Select School <b class="caret"></b>';
								}
								else if (options.length > 1) {
									return options.length + ' selected <b class="caret"></b>';
								}
								else {
									var selected = '';
									options.each(function() {
										var label = ($(this).attr('label') !== undefined) ? $(this).attr('label') : $(this).text();
				
										selected += label + ', ';
									});
									return selected.substr(0, selected.length -2) + ' <b class="caret"></b>';
								}
							},						
					buttonWidth: '242px',
					filterPlaceholder: 'Search School'
				});
				
				$('#branchNames').multiselect({
						buttonText: function(options, select) {
							if (options.length == 0) {
								return 'Select Branch <b class="caret"></b>';
							}
							else if (options.length > 1) {
								return options.length + ' selected <b class="caret"></b>';
							}
							else {
								var selected = '';
								options.each(function() {
									var label = ($(this).attr('label') !== undefined) ? $(this).attr('label') : $(this).text();
			
									selected += label + ', ';
								});
								return selected.substr(0, selected.length -2) + ' <b class="caret"></b>';
							}
						},														
					buttonWidth: '242px',
					filterPlaceholder: 'Search Branch'
				});
				
				$('#classNames').multiselect({
						buttonText: function(options, select) {
							if (options.length == 0) {
								return 'Select Class <b class="caret"></b>';
							}
							else if (options.length > 1) {
								return options.length + ' selected <b class="caret"></b>';
							}
							else {
								var selected = '';
								options.each(function() {
									var label = ($(this).attr('label') !== undefined) ? $(this).attr('label') : $(this).text();
			
									selected += label + ', ';
								});
								return selected.substr(0, selected.length -2) + ' <b class="caret"></b>';
							}
						},														
					buttonWidth: '242px',
					filterPlaceholder: 'Search Class'
				});
		
				$('#sectionNames').multiselect({
						buttonText: function(options, select) {
							if (options.length == 0) {
								return 'Select Section <b class="caret"></b>';
							}
							else if (options.length > 2) {
								return options.length + ' selected <b class="caret"></b>';
							}
							else {
								var selected = '';
								options.each(function() {
									var label = ($(this).attr('label') !== undefined) ? $(this).attr('label') : $(this).text();
			
									selected += label + ', ';
								});
								return selected.substr(0, selected.length -2) + ' <b class="caret"></b>';
							}
						},														
					buttonWidth: '271px',
					filterPlaceholder: 'Search Section'
				});
				
					$('#classAdmittedIn').multiselect({
						buttonText: function(options, select) {
							if (options.length == 0) {
								return 'Select Class <b class="caret"></b>';
							}
							else if (options.length > 2) {
								return options.length + ' selected <b class="caret"></b>';
							}
							else {
								var selected = '';
								options.each(function() {
									var label = ($(this).attr('label') !== undefined) ? $(this).attr('label') : $(this).text();
			
									selected += label + ', ';
								});
								return selected.substr(0, selected.length -2) + ' <b class="caret"></b>';
							}
						},														
					buttonWidth: '271px',
					filterPlaceholder: 'Search Class'
				});
				
	 
					schoolMasterList();
					getUserTypeMaster();
					searchHistoryLoad();//Staff purpose ......commented
					jQuery("#schoolNames").multiselect('deselect', "0");

	   });//onready end 
		    
		    
		    
      	function	changeUserRights(value){
      			document.getElementById("designation").style.display="none";

					if(value == "TC" || value == "NTS" ||value == "Admin"){
						jQuery("#tileDisp").html('View Staff Details');
						jQuery("#hidePurposeEdit").val('Add Staff');
						jQuery('.parentClassID').hide();
						jQuery("#hidePurposeEdit").show();						
						document.getElementById("designation").disabled=false;		
						document.getElementById("designation").style.display="block";
						
						//document.getElementById("isParent").disabled=false;		
						//document.getElementById("isParent").style.display="block";
						
						jQuery('#isDefaultLable').hide();
						jQuery('#isDefaultCheck').hide();
						
						jQuery('#classSectionIDDisp').hide();						
						jQuery('#branchNamesGo').hide();		
						//jQuery("#branchIDDisp").css("width", "271px");				
						
						document.getElementById("parentTypeSel").style.display="block";						
						document.getElementById("parentTypeSel").disabled=true;	
						
						document.getElementById("admissionOne").style.display='none';
						document.getElementById("admissionTwo").style.display='none';
						}
					else{
						jQuery("#tileDisp").html('View Parent Details');
						//jQuery("#hidePurposeEdit").val('Add Parent');
						jQuery("#hidePurposeEdit").hide();
						jQuery('.parentClassID').show();
							
						//document.getElementById("isParent").style.display="block";						
						//document.getElementById("isParent").disabled=true;	

						document.getElementById("parentTypeSel").disabled=false;		
						document.getElementById("parentTypeSel").style.display="block";
						
						jQuery('#desigID').hide();
						jQuery('#desigIDLabel').hide();
						
						jQuery('#isDefaultLable').show();
						jQuery('#isDefaultCheck').show();			
									
						jQuery('#classSectionIDDisp').show();		
						jQuery('#branchNamesGo').show();		
						
						//jQuery("#branchNames").css("width", "271px");		
						
						jQuery('#admissionOne').show();
						jQuery('#admissionTwo').show();//bcz based on student details ,,they may get parent deatils
						//document.getElementById("admissionOne").style.display='none';
					//	document.getElementById("admissionTwo").style.display='none';
						if(value == "ST"){
							jQuery("#tileDisp").html('View Student Details');
							jQuery("#hidePurposeEdit").show();
							jQuery("#hidePurposeEdit").val('Add Student');
							jQuery('.parentClassID').hide();
							jQuery('#desigID').hide();
							jQuery('#desigIDLabel').hide();
						 	jQuery('#admissionOne').show();
							jQuery('#admissionTwo').show();
							 
							jQuery('#isDefaultLable').hide();
							jQuery('#isDefaultCheck').hide();
						    }
					}
      	}

	    var userTypes;
	    function getUserTypeMaster(){
				//Process DWR/AJAX request here
				try{
				var userTypeM = '<%=userTypeEdit%>';
				//alert(userTypeM+"<--");
				if (userTypeM == "EditStudent") {
					userTypeGlobal = "ST";
					changeUserRights("ST");
				} else if (userTypeM == "EditStaff") {
					userTypeGlobal = "TC";
					changeUserRights("TC");
				}else if (userTypeM == "EditParent") {
					userTypeGlobal = "PA";
					changeUserRights("PA");
				}
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
					        jQuery("#infoError").html("&nbsp;");
	        	     } 	
			}
				    

	    
	 function schoolMasterList(){
		try{
			SchoolMasterDWR.getSchoolMasterList(function(data){
                               if (data == null) {													
                                 alert("error");						                                 
							} else {				
                              for(var i = 0; i < data.length; i++) {
							  var opt = document.createElement("option");
								    var temp = data[i];														
								    var d 		= temp[0];
								    var dV		 = temp[1];
								    var  innerHtml= "<option value="+d+">"+dV+"</option>)";															    
								    $('#schoolNames').append(innerHtml);
									  }
							$('#schoolNames').multiselect('rebuild');		                          
						}
			}) ; 
		 }catch(e){
			 alert("incatch::"+e);
			jQuery.log.info(e.message);
		}
	}
		 
	function selectSchoolforBranches(){
	     
	   var schoolID = jQuery("#schoolNames").val();
		removeAllOptions("#branchNames");
		removeAllOptions("#classNames");
		removeAllOptions("#classAdmittedIn");
		removeAllOptions("#sectionNames");
		
		if(schoolID == null || schoolID == ""){
			setError("schoolNames","Please select School");
			 jQuery("#schoolNames").focus();
			return false;
		}
		   try{
					var listofbranchs = document.getElementById('branchNames');				
					    BranchMasterDWR.getMultiBranchMasterList(schoolID,function(data){
						                                if (data == null) {
						                                  alert("error");
														} else {
													       for(var i = 0; i < data.length; i++) {
															  var opt 		= document.createElement("option");
															   var temp 	= data[i];
															  	var  innerHtml= "<option value="+ temp[0]+">"+ temp[1]+"</option>";
															    	$('#branchNames').append(innerHtml);
															}
														}
															$('#branchNames').multiselect('rebuild');
						      }) ;
		   		 }catch(e){
						 alert("incatch::"+e);
		         } 	
	 }
	 
	 function selectBranchesforClasses(val){
	   var schoolID = jQuery("#schoolNames").val();
		removeAllOptions("#classNames");
		removeAllOptions("#classAdmittedIn");
		removeAllOptions("#sectionNames");
		if(schoolID == null || schoolID == ""){
			setError("schoolNames","Please select School");
			 jQuery("#schoolNames").focus();
			return false;
		}
		
		var branchID = jQuery("#branchNames").val();
		if(branchID == null || branchID == ""){
			setError("branchNames","Please select Branch");
			 jQuery("#branchNames").focus();
			return false;
		}
		   try{
		   	var im_id = "<%=session.getAttribute("IM_ID")%>";			   	
			    ClassMasterDWR.getMultiClassMasterList(branchID,function(data){
				                                if (data == null) {
				                                  alert("error");
												} else {
				                               var classListJson  =  jQuery.parseJSON(data);
				                                var tttt = '';
					                              if(classListJson != null && classListJson.length > 0){				                               
					                                 for(var i = 0; i < classListJson.length; i++) {
						                                 if (tttt != classListJson[i].class_name) {
						                                  	tttt = classListJson[i].class_name;
						                               		var opt = document.createElement("option");
													    	var  innerHtml= "<option value="+ classListJson[i].class_id+">"+ classListJson[i].class_name+"</option>";
													    	$('#classNames').append(innerHtml);
													    	$('#classAdmittedIn').append(innerHtml);
														}
				                               	     }
				                               	 }
											}
											$('#classNames').multiselect('rebuild');
											$('#classAdmittedIn').multiselect('rebuild');
											jQuery("#classNames").focus();
				      }) ;						
						jQuery("#classNames").focus();
		   		 }catch(e){
						 alert("incatch::"+e);
		         } 	
		 }
		 
		function selectSectionForClasses(val){
		
		   	var schoolID = jQuery("#schoolNames").val();
			var branchID = jQuery("#branchNames").val();
			var classID = jQuery("#classNames").val();
			removeAllOptions("#sectionNames");
			
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
				var listofclasses = document.getElementById('sectionNames');		
				
				 ClassMasterDWR.getMultiSectionMasterList(branchID,classID,function(data){
					                               if (data == null) {
						                                  alert("error");
														} else {
						                             	  for(var i = 0; i < data.length; i++) {
																var opt 		= document.createElement("option");
																var temp 	= data[i];
																var  innerHtml= "<option value="+ temp[0]+">"+ temp[1]+"</option>";
																$('#sectionNames').append(innerHtml);
														   }
														}
														
												$('#sectionNames').multiselect('rebuild');
												jQuery("#sectionNames").focus();
					      }) ;	
							jQuery("#sectionNames").focus();
			   		 }catch(e){
							 alert("incatch::"+e);
			         } 	
		 }
		 
	  function removeAllOptions(whichOption){
					$(whichOption).html('');
					var  innerHtml= "";
					
					if (whichOption == "#selectType") {
					// jQuery("#selectType").multiselect('deselect', "Parents");
						  innerHtml= "<option value='0'>Select All </option><option value='Students'>Students</option>"+"<option value='Parents'>Parents</option>";							 														    
					}else{
					  innerHtml= "<option value='0'>Select All </option>";
					  }			
				    $(whichOption).append(innerHtml);
				    $(whichOption).multiselect('rebuild');	
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
	    
    </script>
    <script>
	    
	    	//PAgination
		var totaljson		= [];
		var indexforTotalJ = 0;
		var NewScrubber = [];
		var PageCount	= 5;
		//var OpenItemCount	= 15;
		var OpenItemCount	= 5;
	    
	    function  getUsersList(){
			var  schoolNames 				= 	jQuery("#schoolNames").val();
			var  branchNames 				= 	jQuery("#branchNames").val();
			var classNames 	 	 			= jQuery("#classNames").val();
			var sectionNames  	 				= jQuery("#sectionNames").val();			 
			 
			var  fname 								= 	jQuery("#fname").val();
			var  lname 								= 	jQuery("#lname").val();
			var  dob 									= 	jQuery("#dob").val();
			var  email 								= 	jQuery("#email").val();
			var  mobile 							= 	jQuery("#mobile").val();
			var  landline 							= 	jQuery("#landline").val();
			var  addr1 								= 	jQuery("#addr1").val();
			var  addr2 								= 	jQuery("#addr2").val();		
			var  city 									= 	jQuery("#city").val();
			var state									= 	jQuery("#state").val();
			var gender								= 	jQuery("#gender").val();
			
			var admissionNumber			= jQuery("#admissionNumber").val();
			var admissionDateFrom		= jQuery("#admissionDateFrom").val();
			var admissionDateTO			= jQuery("#admissionDateTO").val();
			var classAdmittedIn				= jQuery("#classAdmittedIn").val();
			var fromDate  	 					= jQuery("#fromDate").val();
			var toDate  			 				= jQuery("#toDate").val();
			//staff	
			var designation			 				= jQuery("#designation").val();
	
			var  active								= jQuery("#active").prop('checked') ? 'Y':'N';

			//var  isParentCheck								= jQuery("#isParent").prop('checked') ? 'Y':'N';
			//2 fileds for parent ..extra i think 2013-08-10---parent type ,isDefault
			var  isDefault								= jQuery("#isDefault").prop('checked') ? 'Y':'N';			
			var parentTypeSel  	 					= jQuery("#parentTypeSel").val();
			
			 var studentGridId  	 = jQuery('#studentDynamicGrid');		 
			 studentGridId.html("");		 
	 		var pageTO = '<%=userTypeEdit%>';
				//alert(pageTO+"<--");
			if (pageTO == "EditStudent") {				
		 			StudentDWR.getStudentListForEdit(schoolNames,branchNames,classNames,sectionNames,
		 																			fname,lname,dob,email,mobile,landline,addr1,addr2,city,
		 																			state,gender,admissionNumber,classAdmittedIn,
		 																			admissionDateFrom,admissionDateTO,active,function(data) {
		 																						if (data == null) {
																									alert("Error");	
																								} else {
	 																								getStudentListDisp(data);
																								}
				 	});
			} else if (pageTO == "EditStaff") {
					StaffDWR.getStaffListForEdit(schoolNames,branchNames,classNames,sectionNames,
		 																			fname,lname,dob,email,mobile,landline,addr1,addr2,city,
		 																			state,gender,admissionNumber,classAdmittedIn,
		 																			admissionDateFrom,admissionDateTO,active,parentTypeSel,isDefault,designation,"N",function(data) {
		 																		//	admissionDateFrom,admissionDateTO,active,parentTypeSel,isDefault,designation,isParentCheck,function(data) {
		 																						if (data == null) {
																									alert("Error");	
																								} else {
																								/* 	if (isParentCheck =='Y') {
		 																							getStudentListDisp(data);
																									} else if (isParentCheck =='N') { */
																										getStaffNoParentCheckDisp(data);	
																								//	}
																								
																								}
				 		});
			
			} else if (pageTO == "EditParent") {
			 			ParentDetailsDWR.getParentsListForEdit(schoolNames,branchNames,classNames,sectionNames,
		 																			fname,lname,dob,email,mobile,landline,addr1,addr2,city,
		 																			state,gender,admissionNumber,classAdmittedIn,
		 																			admissionDateFrom,admissionDateTO,active,parentTypeSel,isDefault,function(data) {
		 																						if (data == null) {
																									alert("Error");	
																								} else {
	 																							getStudentListDisp(data);
																								}
				 		});
			}
	 		
	 }
	 

	 
	 function getStudentListDisp(data){
		var studentGridId  	 = jQuery('#studentDynamicGrid');
				studentGridId.html("");
		if (data == null) {
                         alert("error");
		} else {
			var studentedDocJson = jQuery.parseJSON(data);
			if(studentedDocJson != null && studentedDocJson.length > 0){
 			var mainJSONOBJ = '';
 			var globalIncr = 0;
		     for(var i = 0; i < studentedDocJson.length; i++) {//school Obj's
		                                   var studentID 			= i+"studentID";
	  
		      	var schoolWise = studentedDocJson[i];
				var one = 10;
				var OneMain ='';
				  
				  for(var subKKey in studentedDocJson[i]){//ABODE Kids International, value: [object Object]
				 		var studentStringCreate = '';
				 		var tempSchoolID = 'school'+subKKey+"-"+globalIncr;;
						var tempSchoolIDA = 'Aschool'+subKKey+"-"+globalIncr;;
							studentStringCreate += '<div style="   background-color: #FFFFFF; border: 1px solid #DDDDDD; border-radius: 6px 6px 6px 6px; color: #9DA0A4; font-size: 12px; font-weight: bold; left: -1px; margin: 0 auto; max-width: 98%; padding: 3px 3px;">';
							studentStringCreate += ' <div style="padding:10px 20px 20px 20px;"><fieldset>';
							studentStringCreate += '<div class="TogglerHaderALL" id=\''+tempSchoolIDA+'\' ><div  style="float:left;width:100%;" class="open"  >';
							studentStringCreate += ' <legend style="margin-bottom:15px">';
							studentStringCreate += '<a href="\''+tempSchoolIDA+'\'"  class="forChrome"  onclick="showTogglerBodyAll(\''+tempSchoolID+'\'); return false;" >School : '+subKKey+'</a></legend></div></div><div style="display: inline-block;padding-top: 6px;	float: left;width:100%;" id=\''+tempSchoolID+'\'>';

				   		for(var subKKey2 in studentedDocJson[i][subKKey]){//0, value: [object Object]
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
							      for(var subKKey5 in studentedDocJson[i][subKKey][subKKey2][subKKey3][subKKey4]){
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
						   		}
						   	 }
						   	 studentStringCreate += '</div></fieldset></div></div><div><hr style="border-style: none;height: 0;margin:10px;"/></div>';     //branch level
						}
					}
						studentStringCreate += '</div></fieldset></div></div><div><hr style="border-style: none;height: 0;margin:15px;"/></div>';     //school level
				}
				 mainJSONOBJ += studentStringCreate;
 			}//main for loop
		 		studentGridId.html(mainJSONOBJ);
		 		
		for(var k = 0; k < studentedDocJson.length; k++) {//school Obj's
			var schoolWise 	=  studentedDocJson[k];
			var oneD 				= 10;
			var OneMainD 		= '';

		     for(var subKey in studentedDocJson[k]){
		     
			        for(var subKey2 in studentedDocJson[k][subKey]){
			        
				         for(var subKey3 in studentedDocJson[k][subKey][subKey2]){
				         
				         	for(var subKey4 in studentedDocJson[k][subKey][subKey2][subKey3]){
				         	
							      for(var subKey5 in studentedDocJson[k][subKey][subKey2][subKey3][subKey4]){
							      
									for(var subKey6 in studentedDocJson[k][subKey][subKey2][subKey3][subKey4][subKey5]){
									
										for(var subKey7 in studentedDocJson[k][subKey][subKey2][subKey3][subKey4][subKey5][subKey6]){
								
								 	 		  totaljson[indexforTotalJ]=studentedDocJson[k][subKey][subKey2][subKey3][subKey4][subKey5][subKey6][subKey7];
												if(totaljson[indexforTotalJ].length>0){
												 	 	OneMainD = oneD+""+k;
									 					oneD++;
														NewScrubber[indexforTotalJ]		= new jQuery.DataScrubber(totaljson[indexforTotalJ], PageCount, OpenItemCount);
														renderPagination(NewScrubber[indexforTotalJ].init(),OneMainD,indexforTotalJ);			
														indexforTotalJ++;			
														}
														
								  }
								}
							  }
							}		       
						}////key4: AKI Compass, value: [object Object],[object Object]---END						       
					}////key3: 0, value: [object Object]					        
				 }//key2: ABODE Kids International, value: [object Object]--END
		      }//for loop
		     
			}else{
			 studentGridId.html('<table class="table table-bordered" width="100%" style="margin-bottom:0px;"><tr><td style="color:red;font-size:16px;font-weight:bold;text-align: center;background-color:silver ">No records found.</td></tr></table>');
			}//if
 		}//main else
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
				}else if (userTypeGlobal == "PA") {
				
				stringForm += '<th width="14%" style="text-align:center;">Name</th><th width="11%" style="text-align:center;">Student Name</th><th width="11%">Admission Number</th>';
				stringForm += '<th width="14%" style="text-align:center;">Parent Type</th><th width="11%" style="text-align:center;">City</th>';
				stringForm += '<th width="11%" style="text-align:center;">Phone Number</th><th width="11%" style="text-align:center;">Email ID</th>';
				stringForm += '<th width="11%" style="text-align:center;">Default Contact</th>';
				}else if (userTypeGlobal == "TC") {
				stringForm += '<th width="14%" style="text-align:center;">Name</th><th width="11%" style="text-align:center;">Student Name</th><th width="11%">Admission Number</th>';
				stringForm += '<th width="14%" style="text-align:center;">Parent Type</th><th width="11%" style="text-align:center;">City</th>';
				stringForm += '<th width="11%" style="text-align:center;">Phone Number</th><th width="11%" style="text-align:center;">Email ID</th>';
				stringForm += '<th width="11%" style="text-align:center;">Default Contact</th>';
				}
				stringForm += '<th width="8%" style="text-align:center;">View Photo</th>';
				stringForm += '<th width="5%" style="text-align:center;">Edit</th><th width="5%" style="text-align:center;">'+activeString+'</th> </tr>';
				
			
			
			if(pickObj.length > 0){
               for(var subKey4 in pickObj){
				  var studentID 			= subKey4+"studentID"+idff;
					stringForm += '<tr>';
					  if (userTypeGlobal == "ST") {					                              	
			        stringForm += '<td  width="14%" style="text-align:center;">'+pickObj[subKey4].firstName+" "+pickObj[subKey4].lastName+'</td><td  width="13%" style="text-align:center;">'+pickObj[subKey4].dob+'</td>';
			        stringForm += ' <td  width="11%" style="text-align:center;">'+pickObj[subKey4].gender+' </td> <td  width="11%" style="text-align:center;">'+pickObj[subKey4].addr1+' </td>';					                             	
			        stringForm += '<td  width="20%" style="text-align:center;">'+pickObj[subKey4].addr2+'</td><td width="9%"  style="text-align:center;">'+pickObj[subKey4].city+'</td>';
			        stringForm += '<td  width="5%" style="text-align:center;">'+pickObj[subKey4].admissionNumber+'</td><td  width="10%"  style="text-align:center;">'+formatDate(pickObj[subKey4].admissionDate,"d M Y")+'</td>';
			        stringForm += '<td  width="20%" style="text-align:center;">'+pickObj[subKey4].className+'</td>';
			         stringForm += '<td width="9%"  style="text-align:center;">'+(pickObj[subKey4].active=="Y"?"Yes":"No")+'</td>';
            		//stringForm += '<td  width="8%" style="text-align:center;"><a href="downloadImage.action?type='+userTypeGlobal+'&fileName='+pickObj[subKey4].photo+'&admNumber='+pickObj[subKey4].admissionNumber+'" id="'+pickObj[subKey4].smID+'" ><i class="icon-eye-open"></i> </a></td>	<td  width="5%" style="text-align:center;"><a href="javascript:void(0)" id="'+studentID+'"  value="'+pickObj[subKey4].stuID+'" onclick="editUserCall(id,\''+pickObj[subKey4].stuID+'\');"><i class="icon-pencil"></i></a> </td>		<td  width="5%"  style="text-align:center;"><a href="#"  onclick="dEnableUserCall(\''+pickObj[subKey4].stuID+'\');" ><i class="icon-trash"></i> </a></td></tr>';
            		stringForm += '<td  width="8%" style="text-align:center;"><img style="height: 35px; width: 35px;" src="downloadImage.action?type='+userTypeGlobal+'&fileName='+pickObj[subKey4].photo+'&admNumber='+pickObj[subKey4].email+'" id="'+pickObj[subKey4].smID+'" onclick="openImageViewer(\''+pickObj[subKey4].photo+'\',\''+pickObj[subKey4].admissionNumber+'\');"  /></td>	<td  width="5%" style="text-align:center;"><a href="javascript:void(0)" id="'+studentID+'"  value="'+pickObj[subKey4].stuID+'" onclick="editUserCall(id,\''+pickObj[subKey4].stuID+'\');"><i class="icon-pencil"></i></a> </td>		<td  width="5%"  style="text-align:center;"><a href="#"  onclick="dEnableUserCall(\''+pickObj[subKey4].stuID+'\');" ><i class="icon-trash"></i> </a></td></tr>';
			       
			       }else  if (userTypeGlobal == "PA") {
			        stringForm += '<td  width="14%" style="text-align:center;">'+pickObj[subKey4].firstName+" "+pickObj[subKey4].lastName+'</td><td  width="13%" style="text-align:center;">'+pickObj[subKey4].sfirstName+" "+pickObj[subKey4].slastName+'</td>';
			        stringForm += ' <td  width="11%" style="text-align:center;">'+pickObj[subKey4].admissionNumber+' </td> <td  width="11%" style="text-align:center;">'+pickObj[subKey4].parentType+' </td>';					                             	
			        stringForm += '<td  width="20%" style="text-align:center;">'+pickObj[subKey4].city+'</td><td width="9%"  style="text-align:center;">'+pickObj[subKey4].mobile+'</td>';
			        stringForm += '<td  width="5%" style="text-align:center;">'+pickObj[subKey4].email+'</td>';
			        stringForm += '<td width="9%"  style="text-align:center;">'+(pickObj[subKey4].isDefault=="Y"?"Yes":"No")+'</td>';
            		//stringForm += '<td  width="8%" style="text-align:center;"><a href="downloadImage.action?type='+userTypeGlobal+'&fileName='+pickObj[subKey4].photo+'&admNumber='+pickObj[subKey4].admissionNumber+'" id="'+pickObj[subKey4].smID+'" ><i class="icon-eye-open"></i> </a></td>	<td  width="5%" style="text-align:center;"><a href="javascript:void(0)"  value="'+pickObj[subKey4].pdID+'" onclick="editUserCall(id,\''+pickObj[subKey4].pdID+'\');"><i class="icon-pencil"></i></a> </td>		<td  width="5%"  style="text-align:center;"><a href="#" onclick="dEnableUserCall(\''+pickObj[subKey4].pdID+'\');" ><i class="icon-trash"></i> </a></td></tr>';
			        stringForm += '<td  width="8%" style="text-align:center;"><img style="height: 35px; width: 35px;" src="downloadImage.action?type='+userTypeGlobal+'&fileName='+pickObj[subKey4].photo+'&admNumber='+pickObj[subKey4].admissionNumber+'" id="'+pickObj[subKey4].smID+'" onclick="openImageViewer(\''+pickObj[subKey4].photo+'\',\''+pickObj[subKey4].admissionNumber+'\');"  /></td>	<td  width="5%" style="text-align:center;"><a href="javascript:void(0)" id="'+studentID+'"  value="'+pickObj[subKey4].stuID+'" onclick="editUserCall(id,\''+pickObj[subKey4].pdID+'\');"><i class="icon-pencil"></i></a> </td>		<td  width="5%"  style="text-align:center;"><a href="#"  onclick="dEnableUserCall(\''+pickObj[subKey4].pdID+'\');" ><i class="icon-trash"></i> </a></td></tr>';
			        }else  if (userTypeGlobal == "TC") {
			        stringForm += '<td  width="14%" style="text-align:center;">'+pickObj[subKey4].firstName+" "+pickObj[subKey4].lastName+'</td><td  width="13%" style="text-align:center;">'+pickObj[subKey4].sfirstName+" "+pickObj[subKey4].slastName+'</td>';
			        stringForm += ' <td  width="11%" style="text-align:center;">'+pickObj[subKey4].admissionNumber+' </td> <td  width="11%" style="text-align:center;">'+pickObj[subKey4].parentType+' </td>';					                             	
			        stringForm += '<td  width="20%" style="text-align:center;">'+pickObj[subKey4].city+'</td><td width="9%"  style="text-align:center;">'+pickObj[subKey4].mobile+'</td>';
			        stringForm += '<td  width="5%" style="text-align:center;">'+pickObj[subKey4].email+'</td>';
			       // stringForm += '<td width="9%"  style="text-align:center;">'+(pickObj[subKey4].isDefault=="Y"?"Yes":"No")+'</td>';
            		stringForm += '<td  width="8%" style="text-align:center;"><a href="downloadImage.action?type='+userTypeGlobal+'&fileName='+pickObj[subKey4].photo+'&admNumber='+pickObj[subKey4].admissionNumber+'" id="'+pickObj[subKey4].smID+'" ><i class="icon-eye-open"></i> </a></td>	<td  width="5%" style="text-align:center;"><a href="javascript:void(0)"  value="'+pickObj[subKey4].pdID+'" onclick="editUserCall(id,\''+pickObj[subKey4].SD_ID+'\');"><i class="icon-pencil"></i></a> </td>		<td  width="5%"  style="text-align:center;"><a href="#" onclick="dEnableUserCall(\''+pickObj[subKey4].SD_ID+'\');" ><i class="icon-trash"></i> </a></td></tr>';
			        }
/*             		stringForm += '<td  width="8%" style="text-align:center;"><a href="downloadImage.action?type=ST&fileName='+pickObj[subKey4].photo+'&admNumber='+pickObj[subKey4].admissionNumber+'" id="'+pickObj[subKey4].smID+'" ><i class="icon-eye-open"></i> </a></td>	<td  width="5%" style="text-align:center;"><a href="javascript:void(0)" id="'+studentID+'"  value="'+pickObj[subKey4].stuID+'" onclick="editUserCall(id,\''+pickObj[subKey4].stuID+'\');"><i class="icon-pencil"></i></a> </td>		<td  width="5%"  style="text-align:center;"><a href="#" id="'+studentID+'"  value="'+pickObj[subKey4].stuID+'" onclick="dEnableUserCall(id);" ><i class="icon-trash"></i> </a></td></tr>'; */
			  
			   }///DATA end
              	stringForm +='</table>';
		}else{
          		stringForm += '<table class="table table-bordered" width="100%" style="margin-bottom:0px;"><tr><td>No records found.</td></tr></table>';
         }//if
		 newCallsGrid.html(stringForm);
	}		
	
	//Staff*****************************************************************************************************************
	 function getStaffNoParentCheckDisp(data){
		var studentGridId  	 = jQuery('#studentDynamicGrid');
				studentGridId.html("");
		if (data == null) {
                         alert("error");
		} else {
			var studentedDocJson = jQuery.parseJSON(data);
			if(studentedDocJson != null && studentedDocJson.length > 0){
 			var mainJSONOBJ = '';
 			var globalIncr = 0;
		     for(var i = 0; i < studentedDocJson.length; i++) {//school Obj's
		                                   var studentID 			= i+"studentID";
	  
		      	var schoolWise = studentedDocJson[i];
				var one = 10;
				var OneMain ='';
				  
				  for(var subKKey in studentedDocJson[i]){//ABODE Kids International, value: [object Object]
				 		var studentStringCreate = '';
				 		var tempSchoolID = 'school'+subKKey+"-"+globalIncr;;
						var tempSchoolIDA = 'Aschool'+subKKey+"-"+globalIncr;;
							studentStringCreate += '<div style="   background-color: #FFFFFF; border: 1px solid #DDDDDD; border-radius: 6px 6px 6px 6px; color: #9DA0A4; font-size: 12px; font-weight: bold; left: -1px; margin: 0 auto; max-width: 98%; padding: 3px 3px;">';
							studentStringCreate += ' <div style="padding:10px 20px 20px 20px;"><fieldset>';
							studentStringCreate += '<div class="TogglerHaderALL" id=\''+tempSchoolIDA+'\' ><div  style="float:left;width:100%;" class="open"  >';
							studentStringCreate += ' <legend style="margin-bottom:15px">';
							studentStringCreate += '<a href="\''+tempSchoolIDA+'\'"  class="forChrome"  onclick="showTogglerBodyAll(\''+tempSchoolID+'\'); return false;" >School : '+subKKey+'</a></legend></div></div><div style="display: inline-block;padding-top: 6px;	float: left;width:100%;" id=\''+tempSchoolID+'\'>';

				   		for(var subKKey2 in studentedDocJson[i][subKKey]){//0, value: [object Object]
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
						studentStringCreate += '</div></fieldset></div></div><div><hr style="border-style: none;height: 0;margin:15px;"/></div>';     //school level
				}
				 mainJSONOBJ += studentStringCreate;
 			}//main for loop
		 		studentGridId.html(mainJSONOBJ);
		 		
		for(var k = 0; k < studentedDocJson.length; k++) {//school Obj's
			var schoolWise 	=  studentedDocJson[k];
			var oneD 				= 10;
			var OneMainD 		= '';

		     for(var subKey in studentedDocJson[k]){
		     
			        for(var subKey2 in studentedDocJson[k][subKey]){
			        
				         for(var subKey3 in studentedDocJson[k][subKey][subKey2]){
				         
				      
								 	 		  totaljson[indexforTotalJ]=studentedDocJson[k][subKey][subKey2][subKey3];
												if(totaljson[indexforTotalJ].length>0){
												 	 	OneMainD = oneD+""+k;
									 					oneD++;
														NewScrubber[indexforTotalJ]		= new jQuery.DataScrubber(totaljson[indexforTotalJ], PageCount, OpenItemCount);
														renderPaginationISParentNO(NewScrubber[indexforTotalJ].init(),OneMainD,indexforTotalJ);			
														indexforTotalJ++;			
														}
														
							      
						}////key4: AKI Compass, value: [object Object],[object Object]---END						       
					}////key3: 0, value: [object Object]					        
				 }//key2: ABODE Kids International, value: [object Object]--END
		      }//for loop
		     
			}else{
			 studentGridId.html('<table class="table table-bordered" width="100%" style="margin-bottom:0px;"><tr><td style="color:red;font-size:16px;font-weight:bold;text-align: center;background-color:silver ">No records found.</td></tr></table>');
			}//if
 		}//main else
	}
	
	function renderPaginationISParentNO(cc,idf,indexforTotalJT){
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
				renderDataISParentNO(cc,idf);
	}
	
	function renderDataISParentNO(pickObj,idff){
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
				//Name	 designation	City	Phone Number	Email ID  
				 if (userTypeGlobal == "TC") {
				stringForm += '<th width="14%" style="text-align:center;">Name</th><th width="11%">Designation</th>';
				stringForm += '<th width="11%" style="text-align:center;">City</th>';
				stringForm += '<th width="11%" style="text-align:center;">Phone Number</th><th width="11%" style="text-align:center;">Email ID</th>';
				}
				stringForm += '<th width="8%" style="text-align:center;">View Photo</th>';
				stringForm += '<th width="5%" style="text-align:center;">Edit</th><th width="5%" style="text-align:center;">'+activeString+'</th> </tr>';
				
			
			
			if(pickObj.length > 0){
               for(var subKey4 in pickObj){
				  var studentID 			= subKey4+"studentID"+idff;
					stringForm += '<tr>';
		 if (userTypeGlobal == "TC") {
		 //Name	 designation	City	Phone Number	Email ID  
			        stringForm += '<td  width="14%" style="text-align:center;">'+pickObj[subKey4].firstName+" "+pickObj[subKey4].lastName+'</td>';
			        stringForm += ' <td  width="11%" style="text-align:center;">'+pickObj[subKey4].designation+' </td> ';					                             	
			        stringForm += '<td  width="20%" style="text-align:center;">'+pickObj[subKey4].city+'</td><td width="9%"  style="text-align:center;">'+pickObj[subKey4].mobile+'</td>';
			        stringForm += '<td  width="5%" style="text-align:center;">'+pickObj[subKey4].email+'</td>';
            		//2013-08-31stringForm += '<td  width="8%" style="text-align:center;"><a href="downloadImage.action?type='+userTypeGlobal+'&fileName='+pickObj[subKey4].photo+'&admNumber='+pickObj[subKey4].email+'" id="'+pickObj[subKey4].smID+'" ><i class="icon-eye-open"></i> </a></td>	<td  width="5%" style="text-align:center;"><a href="javascript:void(0)"  value="'+pickObj[subKey4].SD_ID+'" onclick="editUserCall(id,\''+pickObj[subKey4].SD_ID+'\');"><i class="icon-pencil"></i></a> </td>		<td  width="5%"  style="text-align:center;"><a href="#" onclick="dEnableUserCall(\''+pickObj[subKey4].SD_ID+'\');" ><i class="icon-trash"></i> </a></td></tr>';
            		stringForm += '<td  width="8%" style="text-align:center;"><img style="height:35px; width: 35px;" src="downloadImage.action?type='+userTypeGlobal+'&fileName='+pickObj[subKey4].photo+'&admNumber='+pickObj[subKey4].email+'" id="'+pickObj[subKey4].photo+'" onclick="openImageViewer(\''+pickObj[subKey4].photo+'\',\''+pickObj[subKey4].email+'\');"  /></td>	<td  width="5%" style="text-align:center;"><a href="javascript:void(0)"  value="'+pickObj[subKey4].SD_ID+'" onclick="editUserCall(id,\''+pickObj[subKey4].SD_ID+'\');"><i class="icon-pencil"></i></a> </td>		<td  width="5%"  style="text-align:center;"><a href="#" onclick="dEnableUserCall(\''+pickObj[subKey4].SD_ID+'\');" ><i class="icon-trash"></i> </a></td></tr>';
			       
			        }
			  
			   }///DATA end
              	stringForm +='</table>';
		}else{
          		stringForm += '<table class="table table-bordered" width="100%" style="margin-bottom:0px;"><tr><td>No records found.</td></tr></table>';
         }//if
		 newCallsGrid.html(stringForm);
	}		
		 	
	
	//@@@@@ start
	function openImageViewer(valFromViewr,email){//present only staff
//	var idFOrShow = 'downloadImage.action?type='+userTypeGlobal+'&fileName='+pickObj[subKey4].photo+'&admNumber='+pickObj[subKey4].email+
	
	var idFOrShow = 'downloadImage.action?type='+userTypeGlobal+'&fileName='+valFromViewr+'&admNumber='+email;//email temp
	
	$("#imgIDViwer").attr("src",idFOrShow);
	$("#anchorIDViwer").attr("href",idFOrShow);
	
	//$("a").attr("href", "http://www.google.com/")//$("#link1").text()
		//anchorIDViwer
	//imgIDViwer
	
		$('#demoLightbox').lightbox('show');
	
	}
	
	//@@@@@ end
	//*************************************************************************	 	      
	 function downloadUserCSV(){
	 	 if (userTypeGlobal == "ST") {
		      document.studentSearch.action = 'downloadStudentSearch.action';
		} else if (userTypeGlobal == "PA") {
		      document.studentSearch.action = 'downloadParentSearch.action';
		} 		      
	      document.studentSearch.method = 'post';
	   	  document.studentSearch.submit(); 
		 
	}
		 
		 	var win2;
	function editUserCall(val3,idST){//two ways...2nd paramter ...major
		var valTOID = "#"+val3;
		var stIDForID 			= $(valTOID).attr('value');
		if (userTypeGlobal == "ST") {
	  	win2 =	window.open("Access.action?p1=AddNonAdminUser&p2=uts&p3=forEdit&p4="+idST, "Schooltrix", "height=580,width=870,scrollbars=1,location=no,menubar=no,resizable=0,status=no,toolbar=no,screenX=100,screenY=100");
		 }else  if (userTypeGlobal == "PA") {
	  	win2 =	window.open("Access.action?p1=AddNonAdminUser&p2=rap&p3=forEdit&p4="+idST, "Schooltrix", "height=580,width=870,scrollbars=1,location=no,menubar=no,resizable=0,status=no,toolbar=no,screenX=100,screenY=100");
		 }else  if (userTypeGlobal == "TC") {
	  	win2 =	window.open("Access.action?p1=AddNonAdminUser&p2=ffats&p3=forEdit&p4="+idST, "Schooltrix", "height=580,width=870,scrollbars=1,location=no,menubar=no,resizable=0,status=no,toolbar=no,screenX=100,screenY=100");
		 }
		win2.onbeforeunload = gridRefreshAfterUserEditOpen;		
	}
	
	function gridRefreshAfterUserEditOpen() {
		setTimeout(delayedGridRefreshAfterUserEditOpen,2000);
	}
	function delayedGridRefreshAfterUserEditOpen(){
		jQuery('#studentDynamicGrid').html("");
		getUsersList();//refresh if any changes in edit--grid will update
	}
	 
	var userIDV = '';
	function dEnableUserCall(val2){
		//var valTOID 	= "#"+val2;		 userIDV 			= $(valTOID).attr('value');
		 userIDV = val2;
		if(userTypeGlobal == "ST"){
			  if ($('#active').is(":checked")){
			confirmDialog ('<div style="font-size:18px">Are you sure, you want to disable this student?\n (Parent access will also get disabled)</div>', alert);
			  } else{
			confirmDialog ('<div style="font-size:18px"><p>Are you sure, you want to enable this student?\n (Parent access will also get enabled)</div>', alert);
			 }
		 }else if(userTypeGlobal == "PA"){
		 		  if ($('#active').is(":checked")){
		 	alertDialog('<div style="font-size:18px">Parents cannot be disable directly.\n Disable student to disable parents automatically.</div>');
			  } else{
		 	alertDialog('<div style="font-size:18px">Parents cannot be enable directly.\n Enable student to enable parents automatically.</div>');
			 }
		 }else if(userTypeGlobal == "TC"){
		 	 if ($('#active').is(":checked")){
		 		confirmDialog ('<div style="font-size:18px">Are you sure, you want to disable this staff?\n</div>', alert);
			  } else{
				confirmDialog ('<div style="font-size:18px"><p>Are you sure, you want to enable this staff?\n</div>', alert);
			 }
		 }
		 
	}
	

	function disableUserAfterConfirm(){
			var active ="";
			 if ($('#active').is(":checked")){
							active='N';
			} else{
			 	active='Y';
		    }//Disable
					    
			if(userTypeGlobal == "ST"){		    
				StudentDWR.disableEnableStudentMaster(userIDV,active,function(data){
																	if (data == null) {
																		setError("schoolNames","Error");
																		 jQuery("#schoolNames").focus();
																	} else {
																		//for refreshment of result--test
																		setError("schoolNames","Success");
																		 jQuery("#schoolNames").focus();
																		 getUsersList();
																	}		
			
			});	
		}else if(userTypeGlobal == "TC"){		    
				StaffDWR.disableEnableStaffMaster(userIDV,active,function(data){
																	if (data == null) {
																		setError("schoolNames","Error");
																		 jQuery("#schoolNames").focus();
																	} else {
																		//for refreshment of result--test
																		setError("schoolNames","Success");
																		 jQuery("#schoolNames").focus();
																		 getUsersList();
																	}		
			
			});	
		}
				
	} 
	
	
	 function showTogglerBody(){//pass id ..if u want more ....
	        	    jQuery("#toggleeee").toggle('slow');
	        	    jQuery(jQuery("#SHIcon").children()[0]).toggleClass("open close"); 
      }
      
       function showTogglerBodyAll(togID){//pass id ..if u want more ....
/*        function showTogglerBodyAll(fromWhere,togID){//pass id ..if u want more .... */
		       var toggleiD = "#"+togID;
		       var toggleiDA = "";
		     	  toggleiDA  = "#A"+togID;				
	       	    jQuery(toggleiD).toggle('slow');
 	       	    jQuery(jQuery(toggleiDA).children()[0]).toggleClass("open close");  
      } 
	    
	function editSSP(){
	   if (userTypeGlobal == "ST") {
			 document.location = "Access.action?p1=AddNonAdminUser&p2=uts";       
		}else if(userTypeGlobal == "PA") {
			 document.location = "Access.action?p1=AddNonAdminUser&p2=rap";
		} else if(userTypeGlobal == "TC") {
			 document.location = "Access.action?p1=AddNonAdminUser&p2=ffats";
		} 
	}	
	    
	function resetFormHelp(){
		resetForm();
		jQuery('#searchHistory').val('-1').attr('selected',true);
		schoolMasterList();
	  }
	  
	   function resetForm() {
	       	removeAllOptions("#schoolNames");
			removeAllOptions("#branchNames");
			removeAllOptions("#classNames");
			removeAllOptions("#classAdmittedIn");
			removeAllOptions("#sectionNames");

	       jQuery("#schoolNames").val('Select School');
	       jQuery("#branchNames").val('Select Branch');
	       jQuery("#classNames").val('Select Class');
	       jQuery("#sectionNames").val('Select Section');
	       jQuery("#classAdmittedIn").val('Select Class');
	    
	       jQuery("#fname").val('');
	       jQuery("#lname").val('');
	       jQuery("#dob").val('');
	       jQuery("#email").val('');
	       jQuery("#mobile").val('');
	       jQuery("#landline").val('');
	       jQuery("#addr1").val('');
	       jQuery("#addr2").val('');
	       jQuery("#city").val('');

	       jQuery("#designation").val('');
	       
	       jQuery("#admissionNumber").val('');
	       jQuery("#admissionDateFrom").val('');
	       jQuery("#admissionDateTO").val('');
          jQuery('#active').prop('checked', true);
	       
	       jQuery("#state").val('All');
	       jQuery("#gender").val('All');
	       
	       jQuery("#searchDesc").val('');
	       
	        jQuery('#studentDynamicGrid').html("");		 
	}

	function saveUserSearchHis(){
		var searchDesc = jQuery("#searchDesc").val();	
		var data100 = jQuery("#data100").val();
		
		if (data100 == "undefined" || data100 == undefined ) {
			setError("searchID","Search data before you save");
			jQuery("#searchID").focus();
			return false;
		}

		if (searchDesc !=null &&searchDesc != '' && searchDesc.length>2) {	
		
					StudentDWR.saveUserSearch(searchQueryForHis,searchDesc, searchJSONForHis,userTypeGlobal,function(data){
					//	alert(data);
									if (data == "nosearch") {
										jQuery('#studentDynamicGrid').html("");
										setError("searchID","Search data before you save");
										 jQuery("#searchID").focus();
										  //$(window).scrollTop($('#errorspan').offset().top);
										 														
									}else if (data == "descthere") {
										jQuery('#studentDynamicGrid').html("");
										setError("searchID","Search description already there");
										 jQuery("#searchID").focus();																
									}else	if (data == "success") {
										jQuery('#studentDynamicGrid').html("");
										setError("searchID"," Successfully saved.");
										 jQuery("#searchID").focus();
										 
										 searchHistoryLoad();//***** After Saving ...it will reflect on Search history selection box..
									} else if (data == "already") {
										jQuery('#studentDynamicGrid').html("");
											setError("searchID","Search is already there");
											 jQuery("#searchID").focus();
									} else {
										jQuery('#studentDynamicGrid').html("");
											setError("searchID","Not Saved");
											 jQuery("#searchID").focus();
									}			
				});			
		//	}
		}else{
				setError("searchDesc","Please enter a name to save the search");
				jQuery("#searchDesc").focus();
				return false;
		}		
	}
	
	
	function deleteSearchHistory(){
		var searchHisID = jQuery("#searchHistory").val();
		if (searchHisID != -1) {
		confirmDialogSearch('Are you sure you want to permanently delete this search?', alert);
		}else{
		setError("searchHistory","Please select any search history option");
		 jQuery("#searchHistory").focus();
		 return false;
		}
	}
	
	function deleteSearchHistoryAfterConfirm(){
		var searchHisID = jQuery("#searchHistory").val();
		if (searchHisID != -1) {
			StudentDWR.deleteSearchHistoryDoc(searchHisID,userTypeGlobal,function(data){
					if (data == null) {
						setError("searchHistory","Error During delete..");
						 jQuery("#searchHistory").focus();
					} else {
						//for refreshment of result--test
							setError("searchHistory","Successfully Deleted");
							 jQuery("#searchHistory").focus();
  						jQuery('#studentDynamicGrid').html("");
							resetFormHelp();
							searchHistoryLoad();
					}		
			});			
			
		}else{
			setError("searchHistory", "Please select any search history option");
			return false;
		}
	
	}
	
	function searchHistoryLoad(){
	var searchHisID =	 document.getElementById('searchHistory');	

	 	removeAllOptionsOne("searchHistory");
		StudentDWR.searchHistoryLoadCall(userTypeGlobal,function(data){
									if (data != null) {
										var searchHistorydata = jQuery.parseJSON(data);
										searchHisOnloadJson = searchHistorydata;
									for ( var int = 0; int < searchHistorydata.length; int++) {
											var array_element = searchHistorydata[int];
												    	    var opt = document.createElement("option");
														    opt.innerHTML = array_element.descS;
														    opt.value =  array_element.id;
														    searchHisID.appendChild(opt);
													  }
									}else{
								//	alert("no data");
									}
				}); 
	}
	
	 function removeAllOptionsOne(whichOption){
		var listofObjects =  document.getElementById(whichOption);
		var i;
			for(i=listofObjects.options.length-1;i>=0;i--)
			{
			listofObjects.remove(i);
			}
		if (whichOption == "searchHistory") {
			 var opt = document.createElement("option");
		    opt.value = "-1";
		    opt.innerHTML = "Select";
		    listofObjects.appendChild(opt); 
		    }		
	}
	
	var searchJSON = '';
	var descForSearchDisp = '';
	
	function displaySearchHistoryLoadByID(){
			var searchHisID =	 jQuery('#searchHistory').val();	
			var queryForSearch = '';
			var jsonForSearchDisp = '';

			for ( var int = 0; int < searchHisOnloadJson.length; int++) {
				var array_element = searchHisOnloadJson[int].id;
				if (array_element == searchHisID) {
					queryForSearch = searchHisOnloadJson[int].queryString;
					jsonForSearchDisp = searchHisOnloadJson[int].jsonString;
					descForSearchDisp = searchHisOnloadJson[int].descS;
				}
			}
			
			if (searchHisID !=-1 && searchHisID != '-1' ) {
				resetForm();
				schoolMasterList();
				searchJSON = jQuery.parseJSON(jsonForSearchDisp);
			
				setTimeout(renderDataForm,300);
				
	 			StudentDWR.searchHistoryLoadByIDDisplay(queryForSearch ,userTypeGlobal, function(data){
					if (data == null) {
						alert('error in displaySearchHistoryLoadByID ');
					} else {
						//var  isParentCheck								= jQuery("#isParent").prop('checked') ? 'Y':'N';
						var  isParentCheck								= 'N';
							if (isParentCheck =='N') {
								getStaffNoParentCheckDisp(data);	
							}else{
								getStudentListDisp(data);
							}
					
					}
				});
		}else{
		resetForm();
		schoolMasterList();
		}
	}
	
	function  renderDataForm() {
	
		var schollSplit = searchJSON.sm_ids.split(',');
		
		var timeFor = 200;
		for ( var int = 0; int < schollSplit.length; int++) {
			var schoolListArray_element = schollSplit[int];
			if (schoolListArray_element.indexOf('%') != -1) {
			}else{
			 jQuery("#schoolNames").multiselect('select', schoolListArray_element);
			 }
		}
		  var schoolID = jQuery("#schoolNames").val();
		  
		  if(schoolID != null && schoolID != ""){
			setTimeout(selectSchoolforBranches,timeFor);	
			
			timeFor +=200;
			setTimeout(selectBrachesFucn,timeFor);	
			timeFor +=200;	
			if (userTypeGlobal != "TC") {
			setTimeout(internalBranchDisp,timeFor);
			}	
		 }
		 
		  var branchID = jQuery("#branchNames").val();
		   if(schoolID != null && schoolID != ""&& branchID != null && branchID != ""){
			setTimeout(selectSchoolforBranches,timeFor);	
			
			timeFor +=200;
			setTimeout(selectBrachesFucn,timeFor);	
			timeFor +=200;	
			setTimeout(internalBranchDisp,timeFor);	
		 }
		 
		 timeFor +=400;	
		 if (userTypeGlobal == "ST") {
			setTimeout(remainingSelectStudent,timeFor);
		} else if (userTypeGlobal == "PA") {
			setTimeout(remainingSelectParent,timeFor);
		} else if (userTypeGlobal == "TC") {
			setTimeout(remainingSelectStaff,timeFor);
		}			
		
	}
	
	function internalBranchDisp(){
			  var branchNames = jQuery("#branchNames").val();
		  		if(branchNames != null && branchNames != ""){
					setTimeout(selectBranchesforClasses,50);		
					setTimeout(selectClassFucn,300); 
					setTimeout(selectAdmittedClassFucn,350); 
					setTimeout(selectSectionForClasses,350);		
					setTimeout(selectSectionFucn,1500); 
					
		 		}	
	}
	
	function remainingSelectStudent(){
		 /**
	"uploadType" : "AcademicMaterial",	"fileType" : "txt",			"assignmentType" : "0",		"selectSubject" : "1",		"cmIDs" : "0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19",
	"dateQStringFROM" : "",		"smIDs" : "0,3,4,5,6,7,8",			"bmIDs" : "0,3,4,5,6",			"dateQStringTO" : "",		"userIDs" : "'0','Parents','Students'"	
	*/
		 	
		   jQuery("#fname").val(searchJSON.fname);
	       jQuery("#lname").val(searchJSON.lname);
	       jQuery("#dob").val(searchJSON.dob);
	       jQuery("#email").val(searchJSON.email);
	       jQuery("#mobile").val(searchJSON.mobile);
	       jQuery("#landline").val(searchJSON.landline);
	       jQuery("#addr1").val(searchJSON.addr1);
	       jQuery("#addr2").val(searchJSON.addr2);
	       jQuery("#city").val(searchJSON.city);
	       
	       jQuery("#admissionNumber").val(searchJSON.admissionNumber);
	       jQuery("#admissionDateFrom").val(searchJSON.fromdate);
	       jQuery("#admissionDateTO").val(searchJSON.todate);
	       
	       if(searchJSON.active == "y" || searchJSON.active == "Y"){
          jQuery('#active').prop('checked', true);
	       }else{
          jQuery('#active').prop('checked', false);
	       }
	       
	       jQuery("#state").val(searchJSON.state);
	       jQuery("#gender").val(searchJSON.gender);
	       
	       jQuery("#searchDesc").val(descForSearchDisp);
		 	
		 	
		 
	}
	
	function selectUserType(){
		var userType = searchJSON.userIDs.split(',');
		for ( var int = 0; int < userType.length; int++) {
			var userTypeArra = userType[int].replace(/\'/g,"");
			 jQuery("#selectType").multiselect('select', userTypeArra);
		}
	}

	
	function selectClassFucn(){
		var classListArray = searchJSON.cm_ids.split(',');
			for ( var int = 0; int < classListArray.length; int++) {
			var classListArray_element = classListArray[int].replace(/\'/g,"");
				if (classListArray_element.indexOf('%') != -1) {
				}else{
				 	jQuery("#classNames").multiselect('select', classListArray_element);
				 }
		}
	}
	function selectAdmittedClassFucn(){
		var classListADMArray = searchJSON.cam_ids.split(',');
			for ( var int = 0; int < classListADMArray.length; int++) {
			var classListADMArray_element = classListADMArray[int].replace(/\'/g,"");
				if (classListADMArray_element.indexOf('%') != -1) {
				}else{
			 jQuery("#classAdmittedIn").multiselect('select', classListADMArray_element);
				 }
		}
	}

	
	function selectBrachesFucn(){
		var branchListArray = searchJSON.bm_ids.split(',');
			for ( var int = 0; int < branchListArray.length; int++) {
			var branchListArray_element = branchListArray[int].replace(/\'/g,"");
				if (branchListArray_element.indexOf('%') != -1) {
				}else{
						 jQuery("#branchNames").multiselect('select', branchListArray_element);
			   }
		}
	}
	
		
	function selectSectionFucn(){
		var sectionListArray = searchJSON.sec_ids.split(',');
			for ( var int = 0; int < sectionListArray.length; int++) {
				var sectionListArray_element = sectionListArray[int].replace(/\'/g,"");
				if (sectionListArray_element.indexOf('%') != -1) {
				}else{
					 jQuery("#sectionNames").multiselect('select',sectionListArray_element);
			   }
		}
	}
	
	
	function remainingSelectParent(){
		   jQuery("#fname").val(searchJSON.fname);
	       jQuery("#lname").val(searchJSON.lname);
	       jQuery("#dob").val(searchJSON.dob);
	       jQuery("#email").val(searchJSON.email);
	       jQuery("#mobile").val(searchJSON.mobile);
	       jQuery("#landline").val(searchJSON.landline);
	       jQuery("#addr1").val(searchJSON.addr1);
	       jQuery("#addr2").val(searchJSON.addr2);
	       jQuery("#city").val(searchJSON.city);
	       //change selected way
	       jQuery("#state").val(searchJSON.state).attr('selected',true);
	       jQuery("#gender").val(searchJSON.gender).attr('selected',true);
	       
	       jQuery("#admissionNumber").val(searchJSON.admissionNumber);
	       jQuery("#admissionDateFrom").val(searchJSON.fromdate);
	       jQuery("#admissionDateTO").val(searchJSON.todate);
	       
	       if(searchJSON.active == "y" || searchJSON.active == "Y"){
          jQuery('#active').prop('checked', true);
	       }else{
          jQuery('#active').prop('checked', false);
	       }
	       
	       jQuery("#parentTypeSel").val(searchJSON.parentTypeSel).attr('selected',true);
	   
	       if(searchJSON.isDefault == "y" || searchJSON.isDefault == "Y"){
        	 jQuery('#isDefault').prop('checked', true);
	       }else{
        	  jQuery('#isDefault').prop('checked', false);
	       }
	       
	       jQuery("#searchDesc").val(descForSearchDisp);
	}
	
	function remainingSelectStaff(){
		   jQuery("#fname").val(searchJSON.fname);
	       jQuery("#lname").val(searchJSON.lname);
	       jQuery("#dob").val(searchJSON.dob);
	       jQuery("#email").val(searchJSON.email);
	       jQuery("#mobile").val(searchJSON.mobile);
	       jQuery("#landline").val(searchJSON.landline);
	       jQuery("#addr1").val(searchJSON.addr1);
	       jQuery("#addr2").val(searchJSON.addr2);
	       jQuery("#city").val(searchJSON.city);
	       
	       jQuery("#designation").val(searchJSON.desig);
	       //change selected way
	       jQuery("#state").val(searchJSON.state).attr('selected',true);
	       jQuery("#gender").val(searchJSON.gender).attr('selected',true);
	       
	      
	       
	       if(searchJSON.active == "y" || searchJSON.active == "Y"){
          jQuery('#active').prop('checked', true);
	       }else{
          jQuery('#active').prop('checked', false);
	       }
	       
	
	       
	       jQuery("#searchDesc").val(descForSearchDisp);
	}
	
	 function isParentCheck() {//no need
      	
			if(document.getElementById("isParent").checked == true) {
				document.getElementById("parentTypeSel").disabled=false;		
				document.getElementById("parentTypeSel").style.display="block";
				
				jQuery('#admissionOne').show();
				jQuery('#admissionTwo').show();
				jQuery('#isDefaultLable').show();
				jQuery('#isDefaultCheck').show();		
				jQuery('#classAdmittedInSelect').hide();		
				jQuery('#classAdmittedInLabel').hide();		
				
			}else { 
				document.getElementById("parentTypeSel").style.display="block";						
				document.getElementById("parentTypeSel").disabled=true;	
				jQuery('#isDefaultLable').hide();
				jQuery('#isDefaultCheck').hide();		
					jQuery('#admissionOne').hide();
				jQuery('#admissionTwo').hide();
			}
		} 	
	    
	    </script>
<div style="height:20px;text-align: center;"><span id ="errorspan" style="color:red;font-size: 14px;font-weight: bold;" >&nbsp;</span></div>
<div class="reg_mainCon"><!-- addnonadminuser.action -->
 <form  name="studentSearch"  enctype="multipart/form-data" > 
    <fieldset>
    <legend><img src="img/list_add_user.PNG" class="img-circle">&nbsp;&nbsp;<span id ="tileDisp">&nbsp;</span>
      <div id="SHIcon" class="TogglerHader" style="display: inline-block;padding-top: 6px;	float: right;"><div class="open" onclick="showTogglerBody(); return false;">
      <a href="#SHIcon">Show | Hide</a></div></div>
    </legend>
    
  <div id="toggleeee" style="padding:20px;padding-top: 2px;" >
      <table width="100%" border="0" cellspacing="0" cellpadding="0">
	       <tr>
   				<td><label style="color: #000;">Saved Search&nbsp;&nbsp;&nbsp;</label></td>
   				<td><select class="span3" style="width: 225px" name="searchHistory" id="searchHistory" onchange="displaySearchHistoryLoadByID();">
   							<option value="-1">Select</option>
   						</select>
   						 <a class="btn btn-warning" style="margin-bottom: 10px;text-align: center;height: 18px;width: 4%" href="#" onclick="deleteSearchHistory();return false;"><i class=" icon-trash"></i></a>
   			   </td>
   			   <td>&nbsp;</td>	 <td>&nbsp;</td>	
   			   <td colspan="2" style="float: right;height: 20px;">
	      	<input type="button" class="btn btn-warning"  style="height: 30px;width: 150px;" id='hidePurposeEdit'  onclick="editSSP()"></td>
  	 	 </tr>
  	 	 <tr><td>&nbsp;</td></tr>
	     <tr>
	           <td><label style="color:#000;"><b>School </b></label></td>
		        <td>
		         	<div align="left">
	         		<div class="input-append">  
			  			<select class="multiselect" multiple="multiple"  id="schoolNames" name="schoolNames">			
							<option value="0" >Select All </option>
			  			</select>
						<button class="btn " type="button" style="width:30px;padding: 4px; "  id="schoolNamesGo" onclick="return selectSchoolforBranches();"  name="schoolNamesGo" >Go!</button> <!--onblur="return selectSchoolforBranches();" onclick="return selectSchoolforBranches();"  -->
			 		</div></div>           
			 	</td>     
		       	<td >&nbsp;</td><td><label style="color:#000;"><b>Branch </b></label></td>
		        <td>
		        	<div align="left">
	         		<div class="input-append"  id='branchIDDisp'>  
			   			<select class="multiselect" multiple="multiple"  id="branchNames" name="branchNames">		
				 			 <option value="0" >Select All </option>	
						</select>
						<button class="btn " type="button" style="width:30px;padding: 4px; "  id="branchNamesGo" name="branchNamesGo"  onclick="selectBranchesforClasses();">Go!</button>
			 		</div></div>
			</td>
	    </tr>    
	    <tr id="classSectionIDDisp">
	        <td><label style="color:#000;"><b>Class </b></label></td>
	        <td>
	          <div class="input-append">  
	           	<select class="multiselect" multiple="multiple"  id="classNames" name="classNames">		
					<option value="0" >Select All </option>	
			  	</select>
				<button class="btn " type="button" style="width:30px;padding: 4px; "  id="classNamesGo" name="classNamesGo"  onclick=" selectSectionForClasses();"  >Go!</button>  </div>				          
	        </td>
	        <td>&nbsp;</td>
	        <td><label style="color:#000;"><b>Section </b></label></td>
	        <td>    
	           <select class="multiselect" multiple="multiple"  id="sectionNames" name="sectionNames">		
					<option value="0" >Select All </option>	
			   </select>
			</td>       	
	    </tr>
	    <tr> <td >&nbsp;</td></tr>
		<tr>
         	<td ><label style="color:#000;"><b>First Name</b></label></td>
        	<td ><div align="left"><input style=" width: 256px;" class="span3" type="text" placeholder="" name="fname"  id="fname"></div></td>
        	<td >&nbsp;</td>
        	<td ><label style="color:#000;"><b>Last Name</b></label></td>
         	<td ><input style="width: 256px;" class="span3" type="text" placeholder="" name="lname"  id="lname"> </td>
       </tr>  
       <tr>
			<td ><label style="color:#000;"><b>Date of Birth</b></label></td>
         	<td ><div align="left">
	            <div id="datetimepicker"    class="input-append date"   >
				      <input style="width:85%;" class="span3" type="text" data-format="yyyy-MM-dd"  name="dob"  id="dob"></input>
				      <span class="add-on">
				        <i data-time-icon="icon-time" data-date-icon="icon-calendar"></i>
				      </span>
			    </div></div>
			 </td>
           	 <td >&nbsp;</td>
           	 <td ><label style="color:#000;"><b>Email</b></label></td>
        	 <td ><input style="width: 256px;" class="span3" type="text" placeholder="" name="email"  id="email"></td>          
        </tr>        
        <tr>
     	 	<td ><label style="color:#000;"><b>Mobile</b></label></td>
          	<td ><div align="left"><input style="width: 256px;" class="span3" type="text" placeholder="" name="mobile"  id="mobile">  </div></td>
    	    <td >&nbsp;</td>
    	    <td ><label style="color:#000;"><b>Landline</b></label></td>
          	<td ><input style="width: 256px;" class="span3" type="text" placeholder="" name="landline"  id="landline"></td>
        </tr>
        <tr>
	        <td ><label style="color:#000;"><b>Address 1</b></label></td>
	        <td ><div align="left">
				<input style="width: 256px;" class="span3" type="text" placeholder="" name="addr1"  id="addr1">	</div>
			</td>
          	<td >&nbsp;</td>
          	<td	 ><label style="color:#000;"><b>Address 2</b></label></td>
         	<td ><input style="width: 256px;" class="span3" type="text" placeholder="" name="addr2"  id="addr2"></td>
        </tr>
        
        <tr>
        	<td ><label style="color:#000;"><b>City</b></label></td>
        	<td ><div align="left"><input style="width: 256px;" class="span3" type="text" placeholder="" name="city"  id="city"></div></td>
        	<td >&nbsp;</td>
          	<td ><label style="color:#000;"><b>State</b></label></td>         
	 		<td >
				<div id="displaySubjects" style="display:show;">
					<select    style="width: 268px;"  class="span3"  id="state" name="state">
				    	<option value="-1" selected="selected"	>All</option>
			   			<option value="1"	>Andhra Pradesh</option>
	                 	<option value="2">Madya pradesh</option>
			   			<option value="3">Bihar</option>
			   		</select>
		   		</div>
        	</td>
        </tr> 
        <tr>
			<td ><label style="color:#000;"><b>Gender</b></label></td> 
          	<td ><div align="left">                  
             	<select  style="width: 268px;" class="span3"   name="gender"  id="gender" ><!--  onchange="changeCountry(this.value)" -->
               	    <option value="A" selected="selected">All</option>
               	    <option value="M">Male</option>
                 	<option value="F">Female</option>
       			</select> </div>
      		</td>           
      		<td >&nbsp;</td>
        	<td id="desigIDLabel"><label style="color:#000;"><b>Designation</b></label></td>
         	<td id="desigID"><input style="width: 256px;" class="span3" type="text" placeholder="" name="designation"  id="designation"></td>
	 	</tr>      
		<tr   id="admissionOne" style="display:none;">
          	<td ><label style="color:#000;"><b>Admission Number  </b></label></td>
          	<td ><div align="left"><input style="width: 256px;" class="span3" type="text" placeholder="" name="admissionNumber"  id="admissionNumber">   </div></td>
          	<td >&nbsp;</td>
          	<td id="classAdmittedInLabel" ><label style="color:#000;"><b>Class Admitted In</b></label></td>
	      	<td id="classAdmittedInSelect">
	           	<select class="multiselect" multiple="multiple"  id="classAdmittedIn" name="classAdmittedIn">		
					<option value="0" >Select All </option>	
			  	</select>								          
			 </td>
        </tr>
        <tr   id="admissionTwo" style="display:none;">
        	<td ><label style="color:#000;"><b>Admission Date From</b></label></td>
         	<td ><div align="left">
      	 		<div id="admissionDate1"    class="input-append date"   >
			    		<input style="width:85%;" class="span3" type="text" data-format="yyyy-MM-dd"  name="admissionDateFrom"  id="admissionDateFrom"></input>
			      		<span class="add-on">
			        		<i data-time-icon="icon-time" data-date-icon="icon-calendar"></i>
			      		</span>
		   		</div>	</div>
		   	</td>
			<td >&nbsp;</td>
          	<td ><label style="color:#000;"><b>Admission Date To</b></label></td>
         	<td ><div align="left">
      	 		<div id="admissionDate11"    class="input-append date"   >
					<input style="width:85%;" class="span3" type="text" data-format="yyyy-MM-dd"  name="admissionDateTO"  id="admissionDateTO"></input>
					<span class="add-on">
			        	<i data-time-icon="icon-time" data-date-icon="icon-calendar"></i>
			      	</span>
		    	</div></div>
		    </td>
		</tr>
	    <tr class='parentClassID'><!-- class='parentClassID' -->
	 <!--    	<td ><label style="color:#000;"><b>Is Parent</b></label></td>
	        <td ><div align="left"><input type="checkbox" style="margin-top:-6px;" name="isParent"  id="isParent" onclick="isParentCheck();" disabled="disabled"></div></td>
	         -->
	           <td id='isDefaultLable'><label style="color:#000;"><b>Is Default</b></label></td>
      		 <td id='isDefaultCheck'><div align="left"><input type="checkbox" style="margin-top:-6px;" name="isDefault"  id="isDefault" checked="checked" ></div></td>
	         <td>&nbsp;</td>
	      	<td ><label style="color:#000;"><b>Parent Type</b></label></td>         
	        <td > 
				<select    style="width: 268px;"  class="span3"  id="parentTypeSel"   name="parentTypeSel">
					<option selected="selected" value="-1">Select</option>
					<option value="1">Father</option>
	                <option value="2">Mother</option>
					<option value="3">Other</option>
				</select>
	         </td>       
	     </tr> 
	     <tr>
	         <td ><label style="color:#000;"><b>Active</b></label></td>
	         <td ><div align="left"><input type="checkbox" name="active" id="active"  checked="checked">	</div></td>        
	         <td >&nbsp;</td>  
	       
	   	</tr>
		<tr><td colspan="5" >&nbsp;</td> </tr>
        <tr>
        	<td colspan="5">
        	<!-- 	<div style="display: inline-block;padding-left: 200px;"> -->
        		<div style="text-align: center;">
          			<button type="button" id ="searchID" name="searchID"  style="margin-left: 20px;" class="btn btn-warning" onclick="getUsersList();return false;">Search</button>  
           			<button type="button" id ="resetID" name="resetID"  style="margin-left: 10px;"  class="btn btn-warning" onclick="resetFormHelp();return false;">Reset</button> 
          		</div>
	       </td>
	   </tr>
	   <tr><td>&nbsp;</td></tr> 
       <tr><td><label style="color:#000;width: 103%;"><b>Save search as</b></label></td>
        <td><input style='width:73%;margin-left: 10px;' type="text" class="span3" name="searchDesc" id="searchDesc" >
        		<a class="btn btn-warning" style="margin-bottom: 10px;text-align: center;height: 18px;width: 5%" href="#" onclick="saveUserSearchHis();return false;"><i class="icon-ok-circle"></i></a>
        </td><td>&nbsp;</td>
        <td  colspan="2"  style="text-align: right;">&nbsp;<a href="#" onclick="downloadUserCSV();" style="color: #000;">Download Result as CSV</a>&nbsp;</td>
        </tr>
	   
     </table>
    </div>
    </fieldset>
  </form>
</div>
<div style="height:20px;"></div>

<div id="studentDynamicGrid" ></div>

<!-- /container -->
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
			
		<div id="idConfirmDialogSearch" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="windowTitleLabel" aria-hidden="true">
			<img src="img/alert.gif" width="34" height="28" alt="confirm icon">
			<h3 id="idConfirmDialogPromptSearch"></h3>
			<a href="#" class="btn btn-primary" style="margin-top: 0px;" onclick="okConfirmDialogSearch ();">Yes</a>
			<a href="#" class="btn" style="margin-top: 0px;"onclick="closeConfirmDialogSearch ();">No</a>
		</div>		   
            
<!--         START**********     http://jbutz.github.io/bootstrap-lightbox/#demo-->
      <div id="demoLightbox"  aria-hidden="true" role="dialog" tabindex="-1" class="lightbox hide fade" style="position: fixed; width: 794px; height: 534px; top: 0px; left: 50%; margin-left: -397px; display: none;">
			<div class="lightbox-content" style="width: 774px; height: 514px;">
				  <img id='imgIDViwer' src="" alt="image"/>
						<div class="lightbox-caption">
							<a id="anchorIDViwer" href="#">
								<i style='color: white;'>Download</i> 
							</a>
						</div>
			</div>
		</div>
            
<!--         END**********     -->
  <script>
  
  
	Date.prototype.format2String = function (format) {
	var returnStr = "";
	var replace = Date.replaceChars;
	for (var i = 0; i < format.length; i++) {
		var curChar = format.charAt(i);
		if (i - 1 >= 0 && format.charAt(i - 1) == "\\") {
			returnStr += curChar;
		} else {
			if (replace[curChar]) {
				returnStr += replace[curChar].call(this);
			} else {
				if (curChar != "\\") {
					returnStr += curChar;
				}
			}
		}
	}
	return returnStr;
};
Date.replaceChars = {
	shortMonths : ["Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"],
	longMonths : ["January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"],
	shortDays : ["Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat"],
	longDays : ["Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"],
	d : function () {
		return (this.getDate() < 10 ? "0" : "") + this.getDate();
	},
	D : function () {
		return Date.replaceChars.shortDays[this.getDay()];
	},
	j : function () {
		return this.getDate();
	},
	l : function () {
		return Date.replaceChars.longDays[this.getDay()];
	},
	N : function () {
		return this.getDay() + 1;
	},
	S : function () {
		return (this.getDate() % 10 == 1 && this.getDate() != 11 ? "st" : (this.getDate() % 10 == 2 && this.getDate() != 12 ? "nd" : (this.getDate() % 10 == 3 && this.getDate() != 13 ? "rd" : "th")));
	},
	w : function () {
		return this.getDay();
	},
	z : function () {
		var d = new Date(this.getFullYear(), 0, 1);
		return Math.ceil((this - d) / 86400000);
	},
	W : function () {
		var d = new Date(this.getFullYear(), 0, 1);
		return Math.ceil((((this - d) / 86400000) + d.getDay() + 1) / 7);
	},
	F : function () {
		return Date.replaceChars.longMonths[this.getMonth()];
	},
	m : function () {
		return (this.getMonth() < 9 ? "0" : "") + (this.getMonth() + 1);
	},
	M : function () {
		return Date.replaceChars.shortMonths[this.getMonth()];
	},
	n : function () {
		return this.getMonth() + 1;
	},
	t : function () {
		var d = new Date();
		return new Date(d.getFullYear(), d.getMonth(), 0).getDate();
	},
	L : function () {
		var year = this.getFullYear();
		return (year % 400 == 0 || (year % 100 != 0 && year % 4 == 0));
	},
	o : function () {
		var d = new Date(this.valueOf());
		d.setDate(d.getDate() - ((this.getDay() + 6) % 7) + 3);
		return d.getFullYear();
	},
	Y : function () {
		return this.getFullYear();
	},
	y : function () {
		return ("" + this.getFullYear()).substr(2);
	},
	a : function () {
		return this.getHours() < 12 ? "am" : "pm";
	},
	A : function () {
		return this.getHours() < 12 ? "AM" : "PM";
	},
	B : function () {
		return Math.floor((((this.getUTCHours() + 1) % 24) + this.getUTCMinutes() / 60 + this.getUTCSeconds() / 3600) * 1000 / 24);
	},
	g : function () {
		return this.getHours() % 12 || 12;
	},
	G : function () {
		return this.getHours();
	},
	h : function () {
		return ((this.getHours() % 12 || 12) < 10 ? "0" : "") + (this.getHours() % 12 || 12);
	},
	H : function () {
		return (this.getHours() < 10 ? "0" : "") + this.getHours();
	},
	i : function () {
		return (this.getMinutes() < 10 ? "0" : "") + this.getMinutes();
	},
	s : function () {
		return (this.getSeconds() < 10 ? "0" : "") + this.getSeconds();
	},
	u : function () {
		var m = this.getMilliseconds();
		return (m < 10 ? "00" : (m < 100 ? "0" : "")) + m;
	},
	e : function () {
		return "Not Yet Supported";
	},
	I : function () {
		return "Not Yet Supported";
	},
	O : function () {
		return (-this.getTimezoneOffset() < 0 ? "-" : "+") + (Math.abs(this.getTimezoneOffset() / 60) < 10 ? "0" : "") + (Math.abs(this.getTimezoneOffset() / 60)) + "00";
	},
	P : function () {
		return (-this.getTimezoneOffset() < 0 ? "-" : "+") + (Math.abs(this.getTimezoneOffset() / 60) < 10 ? "0" : "") + (Math.abs(this.getTimezoneOffset() / 60)) + ":00";
	},
	T : function () {
		var m = this.getMonth();
		this.setMonth(0);
		var result = this.toTimeString().replace(/^.+ \(?([^\)]+)\)?$/, "$1");
		this.setMonth(m);
		return result;
	},
	Z : function () {
		return -this.getTimezoneOffset() * 60;
	},
	c : function () {
		return this.format("Y-m-d\\TH:i:sP");
	},
	r : function () {
		return this.toString();
	},
	U : function () {
		return this.getTime() / 1000;
	}
};
		function formatDate(dateStr, format){
				try{
					var dateTimeArr 	= dateStr.split(" ");
					var formattedDate 	= "";
					
					var format = (format == "" || format == undefined || format == "undefined" || format == null) ? "d M y" : format;
					
					if(dateTimeArr.length > 1){
						//date has time inclided					
						var dateArr = dateTimeArr[0].split("-");
						var timeArr = dateTimeArr[1].split(":");
						//alert("jkjk");
						return new Date(dateArr[0],dateArr[1]-1,dateArr[2],timeArr[0], timeArr[1], timeArr[2]).format2String(format);
					}else{
						//only date
						var dateArr = dateTimeArr[0].split("-");
						//alert("jkjk222");
						return new Date(dateArr[0],dateArr[1]-1,dateArr[2]).format2String(format);
					}
				}catch(e){
				//Logger.error("There is an arror formatting date, " + e.message + "\n in formatDate function");
				}
			}
    jQuery('#datetimepicker').datetimepicker({
      		  pickTime: false
      			}); 
    jQuery('#admissionDate1').datetimepicker({
      		  pickTime: false
      			}); 
    jQuery('#admissionDate11').datetimepicker({
      		  pickTime: false
      			}); 
</script>

 <script  src="js/paginationjQuery.js"> </script> 

    