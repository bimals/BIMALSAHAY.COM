<!DOCTYPE html>
<html ng-app="blogApp"
	style="padding-left: 350px; padding-right: 350px;">
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">

<!-- Optional theme -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap-theme.min.css">
	
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap-theme.min.css">
	
<link rel="stylesheet" type="text/css" media="screen" href="/web/resources/css/custom.css" />

<!-- Latest compiled and minified JavaScript -->
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
<body>
	<div class="topbar">
		<tiles:insertAttribute name="masthead" />
	</div>
	<div id="topnav" class="topbar">
		<tiles:insertAttribute name="topnav" />
	</div>
	<div id="menu" class="menu">
		<tiles:insertAttribute name="menu" />
	</div>
	<div id="container-wrap" class="row-fluid clearfix">
		<div class="container">
 			<tiles:insertAttribute name="main" />
		</div>
	</div>
	<div id="footer" class="footer">
		<tiles:insertAttribute name="footer" />
	</div>
</body>
</html>