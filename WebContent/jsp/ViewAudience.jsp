<%@page import="com.schooltrix.dtos.SMSCapmaignDto"%>
<%@page import="java.util.*" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <script type="text/javascript" src="js/bootstrap-jquery.js"></script>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />

<title>School trix</title>
<script type="text/javascript">

function readingJson(){
	var dataofUesrs = '<%=request.getParameter("user")%>';
	var innerHtmlone='';
	/* 
		for (String[] object : dataofUesrs) {
			for(int i =0;i<object.length;i++){
			
				System.out.println(object[i]+":uuu:"+i);
				}
				System.out.println();
		} */
	//alert("in View Audiance"+dataofUesrs);
			for (i = 0; i < dataofUesrs.length(); i++) {
			   JSONObject item = userdata.getJSONObject(i);
			System.out.println("--FName---"+item.getString("First_Name"));
		}
		 
	
	
	     for(j=0;j<calls_array.length;j++){
	
	             var details = calls_array[j];
	             var emailid    =    details.emailid;
	             var phonenumber    =    details.phonenumber;
	            
	innerHtmlone += ' <tr>';
	innerHtmlone +='    <td style="font-size:12px;border:1px solid #d7d7d7;line-height:25px;text-align:left;"><div align="center">'+emailid+'</div></td>';
	innerHtmlone +='    <td style="font-size:12px;border:1px solid #d7d7d7;line-height:25px;text-align:left;"><div align="center">'+phonenumber+'</div></td>';
	innerHtmlone +='  </tr>';
	            
	         }
	         jQuery('#detailed_Performance').html(innerHtmlone);
 }

</script>
<%
/* 		ArrayList<String[]> userdata =(ArrayList<String[]>) request.getAttribute("usersData1");
		System.out.println(userdata);
		for (String[] object : userdata) {
			for(int i =0;i<object.length;i++){
			
				System.out.println(object[i]+":uuu:"+i);
				}
				System.out.println();
		} */
System.out.println(request.getParameter("user"));

 %>
</head>
<body onload="readingJson()">
<table width="100%" cellspacing="1" cellpadding="0" border="0">

<tr>    
<th width="15%" style="font-size:12px;border:1px solid #d7d7d7; background:#d7d7d7;line-height:25px;text-align:left; padding-left:5px"><strong>Email id</strong></th>
<th width="15%" style="font-size:12px;border:1px solid #d7d7d7;background:#d7d7d7;line-height:25px;text-align:left;"><div align="center"><strong>Phone Number</strong></div></th>
</tr>
<div id="detailed_Performance"></div>
</table>
</body>
</html> 