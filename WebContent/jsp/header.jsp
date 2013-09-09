<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<head></head>

<meta charset="utf-8">
<title>Welcome to SchoolZippy</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="">
<meta name="author" content="">
		<!--fav and touch icons -->
<link rel="shortcut icon" href="ico/fff-pencil.ico">
	
<%

	String fname = null;
	String lName =null;
	String active =null;
	String email =null;
	String pdID = null;
//IM_ID,SM_ID,BM_ID,UM_ID,UM_ID,IM_SN
	try{
	 fname =(String) session.getAttribute("fName");
	 lName =(String) session.getAttribute("lName");
	 active =(String) session.getAttribute("active");
	 email =(String) session.getAttribute("email");
	 pdID = (String)session.getAttribute("pdID");
	System.out.println("-in header jsp--fname-"+fname+"----"+lName+"----"+email+"---"+pdID);
	 
	 
	 String userType =  (String)session.getAttribute("userType");
	 //String sn_name =  (String)session.getAttribute("IM_SN");
	 
//city,stateID
	}catch(Exception e){
	 System.out.println("exxp---in header jsp");
	e.printStackTrace();	
	}
 %>
<script type="text/javascript">
<!--
function onloadmethods() {
	   var dd1 = jQuery('#dynamic_menu').val();
       
       var selectMenu = null;
       
        var SuperAdmin ='{"School Setup":{"Create Institution":"Access.action?p1=SA_inst1&p2=SA_inst2","Create Admin User":"Access.action?p1=AddAdminUser&p2=SA_inst2"},"Communication":{"Upload Document":"#Access.action?p1=UploadDocuments&p2=SA_inst2","Send SMS":"#Access.action?p1=UserSMS&p2=SA_inst2","Send Email":"#Access.action?p1=UserEmail&p2=SA_inst2"}}';
//        var SuperAdmin ='{"School Setup":{"Create Institution":"Access.action?p1=SA_inst1&p2=SA_inst2","Create Admin User":"Access.action?p1=AddAdminUser&p2=SA_inst2"},"Communication":{"Upload Document":"Access.action?p1=UploadDocuments&p2=SA_inst2","Send SMS":"Access.action?p1=UserSMS&p2=SA_inst2","Send Email":"Access.action?p1=UserEmail&p2=SA_inst2"}}';

       //2013-06-09
/*         var Admin='{"School Setup":{"Add School":"Access.action?p1=schoolMaster&p2=SA_inst2","Add Branch":"Access.action?p1=branchMaster&p2=SA_inst2","Add Class":"Access.action?p1=comeSoon&p2=SA_inst2","Add Section":"Access.action?p1=comeSoon&p2=SA_inst2","Add Staff":"Access.action?p1=AddNonAdminUser&p2=ffats","Add Students":"Access.action?p1=AddNonAdminUser&p2=uts","Edit Students":"Access.action?p1=EditStudent&p2=SA_inst2","Add Parents":"Access.action?p1=AddNonAdminUser&p2=rap","Edit Parents":"Access.action?p1=EditParent&p2=SA_inst2"},"Communication":{"Upload Document":"Access.action?p1=UploadDocuments&p2=SA_inst2","Edit Upload Document":"Access.action?p1=EditUploadedDocuments&p2=SA_inst2","Send SMS":"Access.action?p1=UserSMS&p2=SA_inst2","Send Email":"Access.action?p1=UserEmail&p2=SA_inst2"},"Bulk Upload":{"Upload Class Master":"Access.action?p1=classMaster&p2=SA_inst2","Upload Section Master":"Access.action?p1=sectionMaster&p2=SA_inst2","Upload Student Master":"Access.action?p1=studentMaster&p2=SA_inst2"},"App Store":"Access.action?p1=AppStore&p2=SA_inst2","Knowledge Base":"Access.action?p1=comeSoon&p2=SA_inst2"}'; */
       //2013-08-12
        var Admin='{"School Setup":{"School":"Access.action?p1=EditSchools&p2=SA_inst2","Branch":"Access.action?p1=EditBranches&p2=SA_inst2","Class":"Access.action?p1=classMasterUI&p2=SA_inst2","Section":"Access.action?p1=sectionMasterUI&p2=SA_inst2","Staffs":"Access.action?p1=EditStaff&p2=SA_inst2","Students":"Access.action?p1=EditStudent&p2=SA_inst2","Parents":"Access.action?p1=EditParent&p2=SA_inst2"},"Communication":{"Upload Document":"Access.action?p1=UploadDocuments&p2=SA_inst2","Edit Upload Document":"Access.action?p1=EditUploadedDocuments&p2=SA_inst2","Send SMS":"Access.action?p1=UserSMS&p2=SA_inst2","Send Email":"Access.action?p1=UserEmail&p2=SA_inst2"},"Bulk Upload":{"Upload Class Master":"Access.action?p1=classMaster&p2=SA_inst2","Upload Section Master":"Access.action?p1=sectionMaster&p2=SA_inst2","Upload Student Master":"Access.action?p1=studentMaster&p2=SA_inst2"},"App Store":"Access.action?p1=AppStore&p2=SA_inst2","Knowledge Base":"Access.action?p1=comeSoon&p2=SA_inst2"}';
        //var Staff='{"Reports":"Access.action?p1=SA_inst1&p2=SA_inst2","text3":{"abc":"Access.action?p1=SA_inst1&p2=SA_inst2","def":"def.do","xyz":"xyz.do"}}';
        var Staff='{"Staff View":"#Access.action?p1=StaffStartUp&p2=SA_inst2","Staff Reports":{"Mark Attendance":"Access.action?p1=AttendanceMark&p2=SA_inst2","View Attendance":"Access.action?p1=AttendanceView&p2=SA_inst2","Etc":"#"}}';
        var Parent='{"Parent View":"#Access.action?p1=ParentStartUp&p2=SA_inst2"}';
		//,"Parent Reports":{"Report 1":"#Access.action?p1=SA_inst1&p2=SA_inst2","Report 2":"#","Report 3":"#"}}';
        var Student='{"Student View":"#Access.action?p1=StudentStartUp&p2=SA_inst2"}';
		//"Student Reports":{"Report 1":"#Access.action?p1=SA_inst1&p2=SA_inst2","Report 2":"#","Report 3":"#"}}';
        
        var userTypeJS = ('<%=(String)session.getAttribute("userType")%>').toUpperCase();
     
     //alert("userTypeJS--"+userTypeJS);
     if("PA" == userTypeJS){
     selectMenu = Parent;
     }else if("SA" == userTypeJS){
     selectMenu = SuperAdmin;
     }else if("AD" == userTypeJS){
     selectMenu = Admin;
     }else if("TC" == userTypeJS){
     selectMenu = Staff;
     }else if("NTS" == userTypeJS){
     selectMenu = Staff;
     }else if("ST" == userTypeJS){
     selectMenu = Student;
     }
     
     
        var items =  JSON.parse(selectMenu);

            var $ul = $("#dynamic_menu");
            jQuery("#dynamic_menu li").remove();   
            jQuery("#dynamic_menu ul").remove();   
           
        $.each(items, function(index, item) {
           
             
             if(typeof item == 'object'){
           
                         var itemsInner =item;
                         var innerhtml1="";
                         innerhtml1='<li class="dropdown"><a data-toggle="dropdown" class="dropdown-toggle" href="#">'+index +' <b class="caret"></b></a><ul class="dropdown-menu">';
                     $.each(itemsInner, function(indexinner, iteminner) {
                innerhtml1=innerhtml1+'<li><a href="'+iteminner+'">'+indexinner+'</a></li>';
                           });
                           innerhtml1=innerhtml1+'</ul></li>';
                        $ul.append(innerhtml1);   
                         
                         
              }else{
               $ul.append('<li><a href="'+item+'">'+index+'</a></li>');
               }
              });

}
//-->
</script>

	<style type="text/css">
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
<!-- Static navbar -->
<div class="navbar navbar-top">
  <div class="navbar-inner">
    <div class="container"> <a class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse"> <span class="icon-bar"></span> <span class="icon-bar"></span> <span class="icon-bar"></span> </a> <a class="brand" href="#"><span style="color:#ec7717;">School</span><span style="color:#000;">Zippy</span> Admin</a>
      <div class="nav-collapse collapse">
<!--         <ul class="nav">
          <li><a href="#">Home</a></li>
          <li><a href="#about">About</a></li>
          <li><a href="#contact">Contact</a></li>
          <li class="dropdown"> <a href="#" class="dropdown-toggle" data-toggle="dropdown">Dropdown <b class="caret"></b></a>
            <ul class="dropdown-menu">
              <li><a href="#">Action</a></li>
              <li><a href="#">Another action</a></li>
              <li><a href="#">Something else here</a></li>
              <li class="divider"></li>
              <li class="nav-header">Nav header</li>
              <li><a href="#">Separated link</a></li>
              <li><a href="#">One more separated link</a></li>
            </ul>
          </li>
        </ul> -->
        <ul class="nav pull-right"  style="margin-right: 127px;">
             <table width="100%" border="0" cellspacing="0" cellpadding="0">
                  <tr>
 <%--                  <%if(session.getAttribute("IM_ID")!=null || session.getAttribute("superadmin")!=null) {%>
                    <td width="25%">&nbsp;</td>
					<%}else{%>                    
					<td width="10%">&nbsp;</td>					
					<%} %> --%>

         <%if(session.getAttribute("IM_ID")!=null || session.getAttribute("superadmin")!=null) {%>
                    <td width="45%">         
					<div style="float:right;"><span style="font-size: 12px;font-weight: bold;padding-top: 10px;;line-height: 33px;color: black;text-decoration: none;float:left; text-align:right;width: auto;">
<!-- 					<div style="float:right;"><span style="font-size: 12px;font-weight: bold;margin: -1px 10px 0 0;line-height: 33px;color: black;text-decoration: none;float:left; text-align:right;width: auto;"> -->
					Welcome&nbsp;<%=fname+" "+lName %>&nbsp;!&nbsp;&nbsp;</span></div>         
                    <div style="margin-right: -236px;; float:right;"><a class="btn" href = "signout.action" >Logout</a></div>                    	
         <%}else{ %>
                                     	         
         <%} %>   
                    </td>
                  
                  </tr>
                </table>
        
               </ul>
      </div>
      <!--/.nav-collapse -->
    </div>
  </div>
</div>

<div class="navbar navbar-top"><!--  style="border-radius: 6px 6px 6px 6px;" -->
  <div class="navbar-inner">
    <div class="container">
     <a class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse"> 
     <span class="icon-bar"></span> <span class="icon-bar"></span> 
     <span class="icon-bar"></span> </a>           
      <div class="nav-collapse collapse">
		<ul class="nav" id="dynamic_menu">		
        </ul>
      </div>
	</div>
   </div>
 </div>
<script>
onloadmethods();
</script>




