<!DOCTYPE html>
<html ng-app="bs">
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
	
<link rel="stylesheet" type="text/css" media="screen" href="/blog/resources/css/bootstrap-theme.css"/>	
<link rel="stylesheet" type="text/css" media="screen" href="/blog/resources/css/bootstrap.css"/>	
<link rel="stylesheet" type="text/css" media="screen" href="/blog/resources/css/custom.css" />

<script src="/blog/resources/js/bootstrap.js"></script>
<script src="/blog/resources/js/angular.js"></script>
<script src="/blog/resources/js/bs.js"></script>
<body>
	<div class="topbar">
		<tiles:insertAttribute name="masthead" />
	</div>
 	<div id="topnav" class="topbar">
		<tiles:insertAttribute name="topnav" />		
	</div> 
	
	<div id="container-wrap" class="row-fluid">
		<div class="container">
			<tiles:insertAttribute name="menu" />		
 			<tiles:insertAttribute name="main" />
		</div>
	</div>
 	<div id="footer" class="footer">
		<tiles:insertAttribute name="footer" />
	</div> 
</body>
</html>