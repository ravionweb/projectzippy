	<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	    pageEncoding="ISO-8859-1"%>

   <%! 
	String forBranchEditJson ;
	%> 

<%
      
        String forRef 	= "";
        String par1		= "";
	 	if( request.getParameter("p2") != null){
    	   	
    	   	forRef 		= (String) request.getParameter("p2");
      		
	        if(session.getAttribute("forBranchEdit") != null && forRef.equalsIgnoreCase("Edit")){
	        forBranchEditJson =(String) session.getAttribute("forBranchEdit");        
	        }else{
	         	par1 		= forRef;//
	        }
	        System.out.println(forBranchEditJson+"forBranchEdit");
	        if(forBranchEditJson !=null)
				session.removeAttribute("forBranchEdit"); 
	   	//String tempShortname =(String) session.getAttribute("shortNameTemp");//this is for auto select of institute from InstitutionAdding
    }
         %>


<script type="text/javascript" src="js/branchmaster.js"></script>

<script type="text/javascript" src="dwr/interface/BranchMasterDWR.js"></script>
<script type="text/javascript" src="dwr/interface/SchoolMasterDWR.js"></script>
 <script type="text/javascript" src="js/bootstrap-radio.js"></script>
	    <style type="text/css">
	    .styledRadio {
			display: inline-block;
		}
		
		</style>

	<script type="text/javascript">
	 $(function(){
			$('input:checkbox').screwDefaultButtons({
				image: 'url("img/checkboxSmall.jpg")',
				width: 20,
				height: 20
			});
				});
	</script>
	<style type="text/css">
span.mandatory {
    color: #FF0000;
    display: inline;
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
                height: 185px;
                background-color: whitesmoke;
                }
             #idAlertDialog .btn {
                width: 70px;
                margin-bottom: 8px;
                margin-right: 15px;
                margin-top: 80px;
                float: right;
                }
             #idAlertDialog h3 {
                margin-left: 60px;
                margin-top:15px;
                }
             #idAlertDialog img {
                float: left;
                margin-left: 15px;
                margin-top: 15px;
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
				margin-top: 15px;
				}
</style>
	<script type="text/javascript">
	  var forBranchSubm;
      var jsonForEditBeforeParse;
        <%
   		  if(forRef.equalsIgnoreCase("Edit")){%>
        		jsonForEditBeforeParse = <%=forBranchEditJson%>
        		forBranchSubm = "EditOK";
        <%} %>
	
	
	   	    function getSchoolMaster(){
				//Process DWR/AJAX request here
				try{
					var listofschools = document.getElementById('sm_id_list');
					
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
					
					 	var shortNameSel ='<%=par1%>';
					 	//alert(shortNameSel);//here is missing
					 
		    		for (var i = 0; i < listofschools.options.length; i++) {
				        if (listofschools.options[i].text== shortNameSel) {
				            listofschools.options[i].selected = true;
				            return;
				      	}
    				}
		    
					
					 }catch(e){
					 alert("incatch::"+e);
					        jQuery.log.info(e.message);
					        jQuery("#infoError").html("&nbsp;");
	        	     } 		
				 										
				
			}
			 
	function onloadmethods(){
	 //   alert("in onload");
	 
//		resultPopUp();
//below code for edit branch
	    try{
						if (jsonForEditBeforeParse !== undefined && jsonForEditBeforeParse !== null && jsonForEditBeforeParse !== "null" ) {
							jQuery('.navbar-inner').hide();//2013-07-07---window open...hide above login and menu...1
							jQuery('.footer').hide();
							jQuery('#hidePurposeEdit').hide();//3
							jQuery('#schollSecletionEdit').hide();
							jQuery('#copyfromSchoolEdit').hide();
							jQuery('#activeID').hide();
							setTimeout(branchEditDataArrange,600);
						}else{
							getSchoolMaster();
						}
						
				}catch (e) {
				//alert(e);
				}
	    }
			
			
			
</script>

<div style="height:50px;"></div>
<div style="height:20px;text-align: center;"><span id ="errorspan" style="color:red;font-size: 14px;font-weight: bold;" >&nbsp;</span>
<span id ="countDownDisp" style="color:red;font-size: 14px;font-weight: bold;" >&nbsp;</span></div>
<div class="reg_mainCon">
  <form action="school.action" method="post" id="branchFrom">
    <fieldset>
    <legend><img src="img/list_add_user.PNG" class="img-circle">&nbsp;&nbsp;Add
        Branch for School</legend>
    <div style="padding:20px;padding-top: 0px;">
  <table width="100%" border="0" cellspacing="0" cellpadding="0">
         <tr>
         <td colspan="4" style="height: 30px">
      		<input type="button" class="btn btn-warning"  style="float: right;height: 30px" id="hidePurposeEdit"  value="Edit Previous Branches" onclick="editBranches()">
      		</td>
      		</tr>
           <tr id='schollSecletionEdit'>
	          <td>
	          <label style="text-align:center;color:#000;"><b>School Name</b></label>         
	          </td>			
			<td colspan="3"><select id="sm_id_list" style='margin-left:  -143px;' name="sm_id_list" class="span3"><!--  onchange="changeCountry(this.value)" -->
               <option selected="selected" value="-1">Select</option>
              </select> </td>
		</tr>       
      <!--    <tr><td colspan="4" style="padding-left: 260px;"><span id ="errorspan" style="color:red;font-size: 14px;" ></span>&nbsp;</td></tr> -->
        <!--  <tr><td>&nbsp;&nbsp;</td></tr> -->
            <tr>
                <td>
                    <label style="color:#000;"><b>Branch Name</b></label>
                  <input class="span4" type="text" placeholder="" style="width:305px;"  name="br_sname" id="br_sname">                </td>
                  <td>&nbsp;</td>
          <td>
                    <label style="color:#000;"><b>Branch Short Name</b></label>
                    <input class="span4" type="text" placeholder="" style="width:305px;"  name="br_ssname" id="br_ssname">     
                             </td>
        </tr>
        <tr> <td>&nbsp;</td></tr>
         <tr id='copyfromSchoolEdit'>
         <td>&nbsp;</td>
         <td>&nbsp;</td>
                <td  ><input class="span4" type="checkbox" placeholder=""  name="br_chkbox" id="br_chkbox"  onclick="copyFromSchoolCall();return false;">
                 <label style="color:#000;"><b>Copy from School </b></label> 
                </td>
            </tr>
            <tr>
                <td colspan="3">
                <label style="color:#000;"><b>Address</b></label>     
                <textarea  rows="3" style="width:707px;" name="br_address" id="br_address"></textarea>
                </td>
            </tr>
            <tr>
                <td>
                    <label style="color:#000;"><b>City</b></label>
                    <input class="span4" type="text" placeholder="" style="width:305px;"  name="br_city" id="br_city">                </td>
                     <td>&nbsp;</td>
                <td>
                    <label style="color:#000;"><b>Country</b></label>
                    <select style="width: 315px;" type="text" placeholder=""  name="br_country" id="br_country"  > <!--  onchange="changeCountry(this.value)" -->
                    <option value="Abkhazia">Abkhazia</option>
                     <option value="India" selected="selected">India</option>
                    </select>                </td>
            </tr>
            <tr>
                <td>

                     <!--    <div id="displayStateinput" style="display:none;">
                            <label style="color:#000;"><b>State</b>
                            </label><input type="text" class="span4"
                                id="br_state_input" name="br_state_input" />
                        </div> -->

                        <div id="displayStateselection">
                            <label style="color:#000;"><b>State</b>
                            </label><select id="br_state_selection"
                                name="br_state_selection" style="width:315px;">
                                 <option value="1" selected="selected">Andhra Pradesh</option>
                                <option value="2">Bihar</option>
                                <option value="3">Madya pradesh</option>
                            </select>
                        </div></td> <td>&nbsp;</td>
                        <td>
                    <label style="color:#000;"><b>Contact Person</b></label>
                    <input class="span4" type="text" placeholder="" style="width:305px;"  name="br_cperson" id="br_cperson">                </td>
            </tr>
            <tr>
                <td>
                    <label style="color:#000;"><b>Email ID</b></label>
                    <input class="span4" type="text" placeholder="" style="width:305px;"  name="br_email" id="br_email">                </td> <td>&nbsp;</td>
                <td>
                    <label style="color:#000;"><b>Mobile</b></label>
                    <input class="span4" type="text" placeholder="" style="width:305px;" name="br_mobile" id="br_mobile">                </td>
            </tr>
            <tr>
                <td>
                    <label style="color:#000;"><b>Landline</b></label>
                    <input class="span4" type="text" placeholder="" style="width:305px;" name="br_landline" id="br_landline">                </td> <td>&nbsp;</td>
                <td id="activeID">
                    <label style="color:#000;"><b>Active</b></label>                    
                    <input type="checkbox"  name="br_active" id="br_active" checked="checked">
                    </td>
            </tr>
            <tr><td>&nbsp;</td> </tr>
            <tr>
            <td style="border:none;text-align:center; padding-right: 123px;" colspan="3" >
                        <button type="button" class="btn"
                            onclick="formSubmitBothBranch(); return false;">Save</button> 
<!--                             onclick="saveBranchMaster(); return false;">Save</button>  -->
                    </td>
        </tr>
        </table>

    </div>
    </fieldset>
  </form>
</div>


   <div id="idAlertDialog" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="windowTitleLabel" aria-hidden="true">
            <img src="img/alert.gif" width="34" height="28" alt="alert icon">
            <h3 id="idAlertDialogPrompt"></h3>
            <a href="#" class="btn btn-primary" onclick="okAlertDialog ();">OK</a>
            </div>
            
            <div id="idConfirmDialog" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="windowTitleLabel" aria-hidden="true">
			<img src="img/alert.gif" width="34" height="28" alt="confirm icon">
			<h3 id="idConfirmDialogPrompt"></h3>
			<a href="#" class="btn btn-primary" style="margin-top: 60px;" onclick="okConfirmDialog ();">Yes</a>
			<a href="#" class="btn" style="margin-top: 60px;"onclick="closeConfirmDialog ();">No</a>
			</div>

<script type="text/javascript">
 onloadmethods();
 </script>
