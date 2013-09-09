<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

    <%
    String searchHistoryQu =(String) session.getAttribute("queryForSchoolSearch");
    String searchHistoryJSON =(String) session.getAttribute("jsonForSchoolSearch");
    System.out.println("searchHistoryQu**"+searchHistoryQu);
    System.out.println("searchHistoryJSON**"+searchHistoryJSON);
    if(searchHistoryQu != "" && searchHistoryQu != null && searchHistoryQu != "null" ){
    session.removeAttribute("queryForSchoolSearch");    
    }
    if(searchHistoryJSON != "" && searchHistoryJSON != null && searchHistoryJSON != "null" ){
    session.removeAttribute("jsonForSchoolSearch");    
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
		}
</style>

	<script src="js/bootstrap-multiselect.js" type="text/javascript"></script>
	<script 	src="<%=request.getContextPath()%>/dwr/interface/SchoolMasterDWR.js"></script>
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
				schoolMasterDetailedList();
        
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
				
				$('#shortName').multiselect({
						buttonText: function(options, select) {
							if (options.length == 0) {
								return 'Select Short Name <b class="caret"></b>';
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
					filterPlaceholder: 'Search Short Name'
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
				
		
				searchHistoryLoad();
	 });//onready end
		 
	function schoolMasterDetailedList(){
	
		SchoolMasterDWR.getSchoolMasterDetailedListJson(function(data){
					if (data == null) {
						alert("error");
					} else {
					   var schooldeatiledforAllFileds = jQuery.parseJSON(data);
							for(var event2 in schooldeatiledforAllFileds){
							//event2--->citys,shortNames,schools,states
							  if (event2 == "schools") {
									 var schools = schooldeatiledforAllFileds[event2];
											 for(key2 in schools){
																																//  alert(key2+"2444222*"+schools[key2]);//"3" : "ABODE Kids International",
										    	var opt = document.createElement("option");
											    var  innerHtml= "<option value="+key2+">"+schools[key2]+"</option>)";															    
											    $('#schoolNames').append(innerHtml);
											  }
												$('#schoolNames').multiselect('rebuild');		
							   }
							   if (event2 == "shortNames") {
									 var shortNames = schooldeatiledforAllFileds[event2];
											 for(key2 in shortNames){
																																//  alert(key2+"2444222*"+schools[key2]);//"3" : "ABODE Kids International",
										    	var opt = document.createElement("option");
											    var  innerHtml= "<option value="+key2+">"+shortNames[key2]+"</option>)";															    
											    $('#shortName').append(innerHtml);
											  }
												$('#shortName').multiselect('rebuild');		
							   }
							   if (event2 == "states") {
									 var states = schooldeatiledforAllFileds[event2];
											 for(key2 in states){
																																//  alert(key2+"2444222*"+schools[key2]);//"3" : "ABODE Kids International",
										    	var opt = document.createElement("option");
											    var  innerHtml= "<option value="+key2+">"+states[key2]+"</option>)";															    
											    $('#state').append(innerHtml);
											  }
												$('#state').multiselect('rebuild');		
							  }
							  if (event2 == "citys") {
									 var citys = schooldeatiledforAllFileds[event2];
											 for(key2 in citys){
																																//  alert(key2+"2444222*"+schools[key2]);//"3" : "ABODE Kids International",
										    	var opt = document.createElement("option");
											    var  innerHtml= "<option value="+key2+">"+citys[key2]+"</option>)";															    
											    $('#city').append(innerHtml);
											  }
												$('#city').multiselect('rebuild');		
							}
						}//for
							
				 jQuery("#schoolNames").multiselect('deselect', "0");
				  }//else
		});
			
			
	}
	
	function getSchoolList(){
		 var schoolNames  	 = jQuery('#schoolNames').val();
		 var shortName  	 	= jQuery('#shortName').val();
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
		SchoolMasterDWR.getSchoolMasterDisplay(
										schoolNames,
											shortName,
												state , 
													city, 
													addr,
														contactPerson , 
															mobile,
																landline ,
																	email,
																		active,	function(data){
				//0			1			2						3			4			5				6			7							8					9			10		11														
			// SM_ID,IM_ID,School_Name,Name,Address,City,State_ID,Contact_Person,Email_ID,Mobile,Landline,Active															
					//	alert(data);
						if(data == null){
							alert("error");
						}else{
						getSchoolMasterDisplayPart(data);
						}
	
	});
	}
	
	function getSchoolMasterDisplayPart(data){
	
						var idForDisplay =jQuery('#schoolDynamicGrid');
						var stringForm ='';
						 var  active;
						 if ($('#active').is(":checked")){
							active='Disable';
						} else{
						 	active='Enable';
					    }//Disable
						
						if(data.length>0){
						//alert(data.length);
						stringForm += '<div><hr style="border-style: none;height: 0;"/></div><table class="table table-bordered" id="cssTable" width="100%" style="margin-bottom:0px;">';
						stringForm += '<tr><th style="width:10%;">Short Name</th><th style="width:14%;">School Name</th><th style="width:14%;">Address</th>';
						stringForm += '<th style="width:10%;">City</th><th style="width:10%;">Contact Person</th><th style="width:11%;">Email Id</th>';
						stringForm += '<th style="width:8%;">Mobile</th><th style="width:8%;">Landline</th><th style="width:5%;">Active</th>';
						stringForm += '<th style="width:5%;">Edit</th><th style="width:5%;">'+active+'</th><tr>';
							for(var i = 0; i < data.length; i++) {
									   var temp 	= data[i];
								stringForm += '<tr style="">';					                              	
				       			stringForm += '<td>'+temp[2]+'</td><td>'+temp[3]+'</td><td>'+temp[4]+'</td><td>'+temp[5]+'</td><td>'+temp[7]+'</td>';
				       			stringForm += '<td>'+temp[8]+'</td><td>'+temp[9]+'</td><td>'+temp[10]+'</td><td>'+(temp[11]=="Y"?"Yes":"No")+'</td>';
				       			stringForm += '	<td><a href="javascript:void(0)" id="'+i+'"  value="'+temp[0]+'" onclick="editSchoolCall(id);"><i class="icon-pencil"></i></a> </td><td  width="5%"  style=""><a href="#" id="'+i+'"  value="'+temp[0]+'" onclick="disableSchoolCall(id);" ><i class="icon-trash"></i> </a></td>';
		
			       				stringForm += '</tr>';
				       			}
			       			stringForm += '</table>';
		       			}else{
			       			stringForm += '<table class="table table-bordered" width="100%" style="margin-bottom:0px;"><tr><td style="color:red;font-size:16px;font-weight:bold;text-align: center;background-color:silver ">No records found.</td></tr></table>';
			       			}
			       			
						idForDisplay.html(stringForm);
	}
	var win2;
	function editSchoolCall(val3){
		var valTOID = "#"+val3;
		 var upIDForID 			= $(valTOID).attr('value');
		 win2 = window.open("Access.action?p1=schoolMaster&p2=forEdit&p3="+upIDForID, "Schooltrix", "height=580,width=870,scrollbars=1,location=no,menubar=no,resizable=0,status=no,toolbar=no,screenX=100,screenY=100");
		win2.onbeforeunload = gridRefreshAfterEditOpen;		
	}
	function gridRefreshAfterEditOpen() {
		jQuery('#schoolDynamicGrid').html("");
		getSchoolList();//refresh if any changes in edit--grid will update
	}
	
	var schoolIDV = '';
	function disableSchoolCall(val2){
		var valTOID = "#"+val2;
		 schoolIDV 			= $(valTOID).attr('value');
		 	 if ($('#active').is(":checked")){
		confirmDialog ('Are you sure, you want to disable this school?', alert);
						} else{
		confirmDialog ('Are you sure, you want to enable this school?', alert);
					    }
		 
	}
	
		function disableSchoolAfterConfirm(){
		var active ="";
			 if ($('#active').is(":checked")){
							active='N';
						} else{
						 	active='Y';
					    }//Disable
		
			SchoolMasterDWR.disableEnableSchoolMaster(schoolIDV,active,function(data){
																if (data == null) {
																	setError("schoolNames","Error");
																	 jQuery("#schoolNames").focus();
																} else {
																	//alert("sucess***"+data);
																	 setError("schoolNames","Success");
																	 jQuery("#schoolNames").focus();
																	gridRefreshAfterEditOpen();
																}		
		
		});			
	} 
	
	 function downloadCSV(){
		      document.schoolSearch.action = 'downloadSchoolSearch.action';
		      document.schoolSearch.method = 'post';
		   	  document.schoolSearch.submit(); 
		 
		 }
		 
   function showTogglerBody(){//pass id ..if u want more ....
	        	    jQuery("#toggleeee").toggle();
	        	    jQuery(jQuery("#SHIcon").children()[0]).toggleClass("open close"); 
      }
      
      	
	function saveSchoolSearchHis(){
		var searchDesc = jQuery("#searchDesc").val();	
		var cssTable = jQuery('#cssTable').val();
		
		
		if (cssTable == undefined ||cssTable == "undefined" ) {
			setError("searchID","Search data before you save");
			jQuery("#searchID").focus();
			return false;
		}

		if (searchDesc !=null &&searchDesc != '' && searchDesc.length>2) {	
		
					SchoolMasterDWR.saveSchoolSearch(searchQueryForHis,searchDesc, searchJSONForHis,function(data){
					//	alert(data);
									if (data == "nosearch") {
										jQuery('#schoolDynamicGrid').html("");
										setError("searchID","Search data before you save");
										 jQuery("#searchID").focus();
										 														
									}else if (data == "descthere") {
										jQuery('#schoolDynamicGrid').html("");
										setError("searchID","Search description already there");
										 jQuery("#searchID").focus();																
									}else	if (data == "success") {
										jQuery('#schoolDynamicGrid').html("");
										setError("searchID"," Successfully saved.");
										 jQuery("#searchID").focus();
										 
										 searchHistoryLoad();//***** After Saving ...it will reflect on Search history selection box..
									} else if (data == "already") {
										jQuery('#schoolDynamicGrid').html("");
											setError("searchID","Search is already there");
											 jQuery("#searchID").focus();
									} else {
										jQuery('#schoolDynamicGrid').html("");
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
	function searchHistoryLoad(){
		var searchHisID =	 document.getElementById('searchHistory');	
		removeAllOptionsOne("searchHistory");
			SchoolMasterDWR.searchHistoryLoadCall(function(data){
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
			SchoolMasterDWR.deleteSchoolSearchHistoryDoc(searchHisID,function(data){
					if (data == null) {
						setError("searchHistory","Error During delete..");
						 jQuery("#searchHistory").focus();
					} else {
						//for refreshment of result--test
							setError("searchHistory","Successfully Deleted");
							 jQuery("#searchHistory").focus();
  						jQuery('#uploadDynamicGrid').html("");
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
	
	function  displaySchoolSearchHistoryLoadByID() {
		
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
				schoolMasterDetailedList();
				searchJSON = jQuery.parseJSON(jsonForSearchDisp);
		
				setTimeout(renderDataForm,300);
				
	 			SchoolMasterDWR.searchHistoryLoadByIDDisplay(queryForSearch , function(data){
					if (data == null) {
						alert('error in displaySearchHistoryLoadByID ');
					} else {
						getSchoolMasterDisplayPart(data);
					}
				});
		}else{
		resetForm();
		schoolMasterList();
		}
	}
	
		function  renderDataForm() {
		var schollSplit = searchJSON.sm_ids.split(',');
		var shortSplit = searchJSON.sn_ids.split(',');
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
	
		
		for ( var int = 0; int < schollSplit.length; int++) {
			var schoolListArray_element = schollSplit[int];
			if (schoolListArray_element.indexOf('%') != -1) {
			}else{
			 jQuery("#schoolNames").multiselect('select', schoolListArray_element);
			 }
		}
		for ( var int = 0; int < shortSplit.length; int++) {
			var shortListArray_element = shortSplit[int];
			if (shortListArray_element.indexOf('%') != -1) {
			}else{
			 jQuery("#shortName").multiselect('select', shortListArray_element);
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
		
		for ( var int = 0; int < schollSplit.length; int++) {
			var schoolListArray_element = schollSplit[int];
			if (schoolListArray_element.indexOf('%') != -1) {
			}else{
			 jQuery("#schoolNames").multiselect('select', schoolListArray_element);
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
	
	 function resetForm() {	
	 	 var schoolNames  	 = jQuery('#schoolNames').val();
		 var shortName  	 	= jQuery('#shortName').val();
		 var state 	 	 			= jQuery('#state').val();
		 var city 	 	 				= jQuery('#city').val();
		 
	       	removeAllOptions("#schoolNames");
			removeAllOptions("#shortName");
			removeAllOptions("#state");
			removeAllOptions("#city");

			 jQuery("#schoolNames").val('Select School');
			 jQuery("#shortName").val('Select Short Name');
			 jQuery("#state").val('Select State');
			 jQuery("#city").val('Select City');
	       jQuery("#addr").val('');
	       jQuery("#contactPerson").val('');
	       jQuery("#mobile").val('');
	       jQuery("#landline").val('');
	       jQuery("#email").val('');

	       jQuery("#searchDesc").val('');
	       
	       $('#active').prop('checked', true);
	        jQuery('#schoolDynamicGrid').html("");		 

	}
	
	function resetFormHelp(){
		resetForm();
		jQuery('#searchHistory').val('-1').attr('selected',true);
		schoolMasterDetailedList();
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
			 document.location = "Access.action?p1=schoolMaster&p2=ffats";		
		}	
	    
	
	</script>

<form method="post" name="schoolSearch" id = "schoolSearch" enctype="multipart/form-data" >
<div style="height:25px;text-align: center;"><span id ="errorspan" style="color:red;font-size: 14px;font-weight: bold;" >&nbsp;</span></div>
<div class="reg_mainCon">
    <fieldset>
    <legend style="padding: 0px;"><img src="img/list_add_user.PNG" class="img-circle">&nbsp;&nbsp;View Schools
      <div id="SHIcon" class="TogglerHader" style="display: inline-block;padding-top: 6px;	float: right;"><div class="open" onclick="showTogglerBody(); return false;">
      <a href="#SHIcon">Show | Hide</a></div></div>
      </legend>
    <div id="toggleeee" style="padding:20px;padding-top: 2px;" >
   <table width="100%" border="0" cellspacing="0" cellpadding="0">
 	 <tr>
   		<td ><label style="color: #000;text-align: left: ;">Saved Search&nbsp;&nbsp;&nbsp;</label></td>
   		<td ><select class="span3" style="width: 82%" name="searchHistory" id="searchHistory" onchange="displaySchoolSearchHistoryLoadByID();">
   		<option value="-1">Select</option>
   		</select> <a class="btn btn-warning" style="margin-bottom: 10px;text-align: center;height: 18px;width: 4%" href="#" onclick="deleteSearchHistory();return false;"><i class=" icon-trash"></i></a>
   		</td>	
   			   <td>&nbsp;</td>	
   			   <td colspan="2" style="float: right;height: 20px;">
	      	<input type="button" class="btn btn-warning"  style="height: 30px;width: 150px;" id='' value="Add School" onclick="editSSP()"></td>
  	 </tr>
      <tr>
      	     <td><label style="color:#000;"><b>School Name </b></label></td>
      		 <td>
      		    <div class="input-append">  
		  			<select class="multiselect" multiple="multiple"  id="schoolNames" name="schoolNames">			
						<option value="0" >Select All </option>
		  			</select>
		 		</div>
			</td>
          <td><label style="color: #000;padding-left: 10px;"><b> Short Name </b></label></td>
          <td>
		  	<div class="input-append">  
		  		<select class="multiselect" multiple="multiple"  id="shortName" name="shortName">		
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
        <td><input type="text" class="span3" name="addr" id="addr" style="width: 255px;" ></td>
     	<td><label style="color:#000;padding-left: 10px;"><b> Contact Person </b></label></td>
        <td><input type="text" class="span3" name="contactPerson" style="width: 255px;" id="contactPerson" ></td>
     </tr>
     <tr>
     	<td><label style="color:#000;"><b>Mobile  </b></label></td>
        <td><input type="text" class="span3" style="width: 255px;" name="mobile" id="mobile" ></td>
     	<td><label style="color:#000;padding-left: 10px;"><b>Landline</b></label></td>
        <td><input type="text" class="span3" style="width: 255px;" name="landline" id="landline" ></td>
     </tr>
     <tr>
     	<td><label style="color:#000"><b>Email Id  </b></label></td>
        <td><input type="text" class="span3" name="email" id="email" style="width: 255px;" ></td>
     	<td><label style="color:#000;padding-left: 10px;"><b>Active</b></label></td>
        <td><input type="checkbox" class="span3" name="active" id="active" checked="checked"></td>
     </tr><tr><td>&nbsp;</td></tr>
       <tr>
          <td>&nbsp;</td>
            <td colspan="2"><div style="display: inline-block;padding-left: 200px;">
          <button type="button" id ="searchID" name="searchID"  style="margin-left: 20px;" class="btn btn-warning" onclick="getSchoolList();return false;">Search</button>  
           <button type="button" id ="resetID" name="resetID"  style="margin-left: 10px;"  class="btn btn-warning" onclick="resetFormHelp();return false;">Reset</button> 
          </div>
          </td>
          <td width="1%">&nbsp;</td>
        </tr>       
        <tr><td>&nbsp;</td></tr> 
       <tr><td><label style="color:#000;width: 103%;"><b>Save search as</b></label></td>
        <td><input style='width:73%;margin-left: 10px;' type="text" class="span3" name="searchDesc" id="searchDesc" >
        		<a class="btn btn-warning" style="margin-bottom: 10px;text-align: center;height: 18px;width: 5%" href="#" onclick="saveSchoolSearchHis();return false;"><i class="icon-ok-circle"></i></a>
        </td><td>&nbsp;</td>
        <td style="text-align: right;">&nbsp;<a href="#" onclick="downloadCSV();" style="color: #000;">Download Result as CSV</a>&nbsp;</td>
        </tr>
        
   </table> 
  </div>
 </fieldset>
</div>   
</form>
					<!-- result display ID -->
<div id="schoolDynamicGrid" style="padding: 10px;"></div>
    
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
			