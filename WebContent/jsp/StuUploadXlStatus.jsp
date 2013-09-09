<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%! 
	String msg ;
	String resultString;


%>
		
	<%
	
	//String BakresultString="";
if(session.getAttribute("msg") != null && session.getAttribute("result") != null){
	 msg			= (String)session.getAttribute("msg"); 
	 resultString	= (String)session.getAttribute("result"); 
	 //BakresultString =
	}

//String msg			= (String)session.getAttribute("msg"); 
//String resultString			= (String)session.getAttribute("result"); 

String[] resultSplit = resultString.split("~");

//String[] resultSplit = {"5","0","1","10"};

if(msg !=null)
session.removeAttribute("msg");
 if(resultString !=null)
session.removeAttribute("result"); 

System.out.println("msg@@"+msg+"$$"+resultString);
%>
    	<!-- DWR calls -->
	    <script 	src="<%=request.getContextPath()%>/dwr/interface/StudentDWR.js"></script>
	  	<script type="text/javascript">
	function getErrorReport(){
	//dwr for getting error details from DB
	
	//alert("Comming SOON");
		var innerHtmlone='<table width="100%" cellspacing="1" cellpadding="0" border="0"><tr><th width="1%" style="font-size:12px;border:1px solid #d7d7d7; background:#d7d7d7;line-height:25px;text-align:center; padding-left:5px"><strong>S.NO</strong></th><th width="15%" style="font-size:12px;border:1px solid #d7d7d7; background:#d7d7d7;line-height:25px;text-align:center; padding-left:5px"><strong>Row</strong></th><th width="6%" style="font-size:12px;border:1px solid #d7d7d7;background:#d7d7d7;line-height:25px;text-align:center;"><div align="center"><strong>Reason</strong></div></th></tr>';
		var z=0;
	StudentDWR.getErrorMsg(function(data){
						                                if (data == null) {													
						                                  alert("error");
						                                 
														} else {				
														//alert(data+":::"+data.length+data[1]);	                                 
						                               for(var i = 0; i < data.length; i++) {
															z++;
															    var temp = data[i];
															   //alert(data[i]);
															    	innerHtmlone += ' <tr>';
			innerHtmlone +='    <td style="font-size:12px;border:1px solid #d7d7d7;line-height:25px;text-align:left;"><div align="center">'+(i+1)+'</div></td>';
			innerHtmlone +='    <td style="font-size:12px;border:1px solid #d7d7d7;line-height:25px;text-align:left;"><div align="center">'+temp[0]+'</div></td>';
			innerHtmlone +='    <td style="font-size:12px;border:1px solid #d7d7d7;line-height:25px;text-align:left;"><div align="center">'+ temp[1]+'</div></td>';
			innerHtmlone +='  </tr>';	 
															}		                          
														}
						                         }
						      ) ;  
	//alert(innerHtmlone);//height=200,left=0,top=100,screenX=0,screenY=100'
	
	//	if(z>0){
		var myWindow = window.open("", "Schooltrix", "height=500,width=850,scrollbars=1,location=no,menubar=no,resizable=1,status=no,toolbar=no,screenX=100,screenY=100");
				myWindow.document.write(innerHtmlone);
		//}
	}
/* 	$(document).keyup(function(e) {
	alert("e.keyCode"+e.keyCode);
  if (e.keyCode == 13) { $('.save').click(); }     // enter
  if (e.keyCode == 27) {
  myWindow.close();}   // esc
}); */
	
		jQuery(document).ready(
				function(){
				//init();
				}
			);
	
		</script > 
 <div style="height:50px;"></div>
	<div class="reg_mainCon">
     <fieldset>
	    <legend><img src="img/list_add_user.PNG" class="img-circle">&nbsp;&nbsp;Student File Upload Status Report</legend>
	    <div style="padding:20px;">
	          <label style="color:#000;"><b><%=msg %></b></label>
	   <table width="100%" border="0" cellspacing="0" cellpadding="0" style="color: #000;">
		           <tr><td><label style="color:#000;"><b><%=resultSplit[3] %></b></td><td></label><label>records Uploaded</label></td></tr>
		           <tr><td><label style="color:#009B00"><b><%=resultSplit[2] %></b></td><td></label><label>new records entered</label></td></tr>
		           <tr><td><label style="color:#CCCC66;"><b><%=resultSplit[1] %></b></td><td></label><label>existing records ignored</label></td></tr>
		           <tr><td><label style="color:#FF0D0D"><b><%=resultSplit[0] %></b></td><td></label><label>records had errors <b><a style="color: #FF0D0D;"  onclick="getErrorReport();">View</a></b></label></td></tr>
		   </table>
	    </div>
	  
    </fieldset>
</div>
    