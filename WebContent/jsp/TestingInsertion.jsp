<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<!-- 	<link href="css/bootstrap.css" type="text/css"  rel="stylesheet" media="screen">
	<link href="css/bootstrap-responsive.css"  type="text/css"  rel="stylesheet" media="screen"> -->
	<script src="http://ntr.schooltrix1.com:8080/Schooltrix/js/bootstrap-jquery.js"></script>
<script type="text/javascript">
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
		successDisp.animate({opacity: 0}, 14000);
			  //setTimeout('self.close()',11000);
			// setTimeout(function() { window.close(); }, 10000);
			  setTimeout("window.close()",10000);
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
function hhhhh(){

sucessShow("Success. This window will close in ");

}

</script>	
</head>
<body>
<!-- <form action="/Schooltrix/sction.do"> -->
<!-- <form action="TestLogin.do"   > -->
	<!-- 	<form action="TestLogin.action" method="post"  >
			Class Name:<input type="text" name="class_name"><br>
			Active::<input type="text" name="active">
							<input type="submit">
		</form> -->
		
		
		
		<div style="height:20px;text-align: center;">
		<span id ="errorspan" style="color:red;font-size: 14px;font-weight: bold;" >&nbsp;</span>
		<span id ="countDownDisp" style="color:red;font-size: 14px;font-weight: bold;" >&nbsp;</span></div>
		<button onclick='hhhhh()'  >tttttt</button>
		
</body>
</html>