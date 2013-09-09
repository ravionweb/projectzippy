<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
    <%
    String searchHistoryQu =(String) session.getAttribute("queryForSearch");
    String searchHistoryJSON =(String) session.getAttribute("jsonForSearch");
    System.out.println("searchHistoryQu**"+searchHistoryQu);
    System.out.println("searchHistoryJSON**"+searchHistoryJSON);
    if(searchHistoryQu != "" && searchHistoryQu != null && searchHistoryQu != "null" ){
    session.removeAttribute("queryForSearch");    
    }
    if(searchHistoryJSON != "" && searchHistoryJSON != null && searchHistoryJSON != "null" ){
    session.removeAttribute("jsonForSearch");    
    }
    
     %>
    
  	<link href="css/bootstrap.css" rel="stylesheet">
	<link href="css/bootstrap-datetimepicker.min.css"  rel="stylesheet" type="text/css" media="screen"  >
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



</style>
    
   
   	<script src="js/bootstrap-multiselect.js" type="text/javascript"></script>
    <script src="js/bootstrap-datetimepicker.min.js"> </script>
 	<script  src="js/bootstrap-datetimepicker.pt-BR.js"> </script>
 
   	 <script 	src="<%=request.getContextPath()%>/dwr/interface/SchoolMasterDWR.js"></script>
  	 <script 	src="<%=request.getContextPath()%>/dwr/interface/BranchMasterDWR.js"></script>
  	 <script 	src="<%=request.getContextPath()%>/dwr/interface/ClassMasterDWR.js"></script>
  	 <script 	src="<%=request.getContextPath()%>/dwr/interface/AssignmentGetDWR.js"></script>
      
     <script type="text/javascript">
        
        var searchQueryForHis = "<%=searchHistoryQu%>";
        var searchJSONForHis = '<%=searchHistoryJSON%>';        
        var searchHisOnloadJson = '';        
        	// code for confirm box
		var confirmDialogCallback;
		
		//PAgination
		var totaljson		= [];
		var indexforTotalJ = 0;
		var NewScrubber = [];
		var PageCount	= 5;
		//var OpenItemCount	= 15;
		var OpenItemCount	= 4;
		
		function closeConfirmDialog () {
			$("#idConfirmDialog").modal ('hide'); 
			};
		function okConfirmDialog () {
			$("#idConfirmDialog").modal ('hide'); 
			deleteDocAfterConfirm();
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
				
				 var fileTypes1	  	     =  document.getElementById('fileType');	
				var allowed = ['All','pdf', 'doc', 'docx', 'xls', 'xlsx', 'txt','ppt','pptx','gif','csv','png','jpg','jpeg','dot','dotx','html','odm','ott','odt','rtf','xps'];
					for ( var int = 0; int < allowed.length; int++) {
							 var opt = document.createElement("option");
						    opt.innerHTML = allowed[int];
						    opt.value = allowed[int];
						    fileTypes1.appendChild(opt);
					}
					document.getElementById("fileType").selectedIndex=0;
						jQuery("#schoolNames").multiselect('deselect', "0");
					searchHistoryLoad();
		 });//onready end
		 
		 function validateForm(){
		 var schoolNames  	 = jQuery('#schoolNames').val();
		 var branchNames  	 = jQuery('#branchNames').val();
		 var selectClass 	 	 = jQuery('#selectClass').val();
		 var selectType  		 = jQuery('#selectType').val();//parents or student or both
		 var uploadType			 = jQuery('#uploadType').val();
		 var fileType		  	     = jQuery('#fileType').val();
		 
		 var assignmentType  	 = jQuery('#assignmentType').val();
		 var selectSubject  	 = jQuery('#selectSubject').val();
		 
		if(schoolNames == null || schoolNames == ""){
			setError("schoolNames","Please select School(s)");
			 jQuery("#schoolNames").focus();
			return false;
		}
		 
		if(branchNames == null || branchNames == ""){
			setError("branchNames","Please select Branch(s)");
			 jQuery("#branchNames").focus();
			return false;
		}
		 
		if(selectClass == null || selectClass == ""){
			setError("selectClass","Please select Grade(s)/Class(s)");
			 jQuery("#selectClass").focus();
			return false;
		}
		 
		if(selectType == null || selectType == ""){
			setError("selectType","Please select Audience ");
			 jQuery("#selectType").focus();
			return false;
		}
		
		if(uploadType == -1 || uploadType == ""){
			setError("uploadType","Please select Upload Type");
			 jQuery("#uploadType").focus();
			return false;
		}
		if(fileType == -1 || fileType == ""){
			setError("fileType","Please select File Type");
			 jQuery("#fileType").focus();
			return false;
		}
		 
		 if (uploadType == "Assignment" && assignmentType == -1) {
			setError("assignmentType", "Please select Assignment Type");
			 jQuery("#assignmentType").focus();
			return false;
		}
		 if (uploadType == "Assignment" & selectSubject ==-1) {
			setError("selectSubject", "Please select Subject");
			 jQuery("#selectSubject").focus();
			return false;
		}
		
		 if (uploadType == "AcademicMaterial" & selectSubject ==-1) {
			setError("selectSubject", "Please select Subject");
			 jQuery("#selectSubject").focus();
			return false;
		}
		return true;
	 }
		 
	//2013-06-15
	function getUploadedDocsNew(){
	 //schoolNames-branchNames-selectClass-selectType-uploadType-fileType-assignmentType-selectSubject---8
	     var schoolNames  	 = jQuery('#schoolNames').val();
		 var branchNames  	 = jQuery('#branchNames').val();
		 var selectClass 	 	 = jQuery('#selectClass').val();
		 var selectType  		 = jQuery('#selectType').val();//parents or student or both
		 var uploadType			 = jQuery('#uploadType').val();
		 var fileType		  	     = jQuery('#fileType').val();
		 
		 var assignmentType  = jQuery('#assignmentType').val();
		 var selectSubject  	 = jQuery('#selectSubject').val();

		 var fromDate  	 		= jQuery('#fromDate').val();
		 var toDate  			 	= jQuery('#toDate').val();
		 
		 var uploadGridId  	 = jQuery('#uploadDynamicGrid');		 
		 uploadGridId.html("");		 
		 var uploadStringCreateMain = '';
		 
 			AssignmentGetDWR.getUploadedDocForEditNew(schoolNames,branchNames,selectClass,selectType,uploadType,
 																							fileType,assignmentType,selectSubject,fromDate,toDate,function(data) {
 																							if (data == null) {
																							alert("Error");	
																							} else {
 																							getUploadedDocsNew_SEE(data);
																							}
 																							
 																							});
	}
	
	function getUploadedDocsNew_SEE(data){
		var uploadGridId  	 = jQuery('#uploadDynamicGrid');
		var uploadStringCreateMain = '';
			uploadGridId.html("");
		if (data == null) {
                         alert("error");
		} else {
			var uploadedDocJson = jQuery.parseJSON(data);
			if(uploadedDocJson != null && uploadedDocJson.length > 0){
 			var mainJSONOBJ = '';
		     for(var i = 0; i < uploadedDocJson.length; i++) {//school Obj's
		                                   var uploadID 			= i+"uploadID";
	  
		      	var schoolWise = uploadedDocJson[i];
				var one = 10;
				var OneMain ='';
				  
				  for(var subKKey in uploadedDocJson[i]){//ABODE Kids International, value: [object Object]
				 		var uploadStringCreate = '';
							uploadStringCreate += '<div><hr style="border-style: none;height: 0;"/></div><div style="   background-color: #FFFFFF; border: 1px solid #DDDDDD; border-radius: 6px 6px 6px 6px; color: #9DA0A4; font-size: 12px; font-weight: bold; left: -1px; margin: 0 auto; max-width: 98%; padding: 3px 3px;">';
							uploadStringCreate += ' <div style="padding:10px 20px 20px 20px;">';
							uploadStringCreate += ' <fieldset><legend><div  width="100%" style="margin-bottom:0px;color:black;">'+subKKey+'</div></legend>';

				   		for(var subKKey2 in uploadedDocJson[i][subKKey]){//0, value: [object Object]		
				   	
				   			for(var subKKey3 in uploadedDocJson[i][subKKey][subKKey2]){//AKI Compass, value: [object Object],[obje
					   			if (OneMain !== '') {
										uploadStringCreate +='<div><hr style="border-style: none;height: 10px;"/></div>';
								}
					   			OneMain = one+""+i;
					 			one++;
								    uploadStringCreate += '<div> <table class="table table-bordered" width="100%" style="margin-bottom:0px;color:black;"><tbody><tr><td colspan="10" style="text-align:left;font-size:18px;">'+subKKey3+'</td></tr>';/* 	<td colspan="8"><hr/></td></tr> */							  
									/* uploadStringCreate += ' <tr><th width="14%" style="text-align:center;">Upload  Type </th><th width="13%" style="text-align:center;">Audience </th>';
									uploadStringCreate += ' <th width="11%">Assignment Type</th>';
									uploadStringCreate += ' <th width="20%" style="text-align:center;">Description</th><th width="9%" style="text-align:center;">Subject</th>';
									uploadStringCreate += '<th width="5%" style="text-align:center;">Email</th><th width="10%" style="text-align:center;">Date</th><th width="8%" style="text-align:center;">View File</th><th width="5%" style="text-align:center;">Edit</th><th width="5%" style="text-align:center;">Delete</th></tr>';
								 */	uploadStringCreate += '<tbody></table></div>';  
									uploadStringCreate += ' <div id="data'+OneMain+'"></div><div><hr style="border-style: none;margin: 4px;"/></div><div id="pag'+OneMain+'"></div>';
						   	}
					}
									uploadStringCreate += '</fieldset></div></div>';     
				}
				 mainJSONOBJ += uploadStringCreate;
 			}//main for loop
		 		uploadGridId.html(mainJSONOBJ);
		 		
		for(var k = 0; k < uploadedDocJson.length; k++) {//school Obj's
			var schoolWise 	=  uploadedDocJson[k];
			var oneD 				= 10;
			var OneMainD 		= '';

		     for(var subKey in uploadedDocJson[k]){
										       
			        for(var subKey2 in uploadedDocJson[k][subKey]){
			        
				         for(var subKey3 in uploadedDocJson[k][subKey][subKey2]){
			 	 		  totaljson[indexforTotalJ]=uploadedDocJson[k][subKey][subKey2][subKey3];
							if(totaljson[indexforTotalJ].length>0){
							 	 	OneMainD = oneD+""+k;
				 					oneD++;
									NewScrubber[indexforTotalJ]		= new jQuery.DataScrubber(totaljson[indexforTotalJ], PageCount, OpenItemCount);
									renderPagination(NewScrubber[indexforTotalJ].init(),OneMainD,indexforTotalJ);			
									indexforTotalJ++;			
									}
										       
						}////key4: AKI Compass, value: [object Object],[object Object]---END						       
					}////key3: 0, value: [object Object]					        
				 }//key2: ABODE Kids International, value: [object Object]--END
		      }//for loop
			}else{
			 uploadGridId.html('<table class="table table-bordered" width="100%" style="margin-bottom:0px;"><tr><td style="color:red;font-size:16px;font-weight:bold;text-align: center;background-color:silver ">No records found.</td></tr></table>');
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
				stringForm += '<table class="table table-bordered" width="100%" style="margin-bottom:0px;">';
					stringForm += ' <tr style="color:#000"><th width="14%" style="text-align:center;">Upload  Type </th><th width="13%" style="text-align:center;">Audience </th>';
		stringForm += ' <th width="11%">Assignment Type</th>';
		stringForm += ' <th width="20%" style="text-align:center;">Description</th><th width="9%" style="text-align:center;">Subject</th>';
		stringForm += '<th width="5%" style="text-align:center;">Email</th><th width="10%" style="text-align:center;">Date</th><th width="8%" style="text-align:center;">View File</th>';
		stringForm += '<th width="5%" style="text-align:center;">Edit</th><th width="5%" style="text-align:center;">Delete</th></tr>';
			if(pickObj.length > 0){
               for(var subKey4 in pickObj){
				  var uploadID 			= subKey4+"uploadID"+idff;
					stringForm += '<tr>';					                              	
			        stringForm += '<td  width="14%" style="text-align:center;">'+pickObj[subKey4].uploadType+'</td><td  width="13%" style="text-align:center;">'+(pickObj[subKey4].to_whome == "0"?"Parents,Students":pickObj[subKey4].to_whome)+'</td>';
			        stringForm += ' <td  width="11%" style="text-align:center;">'+pickObj[subKey4].assign_type+' </td>';					                             	
			        stringForm += '<td  width="20%" style="text-align:center;">'+pickObj[subKey4].assg_desc+'</td><td width="9%"  style="text-align:center;">'+pickObj[subKey4].subject+'</td>';
			        stringForm += '<td  width="5%" style="text-align:center;">'+pickObj[subKey4].isEmail+'</td><td  width="10%"  style="text-align:center;">'+formatDate(pickObj[subKey4].upload_date,"d M Y")+'</td>';
            		stringForm += '<td  width="8%" style="text-align:center;"><a href="downloadUploadDoc.action?type='+pickObj[subKey4].uploadType+'&fileName='+pickObj[subKey4].fileName+'" id="'+pickObj[subKey4].ud_id+'" ><i class="icon-eye-open"></i> </a></td>	<td  width="5%" style="text-align:center;"><a href="javascript:void(0)" id="'+uploadID+'"  value="'+pickObj[subKey4].ud_id+'" onclick="editUploadCall(id);"><i class="icon-pencil"></i></a> </td>		<td  width="5%"  style="text-align:center;"><a href="#" id="'+uploadID+'"  value="'+pickObj[subKey4].ud_id+'" onclick="deleteUploadCall(id);" ><i class="icon-trash"></i> </a></td></tr>';
			   }///DATA end
              	stringForm +='</table>';
		}else{
          		stringForm += '<table class="table table-bordered" width="100%" style="margin-bottom:0px;"><tr><td>No records found.</td></tr></table>';
         }//if
		 newCallsGrid.html(stringForm);
	}		
		 	      
	function selectType1(val){
	    if(val=='Assignment' || val=='AcademicMaterial'){
		    if ( val=='Assignment') {
				document.getElementById('assignmentType').disabled = false;
			}else{
				document.getElementById('assignmentType').disabled = true;
			}
				document.getElementById('selectSubject').disabled 	= false;				
				 subjectMaster();
		}else{
			document.getElementById('assignmentType').disabled 	= true;
			document.getElementById('selectSubject').disabled 		= true;
			document.getElementById('selectSubject').display 			= 'none';
		}
	}
	
	function schoolMasterList(){
		try{
			var listofschools = document.getElementById('schoolNames');					
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
		removeAllOptions("#selectClass");
		
		if(schoolID == null || schoolID == ""){
			setError("schoolNames","Please select School");
			 jQuery("#schoolNames").focus();
			return false;
		}
		   try{
		   			var im_id = "<%=session.getAttribute("IM_ID")%>";			   	
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
		removeAllOptions("#selectClass");
		removeAllOptionsOne("selectSubject");//new one 2013-06-29
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
				                                if (data == null) {
				                                  alert("error");
												} else {
				                               var classListJson  =  jQuery.parseJSON(data);

					                              if(classListJson != null && classListJson.length > 0){				                               
					                               jQuery('#classBMIds').val(data);//hidden value
					                              	 var tttt = '';
					                                 for(var i = 0; i < classListJson.length; i++) {
						                                 if (tttt != classListJson[i].class_name) {
						                                 	tttt = classListJson[i].class_name;
						                               		var opt = document.createElement("option");
													    	var  innerHtml= "<option value="+ classListJson[i].class_id+">"+ classListJson[i].class_name+"</option>";
													    	$('#selectClass').append(innerHtml);
														}
				                               	     }
				                               	 }
											}
													$('#selectClass').multiselect('rebuild');
													jQuery("#selectClass").focus();
				      }) ;
						
						jQuery("#selectClass").focus();
		   		 }catch(e){
						 alert("incatch::"+e);
		         } 	
		 }
	 
	 	function selectSubjectsForClasses(){	   		 
		   var upload = jQuery("#uploadType").val();		   
				    if(upload =='Assignment' || upload=='AcademicMaterial'){
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
			
					var listofSubjects = document.getElementById('selectSubject');			
				     ClassMasterDWR.getMultiSubjectMasterList(schoolIDs,branchIDs,classIDs,function(data){
					                                if (data == null) {
					                                  alert("error");
													} else {
													    for(var i = 0; i < data.length; i++) {
												    	    var opt = document.createElement("option");
														    var temp = data[i];
														    opt.innerHTML = temp[0];
														    opt.value = temp[1];
														    listofSubjects.appendChild(opt);
													  }
												}
					     }) ;
					   }
			 }catch(e){
					 alert("incatch::"+e);
					        jQuery.log.info(e.message);
					        jQuery("#infoError").html("&nbsp;");
	         } 	
	}
		
	function removeAllOptions(whichOption)
		{
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
		
		var uploadIDV = '';
	function deleteUploadCall(val2){
		var valTOID = "#"+val2;
		 uploadIDV 			= $(valTOID).attr('value');
		confirmDialog ('Are you sure, you want to delete this document?', alert);
	}
	
	function deleteDocAfterConfirm(){
			AssignmentGetDWR.deleteUploadedDoc(uploadIDV,function(data){
																if (data == null) {
																	setError("schoolNames","Error During delete..");
																	 jQuery("#schoolNames").focus();
																} else {
																	//for refreshment of result--test
																		setError("searchID","Successfully Deleted");
																		 jQuery("#searchID").focus();
																		getUploadedDocsNew();
																}		
		
		});			
	} 
		var win2;
	function editUploadCall(val3){
		var valTOID = "#"+val3;
		var upIDForID 			= $(valTOID).attr('value');
	  	win2 =	window.open("Access.action?p1=UploadDocuments&p2=forEdit&p3="+upIDForID, "Schooltrix", "height=580,width=870,scrollbars=1,location=no,menubar=no,resizable=0,status=no,toolbar=no,screenX=100,screenY=100");
		win2.onbeforeunload = gridRefreshAfterEditOpen;		
	}
	
	function gridRefreshAfterEditOpen() {
		setTimeout(delayedGridRefreshAfterEditOpen,2000);
	}
	//in above ,function gridRefreshAfterEditOpen called when edit of upload saved(Action submit)....but it not happens in dwr...
	function delayedGridRefreshAfterEditOpen(){
		//alert("jjjhjjhj");
		jQuery('#uploadDynamicGrid').html("");
		getUploadedDocsNew();//refresh if any changes in edit--grid will update
	}
	
	
	function saveSearchHis(){
		var searchDesc = jQuery("#searchDesc").val();	
		var data100 = jQuery("#data100").val();
		
		if (data100 == "undefined" || data100 == undefined ) {
			setError("searchID","Search data before you save");
			jQuery("#searchID").focus();
			return false;
		}

		if (searchDesc !=null &&searchDesc != '' && searchDesc.length>2) {	
		
					AssignmentGetDWR.saveSearch(searchQueryForHis,searchDesc, searchJSONForHis,function(data){
					//	alert(data);
									if (data == "nosearch") {
										jQuery('#uploadDynamicGrid').html("");
										setError("searchID","Search data before you save");
										 jQuery("#searchID").focus();
										 														
									}else if (data == "descthere") {
										jQuery('#uploadDynamicGrid').html("");
										setError("searchID","Search description already there");
										 jQuery("#searchID").focus();																
									}else	if (data == "success") {
										jQuery('#uploadDynamicGrid').html("");
										setError("searchID"," Successfully saved.");
										 jQuery("#searchID").focus();
										 
										 searchHistoryLoad();//***** After Saving ...it will reflect on Search history selection box..
									} else if (data == "already") {
										jQuery('#uploadDynamicGrid').html("");
											setError("searchID","Search is already there");
											 jQuery("#searchID").focus();
									} else {
										jQuery('#uploadDynamicGrid').html("");
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
			AssignmentGetDWR.deleteSearchHistoryDoc(searchHisID,function(data){
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
	
	function searchHistoryLoad(){
	var searchHisID =	 document.getElementById('searchHistory');	
	removeAllOptionsOne("searchHistory");
		AssignmentGetDWR.searchHistoryLoadCall(function(data){
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
	 			AssignmentGetDWR.searchHistoryLoadByIDDisplay(queryForSearch , function(data){
					if (data == null) {
						alert('error in displaySearchHistoryLoadByID ');
					} else {
						getUploadedDocsNew_SEE(data);
					}
				});
		}else{
		resetForm();
		schoolMasterList();
		}
	}
	
	function  renderDataForm() {
	
		var schollSplit = searchJSON.smIDs.split(',');
		
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
			setTimeout(internalBranchDisp,timeFor);	
		 }
		 timeFor +=400;			
		setTimeout(remainingSelect,timeFor);
		
	}
	
	function internalBranchDisp(){
			  var branchNames = jQuery("#branchNames").val();
		  		if(branchNames != null && branchNames != ""){
					setTimeout(selectBranchesforClasses,50);		
					setTimeout(selectClassFucn,300); 
		 		}	
	}
	
	function remainingSelect(){
		 /**
	"uploadType" : "AcademicMaterial",	"fileType" : "txt",			"assignmentType" : "0",		"selectSubject" : "1",		"cmIDs" : "0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19",
	"dateQStringFROM" : "",		"smIDs" : "0,3,4,5,6,7,8",			"bmIDs" : "0,3,4,5,6",			"dateQStringTO" : "",		"userIDs" : "'0','Parents','Students'"	
	*/
		  var uploadType 		= searchJSON.uploadType;
		 var assignmentType = searchJSON.assignmentType;
		
		 var sub 				= searchJSON.selectSubject;
		 var fileName 	= searchJSON.fileName;
		 var fileType 		= searchJSON.fileType;
		 var dateQStringTO 		= searchJSON.dateQStringTO;
		 var dateQStringFROM = searchJSON.dateQStringFROM;
			 
			 jQuery("#uploadType").val( uploadType ).attr('selected',true);
			 jQuery("#fileType").val( fileType ).attr('selected',true);
			 
			 jQuery("#fromDate").val(dateQStringFROM);
			 jQuery("#toDate").val(dateQStringTO);
			 jQuery("#searchDesc").val( descForSearchDisp );
		 	
		 	setTimeout(selectUserType,300);
	
	 		 if (uploadType == "Assignment" ) {
		 		  jQuery('#assignmentType').prop('disabled', false);
		 		  jQuery('#selectSubject').prop('disabled', false);
		 		  jQuery("#assignmentType").val( assignmentType ).attr('selected',true);
		 		  setTimeout(selectSubjectsForClasses,300);
		 		  setTimeout(selectSubFucn,400);
		 	}else if(uploadType == "AcademicMaterial"){
			 	 jQuery('#selectSubject').prop('disabled', false);
		 		  setTimeout(selectSubjectsForClasses,300);
		 		  setTimeout(selectSubFucn,500);
			 
	       }
		 
		 
	}
	
	function selectUserType(){
		var userType = searchJSON.userIDs.split(',');
		for ( var int = 0; int < userType.length; int++) {
			var userTypeArra = userType[int].replace(/\'/g,"");
			 jQuery("#selectType").multiselect('select', userTypeArra);
		}
	}
	function selectSubFucn(){
		  var sub = searchJSON.selectSubject;
		  jQuery("#selectSubject").val( sub ).attr('selected',true);
	}
	
	function selectClassFucn(){
		var classListArray = searchJSON.cmIDs.split(',');
			for ( var int = 0; int < classListArray.length; int++) {
			var classListArray_element = classListArray[int].replace(/\'/g,"");
			 jQuery("#selectClass").multiselect('select', classListArray_element);
		}
	}
	
	function selectBrachesFucn(){
		var branchListArray = searchJSON.bmIDs.split(',');
			for ( var int = 0; int < branchListArray.length; int++) {
			var branchListArray_element = branchListArray[int].replace(/\'/g,"");
				if (branchListArray_element.indexOf('%') != -1) {
				}else{
						 jQuery("#branchNames").multiselect('select', branchListArray_element);
			   }
		}
	}
	
	 function resetForm() {
	       	removeAllOptions("#schoolNames");
			removeAllOptions("#branchNames");
			removeAllOptions("#selectClass");

			removeAllOptions("#selectType");

	       jQuery("#schoolNames").val('Select School');
	       jQuery("#branchNames").val('Select Branch');
	       jQuery("#selectClass").val('Select Class');
	       jQuery("#selectType").val('Select Audience');
	       jQuery("#uploadType").val('All');
	       jQuery("#fileType").val('All');
	       jQuery("#assignmentType").val('All');
	       jQuery("#selectSubject").val('All');
	       jQuery("#toDate").val('');
	       jQuery("#fromDate").val('');
	       jQuery("#searchDesc").val('');
	       
	        jQuery('#uploadDynamicGrid').html("");		 

	}
	
	function resetFormHelp(){
		resetForm();
		jQuery('#searchHistory').val('-1').attr('selected',true);
		schoolMasterList();
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
		 
		 function downloadCSV(){
		      document.uploadSearch.action = 'downloadUploadDocSearch.action';
		      document.uploadSearch.method = 'post';
		   	  document.uploadSearch.submit(); 
		 
		 }
		 
		 function showTogglerBody(){//pass id ..if u want more ....
	        	    jQuery("#toggleeee").toggle();
	        	    jQuery(jQuery("#SHIcon").children()[0]).toggleClass("open close"); 
      }
		 
		</script> 
     <form method="post" name="uploadSearch" id = "uploadSearch"  enctype="multipart/form-data" >
<div style="height:25px;text-align: center;"><span id ="errorspan" style="color:red;font-size: 14px;font-weight: bold;" >&nbsp;</span></div>
<div class="reg_mainCon">
    <fieldset>
    <legend style="padding: 0px;display: inline-block;"><img src="img/list_add_user.PNG" class="img-circle">&nbsp;&nbsp;Edit Previous Uploaded Documents
      <div id="SHIcon" class="TogglerHader" style="display: inline-block;padding-top: 6px;	float: right;"><div class="open" onclick="showTogglerBody(); return false;">
      <a href="#SHIcon">Show | Hide</a></div></div>
    
    </legend>
    <div id="toggleeee" style="padding:20px;padding-top: 2px;" >
   <table width="100%" border="0" cellspacing="0" cellpadding="0">
  
 	  <tr><td></td>
   		<td><label style="color: #000;text-align: right;">Saved Search&nbsp;&nbsp;&nbsp;</label></td>
   		<td><select class="span3" style="width: 76%" name="searchHistory" id="searchHistory" onchange="displaySearchHistoryLoadByID();">
   		<option value="-1">Select</option>
   		</select> <a class="btn btn-warning" style="margin-bottom: 10px;text-align: center;height: 18px;width: 4%" href="#" onclick="deleteSearchHistory();return false;"><i class=" icon-trash"></i></a>
   		</td>	
  	 </tr>
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
				<option value="0" >Select All</option>	
				<option value="Parents">Parents</option>
                <option value="Students">Students</option>
			</select>
           </td>                 
        </tr>
        <tr>
         <td><label style="color:#000;"><b>Upload Type</b></label></td>
                <td><select class="span4"   name="uploadType" id="uploadType" onchange="selectType1(this.value)">
                       <!--  <option value="-1" >Select</option> -->
                      <option value="0"  selected="selected">All</option>
                      <option value="AcademicMaterial">Academic Material</option>
                      <option value="Assignment">Assignment</option>
                      <option value="Calendar">Calendar</option>
                      <option value="Canteen menu">Canteen Menu</option>
                      <option value="ExamSchedule">Exam Schedule</option>
                      <option value="Time table">Timetable</option>
                       </select>                   
                 </td>
                <td><label style="color:#000;"><b>From Date / On Date</b></label></td>
        </tr>
         <tr>
         <td><label style="color:#000;"><b>File Type</b></label></td>
                <td><select class="span4"   name="fileType" id="fileType" >
                       <!--  <option value="-1" >Select</option> -->
                       </select>                   
                 </td>
                <td>
                    <div id="datetimepickerID"    class="input-append date"   >
			     		 	<input style="width:77%;" class="span3" type="text" data-format="yyyy-MM-dd hh:mm:ss PP"  name="fromDate"  id="fromDate"></input>
			      			<span class="add-on">
			        			<i data-time-icon="icon-time" data-date-icon="icon-calendar"></i>
			      			</span>
		    	</div>
                </td>            
        </tr>
         <tr>
         <td><label style="color:#000;"><b>Assignment Type</b></label></td>
                <td><select class="span4" name="assignmentType"  id="assignmentType" disabled="disabled">
                    <!--     <option value="-1">Select</option> -->
                         <option value="0"  selected="selected">All</option>
                        <option value="Daily">Daily</option>
                        <option value="Holiday">Holiday</option>
                        <option value="Weekend">Weekend</option>
                       </select>                   
                 </td>
                <td><label style="color:#000;"><b>To Date</b></label> </td>
        </tr>
        <tr>
         <td><label style="color:#000;"><b>Subject</b></label></td>
                <td>
                 <select class="span4"  id="selectSubject" name="selectSubject" disabled="disabled">		
					<!-- 	 <option value="-1" >Select</option>	 -->
						 <option value="0" selected="selected" >All</option>	
					</select>
                 </td>
              <td> <div id="datetimepickerID1"    class="input-append date"   >
			      <input style="width:77%;" class="span3" type="text" data-format="yyyy-MM-dd hh:mm:ss PP"  name="toDate"  id="toDate"></input>
			      <span class="add-on">
			        <i data-time-icon="icon-time" data-date-icon="icon-calendar"></i>
			      </span>
		    </div></td> 
        </tr>
       <tr><td>&nbsp;</td></tr>
              <tr>
          <td>&nbsp;</td>
          <td colspan="2"><div style="display: inline-block;padding-left: 200px;">
          <button type="button" id ="searchID" name="searchID"  style="margin-left: 20px;" class="btn btn-warning" onclick="getUploadedDocsNew();return false;">Search</button>  
           <button type="button" id ="resetID" name="resetID"  style="margin-left: 10px;"  class="btn btn-warning" onclick="resetFormHelp();return false;">Reset</button> 
          </div>
          </td>
          <td width="1%">&nbsp;</td>
        </tr>        
        <tr><td>&nbsp;</td></tr>
         <tr><td><label style="color:#000"><b>Save search as</b></label></td>
        <td><input style='width:70%' type="text" class="span3" name="searchDesc" id="searchDesc" >
        		<a class="btn btn-warning" style="margin-bottom: 10px;text-align: center;height: 18px;width: 4%" href="#" onclick="saveSearchHis();return false;"><i class="icon-ok-circle"></i></a>
        </td>
        <td style="text-align: right;">&nbsp;<a href="#" onclick="downloadCSV();" style="color: #000;">Download Result as CSV</a>&nbsp;</td>
        </tr>
              </table>
    </div>
    </fieldset>
</div>   
		</form>
					<!-- result display ID -->
<div id="uploadDynamicGrid" ></div>
    
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
		/* language: 'pt-BR' */
            $('#datetimepickerID').datetimepicker({
      		  pickTime: true,
      		language: 'en',
      pick12HourFormat: false
  
      			}); 
    $('#datetimepickerID1').datetimepicker({
      		  pickTime: true,
      		  language: 'en',
      pick12HourFormat: false
      			}); 
    </script>
    	 <script  src="js/paginationjQuery.js"> </script> 