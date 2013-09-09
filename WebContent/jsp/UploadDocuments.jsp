<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
        pageEncoding="ISO-8859-1"%>
      
    <%! 
	String forEditJson ;
	String resultAfterEidtSuccess ; 
	%> 
        <%        
        String forRef = (String) request.getParameter("p2");
        System.out.println(forRef+"-->forRef");
        System.out.println(forEditJson+"edit::"+session.getAttribute("forEdit"));
        
	        if(session.getAttribute("forEdit") != null && forRef.equalsIgnoreCase("Edit")){
	        forEditJson =(String) session.getAttribute("forEdit");        
	        }else{
	        //forEditJson = null;//obervation is req
	        }
	         if(session.getAttribute("resAfterEdit") != null){
	         resultAfterEidtSuccess = (String) session.getAttribute("resAfterEdit"); 
	         session.removeAttribute("resAfterEdit"); 
	         }
	        
	        if(forEditJson !=null)
				session.removeAttribute("forEdit"); 
         %>

        <link href="css/bootstrap.css" rel="stylesheet">
            <link href="css/bootstrap-fileupload.css" rel="stylesheet" media="screen">	
        	 <!--    <script src="js/bootstrap-jquery.js"></script> -->
        	<script type="text/javascript" src="js/bootstrap-radio.js"></script>
            <script src="js/bootstrap-fileupload.js" type="text/javascript"></script>
            <script src="js/bootstrap-multiselect.js" type="text/javascript"></script>
        
         <script 	src="<%=request.getContextPath()%>/dwr/interface/SchoolMasterDWR.js"></script>
         <script 	src="<%=request.getContextPath()%>/dwr/interface/BranchMasterDWR.js"></script>
         <script 	src="<%=request.getContextPath()%>/dwr/interface/ClassMasterDWR.js"></script>
        
        
        
        
        <script type="text/javascript">
        var forSubm;
        var responseAfterEdit = '<%=resultAfterEidtSuccess%>';
        <%
   		  if(forRef.equalsIgnoreCase("Edit")){%>
        		var jsonForEditBeforeParse = <%=forEditJson%>
        		forSubm = "EditOK";
        <%} %>
        

        
        $(document).ready(function() {
        
        		resultPopUp();
				schoolMasterList();
        
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
					buttonWidth: '270px',
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
					buttonWidth: '270px',
					filterPlaceholder: 'Search Branch'
				});
				
				$('#selectClass').multiselect({
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
					buttonWidth: '270px',
					filterPlaceholder: 'Search Class'
				});
		
				$('#selectType').multiselect({
						buttonText: function(options, select) {
							if (options.length == 0) {
								return 'Select Audience Type <b class="caret"></b>';
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
					buttonWidth: '297px',
					filterPlaceholder: 'Search'
				});
				try{
						if (jsonForEditBeforeParse !== undefined && jsonForEditBeforeParse !== null && jsonForEditBeforeParse !== "null" ) {								
							jQuery('.navbar-inner').hide();//2013-07-07---window open...hide above login and menu...1
							jQuery('.footer').hide();//footer hide//2
							jQuery('#hidePurposeEditUp').hide();//3
							setTimeout(uploadEditDataArrange,600);
				      }
				}catch (e) {	//alert(e);
				}						
		 });
			 
		 function uploadEditDataArrange(){
			 var schoolListArray = jsonForEditBeforeParse.schoolList;		
			var timeFor = 200;

			for ( var int = 0; int < schoolListArray.length; int++) {
				var schoolListArray_element = schoolListArray[int];
				 jQuery("#schoolNames").multiselect('select', schoolListArray_element);
			}
		//after selecting school we need to call brachmaster load...after that select req values based on for loop..
		
		setTimeout(selectSchoolforBranches,timeFor);		
		timeFor +=200;
		setTimeout(selectBrachesFucn,timeFor);
		
		timeFor +=200;	
		setTimeout(selectBranchesforClasses,timeFor);		
		
		timeFor +=300;			
		setTimeout(selectClassFucn,timeFor);
		
		timeFor +=400;			
			
		setTimeout(remainingSelect,timeFor);

		 }
		 
		 function remainingSelect(){
		 
		  var userType = jsonForEditBeforeParse.userType;
		  var uploadType = jsonForEditBeforeParse.uploadType;
		 var assignType = jsonForEditBeforeParse.assignType;
		 var assignDesc = jsonForEditBeforeParse.assignDesc;
		 var sub = jsonForEditBeforeParse.sub;
		 var fileName = jsonForEditBeforeParse.fileName;
		 var email = jsonForEditBeforeParse.email;
		  
		 //alert(userType+"userType");
		 if (userType == "0") {
		 jQuery("#selectType").multiselect('select', "0");
		 jQuery("#selectType").multiselect('select', "Parents");
		 jQuery("#selectType").multiselect('select', "Students");			
		}else{
		 jQuery("#selectType").multiselect('select', userType);
		}
		 
		 jQuery("#uploadType").val( uploadType ).attr('selected',true);
		 
	
 		 if (uploadType == "Assignment" ) {
 		  jQuery('#assignmentType').prop('disabled', false);
 		  jQuery('#selectSubject').prop('disabled', false);
 		  jQuery('#desc').prop('disabled', false);
 		  jQuery("#assignmentType").val( assignType ).attr('selected',true);
 		  jQuery("#desc").val( assignDesc ).attr('selected',true);
 		  setTimeout(selectSubjectsForClasses,300);
 		     setTimeout(selectSubFucn,400);
	 	}else if(uploadType == "AcademicMaterial"){
	 	 jQuery('#selectSubject').prop('disabled', false);
 		  jQuery('#desc').prop('disabled', false);
 		  jQuery("#desc").val( assignDesc ).attr('selected',true);

 		  setTimeout(selectSubjectsForClasses,300);
 		  setTimeout(selectSubFucn,500);
		 
       }
	
			 var innerForFile = '<td  style="text-align:center;"><a href="downloadUploadDoc.action?type='+uploadType+'&fileName='+fileName+'" style="color:red; text-decoration: none;"  ><i class="icon-eye-open"></i>  View Previous File</a></td>	'; 
				jQuery("#editFile").html(innerForFile);
			 
			 if (email == "Y" || email == "y") {
				document.getElementById("nty_email").checked="true";
				showEmailSub();
			}
			 
		 
		 
		 }
		 function selectSubFucn(){
		  var sub = jsonForEditBeforeParse.sub;
		  jQuery("#selectSubject").val( sub ).attr('selected',true);
		//  alert("sub:"+sub);
		 }
		 
		 
		function selectBrachesFucn(){
		//alert(branchListArray+"branchListArray");
		 var branchListArray = jsonForEditBeforeParse.branchList;
			for ( var int = 0; int < branchListArray.length; int++) {
			var branchListArray_element = branchListArray[int];
			//alert(branchListArray_element+"*");
			 jQuery("#branchNames").multiselect('select', branchListArray_element);
		}
		}
		function selectClassFucn(){
		//alert(branchListArray+"branchListArray");
		 var classListArray = jsonForEditBeforeParse.classList;
			for ( var int = 0; int < classListArray.length; int++) {
			var classListArray_element = classListArray[int];
			//alert(branchListArray_element+"*");
			 jQuery("#selectClass").multiselect('select', classListArray_element);
		}
		}
		
		 
			        
  /*           $(function(){
          $('input:checkbox').screwDefaultButtons({
                image: 'url("img/checkboxSmall.jpg")',
                width: 20,
                height: 20
            });  
            }); */
       
	    function editPreviousUploads(){
	      		//alert('editPreviousUploads');
	      		 document.location = "Access.action?p1=EditUploadedDocuments&p2=SA_inst2";       
	      }
	      
	       function selectType1(val){
			    if(val=='Assignment' || val=='AcademicMaterial'){
				    if ( val=='Assignment') {
						document.getElementById('assignmentType').disabled = false;
					}else{
						document.getElementById('assignmentType').disabled = true;
					}
					document.getElementById('selectSubject').disabled = false;				
					document.getElementById('desc').disabled = false;
					  subjectMaster();
				}else{
					    document.getElementById('assignmentType').disabled = true;
					    document.getElementById('selectSubject').disabled = true;
					    document.getElementById('desc').disabled = true;
					    	//$("#selectSubject").attr("disabled", "disabled"); display: none;
					    document.getElementById('selectSubject').display = 'none';
					//	jQuery('.multiselect dropdown-toggle btn[data-toggle="dropdown"]').addClass('disabled');

					/* var $spans = $('button[data-toggle="dropdown"]')[4];
					$(".btn-group").find( $spans ).addClass('disabled');	 */
	
			  }
			  
		 }
		 
	
	function schoolMasterList(){
	    
		   try{
				//	alert("onload school master"+im_id);
						var listofschools = document.getElementById('schoolNames');					
	//alert(listofschools.value+"**");
						    SchoolMasterDWR.getSchoolMasterList(function(data){
						                                if (data == null) {													
						                                  alert("error");
						                                 
														} else {				
														//alert(data+":::"+data.length+data[1]);	                                 
						                               for(var i = 0; i < data.length; i++) {
														  var opt = document.createElement("option");
															    var temp = data[i];														
															    var d = temp[0];
															    var dV = temp[1];
															    var  innerHtml= "<option value="+d+">"+dV+"</option>)";
															    
															  //  alert(d+"^^^"+dV);
															    $('#schoolNames').append(innerHtml);																	    
															    
															}
																			//alert(listofschools.value+"$$$$$");
															$('#schoolNames').multiselect('rebuild');		                          
														}
						                         }
						      ) ;  
						
		   		 }catch(e){
						 alert("incatch::"+e);
						        jQuery.log.info(e.message);
						        jQuery("#infoError").html("&nbsp;");
		         }
	} 
		 
		 
		 
	function selectSchoolforBranches(){
	   var schoolID = jQuery("#schoolNames").val();
		removeAllOptions("#branchNames");
		removeAllOptions("#selectClass");
		if(schoolID == null || schoolID == ""){
			setError("schoolNames","Please select School");
			 jQuery("#schoolNames").focus();
			return false;
		}
		   try{
		   	var im_id = "<%=session.getAttribute("IM_ID")%>";			   	
						var listofbranchs = document.getElementById('branchNames');					
	
					//    BranchMasterDWR.getBranchMasterList(schoolID,function(data){
					    BranchMasterDWR.getMultiBranchMasterList(schoolID,function(data){
						                                if (data == null) {
						                                  alert("error");
														} else {
														//alert(data+":::"+data.length+data[1]);	                                 
						                               for(var i = 0; i < data.length; i++) {
															  var opt = document.createElement("option");
															    var temp = data[i];
															    	var  innerHtml= "<option value="+ temp[0]+">"+ temp[1]+"</option>)";
															    	$('#branchNames').append(innerHtml);
															}
														}
															$('#branchNames').multiselect('rebuild');
						                         }
						      ) ;
						
		   		 }catch(e){
						 alert("incatch::"+e);
						        jQuery.log.info(e.message);
						        jQuery("#infoError").html("&nbsp;");
		         } 	
	 }
	 
	 function selectBranchesforClasses(val){
	   var schoolID = jQuery("#schoolNames").val();
		removeAllOptions("#selectClass");
		//removeAllOptions("selectClass");

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
			var listofclasses = document.getElementById('selectClass');					
	
			    ClassMasterDWR.getMultiClassMasterList(branchID,function(data){
			   // alert( "data***"+data);
				                                if (data == null) {
				                                  alert("error");
												} else {
												//alert(data+":::"+data.length+data[1]);	                                 
				                               var classListJson  =  jQuery.parseJSON(data);
				                               
				          /*                       for (var key in classListJson) {
													  if (classListJson.hasOwnProperty(key)) {
													    alert(classListJson[key].class_name+"@@"+key + " -> " + classListJson[key].class_id);
													  }
												} */
				                               
				                               //alert(classListJson+"classList***"+classListJson.length);
				                               if(classListJson != null && classListJson.length > 0){
				                               
				                               jQuery('#classBMIds').val(data);//hidden value
				                              	 var tttt = '';
				                                 for(var i = 0; i < classListJson.length; i++) {
				                               //  alert(classListJson[i].class_name+"@@"+i + " -> " + classListJson[i].class_id);
					                                 if (tttt != classListJson[i].class_name) {
					                                 	tttt = classListJson[i].class_name;
					                               		var opt = document.createElement("option");
												    	var  innerHtml= "<option value="+ classListJson[i].class_id+">"+ classListJson[i].class_name+"</option>)";
												    	$('#selectClass').append(innerHtml);
													}
				                               	}
				                               }
											}
													$('#selectClass').multiselect('rebuild');
													jQuery("#selectClass").focus();
				                         }
				      ) ;
						
													jQuery("#selectClass").focus();
		   		 }catch(e){
						 alert("incatch::"+e);
						        jQuery.log.info(e.message);
						        jQuery("#infoError").html("&nbsp;");
		         } 	
			  
		 }
	 
	 	function selectSubjectsForClasses(){	   		 
		   var upload = jQuery("#uploadType").val();		   
				    if(upload =='Assignment' || upload=='AcademicMaterial'){
				    //alert(upload+"in selct  ===");
				//    removeAllOptions("#selectSubject");				    
						  subjectMaster();
						
					}		  
	}
		 
		 
	function subjectMaster(){
			//Process DWR/AJAX request here
				try{
			    var branchIDs = jQuery("#branchNames").val();
				var classIDs = jQuery("#selectClass").val();
				if (branchIDs != null && classIDs != null) {
			
				var im_id = "<%=session.getAttribute("IM_ID")%>";					
	   			var schoolIDs = jQuery("#schoolNames").val();
	   			
				removeAllOptionsOne("selectSubject");
			
					//	alert(listofSubjects+":::"+im_id+"--"+schoolID+"--"+branchID);
					var listofSubjects = document.getElementById('selectSubject');			
				     ClassMasterDWR.getMultiSubjectMasterList(schoolIDs,branchIDs,classIDs,function(data){
					                                if (data == null) {
					                                  alert("error");
													} else {
									//alert(data+"**"+data.length);
													    for(var i = 0; i < data.length; i++) {
											    		 var opt = document.createElement("option");
													    var temp = data[i];
													    opt.innerHTML = temp[0];
													    opt.value = temp[1];
													    listofSubjects.appendChild(opt);
													}
												}
					                         }
					      ) ;}
					 }catch(e){
					 alert("incatch::"+e);
					        jQuery.log.info(e.message);
					        jQuery("#infoError").html("&nbsp;");
	        	     } 	
	}
		
			function removeAllOptions(whichOption)
		{
					//alert(whichOption+"---");
					$(whichOption).html('');
					var  innerHtml= "<option value='0'>Select All </option>";					

				    $(whichOption).append(innerHtml);		
	
				$(whichOption).multiselect('rebuild');	
	
		}
		
    	 function removeAllOptionsOne(whichOption)
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
		    listofSubjects.appendChild(opt);  */
			
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
		       jQuery("#schoolNames").val('Select School');
		       jQuery("#branchNames").val('Select Branch');
		       jQuery("#selectClass").val('Select Class');
		       jQuery("#selectType").val('Select');
		       jQuery("#uploadType").val('Select');
		       jQuery("#assignmentType").val('Select');
		       jQuery("#selectSubject").val('Select Subject');
		 /*    
		    $form.find('input:radio, input:checkbox')
		         .removeAttr('checked').removeAttr('selected'); */
		}
		
		function showEmailSub(){
			var msgg="";
			var uploadValue =  jQuery("#uploadType").val();
				if (document.getElementById("nty_email").checked) {
				 	var name = "<%=session.getAttribute("name")%>";		
					if ( uploadValue == -1) {
						setError("uploadType","Please select upload type");
			 			jQuery("#uploadType").focus();
						return false;
					}else{			
							/* msgg = "Following email will be sent to the parents:<br/><br/>Subject:&nbsp;&nbsp;&nbsp;New "+uploadValue+" is uploaded.<br/>Body:<br/><p style='padding-left:11px;'>Dear Parent,  "+
												"<br/>New "+uploadValue+" has been uploaded on the school portal.<br/> Kindly login and check.<br/><br/>Yours,<br/>"+name+"</p> ";*/

							msgg = "Subject:&nbsp;&nbsp;&nbsp;   New "+uploadValue+" uploaded<br/><br/>Dear Parent, <br/><br/>New "+uploadValue+" has been uploaded on the school portal.<br/><br/>Kindly login and check.<br/><br/>Yours,<br/>School Admin<br/> ";

							jQuery("#emailSub").html(msgg);							
					}
					
				}else if(!jQuery("#nty_email").checked){
							jQuery("#emailSub").html(msgg);
				}
		}
		
		   function resultPopUp(){
		    jQuery("#schoolNames").focus();
	    	var msg = "<%=session.getAttribute("msg")%>";
	    	
		    if(msg == null || msg == "null"){
		    }else if(responseAfterEdit != null && responseAfterEdit != "null"){
		    	jQuery('.navbar-inner').hide();//2013-07-07---window open...hide above login and menu...1
		    	jQuery('.reg_mainCon').hide();
				jQuery('.footer').hide();//footer hide//2
				jQuery('#hidePurposeEditUp').hide();//3
		     sucessShow();
		     //sucessShow("Success. This window will close in ");
		    }else{
		    	var paramP2 = "<%=forRef%>";
			     setError("schoolNames",msg);
		      	jQuery("#schoolNames").focus();
		        resetForm($('#uploaddocForm')); // by id, recommended
		    }
		     if(msg != null){
		 		<%session.removeAttribute("msg"); %> ;
		     }
	    }
	    
	    var successDisp=null;
		var countdown;
		var countdown_number;
	    function sucessShow() {
/* 		if(successDisp != null){
			successDisp.stop(true, true).animate({opacity: 1}, 0);
			successDisp.hide();
		}
		var feild 		= jQuery("#"+"errorspan");
		successDisp 	= jQuery("#errorspan");		
		successDisp.show();											
		jQuery("#errorspan").html(msg);
		successDisp.position({
			of:feild,
			my:"left" 	+ " " + "top",
			at:"right" 	+ " " + "top",
			offset:'0 0',
			collision : 'none none'			
		});
		successDisp.animate({opacity: 0}, 15000); */
		  setTimeout('window.close()',9000);
		
	    countdown_number = 11;
	    countdown_trigger();
	}	
		function countdown_trigger() {
	    if(countdown_number > 1) {
	        countdown_number--;
	        document.getElementById('countDownDisp').innerHTML ="Success. This window will close in "+ countdown_number+" sec's";
	        if(countdown_number > 1) {
	            countdown = setTimeout('countdown_trigger()', 1000);
	        }
	    }
	}

		function validateForm(val){
	
			var schoolNames = jQuery("#schoolNames").val();
			var branchNames = jQuery("#branchNames").val();
			var selectClass = jQuery("#selectClass").val();
			var selectType = jQuery("#selectType").val();
			var uploadType = jQuery("#uploadType").val();
			
			
			if(schoolNames== null){
			 setError("schoolNames","Please select valid School(s)");
				jQuery("#schoolNames").focus();
				return false;
			}
			
			if(branchNames== null){
				 setError("branchNames","Please select  Branch(s)");
				jQuery("#branchNames").focus();
				return false;
			}
			
			if(selectClass== null){
				 setError("selectClass","Please select  Class(s)");
				jQuery("#selectClass").focus();
				return false;
			}
			if(selectType== null){
				 setError("selectType","Please select  Parent/Student/All");
				jQuery("#selectType").focus();
				return false;
			}
			
		if(uploadType=="-1"){
				 setError("uploadType","Please select  Upload type");
				jQuery("#uploadType").focus();
				return false;
			}
			
			if (uploadType == "Assignment" || uploadType == "AcademicMaterial") {
				var assignmentType = jQuery("#assignmentType").val();
				var selectSubject = jQuery("#selectSubject").val();
				var desc = jQuery("#desc").val();
				
					if (assignmentType == "-1" && uploadType == "Assignment") {
							setError("assignmentType","Please select Assignment Type");
							jQuery("#assignmentType").focus();
						return false;
					}		
					
						
					if (selectSubject == "-1") {
							setError("selectSubject","Please select Subject(s)");
							jQuery("#selectSubject").focus();
				return false;
					}	
					if (desc == "" || desc.length<1) {
							setError("desc","Please select description");
							jQuery("#desc").focus();
				return false;
					}		
				
			}
				
				var file1  = jQuery("#fileUP").val();
				if (val == 2) {
						if(file1 == "" || file1 == undefined  || file1 == "undefined"){
							setError("branchNames","Please select a file");
								jQuery("#fileUP").focus();
							return false;
						}

				}
			if(file1 !== "" && file1 !== undefined  && file1 !== "undefined"){
					var extension = file1.split('.').pop().toLowerCase();
					var allowed = ['pdf', 'doc', 'docx', 'xls', 'xlsx', 'txt','ppt','pptx','gif','csv','png','jpg','jpeg','dot','dotx','html','odm','ott','odt','rtf','xps'];
			
					if(allowed.indexOf(extension) === -1) {
					    // Not valid.
						setError("branchNames","Your selected "+ file1+"  extension wrong");
							jQuery("#fileUP").focus();
						return false;
					}
			}

			
		return true;	
		}
		
	
	function formSubmitBoth(){
		//alert("999999999999"+forSubm+"**ID*"+jsonForEditBeforeParse.uploadID);
		 if (forSubm == "EditOK") {
			//alert("in if88888;;;;"+jsonForEditBeforeParse.uploadID);
			if(!validateForm(1)){
				return false;		
			}
			  document.uploaddocForm.action = 'editedDocumentSaveAction.action?upld_id='+jsonForEditBeforeParse.uploadID+'&fileNameOld='+jsonForEditBeforeParse.fileName;
		      document.uploaddocForm.method = 'post';
		   	  document.uploaddocForm.submit(); 
			
		} else{
				//alert("in else;;;;");
		   if(!validateForm(2)){
				return false;		
			}
			  document.uploaddocForm.action = 'uploadDocActionName.action';
		      document.uploaddocForm.method = 'post';
		   	  document.uploaddocForm.submit(); 
				
		}
     
		}

    </script>
   
  
<div style="height:20px;text-align: center;"><span id ="countDownDisp" style="color:red;font-size: 14px;font-weight: bold;" >&nbsp;</span></div>
<div class="reg_mainCon">
   <form  method="post"  name="uploaddocForm" id="uploaddocForm" enctype="multipart/form-data" > 
<!--    <form  method="post"  action="uploadDocActionName.action" name="uploaddocForm" id="uploaddocForm" enctype="multipart/form-data" onsubmit="return validateForm()">  -->
    <fieldset>
    <legend><img src="img/list_add_user.PNG" class="img-circle">&nbsp;&nbsp;Upload Documents</legend>
    <div style="padding:20px;padding-top: 2px;">
      <label style="color:#000;"></label>
   <table width="100%" border="0" cellspacing="0" cellpadding="0">
  
           <tr>
             <td colspan="3" style="text-align:right;"><button type="button" id="hidePurposeEditUp" class="btn btn-warning" onclick="editPreviousUploads(); return false;">Edit previous uploads</button> </td>
        </tr>
   <tr><td colspan="4" style="text-align: center;"><span id ="errorspan" style="color:red;font-size: 14px;" ></span>&nbsp;</td></tr>
        <tr>
                <td colspan="3"><label style="color:#000;"><b>Select Audience</b></label></td>
        </tr>
          <tr>
                <td><label style="color:#000;"><b>Display to</b></label></td>
                <td>
                   <div class="input-append">  
						<select class="multiselect" multiple="multiple"  id="schoolNames" name="schoolNames">			
							<option value="0" >Select All </option>
						</select>
					  <button class="btn " type="button" style="width:30px;padding: 4px; "  id="schoolNamesGo" onclick="return selectSchoolforBranches();"  name="schoolNamesGo" >Go!</button> <!--onblur="return selectSchoolforBranches();" onclick="return selectSchoolforBranches();"  -->
				 </div><!-- btn-primary -->                
			</td>                 
            <td>
               <div class="input-append">  
					<select class="multiselect" multiple="multiple"  id="branchNames" name="branchNames">		
						<option value="0" >Select All </option>	
					</select>
				    <button class="btn " type="button" style="width:30px;padding: 4px; "  id="branchNamesGo" name="branchNamesGo"  onclick="selectBranchesforClasses();">Go!</button><!-- onblur=" selectBranchesforClasses();" --> 
			  		<input type="hidden" id = "classBMIds" name = "classBMIds"/>
			   </div>
			</td>
        </tr>
        <tr>
            <td></td>
            <td>
              <div class="input-append">  
              		<select class="multiselect" multiple="multiple"  id="selectClass" name="selectClass">		
						<option value="0" >Select All </option>	
					</select>
				  	<button class="btn " type="button" style="width:30px;padding: 4px; "  id="classNamesGo" name="classNamesGo"  onclick=" selectSubjectsForClasses();"  >Go!</button>  </div>				          
            </td>
            <td>
					<select class="multiselect" multiple="multiple"  id="selectType" name="selectType">		
						<option value="0" >Select All </option>	
						    <option value="Parents"> Parents</option>
                        <option value="Students">Students</option>
					</select>
           </td>                 
        </tr>
        <tr>
         <td><label style="color:#000;"><b>Upload Type</b></label></td>
                <td><select class="span4"   name="uploadType" id="uploadType" onchange="selectType1(this.value)">
                        <option value="-1"  selected="selected">Select</option>
                        <option value="AcademicMaterial">Academic Material</option>
                        <option value="Assignment">Assignment</option>
                        <option value="Calendar">Calendar</option>
                        <option value="Canteen menu">Canteen Menu</option>
                        <option value="ExamSchedule">Exam Schedule</option>
                        <option value="Time table">Timetable</option>
                       </select>                   
                 </td>
                <td>&nbsp;</td>
             
        </tr>
         <tr>
         <td><label style="color:#000;"><b>Assignment Type</b></label></td>
                <td><select class="span4" name="assignmentType"  id="assignmentType" disabled="disabled">
                        <option value="-1"  selected="selected">Select</option>
                        <option value="Daily">Daily</option>
                        <option value="Holiday">Holiday</option>
                        <option value="Weekend">Weekend</option>
                       </select>                   
                 </td>
                <td>&nbsp;</td>
             
        </tr>
         <tr>
         <td><label style="color:#000;"><b>Description</b></label></td>
                <td> <input class="input-xlarge" style="width: 88%" type="text" placeholder="" name="desc"  id="desc" disabled="disabled">             
                 </td>
                <td>&nbsp;</td>
             
        </tr>
        
          <tr>
         <td><label style="color:#000;"><b>Subject</b></label></td>
                <td>
                 <select class="span4"  id="selectSubject" name="selectSubject" disabled="disabled">		
						<option value="-1" >Select</option>
					</select>
                 </td>
              <td>&nbsp;</td> 
        </tr>
       <!--  <tr><td>&nbsp;</td></tr> -->
         <tr>             
        <td width="20%"><label style="color:#000;"><b>Upload</b></label></td>
          <td colspan="2">
          <div id="editFile"></div>
          	<div class="fileupload fileupload-new" data-provides="fileupload" >
				  <div class="input-append">
				    <div class="uneditable-input span4"><i class="icon-file fileupload-exists"></i> <span class="fileupload-preview"></span></div><span class="btn btn-file">
				    <span class="fileupload-new">Select file</span><span class="fileupload-exists">Change</span>
				    <input type="file" name="fileUP" id ="fileUP"/></span><a href="#" class="btn fileupload-exists" data-dismiss="fileupload">Remove</a>
				  </div>
</div>
          </td>
          </tr>
         <tr>
             <td colspan="3">
             <div style="float: left;">
                <input class="span4" style="width:55px;padding-top: 3px;" type="checkbox" placeholder=""  name="nty_email" id="nty_email" onclick="showEmailSub();"></div>
               <div style="float: left;"><label for="nty_email" style="color:#000;vertical-align: 14px;"><b>Notify parents by email</b></label></div>
               <div style="width:100%;height:1px; clear:both;">&nbsp;</div>   
                </td>
        </tr>

  <tr><td style="" rowspan="8" colspan="2"><span id ="emailSub" style="color:#000;font-size: 14px;" ></span>&nbsp;</td>
           
            <td style="border:none;text-align:center;padding-bottom: 132px;" >
                      <button type="button" class="btn btn-warning" onclick="formSubmitBoth()">  &nbsp;&nbsp;&nbsp; Save&nbsp; &nbsp;&nbsp; </button>
<!--                       <button type="submit" class="btn">  &nbsp;&nbsp;&nbsp; Save&nbsp; &nbsp;&nbsp; </button> -->
                    </td>
        </tr>

       </table>

    </div>
    </fieldset>
  </form>
</div>

