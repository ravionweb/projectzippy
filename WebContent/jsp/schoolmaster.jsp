	<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	    pageEncoding="ISO-8859-1"%>
   <%! 
	String forSchoolEditJson ;
	%> 

<%
String insName =(String) session.getAttribute("name");
      
        String forRef = (String) request.getParameter("p2");
        System.out.println(forRef+"-->forRef");
        System.out.println(forSchoolEditJson+"edit::"+session.getAttribute("forSchoolEdit"));
        
	        if(session.getAttribute("forSchoolEdit") != null && forRef.equalsIgnoreCase("Edit")){
	        forSchoolEditJson =(String) session.getAttribute("forSchoolEdit");        
	        }else{
	        //forEditJson = null;//obervation is req
	        }
	        System.out.println(forSchoolEditJson+"forSchoolEditJson");
	        if(forSchoolEditJson !=null)
				session.removeAttribute("forSchoolEdit"); 
         %>

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

<script type="text/javascript" src="dwr/interface/SchoolMasterDWR.js"></script>
<script type="text/javascript">
  var forSchoolSubm;
        
        <%
   		  if(forRef.equalsIgnoreCase("Edit")){%>
        		var jsonForEditBeforeParse = <%=forSchoolEditJson%>
        		forSchoolSubm = "EditOK";
        <%} %>
        
			
 $(document).ready(function() {
/* setTimeout(testSelectionOFmulti,1000); */
				try{
						if (jsonForEditBeforeParse !== undefined && jsonForEditBeforeParse !== null && jsonForEditBeforeParse !== "null" ) {
								
							jQuery('.navbar-inner').hide();//2013-07-07---window open...hide above login and menu...1
							jQuery('.footer').hide();//footer hide//2
							jQuery('#hidePurposeEditSchool').hide();//3
							jQuery('#saveAddBranch').hide();//3
							jQuery('#activeID').hide();//3
							setTimeout(schoolEditDataArrange,600);
						}
				}catch (e) {
				//alert(e);
				}

 });
</script>
<script type="text/javascript" src="js/schoolmaster.js"></script>
<!-- Static navbar -->
<div style="height:20px;text-align: center;"><span id ="errorspan" style="color:red;font-size: 14px;font-weight: bold;" >&nbsp;</span>
<span id ="countDownDisp" style="color:red;font-size: 14px;font-weight: bold;" >&nbsp;</span></div>
<div class="reg_mainCon">
  <form method="post" action="schoolmaster.action" id="schoolForm">
      <fieldset>
    <legend><img src="img/list_add_user.PNG" class="img-circle">&nbsp;&nbsp;Add School for <%=insName %></legend>
     <div style="padding:20px;">
      <table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr><td></td><td colspan="2" style="float: right;height: 20px">
      	<input type="button" class="btn btn-warning"  style="height: 30px" id='hidePurposeEditSchool'  value="Edit Previous Schools" onclick="editSchools()"></td></tr>
        <tr>
          <td><label  style="color:#000;"><b>School Name</b></label></td>
            <td><input  type="text" class="span4" style="width:312px;"  name="sName" id="sName" /></td>
          </tr>  
          <tr>
          	<td><label style="color:#000;"><b>School Short Name</b></label></td>
            <td><input  type="text" class="span4" style="width:312px;"  name="ssName" id="ssName"/></td>
        </tr>
        
        <tr>
          <td><label style="color:#000;"><b>Address</b></label></td>
           <td> <textarea class="span4" rows="3" style="width:312px;"   name="sAddress" id="sAddress"></textarea></td>
            </tr>
            <tr>
          <td><label style="color:#000;"><b>City</b></label></td>
           <td> <input  type="text" class="span4" style="width:312px;"  name="sCity" id="sCity" /></td>
        </tr>
         <tr>
          <td><label style="color:#000;"><b>Country</b></label></td>
            <td><select class="span4" style="width:312px;"  name="sCountry"  id="sCountry" ><!--  onchange="changeCountry(this.value)" -->
               <option value="India"  selected="selected">India</option><option value="Indonesia">Indonesia</option>
                    </select>           
        </tr>        
		<tr>
          <td><label style="color:#000;"><b>State</b></label></td>
            <!-- <td><input  type="text" name="sState" id="sState" /></td> -->
            <td><select id="sState" class="span4" name="sState" style="width:312px;"  >
            					<option value="-1" >Select</option>
                                 <option value="1" selected="selected">Andhra Pradesh</option>
                                <option value="2">Bihar</option>
                                <option value="3">Madya pradesh</option>
                            </select></td>
            </tr>     
        <tr>
          <td><label style="color:#000;"><b>Contact Person</b></label></td>
            <td><input  type="text" class="span4" style="width:312px;"  name="sContPerson" id="sContPerson" /></td>
            </tr>
            <tr>
          <td><label style="color:#000;"><b>Email Id </b></label></td>
           <td> <input  type="text" class="span4"  style="width:312px;"  name="sEmail" id="sEmail"/>
          </td>
        </tr>
        
        
        
		<tr>
          <td><label style="color:#000;"><b>Mobile</b></label></td>
            <td><input  type="text" class="span4" style="width:312px;"  name="sMobile" id="sMobile"/></td>
            </tr>
            <tr>
          <td><label style="color:#000;"><b>Landline</b></label></td>
          <td>  <input  type="text" class="span4" style="width:312px;"  name="sLandline" id="sLandline"/></td>
        </tr>
			<tr>
          <td colspan="2" id="activeID"><label style="color:#000;"><b>IsActive</b>&nbsp;&nbsp;&nbsp;&nbsp;
            <input  type="checkbox" class="span4"  name="sIsActive" id="sIsActive" checked="checked"/></label>
          </td>
          </tr>
          <tr><td>&nbsp;</td></tr>
        <tr>
          <td><button type="button" class="btn btn-warning"  style="float:right;margin-right: 25px; " onclick="formSubmitBothSchools(1);">Save</button></td>
            <td><button type="button" class="btn btn-warning" id='saveAddBranch'  onclick="saveSchoolMasterAndGo(2);" >Save and Add Branch</button></td>
        </tr>
      </table>
    </div>
    </fieldset>
  </form>
</div>

        <div id="idAlertDialog" class="modal hide fade" style="height: 200px;" tabindex="-1" role="dialog" aria-labelledby="windowTitleLabel" aria-hidden="true">
            <img src="img/alert.gif" width="34" height="28" alt="alert icon">
            <h3 id="idAlertDialogPrompt"></h3>
            <a href="#" class="btn btn-primary" style="margin-top: 0px;" onclick="okAlertDialog ();">OK</a>
            </div>     
  
