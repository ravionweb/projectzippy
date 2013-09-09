<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>

<script language="javascript">
/* var URL;
var i ;
var QryStrValue;
URL=window.location.href ;
alert(URL+"---")
i=URL.indexOf("?");
QryStrValue=URL.substring(i+1);
alert(QryStrValue+"--QryStrValue");
if (QryStrValue!='X')
{
window.location=URL + "?X";
} */
</script>

<%
 session.invalidate(); 
response.sendRedirect("BeforeLogin.action");

%>

</head>
<body>

</body>
</html>
<center><h3> You have successfully Logged out.<br><br>


</body>
</html>