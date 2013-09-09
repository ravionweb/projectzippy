
 function schoolEditDataArrange() {
	 
	 		var schoolObj = jsonForEditBeforeParse;
	 		var activeST = schoolObj.active;
			 jQuery("#sName").val(schoolObj.schoolName);
			jQuery("#ssName").val(schoolObj.shortName);
			jQuery("#sAddress").val(schoolObj.addr);
			jQuery("#sCity").val(schoolObj.city);
			jQuery("#sContPerson").val(schoolObj.contactPerson);
			jQuery("#sEmail").val(schoolObj.email);
			jQuery("#sMobile").val(schoolObj.mobile);
			jQuery("#sLandline").val(schoolObj.landline);
			
		 jQuery("#sState").val( schoolObj.stateID ).attr('selected',true);
		 jQuery("#sCountry").val( '1' ).attr('selected',true);//manual ..presently
		 
		 if (activeST == "Y" || activeST == "y") {
			 $('input[name=active]').attr('checked', true);
			}else{
				$('input[name=active]').attr('checked', false);
			}
}
 


// code for alert box
function okAlertDialog () {
	errorspan
	};
function alertDialog (prompt) {
	document.getElementById ("idAlertDialogPrompt").innerHTML = prompt;
	$("#idAlertDialog").modal ("show");
	}
//flag 0,1 but here no diff ..prasently..2013-07-12
	function validateSchoolMasterForm(flag){
		var patEmail		=/^\w+([-+.']\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/;			
		var patName		=/^[a-zA-Z \']{3,25}$/;
		var patAddress	=/^[a-z-A-Z][a-zA-Z0-9\s,'-.]*$/;///^[a-z-A-Z][a-zA-Z0-9\s,'-.]*$/
		var num				= /^[0-9-+ ]{8,20}$/;	
		var patSchoolname 		= "/^([a-zA-Z]+\s)*[a-zA-Z]*['. ]?[ ]?([a-zA-Z]+[.]?\s?)*[a-zA-Z]+$/";//later change
		
		var sName			= jQuery("#sName").val();
		var ssName		= jQuery("#ssName").val();
		var sAddress		= jQuery("#sAddress").val();
		var sCity			= jQuery("#sCity").val();
		var sCountry		= jQuery("#sCountry").val();
		var sState			= jQuery("#sState").val();
		var sContPerson	= jQuery("#sContPerson").val();
		var sEmail			= jQuery("#sEmail").val();
		var sMobile		= jQuery("#sMobile").val();
		var sLandline		= jQuery("#sLandline").val();
		
		var  sIsActive;
		 if ($('#sIsActive').is(":checked"))
					{sIsActive='Y';
					  // it is checked
		} else{sIsActive='N';}
	
		if (sName.search(/^([a-zA-Z]+\s)*[a-zA-Z]*['. ]?[ ]?([a-zA-Z]+[.]?\s?)*[a-zA-Z]+$/) == -1 || sName =="") {
				  // alert("Enter valid school Name");
				   setError("sName","Enter valid school Name");
	             jQuery("#sName").focus();
		         return false;
	          }
		
		    //--------------------------
		    if (ssName.search(/[^a-zA-Z]+/) != -1 || ssName =="") {
				  // alert("Short Name should contain only characters");
				   setError("ssName","Short Name should contain only characters");
	              jQuery("#ssName").focus();
		         return false;
	          }
	          //if (insAddr.search(/^[a-zA-Z0-9\s,'-]*$/) != -1 || insAddr =="") {
	          if (sAddress.search(/[a-z-A-Z][a-zA-Z0-9\s,'-.#@;:$]*$/)== -1 || sAddress =="") {
				   //alert("Enter address in valid format");
				   setError("sAddress","Enter address in valid format");
	              jQuery("#sAddress").focus();
		         return false;
	          }
	          if (sCity.search(/^[a-zA-Z][a-zA-Z ]*['.]?[a-zA-Z ]+[a-zA-Z ]+$/) == -1 || sCity =="") {
				 //  alert("Enter valid City Name");
				   setError("sCity","Enter valid City Name");
	             jQuery("#sCity").focus();
		         return false;
	          }
	
			if(sCountry == -1){
				setError("sCountry","Please enter valid country");
				jQuery("#sCountry").focus();
				return false;
			}
	      	if(sState==-1){
				setError("sState","Please select valid state");
				jQuery("#sState").focus();
				return false;
			}
			if(sContPerson.search(/^[a-zA-Z]([a-zA-Z]+\s)*[a-zA-Z]+$/) == -1 || sContPerson ==""){
				setError("sContPerson","Please enter valid contact name");
				jQuery("#sContPerson").focus();
				return false;
			}
			if(sEmail.search(patEmail) == -1 || sEmail ==""){
				setError("sEmail","Please enter valid email id");
				jQuery("#sEmail").focus();
				return false;
			}
			if(sMobile.search(/^[0]?[789]\d{9}$/) == -1 || sMobile ==""){
				setError("sMobile","Please enter valid mobile number");
				jQuery("#sMobile").focus();
				return false;
			}
			if(sLandline.search(/^\d{3,5}(\d{6,8})?$/) == -1 || sLandline ==""){
				setError("sLandline","Please enter valid landline number");
				jQuery("#sLandline").focus();
				return false;
			}	
			
		    if(sIsActive=="N"){
	            //alert("Please select check box");
	            setError("sIsActive","Please select check box");
	            jQuery("#sIsActive").focus();
	            return false;
	         }
	return true;
	
	}
	function saveSchoolMasterAndGo(flag){
		var retrnvalue=validateSchoolMasterForm(0);
		if(retrnvalue == "false" || retrnvalue == false)
		{
		return false; 
		}
		var  sName 	= jQuery("#sName").val();
		var  ssName 	= jQuery("#ssName").val();
		var  sAddress 	= jQuery("#sAddress").val();
		var  sCity 	= jQuery("#sCity").val();
		var  sState 	= jQuery("#sState").val();
		var  sCountry 	= jQuery("#sCountry").val();
		var  sContPerson 	= jQuery("#sContPerson").val();
		var  sEmail 	= jQuery("#sEmail").val();
		var  sMobile 		= jQuery("#sMobile").val();
		var  sLandline 	= jQuery("#sLandline").val();
		var  sIsActive;
		 if ($('#sIsActive').is(":checked"))
		{
					sIsActive='Y';
		} else{sIsActive='N';}
		try{
		        SchoolMasterDWR.saveSchoolMaster(
			                                   sName,
			                                   ssName,
			                                   sAddress,
			                                   sCity,
			                                   sState,
			                                   sCountry,
			                                   sContPerson,
			                                   sEmail,
			                                   sMobile,
			                                   sLandline,
			                                   sIsActive,
			                                   function(data){
			                                   if (data =='saved') {
												if(flag == 2){
			                                       document.location ="Access.action?p1=branchMaster&p2="+sName;
			                                       resetForm($('#schoolForm')); // by id, recommended
			                                       }else if(flag ==1){
			                                    	   alertDialog("Success.<br/>School Name:<br/>"+sName);
			                                    	   resetForm($('#schoolForm')); // by id, recommended
			                                     }
			                                       
												}else {
				                                   alert("exception::"+data);
	
												}
				                             }) ;    
			 }catch(e){
			 alert("incatch::"+e);
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
			errorDisp.animate({opacity: 0}, 4000);
	}

	 function resetForm($form) {
		    $form.find('input:text, input:password, input:file, textarea').val('');
		       jQuery("#sState").val('Andhra Pradesh');
		       jQuery("#sCountry").val('India');	    
		}

	 function editSchools() {
		 document.location = "Access.action?p1=EditSchools&p2=SA_inst2";       
	}
	 

		function saveEditedSchoolMasterAndGo(flag){//flag not using Presently
	
			var  sName 	= jQuery("#sName").val();
			var  ssName 	= jQuery("#ssName").val();
			var  sAddress 	= jQuery("#sAddress").val();
			var  sCity 	= jQuery("#sCity").val();
			var  sState 	= jQuery("#sState").val();
			var  sCountry 	= jQuery("#sCountry").val();
			var  sContPerson 	= jQuery("#sContPerson").val();
			var  sEmail 	= jQuery("#sEmail").val();
			var  sMobile 		= jQuery("#sMobile").val();
			var  sLandline 	= jQuery("#sLandline").val();
			/*var  sIsActive;
			 if ($('#sIsActive').is(":checked"))
			{
						sIsActive='Y';
			} else{sIsActive='N';}*/
			try{
			        SchoolMasterDWR.editSchoolMaster(jsonForEditBeforeParse.schoolID,
				                                   sName, ssName, sAddress, sCity, sState, sCountry, sContPerson,
				                                   sEmail, sMobile, sLandline, jsonForEditBeforeParse.active, function(data){
						                                   if (data =='saved') {
						                                	   sucessShow("Success. This window will close in ");
						                                	   jQuery('.reg_mainCon').hide();
							                                 
															}else {
							                                   alert("exception::"+data);				
														   }
					                             }) ;    
				 }catch(e){
				 alert("incatch::"+e);
			     } 		
			
		}
		
		var successDisp=null;
		var countdown;
		var countdown_number;
	function sucessShow(msg) {
	
		
		if(successDisp != null){
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
		successDisp.animate({opacity: 0}, 15000);
		  setTimeout('window.close()',9000);
		
	    countdown_number = 11;
	    countdown_trigger();
	}	

	function countdown_trigger() {
	    if(countdown_number > 1) {
	        countdown_number--;
	        document.getElementById('countDownDisp').innerHTML = countdown_number+" sec's";
	        if(countdown_number > 1) {
	            countdown = setTimeout('countdown_trigger()', 1000);
	        }
	    }
	}
	 
	function formSubmitBothSchools(valuee){
			//alert("999999999999"+forSubm+"**ID*"+jsonForEditBeforeParse.uploadID);	 
		if (forSchoolSubm == "EditOK") {		
				if(!validateSchoolMasterForm(1)){
					return false;		
				}
				saveEditedSchoolMasterAndGo(5);
			 }else{
					if(!validateSchoolMasterForm(1)){
						return false;		
					}
					saveSchoolMasterAndGo(valuee);//valuee---1
			 }
		}
