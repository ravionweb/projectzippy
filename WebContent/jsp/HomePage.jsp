<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%
		String msgfromLogin = (String)session.getAttribute("whylogin");//may null
		System.out.println(msgfromLogin+"_-----msgfromLogin");
		if(session.getAttribute("whylogin") != null){
		session.removeAttribute("whylogin");
		}


 %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<script src="js/engine.js"></script>
	<script src="js/util.js"></script>
	<script 	src="<%=request.getContextPath()%>/dwr/interface/DomainControllDWR.js"></script>
	<script src="js/bootstrap-jquery.js"></script>
<!-- 	<script src="http://code.jquery.com/jquery-latest.js"></script> -->
	<script type="text/javascript">
	<!--
	   function resultPopUp(){
		    var msg =  "<%=msgfromLogin%>";
			    if(msg != null && msg != "null"){
			   //  alertDialog("<div style='padding-left:50px; text-align:center; margin-top: 15px;'> "+msg+".</div>");
			     alert(msg);
		    }else{
		    //alertDialog("<div style='padding-left:50px; text-align:center; margin-top: 15px;'> "+msg+".<br/> Admin ID : "+userID+"<br/>Password : "+password+"</div>");
		  //  alert("justtt");
		    }
	    }
	  function loginValidate(login){
			var patEmail = /^(([\w\-\.]+)(@((\[([0-9]{1,3}\.){3}[0-9]{1,3}\])|(([\w\-]+\.)+)([a-zA-Z]{2,4}))){0,1}){6,30}$/;
			
			
			var username = jQuery("#username").val().match(patEmail);
			var password=jQuery("#password").val().trim();
			if (!username){
			alert("Please enter valid Username");
			return false;
	 	}
	 	
	 	if (password==""){
			alert("Please enter password ");
			return false;
	 	}
	}
	
	
		 function onloadMethods(){
		     var shName = '<%=session.getAttribute("short_name")%>';
		     var superHome = '<%=session.getAttribute("superadminHome")%>';
		   //  alert("access Before"+shName);		
					                                   
		 if((shName == null || shName == "null") && (superHome == null || superHome == "null" )){		
			//alert("this is for if SN is null only");
		
			DomainControllDWR.domainControllerCheck(function(data){								
					                                   if (data =="success") {
					                                   var f = '<%=session.getAttribute("short_name")%>';
					                                 //  alert("access success"+data+"---"+f);											
														} else {		                                
						                                   //alert("access denaied page::"+data);
						                                   document.location = "accessdenied.action";         		                                   													
														}
					                        	 }
					      );  
		 
		 }else{
	//	 alert("in dont waste call to DWR session");
		 }
		 resultPopUp();
	}

	//-->
	  
    </script>
    
 
    
    
<%

	String path						= request.getContextPath();
	String scheme					= request.getScheme();
	String domainName				= request.getServerName();

		System.out.println("path--"+path+"::"+scheme+"---"+domainName+"---sesssionn--"+session.getAttribute("short_name"));
			//String realll = request.getRealPath("bhanu");
		//System.out.println("---"+realll);

 %>
    
<meta charset="utf-8">
<title>Welcome to SchoolZippy</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="">
<meta name="author" content="">
<!-- Bootstrap -->
<link href="css/bootstrap.css" rel="stylesheet" media="screen">
<link href="css/bootstrap-responsive.css" rel="stylesheet" media="screen">
<!-- HTML5 shim, for IE6-8 support of HTML5 elements -->
<!--[if lt IE 9]>
      <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
    <![endif]-->
<!--fav and touch icons -->
<link rel="shortcut icon" href="ico/fff-pencil.ico">
<link rel="apple-touch-icon-precomposed" sizes="144x144" href="ico/apple-touch-icon-144-precomposed.png">
<link rel="apple-touch-icon-precomposed" sizes="114x114" href="ico/apple-touch-icon-114-precomposed.png">
<link rel="apple-touch-icon-precomposed" sizes="72x72" href="ico/apple-touch-icon-72-precomposed.png">
<link rel="apple-touch-icon-precomposed" href="ico/apple-touch-icon-57-precomposed.png">
</head>
<body>
<!-- Static navbar -->
<div class="navbar navbar-top">
  <div class="navbar-inner">
    <div class="container"><a class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse"> <span class="icon-bar"></span> <span class="icon-bar"></span> <span class="icon-bar"></span> </a> <a class="brand" href="#"><span style="color:#ec7717;">School</span><span style="color:#000;">Zippy</span> Admin</a>
      <!-- <div class="nav-collapse collapse">
        <ul class="nav">
          <li><a href="#">Home</a></li>
          <li><a href="#about">About</a></li>
          <li><a href="#contact">Contact</a></li>
          <li class="dropdown"> <a href="#" class="dropdown-toggle" data-toggle="dropdown">Dropdown </a> 
            <ul class="dropdown-menu"> 
              <li><a href="#">Action</a></li> 
              <li><a href="#">Another action</a></li> 
              <li><a href="#">Something else here</a></li> 
              </ul></li><li class="dropdown"><br></li><li class="dropdown"><br></li><li class="dropdown"><br></li><li class="dropdown"><br><ul class="dropdown-menu"> 
              <li class="nav-header">Nav header</li> 
              <li><a href="#">Separated link</a></li> 
              <li><a href="#">One more separated link</a></li> 
            </ul> 
          </li>
        </ul>
        <ul class="nav pull-right">
          <li class="active"><a href="#">Admin</a></li>
          <li><a href="#">Logout</a></li>
        </ul>
      </div> -->
      <!--/.nav-collapse -->
    </div>
  </div>
</div>
<div class="container">
  <div style="margin:80px 0px;">
<form action="login.action"   class="form-signin"  method="post" onsubmit="return loginValidate(this)">
      <h2 class="form-signin-heading">Please sign in</h2>
      <input type="text" class="input-block-level" placeholder="Email address" id="username"name="username" >
      <input type="password" class="input-block-level" placeholder="Password" id="password" name="password">
      <label class="checkbox">
      <input type="checkbox" value="remember-me">
      Remember me </label>
      <button class="btn btn-warning" type="submit">Sign in</button>
    </form>
  </div>
</div>
<!-- /container -->
<footer class="footer" style="position:relative;bottom:0px;width:100%;">
  <div class="container">
    <p class="pull-right"><a href="#">Back to top</a></p>
    <ul class="footer-links">
      <li><a href="http://blog.getbootstrap.com">Blog</a></li>
      <li class="muted">·</li>
      <li><a href="https://github.com/twitter/bootstrap/issues?state=open">Issues</a></li>
      <li class="muted">·</li>
      <li><a href="https://github.com/twitter/bootstrap/wiki">Roadmap and changelog</a></li>
    </ul>
  </div>
</footer>
<script src="js/bootstrap.js"></script>
<script>

</script>
   <script type="text/javascript">
	onloadMethods();
</script>
</body>
</html>
