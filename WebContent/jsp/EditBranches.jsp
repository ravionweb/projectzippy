<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

    <%
    String searchHistoryQu =(String) session.getAttribute("queryForBranchSearch");
    String searchHistoryJSON =(String) session.getAttribute("jsonForBranchSearch");
    System.out.println("searchHistoryQu**"+searchHistoryQu);
    System.out.println("searchHistoryJSON**"+searchHistoryJSON);
    if(searchHistoryQu != "" && searchHistoryQu != null && searchHistoryQu != "null" ){
    session.removeAttribute("queryForBranchSearch");    
    }
    if(searchHistoryJSON != "" && searchHistoryJSON != null && searchHistoryJSON != "null" ){
    session.removeAttribute("jsonForBranchSearch");    
    }
    
     %>

  <style  type="text/css">
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

 			#idConfirmDialogSearch {
				width: 450px;
				height: 165px;
				background-color: whitesmoke;
				}
			 #idConfirmDialogSearch .btn {
				width: 70px;
				margin-bottom: 8px;
				margin-right: 15px;
				margin-top: 80px;
				float: right;
				}
			 #idConfirmDialogSearch h3 {
				margin-left: 60px;
				margin-top:15px;
				}
			 #idConfirmDialogSearch img {
				float: left;
				margin-left: 15px;
				margin-top: 15px;
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
		
		#cssTable td 
		{
		    text-align:center; 
		    vertical-align:middle;
		}
			#cssTable th 
		{
		    text-align:center; 
		    vertical-align:middle;
		    color:black;
		}
</style>

	<script src="js/bootstrap-multiselect.js" type="text/javascript"></script>
	<script 	src="<%=request.getContextPath()%>/dwr/interface/SchoolMasterDWR.js"></script>
	 <script 	src="<%=request.getContextPath()%>/dwr/interface/BranchMasterDWR.js"></script>
	<script>
	
	   var searchQueryForHis = "<%=searchHistoryQu%>";
        var searchJSONForHis = '<%=searchHistoryJSON%>';        
        var searchHisOnloadJson = '';        
        
        function closeConfirmDialog () {
			$("#idConfirmDialog").modal ('hide'); 
			};
		function okConfirmDialog () {
			$("#idConfirmDialog").modal ('hide'); 
			disableSchoolAfterConfirm();
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
	   		
				schoolMasterList();//only school master load
				onloadBranchListPreSelectionJson();//remaining slection loading
        
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
					buttonWidth: '241px',
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
				
				$('#branchShortName').multiselect({
						buttonText: function(options, select) {
							if (options.length == 0) {
								return 'Select Branch Short Name <b class="caret"></b>';
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
					filterPlaceholder: 'Search Branch Short Name'
				});
				
				$('#city').multiselect({
						buttonText: function(options, select) {
							if (options.length == 0) {
								return 'Select City <b class="caret"></b>';
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
					filterPlaceholder: 'Search City'
				});
		
				$('#state').multiselect({
						buttonText: function(options, select) {
							if (options.length == 0) {
								return 'Select State <b class="caret"></b>';
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
					buttonWidth: '270px',
					filterPlaceholder: 'Search State'
				});
				
		 jQuery("#schoolNames").multiselect('deselect', "0");
				searchHistoryLoad();
	 });//onready end
		 
	function schoolMasterList(){
	    
		   try{
						var listofschools = document.getElementById('schoolNames');					
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
															    $('#schoolNames').append(innerHtml);																	    
															}
															$('#schoolNames').multiselect('rebuild');		                          
														}
						                         }
						      ) ;  						
		   		 }catch(e){
						 alert("incatch::"+e);
		         }
	}
		 
	function selectSchoolforBranches(){
	   var schoolID = jQuery("#schoolNames").val();
	/* 	if(schoolID == null || schoolID == ""){
			setError("schoolNames","Please select School");
			 jQuery("#schoolNames").focus();
			return false;
		} */
		removeAllOptions("#branchNames");
		removeAllOptions("#branchShortName");
		removeAllOptions("#state");
		removeAllOptions("#city");
	onloadBranchListPreSelectionJson();
	 }
	 
	 function onloadBranchListPreSelectionJson(){
	    var schoolID = jQuery("#schoolNames").val();	 
	 	   try{
	
		    BranchMasterDWR.getMultiBranchMasterListDetailedJSON(schoolID,function(data){
			                                if (data == null) {
			                                  alert("error");
											} else {
														
					   var branchdeatiledforAllFileds = jQuery.parseJSON(data);
							for(var event2 in branchdeatiledforAllFileds){
							//event2--->citys,shortNames,schools,states
							  if (event2 == "branches") {
									 var branches = branchdeatiledforAllFileds[event2];
											 for(key2 in branches){
																																//  alert(key2+"2444222*"+schools[key2]);//"3" : "ABODE Kids International",
										    	var opt = document.createElement("option");
											    var  innerHtml= "<option value="+key2+">"+branches[key2]+"</option>)";															    
											    $('#branchNames').append(innerHtml);
											  }
												$('#branchNames').multiselect('rebuild');		
							   }
							   if (event2 == "branchShortNames") {
									 var shortNames = branchdeatiledforAllFileds[event2];
											 for(key2 in shortNames){
																																//  alert(key2+"2444222*"+schools[key2]);//"3" : "ABODE Kids International",
										    	var opt = document.createElement("option");
											    var  innerHtml= "<option value="+key2+">"+shortNames[key2]+"</option>)";															    
											    $('#branchShortName').append(innerHtml);
											  }
												$('#branchShortName').multiselect('rebuild');		
							   }
							   if (event2 == "states") {
									 var states = branchdeatiledforAllFileds[event2];
											 for(key2 in states){
																																//  alert(key2+"2444222*"+schools[key2]);//"3" : "ABODE Kids International",
										    	var opt = document.createElement("option");
											    var  innerHtml= "<option value="+key2+">"+states[key2]+"</option>)";															    
											    $('#state').append(innerHtml);
											  }
												$('#state').multiselect('rebuild');		
							  }
							  if (event2 == "citys") {
									 var citys = branchdeatiledforAllFileds[event2];
											 for(key2 in citys){
																																//  alert(key2+"2444222*"+schools[key2]);//"3" : "ABODE Kids International",
										    	var opt = document.createElement("option");
											    var  innerHtml= "<option value="+key2+">"+citys[key2]+"</option>)";															    
											    $('#city').append(innerHtml);
											  }
												$('#city').multiselect('rebuild');		
							}
						}//main for
							
					// jQuery("#schoolNames").multiselect('deselect', "0");
				  }//else
			}) ;
			
   		 }catch(e){
				 alert("incatch::"+e);
         } 	
	 
	 }
	
	
	function getBranchList(){
		 var schoolNames  	 = jQuery('#schoolNames').val();
		 var branchNames  	 = jQuery('#branchNames').val();
		 var shortName  	 	= jQuery('#branchShortName').val();
		 var state 	 	 			= jQuery('#state').val();
		 var city 	 	 				= jQuery('#city').val();
		 
		 var addr 	 	 			= jQuery('#addr').val();
		 var contactPerson 	= jQuery('#contactPerson').val();
		 var mobile 	 	 		= jQuery('#mobile').val();
		 var landline 	 	 		= jQuery('#landline').val();
		 var email 	 	 			= jQuery('#email').val();
		 
		 var  active;
		 if ($('#active').is(":checked")){
		 	active='Y';
		} else{
			active='N';
	    }
		BranchMasterDWR.getBranchMasterDisplay(
										schoolNames, branchNames, shortName, state , city, 
													addr, contactPerson , mobile, landline ,
																	email,active,	function(data){
				//0			1			2						3			4			5				6			7							8					9			10		11														
			// SM_ID,IM_ID,School_Name,Name,Address,City,State_ID,Contact_Person,Email_ID,Mobile,Landline,Active															
					//	alert(data);
						if(data == null){
							alert("error");
						}else{
						getBranchMasterDisplayPart(data);
						}
	
	});
	}
	
	function getBranchMasterDisplayPart(data){
	
						var idForDisplay =jQuery('#branchDynamicGrid');
						var stringForm ='';
					
						// console.log("data**"+data);
		if(data.length>0){
						
			var uploadedDocJson = jQuery.parseJSON(data);
			if(uploadedDocJson != null && uploadedDocJson.length > 0){
	 			var mainJSONOBJ = '';
	 			var stringForm = '';
	 			
 				 var  activeString;
					 if ($('#active').is(":checked")){
						activeString='Disable';
					} else{
					 	activeString='Enable';
				    }
	 			
			     for(var i = 0; i < uploadedDocJson.length; i++) {//school Obj's
			                                   var uploadID 			= i+"uploadID";
		  
			      	var schoolWise = uploadedDocJson[i];
					var one = 10;
					var OneMain ='';
					  
					  for(var subKKey in uploadedDocJson[i]){//ABODE Kids International, value: [object Object]
					 	
							stringForm += '<div><hr style="border-style: none;height: 0;"/></div><div style="   background-color: #FFFFFF; border: 1px solid #DDDDDD; border-radius: 6px 6px 6px 6px; color: #9DA0A4; font-size: 12px; font-weight: bold; left: -1px; margin: 0 auto; max-width: 98%; padding: 3px 3px;">';
							stringForm += ' <div style="padding:10px 20px 20px 20px;">';
							stringForm += ' <fieldset><legend><div  width="100%" style="margin-bottom:0px;color:black;">'+subKKey+'</div></legend>';
	
							stringForm += '<div><hr style="border-style: none;height: 0;"/></div><table class="table table-bordered" id="cssTable" width="100%" style="margin-bottom:0px;">';
							stringForm += '<tr><th style="width:13%;">Branch Short Name</th><th style="width:13%;">Branch Name</th><th style="width:13%;">Address</th>';
							stringForm += '<th style="width:7%;">City</th><th style="width:6%;">State</th><th style="width:10%;">Contact Person</th><th style="width:11%;">Email Id</th>';
							stringForm += '<th style="width:7%;">Mobile</th><th style="width:7%;">Landline</th><th style="width:5%;">Active</th>';
							stringForm += '<th style="width:5%;">Edit</th><th style="width:5%;">'+activeString+'</th><tr>';
							
					   		for(var subKKey2 in uploadedDocJson[i][subKKey]){//0, value: [object Object]		
					   	
					   			for(var subKKey3 in uploadedDocJson[i][subKKey][subKKey2]){//AKI Compass, value: [object Object],[obje
									
									  for(var subKey4 in uploadedDocJson[i][subKKey][subKKey2][subKKey3]){
									  	var  totaljson=uploadedDocJson[i][subKKey][subKKey2][subKKey3][subKey4];
					
									stringForm += '<tr style="">';					                              	
					       			stringForm += '<td>'+totaljson.Short_Name+'</td><td>'+totaljson.Branch_Name+'</td><td>'+totaljson.Address+'</td><td>'+totaljson.City+'</td><td>'+totaljson.State_name+'</td>';
					       			stringForm += '<td>'+totaljson.Contact_Person+'</td><td>'+totaljson.Email_ID+'</td><td>'+totaljson.Mobile+'</td><td>'+totaljson.Landline+'</td><td>'+(totaljson.Active=="Y"?"Yes":"No")+'</td>';
					       			stringForm += '	<td><a href="javascript:void(0)" id="'+i+'"  value="'+totaljson.bm_ID+'" onclick="editBranchCall(id,\''+totaljson.bm_ID+'\');"><i class="icon-pencil"></i></a> </td><td  width="5%"  style=""><a href="#" id="'+i+'"  value="'+totaljson.bm_ID+'" onclick="disableBranchCall(id);" ><i class="icon-trash"></i> </a></td>';
			
				       				stringForm += '</tr>';
					       			}
							}
						}
							stringForm += '</table>';
									 
										stringForm += '</fieldset></div></div>';     
					}//main for loop
	 			}
		}else{
 				stringForm += '<table class="table table-bordered" width="100%" style="margin-bottom:0px;"><tr><td style="color:red;font-size:16px;font-weight:bold;text-align: center;background-color:silver ">No records found.</td></tr></table>';
 			}
 			idForDisplay.html(stringForm);
		}
	}
	
	
	var win2;
	function editBranchCall(val3,idBD){
		var valTOID = "#"+val3;
		 var upIDForID 			= $(valTOID).attr('value');
		 win2 = window.open("Access.action?p1=branchMaster&p2=forEdit&p3="+idBD, "Schooltrix", "height=580,width=870,scrollbars=1,location=no,menubar=no,resizable=0,status=no,toolbar=no,screenX=100,screenY=100");
		win2.onbeforeunload = gridRefreshAfterEditOpen;		
	}
	function gridRefreshAfterEditOpen() {
		jQuery('#branchDynamicGrid').html("");
		getBranchList();//refresh if any changes in edit--grid will update
	}
	
	var branchIDV = '';
	function disableBranchCall(val2){
		var valTOID = "#"+val2;
		 branchIDV 			= $(valTOID).attr('value');
		  if ($('#active').is(":checked")){
		confirmDialog ('Are you sure, you want to disable this branch?', alert);
						} else{
		confirmDialog ('Are you sure, you want to enable this branch?', alert);
					    }
		 
	}
	
		function disableSchoolAfterConfirm(){
			var active ="";
			 if ($('#active').is(":checked")){
							active='N';
						} else{
						 	active='Y';
					    }//Disable
			BranchMasterDWR.disableEnableBranchMaster(branchIDV,active,function(data){
																if (data == null) {
																	setError("schoolNames","Error");
																	 jQuery("#schoolNames").focus();
																} else {
																	//for refreshment of result--test
																	setError("schoolNames","Success");
																	 jQuery("#schoolNames").focus();
																	 gridRefreshAfterEditOpen();
																}		
		
		});			
	} 
	
	 function downloadBranchCSV(){
		      document.schoolSearch.action = 'downloadBranchSearch.action';
		      document.schoolSearch.method = 'post';
		   	  document.schoolSearch.submit(); 
		 
		 }
		 
   function showTogglerBody(){//pass id ..if u want more ....
	        	    jQuery("#toggleeee").toggle();
	        	    jQuery(jQuery("#SHIcon").children()[0]).toggleClass("open close"); 
      }
      
      	
	function saveBranchSearchHis(){
		var searchDesc = jQuery("#searchDesc").val();	
		var cssTable = jQuery('#cssTable').val();
		
		if (cssTable == undefined ||cssTable == "undefined" ) {
			setError("searchID","Search data before you save");
			jQuery("#searchID").focus();
			return false;
		}

		if (searchDesc !=null &&searchDesc != '' && searchDesc.length>2) {	
		
					BranchMasterDWR.saveBranchSearch(searchQueryForHis,searchDesc, searchJSONForHis,function(data){
									if (data == "nosearch") {
										jQuery('#branchDynamicGrid').html("");
										setError("searchID","Search data before you save");
										 jQuery("#searchID").focus();
										 														
									}else if (data == "descthere") {
										jQuery('#branchDynamicGrid').html("");
										setError("searchID","Search description already there");
										 jQuery("#searchID").focus();																
									}else	if (data == "success") {
										jQuery('#branchDynamicGrid').html("");
										setError("searchID"," Successfully saved.");
										 jQuery("#searchID").focus();
										 resetFormHelp();
										 searchHistoryLoad();//***** After Saving ...it will reflect on Search history selection box..
									} else if (data == "already") {
										jQuery('#branchDynamicGrid').html("");
											setError("searchID","Search is already there");
											 jQuery("#searchID").focus();
									} else {
										jQuery('#branchDynamicGrid').html("");
											setError("searchID","Not Saved");
											 jQuery("#searchID").focus();
									}			
				});			
		}else{
				setError("searchDesc","Please enter a name to save the search");
				jQuery("#searchDesc").focus();
				return false;
		}		
	}
	function searchHistoryLoad(){
		var searchHisID =	 document.getElementById('searchHistory');	
		removeAllOptionsOne("searchHistory");
			BranchMasterDWR.searchHistoryLoadCall(function(data){
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
			BranchMasterDWR.deleteBranchSearchHistoryDoc(searchHisID,function(data){
					if (data == null) {
						setError("searchHistory","Error During delete..");
						 jQuery("#searchHistory").focus();
					} else {
						//for refreshment of result--test
							setError("searchHistory","Successfully Deleted");
							 jQuery("#searchHistory").focus();
  						jQuery('#branchDynamicGrid').html("");
							resetFormHelp();
							searchHistoryLoad();
					}		
			});			
			
		}else{
			setError("searchHistory", "Please select any search history option");
			return false;
		}
	
	}
	
	var searchJSON = '';
	var descForSearchDisp = '';
	
	function  displayBranchSearchHistoryLoadByID() {
		
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
				schoolMasterList();//only school master load
				onloadBranchListPreSelectionJson();//remaining slection loading
        
				searchJSON = jQuery.parseJSON(jsonForSearchDisp);
		
				setTimeout(renderDataForm,400);
				
	 			BranchMasterDWR.searchHistoryLoadByIDDisplay(queryForSearch , function(data){
					if (data == null) {
						alert('error in displaySearchHistoryLoadByID ');
					} else {
						getBranchMasterDisplayPart(data);
					}
				});
		}else{
		resetForm();
		schoolMasterList();
		}
	}
	
	function  renderDataForm() {
		var schoolSplit = searchJSON.sm_ids.split(',');
		var branchSplit = searchJSON.bm_ids.split(',');
		var shortSplit = searchJSON.bn_ids.split(',');
		var stateSplit = searchJSON.stateIds.split(',');
		var citySplit = searchJSON.cityIds.split(',');
		var active = searchJSON.active;
		if (active == 'y') {
			$('#active').prop('checked', true);
		} else {
			$('#active').prop('checked', false);
		}
		
		
			 jQuery("#addr").val(searchJSON.addr);
			 jQuery("#contactPerson").val(searchJSON.contactPerson);
			 jQuery("#mobile").val(searchJSON.mobile);
			 jQuery("#landline").val(searchJSON.landline);
			 jQuery("#email").val(searchJSON.email);
			 
			 jQuery("#searchDesc").val(descForSearchDisp);
	
		
		for ( var int = 0; int < schoolSplit.length; int++) {
			var schoolListArray_element = schoolSplit[int];
			if (schoolListArray_element.indexOf('%') != -1) {
			}else{
			 jQuery("#schoolNames").multiselect('select', schoolListArray_element);
			 }
		}
		//here req setTimeout based school selection load main json for remaindata rendring after that selection of dt data...
				for ( var int = 0; int < branchSplit.length; int++) {
			var branchListArray_element = branchSplit[int];
			if (branchListArray_element.indexOf('%') != -1) {
			}else{
			 jQuery("#branchNames").multiselect('select', branchListArray_element);
			 }
		}
		
		for ( var int = 0; int < shortSplit.length; int++) {
			var shortListArray_element = shortSplit[int];
			if (shortListArray_element.indexOf('%') != -1) {
			}else{
			 jQuery("#branchShortName").multiselect('select', shortListArray_element);
			 }
		}
		for ( var int = 0; int < stateSplit.length; int++) {
			var stateListArray_element = stateSplit[int];
			if (stateListArray_element .indexOf('%') != -1) {
			}else{
			 jQuery("#state").multiselect('select', stateListArray_element );
			 }
		}
		for ( var int = 0; int < citySplit.length; int++) {
			var cityListArray_element = citySplit[int];
			if (cityListArray_element .indexOf('%') != -1) {
			}else{
			 jQuery("#city").multiselect('select', cityListArray_element );
			 }
		}
	}
	
		function removeAllOptions(whichOption)
		{
					$(whichOption).html('');
					var  innerHtml= "";
					  innerHtml= "<option value='0'>Select All </option>";
				    $(whichOption).append(innerHtml);
				    $(whichOption).multiselect('rebuild');	
		}
		
	 function removeAllOptionsOne(whichOption){
		var listofObjects =  document.getElementById(whichOption);
		var i;
			for(i=listofObjects.options.length-1;i>=0;i--)
			{
			listofObjects.remove(i);
			}
			if (whichOption == "selectSubject") {
				    var opt1 = document.createElement("option");
				    opt1.value = "0";
				    opt1.innerHTML = "All";
				    listofObjects.appendChild(opt1);
			}
			if (whichOption == "searchHistory") {
			 var opt = document.createElement("option");
		    opt.value = "-1";
		    opt.innerHTML = "Select";
		    listofObjects.appendChild(opt); 
		    }		
	}
	
	 function resetForm() {	
	 	 var schoolNames  	 = jQuery('#schoolNames').val();
	 	 var branchNames  	 = jQuery('#branchNames').val();
		 var branchShortName  	 	= jQuery('#branchShortName').val();
		 var state 	 	 			= jQuery('#state').val();
		 var city 	 	 				= jQuery('#city').val();
		 
	       	removeAllOptions("#schoolNames");
	       	removeAllOptions("#branchNames");
			removeAllOptions("#branchShortName");
			removeAllOptions("#state");
			removeAllOptions("#city");

			 jQuery("#schoolNames").val('Select School');
			 jQuery("#branchNames").val('Select Branch');
			 jQuery("#branchShortName").val('Select Branch Short Name');
			 jQuery("#state").val('Select State');
			 jQuery("#city").val('Select City');
	       jQuery("#addr").val('');
	       jQuery("#contactPerson").val('');
	       jQuery("#mobile").val('');
	       jQuery("#landline").val('');
	       jQuery("#email").val('');

	       jQuery("#searchDesc").val('');
	       
	       $('#active').prop('checked', true);
	        jQuery('#branchDynamicGrid').html("");		 

	}
	
	function resetFormHelp(){
		resetForm();
		jQuery('#searchHistory').val('-1').attr('selected',true);
		schoolMasterList();//only school master load
		onloadBranchListPreSelectionJson();//remaining slection loading
        
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
	
		function editSSP(){ 
			 document.location = "Access.action?p1=branchMaster&p2=ffats";		
		}	
	    
	
	</script>

<form method="post" name="schoolSearch" id = "schoolSearch" enctype="multipart/form-data" >
<div style="height:25px;text-align: center;"><span id ="errorspan" style="color:red;font-size: 14px;font-weight: bold;" >&nbsp;</span></div>
<div class="reg_mainCon">
    <fieldset>
    <legend style="padding: 0px;"><img src="img/list_add_user.PNG" class="img-circle">&nbsp;&nbsp;View Branches
      <div id="SHIcon" class="TogglerHader" style="display: inline-block;padding-top: 6px;	float: right;"><div class="open" onclick="showTogglerBody(); return false;">
      <a href="#SHIcon">Show | Hide</a></div></div>
      </legend>
    <div id="toggleeee" style="padding:20px;padding-top: 2px;" >
   <table width="100%" border="0" cellspacing="0" cellpadding="0">
 	 <tr>
   		<td><label style="color: #000;text-align: left ;">Saved Search&nbsp;&nbsp;&nbsp;</label></td>
   		<td ><select class="span3" style="width: 82%" name="searchHistory" id="searchHistory" onchange="displayBranchSearchHistoryLoadByID();">
   		<option value="-1">Select</option>
   		</select> <a class="btn btn-warning" style="margin-bottom: 10px;text-align: center;height: 18px;width: 4%" href="#" onclick="deleteSearchHistory();return false;"><i class=" icon-trash"></i></a>
   		</td>	
   		<td>&nbsp;</td>	
   	    <td colspan="2" style="float: right;height: 20px;">
	      	<input type="button" class="btn btn-warning"  style="height: 30px;width: 150px;" id='' value="Add Branch" onclick="editSSP()">
	    </td>
   		
  	 </tr>
      <tr>
      	     <td><label style="color:#000;"><b>School Name </b></label></td>
      		 <td>
      		       <div class="input-append">  
		  <select class="multiselect" multiple="multiple"  id="schoolNames" name="schoolNames">			
			<option value="0" >Select All </option>
		  </select>
			<button class="btn " type="button" style="width:30px;padding: 4px; "  id="schoolNamesGo" onclick="return selectSchoolforBranches();"  name="schoolNamesGo" >Go!</button> <!--onblur="return selectSchoolforBranches();" onclick="return selectSchoolforBranches();"  -->
		 </div>
		 </td>
     </tr>
           <tr>
      	     <td><label style="color:#000;"><b>Branch Name </b></label></td>
      		 <td>
      		    <div class="input-append">  
		  			<select class="multiselect" multiple="multiple"  id="branchNames" name="branchNames">	
						<option value="0" >Select All </option>
		  			</select>
		 		</div>
			</td>
          <td><label style="color: #000;padding-left: 10px;"><b> Branch Short Name </b></label></td>
          <td>
		  	<div class="input-append">  
		  		<select class="multiselect" multiple="multiple"  id="branchShortName" name="branchShortName">		
					  <option value="0" >Select All </option>	
				</select>
			</div>
		</td>
     </tr>
     <tr>
     	  <td><label style="color: #000;"><b> State</b></label></td>
          <td>
  			<div class="input-append">  
		  		<select class="multiselect" multiple="multiple"  id="state" name="state">		
					  <option value="0" >Select All </option>	
				</select>
		  </div>
	  	 </td>
   	     <td><label style="color:#000;padding-left: 10px;"><b>City</b></label></td>
   		 <td>
   		    <div class="input-append">  
	 			<select class="multiselect" multiple="multiple"  id="city" name="city">			
					<option value="0" >Select All </option>
	 			</select>
			</div>
		</td>
  
     </tr>
     <tr>
     	<td><label style="color:#000;"><b>Address  </b></label></td>
        <td><input type="text" class="span3" style="width: 255px;" name="addr" id="addr" ></td>
     	<td><label style="color:#000;padding-left: 10px;"><b> Contact Person </b></label></td>
        <td><input type="text" class="span3" style="width: 255px;" name="contactPerson" id="contactPerson" ></td>
     </tr>
     <tr>
     	<td><label style="color:#000;"><b>Mobile  </b></label></td>
        <td><input type="text" class="span3" style="width: 255px;" name="mobile" id="mobile" ></td>
     	<td><label style="color:#000;padding-left: 10px;"><b>Landline</b></label></td>
        <td><input type="text" class="span3" style="width: 255px;" name="landline" id="landline" ></td>
     </tr>
     <tr>
     	<td><label style="color:#000"><b>Email Id  </b></label></td>
        <td><input type="text" class="span3" style="width: 255px;" name="email" id="email" ></td>
     	<td><label style="color:#000;padding-left: 10px;"><b>Active</b></label></td>
        <td><input type="checkbox"  class="span3" name="active" id="active" checked="checked"></td>
     </tr><tr><td>&nbsp;</td></tr>
       <tr>
          <td>&nbsp;</td>
            <td colspan="2"><div style="display: inline-block;padding-left: 200px;">
          <button type="button" id ="searchID" name="searchID"  style="margin-left: 20px;" class="btn btn-warning" onclick="getBranchList();return false;">Search</button>  
           <button type="button" id ="resetID" name="resetID"  style="margin-left: 10px;"  class="btn btn-warning" onclick="resetFormHelp();return false;">Reset</button> 
          </div>
          </td>
          <td width="1%">&nbsp;</td>
        </tr>       
        <tr><td>&nbsp;</td></tr> 
       <tr><td><label style="color:#000;width: 103%;"><b>Save search as</b></label></td>
        <td><input style='width:73%;margin-left: 10px;' type="text" class="span3" name="searchDesc" id="searchDesc" >
        		<a class="btn btn-warning" style="margin-bottom: 10px;text-align: center;height: 18px;width: 5%" href="#" onclick="saveBranchSearchHis();return false;"><i class="icon-ok-circle"></i></a>
        </td><td>&nbsp;</td>
        <td style="text-align: right;">&nbsp;<a href="#" onclick="downloadBranchCSV();" style="color: #000;">Download Result as CSV</a>&nbsp;</td>
        </tr>
        
   </table> 
  </div>
 </fieldset>
</div>   
</form>
					<!-- result display ID -->
<div id="branchDynamicGrid" style="padding: 10px;"></div>
    
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
			