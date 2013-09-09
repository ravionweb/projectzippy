<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

    <div class="reg_mainCon">
  <legend><img src="img/list_add_user.PNG" class="img-circle">&nbsp;&nbsp;Class Teachers Login</legend>
  <div style="padding:20px;">
    <div class="row-fluid">
      <div class="span4">
        <div class="controls">
          <table class="table">
            <tr>
              <td style="border:none;">Name</td>
              <td style="border:none;">:</td>
              <td style="border:none;">Varun Kumar</td>
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
          </table>
        </div>
      </div>
      <!--/span-->
    </div>
    <div class="row-fluid">
      <div class="span12">
        <div style="padding:0px 10px; border:1px #CCCCCC solid;margin-bottom:10px;">
          <h4>Notification</h4>
          <div class="alert">No New Notification</div>
          <table class="table table-bordered" width="100%" style="margin-bottom:0px;">
            <tr>
              <th width="15%">From</th>
              <th width="14%">Sent On</th>
              <th width="40%">Subject</th>
              <th width="15%" style="text-align:center;">View</th>
            </tr>
            <tr>
              <td>Administrator</td>
              <td>02-04-2013</td>
              <td><a href="#myModal_1" style="color:#666; text-decoration:none;" data-toggle="modal">School remains closed on 02-04-2013</a></td>
              <td><a href="#myModal" style="color:#666; text-decoration:none; padding-left:40px;" data-toggle="modal"><i class="icon-eye-close"></i> </a></td>
            </tr>
            <tr>
              <td>Administrator</td>
              <td>22-02-2013</td>
              <td><a href="#myModal_2" style="color:#666; text-decoration:none;" data-toggle="modal">Final Exams start from 2nd March</a></td>
              <td><a href="#myModal" style="color:#666; text-decoration:none;padding-left:40px;" data-toggle="modal"><i class="icon-eye-open"></i></a></td>
            </tr>
          </table>
          <div style="text-align:right; margin-bottom:10px; margin-right:32px; margin-top:10px;"><a href="#" style="color:#666; text-decoration:none;"><i class="icon-eye-open"></i> View All</a></div>
        </div>
      </div>
      <!--/span-->
    </div>
    <hr>
    <div class="row-fluid">
      <div class="span4">
        <div style="padding:0px 10px; border:1px #CCCCCC solid;margin-bottom:10px; min-height:223px;">
          <h4 style="text-align:center;">Utilities</h4>
          <p style="margin-bottom:0px;"> <a href="#myModal_img" class="btn" data-toggle="modal" style="display:block; margin:8px auto;  text-align:left;"><i class="icon-calendar"></i> Exam Schedule&nbsp;&nbsp;</a><a href="#myModal_img" class="btn" data-toggle="modal" style="display:block; margin:5px auto;  text-align:left;"><i class="icon-calendar"></i> School Calendar</a> <a href="#myModal_img" class="btn" data-toggle="modal" style="display:block; margin:8px auto; text-align:left;"><i class="icon-time"></i> Class Timetable</a> <a href="#myModal_img" class="btn" data-toggle="modal" style="display:block; margin:8px auto;  text-align:left;"><i class="icon-list"></i> Canteen Menu</a> <a href="#myModal_img" class="btn" data-toggle="modal" style="display:block; margin:8px auto;  text-align:left;"><i class="icon-eye-open"></i> Reports</a> <br>
          </p>
        </div>
      </div>
      <!--/span-->
      <div class="span8">
        <div style="padding:0px 10px; border:1px #CCCCCC solid;margin-bottom:10px; min-height:251px;">
          <h4>Send Quick Message</h4>
          <div>
          	<table width="100%" border="0" cellspacing="0" cellpadding="0">
              <tr>
                <td align="left" valign="middle"><span style="padding-top:5px; vertical-align: middle;"> TO :</span></td>
                <td><input type="checkbox" id="inlineCheckbox1" value="option1"></td>
                <td><span style="padding-top:5px; vertical-align: middle;">Admin</span></td>
                <td><input type="checkbox" id="inlineCheckbox2" value="option2"></td>
                <td><span style="padding-top:5px; vertical-align: middle;">Principal</span></td>
                <td><span style="padding-top:5px; vertical-align: middle;">Parent of</span></td>
                <td align="left" valign="middle">
                    <select class="selectpicker">
                      <option>Student -1</option>
                      <option>Student -2</option>                     
                    </select>
                </td>
              </tr>
			</table>
    
          </div>
          
           <textarea style="width:95%; height:93px;"></textarea>
           <p style="text-align:center;"><button class="btn btn-warning" type="button">Send</button>&nbsp;&nbsp;<input type="checkbox" id="inlineCheckbox1" value="option1"><span style="padding-top:5px; vertical-align: middle;">  Send as SMS too </p>
       </div>
      </div>
      <!--/span-->
    </div>
  </div>
</div>