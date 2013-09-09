<%@page import="org.json.JSONArray"%>
<%@page import="com.schooltrix.dtos.EmailCapmaignDto"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	    pageEncoding="ISO-8859-1"%>
<%@page import ="java.util.*" %>   
	    <script 	src="<%=request.getContextPath()%>/dwr/interface/SentEmailDWR.js"></script>
	<%
	EmailCapmaignDto dto = (EmailCapmaignDto) request.getAttribute("EmailDto");
	
	String emailBodyDto = 	dto.getEbody() != null?dto.getEbody() : "";
	String FromIdDto		=		dto.getFrmmail();
	String subDto 			=		dto.getSubj();
	String emailCount 	= 		dto.getEmailCount();
	String eeee = emailBodyDto.replaceAll("\"","\\\\\"");	
	String eeee11 =emailBodyDto.replaceAll("\"","\\\\\"").replaceAll("\n", "").replaceAll("\r", "");
/* 	String eeee11 =eeee.replaceAll("\n", "").replaceAll("\r", ""); */
	System.out.println("ghgeeee11111111---"+eeee11);
	
	
	 %>
	
	<%JSONArray userdata =null;
	String count="0";
	try{
	//request.setAttribute("usersData1",(ArrayList<String[]>) request.getAttribute("usersData"));
		userdata  =(JSONArray) request.getAttribute("usersData");
		count  =(String) request.getAttribute("count");
	}catch(Exception ex){
	}
	
	 %>
		
	    <link href="css/bootstrap.css" rel="stylesheet">
	    <script type="text/javascript">
	    function onload() {
	    var sss="<%=eeee11%>";
	    <%-- 
			var emailBodyText = '<%=emailBodyDto%>'';
			alert( '<%=emailBodyDto%>');
			alert(emailBodyText);
			document.getElementById("comment").innerHTML = emailBodyText; --%>
			jQuery("#comment").html(sss);
		}
		
	function sendEmail(){
	var retrnvalue=previewEmailValidate1();
			if(retrnvalue == "false" || retrnvalue == false)
			{
			return false; 
			}
	
				var  emailBodyText 	= '<%=eeee11%>';
				var  fromEmailId 	=  ' <%=FromIdDto%> ';
				var  emailSubject 	=  ' <%=subDto%> ';
				var  sentUsersCount 	=  ' <%=emailCount%> ';
				var  usersList 	=  ' <%=userdata%> ';
				var IM_ID ='<%=session.getAttribute("IM_ID")%>';
				var BM_ID ='<%=dto.getBM_ID()%>';
				var SM_ID ='<%=dto.getSM_ID()%>';
				
				
				      if (confirm('Do you want to send the Email now. Yes | No'))
					    {
					        
						//Process DWR/AJAX request here
						try{
					
						        SentEmailDWR.sendEmailCall(
						        								fromEmailId,
						        								emailSubject,
						        								emailBodyText,
							                                    sentUsersCount,							                                  
							                                  	 usersList,
							                                   IM_ID,
							                                   BM_ID,
							                                   SM_ID,					                                 
							                                   function(data){
							                                   if (data =='success') {
							                                   alert("Success");
															document.location ="Access.action?p1=UserEmail";
														
																} else if (data =='No Users'){
								                                   alert("No Users");
								                                   document.location ="Access.action?p1=UserEmail";
																}else{
								                                   alert("Server Error");
																}
							                                   }
							                              ) ;    
							 }catch(e){
									 alert("incatch::"+e);
							        jQuery.log.info(e.message);
							        jQuery("#infoError").html("&nbsp;");
					    document.location ="Access.action?p1=UserEmail";
			        	     } 		
				 	
					    }else{
					    alert("Thanx");
					    document.location ="Access.action?p1=UserEmail";
					    }							
				
			}
		
function tellMeMore(call){
	
		var myWindow = window.open("", "Schooltrix", "height=600,width=800,scrollbars=1,location=no,menubar=no,resizable=1,status=no,toolbar=no");
	
		
		var dtt = '<%=userdata%>';
		var performence_json	=  jQuery.parseJSON(dtt);
	
		var innerHtmlone='<table width="100%" cellspacing="1" cellpadding="0" border="0"><tr><th width="15%" style="font-size:12px;border:1px solid #d7d7d7; background:#d7d7d7;line-height:25px;text-align:center; padding-left:5px"><strong>Name</strong></th><th width="15%" style="font-size:12px;border:1px solid #d7d7d7;background:#d7d7d7;line-height:25px;text-align:left;"><div align="center"><strong>Email Id</strong></div></th></tr>';
	
	for ( var i = 0; i < performence_json.length; i++) {
 		 	var obj = performence_json[i];
		    var nameU      =  obj.First_Name+" "+obj.Last_Name;
			var email	    =	obj.Email;	
			innerHtmlone += ' <tr>';
			innerHtmlone +='    <td style="font-size:12px;border:1px solid #d7d7d7;line-height:25px;text-align:left;"><div align="center">'+nameU+'</div></td>';
			innerHtmlone +='    <td style="font-size:12px;border:1px solid #d7d7d7;line-height:25px;text-align:left;"><div align="center">'+email+'</div></td>';
			innerHtmlone +='  </tr>';	    
	}
		myWindow.document.write(innerHtmlone);
		}
		
		
		function previewEmailValidate1(){


			var patEmail	= /^([\w\-\.]+)@((\[([0-9]{1,3}\.){3}[0-9]{1,3}\])|(([\w\-]+\.)+)([a-zA-Z]{2,4}))$/;					
			var body =/^[A-Za-z0-9].+$/;
			
			var frmmail			= jQuery("#frmmail").val().match(patEmail);
			var subj			=  jQuery("#subj").val().match(body);
			var sent_Sms_Count			=  jQuery("#sent_Sms_Count").val();
				if (sent_Sms_Count <=0) {
					alertDialog('No Recipients');
					//setTimeout(document.location ="Access.action?p1=UserEmail", 300000);
					setTimeout(function() { document.location ="Access.action?p1=UserEmail";}, 3000);
					 //document.location ="Access.action?p1=UserEmail";
					return false;
				}
						
				if(!frmmail){
					alertDialog('Please enter valid from mail');
					return false;
				}if(!subj){
					alertDialog('Please enter valid subject');
					return false;
				}
		//one more required 2013-04-22
		//empty subject or empty body u want to submit

	}
	function okAlertDialog () {
	$("#idAlertDialog").modal ('hide'); 
	};
function alertDialog (prompt) {
	document.getElementById ("idAlertDialogPrompt").innerHTML = prompt;
	$("#idAlertDialog").modal ("show");
	}
		
	</script>
	
	<div style="height:80px;"></div>
<div class="reg_mainCon">
  <form  method="post"  name="previewEmail ">
    <fieldset>
    <legend><img src="img/list_add_user.PNG" class="img-circle">&nbsp;&nbsp;Email Preview</legend>
    <div style="padding:20px;">
      <label style="color:#000;"></label>
   <table width="100%" border="0" cellspacing="0" cellpadding="0">
   
   		
   		  <tr>
                <td colspan="1">
                    <label style="color:#000;"><b>From email id</b></label></td>
                   <td><input class="span4" type="text" placeholder=""  name="frmmail" id="frmmail" value="<%=FromIdDto%>">  
                  </td>
        
        </tr>
          <tr>
                <td colspan="1">
                    <label style="color:#000;"><b>Subject</b></label></td>
                 <td>  <input class="span4" type="text" placeholder=""  name="subj" id="subj" value="<%=subDto%>">  
                  </td>      
        </tr>  
        <tr>
                <td colspan="1"><label style="color:#000;"><b>Email Body</b></label></td>

				<!-- <td><textarea style="width:96%;" rows="8"  name="comment" id="comment"  wrap="physical"></textarea></td> -->  
				<td><div id="comment" style="width: 600px;height: 600px; border: 1px;border-style: solid;"></div>
				</td>
		 </tr>
        <tr>
        </tr>
        <tr><td>&nbsp;</td></tr>
         <tr>
             <td><label style="color:#000;"><b>No of Emails to be sent</b></label></td>
			 <td><input class="span4" type="text" style="width:55px;"  placeholder="" disabled="disabled" name="sent_Sms_Count" id="sent_Sms_Count" value="<%=emailCount %>">
			<a style="color:#000000;" href="#" onclick="tellMeMore('audience'); return false">View audience</a></td>
		 </tr>        
  		<tr>
            <td style="border:none;text-align:center;" colspan="2">
                        <button type="button" class="btn"
                            onclick="sendEmail(); return false;">Send Email</button> 
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
<script>
onload();
</script>