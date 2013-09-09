<%@page import="org.json.JSONArray"%>
<%@page import="com.schooltrix.dtos.SMSCapmaignDto"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	    pageEncoding="ISO-8859-1"%>
<%@page import ="java.util.*" %>   


	    <link href="css/bootstrap.css" rel="stylesheet">
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


	<%
	
	SMSCapmaignDto dto = (SMSCapmaignDto) request.getAttribute("dto");
	System.out.println(dto.getSmsbody()+":::::sms body");

	String sms_notifi = dto.getSms_notif() != null?dto.getSms_notif() : "";

	String notifiSub = dto.getNotiSub() != null?dto.getNotiSub() : "";
	
	String smsBody = dto.getSmsbody() != null?dto.getSmsbody() : "";
	String totalChars = dto.getLimit() != null?dto.getLimit() : "";
	String smsCredits = dto.getSMSCredit() != null?dto.getSMSCredit() : "0";
	String numberOfSms = dto.getSMSCount() != null?dto.getSMSCount() : "0";
	
	String toWhome = dto.getSelectAll() != null?dto.getSelectAll() : "";
	String toClass = dto.getSelectClass() != null?dto.getSelectClass() : "";
	
	
	String balanceSMScredits = null;
		try{
		System.out.println(smsCredits+"::"+numberOfSms);
			balanceSMScredits =( Integer.parseInt(smsCredits)-Integer.parseInt(numberOfSms))+"";
			if(( Integer.parseInt(smsCredits)-Integer.parseInt(numberOfSms))<0){
			balanceSMScredits="0";
			}
			
		}catch(Exception e){
			System.out.println(smsCredits+":exxxxxxx:"+numberOfSms);
			balanceSMScredits = "0";
			e.printStackTrace();
		}
	
	 %>
	
	<%JSONArray userdata =null;
	try{
	//request.setAttribute("usersData1",(ArrayList<String[]>) request.getAttribute("usersData"));
		userdata  =(JSONArray) request.getAttribute("usersData");
	}catch(Exception ex){
	}
	
	 %>

	    <script 	src="<%=request.getContextPath()%>/dwr/interface/SentSMSDWR.js"></script>
	    
	<script type="text/javascript">
	function onloadMethods(){
	var limiii = '<%=totalChars%>';
	
		document.getElementById("limit").innerHTML=limiii;
	
	var sms_nift = '<%=sms_notifi%>';
	//alert(sms_nift+"--------"+limiii);
	//smsRadio,notifictionRadio,smsnotificationRadio
	if (sms_nift == "smsRadio") {
		document.getElementById("notificationOR").style.display='none';
		document.getElementById("smsOR").style.display="table-row";
		document.getElementById("smsOR2").style.display='table-row';
	} else if(sms_nift == "notifictionRadio"){
		document.getElementById("smsOR").style.display='none';
		document.getElementById("smsOR2").style.display='none';
		document.getElementById("notificationOR").style.display="table-row";
	} else if(sms_nift == "smsnotificationRadio"){
		document.getElementById("smsOR").style.display='table-row';
		document.getElementById("smsOR2").style.display='table-row';
		document.getElementById("notificationOR").style.display="table-row";
	}
	}
	
		function okAlertDialog () {
			$("#idAlertDialog").modal ('hide'); 
		};
		function alertDialog (prompt) {
			document.getElementById ("idAlertDialogPrompt").innerHTML = prompt;
			$("#idAlertDialog").modal ("show");
		}
	function previewSMSValidate1(){
	var body =/^[A-Za-z0-9].+$/;
	var subj			=  jQuery("#sms_body").val().match(body);
		if(!subj){
			alertDialog('Please enter valid SMS text');
			return false;
		}
/* 	var  sentUsersCount 	= jQuery("#sent_Sms_Count").val();
	alert("ll**"+sentUsersCount.length);
		if (!sentUsersCount.length >0) {			
	alert("YYY**"+sentUsersCount.length);
			alertDialog('No Recipients');
		} */
		
	}
	
	   function sendSmS(){
		alert('preview');
			
		}
		
		  function closepopup()
   {
      if(false == myWindow.closed)
      {
         myWindow.close ();
      }
      else
      {
         alert('Window already closed!');
      }
   }
		
	function tellMeMore(call){
	
	window.opener=self; window.close();
		var  myWindow = window.open("", "Schooltrix", "height=600,width=800,scrollbars=1,location=no,menubar=no,resizable=1,status=no,toolbar=no");
	//	closepopup();

		
		var dtt = '<%=userdata%>';
		var performence_json	=  jQuery.parseJSON(dtt);
	
		var innerHtmlone='<table width="100%" cellspacing="1" cellpadding="0" border="0"><tr><th width="15%" style="font-size:12px;border:1px solid #d7d7d7; background:#d7d7d7;line-height:25px;text-align:center; padding-left:5px"><strong>Name</strong></th><th width="15%" style="font-size:12px;border:1px solid #d7d7d7;background:#d7d7d7;line-height:25px;text-align:center;"><div align="center"><strong>Mobile Number</strong></div></th></tr>';
	
	for ( var i = 0; i < performence_json.length; i++) {
 		 	var obj = performence_json[i];
		    var nameU      =  obj.First_Name+" "+obj.Last_Name;
			var numberU	    =	obj.Mobile;	
			innerHtmlone += ' <tr>';
			innerHtmlone +='    <td style="font-size:12px;border:1px solid #d7d7d7;line-height:25px;text-align:left;"><div align="center">'+nameU+'</div></td>';
			innerHtmlone +='    <td style="font-size:12px;border:1px solid #d7d7d7;line-height:25px;text-align:left;"><div align="center">'+numberU+'</div></td>';
			innerHtmlone +='  </tr>';	    
	}
		myWindow.document.write(innerHtmlone);
 }
		
		  function closepopup()
   {
   

      if(false == my_window.closed)
      {
         my_window.close ();
      }
      else
      {
         alert('Window already closed!');
      }
  
    
   }
	function limiter(){
	var tex = document.smsSent.sms_body.value;
	var len = tex.length;
/* 	if(len >120){
	        tex = tex.substring(119,count);
	        document.smsSent.comment.value =tex;
	        return false;
	} */
	document.getElementById("limit").innerHTML=len;
	//document.smsSent.limit.value = len;
	}
	
	function sendSMSCall(){
	
		var retrnvalue=previewSMSValidate1();
			if(retrnvalue == "false" || retrnvalue == false)
			{
			return false; 
			}
				var  patName 	= /^[a-zA-Z \']{3,25}$/;
				var  patZipCode	= /^[0-9]{3,6}$/;
				
				var  smsBodyText 	= jQuery("#sms_body").val();
				var  sentUsersCount 	= jQuery("#sent_Sms_Count").val();
				var  smsBalcredits 	= jQuery("#sms_Balcredits").val();
				var 	usersList				 = '<%=userdata%>';
				
				
				var Nsub = jQuery("#notif_subj").val();//this is for nitification
				
				var BM_ID ='<%=dto.getBM_ID()%>';
				var SM_ID ='<%=dto.getSM_ID()%>';
				
				
				var ipaddr = '<%=request.getRemoteAddr()%>';				
	
			//	alert(IM_ID+"--"+SM_ID+"--"+BM_ID+"-*"+UM_ID+"^^"+ipaddr);
				
			// if (alertDialog('Do you want to send the SMS now. Yes | No'))
		/* 	 if (confirm('Do you want to send the SMS now. Yes | No'))
			    {
			         */
//smsRadio,notifictionRadio,smsnotificationRadio
	var sms_nift = '<%=sms_notifi%>';
    if(sms_nift=="smsRadio"){
			   
				try{				
				        SentSMSDWR.sendSMSCall(	smsBodyText, sentUsersCount, smsBalcredits, 	usersList, BM_ID,	 SM_ID,	ipaddr,				                                 
					                                   function(data){
					                                   document.location ="Access.action?p1=UserSMS";
						                                   //if (data =='success') {document.location ="Access.action?p1=UserSMS"; }else if (data =='No Users'){document.location ="Access.action?p1=UserSMS";}else{ document.location ="Access.action?p1=UserSMS"; }
						                                    }
					                              ) ;    
					 }catch(e){
							 alert("incatch::"+e);
	        	     }
				 	
		}else   if(sms_nift=="notifictionRadio"){				 	
		 	try{
				var toWhome = '<%=toWhome%>';
				var toClass = '<%=toClass%>';
				        SentSMSDWR.sendNotififcation(SM_ID,	 BM_ID, smsBodyText, Nsub, toWhome,	toClass,	usersList, ipaddr,				                                 
					                               function(data){
					                                   document.location ="Access.action?p1=UserSMS";
						                                   //if (data =='success') {document.location ="Access.action?p1=UserSMS"; }else if (data =='No Users'){document.location ="Access.action?p1=UserSMS";}else{ document.location ="Access.action?p1=UserSMS"; }
						                                    }
					                              ) ;    
					 }catch(e){
							 alert("incatch::"+e);
	        	     }
				 	
				 	
		}else   if(sms_nift =="smsnotificationRadio"){				 	
		 	try{
				 	var toWhome = '<%=toWhome%>';
					var toClass = '<%=toClass%>';
					
				        SentSMSDWR.sendSMSNotificationCall(smsBodyText,	sentUsersCount,	smsBalcredits,	usersList,	Nsub,	toWhome,	toClass, BM_ID, SM_ID, ipaddr,				                                 
					                                 function(data){
					                                   document.location ="Access.action?p1=UserSMS";
						                                   //if (data =='success') {document.location ="Access.action?p1=UserSMS"; }else if (data =='No Users'){document.location ="Access.action?p1=UserSMS";}else{ document.location ="Access.action?p1=UserSMS"; }
						                                    }
					                              ) ;    
					 }catch(e){
							 alert("incatch::"+e);
	        	     }
				 	
				 	
				 	}
				 	
		
				
				 	
			   /*  }else{
			 //  alertDialog("Thanx");
			    document.location ="Access.action?p1=UserSMS";
			    }		 */							
				
			}
	
		    function test(){
	    
	    alert("hjhjhj"+document.getElementById("sms_Ccredits").value);
	    }
	
	</script>
	
	<div style="height:80px;"></div>
<div class="reg_mainCon">
  <form  method="post"  name="smsSent">
    <fieldset>
    <legend><img src="img/list_add_user.PNG" class="img-circle">&nbsp;&nbsp;
 
    <%
//smsRadio,notifictionRadio,smsnotificationRadio
    if(sms_notifi.equalsIgnoreCase("smsRadio")){
    %>
     SMS Preview    
    <%        
    }else  if(sms_notifi.equalsIgnoreCase("notifictionRadio")){
    %>    
    Notification Preview
    <%
        }else{
        %>
        SMS/Notification Preview        
        <%
        }
        %>
   </legend>
    <div style="padding:20px;">
      <label style="color:#000;"></label>
   <table width="100%" border="0" cellspacing="0" cellpadding="0">
   
   		<%-- <tr>
             <td colspan="2"><label style="color:#000;text-align:right;"><b>SMS Credits:</b>
             <input type="text" placeholder=""  style="width:55px;margin-right:20px;" disabled="disabled" name="sms_credits" id="sms_credits" value="<%=smsCredits%>"	></label></td>
        </tr> --%>
        <tr>
                <td><label style="color:#000;"><b>Message</b></label></td>
                <td><textarea style="width:100%;" rows="3"  name="sms_body" id="sms_body" onkeyup="limiter();" onload="limiter();" wrap="physical" ><%=smsBody %></textarea></td>
        </tr>
        <tr>
                <td><label style="color:#000;"><b>Total Characters</b></label></td>
                <td>      <div style="float:left; color:#333333;padding-bottom: 5px;"><span id='limit' ></span></div>  
                  <%--   <script type="text/javascript">
document.write("<input type=text style=width:55px; name=limit size=4 readonly value='<%=totalChars%>'>");
</script> --%></td>
        </tr>
           <tr id="notificationOR">
             <td><label style="color:#000;"><b>Notification Subject</b></label></td>
                <td><input class="span4" style="width:100%;" type="text" placeholder="" disabled="disabled"  name="notif_subj" id="notif_subj" value="<%=notifiSub%>"></td>
        </tr>
        <tr>
               <td><label style="color:#000;"><b>Number of Recipients</b></label></td>
               <td><input class="span4" type="text" style="width:55px;"  placeholder="" disabled="disabled" name="sent_Sms_Count" id="sent_Sms_Count" value="<%=numberOfSms %>">
                <a style="color:#000000;" href="#" onclick="tellMeMore('audience'); return false">View audience</a></td>
        </tr>
          <tr id="smsOR">
             <td><label style="color:#000;"><b>Current SMS Credits</b></label></td>
                <td><input class="span4" style="width:55px;"  type="text" placeholder="" disabled="disabled"  name="sms_Ccredits" id="sms_Ccredits" value="<%=smsCredits %>"></td>
        </tr>
         <tr id="smsOR2">
             <td><label style="color:#000;"><b>Balance SMS Credits</b></label></td>
                <td><input class="span4" style="width:55px;" type="text" placeholder="" disabled="disabled"  name="sms_Balcredits" id="sms_Balcredits" value="<%=balanceSMScredits%>"></td>
        </tr>
        
  <tr>
            <td style="border:none;text-align:center;" colspan="2">
                     <button type="button" class="btn"  onclick="sendSMSCall();return false">
                         <%
//smsRadio,notifictionRadio,smsnotificationRadio
    if(sms_notifi.equalsIgnoreCase("smsRadio")){
    %>
     Send SMS    
    <%        
    }else  if(sms_notifi.equalsIgnoreCase("notifictionRadio")){
    %>    
    Send Notification
    <%
        }else{
        %>
        Send SMS/Notification       
        <%
        }
        %>
        </button>
                   
                    </td>
        </tr>

       </table>

    </div>
    </fieldset>
  </form>
</div>

   <div id="idAlertDialog"    class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="windowTitleLabel" aria-hidden="true">
            <img src="img/alert.gif" width="34" height="28" alt="alert icon">
            <h3 id="idAlertDialogPrompt"></h3>
            <a href="#" class="btn btn-primary" onclick="okAlertDialog ();">OK</a>
            </div>
   
   <script type="text/javascript">
<!--
onloadMethods();
//-->
</script>         
            
            
            