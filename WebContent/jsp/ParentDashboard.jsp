<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%

System.out.println(request.getContextPath());

 %>


	<script 	src="<%=request.getContextPath()%>/dwr/interface/StudentDWR.js"></script>
	<script 	src="<%=request.getContextPath()%>/dwr/interface/NotificationGetDWR.js"></script>
	<script 	src="<%=request.getContextPath()%>/dwr/interface/AssignmentGetDWR.js"></script>
	<script 	src="<%=request.getContextPath()%>/dwr/interface/ClassMasterDWR.js"></script>
	<script 	src="<%=request.getContextPath()%>/dwr/interface/ParentDetailsDWR.js"></script>
	<script 	src="<%=request.getContextPath()%>/dwr/interface/SMSCreditsDWR.js"></script>
    <script type="text/javascript">
    	var subjectMasterList = null;
    
    function okAlertDialog () {
	$("#idAlertDialog").modal ('hide'); 
	};
function alertDialog (prompt) {
	document.getElementById ("idAlertDialogPrompt").innerHTML = prompt;
	$("#idAlertDialog").modal ("show");
	}
    jQuery(document).ready(function () {
			studentDataLoad();
			notificationLoad();
			getSubjectMasterListBM_CM_ID();			
			//assignmentLoad();
			getParentsDetailsLoad();
			utilitiesLoad();
			//academicsLoad();
			setTimeout(assignmentLoad, 300);				
			setTimeout(academicsLoad, 300);
	});

		function studentDataLoad(){
			StudentDWR.getStudentData(function(data) {
				        if (data == null) {
                                alert("error");						                                 
						} else {
							var gg = jQuery.parseJSON(data);
							var mainPath = '<%=request.getContextPath()%>';
							
								if (gg.photoID.indexOf("noImage") !== -1) {
									$("#photoID").attr("src",mainPath+"/noImage.jpg");
								}else{
									$("#photoID").attr("src",mainPath+gg.photoID);
								}
								
							jQuery("#studentID").html(gg.StuUserID);
							jQuery("#studentName").html(gg.StuName);
							jQuery("#studentDOB").html(gg.DOB);
							jQuery("#studentClass").html(gg.className);
							jQuery("#studentJoinDate").html(gg.joinDate);
						}
					});
		}

		function notificationLoad(){
			NotificationGetDWR.getNotifications(function(data) {
				          if (data == null) {													
                                alert("error");						                                 
						} else {				
							if ( data.length>0) {
								var newNtoficationGrid  	 = jQuery('#notificationDynamicTable');
								var newNtoficationAllGrid  	 = jQuery('#notificationDynamicTableAll');
								
					         var DataForNext='' ;
		                      for ( var int = 0; int < data.length; int++) {
			                      var innerArra ='' ;
									var teby = data[int];
									 for ( var int2 = 0; int2 < teby.length; int2++) {
										innerArra+=teby[int2];
										if (int2<teby.length-1) {
											innerArra+="*^";
										}
									 }
									DataForNext+=innerArra;
									 	if (int<data.length-1) {
											DataForNext+="~^";
										}
							   }
							   	var newNotificationStr = '';     
				   						newNotificationStr += '<table class="table table-bordered" width="100%" style="margin-bottom:0px;">';
										newNotificationStr += '<tr><th width="15%">From</th><th width="16%">Sent On</th><th width="40%">Subject</th><th width="15%" style="text-align:center;">View</th></tr>';
					                         var displayOption = data.length >4 ? 4:data.length;
					                           
				                           for(var i = 0; i < displayOption; i++) {
				                           var notifBodyID = i+"yy";
				                           var notifSubID 	=	i+"uu"; 
												var temp = data[i];
						 						newNotificationStr += '<tr>';
												newNotificationStr += '<td>Administrator</td><td>'+formatDate(temp[2],"d M Y")+'</td><td><label style = "color:#666666;font-weight: bold; font-size: 12px;" id ="'+notifSubID+'" value="'+temp[0]+'">'+temp[0]+'</label ></td><td><a href="#myModal_1" id= "'+i+'"  value="yuyu" style="color:#666; text-decoration:none; padding-left:50px;" data-toggle="modal" onclick="openModelWithData(id);"><i class="icon-eye-open"></i> <input type="hidden" id='+notifBodyID+' value="'+temp[1]+'"></a></td>';
												newNotificationStr += '</tr> ';
											}
										newNotificationStr += '</table>'; 
					   		 newNtoficationGrid.html(newNotificationStr);  
					   		 
					   		  newNtoficationAllGrid.html('<form method="post" id="allForm2" name="allForm2" action="ViewAllListParent.action"><a href="#" id= "66"  value="yuyu" style="color:#666; text-decoration:none; padding-left:26px;" data-toggle="modal" onclick="document.allForm2.submit()"><i class="icon-eye-open"></i><input type="hidden" id="notifAlldata" name="notifAlldata"  value="'+DataForNext+'">View  All</a></form>');

						   document.getElementById("notificationDynamicTable").style.display='block';
					       document.getElementById("notificationDynamicTableAll").style.display='block';//these r required??
					       document.getElementById("noNotification").style.display='none';
					       }else{
					       document.getElementById("notificationDynamicTable").style.display='none';
					       document.getElementById("notificationDynamicTableAll").style.display='none';
					       document.getElementById("noNotification").style.display='block';
					       }
						}
		
		});		
		}		
		function openModelWithData(vall){
		var idref = '#'+vall;
		var notifBodyID 	= '#'+vall+"yy";//for notif body
		var subNotifID 		= '#'+vall+"uu";//for sub 
		var bodyNotif 		= jQuery(notifBodyID).val();		
		var subNotif 			= $(subNotifID).attr('value');	
			
		var id = $(idref).attr('href');
		
		$("#myModalLabel11").text(subNotif); 
		$("#bodyyID").text(bodyNotif); 
		}
		function getSubjectMasterListBM_CM_ID(){
		
			var schoolID 	= "<%=session.getAttribute("SM_ID")%>";
			var branchID 	= "<%=session.getAttribute("BM_ID")%>";
			var classID 	= "<%=session.getAttribute("ClassID")%>";			
			 ClassMasterDWR.getSubjectMasterList(schoolID,branchID,classID,function(data){
						                                if (data == null) {
						                                  alert("error");					                                 
														} else {
													subjectMasterList = data;
													}
						                         }
						      ) ;
		}		
		function nextPageView(){
		  document.getElementById('allForm1').submit();
		}
				
	function assignmentLoad(){
		AssignmentGetDWR.getAssignmenet("parent",function(data) {
				          if (data == null) {													
                                alert("error");						                                 
						} else {				
							if ( data.length>0) {							
							var schoolID 	= "<%=session.getAttribute("SM_ID")%>";
							var branchID 	= "<%=session.getAttribute("BM_ID")%>";
							var classID 	= "<%=session.getAttribute("ClassID")%>";
								var newAssignmentGrid  	 = jQuery('#assignmentDynamicTable');
								var newAssignmentAllGrid  	 = jQuery('#assignmentDynamicTableAll');
							   	var newAssignmentStr = '';     
				   						newAssignmentStr += '<table class="table table-bordered" width="100%" style="margin-bottom:0px;">';
										newAssignmentStr += '<tr><th width="19%">Date</th><th width="20%">Assignment Type</th><th width="20%">Subject</th><th width="30%">Description</th><th width="12%" style="text-align:center;">View</th></tr>';
					         
					                  	  var subdataList = subjectMasterList.join('~'); 
					                      var dataTData='' ;
					                      for ( var int = 0; int < data.length; int++) {
						                      var innerArra ='' ;
												var teby = data[int];
												 for ( var int2 = 0; int2 < teby.length; int2++) {
													innerArra+=teby[int2];
													if (int2<teby.length-1) {
														innerArra+="*";
													}
												 }
												dataTData+=innerArra;
												 	if (int<data.length-1) {
														dataTData+="~";
													}
										   }										   
									      var subTData='' ;
					                      for ( var int = 0; int < subjectMasterList.length; int++) {
						                      var innerSubArra ='' ;
												var teby1 = subjectMasterList[int];
												 for ( var int2 = 0; int2 < teby1.length; int2++) {
													innerSubArra+=teby1[int2];
													if (int2<teby1.length-1) {
														innerSubArra+="*";
													}
												 }
												subTData+=innerSubArra;
												 	if (int<subjectMasterList.length-1) {
														subTData+="~";
													}
										   }	   
					                      
					                      var EE = 0;
				                           MM:for(var i = 0; i < data.length; i++) {
				                           var assignTypeID 			= i+"asign_t";
				                           var assignSubjID 			= i+"asign_s";
			                           	   var assignDescID 			= i+"asign_d";
				                           var assignFileNameID 	=	i+"asign_f"; 
												var temp = data[i];
						 						//here required subject wise split
						 						var subjectsSplit = temp[2].split(',');						 						
						 						var subjectNAem = null;						 						
						 						for ( var p = 0; p < subjectsSplit.length; p++) {//Subject wise.................................
						 							if (EE>3) {
														break MM;
													}
													if (subjectsSplit[p] != 0) {
													    for(var qq = 0; qq < subjectMasterList.length; qq++) {
															    var tempS = subjectMasterList[qq];
															    	//alert(tempS+"##tempS##"+subjectsSplit[p]);
															    if (tempS[0] == subjectsSplit[p]) {
																	subjectNAem = tempS[1];
																	EE++;
																	break;
																}															    	
															}															
													newAssignmentStr += '<tr>';
							 						
													newAssignmentStr += '<td>'+formatDate(temp[0],"d M Y")+'</td><td><label style = "color:#666666;font-weight: bold; font-size: 12px;" id ="'+assignTypeID+'" value="'+temp[1]+'">'+temp[1]+'</label ></td>';		//2										
													newAssignmentStr += '<td><label style = "color:#666666;font-weight: bold; font-size: 12px;" id ="'+assignSubjID+'" value="'+temp[2]+'">'+subjectNAem+'</label ></td>';
													newAssignmentStr += '<td><label style = "color:#666666;font-weight: bold; font-size: 12px;" id ="'+assignDescID+'" value="'+temp[3]+'">'+temp[3]+'</label >	</td>';
													newAssignmentStr += '<td><a href="#myModal_3" id= "'+i+'"  value="yuyu" style="color:#666; text-decoration:none; padding-left:26px;" data-toggle="modal" onclick="openModelWithAssignmentData(id);"><i class="icon-eye-open"></i> <input type="hidden" id='+assignFileNameID+' value="'+temp[4]+'"></a></td>';
													newAssignmentStr += '</tr> ';	
														}
												}
						 				
											}
								newAssignmentStr += '</table>'; 
					   		 newAssignmentGrid.html(newAssignmentStr);  
					   		 newAssignmentAllGrid.html('<form method="post" id="allForm1" name="allForm1" action="ViewAllListParent.action"><a href="#" id= "66"  value="yuyu" style="color:#666; text-decoration:none; padding-left:26px;" data-toggle="modal" onclick="document.allForm1.submit()"><i class="icon-eye-open"></i><input type="hidden" id="assAlldata" name="assAlldata"  value="'+dataTData+'"><input type="hidden" id="assAllSubdata" name="assAllSubdata"  value="'+subTData+'">View  All</a></form>');
					   		 
					   	   document.getElementById("assignmentDynamicTable").style.display='block';
					       document.getElementById("assignmentDynamicTableAll").style.display='block';//these r required??
					       document.getElementById("noAssignments").style.display='none';
					       }else{
					       //noNotification			notificationDynamicTable	
					       document.getElementById("assignmentDynamicTable").style.display='none';
					       document.getElementById("assignmentDynamicTableAll").style.display='none';
					       document.getElementById("noAssignments").style.display='block';
					       }
						}		
		});	
		}
		
		function openModelWithAssignmentData(vall){

		var assignTypeID 	= '#'+vall+"asign_t";//for notif body
		var assignSubjID 	= '#'+vall+"asign_s";//for notif body
		var assignDescID 		= '#'+vall+"asign_d";//for sub 
		var assignFileNameID 		= '#'+vall+"asign_f";//for sub 		
		var assignFileName 		= jQuery(assignFileNameID).val();		//text || hidden field value
		var assignDesc 			= $(assignDescID).attr('value');	//fetch lable value
			
		var assignFileWithMsg ='      to download the  assignment';
		 //Get the element that we want to append to
         var divEl = document.getElementById('assignBodyID');
        document.getElementById('assignBodyID').innerHTML = "";
        //Create the new <a>
        var aElem = document.createElement('a');
        aElem.href="downloadUploadDoc.action?type=Assignment&fileName="+assignFileName;
        //Create a text node to hold the text of the <a>
        var aElemTN = document.createTextNode('Click here');
        //Append the <a> text node to the <a> element
        aElem.appendChild(aElemTN);
        //Append the new link to the existing <div>
        divEl.appendChild(aElem);		
		$("#myModalLabelAssign").text(assignDesc); 
		$("#assignBodyID1").text(assignFileWithMsg); 
		}
		
		function utilitiesLoad(){
			AssignmentGetDWR.getUtilites("parent",function(data) {
				          if (data == null) {													
                                alert("error");						                                 
						} else {			
							if ( data.length>0) {
								var newUtilityGrid  	 = jQuery('#utilsMain');
							   	var newUtilityStr = '';				   					
				             for(var i = 0; i < data.length; i++) {
										  var temp = data[i].toString();				                        
				                           var utilID 			= i+"util_t";
				                           var utilesFileNameID 	=	i+"util_f";
				                     if (i ==0) {
											if (temp.length>0) {
											var ty = temp.split(",");
												newUtilityStr += '<a href="#myModal_2" id= "'+utilID+'" value="Exam Schedule" class="btn" data-toggle="modal" style="display:block; margin:8px auto;  text-align:left;" onclick="openModelWithUtilityData(0);">';
												newUtilityStr += '<i class="icon-calendar"></i><input type="hidden"  id="'+utilesFileNameID+'" value="'+ty[2]+'">  Exam Schedule&nbsp;&nbsp;</a>';
											}else{
												newUtilityStr += '<a href="#myModal_2" id= "'+utilID+'" value="No Exam Schedule  found" class="btn" data-toggle="modal" style="display:block; margin:8px auto;  text-align:left;" onclick="openModelWithUtilityData(0);">';
												newUtilityStr += '<i class="icon-calendar"></i><input type="hidden"  id="'+utilesFileNameID+'" value="No Exam Schedule  found">  Exam Schedule&nbsp;&nbsp;</a>';
											}										
									}else           if (i ==1) {
											if (temp.length>0) {
											var ty = temp.split(",");
												newUtilityStr += '<a href="#myModal_2" id= "'+utilID+'" value=" School Calendar" class="btn" data-toggle="modal" style="display:block; margin:8px auto;  text-align:left;" onclick="openModelWithUtilityData(1);">';
												newUtilityStr += '<i class="icon-calendar"></i><input type="hidden"  id="'+utilesFileNameID+'" value="'+ty[2]+'">  School Calendar</a>';
											}else{
												newUtilityStr += '<a href="#myModal_2" id= "'+utilID+'" value="No School Calendar  found" class="btn" data-toggle="modal" style="display:block; margin:8px auto;  text-align:left;" onclick="openModelWithUtilityData(1);">';
												newUtilityStr += '<i class="icon-calendar"></i><input type="hidden" id="'+utilesFileNameID+'" value="No School Calendar">  School Calendar</a>';
											}
									}else           if (i ==2) {
											if (temp.length>0) {
												var ty = temp.split(",");
												newUtilityStr += '<a href="#myModal_2" id= "'+utilID+'" value="Class Timetable" class="btn" data-toggle="modal" style="display:block; margin:8px auto;  text-align:left;" onclick="openModelWithUtilityData(2);">';
												newUtilityStr += '<i class="icon-time"></i><input type="hidden" id='+utilesFileNameID+' value="'+ty[2]+'"> Class Timetable</a>';
											}else{
												newUtilityStr += '<a href="#myModal_2" id= "'+utilID+'" value="No Class Timetable  found" class="btn" data-toggle="modal" style="display:block; margin:8px auto;  text-align:left;" onclick="openModelWithUtilityData(2);">';
												newUtilityStr += '<i class="icon-time"></i><input type="hidden" id='+utilesFileNameID+' value="No Class Timetable"> Class Timetable</a>';
											}										
									}else           if (i ==3) {
											if (temp.length>0) {
											var ty = temp.split(",");
												newUtilityStr += '<a href="#myModal_2" id= "'+utilID+'" value="Canteen Menu" class="btn" data-toggle="modal" style="display:block; margin:8px auto;  text-align:left;" onclick="openModelWithUtilityData(3);">';
												newUtilityStr += '<i class="icon-list"></i><input type="hidden" id='+utilesFileNameID+' value="'+ty[2]+'">  Canteen Menu</a>';
											}else{
												newUtilityStr += '<a href="#myModal_2" id= "'+utilID+'" value="No Canteen Menu  found" class="btn" data-toggle="modal" style= "display:block; margin:8px auto;  text-align:left;" onclick="openModelWithUtilityData(3);">';
												newUtilityStr += '<i class="icon-list"></i><input type="hidden" id="'+utilesFileNameID+'" value="No Canteen Menu">  Canteen Menu</a>';
											}
									}
								}				 				
							 }
							 newUtilityStr += '<a href="#myModal_2" id= "4util_t" value="Coming Soon" class="btn" data-toggle="modal" style="display:block; margin:8px auto;  text-align:left;" onclick="openModelWithUtilityData(4);"><i class="icon-eye-open"></i><input type="hidden" id="" value="No Reports">   Reports</a>';
					   		 newUtilityGrid.html(newUtilityStr);  
					       }	
		});		
		}
		
	function openModelWithUtilityData(vall){
		var utilDescID 		= '#'+vall+"util_t";//for sub 
		var utilesFileNameID 		= '#'+vall+"util_f";//for sub 
		var utilesFileName 		= jQuery(utilesFileNameID).val();		
		var utilDesc 			= $(utilDescID).attr('value');	
		var utilFileWithMsg ='      to download the  '+utilDesc;
		var fileAddr = "";
		if (utilDesc.indexOf("Exam") !== -1) {
			fileAddr = "ExamSchedule";
		}else	if (utilDesc.indexOf("Calendar") !== -1) {
			fileAddr = "Calendar";
		}else	if (utilDesc.indexOf("Class") !== -1) {
			fileAddr = "Time table";
		}else	if (utilDesc.indexOf("Canteen") !== -1) {
			fileAddr = "Canteen menu";
		}
		 //Get the element that we want to append to
         var divElUtil = document.getElementById('utilitiesBodyID');
        document.getElementById('utilitiesBodyID').innerHTML = "";
        //Create the new <a>
        var aElemUtil = document.createElement('a');
       // aElem.href="javascript:Tesssst();";
        aElemUtil.href="downloadUploadDoc.action?type="+fileAddr+"&fileName="+utilesFileName;
       // aElem.href="http://www.google.com";       
       
        //Create a text node to hold the text of the <a>
        var aElemTNUtil = document.createTextNode('Click here');
        //Append the <a> text node to the <a> element
        aElemUtil.appendChild(aElemTNUtil);
        //Append the new link to the existing <div>
       if (utilDesc.indexOf("Coming") !== -1) {
        utilFileWithMsg = "Coming Soon";
		}else if (utilDesc.indexOf("No") !== -1) {
        utilFileWithMsg = utilDesc;
		}else{
        divElUtil.appendChild(aElemUtil);
		}
		
		$("#myModalLabelUtilities").text(utilDesc); 
		//$("#assignBodyID").text(assignFileWithMsg); 
		$("#utilitiesBodyID1").text(utilFileWithMsg); 
	}
	
	function getParentsDetailsLoad(){
	//get mother and father details based on UM_ID..and .PTM_ID wil differentiate both mother(1) and father(2)
	//we have stu id also...
				 ParentDetailsDWR.getParentDetails(function(data){
				                                if (data == null) {
				                                  alert("error");					                                 
												} else {
														 for(var i = 0; i < data.length; i++) {															
															var temp = data[i];														
													    	if(temp[3]=='1'){
													    	jQuery('#fMobile').val(temp[0]);
													    	jQuery('#fMail').val(temp[1]);
													    	jQuery('#fpdid').val(temp[4]);
													    	
														    	if (temp[2] == "Y") {
														    	$('input:radio[name=optionsRadios]')[0].checked = true;														     															
																}													    	
													    	}else 	if(temp[3]=='2'){
														    	jQuery('#mMobile').val(temp[0]);
														    	jQuery('#mMail').val(temp[1]);
														    	jQuery('#mpdid').val(temp[4]);
															    	if (temp[2] == "Y") {
														    	$('input:radio[name=optionsRadios]')[1].checked = true;
																	}													    	
													    	}//others i am not handling..
													}
											}
										 }
						     		) ;
	}
	
	function saveParentsDetails(){
			var fMobile = jQuery('#fMobile').val();
			var mMobile = jQuery('#mMobile').val();
			var fMail = jQuery('#fMail').val();
			var mMail = jQuery('#mMail').val();			
			var checkReq = $('input[name=optionsRadios]:checked').val();
			
			var fpdid = jQuery('#fpdid').val();			
			var mpdid = jQuery('#mpdid').val();			
			
			var patNum			= /^[0-9-+ ]{8,15}$/;
			 var patEmail	= /^([\w\-\.]+)@((\[([0-9]{1,3}\.){3}[0-9]{1,3}\])|(([\w\-]+\.)+)([a-zA-Z]{2,4}))$/;		
			
			if(!fMobile.match(patNum)){
			   setError("fMobile","invalid input data","errorspan");
						jQuery("#fMobile").focus();
						return false;	
			}
			if(!mMobile.match(patNum)){
			   setError("mMobile","invalid input data","errorspan");
						jQuery("#mMobile").focus();
						return false;	
			}
			if(!fMail.match(patEmail)){
			   setError("fMail","invalid input data","errorspan");
						jQuery("#fMail").focus();
						return false;	
			}
			if(!mMail.match(patEmail)){
			   setError("mMail","invalid input data","errorspan");
						jQuery("#mMail").focus();
						return false;	
			}
			
			if ($('input[name=optionsRadios]:checked').length == 0) {
			    setError("optionsRadios","invalid input data","errorspan");
						jQuery("#optionsRadios").focus();
						return false;	
				}
	
	 ParentDetailsDWR.saveParentDetails(fMobile,mMobile,fMail,mMail,checkReq,fpdid,mpdid,function(data){
				                                if (data == null) {
				                                  setError("fMobile","Not Saved","errorspan");
													jQuery("#fMobile").focus();
													return false;						                                 
												} else {
													if (data == false) {
													setError("fMobile","No Changes","errorspan");
													jQuery("#fMobile").focus();
													return false;
													}
													setError("fMobile","Saved","errorspan");
													jQuery("#fMobile").focus();
													return false;	
											}
							 }
		 );
	}
	
	function sendQuickMSG(){
		// QuickMsg-PRCheck-THCheck-AdminCheck
		var QuickMsg = jQuery("#QuickMsg").val();
		var PRCheck = jQuery("#PRCheck").val();
		var THCheck = jQuery("#THCheck").val();
		var AdminCheck = jQuery("#AdminCheck").val();
		//alert(QuickMsg+"*"+PRCheck+"**"+THCheck+"***"+AdminCheck+"^^"+jQuery("#PRCheck").Checked);
		var ipaddr = '<%=request.getRemoteAddr()%>';	
			if (!$('input[name="quickCheck"]').is(':checked')) {
				setError1("AdminCheck","Please select atleast one checkbox","QuickError");
				jQuery("#AdminCheck").focus();
			    return false;
			}else if(QuickMsg.length<=3){
				setError1("QuickMsg","Please enter some text","QuickError");
				jQuery("#QuickMsg").focus();
			    return false;		
			}
			var toWhome="";//while reading from db omet last index value...i.e lenth -1			
			if ($('#PRCheck').is(':checked')) {
			 toWhome+=PRCheck+"~";
			}
			if ($('#THCheck').is(':checked')) {
			 toWhome+=THCheck+"~";
			}
			if ($('#AdminCheck').is(':checked')) {
			 toWhome+=AdminCheck+"~";
			}
			//alert(toWhome);
			SMSCreditsDWR.sentQuickSMS(toWhome,QuickMsg,ipaddr,function(msgRes){					
			if (msgRes == "Success") {
				setError1("AdminCheck","Success","QuickError");
				jQuery("#AdminCheck").focus();
				$('input[name=quickCheck]').attr('checked', false);
				jQuery("#QuickMsg").val("");				
			} else {
				setError1("AdminCheck","Failed","QuickError");
				jQuery("#AdminCheck").focus();
			}			
			});
	}
	
	function academicsLoad(){		
		AssignmentGetDWR.getAcademics("parent",function(data) {
				          if (data == null) {													
                                alert("error");						                                 
						} else {				
							if ( data.length>0) {
							var schoolID 	= "<%=session.getAttribute("SM_ID")%>";
							var branchID 	= "<%=session.getAttribute("BM_ID")%>";
							var classID 	= "<%=session.getAttribute("ClassID")%>";
								var subjectMasterList2 = subjectMasterList;
								var newAcademicsGrid  	 = jQuery('#academicsDynamicTable');
								var newAcademicsAllGrid  	 = jQuery('#academicsDynamicTableAll');
							   	var newAcademicsStr = '';     
				   						newAcademicsStr += '<table class="table table-bordered" width="100%" style="margin-bottom:0px;">';
										newAcademicsStr += '<tr><th width="18%">Subject</th><th width="44%">Description</th><th width="12%" style="text-align:center;">View</th></tr>';
					                     /* <th width="19%">Date</th> */
					                     
					                  	  var subdataList = subjectMasterList2.join('~'); 
					                      var dataTDataAC='' ;
					                      for ( var int = 0; int < data.length; int++) {
						                      var innerArra ='' ;
												var teby = data[int];
												 for ( var int2 = 0; int2 < teby.length; int2++) {
													innerArra+=teby[int2];
													if (int2<teby.length-1) {
														innerArra+="*";
													}
												 }
												dataTDataAC+=innerArra;
												 	if (int<data.length-1) {
														dataTDataAC+="~";
													}
										   }										   
									          var subTDataAC='' ;
					                      for ( var int = 0; int < subjectMasterList2.length; int++) {
						                      var innerSubArra ='' ;
												var teby1 = subjectMasterList2[int];
												 for ( var int2 = 0; int2 < teby1.length; int2++) {
													innerSubArra+=teby1[int2];
													if (int2<teby1.length-1) {
														innerSubArra+="*";
													}
												 }
												subTDataAC+=innerSubArra;
												 	if (int<subjectMasterList2.length-1) {
														subTDataAC+="~";
													}
										   }	   
					                      
					                      var EE = 0;
				                           MM:for(var i = 0; i < data.length; i++) {
				                           var academicSubjID 			= i+"academic_s";
			                           	   var academicDescID 			= i+"academic_d";
				                           var academicFileNameID 	=	i+"academic_f"; 
												var temp = data[i];
						 						//here required subject wise split
						 						var subjectsSplit = temp[1].split(',');						 						
						 						var subjectNAem = null;						 						
						 						for ( var p = 0; p < subjectsSplit.length; p++) {//Subject wise.................................
						 							if (EE>3) {
														break MM;
													}
													if (subjectsSplit[p] != 0) {
													    for(var qq = 0; qq < subjectMasterList2.length; qq++) {
															    var tempS = subjectMasterList2[qq];
															    if (tempS[0] == subjectsSplit[p]) {
																	subjectNAem = tempS[1];
																	EE++;
																	break;
																}															    	
															}															
													newAcademicsStr += '<tr>';							 						
													//newAcademicsStr += '<td>'+formatDate(temp[0],"d M Y")+'</td>';		//date of academic uploaded...																					
													//here actually not doing anything with subject name.....
													newAcademicsStr += '<td><label style = "color:#666666;font-weight: bold; font-size: 12px;" id ="'+academicSubjID+'" value="'+temp[1]+'">'+subjectNAem+'</label ></td>';
	/* 												newacademicmentStr += '<td><label style = "color:#666666;font-weight: bold; font-size: 12px;" id ="'+academicSubjID+'" value="'+temp[2]+'">'+temp[2]+'</label ></td>'; */
													newAcademicsStr += '<td><label style = "color:#666666;font-weight: bold; font-size: 12px;" id ="'+academicDescID+'" value="'+temp[2]+'">'+temp[2]+'</label >	</td>';
													newAcademicsStr += '<td><a href="#myModal_4" id= "'+i+'"  value="yuyu" style="color:#666; text-decoration:none; padding-left:20px;" data-toggle="modal" onclick="openModelWithAcademicsData(id);"><i class="icon-eye-open"></i> <input type="hidden" id='+academicFileNameID+' value="'+temp[3]+'"></a></td>';
													newAcademicsStr += '</tr> ';	
														}
													//here based on this thid get subject name from above method
												}
											}
								newAcademicsStr += '</table>'; 
					   		 newAcademicsGrid.html(newAcademicsStr);  
					   		 newAcademicsAllGrid.html('<form method="post" id="allForm3" name="allForm3" action="ViewAllListParent.action"><a href="#" id= "66"  value="yuyu" style="color:#666; text-decoration:none; padding-left:26px;" data-toggle="modal" onclick="document.allForm3.submit()"><i class="icon-eye-open"></i><input type="hidden" id="acadAlldata" name="acadAlldata"  value="'+dataTDataAC+'"><input type="hidden" id="acadAllSubdata" name="acadAllSubdata"  value="'+subTDataAC+'">View  All</a></form>');
					   		 
					   	   document.getElementById("academicsDynamicTable").style.display='block';
					       document.getElementById("academicsDynamicTableAll").style.display='block';//these r required??
					       document.getElementById("noAcademics").style.display='none';
					       }else{
					       //noNotification			notificationDynamicTable	
					       document.getElementById("academicsDynamicTable").style.display='none';
					       document.getElementById("academicsDynamicTableAll").style.display='none';
					       document.getElementById("noAcademics").style.display='block';
					       }
						}		
			});	
		}
	
	function openModelWithAcademicsData(vall){

		var academicTypeID 	= '#'+vall+"academic_t";//for notif body
		var academicSubjID 	= '#'+vall+"academic_s";//for notif body
		var academicDescID 		= '#'+vall+"academic_d";//for sub 
		var academicFileNameID 		= '#'+vall+"academic_f";//for sub 		
		var academicFileName 		= jQuery(academicFileNameID).val();		//text || hidden field value
		var academicDesc 			= $(academicDescID).attr('value');	//fetch lable value			
		var academicFileWithMsg ='      to download the  academic metirial.';
		 //Get the element that we want to append to
         var divEl = document.getElementById('academicsBodyID');
        document.getElementById('academicsBodyID').innerHTML = "";
         //divEl.text('');         
        //Create the new <a>
        var aElem = document.createElement('a');
       // aElem.href="javascript:Tesssst();";
        aElem.href="downloadUploadDoc.action?type=AcademicMaterial&fileName="+academicFileName;
       // aElem.href="http://www.google.com";
        //Create a text node to hold the text of the <a>
        var aElemTN = document.createTextNode('Click here');
        //Append the <a> text node to the <a> element
        aElem.appendChild(aElemTN);
        //Append the new link to the existing <div>
        divEl.appendChild(aElem);		
		$("#myModalLabelAcademics").text(academicDesc); 
		//$("#academicacademic").text(academicFileWithMsg); 
		$("#academicsBodyID1").text(academicFileWithMsg); 
		}
			var errorDisp1=null;
       function setError1(errElement, msg,errorplace){
			if(errorDisp1 != null){
				errorDisp1.stop(true, true).animate({opacity: 1}, 0);
				errorDisp1.hide();
			}
			var feild1 		= jQuery("#"+errElement);
				errorDisp1 	= jQuery("#"+errorplace);			
			errorDisp1.show();											
			jQuery("#"+errorplace).html(msg);
			errorDisp1.position({
				of:feild1,
				my:"left" 	+ " " + "top",
				at:"right" 	+ " " + "top",
				offset:'0 0',
				collision : 'none none'				
			});
			errorDisp1.animate({opacity: 0}, 6000);
	  }
	
	
		var errorDisp=null;
       function setError(errElement, msg,errorplace){
			if(errorDisp != null){
				errorDisp.stop(true, true).animate({opacity: 1}, 0);
				errorDisp.hide();
			}
			var feild 		= jQuery("#"+errElement);
				errorDisp 	= jQuery("#"+errorplace);			
			errorDisp.show();											
			jQuery("#"+errorplace).html(msg);
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
    
    <div class="reg_mainCon">
  <legend><img src="img/list_add_user.PNG" class="img-circle">&nbsp;&nbsp;Parents Dashboard</legend>
  <div style="padding:20px;">
    <div class="row-fluid">   
      <div class="span4">
        <div class="controls">
         <table class="table">
              <tr>
                <td style="border:none;">Student ID </td>
                 <td style="border:none;">:</td>
                <td style="border:none;"><label style="color:#000;font-weight: bold;" id="studentID"></label></td>
              </tr>
              <tr>
                <td style="border:none;">Name</td>
                <td style="border:none;">:</td>
                <td style="border:none;"><label style="color:#000;font-weight: bold;" id="studentName"></label></td>
              </tr>
              <tr>
                <td style="border:none;">Date of Birth</td>
                <td style="border:none;">:</td>
                <td style="border:none;"><label style="color:#000;font-weight: bold;" id="studentDOB"></label></td>
              </tr>
              <tr>
                <td style="border:none;">Class</td>
                <td style="border:none;">:</td>
                <td style="border:none;"><label style="color:#000;font-weight: bold;" id="studentClass"></label></label></td>
              </tr>
              <tr>
                <td style="border:none;">Joined on</td>
                <td style="border:none;">:</td>
                <td style="border:none;"><label style="color:#000;font-weight: bold;" id="studentJoinDate"></label></label> </td>
              </tr>
		  </table>

        </div>
      </div>
      <!--/span-->      
    <div class="span3"  id="photoID1" style="text-align:center; float:right;"> <img src="" id="photoID" width="176" height="180"class="img-rounded" style="border:1px #CCCCCC solid;"> </div>
    </div>
    
    <hr>
    <div class="row-fluid">						<!--Notification Start-->
      <div class="span12">      
        <div style="padding:0px 10px; border:1px #CCCCCC solid;margin-bottom:10px;">
          <h4>Messages</h4>          
          <div id="noNotification" class="alert" >No New Notification</div>
          <div id="notificationDynamicTable"></div>          
            <div id="notificationDynamicTableAll" style="text-align:right; margin-bottom:10px; margin-right:32px; margin-top:10px;">
            <a href="#" style="color:#666; text-decoration:none;" ><i class="icon-eye-open"></i> View All</a></div>
        </div>        
      </div>
      <!--/span-->
    </div>														<!--Notification End-->
    <hr>
    <div class="row-fluid">						<!--Assignment Start-->
      <div class="span12">
        <div style="padding:0px 10px; border:1px #CCCCCC solid;margin-bottom:10px;">
          <h4>Assignments</h4>          
          <div id="noAssignments" class="alert">No new Assignments</div>
           <div id="assignmentDynamicTable"></div>          
          <div  id="assignmentDynamicTableAll" style="text-align:right; margin-bottom:10px; margin-right:13px; margin-top:10px;">
          <a href="#" style="color:#666; text-decoration:none;"><i class="icon-eye-open"></i> View All</a></div>          
        </div>
      </div>
      <!--/span-->
    </div>													<!--Assignment End-->
    <hr>
    <div class="row-fluid">
      <div class="span6">
        <div style="padding:0px 10px; border:1px #CCCCCC solid;margin-bottom:10px; min-height:250px;">
          <h4 style="text-align:center;">Utilities</h4>
           
          <p style="margin-bottom:0px;">
          <div id="utilsMain"></div>
          <!-- 	<a href="#myModal_2" class="btn" data-toggle="modal" style="display:block; margin:8px auto;  text-align:left;"><i class="icon-calendar"></i> Exam Schedule&nbsp;&nbsp;</a>
          	<a href="#myModal_2" class="btn" data-toggle="modal" style="display:block; margin:5px auto;  text-align:left;"><i class="icon-calendar"></i> School Calendar</a>
            <a href="#myModal_2" class="btn" data-toggle="modal" style="display:block; margin:8px auto; text-align:left;"><i class="icon-time"></i> Class Timetable</a>
            <a href="#myModal_2" class="btn" data-toggle="modal" style="display:block; margin:8px auto;  text-align:left;"><i class="icon-list"></i> Canteen Menu</a>
            <a href="#myModal_2" class="btn" data-toggle="modal" style="display:block; margin:8px auto;  text-align:left;"><i class="icon-eye-open"></i> Reports</a>
            <br>-->
          </p> 
        </div>
      </div>
      <!--/span-->
      <div class="span6">
        <div style="padding:0px 10px; border:1px #CCCCCC solid;margin-bottom:10px; min-height:251px;">
          <h4 style="text-align:center;">Send Quick Message</h4>
          <p style="text-align: center;margin-bottom:0px"><span id ="QuickError" style="color:red;font-size: 14px;" >&nbsp;</span></p>
          <p style="margin-top:0px;"><span style="padding-top:6px; vertical-align: middle;"> TO :</span>
                <label class="checkbox inline">
                <input type="checkbox" id="AdminCheck" name="quickCheck"  value="0">Admin
                </label>
                <label class="checkbox inline">
                <input type="checkbox" id="THCheck" name="quickCheck" value="1">Class Teacher         
                </label>
                <label class="checkbox inline">
                <input type="checkbox" id="PRCheck" name="quickCheck" value="2">Principal
                </label>              
          </p>          
           <textarea style="width:95%; height:93px;" id="QuickMsg" name="QuickMsg"></textarea>
           
           <p style="text-align:center;"><button class="btn btn-warning" type="button" onclick="sendQuickMSG();return false;">Send</button> </p>          
        </div>
      </div>
      <!--/span-->
    </div>
    <hr>
    <div class="row-fluid">
      <div class="span6">
        <div style="padding:0px 10px; border:1px #CCCCCC solid;margin-bottom:10px;min-height: 250px;">
          <h4>Edit Details</h4>
          <form class="form-inline">
          <p><span id ="errorspan" style="color:red;font-size: 14px;" >&nbsp;</span></p>
            <p>
              <label style="width:102px;">Fathers Mobile</label>
              <input type="text" class="input-small" placeholder="" id="fMobile">
              <input type="hidden"  id="fpdid">
              <label class="radio">
  				<input type="radio" name="optionsRadios"   id="fMobileRadio"  value="1" >Default
              </label>
            </p>
                 <p>
              <label style="width:102px;">Fathers Mail</label>
              <input type="text" class="input-small" placeholder="" id="fMail">
          <!--     <label class="radio">
  				<input type="radio" name="optionsRadios1"  id="fMailRadio"  value="option3" >Default
              </label> -->
            </p>
            <p>
              <label style="width:102px;">Mothers Mobile</label>
              <input type="text" class="input-small" placeholder="" id="mMobile" >
              <input type="hidden"  id="mpdid">
              <label class="radio">
  				<input type="radio" name="optionsRadios" id="mMobileRadio" value="2" checked>Default
              </label>
            </p>
       
            <p>
              <label style="width:102px;">Mothers Mail</label>
              <input type="text" class="input-small" placeholder=""  id="mMail">
            <!--   <label class="radio">
  				<input type="radio" name="optionsRadios1"  id="mMailRadio" value="option4" checked>Default
              </label> -->
            </p>
             <p style="text-align:center;"><button class="btn btn-warning" type="button" onclick="return saveParentsDetails();">Save</button> </p> 
          </form>
        </div>
      </div>
      <!--/span-->
      <div class="span6">
        <div style="padding:0px 10px; border:1px #CCCCCC solid;margin-bottom:10px;min-height: 279px;">
          <h4>Academics</h4>          
          <div id="noAcademics" class="alert">No new Assignments</div>
           <div id="academicsDynamicTable"></div>          
          <div  id="academicsDynamicTableAll" style="text-align:right; margin-bottom:10px; margin-right:13px; margin-top:10px;">
          <a href="#" style="color:#666; text-decoration:none;"><i class="icon-eye-open"></i> View All</a></div>          
        </div>
      </div>
      <!--/span-->
          
        </div>
      </div>
      <!--/span-->
    </div>
  </div>
</div>
<div style="height:20px;"></div>
<!-- /container -->
<div id="footer">
  <div class="container">
    <p class="muted credit" style="text-align:center;">© 2013 SchoolTrix, All Rights reserved</p>
  </div>
</div>

<!-- Common Modal -->
<div id="myModal" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
  <div class="modal-header">
    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
    <h3 id="myModalLabel">Modal header</h3>
  </div>
  <div class="modal-body">
    <p>One fine body...</p>
  </div>
  <div class="modal-footer">
    <button class="btn" data-dismiss="modal" aria-hidden="true">Close</button>
    <button class="btn btn-warning">Save changes</button>
  </div>
</div>
<!-- Common Modal End-->

<!--  myModal_img -->
<div id="myModal_img" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
  <div class="modal-header">sdsdsdsd444
    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
   </div>
  <div class="modal-body">sdsdsd
   
  </div>
</div>
<!--  myModal_img End-->

	
	<!-- Notification Modal-1 -->
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
	<!-- Notification Modal-1 End-->
	
	
	<!-- AssignMenet Modal-1 -->
	<div id="myModal_3" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	  <div class="modal-header">
	    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
	    <h3 id="myModalLabelAssign">Holiday Homework</h3>
	  </div>
	  <div class="modal-body">
		<p id="assignBodyID" style="display:inline"></p>&nbsp;&nbsp;<p id="assignBodyID1" style="display:inline"></p>
	<!-- 	<p id="assignBodyID"><a href="#">Click here</a> to download the Holiday Home work for Summer Vacation 2013</p> -->
	  </div>
	  <div class="modal-footer">
	    <button class="btn" data-dismiss="modal" aria-hidden="true">Close</button>
	  </div>
	</div>
	<!-- AssignMenet Modal-1 End-->
	
	
	<!-- Utilities Modal-1 -->
	<div id="myModal_2" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	  <div class="modal-header">
	    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
	    <h3 id="myModalLabelUtilities">Holiday Homework</h3>
	  </div>
	  <div class="modal-body">
		<p id="utilitiesBodyID" style="display:inline"></p>&nbsp;&nbsp;<p id="utilitiesBodyID1" style="display:inline"></p>
	<!-- 	<p id="assignBodyID"><a href="#">Click here</a> to download the Holiday Home work for Summer Vacation 2013</p> -->
	  </div>
	  <div class="modal-footer">
	    <button class="btn" data-dismiss="modal" aria-hidden="true">Close</button>
	  </div>
	</div>
	<!-- Utilities Modal-1 End-->


	<!-- Academic Modal-4 -->
	<div id="myModal_4" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	  <div class="modal-header">
	    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
	    <h3 id="myModalLabelAcademics">Holiday Homework</h3>
	  </div>
	  <div class="modal-body">
		<p id="academicsBodyID" style="display:inline"></p>&nbsp;&nbsp;<p id="academicsBodyID1" style="display:inline"></p>
	<!-- 	<p id="assignBodyID"><a href="#">Click here</a> to download the Holiday Home work for Summer Vacation 2013</p> -->
	  </div>
	  <div class="modal-footer">
	    <button class="btn" data-dismiss="modal" aria-hidden="true">Close</button>
	  </div>
	</div>
	<!-- Academic Modal-4 End-->



<!-- Notification Modal-5 -->
<div id="myModal_5" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
  <div class="modal-header">
    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
    <h3 id="myModalLabel">Weekend Assignment - 15th February</h3>
  </div>
  <div class="modal-body">
    <p><a href="#">Click here</a> to download the week end assignment for 15th February</p>
  </div>
  <div class="modal-footer">
    <button class="btn" data-dismiss="modal" aria-hidden="true">Close</button>
  </div>
</div>
<!-- Notification Modal-4 End-->
    
        <div id="idAlertDialog" class="modal hide fade" style="height: 200px;" tabindex="-1" role="dialog" aria-labelledby="windowTitleLabel" aria-hidden="true">
            <img src="img/alert.gif" width="34" height="28" alt="alert icon">
            <h3 id="idAlertDialogPrompt"></h3>
            <a href="#" class="btn btn-primary" style="margin-top: 0px;" onclick="okAlertDialog ();">OK</a>
            </div>     
            <input type="hidden" id='subText' name='subText'/>
     <script type="text/javascript">
       		
	function showHideMsgNew(message,container){

		var top			= 10;
		
		if( jQuery.browser.msie == true){

			top		= document.body.scrollTop+3;

		}else{

			top	 	= window.pageYOffset + 3;
		}
		
		if(message !='' && message !=null && message !='undefined' && message !=undefined ){

			jQuery("#msgPane").stop(true,true).css('top',top).html(message).fadeIn('slow').delay(3000).fadeOut('slow');
		}else{	
			jQuery("#msgPane").fadeOut();	
		}
	
	}
	
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
		
		</script>