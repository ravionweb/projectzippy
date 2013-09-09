<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%> 

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>SchoolZippy</title>

<link href="../css/bootstrap.css" rel="stylesheet" media="screen">
<link href="http://jbutz.github.io/bootstrap-lightbox/assets/css/bootstrap-lightbox.min.css" rel="stylesheet" media="screen">
<link href="../css/bootstrap-responsive.css" rel="stylesheet" media="screen">


<!-- <script src="http://jbutz.github.io/bootstrap-lightbox/assets/js/jquery.min.js"></script> -->
<script src="../js/bootstrap-jquery.js"></script>

 <script src="../js/bootstrap.js"></script> 
<!-- <script src="http://jbutz.github.io/bootstrap-lightbox/assets/js/bootstrap.min.js"></script> -->
<script src="http://jbutz.github.io/bootstrap-lightbox/assets/js/bootstrap-lightbox.min.js"></script>

<link rel="apple-touch-icon-precomposed" href="ico/apple-touch-icon-57-precomposed.png">
<!--
<script src="js/bootstrap-transition.js"></script>
<script src="js/bootstrap-modal.js"></script> -->

<script type="text/javascript">
     jQuery(document).ready(function () {

					
		});
		
		function testtt() {
			$('#demoLightbox').lightbox('show');
		}
</script>


<!--     <div id="demoLightbox" class="lightbox hide fade" tabindex="-1" role="dialog" aria-hidden="true">
    <div class='lightbox-content'>
  <img src="../downloadImage.action?type=TC&amp;fileName=fdLdY3xKLm.jpg&amp;admNumber=suman@gmail.com">
    <div class="lightbox-caption"><p>Your caption here</p></div>
    </div>
    </div> -->
    
  <div aria-hidden="true" role="dialog" tabindex="-1" class="lightbox hide fade" id="demoLightbox" style="position: fixed; width: 794px; height: 534px; top: 0px; left: 50%; margin-left: -397px; display: none;">
			<div class="lightbox-content" style="width: 774px; height: 514px;">
<!-- 				<img src="assets/img/large.png"> -->
  <img src="../downloadImage.action?type=TC&amp;fileName=fdLdY3xKLm.jpg&amp;admNumber=suman@gmail.com">
				<div class="lightbox-caption"><a id="undefined" href="../downloadImage.action?type=TC&amp;fileName=fdLdY3xKLm.jpg&amp;admNumber=suman@gmail.com">
				<i >Download</i> </a></div>
			</div>
		</div>

<!-- <img src="../downloadImage.action?type=TC&amp;fileName=fdLdY3xKLm.jpg&amp;admNumber=suman@gmail.com"> -->
<i class="icon-eye-open" onclick="testtt();"></i>

<!-- <script src="js/jquery.min.js"></script> -->
<!-- <script src="js/jQuery-Plugins.js"></script> -->
</head>
<body>


</body>

</html>