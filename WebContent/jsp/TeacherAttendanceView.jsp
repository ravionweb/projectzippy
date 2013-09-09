<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
    <link href="css/datepicker-Staff.css" rel="stylesheet" media="screen">
    
    <script type="text/javascript" src="js/bootstrap-datepicker-Staff.js"></script>
    <script type="text/javascript">		
			jQuery(document).ready(
			function () {
			jQuery('#dp5').datepicker();
		});
    </script>
    
<div class="reg_mainCon">
  <legend><img src="img/list_add_user.PNG" class="img-circle">&nbsp;&nbsp;View Attendance</legend>
  <div style="padding:20px;">
    <div class="row-fluid">
      <div class="span5">
        <div class="controls">
          <table width="100%" class="table">
            <tr>
              <td width="42%" style="border:none;">Student Name</td>
              <td width="8%" style="border:none;">:</td>
              <td width="50%" style="border:none;">Varun Kumar</td>
            </tr>
            <tr>
              <td style="border:none;">Class</td>
              <td style="border:none;">:</td>
              <td style="border:none;">VI Class</td>
            </tr>
            <tr>
              <td style="border:none;"><p>Section</p></td>
              <td style="border:none;">:</td>
              <td style="border:none;">A Section</td>
            </tr>
             <tr>
              <td height="46" style="border:none;"><p>Month</p></td>
              <td style="border:none;">:</td>
              <td style="border:none;">June</td>
            </tr>
          </table>
        </div>
      </div>
      <!--/span-->
    </div>
    <div class="row-fluid">
		<div id="dp5" data-date="12-02-2012" data-date-format="dd-mm-yyyy"></div>
        Days Absent is marked in Red
    </div>
    <hr>
  </div>
</div>
<div style="height:20px;"></div>
<!-- /container -->
<div id="footer">
  <div class="container">
    <p class="muted credit" style="text-align:center;">© 2013 SchoolTrix, All Rights reserved</p>
  </div>
</div>

<!-- Common Modal End-->

<!--  myModal_img -->
<div id="myModal_img" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
  <div class="modal-header">
    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
   </div>
  <div class="modal-body">
   
  </div>
</div>
<!--  myModal_img End-->


<!-- Notification Modal-1 -->
<div id="myModal_1" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
  <div class="modal-header">
    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
    <h3 id="myModalLabel">Notifications 1</h3>
  </div>
  <div class="modal-body">
    <p>t is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout. The point of using Lorem Ipsum is that it has a more-or-less normal distribution of letters, as opposed to using 'Content here, content here', making it look like readable English. Many desktop publishing packages and web page editors now use Lorem Ipsum as their default model text, and a search for 'lorem ipsum' will uncover many web sites still in their infancy. Various versions have evolved over the years, sometimes by accident, sometimes on purpose.</p>
  </div>
  <div class="modal-footer">
    <button class="btn" data-dismiss="modal" aria-hidden="true">Close</button>
    <button class="btn btn-warning">Save changes</button>
  </div>
</div>
<!-- Notification Modal-1 End-->



<!-- Notification Modal-2 -->
<div id="myModal_2" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
  <div class="modal-header">
    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
    <h3 id="myModalLabel">Notifications 2</h3>
  </div>
  <div class="modal-body">
    <p>t is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout. The point of using Lorem Ipsum is that it has a more-or-less normal distribution of letters, as opposed to using 'Content here, content here', making it look like readable English. Many desktop publishing packages and web page editors now use Lorem Ipsum as their default model text, and a search for 'lorem ipsum' will uncover many web sites still in their infancy. Various versions have evolved over the years, sometimes by accident, sometimes on purpose.</p>
  </div>
  <div class="modal-footer">
    <button class="btn" data-dismiss="modal" aria-hidden="true">Close</button>
    <button class="btn btn-warning">Save changes</button>
  </div>
</div>